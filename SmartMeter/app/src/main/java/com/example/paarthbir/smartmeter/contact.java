package com.example.paarthbir.smartmeter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

public class contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        LinearLayout lweb = (LinearLayout) findViewById(R.id.contact_website);
        LinearLayout lcall = (LinearLayout) findViewById(R.id.contact_helpline);
        lweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="https://www.eeslindia.org/content/raj/eesl/en/home.html";
                Intent i= new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        lcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="tel:0000000000";
                Intent i=new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                startActivity(i);
            }
        });

    }

}
