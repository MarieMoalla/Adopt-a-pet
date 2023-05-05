package com.example.projetandroid.home;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projetandroid.R;

import model.User;
import service.DatabaseHelper;

public class ProfileFragment extends Fragment {

    private Button edit_user_btn;
    private DatabaseHelper databaseHelper;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //allow fragment to extract view components
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        //db
        databaseHelper = new DatabaseHelper(this.getContext());
        //get current user id
        SharedPreferences sp1 = this.getContext().getSharedPreferences("Login", MODE_PRIVATE);
        int id = sp1.getInt("Id", 0);

        //edited user
        User user = new User();

        EditText userFirstName = view.findViewById(R.id.user_first_name);
        EditText userLastName = view.findViewById(R.id.user_last_name);
        EditText userEmail = view.findViewById(R.id.user_email);
        EditText userPassword = view.findViewById(R.id.user_password);


        //configure edit user btn
        edit_user_btn = view.findViewById(R.id.edit_user_btn);


        edit_user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String fname = userFirstName.getText().toString();
                if (!fname.isEmpty())user.setmName(fname);

                String lname = userLastName.getText().toString();
                if (!lname.isEmpty())user.setmLastname(lname);

                String email = userEmail.getText().toString();
                if (!email.isEmpty())user.setmEmail(email);

                String password = userPassword.getText().toString();
                if (!password.isEmpty())user.setmPassword(password);

                databaseHelper.updateUser(id,user);
                Toast.makeText(getActivity(), "User updated!" , Toast.LENGTH_SHORT).show();

            }
            });
        return view;
    }
}
