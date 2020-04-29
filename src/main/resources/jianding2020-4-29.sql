/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.28 : Database - appraisal
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`appraisal` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

/*Table structure for table `ap_accept_log` */

DROP TABLE IF EXISTS `ap_accept_log`;

CREATE TABLE `ap_accept_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `admin_id` int(11) DEFAULT NULL COMMENT '操作人id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `type` varchar(255) DEFAULT NULL COMMENT '类型/纠错/采纳/取消采纳/删除  /error/accept /cancel/del',
  `content` varchar(255) DEFAULT NULL COMMENT '用户评论内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4;

/*Data for the table `ap_accept_log` */

insert  into `ap_accept_log`(`id`,`create_time`,`admin_id`,`user_id`,`type`,`content`) values (1,'2019-10-22 11:10:52',1,2,'accept','测试'),(2,'2019-10-22 12:07:26',1,NULL,'cancel',NULL),(3,'2019-10-22 12:07:29',1,NULL,'accept',NULL),(4,'2019-10-22 12:07:32',1,NULL,'cancel',NULL),(5,'2019-10-22 12:07:34',1,NULL,'accept',NULL),(6,'2019-10-22 12:07:37',1,NULL,'cancel',NULL),(7,'2019-10-22 12:08:36',1,NULL,'accept',NULL),(8,'2019-10-22 12:09:55',1,3,'del','hooks'),(9,'2019-10-22 12:10:05',1,3,'cancel','铺u收拾退模糊'),(10,'2019-10-22 12:10:08',1,3,'accept','铺u收拾退模糊'),(11,'2019-10-22 12:10:15',1,3,'cancel','铺u收拾退模糊'),(12,'2019-10-22 15:39:50',1,5,'error','沟通'),(13,'2019-10-22 15:43:40',1,5,'cancel','沟通'),(14,'2019-10-22 15:43:43',1,2,'accept','测试后台发布评论'),(15,'2019-10-27 16:52:12',1,3,'cancel','计算机（computer）俗称电脑，是现代一种用于高速计算的电子计算机器，可以进行数值计算，又可以进行逻辑计算，还具有存储记忆功能。'),(16,'2019-10-27 16:52:16',1,2,'accept','计算机（computer）俗称电脑，是现代一种用于高速计算的电子计算机器，可以进行数值计算，又可以进行逻辑计算，还具有存储记忆功能。'),(17,'2019-10-28 13:37:11',1,3,'accept','富通九州'),(18,'2019-10-28 13:41:05',1,3,'error','测试纠错'),(19,'2019-10-30 12:16:52',1,1,'accept','小米logo都没有，建议扔了'),(20,'2019-11-04 10:49:13',1,2,'del','123456789'),(21,'2019-11-04 14:46:39',1,3,'cancel','测试纠错'),(22,'2019-11-07 13:55:35',1,5,'cancel','垃圾'),(23,'2019-11-07 13:55:37',1,5,'accept','假的'),(24,'2019-11-07 13:56:05',1,5,'cancel','假的'),(25,'2019-11-07 13:56:11',1,5,'accept','垃圾'),(26,'2019-11-07 14:25:24',1,3,'cancel','u哦辛苦啦'),(27,'2019-11-07 15:59:09',1,5,'error','假的'),(28,'2019-11-07 16:37:09',1,3,'del','富通九州'),(29,'2019-11-07 16:37:12',1,3,'del','测试纠错'),(30,'2019-11-14 14:07:39',1,2,'del','看着像真的'),(31,'2019-11-14 14:07:42',1,2,'del','阿里巴巴');

/*Table structure for table `ap_appraisal` */

DROP TABLE IF EXISTS `ap_appraisal`;

CREATE TABLE `ap_appraisal` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '鉴定物品表',
  `user_id` int(11) DEFAULT NULL COMMENT '发布人id',
  `appraisal_type_id` int(11) DEFAULT NULL COMMENT '商品类型id',
  `title` varchar(255) DEFAULT NULL,
  `describc` text,
  `appraisal_state` varchar(255) CHARACTER SET utf8 DEFAULT 'undetermined' COMMENT '商品状态 待鉴定undetermined 真adopt 假failedpass',
  `browse` int(11) DEFAULT '0' COMMENT '浏览量',
  `ap_case` varchar(25) CHARACTER SET utf8 DEFAULT 'no' COMMENT '是否可以成为案例 yes|no',
  `status` varchar(255) CHARACTER SET utf8 DEFAULT 'yes' COMMENT '状态(yes 上架，no下架)',
  `ap_images` text CHARACTER SET utf8 COMMENT '存放图片url,',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `reised_state` varchar(25) DEFAULT '0' COMMENT '无纠错内容0  有纠错内容10 处理中20   已处理30',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8mb4;

/*Data for the table `ap_appraisal` */

insert  into `ap_appraisal`(`id`,`user_id`,`appraisal_type_id`,`title`,`describc`,`appraisal_state`,`browse`,`ap_case`,`status`,`ap_images`,`create_time`,`update_time`,`reised_state`) values (1,1,14,'Nike回到未来','拼多多拼的帮我鉴定一下，采纳必有重谢','undetermined',0,'yes','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1571300947295.jpg,https://appraisal.oss-cn-beijing.aliyuncs.com/1571301073849.jpg,https://appraisal.oss-cn-beijing.aliyuncs.com/1571301214422.jpg,','2019-10-17 16:28:59','2019-10-17 16:28:59','0'),(2,1,14,'老爹鞋椰子500','抽奖抽到的，快来帮我鉴定一下','undetermined',0,'yes','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1571301073849.jpg,','2019-10-17 16:31:10','2019-10-17 16:31:10','0'),(3,1,11,'满天星椰子','穿上不舒服，是不是假的','undetermined',0,'yes','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1571301214422.jpg,','2019-10-17 16:33:26','2019-10-17 16:33:26','0'),(4,1,12,'理查德腕表','不知道真假，抽个鉴定者送了','undetermined',0,'yes','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1571302474248.jpg,https://appraisal.oss-cn-beijing.aliyuncs.com/1571302603616.jpg,https://appraisal.oss-cn-beijing.aliyuncs.com/1571303178298.jpg,','2019-10-17 16:54:32','2019-10-17 16:54:32','0'),(5,1,13,'cucci手包','老公送我个cucci的手包，不知道哪来的钱，帮我看看真的假的','undetermined',0,'yes','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1571302603616.jpg,','2019-10-17 16:56:37','2019-10-17 16:56:37','0'),(6,1,11,'180克拉的戒指','戒指','undetermined',0,'yes','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1571302804318.jpg,','2019-10-17 16:59:57','2019-10-17 16:59:57','0'),(7,2,12,'小米顶配','刚入手的笔记本，大佬帮我看下，','failedpass',0,'yes','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1571302944754.png,https://appraisal.oss-cn-beijing.aliyuncs.com/1571302938404.png,https://appraisal.oss-cn-beijing.aliyuncs.com/1571302947283.png','2019-10-17 17:02:20','2019-11-06 14:57:49','10'),(8,1,14,'Nike','Nike','undetermined',0,'yes','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1571303004916.jpg,','2019-10-17 17:03:20','2019-10-17 17:03:20','0'),(9,1,11,'野驴包包','老公说是野驴牌子的包包，好嘛','undetermined',0,'yes','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1571303178298.jpg,','2019-10-17 17:06:10','2019-10-22 12:10:15','0'),(10,1,11,'不知名香水','什么牌子的啊','undetermined',0,'yes','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1571303249704.jpg,','2019-10-17 17:07:21','2019-11-04 14:46:39','10'),(11,3,14,'鳄鱼皮鞋真皮(ʘ̆ωʘ̥̆‖)՞','鳄鱼皮鞋真皮?٩(๑`^´๑)۶我不管','adopt',0,'yes','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1571367015664.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1571367021334.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1571367022906.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1571367022167.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1571367016692.jpg,https://appraisal.oss-cn-beijing.aliyuncs.com/1571367019730.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1571367019615.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1571367014406.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1571367019189.jpeg','2019-10-18 10:50:14','2019-11-06 14:57:42','0'),(64,3,14,'鳄鱼皮男鞋','康庄大道 秋季新款男士皮鞋男真皮商务正装皮鞋鳄鱼纹奢侈品男鞋','undetermined',0,'yes','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1572165580902.jpg,https://appraisal.oss-cn-beijing.aliyuncs.com/1572165581066.jpeg','2019-10-27 16:39:34','2019-10-27 16:44:30','0'),(65,3,11,'腕表','J12腕表,以极黑与纯白两种色泽的精密陶瓷打造,魅力隽永,永恒之选.','undetermined',0,'yes','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1572165854984.png,https://appraisal.oss-cn-beijing.aliyuncs.com/1572165854215.png','2019-10-27 16:44:10','2019-10-27 16:44:24','0'),(66,3,12,'古董电脑','计算机（computer）俗称电脑，是现代一种用于高速计算的电子计算机器，可以进行数值计算，又可以进行逻辑计算，还具有存储记忆功能。','undetermined',0,'yes','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1572166142924.png,https://appraisal.oss-cn-beijing.aliyuncs.com/1572166144436.png,https://appraisal.oss-cn-beijing.aliyuncs.com/1572166137106.png','2019-10-27 16:48:54','2019-10-27 16:49:37','0'),(67,3,11,'相机','计算机（computer）俗称电脑，是现代一种用于高速计算的电子计算机器，可以进行数值计算，又可以进行逻辑计算，还具有存储记忆功能。','adopt',0,'yes','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1572166206881.png,https://appraisal.oss-cn-beijing.aliyuncs.com/1572166206910.png,https://appraisal.oss-cn-beijing.aliyuncs.com/1572166202983.png,https://appraisal.oss-cn-beijing.aliyuncs.com/1572166202842.png','2019-10-27 16:50:02','2019-10-27 16:52:16','10'),(68,3,13,'测试','测试','adopt',0,'yes','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1572240078025.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1572240069927.jpg,https://appraisal.oss-cn-beijing.aliyuncs.com/1572240076727.jpg,https://appraisal.oss-cn-beijing.aliyuncs.com/1572240077666.jpg,https://appraisal.oss-cn-beijing.aliyuncs.com/1572240069014.jpg,https://appraisal.oss-cn-beijing.aliyuncs.com/1572240074159.jpg,https://appraisal.oss-cn-beijing.aliyuncs.com/1572240079470.jpg,https://appraisal.oss-cn-beijing.aliyuncs.com/1572240075325.jpg,https://appraisal.oss-cn-beijing.aliyuncs.com/1572240072249.jpeg','2019-10-28 13:21:09','2019-11-08 10:21:17','0'),(69,6,11,'不错','脱衣舞提醒我一下无图突突突一起','undetermined',0,'yes','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1572506013548.jpg,https://appraisal.oss-cn-beijing.aliyuncs.com/1572506006875.jpg','2019-10-31 15:13:24','2019-11-06 14:57:28','0'),(86,6,2,'www秃头土司','吐了露臀酷兔兔涂抹的阿里户户通极速图太空旅客考虑考虑路凸透镜咯莫','undetermined',0,'yes','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1572836370212.jpg,https://appraisal.oss-cn-beijing.aliyuncs.com/1572836370311.jpg','2019-11-04 10:59:22','2019-11-06 14:57:04','0'),(87,6,2,'三个女人','中央音乐学院','undetermined',0,'yes','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1572836592612.jpg,https://appraisal.oss-cn-beijing.aliyuncs.com/1572836592441.jpg,https://appraisal.oss-cn-beijing.aliyuncs.com/1572836595325.jpg','2019-11-04 11:03:10','2019-11-06 14:56:58','0'),(88,2,15,'腾讯游戏用钱创造快乐','冲8万你能这样？','undetermined',0,'yes','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1572923445213.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1572923441924.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1572923448049.jpeg','2019-11-05 11:10:42','2019-11-06 14:56:44','0'),(90,3,45,'阿里巴巴买的Lv','阿里巴巴买的Lv','failedpass',0,'yes','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1573019669040.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1573019674182.jpeg','2019-11-06 13:54:26','2019-11-06 14:56:31','0'),(91,2,26,'鳄鱼皮皮鞋','鳄鱼皮皮鞋','undetermined',0,'yes','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1573021190909.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1573021188494.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1573021184725.jpeg','2019-11-06 14:19:43','2019-11-06 14:56:24','0'),(92,3,57,'手表','手表','undetermined',0,'yes','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1573021819641.jpeg','2019-11-06 14:30:14','2019-11-06 14:56:18','0'),(93,3,62,'商务机','商务机','undetermined',0,'yes','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1573021909969.jpeg','2019-11-06 14:31:40','2019-11-06 14:56:10','0'),(94,3,45,'手提包','手提包','adopt',0,'no','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1573034664695.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1573034660214.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1573034662436.jpeg,','2019-11-06 18:04:17','2019-11-06 18:05:03','10'),(95,5,51,'啦啦啦','啦啦啦','undetermined',0,'no','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1573035228560.gif,','2019-11-06 18:13:43','2019-11-06 18:13:43','0'),(96,5,45,'*东买的?','*东买的????','undetermined',0,'no','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1573037403260.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1573037399636.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1573037402980.jpeg,','2019-11-06 18:49:55','2019-11-06 18:49:55','0'),(97,3,26,'这是一条标题这是一条标题','这是一条描述这是一条描述','failedpass',0,'no','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1573037841419.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1573037844030.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1573037844463.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1573037836705.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1573037839133.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1573037845439.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1573037838545.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1573037848113.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1573037842675.jpeg,','2019-11-06 18:57:20','2019-11-07 15:59:09','30'),(98,9,59,'一','打开','adopt',0,'no','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1573052643932.jpeg,','2019-11-06 23:03:58','2019-11-07 21:21:17','0'),(99,9,59,'一','打开','adopt',0,'no','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1573052638233.jpeg,','2019-11-06 23:03:58','2019-11-07 21:27:04','0'),(100,3,59,'咯好了','斯里兰卡健健康康','undetermined',0,'no','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1573107055253.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1573107052662.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1573107054173.jpeg,','2019-11-07 14:10:53','2019-11-07 14:10:53','0'),(101,5,50,'咖喱鸡块加','V5咯哦哦','failedpass',0,'no','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1573107068239.png,https://appraisal.oss-cn-beijing.aliyuncs.com/1573107067696.png,','2019-11-07 14:10:59','2019-11-07 15:16:17','0'),(102,3,49,'里面图啦啦啦啦啦咯哦统一战线咯哦OK同居ton ','阿里健康阿里健康阿里健康快快乐乐咯啦啦啦咯哦咯哦哦哦哦哦哦哦哦下雨天口蘑婆婆诺托top就怕查就是骷髅头某剧套路就是肌肉哭了录取率就开始噼里啪啦噼里啪啦噼里啪啦数据拒绝夫妇剧套路理论土木楼剧目图库目录结局太酷了特铁路局据统计局目录天摩托母女剧套路了噼里啪啦噼里啪啦视频撸啊撸剧套路了塔塔KTV录了噼里啪啦噼里啪啦噼里啪啦噼里啪啦开始噼里啪啦说了录取率看看去咯图呢偷摸就是怕逃脱欧诺托夫头目摩托木头娜讨论木头娜娜脱欧诺偷偷某BS多土摩托头目哭哭哭咯偷偷某头哦头娜头目哭哭哭目录娜罗斯科普夫某夫妇墓啊婆婆噼里啪啦开始老老实实咯婆婆哦婆婆哦婆婆婆婆女top末日婆婆千岁千岁千千岁生日阿里健康阿里健康阿里健康快快乐乐咯啦啦啦咯哦咯哦哦哦哦哦哦哦哦下雨天口蘑婆婆诺托top就怕查就是骷髅头某剧套路就是肌肉哭了录取率就开始噼里啪啦噼里啪啦噼里啪啦数据拒绝夫妇剧套路理论土木楼剧目图库目录结局太酷了特铁路局据统计局目录天摩托母女剧套路了噼里啪啦噼里啪啦视频撸啊撸剧套路了塔塔KTV录了噼里啪啦噼里啪啦噼里啪啦噼里啪啦开始噼里啪啦说了录取率看看去咯图呢偷摸就是怕逃脱欧诺托夫头目摩托木头娜讨论木头娜娜脱欧诺偷偷阿里健康阿里健康阿里健康快快乐乐','adopt',0,'no','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1573108436682.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1573108436039.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1573108437961.jpeg,','2019-11-07 14:33:52','2019-11-07 15:05:54','0'),(103,5,53,'如图吉里吉里估计快了*东','擦擦擦擦擦擦→_→啊啊啊擦擦擦+156499????????擦擦擦擦擦擦→_→啊啊啊擦擦擦+156499????????擦擦擦擦擦擦→_→啊啊啊擦擦擦+156499????????擦擦擦擦擦擦→_→啊啊啊擦擦擦+156499????????擦擦擦擦擦擦→_→啊啊啊擦擦擦+156499????????擦擦擦擦擦擦→_→啊啊啊擦擦擦+156499????????擦擦擦擦擦擦→_→啊啊啊擦擦擦+156499????????擦擦擦擦擦擦→_→啊啊啊擦擦擦+156499????????','adopt',0,'no','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1573108710835.jpg,https://appraisal.oss-cn-beijing.aliyuncs.com/1573108707529.jpg,https://appraisal.oss-cn-beijing.aliyuncs.com/1573108710630.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1573108711916.jpg,','2019-11-07 14:38:24','2019-11-08 10:36:34','0'),(104,5,59,'dhgf','cggf','undetermined',0,'no','no','https://appraisal.oss-cn-beijing.aliyuncs.com/1573110813831.png,https://appraisal.oss-cn-beijing.aliyuncs.com/1573110815707.png,https://appraisal.oss-cn-beijing.aliyuncs.com/1573110814291.png,','2019-11-07 15:13:28','2019-11-07 16:16:14','0'),(105,5,35,'zfg','fghfw','undetermined',0,'no','no','https://appraisal.oss-cn-beijing.aliyuncs.com/1573110833409.png,https://appraisal.oss-cn-beijing.aliyuncs.com/1573110828100.png,https://appraisal.oss-cn-beijing.aliyuncs.com/1573110830406.png,','2019-11-07 15:13:45','2019-11-07 16:16:12','0'),(106,5,59,'rgg','cbbg','undetermined',0,'no','no','https://appraisal.oss-cn-beijing.aliyuncs.com/1573110874270.png,https://appraisal.oss-cn-beijing.aliyuncs.com/1573110873077.png,https://appraisal.oss-cn-beijing.aliyuncs.com/1573110875140.png,','2019-11-07 15:14:31','2019-11-07 16:16:10','0'),(107,5,60,'etgf','dhgf','undetermined',0,'no','no','https://appraisal.oss-cn-beijing.aliyuncs.com/1573110904135.png,https://appraisal.oss-cn-beijing.aliyuncs.com/1573110900233.png,https://appraisal.oss-cn-beijing.aliyuncs.com/1573110897750.png,','2019-11-07 15:14:57','2019-11-07 16:16:08','0'),(108,5,41,'fhh','fhc','undetermined',0,'no','no','https://appraisal.oss-cn-beijing.aliyuncs.com/1573110914152.png,https://appraisal.oss-cn-beijing.aliyuncs.com/1573110918589.png,https://appraisal.oss-cn-beijing.aliyuncs.com/1573110915213.png,','2019-11-07 15:15:14','2019-11-07 16:16:06','0'),(109,9,63,'一','奔','adopt',0,'no','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1573111111398.jpeg,','2019-11-07 15:18:29','2019-11-07 15:19:00','0'),(110,5,31,'手机将军令','啦啦啦啦啦','undetermined',0,'no','no','https://appraisal.oss-cn-beijing.aliyuncs.com/1573111325334.png,https://appraisal.oss-cn-beijing.aliyuncs.com/1573111324607.png,https://appraisal.oss-cn-beijing.aliyuncs.com/1573111323743.png,','2019-11-07 15:21:57','2019-11-07 16:16:03','0'),(111,5,59,'来了来了','涂涂乐','undetermined',0,'no','no','https://appraisal.oss-cn-beijing.aliyuncs.com/1573111342866.png,https://appraisal.oss-cn-beijing.aliyuncs.com/1573111340191.png,https://appraisal.oss-cn-beijing.aliyuncs.com/1573111340529.png,','2019-11-07 15:22:14','2019-11-07 16:16:01','0'),(112,9,60,'手机','拍照手机','undetermined',0,'no','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1573131342647.jpeg,','2019-11-07 20:55:41','2019-11-07 20:55:41','0'),(113,4,21,'好不好','测试一下评论吧','adopt',0,'no','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1573176597311.jpeg,','2019-11-08 09:29:50','2019-11-08 09:34:42','0'),(130,9,54,'sdf','Asdf','undetermined',0,'no','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1573628825869.jpeg,','2019-11-13 15:07:00','2019-11-13 15:07:00','0'),(131,9,63,'对讲机','满分对讲机','undetermined',0,'no','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1573631114277.jpeg,','2019-11-13 15:45:08','2019-11-13 15:45:08','0'),(132,3,35,'dat hgffe','Rjhhgfddr ','undetermined',0,'no','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1573632823445.jpeg,','2019-11-13 16:13:37','2019-11-13 16:13:37','0'),(133,13,64,'新采购块硬盘 请大神看一下','看看是不是原厂的','failedpass',0,'no','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1573704454668.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1573704462168.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1573704457356.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1573704459203.jpeg,https://appraisal.oss-cn-beijing.aliyuncs.com/1573704458290.jpeg,','2019-11-14 12:07:36','2019-11-14 15:59:34','0'),(134,3,29,'联想','屏幕有坏点','undetermined',0,'no','yes','https://appraisal.oss-cn-beijing.aliyuncs.com/1583401872140.jpeg,','2020-03-05 17:51:12','2020-03-05 17:51:12','0');

