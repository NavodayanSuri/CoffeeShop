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

public class sign_up_activity extends AppCompatActivity {
    EditText mil,nm,dob,ps;
    Button reg;
    FirebaseAuth mauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);
        mil=(EditText)findViewById(R.id.mil);
        ps=(EditText)findViewById(R.id.ps);
        nm=(EditText)findViewById(R.id.firstname);
        dob=(EditText)findViewById(R.id.DOB);
        reg=(Button)findViewById(R.id.regi);
        mauth=FirebaseAuth.getInstance();

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=nm.getText().toString().trim();
                String pass=ps.getText().toString().trim();
                String db=dob.getText().toString().trim();
                final String mail=mil.getText().toString().trim();

                if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(pass)&&!TextUtils.isEmpty(db)&&!TextUtils.isEmpty(mail)){
                    mauth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"register sucessful \n redircting to login page",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(sign_up_activity.this,login_activity.class));
                            }
                        }
                    });
                }
                else
                    Toast.makeText(sign_up_activity.this,"Incomplete Data !!try again",Toast.LENGTH_LONG).show();

            }
        });

    }
}
