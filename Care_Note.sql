/*
MySQL Backup
Source Server Version: 8.0.24
Source Database: care_note
Date: 2022/8/15 16:34:39
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
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb3;

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
  PRIMARY KEY (`id`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;

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
  PRIMARY KEY (`id`,`name`)
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
--  Table structure for `setting`
-- ----------------------------
DROP TABLE IF EXISTS `setting`;
CREATE TABLE `setting` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `local` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(20) NOT NULL,
  `status` int NOT NULL DEFAULT '7',
  `sex` int NOT NULL DEFAULT '0',
  `law` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

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
INSERT INTO `log` VALUES ('3','1','1','2022-08-05 14:44:50','1','delete2022-08-05 14:44:48'), ('4','1','1','2022-08-05 14:42:22','1','delete2022-08-06 13:49:38'), ('6','2','3','2022-08-06 12:17:38','1','true'), ('7','1','2','2022-08-06 12:17:38','-1','true'), ('11','1','2','2022-08-08 16:39:49','-2','true'), ('12','4','1','2022-08-08 16:39:51','-1','true'), ('13','6','6','2022-08-08 16:39:52','-1','true'), ('14','4','1','2022-08-08 16:44:40','-1','true'), ('15','6','6','2022-08-08 16:44:42','-1','true'), ('16','1','2','2022-08-08 17:30:50','-2','true'), ('17','4','1','2022-08-08 17:30:52','-1','true'), ('18','6','6','2022-08-08 17:30:54','-1','true'), ('19','1','2','2022-08-08 21:00:02','-2','true'), ('20','4','1','2022-08-08 21:00:04','-1','true'), ('21','6','6','2022-08-08 21:00:06','-1','true'), ('22','1','2','2022-08-09 12:00:02','-2','true'), ('23','4','1','2022-08-09 12:00:04','-1','true'), ('24','6','6','2022-08-09 12:00:06','-1','true'), ('25','1','2','2022-08-09 21:00:01','-2','true'), ('26','4','1','2022-08-09 21:00:02','-1','true'), ('27','6','6','2022-08-09 21:00:04','-1','true'), ('28','4','1','2022-08-10 07:00:01','-1','true'), ('29','6','6','2022-08-10 07:00:03','-1','true'), ('30','1','2','2022-08-10 12:00:01','-2','true'), ('31','6','6','2022-08-10 12:00:03','-1','true'), ('32','1','2','2022-08-10 21:00:01','-2','true'), ('33','4','1','2022-08-10 21:00:03','-1','true'), ('34','6','6','2022-08-10 21:00:04','-1','true'), ('35','6','6','2022-08-11 07:00:01','-1','true'), ('36','6','6','2022-08-11 12:00:01','-1','true'), ('37','6','6','2022-08-11 12:00:01','-1','true'), ('38','4','1','2022-08-11 21:00:01','-1','true'), ('39','4','1','2022-08-11 21:00:01','-1','true'), ('40','6','6','2022-08-11 21:00:03','-1','true'), ('41','6','6','2022-08-11 21:00:08','-1','true'), ('42','4','1','2022-08-12 07:00:01','-1','true'), ('43','6','6','2022-08-12 07:00:03','-1','true'), ('44','6','6','2022-08-12 12:00:01','-1','true'), ('45','4','1','2022-08-12 21:00:01','-1','true'), ('46','6','6','2022-08-12 21:00:02','-1','true'), ('47','4','1','2022-08-13 07:00:01','-1','true'), ('48','6','6','2022-08-13 07:00:03','-1','true'), ('49','6','6','2022-08-13 12:00:01','-1','true'), ('50','4','1','2022-08-13 21:00:01','-1','true'), ('51','6','6','2022-08-13 21:00:03','-1','true'), ('52','4','1','2022-08-14 07:00:01','-1','true'), ('53','6','6','2022-08-14 07:00:02','-1','true'), ('54','6','6','2022-08-14 12:00:01','-1','true'), ('55','4','1','2022-08-14 21:00:01','-1','true'), ('56','6','6','2022-08-14 21:00:03','-1','true'), ('57','4','1','2022-08-15 07:00:01','-1','true'), ('58','6','6','2022-08-15 07:00:03','-1','true'), ('59','6','6','2022-08-15 12:00:01','-1','true');
INSERT INTO `module` VALUES ('-2','JIMO关怀（女）！','AUTO','AUTO','AUTO','亲干饭时间，不要忘了给自己加块肉奥！','AUTO','-2'), ('-1','JIMO关怀（男）！','AUTO','AUTO','AUTO','AUTO','AUTO','-1'), ('1','己墨的模板','AUTO','AUTO','AUTO','AUTO','AUTO','1'), ('11','JIMO关怀（中）！','AUTO','AUTO','早上好呀！我的小可爱，助你今天元气慢慢活力无线！','亲干饭时间，不要忘了给自己加块肉奥！','忙了一天了乖乖，该好好休息了，晚安玛卡巴卡！','0'), ('15','世杰','AUTO','AUTO','早呀','AUTO','AUTO','6'), ('16','小朋友','AUTO','AUTO','AUTO','AUTO','AUTO','4');
INSERT INTO `page` VALUES ('1','404','/404','1','404地址！URL坚决不可擅自修改!!!','10','error/404'), ('2','400','/400','1','400地址！！！','0','error/400'), ('3','500','/500','1','500地址！！！','10','error/500'), ('4','大数据页面','/','1','本站的大数据平台','252','index'), ('5','用户主页','/user','1','UserHome','4','user/home'), ('6','管理员主页','/admin','8','AdminHome','4','admin/home'), ('7','JIMO主页','/jimo.fun','1','JIMO友情连接','3','redirect:http://jimo.fun/'), ('8','用户登录','/login/user','1','用户登录','97','user/login'), ('9','管理员登录','/login/admin','1','管理员登录','88','admin/login'), ('10','用户首页','/user/console','1','用户首页','100','user/view/console'), ('11','个人中心','/user/my','1','用户的信息中心页面','71','user/view/user'), ('12','模板中心','/user/module','1','模板显示中心','90','user/view/module'), ('13','关怀中心','/user/setting','1','关怀对象的设置中心','102','user/view/setting'), ('14','用户修改','/user/update','1','用户修改自己信息','8','user/update/userUpdate'), ('15','修改模型','/user/moduleUpdate','1','用户修改模型','26','user/update/moduleUpdate'), ('16','用户修改对象','/user/settingUpdate','1','用户修改对象','30','user/update/settingUpdate'), ('17','充钱页面','/user/money','1','用户充钱页面','14','user/update/money'), ('18','用户查询','/admin/v/users','4','管理员用户查询','12','admin/view/users'), ('19','模板查询','/admin/v/modules','4','管理员模板查询','11','admin/view/modules'), ('20','对象查寻','/admin/v/settings','4','管理员对象查寻','7','admin/view/settings'), ('21','页面查询','/admin/v/pages','6','高级管理员页面查询','6','admin/view/pages'), ('22','日志查询','/admin/v/logs','4','管理员日志查询','6','admin/view/logs'), ('23','关系查询','/admin/v/relations','4','管理员关系查询','8','admin/view/relations'), ('24','管理员查询','/admin/v/admins','7','开发人员查询管理员','14','admin/view/a'), ('25','用户管理','/admin/u/user','5','管理用户','26','admin/update/user'), ('26','管理员管理','/admin/u/admin','7','开发人员管理员管理','8','admin/update/admin'), ('27','模板管理','/admin/u/module','6','高级管理员模板管理','9','admin/update/module'), ('28','页面管理','/admin/u/page','7','开发人员页面管理','5','admin/update/page'), ('29','充钱管理','/admin/u/money','5','管理员手动给用户充钱','13','admin/update/money');
INSERT INTO `relation` VALUES ('1','1','2','1'), ('3','4','1','-1'), ('4','6','6','-1'), ('5','1','7','1'), ('6','6','8','15');
INSERT INTO `setting` VALUES ('1','亲爱的JIMO','郑州市','17761612832','5','1',NULL), ('2','Q','浦东新区','18621127661','0','0','EVERY_DAY'), ('6','世杰呀','淅川县','18595490267','7','1',NULL), ('7','老朋友','杭州','18621127661','-1','0','EVERY_DAY'), ('8','小可爱','武汉','18595490267','0','0','EVERY_DAY');
INSERT INTO `user` VALUES ('1','陈己墨','root','1517962688@qq.com','7','17761612832','开发者JIMO','213','2022-08-04 09:14:20','2022-08-15 16:33:44','1'), ('4','不一样的烟花','root','745076951@qq.com','2','18621127661','JIMO小弟','11','2022-08-06 16:26:10','2022-08-15 16:33:52','0'), ('6','世杰','root','3342885064@qq.com','6','18595490267','开发者世杰','177','2022-08-07 22:01:52','2022-08-15 12:00:01','1');
