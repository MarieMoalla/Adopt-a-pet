package com.example.projetandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import service.DatabaseHelper;
public class LoginActivity extends AppCompatActivity{

    //region variables
    EditText mLogin;
    EditText mPassword;
    Button mLogin_btn;
    TextView mRegister;
    DatabaseHelper databasHelper;
    //endregion

    //on create page
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //inputs
        mLogin = (EditText) findViewById(R.id.login_et_login);
        mPassword = (EditText) findViewById(R.id.login_et_password);
        mLogin_btn = (Button) findViewById(R.id.login_et_btn);
        mRegister = (TextView) findViewById(R.id.login_tv_register);

        //database instanciation
        databasHelper = new DatabaseHelper(this);

        //variables
        String login = mLogin.getText().toString();
        String password = mPassword.getText().toString();

        //region login button action
        mLogin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean var = databasHelper.checkUser(mLogin.getText().toString(),mPassword.getText().toString());
                if(mLogin.getText().toString().equals("")||mPassword.getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this, "Login empty" , Toast.LENGTH_SHORT).show();
                } else {
                    if(var == true) {
                        Toast.makeText(LoginActivity.this, "Login" , Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this , MainActivity.class);
                        intent.putExtra("Login", mLogin.getText().toString());
                        startActivity(intent);

                    } else {
                        Toast.makeText(LoginActivity.this, "Login failed" , Toast.LENGTH_SHORT).show();
                    }
                }
                //Log.i("TEST LOGIN" , String.valueOf(var));

            }
        });
        //endregion

        //region register button action
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this , RegisterActivity.class);
                startActivity(intent);
            }
        });
        //endregion
    }
}
