package com.example.surendrasingh.coffeeshop;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class login_activity extends AppCompatActivity {

    EditText email,password;
    Button login,signup;
    FirebaseAuth mauth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.pass);
        login=(Button)findViewById(R.id.login);
        signup=(Button)findViewById(R.id.sign) ;
        mauth=FirebaseAuth.getInstance();
        mauth.signOut();
        if(mauth.getCurrentUser()!=null)
        {
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
        else {


            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String mail = email.getText().toString().trim();
                    String pass = password.getText().toString().trim();

                    if (!TextUtils.isEmpty(mail) && (!TextUtils.isEmpty(pass))) {
                        mauth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(login_activity.this, MainActivity.class));
                                    finish();
                                } else
                                    Toast.makeText(getApplicationContext(), "Error OCc :" + task.getException(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                }
            });

            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent log = new Intent(login_activity.this, sign_up_activity.class);
                    startActivity(log);
                }
            });
        }
    }
}

