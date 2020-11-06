/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50649
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50649
File Encoding         : 65001

Date: 2020-11-06 15:54:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for databasechangelog
-- ----------------------------
DROP TABLE IF EXISTS `databasechangelog`;
CREATE TABLE `databasechangelog` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of databasechangelog
-- ----------------------------
INSERT INTO `databasechangelog` VALUES ('00000000000000', 'jhipster', 'config/liquibase/changelog/00000000000000_initial_schema.xml', '2020-10-27 11:45:16', '1', 'EXECUTED', '8:b8c27d9dc8db18b5de87cdb8c38a416b', 'createSequence sequenceName=sequence_generator', '', null, '3.9.0', null, null, '3770316885');
INSERT INTO `databasechangelog` VALUES ('00000000000001', 'jhipster', 'config/liquibase/changelog/00000000000000_initial_schema.xml', '2020-10-27 11:45:17', '2', 'EXECUTED', '8:3c2fb2f2606417392082d55679e608bd', 'createTable tableName=jhi_persistent_audit_event; createTable tableName=jhi_persistent_audit_evt_data; addPrimaryKey tableName=jhi_persistent_audit_evt_data; createIndex indexName=idx_persistent_audit_event, tableName=jhi_persistent_audit_event; c...', '', null, '3.9.0', null, null, '3770316885');
INSERT INTO `databasechangelog` VALUES ('20200915020815-1', 'jhipster', 'config/liquibase/changelog/20200915020815_added_entity_WsArea.xml', '2020-10-27 11:45:17', '3', 'EXECUTED', '8:30a6941602d8a17a015f80cb8009e05f', 'createTable tableName=ws_area', '', null, '3.9.0', null, null, '3770316885');
INSERT INTO `databasechangelog` VALUES ('20200915020815-1-relations', 'jhipster', 'config/liquibase/changelog/20200915020815_added_entity_WsArea.xml', '2020-10-27 11:45:17', '4', 'EXECUTED', '8:d41d8cd98f00b204e9800998ecf8427e', 'empty', '', null, '3.9.0', null, null, '3770316885');
INSERT INTO `databasechangelog` VALUES ('20200915020815-1-data', 'jhipster', 'config/liquibase/changelog/20200915020815_added_entity_WsArea.xml', '2020-10-27 11:45:17', '5', 'EXECUTED', '8:80c31bbd10ca7de370e78487b4e61ea9', 'loadData tableName=ws_area', '', null, '3.9.0', 'faker', null, '3770316885');
INSERT INTO `databasechangelog` VALUES ('20200915022620-1', 'jhipster', 'config/liquibase/changelog/20200915022620_added_entity_WsStore.xml', '2020-10-27 11:45:17', '6', 'EXECUTED', '8:2718fe6f7a2aa5c70f10ff3c992d29ec', 'createTable tableName=ws_store; dropDefaultValue columnName=open_time, tableName=ws_store', '', null, '3.9.0', null, null, '3770316885');
INSERT INTO `databasechangelog` VALUES ('20200915022620-1-relations', 'jhipster', 'config/liquibase/changelog/20200915022620_added_entity_WsStore.xml', '2020-10-27 11:45:17', '7', 'EXECUTED', '8:d41d8cd98f00b204e9800998ecf8427e', 'empty', '', null, '3.9.0', null, null, '3770316885');
INSERT INTO `databasechangelog` VALUES ('20200915022620-1-data', 'jhipster', 'config/liquibase/changelog/20200915022620_added_entity_WsStore.xml', '2020-10-27 11:45:17', '8', 'EXECUTED', '8:8edd0fbf3c84eb793575087dadda4c7c', 'loadData tableName=ws_store', '', null, '3.9.0', 'faker', null, '3770316885');
INSERT INTO `databasechangelog` VALUES ('20200915023047-1', 'jhipster', 'config/liquibase/changelog/20200915023047_added_entity_WsProduct.xml', '2020-10-27 11:45:17', '9', 'EXECUTED', '8:06c47ef091038c5ab5605add2f125986', 'createTable tableName=ws_product', '', null, '3.9.0', null, null, '3770316885');
INSERT INTO `databasechangelog` VALUES ('20200915023047-1-relations', 'jhipster', 'config/liquibase/changelog/20200915023047_added_entity_WsProduct.xml', '2020-10-27 11:45:17', '10', 'EXECUTED', '8:d41d8cd98f00b204e9800998ecf8427e', 'empty', '', null, '3.9.0', null, null, '3770316885');
INSERT INTO `databasechangelog` VALUES ('20200915023047-1-data', 'jhipster', 'config/liquibase/changelog/20200915023047_added_entity_WsProduct.xml', '2020-10-27 11:45:17', '11', 'EXECUTED', '8:e2a779f5e04757b57e3a6bd5f8ef079a', 'loadData tableName=ws_product', '', null, '3.9.0', 'faker', null, '3770316885');
INSERT INTO `databasechangelog` VALUES ('20200915023417-1', 'jhipster', 'config/liquibase/changelog/20200915023417_added_entity_WsBuyer.xml', '2020-10-27 11:45:17', '12', 'EXECUTED', '8:85dc24ff45f13ba04e259f43e3b63d3c', 'createTable tableName=ws_buyer', '', null, '3.9.0', null, null, '3770316885');
INSERT INTO `databasechangelog` VALUES ('20200915023417-1-relations', 'jhipster', 'config/liquibase/changelog/20200915023417_added_entity_WsBuyer.xml', '2020-10-27 11:45:17', '13', 'EXECUTED', '8:d41d8cd98f00b204e9800998ecf8427e', 'empty', '', null, '3.9.0', null, null, '3770316885');
INSERT INTO `databasechangelog` VALUES ('20200915023417-1-data', 'jhipster', 'config/liquibase/changelog/20200915023417_added_entity_WsBuyer.xml', '2020-10-27 11:45:17', '14', 'EXECUTED', '8:c9ac9345280931ce6c512ffc18e1bf99', 'loadData tableName=ws_buyer', '', null, '3.9.0', 'faker', null, '3770316885');
INSERT INTO `databasechangelog` VALUES ('20200915023935-1', 'jhipster', 'config/liquibase/changelog/20200915023935_added_entity_WsShopCart.xml', '2020-10-27 11:45:17', '15', 'EXECUTED', '8:79221c4997f05cd9f9789eb36b8509a6', 'createTable tableName=ws_shop_cart', '', null, '3.9.0', null, null, '3770316885');
INSERT INTO `databasechangelog` VALUES ('20200915023935-1-relations', 'jhipster', 'config/liquibase/changelog/20200915023935_added_entity_WsShopCart.xml', '2020-10-27 11:45:17', '16', 'EXECUTED', '8:d41d8cd98f00b204e9800998ecf8427e', 'empty', '', null, '3.9.0', null, null, '3770316885');
INSERT INTO `databasechangelog` VALUES ('20200915023935-1-data', 'jhipster', 'config/liquibase/changelog/20200915023935_added_entity_WsShopCart.xml', '2020-10-27 11:45:17', '17', 'EXECUTED', '8:6316595c797c286af6a7d8c7a8ec0bed', 'loadData tableName=ws_shop_cart', '', null, '3.9.0', 'faker', null, '3770316885');
INSERT INTO `databasechangelog` VALUES ('20200915024209-1', 'jhipster', 'config/liquibase/changelog/20200915024209_added_entity_WsOrder.xml', '2020-10-27 11:45:17', '18', 'EXECUTED', '8:b8d0388f1f4b15e1bbfd0bda075c6ee9', 'createTable tableName=ws_order', '', null, '3.9.0', null, null, '3770316885');
INSERT INTO `databasechangelog` VALUES ('20200915024209-1-relations', 'jhipster', 'config/liquibase/changelog/20200915024209_added_entity_WsOrder.xml', '2020-10-27 11:45:17', '19', 'EXECUTED', '8:d41d8cd98f00b204e9800998ecf8427e', 'empty', '', null, '3.9.0', null, null, '3770316885');
INSERT INTO `databasechangelog` VALUES ('20200915024209-1-data', 'jhipster', 'config/liquibase/changelog/20200915024209_added_entity_WsOrder.xml', '2020-10-27 11:45:17', '20', 'EXECUTED', '8:2766b1783879ad7fab1286d6cd1a8c52', 'loadData tableName=ws_order', '', null, '3.9.0', 'faker', null, '3770316885');
INSERT INTO `databasechangelog` VALUES ('20200915024749-1', 'jhipster', 'config/liquibase/changelog/20200915024749_added_entity_WsOrderDetails.xml', '2020-10-27 11:45:17', '21', 'EXECUTED', '8:69bec096f53d8d029e2b55516af7de4e', 'createTable tableName=ws_order_details', '', null, '3.9.0', null, null, '3770316885');
INSERT INTO `databasechangelog` VALUES ('20200915024749-1-relations', 'jhipster', 'config/liquibase/changelog/20200915024749_added_entity_WsOrderDetails.xml', '2020-10-27 11:45:17', '22', 'EXECUTED', '8:d41d8cd98f00b204e9800998ecf8427e', 'empty', '', null, '3.9.0', null, null, '3770316885');
INSERT INTO `databasechangelog` VALUES ('20200915024749-1-data', 'jhipster', 'config/liquibase/changelog/20200915024749_added_entity_WsOrderDetails.xml', '2020-10-27 11:45:17', '23', 'EXECUTED', '8:37a6f2472cfde644f447e294eb6ad4a2', 'loadData tableName=ws_order_details', '', null, '3.9.0', 'faker', null, '3770316885');