/*Table structure for table `ap_appraisal_images` */

DROP TABLE IF EXISTS `ap_appraisal_images`;

CREATE TABLE `ap_appraisal_images` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品图片信息表',
  `appraisal_id` int(11) NOT NULL COMMENT '商品id',
  `image_url` varchar(255) NOT NULL COMMENT '图片路径',
  `sort` int(11) NOT NULL COMMENT '排序',
  `status` varchar(255) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ap_appraisal_images` */

/*Table structure for table `ap_appraisal_type` */

DROP TABLE IF EXISTS `ap_appraisal_type`;

CREATE TABLE `ap_appraisal_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '鉴定类型表',
  `type_name` varchar(55) NOT NULL COMMENT '类型名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `ap_show` varchar(5) NOT NULL DEFAULT 'yes' COMMENT '状态 是否显示 yes|no',
  `sort` int(255) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

/*Data for the table `ap_appraisal_type` */

insert  into `ap_appraisal_type`(`id`,`type_name`,`create_time`,`update_time`,`ap_show`,`sort`) values (11,'箱包','2019-10-16 17:13:00','2019-11-06 12:45:07','yes',6),(12,'黄金','2019-10-16 17:13:12','2019-11-06 12:51:48','yes',5),(13,'鞋靴配件','2019-10-16 17:13:22','2019-11-06 12:45:26','yes',4),(14,'饰品','2019-10-16 17:13:32','2019-11-06 12:45:37','yes',3),(15,'衣服',NULL,'2019-11-04 14:06:56','no',2),(16,'手表','2019-10-18 17:09:00','2019-11-06 12:46:38','no',1),(17,'test','2019-10-21 15:09:02','2019-11-04 14:07:03','no',0),(18,'test','2019-10-21 15:09:45','2019-11-04 14:07:04','no',100),(19,'服饰','2019-11-04 14:07:14','2019-11-06 12:45:00','yes',17),(20,'腕表','2019-11-06 12:46:11',NULL,'yes',20),(21,'手机数码','2019-11-06 12:49:01',NULL,'yes',30),(22,'电脑办公','2019-11-06 12:49:12',NULL,'yes',40),(23,'家用电器','2019-11-06 12:49:35',NULL,'yes',50),(24,'银饰','2019-11-06 12:51:57',NULL,'yes',6),(25,'木手串把件','2019-11-06 12:52:14',NULL,'yes',70),(26,'翡翠','2019-11-06 12:52:27',NULL,'yes',73),(27,'水晶玛瑙','2019-11-06 12:52:38',NULL,'yes',75),(28,'珍珠','2019-11-06 12:52:52',NULL,'yes',79);

