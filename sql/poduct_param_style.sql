
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `product_param_style`
-- ----------------------------
DROP TABLE IF EXISTS `product_param_style`;
CREATE TABLE `product_param_style` (
 	`id` int(11) unsigned NOT NULL AUTO_INCREMENT,
	`style_name` varchar(255) NULL DEFAULT NULL COMMENT '',
	`style_order` int(10) NULL DEFAULT NULL COMMENT '',
	`create_user_id` int(11) NOT NULL COMMENT '创建用户Id',
	`create_time` int(11) NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
