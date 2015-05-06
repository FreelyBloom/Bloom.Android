package com.Bloom.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.Bloom.R;

public class myPageFrag1 extends Fragment {

    public static Fragment newInstance(Context context) {
        myPageFrag1 f = new myPageFrag1();

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.my_page_profile, null);
        return root;
    }

}