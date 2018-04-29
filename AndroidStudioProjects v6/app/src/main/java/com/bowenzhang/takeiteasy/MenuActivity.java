package com.bowenzhang.takeiteasy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button CheckoutButtonClicked = (Button) findViewById(R.id.CheckoutButtonClicked);
        CheckoutButtonClicked.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View view) {

                OrderlistMemory.Instance().calculatecost("0");

                Intent CheckoutIntent = new Intent(MenuActivity.this, OrderListActivity.class);
                startActivity(CheckoutIntent);
            }
        });
    }

    public void order1ButtonClicked(View view){
        Intent burgerIntent=new Intent(MenuActivity.this,BurgerActivity.class);
        startActivity(burgerIntent);
    }
    public void order2ButtonClicked(View view){
        Intent chickensIntent=new Intent(MenuActivity.this,ChickensActivity.class);
        startActivity(chickensIntent);
    }
    public void order3ButtonClicked(View view){
        Intent FrenchfriesIntent=new Intent(MenuActivity.this,FrenchfrisActivity.class);
        startActivity(FrenchfriesIntent);
    }
    public void order4ButtonClicked(View view){
        Intent onionIntent=new Intent(MenuActivity.this,OnionActivity.class);
        startActivity(onionIntent);
    }
}

