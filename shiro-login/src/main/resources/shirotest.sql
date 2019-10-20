/*
Navicat MySQL Data Transfer

Source Server         : Hello
Source Server Version : 50622
Source Host           : localhost:3306
Source Database       : shirotest

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2019-10-21 00:00:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for s_permission
-- ----------------------------
DROP TABLE IF EXISTS `s_permission`;
CREATE TABLE `s_permission` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL COMMENT 'url地址',
  `name` varchar(255) DEFAULT NULL COMMENT 'url描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_permission
-- ----------------------------
INSERT INTO `s_permission` VALUES ('1', '/user', 'user:user');
INSERT INTO `s_permission` VALUES ('2', '/user/add', 'user:add');
INSERT INTO `s_permission` VALUES ('3', '/user/delete', 'user:delete');

-- ----------------------------
-- Table structure for s_role
-- ----------------------------
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `desc` varchar(255) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_role
-- ----------------------------
INSERT INTO `s_role` VALUES ('1', 'admin', '超级管理员');
INSERT INTO `s_role` VALUES ('2', 'test', '测试账户');

-- ----------------------------
-- Table structure for s_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `s_role_permission`;
CREATE TABLE `s_role_permission` (
  `rid` int(10) DEFAULT NULL COMMENT '角色id',
  `pid` int(10) DEFAULT NULL COMMENT '权限id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_role_permission
-- ----------------------------
INSERT INTO `s_role_permission` VALUES ('1', '2');
INSERT INTO `s_role_permission` VALUES ('1', '3');
INSERT INTO `s_role_permission` VALUES ('2', '2');
INSERT INTO `s_role_permission` VALUES ('1', '1');

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `status` varchar(255) NOT NULL COMMENT '是否有效 1：有效          0：锁定',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES ('1', 'root', '82f25d80c56b33486c871990805a0269', '2019-07-26', '1');
INSERT INTO `s_user` VALUES ('2', 'test', '28db8ac6f473b2e2337df81c004257c5', '2019-07-26', '1');

-- ----------------------------
-- Table structure for s_user_role
-- ----------------------------
DROP TABLE IF EXISTS `s_user_role`;
CREATE TABLE `s_user_role` (
  `user_id` int(10) DEFAULT NULL COMMENT '用户id',
  `role_id` int(10) DEFAULT NULL COMMENT '角色id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_user_role
-- ----------------------------
INSERT INTO `s_user_role` VALUES ('1', '1');
INSERT INTO `s_user_role` VALUES ('2', '2');
