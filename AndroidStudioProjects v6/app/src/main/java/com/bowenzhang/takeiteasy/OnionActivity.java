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

public class OnionActivity extends AppCompatActivity {
    private String name,price,image,desc;
    public OnionActivity(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onion);

        Button orderonion = (Button) findViewById(R.id.orderonion);
        Button onionbuttonminus = (Button) findViewById(R.id.onionbuttonminus);
        Button onionbuttonplus = (Button) findViewById(R.id.onionbuttonplus);
        final EditText onionnumber = (EditText) findViewById(R.id.onionnumber);

        orderonion.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View view) {

                OrderlistMemory.Instance().setOnionNum(onionnumber.getText().toString());

                        Intent onionIntent = new Intent(OnionActivity.this, MenuActivity.class);
                startActivity(onionIntent);
            }
        });
        onionbuttonminus.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View view) {
                int num = Integer.valueOf(onionnumber.getText().toString());
                if(num>0){
                    num--;
                    onionnumber.setText(Integer.toString(num));
                }
            }
        });
        onionbuttonplus.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View view) {
                int num = Integer.valueOf(onionnumber.getText().toString());
                num++;
                onionnumber.setText(Integer.toString(num));
            }
        });
    }
}

