/*
Navicat MySQL Data Transfer

Source Server         : ubuntu1
Source Server Version : 50546
Source Host           : ubuntu1:3306
Source Database       : promoanalysis

Target Server Type    : MYSQL
Target Server Version : 50546
File Encoding         : 65001

Date: 2016-02-24 13:56:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order_history
-- ----------------------------
DROP TABLE IF EXISTS `order_history`;
CREATE TABLE `order_history` (
  `orderTimeSec` varchar(255) DEFAULT NULL,
  `orderTimeMinuteId` varchar(255) DEFAULT NULL,
  `orderId` varchar(255) DEFAULT NULL,
  `skuId` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  `skuNum` varchar(255) DEFAULT NULL,
  `skuPrice` varchar(255) DEFAULT NULL,
  `unitId` varchar(255) DEFAULT NULL,
  `largessId` varchar(255) DEFAULT NULL,
  `packId` varchar(255) DEFAULT NULL,
  `sumId` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_history
-- ----------------------------
INSERT INTO `order_history` VALUES ('160219191211', '1602191912', '1455880331930', '8346583', 'qq', '2', '269', '0', '13711', '742323', '3475204');
INSERT INTO `order_history` VALUES ('160219191211', '1602191912', '1455880331930', '8885752', 'qq', '4', '211', '2929', '0', '742323', '0');
INSERT INTO `order_history` VALUES ('160219191215', '1602191912', '1455880335920', '8346583', 'hello', '4', '393', '0', '13711', '742323', '3475204');
INSERT INTO `order_history` VALUES ('160219191215', '1602191912', '1455880335920', '8885752', 'hello', '4', '127', '2929', '0', '742323', '0');
INSERT INTO `order_history` VALUES ('160219191218', '1602191912', '1455880338769', '8885752', 'hello', '3', '196', '2929', '0', '742323', '0');
INSERT INTO `order_history` VALUES ('160219191220', '1602191912', '1455880340161', '8346583', 'test', '3', '500', '0', '13711', '742323', '3475204');
INSERT INTO `order_history` VALUES ('160219191220', '1602191912', '1455880340161', '8885752', 'test', '4', '141', '2929', '0', '742323', '0');
INSERT INTO `order_history` VALUES ('160219191222', '1602191912', '1455880342307', '8346583', 'haha', '4', '488', '0', '13711', '742323', '3475204');
INSERT INTO `order_history` VALUES ('160219191222', '1602191912', '1455880342307', '8885752', 'haha', '3', '488', '2929', '0', '742323', '0');
INSERT INTO `order_history` VALUES ('160219191224', '1602191912', '1455880344266', '8346583', 'qq', '1', '500', '0', '13711', '742323', '3475204');
INSERT INTO `order_history` VALUES ('160219191224', '1602191912', '1455880344266', '8885752', 'qq', '2', '305', '2929', '0', '742323', '0');
INSERT INTO `order_history` VALUES ('160219191227', '1602191912', '1455880347365', '8346583', 'qq', '3', '360', '0', '13711', '742323', '3475204');
INSERT INTO `order_history` VALUES ('160219191227', '1602191912', '1455880347365', '8885752', 'qq', '4', '291', '2929', '0', '742323', '0');
INSERT INTO `order_history` VALUES ('160219191230', '1602191912', '1455880350589', '8885752', 'haha', '2', '374', '2929', '0', '742323', '0');
INSERT INTO `order_history` VALUES ('160219191235', '1602191912', '1455880355509', '8346583', 'hello', '3', '280', '0', '13711', '742323', '3475204');
INSERT INTO `order_history` VALUES ('160219191235', '1602191912', '1455880355509', '8885752', 'hello', '1', '197', '2929', '0', '742323', '0');
INSERT INTO `order_history` VALUES ('160219191240', '1602191912', '1455880360049', '8885752', '京东', '3', '194', '2929', '0', '742323', '0');
INSERT INTO `order_history` VALUES ('160219191244', '1602191912', '1455880364037', '8885752', 'test', '1', '330', '2929', '0', '742323', '0');
INSERT INTO `order_history` VALUES ('160219191246', '1602191912', '1455880366673', '8346583', '京东', '3', '261', '0', '13711', '742323', '3475204');
INSERT INTO `order_history` VALUES ('160219191246', '1602191912', '1455880366673', '8885752', '京东', '2', '427', '2929', '0', '742323', '0');
INSERT INTO `order_history` VALUES ('160219191251', '1602191912', '1455880371080', '8346583', 'qq', '3', '313', '0', '13711', '742323', '3475204');
INSERT INTO `order_history` VALUES ('160219191251', '1602191912', '1455880371080', '8885752', 'qq', '4', '416', '2929', '0', '742323', '0');
INSERT INTO `order_history` VALUES ('160219191555', '1602191915', '1455880555729', '9757408', '京东', '3', '233', '6990', '0', '565142', '9812399');
INSERT INTO `order_history` VALUES ('160219191558', '1602191915', '1455880558445', '9757408', 'qq', '2', '104', '6990', '0', '565142', '9812399');
INSERT INTO `order_history` VALUES ('160219191602', '1602191916', '1455880562336', '9596169', 'haha', '4', '232', '0', '40712', '565142', '0');
INSERT INTO `order_history` VALUES ('160219191607', '1602191916', '1455880567076', '9757408', 'haha', '3', '106', '6990', '0', '565142', '9812399');
INSERT INTO `order_history` VALUES ('160219191607', '1602191916', '1455880567076', '9596169', 'haha', '1', '346', '0', '40712', '565142', '0');
INSERT INTO `order_history` VALUES ('160219191611', '1602191916', '1455880571606', '9757408', 'test', '1', '270', '6990', '0', '565142', '9812399');
INSERT INTO `order_history` VALUES ('160219191611', '1602191916', '1455880571606', '9596169', 'test', '4', '411', '0', '40712', '565142', '0');
INSERT INTO `order_history` VALUES ('160219191613', '1602191916', '1455880573751', '9757408', '中文用户啊', '2', '112', '6990', '0', '565142', '9812399');
INSERT INTO `order_history` VALUES ('160219191616', '1602191916', '1455880576562', '9757408', 'haha', '3', '419', '6990', '0', '565142', '9812399');
INSERT INTO `order_history` VALUES ('160219191616', '1602191916', '1455880576562', '9596169', 'haha', '2', '267', '0', '40712', '565142', '0');
INSERT INTO `order_history` VALUES ('160219191621', '1602191916', '1455880581232', '9757408', '中文用户啊', '3', '499', '6990', '0', '565142', '9812399');
INSERT INTO `order_history` VALUES ('160219191621', '1602191916', '1455880581232', '9596169', '中文用户啊', '2', '317', '0', '40712', '565142', '0');
INSERT INTO `order_history` VALUES ('160219191626', '1602191916', '1455880586018', '2168838', 'haha', '4', '458', '0', '0', '223227', '8221519');
INSERT INTO `order_history` VALUES ('160219191626', '1602191916', '1455880586018', '707728', 'haha', '1', '391', '2284', '86253', '223227', '0');
INSERT INTO `order_history` VALUES ('160219191631', '1602191916', '1455880591105', '2168838', '中文用户啊', '2', '155', '0', '0', '223227', '8221519');
INSERT INTO `order_history` VALUES ('160219191631', '1602191916', '1455880591105', '707728', '中文用户啊', '2', '206', '2284', '86253', '223227', '0');