/*Table structure for table `ap_appraisal_type_info` */

DROP TABLE IF EXISTS `ap_appraisal_type_info`;

CREATE TABLE `ap_appraisal_type_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `type_id` int(11) DEFAULT NULL COMMENT '父类id',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `ap_show` varchar(255) DEFAULT NULL COMMENT '是否展示 yes/no',
  `picture_url` varchar(255) DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4;

/*Data for the table `ap_appraisal_type_info` */

insert  into `ap_appraisal_type_info`(`id`,`name`,`type_id`,`sort`,`create_time`,`update_time`,`ap_show`,`picture_url`) values (1,'test',11,7,'2019-10-29 11:32:50','2019-11-04 14:07:20','no',NULL),(2,'电脑2',11,60,'2019-10-29 16:11:04','2019-11-04 14:07:19','no','http://appraisal.oss-cn-beijing.aliyuncs.com/mYnFx2_1572412829100.jpg'),(9,'手办',11,3,'2019-10-30 13:21:42','2019-11-04 14:07:22','no','http://appraisal.oss-cn-beijing.aliyuncs.com/CYtnDR_1572412901837.jpg'),(10,'阿迪达斯',19,1,'2019-11-04 14:09:08','2019-11-06 12:54:00','no','http://appraisal.oss-cn-beijing.aliyuncs.com/JyCa2W_1572847746573.jpg'),(11,'耐克Nike ',19,2,'2019-11-04 14:10:35','2019-11-06 12:54:06','no','http://appraisal.oss-cn-beijing.aliyuncs.com/6wBkX7_1572847834326.jpg'),(12,'手镯',11,1,'2019-11-04 14:11:12','2019-11-06 12:53:58','no','http://appraisal.oss-cn-beijing.aliyuncs.com/ib2mH5_1572847871410.jpg'),(13,'扳指',11,2,'2019-11-04 14:12:04','2019-11-06 12:54:04','no','http://appraisal.oss-cn-beijing.aliyuncs.com/k1cBZ6_1572847923893.jpg'),(14,'笔记本电脑',12,1,'2019-11-04 14:12:40','2019-11-06 12:53:56','no','http://appraisal.oss-cn-beijing.aliyuncs.com/CpADz6_1572847959743.jpg'),(15,'手机',12,3,'2019-11-04 14:13:13','2019-11-06 12:54:09','no','http://appraisal.oss-cn-beijing.aliyuncs.com/S4JM4P_1572847992545.jpg'),(16,'迪奥',13,1,'2019-11-04 14:20:06','2019-11-06 12:53:54','no','http://appraisal.oss-cn-beijing.aliyuncs.com/jkTEds_1572848405414.jpg'),(17,'古驰',13,1,'2019-11-04 14:20:17','2019-11-06 12:53:52','no','http://appraisal.oss-cn-beijing.aliyuncs.com/hBjfWh_1572848416453.jpg'),(18,'乔丹',14,0,'2019-11-04 14:20:50','2019-11-06 12:53:48','no','http://appraisal.oss-cn-beijing.aliyuncs.com/QCGRjS_1572848449815.jpg'),(19,'李宁',14,2,'2019-11-04 14:21:25','2019-11-06 12:54:02','no','http://appraisal.oss-cn-beijing.aliyuncs.com/HXCSjp_1572848481904.jpg'),(20,'劳力士',16,1,'2019-11-04 14:22:33','2019-11-06 12:53:50','no','http://appraisal.oss-cn-beijing.aliyuncs.com/464PYb_1572848552980.jpeg'),(21,'浪琴',16,0,'2019-11-04 14:22:45','2019-11-06 12:53:46','no','http://appraisal.oss-cn-beijing.aliyuncs.com/CQnTTP_1572848564456.jpg'),(22,'回力',14,3,'2019-11-04 14:38:07','2019-11-06 12:54:08','no','http://appraisal.oss-cn-beijing.aliyuncs.com/EwzGfx_1572849484851.jpg'),(23,'手镯/手链',14,0,'2019-11-06 12:56:39',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/ZkrX6x_1573016197133.jpg'),(24,'项链',14,1,'2019-11-06 12:57:29',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/iPX31j_1573016245980.jpg'),(25,'耳饰',14,3,'2019-11-06 12:58:30',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/MY53wy_1573016301649.jpg'),(26,'鞋靴',13,4,'2019-11-06 12:59:30',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/aekbyj_1573016367365.jpg'),(27,'钱包',13,5,'2019-11-06 13:00:01',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/1NEzTk_1573016397881.jpg'),(28,'腰带',13,6,'2019-11-06 13:01:30',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/1eZSbW_1573016488025.jpg'),(29,'黄金吊坠',12,10,'2019-11-06 13:02:32',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/TFbify_1573016549091.jpg'),(30,'黄金手镯',12,11,'2019-11-06 13:03:16',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/2kwDmy_1573016592753.jpg'),(31,'黄金项链',12,12,'2019-11-06 13:05:18',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/anjcCG_1573016714618.jpg'),(32,'纯金K金戒指',12,13,'2019-11-06 13:06:21',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/MP22Jd_1573016778611.jpg'),(33,'黄金转运珠',12,15,'2019-11-06 13:07:41',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/cm7tMk_1573016858170.jpg'),(34,'黄金耳饰',12,15,'2019-11-06 13:09:23',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/rczzyS_1573016960838.jpg'),(35,'银吊坠/项链',24,21,'2019-11-06 13:10:10',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/JJ5XwC_1573017008347.jpg'),(36,'银手链/脚链',24,22,'2019-11-06 13:11:05',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/kHCNbM_1573017062233.jpg'),(37,'银戒指',24,23,'2019-11-06 13:11:44',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/HEj5ab_1573017101306.jpg'),(38,'宝宝银饰',24,24,'2019-11-06 13:12:22',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/78sQSK_1573017139435.jpg'),(39,'银手镯',24,26,'2019-11-06 13:12:53',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/s34n4t_1573017170741.jpg'),(40,'银耳饰',24,27,'2019-11-06 13:13:33',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/r6fsEW_1573017210805.jpg'),(41,'拉杆箱',11,31,'2019-11-06 13:15:28',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/Zky3D2_1573017325302.jpg'),(42,'电脑包',11,32,'2019-11-06 13:15:56',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/3tB2CY_1573017353562.jpg'),(43,'书包',11,0,'2019-11-06 13:17:28',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/SXmyxc_1573017446183.jpg'),(44,'男士钱包',11,34,'2019-11-06 13:18:13',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/w2naxB_1573017489215.jpg'),(45,'手提包',11,37,'2019-11-06 13:21:01',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/1xGdeX_1573017658491.jpg'),(46,'斜挎包',11,39,'2019-11-06 13:21:35',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/xTJw47_1573017693023.jpg'),(47,'双肩包',11,40,'2019-11-06 13:22:21',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/iCDMYp_1573017738906.jpg'),(48,'婚 纱',19,0,'2019-11-06 13:26:22','2019-11-14 13:06:04','yes','http://appraisal.oss-cn-beijing.aliyuncs.com/iJdMsh_1573017979546.jpg'),(49,'西装',19,0,'2019-11-06 13:29:32',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/k5R38f_1573018168756.jpg'),(50,'童装',19,50,'2019-11-06 13:30:24',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/6C7PNj_1573018221913.jpg'),(51,'瑞表',20,52,'2019-11-06 13:31:36',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/MtCRxe_1573018293646.jpg'),(52,'国表',20,53,'2019-11-06 13:32:12',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/ynK46n_1573018327997.jpg'),(53,'儿童手表',20,55,'2019-11-06 13:32:47',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/Db3Bnk_1573018364872.jpg'),(54,'欧美表',20,56,'2019-11-06 13:33:24',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/YjGCPc_1573018402069.jpg'),(55,'智能手表',20,57,'2019-11-06 13:34:13',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/ibHi5N_1573018450733.jpg'),(56,'日韩表',20,58,'2019-11-06 13:34:44',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/jhGmEx_1573018482044.jpg'),(57,'德表',20,59,'2019-11-06 13:35:38',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/xF56tB_1573018535833.jpg'),(58,'表配件',20,60,'2019-11-06 13:36:35',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/rX5hFM_1573018592638.jpg'),(59,'游戏手机',21,61,'2019-11-06 13:38:41',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/aGG2ej_1573018718813.jpg'),(60,'拍照手机',21,62,'2019-11-06 13:39:39',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/yKhEEY_1573018773923.jpg'),(61,'老人机',21,64,'2019-11-06 13:40:23',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/RmEEKQ_1573018816399.jpg'),(62,'商务手机',21,65,'2019-11-06 13:41:04',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/XR7eeA_1573018861222.jpg'),(63,'对讲机',21,66,'2019-11-06 13:41:40',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/j1YGhP_1573018897077.jpg'),(64,'服务器主机 服务器选件',22,0,'2019-11-14 12:04:00',NULL,'yes','http://appraisal.oss-cn-beijing.aliyuncs.com/Q1HHjk_1573704242346.png');

/*Table structure for table `ap_banner` */

DROP TABLE IF EXISTS `ap_banner`;

CREATE TABLE `ap_banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '首页banner',
  `picture_url` varchar(255) DEFAULT NULL COMMENT '图片url',
  `skip_url` varchar(255) DEFAULT NULL COMMENT '跳转路径（拼接好的）',
  `skip_id` varchar(255) DEFAULT NULL COMMENT '跳转id 或者外部网页地址',
  `type` varchar(255) DEFAULT NULL COMMENT '跳转类型',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `state` varchar(255) DEFAULT NULL COMMENT '状态(putaway上架，soldout下架)',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建用户id',
  `update_user_id` int(11) DEFAULT NULL COMMENT '修改用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `ap_banner` */

