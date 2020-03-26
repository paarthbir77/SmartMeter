package com.example.paarthbir.smartmeter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    String passcode, user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText username = findViewById(R.id.login_username);
        final EditText pass = findViewById(R.id.login_pass);
        Button button = findViewById(R.id.login_button);
        final String str = "password";

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString();
                passcode = pass.getText().toString();
                if(passcode.equals(str) && !(user.isEmpty())){
                    Toast.makeText(Login.this, "Logging in ...",
                            Toast.LENGTH_SHORT).show();
                    //GlobalClass mApp = ((GlobalClass)getApplicationContext());
//                    String globalVarValue = mApp.getGlobalVarValue();
                    //mApp.setGlobalVarValue(user);
                    Intent i=new Intent(Login.this,MainActivity.class);
                    i.putExtra("username", user);
                    startActivity(i);

                }
                else{
                    Toast.makeText(Login.this, "Incorrect username pair password",
                            Toast.LENGTH_SHORT).show();
                    username.setText("");
                    pass.setText("");
                }

            }
        });


    }
}
