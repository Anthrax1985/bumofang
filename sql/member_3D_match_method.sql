
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `member_3D_match_method`
-- ----------------------------
DROP TABLE IF EXISTS `member_3D_match_method`;
CREATE TABLE `member_3D_match_method` (
 	`id` int(11) unsigned NOT NULL AUTO_INCREMENT,
	`member_id` int(10) NULL DEFAULT NULL COMMENT '',
	`product_id` int(10) NULL DEFAULT NULL COMMENT '',
	`method_name` varchar(100) NULL DEFAULT NULL COMMENT '',
	`scene_num` varchar(100) NULL DEFAULT NULL COMMENT '',
	`layer_channel` varchar(255) NULL DEFAULT NULL COMMENT '',
	`update_time` datetime NULL DEFAULT NULL COMMENT '',
	`create_user_id` int(11) NOT NULL COMMENT '创建用户Id',
	`create_time` int(11) NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
