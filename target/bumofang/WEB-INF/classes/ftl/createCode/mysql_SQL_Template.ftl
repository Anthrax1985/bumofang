
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `${tabletop}${objectNameLower}`
-- ----------------------------
DROP TABLE IF EXISTS `${tabletop}${objectNameLower}`;
CREATE TABLE `${tabletop}${objectNameLower}` (
 		`id` int(20) unsigned NOT NULL AUTO_INCREMENT,
	<#list fieldList as var>
		<#if var[1] == 'Integer'>
		`${var[0]}` int(11) NOT NULL COMMENT '${var[2]}',
		<#elseif var[1] == 'Date'>
		`${var[0]}` timestamp NULL DEFAULT NULL COMMENT '${var[2]}',
		<#else>
		`${var[0]}` varchar(255) DEFAULT NULL COMMENT '${var[2]}',
		</#if>
	</#list>
  		PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
