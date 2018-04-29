package com.bowenzhang.takeiteasy;


import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.*;


/**
 * Created by liuyi on 4/8/18.
 */

public class OrderstatusActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    ArrayList<String> quantity = new ArrayList<>();
    private static int kitchenBurger =50;
    private static int kitchenChicken =50;
    private static int kitchenOnion =50;
    private static int kitchenFrenchFries =50;
    private Button checkStatus;
    NotificationCompat.Builder submitNot;
    private static final int submitNotID = 1;
    NotificationCompat.Builder prepareNot;
    private static final int prepareNotID = 2;
    NotificationCompat.Builder pickuptNot;
    private static final int pickupNotID = 1;
    private Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderstatus);
        Bundle bundle = getIntent().getExtras();
        String user_id = bundle.getString("userID");
        System.out.println(user_id);

        //Read Inventory text file ----------------------------------------
        readInventory();

        //Read from inventory list every hour ------------------------------

        final Handler handler = new Handler();
        Timer    timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @SuppressWarnings("unchecked")
                    public void run() {
                        try {
                            readInventory();
                        }
                        catch (Exception e) {
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 3600000);

        //Get the quantity of items from users ---------------------------------------------------------
        int uBurger = Integer.parseInt(OrderlistMemory.Instance().getBurgerNum());
        int uChicken = Integer.parseInt(OrderlistMemory.Instance().getChickenNum());
        int uOnion = Integer.parseInt(OrderlistMemory.Instance().getOnionNum());
        int uFrenchFries = Integer.parseInt(OrderlistMemory.Instance().getFrenchfrisNum());

        //if the kitchen quantity becomes zero read from inventory to get more items------------------
        if ( (uBurger >0 && kitchenBurger ==0) || (uChicken>0 && kitchenChicken==0)
                ||(uOnion>0 &&kitchenOnion==0) ||(uFrenchFries>0 && kitchenFrenchFries==0)) {
            readInventory();
        }

        // if inventory is zero ask for partial order.-----------------------------------------------
        if ( (uBurger >0 && kitchenBurger ==0) || (uChicken>0 && kitchenChicken==0)
                ||(uOnion>0 &&kitchenOnion==0) ||(uFrenchFries>0 && kitchenFrenchFries==0)) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setMessage("We are sorry we cannot process your complete order. " +
                    "Do you want partial order?").setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getApplicationContext(), "Placing partial order", Toast.LENGTH_LONG).show();
                        }

                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(OrderstatusActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }
                    });

            AlertDialog alert = alertDialog.create();
            alert.setTitle("OOPS!!");
            alert.show();
        }

        //notification for order submitted ---------------------------------------------------------
        submitNot = new NotificationCompat.Builder(this);
        submitNot.setAutoCancel(true);
        //notification for order preparing --------------------------------------------------------
        prepareNot = new NotificationCompat.Builder(this);
        prepareNot.setAutoCancel(true);
        //notification for order pickup --------------------------------------------------------
        pickuptNot = new NotificationCompat.Builder(this);
        pickuptNot.setAutoCancel(true);

        submitNot.setSmallIcon(R.drawable.ic_launcher_background);
        submitNot.setTicker("Order Update!");
        submitNot.setWhen(System.currentTimeMillis());
        submitNot.setContentTitle("Order Update.");
        submitNot.setContentText("Your order has been submitted.");
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(submitNotID,submitNot.build());


        TextView Finalorderlist = (TextView) findViewById(R.id.Finalorderlist);
        checkStatus = (Button)findViewById(R.id.checkStatus);
        checkStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        long futureTime1 = System.currentTimeMillis() + 10000;
                        while(System.currentTimeMillis()<futureTime1) {
                            synchronized (this){
                                try {
                                    wait(futureTime1-System.currentTimeMillis());
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                        handler2.sendEmptyMessage(0);

                        long futureTime2 = System.currentTimeMillis() + 10000;
                        while(System.currentTimeMillis()<futureTime2) {
                            synchronized (this){
                                try {
                                    wait(futureTime2-System.currentTimeMillis());
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                        handler1.sendEmptyMessage(0);
                    }
                };
                Thread t1 = new Thread(r);
                t1.start();
            }
        });
        String  list =
                "Burger: "+OrderlistMemory.Instance().getBurgerNum()+"\n"+
                        "Onion: "+OrderlistMemory.Instance().getOnionNum()+"\n"+
                        "Chicken: "+OrderlistMemory.Instance().getChickenNum()+"\n"+
                        "Frenchfris: "+OrderlistMemory.Instance().getFrenchfrisNum();
        Finalorderlist.setText(list);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Orders").child(user_id);

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                quantity.add(value);
//               int uBurger = Integer.parseInt(quantity.get(0));
//               int uChicken = Integer.parseInt(quantity.get(1));
//               int uOnion = Integer.parseInt(quantity.get(2));
//               int uFrenchFries = Integer.parseInt(quantity.get(3));

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        logout = findViewById(R.id.Finish);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderstatusActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


    }
    Handler handler2 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            TextView statusChange = (TextView)findViewById(R.id.status);
            statusChange.setText("Preparing");
            prepareNot.setSmallIcon(R.drawable.ic_launcher_background);
            prepareNot.setTicker("Order Update!");
            prepareNot.setWhen(System.currentTimeMillis());
            prepareNot.setContentTitle("Order Update.");
            prepareNot.setContentText("Your order is being prepared.");
            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            nm.notify(prepareNotID,prepareNot.build());
        }
    };

    Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            TextView statusChange = (TextView)findViewById(R.id.status);
            statusChange.setText("Ready for pickup");
            pickuptNot.setSmallIcon(R.drawable.ic_launcher_background);
            pickuptNot.setTicker("Order Update!");
            pickuptNot.setWhen(System.currentTimeMillis());
            pickuptNot.setContentTitle("Order Update.");
            pickuptNot.setContentText("Your order is ready for pickup.");
            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            nm.notify(pickupNotID,pickuptNot.build());
        }
    };

    public void readInventory() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("Inventory.txt")));
            String line  = reader.readLine();
            String[] lineVector;
            lineVector = line.split(",");
            long invBurg = Long.parseLong(lineVector[0]);
            long invChicken = Long.parseLong(lineVector[1]);
            long invOnion = Long.parseLong(lineVector[2]);
            long invFrench = Long.parseLong(lineVector[3]);

            int burgerCal = 50 -kitchenBurger;
            invBurg = invBurg-burgerCal;
            String burgerWrite = String.valueOf(invBurg);
            int chickenCal = 50 - kitchenChicken;
            invChicken = invChicken-chickenCal;
            String chickenWrite = String.valueOf(invChicken);
            int onionCal = 50-kitchenOnion;
            invOnion =invOnion-onionCal;
            String onionWrite = String.valueOf(invOnion);
            int frenchCal = 50-kitchenFrenchFries;
            invFrench = invFrench-frenchCal;

            kitchenBurger =50;
            kitchenChicken =50;
            kitchenOnion =50;
            kitchenFrenchFries =50;

        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Error reading file!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                    Toast.makeText(getApplicationContext(),"Error reading file!",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }

    }
}
