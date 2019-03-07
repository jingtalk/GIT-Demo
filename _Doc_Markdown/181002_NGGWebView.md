# WebView 组件

## 1. 功能

NGGWebView 是基于TBS的 webview 封装，对业务只暴露一些必须的接口，简化使用方式。

目前支持的功能如下：

- 原生 WebView 的功能接口全部支持
- 无需手动设置 WebViewClient、WebChromeClient
- 支持视频横竖屏播放
- js通讯消息接口暴露
- x5内核、系统内核切换
- 页面加载、滑动监听状态暴露

## 2. 接口

NGGWebViewConfig 配置类

```java
// 指定 webview 的内核 
public void init(Context context, boolean isSystemCore);
// 指定 webview 的认证信息
public void setUserAgent(String agent);
```

以上代码需要在 `setContentView`之前调用。

NGGWebView 控件类，业务像正常使用view控件一样使用即可。

```java
// 设置宿主Activity
public void setHostActivity(Activity hostActivity);
// 设置滑动监听器
public void setScrollListener(WebViewUtils.IScrollChangeListener listener);
// 设置页面监听器
public void setPageListener(WebViewUtils.IPageListener listener);
// 设置 js 消息解析器
public void setJSParseListener(WebViewUtils.IJSMsgParseListener listener);
```

##3. 视频播放注意事项

- 宿主 xml 配置

  ```xml
  <activity android:name=".WebViewTestActivity"
         android:configChanges="orientation|screenSize|keyboardHidden|navigation|fontScale|locale"
              android:hardwareAccelerated="true"
              android:launchMode="singleTop"
              android:windowSoftInputMode="stateHidden|adjustResize"/>
  ```

- 宿主代码控制横竖屏切换

  ```java
  @Override
  public void onConfigurationChanged(Configuration newConfig) {
          super.onConfigurationChanged(newConfig);
          if (Configuration.ORIENTATION_LANDSCAPE == newConfig.orientation) {
              getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
          } else {
              getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
          }
      }
  ```

## 4. 返回健处理	

	宿主自己处理webview中的返回键，代码如下：

```java
@Override
public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK && mWebView != null && mWebView.canGoBack()) {
        mWebView.goBack();
        return true;
    }
    return super.onKeyDown(keyCode, event);
}
```

## 5. 代码示例

在`xml`文件中引入 **NGGWebView**

```xml
<com.tencent.ngg.webview.NGGWebView
    android:id="@+id/webview"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/black_alpha_50"
    android:scrollbars="none"
    />
```

Activity 中的 onCreate方法中

```java
		NLog.d(TAG, "invoked");
        // TODO: 2018/10/2 确保init操作在setContentView之前调用
        NGGWebViewConfig.getInstance().init(getApplicationContext(), false);

        setContentView(R.layout.activity_webview);
        NLog.d(TAG, "start findViewById");
        mWebView = findViewById(R.id.webview);
        NLog.d(TAG, "start setHostActivity");
        mWebView.setHostActivity(this);


        mWebView.setJSParseListener(new WebViewUtils.IJSMsgParseListener() {
            @Override
            public boolean parseMessage(String msg) {
                return false;
            }
        });

        mWebView.setScrollListener(new WebViewUtils.IScrollChangeListener() {
            @Override
            public void onPageEnd(int l, int t, int oldl, int oldt) {

            }

            @Override
            public void onPageTop(int l, int t, int oldl, int oldt) {

            }

            @Override
            public void onPageScrollChanged(int l, int t, int oldl, int oldt) {

            }
        });

        mWebView.setPageListener(new WebViewUtils.IPageListener() {
            @Override
            public void onPageStarted() {

            }

            @Override
            public void onPageFinished() {

            }

            @Override
            public void onReceiveTitle(String title) {

            }
            @Override
            public void onReceiveError(int errorCode, String description, String failingUrl) {
            }
        });

        NLog.d(TAG, "start loadUrl");
        mWebView.loadUrl(URL);
```

