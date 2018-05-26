
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `member_favor_product`
-- ----------------------------
DROP TABLE IF EXISTS `member_favor_product`;
CREATE TABLE `member_favor_product` (
 	`id` int(11) unsigned NOT NULL AUTO_INCREMENT,
	`member_id` int(10) NULL DEFAULT NULL COMMENT '',
	`product_id` int(10) NULL DEFAULT NULL COMMENT '',
	`create_user_id` int(11) NOT NULL COMMENT '创建用户Id',
	`create_time` int(11) NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
