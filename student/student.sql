/*
Navicat MySQL Data Transfer

Source Server         : gs
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : student

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-04-04 16:08:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) DEFAULT NULL COMMENT '姓名',
  `EMAIL` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `CLASS_ID` int(11) DEFAULT NULL COMMENT '班级id',
  `PHONE` varchar(12) DEFAULT NULL COMMENT '手机号',
  `DORM_ID` int(11) DEFAULT NULL COMMENT '宿舍id',
  `SEX` varchar(10) DEFAULT NULL COMMENT '性别',
  `AGE` int(11) DEFAULT NULL COMMENT '年龄',
  `CREATE_DATE` datetime DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `CLASS_NAME` varchar(255) DEFAULT '' COMMENT '班级名称',
  `STD_NUMBER` varchar(20) NOT NULL COMMENT '学号',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('5', '张兴隆', '', '5', '', '1', '男', null, '2019-04-03 17:40:36', null, '', '10010');
INSERT INTO `student` VALUES ('6', '张三丰', '', '4', '', '1', '男', '0', '2019-04-03 17:44:56', null, '', '10012');
INSERT INTO `student` VALUES ('7', '张无忌', '', '5', '', '1', '男', null, '2019-04-03 17:49:46', null, '', '10013');
INSERT INTO `student` VALUES ('8', '杨过', '', '5', '', '1', '男', null, '2019-04-03 17:50:05', null, '', '10014');
INSERT INTO `student` VALUES ('9', '郭靖', '', '5', '', '1', '男', null, '2019-04-03 17:50:31', null, '', '10015');
INSERT INTO `student` VALUES ('10', '令狐冲', '', '5', '', '2', '男', null, '2019-04-03 17:51:16', null, '', '10016');
INSERT INTO `student` VALUES ('11', '赵敏', '', '5', '', '3', '女', null, '2019-04-03 17:52:02', null, '', '10017');
INSERT INTO `student` VALUES ('12', '周芷若', '', '5', '', '3', '女', null, '2019-04-03 17:52:25', null, '', '10018');
INSERT INTO `student` VALUES ('13', '郭襄', '', '5', '', '3', '女', null, '2019-04-03 17:52:57', null, '', '10019');
INSERT INTO `student` VALUES ('14', '黄蓉', '', '5', '', '3', '女', null, '2019-04-03 17:53:24', null, '', '10011');
INSERT INTO `student` VALUES ('15', '郭芙', '', '5', '', '3', '女', null, '2019-04-03 17:54:15', null, '', '10020');

-- ----------------------------
-- Table structure for st_class
-- ----------------------------
DROP TABLE IF EXISTS `st_class`;
CREATE TABLE `st_class` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CLASS_NAME` varchar(100) DEFAULT NULL COMMENT '班级名称',
  `TEACHER_NAME` varchar(50) DEFAULT NULL COMMENT '班主任名称',
  `TEACHER_NUM` varchar(50) DEFAULT NULL COMMENT '班主任编号',
  `CREATE_DATE` datetime DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `TEACHER_PHONE` varchar(12) DEFAULT NULL COMMENT '班主任电话',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of st_class
-- ----------------------------
INSERT INTO `st_class` VALUES ('4', '大数据分析101', '曹操', '1001', '2019-04-03 17:25:31', null, '1891486643');
INSERT INTO `st_class` VALUES ('5', '大数据分析102', '刘备', '1002', '2019-04-03 17:25:53', null, '1891486633');
INSERT INTO `st_class` VALUES ('6', '计算机科学102', '刘备', '1002', '2019-04-03 17:26:28', null, '1891486633');
INSERT INTO `st_class` VALUES ('7', '机电102', '张飞', '1102', '2019-04-03 17:26:59', null, '1591478633');
INSERT INTO `st_class` VALUES ('8', '机电101', '张飞', '1102', '2019-04-03 17:27:07', null, '1591478633');
INSERT INTO `st_class` VALUES ('9', '机械101', '巩俐', '1011', '2019-04-04 09:28:28', null, '18796342283');
INSERT INTO `st_class` VALUES ('10', '机械102', '巩俐', '1011', '2019-04-04 09:28:48', null, '18796342283');
INSERT INTO `st_class` VALUES ('11', '机械103', '巩俐', '1011', '2019-04-04 09:28:52', null, '18796342283');
INSERT INTO `st_class` VALUES ('12', '化工分析101', '乔峰', '1011', '2019-04-04 09:29:33', null, '18796342283');
INSERT INTO `st_class` VALUES ('13', '化工分析102', '乔峰', '1011', '2019-04-04 09:29:38', null, '18796342283');
INSERT INTO `st_class` VALUES ('14', '化工分析103', '乔峰', '1011', '2019-04-04 09:29:42', null, '18796342283');
INSERT INTO `st_class` VALUES ('17', '机电103', '张无忌', '100112', '2019-04-04 15:50:20', null, '1');

-- ----------------------------
-- Table structure for st_dorm
-- ----------------------------
DROP TABLE IF EXISTS `st_dorm`;
CREATE TABLE `st_dorm` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL COMMENT '宿舍名称',
  `MAX_NUM` int(11) DEFAULT NULL COMMENT '床位',
  `SURPLUS_NUM` int(11) DEFAULT NULL COMMENT '剩余床位',
  `CREATE_DATE` datetime DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `SEX` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of st_dorm
-- ----------------------------
INSERT INTO `st_dorm` VALUES ('1', 'C1-101', '5', '5', '2019-04-02 16:20:40', '2019-04-03 09:09:44', '男');
INSERT INTO `st_dorm` VALUES ('2', 'C1-102', '6', '1', '2019-04-03 09:04:18', null, '男');
INSERT INTO `st_dorm` VALUES ('3', 'C2-101', '6', '5', '2019-04-03 17:27:46', null, '女');
INSERT INTO `st_dorm` VALUES ('4', 'C2-102', '6', '0', '2019-04-03 17:27:52', null, '女');
INSERT INTO `st_dorm` VALUES ('5', 'C2-103', '6', '0', '2019-04-03 17:28:00', null, '女');
INSERT INTO `st_dorm` VALUES ('6', 'C2-104', '6', '0', '2019-04-03 17:28:06', null, '女');
INSERT INTO `st_dorm` VALUES ('7', 'C2-105', '6', '0', '2019-04-03 17:28:12', null, '女');
INSERT INTO `st_dorm` VALUES ('8', 'C2-106', '6', '0', '2019-04-03 17:28:17', null, '女');
INSERT INTO `st_dorm` VALUES ('9', 'C2-107', '6', '0', '2019-04-04 09:25:50', null, '女');
INSERT INTO `st_dorm` VALUES ('10', 'C2-108', '6', '0', '2019-04-04 09:25:57', null, '女');
INSERT INTO `st_dorm` VALUES ('11', 'C2-109', '6', '0', '2019-04-04 09:26:03', null, '女');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCOUNT` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `SALT` varchar(255) DEFAULT NULL,
  `ROLE_ID` int(11) DEFAULT NULL COMMENT '(1:管理员 2:学生)',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '001', '超级管理员', 'a77d90ae9b99a6c39a75f14c4d52981f', 'b60142d315e4596cd2d8744352ffd1ec', '0');
INSERT INTO `user` VALUES ('3', '003', '吴奇隆', '4cbacd613874f0eab29c91a7ba0e48f9', '10af1d73df8b4e98a84577a1e217e61a', '1');
INSERT INTO `user` VALUES ('4', '10010', '张兴隆', 'f3a742aa37111821f02cedfa9b600dd4', 'b271a2544f9149d48b7921c4b4e33e07', '1');
INSERT INTO `user` VALUES ('5', '10011', '问问', '195de9e15be6679b930ff9443567b39d', '96db79f44c994bc89e56448dc26de89e', '0');
INSERT INTO `user` VALUES ('6', '10012', '张飞', 'a075476584434e4041cf6ed690d55b84', '2458834b7cc341378d328f4c1dd6df2e', '0');
INSERT INTO `user` VALUES ('7', '10013', '张雨', '3c2dd5eeba74ce5f188175334b11e92c', '62739125ac4b424e9ba665ca1975a9e1', '1');
INSERT INTO `user` VALUES ('8', '10014', '赵丽颖', 'b156ada7d6cccf03b3e58a3e22c3b957', '4cb801e7927b433fa04763e6600ec781', '1');
INSERT INTO `user` VALUES ('9', '10015', '周杰伦', 'ae62b16d6f6d42dfa10ecbb4198f5192', '4206e45a9f2e4682a921e89eea395c1e', '1');
INSERT INTO `user` VALUES ('10', '10016', '周星驰', '209c5b9f094fe5b3e265ec692c374ab6', '26b7886e2d2449fd86e8a211df4257a3', '1');
INSERT INTO `user` VALUES ('11', '10017', '周润发', '433fe8feafd9398b8be6fdb7c26a1380', '7c9e7f85ca0c4aa6abfa91b3c4ea9cf3', '1');
INSERT INTO `user` VALUES ('12', '10018', '邓超', 'bb90b4a6de1c329991e0e5d29996debd', '4d8195d8e4dd4d88b71602e6d1272b4a', '1');
INSERT INTO `user` VALUES ('13', '10021', '杨颖', '226dab594016c003d696e9c643d6eea7', 'af901d12c1234ff38091487db895f800', '1');
INSERT INTO `user` VALUES ('16', '20010', 'admin', '471ad44e0818568d7079c21a7a986f3d', '6ecf24958772468291e92400d49c71b6', '1');
