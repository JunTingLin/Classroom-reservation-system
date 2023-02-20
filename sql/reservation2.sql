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

 Date: 20/02/2023 16:57:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for reservation
-- ----------------------------
DROP TABLE IF EXISTS `reservation`;
CREATE TABLE `reservation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `chinese_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `classroom` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `end` time NULL DEFAULT NULL,
  `info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_batch` bit(1) NOT NULL,
  `start` time NULL DEFAULT NULL,
  `student_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reservation
-- ----------------------------
INSERT INTO `reservation` VALUES (1, '林俊霆', 'S130', '2023-02-24', '10355098@st.thsh.tp.edu.tw', '16:00:00', '教室突然要打掃', b'0', '13:00:00', '109403537');
INSERT INTO `reservation` VALUES (37, '林俊霆', 'S130', '2023-02-21', '10355098@st.thsh.tp.edu.tw', '16:00:00', '考古學', b'1', '13:00:00', '109403537');
INSERT INTO `reservation` VALUES (38, '林俊霆', 'S130', '2023-02-28', '10355098@st.thsh.tp.edu.tw', '16:00:00', '考古學', b'1', '13:00:00', '109403537');
INSERT INTO `reservation` VALUES (39, '林俊霆', 'S130', '2023-03-07', '10355098@st.thsh.tp.edu.tw', '16:00:00', '考古學', b'1', '13:00:00', '109403537');
INSERT INTO `reservation` VALUES (40, '林俊霆', 'S130', '2023-03-14', '10355098@st.thsh.tp.edu.tw', '16:00:00', '考古學', b'1', '13:00:00', '109403537');
INSERT INTO `reservation` VALUES (41, '林俊霆', 'S130', '2023-03-21', '10355098@st.thsh.tp.edu.tw', '16:00:00', '考古學', b'1', '13:00:00', '109403537');
INSERT INTO `reservation` VALUES (42, '林俊霆', 'S130', '2023-03-28', '10355098@st.thsh.tp.edu.tw', '16:00:00', '考古學', b'1', '13:00:00', '109403537');
INSERT INTO `reservation` VALUES (43, '林俊霆', 'S130', '2023-04-04', '10355098@st.thsh.tp.edu.tw', '16:00:00', '考古學', b'1', '13:00:00', '109403537');
INSERT INTO `reservation` VALUES (44, '林俊霆', 'S130', '2023-04-11', '10355098@st.thsh.tp.edu.tw', '16:00:00', '考古學', b'1', '13:00:00', '109403537');
INSERT INTO `reservation` VALUES (45, '林俊霆', 'S130', '2023-04-18', '10355098@st.thsh.tp.edu.tw', '16:00:00', '考古學', b'1', '13:00:00', '109403537');
INSERT INTO `reservation` VALUES (46, '林俊霆', 'S130', '2023-04-25', '10355098@st.thsh.tp.edu.tw', '16:00:00', '考古學', b'1', '13:00:00', '109403537');
INSERT INTO `reservation` VALUES (47, '林俊霆', 'S130', '2023-05-02', '10355098@st.thsh.tp.edu.tw', '16:00:00', '考古學', b'1', '13:00:00', '109403537');
INSERT INTO `reservation` VALUES (48, '林俊霆', 'S130', '2023-05-09', '10355098@st.thsh.tp.edu.tw', '16:00:00', '考古學', b'1', '13:00:00', '109403537');
INSERT INTO `reservation` VALUES (49, '林俊霆', 'S130', '2023-02-18', '10355098@st.thsh.tp.edu.tw', '11:00:00', '行銷學', b'1', '09:00:00', '109403537');
INSERT INTO `reservation` VALUES (50, '林俊霆', 'S130', '2023-02-25', '10355098@st.thsh.tp.edu.tw', '11:00:00', '行銷學', b'1', '09:00:00', '109403537');

SET FOREIGN_KEY_CHECKS = 1;
