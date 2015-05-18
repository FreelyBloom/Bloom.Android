package com.Bloom.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.Bloom.R;
import com.Bloom.RoundImage;
import com.Bloom.adapter.ViewPagerAdapter;
import com.Bloom.mainPage;


public class myPage extends FragmentActivity {
    private DrawerLayout drawer;
    private ListView navList;
    private String[] data = {"Main","My Page","Favorite", "BLooM", "Setting"};  // 메뉴리스트
    private ImageView profile;
    private RoundImage roundedImage;
    private FragmentActivity fragActivity;

    private ViewPager _mViewPager;
    private ViewPagerAdapter _adapter;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
        setUpView();
        setTab();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        navList = (ListView) findViewById(R.id.drawerList);
        View header = getLayoutInflater().inflate(R.layout.header,null);
        profile = (ImageView) header.findViewById(R.id.profile_image);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.profile);
        roundedImage = new RoundImage(bm);
        profile.setImageDrawable(roundedImage);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(intent.ACTION_GET_CONTENT);
                startActivityForResult(intent.createChooser(intent, "Select an Image"), 1);

            }
        });
        navList.addHeaderView(header);
        navList.setAdapter(adapter);

        // 메뉴 클릭에 따른 표시되는 fragment 변경
        navList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos,long id){
                switchFragment(pos);
                drawer.setDrawerListener( new DrawerLayout.SimpleDrawerListener(){

                    @Override
                    public void onDrawerClosed(View drawerView){
                        super.onDrawerClosed(drawerView);
                        invalidateOptionsMenu();
                    }
                });

                drawer.closeDrawer(navList);
            }
        });
    }
    private void setUpView(){
        _mViewPager = (ViewPager) findViewById(R.id.viewPager);
        _adapter = new ViewPagerAdapter(getApplicationContext(),getSupportFragmentManager());
        _mViewPager.setAdapter(_adapter);
        _mViewPager.setCurrentItem(0);
    }
    private void setTab(){
        _mViewPager.setOnPageChangeListener(new OnPageChangeListener(){

            @Override
            public void onPageScrollStateChanged(int position) {}
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {}
            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub

            }

        });

    }
    private void switchFragment(int pos){
        switch(pos) {
            case 1:
                Fragment mainFragment = new mainPage();
                FragmentTransaction trans = fragActivity.getSupportFragmentManager().beginTransaction();
                trans.setCustomAnimations(R.anim.abc_fade_in,R.anim.abc_fade_out);
                trans.replace(R.id.main, mainFragment);
                trans.commit();
                break;
            case 2:
                Intent myPage = new Intent(myPage.this, myPage.class);
                myPage.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(myPage);
                overridePendingTransition(R.anim.abc_fade_in,R.anim.abc_fade_out);
                break;
            case 3:
                Intent favor = new Intent(myPage.this, Favorite.class);
                startActivity(favor);
                overridePendingTransition(R.anim.abc_fade_in,R.anim.abc_fade_out);
                break;
            case 4:
                Intent bloom = new Intent(myPage.this, Bloom.class);
                startActivity(bloom);
                overridePendingTransition(R.anim.abc_fade_in,R.anim.abc_fade_out);
                break;
            case 5:
                Intent setting = new Intent(myPage.this, Setting.class);
                startActivity(setting);
                overridePendingTransition(R.anim.abc_fade_in,R.anim.abc_fade_out);
                break;
        }
    }

    // MainActivity 안의 mainPage fragment

    public void onPause(){
        super.onPause();
    }

    public void onResume(){
        super.onResume();
    }

    public void onActivityResult(int reqCode, int resCode, Intent data){
        if(resCode == RESULT_OK){
            if (reqCode == 1){
                profile.setImageURI(data.getData());
            }
        }
    }

}