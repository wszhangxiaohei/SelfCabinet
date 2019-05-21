/*
 Navicat Premium Data Transfer

 Source Server         : Self_service_bar
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : self_cabinet

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 08/05/2019 10:07:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_info
-- ----------------------------
DROP TABLE IF EXISTS `admin_info`;
CREATE TABLE `admin_info` (
  `admin_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `password` varchar(20) DEFAULT NULL,
  `bar_id` bigint(20) unsigned DEFAULT NULL,
  `state` varchar(20) DEFAULT '0',
  `tel` varchar(20) DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `info` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10001 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of admin_info
-- ----------------------------
BEGIN;
INSERT INTO `admin_info` VALUES (10000, '123123', 10000, '1', '4123123', '王伟', '北邮店');
COMMIT;

-- ----------------------------
-- Table structure for cabinet_info
-- ----------------------------
DROP TABLE IF EXISTS `cabinet_info`;
CREATE TABLE `cabinet_info` (
  `cabinet_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `cupboard_id` bigint(20) unsigned NOT NULL,
  `bar_id` bigint(20) unsigned NOT NULL,
  `line` tinyint(3) unsigned DEFAULT NULL,
  `row` tinyint(3) unsigned DEFAULT NULL,
  `no` varchar(5) DEFAULT NULL,
  `used` varchar(20) DEFAULT '0' COMMENT '0空闲 1使用',
  `state` varchar(5) DEFAULT '0' COMMENT '0正常 1异常',
  `open` varchar(5) DEFAULT '0',
  PRIMARY KEY (`cabinet_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10042 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of cabinet_info
-- ----------------------------
BEGIN;
INSERT INTO `cabinet_info` VALUES (10000, 10000, 10000, 1, 1, '1', '1', '0', '1');
INSERT INTO `cabinet_info` VALUES (10001, 10000, 10000, 1, 2, '2', '1', '0', '1');
INSERT INTO `cabinet_info` VALUES (10002, 10000, 10000, 1, 3, '3', '1', '0', '1');
INSERT INTO `cabinet_info` VALUES (10003, 10000, 10000, 1, 4, '4', '1', '0', '1');
INSERT INTO `cabinet_info` VALUES (10004, 10000, 10000, 1, 5, '5', '0', '0', '1');
INSERT INTO `cabinet_info` VALUES (10005, 10000, 10000, 1, 6, '6', '0', '0', '1');
INSERT INTO `cabinet_info` VALUES (10006, 10000, 10000, 2, 1, '7', '0', '0', '1');
INSERT INTO `cabinet_info` VALUES (10007, 10000, 10000, 2, 2, '8', '0', '0', '1');
INSERT INTO `cabinet_info` VALUES (10008, 10000, 10000, 2, 3, '9', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10009, 10000, 10000, 2, 4, '10', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10010, 10000, 10000, 2, 5, '11', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10011, 10000, 10000, 2, 6, '12', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10012, 10000, 10000, 3, 1, '13', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10013, 10000, 10000, 3, 2, '14', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10014, 10000, 10000, 3, 3, '15', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10015, 10000, 10000, 3, 4, '16', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10016, 10000, 10000, 3, 5, '17', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10017, 10000, 10000, 3, 6, '18', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10018, 10000, 10000, 4, 1, '19', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10019, 10000, 10000, 4, 2, '20', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10020, 10000, 10000, 4, 3, '21', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10021, 10000, 10000, 4, 4, '22', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10022, 10000, 10000, 4, 5, '23', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10023, 10000, 10000, 4, 6, '24', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10024, 10000, 10000, 5, 1, '25', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10025, 10000, 10000, 5, 2, '26', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10026, 10000, 10000, 5, 3, '27', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10027, 10000, 10000, 5, 4, '28', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10028, 10000, 10000, 5, 5, '29', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10029, 10000, 10000, 5, 6, '30', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10030, 10000, 10000, 6, 1, '31', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10031, 10000, 10000, 6, 2, '32', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10032, 10000, 10000, 6, 3, '33', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10033, 10000, 10000, 6, 4, '34', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10034, 10000, 10000, 6, 5, '35', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10035, 10000, 10000, 6, 6, '36', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10036, 10000, 10000, 7, 1, '37', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10037, 10000, 10000, 7, 2, '38', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10038, 10000, 10000, 7, 3, '39', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10039, 10000, 10000, 7, 4, '40', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10040, 10000, 10000, 7, 5, '41', '0', '0', '0');
INSERT INTO `cabinet_info` VALUES (10041, 10000, 10000, 7, 6, '42', '0', '0', '0');
COMMIT;

-- ----------------------------
-- Table structure for cupboard_info
-- ----------------------------
DROP TABLE IF EXISTS `cupboard_info`;
CREATE TABLE `cupboard_info` (
  `cupboard_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `num` int(11) DEFAULT '0',
  `line` varchar(5) DEFAULT NULL,
  `row` varchar(5) DEFAULT NULL,
  `bar_id` bigint(20) unsigned DEFAULT NULL,
  `spare_num` int(11) DEFAULT '0',
  PRIMARY KEY (`cupboard_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10001 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of cupboard_info
-- ----------------------------
BEGIN;
INSERT INTO `cupboard_info` VALUES (10000, 40, '7', '6', 10000, 39);
COMMIT;

-- ----------------------------
-- Table structure for equip_info
-- ----------------------------
DROP TABLE IF EXISTS `equip_info`;
CREATE TABLE `equip_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip` varchar(20) DEFAULT NULL,
  `groupNo` int(11) DEFAULT NULL,
  `boxNo` int(11) DEFAULT NULL,
  `doorStatus` varchar(5) DEFAULT '0' COMMENT ' 0 箱门关闭，1 箱门打开 ',
  `hasItem` varchar(5) DEFAULT '1' COMMENT '0 有物，1 无物品 ',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=43 DEFAULT CHARSET=latin1 COMMENT='模拟硬件状态';

-- ----------------------------
-- Records of equip_info
-- ----------------------------
BEGIN;
INSERT INTO `equip_info` VALUES (1, NULL, 10000, 1, '0', '0');
INSERT INTO `equip_info` VALUES (2, NULL, 10000, 2, '1', '0');
INSERT INTO `equip_info` VALUES (3, NULL, 10000, 3, '1', '0');
INSERT INTO `equip_info` VALUES (4, NULL, 10000, 4, '0', '0');
INSERT INTO `equip_info` VALUES (5, NULL, 10000, 5, '0', '1');
INSERT INTO `equip_info` VALUES (6, NULL, 10000, 6, '0', '1');
INSERT INTO `equip_info` VALUES (7, NULL, 10000, 7, '1', '1');
INSERT INTO `equip_info` VALUES (8, NULL, 10000, 8, '0', '1');
INSERT INTO `equip_info` VALUES (9, NULL, 10000, 9, '0', '1');
INSERT INTO `equip_info` VALUES (10, NULL, 10000, 10, '0', '1');
INSERT INTO `equip_info` VALUES (11, NULL, 10000, 11, '1', '1');
INSERT INTO `equip_info` VALUES (12, NULL, 10000, 12, '0', '1');
INSERT INTO `equip_info` VALUES (13, NULL, 10000, 13, '1', '1');
INSERT INTO `equip_info` VALUES (14, NULL, 10000, 14, '1', '1');
INSERT INTO `equip_info` VALUES (15, NULL, 10000, 15, '1', '1');
INSERT INTO `equip_info` VALUES (16, NULL, 10000, 16, '1', '1');
INSERT INTO `equip_info` VALUES (17, NULL, 10000, 17, '1', '1');
INSERT INTO `equip_info` VALUES (18, NULL, 10000, 18, '1', '1');
INSERT INTO `equip_info` VALUES (19, NULL, 10000, 19, '1', '1');
INSERT INTO `equip_info` VALUES (20, NULL, 10000, 20, '1', '1');
INSERT INTO `equip_info` VALUES (21, NULL, 10000, 21, '1', '1');
INSERT INTO `equip_info` VALUES (22, NULL, 10000, 22, '1', '1');
INSERT INTO `equip_info` VALUES (23, NULL, 10000, 23, '0', '1');
INSERT INTO `equip_info` VALUES (24, NULL, 10000, 24, '1', '1');
INSERT INTO `equip_info` VALUES (25, NULL, 10000, 25, '1', '1');
INSERT INTO `equip_info` VALUES (26, NULL, 10000, 26, '1', '1');
INSERT INTO `equip_info` VALUES (27, NULL, 10000, 27, '1', '1');
INSERT INTO `equip_info` VALUES (28, NULL, 10000, 28, '1', '1');
INSERT INTO `equip_info` VALUES (29, NULL, 10000, 29, '1', '1');
INSERT INTO `equip_info` VALUES (30, NULL, 10000, 30, '1', '1');
INSERT INTO `equip_info` VALUES (31, NULL, 10000, 31, '1', '1');
INSERT INTO `equip_info` VALUES (32, NULL, 10000, 32, '1', '1');
INSERT INTO `equip_info` VALUES (33, NULL, 10000, 33, '1', '1');
INSERT INTO `equip_info` VALUES (34, NULL, 10000, 34, '0', '1');
INSERT INTO `equip_info` VALUES (35, NULL, 10000, 35, '0', '1');
INSERT INTO `equip_info` VALUES (36, NULL, 10000, 36, '1', '1');
INSERT INTO `equip_info` VALUES (37, NULL, 10000, 37, '0', '1');
INSERT INTO `equip_info` VALUES (38, NULL, 10000, 38, '0', '1');
INSERT INTO `equip_info` VALUES (39, NULL, 10000, 39, '0', '1');
INSERT INTO `equip_info` VALUES (40, NULL, 10000, 40, '0', '1');
INSERT INTO `equip_info` VALUES (41, NULL, 10000, 41, '0', '1');
INSERT INTO `equip_info` VALUES (42, NULL, 10000, 42, '0', '1');
COMMIT;

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `order_id` bigint(20) NOT NULL,
  `bar_id` bigint(20) unsigned NOT NULL,
  `cabinet_id` bigint(20) unsigned NOT NULL,
  `cupboard_id` bigint(20) unsigned NOT NULL,
  `state` varchar(20) DEFAULT '0' COMMENT '0 订单未完成 1订单完成',
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `carrier_code` varchar(20) DEFAULT '0000',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10000094 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of order_info
-- ----------------------------
BEGIN;
INSERT INTO `order_info` VALUES (10000000, 10000, 10000, 10000, '1', 10000016, '0000');
INSERT INTO `order_info` VALUES (10000001, 10000, 10002, 10000, '1', 10000017, '0000');
INSERT INTO `order_info` VALUES (10000003, 10000, 10001, 10000, '1', 10000018, '0000');
INSERT INTO `order_info` VALUES (10000004, 10000, 10003, 10000, '1', 10000019, '0000');
INSERT INTO `order_info` VALUES (10000005, 10000, 10000, 10000, '1', 10000020, '0000');
INSERT INTO `order_info` VALUES (10000006, 10000, 10004, 10000, '1', 10000021, '0000');
INSERT INTO `order_info` VALUES (10000010, 10000, 10005, 10000, '1', 10000022, '0000');
INSERT INTO `order_info` VALUES (10000011, 10000, 10006, 10000, '1', 10000023, '0000');
INSERT INTO `order_info` VALUES (10000012, 10000, 10006, 10000, '1', 10000024, '0000');
INSERT INTO `order_info` VALUES (10000013, 10000, 10006, 10000, '1', 10000025, '0000');
INSERT INTO `order_info` VALUES (10000014, 10000, 10006, 10000, '1', 10000026, '0000');
INSERT INTO `order_info` VALUES (10000015, 10000, 10006, 10000, '1', 10000027, '0000');
INSERT INTO `order_info` VALUES (10000020, 10000, 10000, 10000, '1', 10000028, '0000');
INSERT INTO `order_info` VALUES (10000016, 10000, 10000, 10000, '1', 10000030, '0000');
INSERT INTO `order_info` VALUES (10000016, 10000, 10000, 10000, '1', 10000031, '0000');
INSERT INTO `order_info` VALUES (10000016, 10000, 10000, 10000, '1', 10000032, '0000');
INSERT INTO `order_info` VALUES (10000016, 10000, 10000, 10000, '1', 10000033, '0000');
INSERT INTO `order_info` VALUES (10000016, 10000, 10000, 10000, '1', 10000034, '0000');
INSERT INTO `order_info` VALUES (10000011, 10000, 10000, 10000, '1', 10000035, '0000');
INSERT INTO `order_info` VALUES (10000011, 10000, 10000, 10000, '1', 10000036, '0000');
INSERT INTO `order_info` VALUES (10000011, 10000, 10000, 10000, '1', 10000037, '0000');
INSERT INTO `order_info` VALUES (10000011, 10000, 10000, 10000, '1', 10000038, '0000');
INSERT INTO `order_info` VALUES (10000000, 10000, 10000, 10000, '1', 10000039, '0000');
INSERT INTO `order_info` VALUES (10000000, 10000, 10000, 10000, '1', 10000040, '0000');
INSERT INTO `order_info` VALUES (10000000, 10000, 10000, 10000, '1', 10000041, '0000');
INSERT INTO `order_info` VALUES (10000006, 10000, 10000, 10000, '1', 10000042, '0000');
INSERT INTO `order_info` VALUES (10000009, 10000, 10000, 10000, '1', 10000043, '0000');
INSERT INTO `order_info` VALUES (10000021, 10000, 10000, 10000, '1', 10000044, '0000');
INSERT INTO `order_info` VALUES (10000022, 10000, 10001, 10000, '1', 10000045, '0000');
INSERT INTO `order_info` VALUES (10000026, 10000, 10000, 10000, '1', 10000046, '0000');
INSERT INTO `order_info` VALUES (10000025, 10000, 10001, 10000, '1', 10000047, '0000');
INSERT INTO `order_info` VALUES (10000020, 10000, 10000, 10000, '0', 10000055, '0000');
INSERT INTO `order_info` VALUES (10000780, 10000, 10000, 10000, '1', 10000056, '0000');
INSERT INTO `order_info` VALUES (20000001, 10000, 10000, 10000, '0', 10000057, '0000');
INSERT INTO `order_info` VALUES (10000199, 10000, 10000, 10000, '1', 10000054, '0000');
INSERT INTO `order_info` VALUES (20000003, 10000, 10001, 10000, '0', 10000058, '0000');
INSERT INTO `order_info` VALUES (20000000, 10000, 10002, 10000, '1', 10000059, '0000');
INSERT INTO `order_info` VALUES (123456, 10000, 10002, 10000, '0', 10000060, '0000');
INSERT INTO `order_info` VALUES (1234568, 10000, 10003, 10000, '1', 10000061, '0000');
INSERT INTO `order_info` VALUES (10000777, 10000, 10003, 10000, '1', 10000062, '0000');
INSERT INTO `order_info` VALUES (10000092, 10000, 10003, 10000, '0', 10000063, '0000');
INSERT INTO `order_info` VALUES (10000092, 10000, 10003, 10000, '0', 10000064, '0000');
INSERT INTO `order_info` VALUES (10000092, 10000, 10003, 10000, '0', 10000065, '0000');
INSERT INTO `order_info` VALUES (10000092, 10000, 10003, 10000, '0', 10000066, '0000');
INSERT INTO `order_info` VALUES (10000092, 10000, 10003, 10000, '0', 10000067, '0000');
INSERT INTO `order_info` VALUES (10000092, 10000, 10003, 10000, '0', 10000068, '0000');
INSERT INTO `order_info` VALUES (10000093, 10000, 10003, 10000, '1', 10000069, '0000');
INSERT INTO `order_info` VALUES (10000111, 10000, 10003, 10000, '1', 10000070, '1234');
INSERT INTO `order_info` VALUES (10000112, 10000, 10003, 10000, '1', 10000071, '1111');
INSERT INTO `order_info` VALUES (10000099, 10000, 10003, 10000, '0', 10000072, '111111');
INSERT INTO `order_info` VALUES (11000099, 10000, 10004, 10000, '1', 10000073, '123456');
INSERT INTO `order_info` VALUES (66666666, 10000, 10003, 10000, '1', 10000074, '1');
INSERT INTO `order_info` VALUES (10000030, 10000, 10003, 10000, '1', 10000075, '1122');
INSERT INTO `order_info` VALUES (10000779, 10000, 10003, 10000, '1', 10000076, '1214');
INSERT INTO `order_info` VALUES (10000033, 10000, 10003, 10000, '1', 10000077, '113355');
INSERT INTO `order_info` VALUES (1865854430, 10000, 10003, 10000, '0', 10000078, '314784');
INSERT INTO `order_info` VALUES (934739722, 10000, 10003, 10000, '1', 10000079, '572685');
INSERT INTO `order_info` VALUES (1625097677, 10000, 10003, 10000, '1', 10000080, '674441');
INSERT INTO `order_info` VALUES (1968408075, 10000, 10003, 10000, '0', 10000090, '967969');
INSERT INTO `order_info` VALUES (8349716812, 10000, 10003, 10000, '1', 10000093, '387562');
COMMIT;

-- ----------------------------
-- Table structure for stimulate_order
-- ----------------------------
DROP TABLE IF EXISTS `stimulate_order`;
CREATE TABLE `stimulate_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` varchar(20) DEFAULT NULL,
  `cupboard_id` varchar(20) DEFAULT NULL,
  `type` varchar(20) DEFAULT '',
  `carrier_code` varchar(20) NOT NULL,
  `status` varchar(10) DEFAULT '0' COMMENT '0为未完成 1为已完成',
  PRIMARY KEY (`id`),
  UNIQUE KEY `stimulate_order_carrier_code_uindex` (`carrier_code`),
  UNIQUE KEY `stimulate_order_order_id_uindex` (`order_id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of stimulate_order
-- ----------------------------
BEGIN;
INSERT INTO `stimulate_order` VALUES (1, '1865854430', '10000', 'user', '314784', '1');
INSERT INTO `stimulate_order` VALUES (2, '1625097677', '10000', 'user', '674441', '1');
INSERT INTO `stimulate_order` VALUES (3, '5369367049', '10000', 'user', '032653', '0');
INSERT INTO `stimulate_order` VALUES (4, '5204804975', '10000', 'user', '027504', '0');
INSERT INTO `stimulate_order` VALUES (5, '8349716812', '10000', 'user', '387562', '1');
INSERT INTO `stimulate_order` VALUES (6, '8179283427', '10000', 'user', '623465', '0');
INSERT INTO `stimulate_order` VALUES (7, '0150458846', '10000', 'user', '691372', '0');
INSERT INTO `stimulate_order` VALUES (8, '3091121947', '10000', 'user', '973354', '0');
INSERT INTO `stimulate_order` VALUES (9, '1718856668', '10000', 'user', '938650', '0');
INSERT INTO `stimulate_order` VALUES (10, '5596283138', '10000', 'user', '776053', '0');
INSERT INTO `stimulate_order` VALUES (11, '3538594370', '10000', 'user', '549937', '0');
INSERT INTO `stimulate_order` VALUES (12, '2909959283', '10000', 'user', '393444', '0');
INSERT INTO `stimulate_order` VALUES (13, '7423298794', '10000', 'user', '054407', '0');
INSERT INTO `stimulate_order` VALUES (14, '4338755870', '10000', 'user', '925183', '0');
INSERT INTO `stimulate_order` VALUES (15, '7129763877', '10000', 'user', '270535', '0');
INSERT INTO `stimulate_order` VALUES (16, '9718307007', '10000', 'user', '324294', '0');
INSERT INTO `stimulate_order` VALUES (17, '5996587098', '10000', 'user', '097332', '0');
INSERT INTO `stimulate_order` VALUES (18, '3208608017', '10000', 'user', '167849', '0');
INSERT INTO `stimulate_order` VALUES (19, '0934739722', '10000', 'user', '572685', '1');
INSERT INTO `stimulate_order` VALUES (20, '1968408075', '10000', 'user', '967969', '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