insert  into `ap_banner`(`id`,`picture_url`,`skip_url`,`skip_id`,`type`,`title`,`sort`,`state`,`create_user_id`,`update_user_id`,`create_time`,`update_time`) values (14,'http://appraisal.oss-cn-beijing.aliyuncs.com/p5WYbG_1571372983615.jpg',NULL,'4','appraisal','banner2',2,'putaway',1,1,'2019-10-16 17:07:06','2019-10-18 17:29:00'),(16,'http://appraisal.oss-cn-beijing.aliyuncs.com/yjAB87_1571388868414.jpg',NULL,'4','appraisal','jrtc',3,'putaway',1,1,'2019-10-18 16:44:14','2019-10-21 12:57:11'),(17,'http://appraisal.oss-cn-beijing.aliyuncs.com/6fGwcj_1572240599875.png',NULL,'https://m.jd.com/','external','京东',5,'putaway',1,1,'2019-10-28 13:30:06','2019-10-28 16:34:57');

/*Table structure for table `ap_comment` */

DROP TABLE IF EXISTS `ap_comment`;

CREATE TABLE `ap_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论表',
  `appraisal_id` int(11) NOT NULL COMMENT '鉴定id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `cn_comment` mediumtext NOT NULL COMMENT '评论内容 仅限256个字符',
  `goods_state` varchar(50) NOT NULL COMMENT '状态 是否被采纳 yes| no  默认no',
  `judge` varchar(50) NOT NULL COMMENT '判断  真genuine   假counterfeit',
  `cm_show` varchar(50) NOT NULL COMMENT '是否显示 yes|no 默认yes',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8mb4;

/*Data for the table `ap_comment` */

insert  into `ap_comment`(`id`,`appraisal_id`,`user_id`,`cn_comment`,`goods_state`,`judge`,`cm_show`,`create_time`,`update_time`) values (2,7,1,'小米logo都没有，建议扔了','yes','counterfeit','yes','2019-10-17 17:13:33','2019-10-30 12:16:52'),(3,11,5,'真的吧','no','genuine','no','2019-10-18 10:58:44','2019-10-18 15:18:15'),(4,11,2,'假的吧','no','counterfeit','yes','2019-10-18 11:00:10','2019-10-18 15:16:30'),(5,11,5,'真的真的','no','genuine','no','2019-10-18 11:01:13','2019-10-18 15:17:51'),(6,4,3,'送我吧\n(˶˚**ᗨ*˚˶)','no','genuine','yes','2019-10-18 11:29:22','2019-10-18 11:29:22'),(7,2,6,'得得得额','no','genuine','yes','2019-10-18 14:02:57','2019-10-18 14:02:57'),(8,1,6,'LOL看看','no','counterfeit','yes','2019-10-18 14:03:49','2019-10-18 14:03:49'),(9,11,5,'真的?(｡･ω･｡)ﾉ♡','no','genuine','no','2019-10-18 14:35:31','2019-10-18 14:58:43'),(10,11,5,'真的?(｡･ω･｡)ﾉ♡','no','genuine','no','2019-10-18 14:35:43','2019-10-18 14:53:45'),(12,13,5,'4\n\n\n\n2','no','counterfeit','yes','2019-10-18 15:14:14','2019-10-18 15:14:14'),(13,14,3,'大家好','no','genuine','yes','2019-10-18 15:23:39','2019-10-18 15:47:00'),(14,14,3,'我是一名kf**cs**yw**dz','no','counterfeit','no','2019-10-18 15:24:22','2019-10-18 15:25:15'),(15,1,5,'哈哈哈该','no','counterfeit','yes','2019-10-18 15:26:23','2019-10-18 15:26:23'),(17,7,5,'假的假的','no','counterfeit','no','2019-10-18 16:55:07','2019-10-21 17:35:51'),(18,8,5,'(*^▽^*)(*˘*³˘)♥','no','counterfeit','yes','2019-10-18 17:25:25','2019-10-18 17:25:25'),(19,19,3,'鉴定未通过','yes','counterfeit','yes','2019-10-18 17:25:30','2019-10-18 17:42:46'),(20,20,7,'ᵕ᷄*≀*̠˘᷅难顶','yes','genuine','yes','2019-10-18 18:36:23','2019-10-18 18:38:50'),(25,9,3,'hooks','no','genuine','no','2019-10-22 12:08:05','2019-10-22 12:09:55'),(26,9,3,'铺u收拾退模糊','no','counterfeit','yes','2019-10-22 12:08:21','2019-10-22 12:10:15'),(27,46,5,'纠错','no','genuine','yes','2019-10-22 15:30:26','2019-10-22 15:39:50'),(28,46,5,'沟通','no','genuine','yes','2019-10-22 15:39:50','2019-10-22 15:43:40'),(29,46,2,'测试后台发布评论','yes','genuine','yes','2019-10-22 15:43:15','2019-10-22 15:43:43'),(30,4,5,'。。。。。','no','genuine','yes','2019-10-22 16:19:41','2019-10-22 16:19:41'),(41,2,4,'Djsfdshfsfdhsfsfsdfsfs','no','genuine','yes','2019-10-25 10:59:13','2019-10-25 10:59:13'),(45,67,3,'计算机（computer）俗称电脑，是现代一种用于高速计算的电子计算机器，可以进行数值计算，又可以进行逻辑计算，还具有存储记忆功能。','no','genuine','yes','2019-10-27 16:50:46','2019-10-27 16:52:12'),(46,67,2,'计算机（computer）俗称电脑，是现代一种用于高速计算的电子计算机器，可以进行数值计算，又可以进行逻辑计算，还具有存储记忆功能。','yes','genuine','yes','2019-10-27 16:51:14','2019-10-27 16:52:16'),(50,10,3,'富通九州','no','genuine','no','2019-10-28 13:36:50','2019-11-07 16:37:09'),(51,10,3,'测试纠错','no','genuine','no','2019-10-28 13:41:05','2019-11-07 16:37:12'),(53,7,3,'啊啊啊','no','genuine','yes','2019-10-30 12:20:36','2019-10-30 12:20:36'),(54,68,2,'123456789','no','genuine','no','2019-11-04 10:43:23','2019-11-04 10:49:13'),(55,69,2,'123456789','no','genuine','yes','2019-11-04 10:44:22','2019-11-04 10:44:22'),(56,9,3,'就是真的','no','genuine','yes','2019-11-05 10:51:22','2019-11-05 10:51:22'),(57,9,3,'大家好我是渣渣辉，?','no','counterfeit','yes','2019-11-05 10:51:49','2019-11-05 10:51:49'),(59,4,2,'???????','no','genuine','yes','2019-11-05 16:05:09','2019-11-05 16:05:09'),(62,65,2,'?','no','genuine','yes','2019-11-05 16:14:21','2019-11-05 16:14:21'),(70,4,2,'3','no','阿里巴巴1234132','yes','2019-11-05 16:37:35','2019-11-05 16:37:35'),(71,4,2,'3','no','阿里巴巴1234132','yes','2019-11-05 16:39:13','2019-11-05 16:39:13'),(72,4,2,'****1234132','no','genuine','yes','2019-11-05 16:39:59','2019-11-05 16:39:59'),(74,89,3,'rollKKK','yes','genuine','yes','2019-11-05 17:00:44','2019-11-05 17:01:02'),(76,90,2,'看着像真的','no','genuine','no','2019-11-06 13:58:32','2019-11-14 14:07:39'),(77,90,2,'干嘛','yes','counterfeit','yes','2019-11-06 13:59:03','2019-11-06 13:59:09'),(78,90,2,'阿里巴巴','no','genuine','no','2019-11-06 14:00:33','2019-11-14 14:07:42'),(79,90,3,'阿里巴巴','no','genuine','yes','2019-11-06 14:10:46','2019-11-06 14:10:46'),(80,91,3,'公积金','no','genuine','yes','2019-11-06 14:20:06','2019-11-06 14:20:06'),(81,91,3,'公积金','no','counterfeit','yes','2019-11-06 14:20:32','2019-11-06 14:20:32'),(82,94,5,'啦啦啦啦','yes','genuine','yes','2019-11-06 18:04:29','2019-11-06 18:05:03'),(83,94,5,'啦啦啦啦','no','genuine','yes','2019-11-06 18:04:39','2019-11-06 18:04:39'),(85,94,3,'****四十大盗','no','genuine','yes','2019-11-06 18:41:47','2019-11-06 18:41:47'),(86,94,3,'*东','no','genuine','yes','2019-11-06 18:42:27','2019-11-06 18:42:27'),(87,2,5,'*东','no','genuine','yes','2019-11-06 18:49:01','2019-11-06 18:49:01'),(88,97,5,'垃圾','no','genuine','yes','2019-11-07 13:53:38','2019-11-07 15:59:09'),(90,101,3,'u哦辛苦啦','yes','counterfeit','yes','2019-11-07 14:23:50','2019-11-07 15:16:17'),(91,5,5,'同','no','genuine','yes','2019-11-07 14:55:09','2019-11-07 14:55:09'),(93,102,5,'测试','yes','genuine','yes','2019-11-07 15:05:31','2019-11-07 15:05:54'),(94,109,5,'测试采纳','yes','genuine','yes','2019-11-07 15:18:45','2019-11-07 15:19:00'),(95,97,5,'假的','yes','counterfeit','yes','2019-11-07 15:59:09','2019-11-07 15:59:09'),(96,98,4,'Nice','yes','genuine','yes','2019-11-07 20:40:38','2019-11-07 21:21:17'),(97,99,4,'GUI','yes','genuine','yes','2019-11-07 21:26:57','2019-11-07 21:27:04'),(98,2,9,'哈哈','no','genuine','yes','2019-11-08 09:22:56','2019-11-08 09:22:56'),(99,113,9,'好的吧','yes','genuine','yes','2019-11-08 09:34:29','2019-11-08 09:34:42'),(100,68,5,'得得','yes','genuine','yes','2019-11-08 10:21:12','2019-11-08 10:21:17'),(101,103,3,'*东*东*东','yes','genuine','yes','2019-11-08 10:36:29','2019-11-08 10:36:34'),(102,112,4,'好手机','no','genuine','yes','2019-11-08 10:57:15','2019-11-08 10:57:15'),(103,133,14,'从图片看防伪和字体不是原厂。','yes','counterfeit','yes','2019-11-14 15:58:55','2019-11-14 15:59:34'),(105,5,3,'，不通','no','genuine','yes','2020-03-10 10:28:54','2020-03-10 10:28:54');

/*Table structure for table `ap_enshrine` */

DROP TABLE IF EXISTS `ap_enshrine`;

CREATE TABLE `ap_enshrine` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收藏表',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `appraisal_id` int(11) NOT NULL COMMENT '鉴定id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;

/*Data for the table `ap_enshrine` */

insert  into `ap_enshrine`(`id`,`user_id`,`appraisal_id`,`create_time`) values (2,52,2,'2019-10-18 14:01:37'),(3,52,1,'2019-10-18 14:01:40'),(6,5,12,'2019-10-18 15:10:53'),(8,5,13,'2019-10-18 15:33:51'),(12,2,45,'2019-10-22 15:39:52'),(13,4,1,'2019-10-27 17:45:55'),(14,4,5,'2019-10-27 17:45:59'),(15,4,67,'2019-10-27 17:46:20'),(16,4,2,'2019-10-28 21:51:45'),(25,2,69,'2019-11-05 11:26:12'),(40,5,93,'2019-11-06 18:19:42'),(44,5,1,'2019-11-07 15:22:40'),(45,5,2,'2019-11-07 15:22:44'),(46,5,3,'2019-11-07 15:22:47'),(47,5,4,'2019-11-07 15:22:49'),(48,5,5,'2019-11-07 15:22:51'),(49,5,6,'2019-11-07 15:22:53'),(50,5,7,'2019-11-07 15:22:56'),(51,5,11,'2019-11-07 15:22:58'),(52,5,8,'2019-11-07 15:23:00'),(53,5,9,'2019-11-07 15:23:02'),(54,5,10,'2019-11-07 15:23:05'),(55,5,66,'2019-11-07 15:23:07'),(56,5,67,'2019-11-07 15:23:09'),(57,5,68,'2019-11-07 15:23:12'),(58,5,69,'2019-11-07 15:23:15'),(59,5,86,'2019-11-07 15:23:19'),(60,5,87,'2019-11-07 15:23:22'),(61,5,88,'2019-11-07 15:23:24'),(62,5,89,'2019-11-07 15:23:26'),(63,5,92,'2019-11-07 15:23:29'),(64,5,91,'2019-11-07 15:23:31'),(65,5,90,'2019-11-07 15:23:33'),(71,10,2,'2019-11-08 09:16:05'),(72,10,1,'2019-11-08 09:16:08'),(73,10,5,'2019-11-08 09:16:11'),(75,10,68,'2019-11-08 09:16:32'),(76,3,87,'2019-11-08 12:22:30');

/*Table structure for table `ap_feedback` */

DROP TABLE IF EXISTS `ap_feedback`;

CREATE TABLE `ap_feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '意见反馈表',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `describc` varchar(255) NOT NULL COMMENT '描述',
  `ap_status` int(2) NOT NULL COMMENT '0 已读 1 未读',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `is_show` varchar(11) NOT NULL DEFAULT 'yes' COMMENT '是否显示 yes|no',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `ap_feedback` */

