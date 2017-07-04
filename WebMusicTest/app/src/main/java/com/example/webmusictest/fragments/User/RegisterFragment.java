package com.example.webmusictest.fragments.User;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.webmusictest.R;
import com.example.webmusictest.beans.Login.User;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by King on 2017/7/3.
 */

public class RegisterFragment extends Fragment implements View.OnClickListener{

    private EditText username;
    private EditText password;
    private Button register;
    private Button reset;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        username = (EditText) view.findViewById(R.id.ed_username);
        password = (EditText) view.findViewById(R.id.ed_password);
        register = (Button) view.findViewById(R.id.btn_register);
        reset = (Button) view.findViewById(R.id.btn_reset);

        register.setOnClickListener(this);
        reset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                String name = username.getText().toString();
                String pwd = password.getText().toString();

                List<User> res = DataSupport.select("username", "password")
                        .where("username = ?", name)
                        .where("password = ?", pwd).find(User.class);

                if(res.size() > 0)
                    Toast.makeText(this.getContext(), "该用户已存在", Toast.LENGTH_LONG).show();
                else{
                    User u = new User();
                    u.setUsername(name);
                    u.setPassword(pwd);
                    u.save();
                    Toast.makeText(this.getContext(), "注册成功，请重新登录", Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.btn_reset:
                username.setText("");
                password.setText("");
                break;
        }
    }
}
