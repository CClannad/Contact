package com.example.zc.mainactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.litepal.LitePal;

public class Update extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_layout);
        Button cancel_update_list = (Button)findViewById(R.id.cancel_update_list);
        Button update_list = (Button)findViewById(R.id.update_people);
        final EditText tel = (EditText)findViewById(R.id.update_tel);
        final EditText name = (EditText)findViewById(R.id.update_name);
        final String del_data = getIntent().getStringExtra("extra_data");
        cancel_update_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Update.this,MainActivity.class);
                startActivity(intent);
            }
        });
        update_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.deleteAll(People.class,"telphone=?",del_data);
                People person = new People();
                person.setName(name.getText().toString());
                person.setTel(tel.getText().toString());
                person.save();
                Intent intent = new Intent(Update.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
