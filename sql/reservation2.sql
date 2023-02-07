/*
 Navicat Premium Data Transfer

 Source Server         : local_db
 Source Server Type    : MySQL
 Source Server Version : 50739
 Source Host           : localhost:3306
 Source Schema         : reservation2

 Target Server Type    : MySQL
 Target Server Version : 50739
 File Encoding         : 65001

 Date: 07/02/2023 18:13:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for reservation
-- ----------------------------
DROP TABLE IF EXISTS `reservation`;
CREATE TABLE `reservation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `chinese_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `classroom` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  `start` time NULL DEFAULT NULL,
  `end` time NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reservation
-- ----------------------------
INSERT INTO `reservation` VALUES (1, '109403537', '林俊霆', '10355098@st.thsh.tp.edu.tw', 'S130', '2023-01-30', '05:00:00', '06:00:00');
INSERT INTO `reservation` VALUES (4, '109403537', '林俊霆', '10355098@st.thsh.tp.edu.tw', 'S130', '2023-01-30', '01:00:00', '02:00:00');
INSERT INTO `reservation` VALUES (5, '109403537', '林俊霆', '10355098@st.thsh.tp.edu.tw', 'S130', '2023-01-30', '07:00:00', '08:00:00');
INSERT INTO `reservation` VALUES (6, '109403537', '林俊霆', '10355098@st.thsh.tp.edu.tw', 'S130', '2023-01-31', '13:00:00', '17:00:00');
INSERT INTO `reservation` VALUES (9, '109403537', '林俊霆', '10355098@st.thsh.tp.edu.tw', 'S130', '2023-02-07', '13:00:00', '14:00:00');
INSERT INTO `reservation` VALUES (11, '109403537', '林俊霆', '10355098@st.thsh.tp.edu.tw', 'S231', '2023-01-31', '10:00:00', '12:00:00');
INSERT INTO `reservation` VALUES (12, '109403537', '林俊霆', '10355098@st.thsh.tp.edu.tw', 'S253', '2023-01-31', '13:00:00', '14:00:00');

SET FOREIGN_KEY_CHECKS = 1;
