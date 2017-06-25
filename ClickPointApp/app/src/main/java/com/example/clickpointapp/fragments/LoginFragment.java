package com.example.clickpointapp.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.example.clickpointapp.R;
import com.example.clickpointapp.beans.User;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private CircleImageView imageUser;
    private Button loginBtn;
    private Button registerBtn;
    private EditText usernameEd;
    private EditText passwordEd;

    private View view;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Connector.getDatabase();

        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123456");
        user.save();

        user = new User();
        user.setUsername("lisi");
        user.setPassword("123456");
        user.save();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageUser = (CircleImageView) view.findViewById(R.id.image_user);

        loginBtn = (Button) view.findViewById(R.id.btn_login);
        registerBtn = (Button) view.findViewById(R.id.btn_register);

        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);

        usernameEd = (EditText) view.findViewById(R.id.ed_username);
        passwordEd = (EditText) view.findViewById(R.id.ed_password);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                //TODO implement
                String username = usernameEd.getText().toString();
                String password = passwordEd.getText().toString();
                List<User> rus = DataSupport.select("username", "password")
                        .where("username = ?", username)
                        .where("password = ?", password).find(User.class);
                if(rus.size() > 0)
                    Toast.makeText(this.getContext(), "登录成功", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(this.getContext(), "登录失败", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_register:
                //TODO implement
                break;
        }
    }
}
