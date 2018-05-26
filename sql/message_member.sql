
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `message_member`
-- ----------------------------
DROP TABLE IF EXISTS `message_member`;
CREATE TABLE `message_member` (
 	`id` int(11) unsigned NOT NULL AUTO_INCREMENT,
	`member_id` int(10) NULL DEFAULT NULL COMMENT '',
	`member_name` varchar(100) NULL DEFAULT NULL COMMENT '',
	`member_avatar` varchar(100) NULL DEFAULT NULL COMMENT '',
	`content_info` varchar(100) NULL DEFAULT NULL COMMENT '',
	`content_time` datetime NULL DEFAULT NULL COMMENT '',
	`reply_info` varchar(255) NULL DEFAULT NULL COMMENT '',
	`reply_time` datetime NULL DEFAULT NULL COMMENT '',
	`replier_id` int(10) NULL DEFAULT NULL COMMENT '',
	`replier_name` varchar(100) NULL DEFAULT NULL COMMENT '',
	`replier_avatar` varchar(100) NULL DEFAULT NULL COMMENT '',
	`status` int(2) NULL DEFAULT NULL COMMENT '',
	`del_flag` int(2) NULL DEFAULT NULL COMMENT '',
	`create_user_id` int(11) NOT NULL COMMENT '创建用户Id',
	`create_time` int(11) NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
