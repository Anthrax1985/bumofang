
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `product_param_craft`
-- ----------------------------
DROP TABLE IF EXISTS `product_param_craft`;
CREATE TABLE `product_param_craft` (
 	`id` int(11) unsigned NOT NULL AUTO_INCREMENT,
	`craft_name` varchar(255) NULL DEFAULT NULL COMMENT '',
	`craft_order` int(10) NULL DEFAULT NULL COMMENT '',
	`create_user_id` int(11) NOT NULL COMMENT '创建用户Id',
	`create_time` int(11) NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
