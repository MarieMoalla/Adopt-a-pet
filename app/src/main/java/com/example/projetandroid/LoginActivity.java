package com.example.projetandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import service.DatabaseHelper;
public class LoginActivity extends AppCompatActivity{

    //region variables
    EditText mLogin;
    EditText mPassword;
    Button mLogin_btn;
    TextView mRegister;
    TextView mResetPws;
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
        mResetPws = (TextView) findViewById(R.id.reset_pwd);

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
                        // region save logged in user data locally

                        //get user id
                        int mId = databasHelper.getUserId(mLogin.getText().toString());

                        // create share preference
                        SharedPreferences sp=getSharedPreferences("Login", MODE_PRIVATE);
                        SharedPreferences.Editor Ed=sp.edit();
                        Ed.putInt("Id", mId);
                        Ed.commit();

                        startActivity(intent);
                        //endregion
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

        //region reset pwd button action
        mResetPws.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean var = databasHelper.checkUserEmail(mLogin.getText().toString());
                if(var != true){
                    Toast.makeText(LoginActivity.this, "Write email first" , Toast.LENGTH_SHORT).show();
                } else {
                    String pwd = databasHelper.getUserPassword(mLogin.getText().toString());
                    Toast.makeText(LoginActivity.this, "Your password is: "+pwd , Toast.LENGTH_SHORT).show();
            }
        }});

        //endregion
    }
    public void sendEmail(String subject, String content, String to_email)
    {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to_email});
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, content);

        //need this to prompts email client only
        email.setType("message/rfc822");

        email.putExtra(Intent.EXTRA_CC,  "meriam.moalla1@gmail.com" ); // add sender email address
        email.setPackage("com.google.android.gm"); // set Gmail as email provider

        startActivity(Intent.createChooser(email, "Choose an Email client:"));
    }
    //region Generate random token
    public String randomString() {
        // create a string of all characters
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string
        int length = 7;

        for(int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphabet.length());

            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }

        String randomString = sb.toString();
        return randomString;
    }
    //endregionra
}
