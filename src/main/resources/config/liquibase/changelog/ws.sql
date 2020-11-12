/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50649
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50649
File Encoding         : 65001

Date: 2020-11-12 16:48:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ws_area
-- ----------------------------
DROP TABLE IF EXISTS `ws_area`;
CREATE TABLE `ws_area` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ws_area
-- ----------------------------
INSERT INTO `ws_area` VALUES ('1', '测试区域1', '38|137', '幸福');
INSERT INTO `ws_area` VALUES ('2', 'Ergonomic Steel Bacon Industrial', 'New Israeli Sheqel CFP Franc', 'District South Africa');
INSERT INTO `ws_area` VALUES ('3', 'niches', 'panel efficient Lead', 'Graphic Interface');
INSERT INTO `ws_area` VALUES ('4', 'Shoes alarm SQL', 'Place Intranet', 'Virtual deposit');
INSERT INTO `ws_area` VALUES ('5', 'vortals', 'zero administration Pitcairn Islands invoice', 'Metrics cross-media');
INSERT INTO `ws_area` VALUES ('6', 'FTP Seychelles Rupee Corporate', 'Pennsylvania generate Fantastic Metal Chicken', 'e-services Chips');
INSERT INTO `ws_area` VALUES ('7', '6th generation Human', 'project Chips embrace', 'Practical Cambridgeshire');
INSERT INTO `ws_area` VALUES ('8', 'quantify HDD Savings Account', 'Iowa Pants', 'Metrics solid state Investment Account');
INSERT INTO `ws_area` VALUES ('9', 'Handmade Frozen Mouse generating', 'grow Cambridgeshire', 'Ireland orchestrate auxiliary');
INSERT INTO `ws_area` VALUES ('10', 'synthesizing impactful Intelligent', 'Borders bluetooth Manager', 'Metal');

-- ----------------------------
-- Table structure for ws_buyer
-- ----------------------------
DROP TABLE IF EXISTS `ws_buyer`;
CREATE TABLE `ws_buyer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `balance` float DEFAULT '0',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ws_buyer
-- ----------------------------
INSERT INTO `ws_buyer` VALUES ('1', 'uzi', '15166660000', 'monitor portal Avon', '', '888', '1');
INSERT INTO `ws_buyer` VALUES ('2', 'jams', '15166660001', 'Coordinator ubiquitous Metal', '', '0', '1');
INSERT INTO `ws_buyer` VALUES ('3', 'weide', '15166660002', 'e-enable sky blue', '', '0', '1');
INSERT INTO `ws_buyer` VALUES ('4', '布鲁克斯', '15166660003', 'Savings Account distributed Kip', '', '0', '1');
INSERT INTO `ws_buyer` VALUES ('5', '白冰', '15166660004', 'redundant challenge', '', '0', '1');
INSERT INTO `ws_buyer` VALUES ('6', '小鱼儿', '15166660005', 'generate Steel sky blue', '', '0', '1');
INSERT INTO `ws_buyer` VALUES ('7', '三蹦子', '15166660005', 'Niger', '', '0', '1');
INSERT INTO `ws_buyer` VALUES ('8', '李大叔', '15166660006', 'Associate Engineer Mouse', '', '0', '1');
INSERT INTO `ws_buyer` VALUES ('9', '刘阳', '15166660007', 'Stravenue', '', '0', '1');
INSERT INTO `ws_buyer` VALUES ('10', '字母哥', '15166660008', 'Quetzal', '', '0', '1');
INSERT INTO `ws_buyer` VALUES ('11', '小二', '11100000000', 'abc', '', null, '1');
INSERT INTO `ws_buyer` VALUES ('12', '小二2', '11100000001', 'abc', '', null, '1');
INSERT INTO `ws_buyer` VALUES ('13', '小二22', '11100000002', 'abc', '', '0', '1');
INSERT INTO `ws_buyer` VALUES ('14', 'string', 'string', 'string', '', '0', 'string');
INSERT INTO `ws_buyer` VALUES ('16', '用户1', '11177771111', '住址1', '', '5', '1');

-- ----------------------------
-- Table structure for ws_order
-- ----------------------------
DROP TABLE IF EXISTS `ws_order`;
CREATE TABLE `ws_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `store_id` bigint(20) DEFAULT NULL,
  `buyer_id` bigint(20) DEFAULT NULL,
  `parker_id` bigint(20) DEFAULT NULL,
  `total_price` float DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `remark` varchar(2550) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ws_order
