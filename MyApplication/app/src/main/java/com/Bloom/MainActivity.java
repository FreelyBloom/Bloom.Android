package com.Bloom;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import com.Bloom.activity.*;

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
    // private ActionBarDrawerToggle actionBar;
    // 프래그먼트 리스트

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);

        drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        navList = (ListView) findViewById(R.id.drawerList);
        View header = getLayoutInflater().inflate(R.layout.header,null);
        ImageView profile = (ImageView) header.findViewById(R.id.profile_image);
        navList.addHeaderView(header);
        navList.setAdapter(adapter);

        // 메뉴 클릭에 따른 표시되는 fragment 변경
        navList.setOnItemClickListener(new OnItemClickListener(){
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
        Fragment mainFragment = new mainPage();
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.main, mainFragment);
        trans.commit();
    }

    // 메뉴 선택에 따른 화면 전환 method
    private void switchFragment(int pos){
        switch(pos) {
            case 1:
                Fragment mainFragment = new mainPage();
                FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
                trans.replace(R.id.main, mainFragment);
                trans.commit();
                break;
            case 2:
                Intent myPage = new Intent(MainActivity.this, myPage.class);
                startActivity(myPage);
                break;
            case 3:
                Intent favor = new Intent(MainActivity.this, Favorite.class);
                startActivity(favor);
                break;
            case 4:
                Intent bloom = new Intent(MainActivity.this, Bloom.class);
                startActivity(bloom);
                break;
            case 5:
                Intent setting = new Intent(MainActivity.this, Setting.class);
                startActivity(setting);
                break;
        }
    }

    public static class mainPage extends Fragment {

        public static Fragment newInstance(Context context) {

            mainPage mainFragment = new mainPage();

            return mainFragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
            ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_main_page, null);
            return root;
        }
    }
}