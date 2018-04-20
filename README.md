## CIYOU

### 思源教育

该项目是基于翻转课堂实现的小学网络教育平台。

国内已经有很多尝试使用翻转课堂模式的教学网络平台，但是现在大多数的网络教学平台不能很好的抓住翻转课堂的痛点，翻转课堂教学的重点和难点在于教师要如何对学生进行个性化指导，这就需要有翻转课堂网络教学平台的辅助，需要系统记录学生的学习进度，学习中遇到的困难、问题，教师可以查看到这些数据，教师通过平台提供的分析数据图表，可以清晰地了解到学生在课前的学习中，都学到了什么程度，疑惑难点集中在哪些地方，落后的学生对什么样问题会产生疑惑和纠结。如此，教师才能根据不同的学生的学习情况，设置不同的教学策略，开展有针对性的辅导，这才是翻转课堂的灵魂所在。

因为从目前的中国国情来讲，翻转课堂还太适合中国的中高考，而针对于小学进行翻转课堂的教学，更加有研究意义。


### 翻转课堂

目前国内的小学教育大多数是小班授课制，而小班授课制度在我们传统的课堂教学模式下也不能解决学生间理解能力差异的问题，而且老师作为课堂的掌控者，学生在上课时间内没有弄明白问题，老师在课堂上也不会停下来解答，相反会继续后面的教学任务，但是知识点与知识点之间是有关联的，这就造成了学生的学习效率大大降低了，翻转课堂的出现很好的解决了这一问题。

翻转课堂的教育模式在教育应用这一方面的前景十分广阔。在计算机科学技术与互联网的快速发展的今天，“翻转课堂式”的网络教学模式慢慢出现在人们的视野。翻转课堂把传统课堂的教学模式彻底颠覆了。这种模式满足学生对不同科目的知识点进行个性化学习，还能帮助学生查漏补缺又能巩固知识，传统课堂的教学资源被大大地补充和拓展了。对于老师而言，教师从一个知识的灌输者转变为一个课堂的管理者，教师可以更加容易针对学生进行个性化指导。



### 开发环境简介

该基于翻转课堂的小学网络教育平台是基于Groovy语言开发，使用Spring Boot+Mybatis框架为技术支持，并使用Maven来管理项目的构建，系统数据库使用的是MySQL，前端使用Bootstrap轻量级后台模板AdminLTE。这个项目还使用Shiro作为安全框架，采用Redis作为项目的缓存，并使用阿里巴巴开源平台上一个数据库连接池Druid。Spring Boot我属于初学者，学了一个月的时间包括其他的一些第三方技术的集成学习。所以这个项目也算是我对Spring Boot的学习和实践，大概用了一个月的时间完成了这个项目，由于时间紧迫，功能还不是很完善，部分功能也是从我另一个项目中拿来使用，所以项目中也有很多需要改进和不足的地方，以后会继续学习和改进。



#### Groovy概述
Groovy是脚本语言，和系统编程语言比如Java相比，它表示能力更加强，能提供更高的抽象等级、编程生产力，更快捷的开发能力。

