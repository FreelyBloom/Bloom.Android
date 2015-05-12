package com.Bloom.activity;

import android.app.Activity;
import android.content.Intent;
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

        AsyncHttpClient client = httpClient.getInstance();
        PersistentCookieStore myCookie = new PersistentCookieStore(this);
        client.setCookieStore(myCookie);

        join.setOnClickListener(this);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Registration.class);
                startActivity(intent);
            }
        });

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
        try {
            result = toJson();
            RequestParams params = new RequestParams("JSONData", result);
        httpClient.post("/signin",params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                String result = response.toString();
                System.out.println(result);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers,String response,
                                  Throwable throwable) {
                Log.i("SigninPost", "SigninFailed");
            }

        });
       httpClient.get("/signin", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                System.out.println(response.toString());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject jsonObject) {
                Log.i("SigninGet", "SigninFailed");

            }

        });
        }catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String toJson() throws JSONException {
        personInfo person = new personInfo();
        person.setEmail(email.getText().toString());
        person.setPw(password.getText().toString());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_name", person.getEmail());
        jsonObject.put("user_",person.getPw());
        return jsonObject.toString();
    }



}
