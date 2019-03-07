package com.jing.sample.test.feature.crash;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jing.sample.notimportant.BaseActivity;

/**
 * <pre>
 *     author : jinghuang
 *     e-mail : jinghuang1130@yahoo.com
 *     time   : 2018/03/05
 *     desc   : App 检测自身 crash
 *              请注意配置 CrashApplication
 *     version: 1.0
 * </pre>
 */
public class CrashExceptionActivity extends BaseActivity {
    private String mString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(getTAG(), "onCreate");

        Button button = new Button(this);
        button.setText("click this will crash");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(getTAG(), "" + mString.equals("test"));
            }
        });
        mMainLayout.addView(button);
    }

    @Override
    public String getTAG() {
        return "CrashExceptionActivity";
    }
}
