[TOC]

# 一、 Unity

## 1. 编译相关

- jdk 版本

- Android sdk 版本，默认会取 `\android-sdk-windows\build-tools` 下面最高版本编译

- unity4.X 和 Android26 不兼容同，编译 APK 会出现黑屏，[传送门][unity]

- unity 在编译的时候报错，如果日志太多是会被截断的，此时会有下面的提示，关键字`message truncated`

  ```bash
  F:\_Demo_Unity\2.0\facebook1120\4.27.0\iMSDK_Unity3D_V2.0.0\imsdk\Temp\StagingArea\android-libraries\appcompat-v7-23.4.0\res\drawable<message truncated>
  ```

  此时可以点击右上角的图标，然后点击`Open Editor Log` 获取到完整的日志信息。

  ![1122_unitylog](images/1122_unitylog.png)

  ​

## 2. click 事件

配置选项 `unityplayer.ForwardNativeEventsToDalvik` 为 true，即可处理点击事件

```xml
<activity 
          android:name="com.unity3d.player.UnityPlayerNativeActivity"
          android:label="@string/app_name">  
  <intent-filter>
    <action android:name="android.intent.action.MAIN"/>
    <category android:name="android.intent.category.LAUNCHER"/>
    <category android:name="android.intent.category.LEANBACK_LAUNCHER"/>
  </intent-filter>
  
  <meta-data android:name="unityplayer.UnityActivity" android:value="true"/>
  <!--此处配置为true-->
  <meta-data android:name="unityplayer.ForwardNativeEventsToDalvik" android:value="false"/>
</activity>
```

# 三、AS

## 1. Gradle

Android Studio 通过 [Gradle][build] 来构建应用程序。

通过 AS 查看工程 Gradle 版本，查看路径 `File->Project Structure->Project`

![1211_gradleVersion](images/1211_gradleVersion.png)



### 1> Gradle Plugin 版本

[官方传送门][gradle-plugin]

此为 Gradle 插件工具版本。

当您更新Android Studio时，系统可能会提示您自动将 Gradle 的 Android 插件更新为最新版本， 您可以选择接受更新或根据项目的构建要求手动指定版本。

它在工程目录的顶级 build.gradle 文件中

```xml
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.3.0'
        classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}
```



### 2> Gradle 版本

[官方传送门][gradle]

此为使用的 Gradle 版本。

更新Android Studio时，您可能会收到提示，同时将Gradle更新为最新版本。 您可以选择接受更新或根据项目的构建要求手动指定版本。

它在工程根目录下的 `\gradle\wrapper\gradle-wrapper.properties` 文件中。

```xml
#Mon Mar 27 11:40:24 CST 2017
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-3.3-all.zip

```

[][]

它与 Gradle Plugin 的对应关系如下：

![1211_gradleVersion](images/1211_gradleVS.png)

## 2. 快捷键

|                   快捷键                    |               功能                |
| :--------------------------------------: | :-----------------------------: |
|             Ctrl + Shift + A             | 快速找到命令入口，通过该命令弹窗中输入相应的提示语点击查询即可 |
|                 Ctrl + J                 |  弹出代码快捷键模板，如 TODO/FIXME 或者自定义   |
|                 Alt + F1                 |         查看代码所在的位置，非常实用          |
|              Ctrl + Alt + T              |          选中代码， 快速写包围方法          |
|         Ctrl + Shift + Backspace         |            返回上一个编辑位置            |
|                   F11                    |             添加、移除标签             |
|                Ctrl + F11                |          添加、移除书签(带标记)           |
|               Shift + F11                |       显示所有的书签列表，并且是可以搜索的        |
|        File \|Settings \| Keymap         |            设置、修改快捷键             |
| File \| Settings  Editor \| Code Style \| Java |             设置命名前缀              |
|      Alt + Enter \|  Ctrl + Alt + O      |          快速导包， 清除无用导包           |
| File \| Settings \| Editor \| General \| Auto Import |        自动完成 快速导包， 清除无用导包        |
| File \| Settings Editor \| Color & Fonts \| Android Logcat |          设置 LogCat 颜色           |
| File \| Settings \|  Editor \| Code Style \| Live Templates |        设置活动模板 常用代码的一个缩写         |
|  选中代码 \| Refactor \| Extract \| Method   |              快速写方法              |
|  选中代码 \| Refactor \| Extract \|  Style   |            快速写style             |





# 四、Git

## 1. git stash

[详细教程][git-stash]



