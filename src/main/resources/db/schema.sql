DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
											`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
											`name` varchar(30) DEFAULT NULL COMMENT '姓名',
											`age` int(11) DEFAULT NULL COMMENT '年龄',
											`email` varchar(50) DEFAULT NULL COMMENT '邮箱',
											`version` int(20) NOT NULL,
											`deleted` int(20) NOT NULL,
											`createTime` varchar(36) NOT NULL,
											`updateTime` varchar(36) DEFAULT NULL,
											PRIMARY KEY (`id`)
)