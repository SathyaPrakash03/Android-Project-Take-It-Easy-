package com.bowenzhang.takeiteasy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by bowenzhang on 2018/4/3.
 */

public class BurgerActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burger);


        Button orderburgers = (Button) findViewById(R.id.orderburgers);
        Button burgerbuttonminus = (Button) findViewById(R.id.burgerbuttonminus);
        Button burgerbuttonplus = (Button) findViewById(R.id.burgerbuttonplus);
        final EditText burgersnumber = (EditText) findViewById(R.id.burgersnumber);

        orderburgers.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View view) {

                OrderlistMemory.Instance().setBurgerNum(burgersnumber.getText().toString());

                Intent burgerIntent = new Intent(BurgerActivity.this, MenuActivity.class);
                startActivity(burgerIntent);
            }
        });
        burgerbuttonminus.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View view) {
                int num = Integer.valueOf(burgersnumber.getText().toString());
                if(num>0){
                    num--;
                    burgersnumber.setText(Integer.toString(num));
                }
            }
        });
        burgerbuttonplus.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View view) {
                int num = Integer.valueOf(burgersnumber.getText().toString());
                num++;
                burgersnumber.setText(Integer.toString(num));
            }
        });
    }
}


