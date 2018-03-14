package com.jing.sample.notimportant;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jing.sample.R;
import com.jing.sample.test.component.loadermanager.LoaderCustomActivity;
import com.jing.sample.test.feature.crash.CrashExceptionActivity;
import com.jing.sample.test.ui.supportlibrary.DesignActivity;
import com.jing.sample.test.ui.supportlibrary.PercentActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.samples_list)
    ListView mListView;

    private ArrayList<ItemSample> mSamples;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Resources resources = getResources();
        mSamples = new ArrayList<>();
        mSamples.add(new ItemSample("Test Load", "Custom load", LoaderCustomActivity.class));
        mSamples.add(new ItemSample(R.string.demo_test_in_activity, R.string.demo_test_in_activity, TestActivity.class));
        mSamples.add(new ItemSample("Test Percent Support Library", "PercentFrameLayout + PercentRelativeLayout", PercentActivity.class));
        mSamples.add(new ItemSample("Test Design Support Library", "Snackbar", DesignActivity.class));
        mSamples.add(new ItemSample("Test App Crash", "Monitor app itself crash", CrashExceptionActivity.class));

        mListView.setAdapter(new MainAdapter(this, mSamples));
    }

    @OnItemClick(R.id.samples_list)
    void onSampleListClick(AdapterView<?> parent, View view, int position, long id) {

        if (mSamples != null && position >= 0 && position < mSamples.size()) {
            startActivity(new Intent(MainActivity.this, mSamples.get(position).mActivityClass));
        }
    }
}
