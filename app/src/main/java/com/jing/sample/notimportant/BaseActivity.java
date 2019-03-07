package com.jing.sample.notimportant;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.jing.sample.R;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    protected String TAG = this.getClass().getName();
    public ConstraintLayout mMainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_base);
        mMainLayout = findViewById(R.id.activity_base);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = null;
        switch (item.getItemId()) {
            case R.id.key_viewGithub:
                i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://github.com/jingtalk"));
                startActivity(i);
                break;
            case R.id.key_report:
                i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "garry.huang.it@gmail.com", null));
                i.putExtra(Intent.EXTRA_SUBJECT, "Git-Demo Issue");
                i.putExtra(Intent.EXTRA_TEXT, "Your error report here...");
                startActivity(Intent.createChooser(i, "Report Problem"));
                break;
            case R.id.key_blog:
                i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://jingtalk.github.io/"));
                startActivity(i);
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
    }

    public String getTAG() {
        return TAG;
    }
}
