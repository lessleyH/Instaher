package com.kalicalitally.instaher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {
    private Button registerbtn;
    private EditText usernameInput;
    private EditText passInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameInput = findViewById(R.id.etUsernameS);
        passInput = findViewById(R.id.etPasswordS);
        registerbtn = findViewById(R.id.done_btn);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = usernameInput.getText().toString();
                final String password = passInput.getText().toString();
                register(username, password);

            }
        });
    }

    private void register(String username, String password){
        // Create the ParseUser
        ParseUser user = new ParseUser();
        // Set core properties
        user.setUsername(username);
        user.setPassword(password);
//        user.setEmail("email@example.com");
        // Set custom properties
//        user.put("phone", "650-253-0000");
        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    Log.d("SignUpActivity", "Sign up successful!");
                    final Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                }
            }
        });
    }
}
