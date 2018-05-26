
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `member_money_detail`
-- ----------------------------
DROP TABLE IF EXISTS `member_money_detail`;
CREATE TABLE `member_money_detail` (
 	`id` int(11) unsigned NOT NULL AUTO_INCREMENT,
	`member_id` int(10) NULL DEFAULT NULL COMMENT '',
	`serial_number` varchar(100) NULL DEFAULT NULL COMMENT '',
	`amount` decimal(12,2) NULL DEFAULT NULL COMMENT '',
	`type` int(2) NULL DEFAULT NULL COMMENT '',
	`create_user_id` int(11) NOT NULL COMMENT '创建用户Id',
	`create_time` int(11) NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
