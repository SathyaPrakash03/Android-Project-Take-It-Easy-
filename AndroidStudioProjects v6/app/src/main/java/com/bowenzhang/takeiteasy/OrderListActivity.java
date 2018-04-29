package com.bowenzhang.takeiteasy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.*;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by liuyi on 4/4/18.
 */

public class OrderListActivity extends AppCompatActivity {

    private DatabaseReference OrderDatabase;
    private FirebaseAuth OAuth;
    private String orderlistshow;
    private String username;
    private String burgerquantity;
    private String onionquantity;
    private String chickenquantity;
    private String frenchquantity;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderlist);

        OAuth= FirebaseAuth.getInstance();
        OrderDatabase= FirebaseDatabase.getInstance().getReference().child("Orders");

        TextView OrderList = (TextView) findViewById(R.id.OrderList);
        orderlistshow = "UserName: "+OrderlistMemory.Instance().getUsername()+"\n"
                +"Burger: "+OrderlistMemory.Instance().getBurgerNum()+"\n"+
                "Onion: "+OrderlistMemory.Instance().getOnionNum()+"\n"+
                "Chicken: "+OrderlistMemory.Instance().getChickenNum()+"\n"+
                "Frenchfris: "+OrderlistMemory.Instance().getFrenchfrisNum();
        OrderList.setText(orderlistshow);
        burgerquantity=OrderlistMemory.Instance().getBurgerNum();
        onionquantity=OrderlistMemory.Instance().getOnionNum();
        chickenquantity=OrderlistMemory.Instance().getChickenNum();
        frenchquantity=OrderlistMemory.Instance().getFrenchfrisNum();
        username=OrderlistMemory.Instance().getUsername();
        TextView Cost = (TextView) findViewById(R.id.Cost);
        Cost.setText("\nSubtotal: $"+OrderlistMemory.Instance().getTotal() );

        Button Submit = (Button) findViewById(R.id.Submitorder);
        Submit.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view) {

                String user_id=OAuth.getCurrentUser().getUid();
                DatabaseReference current_user = OrderDatabase.child(user_id);
                current_user.child("username").setValue(username);
                current_user.child("burgerquantity").setValue(burgerquantity);
                current_user.child("onionquantity").setValue(onionquantity);
                current_user.child("chickenquantity").setValue(chickenquantity);
                current_user.child("frenchquantity").setValue(frenchquantity);


                Intent FinishIntent = new Intent(OrderListActivity.this, OrderstatusActivity.class);
                FinishIntent.putExtra("userID",user_id);
                startActivity(FinishIntent);
            }
        });
    }


    public void ChangeOrder(View view){
        Intent ChangeOrderrIntent=new Intent(OrderListActivity.this,MenuActivity.class);
        startActivity(ChangeOrderrIntent);
    }

}
