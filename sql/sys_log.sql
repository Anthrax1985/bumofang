
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
 	`id` int(11) unsigned NOT NULL AUTO_INCREMENT,
	`user_name` varchar(255) NULL DEFAULT NULL COMMENT '',
	`user_ip` varchar(255) NULL DEFAULT NULL COMMENT '',
	`role_type` varchar(255) NULL DEFAULT NULL COMMENT '',
	`page_name` varchar(255) NULL DEFAULT NULL COMMENT '',
	`operation_content` varchar(255) NULL DEFAULT NULL COMMENT '',
	`create_user_id` int(11) NOT NULL COMMENT '创建用户Id',
	`create_time` int(11) NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
