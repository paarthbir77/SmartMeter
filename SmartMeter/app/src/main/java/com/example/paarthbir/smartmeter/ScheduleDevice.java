package com.example.paarthbir.smartmeter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class ScheduleDevice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_device);
        final String USER;
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        USER = bundle.getString("username");
        LinearLayout add = (LinearLayout) findViewById(R.id.add_device);
        LinearLayout view_device = (LinearLayout) findViewById(R.id.view_device);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ScheduleDevice.this,AddDevice.class);
                i.putExtra("username", USER);
                startActivity(i);
            }
        });
        view_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ScheduleDevice.this,ViewDevices.class);
                i.putExtra("username", USER);
                startActivity(i);
            }
        });


    }

}
