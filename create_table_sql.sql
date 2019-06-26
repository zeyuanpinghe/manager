/*
 Navicat Premium Data Transfer

 Source Server         : apricot_woods
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : apricot_woods

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 18/03/2019 15:49:23
*/


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

#创建用户安全表
-- ----------------------------
-- Table structure for user_tb
-- ----------------------------
DROP TABLE IF EXISTS user_tb;
CREATE TABLE user_tb  (
  ID int(6) NOT NULL AUTO_INCREMENT COMMENT '自增长用户表ID',
  PHONENUMBER varchar(11) NULL DEFAULT NULL COMMENT '手机号码唯一',
  EMAIL varchar(50) CHARACTER SET utf8  NULL DEFAULT NULL COMMENT '邮箱',
  PASSWORD varchar(500) CHARACTER SET utf8  NULL DEFAULT NULL COMMENT '密码',
  CREATETIME timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间，默认为当前时间',
  UPDATETIME timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  ROLEID int(6) NULL DEFAULT NULL COMMENT '角色ID',
  NAMED varchar(50) CHARACTER SET utf8  NULL DEFAULT NULL COMMENT '用户称号',
  STATU varchar(1) CHARACTER SET utf8  NULL DEFAULT 'U' COMMENT '状态标识，U：有效，E：失效',
  TOKEN varchar(200) CHARACTER SET utf8  NULL DEFAULT NULL COMMENT '用户登录时生成token',
  PRIMARY KEY (ID) USING BTREE,
  UNIQUE INDEX PHONENUMBER(PHONENUMBER) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COMMENT = '用户安全表' ROW_FORMAT = Dynamic;


#创建权限信息表
-- ----------------------------
-- Table structure for role_tb
-- ----------------------------
DROP TABLE IF EXISTS role_tb;
CREATE TABLE role_tb  (
  ID int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长角色表ID',
  ROLE_LEVEL varchar(50) CHARACTER SET utf8  NULL DEFAULT NULL COMMENT 'ILL：病人，VOLUN：志愿者，ADMINF：一级管理员，ADMINS：二级管理员',
  CREATETIME timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间，默认为当前时间',
  UPDATETIME timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  ROLEDESC varchar(200) CHARACTER SET utf8  NULL DEFAULT NULL COMMENT '角色描述',
  STATU varchar(1) CHARACTER SET utf8  NULL DEFAULT 'U' COMMENT '状态标识，U：有效，E：失效',
  PRIMARY KEY (ID) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COMMENT = '权限信息表' ROW_FORMAT = Dynamic;


#创建预约表
-- ----------------------------
-- Table structure for reserv_tb
-- ----------------------------
DROP TABLE IF EXISTS reserv_tb;
CREATE TABLE reserv_tb  (
  ID int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长预约表ID',
  GENDER VARCHAR(1)  NULL DEFAULT NULL COMMENT '1:god,2:goddess',
  USERID int(11) NULL DEFAULT NULL COMMENT '用户表ID',
  HOSPITALID int(11) NULL DEFAULT NULL COMMENT '医院ID',
  RESERVTIME timestamp(0) NULL DEFAULT NULL COMMENT '求诊时间',
  RESERVSTATU int(11) NULL DEFAULT NULL COMMENT '预约状态，1:待陪诊匹配，2:匹配，3：完成',
  PHONENUM varchar(20) NULL DEFAULT NULL COMMENT '联系电话',
  STATU  varchar(20) NULL DEFAULT NULL COMMENT 'U:正常订单，E：非正常结束订单',
  CREATETIME timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  UPDATETIME timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  EMAIL varchar(50) CHARACTER SET utf8  NULL DEFAULT NULL COMMENT '联络邮箱',
  CALLNAME  varchar(49) NULL DEFAULT NULL COMMENT '订单中的称呼',
  SYMPTOM varchar(2000) NULL DEFAULT NULL COMMENT '症状' ,
  VOLUNID int(2) NULL DEFAULT NULL COMMENT '志愿者ID' ,
  PRIMARY KEY (ID) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COMMENT = '预约表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

#创建用户登录表
-- ----------------------------
-- Table structure for login_tb
-- ----------------------------
DROP TABLE IF EXISTS login_tb;
CREATE TABLE login_tb  (
  ID int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长登录表ID',
  IMEI varchar(200) CHARACTER SET utf8  NULL DEFAULT NULL COMMENT '手机IMSI码',
  TOKEN varchar(200) CHARACTER SET utf8  NOT NULL COMMENT '生成的登录TOKEN,用户ID+密码+IMEI码生成MD5码作为TOKEN',
  USERID int(11) NULL DEFAULT NULL COMMENT '用户表ID',
  PHONENUM varchar(11) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '用户登录手机号码',
  EMAIL varchar(100) CHARACTER SET utf8  NULL DEFAULT NULL COMMENT '用户登录email帐号',
  LOGINTIME timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '登录时间，默认为当前时间',
  PRIMARY KEY (ID) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COMMENT = '用户登录表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;


#创建医院信息表
-- ----------------------------
-- Table structure for hospital_tb
-- ----------------------------
DROP TABLE IF EXISTS hospital_tb;
CREATE TABLE hospital_tb  (
  ID int(6) NOT NULL AUTO_INCREMENT COMMENT '医院信息表ID',
  HOSPITALNAME varchar(200) CHARACTER SET utf8  NULL DEFAULT NULL COMMENT '医院名称',
  PHOTOPATH varchar(200) CHARACTER SET utf8  COMMENT '医院图片路径',
  THERAPYID varchar(200) CHARACTER SET utf8  COMMENT '医院疗法ID，多个以英文“;”分割',
  Hospitalbrief varchar(8000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '医院简介',
  HOSPDESC  varchar(200) CHARACTER SET utf8  COMMENT '医院描述简写，以进行判断是否需要对前端数据进行替换',
  CREATETIME timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间，默认为当前时间',
  UPDATETIME timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (ID) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COMMENT = '医院信息表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

#创建医院地址表
-- ----------------------------
-- Table structure for address_tb
-- ----------------------------
DROP TABLE IF EXISTS address_tb;
CREATE TABLE address_tb  (
  ID int(6) NOT NULL AUTO_INCREMENT COMMENT '自增长医院地址表ID',
  HOSTPITALID int(6) COMMENT '医院信息ID',
  ADDRESS varchar(200) NULL DEFAULT NULL COMMENT '医院地址',
  BUS varchar(200) CHARACTER SET utf8  NULL DEFAULT NULL COMMENT '公交到达方式',
  METRA varchar(200) CHARACTER SET utf8  NULL DEFAULT NULL COMMENT '地铁到方式',
  CREATETIME timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间，默认为当前时间',
  UPDATETIME timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (ID) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COMMENT = '医院地址表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

#创建中医疗法表
-- ----------------------------
-- Table structure for therapy_tb
-- ----------------------------
DROP TABLE IF EXISTS therapy_tb;
CREATE TABLE therapy_tb  (
  ID int(6) NOT NULL AUTO_INCREMENT COMMENT '自增长疗法表ID',
  THERNAME varchar(200) NULL DEFAULT NULL COMMENT '疗法名称',
  INTROALL varchar(200) CHARACTER SET utf8  NULL DEFAULT NULL COMMENT '疗法详细描述',
  INTROBRIEF varchar(200) CHARACTER SET utf8  NULL DEFAULT NULL COMMENT '疗法简单描述',
  THERDESC  varchar(200) CHARACTER SET utf8  COMMENT '疗法描述简写，以进行判断是否需要对前端数据进行替换',
  CREATETIME timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间，默认为当前时间',
  UPDATETIME timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (ID) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COMMENT = '中医疗法表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

#创建用户登出表
-- ----------------------------
-- Table structure for logout_tb
-- ----------------------------
DROP TABLE IF EXISTS logout_tb;
CREATE TABLE logout_tb  (
  ID int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长登出表ID',
  TOKEN varchar(200) CHARACTER SET utf8  NOT NULL COMMENT '生成的登录TOKEN,用户ID+密码+IMEI码生成MD5码作为TOKEN',
  USERID int(11)  NOT NULL COMMENT '用户表ID',
  LOGOUTTIME timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '登出时间，默认为当前时间',
  PRIMARY KEY (ID) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COMMENT = '用户登出表';

SET FOREIGN_KEY_CHECKS = 1;

DROP TABLE IF EXISTS message_tb;
CREATE TABLE message_tb  (
  ID int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长消息表ID',
  RESERVID INT(11) NOT NULL COMMENT '预约订单ID',
  USERID int(11)  NOT NULL COMMENT '用户表ID',
  MESGSTATU VARCHAR(1) default '1' COMMENT '消息状态码，1:未读，2:已读，3:删除',
  RESERVSTATU int(11) NULL DEFAULT NULL COMMENT '预约状态，1:待陪诊匹配，2:匹配，3：完成',
  PRIMARY KEY (ID) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COMMENT = '消息通知表';


#角色信息录入
INSERT INTO `role_tb`(`ID`, `ROLE_LEVEL`, `CREATETIME`, `UPDATETIME`, `ROLEDESC`, `STATU`) VALUES (1, 'ILL', '2019-03-13 20:07:53', NULL, '病患', 'U');
INSERT INTO `role_tb`(`ID`, `ROLE_LEVEL`, `CREATETIME`, `UPDATETIME`, `ROLEDESC`, `STATU`) VALUES (2, 'VOLUN', '2019-03-13 20:08:07', NULL, '志愿者', 'U');
INSERT INTO `role_tb`(`ID`, `ROLE_LEVEL`, `CREATETIME`, `UPDATETIME`, `ROLEDESC`, `STATU`) VALUES (3, 'ADMINF', '2019-03-13 20:10:51', NULL, '一级管理员', 'U');
INSERT INTO `role_tb`(`ID`, `ROLE_LEVEL`, `CREATETIME`, `UPDATETIME`, `ROLEDESC`, `STATU`) VALUES (4, 'ADMINS', '2019-03-13 20:36:30', NULL, '二级管理员', 'U');
#医院信息录入
INSERT INTO hospital_tb (ID, HOSPITALNAME, PHOTOPATH, THERAPYID, HOSPDESC, CREATETIME, UPDATETIME) VALUES ('1', 'Shuguang Hospital', '', '', '曙光医院', '2019-03-22 21:50:44', NULL);
INSERT INTO hospital_tb (ID, HOSPITALNAME, PHOTOPATH, THERAPYID, HOSPDESC, CREATETIME, UPDATETIME) VALUES ('2', 'Yueyang Hospital', NULL, NULL, '岳阳医院', '2019-05-02 04:10:58', NULL);
INSERT INTO hospital_tb (ID, HOSPITALNAME, PHOTOPATH, THERAPYID, HOSPDESC, CREATETIME, UPDATETIME) VALUES ('3', 'Longhua Hospital', NULL, NULL, '龙华医院', '2019-05-02 04:11:21', NULL);
INSERT INTO hospital_tb (ID, HOSPITALNAME, PHOTOPATH, THERAPYID, HOSPDESC, CREATETIME, UPDATETIME) VALUES ('4', 'Shanghai Traditional Chinese medicine Hospital', NULL, NULL, '上海市中医医院', '2019-05-02 04:11:28', NULL);
INSERT INTO hospital_tb (ID, HOSPITALNAME, PHOTOPATH, THERAPYID, HOSPDESC, CREATETIME, UPDATETIME) VALUES ('5', 'Shanghai TCM-integrated Hospital', NULL, NULL, '上海市中西医结合医院', '2019-05-02 04:11:40', NULL);

#志愿者信息录入
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('18221715997','784154698@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','陈曦语','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('13629436309','why199943@163.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','杨惠','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('13601629902','809175609@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','吴奕涵','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('18339267980','1397222082@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','岳帅','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('18780946424','2661293028@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','王丽春','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('18810079216','821702285@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','张婷婷','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('18201830651','1196015754@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','张梦蝶','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('13120551416','1263312361@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','程玉琳','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('13321938535','953035541@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','胡卓妍','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('18201836306','2499673861@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','孙洁月','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('18621825958','wo_xinxing@126.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','张诗琦','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('18040313246','961393397@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','陈鋆玮','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('18388906265','849012704@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2',' 高中制 ','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('18224041976','2467112707@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','谢源源','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('18201751817','1054264077@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','冯儒澜','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('18217093193','1585226006@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','杨嘉灵','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('18201815751','2474336873@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','张涛涛','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('19921253161',NULL,'73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','李晓梅','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('18302120015','1796193735@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','朱邵霆','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('19921842661','879712272@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','祖拉兰','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('18857557330','1291683526@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','宣雅雯','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('18117589372','785024125@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','沈依薇','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('13016519977','156931722@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','徐浩展','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('18516219884','zhuangjymiao@126.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','庄婧妍','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('19921253175','1144100032@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','李玉珊','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('15282881709','2967226628@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','张贵振','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('15921489284','2464287172@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','赵珂乐','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('13966174424','2086795580@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','董辰雨','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('17390368065','1056874570@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','林雨佳','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('16621394007','1076242836@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','葛巍','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('13918562932','1528900120@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','沈博艺','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('13564030920','1473949943@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','韦从蓉','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('18298780019','2750046015@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','赵紫琼','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('17317587679','1163613464@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','胡雯茜','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('17717044124','1205001379@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','李申锁','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('15901955947','735798028@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','程诗雨','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('18217758986','1160402579@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','龚晨婷','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('13916974698','13916974698@163.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','刘雨昕','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('15614675783','1220579492@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','黄硕','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('19921842583','1191530357@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','史汶龙','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('17621912669','1098097852@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','金敏','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('13626571600','992052002@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','郑慧敏','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('15514412848','3305841634@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','郭银婷','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('17608915270','2298274756@qq.con','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','谭秋玲','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('18336330028','1196985213@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','尚剑辉','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('15138309431','3229181310@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','孔富康','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('18019117973','1692997497@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','周茹霞','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('17317138623','1010811355@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','蒋笑仪','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('18116227003','550900473@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','沈昱','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('18621826570','chenxiaomin186@163.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','陈晓敏','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('15821616770','yuyinjie1998@163.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','虞寅杰','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('19921253139','2860709523@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','阿布都','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('18621153679','18393366905@163.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','何云云','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('18916510733','954144317@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','吴静言','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('15900573360','526304805@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','陶斯阳','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('13816029758','2370553222@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','陶茱婷','U');
INSERT INTO user_tb (PHONENUMBER,EMAIL,PASSWORD,ROLEID,NAMED,STATU)VALUES('15055844863','1959220279@qq.com','73A3A62FC48A9CA22FAFDBD3BE06B0CC','2','韩子怡','U');