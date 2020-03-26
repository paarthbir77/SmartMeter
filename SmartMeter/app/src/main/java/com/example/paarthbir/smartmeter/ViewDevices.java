package com.example.paarthbir.smartmeter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ViewDevices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_devices);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final String USER;
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        USER = bundle.getString("username");

        final String HttpUrl = "https://paarthbirtest.000webhostapp.com/viewdevices.php";
        if (!AppStatus.getInstance(Objects.requireNonNull(ViewDevices.this)).isOnline()) {

            Toast.makeText(ViewDevices.this, "Internet Connection Problem!!", Toast.LENGTH_LONG).show();

        }

        //final RequestQueue requestQueue;
        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(ViewDevices.this));
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(ViewDevices.this, R.style.MyAlertDiallogStyle);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String Serverresponse) {
                progressDialog.dismiss();
                //Toast.makeText(getContext(), ServerResponse, Toast.LENGTH_LONG).show();
                Toast.makeText(ViewDevices.this, Serverresponse, Toast.LENGTH_LONG).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(ViewDevices.this, "Connection Problem, Try Again", Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<>();

                // Adding All values to Params.
                params.put("USER", USER);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // Creating RequestQueue.


        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);


    }

}

