/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : bumofang

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-03-17 16:49:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `accessory`
-- ----------------------------
DROP TABLE IF EXISTS `accessory`;
CREATE TABLE `accessory` (
  `ID` varchar(40) NOT NULL,
  `NUMBER` varchar(40) NOT NULL COMMENT '编号',
  `NAME` varchar(40) NOT NULL COMMENT '名称',
  `CATEGORY` varchar(40) NOT NULL COMMENT '品类',
  `MATERIAL` varchar(40) NOT NULL COMMENT '材质',
  `SKU` varchar(40) NOT NULL COMMENT '规格',
  `SERIES` varchar(40) NOT NULL COMMENT '系列',
  `FLAT_PICTURE` varchar(255) NOT NULL COMMENT '平面图片',
  `ART_PICTURE` varchar(255) NOT NULL COMMENT '艺术图片',
  `USE_LABELS` varchar(255) NOT NULL COMMENT '用途标签',
  `COLOR_LABELS` varchar(255) NOT NULL COMMENT '颜色标签 ',
  `STYLE_TAGS` varchar(255) NOT NULL COMMENT '风格标签',
  `COLLOCATION_TAGS` varchar(255) NOT NULL COMMENT '搭配标签',
  `DESIGN_TAGS` varchar(255) NOT NULL COMMENT '款式标签',
  `OTHER_TAGS` varchar(255) NOT NULL COMMENT '其他标签',
  `PRICE` decimal(10,2) NOT NULL COMMENT '单价',
  `REMARK` varchar(255) NOT NULL COMMENT '备注',
  `RECORD_ID` varchar(40) NOT NULL COMMENT '记录人ID',
  `RECORD_NAME` varchar(40) NOT NULL COMMENT '记录人',
  `RECORD_DATE` varchar(40) NOT NULL COMMENT '记录时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `c3p0_test`
-- ----------------------------
DROP TABLE IF EXISTS `c3p0_test`;
CREATE TABLE `c3p0_test` (
  `a` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of c3p0_test
-- ----------------------------

-- ----------------------------
-- Table structure for `capital`
-- ----------------------------
DROP TABLE IF EXISTS `capital`;
CREATE TABLE `capital` (
  `ID` varchar(100) NOT NULL,
  `USER_ID` varchar(40) DEFAULT NULL COMMENT '用户ID',
  `USER_NAME` varchar(40) DEFAULT NULL COMMENT '用户名',
  `PAY_PASSWORD` varchar(100) DEFAULT NULL COMMENT '支付密码',
  `MOBILE` varchar(11) NOT NULL COMMENT '手机号',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `BALANCE` decimal(10,2) DEFAULT NULL COMMENT '余额',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of capital
-- ----------------------------

-- ----------------------------
-- Table structure for `fabric`
-- ----------------------------
DROP TABLE IF EXISTS `fabric`;
CREATE TABLE `fabric` (
  `ID` varchar(100) NOT NULL,
  `NUMBER` varchar(40) NOT NULL COMMENT '编号',
  `NAME` varchar(40) NOT NULL COMMENT '品名',
  `COLOR` varchar(60) NOT NULL COMMENT '色号',
  `INGREDIENTS` varchar(60) NOT NULL COMMENT '成份',
  `WIDTH` decimal(10,2) NOT NULL COMMENT '门幅',
  `FLOWER_SIZE` decimal(10,2) NOT NULL COMMENT '花幅',
  `FLOWER_DISTANCE` decimal(10,2) NOT NULL COMMENT '花距',
  `WEIGHT` decimal(10,2) NOT NULL COMMENT '克重',
  `FLAT_PICTURE` varchar(255) DEFAULT NULL COMMENT '平面图片',
  `ART_PICTURE` varchar(255) NOT NULL COMMENT '艺术图片',
  `STREAM_PICTURE` varchar(255) DEFAULT NULL COMMENT '瀑布流图片',
  `USE_LABELS` varchar(255) NOT NULL COMMENT '用途标签',
  `COLOR_LABELS` varchar(255) NOT NULL COMMENT '颜色标签 ',
  `STYLE_TAGS` varchar(255) NOT NULL COMMENT '风格标签',
  `COLLOCATION_TAGS` varchar(255) NOT NULL COMMENT '搭配标签',
  `DESIGN_TAGS` varchar(255) NOT NULL COMMENT '款式标签',
  `OTHER_TAGS` varchar(255) NOT NULL COMMENT '其他标签',
  `PRICE` float(10,2) NOT NULL COMMENT '单价',
  `IS_SHOW` tinyint(1) NOT NULL DEFAULT '0' COMMENT '显示',
  `RECORD_ID` varchar(40) NOT NULL COMMENT '记录人ID',
  `RECORD_NAME` varchar(100) NOT NULL COMMENT '记录人',
  `RECORD_DATE` varchar(40) NOT NULL COMMENT '记录时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `member`
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `ID` varchar(40) NOT NULL,
  `NUMBER` varchar(40) DEFAULT NULL COMMENT '编号',
  `USERNAME` varchar(40) DEFAULT NULL COMMENT '用户名',
  `PASSWORD` varchar(40) DEFAULT NULL COMMENT '密码',
  `NICKNAME` varchar(40) DEFAULT NULL COMMENT '昵称',
  `AVATAR` varchar(255) DEFAULT NULL COMMENT '头像',
  `MOBILE` varchar(11) NOT NULL COMMENT '手机号',
  `PROFESSION` varchar(50) DEFAULT NULL COMMENT '职业',
  `DEFAULT_AREA` varchar(100) DEFAULT NULL COMMENT '默认区域',
  `DEFAULT_ADDRESS` varchar(255) DEFAULT NULL COMMENT '默认地址',
  `REALNAME` varchar(40) DEFAULT NULL COMMENT '真实姓名',
  `ID_CARD` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `BANK_CARD` varchar(40) DEFAULT NULL COMMENT '银行卡号',
  `STATUS` int(2) NOT NULL DEFAULT '1' COMMENT '账号状态',
  `REGISTER_DATE` varchar(30) DEFAULT NULL COMMENT '注册时间',
  `UPDATE_DATE` varchar(30) DEFAULT NULL COMMENT '更新时间',
  `LAST_LOGIN_DATE` varchar(30) DEFAULT NULL COMMENT '最近登录时间',
  `LAST_LOGIN_IP` varchar(30) DEFAULT NULL COMMENT '最近登录IP',
  `WECHAT_ID` varchar(100) DEFAULT NULL COMMENT '三方登录，微信ID',
  `QQ_ID` varchar(100) DEFAULT NULL COMMENT '三方登录，QQID',
  `WEIBO_ID` varchar(100) DEFAULT NULL COMMENT '三方登录，微博ID',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `uq_mobile` (`MOBILE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `num` varchar(20) NOT NULL COMMENT '订单编号',
  `buy_id` varchar(40) NOT NULL COMMENT '购买者id',
  `total_price` float(15,2) unsigned NOT NULL COMMENT '总价',
  `real_pay` float(15,2) unsigned NOT NULL COMMENT '实际支付',
  `discount` float(3,2) unsigned DEFAULT NULL COMMENT '折扣',
  `coupon_id` int(12) unsigned DEFAULT NULL COMMENT '优惠券id',
  `receiver_id` int(12) unsigned NOT NULL COMMENT '接收人id',
  `express_id` varchar(30) DEFAULT NULL COMMENT '快递id',
  `express_name` varchar(100) DEFAULT NULL COMMENT '快递名称',
  `status` int(2) unsigned DEFAULT '0' COMMENT '订单状态',
  `create_time` varchar(30) NOT NULL COMMENT '创建时间',
  `pay_time` varchar(30) DEFAULT NULL COMMENT '支付时间',
  `pay_way` varchar(20) DEFAULT NULL COMMENT '支付方式',
  `pay_account` varchar(150) DEFAULT NULL COMMENT '支付账户',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `order_item`
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `order_num` varchar(20) NOT NULL,
  `fabric_id` varchar(40) NOT NULL COMMENT '面料id',
  `amount` int(10) NOT NULL COMMENT '数量',
  `unit_price` float(15,2) unsigned NOT NULL COMMENT '单价',
  `total_price` float(15,2) unsigned NOT NULL COMMENT '总价',
  `real_pay` float(15,2) unsigned NOT NULL COMMENT '实际支付',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态',
  `discount` float(3,2) DEFAULT NULL COMMENT '折扣',
  `buy_id` varchar(40) NOT NULL COMMENT '购买用户id',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `fk_oi_fabric_id` (`fabric_id`),
  KEY `fk_oi_buy_id` (`buy_id`),
  CONSTRAINT `fk_oi_buy_id` FOREIGN KEY (`buy_id`) REFERENCES `member` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_oi_fabric_id` FOREIGN KEY (`fabric_id`) REFERENCES `fabric` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `receiver`
-- ----------------------------
DROP TABLE IF EXISTS `receiver`;
CREATE TABLE `receiver` (
  `id` int(12) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `mobile` varchar(11) NOT NULL COMMENT '手机号',
  `addr_province` varchar(20) NOT NULL COMMENT '收件人省份',
  `addr_city` varchar(20) NOT NULL COMMENT '收件人城市',
  `addr_county` varchar(20) NOT NULL COMMENT '收件人区、县',
  `addr_detail` varchar(255) NOT NULL COMMENT '收件人详细地址',
  `post_code` varchar(20) NOT NULL COMMENT '邮编',
  `create_user` varchar(40) NOT NULL COMMENT '创建用户id',
  `create_time` varchar(30) NOT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `shopcart`
-- ----------------------------
DROP TABLE IF EXISTS `shopcart`;
CREATE TABLE `shopcart` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `fabric_id` varchar(40) NOT NULL COMMENT '面料id',
  `amount` int(12) NOT NULL COMMENT '数量',
  `unit_price` float(12,2) DEFAULT NULL COMMENT '单价',
  `total_price` float(12,2) DEFAULT NULL COMMENT '总价',
  `buy_id` varchar(40) NOT NULL COMMENT '收件人详细地址',
  `status` int(2) DEFAULT '0' COMMENT '状态',
  `create_time` varchar(30) NOT NULL DEFAULT 'CURRENT_TIMESTAMP' COMMENT '创建时间',
  `update_time` varchar(30) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `sys_app_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_app_user`;
CREATE TABLE `sys_app_user` (
  `USER_ID` varchar(100) NOT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `RIGHTS` varchar(255) DEFAULT NULL,
  `ROLE_ID` varchar(100) DEFAULT NULL,
  `LAST_LOGIN` varchar(255) DEFAULT NULL,
  `IP` varchar(100) DEFAULT NULL,
  `STATUS` varchar(32) DEFAULT NULL,
  `BZ` varchar(255) DEFAULT NULL,
  `PHONE` varchar(100) DEFAULT NULL,
  `SFID` varchar(100) DEFAULT NULL,
  `START_TIME` varchar(100) DEFAULT NULL,
  `END_TIME` varchar(100) DEFAULT NULL,
  `YEARS` int(10) DEFAULT NULL,
  `NUMBER` varchar(100) DEFAULT NULL,
  `EMAIL` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_app_user
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_dictionaries`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionaries`;
CREATE TABLE `sys_dictionaries` (
  `ZD_ID` varchar(100) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `BIANMA` varchar(100) DEFAULT NULL,
  `ORDY_BY` int(10) DEFAULT NULL,
  `PARENT_ID` varchar(100) DEFAULT NULL,
  `JB` int(10) DEFAULT NULL,
  `P_BM` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`ZD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dictionaries
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_gl_qx`
-- ----------------------------
DROP TABLE IF EXISTS `sys_gl_qx`;
CREATE TABLE `sys_gl_qx` (
  `GL_ID` varchar(100) NOT NULL,
  `ROLE_ID` varchar(100) DEFAULT NULL,
  `FX_QX` int(10) DEFAULT NULL,
  `FW_QX` int(10) DEFAULT NULL,
  `QX1` int(10) DEFAULT NULL,
  `QX2` int(10) DEFAULT NULL,
  `QX3` int(10) DEFAULT NULL,
  `QX4` int(10) DEFAULT NULL,
  PRIMARY KEY (`GL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_gl_qx
-- ----------------------------
INSERT INTO `sys_gl_qx` VALUES ('1', '2', '1', '1', '1', '1', '1', '1');
INSERT INTO `sys_gl_qx` VALUES ('1d3e2978e866486682f0934d4e62ed60', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('2', '1', '0', '0', '0', '0', '1', '1');
INSERT INTO `sys_gl_qx` VALUES ('505edae39fa84cfbbc829c96db8070e2', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('90125a0e41654148a68bd79052515032', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('9f1e8811d2bb40939f256d90ed69d077', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('ac66961adaa2426da4470c72ffeec117', '1', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('c51b63e4560e43f99a0afb9d364db388', '1', '0', '0', '0', '0', '0', '0');

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `MENU_ID` int(11) NOT NULL,
  `MENU_NAME` varchar(255) DEFAULT NULL,
  `MENU_URL` varchar(255) DEFAULT NULL,
  `PARENT_ID` varchar(100) DEFAULT NULL,
  `MENU_ORDER` varchar(100) DEFAULT NULL,
  `MENU_ICON` varchar(30) DEFAULT NULL,
  `MENU_TYPE` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '#', '0', '1', 'icon-desktop', '2');
INSERT INTO `sys_menu` VALUES ('2', '组织管理', 'role.do', '1', '2', null, '2');
INSERT INTO `sys_menu` VALUES ('4', '会员管理', 'happuser/listUsers.do', '1', '4', null, '2');
INSERT INTO `sys_menu` VALUES ('5', '系统用户', 'user/listUsers.do', '1', '3', null, '2');
INSERT INTO `sys_menu` VALUES ('6', '信息管理', '#', '0', '2', 'fa fa-list-alt', '2');
INSERT INTO `sys_menu` VALUES ('8', '性能监控', 'druid/index.html', '9', '1', null, '2');
INSERT INTO `sys_menu` VALUES ('22', '适配', 'capital/list.do', '6', '1', null, '2');
INSERT INTO `sys_menu` VALUES ('23', '面料', 'fabric/list.do', '6', '2', null, '2');
INSERT INTO `sys_menu` VALUES ('24', '配饰', 'accessory/list.do', '6', '3', null, '2');
INSERT INTO `sys_menu` VALUES ('25', '用户管理', 'member/list.do', '1', '5', null, '2');
INSERT INTO `sys_menu` VALUES ('26', '用户相关', '#', '0', '3', null, '');
INSERT INTO `sys_menu` VALUES ('27', '订单', 'orders/list.do', '26', '1', null, '');
INSERT INTO `sys_menu` VALUES ('28', '购物车', 'shopcart/list.do', '26', '2', null, '');
INSERT INTO `sys_menu` VALUES ('29', '收件人信息', 'receiver/list.do', '26', '3', null, '');
INSERT INTO `sys_menu` VALUES ('30', '订单商品', 'orderitem/list.do', '26', '4', null, '');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `ROLE_ID` varchar(100) NOT NULL,
  `ROLE_NAME` varchar(100) DEFAULT NULL,
  `RIGHTS` varchar(255) DEFAULT NULL,
  `PARENT_ID` varchar(100) DEFAULT NULL,
  `ADD_QX` varchar(255) DEFAULT NULL,
  `DEL_QX` varchar(255) DEFAULT NULL,
  `EDIT_QX` varchar(255) DEFAULT NULL,
  `CHA_QX` varchar(255) DEFAULT NULL,
  `QX_ID` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统管理员', '2143289462', '0', '1', '1', '1', '1', '1');
INSERT INTO `sys_role` VALUES ('1d3e2978e866486682f0934d4e62ed60', '测试员', '2143289462', '1', '0', '0', '0', '0', '1d3e2978e866486682f0934d4e62ed60');
INSERT INTO `sys_role` VALUES ('2', '超级管理员', '2143289462', '1', '26214518', '125829234', '125829238', '125829238', '2');
INSERT INTO `sys_role` VALUES ('c51b63e4560e43f99a0afb9d364db388', '工厂管理员', '2143289462', '1', '0', '0', '0', '0', 'c51b63e4560e43f99a0afb9d364db388');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `USER_ID` varchar(100) NOT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `RIGHTS` varchar(255) DEFAULT NULL,
  `ROLE_ID` varchar(100) DEFAULT NULL,
  `LAST_LOGIN` varchar(255) DEFAULT NULL,
  `IP` varchar(100) DEFAULT NULL,
  `STATUS` varchar(32) DEFAULT NULL,
  `BZ` varchar(255) DEFAULT NULL,
  `SKIN` varchar(100) DEFAULT NULL,
  `EMAIL` varchar(32) DEFAULT NULL,
  `NUMBER` varchar(100) DEFAULT NULL,
  `PHONE` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '729bef0bc3db5f100e55c5a230632c90d2c0bc5a', '系统管理员', '1133671055321055258374707980945218933803269864762743594642571294', '1', '2017-03-16 16:57:04', '0:0:0:0:0:0:0:1', '0', '最高统治者', 'default', 'admin@main.com', '001', '18788888888');
INSERT INTO `sys_user` VALUES ('5422a98089e240c6a9e776b29b82d74b', 'lili', 'b38130ad631e41b02f444dc1725799a3c43358af', '李立', '', 'c51b63e4560e43f99a0afb9d364db388', '2017-03-10 10:09:52', '0:0:0:0:0:0:0:1', '0', '', 'default', '', null, '13675892929');

-- ----------------------------
-- Table structure for `sys_user_qx`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_qx`;
CREATE TABLE `sys_user_qx` (
  `U_ID` varchar(100) NOT NULL,
  `C1` int(10) DEFAULT NULL,
  `C2` int(10) DEFAULT NULL,
  `C3` int(10) DEFAULT NULL,
  `C4` int(10) DEFAULT NULL,
  `Q1` int(10) DEFAULT NULL,
  `Q2` int(10) DEFAULT NULL,
  `Q3` int(10) DEFAULT NULL,
  `Q4` int(10) DEFAULT NULL,
  PRIMARY KEY (`U_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_qx
-- ----------------------------
INSERT INTO `sys_user_qx` VALUES ('1', '1', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('1d3e2978e866486682f0934d4e62ed60', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('2', '0', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `sys_user_qx` VALUES ('505edae39fa84cfbbc829c96db8070e2', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('90125a0e41654148a68bd79052515032', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('9f1e8811d2bb40939f256d90ed69d077', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('ac66961adaa2426da4470c72ffeec117', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('c51b63e4560e43f99a0afb9d364db388', '0', '0', '0', '0', '0', '0', '0', '0');

-- ----------------------------
-- Table structure for `tb_pictures`
-- ----------------------------
DROP TABLE IF EXISTS `tb_pictures`;
CREATE TABLE `tb_pictures` (
  `PICTURES_ID` varchar(100) NOT NULL,
  `TITLE` varchar(255) DEFAULT NULL COMMENT '标题',
  `NAME` varchar(255) DEFAULT NULL COMMENT '文件名',
  `PATH` varchar(255) DEFAULT NULL COMMENT '路径',
  `CREATETIME` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `MASTER_ID` varchar(255) DEFAULT NULL COMMENT '属于',
  `BZ` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`PICTURES_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_pictures
-- ----------------------------
