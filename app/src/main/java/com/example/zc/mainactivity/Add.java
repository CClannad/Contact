package com.example.zc.mainactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Add extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_layout);
        Button cancel_list = (Button)findViewById(R.id.cancel_add_list);
        Button add_list = (Button)findViewById(R.id.add_people);
        final EditText tel = (EditText)findViewById(R.id.add_tel);
        final EditText name = (EditText)findViewById(R.id.add_name);
        cancel_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add.this,MainActivity.class);
                startActivity(intent);
            }
        });
        add_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                People person = new People();
                person.setName(name.getText().toString());
                person.setTel(tel.getText().toString());
                person.save();
                Intent intent = new Intent(Add.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
