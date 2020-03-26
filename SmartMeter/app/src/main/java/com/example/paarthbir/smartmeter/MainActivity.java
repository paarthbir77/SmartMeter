package com.example.paarthbir.smartmeter;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    int status =0;
    String USER = "Username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if (intent.hasExtra("username"))
        {
            Bundle bundle = intent.getExtras();
            USER = bundle.getString("username");
            status = 1;
        }

        TextView textView = findViewById(R.id.greetuser);
        TextView textView1 = findViewById(R.id.amount);
        //TextView textView1 = findViewById(R.id.fillusername_drawer);
        textView.setText("HI " +  USER);
        //textView1.setText(USER);
        Random rn = new Random();
        int amt = rn.nextInt(2500) + 1;
        String amnt = String.valueOf(amt);

        if (status == 1)
        {
            textView1.setText(amnt);
        }
        Button button = findViewById(R.id.pay);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Toast.makeText(MainActivity.this, "Opening..",
                                                  Toast.LENGTH_SHORT).show();
                                          PackageManager pm = getPackageManager();
                                          Intent intent = pm.getLaunchIntentForPackage("net.one97.paytm");
                                          if (intent != null) {
                                              startActivity(intent);
                                          }

                                      }
                                  });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) header.findViewById(R.id.fillusername_drawer);
        navUsername.setText(USER);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        TextView textView = findViewById(R.id.greetuser);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) header.findViewById(R.id.fillusername_drawer);


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            if(status == 1)
            {
                Toast.makeText(MainActivity.this, "Logged Out..",
                        Toast.LENGTH_SHORT).show();
                status = 0;
                USER = "Username";
                textView.setText("Hi " + USER);
                TextView textView1 = findViewById(R.id.amount);
                textView1.setText("Rs. XXXX");
                navUsername.setText(USER);
                return true;
            }
            else
            {
                Toast.makeText(MainActivity.this, "Log In First..",
                        Toast.LENGTH_SHORT).show();
                return true;
            }

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_login) {
            // Handle the camera action
            if(status == 0){
                Intent i=new Intent(MainActivity.this,Login.class);
                startActivity(i);
                return true;
            }
            else
            {
                Toast.makeText(MainActivity.this, "Already Logged in",
                        Toast.LENGTH_SHORT).show();
            }
        }
        else if (id == R.id.nav_schedule) {
            if (status == 1){
                Intent i=new Intent(MainActivity.this,ScheduleDevice.class);
                i.putExtra("username", USER);
                startActivity(i);
                return true;
            }
            else
            {
                Toast.makeText(MainActivity.this, "Must be logged in",
                        Toast.LENGTH_SHORT).show();
            }

        }
        else if (id == R.id.nav_stats) {
            if (status == 1){
                Intent i=new Intent(MainActivity.this,Statistics.class);
                startActivity(i);
                i.putExtra("username", USER);
                return true;

            }
            else
            {
                Toast.makeText(MainActivity.this, "Must be logged in",
                        Toast.LENGTH_SHORT).show();
            }

        }
        else if (id == R.id.nav_security) {
            Intent i=new Intent(MainActivity.this,Security.class);
            startActivity(i);
            return true;

        }
        else if(id == R.id.nav_contact){
            Intent i=new Intent(MainActivity.this,contact.class);
            startActivity(i);
            return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
