package com.example.projetandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import model.User;
import service.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity{

    //region variables
    EditText mName;
    EditText mLastname;
    EditText mUsername;
    EditText mPassword;
    Button mSign;
    TextView mLogin;
    //endregion

    //database instanciation
    DatabaseHelper databasHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //inputs
        mName = (EditText) findViewById(R.id.sign_et_name);
        mLastname = (EditText) findViewById(R.id.sign_et_lastname);
        mUsername = (EditText) findViewById(R.id.sign_et_username);
        mPassword = (EditText) findViewById(R.id.sign_et_password);
        mSign = (Button) findViewById(R.id.sing_et_btn);
        mLogin = (TextView) findViewById(R.id.sign_tv_login);
        databasHelper  = new DatabaseHelper(this);

        //region register button action
        mSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mName.getText().toString().equals("") || mLastname.getText().toString().equals("")  ||
                        mUsername.getText().toString().equals("")
                        || mPassword.getText().toString().equals("")) {
                    //Log.i("Name",name);
                    Toast.makeText(RegisterActivity.this, "Test" , Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User(0,mName.getText().toString(),mLastname.getText().toString(),mUsername.getText().toString(),mPassword.getText().toString(),"");
                    databasHelper.addUser(user);
                    databasHelper.close();
                    Intent intent = new Intent(RegisterActivity.this , LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
        //endregion

        //region login button action
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this , LoginActivity.class);
                startActivity(intent);
            }
        });
        //endregion
    }

}
