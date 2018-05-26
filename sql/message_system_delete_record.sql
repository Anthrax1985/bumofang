
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `message_system_delete_record`
-- ----------------------------
DROP TABLE IF EXISTS `message_system_delete_record`;
CREATE TABLE `message_system_delete_record` (
 	`id` int(11) unsigned NOT NULL AUTO_INCREMENT,
	`member_id` int(10) NULL DEFAULT NULL COMMENT '',
	`message_id` int(10) NULL DEFAULT NULL COMMENT '',
	`del_flag` int(2) NULL DEFAULT NULL COMMENT '',
	`create_user_id` int(11) NOT NULL COMMENT '创建用户Id',
	`create_time` int(11) NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
