
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `product_param_application`
-- ----------------------------
DROP TABLE IF EXISTS `product_param_application`;
CREATE TABLE `product_param_application` (
 	`id` int(11) unsigned NOT NULL AUTO_INCREMENT,
	`application_name` varchar(255) NULL DEFAULT NULL COMMENT '',
	`application_order` int(10) NULL DEFAULT NULL COMMENT '',
	`create_user_id` int(11) NOT NULL COMMENT '创建用户Id',
	`create_time` int(11) NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
