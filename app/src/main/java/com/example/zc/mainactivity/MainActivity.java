package com.example.zc.mainactivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.PeriodicSync;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<People> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.contact_list);
        contactDisplay();


        //添加用户界面
        Button add_people = (Button) findViewById(R.id.action_add_people);
        add_people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Add.class);
                startActivity(intent);
            }
        });
        //listView显示
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final People person = data.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View v = View.inflate(MainActivity.this,R.layout.dialog,null);
                final AlertDialog dialog = builder.create();
                dialog.setView(v);
                dialog.setTitle(person.getName()+"    "+person.getTel());
                dialog.show();
                dialog.getWindow().setGravity(Gravity.CENTER);
                Button delete = dialog.findViewById(R.id.delete_person);
                Button call = dialog.findViewById(R.id.call_person);
                Button update = dialog.findViewById(R.id.update_person);
                Button message = dialog.findViewById(R.id.send_message);
                //打电话
                call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:"+person.getTel()));
                        startActivity(intent);
                        dialog.cancel();
                    }
                });
                //发送短信
                message.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("sms:"+person.getTel()));
                        startActivity(intent);
                        dialog.cancel();
                    }
                });
                //删除用户
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LitePal.deleteAll(People.class,"telphone=?",person.getTel());
                        contactDisplay();
                        dialog.cancel();
                    }
                });
                //更新用户信息
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String del_data = person.getTel();
                        Intent intent = new Intent(MainActivity.this, Update.class);
                        intent.putExtra("extra_data",del_data);
                        startActivity(intent);
                    }
                });

            }

        });
    }
    //显示Item
    public void contactDisplay(){
        ListView listView = (ListView) findViewById(R.id.contact_list);
        data.clear();
        List<People> allperson = LitePal.findAll(People.class);
        for (People perso : allperson) {
            People temp = new People();
            temp.setName(perso.getName());
            temp.setTel(perso.getTel());
            data.add(temp);
        }
        MyAdapter adapter = new MyAdapter(
                MainActivity.this, R.layout.show, data);
        listView.setAdapter(adapter);
    }
}
