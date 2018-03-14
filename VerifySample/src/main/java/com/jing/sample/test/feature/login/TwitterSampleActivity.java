package com.jing.sample.test.feature.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.jing.sample.R;
import com.jing.sample.notimportant.BaseActivity;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import retrofit2.Response;

/**
 * <pre>
 *     author : jinghuang
 *     e-mail : jinghuang1130@yahoo.com
 *     time   : 2018/02/27
 *     desc   : 功能
 *     version: 1.0
 * </pre>
 */
public class TwitterSampleActivity extends BaseActivity {

    private TwitterLoginButton loginButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig("CONSUMER_KEY", "CONSUMER_SECRET"))
                .debug(true)
                .build();
//        Twitter.initialize(config);
        Twitter.initialize(this);

        setContentView(R.layout.activity_twitter);

        loginButton = findViewById(R.id.login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {

//                requestEmailAddress(getApplicationContext(), result.data);

                TwitterSession session = result.data;
                long userId = session.getUserId();
                String userName = session.getUserName();
                TwitterAuthToken authToken = session.getAuthToken();
                String token = "";
                String secret = "";
                if (authToken != null) {
                    token = authToken.token;
                    secret = authToken.secret;
                }

                Log.d(getTAG(),"success data, userId = " + userId + ", userName = " + userName
                        + ", token = " + token + ", secret = " + secret);
                Response response = result.response;
                Log.d(getTAG(),"success response = " + (response != null ? response.toString() : "response is NULL"));

                Toast.makeText(getApplicationContext(), "login success",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(TwitterException exception) {
                // Upon error, show a toast message indicating that authorization request failed.
                Toast.makeText(getApplicationContext(), exception.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });



    }

    private static void requestEmailAddress(final Context context, TwitterSession session) {
        new TwitterAuthClient().requestEmail(session, new Callback<String>() {
            @Override
            public void success(Result<String> result) {
                Toast.makeText(context, result.data, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(context, exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Pass the activity result to the saveSession button.
        Log.d(getTAG(),"requestCode = " + requestCode + ", resultCode = " + resultCode + ", data = " + data.toString());
        loginButton.onActivityResult(requestCode, resultCode, data);
    }
}
