/*
 Navicat Premium Data Transfer

 Source Server         : local_db
 Source Server Type    : MySQL
 Source Server Version : 50739
 Source Host           : localhost:3306
 Source Schema         : reservation

 Target Server Type    : MySQL
 Target Server Version : 50739
 File Encoding         : 65001

 Date: 30/01/2023 03:31:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for classroom
-- ----------------------------
DROP TABLE IF EXISTS `classroom`;
CREATE TABLE `classroom`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of classroom
-- ----------------------------
INSERT INTO `classroom` VALUES (1, 'S130');
INSERT INTO `classroom` VALUES (2, 'S231');
INSERT INTO `classroom` VALUES (3, 'S253');

-- ----------------------------
-- Table structure for reservation
-- ----------------------------
DROP TABLE IF EXISTS `reservation`;
CREATE TABLE `reservation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `classroom_id` int(11) NOT NULL,
  `teacher_id` int(11) NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_user_id`(`user_id`) USING BTREE,
  INDEX `FK_classroom_id`(`classroom_id`) USING BTREE,
  INDEX `FK_teacher_id`(`teacher_id`) USING BTREE,
  CONSTRAINT `FK_classroom_id` FOREIGN KEY (`classroom_id`) REFERENCES `classroom` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_teacher_id` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reservation
-- ----------------------------
INSERT INTO `reservation` VALUES (1, 1, 2, 3, '2023-01-16 13:00:00', '2023-01-16 15:00:00');
INSERT INTO `reservation` VALUES (12, 1, 2, 3, '2023-01-18 13:00:00', '2023-01-18 14:00:00');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `chinese_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `english_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '倪春發', 'Chuen-Fa Ni');
INSERT INTO `teacher` VALUES (2, '陳瑞昇', 'Jui-Sheng Chen');
INSERT INTO `teacher` VALUES (3, '董家鈞', 'Jia-Jyun Dong');
INSERT INTO `teacher` VALUES (4, '黃文正', 'Wen-Jeng Huang');
INSERT INTO `teacher` VALUES (5, '王士榮', 'Shih-Jung Wang');
INSERT INTO `teacher` VALUES (6, '波玫琳', 'Maryline Le Béon');
INSERT INTO `teacher` VALUES (7, '盧乙嘉', 'Yi-Chia Lu');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `number` int(11) NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `is_admin` bit(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '林俊霆', 109405689, 'junting@gmail.com', b'0');
INSERT INTO `user` VALUES (2, '黃美麗', 102589875, 'geo@ncu.edu.tw', b'1');

SET FOREIGN_KEY_CHECKS = 1;
