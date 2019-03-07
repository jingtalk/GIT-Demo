# NGGAuth - 登陆分享组件

## 1. API接口

```java
public static boolean init(@NonNull Context context, NetWorkInitParam param);
public static boolean setChannel(@NonNull String channel);
public static void login(INGGResultListener<NGGLoginResult> listener);
public static void login(@NonNull List<String> permissions, INGGResultListener<NGGLoginResult> listener);
public static void logout(INGGResultListener<NGGResult> listener);
public static NGGLoginResult getLoginResult();
public static boolean isLogin();
public static void share(@NonNull NGGShareReqInfo reqInfo, INGGResultListener<NGGResult> listener);
public static void sendMessage(@NonNull NGGShareReqInfo reqInfo, INGGResultListener<NGGResult> listener);
public static void share(@NonNull String channel, @NonNull NGGShareReqInfo reqInfo, INGGResultListener<NGGResult> listener);
public static void sendMessage(@NonNull String channel, @NonNull NGGShareReqInfo reqInfo, INGGResultListener<NGGResult> listener);
```

