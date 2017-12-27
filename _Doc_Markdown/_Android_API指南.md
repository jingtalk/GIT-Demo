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









