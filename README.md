# Care_Note

#### 介绍
这是一个我自己突发奇想的自动化天气关怀短信小程序，整合mybatis plus、spring boot等框架实现可控的短信服务，有C、B两端的自动化菜单权限，及定时提醒和集成手机Email钉钉的自动化提醒为运维提供方便！最后由我和我的小伙伴杰一起完成全部开发，功能会一步一步慢慢迭代更新的。可以用来服务自己的表白对象或亲人以及出售自己购买的云服务短息等，更多详情见说明文档！

#### 软件架构
软件架构说明：废话不多说我直接把的设计文档献给大家，搞开发玩的可以详细阅读，还有很多不足欢迎与我讨论，
联系我的渠道有很多的： **[https://www.yuque.com/jimoworld/javabj/care_note/](https://www.yuque.com/jimoworld/javabj/care_note/)** 
项目链接：
Gitee: **[https://gitee.com/chenjimo/Care_Note](https://gitee.com/chenjimo/Care_Note)** 
平台首页！ **[http://care.jimo.fun/](http://care.jimo.fun/)** 
![输入图片说明](https://foruda.gitee.com/images/1660478966724759070/屏幕截图.png "屏幕截图.png")
用户页
用户登录： **[http://care.jimo.fun/login/user](http://care.jimo.fun/login/user)** 
![输入图片说明](https://foruda.gitee.com/images/1660478899714027835/屏幕截图.png "屏幕截图.png")
用户首页： **[http://care.jimo.fun/user](http://care.jimo.fun/user)** 
![输入图片说明](https://foruda.gitee.com/images/1660478918248069693/屏幕截图.png "屏幕截图.png")
![输入图片说明](https://foruda.gitee.com/images/1660478930397560592/屏幕截图.png "屏幕截图.png")
管理员页面：
管理员登录： **[http://care.jimo.fun/login/admin](http://care.jimo.fun/login/admin)** 
![输入图片说明](https://foruda.gitee.com/images/1660478940979033020/屏幕截图.png "屏幕截图.png")
管理员首页： **[http://care.jimo.fun/admin](http://care.jimo.fun/admin)** 
![输入图片说明](https://foruda.gitee.com/images/1660479009851784570/屏幕截图.png "屏幕截图.png")
##一、功能- 更多见上边开发文档
###1.面向C端
####（1）核心功能：
1.针对关怀的目标对象所在地查询一周内的天气情况！
2.根据所在地的天气情况，程序定义关怀语句！
3.添加自己的话语！
4.发送短息消息！
####（2）扩展功能：
1.海王群发关怀消息！
2.设定每天提示上限！
3.自动获取IP或手机号判断地址！
4.添加每日一句（不重复）！
5.关怀记录可视化数据展示！
6.每次成功和错误邮箱提示！
7.余额提示
8.时间完全自定义（特殊时间倒计时提醒）
9.女神榜、男神榜、情话榜
##2.面向B端
1.可控的服务者CRUD。
2.收银按量服务（包月或买数量）。
3.套餐选择。
4.数据可视化管控台。
5.订单提示！（集成钉钉机器人提示维运团队）
6.异常错误QQ提示管理员！
7.日志记录
8.权限管理
##二、需求与设计架构
用户管理、消息管理、消息模板、订单管理、
新加入用户默认消息模板，添加用户判断键，未付款的用户只能享受一次发消息权力。
订单管理简化程序的复杂度，就人工控制吧（后续有精力在完善）！
当有人付款后给我的邮箱发送消息提示修改用户状态值即可。
1.后台管控略图
![输入图片说明](https://foruda.gitee.com/images/1660479313189368004/屏幕截图.png "屏幕截图.png")
2.消费者页面
![输入图片说明](https://foruda.gitee.com/images/1660479303589526154/屏幕截图.png "屏幕截图.png")

#### 安装教程其实很简单的！！！

1.  先下载代码最新版！
2.  然后修改配置文件(就下面这个图中的文件，路径都在图里！哪里需要修改文件中都有讲！！！)
![输入图片说明](https://foruda.gitee.com/images/1660479175737530083/屏幕截图.png "屏幕截图.png")
3.  然后初始化自己的数据库文件，见文档或下面脚本！！！

```
/*
MySQL Backup
Source Server Version: 8.0.24
Source Database: care_note
Date: 2022/8/14 19:17:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `log`
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `u_id` int NOT NULL,
  `s_id` int NOT NULL,
  `date` timestamp NOT NULL,
  `m_id` int NOT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
--  Table structure for `module`
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(15) NOT NULL,
  `status` varchar(34) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'AUTO',
  `temp` varchar(34) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'AUTO',
  `morning` varchar(34) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'AUTO',
  `noon` varchar(34) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'AUTO',
  `evening` varchar(34) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'AUTO',
  `u_id` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
--  Table structure for `page`
-- ----------------------------
DROP TABLE IF EXISTS `page`;
CREATE TABLE `page` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(15) NOT NULL,
  `url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'http://jimo.fun/404.html',
  `power` int NOT NULL DEFAULT '7',
  `bz` varchar(255) DEFAULT NULL,
  `visit` int DEFAULT '0',
  `page_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '/404',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
--  Table structure for `relation`
-- ----------------------------
DROP TABLE IF EXISTS `relation`;
CREATE TABLE `relation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `u_id` int NOT NULL,
  `s_id` int NOT NULL,
  `m_id` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
--  Table structure for `setting`
-- ----------------------------
DROP TABLE IF EXISTS `setting`;
CREATE TABLE `setting` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `local` varchar(15) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `status` int NOT NULL DEFAULT '7',
  `sex` int NOT NULL DEFAULT '0',
  `law` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `pwd` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `power` int DEFAULT '1',
  `phone` varchar(20) DEFAULT NULL,
  `bz` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `money` int DEFAULT '3',
  `create_time` timestamp NOT NULL,
  `login_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `sex` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
--  Records 
-- ----------------------------
INSERT INTO `log` VALUES ('3','1','1','2022-08-05 14:44:50','1','delete2022-08-05 14:44:48'), ('4','1','1','2022-08-05 14:42:22','1','delete2022-08-06 13:49:38'), ('6','2','3','2022-08-06 12:17:38','1','true'), ('7','1','2','2022-08-06 12:17:38','-1','true'), ('11','1','2','2022-08-08 16:39:49','-2','true'), ('12','4','1','2022-08-08 16:39:51','-1','true'), ('13','6','6','2022-08-08 16:39:52','-1','true'), ('14','4','1','2022-08-08 16:44:40','-1','true'), ('15','6','6','2022-08-08 16:44:42','-1','true'), ('16','1','2','2022-08-08 17:30:50','-2','true'), ('17','4','1','2022-08-08 17:30:52','-1','true'), ('18','6','6','2022-08-08 17:30:54','-1','true'), ('19','1','2','2022-08-08 21:00:02','-2','true'), ('20','4','1','2022-08-08 21:00:04','-1','true'), ('21','6','6','2022-08-08 21:00:06','-1','true'), ('22','1','2','2022-08-09 12:00:02','-2','true'), ('23','4','1','2022-08-09 12:00:04','-1','true'), ('24','6','6','2022-08-09 12:00:06','-1','true'), ('25','1','2','2022-08-09 21:00:01','-2','true'), ('26','4','1','2022-08-09 21:00:02','-1','true'), ('27','6','6','2022-08-09 21:00:04','-1','true'), ('28','4','1','2022-08-10 07:00:01','-1','true'), ('29','6','6','2022-08-10 07:00:03','-1','true'), ('30','1','2','2022-08-10 12:00:01','-2','true'), ('31','6','6','2022-08-10 12:00:03','-1','true'), ('32','1','2','2022-08-10 21:00:01','-2','true'), ('33','4','1','2022-08-10 21:00:03','-1','true'), ('34','6','6','2022-08-10 21:00:04','-1','true'), ('35','6','6','2022-08-11 07:00:01','-1','true'), ('36','6','6','2022-08-11 12:00:01','-1','true'), ('37','6','6','2022-08-11 12:00:01','-1','true'), ('38','4','1','2022-08-11 21:00:01','-1','true'), ('39','4','1','2022-08-11 21:00:01','-1','true'), ('40','6','6','2022-08-11 21:00:03','-1','true'), ('41','6','6','2022-08-11 21:00:08','-1','true'), ('42','4','1','2022-08-12 07:00:01','-1','true'), ('43','6','6','2022-08-12 07:00:03','-1','true'), ('44','6','6','2022-08-12 12:00:01','-1','true'), ('45','4','1','2022-08-12 21:00:01','-1','true'), ('46','6','6','2022-08-12 21:00:02','-1','true'), ('47','4','1','2022-08-13 07:00:01','-1','true'), ('48','6','6','2022-08-13 07:00:03','-1','true'), ('49','6','6','2022-08-13 12:00:01','-1','true'), ('50','4','1','2022-08-13 21:00:01','-1','true'), ('51','6','6','2022-08-13 21:00:03','-1','true'), ('52','4','1','2022-08-14 07:00:01','-1','true'), ('53','6','6','2022-08-14 07:00:02','-1','true'), ('54','6','6','2022-08-14 12:00:01','-1','true');
INSERT INTO `module` VALUES ('-2','JIMO关怀（女）！','AUTO','AUTO','AUTO','亲干饭时间，不要忘了给自己加块肉奥！','AUTO','-2'), ('-1','JIMO关怀（男）！','AUTO','AUTO','AUTO','AUTO','AUTO','-1'), ('0','JIMO关怀（中）！','AUTO','AUTO','早上好呀！我的小可爱，助你今天元气慢慢活力无线！','亲干饭时间，不要忘了给自己加块肉奥！','忙了一天了乖乖，该好好休息了，晚安玛卡巴卡！','0'), ('1','己墨的模板','AUTO','AUTO','AUTO','AUTO','AUTO','1');
INSERT INTO `page` VALUES ('1','404','/404','1','404地址！URL坚决不可擅自修改','10','error/404'), ('2','400','/400','1','400地址！！！','0','error/400'), ('3','500','/500','1','500地址！！！','10','error/500'), ('4','大数据页面','/','1','本站的大数据平台','172','index'), ('5','用户主页','/user','1','UserHome','4','user/home'), ('6','管理员主页','/admin','8','AdminHome','4','admin/home'), ('7','JIMO主页','/jimo.fun','1','JIMO友情连接','3','redirect:http://jimo.fun/'), ('8','用户登录','/login/user','1','用户登录','68','user/login'), ('9','管理员登录','/login/admin','1','管理员登录','64','admin/login'), ('10','用户首页','/user/console','1','用户首页','49','user/view/console'), ('11','个人中心','/user/my','1','用户的信息中心页面','40','user/view/user'), ('12','模板中心','/user/module','1','模板显示中心','47','user/view/module'), ('13','关怀中心','/user/setting','1','关怀对象的设置中心','49','user/view/setting'), ('14','用户修改','/user/update','1','用户修改自己信息','4','user/update/userUpdate'), ('15','修改模型','/user/moduleUpdate','1','用户修改模型','19','user/update/moduleUpdate'), ('16','用户修改对象','/user/settingUpdate','1','用户修改对象','18','user/update/settingUpdate'), ('17','充钱页面','/user/money','1','用户充钱页面','10','user/update/money'), ('18','用户查询','/admin/v/users','4','管理员用户查询','7','admin/view/users'), ('19','模板查询','/admin/v/modules','4','管理员模板查询','7','admin/view/modules'), ('20','对象查寻','/admin/v/settings','4','管理员对象查寻','5','admin/view/settings'), ('21','页面查询','/admin/v/pages','6','高级管理员页面查询','4','admin/view/pages'), ('22','日志查询','/admin/v/logs','4','管理员日志查询','3','admin/view/logs'), ('23','关系查询','/admin/v/relations','4','管理员关系查询','5','admin/view/relations'), ('24','管理员查询','/admin/v/admins','7','开发人员查询管理员','12','admin/view/a'), ('25','用户管理','/admin/u/user','5','管理用户','10','admin/update/user'), ('26','管理员管理','/admin/u/admin','7','开发人员管理员管理','1','admin/update/admin'), ('27','模板管理','/admin/u/module','6','高级管理员模板管理','1','admin/update/moduleUpdate'), ('28','页面管理','/admin/u/page','7','开发人员页面管理','0','admin/update/pageUpdate'), ('29','充钱管理','/admin/u/money','5','管理员手动给用户充钱','8','admin/update/money');
INSERT INTO `relation` VALUES ('1','1','2','-2'), ('3','4','1','-1'), ('4','6','6','-1'), ('5','1','7','1');
INSERT INTO `setting` VALUES ('1','亲爱的JIMO','新郑市','17761612832','5','1',NULL), ('2','Q','浦东新区','18621127661','0','0','EVERY_DAY'), ('6','世杰呀','淅川县','18595490267','7','1',NULL), ('7','老朋友','杭州','18621127661','-1','0','EVERY_DAY');
INSERT INTO `user` VALUES ('1','陈己墨','root','1517962688@qq.com','7','17761612832','开发者JIMO','113','2022-08-04 09:14:20','2022-08-14 19:16:36','1'), ('4','不一样的烟花','root','745076951@qq.com','1','18621127661','','13','2022-08-06 16:26:10','2022-08-14 19:16:40','0'), ('6','世杰','root','3342885064@qq.com','6','18595490267','开发者世杰','80','2022-08-07 22:01:52','2022-08-14 19:16:52','1');

```


#### 使用说明

然后就直接启动项目即可（详细上面文档都有讲）
项目端口号为：9277（默认）

#### 参与贡献
JIMO、Fujie

#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