关于Groovy的学习，可以查看我的博文：[Groovy系列](https://blog.csdn.net/cckevincyh/article/category/6989995)。

#### Spring Boot概述
随着动态语言的流行，Java的开发显得格外的笨重，比如多配置、低开发效率、复杂的部署流程以及第三方技术集成难度大。而Spring Boot很好地解决了上述问题，它有一个内置的配置，让你不需要手动进行配置，可以很简单快捷创建一个独立运行的Spring框架项目。使用Spring Boot你可以不用或者只要很少的配置，并且对主流开发框架进行无配置集成，而且项目可独立运行，极大地提高了开发、部署的效率。

关于Spring Boot的学习还有我项目中的遇到的问题、项目中与其他第三方技术的集成配置。可以点击我的博文查看：[Spring Boot系列](https://blog.csdn.net/cckevincyh/article/category/7378037)

当然我也推荐初学者最好去看看作者**恒宇少年**的[Spring Boot 核心技术系列](https://www.jianshu.com/c/3f69deddbed3) 


#### Mybatis概述
Mybatis是支持定制化SQL、存储过程和高级映射的优秀的持久层框架。而且Mybatis没有其他的第三方依赖，所以框架体积就很小，而且Mybatis简单易于学习和使用。


#### Redis概述
Redis是非关系型数据库。以往我们的项目都是采用直接访问数据库，但一旦访问量过大或者访问过于频繁，都会对项目的数据库带来很大的压力，而Redis的出现很好的解决了这一问题，Redis数据库可以缓存我们项目的数据和session，实现分布式session共享。因为基于键值对，所以Redis性能很强，读写速度快，满足实时的高并发需求。


#### Shiro概述
Apache Shiro是一个强大易用的Java安全框架,执行身份验证、授权、密码学和会话管理。



#### Druid概述

Druid是阿里巴巴开源平台上一个数据库连接池，具有强大的监控性和扩展性，是Java语言中最好的数据库连接池。



### 系统功能

由于时间紧迫，所以设计的功能比较少，有些设想好的功能也只能毙掉了，比如教师布置作业模块，学生教师交流解答模块，还有视频学习进度记录模块等等，答题模块也是从我github上的另一个[在线考试系统](https://github.com/cckevincyh/OnLineTest)项目中抽取出来的。下面就是这个项目已经实现好的功能模块图。

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/系统功能.png)




### 系统截图



#### 管理员登录界面

url：adminLogin

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/管理员登录.png)

登陆模块是对不同用户的身份进行验证，然后进入主界面。登陆入口有三个，分别是学生、教师、管理员登陆入口。系统首先通过区分不同的登陆入口，不同的登录入口LoginType对应不同的角色，比如：Admin、Teacher、Student，前台页面先对输入的用户名和密码进行js校验判断输入值是否合法，然后在把用户名、密码还有LoginType传入后台进行判断，后台也会对传入的数据进行校验合法性，校验成功后使用Shiro对登陆用户进行认证和授权。Shiro根据LoginType的值去找对应的AuthorizingRealm进行认证，认证过程由Shiro自动完成，然后返回认证结果，认证成功则跳转到相应的主界面，认证失败则返回相应的错误信息。

#### 教师登录界面

url：teacherLogin

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/教师登录.png)


#### 学生登录界面

url：login

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/学生登录.png)


#### 管理员主界面

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/管理员主界面.png)



#### 管理员管理模块

- 管理员管理

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/管理员管理界面.png)

- 添加管理员

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/添加管理员.png)

- 修改管理员

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/修改管理员.png)

- 超级管理员授权

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/管理员授权.png)

超级管理员可以对管理员的权限进行授权，前台通过ajax把管理员的ID传入后台，后台查询数据库，把管理员的权限信息构造为一个TreeNode的对象，并把这个对象转化为一个JSON字符串返回给前台，前台使用bootstrap-treeview插件，把后台传入的JSON字符串构造出一个权限树，并把该管理员的权限信息显示在权限树上。超级管理员可以勾选权限，点击保存提交到后台，后台对提交的权限字符串信息进行分割处理，然后添加到数据库中。


#### 教师管理模块

- 教师管理

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/教师管理界面.png)


- 添加教师

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/添加教师.png)


- 修改教师

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/修改教师.png)



#### 学生管理模块


- 学生管理

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/学生管理.png)


- 添加学生

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/添加学生.png)


- 修改学生

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/修改学生.png)



#### 权限管理模块


- 查看权限

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/权限管理.png)


- 添加根权限

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/添加根权限.png)


- 添加子权限

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/添加子权限.png)

- 编辑权限

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/编辑权限.png)



#### 年级管理模块

- 年级管理

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/年级管理.png)

- 添加年级

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/添加年级.png)


- 修改年级

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/修改年级.png)



#### 班级管理模块


- 班级管理

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/班级管理.png)

- 添加班级

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/添加班级.png)


- 修改班级

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/修改班级.png)




#### 科目管理模块


- 科目管理

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/科目管理.png)

- 添加科目

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/添加科目.png)


- 修改科目

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/修改科目.png)


#### 视频审核模块


![image](https://github.com/cckevincyh/CIYOU/blob/master/img/视频管理.png)



#### 教师主界面

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/教师主界面.png)


#### 教师视频管理模块

- 教师视频管理

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/教师视频管理.png)


- 视频上传

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/添加视频.png)

上传视频和对应的视频封面。文件上传使用的是Baidu WebFE(FEX)团队开发的一款上传插件：WebUploader。WebUploader会把大文件分割成一块块然后并发上传到后台，这大大的提高了文件上传效率。