-- ----------------------------
INSERT INTO `ws_order` VALUES ('10', '1', '1', null, '6', '已签收', null, null, null);
INSERT INTO `ws_order` VALUES ('11', '1', '1', null, '12', '等待商家接单', null, '2020-11-02 08:21:22', null);
INSERT INTO `ws_order` VALUES ('12', '1', '1', null, '30', '等待商家接单', null, '2020-11-02 08:25:37', '2020-11-02 08:25:37');
INSERT INTO `ws_order` VALUES ('13', '15', '16', null, '65', '已签收', null, '2020-11-09 06:33:12', '2020-11-09 06:33:12');
INSERT INTO `ws_order` VALUES ('14', '15', '16', null, '65', '等待商家接单', null, '2020-11-09 06:33:55', '2020-11-09 06:33:55');

-- ----------------------------
-- Table structure for ws_order_details
-- ----------------------------
DROP TABLE IF EXISTS `ws_order_details`;
CREATE TABLE `ws_order_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `num` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ws_order_details
-- ----------------------------
INSERT INTO `ws_order_details` VALUES ('6', '10', '1', '3', null, '2');
INSERT INTO `ws_order_details` VALUES ('7', '11', '2', '4', null, '3');
INSERT INTO `ws_order_details` VALUES ('8', '12', '3', '6', null, '5');
INSERT INTO `ws_order_details` VALUES ('9', '13', '11', '10', null, '5');
INSERT INTO `ws_order_details` VALUES ('10', '13', '12', '5', null, '3');
INSERT INTO `ws_order_details` VALUES ('11', '14', '11', '10', null, '5');
INSERT INTO `ws_order_details` VALUES ('12', '14', '12', '5', null, '3');

-- ----------------------------
-- Table structure for ws_product
-- ----------------------------
DROP TABLE IF EXISTS `ws_product`;
CREATE TABLE `ws_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `store_id` bigint(20) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `total_sales_num` int(11) DEFAULT NULL,
  `point` int(11) DEFAULT NULL,
  `del_flag` smallint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ws_product
-- ----------------------------
INSERT INTO `ws_product` VALUES ('1', '胡萝卜', 'none', '1', '3', '斤', 'Mouse Investor partnerships', '0', '2', '0');
INSERT INTO `ws_product` VALUES ('2', '西红柿', 'none', '1', '4', '斤', 'Ramp', '10', '1', '0');
INSERT INTO `ws_product` VALUES ('3', '白萝卜', 'none', '2', '6', '斤', 'Specialist copy programming', '0', '0', '0');
INSERT INTO `ws_product` VALUES ('4', '黄瓜', 'none', '2', '55', '斤', 'Avon sky blue', '0', '0', '0');
INSERT INTO `ws_product` VALUES ('5', '西瓜', 'none', '2', '6', '斤', 'North Korean Won Cambridgeshire', '0', '0', '0');
INSERT INTO `ws_product` VALUES ('6', '生菜', 'none', '1', '7', '斤', 'Functionality', '0', '0', '0');
INSERT INTO `ws_product` VALUES ('7', '韭菜', 'none', '1', '8', '斤', 'orchestrate magnetic', '0', '0', '0');
INSERT INTO `ws_product` VALUES ('8', '土豆', 'none', '3', '22', '斤', 'Soft', '0', '0', '0');
INSERT INTO `ws_product` VALUES ('9', '火龙果', 'none', '3', '11', '斤', 'green', '0', '0', '0');
INSERT INTO `ws_product` VALUES ('10', '牛肉干', 'none', '4', '18', '斤', 'Switchable Mouse engineer', '0', '0', '0');
INSERT INTO `ws_product` VALUES ('11', '商品1', null, '15', '10', '斤', 'test', null, '10', '0');
INSERT INTO `ws_product` VALUES ('12', '商品2', null, '15', '5', '斤', '论个卖->按斤卖', null, '9', '0');