-- ----------------------------
-- Table structure for databasechangeloglock
-- ----------------------------
DROP TABLE IF EXISTS `databasechangeloglock`;
CREATE TABLE `databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of databasechangeloglock
-- ----------------------------
INSERT INTO `databasechangeloglock` VALUES ('1', '\0', null, null);

-- ----------------------------
-- Table structure for jhi_persistent_audit_event
-- ----------------------------
DROP TABLE IF EXISTS `jhi_persistent_audit_event`;
CREATE TABLE `jhi_persistent_audit_event` (
  `event_id` bigint(20) NOT NULL,
  `principal` varchar(50) NOT NULL,
  `event_date` timestamp NULL DEFAULT NULL,
  `event_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  KEY `idx_persistent_audit_event` (`principal`,`event_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jhi_persistent_audit_event
-- ----------------------------

-- ----------------------------
-- Table structure for jhi_persistent_audit_evt_data
-- ----------------------------
DROP TABLE IF EXISTS `jhi_persistent_audit_evt_data`;
CREATE TABLE `jhi_persistent_audit_evt_data` (
  `event_id` bigint(20) NOT NULL,
  `name` varchar(150) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`,`name`),
  KEY `idx_persistent_audit_evt_data` (`event_id`),
  CONSTRAINT `fk_evt_pers_audit_evt_data` FOREIGN KEY (`event_id`) REFERENCES `jhi_persistent_audit_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jhi_persistent_audit_evt_data
-- ----------------------------

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
INSERT INTO `ws_area` VALUES ('1', 'copying', 'Roads', 'drive Grass-roots');
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ws_buyer
-- ----------------------------
INSERT INTO `ws_buyer` VALUES ('1', 'uzi', '15166660000', 'monitor portal Avon', '', '358', '1');
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
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ws_order
-- ----------------------------
INSERT INTO `ws_order` VALUES ('10', '1', '1', null, '6', '已签收', null, null);
INSERT INTO `ws_order` VALUES ('11', '1', '1', null, '12', '等待商家接单', '2020-11-02 08:21:22', null);
INSERT INTO `ws_order` VALUES ('12', '1', '1', null, '30', '等待商家接单', '2020-11-02 08:25:37', '2020-11-02 08:25:37');

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ws_order_details
-- ----------------------------
INSERT INTO `ws_order_details` VALUES ('6', '10', '1', '3', null, '2');
INSERT INTO `ws_order_details` VALUES ('7', '11', '2', '4', null, '3');
INSERT INTO `ws_order_details` VALUES ('8', '12', '3', '6', null, '5');

-- ----------------------------
-- Table structure for ws_product
-- ----------------------------
DROP TABLE IF EXISTS `ws_product`;
CREATE TABLE `ws_product` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `store_id` bigint(20) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `total_sales_num` int(11) DEFAULT NULL,
  `point` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ws_product
-- ----------------------------
INSERT INTO `ws_product` VALUES ('1', '胡萝卜', 'none', '1', '3', '斤', 'Mouse Investor partnerships', '0', '0');
INSERT INTO `ws_product` VALUES ('2', '西红柿', 'none', '1', '4', '斤', 'Ramp', '0', '0');
INSERT INTO `ws_product` VALUES ('3', '白萝卜', 'none', '2', '6', '斤', 'Specialist copy programming', '0', '0');
INSERT INTO `ws_product` VALUES ('4', '黄瓜', 'none', '2', '55', '斤', 'Avon sky blue', '0', '0');
INSERT INTO `ws_product` VALUES ('5', '西瓜', 'none', '2', '6', '斤', 'North Korean Won Cambridgeshire', '0', '0');
INSERT INTO `ws_product` VALUES ('6', '生菜', 'none', '1', '7', '斤', 'Functionality', '0', '0');
INSERT INTO `ws_product` VALUES ('7', '韭菜', 'none', '1', '8', '斤', 'orchestrate magnetic', '0', '0');
INSERT INTO `ws_product` VALUES ('8', '土豆', 'none', '3', '22', '斤', 'Soft', '0', '0');
INSERT INTO `ws_product` VALUES ('9', '火龙果', 'none', '3', '11', '斤', 'green', '0', '0');
INSERT INTO `ws_product` VALUES ('10', '牛肉干', 'none', '4', '18', '斤', 'Switchable Mouse engineer', '0', '0');

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
  `status` bit(1) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `total_order_num` int(11) DEFAULT NULL,
  `point` int(11) DEFAULT NULL,
  `open_time` datetime,
  `area_id` bigint(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ws_store
-- ----------------------------
INSERT INTO `ws_store` VALUES ('1', '小李', '小李', '12300000000', 'Investment Account Seamless Soft', '6.00', '', 'none', '0', '0', '2020-09-14 06:31:25', '1', '1');
INSERT INTO `ws_store` VALUES ('2', '小王', '小王', '12300000001', 'markets Frozen', '0.00', '', 'none', '0', '0', '2020-09-14 07:05:04', '1', '1');
INSERT INTO `ws_store` VALUES ('3', '小刘', '小刘', '12300000002', '24/365', '0.00', '', 'none', '0', '0', '2020-09-14 18:19:48', '1', '1');
INSERT INTO `ws_store` VALUES ('4', '小刚', '小刚', '12300000003', 'Licensed PCI e-commerce', '0.00', '', 'none', '0', '0', '2020-09-14 03:48:11', '1', '1');
INSERT INTO `ws_store` VALUES ('5', '小小', '小小', '12300000004', 'web-readiness', '0.00', '', 'none', '0', '0', '2020-09-13 20:08:41', '1', '1');
INSERT INTO `ws_store` VALUES ('6', '大雄', '大雄', '12300000005', 'pink quantifying Pre-emptive', '0.00', '', 'none', '0', '0', '2020-09-14 07:25:45', '1', '1');
INSERT INTO `ws_store` VALUES ('7', '静香', '静香', '12300000006', 'Creative Shoes open-source', '0.00', '', 'none', '0', '0', '2020-09-14 08:43:13', '1', '1');
INSERT INTO `ws_store` VALUES ('8', '胖虎', '胖虎', '12300000007', 'dedicated', '0.00', '', 'none', '0', '0', '2020-09-14 09:20:55', '1', '1');
INSERT INTO `ws_store` VALUES ('9', '哆啦A梦', '哆啦A梦', '12300000008', 'Pants Chicken User-centric', '0.00', '', 'none', '0', '0', '2020-09-14 16:47:24', '1', '1');
INSERT INTO `ws_store` VALUES ('10', '西伯利亚狼', '西伯利亚狼', '12300000009', 'invoice AI', '0.00', '', 'none', '0', '0', '2020-09-14 14:30:21', '1', '1');
