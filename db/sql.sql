-- MySQL dump 10.13  Distrib 5.5.28, for Win64 (x86)
--
-- Host: localhost    Database: blog
-- ------------------------------------------------------
-- Server version	5.5.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `createDateTime` datetime NOT NULL,
  `type` int(11) NOT NULL DEFAULT '0',
  `url` varchar(200) DEFAULT NULL,
  `viewCount` int(11) NOT NULL DEFAULT '0',
  `replyCount` int(11) NOT NULL DEFAULT '0',
  `content` text NOT NULL,
  `categorySubId` int(11) DEFAULT NULL,
  `tags` varchar(300) NOT NULL,
  `projectId` int(11) DEFAULT NULL,
  `finish` tinyint(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (2,'我的ABAP命名规范','2012-12-11 17:33:44',0,'',192,0,'<h3>\n	<strong><span style=\"background-color:#FFFFFF;color:#FF9900;line-height:2;\"><span style=\"line-height:1.5;\">目录</span><span></span></span></strong> \n</h3>\n<h4>\n	<ul>\n		<li>\n			<span style=\"color:#337FE5;\"><span style=\"color:#337FE5;\"><a href=\"#g_type\">全局类型</a></span></span> \n		</li>\n		<li>\n			<span style=\"color:#337FE5;\"><a href=\"#g_cns\">全局常量</a></span> \n		</li>\n		<li>\n			<span style=\"color:#337FE5;\"><a href=\"#g_var\">全局变量</a></span> \n		</li>\n		<li>\n			<span style=\"color:#337FE5;\"><span style=\"color:#337FE5;\"><a href=\"#l_var\">局部变量</a></span> </span> \n		</li>\n	</ul>\n</h4>\n<h3>\n	<span style=\"color:#FF9900;\"><a name=\"g_type\"></a>全局类型</span> \n</h3>\n<ol>\n	<li>\n		<span><span>表类型</span><br />\n<pre class=\"prettyprint brush:abap\">TYP_TAB_*</pre>\n</span> \n	</li>\n	<li>\n		<span><span>结构体类型</span><br />\n<pre class=\"prettyprint brush:abap\">TYP_*</pre>\n</span> \n	</li>\n</ol>\n<p>\n	<span></span> \n</p>\n<h3>\n	<span style=\"color:#FF9900;\"><a name=\"g_cns\"></a>全局常量</span> \n</h3>\n<ol>\n	<li>\n		<span>表类型常量<br />\n<pre class=\"prettyprint brush:abap\">CNS_TAB_*</pre>\n</span> \n	</li>\n	<li>\n		<span>结构体类型常量<br />\n<pre class=\"prettyprint brush:abap\">CNS_*</pre>\n</span> \n	</li>\n</ol>\n<h3>\n	<span style=\"color:#FF9900;\"><a name=\"g_var\"></a>全局变量</span> \n</h3>\n<ol>\n	<li>\n		<span>表类型变量<br />\n<pre class=\"prettyprint brush:abap\">TAB_*</pre>\n</span> \n	</li>\n	<li>\n		<span>结构体类型变量<br />\n<span></span> \n<pre class=\"prettyprint brush:abap\">W_*</pre>\n</span> \n	</li>\n</ol>\n<h3>\n	<span style=\"color:#FF9900;\"><a name=\"l_var\"></a>局部变量</span> \n</h3>\n<ol>\n	<li>\n		<span>表类型局部变量<br />\n<pre class=\"prettyprint brush:abap\">L_TAB_*</pre>\n</span> \n	</li>\n	<li>\n		<span>结构体类型变量<br />\n<pre class=\"prettyprint brush:abap\">L_W_*</pre>\n</span> \n	</li>\n</ol>',1,'sap,abap,types',2,1),(4,'如何让选择屏幕的radio放在字段的后面','2012-12-22 15:27:51',0,'',84,0,'<ol>\n	<li>\n		<strong><span style=\"font-size:14px;line-height:2;color:#FF9900;\">sap中选择屏幕中的radiobutton默认放在字段名的前面<br />\n</span></strong>如：<br />\n<pre class=\"prettyprint brush:abap\">REPORT  Z_MIKE_REPORT_TEST.\n\nPARAMETER p0 radiobutton group out.\nPARAMETER p1 radiobutton group out.</pre>\n显示的结果为:<br />\n<img src=\"/img/u/2012/12/13/ZFGD.jpg\" width=\"324\" height=\"90\" alt=\"\" /><br />\n<br />\n	</li>\n	<li>\n		<strong><span style=\"font-size:14px;line-height:2;color:#FF9900;\">下面的代码可以使radiobutton放在字段名的后面</span></strong><br />\n<pre class=\"prettyprint brush:abap\">REPORT  Z_MIKE_REPORT_TEST.\n\nSELECTION-SCREEN BEGIN OF LINE.\n  SELECTION-SCREEN COMMENT 1(10) FOR FIELD p0.\n  PARAMETER p0 radiobutton group out.\nSELECTION-SCREEN END OF LINE.\nSELECTION-SCREEN BEGIN OF LINE.\n  SELECTION-SCREEN COMMENT 1(10) FOR FIELD p1.\n  PARAMETER p1 radiobutton group out.\nSELECTION-SCREEN END OF LINE.</pre>\n<span>显示的结果为:<br />\n</span><img src=\"/img/u/2012/12/13/XLG6.jpg\" width=\"256\" height=\"98\" alt=\"\" /><br />\n	</li>\n</ol>',1,'abap,radiobutton,radio,parameter',3,1),(15,'我的小米','2012-12-22 15:24:54',0,'',78,0,'<p>\n	分享一下我的小米2\n</p>\n<p>\n	<img src=\"/img/u/2012/12/16/BAWV.JPG\" width=\"800\" height=\"598\" alt=\"\" /> \n</p>\n<p>\n	<br />\n</p>\n<p>\n	<img src=\"/img/u/2012/12/16/CMJA.JPG\" width=\"800\" height=\"598\" alt=\"\" /> \n</p>\n<p>\n	<br />\n</p>\n<p>\n	<img src=\"/img/u/2012/12/16/8U3Q.JPG\" width=\"800\" height=\"598\" alt=\"\" /> \n</p>\n<p>\n	<br />\n</p>\n<p>\n	<br />\n</p>',10,'xiaomi,picture',1,1),(38,'ALV 詳解','2013-01-04 14:05:30',0,NULL,8,0,'文件地址:&nbsp;<a href=\"http://pan.baidu.com/share/link?shareid=182774&amp;uk=722834907\" target=\"_blank\">ALV 詳解</a>',15,'alv,abap',9,1);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_sub`
--

DROP TABLE IF EXISTS `category_sub`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_sub` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `pId` int(11) DEFAULT NULL,
  `order` int(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_sub`
--

LOCK TABLES `category_sub` WRITE;
/*!40000 ALTER TABLE `category_sub` DISABLE KEYS */;
INSERT INTO `category_sub` VALUES (1,'基础知识',1,0),(3,'JFINAL',2,0),(4,'FUNCTION详解',1,0),(6,'javascript',3,0),(7,'css',3,0),(8,'谈谈生活',4,0),(9,'软件分享',4,2),(10,'图片展示',4,0),(11,'开发需知',1,0),(12,'代码分享',2,0),(13,'SAPJCO',1,0),(15,'ABAP',6,0),(16,'SAP',6,1);
/*!40000 ALTER TABLE `category_sub` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_super`
--

DROP TABLE IF EXISTS `category_super`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_super` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `order` int(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_super`
--

LOCK TABLES `category_super` WRITE;
/*!40000 ALTER TABLE `category_super` DISABLE KEYS */;
INSERT INTO `category_super` VALUES (1,'ABAP',0),(2,'JAVA',1),(3,'WEB技术',2),(4,'@生活@',3),(6,'资料分享',4);
/*!40000 ALTER TABLE `category_super` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `articleId` int(11) NOT NULL,
  `email` varchar(40) NOT NULL,
  `content` varchar(2000) NOT NULL,
  `dateTime` datetime NOT NULL,
  `nick` varchar(45) NOT NULL,
  `pId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (38,38,'fdsa@qq.com','fdsagasdf','2013-01-05 20:35:15','fdsaf',0);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `content` varchar(200) NOT NULL,
  `dateTime` datetime NOT NULL,
  `nick` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (4,'fdsfsaf@qq.com','cfsadgsafdsafas','2012-12-19 10:50:05','cloud');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `finish` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'无',0),(2,'ABAP基础',0),(3,'报表程序--选择屏幕',0),(4,'FUNCTION详解',0),(5,'ALV细说',0),(6,'ABAP关于时间',0),(7,'DNPRO细节',0),(8,'ALV控件的使用',0),(9,'资料分享',0);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) NOT NULL,
  `password` varchar(80) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'mike',sha1('root'));
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-01-05 21:10:52
