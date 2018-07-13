package com.kalicalitally.instaher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {
    private EditText usernameInput;
    private EditText passInput;
    private Button loginBtn;
    private Button signUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameInput = findViewById(R.id.etUsername);
        passInput = findViewById(R.id.etPassword);
        loginBtn = findViewById(R.id.login_btn);
        signUpBtn = findViewById(R.id.register_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = usernameInput.getText().toString();
                final String password = passInput.getText().toString();
                login(username, password);

            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = usernameInput.getText().toString();
                final String password = passInput.getText().toString();
                final Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);

            }
        });

    }
    private void login(String username, String password){
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e == null){
                    Log.d("LogInActivity", "Login successful!");
                    final Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Log.d("LogInActivity", "Login failed!");
                    e.printStackTrace();
                }
            }
        });
    }
}