-- ----------------------------
-- Table structure for ws_shop_cart
-- ----------------------------
DROP TABLE IF EXISTS `ws_shop_cart`;
CREATE TABLE `ws_shop_cart` (
  `id` bigint(20) NOT NULL,
  `buyer_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `num` float DEFAULT NULL,
  `total_price` float DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ws_shop_cart
-- ----------------------------
INSERT INTO `ws_shop_cart` VALUES ('1', '99019', '86041', '93231', '81518', '\0');
INSERT INTO `ws_shop_cart` VALUES ('2', '18297', '64771', '46349', '22196', '');
INSERT INTO `ws_shop_cart` VALUES ('3', '38715', '56535', '92541', '3533', '\0');
INSERT INTO `ws_shop_cart` VALUES ('4', '3296', '89476', '22262', '72366', '\0');
INSERT INTO `ws_shop_cart` VALUES ('5', '66308', '78542', '91849', '60843', '');
INSERT INTO `ws_shop_cart` VALUES ('6', '14230', '60769', '8812', '76827', '\0');
INSERT INTO `ws_shop_cart` VALUES ('7', '31642', '74833', '58430', '13150', '');
INSERT INTO `ws_shop_cart` VALUES ('8', '33582', '85259', '11898', '73383', '');
INSERT INTO `ws_shop_cart` VALUES ('9', '18318', '34880', '15519', '29200', '\0');
INSERT INTO `ws_shop_cart` VALUES ('10', '6374', '57721', '63931', '98004', '');

-- ----------------------------
-- Table structure for ws_store
-- ----------------------------
DROP TABLE IF EXISTS `ws_store`;
CREATE TABLE `ws_store` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `person_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `balance` decimal(21,2) DEFAULT '0.00',
  `status` bit(1) DEFAULT b'1',
  `picture` varchar(255) DEFAULT NULL,
  `total_order_num` int(11) DEFAULT '0',
  `point` int(11) DEFAULT '0',
  `open_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `area_id` bigint(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ws_store
-- ----------------------------
INSERT INTO `ws_store` VALUES ('1', '小李', '小李', '12300000000', 'Investment Account Seamless Soft', '6.00', '', 'none', '1', '1', '2020-09-14 06:31:25', '1', '1');
INSERT INTO `ws_store` VALUES ('2', '小王', '小王', '12300000001', 'markets Frozen', '0.00', '', 'none', '2', '2', '2020-09-14 07:05:04', '1', '1');
INSERT INTO `ws_store` VALUES ('3', '小刘', '小刘', '12300000002', '24/365', '0.00', '', 'none', '0', '0', '2020-09-14 18:19:48', '1', '1');
INSERT INTO `ws_store` VALUES ('4', '小刚', '小刚', '12300000003', 'Licensed PCI e-commerce', '0.00', '', 'none', '0', '0', '2020-09-14 03:48:11', '1', '1');
INSERT INTO `ws_store` VALUES ('5', '小小', '小小', '12300000004', 'web-readiness', '0.00', '', 'none', '0', '0', '2020-09-13 20:08:41', '1', '1');
INSERT INTO `ws_store` VALUES ('6', '大雄', '大雄', '12300000005', 'pink quantifying Pre-emptive', '0.00', '', 'none', '0', '0', '2020-09-14 07:25:45', '1', '1');
INSERT INTO `ws_store` VALUES ('7', '静香', '静香', '12300000006', 'Creative Shoes open-source', '0.00', '', 'none', '0', '0', '2020-09-14 08:43:13', '1', '1');
INSERT INTO `ws_store` VALUES ('8', '胖虎', '胖虎', '12300000007', 'dedicated', '0.00', '', 'none', '0', '0', '2020-09-14 09:20:55', '1', '1');
INSERT INTO `ws_store` VALUES ('9', '哆啦A梦', '哆啦A梦', '12300000008', 'Pants Chicken User-centric', '0.00', '', 'none', '0', '0', '2020-09-14 16:47:24', '1', '1');
INSERT INTO `ws_store` VALUES ('10', '西伯利亚狼', '西伯利亚狼', '12300000009', 'invoice AI', '0.00', '', 'none', '0', '0', '2020-09-14 14:30:21', '1', '1');
INSERT INTO `ws_store` VALUES ('11', '门店1', '门店主1', '11122226666', '门店地址1', '0.00', null, null, null, '0', null, '1', null);
INSERT INTO `ws_store` VALUES ('12', '门店2', '门店主2', '11122228888', '门店地址2', '0.00', null, null, null, '0', null, '1', null);
INSERT INTO `ws_store` VALUES ('13', '门店3', '门店主3', '11111111111', '门店地址3', '0.00', null, null, null, '0', null, '1', '111');
INSERT INTO `ws_store` VALUES ('14', '门店4', '门店主4', '11111111114', '门店地址4', '0.00', null, null, null, '0', null, '1', '4');
INSERT INTO `ws_store` VALUES ('15', '门店5', '门店主5', '11111111115', '门店地址5', '65.00', null, null, null, '0', null, '1', '5');
INSERT INTO `ws_store` VALUES ('16', '门店6', '门店主6', '11111111116', '门店地址6', '0.00', '', null, null, '0', null, '1', '6');
INSERT INTO `ws_store` VALUES ('17', '门店7', '门店主7', '11111111117', '门店地址7', '0.00', '', null, '0', '0', '2020-11-12 07:39:16', '1', '7');
