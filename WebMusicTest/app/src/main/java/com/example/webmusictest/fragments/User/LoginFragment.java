package com.example.webmusictest.fragments.User;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.webmusictest.MainActivity;
import com.example.webmusictest.R;
import com.example.webmusictest.beans.Login.User;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private CircleImageView imageUser;
    private Button loginBtn;
    private Button registerBtn;
    private EditText usernameEd;
    private EditText passwordEd;
    private User user;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Connector.getDatabase();

        List<User> res = DataSupport.where("username = ?", "zhangsan").find(User.class);
        if (res.size() <= 0){
            user = new User();
            user.setUsername("zhangsan");
            user.setPassword("123456");
            user.save();
        }

        res = DataSupport.where("username = ?", "lisi").find(User.class);
        if (res.size() <= 0){
            user = new User();
            user.setUsername("lisi");
            user.setPassword("123456");
            user.save();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, null);
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
                List<User> res = DataSupport.where("username = ?", username).find(User.class);
                if(res.size() > 0 && (res.get(0)).getPassword().equals(password))
                    Toast.makeText(this.getContext(), "登录成功", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(this.getContext(), "登录失败", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_register:
                //TODO implement
                final MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.setFragment2Fragment(new MainActivity.Fragment2Fragment(){
                    @Override
                    public void gotoFragment(ViewPager viewPager) {
                        viewPager.setCurrentItem(3);
                    }
                });
                mainActivity.forSkip();
                break;
        }
    }
}
