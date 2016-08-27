/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : mystock

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-08-27 21:30:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menuid` int(11) NOT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `menuname` varchar(255) DEFAULT NULL,
  `menutype` int(11) DEFAULT NULL,
  `menuurl` varchar(255) DEFAULT NULL,
  `ordernum` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `labername` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`menuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('10', 'icon1.png', '个人事务', '0', '../menu/personal-affairs.html', '0', '-1', '个人事务');
INSERT INTO `menu` VALUES ('20', 'icon3.png', '学习资料', '0', '../datuminfo/datuminfoStudent.jsp', '1', '-1', '学习资料');
INSERT INTO `menu` VALUES ('30', 'icon2.png', '朋辈辅学', '0', '../menu/tutoring-answer.html', '2', '-1', '朋辈辅学');
INSERT INTO `menu` VALUES ('40', 'icon4.png', '二手书店', '0', '../secondbookstore/secondbookstore.jsp', '3', '-1', '二手书店');
INSERT INTO `menu` VALUES ('50', 'icon5.png', '专家预约', '0', '../university/expertbespeak/studentbespeak.jsp', '4', '-1', '专家预约');
INSERT INTO `menu` VALUES ('60', 'icon6.png', '记实考评', '0', '../university/recordcheck/recordcheck.jsp', '5', '-1', '记实考评');
INSERT INTO `menu` VALUES ('70', 'icon7.png', '请假审批', '0', '../leaveinfo/leaveinfoApproval.jsp', '6', '-1', '请假审批辅导员');
INSERT INTO `menu` VALUES ('80', 'icon7.png', '请假审批', '0', '../leaveinfo/leaveinfoLeader.jsp', '7', '-1', '请假审批学园领导');
INSERT INTO `menu` VALUES ('90', 'icon8.png', '场地审批', '0', '../siteinfo/sitecheck.jsp', '8', '-1', '场地审批');
INSERT INTO `menu` VALUES ('100', 'icon12.png', '去向统计', '0', '../university/whereabouts/whereaboutsforteacher.jsp', '9', '-1', '去向统计查询');
INSERT INTO `menu` VALUES ('110', 'icon9.png', '特别关心', '0', '../specialcare/specialcare.jsp', '10', '-1', '特别关心');
INSERT INTO `menu` VALUES ('120', 'icon3.png', '学习资料', '0', '../datuminfo/datuminfoTeacher.jsp', '11', '-1', '学习资料老师端');
INSERT INTO `menu` VALUES ('150', 'icon5.png', '我的预约', '0', '../university/expertbespeak/expertbespeak.jsp', '14', '-1', '我的预约');
INSERT INTO `menu` VALUES ('160', 'icon10.png', '活动管理', '0', '../menu/activity-managemen.html', '15', '-1', '活动管理');
INSERT INTO `menu` VALUES ('170', 'icon11.png', '场地管理', '0', '../siteinfo/sitemanage.jsp', '16', '-1', '场地管理');
INSERT INTO `menu` VALUES ('180', 'icon12.png', '去向统计', '0', '../university/whereabouts/whereaboutslaunch.jsp', '17', '-1', '去向统计管理');
INSERT INTO `menu` VALUES ('190', 'icon3.png', '学习资料', '0', '../datuminfo/datuminfomange.jsp', '18', '-1', '学习资料管理');
INSERT INTO `menu` VALUES ('200', 'icon2.png', '辅学管理', '0', '../menu/tutoring-answer-admin.html', '19', '-1', '辅学管理');
INSERT INTO `menu` VALUES ('210', 'icon4.png', '书店管理', '0', '../secondbookstore/secondbookstoremanage.jsp', '20', '-1', '书店管理');
INSERT INTO `menu` VALUES ('220', 'icon5.png', '专家管理', '0', '../menu/appointment-experts.html', '21', '-1', '专家管理');
INSERT INTO `menu` VALUES ('230', 'icon13.png', '系统设置', '0', '../menu/system-setting.html', '22', '-1', '系统设置');
INSERT INTO `menu` VALUES ('240', 'icon14.png', '个人信息', '0', '../personinfo/alterInfo.jsp', '23', '-1', '个人信息');
INSERT INTO `menu` VALUES ('250', 'icon10.png', '活动创建', '0', '../admin/activity/activity.jsp', '24', '-1', '活动创建');
INSERT INTO `menu` VALUES ('260', 'icon10.png', '新闻发布', '0', '../admin/news/newsPublish.jsp', '25', '-1', '新闻发布');
