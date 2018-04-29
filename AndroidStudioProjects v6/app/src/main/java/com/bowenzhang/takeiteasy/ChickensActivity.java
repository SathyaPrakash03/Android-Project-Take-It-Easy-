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

public class ChickensActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chickens);

        Button orderochickens = (Button) findViewById(R.id.orderochickens);
        Button chickenbuttonminus = (Button) findViewById(R.id.chickenbuttonminus);
        Button chickenbuttonplus = (Button) findViewById(R.id.chickenbuttonplus);
        final EditText chickensnumber = (EditText) findViewById(R.id.chickensnumber);

        orderochickens.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View view) {

                OrderlistMemory.Instance().setChickenNum(chickensnumber.getText().toString());

                Intent ChickenIntent = new Intent(ChickensActivity.this, MenuActivity.class);
                startActivity(ChickenIntent);
            }
        });
        chickenbuttonminus.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View view) {
                int num = Integer.valueOf(chickensnumber.getText().toString());
                if(num>0){
                    num--;
                    chickensnumber.setText(Integer.toString(num));
                }
            }
        });
        chickenbuttonplus.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View view) {
                int num = Integer.valueOf(chickensnumber.getText().toString());
                num++;
                chickensnumber.setText(Integer.toString(num));
            }
        });
    }
}

