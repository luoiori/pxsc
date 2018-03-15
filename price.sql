/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : psxc

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-03-15 12:29:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for price
-- ----------------------------
DROP TABLE IF EXISTS `price`;
CREATE TABLE `price` (
  `tc` int(11) NOT NULL,
  `price` double NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tc`),
  UNIQUE KEY `tc_uniq` (`tc`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of price
-- ----------------------------
INSERT INTO `price` VALUES ('1', '168', '168/盒');
INSERT INTO `price` VALUES ('2', '492', '492/4盒');
INSERT INTO `price` VALUES ('3', '2100', '2100/20盒');
