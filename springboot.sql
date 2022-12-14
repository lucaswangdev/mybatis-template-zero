/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : springboot

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 15/11/2022 00:17:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for api_mock
-- ----------------------------
DROP TABLE IF EXISTS `api_mock`;
CREATE TABLE `api_mock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `api_path` varchar(1024) NOT NULL COMMENT 'mock接口url',
  `api_content` varchar(1024) NOT NULL COMMENT 'mock接口content',
  `delay` int(255) DEFAULT '0' COMMENT '延时',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of api_mock
-- ----------------------------
BEGIN;
INSERT INTO `api_mock` VALUES (1, 'aaa', '{\'name\': \'xxx\'}', 0);
INSERT INTO `api_mock` VALUES (2, 'bbb', '{\'name\': \'yyy\'}', 0);
INSERT INTO `api_mock` VALUES (3, 'user/aaa', '{\'name\': \'yyy\'}', 0);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) DEFAULT NULL,
  `sex` varchar(1) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (10, '测试20', '女', '地址20...');
INSERT INTO `user` VALUES (12, 'name5', '男', '地址：xxx5');
INSERT INTO `user` VALUES (13, '詹姆斯', '男', 'USA');
INSERT INTO `user` VALUES (15, 'jack333', '男', '北京');
INSERT INTO `user` VALUES (16, '詹姆斯', '男', 'USA');
INSERT INTO `user` VALUES (17, '詹姆斯2', '男', 'USA');
INSERT INTO `user` VALUES (18, '詹姆斯3$', '男', 'USA');
INSERT INTO `user` VALUES (19, '詹姆斯8', '男', 'USA');
INSERT INTO `user` VALUES (20, '张三1001', '男', '深圳');
INSERT INTO `user` VALUES (21, '测试1', '女', '地址11111');
INSERT INTO `user` VALUES (22, '测试2', '女', '地址11111');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
