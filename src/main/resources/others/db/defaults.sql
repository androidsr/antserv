/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 30/01/2019 13:59:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_buttons
-- ----------------------------
DROP TABLE IF EXISTS `sys_buttons`;
CREATE TABLE `sys_buttons`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '标题',
  `click` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '事件',
  `icon` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '图标',
  `state` char(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_buttons
-- ----------------------------
INSERT INTO `sys_buttons` VALUES (1, '查询', 'query', NULL, '1');
INSERT INTO `sys_buttons` VALUES (2, '新增', 'add', NULL, '1');
INSERT INTO `sys_buttons` VALUES (3, '编辑', 'edit', NULL, '0');
INSERT INTO `sys_buttons` VALUES (4, '删除', 'del', NULL, '0');
INSERT INTO `sys_buttons` VALUES (5, '详情', 'detail', NULL, '0');

-- ----------------------------
-- Table structure for sys_menus
-- ----------------------------
DROP TABLE IF EXISTS `sys_menus`;
CREATE TABLE `sys_menus`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `TITLE` varchar(25) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '名称',
  `MENU_KEY` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '路径',
  `MENU_LEVEL` char(1) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '级别',
  `ICON` varchar(25) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '图标',
  `SUPER_ID` int(11) DEFAULT NULL COMMENT '父ID',
  `BTN_ID` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '按钮权限',
  `MENU_ORDER` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_menus
-- ----------------------------
INSERT INTO `sys_menus` VALUES (1, '系统管理', '', '1', 'h-icon-home', NULL, '', 1);
INSERT INTO `sys_menus` VALUES (2, '菜单管理', '/menu', '2', NULL, 1, '1,2,3,4,5', 2);
INSERT INTO `sys_menus` VALUES (3, '角色管理', '/role', '2', NULL, 1, '1,2,3,4,5', 3);
INSERT INTO `sys_menus` VALUES (4, '参数管理', '/params', '2', NULL, 1, '1,2,3,4,5', 4);
INSERT INTO `sys_menus` VALUES (5, '按钮管理', '/button', '2', NULL, 1, '1,2,3,4,5', 5);
INSERT INTO `sys_menus` VALUES (8, '用户管理', '/user', '2', NULL, 1, '1,2,3,4,5', NULL);

-- ----------------------------
-- Table structure for sys_params
-- ----------------------------
DROP TABLE IF EXISTS `sys_params`;
CREATE TABLE `sys_params`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PARAM_NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '键名称',
  `PARAM_VALUE` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '键值',
  `GROUP_NAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '分组名称',
  `GROUP_ID` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '分组ID',
  `OTHER_VALUE` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '业务关联值',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_params
-- ----------------------------
INSERT INTO `sys_params` VALUES (1, '一级菜单', '1', '菜单级别', 'MENU_LEVEL', '3');
INSERT INTO `sys_params` VALUES (2, '二级菜单', '2', '菜单级别', 'MENU_LEVEL', NULL);
INSERT INTO `sys_params` VALUES (5, '启用', '1', '按钮状态', 'BUTTON_STATE', NULL);
INSERT INTO `sys_params` VALUES (6, '禁用', '0', '按钮状态', 'BUTTON_STATE', NULL);

-- ----------------------------
-- Table structure for sys_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles`;
CREATE TABLE `sys_roles`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ROLE_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '名称',
  `MENU_IDS` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_roles
-- ----------------------------
INSERT INTO `sys_roles` VALUES (3, '超级管理员', '1,2,3,4,5,8,8-1,8-2,8-3,8-4,8-5,2-1,2-2,2-3,2-4,2-5,3-1,3-2,3-3,3-4,3-5,4-1,4-2,4-3,4-4,4-5,5-1,5-2,5-3,5-4,5-5');

-- ----------------------------
-- Table structure for sys_users
-- ----------------------------
DROP TABLE IF EXISTS `sys_users`;
CREATE TABLE `sys_users`  (
  `ID` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户账号',
  `NAME` varchar(25) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `PHONE` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '电话',
  `EMAIL` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '邮箱',
  `SEX` int(11) DEFAULT NULL COMMENT '性别',
  `AGE` int(11) DEFAULT NULL COMMENT '年龄',
  `PASSWD` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `ROLE_ID` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_users
-- ----------------------------
INSERT INTO `sys_users` VALUES ('sirui', '司锐', '15308066831', '123123', 1, 33, '96e79218965eb72c92a549dd5a330112', '3');
INSERT INTO `sys_users` VALUES ('test', '测试', '12321321', '123123', 1, 33, '96e79218965eb72c92a549dd5a330112', '3');

SET FOREIGN_KEY_CHECKS = 1;