insert  into `ap_feedback`(`id`,`user_id`,`describc`,`ap_status`,`create_time`,`update_time`,`is_show`) values (1,3,'不回头',0,'2019-10-18 10:25:40','2019-10-18 13:34:49','yes'),(2,3,'垃圾@',0,'2019-10-18 12:09:53','2019-10-18 13:34:50','yes'),(3,3,'15788',0,'2019-10-18 12:10:27','2019-10-18 13:34:51','yes'),(4,5,'。。。',0,'2019-10-18 15:25:24','2019-10-22 16:44:56','yes'),(5,5,'呦西呦西',0,'2019-10-18 15:25:44','2019-10-22 16:44:57','yes'),(6,3,'啊啊啊',0,'2019-10-27 16:28:15','2019-10-28 14:11:40','yes'),(7,3,'测试反馈',0,'2019-10-28 14:11:30','2019-10-30 12:57:49','yes'),(8,2,'Emmmm',0,'2019-11-05 11:31:25','2019-11-14 14:04:05','yes'),(9,2,'   ',0,'2019-11-05 11:31:34','2019-11-14 14:04:05','yes'),(10,2,'    ',0,'2019-11-05 11:32:40','2019-11-14 14:04:06','yes'),(11,2,'   ',0,'2019-11-05 11:33:13','2019-11-14 14:04:07','yes');

/*Table structure for table `ap_grade` */

DROP TABLE IF EXISTS `ap_grade`;

CREATE TABLE `ap_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '等级表',
  `empirical` int(11) DEFAULT NULL COMMENT '经验值',
  `empirical_end` int(11) DEFAULT NULL COMMENT '截止',
  `grade_name` varchar(255) DEFAULT NULL COMMENT '名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `ap_grade` */

insert  into `ap_grade`(`id`,`empirical`,`empirical_end`,`grade_name`,`create_time`,`update_time`) values (9,0,NULL,'新手','2019-10-16 17:03:38',NULL),(10,10,NULL,'青铜','2019-10-16 17:28:45','2019-10-28 13:43:33');

/*Table structure for table `ap_key_val` */

DROP TABLE IF EXISTS `ap_key_val`;

CREATE TABLE `ap_key_val` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '键值表',
  `key` varchar(255) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL COMMENT '备注',
  `values` varchar(255) DEFAULT NULL,
  `create_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `update_id` int(11) DEFAULT NULL COMMENT '修改人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `ap_key_val` */

insert  into `ap_key_val`(`id`,`key`,`comment`,`values`,`create_id`,`update_id`,`create_time`,`update_time`) values (1,'proportion','打赏比例','0.5',NULL,NULL,NULL,NULL);

/*Table structure for table `ap_order` */

DROP TABLE IF EXISTS `ap_order`;

CREATE TABLE `ap_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '支付信息表',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `order_no` bigint(20) DEFAULT NULL COMMENT '订单号',
  `pay_platform` int(10) DEFAULT NULL COMMENT '支付平台 1-支付宝 2-微信 3-IOS内购  4-鉴定余额',
  `platform_number` varchar(200) DEFAULT NULL COMMENT '支付流水号',
  `platform_status` varchar(20) DEFAULT NULL COMMENT ' 0-已取消 10-未付款 20-已付款',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ap_order` */

/*Table structure for table `ap_order_item` */

DROP TABLE IF EXISTS `ap_order_item`;

