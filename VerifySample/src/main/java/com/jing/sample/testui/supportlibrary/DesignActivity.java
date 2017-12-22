package com.jing.sample.testui.supportlibrary;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jing.sample.R;
import com.jing.sample.notimportant.BaseActivity;

import java.util.ArrayList;

/**
 * Design support library test
 *
 * @author hakimhuang
 *         Date : 2017/9/6 18:13
 */
public class DesignActivity extends BaseActivity {

    /**
     * 类型 Toast 形式的弹窗 从屏幕下方弹出来
     */
    private Snackbar mSnackbar = null;
    /**
     * TextInputLayout 显示错误提示信息
     */
    TextInputLayout mTILayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTabLayoutView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_snak:
                mSnackbar = Snackbar.make(view, "This is Message", Snackbar.LENGTH_LONG)
                        .setAction("This is Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(DesignActivity.this, "You click this action", Toast.LENGTH_SHORT).show();
                            }
                        });
                mSnackbar.show();
                break;
            case R.id.btn_snak2:
                if (mSnackbar != null) {
                    mSnackbar.setText("This is Text").setActionTextColor(Color.GRAY).show();
                }
                break;
            case R.id.btn_snak3:
                if (mSnackbar != null) {
                    View viewChild = mSnackbar.getView();
                    viewChild.setBackgroundColor(Color.GREEN);
                    mSnackbar.show();
                }
                break;
            case R.id.btn_snak4:
                if (mSnackbar != null) {
                    Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) mSnackbar.getView();
                    View addView = LayoutInflater.from(snackbarLayout.getContext()).inflate(R.layout.view_snackbar, null);
                    LinearLayout.LayoutParams layoutParams
                            = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.gravity = Gravity.CENTER_VERTICAL;
                    int index = -1; // the last position
                    index = 1;
                    index = 0;
                    snackbarLayout.addView(addView, index, layoutParams);
                    mSnackbar.show();
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_design, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = null;
        switch (item.getItemId()) {
            case R.id.key_viewDefault:
                initSnackEditView();
                break;
            case R.id.key_viewTabLayout:
                initTabLayoutView();
                break;
            case R.id.key_viewTabLayoutVP:
                initViewPagerTabLayoutView();
                break;
        }
        return true;
    }

    private void initSnackEditView() {
        setContentView(R.layout.activity_support_design);
        mTILayout = (TextInputLayout) findViewById(R.id.til_layout);
        findViewById(R.id.btn_snak).setOnClickListener(this);
        findViewById(R.id.btn_snak2).setOnClickListener(this);
        findViewById(R.id.btn_snak3).setOnClickListener(this);
        findViewById(R.id.btn_snak4).setOnClickListener(this);
        ((TextInputEditText)findViewById(R.id.tie_password)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    if ("".equals(charSequence + "")) {
                        mTILayout.setError(null);
                    } else if (charSequence.length() > 6) {
                        mTILayout.setError("密码长度超过上限！");
                    } else {
                        Integer.parseInt(charSequence + "");
                        mTILayout.setError(null);
                    }
                } catch (Exception e) {
                    mTILayout.setError("输入内容不是数字！");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void initTabLayoutView() {
        setContentView(R.layout.activity_support_design_2);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.mipmap.ic_launcher));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 4"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 5"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 6"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 7"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabSelected tab.position=" + tab.getPosition() + ", tap.text=" + tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabUnselected tab.position=" + tab.getPosition() + ", tap.text=" + tab.getText());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabReselected tab.position=" + tab.getPosition() + ", tap.text=" + tab.getText());
            }
        });
    }

    private ArrayList<String> mTitleList = new ArrayList<String>() {
        {
            add("推荐");
            add("热点");
            add("视频");
            add("社会");
            add("国际");
            add("娱乐");
            add("科技");
        }
    };

    private void initViewPagerTabLayoutView() {
        setContentView(R.layout.activity_support_design_3);
        ViewPager viewPagerOne = (ViewPager) findViewById(R.id.viewPagerOne);
        TabLayout tabLayoutOne = (TabLayout) findViewById(R.id.tabLayoutOne);
        tabLayoutOne.addTab(tabLayoutOne.newTab().setText("Tab A"));
        tabLayoutOne.addTab(tabLayoutOne.newTab().setText("Tab B"));
        tabLayoutOne.addTab(tabLayoutOne.newTab().setText("Tab C"));
    }
}
