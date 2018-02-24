
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET gb2312 */;

USE `test`;


DROP TABLE IF EXISTS `upload_files`;

CREATE TABLE `upload_files` (
  `id` int(11) NOT NULL auto_increment,
  `file_name` varchar(50) NOT NULL,
  `file_path` varchar(200) default NULL,
  `file_desc` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

