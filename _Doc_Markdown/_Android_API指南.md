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

### 1> Intent 及 过滤器

PendingIntent 是 Android 提供的一种用于外部程序调起自身程序的能力，生命周期不与主程序相关。外部程序通过 PendingIntent 只能调用起三种组件：

- Activity
- Service
- Broadcast

PendingIntent 的使用场景有三个：

- 使用 AlarmManager 设定闹钟
- 在系统状态栏显示 Notification
- 在桌面显示 Widget


### 2> [通用Intent](https://developer.android.com/guide/components/intents-common.html)

- 闹钟

  - 创建闹钟

    ```java
    public void createAlarm(String message, int hour, int minutes) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } 
    } 
    ```


  - 创建定时器

  - 显示所有闹钟

- 日历

  - 添加日历事件

- 相机

  - 拍摄照片或视频并将其返回
  - 以静态图像模式启动相机应用
  - 以视频模式启动相机应用

- 联系人/人员应用

  - 选择联系人
  - 选择特定联系人数据
  - 查看联系人
  - 编辑现有联系人
  - 插入联系人

- 电子邮件
  - 撰写带有可选附件的电子邮件

- 文件存储 

  - **检索特定类型的文件**

    用户可以不选择现有照片，而是用相机拍摄新照片

  - **打开特定类型的文件**

- 本地操作

  - 叫车 - 仅限 Android Wear

- 地图

  - 显示地图上的位置

- 音乐或视频

  - 播放媒体文件
  - **基于搜索查询播放音乐**

- 新笔记

  - 创建笔记

- 电话

  - 发起通话

- 搜索

  - 使用特定应用搜索，使自己的应用支持搜索
  - 执行网页搜索

- 设置

  - 打开特定设置部分 - 如需在您的应用要求用户更改内容时打开某个系统设置屏幕，请使用下列其中一个 Intent 操作打开与操作名称对应的设置屏幕。

- 发送短信

  - 撰写带附件的短信/彩信

- 网络浏览器

  - 加载网址

- 使用 Android 调试桥验证 Intent

  ```bash
  adb shell am start -a <ACTION> -t <MIME_TYPE> -d <DATA> \
    -e <EXTRA_NAME> <EXTRA_VALUE> -n <ACTIVITY>
  ```

  比如

  ```bash
  adb shell am start -a android.intent.action.DIAL \
    -d tel:555-5555 -n org.example.MyApp/.MyActivity
  ```

### 3> Activity

