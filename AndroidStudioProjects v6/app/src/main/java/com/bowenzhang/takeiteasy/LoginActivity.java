package com.bowenzhang.takeiteasy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private EditText userEmail;
    private EditText userPass;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userEmail=(EditText)findViewById(R.id.userEmail);
        userPass=(EditText)findViewById(R.id.userPass);
        mAuth= FirebaseAuth.getInstance();

    }

    public void loginButtonClicked(View view){

        final ProgressDialog progressDialog= ProgressDialog.show(LoginActivity.this,"Please wait ....","Processing ...", true);
        mAuth.signInWithEmailAndPassword(userEmail.getText().toString(),userPass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"Login Successful!",Toast.LENGTH_LONG).show();
                    Intent loginIntent=new Intent(LoginActivity.this,MenuActivity.class);
                    loginIntent.putExtra("Email",mAuth.getCurrentUser().getEmail());
                    OrderlistMemory.Instance().setUsername(userEmail.getText().toString());
                    startActivity(loginIntent);
                }else{
                    Log.e("Error",task.getException().toString());
                    Toast.makeText(LoginActivity.this,task.getException().toString(),Toast.LENGTH_LONG).show();

                }

            }
        });

    }


}