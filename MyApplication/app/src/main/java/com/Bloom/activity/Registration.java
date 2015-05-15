package com.Bloom.activity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.Bloom.util.httpClient;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import com.Bloom.R;

public class Registration extends Activity implements View.OnClickListener {

    private Button signUp;
    private EditText fullName, nickname, college;
    private String uuid = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        signUp = (Button) findViewById(R.id.signUP);
        fullName = (EditText) findViewById(R.id.fullName);
        nickname = (EditText) findViewById(R.id.nickname);
        college = (EditText) findViewById(R.id.college);

        AsyncHttpClient client = httpClient.getInstance();
        client.setURLEncodingEnabled(false);
        PersistentCookieStore myCookie = new PersistentCookieStore(this);
        client.setCookieStore(myCookie);

        uuid = GetDevicesUUID(getApplicationContext());
        signUp.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onPause(){
        super.onPause();
    }

    //사인업 버튼 클릭 시
    @Override
    public void onClick(View v) {
        String result;
        try {
            result = toJson();
            RequestParams params = new RequestParams();
            params.put("JSONData", result);

            httpClient.post("/signin", params, new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                    String result = response.toString();
                    System.out.println(result);
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, String response, Throwable throwable) {
                    System.out.println("Response is : " + response);
                }
            });
            httpClient.get("/signin", params, new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    String result = response.toString();
                    System.out.println(result);
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject jsonObject) {
                    Log.i("SignUpGet", "SignUpFailed");
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, String response, Throwable throwable) {
                    System.out.println("Response is : " + response);
                }
            });

        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //폰 uuid 받아오기
    private String GetDevicesUUID(Context mContext) {
        TelephonyManager tManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = tManager.getDeviceId();
        return deviceId;
    }

    //Json으로 데이터변형
    public String toJson() throws JSONException {
        personInfo person = new personInfo();
        person.setFullName(fullName.getText().toString());
        person.setNickName(nickname.getText().toString());
        person.setCollege(college.getText().toString());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_name", person.getFullName());
        jsonObject.put("user_pw",person.getCollege());
        jsonObject.put("user_id",person.getNickName());
        jsonObject.put("user_num", uuid);
        return jsonObject.toString();
    }

}