CREATE TABLE `ap_order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单表详情表',
  `order_number` varchar(520) DEFAULT NULL COMMENT '订单号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `payment` decimal(20,2) DEFAULT NULL COMMENT '实际支付金额，单位元，保留两位小数',
  `state` int(10) DEFAULT '0' COMMENT '订单状态 0-(未付款，已取消) 10-已付款',
  `reward_id` int(11) DEFAULT NULL COMMENT '被打赏人id',
  `reward_amount` decimal(20,2) DEFAULT NULL COMMENT '被打赏人实际收到金额',
  `comment_id` int(11) DEFAULT NULL COMMENT '评论id',
  `appraisal_id` int(11) DEFAULT NULL COMMENT '鉴定id',
  `pay_platform` varchar(256) DEFAULT NULL COMMENT '1-支付宝 2-微信 3-IOS内购 4-鉴定余额',
  `platform_number` varchar(256) DEFAULT NULL COMMENT '支付流水号',
  `payment_time` datetime DEFAULT NULL COMMENT '[冗余]',
  `close_time` datetime DEFAULT NULL COMMENT '完成时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间[冗余]',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_no` (`order_number`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

/*Data for the table `ap_order_item` */

insert  into `ap_order_item`(`id`,`order_number`,`user_id`,`payment`,`state`,`reward_id`,`reward_amount`,`comment_id`,`appraisal_id`,`pay_platform`,`platform_number`,`payment_time`,`close_time`,`create_time`,`update_time`) values (1,'20191017164711393134',50,'0.01',0,1,NULL,NULL,3,'1',NULL,NULL,NULL,'2019-10-17 16:47:11','2019-10-17 16:47:11'),(2,'20191017165739207347',3,'1.00',0,1,NULL,1,1,'1',NULL,NULL,NULL,'2019-10-17 16:57:45','2019-10-17 16:57:45'),(3,'20191017170049322185',50,'0.01',0,1,NULL,NULL,3,'1',NULL,NULL,NULL,'2019-10-17 17:00:49','2019-10-17 17:00:49'),(4,'20191017171031226745',1,'1.00',0,2,'0.50',NULL,7,'2',NULL,NULL,NULL,'2019-10-17 17:10:31','2019-10-17 17:10:31'),(5,'20191017173341522001',3,'1.00',0,1,NULL,NULL,3,'1',NULL,NULL,NULL,'2019-10-17 17:33:41','2019-10-17 17:33:41'),(6,'20191017173514176319',3,'1.00',10,1,'0.50',NULL,3,'1','2019101722001458370572333964',NULL,'2019-10-18 18:04:48','2019-10-17 17:35:14','2019-10-17 17:35:14'),(7,'20191018105419469078',3,'1.00',10,1,'0.50',NULL,3,'1','2019101822001458370573635592',NULL,'2019-10-19 11:23:41','2019-10-18 10:54:19','2019-10-18 10:54:19'),(8,'20191018123043916522',3,'1000.00',0,1,NULL,NULL,2,'1',NULL,NULL,NULL,'2019-10-18 12:30:43','2019-10-18 12:30:43'),(9,'20191018123102795486',3,'100.00',0,1,'50.00',NULL,2,'2',NULL,NULL,NULL,'2019-10-18 12:31:03','2019-10-18 12:31:03'),(10,'20191018123106621721',3,'100.00',0,1,'50.00',NULL,2,'2',NULL,NULL,NULL,'2019-10-18 12:31:06','2019-10-18 12:31:06'),(11,'20191018152704324134',5,'1.00',0,1,'0.50',NULL,5,'2',NULL,NULL,NULL,'2019-10-18 15:27:04','2019-10-18 15:27:04'),(12,'20191018152730751780',5,'100000.00',0,1,'50000.00',NULL,2,'2',NULL,NULL,NULL,'2019-10-18 15:27:30','2019-10-18 15:27:30'),(13,'20191018152749531689',5,'100000.00',0,1,NULL,NULL,2,'1',NULL,NULL,NULL,'2019-10-18 15:27:49','2019-10-18 15:27:49'),(14,'20191018185519701829',3,'1.00',0,7,NULL,20,20,'1',NULL,NULL,NULL,'2019-10-18 18:55:19','2019-10-18 18:55:19'),(15,'20191021125324951633',2,'1.00',10,1,'1.00',NULL,8,'4',NULL,'2019-10-21 12:53:24','2019-10-21 12:53:24','2019-10-21 12:53:24','2019-10-21 12:53:24'),(16,'20191021125549382968',2,'2.00',10,3,'2.00',NULL,20,'4',NULL,'2019-10-21 12:55:49','2019-10-21 12:55:49','2019-10-21 12:55:49','2019-10-21 12:55:49'),(17,'20191022113145382038',3,'1.00',10,1,'0.50',NULL,2,'1','2019102222001458370585231461',NULL,'2019-10-23 12:01:06','2019-10-22 11:31:45','2019-10-22 11:31:45'),(18,'20191025151349559398',6,'1.00',10,1,'0.50',NULL,1,'4',NULL,'2019-10-25 15:13:49','2019-10-25 15:13:49','2019-10-25 15:13:49','2019-10-25 15:13:49'),(19,'20191028132430709201',3,'1.00',0,1,NULL,NULL,3,'1',NULL,NULL,NULL,'2019-10-28 13:24:30','2019-10-28 13:24:30'),(20,'20191028174230863757',9,'1.00',10,3,'0.50',51,10,'4',NULL,'2019-10-28 17:42:30','2019-10-28 17:42:30','2019-10-28 17:42:30','2019-10-28 17:42:30'),(21,'20191028205011718515',4,'1.00',10,1,'0.50',NULL,2,'4',NULL,'2019-10-28 20:50:11','2019-10-28 20:50:11','2019-10-28 20:50:11','2019-10-28 20:50:11'),(22,'20191028210430586807',4,'1.00',10,1,'0.50',NULL,1,'4',NULL,'2019-10-28 21:04:30','2019-10-28 21:04:30','2019-10-28 21:04:30','2019-10-28 21:04:30'),(23,'20191029141502116703',9,'1.00',10,3,'0.50',NULL,11,'4',NULL,'2019-10-29 14:15:03','2019-10-29 14:15:03','2019-10-29 14:15:03','2019-10-29 14:15:03'),(24,'20191030093041459857',9,'1.00',10,1,'0.50',NULL,1,'4',NULL,'2019-10-30 09:30:41','2019-10-30 09:30:41','2019-10-30 09:30:41','2019-10-30 09:30:41'),(25,'20191104153731265479',4,'1.00',10,1,'0.50',NULL,2,'4',NULL,'2019-11-04 15:37:31','2019-11-04 15:37:31','2019-11-04 15:37:31','2019-11-04 15:37:31'),(26,'20191104190957899091',4,'1.00',10,1,'0.50',NULL,2,'4',NULL,'2019-11-04 19:09:57','2019-11-04 19:09:57','2019-11-04 19:09:57','2019-11-04 19:09:57'),(27,'20191104191003365418',4,'1.00',10,1,'0.50',NULL,2,'4',NULL,'2019-11-04 19:10:03','2019-11-04 19:10:03','2019-11-04 19:10:03','2019-11-04 19:10:03'),(28,'20191104191011326408',4,'1.00',10,1,'0.50',NULL,2,'4',NULL,'2019-11-04 19:10:11','2019-11-04 19:10:11','2019-11-04 19:10:11','2019-11-04 19:10:11'),(29,'20191104191020618659',4,'1.00',10,1,'0.50',NULL,2,'4',NULL,'2019-11-04 19:10:20','2019-11-04 19:10:20','2019-11-04 19:10:20','2019-11-04 19:10:20'),(30,'20191104191024282551',4,'1.00',10,1,'0.50',NULL,2,'4',NULL,'2019-11-04 19:10:24','2019-11-04 19:10:24','2019-11-04 19:10:24','2019-11-04 19:10:24'),(31,'20191104191028771283',4,'1.00',10,1,'0.50',NULL,2,'4',NULL,'2019-11-04 19:10:28','2019-11-04 19:10:28','2019-11-04 19:10:28','2019-11-04 19:10:28'),(32,'20191104191029455958',4,'1.00',10,1,'0.50',NULL,2,'4',NULL,'2019-11-04 19:10:29','2019-11-04 19:10:29','2019-11-04 19:10:29','2019-11-04 19:10:29'),(33,'20191104191033330214',4,'1.00',10,1,'0.50',NULL,2,'4',NULL,'2019-11-04 19:10:33','2019-11-04 19:10:33','2019-11-04 19:10:33','2019-11-04 19:10:33'),(34,'20191104191252903686',4,'1.00',10,1,'0.50',NULL,1,'4',NULL,'2019-11-04 19:12:52','2019-11-04 19:12:52','2019-11-04 19:12:52','2019-11-04 19:12:52'),(35,'20191104191300953541',4,'1.00',10,1,'0.50',NULL,3,'4',NULL,'2019-11-04 19:13:00','2019-11-04 19:13:00','2019-11-04 19:13:00','2019-11-04 19:13:00'),(36,'20191104191306845543',4,'1.00',10,1,'0.50',NULL,3,'4',NULL,'2019-11-04 19:13:06','2019-11-04 19:13:06','2019-11-04 19:13:06','2019-11-04 19:13:06'),(37,'20191104191622699506',10,'1.00',10,6,'0.50',NULL,69,'4',NULL,'2019-11-04 19:16:22','2019-11-04 19:16:22','2019-11-04 19:16:22','2019-11-04 19:16:22'),(38,'20191104191622505441',10,'1.00',10,6,'0.50',NULL,69,'4',NULL,'2019-11-04 19:16:22','2019-11-04 19:16:22','2019-11-04 19:16:22','2019-11-04 19:16:22'),(39,'20191104191646649213',10,'1.00',10,6,'0.50',NULL,69,'4',NULL,'2019-11-04 19:16:46','2019-11-04 19:16:46','2019-11-04 19:16:46','2019-11-04 19:16:46'),(40,'20191104191702522943',10,'1.00',10,3,'0.50',NULL,65,'4',NULL,'2019-11-04 19:17:02','2019-11-04 19:17:02','2019-11-04 19:17:02','2019-11-04 19:17:02'),(41,'20191104220530892586',9,'1.00',10,2,'0.50',46,67,'4',NULL,'2019-11-04 22:05:30','2019-11-04 22:05:30','2019-11-04 22:05:30','2019-11-04 22:05:30'),(42,'20191104220533698259',9,'1.00',10,3,'0.50',NULL,67,'4',NULL,'2019-11-04 22:05:33','2019-11-04 22:05:33','2019-11-04 22:05:33','2019-11-04 22:05:33'),(43,'20191104220735882218',9,'1.00',10,2,'0.50',46,67,'4',NULL,'2019-11-04 22:07:35','2019-11-04 22:07:35','2019-11-04 22:07:35','2019-11-04 22:07:35'),(44,'20191104220757644184',9,'1.00',10,3,'0.50',NULL,67,'4',NULL,'2019-11-04 22:07:57','2019-11-04 22:07:57','2019-11-04 22:07:57','2019-11-04 22:07:57'),(45,'20191104220800462991',9,'1.00',10,2,'0.50',46,67,'4',NULL,'2019-11-04 22:08:00','2019-11-04 22:08:00','2019-11-04 22:08:00','2019-11-04 22:08:00'),(46,'20191104220851354249',9,'1.00',10,3,'0.50',NULL,67,'4',NULL,'2019-11-04 22:08:51','2019-11-04 22:08:51','2019-11-04 22:08:51','2019-11-04 22:08:51'),(47,'20191104220926461321',9,'1.00',10,1,'0.50',NULL,1,'4',NULL,'2019-11-04 22:09:26','2019-11-04 22:09:26','2019-11-04 22:09:26','2019-11-04 22:09:26'),(48,'20191104221246177338',9,'1.00',10,3,'0.50',NULL,64,'4',NULL,'2019-11-04 22:12:46','2019-11-04 22:12:46','2019-11-04 22:12:46','2019-11-04 22:12:46'),(49,'20191106180748887853',5,'1.00',0,3,'0.50',NULL,94,'2',NULL,NULL,NULL,'2019-11-06 18:07:49','2019-11-06 18:07:49'),(50,'20191107123644196659',5,'10.00',10,1,'5.00',NULL,2,'4',NULL,'2019-11-07 12:36:44','2019-11-07 12:36:44','2019-11-07 12:36:44','2019-11-07 12:36:44'),(51,'20191107143031495780',5,'1.00',0,1,NULL,NULL,2,'1',NULL,NULL,NULL,'2019-11-07 14:30:31','2019-11-07 14:30:31'),(52,'20191107145647461624',5,'1.00',10,1,'0.50',NULL,5,'4',NULL,'2019-11-07 14:56:47','2019-11-07 14:56:47','2019-11-07 14:56:47','2019-11-07 14:56:47'),(53,'20191107163853729296',5,'1.00',0,3,'0.50',NULL,97,'2',NULL,NULL,NULL,'2019-11-07 16:38:53','2019-11-07 16:38:53'),(54,'20191107163905826976',5,'1.00',0,3,NULL,NULL,97,'1',NULL,NULL,NULL,'2019-11-07 16:39:05','2019-11-07 16:39:05'),(55,'20191107221701768608',9,'6.00',10,2,'3.00',NULL,7,'3',NULL,'2019-11-07 22:17:01','2019-11-07 22:17:01','2019-11-07 22:17:01','2019-11-07 22:17:01'),(56,'20191107221743606422',9,'6.00',10,2,'3.00',NULL,7,'3',NULL,'2019-11-07 22:17:43','2019-11-07 22:17:43','2019-11-07 22:17:43','2019-11-07 22:17:43'),(57,'20191108121935591381',3,'6.00',10,1,'3.00',NULL,10,'3',NULL,'2019-11-08 12:19:35','2019-11-08 12:19:35','2019-11-08 12:19:35','2019-11-08 12:19:35'),(58,'20191108122120966795',3,'6.00',10,1,'3.00',NULL,10,'3',NULL,'2019-11-08 12:21:20','2019-11-08 12:21:20','2019-11-08 12:21:20','2019-11-08 12:21:20'),(59,'20191120145549341704',5,'1.00',0,3,NULL,NULL,100,'1',NULL,NULL,NULL,'2019-11-20 14:55:49','2019-11-20 14:55:49'),(60,'20191120145605951451',5,'1.00',0,3,'0.50',NULL,100,'2',NULL,NULL,NULL,'2019-11-20 14:56:05','2019-11-20 14:56:05'),(61,'20191120145615482831',5,'1.00',10,3,'0.50',NULL,100,'4',NULL,'2019-11-20 14:56:15','2019-11-20 14:56:15','2019-11-20 14:56:15','2019-11-20 14:56:15'),(62,'20200310114745477588',3,'1.00',0,1,'0.50',NULL,2,'2',NULL,NULL,NULL,'2020-03-10 11:47:45','2020-03-10 11:47:45');

/*Table structure for table `ap_reised` */

DROP TABLE IF EXISTS `ap_reised`;

CREATE TABLE `ap_reised` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '纠错表',
  `appraisal_id` int(11) NOT NULL COMMENT '纠错的物品id',
  `comment_id` int(11) NOT NULL COMMENT '原被采纳的评论id',
  `content` text,
  `user_id` int(11) NOT NULL COMMENT '发起纠错请求的用户id',
  `status` varchar(10) CHARACTER SET utf8 DEFAULT 'ignore' COMMENT '状态 采纳accept 未采纳ignore',
  `create_time` datetime NOT NULL COMMENT '发起请求的时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `ap_show` varchar(11) CHARACTER SET utf8 NOT NULL DEFAULT 'yes' COMMENT '是否显示 yes | no',
  `ap_read` int(11) NOT NULL DEFAULT '0' COMMENT '已读 1  未读 0',
  `judge` varchar(11) CHARACTER SET utf8 DEFAULT NULL COMMENT '判断  真genuine   假counterfeit',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4;

/*Data for the table `ap_reised` */

insert  into `ap_reised`(`id`,`appraisal_id`,`comment_id`,`content`,`user_id`,`status`,`create_time`,`update_time`,`ap_show`,`ap_read`,`judge`) values (19,11,4,'真的?(｡･ω･｡)ﾉ♡',5,'accept','2019-10-18 11:20:41','2019-10-18 14:35:43','yes',0,'genuine'),(20,46,27,'李子怡',5,'ignore','2019-10-22 15:30:50','2019-10-22 15:30:50','yes',0,'genuine'),(21,46,27,'沟通',5,'accept','2019-10-22 15:31:59','2019-10-22 15:39:50','yes',0,'genuine'),(22,46,29,'HK途虎',5,'ignore','2019-10-22 16:35:44','2019-10-22 16:35:44','yes',0,'genuine'),(23,46,29,'上我扣扣',5,'ignore','2019-10-22 17:08:38','2019-10-22 17:08:38','yes',0,'genuine'),(24,46,29,'扣扣',5,'ignore','2019-10-22 17:09:21','2019-10-22 17:09:21','yes',0,'genuine'),(25,46,29,'咯路',5,'ignore','2019-10-22 17:09:34','2019-10-22 17:09:34','yes',0,'genuine'),(26,10,50,'测试纠错',3,'accept','2019-10-28 13:39:20','2019-10-28 13:41:05','yes',0,'genuine'),(27,7,2,'soilKKK',3,'ignore','2019-10-30 12:20:56','2019-10-30 12:20:56','yes',0,'genuine'),(28,67,46,'  ',2,'ignore','2019-11-05 11:37:55','2019-11-05 11:37:55','yes',0,'counterfeit'),(29,94,82,'JCB卡',5,'ignore','2019-11-06 18:07:17','2019-11-06 18:07:17','yes',0,'counterfeit'),(30,97,88,'假的',5,'accept','2019-11-07 15:57:55','2019-11-07 15:59:09','yes',0,'counterfeit'),(31,97,95,'真的',5,'ignore','2019-11-07 16:00:03','2019-11-07 16:00:03','yes',0,'genuine');

/*Table structure for table `ap_sensitivity` */

DROP TABLE IF EXISTS `ap_sensitivity`;

CREATE TABLE `ap_sensitivity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '敏感字',
  `str` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10411 DEFAULT CHARSET=utf8mb4;

/*Data for the table `ap_sensitivity` */

insert  into `ap_sensitivity`(`id`,`str`,`create_time`) values (10408,'阿里巴巴','2019-10-28 13:31:46'),(10410,'京','2019-11-06 18:42:19');

/*Table structure for table `ap_statistical` */

DROP TABLE IF EXISTS `ap_statistical`;

CREATE TABLE `ap_statistical` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录表',
  `active_count` varchar(255) DEFAULT '0' COMMENT '当天活跃数',
  `registration_count` varchar(255) DEFAULT '0' COMMENT '当天注册数',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `ap_statistical` */

insert  into `ap_statistical`(`id`,`active_count`,`registration_count`,`create_time`,`update_time`) values (1,'4','4','2019-10-17',NULL),(2,'7',NULL,'2019-10-18',NULL),(3,'4',NULL,'2019-10-21',NULL),(4,'4','0','2019-10-22',NULL),(5,'4','0','2019-10-23',NULL),(6,'3','0','2019-10-24',NULL),(7,'7','1','2019-10-25',NULL),(8,'2','0','2019-10-26',NULL),(9,'3','0','2019-10-27',NULL),(10,'4','1','2019-10-28',NULL),(11,'4','0','2019-10-29',NULL),(12,'5','0','2019-10-30',NULL),(13,'2','0','2019-10-31',NULL),(14,'4','0','2019-11-01',NULL),(15,'5','0','2019-11-04',NULL),(16,'5','1','2019-11-05',NULL),(17,'6','0','2019-11-06',NULL),(18,'0','1','2019-11-08',NULL),(19,'0','2','2019-11-14',NULL),(20,'0','1','2020-03-10',NULL);

/*Table structure for table `ap_top_search` */

DROP TABLE IF EXISTS `ap_top_search`;

CREATE TABLE `ap_top_search` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '热门搜素',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `state` varchar(255) DEFAULT 'yes' COMMENT '状态',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `ap_top_search` */

/*Table structure for table `ap_user` */

DROP TABLE IF EXISTS `ap_user`;

CREATE TABLE `ap_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用戶表',
  `name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `head_url` varchar(255) DEFAULT 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1571729958141&di=b93cd34675c563c3ad35550b96430aac&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F69ad7a731f43d4b8729f1a2fbe65c43801ca0f033250-EV1vMf_fw658' COMMENT '头像路径',
  `integral` int(11) DEFAULT '0' COMMENT '经验值',
  `money` double(11,2) DEFAULT '0.00' COMMENT '账户金额',
  `qq_openid` varchar(255) DEFAULT NULL COMMENT 'qq登录id',
  `wx_openid` varchar(255) DEFAULT NULL COMMENT '微信登录id',
  `status` varchar(255) DEFAULT 'no' COMMENT '状态(yes为拉黑，no未拉黑)',
  `registered_channels` varchar(255) DEFAULT NULL COMMENT '注册渠道',
  `id_number` varchar(255) DEFAULT NULL COMMENT '身份证号',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `sex` varchar(255) DEFAULT NULL COMMENT '性别',
  `system` varchar(255) DEFAULT 'no' COMMENT '是否为系统用户 yes/no',
  `zfb_user_id` varchar(255) DEFAULT NULL COMMENT '支付宝userid',
  `pay_password` varchar(255) DEFAULT NULL COMMENT '支付密码',
  `watermark` varchar(255) DEFAULT NULL COMMENT '用户水印',
  `watermark_state` varchar(255) DEFAULT '0' COMMENT '水印状态 0 未设置 1 审核中  2审核成功 3未通过',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `ap_user` */

insert  into `ap_user`(`id`,`name`,`phone`,`head_url`,`integral`,`money`,`qq_openid`,`wx_openid`,`status`,`registered_channels`,`id_number`,`birthday`,`email`,`create_time`,`update_time`,`sex`,`system`,`zfb_user_id`,`pay_password`,`watermark`,`watermark_state`) values (1,'用户_15690469182','15690469182','https://appraisal.oss-cn-beijing.aliyuncs.com/1571303288827.jpg',1,235.72,NULL,NULL,'no','Android',NULL,NULL,NULL,'2019-10-17 16:23:34','2019-11-08 12:21:20',NULL,'no',NULL,NULL,NULL,'3'),(2,'小汶','15214440324','https://appraisal.oss-cn-beijing.aliyuncs.com/1571303234514.jpg',2,100000004.50,'1F451FCCA101FD891BCB910A01ADC1BE','orZ4m0qi5VSxqqV1-wOYidYyWK7Q','no','Android',NULL,'2019-02-28',NULL,'2019-10-17 16:54:17','2019-11-07 22:17:43','男','yes',NULL,NULL,'京睿天成科技有限公司','2'),(3,'测试用户','17600334372','https://appraisal.oss-cn-beijing.aliyuncs.com/1573541320480.jpeg',0,9.50,'4137C7E75548078E0A6AC72AB00AC2C1','orZ4m0upvWFA_6QvvOq_eWecSfYo','no','IOS',NULL,'2019-11-14',NULL,'2019-11-08 15:28:18','2020-03-10 10:18:54','男','no','2088822176458377','3d4f2bf07dc1be38b20cd6e46949a1071f9d0e3d','京睿天成科技有限公司','1'),(4,'dfdsfsf','18701683202','https://appraisal.oss-cn-beijing.aliyuncs.com/1572269464469.jpeg',2,986.00,NULL,NULL,'no','IOS',NULL,NULL,NULL,'2019-10-17 20:40:34','2019-11-08 14:46:19',NULL,'no','','7c4a8d09ca3762af61e59520943dc26494f8941b','真的不可思议','2'),(5,'哒哒哒哒哒哒哒哒哒哒','18731612878','https://appraisal.oss-cn-beijing.aliyuncs.com/1571384192040.jpg',7,977.00,'51AD7DE3E43850839C3F4E5F46711492','orZ4m0sXZVcEnyfHZtkWDWJzj4e8','no','Android',NULL,'2000-01-01',NULL,'2019-10-18 10:02:46','2019-11-20 14:56:15','男','no','2088532213868965','7c4a8d09ca3762af61e59520943dc26494f8941b','富通九州','1'),(6,'用户_15033345680','15033345680','https://appraisal.oss-cn-beijing.aliyuncs.com/1571378605424.jpg',0,99955.50,'C64D9C3D90E4B8A367BEBA256C3272BF','orZ4m0nH5F68PEmdmtlv47QD2Kls','no','Android',NULL,NULL,NULL,'2019-10-18 14:02:13','2019-11-08 14:46:35',NULL,'no','2088902332461053','dd5fef9c1c1da1394d6d34b248c51be2ad740840','这是水印啊可口可乐了看看啦啦啦看看了可口可乐','2'),(7,'用户_13522149274','13522149274','https://appraisal.oss-cn-beijing.aliyuncs.com/1573109904479.jpeg',1,1000.00,NULL,'orZ4m0layW8t40wl-KgQkF6xTuGU','no','Android',NULL,NULL,NULL,'2019-10-18 18:36:03','2020-01-10 12:53:23',NULL,'no',NULL,NULL,NULL,'0'),(8,'系统','000000000','https://appraisal.oss-cn-beijing.aliyuncs.com/1573109904479.jpeg',0,1000.00,NULL,NULL,'no',NULL,NULL,NULL,NULL,'2019-10-21 12:38:45','2019-10-21 12:38:47','男','no',NULL,NULL,NULL,'0'),(9,'allll','18701629187','https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1571729958141&di=b93cd34675c563c3ad35550b96430aac&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F69ad7a731f43d4b8729f1a2fbe65c43801ca0f033250-EV1vMf_fw658',1,987.00,NULL,'orZ4m0iKEbYZMSn6lsEQWxMWllrs','no','IOS',NULL,NULL,NULL,'2019-10-25 17:25:13','2020-03-09 19:43:53',NULL,'no','2088502892278243','7c4a8d09ca3762af61e59520943dc26494f8941b','我要开始学习这个丁墨的','2'),(10,'zippo','15711413202','https://appraisal.oss-cn-beijing.aliyuncs.com/1573032688885.jpeg',0,996.00,NULL,NULL,'no','IOS',NULL,'2019-11-06',NULL,'2019-10-28 13:39:36','2019-11-08 09:17:29','男','no',NULL,NULL,'八戒','1'),(11,'用户_13552931975','13552931975','https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1571729958141&di=b93cd34675c563c3ad35550b96430aac&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F69ad7a731f43d4b8729f1a2fbe65c43801ca0f033250-EV1vMf_fw658',0,0.00,NULL,NULL,'no','Android',NULL,NULL,NULL,'2019-11-05 18:28:20','2019-11-05 18:28:20',NULL,'no',NULL,NULL,NULL,'0'),(13,'用户_18910309585','18910309585','https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1571729958141&di=b93cd34675c563c3ad35550b96430aac&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F69ad7a731f43d4b8729f1a2fbe65c43801ca0f033250-EV1vMf_fw658',0,0.00,NULL,'orZ4m0v5K4CuTExpcBicOaI4FBaQ','no','IOS',NULL,NULL,NULL,'2019-11-14 11:55:33','2019-11-14 11:55:33',NULL,'no',NULL,NULL,NULL,'0'),(14,'用户_15011221686','15011221686','https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1571729958141&di=b93cd34675c563c3ad35550b96430aac&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F69ad7a731f43d4b8729f1a2fbe65c43801ca0f033250-EV1vMf_fw658',1,0.00,NULL,NULL,'no','IOS',NULL,NULL,NULL,'2019-11-14 15:57:20','2019-11-14 16:09:12',NULL,'no',NULL,NULL,'服务器销售网。15011221686','2'),(15,'梦想型动物玛丽','18911630448','https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1571729958141&di=b93cd34675c563c3ad35550b96430aac&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F69ad7a731f43d4b8729f1a2fbe65c43801ca0f033250-EV1vMf_fw658',0,0.00,NULL,NULL,'no','Android',NULL,NULL,NULL,'2020-03-10 15:26:40','2020-03-10 15:27:31','女','no',NULL,NULL,NULL,'0');

/*Table structure for table `ap_withdraw` */

DROP TABLE IF EXISTS `ap_withdraw`;

CREATE TABLE `ap_withdraw` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '提现记录表',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `phone_type` varchar(255) DEFAULT NULL COMMENT '手机系统android，ios',
  `account_type` varchar(255) DEFAULT NULL COMMENT '账户类型 (alipay支付宝 ，wechat微信)',
  `account` varchar(255) DEFAULT NULL COMMENT '提现账户',
  `api_type` varchar(255) DEFAULT NULL COMMENT '第三方状态',
  `api_msg` varchar(255) DEFAULT NULL COMMENT '第三方信息',
  `type` varchar(255) DEFAULT NULL COMMENT '状态  审核中/通过/拒绝/成功/失败   not/yes/no/success/failure',
  `money` double DEFAULT NULL COMMENT '体现金额',
  `admin_id` int(11) DEFAULT NULL COMMENT '操作员id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `order_number` varchar(255) DEFAULT NULL COMMENT '订单号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4;

/*Data for the table `ap_withdraw` */

insert  into `ap_withdraw`(`id`,`user_id`,`phone_type`,`account_type`,`account`,`api_type`,`api_msg`,`type`,`money`,`admin_id`,`create_time`,`update_time`,`order_number`) values (31,5,'android','alipay','2088532213868965',NULL,NULL,'no',3,1,'2019-11-07 15:44:48','2019-11-08 15:30:50','20191107154448389020'),(32,3,'ios','alipay','2088822176458377',NULL,NULL,'not',2,NULL,'2019-11-08 11:02:17',NULL,'20191108110217922430'),(33,3,'ios','alipay','2088822176458377',NULL,NULL,'not',1,NULL,'2019-11-08 11:03:58',NULL,'20191108110358609337'),(34,5,'android','alipay','2088532213868965',NULL,NULL,'no',5,1,'2019-11-08 11:20:28','2019-11-08 14:46:39','20191108112028360907'),(35,5,'android','alipay','2088532213868965',NULL,NULL,'no',5,1,'2019-11-08 11:21:13','2019-11-08 14:46:37','20191108112113565356'),(36,6,'android','alipay','2088902332461053',NULL,NULL,'no',888,1,'2019-11-08 11:22:39','2019-11-08 14:46:35','20191108112239703437'),(37,3,'android','alipay','2088822176458377',NULL,NULL,'no',9,1,'2019-11-08 11:48:15','2019-11-08 14:46:21','20191108114815379233'),(38,4,'ios','alipay','8dfb82b5bab247259ccf096f0c3dPX53',NULL,NULL,'no',2,1,'2019-11-08 13:27:21','2019-11-08 14:46:19','20191108132721456823'),(39,4,'ios','alipay','8dfb82b5bab247259ccf096f0c3dPX53',NULL,NULL,'no',1,1,'2019-11-08 13:27:32','2019-11-08 14:46:17','20191108132732853531'),(40,3,'ios','alipay','2b17cb88bb5d4bfa8fe4a2a1ad1fVA37',NULL,NULL,'no',1,1,'2019-11-08 13:48:07','2019-11-08 14:46:14','20191108134807527856'),(41,3,'ios','alipay','2b17cb88bb5d4bfa8fe4a2a1ad1fVA37',NULL,NULL,'no',1,1,'2019-11-08 14:15:02','2019-11-08 14:46:11','20191108141502686762'),(42,3,'ios','alipay','2b17cb88bb5d4bfa8fe4a2a1ad1fVA37',NULL,NULL,'no',7,1,'2019-11-08 14:16:08','2019-11-08 14:46:06','20191108141608132960'),(43,3,'ios','alipay','2b17cb88bb5d4bfa8fe4a2a1ad1fVA37',NULL,NULL,'no',1,1,'2019-11-08 14:18:09','2019-11-08 14:46:08','20191108141809304190'),(44,3,'ios','alipay','a16766526bfa4263a3695323e6eaOB37',NULL,'转账失败','failure',1,1,'2019-11-08 14:52:19','2019-11-08 14:52:27','20191108145219530523'),(45,3,'android','alipay','a16766526bfa4263a3695323e6eaOB37',NULL,'转账失败','failure',2,1,'2019-11-08 14:53:25','2019-11-08 14:53:36','20191108145325839917'),(46,9,'ios','alipay','2088502892278243',NULL,'转账成功','yes',1,1,'2019-11-08 15:10:58','2019-11-08 15:12:42','20191108151058796692'),(47,3,'ios','alipay','2088822176458377',NULL,NULL,'not',1,NULL,'2019-11-25 18:48:30',NULL,'20191125184830638439');

/*Table structure for table `sys_admin` */

DROP TABLE IF EXISTS `sys_admin`;

CREATE TABLE `sys_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员表',
  `phone_number` varchar(55) NOT NULL COMMENT '手机号',
  `name` varchar(55) NOT NULL COMMENT '姓名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `status` varchar(50) NOT NULL DEFAULT 'yes' COMMENT '状态是否启用 yes|no',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `sys_admin` */

insert  into `sys_admin`(`id`,`phone_number`,`name`,`password`,`role_id`,`status`) values (1,'666666','管理员','7c4a8d09ca3762af61e59520943dc26494f8941b',1,'normal');

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单表',
  `menu_name` varchar(256) DEFAULT NULL COMMENT '菜单名称',
  `pid` int(11) DEFAULT NULL COMMENT '父id',
  `url` varchar(700) DEFAULT NULL COMMENT '路径',
  `index` varchar(255) DEFAULT NULL COMMENT '前端路由地址',
  `icon` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`menu_name`,`pid`,`url`,`index`,`icon`) values (1,'用户管理',0,'url','1','el-icon-user'),(2,'用户列表',1,'/user/getUserList,/user/getUserById,/user/updUser,/user/updatewatermarkState,','user','el-icon-user'),(3,'系统设置',0,'url','3','el-icon-setting'),(4,'鉴定管理',0,'url','4','el-icon-menu'),(5,'鉴定列表',4,'/reised/adoptReised,/appraisal/updateAppraisal,/appraisal/apprasialReised,/appraisal/appraisalComment,/appraisal/appraisalDetails,/queryAppraisalType,/reised/queryReised,/appraisal/queryAppraisal,/queryAppraisalType/getAppraisalType,/comment/commentGoods,/comment/deleteCommentById,/appraisal/saveAppState,/user/querySystemUser,/comment/addComment,/appraisal/saveAppReisedState,','appraisal','el-icon-menu'),(6,'管理人员列表',3,'/admin/adminRegister,/admin/changePassword,/admin/updateAdminUser,/admin/delAdmins,/admin/getAdminById,','admin','el-icon-user'),(7,'角色列表',3,'/admin/delRoleByIds,/admin/grantAuthority,/admin/getAllRoleMenu,/admin/addRoleGrantAuthority,','role','el-icon-apple'),(8,'banner管理',3,'/banner/addBanner,/banner/updBanner,/banner/delBanner,/banner/getBannerById,/banner/getBannerList,','banner','el-icon-position'),(9,'版本管理',3,'/Versions/queryVersions,/Versions/deleteVersions,/Versions/addVersions,','version','el-icon-refresh'),(12,'报表',0,'url','12','el-icon-notebook-2'),(13,'统计图表',12,'/statementController/getStatement,','statistical','el-icon-s-data'),(14,'意见反馈',0,'url','14','el-icon-s-comment'),(15,'反馈列表',14,'/feedBack/saveFeedBack,/feedBack/feedBackList,','feedBack','el-icon-s-comment'),(16,'订单列表',12,'/order/getOrderList,','order','el-icon-s-cooperation'),(17,'其他配置',0,'url','5','el-icon-s-grid'),(18,'等级配置',17,'/grade/addGrade,/grade/delGrade,/grade/updGrade,/grade/getGradeBOList,','level','el-icon-s-data'),(19,'参数配置',17,'/keyValue/updKeyValue,/keyValue/getKeyValueList,','param','el-icon-date'),(20,'分类配置',17,'/appraisalType/getAppraisalType,/appraisalType/addAppraisalType,/appraisalType/updAppraisalType,','classify','el-icon-s-flag'),(21,'采纳修改记录',12,'/comment/queryAcceptLog,','appcaptLog','el-icon-document-copy'),(22,'提现审核',0,'url','15','el-icon-view'),(23,'审核列表',22,'/withdraw/getWithdrawBOList,/withdraw/updWithdrawBO,','audit','el-icon-view'),(24,'敏感字配置',3,'/sensitivity/add,/sensitivity/query,/sensitivity/delete,','sensitivity','el-icon-edit'),(25,'二级分类',17,'/appraisalType/addTypeInfo,/appraisalType/updTypeInfo,/appraisalType/queryTypeInfo,','typeInfo','el-icon-office-building');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色表',
  `role_name` varchar(256) DEFAULT NULL COMMENT '角色名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`role_name`,`create_time`,`update_time`) values (1,'管理员','2019-09-03 13:01:46','2019-10-30 12:38:34');

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色菜单表',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=239 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`id`,`role_id`,`menu_id`,`create_time`,`update_time`) values (214,7,15,NULL,NULL),(215,7,12,NULL,NULL),(216,1,1,NULL,NULL),(217,1,2,NULL,NULL),(218,1,3,NULL,NULL),(219,1,6,NULL,NULL),(220,1,7,NULL,NULL),(221,1,8,NULL,NULL),(222,1,9,NULL,NULL),(223,1,24,NULL,NULL),(224,1,4,NULL,NULL),(225,1,5,NULL,NULL),(226,1,12,NULL,NULL),(227,1,13,NULL,NULL),(228,1,16,NULL,NULL),(229,1,21,NULL,NULL),(230,1,14,NULL,NULL),(231,1,15,NULL,NULL),(232,1,17,NULL,NULL),(233,1,18,NULL,NULL),(234,1,19,NULL,NULL),(235,1,20,NULL,NULL),(236,1,25,NULL,NULL),(237,1,22,NULL,NULL),(238,1,23,NULL,NULL);

/*Table structure for table `sys_versions` */

DROP TABLE IF EXISTS `sys_versions`;

CREATE TABLE `sys_versions` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '版本id',
  `title` varchar(11) DEFAULT NULL COMMENT '更新标题',
  `downurl` varchar(255) DEFAULT NULL COMMENT '下载地址',
  `platform` varchar(255) DEFAULT NULL COMMENT '平台',
  `subcontent` text COMMENT '更新内容',
  `type` int(11) DEFAULT NULL COMMENT '1.不需要升级2.不强制升级.3强制升级',
  `versioncode` int(11) DEFAULT NULL COMMENT '版本号（数字形式，越大版本越新）',
  `versionname` varchar(255) DEFAULT NULL COMMENT '版本号',
  `create_time` datetime DEFAULT NULL,
  `admin_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `sys_versions` */

insert  into `sys_versions`(`id`,`title`,`downurl`,`platform`,`subcontent`,`type`,`versioncode`,`versionname`,`create_time`,`admin_id`) values (13,'11.8测试版本','http://appraisal.oss-cn-beijing.aliyuncs.com/app-debug.apk','安卓','优化系统，修改bug',2,1,'11.8测试版本','2019-11-08 14:25:29',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
