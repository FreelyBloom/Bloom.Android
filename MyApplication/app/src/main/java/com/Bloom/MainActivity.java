package com.Bloom;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/*
메인 액티비티
로그인 여부 확인해야함 ( if-else using SharedPreferences )
if "first-time-user" OR Log-out : load Log-In page
else : load to main page (stay logged-in unless User hit log-out bttn)
 */
public class MainActivity extends FragmentActivity {
    private DrawerLayout drawer;
    private ListView navList;

    private String[] data = {"Main","My Page","Favorite", "BLooM", "Setting"};  // 메뉴리스트

    // 프래그먼트 리스트
    private String[] fragments ={
            "com.Bloom.MainPageFragment",
            "com.Bloom.MyPageFragment",
            "com.Bloom.FavFragment",
            "com.Bloom.BloomFragment",
            "com.Bloom.SettingFragment"};

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);

        drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        navList = (ListView) findViewById(R.id.drawer);
        navList.setAdapter(adapter);

        // 메뉴 클릭에 따른 표시되는 fragment 변경
        navList.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int pos,long id){
                drawer.setDrawerListener( new DrawerLayout.SimpleDrawerListener(){
                    @Override
                    public void onDrawerClosed(View drawerView){
                        super.onDrawerClosed(drawerView);
                        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
                        tx.replace(R.id.main, Fragment.instantiate(MainActivity.this, fragments[pos]));
                        tx.commit();
                    }
                });
                drawer.closeDrawer(navList);
            }
        });
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.main,Fragment.instantiate(MainActivity.this, fragments[0]));
        tx.commit();
    }



}