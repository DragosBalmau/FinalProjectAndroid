package com.example.traveljournal.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveljournal.NavigationDrawerActivity;
import com.example.traveljournal.R;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);


    }


    public void loginOnClick(View view) {

        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (username.isEmpty()) {
            usernameEditText.setError("Please fill the username");
        }
        if (password.isEmpty()) {
            passwordEditText.setError("Please fill the password number");
        }

        if (!username.isEmpty() && !password.isEmpty()) {
            //Toast.makeText(this, username + " " + password, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LoginActivity.this, NavigationDrawerActivity.class);
            intent.putExtra("User", usernameEditText.getText().toString()+"");
            startActivity(intent);
        }
    }



}
