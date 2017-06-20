package com.example.webmusictest.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Created by King on 2017/6/20.
 */

public class GetFragmentActivity {

    public static FragmentActivity getActivity(Fragment fragment){
        return fragment.getActivity();
    }
}
