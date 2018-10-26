atomikos 两个数据源分布式事务管理示例

两个数据库

test1

    CREATE DATABASE /*!32312 IF NOT EXISTS*/`test1` /*!40100 DEFAULT CHARACTER SET latin1 */;
    
    USE `test1`;
    
    /*Table structure for table `user` */
    
    DROP TABLE IF EXISTS `user`;
    
    CREATE TABLE `user` (
      `uid` int(11) NOT NULL AUTO_INCREMENT,
      `username` varchar(20) DEFAULT NULL,
      `age` int(20) DEFAULT NULL,
      PRIMARY KEY (`uid`)
    ) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;



  
test2



    CREATE DATABASE /*!32312 IF NOT EXISTS*/`test2` /*!40100 DEFAULT CHARACTER SET latin1 */;
    
    USE `test2`;
    
    /*Table structure for table `user` */
    
    DROP TABLE IF EXISTS `user`;
    
    CREATE TABLE `user` (
      `uid` int(11) NOT NULL AUTO_INCREMENT,
      `username` varchar(20) DEFAULT NULL,
      `age` int(20) DEFAULT NULL,
      PRIMARY KEY (`uid`)
    ) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;


