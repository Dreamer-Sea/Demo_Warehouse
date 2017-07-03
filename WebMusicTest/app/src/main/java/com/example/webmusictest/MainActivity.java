package com.example.webmusictest;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.RadioGroup;

import com.example.webmusictest.adapters.FragmentAdapter;
import com.example.webmusictest.fragments.BingPicture.PictureFragment;
import com.example.webmusictest.fragments.DailyNews.TitleFragment;
import com.example.webmusictest.fragments.User.LoginFragment;
import com.example.webmusictest.fragments.User.RegisterFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ViewPager vp;
    private RadioGroup rg;

    public interface Fragment2Fragment{
        public void gotoFragment(ViewPager viewPager);
    }

    private Fragment2Fragment fragment2Fragment;
    public void setFragment2Fragment(Fragment2Fragment fragment2Fragment){
        this.fragment2Fragment = fragment2Fragment;
    }

    public void forSkip(){
        if(fragment2Fragment != null)
            fragment2Fragment.gotoFragment(vp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        navView.setCheckedItem(R.id.nav_call);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        vp = (ViewPager) findViewById(R.id.viewPager1);
        rg = (RadioGroup) findViewById(R.id.tab_menu);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                switch (checkedId) {
                    case R.id.rb_news:
                        vp.setCurrentItem(0);
                        break;

                    case R.id.rb_image:
                        vp.setCurrentItem(1);
                        break;

                    case R.id.rb_user:
                        vp.setCurrentItem(2);
                        break;

                    default:
                        break;
                }
            }
        });
        List<Fragment> mList = new ArrayList<>();
        TitleFragment f1 = new TitleFragment();
        PictureFragment f2 = new PictureFragment();
        LoginFragment f3 = new LoginFragment();
        RegisterFragment f4 = new RegisterFragment();

        mList.add(f1);
        mList.add(f2);
        mList.add(f3);
        mList.add(f4);

        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), mList);
        vp.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(Gravity.START);
                break;
            default:
        }
        return true;
    }
}
