package com.Bloom.activity;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Bloom.R;
import com.Bloom.util.httpClient;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

public class Login extends Activity implements View.OnClickListener {

    private EditText email, password;
    private Button join, signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.pw);
        join = (Button) findViewById(R.id.joinBttn);
        signUp = (Button) findViewById(R.id.signupBttn);
        join.setOnClickListener(this);
        AsyncHttpClient client = httpClient.getInstance();
        PersistentCookieStore myCookie = new PersistentCookieStore(this);
        client.setCookieStore(myCookie);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    @Override
    public void onClick(View v) {

        String result;
        RequestParams params = new RequestParams();

        try {
            result = toJson();
        params.put("JSONData", result);
        httpClient.post("/login",params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                String result = response.toString();
                System.out.println(result);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  Throwable throwable, JSONObject errorResponse) {
                Log.i("Login", "LoginFailed");
            }

        });
        httpClient.get("/login", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable){
                Log.i("Login", "LoginFailed");
                System.out.println(responseString);
            }

        });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String toJson() throws JSONException {
        personInfo person = new personInfo();
        person.setEmail(email.getText().toString());
        person.setPw(password.getText().toString());
        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("user_id", person.getEmail());
        jsonObject.accumulate("user_pw",person.getPw());
        return jsonObject.toString();
    }



}
