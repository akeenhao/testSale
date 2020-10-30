/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50649
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50649
File Encoding         : 65001

Date: 2020-10-30 15:58:16
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
-- Table structure for ws_buyer
-- ----------------------------
DROP TABLE IF EXISTS `ws_buyer`;
CREATE TABLE `ws_buyer` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `balance` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ws_order
-- ----------------------------
DROP TABLE IF EXISTS `ws_order`;
CREATE TABLE `ws_order` (
  `id` bigint(20) NOT NULL,
  `store_id` bigint(20) DEFAULT NULL,
  `buyer_id` bigint(20) DEFAULT NULL,
  `parker_id` bigint(20) DEFAULT NULL,
  `total_price` float DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ws_order_details
-- ----------------------------
DROP TABLE IF EXISTS `ws_order_details`;
CREATE TABLE `ws_order_details` (
  `id` bigint(20) NOT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `num` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
-- Table structure for ws_store
-- ----------------------------
DROP TABLE IF EXISTS `ws_store`;
CREATE TABLE `ws_store` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `person_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `balance` decimal(21,2) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `total_order_num` int(11) DEFAULT NULL,
  `point` int(11) DEFAULT NULL,
  `open_time` datetime,
  `area_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
