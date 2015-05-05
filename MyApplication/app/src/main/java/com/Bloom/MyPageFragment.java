package com.Bloom;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// 내 프로필 페이지
public class MyPageFragment extends Fragment {

    public static Fragment newInstance(String string) {
        MyPageFragment myPage = new MyPageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("msg", string);
        myPage.setArguments(bundle);
        return myPage;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_activity, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mViewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
        mViewPager.setCurrentItem(1);
    }

    public static class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0 : return ActivityFragment.newInstance("ActivityFragment, Instance 0");
                case 1 : return MyPageFragment.newInstance("MyPageFragment, Instance 1");
                default : return MyPageFragment.newInstance("MyPageFragment, Instance 1");
            }
        }
    }
}