
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `product_param_material`
-- ----------------------------
DROP TABLE IF EXISTS `product_param_material`;
CREATE TABLE `product_param_material` (
 	`id` int(11) unsigned NOT NULL AUTO_INCREMENT,
	`material_name` varchar(255) NULL DEFAULT NULL COMMENT '',
	`material_order` int(10) NULL DEFAULT NULL COMMENT '',
	`create_user_id` int(11) NOT NULL COMMENT '创建用户Id',
	`create_time` int(11) NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
