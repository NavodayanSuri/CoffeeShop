package com.example.surendrasingh.coffeeshop;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button inc,dec,porder,signout;
    TextView item;
    int count=0;
    EditText name,phone,table;
    Spinner spin;
    private DatabaseReference mref;
    private FirebaseDatabase firebaseDatabase=null;
    CheckBox check1,check2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inc=(Button)findViewById(R.id.inc);
        dec=(Button)findViewById(R.id.dec);
        item=(TextView)findViewById(R.id.item);
        name=(EditText)findViewById(R.id.editname);
        table=(EditText)findViewById(R.id.tab);
        phone=(EditText)findViewById(R.id.phone);
        porder=(Button)findViewById(R.id.porder);
        spin=(Spinner)findViewById(R.id.cofee);
        mref=FirebaseDatabase.getInstance().getReference("new order");
        check1=(CheckBox)findViewById(R.id.hot);
        check2=(CheckBox)findViewById(R.id.cold);
        signout=(Button)findViewById(R.id.out);



        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.Coffee_type,android.R.layout.simple_spinner_item);
       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);



        inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                item.setText("0"+count);

            }
        });
        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count>0)
                    count--;
                item.setText("0"+count);

            }
        });
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,login_activity.class));
            }
        });

        porder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder temp=new StringBuilder();
                if (check1.isChecked())
                    temp.append("hot");
                if (check2.isChecked())
                    temp.append("cold");
                 String nm=name.getText().toString().trim();
                 String ph=phone.getText().toString().trim();
                 String sp=spin.getSelectedItem().toString().trim();
                 String cup=""+count;
                 String tb=table.getText().toString().trim();
                 order item=new order(nm,ph,sp,cup,tb,temp.toString());
                if (!TextUtils.isEmpty(nm) &&!TextUtils.isEmpty(ph) &&!TextUtils.isEmpty(sp)&&count!=0&&!TextUtils.isEmpty(tb)&&!TextUtils.isEmpty(temp)) {
                    Toast.makeText(MainActivity.this, "Order Succesfully placed", Toast.LENGTH_LONG).show();
                    mref.push().setValue(item);
                }
                    else
                        Toast.makeText(MainActivity.this,"!!!Fill All Details Correctly",Toast.LENGTH_LONG).show();

                }
            });
    }

    }

