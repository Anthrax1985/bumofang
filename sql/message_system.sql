
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `message_system`
-- ----------------------------
DROP TABLE IF EXISTS `message_system`;
CREATE TABLE `message_system` (
 	`id` int(11) unsigned NOT NULL AUTO_INCREMENT,
	`release_title` varchar(255) NULL DEFAULT NULL COMMENT '',
	`release_info` text NULL DEFAULT NULL COMMENT '',
	`releaser_id` int(10) NULL DEFAULT NULL COMMENT '',
	`releaser_info` varchar(255) NULL DEFAULT NULL COMMENT '',
	`release_time` datetime NULL DEFAULT NULL COMMENT '',
	`auditor_id` int(10) NULL DEFAULT NULL COMMENT '',
	`auditor_info` varchar(255) NULL DEFAULT NULL COMMENT '',
	`audit_time` datetime NULL DEFAULT NULL COMMENT '',
	`status` int(2) NULL DEFAULT NULL COMMENT '',
	`del_flag` int(2) NULL DEFAULT NULL COMMENT '',
	`create_user_id` int(11) NOT NULL COMMENT '创建用户Id',
	`create_time` int(11) NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
