-- MySQL dump 10.13  Distrib 5.1.50, for Win32 (ia32)
--
-- Host: localhost    Database: remoteconsultation
-- ------------------------------------------------------
-- Server version	5.1.50-community

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
-- Table structure for table `consultation`
--

DROP TABLE IF EXISTS `consultation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consultation` (
  `id` varchar(50) NOT NULL COMMENT '记录主键',
  `callerId` varchar(50) DEFAULT NULL COMMENT '发起会诊的用户，引用SystemUser表的记录主键',
  `receiverId` varchar(50) DEFAULT NULL COMMENT '接收会诊的用户，引用SystemUser表的记录主键',
  `patientId` varchar(50) DEFAULT NULL COMMENT '病人的主键，引用Patient表的主键',
  `consulationMode` char(1) DEFAULT '0' COMMENT '0为交互式，1为离线式，如果即有交互又有离线那就是交互式，默认为0',
  `consulationType` char(1) DEFAULT '0' COMMENT '会诊类型， 0为普通会诊，1为急会诊，默认为0',
  `chargeType` char(2) DEFAULT NULL COMMENT '结算付费方式，自费医疗、合作医疗、职工医保等等',
  `description` text COMMENT '会诊描述，对病人的病况进行详细描述。Text类型，可以存放HTML编码的文本。',
  `conObjective` varchar(1024) DEFAULT NULL COMMENT '会诊目的，会诊想要达到的目的，不能超过1024个字符',
  `conFile` varchar(1024) DEFAULT NULL COMMENT '会诊发起发上传的一些附件，此处存放的是附件上传到服务器后的相对路径地址。可以是文本文档、图片或者是和病人相关的其它电子资料，PACS影像文件单独存放到另一张表中，不在这里存放。',
  `sectionOfficeId` varchar(50) DEFAULT NULL COMMENT '要求的会诊科室，引用HospitalSectionOffice表的主键。',
  `requestDoctorId` varchar(50) DEFAULT NULL COMMENT '要求会诊的医生，引用SystemUser表的主键。最多可以要求四个，只有一个的话就只给requestDoctorId赋值。',
  `requestDoctorId2` varchar(50) DEFAULT NULL COMMENT '要求会诊的医生，引用SystemUser表的主键。最多可以要求四个，只有一个的话就只给requestDoctorId赋值。',
  `requestDoctorId3` varchar(50) DEFAULT NULL COMMENT '要求会诊的医生，引用SystemUser表的主键。最多可以要求四个，只有一个的话就只给requestDoctorId赋值。',
  `requestDoctorId4` varchar(50) DEFAULT NULL COMMENT '要求会诊的医生，引用SystemUser表的主键。最多可以要求四个，只有一个的话就只给requestDoctorId赋值。',
  `actualDoctorId` varchar(50) DEFAULT NULL COMMENT '接收方医院管理员实际安排的会医生，引用SystemUser表的主键。最多可以安排四个，只有一个的话就只给actualDoctorId赋值。',
  `actualDoctorId2` varchar(50) DEFAULT NULL COMMENT '接收方医院管理员实际安排的会医生，引用SystemUser表的主键。最多可以要求四个，只有一个的话就只给actualDoctorId赋值。',
  `actualDoctorId3` varchar(50) DEFAULT NULL COMMENT '接收方医院管理员实际安排的会医生，引用SystemUser表的主键。最多可以要求四个，只有一个的话就只给actualDoctorId赋值。',
  `actualDoctorId4` varchar(50) DEFAULT NULL COMMENT '接收方医院管理员实际安排的会医生，引用SystemUser表的主键。最多可以要求四个，只有一个的话就只给actualDoctorId赋值。',
  `requestTime` datetime DEFAULT NULL COMMENT '要求的最晚会诊时间',
  `processRequestTime` datetime DEFAULT NULL COMMENT '接收方管理员处理此会诊请求的时间，就是安排专家、会诊室后保存表单的时间',
  `arrangeConTime` datetime DEFAULT NULL COMMENT '接收方医院管理员安排的会诊时间',
  `beginConTime` datetime DEFAULT NULL COMMENT '实际会诊开始时间',
  `endConTime` datetime DEFAULT NULL COMMENT '实际会诊结束时间',
  `callerConRoomId` varchar(50) DEFAULT NULL COMMENT '发起方安排的会诊室，引用ConsulationRoom表的主键',
  `receiverConRoomId` varchar(50) DEFAULT NULL COMMENT '接收方安排的会诊室，引用ConsulationRoom表的主键',
  `diagnosis` varchar(1024) DEFAULT NULL COMMENT '接受方医生的诊断结果，文本说明',
  `diagnosisFile` varchar(1024) DEFAULT NULL COMMENT '诊断结果附件，也是存服务器的存放路径',
  `notPassResult` varchar(1024) DEFAULT NULL COMMENT '申请未通过原因。申请被驳回的情况，由接收方医院管理员填写',
  `cancelConResult` varchar(1024) DEFAULT NULL COMMENT '取消会诊原因。发起方取消会诊请求的情况，由发起方用户填写',
  `addTime` datetime DEFAULT NULL COMMENT '记录添加时间，也就是会诊单生成的时间',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间，最后一次修改的时间',
  `consultationStatus` char(2) DEFAULT NULL COMMENT '会诊单当前所处的状态',
  PRIMARY KEY (`id`),
  KEY `FK_Consultation_Patient` (`patientId`),
  CONSTRAINT `FK_Consultation_Patient` FOREIGN KEY (`patientId`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='远程会诊请求相关信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consultation`
--

LOCK TABLES `consultation` WRITE;
/*!40000 ALTER TABLE `consultation` DISABLE KEYS */;
/*!40000 ALTER TABLE `consultation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consultationdigitalimage`
--

DROP TABLE IF EXISTS `consultationdigitalimage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consultationdigitalimage` (
  `consultationId` varchar(50) NOT NULL COMMENT '会诊表单，引用consultation表的主键',
  `digitalImageId` varchar(50) NOT NULL COMMENT '数字影像，引用DigitalImage表的主键',
  `addTime` datetime DEFAULT NULL COMMENT '记录添加时间',
  PRIMARY KEY (`consultationId`,`digitalImageId`),
  KEY `FK_ConsultationDigitalImage_DigitalImage` (`digitalImageId`),
  CONSTRAINT `FK_ConsultationDigitalImage_Consultation` FOREIGN KEY (`consultationId`) REFERENCES `consultation` (`id`),
  CONSTRAINT `FK_ConsultationDigitalImage_DigitalImage` FOREIGN KEY (`digitalImageId`) REFERENCES `digitalimage` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='会诊信息和数字影像的中间表，描述某一会诊单所包含的数字影像';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consultationdigitalimage`
--

LOCK TABLES `consultationdigitalimage` WRITE;
/*!40000 ALTER TABLE `consultationdigitalimage` DISABLE KEYS */;
/*!40000 ALTER TABLE `consultationdigitalimage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consultationpatientrecord`
--

DROP TABLE IF EXISTS `consultationpatientrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consultationpatientrecord` (
  `consultationId` varchar(50) NOT NULL COMMENT '会诊表单，引用Consultation表的主键',
  `patientRecordId` varchar(50) NOT NULL COMMENT '病人病历，引用PatientRecord表的主键',
  `addTime` datetime DEFAULT NULL,
  PRIMARY KEY (`consultationId`,`patientRecordId`),
  KEY `FK_ConsultationPatientRecord_Patient` (`patientRecordId`),
  CONSTRAINT `FK_ConsultationPatientRecord_Patient` FOREIGN KEY (`patientRecordId`) REFERENCES `patientrecord` (`id`),
  CONSTRAINT `FK_Consultation_ConsultationPatientRecord` FOREIGN KEY (`consultationId`) REFERENCES `consultation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='描述会诊信息和病人病历的中间表，因为会诊信息表（ConsultationInfo）和病人病历表（PatientRecor';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consultationpatientrecord`
--

LOCK TABLES `consultationpatientrecord` WRITE;
/*!40000 ALTER TABLE `consultationpatientrecord` DISABLE KEYS */;
/*!40000 ALTER TABLE `consultationpatientrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consultationroom`
--

DROP TABLE IF EXISTS `consultationroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consultationroom` (
  `id` varchar(50) NOT NULL COMMENT '记录主键',
  `hospitalId` varchar(50) DEFAULT NULL COMMENT '会诊室所属医院',
  `name` varchar(50) DEFAULT NULL COMMENT '会诊室名称',
  `ip` varchar(50) DEFAULT NULL COMMENT '设备分配的IP地址',
  `DeviceType` char(2) DEFAULT NULL COMMENT '使用的视频通讯设备类型',
  `description` varchar(1024) DEFAULT NULL COMMENT '会诊室描述、备注',
  `addTime` datetime DEFAULT NULL COMMENT '添加时间',
  `updateTime` datetime DEFAULT NULL COMMENT '记录最后一次修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_ConsultationRoom_Hospital` (`hospitalId`),
  CONSTRAINT `FK_ConsultationRoom_Hospital` FOREIGN KEY (`hospitalId`) REFERENCES `hospital` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='医院会诊室相关信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consultationroom`
--

LOCK TABLES `consultationroom` WRITE;
/*!40000 ALTER TABLE `consultationroom` DISABLE KEYS */;
/*!40000 ALTER TABLE `consultationroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dictgeneralsectionoffice`
--

DROP TABLE IF EXISTS `dictgeneralsectionoffice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dictgeneralsectionoffice` (
  `id` varchar(50) NOT NULL COMMENT '记录主键',
  `name` varchar(200) DEFAULT NULL COMMENT '科室名称',
  `level` char(1) DEFAULT NULL,
  `fartherId` varchar(50) DEFAULT NULL COMMENT '此科室的父级科室，引用自身表的主键，如果为一级科室，此处为空',
  `description` varchar(1024) DEFAULT NULL COMMENT '科室的描述、备注信息',
  `addTime` datetime DEFAULT NULL COMMENT '记录添加时间',
  `updateTime` datetime DEFAULT NULL COMMENT '记录修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='通用科室列表，字典表。其中的科室数据从网上整理得出，是比较全的一种';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dictgeneralsectionoffice`
--

LOCK TABLES `dictgeneralsectionoffice` WRITE;
/*!40000 ALTER TABLE `dictgeneralsectionoffice` DISABLE KEYS */;
/*!40000 ALTER TABLE `dictgeneralsectionoffice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dictionary`
--

DROP TABLE IF EXISTS `dictionary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录主键',
  `typeId` char(20) DEFAULT NULL COMMENT '引用DictionaryType表的ID',
  `code` char(20) DEFAULT NULL COMMENT '字典信息编码，在其它数据表中存储的内容及程序中使用的属性就是这个编码',
  `name` varchar(200) DEFAULT NULL COMMENT '字典信息内容，显示内容',
  `description` varchar(1024) DEFAULT NULL COMMENT '字典备注，相关说明',
  `addTime` datetime DEFAULT NULL COMMENT '记录添加时间',
  `updateTime` datetime DEFAULT NULL COMMENT '数据最后一次修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_Dictionary_DictionaryType` (`typeId`),
  CONSTRAINT `FK_Dictionary_DictionaryType` FOREIGN KEY (`typeId`) REFERENCES `dictionarytype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=gb2312 COMMENT='数据字典，通用字典表，此字典没有进行等级设计，不能对字典进行分级。';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dictionary`
--

LOCK TABLES `dictionary` WRITE;
/*!40000 ALTER TABLE `dictionary` DISABLE KEYS */;
INSERT INTO `dictionary` VALUES (1,'01','0','男',NULL,NULL,NULL),(2,'01','1','女',NULL,NULL,NULL),(3,'02','0','已婚',NULL,NULL,NULL),(4,'02','1','未婚',NULL,NULL,NULL),(5,'03','1','身份证',NULL,NULL,NULL),(6,'03','2','社保卡',NULL,NULL,NULL),(7,'03','3','医保卡',NULL,NULL,NULL),(8,'03','4','军官证',NULL,NULL,NULL),(9,'04','0','在线',NULL,NULL,NULL),(10,'04','1','离线',NULL,NULL,NULL),(11,'05','0','活跃用户',NULL,NULL,NULL),(12,'05','1','注销用户',NULL,NULL,NULL),(13,'06','1','一级模块',NULL,NULL,NULL),(14,'06','2','二级模块',NULL,NULL,NULL),(15,'07','01','一',NULL,NULL,NULL),(16,'07','02','二',NULL,NULL,NULL),(17,'07','03','三',NULL,NULL,NULL),(18,'07','04','四',NULL,NULL,NULL),(19,'07','05','五',NULL,NULL,NULL),(20,'07','06','六',NULL,NULL,NULL),(21,'07','07','七',NULL,NULL,NULL),(22,'07','08','八',NULL,NULL,NULL),(23,'07','09','九',NULL,NULL,NULL),(24,'07','10','十',NULL,NULL,NULL),(25,'08','0','共公模块',NULL,NULL,NULL),(26,'08','1','非共公模块',NULL,NULL,NULL),(27,'09','1','一级科室',NULL,NULL,NULL),(28,'09','2','二级科室',NULL,NULL,NULL),(29,'10','00','增加',NULL,NULL,NULL),(30,'10','01','删除',NULL,NULL,NULL),(31,'10','02','修改',NULL,NULL,NULL),(32,'10','03','查询',NULL,NULL,NULL),(33,'11','0','操作成功',NULL,NULL,NULL),(34,'11','1','操作出错',NULL,NULL,NULL),(35,'12','0000','成功',NULL,NULL,NULL),(36,'12','-9999','出错',NULL,NULL,NULL),(37,'13','0','交互',NULL,NULL,NULL),(38,'13','1','离线',NULL,NULL,NULL),(39,'14','0','普通会诊',NULL,NULL,NULL),(40,'14','1','急会诊',NULL,NULL,NULL),(41,'15','01','自费医疗',NULL,NULL,NULL),(42,'15','02','农村合作医疗',NULL,NULL,NULL),(43,'15','03','职工医保',NULL,NULL,NULL),(44,'15','04','居民医保',NULL,NULL,NULL),(45,'15','05','城乡医疗救助',NULL,NULL,NULL),(46,'15','06','商业医疗保险',NULL,NULL,NULL),(47,'15','07','其他社会保险',NULL,NULL,NULL),(48,'16','01','申请已提交',NULL,NULL,NULL),(49,'16','02','申请未通过',NULL,NULL,NULL),(50,'16','03','已安排专家',NULL,NULL,NULL),(51,'16','04','正在视音频',NULL,NULL,NULL),(52,'16','05','视音频结束',NULL,NULL,NULL),(53,'16','06','会诊报告已提交',NULL,NULL,NULL),(54,'16','07','会诊已取消',NULL,NULL,NULL),(55,'16','08','会诊结束确认',NULL,NULL,NULL),(56,'16','09','会诊取消确认',NULL,NULL,NULL),(57,'17','01','V2',NULL,NULL,NULL),(58,'17','02','V2',NULL,NULL,NULL),(59,'18','1','直辖市',NULL,NULL,NULL),(60,'18','2','省',NULL,NULL,NULL),(61,'18','3','自治区',NULL,NULL,NULL),(62,'18','4','特别行政区',NULL,NULL,NULL),(63,'18','5','地级市',NULL,NULL,NULL),(64,'18','6','直辖市下分区',NULL,NULL,NULL),(65,'18','7','县级市','县级市不好区分，可以县级市不好区分，可以都暂时默认为8',NULL,NULL),(66,'18','8','县',NULL,NULL,NULL),(67,'15','08','公费医疗',NULL,NULL,NULL);
/*!40000 ALTER TABLE `dictionary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dictionarytype`
--

DROP TABLE IF EXISTS `dictionarytype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dictionarytype` (
  `id` char(20) NOT NULL COMMENT '记录主键',
  `name` varchar(50) DEFAULT NULL COMMENT '字典类别铝管',
  `description` varchar(1024) DEFAULT NULL COMMENT '字典类别备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='字典类别，对字典信息表（Dictionary）分文别类的进行维护';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dictionarytype`
--

LOCK TABLES `dictionarytype` WRITE;
/*!40000 ALTER TABLE `dictionarytype` DISABLE KEYS */;
INSERT INTO `dictionarytype` VALUES ('01','gender','人员性别'),('02','marrige','是否已婚'),('03','idType','证件类型'),('04','onlineStatus','在线状态'),('05','deleteFlag','是否注销'),('06','moduleLevel','模块级别'),('07','sortField','排序字段'),('08','isPublic','公共模块'),('09','sectionOfficeLevel','科室级别'),('10','operationType','操作类型'),('11','isSuccess','是否成功'),('12','resultCode','结果代码'),('13','consulationMode','会诊方式'),('14','consulationType',' 会诊类型'),('15','chargeType','结算方式'),('16','consultationStatus','会诊状态'),('17','deviceType','设备类型'),('18','regionCategory','地区类型');
/*!40000 ALTER TABLE `dictionarytype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `digitalimage`
--

DROP TABLE IF EXISTS `digitalimage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `digitalimage` (
  `id` varchar(50) NOT NULL COMMENT '记录主键',
  `patientId` varchar(50) DEFAULT NULL COMMENT '影像所属病人，引用Patient表的主键',
  `url` varchar(1024) DEFAULT NULL COMMENT '影像文件存放在服务器上的相对路径',
  `description` varchar(1024) DEFAULT NULL COMMENT '相关描述',
  `addTime` datetime DEFAULT NULL COMMENT '记录添加时间',
  `updateTime` datetime DEFAULT NULL COMMENT '记录最后一次修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='记录数字影像文件信息，这里的数字影像主要是指PACS影像，可以是JPG或DICOM格式的文件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `digitalimage`
--

LOCK TABLES `digitalimage` WRITE;
/*!40000 ALTER TABLE `digitalimage` DISABLE KEYS */;
/*!40000 ALTER TABLE `digitalimage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `functionmodule`
--

DROP TABLE IF EXISTS `functionmodule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `functionmodule` (
  `moduleId` varchar(50) NOT NULL COMMENT '记录主键',
  `moduleName` varchar(50) DEFAULT NULL COMMENT '模块名称',
  `moduleLevel` char(1) DEFAULT NULL COMMENT '模块级别',
  `parentModuleId` varchar(50) DEFAULT NULL COMMENT '父级模块，引用本表的主键，第一级模块此项为NULL',
  `sortField` char(2) DEFAULT NULL COMMENT '排序字段，根据此字段控制功能模块（链接菜单）的显示顺序。',
  `isPublic` char(1) DEFAULT '0' COMMENT '是否为公共模块，0为是，1为否。默认值为1，不是公共模块',
  `description` text COMMENT '模块描述',
  `addTime` datetime DEFAULT NULL COMMENT '记录添加时间',
  `updateTime` datetime DEFAULT NULL COMMENT '记录修改时间',
  PRIMARY KEY (`moduleId`),
  KEY `FK_FunctionModule_FunctionModule` (`parentModuleId`),
  CONSTRAINT `FK_FunctionModule_FunctionModule` FOREIGN KEY (`parentModuleId`) REFERENCES `functionmodule` (`moduleId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='系统功能模块';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `functionmodule`
--

LOCK TABLES `functionmodule` WRITE;
/*!40000 ALTER TABLE `functionmodule` DISABLE KEYS */;
/*!40000 ALTER TABLE `functionmodule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `functionpage`
--

DROP TABLE IF EXISTS `functionpage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `functionpage` (
  `functionModuleId` varchar(50) NOT NULL COMMENT '记录主键，外键',
  `webPageId` varchar(50) NOT NULL COMMENT '页面主键，外键',
  `addTime` datetime DEFAULT NULL,
  PRIMARY KEY (`functionModuleId`,`webPageId`),
  KEY `AK_Identifier_1` (`functionModuleId`,`webPageId`),
  KEY `FK_FunctionPage_WebPage` (`webPageId`),
  CONSTRAINT `FK_FunctionPage_FunctionModule` FOREIGN KEY (`functionModuleId`) REFERENCES `functionmodule` (`moduleId`),
  CONSTRAINT `FK_FunctionPage_WebPage` FOREIGN KEY (`webPageId`) REFERENCES `webpage` (`pageId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='功能模块和系统页面的中间表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `functionpage`
--

LOCK TABLES `functionpage` WRITE;
/*!40000 ALTER TABLE `functionpage` DISABLE KEYS */;
/*!40000 ALTER TABLE `functionpage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hospital`
--

DROP TABLE IF EXISTS `hospital`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hospital` (
  `id` varchar(50) NOT NULL COMMENT '医院记录主键',
  `name` varchar(200) DEFAULT NULL COMMENT '医院名称',
  `address` varchar(500) DEFAULT NULL COMMENT '医院详细地域',
  `telephone` varchar(50) DEFAULT NULL COMMENT '医院联系电话，多个的用逗号分隔',
  `postCode` varchar(50) NOT NULL COMMENT '邮政编码',
  `introduce` text COMMENT '介绍文章，存HTML编码的文档，用于前端页面的展示',
  `description` varchar(500) DEFAULT NULL COMMENT '记录备注说明',
  `regionId` varchar(50) DEFAULT NULL COMMENT '所属地区 引用DictRegion表的主键',
  `addTime` datetime DEFAULT NULL COMMENT '记录添加时间',
  `updateTime` datetime DEFAULT NULL COMMENT '记录最后一次修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_Hospital_DictRegion` (`regionId`),
  CONSTRAINT `FK_Hospital_DictRegion` FOREIGN KEY (`regionId`) REFERENCES `region` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='医院相关信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hospital`
--

LOCK TABLES `hospital` WRITE;
/*!40000 ALTER TABLE `hospital` DISABLE KEYS */;
/*!40000 ALTER TABLE `hospital` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hospitalrelation`
--

DROP TABLE IF EXISTS `hospitalrelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hospitalrelation` (
  `callerId` varchar(50) NOT NULL COMMENT '通讯发起方医院主键',
  `receiverId` varchar(50) DEFAULT NULL COMMENT '接收方医院主键',
  `addTime` datetime DEFAULT NULL COMMENT '记录添加时间',
  PRIMARY KEY (`callerId`),
  KEY `FK_HospitalRelation_Hospital_Receiver` (`receiverId`),
  CONSTRAINT `FK_HospitalRelation_Hospital_Caller` FOREIGN KEY (`callerId`) REFERENCES `hospital` (`id`),
  CONSTRAINT `FK_HospitalRelation_Hospital_Receiver` FOREIGN KEY (`receiverId`) REFERENCES `hospital` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='医院与医院间的通讯关系，有相互通讯通讯关系的医院，都记录在这张表里，系统根据此表来判断两个医院间有无通讯权限';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hospitalrelation`
--

LOCK TABLES `hospitalrelation` WRITE;
/*!40000 ALTER TABLE `hospitalrelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `hospitalrelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hospitalsectionoffice`
--

DROP TABLE IF EXISTS `hospitalsectionoffice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hospitalsectionoffice` (
  `id` varchar(50) NOT NULL COMMENT '记录主键',
  `name` varchar(200) DEFAULT NULL COMMENT '科室名称',
  `level` char(2) DEFAULT NULL,
  `fartherId` varchar(50) DEFAULT NULL COMMENT '此科室的父级科室，引用自身表的主键，如果为一级科室，此处为空',
  `hospitalId` varchar(50) DEFAULT NULL COMMENT '所属医院，引用Hospital表的主键',
  `description` varchar(1024) DEFAULT NULL COMMENT '科室的描述、备注信息',
  `addTime` datetime DEFAULT NULL COMMENT '记录添加时间',
  `updateTime` datetime DEFAULT NULL COMMENT '记录修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_HospitalSectionOffice_Hospital` (`hospitalId`),
  CONSTRAINT `FK_HospitalSectionOffice_Hospital` FOREIGN KEY (`hospitalId`) REFERENCES `hospital` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='记录相关医院的科室信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hospitalsectionoffice`
--

LOCK TABLES `hospitalsectionoffice` WRITE;
/*!40000 ALTER TABLE `hospitalsectionoffice` DISABLE KEYS */;
/*!40000 ALTER TABLE `hospitalsectionoffice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `linkmenu`
--

DROP TABLE IF EXISTS `linkmenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `linkmenu` (
  `menuId` varchar(50) NOT NULL COMMENT '记录主键',
  `menuName` varchar(50) DEFAULT NULL COMMENT '菜单主键',
  `description` text COMMENT '菜单描述',
  `functionModuleId` varchar(50) DEFAULT NULL COMMENT '每一个链接菜单和一个功能模块相对应，没有加外键约束，可用触发器实现功能模块对菜单的级联增、删、改操作',
  `webPageId` varchar(50) DEFAULT NULL COMMENT '链接页面，外键',
  `addTime` datetime DEFAULT NULL COMMENT '记录添加时间',
  `updateTime` datetime DEFAULT NULL COMMENT '记录最后一次修改时间',
  PRIMARY KEY (`menuId`),
  KEY `FK_LinkMenu_WebPage` (`webPageId`),
  CONSTRAINT `FK_LinkMenu_WebPage` FOREIGN KEY (`webPageId`) REFERENCES `webpage` (`pageId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='系统链接菜单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `linkmenu`
--

LOCK TABLES `linkmenu` WRITE;
/*!40000 ALTER TABLE `linkmenu` DISABLE KEYS */;
/*!40000 ALTER TABLE `linkmenu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loginlog`
--

DROP TABLE IF EXISTS `loginlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loginlog` (
  `logId` varchar(50) NOT NULL COMMENT '记录主键',
  `userId` varchar(50) DEFAULT NULL COMMENT '系统用户主键，外键',
  `loginTime` datetime DEFAULT NULL COMMENT '开始进行系统时间',
  `logoutTime` datetime DEFAULT NULL COMMENT '退出系统时间',
  `durationTime` int(11) DEFAULT NULL COMMENT '在线时长，32位整数，单位秒。',
  PRIMARY KEY (`logId`),
  KEY `FK_LoginLog_SystemUser` (`userId`),
  CONSTRAINT `FK_LoginLog_SystemUser` FOREIGN KEY (`userId`) REFERENCES `systemuser` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='登录日志，记录用户登录操作';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loginlog`
--

LOCK TABLES `loginlog` WRITE;
/*!40000 ALTER TABLE `loginlog` DISABLE KEYS */;
/*!40000 ALTER TABLE `loginlog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operationlog`
--

DROP TABLE IF EXISTS `operationlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operationlog` (
  `id` varchar(50) NOT NULL COMMENT '记录主键',
  `systemUserId` varchar(50) DEFAULT NULL COMMENT '登录用户，外键，引用SystemUser表的主键。',
  `operationType` char(1) DEFAULT NULL COMMENT '操作类型，字典，四种动作，1为增加、2为删除、3为修改、4为查询。',
  `operationTable` varchar(50) DEFAULT NULL COMMENT '操作的对象表名',
  `operationContent` varchar(500) DEFAULT NULL COMMENT '操作内容，说明，备注',
  `isSuccess` char(1) DEFAULT NULL COMMENT '操作结果是否成功，0是成功，1是不成功。',
  `resultCode` char(5) DEFAULT NULL COMMENT '操作结果编码',
  `objectRecordId` varchar(50) DEFAULT NULL COMMENT '操作记录的主键',
  `addTime` datetime DEFAULT NULL COMMENT '操作时间',
  `loginLogId` varchar(50) DEFAULT NULL COMMENT '本次操作属于哪次登录，引用LoginLog表的主键。',
  PRIMARY KEY (`id`),
  KEY `FK_OperationLog_SystemUser` (`systemUserId`),
  KEY `FK_FK_OperationLog_LoginLog` (`loginLogId`),
  CONSTRAINT `FK_FK_OperationLog_LoginLog` FOREIGN KEY (`loginLogId`) REFERENCES `loginlog` (`logId`),
  CONSTRAINT `FK_OperationLog_SystemUser` FOREIGN KEY (`systemUserId`) REFERENCES `systemuser` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='记录登录用户的操作日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operationlog`
--

LOCK TABLES `operationlog` WRITE;
/*!40000 ALTER TABLE `operationlog` DISABLE KEYS */;
/*!40000 ALTER TABLE `operationlog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient` (
  `id` varchar(50) NOT NULL COMMENT '记录主键',
  `name` varchar(50) DEFAULT NULL COMMENT '病人姓名',
  `gender` char(1) DEFAULT '0' COMMENT '0为男，1为女，默认为0',
  `marriage` char(1) DEFAULT '0' COMMENT '病人是否已婚，0是已婚，1是未婚，默认为0',
  `born` datetime DEFAULT NULL COMMENT '病人出生日期',
  `idType` char(1) DEFAULT NULL COMMENT '证件类型',
  `idNumber` varchar(50) DEFAULT NULL COMMENT '病人证件号码',
  `hospitalId` varchar(50) DEFAULT NULL COMMENT '病人所属医院，引用Hospital表的主键。',
  `hospitalTime` datetime DEFAULT NULL COMMENT '病人入院（住院）时间',
  `createrId` varchar(50) DEFAULT NULL COMMENT '记录添加者，引用SystemUser表的主键',
  `currentAddress` varchar(500) DEFAULT NULL COMMENT '病人当前住址',
  `contactName` varchar(50) DEFAULT NULL COMMENT '病人家属（联系人）姓名',
  `contactPhone` varchar(50) DEFAULT NULL COMMENT '病人家属（联系人）电话',
  `telephone` varchar(50) DEFAULT NULL COMMENT '病人联系电话',
  `description` varchar(500) DEFAULT NULL COMMENT '病人备注信息',
  `addTime` datetime DEFAULT NULL COMMENT '记录添加时间',
  `updateTime` datetime DEFAULT NULL COMMENT '记录最后一次修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_Patient_Hospital` (`hospitalId`),
  CONSTRAINT `FK_Patient_Hospital` FOREIGN KEY (`hospitalId`) REFERENCES `hospital` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='病人基本信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patientrecord`
--

DROP TABLE IF EXISTS `patientrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patientrecord` (
  `id` varchar(50) NOT NULL COMMENT '记录主键',
  `patientId` varchar(50) DEFAULT NULL COMMENT '所属病人，引用Patient表的主键',
  `representor` varchar(50) DEFAULT NULL COMMENT '病情的陈述者',
  `chiefComplaint` text COMMENT '病人（或陈述人）对病情的主诉',
  `historyPresentIllness` text COMMENT '记述患者病后的全过程，即发生、发展、演变和诊治经过',
  `historyPastIllness` text COMMENT '既往病史的疾病主要涉及心、肺、肝、脾、肾一些重大脏器以及癫痫史、精神病史等；如果做过重大手术，应注明何时、何处做过何种手术，目前状况如何；有残疾状况则要填写具体的残疾情况和自己的残疾证号',
  `historyPersonal` text COMMENT '个人相关经历',
  `historyFamily` text COMMENT '家族病史',
  `physicalExam` text COMMENT '在院的体验情况',
  `inHospitalDiagnostic` varchar(1024) DEFAULT NULL COMMENT '在院的诊断结果',
  `confirmedDiagnostic` varchar(1024) DEFAULT NULL COMMENT '最终证实的诊断结果',
  `createDate` datetime DEFAULT NULL COMMENT '入院检查的时间',
  `description` varchar(1024) DEFAULT NULL COMMENT '病历相关的其它描述、备注',
  `addTime` datetime DEFAULT NULL COMMENT '此记录添加到数据库的时间',
  `updateTime` datetime DEFAULT NULL COMMENT '此条记录最后一次修改的时间',
  PRIMARY KEY (`id`),
  KEY `FK_PatientRecord_Patient` (`patientId`),
  CONSTRAINT `FK_PatientRecord_Patient` FOREIGN KEY (`patientId`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='描述病人入院时的初步会诊信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patientrecord`
--

LOCK TABLES `patientrecord` WRITE;
/*!40000 ALTER TABLE `patientrecord` DISABLE KEYS */;
/*!40000 ALTER TABLE `patientrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `region`
--

DROP TABLE IF EXISTS `region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `region` (
  `id` varchar(50) NOT NULL COMMENT '主键编码',
  `name` varchar(500) DEFAULT NULL COMMENT '地区名称',
  `category` char(2) DEFAULT NULL COMMENT '地区类别字典，用于区分DictRegion表中记录的类型。1是直辖市，2是省，3是自治区，4是特别行政区，5是地级市，6是直辖市下的区，7是县级市（县级市不好区分，可以都暂时默认为7），8是县',
  `fartherId` varchar(50) DEFAULT NULL COMMENT '父级地区，引用父级行政机构的主键。',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='全国省市区列表 字典表，第一级为中国，其父级机构为空，下面为直辖市、省、自治区、特别行政区，然后是地级市、直辖市下的区，';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `region`
--

LOCK TABLES `region` WRITE;
/*!40000 ALTER TABLE `region` DISABLE KEYS */;
INSERT INTO `region` VALUES ('000000','中国','0','0'),('110000','北京市','1','000000'),('110101','东城区','6','110000'),('110102','西城区','6','110000'),('110103','崇文区','6','110000'),('110104','宣武区','6','110000'),('110105','朝阳区','6','110000'),('110106','丰台区','6','110000'),('110107','石景山区','6','110000'),('110108','海淀区','6','110000'),('110109','门头沟区','6','110000'),('110111','房山区','6','110000'),('110112','通州区','6','110000'),('110113','顺义区','6','110000'),('110114','昌平区','6','110000'),('110115','大兴区','6','110000'),('110116','怀柔区','6','110000'),('110117','平谷区','6','110000'),('110228','密云县','8','110200'),('110229','延庆县','8','110200'),('120000','天津市','1','000000'),('120101','和平区','6','120000'),('120102','河东区','6','120000'),('120103','河西区','6','120000'),('120104','南开区','6','120000'),('120105','河北区','6','120000'),('120106','红桥区','6','120000'),('120107','塘沽区','6','120000'),('120108','汉沽区','6','120000'),('120109','大港区','6','120000'),('120110','东丽区','6','120000'),('120111','西青区','6','120000'),('120112','津南区','6','120000'),('120113','北辰区','6','120000'),('120114','武清区','6','120000'),('120115','宝坻区','6','120000'),('120221','宁河县','8','120200'),('120223','静海县','8','120200'),('120225','蓟　县','8','120200'),('130000','河北省','2','000000'),('130100','石家庄市','5','130000'),('130102','长安区','8','130100'),('130103','桥东区','8','130100'),('130104','桥西区','8','130100'),('130105','新华区','8','130100'),('130107','井陉矿区','8','130100'),('130108','裕华区','8','130100'),('130121','井陉县','8','130100'),('130123','正定县','8','130100'),('130124','栾城县','8','130100'),('130125','行唐县','8','130100'),('130126','灵寿县','8','130100'),('130127','高邑县','8','130100'),('130128','深泽县','8','130100'),('130129','赞皇县','8','130100'),('130130','无极县','8','130100'),('130131','平山县','8','130100'),('130132','元氏县','8','130100'),('130133','赵　县','8','130100'),('130181','辛集市','8','130100'),('130182','藁城市','8','130100'),('130183','晋州市','8','130100'),('130184','新乐市','8','130100'),('130185','鹿泉市','8','130100'),('130200','唐山市','5','130000'),('130202','路南区','8','130200'),('130203','路北区','8','130200'),('130204','古冶区','8','130200'),('130205','开平区','8','130200'),('130207','丰南区','8','130200'),('130208','丰润区','8','130200'),('130223','滦　县','8','130200'),('130224','滦南县','8','130200'),('130225','乐亭县','8','130200'),('130227','迁西县','8','130200'),('130229','玉田县','8','130200'),('130230','唐海县','8','130200'),('130281','遵化市','8','130200'),('130283','迁安市','8','130200'),('130300','秦皇岛市','5','130000'),('130302','海港区','8','130300'),('130303','山海关区','8','130300'),('130304','北戴河区','8','130300'),('130321','青龙满族自治县','8','130300'),('130322','昌黎县','8','130300'),('130323','抚宁县','8','130300'),('130324','卢龙县','8','130300'),('130400','邯郸市','5','130000'),('130402','邯山区','8','130400'),('130403','丛台区','8','130400'),('130404','复兴区','8','130400'),('130406','峰峰矿区','8','130400'),('130421','邯郸县','8','130400'),('130423','临漳县','8','130400'),('130424','成安县','8','130400'),('130425','大名县','8','130400'),('130426','涉　县','8','130400'),('130427','磁　县','8','130400'),('130428','肥乡县','8','130400'),('130429','永年县','8','130400'),('130430','邱　县','8','130400'),('130431','鸡泽县','8','130400'),('130432','广平县','8','130400'),('130433','馆陶县','8','130400'),('130434','魏　县','8','130400'),('130435','曲周县','8','130400'),('130481','武安市','8','130400'),('130500','邢台市','5','130000'),('130502','桥东区','8','130500'),('130503','桥西区','8','130500'),('130521','邢台县','8','130500'),('130522','临城县','8','130500'),('130523','内丘县','8','130500'),('130524','柏乡县','8','130500'),('130525','隆尧县','8','130500'),('130526','任　县','8','130500'),('130527','南和县','8','130500'),('130528','宁晋县','8','130500'),('130529','巨鹿县','8','130500'),('130530','新河县','8','130500'),('130531','广宗县','8','130500'),('130532','平乡县','8','130500'),('130533','威　县','8','130500'),('130534','清河县','8','130500'),('130535','临西县','8','130500'),('130581','南宫市','8','130500'),('130582','沙河市','8','130500'),('130600','保定市','5','130000'),('130602','新市区','8','130600'),('130603','北市区','8','130600'),('130604','南市区','8','130600'),('130621','满城县','8','130600'),('130622','清苑县','8','130600'),('130623','涞水县','8','130600'),('130624','阜平县','8','130600'),('130625','徐水县','8','130600'),('130626','定兴县','8','130600'),('130627','唐　县','8','130600'),('130628','高阳县','8','130600'),('130629','容城县','8','130600'),('130630','涞源县','8','130600'),('130631','望都县','8','130600'),('130632','安新县','8','130600'),('130633','易　县','8','130600'),('130634','曲阳县','8','130600'),('130635','蠡　县','8','130600'),('130636','顺平县','8','130600'),('130637','博野县','8','130600'),('130638','雄　县','8','130600'),('130681','涿州市','8','130600'),('130682','定州市','8','130600'),('130683','安国市','8','130600'),('130684','高碑店市','8','130600'),('130700','张家口市','5','130000'),('130702','桥东区','8','130700'),('130703','桥西区','8','130700'),('130705','宣化区','8','130700'),('130706','下花园区','8','130700'),('130721','宣化县','8','130700'),('130722','张北县','8','130700'),('130723','康保县','8','130700'),('130724','沽源县','8','130700'),('130725','尚义县','8','130700'),('130726','蔚　县','8','130700'),('130727','阳原县','8','130700'),('130728','怀安县','8','130700'),('130729','万全县','8','130700'),('130730','怀来县','8','130700'),('130731','涿鹿县','8','130700'),('130732','赤城县','8','130700'),('130733','崇礼县','8','130700'),('130800','承德市','5','130000'),('130802','双桥区','8','130800'),('130803','双滦区','8','130800'),('130804','鹰手营子矿区','8','130800'),('130821','承德县','8','130800'),('130822','兴隆县','8','130800'),('130823','平泉县','8','130800'),('130824','滦平县','8','130800'),('130825','隆化县','8','130800'),('130826','丰宁满族自治县','8','130800'),('130827','宽城满族自治县','8','130800'),('130828','围场满族蒙古族自治县','8','130800'),('130900','沧州市','5','130000'),('130902','新华区','8','130900'),('130903','运河区','8','130900'),('130921','沧　县','8','130900'),('130922','青　县','8','130900'),('130923','东光县','8','130900'),('130924','海兴县','8','130900'),('130925','盐山县','8','130900'),('130926','肃宁县','8','130900'),('130927','南皮县','8','130900'),('130928','吴桥县','8','130900'),('130929','献　县','8','130900'),('130930','孟村回族自治县','8','130900'),('130981','泊头市','8','130900'),('130982','任丘市','8','130900'),('130983','黄骅市','8','130900'),('130984','河间市','8','130900'),('131000','廊坊市','5','130000'),('131002','安次区','8','131000'),('131003','广阳区','8','131000'),('131022','固安县','8','131000'),('131023','永清县','8','131000'),('131024','香河县','8','131000'),('131025','大城县','8','131000'),('131026','文安县','8','131000'),('131028','大厂回族自治县','8','131000'),('131081','霸州市','8','131000'),('131082','三河市','8','131000'),('131100','衡水市','5','130000'),('131102','桃城区','8','131100'),('131121','枣强县','8','131100'),('131122','武邑县','8','131100'),('131123','武强县','8','131100'),('131124','饶阳县','8','131100'),('131125','安平县','8','131100'),('131126','故城县','8','131100'),('131127','景　县','8','131100'),('131128','阜城县','8','131100'),('131181','冀州市','8','131100'),('131182','深州市','8','131100'),('140000','山西省','2','000000'),('140100','太原市','5','140000'),('140105','小店区','8','140100'),('140106','迎泽区','8','140100'),('140107','杏花岭区','8','140100'),('140108','尖草坪区','8','140100'),('140109','万柏林区','8','140100'),('140110','晋源区','8','140100'),('140121','清徐县','8','140100'),('140122','阳曲县','8','140100'),('140123','娄烦县','8','140100'),('140181','古交市','8','140100'),('140200','大同市','5','140000'),('140202','城　区','8','140200'),('140203','矿　区','8','140200'),('140211','南郊区','8','140200'),('140212','新荣区','8','140200'),('140221','阳高县','8','140200'),('140222','天镇县','8','140200'),('140223','广灵县','8','140200'),('140224','灵丘县','8','140200'),('140225','浑源县','8','140200'),('140226','左云县','8','140200'),('140227','大同县','8','140200'),('140300','阳泉市','5','140000'),('140302','城　区','8','140300'),('140303','矿　区','8','140300'),('140311','郊　区','8','140300'),('140321','平定县','8','140300'),('140322','盂　县','8','140300'),('140400','长治市','5','140000'),('140402','城　区','8','140400'),('140411','郊　区','8','140400'),('140421','长治县','8','140400'),('140423','襄垣县','8','140400'),('140424','屯留县','8','140400'),('140425','平顺县','8','140400'),('140426','黎城县','8','140400'),('140427','壶关县','8','140400'),('140428','长子县','8','140400'),('140429','武乡县','8','140400'),('140430','沁　县','8','140400'),('140431','沁源县','8','140400'),('140481','潞城市','8','140400'),('140500','晋城市','5','140000'),('140502','城　区','8','140500'),('140521','沁水县','8','140500'),('140522','阳城县','8','140500'),('140524','陵川县','8','140500'),('140525','泽州县','8','140500'),('140581','高平市','8','140500'),('140600','朔州市','5','140000'),('140602','朔城区','8','140600'),('140603','平鲁区','8','140600'),('140621','山阴县','8','140600'),('140622','应　县','8','140600'),('140623','右玉县','8','140600'),('140624','怀仁县','8','140600'),('140700','晋中市','5','140000'),('140702','榆次区','8','140700'),('140721','榆社县','8','140700'),('140722','左权县','8','140700'),('140723','和顺县','8','140700'),('140724','昔阳县','8','140700'),('140725','寿阳县','8','140700'),('140726','太谷县','8','140700'),('140727','祁　县','8','140700'),('140728','平遥县','8','140700'),('140729','灵石县','8','140700'),('140781','介休市','8','140700'),('140800','运城市','5','140000'),('140802','盐湖区','8','140800'),('140821','临猗县','8','140800'),('140822','万荣县','8','140800'),('140823','闻喜县','8','140800'),('140824','稷山县','8','140800'),('140825','新绛县','8','140800'),('140826','绛　县','8','140800'),('140827','垣曲县','8','140800'),('140828','夏　县','8','140800'),('140829','平陆县','8','140800'),('140830','芮城县','8','140800'),('140881','永济市','8','140800'),('140882','河津市','8','140800'),('140900','忻州市','5','140000'),('140902','忻府区','8','140900'),('140921','定襄县','8','140900'),('140922','五台县','8','140900'),('140923','代　县','8','140900'),('140924','繁峙县','8','140900'),('140925','宁武县','8','140900'),('140926','静乐县','8','140900'),('140927','神池县','8','140900'),('140928','五寨县','8','140900'),('140929','岢岚县','8','140900'),('140930','河曲县','8','140900'),('140931','保德县','8','140900'),('140932','偏关县','8','140900'),('140981','原平市','8','140900'),('141000','临汾市','5','140000'),('141002','尧都区','8','141000'),('141021','曲沃县','8','141000'),('141022','翼城县','8','141000'),('141023','襄汾县','8','141000'),('141024','洪洞县','8','141000'),('141025','古　县','8','141000'),('141026','安泽县','8','141000'),('141027','浮山县','8','141000'),('141028','吉　县','8','141000'),('141029','乡宁县','8','141000'),('141030','大宁县','8','141000'),('141031','隰　县','8','141000'),('141032','永和县','8','141000'),('141033','蒲　县','8','141000'),('141034','汾西县','8','141000'),('141081','侯马市','8','141000'),('141082','霍州市','8','141000'),('141100','吕梁市','5','140000'),('141102','离石区','8','141100'),('141121','文水县','8','141100'),('141122','交城县','8','141100'),('141123','兴　县','8','141100'),('141124','临　县','8','141100'),('141125','柳林县','8','141100'),('141126','石楼县','8','141100'),('141127','岚　县','8','141100'),('141128','方山县','8','141100'),('141129','中阳县','8','141100'),('141130','交口县','8','141100'),('141181','孝义市','8','141100'),('141182','汾阳市','8','141100'),('150000','内蒙古自治区','3','000000'),('150100','呼和浩特市','5','150000'),('150102','新城区','8','150100'),('150103','回民区','8','150100'),('150104','玉泉区','8','150100'),('150105','赛罕区','8','150100'),('150121','土默特左旗','8','150100'),('150122','托克托县','8','150100'),('150123','和林格尔县','8','150100'),('150124','清水河县','8','150100'),('150125','武川县','8','150100'),('150200','包头市','5','150000'),('150202','东河区','8','150200'),('150203','昆都仑区','8','150200'),('150204','青山区','8','150200'),('150205','石拐区','8','150200'),('150206','白云矿区','8','150200'),('150207','九原区','8','150200'),('150221','土默特右旗','8','150200'),('150222','固阳县','8','150200'),('150223','达尔罕茂明安联合旗','8','150200'),('150300','乌海市','5','150000'),('150302','海勃湾区','8','150300'),('150303','海南区','8','150300'),('150304','乌达区','8','150300'),('150400','赤峰市','5','150000'),('150402','红山区','8','150400'),('150403','元宝山区','8','150400'),('150404','松山区','8','150400'),('150421','阿鲁科尔沁旗','8','150400'),('150422','巴林左旗','8','150400'),('150423','巴林右旗','8','150400'),('150424','林西县','8','150400'),('150425','克什克腾旗','8','150400'),('150426','翁牛特旗','8','150400'),('150428','喀喇沁旗','8','150400'),('150429','宁城县','8','150400'),('150430','敖汉旗','8','150400'),('150500','通辽市','5','150000'),('150502','科尔沁区','8','150500'),('150521','科尔沁左翼中旗','8','150500'),('150522','科尔沁左翼后旗','8','150500'),('150523','开鲁县','8','150500'),('150524','库伦旗','8','150500'),('150525','奈曼旗','8','150500'),('150526','扎鲁特旗','8','150500'),('150581','霍林郭勒市','8','150500'),('150600','鄂尔多斯市','5','150000'),('150602','东胜区','8','150600'),('150621','达拉特旗','8','150600'),('150622','准格尔旗','8','150600'),('150623','鄂托克前旗','8','150600'),('150624','鄂托克旗','8','150600'),('150625','杭锦旗','8','150600'),('150626','乌审旗','8','150600'),('150627','伊金霍洛旗','8','150600'),('150700','呼伦贝尔市','5','150000'),('150702','海拉尔区','8','150700'),('150721','阿荣旗','8','150700'),('150722','莫力达瓦达斡尔族自治旗','8','150700'),('150723','鄂伦春自治旗','8','150700'),('150724','鄂温克族自治旗','8','150700'),('150725','陈巴尔虎旗','8','150700'),('150726','新巴尔虎左旗','8','150700'),('150727','新巴尔虎右旗','8','150700'),('150781','满洲里市','8','150700'),('150782','牙克石市','8','150700'),('150783','扎兰屯市','8','150700'),('150784','额尔古纳市','8','150700'),('150785','根河市','8','150700'),('150800','巴彦淖尔市','5','150000'),('150802','临河区','8','150800'),('150821','五原县','8','150800'),('150822','磴口县','8','150800'),('150823','乌拉特前旗','8','150800'),('150824','乌拉特中旗','8','150800'),('150825','乌拉特后旗','8','150800'),('150826','杭锦后旗','8','150800'),('150900','乌兰察布市','5','150000'),('150902','集宁区','8','150900'),('150921','卓资县','8','150900'),('150922','化德县','8','150900'),('150923','商都县','8','150900'),('150924','兴和县','8','150900'),('150925','凉城县','8','150900'),('150926','察哈尔右翼前旗','8','150900'),('150927','察哈尔右翼中旗','8','150900'),('150928','察哈尔右翼后旗','8','150900'),('150929','四子王旗','8','150900'),('150981','丰镇市','8','150900'),('152200','兴安盟','5','150000'),('152201','乌兰浩特市','8','152200'),('152202','阿尔山市','8','152200'),('152221','科尔沁右翼前旗','8','152200'),('152222','科尔沁右翼中旗','8','152200'),('152223','扎赉特旗','8','152200'),('152224','突泉县','8','152200'),('152500','锡林郭勒盟','5','150000'),('152501','二连浩特市','8','152500'),('152502','锡林浩特市','8','152500'),('152522','阿巴嘎旗','8','152500'),('152523','苏尼特左旗','8','152500'),('152524','苏尼特右旗','8','152500'),('152525','东乌珠穆沁旗','8','152500'),('152526','西乌珠穆沁旗','8','152500'),('152527','太仆寺旗','8','152500'),('152528','镶黄旗','8','152500'),('152529','正镶白旗','8','152500'),('152530','正蓝旗','8','152500'),('152531','多伦县','8','152500'),('152900','阿拉善盟','5','150000'),('152921','阿拉善左旗','8','152900'),('152922','阿拉善右旗','8','152900'),('152923','额济纳旗','8','152900'),('210000','辽宁省','2','000000'),('210100','沈阳市','5','210000'),('210102','和平区','8','210100'),('210103','沈河区','8','210100'),('210104','大东区','8','210100'),('210105','皇姑区','8','210100'),('210106','铁西区','8','210100'),('210111','苏家屯区','8','210100'),('210112','东陵区','8','210100'),('210113','新城子区','8','210100'),('210114','于洪区','8','210100'),('210122','辽中县','8','210100'),('210123','康平县','8','210100'),('210124','法库县','8','210100'),('210181','新民市','8','210100'),('210200','大连市','5','210000'),('210202','中山区','8','210200'),('210203','西岗区','8','210200'),('210204','沙河口区','8','210200'),('210211','甘井子区','8','210200'),('210212','旅顺口区','8','210200'),('210213','金州区','8','210200'),('210224','长海县','8','210200'),('210281','瓦房店市','8','210200'),('210282','普兰店市','8','210200'),('210283','庄河市','8','210200'),('210300','鞍山市','5','210000'),('210302','铁东区','8','210300'),('210303','铁西区','8','210300'),('210304','立山区','8','210300'),('210311','千山区','8','210300'),('210321','台安县','8','210300'),('210323','岫岩满族自治县','8','210300'),('210381','海城市','8','210300'),('210400','抚顺市','5','210000'),('210402','新抚区','8','210400'),('210403','东洲区','8','210400'),('210404','望花区','8','210400'),('210411','顺城区','8','210400'),('210421','抚顺县','8','210400'),('210422','新宾满族自治县','8','210400'),('210423','清原满族自治县','8','210400'),('210500','本溪市','5','210000'),('210502','平山区','8','210500'),('210503','溪湖区','8','210500'),('210504','明山区','8','210500'),('210505','南芬区','8','210500'),('210521','本溪满族自治县','8','210500'),('210522','桓仁满族自治县','8','210500'),('210600','丹东市','5','210000'),('210602','元宝区','8','210600'),('210603','振兴区','8','210600'),('210604','振安区','8','210600'),('210624','宽甸满族自治县','8','210600'),('210681','东港市','8','210600'),('210682','凤城市','8','210600'),('210700','锦州市','5','210000'),('210702','古塔区','8','210700'),('210703','凌河区','8','210700'),('210711','太和区','8','210700'),('210726','黑山县','8','210700'),('210727','义　县','8','210700'),('210781','凌海市','8','210700'),('210782','北宁市','8','210700'),('210800','营口市','5','210000'),('210802','站前区','8','210800'),('210803','西市区','8','210800'),('210804','鲅鱼圈区','8','210800'),('210811','老边区','8','210800'),('210881','盖州市','8','210800'),('210882','大石桥市','8','210800'),('210900','阜新市','5','210000'),('210902','海州区','8','210900'),('210903','新邱区','8','210900'),('210904','太平区','8','210900'),('210905','清河门区','8','210900'),('210911','细河区','8','210900'),('210921','阜新蒙古族自治县','8','210900'),('210922','彰武县','8','210900'),('211000','辽阳市','5','210000'),('211002','白塔区','8','211000'),('211003','文圣区','8','211000'),('211004','宏伟区','8','211000'),('211005','弓长岭区','8','211000'),('211011','太子河区','8','211000'),('211021','辽阳县','8','211000'),('211081','灯塔市','8','211000'),('211100','盘锦市','5','210000'),('211102','双台子区','8','211100'),('211103','兴隆台区','8','211100'),('211121','大洼县','8','211100'),('211122','盘山县','8','211100'),('211200','铁岭市','5','210000'),('211202','银州区','8','211200'),('211204','清河区','8','211200'),('211221','铁岭县','8','211200'),('211223','西丰县','8','211200'),('211224','昌图县','8','211200'),('211281','调兵山市','8','211200'),('211282','开原市','8','211200'),('211300','朝阳市','5','210000'),('211302','双塔区','8','211300'),('211303','龙城区','8','211300'),('211321','朝阳县','8','211300'),('211322','建平县','8','211300'),('211324','喀喇沁左翼蒙古族自治县','8','211300'),('211381','北票市','8','211300'),('211382','凌源市','8','211300'),('211400','葫芦岛市','5','210000'),('211402','连山区','8','211400'),('211403','龙港区','8','211400'),('211404','南票区','8','211400'),('211421','绥中县','8','211400'),('211422','建昌县','8','211400'),('211481','兴城市','8','211400'),('220000','吉林省','2','000000'),('220100','长春市','5','220000'),('220102','南关区','8','220100'),('220103','宽城区','8','220100'),('220104','朝阳区','8','220100'),('220105','二道区','8','220100'),('220106','绿园区','8','220100'),('220112','双阳区','8','220100'),('220122','农安县','8','220100'),('220181','九台市','8','220100'),('220182','榆树市','8','220100'),('220183','德惠市','8','220100'),('220200','吉林市','5','220000'),('220202','昌邑区','8','220200'),('220203','龙潭区','8','220200'),('220204','船营区','8','220200'),('220211','丰满区','8','220200'),('220221','永吉县','8','220200'),('220281','蛟河市','8','220200'),('220282','桦甸市','8','220200'),('220283','舒兰市','8','220200'),('220284','磐石市','8','220200'),('220300','四平市','5','220000'),('220302','铁西区','8','220300'),('220303','铁东区','8','220300'),('220322','梨树县','8','220300'),('220323','伊通满族自治县','8','220300'),('220381','公主岭市','8','220300'),('220382','双辽市','8','220300'),('220400','辽源市','5','220000'),('220402','龙山区','8','220400'),('220403','西安区','8','220400'),('220421','东丰县','8','220400'),('220422','东辽县','8','220400'),('220500','通化市','5','220000'),('220502','东昌区','8','220500'),('220503','二道江区','8','220500'),('220521','通化县','8','220500'),('220523','辉南县','8','220500'),('220524','柳河县','8','220500'),('220581','梅河口市','8','220500'),('220582','集安市','8','220500'),('220600','白山市','5','220000'),('220602','八道江区','8','220600'),('220621','抚松县','8','220600'),('220622','靖宇县','8','220600'),('220623','长白朝鲜族自治县','8','220600'),('220625','江源县','8','220600'),('220681','临江市','8','220600'),('220700','松原市','5','220000'),('220702','宁江区','8','220700'),('220721','前郭尔罗斯蒙古族自治县','8','220700'),('220722','长岭县','8','220700'),('220723','乾安县','8','220700'),('220724','扶余县','8','220700'),('220800','白城市','5','220000'),('220802','洮北区','8','220800'),('220821','镇赉县','8','220800'),('220822','通榆县','8','220800'),('220881','洮南市','8','220800'),('220882','大安市','8','220800'),('222400','延边朝鲜族自治州','5','220000'),('222401','延吉市','8','222400'),('222402','图们市','8','222400'),('222403','敦化市','8','222400'),('222404','珲春市','8','222400'),('222405','龙井市','8','222400'),('222406','和龙市','8','222400'),('222424','汪清县','8','222400'),('222426','安图县','8','222400'),('230000','黑龙江省','2','000000'),('230100','哈尔滨市','5','230000'),('230102','道里区','8','230100'),('230103','南岗区','8','230100'),('230104','道外区','8','230100'),('230106','香坊区','8','230100'),('230107','动力区','8','230100'),('230108','平房区','8','230100'),('230109','松北区','8','230100'),('230111','呼兰区','8','230100'),('230123','依兰县','8','230100'),('230124','方正县','8','230100'),('230125','宾　县','8','230100'),('230126','巴彦县','8','230100'),('230127','木兰县','8','230100'),('230128','通河县','8','230100'),('230129','延寿县','8','230100'),('230181','阿城市','8','230100'),('230182','双城市','8','230100'),('230183','尚志市','8','230100'),('230184','五常市','8','230100'),('230200','齐齐哈尔市','5','230000'),('230202','龙沙区','8','230200'),('230203','建华区','8','230200'),('230204','铁锋区','8','230200'),('230205','昂昂溪区','8','230200'),('230206','富拉尔基区','8','230200'),('230207','碾子山区','8','230200'),('230208','梅里斯达斡尔族区','8','230200'),('230221','龙江县','8','230200'),('230223','依安县','8','230200'),('230224','泰来县','8','230200'),('230225','甘南县','8','230200'),('230227','富裕县','8','230200'),('230229','克山县','8','230200'),('230230','克东县','8','230200'),('230231','拜泉县','8','230200'),('230281','讷河市','8','230200'),('230300','鸡西市','5','230000'),('230302','鸡冠区','8','230300'),('230303','恒山区','8','230300'),('230304','滴道区','8','230300'),('230305','梨树区','8','230300'),('230306','城子河区','8','230300'),('230307','麻山区','8','230300'),('230321','鸡东县','8','230300'),('230381','虎林市','8','230300'),('230382','密山市','8','230300'),('230400','鹤岗市','5','230000'),('230402','向阳区','8','230400'),('230403','工农区','8','230400'),('230404','南山区','8','230400'),('230405','兴安区','8','230400'),('230406','东山区','8','230400'),('230407','兴山区','8','230400'),('230421','萝北县','8','230400'),('230422','绥滨县','8','230400'),('230500','双鸭山市','5','230000'),('230502','尖山区','8','230500'),('230503','岭东区','8','230500'),('230505','四方台区','8','230500'),('230506','宝山区','8','230500'),('230521','集贤县','8','230500'),('230522','友谊县','8','230500'),('230523','宝清县','8','230500'),('230524','饶河县','8','230500'),('230600','大庆市','5','230000'),('230602','萨尔图区','8','230600'),('230603','龙凤区','8','230600'),('230604','让胡路区','8','230600'),('230605','红岗区','8','230600'),('230606','大同区','8','230600'),('230621','肇州县','8','230600'),('230622','肇源县','8','230600'),('230623','林甸县','8','230600'),('230624','杜尔伯特蒙古族自治县','8','230600'),('230700','伊春市','5','230000'),('230702','伊春区','8','230700'),('230703','南岔区','8','230700'),('230704','友好区','8','230700'),('230705','西林区','8','230700'),('230706','翠峦区','8','230700'),('230707','新青区','8','230700'),('230708','美溪区','8','230700'),('230709','金山屯区','8','230700'),('230710','五营区','8','230700'),('230711','乌马河区','8','230700'),('230712','汤旺河区','8','230700'),('230713','带岭区','8','230700'),('230714','乌伊岭区','8','230700'),('230715','红星区','8','230700'),('230716','上甘岭区','8','230700'),('230722','嘉荫县','8','230700'),('230781','铁力市','8','230700'),('230800','佳木斯市','5','230000'),('230802','永红区','8','230800'),('230803','向阳区','8','230800'),('230804','前进区','8','230800'),('230805','东风区','8','230800'),('230811','郊　区','8','230800'),('230822','桦南县','8','230800'),('230826','桦川县','8','230800'),('230828','汤原县','8','230800'),('230833','抚远县','8','230800'),('230881','同江市','8','230800'),('230882','富锦市','8','230800'),('230900','七台河市','5','230000'),('230902','新兴区','8','230900'),('230903','桃山区','8','230900'),('230904','茄子河区','8','230900'),('230921','勃利县','8','230900'),('231000','牡丹江市','5','230000'),('231002','东安区','8','231000'),('231003','阳明区','8','231000'),('231004','爱民区','8','231000'),('231005','西安区','8','231000'),('231024','东宁县','8','231000'),('231025','林口县','8','231000'),('231081','绥芬河市','8','231000'),('231083','海林市','8','231000'),('231084','宁安市','8','231000'),('231085','穆棱市','8','231000'),('231100','黑河市','5','230000'),('231102','爱辉区','8','231100'),('231121','嫩江县','8','231100'),('231123','逊克县','8','231100'),('231124','孙吴县','8','231100'),('231181','北安市','8','231100'),('231182','五大连池市','8','231100'),('231200','绥化市','5','230000'),('231202','北林区','8','231200'),('231221','望奎县','8','231200'),('231222','兰西县','8','231200'),('231223','青冈县','8','231200'),('231224','庆安县','8','231200'),('231225','明水县','8','231200'),('231226','绥棱县','8','231200'),('231281','安达市','8','231200'),('231282','肇东市','8','231200'),('231283','海伦市','8','231200'),('232700','大兴安岭地区','5','230000'),('232721','呼玛县','8','232700'),('232722','塔河县','8','232700'),('232723','漠河县','8','232700'),('310000','上海市','1','000000'),('310101','黄浦区','6','310000'),('310103','卢湾区','6','310000'),('310104','徐汇区','6','310000'),('310105','长宁区','6','310000'),('310106','静安区','6','310000'),('310107','普陀区','6','310000'),('310108','闸北区','6','310000'),('310109','虹口区','6','310000'),('310110','杨浦区','6','310000'),('310112','闵行区','6','310000'),('310113','宝山区','6','310000'),('310114','嘉定区','6','310000'),('310115','浦东新区','6','310000'),('310116','金山区','6','310000'),('310117','松江区','6','310000'),('310118','青浦区','6','310000'),('310119','南汇区','6','310000'),('310120','奉贤区','6','310000'),('310230','崇明县','8','310200'),('320000','江苏省','2','000000'),('320100','南京市','5','320000'),('320102','玄武区','8','320100'),('320103','白下区','8','320100'),('320104','秦淮区','8','320100'),('320105','建邺区','8','320100'),('320106','鼓楼区','8','320100'),('320107','下关区','8','320100'),('320111','浦口区','8','320100'),('320113','栖霞区','8','320100'),('320114','雨花台区','8','320100'),('320115','江宁区','8','320100'),('320116','六合区','8','320100'),('320124','溧水县','8','320100'),('320125','高淳县','8','320100'),('320200','无锡市','5','320000'),('320202','崇安区','8','320200'),('320203','南长区','8','320200'),('320204','北塘区','8','320200'),('320205','锡山区','8','320200'),('320206','惠山区','8','320200'),('320211','滨湖区','8','320200'),('320281','江阴市','8','320200'),('320282','宜兴市','8','320200'),('320300','徐州市','5','320000'),('320302','鼓楼区','8','320300'),('320303','云龙区','8','320300'),('320304','九里区','8','320300'),('320305','贾汪区','8','320300'),('320311','泉山区','8','320300'),('320321','丰　县','8','320300'),('320322','沛　县','8','320300'),('320323','铜山县','8','320300'),('320324','睢宁县','8','320300'),('320381','新沂市','8','320300'),('320382','邳州市','8','320300'),('320400','常州市','5','320000'),('320402','天宁区','8','320400'),('320404','钟楼区','8','320400'),('320405','戚墅堰区','8','320400'),('320411','新北区','8','320400'),('320412','武进区','8','320400'),('320481','溧阳市','8','320400'),('320482','金坛市','8','320400'),('320500','苏州市','5','320000'),('320502','沧浪区','8','320500'),('320503','平江区','8','320500'),('320504','金阊区','8','320500'),('320505','虎丘区','8','320500'),('320506','吴中区','8','320500'),('320507','相城区','8','320500'),('320581','常熟市','8','320500'),('320582','张家港市','8','320500'),('320583','昆山市','8','320500'),('320584','吴江市','8','320500'),('320585','太仓市','8','320500'),('320600','南通市','5','320000'),('320602','崇川区','8','320600'),('320611','港闸区','8','320600'),('320621','海安县','8','320600'),('320623','如东县','8','320600'),('320681','启东市','8','320600'),('320682','如皋市','8','320600'),('320683','通州市','8','320600'),('320684','海门市','8','320600'),('320700','连云港市','5','320000'),('320703','连云区','8','320700'),('320705','新浦区','8','320700'),('320706','海州区','8','320700'),('320721','赣榆县','8','320700'),('320722','东海县','8','320700'),('320723','灌云县','8','320700'),('320724','灌南县','8','320700'),('320800','淮安市','5','320000'),('320802','清河区','8','320800'),('320803','楚州区','8','320800'),('320804','淮阴区','8','320800'),('320811','清浦区','8','320800'),('320826','涟水县','8','320800'),('320829','洪泽县','8','320800'),('320830','盱眙县','8','320800'),('320831','金湖县','8','320800'),('320900','盐城市','5','320000'),('320902','亭湖区','8','320900'),('320903','盐都区','8','320900'),('320921','响水县','8','320900'),('320922','滨海县','8','320900'),('320923','阜宁县','8','320900'),('320924','射阳县','8','320900'),('320925','建湖县','8','320900'),('320981','东台市','8','320900'),('320982','大丰市','8','320900'),('321000','扬州市','5','320000'),('321002','广陵区','8','321000'),('321003','邗江区','8','321000'),('321011','郊　区','8','321000'),('321023','宝应县','8','321000'),('321081','仪征市','8','321000'),('321084','高邮市','8','321000'),('321088','江都市','8','321000'),('321100','镇江市','5','320000'),('321102','京口区','8','321100'),('321111','润州区','8','321100'),('321112','丹徒区','8','321100'),('321181','丹阳市','8','321100'),('321182','扬中市','8','321100'),('321183','句容市','8','321100'),('321200','泰州市','5','320000'),('321202','海陵区','8','321200'),('321203','高港区','8','321200'),('321281','兴化市','8','321200'),('321282','靖江市','8','321200'),('321283','泰兴市','8','321200'),('321284','姜堰市','8','321200'),('321300','宿迁市','5','320000'),('321302','宿城区','8','321300'),('321311','宿豫区','8','321300'),('321322','沭阳县','8','321300'),('321323','泗阳县','8','321300'),('321324','泗洪县','8','321300'),('330000','浙江省','2','000000'),('330100','杭州市','5','330000'),('330102','上城区','8','330100'),('330103','下城区','8','330100'),('330104','江干区','8','330100'),('330105','拱墅区','8','330100'),('330106','西湖区','8','330100'),('330108','滨江区','8','330100'),('330109','萧山区','8','330100'),('330110','余杭区','8','330100'),('330122','桐庐县','8','330100'),('330127','淳安县','8','330100'),('330182','建德市','8','330100'),('330183','富阳市','8','330100'),('330185','临安市','8','330100'),('330200','宁波市','5','330000'),('330203','海曙区','8','330200'),('330204','江东区','8','330200'),('330205','江北区','8','330200'),('330206','北仑区','8','330200'),('330211','镇海区','8','330200'),('330212','鄞州区','8','330200'),('330225','象山县','8','330200'),('330226','宁海县','8','330200'),('330281','余姚市','8','330200'),('330282','慈溪市','8','330200'),('330283','奉化市','8','330200'),('330300','温州市','5','330000'),('330302','鹿城区','8','330300'),('330303','龙湾区','8','330300'),('330304','瓯海区','8','330300'),('330322','洞头县','8','330300'),('330324','永嘉县','8','330300'),('330326','平阳县','8','330300'),('330327','苍南县','8','330300'),('330328','文成县','8','330300'),('330329','泰顺县','8','330300'),('330381','瑞安市','8','330300'),('330382','乐清市','8','330300'),('330400','嘉兴市','5','330000'),('330402','秀城区','8','330400'),('330411','秀洲区','8','330400'),('330421','嘉善县','8','330400'),('330424','海盐县','8','330400'),('330481','海宁市','8','330400'),('330482','平湖市','8','330400'),('330483','桐乡市','8','330400'),('330500','湖州市','5','330000'),('330502','吴兴区','8','330500'),('330503','南浔区','8','330500'),('330521','德清县','8','330500'),('330522','长兴县','8','330500'),('330523','安吉县','8','330500'),('330600','绍兴市','5','330000'),('330602','越城区','8','330600'),('330621','绍兴县','8','330600'),('330624','新昌县','8','330600'),('330681','诸暨市','8','330600'),('330682','上虞市','8','330600'),('330683','嵊州市','8','330600'),('330700','金华市','5','330000'),('330702','婺城区','8','330700'),('330703','金东区','8','330700'),('330723','武义县','8','330700'),('330726','浦江县','8','330700'),('330727','磐安县','8','330700'),('330781','兰溪市','8','330700'),('330782','义乌市','8','330700'),('330783','东阳市','8','330700'),('330784','永康市','8','330700'),('330800','衢州市','5','330000'),('330802','柯城区','8','330800'),('330803','衢江区','8','330800'),('330822','常山县','8','330800'),('330824','开化县','8','330800'),('330825','龙游县','8','330800'),('330881','江山市','8','330800'),('330900','舟山市','5','330000'),('330902','定海区','8','330900'),('330903','普陀区','8','330900'),('330921','岱山县','8','330900'),('330922','嵊泗县','8','330900'),('331000','台州市','5','330000'),('331002','椒江区','8','331000'),('331003','黄岩区','8','331000'),('331004','路桥区','8','331000'),('331021','玉环县','8','331000'),('331022','三门县','8','331000'),('331023','天台县','8','331000'),('331024','仙居县','8','331000'),('331081','温岭市','8','331000'),('331082','临海市','8','331000'),('331100','丽水市','5','330000'),('331102','莲都区','8','331100'),('331121','青田县','8','331100'),('331122','缙云县','8','331100'),('331123','遂昌县','8','331100'),('331124','松阳县','8','331100'),('331125','云和县','8','331100'),('331126','庆元县','8','331100'),('331127','景宁畲族自治县','8','331100'),('331181','龙泉市','8','331100'),('340000','安徽省','2','000000'),('340100','合肥市','5','340000'),('340102','瑶海区','8','340100'),('340103','庐阳区','8','340100'),('340104','蜀山区','8','340100'),('340111','包河区','8','340100'),('340121','长丰县','8','340100'),('340122','肥东县','8','340100'),('340123','肥西县','8','340100'),('340200','芜湖市','5','340000'),('340202','镜湖区','8','340200'),('340203','马塘区','8','340200'),('340204','新芜区','8','340200'),('340207','鸠江区','8','340200'),('340221','芜湖县','8','340200'),('340222','繁昌县','8','340200'),('340223','南陵县','8','340200'),('340300','蚌埠市','5','340000'),('340302','龙子湖区','8','340300'),('340303','蚌山区','8','340300'),('340304','禹会区','8','340300'),('340311','淮上区','8','340300'),('340321','怀远县','8','340300'),('340322','五河县','8','340300'),('340323','固镇县','8','340300'),('340400','淮南市','5','340000'),('340402','大通区','8','340400'),('340403','田家庵区','8','340400'),('340404','谢家集区','8','340400'),('340405','八公山区','8','340400'),('340406','潘集区','8','340400'),('340421','凤台县','8','340400'),('340500','马鞍山市','5','340000'),('340502','金家庄区','8','340500'),('340503','花山区','8','340500'),('340504','雨山区','8','340500'),('340521','当涂县','8','340500'),('340600','淮北市','5','340000'),('340602','杜集区','8','340600'),('340603','相山区','8','340600'),('340604','烈山区','8','340600'),('340621','濉溪县','8','340600'),('340700','铜陵市','5','340000'),('340702','铜官山区','8','340700'),('340703','狮子山区','8','340700'),('340711','郊　区','8','340700'),('340721','铜陵县','8','340700'),('340800','安庆市','5','340000'),('340802','迎江区','8','340800'),('340803','大观区','8','340800'),('340811','郊　区','8','340800'),('340822','怀宁县','8','340800'),('340823','枞阳县','8','340800'),('340824','潜山县','8','340800'),('340825','太湖县','8','340800'),('340826','宿松县','8','340800'),('340827','望江县','8','340800'),('340828','岳西县','8','340800'),('340881','桐城市','8','340800'),('341000','黄山市','5','340000'),('341002','屯溪区','8','341000'),('341003','黄山区','8','341000'),('341004','徽州区','8','341000'),('341021','歙　县','8','341000'),('341022','休宁县','8','341000'),('341023','黟　县','8','341000'),('341024','祁门县','8','341000'),('341100','滁州市','5','340000'),('341102','琅琊区','8','341100'),('341103','南谯区','8','341100'),('341122','来安县','8','341100'),('341124','全椒县','8','341100'),('341125','定远县','8','341100'),('341126','凤阳县','8','341100'),('341181','天长市','8','341100'),('341182','明光市','8','341100'),('341200','阜阳市','5','340000'),('341202','颍州区','8','341200'),('341203','颍东区','8','341200'),('341204','颍泉区','8','341200'),('341221','临泉县','8','341200'),('341222','太和县','8','341200'),('341225','阜南县','8','341200'),('341226','颍上县','8','341200'),('341282','界首市','8','341200'),('341300','宿州市','5','340000'),('341302','墉桥区','8','341300'),('341321','砀山县','8','341300'),('341322','萧　县','8','341300'),('341323','灵璧县','8','341300'),('341324','泗　县','8','341300'),('341400','巢湖市','5','340000'),('341402','居巢区','8','341400'),('341421','庐江县','8','341400'),('341422','无为县','8','341400'),('341423','含山县','8','341400'),('341424','和　县','8','341400'),('341500','六安市','5','340000'),('341502','金安区','8','341500'),('341503','裕安区','8','341500'),('341521','寿　县','8','341500'),('341522','霍邱县','8','341500'),('341523','舒城县','8','341500'),('341524','金寨县','8','341500'),('341525','霍山县','8','341500'),('341600','亳州市','5','340000'),('341602','谯城区','8','341600'),('341621','涡阳县','8','341600'),('341622','蒙城县','8','341600'),('341623','利辛县','8','341600'),('341700','池州市','5','340000'),('341702','贵池区','8','341700'),('341721','东至县','8','341700'),('341722','石台县','8','341700'),('341723','青阳县','8','341700'),('341800','宣城市','5','340000'),('341802','宣州区','8','341800'),('341821','郎溪县','8','341800'),('341822','广德县','8','341800'),('341823','泾　县','8','341800'),('341824','绩溪县','8','341800'),('341825','旌德县','8','341800'),('341881','宁国市','8','341800'),('350000','福建省','2','000000'),('350100','福州市','5','350000'),('350102','鼓楼区','8','350100'),('350103','台江区','8','350100'),('350104','仓山区','8','350100'),('350105','马尾区','8','350100'),('350111','晋安区','8','350100'),('350121','闽侯县','8','350100'),('350122','连江县','8','350100'),('350123','罗源县','8','350100'),('350124','闽清县','8','350100'),('350125','永泰县','8','350100'),('350128','平潭县','8','350100'),('350181','福清市','8','350100'),('350182','长乐市','8','350100'),('350200','厦门市','5','350000'),('350203','思明区','8','350200'),('350205','海沧区','8','350200'),('350206','湖里区','8','350200'),('350211','集美区','8','350200'),('350212','同安区','8','350200'),('350213','翔安区','8','350200'),('350300','莆田市','5','350000'),('350302','城厢区','8','350300'),('350303','涵江区','8','350300'),('350304','荔城区','8','350300'),('350305','秀屿区','8','350300'),('350322','仙游县','8','350300'),('350400','三明市','5','350000'),('350402','梅列区','8','350400'),('350403','三元区','8','350400'),('350421','明溪县','8','350400'),('350423','清流县','8','350400'),('350424','宁化县','8','350400'),('350425','大田县','8','350400'),('350426','尤溪县','8','350400'),('350427','沙　县','8','350400'),('350428','将乐县','8','350400'),('350429','泰宁县','8','350400'),('350430','建宁县','8','350400'),('350481','永安市','8','350400'),('350500','泉州市','5','350000'),('350502','鲤城区','8','350500'),('350503','丰泽区','8','350500'),('350504','洛江区','8','350500'),('350505','泉港区','8','350500'),('350521','惠安县','8','350500'),('350524','安溪县','8','350500'),('350525','永春县','8','350500'),('350526','德化县','8','350500'),('350527','金门县','8','350500'),('350581','石狮市','8','350500'),('350582','晋江市','8','350500'),('350583','南安市','8','350500'),('350600','漳州市','5','350000'),('350602','芗城区','8','350600'),('350603','龙文区','8','350600'),('350622','云霄县','8','350600'),('350623','漳浦县','8','350600'),('350624','诏安县','8','350600'),('350625','长泰县','8','350600'),('350626','东山县','8','350600'),('350627','南靖县','8','350600'),('350628','平和县','8','350600'),('350629','华安县','8','350600'),('350681','龙海市','8','350600'),('350700','南平市','5','350000'),('350702','延平区','8','350700'),('350721','顺昌县','8','350700'),('350722','浦城县','8','350700'),('350723','光泽县','8','350700'),('350724','松溪县','8','350700'),('350725','政和县','8','350700'),('350781','邵武市','8','350700'),('350782','武夷山市','8','350700'),('350783','建瓯市','8','350700'),('350784','建阳市','8','350700'),('350800','龙岩市','5','350000'),('350802','新罗区','8','350800'),('350821','长汀县','8','350800'),('350822','永定县','8','350800'),('350823','上杭县','8','350800'),('350824','武平县','8','350800'),('350825','连城县','8','350800'),('350881','漳平市','8','350800'),('350900','宁德市','5','350000'),('350902','蕉城区','8','350900'),('350921','霞浦县','8','350900'),('350922','古田县','8','350900'),('350923','屏南县','8','350900'),('350924','寿宁县','8','350900'),('350925','周宁县','8','350900'),('350926','柘荣县','8','350900'),('350981','福安市','8','350900'),('350982','福鼎市','8','350900'),('360000','江西省','2','000000'),('360100','南昌市','5','360000'),('360102','东湖区','8','360100'),('360103','西湖区','8','360100'),('360104','青云谱区','8','360100'),('360105','湾里区','8','360100'),('360111','青山湖区','8','360100'),('360121','南昌县','8','360100'),('360122','新建县','8','360100'),('360123','安义县','8','360100'),('360124','进贤县','8','360100'),('360200','景德镇市','5','360000'),('360202','昌江区','8','360200'),('360203','珠山区','8','360200'),('360222','浮梁县','8','360200'),('360281','乐平市','8','360200'),('360300','萍乡市','5','360000'),('360302','安源区','8','360300'),('360313','湘东区','8','360300'),('360321','莲花县','8','360300'),('360322','上栗县','8','360300'),('360323','芦溪县','8','360300'),('360400','九江市','5','360000'),('360402','庐山区','8','360400'),('360403','浔阳区','8','360400'),('360421','九江县','8','360400'),('360423','武宁县','8','360400'),('360424','修水县','8','360400'),('360425','永修县','8','360400'),('360426','德安县','8','360400'),('360427','星子县','8','360400'),('360428','都昌县','8','360400'),('360429','湖口县','8','360400'),('360430','彭泽县','8','360400'),('360481','瑞昌市','8','360400'),('360500','新余市','5','360000'),('360502','渝水区','8','360500'),('360521','分宜县','8','360500'),('360600','鹰潭市','5','360000'),('360602','月湖区','8','360600'),('360622','余江县','8','360600'),('360681','贵溪市','8','360600'),('360700','赣州市','5','360000'),('360702','章贡区','8','360700'),('360721','赣　县','8','360700'),('360722','信丰县','8','360700'),('360723','大余县','8','360700'),('360724','上犹县','8','360700'),('360725','崇义县','8','360700'),('360726','安远县','8','360700'),('360727','龙南县','8','360700'),('360728','定南县','8','360700'),('360729','全南县','8','360700'),('360730','宁都县','8','360700'),('360731','于都县','8','360700'),('360732','兴国县','8','360700'),('360733','会昌县','8','360700'),('360734','寻乌县','8','360700'),('360735','石城县','8','360700'),('360781','瑞金市','8','360700'),('360782','南康市','8','360700'),('360800','吉安市','5','360000'),('360802','吉州区','8','360800'),('360803','青原区','8','360800'),('360821','吉安县','8','360800'),('360822','吉水县','8','360800'),('360823','峡江县','8','360800'),('360824','新干县','8','360800'),('360825','永丰县','8','360800'),('360826','泰和县','8','360800'),('360827','遂川县','8','360800'),('360828','万安县','8','360800'),('360829','安福县','8','360800'),('360830','永新县','8','360800'),('360881','井冈山市','8','360800'),('360900','宜春市','5','360000'),('360902','袁州区','8','360900'),('360921','奉新县','8','360900'),('360922','万载县','8','360900'),('360923','上高县','8','360900'),('360924','宜丰县','8','360900'),('360925','靖安县','8','360900'),('360926','铜鼓县','8','360900'),('360981','丰城市','8','360900'),('360982','樟树市','8','360900'),('360983','高安市','8','360900'),('361000','抚州市','5','360000'),('361002','临川区','8','361000'),('361021','南城县','8','361000'),('361022','黎川县','8','361000'),('361023','南丰县','8','361000'),('361024','崇仁县','8','361000'),('361025','乐安县','8','361000'),('361026','宜黄县','8','361000'),('361027','金溪县','8','361000'),('361028','资溪县','8','361000'),('361029','东乡县','8','361000'),('361030','广昌县','8','361000'),('361100','上饶市','5','360000'),('361102','信州区','8','361100'),('361121','上饶县','8','361100'),('361122','广丰县','8','361100'),('361123','玉山县','8','361100'),('361124','铅山县','8','361100'),('361125','横峰县','8','361100'),('361126','弋阳县','8','361100'),('361127','余干县','8','361100'),('361128','鄱阳县','8','361100'),('361129','万年县','8','361100'),('361130','婺源县','8','361100'),('361181','德兴市','8','361100'),('370000','山东省','2','000000'),('370100','济南市','5','370000'),('370102','历下区','8','370100'),('370103','市中区','8','370100'),('370104','槐荫区','8','370100'),('370105','天桥区','8','370100'),('370112','历城区','8','370100'),('370113','长清区','8','370100'),('370124','平阴县','8','370100'),('370125','济阳县','8','370100'),('370126','商河县','8','370100'),('370181','章丘市','8','370100'),('370200','青岛市','5','370000'),('370202','市南区','8','370200'),('370203','市北区','8','370200'),('370205','四方区','8','370200'),('370211','黄岛区','8','370200'),('370212','崂山区','8','370200'),('370213','李沧区','8','370200'),('370214','城阳区','8','370200'),('370281','胶州市','8','370200'),('370282','即墨市','8','370200'),('370283','平度市','8','370200'),('370284','胶南市','8','370200'),('370285','莱西市','8','370200'),('370300','淄博市','5','370000'),('370302','淄川区','8','370300'),('370303','张店区','8','370300'),('370304','博山区','8','370300'),('370305','临淄区','8','370300'),('370306','周村区','8','370300'),('370321','桓台县','8','370300'),('370322','高青县','8','370300'),('370323','沂源县','8','370300'),('370400','枣庄市','5','370000'),('370402','市中区','8','370400'),('370403','薛城区','8','370400'),('370404','峄城区','8','370400'),('370405','台儿庄区','8','370400'),('370406','山亭区','8','370400'),('370481','滕州市','8','370400'),('370500','东营市','5','370000'),('370502','东营区','8','370500'),('370503','河口区','8','370500'),('370521','垦利县','8','370500'),('370522','利津县','8','370500'),('370523','广饶县','8','370500'),('370600','烟台市','5','370000'),('370602','芝罘区','8','370600'),('370611','福山区','8','370600'),('370612','牟平区','8','370600'),('370613','莱山区','8','370600'),('370634','长岛县','8','370600'),('370681','龙口市','8','370600'),('370682','莱阳市','8','370600'),('370683','莱州市','8','370600'),('370684','蓬莱市','8','370600'),('370685','招远市','8','370600'),('370686','栖霞市','8','370600'),('370687','海阳市','8','370600'),('370700','潍坊市','5','370000'),('370702','潍城区','8','370700'),('370703','寒亭区','8','370700'),('370704','坊子区','8','370700'),('370705','奎文区','8','370700'),('370724','临朐县','8','370700'),('370725','昌乐县','8','370700'),('370781','青州市','8','370700'),('370782','诸城市','8','370700'),('370783','寿光市','8','370700'),('370784','安丘市','8','370700'),('370785','高密市','8','370700'),('370786','昌邑市','8','370700'),('370800','济宁市','5','370000'),('370802','市中区','8','370800'),('370811','任城区','8','370800'),('370826','微山县','8','370800'),('370827','鱼台县','8','370800'),('370828','金乡县','8','370800'),('370829','嘉祥县','8','370800'),('370830','汶上县','8','370800'),('370831','泗水县','8','370800'),('370832','梁山县','8','370800'),('370881','曲阜市','8','370800'),('370882','兖州市','8','370800'),('370883','邹城市','8','370800'),('370900','泰安市','5','370000'),('370902','泰山区','8','370900'),('370903','岱岳区','8','370900'),('370921','宁阳县','8','370900'),('370923','东平县','8','370900'),('370982','新泰市','8','370900'),('370983','肥城市','8','370900'),('371000','威海市','5','370000'),('371002','环翠区','8','371000'),('371081','文登市','8','371000'),('371082','荣成市','8','371000'),('371083','乳山市','8','371000'),('371100','日照市','5','370000'),('371102','东港区','8','371100'),('371103','岚山区','8','371100'),('371121','五莲县','8','371100'),('371122','莒　县','8','371100'),('371200','莱芜市','5','370000'),('371202','莱城区','8','371200'),('371203','钢城区','8','371200'),('371300','临沂市','5','370000'),('371302','兰山区','8','371300'),('371311','罗庄区','8','371300'),('371312','河东区','8','371300'),('371321','沂南县','8','371300'),('371322','郯城县','8','371300'),('371323','沂水县','8','371300'),('371324','苍山县','8','371300'),('371325','费　县','8','371300'),('371326','平邑县','8','371300'),('371327','莒南县','8','371300'),('371328','蒙阴县','8','371300'),('371329','临沭县','8','371300'),('371400','德州市','5','370000'),('371402','德城区','8','371400'),('371421','陵　县','8','371400'),('371422','宁津县','8','371400'),('371423','庆云县','8','371400'),('371424','临邑县','8','371400'),('371425','齐河县','8','371400'),('371426','平原县','8','371400'),('371427','夏津县','8','371400'),('371428','武城县','8','371400'),('371481','乐陵市','8','371400'),('371482','禹城市','8','371400'),('371500','聊城市','5','370000'),('371502','东昌府区','8','371500'),('371521','阳谷县','8','371500'),('371522','莘　县','8','371500'),('371523','茌平县','8','371500'),('371524','东阿县','8','371500'),('371525','冠　县','8','371500'),('371526','高唐县','8','371500'),('371581','临清市','8','371500'),('371600','滨州市','5','370000'),('371602','滨城区','8','371600'),('371621','惠民县','8','371600'),('371622','阳信县','8','371600'),('371623','无棣县','8','371600'),('371624','沾化县','8','371600'),('371625','博兴县','8','371600'),('371626','邹平县','8','371600'),('371700','荷泽市','5','370000'),('371702','牡丹区','8','371700'),('371721','曹　县','8','371700'),('371722','单　县','8','371700'),('371723','成武县','8','371700'),('371724','巨野县','8','371700'),('371725','郓城县','8','371700'),('371726','鄄城县','8','371700'),('371727','定陶县','8','371700'),('371728','东明县','8','371700'),('410000','河南省','2','000000'),('410100','郑州市','5','410000'),('410102','中原区','8','410100'),('410103','二七区','8','410100'),('410104','管城回族区','8','410100'),('410105','金水区','8','410100'),('410106','上街区','8','410100'),('410108','邙山区','8','410100'),('410122','中牟县','8','410100'),('410181','巩义市','8','410100'),('410182','荥阳市','8','410100'),('410183','新密市','8','410100'),('410184','新郑市','8','410100'),('410185','登封市','8','410100'),('410200','开封市','5','410000'),('410202','龙亭区','8','410200'),('410203','顺河回族区','8','410200'),('410204','鼓楼区','8','410200'),('410205','南关区','8','410200'),('410211','郊　区','8','410200'),('410221','杞　县','8','410200'),('410222','通许县','8','410200'),('410223','尉氏县','8','410200'),('410224','开封县','8','410200'),('410225','兰考县','8','410200'),('410300','洛阳市','5','410000'),('410302','老城区','8','410300'),('410303','西工区','8','410300'),('410304','廛河回族区','8','410300'),('410305','涧西区','8','410300'),('410306','吉利区','8','410300'),('410307','洛龙区','8','410300'),('410322','孟津县','8','410300'),('410323','新安县','8','410300'),('410324','栾川县','8','410300'),('410325','嵩　县','8','410300'),('410326','汝阳县','8','410300'),('410327','宜阳县','8','410300'),('410328','洛宁县','8','410300'),('410329','伊川县','8','410300'),('410381','偃师市','8','410300'),('410400','平顶山市','5','410000'),('410402','新华区','8','410400'),('410403','卫东区','8','410400'),('410404','石龙区','8','410400'),('410411','湛河区','8','410400'),('410421','宝丰县','8','410400'),('410422','叶　县','8','410400'),('410423','鲁山县','8','410400'),('410425','郏　县','8','410400'),('410481','舞钢市','8','410400'),('410482','汝州市','8','410400'),('410500','安阳市','5','410000'),('410502','文峰区','8','410500'),('410503','北关区','8','410500'),('410505','殷都区','8','410500'),('410506','龙安区','8','410500'),('410522','安阳县','8','410500'),('410523','汤阴县','8','410500'),('410526','滑　县','8','410500'),('410527','内黄县','8','410500'),('410581','林州市','8','410500'),('410600','鹤壁市','5','410000'),('410602','鹤山区','8','410600'),('410603','山城区','8','410600'),('410611','淇滨区','8','410600'),('410621','浚　县','8','410600'),('410622','淇　县','8','410600'),('410700','新乡市','5','410000'),('410702','红旗区','8','410700'),('410703','卫滨区','8','410700'),('410704','凤泉区','8','410700'),('410711','牧野区','8','410700'),('410721','新乡县','8','410700'),('410724','获嘉县','8','410700'),('410725','原阳县','8','410700'),('410726','延津县','8','410700'),('410727','封丘县','8','410700'),('410728','长垣县','8','410700'),('410781','卫辉市','8','410700'),('410782','辉县市','8','410700'),('410800','焦作市','5','410000'),('410802','解放区','8','410800'),('410803','中站区','8','410800'),('410804','马村区','8','410800'),('410811','山阳区','8','410800'),('410821','修武县','8','410800'),('410822','博爱县','8','410800'),('410823','武陟县','8','410800'),('410825','温　县','8','410800'),('410881','济源市','8','410800'),('410882','沁阳市','8','410800'),('410883','孟州市','8','410800'),('410900','濮阳市','5','410000'),('410902','华龙区','8','410900'),('410922','清丰县','8','410900'),('410923','南乐县','8','410900'),('410926','范　县','8','410900'),('410927','台前县','8','410900'),('410928','濮阳县','8','410900'),('411000','许昌市','5','410000'),('411002','魏都区','8','411000'),('411023','许昌县','8','411000'),('411024','鄢陵县','8','411000'),('411025','襄城县','8','411000'),('411081','禹州市','8','411000'),('411082','长葛市','8','411000'),('411100','漯河市','5','410000'),('411102','源汇区','8','411100'),('411103','郾城区','8','411100'),('411104','召陵区','8','411100'),('411121','舞阳县','8','411100'),('411122','临颍县','8','411100'),('411200','三门峡市','5','410000'),('411202','湖滨区','8','411200'),('411221','渑池县','8','411200'),('411222','陕　县','8','411200'),('411224','卢氏县','8','411200'),('411281','义马市','8','411200'),('411282','灵宝市','8','411200'),('411300','南阳市','5','410000'),('411302','宛城区','8','411300'),('411303','卧龙区','8','411300'),('411321','南召县','8','411300'),('411322','方城县','8','411300'),('411323','西峡县','8','411300'),('411324','镇平县','8','411300'),('411325','内乡县','8','411300'),('411326','淅川县','8','411300'),('411327','社旗县','8','411300'),('411328','唐河县','8','411300'),('411329','新野县','8','411300'),('411330','桐柏县','8','411300'),('411381','邓州市','8','411300'),('411400','商丘市','5','410000'),('411402','梁园区','8','411400'),('411403','睢阳区','8','411400'),('411421','民权县','8','411400'),('411422','睢　县','8','411400'),('411423','宁陵县','8','411400'),('411424','柘城县','8','411400'),('411425','虞城县','8','411400'),('411426','夏邑县','8','411400'),('411481','永城市','8','411400'),('411500','信阳市','5','410000'),('411502','师河区','8','411500'),('411503','平桥区','8','411500'),('411521','罗山县','8','411500'),('411522','光山县','8','411500'),('411523','新　县','8','411500'),('411524','商城县','8','411500'),('411525','固始县','8','411500'),('411526','潢川县','8','411500'),('411527','淮滨县','8','411500'),('411528','息　县','8','411500'),('411600','周口市','5','410000'),('411602','川汇区','8','411600'),('411621','扶沟县','8','411600'),('411622','西华县','8','411600'),('411623','商水县','8','411600'),('411624','沈丘县','8','411600'),('411625','郸城县','8','411600'),('411626','淮阳县','8','411600'),('411627','太康县','8','411600'),('411628','鹿邑县','8','411600'),('411681','项城市','8','411600'),('411700','驻马店市','5','410000'),('411702','驿城区','8','411700'),('411721','西平县','8','411700'),('411722','上蔡县','8','411700'),('411723','平舆县','8','411700'),('411724','正阳县','8','411700'),('411725','确山县','8','411700'),('411726','泌阳县','8','411700'),('411727','汝南县','8','411700'),('411728','遂平县','8','411700'),('411729','新蔡县','8','411700'),('420000','湖北省','2','000000'),('420100','武汉市','5','420000'),('420102','江岸区','8','420100'),('420103','江汉区','8','420100'),('420104','乔口区','8','420100'),('420105','汉阳区','8','420100'),('420106','武昌区','8','420100'),('420107','青山区','8','420100'),('420111','洪山区','8','420100'),('420112','东西湖区','8','420100'),('420113','汉南区','8','420100'),('420114','蔡甸区','8','420100'),('420115','江夏区','8','420100'),('420116','黄陂区','8','420100'),('420117','新洲区','8','420100'),('420200','黄石市','5','420000'),('420202','黄石港区','8','420200'),('420203','西塞山区','8','420200'),('420204','下陆区','8','420200'),('420205','铁山区','8','420200'),('420222','阳新县','8','420200'),('420281','大冶市','8','420200'),('420300','十堰市','5','420000'),('420302','茅箭区','8','420300'),('420303','张湾区','8','420300'),('420321','郧　县','8','420300'),('420322','郧西县','8','420300'),('420323','竹山县','8','420300'),('420324','竹溪县','8','420300'),('420325','房　县','8','420300'),('420381','丹江口市','8','420300'),('420500','宜昌市','5','420000'),('420502','西陵区','8','420500'),('420503','伍家岗区','8','420500'),('420504','点军区','8','420500'),('420505','','8','420500'),('420506','夷陵区','8','420500'),('420525','远安县','8','420500'),('420526','兴山县','8','420500'),('420527','秭归县','8','420500'),('420528','长阳土家族自治县','8','420500'),('420529','五峰土家族自治县','8','420500'),('420581','宜都市','8','420500'),('420582','当阳市','8','420500'),('420583','枝江市','8','420500'),('420600','襄樊市','5','420000'),('420602','襄城区','8','420600'),('420606','樊城区','8','420600'),('420607','襄阳区','8','420600'),('420624','南漳县','8','420600'),('420625','谷城县','8','420600'),('420626','保康县','8','420600'),('420682','老河口市','8','420600'),('420683','枣阳市','8','420600'),('420684','宜城市','8','420600'),('420700','鄂州市','5','420000'),('420702','梁子湖区','8','420700'),('420703','华容区','8','420700'),('420704','鄂城区','8','420700'),('420800','荆门市','5','420000'),('420802','东宝区','8','420800'),('420804','掇刀区','8','420800'),('420821','京山县','8','420800'),('420822','沙洋县','8','420800'),('420881','钟祥市','8','420800'),('420900','孝感市','5','420000'),('420902','孝南区','8','420900'),('420921','孝昌县','8','420900'),('420922','大悟县','8','420900'),('420923','云梦县','8','420900'),('420981','应城市','8','420900'),('420982','安陆市','8','420900'),('420984','汉川市','8','420900'),('421000','荆州市','5','420000'),('421002','沙市区','8','421000'),('421003','荆州区','8','421000'),('421022','公安县','8','421000'),('421023','监利县','8','421000'),('421024','江陵县','8','421000'),('421081','石首市','8','421000'),('421083','洪湖市','8','421000'),('421087','松滋市','8','421000'),('421100','黄冈市','5','420000'),('421102','黄州区','8','421100'),('421121','团风县','8','421100'),('421122','红安县','8','421100'),('421123','罗田县','8','421100'),('421124','英山县','8','421100'),('421125','浠水县','8','421100'),('421126','蕲春县','8','421100'),('421127','黄梅县','8','421100'),('421181','麻城市','8','421100'),('421182','武穴市','8','421100'),('421200','咸宁市','5','420000'),('421202','咸安区','8','421200'),('421221','嘉鱼县','8','421200'),('421222','通城县','8','421200'),('421223','崇阳县','8','421200'),('421224','通山县','8','421200'),('421281','赤壁市','8','421200'),('421300','随州市','5','420000'),('421302','曾都区','8','421300'),('421381','广水市','8','421300'),('422800','恩施土家族苗族自治州','5','420000'),('422801','恩施市','8','422800'),('422802','利川市','8','422800'),('422822','建始县','8','422800'),('422823','巴东县','8','422800'),('422825','宣恩县','8','422800'),('422826','咸丰县','8','422800'),('422827','来凤县','8','422800'),('422828','鹤峰县','8','422800'),('429000','省直辖行政单位','5','420000'),('429004','仙桃市','8','429000'),('429005','潜江市','8','429000'),('429006','天门市','8','429000'),('429021','神农架林区','8','429000'),('430000','湖南省','2','000000'),('430100','长沙市','5','430000'),('430102','芙蓉区','8','430100'),('430103','天心区','8','430100'),('430104','岳麓区','8','430100'),('430105','开福区','8','430100'),('430111','雨花区','8','430100'),('430121','长沙县','8','430100'),('430122','望城县','8','430100'),('430124','宁乡县','8','430100'),('430181','浏阳市','8','430100'),('430200','株洲市','5','430000'),('430202','荷塘区','8','430200'),('430203','芦淞区','8','430200'),('430204','石峰区','8','430200'),('430211','天元区','8','430200'),('430221','株洲县','8','430200'),('430223','攸　县','8','430200'),('430224','茶陵县','8','430200'),('430225','炎陵县','8','430200'),('430281','醴陵市','8','430200'),('430300','湘潭市','5','430000'),('430302','雨湖区','8','430300'),('430304','岳塘区','8','430300'),('430321','湘潭县','8','430300'),('430381','湘乡市','8','430300'),('430382','韶山市','8','430300'),('430400','衡阳市','5','430000'),('430405','珠晖区','8','430400'),('430406','雁峰区','8','430400'),('430407','石鼓区','8','430400'),('430408','蒸湘区','8','430400'),('430412','南岳区','8','430400'),('430421','衡阳县','8','430400'),('430422','衡南县','8','430400'),('430423','衡山县','8','430400'),('430424','衡东县','8','430400'),('430426','祁东县','8','430400'),('430481','耒阳市','8','430400'),('430482','常宁市','8','430400'),('430500','邵阳市','5','430000'),('430502','双清区','8','430500'),('430503','大祥区','8','430500'),('430511','北塔区','8','430500'),('430521','邵东县','8','430500'),('430522','新邵县','8','430500'),('430523','邵阳县','8','430500'),('430524','隆回县','8','430500'),('430525','洞口县','8','430500'),('430527','绥宁县','8','430500'),('430528','新宁县','8','430500'),('430529','城步苗族自治县','8','430500'),('430581','武冈市','8','430500'),('430600','岳阳市','5','430000'),('430602','岳阳楼区','8','430600'),('430603','云溪区','8','430600'),('430611','君山区','8','430600'),('430621','岳阳县','8','430600'),('430623','华容县','8','430600'),('430624','湘阴县','8','430600'),('430626','平江县','8','430600'),('430681','汨罗市','8','430600'),('430682','临湘市','8','430600'),('430700','常德市','5','430000'),('430702','武陵区','8','430700'),('430703','鼎城区','8','430700'),('430721','安乡县','8','430700'),('430722','汉寿县','8','430700'),('430723','澧　县','8','430700'),('430724','临澧县','8','430700'),('430725','桃源县','8','430700'),('430726','石门县','8','430700'),('430781','津市市','8','430700'),('430800','张家界市','5','430000'),('430802','永定区','8','430800'),('430811','武陵源区','8','430800'),('430821','慈利县','8','430800'),('430822','桑植县','8','430800'),('430900','益阳市','5','430000'),('430902','资阳区','8','430900'),('430903','赫山区','8','430900'),('430921','南　县','8','430900'),('430922','桃江县','8','430900'),('430923','安化县','8','430900'),('430981','沅江市','8','430900'),('431000','郴州市','5','430000'),('431002','北湖区','8','431000'),('431003','苏仙区','8','431000'),('431021','桂阳县','8','431000'),('431022','宜章县','8','431000'),('431023','永兴县','8','431000'),('431024','嘉禾县','8','431000'),('431025','临武县','8','431000'),('431026','汝城县','8','431000'),('431027','桂东县','8','431000'),('431028','安仁县','8','431000'),('431081','资兴市','8','431000'),('431100','永州市','5','430000'),('431102','芝山区','8','431100'),('431103','冷水滩区','8','431100'),('431121','祁阳县','8','431100'),('431122','东安县','8','431100'),('431123','双牌县','8','431100'),('431124','道　县','8','431100'),('431125','江永县','8','431100'),('431126','宁远县','8','431100'),('431127','蓝山县','8','431100'),('431128','新田县','8','431100'),('431129','江华瑶族自治县','8','431100'),('431200','怀化市','5','430000'),('431202','鹤城区','8','431200'),('431221','中方县','8','431200'),('431222','沅陵县','8','431200'),('431223','辰溪县','8','431200'),('431224','溆浦县','8','431200'),('431225','会同县','8','431200'),('431226','麻阳苗族自治县','8','431200'),('431227','新晃侗族自治县','8','431200'),('431228','芷江侗族自治县','8','431200'),('431229','靖州苗族侗族自治县','8','431200'),('431230','通道侗族自治县','8','431200'),('431281','洪江市','8','431200'),('431300','娄底市','5','430000'),('431302','娄星区','8','431300'),('431321','双峰县','8','431300'),('431322','新化县','8','431300'),('431381','冷水江市','8','431300'),('431382','涟源市','8','431300'),('433100','湘西土家族苗族自治州','5','430000'),('433101','吉首市','8','433100'),('433122','泸溪县','8','433100'),('433123','凤凰县','8','433100'),('433124','花垣县','8','433100'),('433125','保靖县','8','433100'),('433126','古丈县','8','433100'),('433127','永顺县','8','433100'),('433130','龙山县','8','433100'),('440000','广东省','2','000000'),('440100','广州市','5','440000'),('440102','东山区','8','440100'),('440103','荔湾区','8','440100'),('440104','越秀区','8','440100'),('440105','海珠区','8','440100'),('440106','天河区','8','440100'),('440107','芳村区','8','440100'),('440111','白云区','8','440100'),('440112','黄埔区','8','440100'),('440113','番禺区','8','440100'),('440114','花都区','8','440100'),('440183','增城市','8','440100'),('440184','从化市','8','440100'),('440200','韶关市','5','440000'),('440203','武江区','8','440200'),('440204','浈江区','8','440200'),('440205','曲江区','8','440200'),('440222','始兴县','8','440200'),('440224','仁化县','8','440200'),('440229','翁源县','8','440200'),('440232','乳源瑶族自治县','8','440200'),('440233','新丰县','8','440200'),('440281','乐昌市','8','440200'),('440282','南雄市','8','440200'),('440300','深圳市','5','440000'),('440303','罗湖区','8','440300'),('440304','福田区','8','440300'),('440305','南山区','8','440300'),('440306','宝安区','8','440300'),('440307','龙岗区','8','440300'),('440308','盐田区','8','440300'),('440400','珠海市','5','440000'),('440402','香洲区','8','440400'),('440403','斗门区','8','440400'),('440404','金湾区','8','440400'),('440500','汕头市','5','440000'),('440507','龙湖区','8','440500'),('440511','金平区','8','440500'),('440512','濠江区','8','440500'),('440513','潮阳区','8','440500'),('440514','潮南区','8','440500'),('440515','澄海区','8','440500'),('440523','南澳县','8','440500'),('440600','佛山市','5','440000'),('440604','禅城区','8','440600'),('440605','南海区','8','440600'),('440606','顺德区','8','440600'),('440607','三水区','8','440600'),('440608','高明区','8','440600'),('440700','江门市','5','440000'),('440703','蓬江区','8','440700'),('440704','江海区','8','440700'),('440705','新会区','8','440700'),('440781','台山市','8','440700'),('440783','开平市','8','440700'),('440784','鹤山市','8','440700'),('440785','恩平市','8','440700'),('440800','湛江市','5','440000'),('440802','赤坎区','8','440800'),('440803','霞山区','8','440800'),('440804','坡头区','8','440800'),('440811','麻章区','8','440800'),('440823','遂溪县','8','440800'),('440825','徐闻县','8','440800'),('440881','廉江市','8','440800'),('440882','雷州市','8','440800'),('440883','吴川市','8','440800'),('440900','茂名市','5','440000'),('440902','茂南区','8','440900'),('440903','茂港区','8','440900'),('440923','电白县','8','440900'),('440981','高州市','8','440900'),('440982','化州市','8','440900'),('440983','信宜市','8','440900'),('441200','肇庆市','5','440000'),('441202','端州区','8','441200'),('441203','鼎湖区','8','441200'),('441223','广宁县','8','441200'),('441224','怀集县','8','441200'),('441225','封开县','8','441200'),('441226','德庆县','8','441200'),('441283','高要市','8','441200'),('441284','四会市','8','441200'),('441300','惠州市','5','440000'),('441302','惠城区','8','441300'),('441303','惠阳区','8','441300'),('441322','博罗县','8','441300'),('441323','惠东县','8','441300'),('441324','龙门县','8','441300'),('441400','梅州市','5','440000'),('441402','梅江区','8','441400'),('441421','梅　县','8','441400'),('441422','大埔县','8','441400'),('441423','丰顺县','8','441400'),('441424','五华县','8','441400'),('441426','平远县','8','441400'),('441427','蕉岭县','8','441400'),('441481','兴宁市','8','441400'),('441500','汕尾市','5','440000'),('441502','城　区','8','441500'),('441521','海丰县','8','441500'),('441523','陆河县','8','441500'),('441581','陆丰市','8','441500'),('441600','河源市','5','440000'),('441602','源城区','8','441600'),('441621','紫金县','8','441600'),('441622','龙川县','8','441600'),('441623','连平县','8','441600'),('441624','和平县','8','441600'),('441625','东源县','8','441600'),('441700','阳江市','5','440000'),('441702','江城区','8','441700'),('441721','阳西县','8','441700'),('441723','阳东县','8','441700'),('441781','阳春市','8','441700'),('441800','清远市','5','440000'),('441802','清城区','8','441800'),('441821','佛冈县','8','441800'),('441823','阳山县','8','441800'),('441825','连山壮族瑶族自治县','8','441800'),('441826','连南瑶族自治县','8','441800'),('441827','清新县','8','441800'),('441881','英德市','8','441800'),('441882','连州市','8','441800'),('441900','东莞市','5','440000'),('442000','中山市','5','440000'),('445100','潮州市','5','440000'),('445102','湘桥区','8','445100'),('445121','潮安县','8','445100'),('445122','饶平县','8','445100'),('445200','揭阳市','5','440000'),('445202','榕城区','8','445200'),('445221','揭东县','8','445200'),('445222','揭西县','8','445200'),('445224','惠来县','8','445200'),('445281','普宁市','8','445200'),('445300','云浮市','5','440000'),('445302','云城区','8','445300'),('445321','新兴县','8','445300'),('445322','郁南县','8','445300'),('445323','云安县','8','445300'),('445381','罗定市','8','445300'),('450000','广西壮族自治区','3','000000'),('450100','南宁市','5','450000'),('450102','兴宁区','8','450100'),('450103','青秀区','8','450100'),('450105','江南区','8','450100'),('450107','西乡塘区','8','450100'),('450108','良庆区','8','450100'),('450109','邕宁区','8','450100'),('450122','武鸣县','8','450100'),('450123','隆安县','8','450100'),('450124','马山县','8','450100'),('450125','上林县','8','450100'),('450126','宾阳县','8','450100'),('450127','横　县','8','450100'),('450200','柳州市','5','450000'),('450202','城中区','8','450200'),('450203','鱼峰区','8','450200'),('450204','柳南区','8','450200'),('450205','柳北区','8','450200'),('450221','柳江县','8','450200'),('450222','柳城县','8','450200'),('450223','鹿寨县','8','450200'),('450224','融安县','8','450200'),('450225','融水苗族自治县','8','450200'),('450226','三江侗族自治县','8','450200'),('450300','桂林市','5','450000'),('450302','秀峰区','8','450300'),('450303','叠彩区','8','450300'),('450304','象山区','8','450300'),('450305','七星区','8','450300'),('450311','雁山区','8','450300'),('450321','阳朔县','8','450300'),('450322','临桂县','8','450300'),('450323','灵川县','8','450300'),('450324','全州县','8','450300'),('450325','兴安县','8','450300'),('450326','永福县','8','450300'),('450327','灌阳县','8','450300'),('450328','龙胜各族自治县','8','450300'),('450329','资源县','8','450300'),('450330','平乐县','8','450300'),('450331','荔蒲县','8','450300'),('450332','恭城瑶族自治县','8','450300'),('450400','梧州市','5','450000'),('450403','万秀区','8','450400'),('450404','蝶山区','8','450400'),('450405','长洲区','8','450400'),('450421','苍梧县','8','450400'),('450422','藤　县','8','450400'),('450423','蒙山县','8','450400'),('450481','岑溪市','8','450400'),('450500','北海市','5','450000'),('450502','海城区','8','450500'),('450503','银海区','8','450500'),('450512','铁山港区','8','450500'),('450521','合浦县','8','450500'),('450600','防城港市','5','450000'),('450602','港口区','8','450600'),('450603','防城区','8','450600'),('450621','上思县','8','450600'),('450681','东兴市','8','450600'),('450700','钦州市','5','450000'),('450702','钦南区','8','450700'),('450703','钦北区','8','450700'),('450721','灵山县','8','450700'),('450722','浦北县','8','450700'),('450800','贵港市','5','450000'),('450802','港北区','8','450800'),('450803','港南区','8','450800'),('450804','覃塘区','8','450800'),('450821','平南县','8','450800'),('450881','桂平市','8','450800'),('450900','玉林市','5','450000'),('450902','玉州区','8','450900'),('450921','容　县','8','450900'),('450922','陆川县','8','450900'),('450923','博白县','8','450900'),('450924','兴业县','8','450900'),('450981','北流市','8','450900'),('451000','百色市','5','450000'),('451002','右江区','8','451000'),('451021','田阳县','8','451000'),('451022','田东县','8','451000'),('451023','平果县','8','451000'),('451024','德保县','8','451000'),('451025','靖西县','8','451000'),('451026','那坡县','8','451000'),('451027','凌云县','8','451000'),('451028','乐业县','8','451000'),('451029','田林县','8','451000'),('451030','西林县','8','451000'),('451031','隆林各族自治县','8','451000'),('451100','贺州市','5','450000'),('451102','八步区','8','451100'),('451121','昭平县','8','451100'),('451122','钟山县','8','451100'),('451123','富川瑶族自治县','8','451100'),('451200','河池市','5','450000'),('451202','金城江区','8','451200'),('451221','南丹县','8','451200'),('451222','天峨县','8','451200'),('451223','凤山县','8','451200'),('451224','东兰县','8','451200'),('451225','罗城仫佬族自治县','8','451200'),('451226','环江毛南族自治县','8','451200'),('451227','巴马瑶族自治县','8','451200'),('451228','都安瑶族自治县','8','451200'),('451229','大化瑶族自治县','8','451200'),('451281','宜州市','8','451200'),('451300','来宾市','5','450000'),('451302','兴宾区','8','451300'),('451321','忻城县','8','451300'),('451322','象州县','8','451300'),('451323','武宣县','8','451300'),('451324','金秀瑶族自治县','8','451300'),('451381','合山市','8','451300'),('451400','崇左市','5','450000'),('451402','江洲区','8','451400'),('451421','扶绥县','8','451400'),('451422','宁明县','8','451400'),('451423','龙州县','8','451400'),('451424','大新县','8','451400'),('451425','天等县','8','451400'),('451481','凭祥市','8','451400'),('460000','海南省','2','000000'),('460100','海口市','5','460000'),('460105','秀英区','8','460100'),('460106','龙华区','8','460100'),('460107','琼山区','8','460100'),('460108','美兰区','8','460100'),('460200','三亚市','5','460000'),('469000','省直辖县级行政单位','5','460000'),('469001','五指山市','8','469000'),('469002','琼海市','8','469000'),('469003','儋州市','8','469000'),('469005','文昌市','8','469000'),('469006','万宁市','8','469000'),('469007','东方市','8','469000'),('469025','定安县','8','469000'),('469026','屯昌县','8','469000'),('469027','澄迈县','8','469000'),('469028','临高县','8','469000'),('469030','白沙黎族自治县','8','469000'),('469031','昌江黎族自治县','8','469000'),('469033','乐东黎族自治县','8','469000'),('469034','陵水黎族自治县','8','469000'),('469035','保亭黎族苗族自治县','8','469000'),('469036','琼中黎族苗族自治县','8','469000'),('469037','西沙群岛','8','469000'),('469038','南沙群岛','8','469000'),('469039','中沙群岛的岛礁及其海域','8','469000'),('500000','重庆市','1','000000'),('500101','万州区','6','500000'),('500102','涪陵区','6','500000'),('500103','渝中区','6','500000'),('500104','大渡口区','6','500000'),('500105','江北区','6','500000'),('500106','沙坪坝区','6','500000'),('500107','九龙坡区','6','500000'),('500108','南岸区','6','500000'),('500109','北碚区','6','500000'),('500110','万盛区','6','500000'),('500111','双桥区','6','500000'),('500112','渝北区','6','500000'),('500113','巴南区','6','500000'),('500114','黔江区','6','500000'),('500115','长寿区','6','500000'),('500222','綦江县','8','500200'),('500223','潼南县','8','500200'),('500224','铜梁县','8','500200'),('500225','大足县','8','500200'),('500226','荣昌县','8','500200'),('500227','璧山县','8','500200'),('500228','梁平县','8','500200'),('500229','城口县','8','500200'),('500230','丰都县','8','500200'),('500231','垫江县','8','500200'),('500232','武隆县','8','500200'),('500233','忠　县','8','500200'),('500234','开　县','8','500200'),('500235','云阳县','8','500200'),('500236','奉节县','8','500200'),('500237','巫山县','8','500200'),('500238','巫溪县','8','500200'),('500240','石柱土家族自治县','8','500200'),('500241','秀山土家族苗族自治县','8','500200'),('500242','酉阳土家族苗族自治县','8','500200'),('500243','彭水苗族土家族自治县','8','500200'),('500381','江津市','8','500300'),('500382','合川市','8','500300'),('500383','永川市','8','500300'),('500384','南川市','8','500300'),('510000','四川省','2','000000'),('510100','成都市','5','510000'),('510104','锦江区','8','510100'),('510105','青羊区','8','510100'),('510106','金牛区','8','510100'),('510107','武侯区','8','510100'),('510108','成华区','8','510100'),('510112','龙泉驿区','8','510100'),('510113','青白江区','8','510100'),('510114','新都区','8','510100'),('510115','温江区','8','510100'),('510121','金堂县','8','510100'),('510122','双流县','8','510100'),('510124','郫　县','8','510100'),('510129','大邑县','8','510100'),('510131','蒲江县','8','510100'),('510132','新津县','8','510100'),('510181','都江堰市','8','510100'),('510182','彭州市','8','510100'),('510183','邛崃市','8','510100'),('510184','崇州市','8','510100'),('510300','自贡市','5','510000'),('510302','自流井区','8','510300'),('510303','贡井区','8','510300'),('510304','大安区','8','510300'),('510311','沿滩区','8','510300'),('510321','荣　县','8','510300'),('510322','富顺县','8','510300'),('510400','攀枝花市','5','510000'),('510402','东　区','8','510400'),('510403','西　区','8','510400'),('510411','仁和区','8','510400'),('510421','米易县','8','510400'),('510422','盐边县','8','510400'),('510500','泸州市','5','510000'),('510502','江阳区','8','510500'),('510503','纳溪区','8','510500'),('510504','龙马潭区','8','510500'),('510521','泸　县','8','510500'),('510522','合江县','8','510500'),('510524','叙永县','8','510500'),('510525','古蔺县','8','510500'),('510600','德阳市','5','510000'),('510603','旌阳区','8','510600'),('510623','中江县','8','510600'),('510626','罗江县','8','510600'),('510681','广汉市','8','510600'),('510682','什邡市','8','510600'),('510683','绵竹市','8','510600'),('510700','绵阳市','5','510000'),('510703','涪城区','8','510700'),('510704','游仙区','8','510700'),('510722','三台县','8','510700'),('510723','盐亭县','8','510700'),('510724','安　县','8','510700'),('510725','梓潼县','8','510700'),('510726','北川羌族自治县','8','510700'),('510727','平武县','8','510700'),('510781','江油市','8','510700'),('510800','广元市','5','510000'),('510802','市中区','8','510800'),('510811','元坝区','8','510800'),('510812','朝天区','8','510800'),('510821','旺苍县','8','510800'),('510822','青川县','8','510800'),('510823','剑阁县','8','510800'),('510824','苍溪县','8','510800'),('510900','遂宁市','5','510000'),('510903','船山区','8','510900'),('510904','安居区','8','510900'),('510921','蓬溪县','8','510900'),('510922','射洪县','8','510900'),('510923','大英县','8','510900'),('511000','内江市','5','510000'),('511002','市中区','8','511000'),('511011','东兴区','8','511000'),('511024','威远县','8','511000'),('511025','资中县','8','511000'),('511028','隆昌县','8','511000'),('511100','乐山市','5','510000'),('511102','市中区','8','511100'),('511111','沙湾区','8','511100'),('511112','五通桥区','8','511100'),('511113','金口河区','8','511100'),('511123','犍为县','8','511100'),('511124','井研县','8','511100'),('511126','夹江县','8','511100'),('511129','沐川县','8','511100'),('511132','峨边彝族自治县','8','511100'),('511133','马边彝族自治县','8','511100'),('511181','峨眉山市','8','511100'),('511300','南充市','5','510000'),('511302','顺庆区','8','511300'),('511303','高坪区','8','511300'),('511304','嘉陵区','8','511300'),('511321','南部县','8','511300'),('511322','营山县','8','511300'),('511323','蓬安县','8','511300'),('511324','仪陇县','8','511300'),('511325','西充县','8','511300'),('511381','阆中市','8','511300'),('511400','眉山市','5','510000'),('511402','东坡区','8','511400'),('511421','仁寿县','8','511400'),('511422','彭山县','8','511400'),('511423','洪雅县','8','511400'),('511424','丹棱县','8','511400'),('511425','青神县','8','511400'),('511500','宜宾市','5','510000'),('511502','翠屏区','8','511500'),('511521','宜宾县','8','511500'),('511522','南溪县','8','511500'),('511523','江安县','8','511500'),('511524','长宁县','8','511500'),('511525','高　县','8','511500'),('511526','珙　县','8','511500'),('511527','筠连县','8','511500'),('511528','兴文县','8','511500'),('511529','屏山县','8','511500'),('511600','广安市','5','510000'),('511602','广安区','8','511600'),('511621','岳池县','8','511600'),('511622','武胜县','8','511600'),('511623','邻水县','8','511600'),('511681','华莹市','8','511600'),('511700','达州市','5','510000'),('511702','通川区','8','511700'),('511721','达　县','8','511700'),('511722','宣汉县','8','511700'),('511723','开江县','8','511700'),('511724','大竹县','8','511700'),('511725','渠　县','8','511700'),('511781','万源市','8','511700'),('511800','雅安市','5','510000'),('511802','雨城区','8','511800'),('511821','名山县','8','511800'),('511822','荥经县','8','511800'),('511823','汉源县','8','511800'),('511824','石棉县','8','511800'),('511825','天全县','8','511800'),('511826','芦山县','8','511800'),('511827','宝兴县','8','511800'),('511900','巴中市','5','510000'),('511902','巴州区','8','511900'),('511921','通江县','8','511900'),('511922','南江县','8','511900'),('511923','平昌县','8','511900'),('512000','资阳市','5','510000'),('512002','雁江区','8','512000'),('512021','安岳县','8','512000'),('512022','乐至县','8','512000'),('512081','简阳市','8','512000'),('513200','阿坝藏族羌族自治州','5','510000'),('513221','汶川县','8','513200'),('513222','理　县','8','513200'),('513223','茂　县','8','513200'),('513224','松潘县','8','513200'),('513225','九寨沟县','8','513200'),('513226','金川县','8','513200'),('513227','小金县','8','513200'),('513228','黑水县','8','513200'),('513229','马尔康县','8','513200'),('513230','壤塘县','8','513200'),('513231','阿坝县','8','513200'),('513232','若尔盖县','8','513200'),('513233','红原县','8','513200'),('513300','甘孜藏族自治州','5','510000'),('513321','康定县','8','513300'),('513322','泸定县','8','513300'),('513323','丹巴县','8','513300'),('513324','九龙县','8','513300'),('513325','雅江县','8','513300'),('513326','道孚县','8','513300'),('513327','炉霍县','8','513300'),('513328','甘孜县','8','513300'),('513329','新龙县','8','513300'),('513330','德格县','8','513300'),('513331','白玉县','8','513300'),('513332','石渠县','8','513300'),('513333','色达县','8','513300'),('513334','理塘县','8','513300'),('513335','巴塘县','8','513300'),('513336','乡城县','8','513300'),('513337','稻城县','8','513300'),('513338','得荣县','8','513300'),('513400','凉山彝族自治州','5','510000'),('513401','西昌市','8','513400'),('513422','木里藏族自治县','8','513400'),('513423','盐源县','8','513400'),('513424','德昌县','8','513400'),('513425','会理县','8','513400'),('513426','会东县','8','513400'),('513427','宁南县','8','513400'),('513428','普格县','8','513400'),('513429','布拖县','8','513400'),('513430','金阳县','8','513400'),('513431','昭觉县','8','513400'),('513432','喜德县','8','513400'),('513433','冕宁县','8','513400'),('513434','越西县','8','513400'),('513435','甘洛县','8','513400'),('513436','美姑县','8','513400'),('513437','雷波县','8','513400'),('520000','贵州省','2','000000'),('520100','贵阳市','5','520000'),('520102','南明区','8','520100'),('520103','云岩区','8','520100'),('520111','花溪区','8','520100'),('520112','乌当区','8','520100'),('520113','白云区','8','520100'),('520114','小河区','8','520100'),('520121','开阳县','8','520100'),('520122','息烽县','8','520100'),('520123','修文县','8','520100'),('520181','清镇市','8','520100'),('520200','六盘水市','5','520000'),('520201','钟山区','8','520200'),('520203','六枝特区','8','520200'),('520221','水城县','8','520200'),('520222','盘　县','8','520200'),('520300','遵义市','5','520000'),('520302','红花岗区','8','520300'),('520303','汇川区','8','520300'),('520321','遵义县','8','520300'),('520322','桐梓县','8','520300'),('520323','绥阳县','8','520300'),('520324','正安县','8','520300'),('520325','道真仡佬族苗族自治县','8','520300'),('520326','务川仡佬族苗族自治县','8','520300'),('520327','凤冈县','8','520300'),('520328','湄潭县','8','520300'),('520329','余庆县','8','520300'),('520330','习水县','8','520300'),('520381','赤水市','8','520300'),('520382','仁怀市','8','520300'),('520400','安顺市','5','520000'),('520402','西秀区','8','520400'),('520421','平坝县','8','520400'),('520422','普定县','8','520400'),('520423','镇宁布依族苗族自治县','8','520400'),('520424','关岭布依族苗族自治县','8','520400'),('520425','紫云苗族布依族自治县','8','520400'),('522200','铜仁地区','5','520000'),('522201','铜仁市','8','522200'),('522222','江口县','8','522200'),('522223','玉屏侗族自治县','8','522200'),('522224','石阡县','8','522200'),('522225','思南县','8','522200'),('522226','印江土家族苗族自治县','8','522200'),('522227','德江县','8','522200'),('522228','沿河土家族自治县','8','522200'),('522229','松桃苗族自治县','8','522200'),('522230','万山特区','8','522200'),('522300','黔西南布依族苗族自治州','5','520000'),('522301','兴义市','8','522300'),('522322','兴仁县','8','522300'),('522323','普安县','8','522300'),('522324','晴隆县','8','522300'),('522325','贞丰县','8','522300'),('522326','望谟县','8','522300'),('522327','册亨县','8','522300'),('522328','安龙县','8','522300'),('522400','毕节地区','5','520000'),('522401','毕节市','8','522400'),('522422','大方县','8','522400'),('522423','黔西县','8','522400'),('522424','金沙县','8','522400'),('522425','织金县','8','522400'),('522426','纳雍县','8','522400'),('522427','威宁彝族回族苗族自治县','8','522400'),('522428','赫章县','8','522400'),('522600','黔东南苗族侗族自治州','5','520000'),('522601','凯里市','8','522600'),('522622','黄平县','8','522600'),('522623','施秉县','8','522600'),('522624','三穗县','8','522600'),('522625','镇远县','8','522600'),('522626','岑巩县','8','522600'),('522627','天柱县','8','522600'),('522628','锦屏县','8','522600'),('522629','剑河县','8','522600'),('522630','台江县','8','522600'),('522631','黎平县','8','522600'),('522632','榕江县','8','522600'),('522633','从江县','8','522600'),('522634','雷山县','8','522600'),('522635','麻江县','8','522600'),('522636','丹寨县','8','522600'),('522700','黔南布依族苗族自治州','5','520000'),('522701','都匀市','8','522700'),('522702','福泉市','8','522700'),('522722','荔波县','8','522700'),('522723','贵定县','8','522700'),('522725','瓮安县','8','522700'),('522726','独山县','8','522700'),('522727','平塘县','8','522700'),('522728','罗甸县','8','522700'),('522729','长顺县','8','522700'),('522730','龙里县','8','522700'),('522731','惠水县','8','522700'),('522732','三都水族自治县','8','522700'),('530000','云南省','2','000000'),('530100','昆明市','5','530000'),('530102','五华区','8','530100'),('530103','盘龙区','8','530100'),('530111','官渡区','8','530100'),('530112','西山区','8','530100'),('530113','东川区','8','530100'),('530121','呈贡县','8','530100'),('530122','晋宁县','8','530100'),('530124','富民县','8','530100'),('530125','宜良县','8','530100'),('530126','石林彝族自治县','8','530100'),('530127','嵩明县','8','530100'),('530128','禄劝彝族苗族自治县','8','530100'),('530129','寻甸回族彝族自治县','8','530100'),('530181','安宁市','8','530100'),('530300','曲靖市','5','530000'),('530302','麒麟区','8','530300'),('530321','马龙县','8','530300'),('530322','陆良县','8','530300'),('530323','师宗县','8','530300'),('530324','罗平县','8','530300'),('530325','富源县','8','530300'),('530326','会泽县','8','530300'),('530328','沾益县','8','530300'),('530381','宣威市','8','530300'),('530400','玉溪市','5','530000'),('530402','红塔区','8','530400'),('530421','江川县','8','530400'),('530422','澄江县','8','530400'),('530423','通海县','8','530400'),('530424','华宁县','8','530400'),('530425','易门县','8','530400'),('530426','峨山彝族自治县','8','530400'),('530427','新平彝族傣族自治县','8','530400'),('530428','元江哈尼族彝族傣族自治县','8','530400'),('530500','保山市','5','530000'),('530502','隆阳区','8','530500'),('530521','施甸县','8','530500'),('530522','腾冲县','8','530500'),('530523','龙陵县','8','530500'),('530524','昌宁县','8','530500'),('530600','昭通市','5','530000'),('530602','昭阳区','8','530600'),('530621','鲁甸县','8','530600'),('530622','巧家县','8','530600'),('530623','盐津县','8','530600'),('530624','大关县','8','530600'),('530625','永善县','8','530600'),('530626','绥江县','8','530600'),('530627','镇雄县','8','530600'),('530628','彝良县','8','530600'),('530629','威信县','8','530600'),('530630','水富县','8','530600'),('530700','丽江市','5','530000'),('530702','古城区','8','530700'),('530721','玉龙纳西族自治县','8','530700'),('530722','永胜县','8','530700'),('530723','华坪县','8','530700'),('530724','宁蒗彝族自治县','8','530700'),('530800','思茅市','5','530000'),('530802','翠云区','8','530800'),('530821','普洱哈尼族彝族自治县','8','530800'),('530822','墨江哈尼族自治县','8','530800'),('530823','景东彝族自治县','8','530800'),('530824','景谷傣族彝族自治县','8','530800'),('530825','镇沅彝族哈尼族拉祜族自治县','8','530800'),('530826','江城哈尼族彝族自治县','8','530800'),('530827','孟连傣族拉祜族佤族自治县','8','530800'),('530828','澜沧拉祜族自治县','8','530800'),('530829','西盟佤族自治县','8','530800'),('530900','临沧市','5','530000'),('530902','临翔区','8','530900'),('530921','凤庆县','8','530900'),('530922','云　县','8','530900'),('530923','永德县','8','530900'),('530924','镇康县','8','530900'),('530925','双江拉祜族佤族布朗族傣族自治县','8','530900'),('530926','耿马傣族佤族自治县','8','530900'),('530927','沧源佤族自治县','8','530900'),('532300','楚雄彝族自治州','5','530000'),('532301','楚雄市','8','532300'),('532322','双柏县','8','532300'),('532323','牟定县','8','532300'),('532324','南华县','8','532300'),('532325','姚安县','8','532300'),('532326','大姚县','8','532300'),('532327','永仁县','8','532300'),('532328','元谋县','8','532300'),('532329','武定县','8','532300'),('532331','禄丰县','8','532300'),('532500','红河哈尼族彝族自治州','5','530000'),('532501','个旧市','8','532500'),('532502','开远市','8','532500'),('532522','蒙自县','8','532500'),('532523','屏边苗族自治县','8','532500'),('532524','建水县','8','532500'),('532525','石屏县','8','532500'),('532526','弥勒县','8','532500'),('532527','泸西县','8','532500'),('532528','元阳县','8','532500'),('532529','红河县','8','532500'),('532530','金平苗族瑶族傣族自治县','8','532500'),('532531','绿春县','8','532500'),('532532','河口瑶族自治县','8','532500'),('532600','文山壮族苗族自治州','5','530000'),('532621','文山县','8','532600'),('532622','砚山县','8','532600'),('532623','西畴县','8','532600'),('532624','麻栗坡县','8','532600'),('532625','马关县','8','532600'),('532626','丘北县','8','532600'),('532627','广南县','8','532600'),('532628','富宁县','8','532600'),('532800','西双版纳傣族自治州','5','530000'),('532801','景洪市','8','532800'),('532822','勐海县','8','532800'),('532823','勐腊县','8','532800'),('532900','大理白族自治州','5','530000'),('532901','大理市','8','532900'),('532922','漾濞彝族自治县','8','532900'),('532923','祥云县','8','532900'),('532924','宾川县','8','532900'),('532925','弥渡县','8','532900'),('532926','南涧彝族自治县','8','532900'),('532927','巍山彝族回族自治县','8','532900'),('532928','永平县','8','532900'),('532929','云龙县','8','532900'),('532930','洱源县','8','532900'),('532931','剑川县','8','532900'),('532932','鹤庆县','8','532900'),('533100','德宏傣族景颇族自治州','5','530000'),('533102','瑞丽市','8','533100'),('533103','潞西市','8','533100'),('533122','梁河县','8','533100'),('533123','盈江县','8','533100'),('533124','陇川县','8','533100'),('533300','怒江傈僳族自治州','5','530000'),('533321','泸水县','8','533300'),('533323','福贡县','8','533300'),('533324','贡山独龙族怒族自治县','8','533300'),('533325','兰坪白族普米族自治县','8','533300'),('533400','迪庆藏族自治州','5','530000'),('533421','香格里拉县','8','533400'),('533422','德钦县','8','533400'),('533423','维西傈僳族自治县','8','533400'),('540000','西藏自治区','3','000000'),('540100','拉萨市','5','540000'),('540102','城关区','8','540100'),('540121','林周县','8','540100'),('540122','当雄县','8','540100'),('540123','尼木县','8','540100'),('540124','曲水县','8','540100'),('540125','堆龙德庆县','8','540100'),('540126','达孜县','8','540100'),('540127','墨竹工卡县','8','540100'),('542100','昌都地区','5','540000'),('542121','昌都县','8','542100'),('542122','江达县','8','542100'),('542123','贡觉县','8','542100'),('542124','类乌齐县','8','542100'),('542125','丁青县','8','542100'),('542126','察雅县','8','542100'),('542127','八宿县','8','542100'),('542128','左贡县','8','542100'),('542129','芒康县','8','542100'),('542132','洛隆县','8','542100'),('542133','边坝县','8','542100'),('542200','山南地区','5','540000'),('542221','乃东县','8','542200'),('542222','扎囊县','8','542200'),('542223','贡嘎县','8','542200'),('542224','桑日县','8','542200'),('542225','琼结县','8','542200'),('542226','曲松县','8','542200'),('542227','措美县','8','542200'),('542228','洛扎县','8','542200'),('542229','加查县','8','542200'),('542231','隆子县','8','542200'),('542232','错那县','8','542200'),('542233','浪卡子县','8','542200'),('542300','日喀则地区','5','540000'),('542301','日喀则市','8','542300'),('542322','南木林县','8','542300'),('542323','江孜县','8','542300'),('542324','定日县','8','542300'),('542325','萨迦县','8','542300'),('542326','拉孜县','8','542300'),('542327','昂仁县','8','542300'),('542328','谢通门县','8','542300'),('542329','白朗县','8','542300'),('542330','仁布县','8','542300'),('542331','康马县','8','542300'),('542332','定结县','8','542300'),('542333','仲巴县','8','542300'),('542334','亚东县','8','542300'),('542335','吉隆县','8','542300'),('542336','聂拉木县','8','542300'),('542337','萨嘎县','8','542300'),('542338','岗巴县','8','542300'),('542400','那曲地区','5','540000'),('542421','那曲县','8','542400'),('542422','嘉黎县','8','542400'),('542423','比如县','8','542400'),('542424','聂荣县','8','542400'),('542425','安多县','8','542400'),('542426','申扎县','8','542400'),('542427','索　县','8','542400'),('542428','班戈县','8','542400'),('542429','巴青县','8','542400'),('542430','尼玛县','8','542400'),('542500','阿里地区','5','540000'),('542521','普兰县','8','542500'),('542522','札达县','8','542500'),('542523','噶尔县','8','542500'),('542524','日土县','8','542500'),('542525','革吉县','8','542500'),('542526','改则县','8','542500'),('542527','措勤县','8','542500'),('542600','林芝地区','5','540000'),('542621','林芝县','8','542600'),('542622','工布江达县','8','542600'),('542623','米林县','8','542600'),('542624','墨脱县','8','542600'),('542625','波密县','8','542600'),('542626','察隅县','8','542600'),('542627','朗　县','8','542600'),('610000','陕西省','2','000000'),('610100','西安市','5','610000'),('610102','新城区','8','610100'),('610103','碑林区','8','610100'),('610104','莲湖区','8','610100'),('610111','灞桥区','8','610100'),('610112','未央区','8','610100'),('610113','雁塔区','8','610100'),('610114','阎良区','8','610100'),('610115','临潼区','8','610100'),('610116','长安区','8','610100'),('610122','蓝田县','8','610100'),('610124','周至县','8','610100'),('610125','户　县','8','610100'),('610126','高陵县','8','610100'),('610200','铜川市','5','610000'),('610202','王益区','8','610200'),('610203','印台区','8','610200'),('610204','耀州区','8','610200'),('610222','宜君县','8','610200'),('610300','宝鸡市','5','610000'),('610302','渭滨区','8','610300'),('610303','金台区','8','610300'),('610304','陈仓区','8','610300'),('610322','凤翔县','8','610300'),('610323','岐山县','8','610300'),('610324','扶风县','8','610300'),('610326','眉　县','8','610300'),('610327','陇　县','8','610300'),('610328','千阳县','8','610300'),('610329','麟游县','8','610300'),('610330','凤　县','8','610300'),('610331','太白县','8','610300'),('610400','咸阳市','5','610000'),('610402','秦都区','8','610400'),('610403','杨凌区','8','610400'),('610404','渭城区','8','610400'),('610422','三原县','8','610400'),('610423','泾阳县','8','610400'),('610424','乾　县','8','610400'),('610425','礼泉县','8','610400'),('610426','永寿县','8','610400'),('610427','彬　县','8','610400'),('610428','长武县','8','610400'),('610429','旬邑县','8','610400'),('610430','淳化县','8','610400'),('610431','武功县','8','610400'),('610481','兴平市','8','610400'),('610500','渭南市','5','610000'),('610502','临渭区','8','610500'),('610521','华　县','8','610500'),('610522','潼关县','8','610500'),('610523','大荔县','8','610500'),('610524','合阳县','8','610500'),('610525','澄城县','8','610500'),('610526','蒲城县','8','610500'),('610527','白水县','8','610500'),('610528','富平县','8','610500'),('610581','韩城市','8','610500'),('610582','华阴市','8','610500'),('610600','延安市','5','610000'),('610602','宝塔区','8','610600'),('610621','延长县','8','610600'),('610622','延川县','8','610600'),('610623','子长县','8','610600'),('610624','安塞县','8','610600'),('610625','志丹县','8','610600'),('610626','吴旗县','8','610600'),('610627','甘泉县','8','610600'),('610628','富　县','8','610600'),('610629','洛川县','8','610600'),('610630','宜川县','8','610600'),('610631','黄龙县','8','610600'),('610632','黄陵县','8','610600'),('610700','汉中市','5','610000'),('610702','汉台区','8','610700'),('610721','南郑县','8','610700'),('610722','城固县','8','610700'),('610723','洋　县','8','610700'),('610724','西乡县','8','610700'),('610725','勉　县','8','610700'),('610726','宁强县','8','610700'),('610727','略阳县','8','610700'),('610728','镇巴县','8','610700'),('610729','留坝县','8','610700'),('610730','佛坪县','8','610700'),('610800','榆林市','5','610000'),('610802','榆阳区','8','610800'),('610821','神木县','8','610800'),('610822','府谷县','8','610800'),('610823','横山县','8','610800'),('610824','靖边县','8','610800'),('610825','定边县','8','610800'),('610826','绥德县','8','610800'),('610827','米脂县','8','610800'),('610828','佳　县','8','610800'),('610829','吴堡县','8','610800'),('610830','清涧县','8','610800'),('610831','子洲县','8','610800'),('610900','安康市','5','610000'),('610902','汉滨区','8','610900'),('610921','汉阴县','8','610900'),('610922','石泉县','8','610900'),('610923','宁陕县','8','610900'),('610924','紫阳县','8','610900'),('610925','岚皋县','8','610900'),('610926','平利县','8','610900'),('610927','镇坪县','8','610900'),('610928','旬阳县','8','610900'),('610929','白河县','8','610900'),('611000','商洛市','5','610000'),('611002','商州区','8','611000'),('611021','洛南县','8','611000'),('611022','丹凤县','8','611000'),('611023','商南县','8','611000'),('611024','山阳县','8','611000'),('611025','镇安县','8','611000'),('611026','柞水县','8','611000'),('620000','甘肃省','2','000000'),('620100','兰州市','5','620000'),('620102','城关区','8','620100'),('620103','七里河区','8','620100'),('620104','西固区','8','620100'),('620105','安宁区','8','620100'),('620111','红古区','8','620100'),('620121','永登县','8','620100'),('620122','皋兰县','8','620100'),('620123','榆中县','8','620100'),('620200','嘉峪关市','5','620000'),('620300','金昌市','5','620000'),('620302','金川区','8','620300'),('620321','永昌县','8','620300'),('620400','白银市','5','620000'),('620402','白银区','8','620400'),('620403','平川区','8','620400'),('620421','靖远县','8','620400'),('620422','会宁县','8','620400'),('620423','景泰县','8','620400'),('620500','天水市','5','620000'),('620502','秦城区','8','620500'),('620503','北道区','8','620500'),('620521','清水县','8','620500'),('620522','秦安县','8','620500'),('620523','甘谷县','8','620500'),('620524','武山县','8','620500'),('620525','张家川回族自治县','8','620500'),('620600','武威市','5','620000'),('620602','凉州区','8','620600'),('620621','民勤县','8','620600'),('620622','古浪县','8','620600'),('620623','天祝藏族自治县','8','620600'),('620700','张掖市','5','620000'),('620702','甘州区','8','620700'),('620721','肃南裕固族自治县','8','620700'),('620722','民乐县','8','620700'),('620723','临泽县','8','620700'),('620724','高台县','8','620700'),('620725','山丹县','8','620700'),('620800','平凉市','5','620000'),('620802','崆峒区','8','620800'),('620821','泾川县','8','620800'),('620822','灵台县','8','620800'),('620823','崇信县','8','620800'),('620824','华亭县','8','620800'),('620825','庄浪县','8','620800'),('620826','静宁县','8','620800'),('620900','酒泉市','5','620000'),('620902','肃州区','8','620900'),('620921','金塔县','8','620900'),('620922','安西县','8','620900'),('620923','肃北蒙古族自治县','8','620900'),('620924','阿克塞哈萨克族自治县','8','620900'),('620981','玉门市','8','620900'),('620982','敦煌市','8','620900'),('621000','庆阳市','5','620000'),('621002','西峰区','8','621000'),('621021','庆城县','8','621000'),('621022','环　县','8','621000'),('621023','华池县','8','621000'),('621024','合水县','8','621000'),('621025','正宁县','8','621000'),('621026','宁　县','8','621000'),('621027','镇原县','8','621000'),('621100','定西市','5','620000'),('621102','安定区','8','621100'),('621121','通渭县','8','621100'),('621122','陇西县','8','621100'),('621123','渭源县','8','621100'),('621124','临洮县','8','621100'),('621125','漳　县','8','621100'),('621126','岷　县','8','621100'),('621200','陇南市','5','620000'),('621202','武都区','8','621200'),('621221','成　县','8','621200'),('621222','文　县','8','621200'),('621223','宕昌县','8','621200'),('621224','康　县','8','621200'),('621225','西和县','8','621200'),('621226','礼　县','8','621200'),('621227','徽　县','8','621200'),('621228','两当县','8','621200'),('622900','临夏回族自治州','5','620000'),('622901','临夏市','8','622900'),('622921','临夏县','8','622900'),('622922','康乐县','8','622900'),('622923','永靖县','8','622900'),('622924','广河县','8','622900'),('622925','和政县','8','622900'),('622926','东乡族自治县','8','622900'),('622927','积石山保安族东乡族撒拉族自治县','8','622900'),('623000','甘南藏族自治州','5','620000'),('623001','合作市','8','623000'),('623021','临潭县','8','623000'),('623022','卓尼县','8','623000'),('623023','舟曲县','8','623000'),('623024','迭部县','8','623000'),('623025','玛曲县','8','623000'),('623026','碌曲县','8','623000'),('623027','夏河县','8','623000'),('630000','青海省','2','000000'),('630100','西宁市','5','630000'),('630102','城东区','8','630100'),('630103','城中区','8','630100'),('630104','城西区','8','630100'),('630105','城北区','8','630100'),('630121','大通回族土族自治县','8','630100'),('630122','湟中县','8','630100'),('630123','湟源县','8','630100'),('632100','海东地区','5','630000'),('632121','平安县','8','632100'),('632122','民和回族土族自治县','8','632100'),('632123','乐都县','8','632100'),('632126','互助土族自治县','8','632100'),('632127','化隆回族自治县','8','632100'),('632128','循化撒拉族自治县','8','632100'),('632200','海北藏族自治州','5','630000'),('632221','门源回族自治县','8','632200'),('632222','祁连县','8','632200'),('632223','海晏县','8','632200'),('632224','刚察县','8','632200'),('632300','黄南藏族自治州','5','630000'),('632321','同仁县','8','632300'),('632322','尖扎县','8','632300'),('632323','泽库县','8','632300'),('632324','河南蒙古族自治县','8','632300'),('632500','海南藏族自治州','5','630000'),('632521','共和县','8','632500'),('632522','同德县','8','632500'),('632523','贵德县','8','632500'),('632524','兴海县','8','632500'),('632525','贵南县','8','632500'),('632600','果洛藏族自治州','5','630000'),('632621','玛沁县','8','632600'),('632622','班玛县','8','632600'),('632623','甘德县','8','632600'),('632624','达日县','8','632600'),('632625','久治县','8','632600'),('632626','玛多县','8','632600'),('632700','玉树藏族自治州','5','630000'),('632721','玉树县','8','632700'),('632722','杂多县','8','632700'),('632723','称多县','8','632700'),('632724','治多县','8','632700'),('632725','囊谦县','8','632700'),('632726','曲麻莱县','8','632700'),('632800','海西蒙古族藏族自治州','5','630000'),('632801','格尔木市','8','632800'),('632802','德令哈市','8','632800'),('632821','乌兰县','8','632800'),('632822','都兰县','8','632800'),('632823','天峻县','8','632800'),('640000','宁夏回族自治区','3','000000'),('640100','银川市','5','640000'),('640104','兴庆区','8','640100'),('640105','西夏区','8','640100'),('640106','金凤区','8','640100'),('640121','永宁县','8','640100'),('640122','贺兰县','8','640100'),('640181','灵武市','8','640100'),('640200','石嘴山市','5','640000'),('640202','大武口区','8','640200'),('640205','惠农区','8','640200'),('640221','平罗县','8','640200'),('640300','吴忠市','5','640000'),('640302','利通区','8','640300'),('640323','盐池县','8','640300'),('640324','同心县','8','640300'),('640381','青铜峡市','8','640300'),('640400','固原市','5','640000'),('640402','原州区','8','640400'),('640422','西吉县','8','640400'),('640423','隆德县','8','640400'),('640424','泾源县','8','640400'),('640425','彭阳县','8','640400'),('640500','中卫市','5','640000'),('640502','沙坡头区','8','640500'),('640521','中宁县','8','640500'),('640522','海原县','8','640500'),('650000','新疆维吾尔自治区','3','000000'),('650100','乌鲁木齐市','5','650000'),('650102','天山区','8','650100'),('650103','沙依巴克区','8','650100'),('650104','新市区','8','650100'),('650105','水磨沟区','8','650100'),('650106','头屯河区','8','650100'),('650107','达坂城区','8','650100'),('650108','东山区','8','650100'),('650121','乌鲁木齐县','8','650100'),('650200','克拉玛依市','5','650000'),('650202','独山子区','8','650200'),('650203','克拉玛依区','8','650200'),('650204','白碱滩区','8','650200'),('650205','乌尔禾区','8','650200'),('652100','吐鲁番地区','5','650000'),('652101','吐鲁番市','8','652100'),('652122','鄯善县','8','652100'),('652123','托克逊县','8','652100'),('652200','哈密地区','5','650000'),('652201','哈密市','8','652200'),('652222','巴里坤哈萨克自治县','8','652200'),('652223','伊吾县','8','652200'),('652300','昌吉回族自治州','5','650000'),('652301','昌吉市','8','652300'),('652302','阜康市','8','652300'),('652303','米泉市','8','652300'),('652323','呼图壁县','8','652300'),('652324','玛纳斯县','8','652300'),('652325','奇台县','8','652300'),('652327','吉木萨尔县','8','652300'),('652328','木垒哈萨克自治县','8','652300'),('652700','博尔塔拉蒙古自治州','5','650000'),('652701','博乐市','8','652700'),('652722','精河县','8','652700'),('652723','温泉县','8','652700'),('652800','巴音郭楞蒙古自治州','5','650000'),('652801','库尔勒市','8','652800'),('652822','轮台县','8','652800'),('652823','尉犁县','8','652800'),('652824','若羌县','8','652800'),('652825','且末县','8','652800'),('652826','焉耆回族自治县','8','652800'),('652827','和静县','8','652800'),('652828','和硕县','8','652800'),('652829','博湖县','8','652800'),('652900','阿克苏地区','5','650000'),('652901','阿克苏市','8','652900'),('652922','温宿县','8','652900'),('652923','库车县','8','652900'),('652924','沙雅县','8','652900'),('652925','新和县','8','652900'),('652926','拜城县','8','652900'),('652927','乌什县','8','652900'),('652928','阿瓦提县','8','652900'),('652929','柯坪县','8','652900'),('653000','克孜勒苏柯尔克孜自治州','5','650000'),('653001','阿图什市','8','653000'),('653022','阿克陶县','8','653000'),('653023','阿合奇县','8','653000'),('653024','乌恰县','8','653000'),('653100','喀什地区','5','650000'),('653101','喀什市','8','653100'),('653121','疏附县','8','653100'),('653122','疏勒县','8','653100'),('653123','英吉沙县','8','653100'),('653124','泽普县','8','653100'),('653125','莎车县','8','653100'),('653126','叶城县','8','653100'),('653127','麦盖提县','8','653100'),('653128','岳普湖县','8','653100'),('653129','伽师县','8','653100'),('653130','巴楚县','8','653100'),('653131','塔什库尔干塔吉克自治县','8','653100'),('653200','和田地区','5','650000'),('653201','和田市','8','653200'),('653221','和田县','8','653200'),('653222','墨玉县','8','653200'),('653223','皮山县','8','653200'),('653224','洛浦县','8','653200'),('653225','策勒县','8','653200'),('653226','于田县','8','653200'),('653227','民丰县','8','653200'),('654000','伊犁哈萨克自治州','5','650000'),('654002','伊宁市','8','654000'),('654003','奎屯市','8','654000'),('654021','伊宁县','8','654000'),('654022','察布查尔锡伯自治县','8','654000'),('654023','霍城县','8','654000'),('654024','巩留县','8','654000'),('654025','新源县','8','654000'),('654026','昭苏县','8','654000'),('654027','特克斯县','8','654000'),('654028','尼勒克县','8','654000'),('654200','塔城地区','5','650000'),('654201','塔城市','8','654200'),('654202','乌苏市','8','654200'),('654221','额敏县','8','654200'),('654223','沙湾县','8','654200'),('654224','托里县','8','654200'),('654225','裕民县','8','654200'),('654226','和布克赛尔蒙古自治县','8','654200'),('654300','阿勒泰地区','5','650000'),('654301','阿勒泰市','8','654300'),('654321','布尔津县','8','654300'),('654322','富蕴县','8','654300'),('654323','福海县','8','654300'),('654324','哈巴河县','8','654300'),('654325','青河县','8','654300'),('654326','吉木乃县','8','654300'),('659000','省直辖行政单位','5','650000'),('659001','石河子市','8','659000'),('659002','阿拉尔市','8','659000'),('659003','图木舒克市','8','659000'),('659004','五家渠市','8','659000'),('710000','台湾省','2','000000'),('810000','香港特别行政区','4','000000'),('820000','澳门特别行政区','4','000000');
/*!40000 ALTER TABLE `region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `roleId` varchar(50) NOT NULL,
  `roleName` varchar(50) DEFAULT NULL,
  `addTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='系统角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roleright`
--

DROP TABLE IF EXISTS `roleright`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roleright` (
  `roleId` varchar(50) NOT NULL COMMENT '记录主键',
  `functionModuleId` varchar(50) NOT NULL COMMENT '功能模块主键，外键',
  `addTime` datetime DEFAULT NULL COMMENT '记录添加时间',
  PRIMARY KEY (`roleId`,`functionModuleId`),
  KEY `AK_Identifier_1` (`roleId`,`functionModuleId`),
  KEY `FK_RoleRight_FunctionModule` (`functionModuleId`),
  CONSTRAINT `FK_RoleRight_FunctionModule` FOREIGN KEY (`functionModuleId`) REFERENCES `functionmodule` (`moduleId`),
  CONSTRAINT `FK_RoleRight_Role` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='描述系统角色拥有哪些功能模块权限的中间表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roleright`
--

LOCK TABLES `roleright` WRITE;
/*!40000 ALTER TABLE `roleright` DISABLE KEYS */;
/*!40000 ALTER TABLE `roleright` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systemuser`
--

DROP TABLE IF EXISTS `systemuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `systemuser` (
  `userId` varchar(50) NOT NULL COMMENT '记录主键',
  `userName` varchar(50) DEFAULT NULL COMMENT '登录用户名',
  `realName` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `gender` char(1) DEFAULT NULL COMMENT '用户性别，0为男，1为女，默认为0',
  `onlineFlag` char(1) DEFAULT NULL COMMENT '在线状态，0为在线，1为不在线',
  `deleteFlag` char(1) DEFAULT NULL COMMENT '注销标识，0为活跃用户，1为已注销用户',
  `idNumber` varchar(50) DEFAULT NULL COMMENT '用户身份证号',
  `password` varchar(50) DEFAULT NULL COMMENT '系统登录密码',
  `description` text COMMENT '用户简介',
  `telephone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `photo` longblob COMMENT '用户照片',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱地址',
  `addTime` datetime DEFAULT NULL COMMENT '本条记录添加时间',
  `updateTime` datetime DEFAULT NULL COMMENT '本条记录最后一次修改时间',
  `hospitalId` varchar(50) DEFAULT NULL,
  `hospitalSectionOfficeId` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  KEY `FK_SystemUser_Hospital` (`hospitalId`),
  KEY `FK_SystemUser_HospitalSectionOffice` (`hospitalSectionOfficeId`),
  CONSTRAINT `FK_SystemUser_Hospital` FOREIGN KEY (`hospitalId`) REFERENCES `hospital` (`id`),
  CONSTRAINT `FK_SystemUser_HospitalSectionOffice` FOREIGN KEY (`hospitalSectionOfficeId`) REFERENCES `hospitalsectionoffice` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='系统用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systemuser`
--

LOCK TABLES `systemuser` WRITE;
/*!40000 ALTER TABLE `systemuser` DISABLE KEYS */;
/*!40000 ALTER TABLE `systemuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userrole`
--

DROP TABLE IF EXISTS `userrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userrole` (
  `userId` varchar(50) NOT NULL COMMENT '系统用户，外键',
  `roleId` varchar(50) NOT NULL COMMENT '系统角色，外键',
  `addTime` datetime DEFAULT NULL COMMENT '记录添加时间',
  PRIMARY KEY (`userId`,`roleId`),
  KEY `FK_UserRole_Role` (`roleId`),
  CONSTRAINT `FK_RoleRight_SystemUser` FOREIGN KEY (`userId`) REFERENCES `systemuser` (`userId`),
  CONSTRAINT `FK_UserRole_Role` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='用户所属角色中间表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userrole`
--

LOCK TABLES `userrole` WRITE;
/*!40000 ALTER TABLE `userrole` DISABLE KEYS */;
/*!40000 ALTER TABLE `userrole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `webpage`
--

DROP TABLE IF EXISTS `webpage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `webpage` (
  `pageId` varchar(50) NOT NULL,
  `url` varchar(500) DEFAULT NULL,
  `pageName` varchar(50) DEFAULT NULL,
  `description` text,
  `addTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`pageId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='存放系统中所有页面信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `webpage`
--

LOCK TABLES `webpage` WRITE;
/*!40000 ALTER TABLE `webpage` DISABLE KEYS */;
/*!40000 ALTER TABLE `webpage` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-11-14 10:16:30
