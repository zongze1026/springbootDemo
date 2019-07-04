/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : slave

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 04/07/2019 17:42:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for super_button
-- ----------------------------
DROP TABLE IF EXISTS `super_button`;
CREATE TABLE `super_button`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` char(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `order_no` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of super_button
-- ----------------------------
INSERT INTO `super_button` VALUES (1, '联系我们', '1', '2019-07-04 16:51:28', '2019-07-04 16:51:28');
INSERT INTO `super_button` VALUES (2, '问题咨询', '2', '2019-07-04 16:52:15', '2019-07-04 16:52:15');
INSERT INTO `super_button` VALUES (3, '注册登入', '3', '2019-07-04 16:52:30', '2019-07-04 16:52:30');

SET FOREIGN_KEY_CHECKS = 1;
