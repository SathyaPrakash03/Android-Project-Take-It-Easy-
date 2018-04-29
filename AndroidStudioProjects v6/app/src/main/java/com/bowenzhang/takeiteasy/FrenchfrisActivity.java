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

public class FrenchfrisActivity extends AppCompatActivity {
    private String name,price,image,desc;
    public FrenchfrisActivity(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frenchfries);

        Button orderfrenchfries = (Button) findViewById(R.id.orderfrenchfries);
        Button frebuttonminus = (Button) findViewById(R.id.frebuttonminus);
        Button frebuttonplus = (Button) findViewById(R.id.frebuttonplus);
        final EditText frenchfriesnumber = (EditText) findViewById(R.id.frenchfriesnumber);

        orderfrenchfries.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View view) {

                OrderlistMemory.Instance().setFrenchfrisNum(frenchfriesnumber.getText().toString());

                Intent FrenchfrisIntent = new Intent(FrenchfrisActivity.this, MenuActivity.class);
                startActivity(FrenchfrisIntent);
            }
        });
        frebuttonminus.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View view) {
                int num = Integer.valueOf(frenchfriesnumber.getText().toString());
                if(num>0){
                    num--;
                    frenchfriesnumber.setText(Integer.toString(num));
                }
            }
        });
        frebuttonplus.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View view) {
                int num = Integer.valueOf(frenchfriesnumber.getText().toString());
                num++;
                frenchfriesnumber.setText(Integer.toString(num));
            }
        });
    }
}
