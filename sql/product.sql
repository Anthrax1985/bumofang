
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
 	`id` int(11) unsigned NOT NULL AUTO_INCREMENT,
	`product_name` varchar(255) NULL DEFAULT NULL COMMENT '',
	`product_craft` varchar(255) NULL DEFAULT NULL COMMENT '',
	`product_material` varchar(255) NULL DEFAULT NULL COMMENT '',
	`product_narrow_price` decimal(12,2) NULL DEFAULT NULL COMMENT '',
	`product_wide_price` decimal(12,2) NULL DEFAULT NULL COMMENT '',
	`product_component` varchar(255) NULL DEFAULT NULL COMMENT '',
	`product_unit_weight` decimal(12,2) NULL DEFAULT NULL COMMENT '',
	`product_narrow_width` decimal(12,2) NULL DEFAULT NULL COMMENT '',
	`product_wide_width` decimal(12,2) NULL DEFAULT NULL COMMENT '',
	`pattern_horizontal_size` decimal(12,2) NULL DEFAULT NULL COMMENT '',
	`pattern_vertical_size` decimal(12,2) NULL DEFAULT NULL COMMENT '',
	`product_source_area` varchar(255) NULL DEFAULT NULL COMMENT '',
	`pattern_picture1` varchar(255) NULL DEFAULT NULL COMMENT '',
	`pattern_picture2` varchar(255) NULL DEFAULT NULL COMMENT '',
	`quality_picture1` varchar(255) NULL DEFAULT NULL COMMENT '',
	`quality_picture2` varchar(255) NULL DEFAULT NULL COMMENT '',
	`quality_picture3` varchar(255) NULL DEFAULT NULL COMMENT '',
	`quality_picture4` varchar(255) NULL DEFAULT NULL COMMENT '',
	`quality_control_report` varchar(255) NULL DEFAULT NULL COMMENT '',
	`del_flag` int(2) NULL DEFAULT NULL COMMENT '',
	`create_user_id` int(11) NOT NULL COMMENT '创建用户Id',
	`create_time` int(11) NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
