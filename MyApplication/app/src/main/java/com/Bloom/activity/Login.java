package com.Bloom.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.Bloom.R;
import com.Bloom.util.httpClient;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends Activity implements View.OnClickListener {

    private EditText email, password;
    private Button join, signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/BloomFont.ttf");

        email = (EditText) findViewById(R.id.email);
        email.setTypeface(font);
        password = (EditText) findViewById(R.id.pw);
        password.setTypeface(font);
        join = (Button) findViewById(R.id.joinBttn);
        join.setTypeface(font);
        signUp = (Button) findViewById(R.id.signupBttn);
        signUp.setTypeface(font);



        AsyncHttpClient client = httpClient.getInstance();
        client.setURLEncodingEnabled(false);
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
        RequestParams params;
        try {
            result = toJson();
            params = new RequestParams("JSONData", result);
            params.setContentEncoding("UTF-8");

            httpClient.post("/login",params, new JsonHttpResponseHandler(){
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
           /* httpClient.get("/login", params, new JsonHttpResponseHandler(){
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
            */
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
        jsonObject.accumulate("user_pw", person.getPw());
        return jsonObject.toString();
    }



}
