# Care_Note

## 介绍

这是一个我自己突发奇想的自动化天气关怀短信小程序，整合mybatis plus、spring boot等框架实现可控的短信服

务，有C、B两端的自动化菜单权限，及定时提醒和集成手机Email钉钉的自动化提醒为运维提供方便！最后由我和我

的小伙伴杰一起完成全部开发，功能会一步一步慢慢迭代更新的。可以用来服务自己的表白对象或亲人以及出售自己

购买的云服务短息等，更多详情见说明文档！

## 软件架构

软件架构说明：废话不多说我直接把的设计文档献给大家，搞开发玩的可以详细阅读，还有很多不足欢迎与我讨论，

联系我的渠道有很多的： **[https://www.yuque.com/jimoworld/javabj/care_note/](https://www.yuque.com/jimoworld/javabj/care_note/)** 

项目链接：

Gitee: **[https://gitee.com/chenjimo/Care_Note](https://gitee.com/chenjimo/Care_Note)** 

平台首页！ **[http://care.jimo.fun/](http://care.jimo.fun/)** 
![输入图片说明](https://foruda.gitee.com/images/1660478966724759070/屏幕截图.png "屏幕截图.png")

### 用户页

用户登录： **[http://care.jimo.fun/login/user](http://care.jimo.fun/login/user)** 
![输入图片说明](https://foruda.gitee.com/images/1660478899714027835/屏幕截图.png "屏幕截图.png")

用户首页： **[http://care.jimo.fun/user](http://care.jimo.fun/user)** 

![输入图片说明](https://foruda.gitee.com/images/1660478918248069693/屏幕截图.png "屏幕截图.png")
<img src="https://foruda.gitee.com/images/1660478930397560592/屏幕截图.png" alt="输入图片说明" title="屏幕截图.png" style="zoom:80%;" />

### 管理员页面

管理员登录： **[http://care.jimo.fun/login/admin](http://care.jimo.fun/login/admin)** 
![输入图片说明](https://foruda.gitee.com/images/1660478940979033020/屏幕截图.png "屏幕截图.png")
管理员首页： **[http://care.jimo.fun/admin](http://care.jimo.fun/admin)** 
![输入图片说明](https://foruda.gitee.com/images/1660479009851784570/屏幕截图.png "屏幕截图.png")

## 一、功能- 更多见上边开发文档

![输入图片说明](https://gitee.com/chenjimo/Care_Note/raw/Care-Note-0.9/imgn1.png)

![输入图片说明](https://gitee.com/chenjimo/Care_Note/raw/Care-Note-0.9/imgn2.png)

## 二、需求与设计架构‘

用户管理、消息管理、消息模板、订单管理、

新加入用户默认消息模板，添加用户判断键，未付款的用户只能享受一次发消息权力。

订单管理简化程序的复杂度，就人工控制吧（后续有精力在完善）！

当有人付款后给我的邮箱发送消息提示修改用户状态值即可。

1. 后台管控略图

   <img src="https://foruda.gitee.com/images/1660479313189368004/屏幕截图.png" alt="输入图片说明" title="屏幕截图.png" style="zoom: 80%;" />

2. 消费者页面

   <img src="https://foruda.gitee.com/images/1660479303589526154/屏幕截图.png" alt="输入图片说明" title="屏幕截图.png" style="zoom:80%;" />

### 安装教程其实很简单的！！！

1. 先下载代码最新版！

2. 然后修改配置文件(就下面这个图中的文件，路径都在图里！哪里需要修改文件中都有讲！！！)
   ![输入图片说明](https://foruda.gitee.com/images/1660479175737530083/屏幕截图.png "屏幕截图.png")

3. 然后初始化自己的数据库文件，见文档或Sql脚本！！！（项目根文件夹下的Care-Note.sql）



### 使用说明

然后就直接启动项目即可（详细上面文档都有讲）
项目端口号为：9277（默认）

### 参与贡献

点击即可跳转主页

- JIMO

  <div align="center">
      <a href="https://gitee.com/chenjimo">
      	<img src="https://portrait.gitee.com/uploads/avatars/user/3368/10105811_chenjimo_1647177308.png!avatar200">  
      </a>
  </div>

- yuli

  <div align="center">
      <a href="https://gitee.com/ajiemo">
      	<img src="https://portrait.gitee.com/uploads/avatars/user/3073/9219638_please-give-me-the-bear_1647783183.png!avatar100">  
      </a>
  </div>

### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)





