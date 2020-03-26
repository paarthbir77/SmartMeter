package com.example.paarthbir.smartmeter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.app.ProgressDialog;

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

import static java.security.AccessController.getContext;

public class AddDevice extends AppCompatActivity {


    String USER;
    String devicename, devicepower, starting, ending;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final String USER;
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        USER = bundle.getString("username");

        Button button = findViewById(R.id.add_device_button);
        final EditText dname = findViewById(R.id.dname);
        final EditText dpower = findViewById(R.id.dpower);
        final Spinner tstart = findViewById(R.id.spinner1);
        final Spinner tend = findViewById(R.id.spinner2);


        final String HttpUrl = "https://paarthbirtest.000webhostapp.com/devices.php";
        if (!AppStatus.getInstance(Objects.requireNonNull(AddDevice.this)).isOnline()) {

            Toast.makeText(AddDevice.this, "Internet Connection Problem!!", Toast.LENGTH_LONG).show();

        }
        final RequestQueue requestQueue;
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(AddDevice.this, R.style.MyAlertDiallogStyle);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          devicename = dname.getText().toString().trim();
                                          devicepower = dpower.getText().toString().trim();
                                          starting = tstart.getSelectedItem().toString().trim();
                                          ending = tend.getSelectedItem().toString().trim();

                                          if(devicename.isEmpty())
                                          {
                                              Toast.makeText(AddDevice.this, "Enter Details", Toast.LENGTH_LONG).show();
                                          }
                                          else
                                          {
                                              progressDialog.setMessage("Adding...");
                                              progressDialog.show();
                                              StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl, new Response.Listener<String>() {
                                                  @Override
                                                  public void onResponse(String Serverresponse) {
                                                      progressDialog.dismiss();
                                                      //Toast.makeText(getContext(), ServerResponse, Toast.LENGTH_LONG).show();
                                                      //dname.setText(Serverresponse);
                                                      Toast.makeText(AddDevice.this, Serverresponse, Toast.LENGTH_LONG).show();
                                                  }
                                              },
                                                      new Response.ErrorListener() {
                                                          @Override
                                                          public void onErrorResponse(VolleyError error) {
                                                              progressDialog.dismiss();
                                                              dname.setText("Connection Problem, Try Again");
                                                              Toast.makeText(AddDevice.this, "Connection Problem, Try Again", Toast.LENGTH_LONG).show();
                                                          }
                                                      })
                                              {
                                                  @Override
                                                  protected Map<String, String> getParams() {

                                                      // Creating Map String Params.
                                                      Map<String, String> params = new HashMap<>();

                                                      // Adding All values to Params.
                                                      params.put("USER", USER);
                                                      params.put("D_name", devicename);
                                                      params.put("POWER", devicepower);
                                                      params.put("T_Start", starting);
                                                      params.put("T_End", ending);
                                                      return params;
                                                  }
                                              };
                                              stringRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                                              // Creating RequestQueue.
                                              RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(AddDevice.this));

                                              // Adding the StringRequest object into requestQueue.
                                              requestQueue.add(stringRequest);
                                              dname.setText("");
                                              dpower.setText("");

                                          }

                                      }

                                  }
        );


    }

}