#### 教师班级分配管理模块

- 教师班级分配

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/教师班级分配.png)

- 添加班级分配

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/添加班级分配.png)


- 修改班级分配

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/修改班级分配.png)


#### 教师查看学生模块

该教师只能查看自己班级的学生资料

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/查看学生.png)



#### 教师小测管理模块

- 教师小测管理

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/教师小测管理.png)

- 添加小测

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/添加小测.png)


- 修改小测

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/修改小测.png)



#### 小测试题管理模块

- 添加选择题

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/添加选择题.png)

- 添加判断题

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/添加判断题.png)



#### 教师成绩查询模块

该教师只能查看自己班级学生的成绩

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/教师成绩查询.png)



#### 教师成绩查询模块

- 教师成绩查询

该教师只能查看自己班级学生的成绩

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/教师成绩查询.png)


- 教师查看成绩详情

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/教师成绩详情.png)


#### 班级分析模块
分析该班级各科平均成绩与全年级各科平均成绩的对比图

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/班级分析.png)


#### 学生分析模块
教师可以在学生分析模块中，选择学生查看该学生的小测成绩分析，可以分析该学生各科成绩与该学生所在班级和所在年级的各科平均成绩进行比较，获得对比的统计图表。统计图表使用Chart.js, Chart.js是一个基于HTML5 canvas技术的开源图表绘制工具库,简化了在网站上绘制动态图表的工作。

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/学生分析.png)

#### 小测分析模块
在小测分析模块中，教师可以选择想要分析的小测，系统分析出各种图表，图表显示该小测的错题分布情况，可以查看错误率最高的题目是哪个。

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/小测分析.png)



#### 学生观看视频模块

- 学生观看视频

学生登录成功后，进入观看视频页面，学生可以选择年级、科目来进行筛选想要的视频。前台使用Video.js来操作视频，Video.js 是一个通用的在网页上嵌入视频播放器的 JS 库，Video.js 自动检测浏览器对 HTML5 的支持情况，如果不支持 HTML5 则自动使用 Flash 播放器。

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/学生观看视频.png)

- 视频播放界面

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/视频播放.png)


#### 学生小测模块

- 学生小测

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/学生小测.png)

- 学生答题界面

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/学生答题.png)



#### 我的成绩模块

- 我的成绩

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/我的成绩.png)

- 学生得分详情

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/得分详情.png)


### 个人设置模块

- 个人设置

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/个人设置.png)

- 头像上传

![image](https://github.com/cckevincyh/CIYOU/blob/master/img/上传头像.png)


### 学习参考

想学习在线教育网站设计的可以参考下[因酷在线教育](http://wx.inxedu.com/)，使用java实现的。

还可以参考[TeachLine](https://github.com/95DBC/TeachLine)，是根据[因酷在线教育](http://wx.inxedu.com/)旧版系统进行改造的。

还有下面大神们写的项目也可以参考：

[高仿慕课网：py3.5 + Django1.10 + xadmin 搭建的在线课程教育平台](https://github.com/zaxlct/imooc-django)

[萌课(tinymooc)](https://github.com/Lemonjing/TinyMooc)

[xstrap](https://github.com/xuebus/xstrap)



### 环境配置

1. 创建数据库，导入sql文件。
2. 下载Redis，并打开Redis服务：[下载和安装方法](https://blog.csdn.net/cckevincyh/article/details/79632542)。
3. 使用IDEA导入项目：[IntelliJ IDEA如何导入maven结构的web工程](https://jingyan.baidu.com/article/47a29f24460367c0142399ee.html)
4. 修改数据库配置
![image](https://github.com/cckevincyh/CIYOU/blob/master/img/数据库配置.png)
5. 修改启动端口Maven pom.xml
![image](https://github.com/cckevincyh/CIYOU/blob/master/img/修改端口.png)
6. 编译项目
7. 项目运行
![image](https://github.com/cckevincyh/CIYOU/blob/master/img/运行设置入口.png)
![image](https://github.com/cckevincyh/CIYOU/blob/master/img/运行设置.png)
8. 管理员后台[http://localhost:8080/adminLogin](http://localhost:8080/adminLogin)，用户名密码都是admin
9. 教师后台[http://localhost:8080/teacherLogin](http://localhost:8080/teacherLogin)
10. 学生入口：[http://localhost:8080/login](http://localhost:8080/login)