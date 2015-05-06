package com.Bloom.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.Bloom.activity.myPageFrag1;
import com.Bloom.activity.myPageFrag2;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Context _context;
    public static int totalPage=2;
    public ViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        _context=context;

    }
    @Override
    public Fragment getItem(int position) {
        Fragment f = new Fragment();
        switch(position){
            case 0:
                f= myPageFrag1.newInstance(_context);
                break;
            case 1:
                f= myPageFrag2.newInstance(_context);
                break;
        }
        return f;
    }
    @Override
    public int getCount() {
        return totalPage;
    }

}