## 2. 一个项目管理多个git分支

如果有这样一个场景，一个项目里有多个module，是分别提交到git上的。每个module是一个独立的项目，项目间可以互相引用。

```xml
include ':app'

include ':base'
project(':base').projectDir = new File(settingsDir, '../iMSDK-Android-Base/base')
```

但是在 AS 下面，发现只有第一个项目可以顺利提交到git。其他项目的git地址都默认到第一个项目上了。

项目目录.idea下有个 vcs.xml 文件。这里记录了git的项目名称，但是只有第一个可以提交的项目，手动修改，增加了其他项目后名称后，可以顺利从git上push和pull。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project version="4">
  <component name="VcsDirectoryMappings">
    <mapping directory="$PROJECT_DIR$" vcs="Git" />
    <mapping directory="$PROJECT_DIR$/../iMSDK-Android-Base" vcs="Git" />
  </component>
</project>
```


# 五、SQL

[W3school-SQL 教程][sql]

查询语句

```sql
// 查询所有的游戏
SELECT DISTINCT `game` FROM `dbiTOP_ClientMonitor`.`tbClientAppVersion` WHERE 1

// 查询指定游戏的 app 版本分布
SELECT `app`, `game`, SUM(`count`) AS `count` FROM `dbiTOP_ClientMonitor`.`tbClientAppVersion` where version <> '' AND `game`=1135 GROUP BY `app`

// 删除表
DROP TABLE tbClientAppVersion_1137

// 建表 1135 gms
CREATE TABLE `tbClientAppVersion_1135_gms` AS SELECT `app`, `game`, `version`, SUM(`count`) AS `count` FROM `dbiTOP_ClientMonitor`.`tbClientAppVersion` where version <> '' AND `game`=1135 AND `app`='gms' GROUP BY `version` DESC

// 插入数据
 INSERT INTO dbiTOP_ClientMonitor.tbClientAppVersion_1135_gms (app,game,version,count) VALUES ('s', '11', 's', 's');
 

// 查询 app 这一列并去重
SELECT DISTINCT `app` FROM `dbiTOP_ClientMonitor`.`tbClientAppVersion` WHERE 1

// 条件查询
SELECT * FROM `dbiTOP_ClientMonitor`.`tbClientAppVersion` WHERE `app`='playgames'

// 总条数 - 返回一行数据
SELECT COUNT(*) FROM `dbiTOP_ClientMonitor`.`tbClientAppVersion` 
SELECT COUNT(*) FROM `dbiTOP_ClientMonitor`.`tbClientAppVersion` WHERE `app`='facebook'

// 将 count 这一列的数据全部相加 - 返回一行数据
SELECT SUM(`count`) AS `sumCount` FROM `dbiTOP_ClientMonitor`.`tbClientAppVersion` 


// 返回 app 及 对应 app 的所有 count 相加 - 如果 app 的取值在3种，则返回3行，count 相加
SELECT `app`, SUM(`count`) AS `sumCount` FROM `dbiTOP_ClientMonitor`.`tbClientAppVersion` GROUP BY `app`

SELECT `app`, `game`, SUM(`count`) AS `count` FROM `dbiTOP_ClientMonitor`.`tbClientAppVersion` WHERE `game`='1135' GROUP BY `app`

SELECT `app`, `game`, SUM(`count`) AS `count` FROM `dbiTOP_ClientMonitor`.`tbClientAppVersion` where version <> '' AND `game`=1135 GROUP BY `app`

DROP TABLE `tb_test`


