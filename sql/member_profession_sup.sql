
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `member_profession_sup`
-- ----------------------------
DROP TABLE IF EXISTS `member_profession_sup`;
CREATE TABLE `member_profession_sup` (
 	`id` int(11) unsigned NOT NULL AUTO_INCREMENT,
	`profession_sup_name` varchar(100) NULL DEFAULT NULL COMMENT '',
	`list_order` int(10) NULL DEFAULT NULL COMMENT '',
	`create_user_id` int(11) NOT NULL COMMENT '创建用户Id',
	`create_time` int(11) NOT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
