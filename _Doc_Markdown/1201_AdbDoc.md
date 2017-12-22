# Adb 命令行操作

Android 调试桥 [官方传送门][adb]

## 1 截图

```bash
adb shell screencap -p /sdcard/screenshot.png
adb pull /sdcard/screenshot.png ./
```

## 2 安装 App

```bash
/**查看连接计算机的设备**/
adb devices

/**获取序列号**/
adb get-serialno

/**对指定的设备进行安装**/
adb -s <serialNumber> install <path-to-apk>
```

## 3 启动 App

```bash
adb shell am start -n <package_name>/.<activity_class_name>

adb shell am start -a android.intent.action.VIEW -n cn.uc.test/.MainActivity -d http://www.baidu.com
```

## 4 查看 64/32 位机型

```bash
capricorn:/ # adb shell getprop ro.product.cpu.abi
arm64-v8a	// 64
armeabi-v7a	// 32

capricorn:/ # uname -m
aarch64
capricorn:/ # uname -a
Linux localhost 3.18.31-perf-g0bf156d-01021-gae3cb9b #1 SMP PREEMPT Thu Nov 16 03:34:01 CST 2017 aarch64
```



# Reference

[Android学习第一天-adb常用命令][adb2]

[Adb 命令大全][adb3]


[adb]: https://developer.android.com/studio/command-line/adb.html

[adb2]:http://www.cnblogs.com/xiaoxuetu/p/3411214.html
[adb3]:http://www.jianshu.com/p/860bc2bf1a6a

