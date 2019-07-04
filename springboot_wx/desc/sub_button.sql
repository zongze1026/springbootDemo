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

 Date: 04/07/2019 17:42:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sub_button
-- ----------------------------
DROP TABLE IF EXISTS `sub_button`;
CREATE TABLE `sub_button`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `s_id` bigint(20) NOT NULL,
  `s_name` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` char(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'view',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `s_order_no` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sub_button
-- ----------------------------
INSERT INTO `sub_button` VALUES (1, 2, '下单问题', 'view', 'https://www.baidu.com', '1');
INSERT INTO `sub_button` VALUES (2, 2, '购买问题', 'view', 'https://www.baidu.com', '2');
INSERT INTO `sub_button` VALUES (3, 2, '退款问题', 'view', 'https://www.baidu.com', '3');
INSERT INTO `sub_button` VALUES (4, 1, '订单发货', 'view', 'https://www.baidu.com', '1');
INSERT INTO `sub_button` VALUES (6, 1, '订单失效', 'view', 'https://www.baidu.com', '3');
INSERT INTO `sub_button` VALUES (7, 3, '无法注册', 'view', 'https://www.baidu.com', '1');
INSERT INTO `sub_button` VALUES (8, 3, '无法登入', 'view', 'https://www.baidu.com', '2');
INSERT INTO `sub_button` VALUES (9, 3, '注册详情', 'view', 'https://www.baidu.com', '3');
INSERT INTO `sub_button` VALUES (10, 2, '会员购买', 'view', 'https://www.baidu.com', '4');

SET FOREIGN_KEY_CHECKS = 1;
