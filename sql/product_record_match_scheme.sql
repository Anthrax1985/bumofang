
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `product_record_match_scheme`
-- ----------------------------
DROP TABLE IF EXISTS `product_record_match_scheme`;
CREATE TABLE `product_record_match_scheme` (
 	`id` int(11) unsigned NOT NULL AUTO_INCREMENT,
	`product_id` int(10) NULL DEFAULT NULL COMMENT '',
	`match_product_id` int(10) NULL DEFAULT NULL COMMENT '',
	`match_product_icon` varchar(200) NULL DEFAULT NULL COMMENT '',
	`create_user_id` int(11) NOT NULL COMMENT '创建用户Id',
	`create_time` int(11) NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
