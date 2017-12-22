[TOC]

#Android API 指南

## 1. 简介

1. 任何权限都可属于一个权限组，包括正常权限和应用定义的权限。但权限组仅当权限危险时才影响用户体验。可以忽略正常权限的权限组。
2. 要实施您自己的权限，必须先使用一个或多个 <permission> 元素在 `AndroidManifest.xml` 中声明它们。通过定义<user-permission> 元素请求其他应用的自定义权限。不过，您应该仔细评估您的应用是否有必要这样做。
3. [URI 权限](https://developer.android.com/guide/topics/security/permissions.html#permissions)

## 2. 平台架构

[Android 平台架构](https://developer.android.com/guide/platform/index.html)![1219_android-stack_2x](images/1219_android-stack_2x.png)

对于运行 Android 5.0（API 级别 21）或更高版本的设备，每个应用都在其自己的进程中运行，并且有其自己的 [Android Runtime (ART)](http://source.android.com/devices/tech/dalvik/index.html) 实例，在 Android 版本 5.0（API 级别 21）之前，Dalvik 是 Android Runtime。如果您的应用在 ART 上运行效果很好，那么它应该也可在 Dalvik 上运行，但[反过来不一定](https://developer.android.com/guide/platform/verifying-apps-art.html)。



## 3. 应用组件

PendingIntent 是 Android 提供的一种用于外部程序调起自身程序的能力，生命周期不与主程序相关。外部程序通过 PendingIntent 只能调用起三种组件：

- Activity
- Service
- Broadcast

PendingIntent 的使用场景有三个：

- 使用 AlarmManager 设定闹钟
- 在系统状态栏显示 Notification
- 在桌面显示 Widget