package com.Bloom.activity;

// 사용자 프로필
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Bloom.R;

public class myPageFrag2 extends Fragment {


    public static Fragment newInstance(Context context) {
        myPageFrag2 f = new myPageFrag2();

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_log, null);
        return root;
    }

}