// PHP 删除字符串中的括号及内容 ADB(12143123) 返回 ADB
return preg_replace('/\(.*?\)/', '', $_val);
```



# 六、语言

## 1. 编译性语言 + 解释性语言

Python是解释型语言，你的代码在执行时会一行一行地翻译成CPU能理解的机器码，这个翻译过程非常耗时，所以很慢。而C程序是运行前直接编译成CPU能执行的机器码，所以非常快。

代码不能加密。如果要发布你的Python程序，实际上就是发布源代码，这一点跟C语言不同，C语言不用发布源代码，只需要把编译后的机器码（也就是你在Windows上常见的xxx.exe文件）发布出去。要从机器码反推出C代码是不可能的，所以，凡是编译型的语言，都没有这个问题，而解释型的语言，则必须把源码发布出去。

### 1> 解释型语言

相对于编译型语言存在的，源代码不是直接翻译成机器语言，而是先翻译成中间代码，再由解释器对中间代码进行解释运行。比如Python/JavaScript / Perl /Shell等都是解释型语言。

程序不需要[编译](https://baike.baidu.com/item/%E7%BC%96%E8%AF%91)，程序在运行时才翻译成[机器语言](https://baike.baidu.com/item/%E6%9C%BA%E5%99%A8%E8%AF%AD%E8%A8%80)，每执 行一次都要翻译一次。因此效率比较低。

在运行程序的时候才翻译，专门有一个解释器去进行翻译，每个语句都是执行的时候才翻译。效率比较低，依赖解释器，跨 平台性好

### 2> 区别

计算机不能直接理解高级语言，只能直接理解[机器语言](https://baike.baidu.com/item/%E6%9C%BA%E5%99%A8%E8%AF%AD%E8%A8%80)，所以必须要把高级语言翻译成机器语言，计算机才能执行高级语言编写的程序。

一个是编译，一个是解释。

两种方式只是翻译的时间不同。[编译型语言](https://baike.baidu.com/item/%E7%BC%96%E8%AF%91%E5%9E%8B%E8%AF%AD%E8%A8%80)写的程序执行之前，需要一个专门的编译过程，把[程序编译](https://baike.baidu.com/item/%E7%A8%8B%E5%BA%8F%E7%BC%96%E8%AF%91)成为机器语言的文件，比如exe文件，以后要运行的话就不用重新翻译了，直接使用编译的结果就行了（exe文件），因为翻译只做了一次，运行时不需要翻译，所以编译型语言的程序执行效率高，但也不能一概而论，部分解释型语言的[解释器](https://baike.baidu.com/item/%E8%A7%A3%E9%87%8A%E5%99%A8)通过在运行时动态优化代码，甚至能够使解释型语言的性能超过编译型语言。

解释则不同，[解释性语言](https://baike.baidu.com/item/%E8%A7%A3%E9%87%8A%E6%80%A7%E8%AF%AD%E8%A8%80)的程序不需要编译，省了道工序，解释性语言在运行程序的时候才翻译，比如解释性basic语言，专门有一个解释器能够直接执行basic程序，每个语句都是执行的时候才翻译。这样解释性语言每执行一次就要翻译一次，效率比较低。解释是一句一句的翻译。

- 编译性语言不如解释性语言跨平台性好

  编译性语言例如c语言：用c语言开发了程序后，需要通过编译器把程序编译成机器语言（即计算机识别的二进制文件，因为不同的操作系统计算机识别的二进制文件是不同的），所以c语言程序进行移植后，要重新编译。（如windows编译成ext文件，linux编译成erp文件）。

  解释性语言，例如java语言，java程序首先通过编译器编译成class文件，如果在windows平台上运行，则通过windows平台上的java虚拟机（VM）进行解释。如果运行在linux平台上，则通过linux平台上的java虚拟机进行解释执行。所以说能跨平台，前提是平台上必须要有相匹配的java虚拟机。如果没有java虚拟机，则不能进行跨平台。

- 编译型与解释型的速度

  前者由于程序执行速度快，同等条件下对系统要求较低，因此像开发操作系统、大型应用程序、数据库系统等时都采用它，像C/C++、Pascal/Object Pascal（Delphi）等都是编译语言，而一些网页脚本、服务器脚本及辅助开发接口这样的对速度要求不高、对不同系统平台间的兼容性有一定要求的程序则通常使用解释性语言，如Java、JavaScript、VBScript、Perl、Python、Ruby、MATLAB 等等。

### 3> 编译型语言

[编译型语言](https://baike.baidu.com/item/%E7%BC%96%E8%AF%91%E5%9E%8B%E8%AF%AD%E8%A8%80)：程序在执行之前需要一个专门的编译过程，把程序编译成 为机器语言的文件，运行时不需要重新翻译，直接使用[编译](https://baike.baidu.com/item/%E7%BC%96%E8%AF%91)的结果就行了。程序执行效率高，依赖编译器，跨平台性差些。如C、C++、Delphi等



[unity]:https://forum.unity.com/threads/game-does-not-work-on-new-android-oreo.490142/
[build]:https://developer.android.com/studio/build/index.html

[gradle-plugin]:https://developer.android.com/studio/releases/gradle-plugin.html#updating-plugin
[gradle]:https://developer.android.com/studio/releases/gradle-plugin.html#updating-gradle

[git-stash]:http://www.jianshu.com/p/ea86475cf922
[sql]:http://www.w3school.com.cn/sql/index.asp

