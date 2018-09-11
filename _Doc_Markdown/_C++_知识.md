[TOC]

# C++

## 1. 知识点

### 1>. 纯虚函数

纯虚函数是一种特殊的虚函数，在许多情况下，在基类中不能对虚函数给出有意义的实现，而把它声明为纯虚函数，它的实现留给该基类的派生类去做。这就是纯虚函数的作用。

类包含纯虚函数，就是“抽象类”

### 2>. 虚函数

在某基类中声明为 virtual 并在一个或多个派生类中被重新定义的成员函数，用法格式为：

virtual 函数返回类型 函数名（参数表） {函数体}；



## 2. mmap









实现多态性，通过指向派生类的基类指针或引用，访问派生类中同名覆盖成员函数

- 实现原理

## x. Reference

1. [C++ 在继承中虚函数、纯虚函数、普通函数，三者的区别][1]
2. [C/C++杂记：虚函数的实现的基本原理][2]
3. [认真分析mmap：是什么 为什么 怎么用][3]
4. [从内核文件系统看文件读写过程][4]
5. [理解mmap][5]
6. [【腾讯Bugly干货分享】微信终端跨平台组件 Mars 系列 - 我们如约而至][6]



[1]:https://www.cnblogs.com/xudong-bupt/p/3570304.html
[2]:https://www.cnblogs.com/malecrab/p/5572730.html
[3]:https://www.cnblogs.com/huxiao-tee/p/4660352.html
[4]:http://www.cnblogs.com/huxiao-tee/p/4657851.html
[5]:https://felixzhang00.github.io/2017/02/25/%E7%90%86%E8%A7%A3mmap/
[6]:https://blog.csdn.net/tencent_bugly/article/details/53992882