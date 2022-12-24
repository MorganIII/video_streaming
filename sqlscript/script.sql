CREATE DATABASE  IF NOT EXISTS `video_streaming`;
USE `video_streaming`;


DROP TABLE IF EXISTS `video_spec`;

 CREATE TABLE `video_spec`  (
			   `id` int(11) NOT NULL AUTO_INCREMENT,
              `video_name` VARCHAR (20) NOT NULL UNIQUE,
               `video`  LONGBLOB, 
               PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1;