- 生命周期

  Activity 的**整个生命周期**发生在 `onCreate()` 调用与 `onDestroy()` 调用之间。

  Activity 的**可见生命周期**发生在 `onStart()` 调用与 `onStop()` 调用之间。

  Activity 的**前台生命周期**发生在 `onResume()` 调用与 `onPause()` 调用之间。

  ![img](https://developer.android.com/images/activity_lifecycle.png)

  ![img](https://developer.android.com/images/fundamentals/restore_instance.png)

  生命周期回调的顺序经过明确定义，当两个 Activity 位于同一进程，并且由一个 Activity 启动另一个 Activity 时，其定义尤其明确。 以下是当 Activity A 启动 Activity B 时一系列操作的发生顺序：

  1. Activity A 的 `onPause()` 方法执行。
  2. Activity B 的 `onCreate()`、`onStart()` 和 `onResume()` 方法依次执行。（Activity B 现在具有用户焦点。）
  3. 然后，如果 Activity A 在屏幕上不再可见，则其 `onStop()` 方法执行。

- 片断 Fragment

  - 片段必须始终嵌入在 Activity 中，其生命周期直接受宿主 Activity 生命周期的影响。

  - 生命周期

    ![img](https://developer.android.com/images/fragment_lifecycle.png)

    ```java
    // Create new fragment and transaction 
    Fragment newFragment = new ExampleFragment(); 
    FragmentTransaction transaction = getFragmentManager().beginTransaction();
     
    // Replace whatever is in the fragment_container view with this fragment, 
    // and add the transaction to the back stack 
    transaction.replace(R.id.fragment_container, newFragment);
    transaction.addToBackStack(null); 
     
    // Commit the transaction 
    transaction.commit(); 
    ```

  - 与 Activity 通信

  - 创建对 Activity 的事件回调

  - Activity 生命周期与片段生命周期之间的最显著差异在于它们在其各自返回栈中的存储方式。 默认情况下，Activity 停止时会被放入由系统管理的 Activity 返回栈（以便用户通过*返回*按钮回退到 Activity，[任务和返回栈](https://developer.android.com/guide/components/tasks-and-back-stack.html)对此做了阐述）。不过，仅当您在移除片段的事务执行期间通过调用 `addToBackStack()` 显式请求保存实例时，系统才会将片段放入由宿主 Activity 管理的返回栈。

  - 生命周期对比

    ![img](https://developer.android.com/images/activity_fragment_lifecycle.png)

    ​

- [加载器][https://developer.android.com/guide/components/loaders.html]

- [任务和返回栈][https://developer.android.com/guide/components/tasks-and-back-stack.html]

  launchMode 属性指定有关应如何将 Activity 启动到任务中的指令。您可以分配给 launchMode 属性的启动模式共有四种：

  - "standard"（默认模式）

  - "singleTop"

    如果当前任务的顶部已存在 Activity 的一个实例，则系统会通过调用该实例的 `onNewIntent()` 方法向其传送 Intent，而不是创建 Activity 的新实例。

  - "singleTask"

    1. 设置了"singleTask"启动模式的Activity，它在启动的时候，会先在系统中查找属性值affinity等于它的属性值taskAffinity的任务存在；如果存在这样的任务，它就会在这个任务中启动，否则就会在新任务中启动。因此，如果我们想要设置了"singleTask"启动模式的Activity在新的任务中启动，就要为它设置一个独立的taskAffinity属性值。
    2. 如果设置了"singleTask"启动模式的Activity不是在新的任务中启动时，它会在已有的任务中查看是否已经存在相应的Activity实例，如果存在，就会把位于这个Activity实例上面的Activity全部结束掉，即最终这个Activity实例会位于任务的堆栈顶端中。

  - "singleInstance"

    1. 以singleInstance模式启动的Activity具有全局唯一性，即整个系统中只会存在一个这样的实例
    2. 以singleInstance模式启动的Activity具有独占性，即它会独自占用一个任务，被他开启的任何activity都会运行在其他任务中（官方文档上的描述为，singleInstance模式的Activity不允许其他Activity和它共存在一个任务中）
    3. 被singleInstance模式的Activity开启的其他activity，能够开启一个新任务，但不一定开启新的任务，也可能在已有的一个任务中开启(通过 taskAffinity 起作用)

  **清理返回栈**

  ​

- [概览屏幕][https://developer.android.com/guide/components/recents.html]

  概览屏幕（也称为最新动态屏幕、最近任务列表或最近使用的应用）是一个系统级别 UI，其中列出了最近访问过的 Activity 和任务。

  ​

## 4. 应用资源

## 5. 应用清单

每个应用的根目录中都必须包含一个 `AndroidManifest.xml` 文件（且文件名精确无误）。

只有 `<manifest>` 和 `<application>` 元素是必需的，它们都必须存在并且只能出现一次。

同一级别的元素通常不分先后顺序。例如，`<activity>`、`<provider>` 和 `<service>` 元素可以按任何顺序混合在一起。这条规则有两个主要例外：

- `<activity-alias>` 元素必须跟在别名所指的 `<activity>` 之后。
- `<application>` 元素必须是 `<manifest>` 元素内最后一个元素。换言之，`</manifest>` 结束标记必须紧接在 `</application>` 结束标记后。

1. ​



## 6. 用户界面

# Android Studio

## 1. [简介](https://developer.android.com/studio/intro/index.html)

### 工具窗口

- 选择项目的 **Problems** 视图会显示指向包含任何已识别编码和语法错误（例如布局文件中缺失一个 XML 元素结束标记）的源文件的链接。

  ![](https://developer.android.com/studio/images/intro/problems-view_2-1_2x.png)

- 您可以随时通过按两下 Shift 键或点击 Android Studio 窗口右上角的放大镜搜索源代码、数据库、操作和用户界面的元素等。此功能非常实用，例如在您忘记如何触发特定 IDE 操作时，可以利用此功能进行查找。

- 要返回到当前默认工具窗口布局，请点击 **Window > Restore Default Layout** 或点击 **Window > Store Current Layout as Default** 自定义默认布局。

- 快捷键

  | 工具窗口                  | Windows 和 Linux       | Mac                   |
  | --------------------- | --------------------- | --------------------- |
  | Project               | **Alt+1**             | **Command+1**         |
  | Version Control       | **Alt+9**             | **Command+9**         |
  | Run                   | **Shift+F10**         | **Control+R**         |
  | Debug                 | **Shift+F9**          | **Control+D**         |
  | Android Monitor       | **Alt+6**             | **Command+6**         |
  | Return to Editor      | **Esc**               | **Esc**               |
  | Hide All Tool Windows | **Control+Shift+F12** | **Command+Shift+F12** |

  如果您想要隐藏所有工具栏、工具窗口和编辑器选项卡，请点击 **View > Enter Distraction Free Mode**。 此操作可启用*无干扰模式*。 要退出“无干扰模式”，请点击 **View > Exit Distraction Free Mode**。

### 代码自动完成

Android Studio 有三种代码自动完成类型，您可以使用键盘快捷键访问它们。

| 类型     | 说明                                       | Windows 和 Linux         | Mac                     |
| ------ | ---------------------------------------- | ----------------------- | ----------------------- |
| 基本自动完成 | 显示对变量、类型、方法和表达式等的基本建议。 如果连续两次调用基本自动完成，将显示更多结果，包括私有成员和非导入静态成员。 | **Control+空格**          | **Control+空格**          |
| 智能自动完成 | 根据上下文显示相关选项。 智能自动完成可识别预期类型和数据流。 如果连续两次调用智能自动完成，将显示更多结果，包括链。 | **Control+Shift+空格**    | **Control+Shift+空格**    |
| 语句自动完成 | 为您自动完成当前语句，添加缺失的圆括号、大括号、花括号和格式化等。        | **Control+Shift+Enter** | **Shift+Command+Enter** |

您还可以按 **Alt+Enter** 执行快速修复并显示建议的操作。

### 导航

- 使用*最近文件*操作在最近访问的文件之间切换。 按 **Control+E**（在 Mac 上，按 **Command+E**）调出“最近文件”操作。
- 使用*文件结构*操作查看当前文件的结构。 按 **Control+F12**（在 Mac 上，按 **Command+F12**）调出“文件结构”操作。
- 使用*导航至类*操作搜索并导航至项目中的特定类。 按 **Control+N**（在 Mac 上，按 **Command+O**）调出此操作。
- 使用*导航至文件*操作导航至文件或文件夹。 按 **Control+Shift+N**（在 Mac 上，按 **Command+Shift+O**）调出“导航至文件”操作。 要搜索文件夹，但不搜索文件，请在表达式末尾添加“/”。
- 使用*导航至符号*操作按名称导航至方法或字段。 按 **Control+Shift+Alt+N**（在 Mac 上，按 **Command+Shift+Alt+O**）调出“导航至符号”操作。
- 按 **Alt+F7** 查找引用当前光标位置处的类、方法、字段、参数或语句的所有代码片段。

### 样式和格式化

- 在您编辑时，Android Studio 将自动应用代码样式设置中指定的格式设置和样式。 您可以通过编程语言自定义代码样式设置，包括指定选项卡和缩进、空格、换行、花括号以及空白行的约定。要自定义代码样式设置，请点击 **File > Settings > Editor > Code Style**（在 Mac 上，点击 **Android Studio > Preferences > Editor > Code Style**）。
- 也可以通过按 **Control+Alt+L**（在 Mac 上，按 **Opt+Command+L**）显式调用*重新格式化代码*操作，或按 *Control+Alt+I\**（在 Mac 上，按 **Alt+Option+I**）自动缩进所有行。

### [键盘快捷键](https://developer.android.com/studio/intro/keyboard-shortcuts.html)

### [Workflow 流程](https://developer.android.com/studio/workflow.html)

## 2. [管理项目](https://developer.android.com/studio/projects/index.html)

### 1> [创建项目](https://developer.android.com/studio/projects/create-project.html)

### 2> [Add Kotlin Code](https://developer.android.com/studio/projects/add-kotlin.html)

### 3> [C/C++](https://developer.android.com/studio/projects/add-native-code.html)

### 4> [创建 Android 库](https://developer.android.com/studio/projects/android-library.html)

Android 库在结构上与 Android 应用模块相同。它可以提供构建应用所需的一切内容，包括源代码、资源文件和 Android 清单。不过，Android 库将编译到您可以用作 Android 应用模块依赖项的 Android 归档 (AAR) 文件，而不是在设备上运行的 APK

库模块在以下情况下非常有用：

- 构建使用某些相同组件（例如 Activity、服务或 UI 布局）的多个应用。
- 构建存在多个 APK 变体（例如免费版本和付费版本）的应用并且需要在两种版本中使用相同的核心组件。

在任何一种情况下，只需要将您希望重用的文件移动到库模块中，然后以依赖项的形式为每个应用模块添加库。

## 3. [编写应用](https://developer.android.com/studio/write/index.html)

Android Studio 包含每个开发阶段用到的工具，但最重要的功能是编写应用：编写代码、构建布局、创建映像，并在这个过程中保持高效。

创建[实时模板](https://developer.android.com/studio/write/index.html)，点击 **File > Settings > Editor > Live Templates**。

快速创建新文件，在 Project 窗口中点击所需的目录，然后按 **Alt + Insert**（在 Mac 上按 **Command + N**）。

创建新布局，点击 **File > New > XML > Layout XML File**。

#### 1> 从模板添加代码

![img](https://developer.android.com/studio/images/projects/templates-menu_2-2-beta_2x.png)

#### 2> [Find Sample Code](https://developer.android.com/studio/write/sample-code.html)

**File > New > Import Sample**

或者

点击一个变量，类型或者方法，右键 select **Find Sample Code**。









