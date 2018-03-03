/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.7.18-log : Database - mall
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Data for the table `cart_product` */

insert  into `cart_product`(`id`,`product_id`,`user_id`,`product_num`) values (1,1,1,2),(2,1,1,1),(3,1,1,3),(4,1,1,4);

/*Data for the table `category` */

insert  into `category`(`id`,`name`,`parent_id`) values (1,'女装',NULL),(2,'男装',NULL),(3,'家电',NULL),(4,'家居',NULL),(5,'饰品',NULL),(6,'箱包',NULL),(7,'连衣裙',1),(8,'上装',1),(9,'下装',1),(10,'外套',1),(11,'上装',1),(12,'下装',1),(13,'外套',1),(14,'衬衫连衣裙',7),(15,'雪纺连衣裙',7),(16,'针织连衣裙',7),(17,'衬衫',8),(18,'卫衣',8),(19,'衬衫',8);

/*Data for the table `comment` */

insert  into `comment`(`id`,`product_id`,`order_id`,`user_id`,`content`,`create_at`) values (1,1,1,1,'真不错，已经迫不及待的穿上了，朋友们都说好，挺有气质的，就是上衣稍微有点肥，不过肉肉被藏起来了！有看上的赶紧下单吧！','2018-03-15 21:12:56'),(2,1,2,1,'真不错，已经迫不及待的穿上了，朋友们都说好，挺有气质的，就是上衣稍微有点肥，不过肉肉被藏起来了！有看上的赶紧下单吧！','2018-03-04 19:12:56'),(3,1,3,1,'真不错，已经迫不及待的穿上了，朋友们都说好，挺有气质的，就是上衣稍微有点肥，不过肉肉被藏起来了！有看上的赶紧下单吧！','2018-03-05 15:12:56'),(4,1,4,1,'真不错，已经迫不及待的穿上了，朋友们都说好，挺有气质的，就是上衣稍微有点肥，不过肉肉被藏起来了！有看上的赶紧下单吧！','2018-03-15 15:12:56'),(5,1,5,1,'真不错，已经迫不及待的穿上了，朋友们都说好，挺有气质的，就是上衣稍微有点肥，不过肉肉被藏起来了！有看上的赶紧下单吧！','2018-03-12 18:12:56'),(6,1,6,1,'真不错，已经迫不及待的穿上了，朋友们都说好，挺有气质的，就是上衣稍微有点肥，不过肉肉被藏起来了！有看上的赶紧下单吧！','2018-03-12 18:12:56'),(7,1,7,1,'真不错，已经迫不及待的穿上了，朋友们都说好，挺有气质的，就是上衣稍微有点肥，不过肉肉被藏起来了！有看上的赶紧下单吧！','2018-03-12 18:12:56'),(8,1,8,1,'真不错，已经迫不及待的穿上了，朋友们都说好，挺有气质的，就是上衣稍微有点肥，不过肉肉被藏起来了！有看上的赶紧下单吧！','2018-03-12 18:12:56'),(9,1,9,1,'真不错，已经迫不及待的穿上了，朋友们都说好，挺有气质的，就是上衣稍微有点肥，不过肉肉被藏起来了！有看上的赶紧下单吧！','2018-03-12 18:12:56'),(10,1,10,1,'真不错，已经迫不及待的穿上了，朋友们都说好，挺有气质的，就是上衣稍微有点肥，不过肉肉被藏起来了！有看上的赶紧下单吧！','2018-03-12 18:12:56'),(11,1,11,1,'商品：宝贝料子和手感都不错，是值这价，卖家很贴心的送了内衣带，5分好评是必须的。喜欢的可以 下手了','2018-03-12 18:12:56'),(12,1,12,1,'真不错，已经迫不及待的穿上了，朋友们都说好，挺有气质的，就是上衣稍微有点肥，不过肉肉被藏起来了！有看上的赶紧下单吧！','2018-03-12 18:12:56'),(13,1,13,1,'真不错，已经迫不及待的穿上了，朋友们都说好，挺有气质的，就是上衣稍微有点肥，不过肉肉被藏起来了！有看上的赶紧下单吧！','2018-03-12 18:12:56'),(14,1,14,1,'真不错，已经迫不及待的穿上了，朋友们都说好，挺有气质的，就是上衣稍微有点肥，不过肉肉被藏起来了！有看上的赶紧下单吧！','2018-03-12 18:12:56'),(15,1,15,1,'真不错，已经迫不及待的穿上了，朋友们都说好，挺有气质的，就是上衣稍微有点肥，不过肉肉被藏起来了！有看上的赶紧下单吧！','2018-03-12 18:12:56'),(16,1,16,1,'真不错，已经迫不及待的穿上了，朋友们都说好，挺有气质的，就是上衣稍微有点肥，不过肉肉被藏起来了！有看上的赶紧下单吧！','2018-03-12 18:12:56'),(17,1,17,1,'真不错，已经迫不及待的穿上了，朋友们都说好，挺有气质的，就是上衣稍微有点肥，不过肉肉被藏起来了！有看上的赶紧下单吧！','2018-03-12 18:12:56'),(18,1,18,1,'真不错，已经迫不及待的穿上了，朋友们都说好，挺有气质的，就是上衣稍微有点肥，不过肉肉被藏起来了！有看上的赶紧下单吧！','2018-03-12 18:12:56'),(19,1,19,1,'真不错，已经迫不及待的穿上了，朋友们都说好，挺有气质的，就是上衣稍微有点肥，不过肉肉被藏起来了！有看上的赶紧下单吧！','2018-03-12 18:12:56'),(20,1,20,1,'真不错，已经迫不及待的穿上了，朋友们都说好，挺有气质的，就是上衣稍微有点肥，不过肉肉被藏起来了！有看上的赶紧下单吧！','2018-03-12 18:12:56'),(21,1,21,1,'真不错，已经迫不及待的穿上了，朋友们都说好，挺有气质的，就是上衣稍微有点肥，不过肉肉被藏起来了！有看上的赶紧下单吧！','2018-03-12 18:12:56'),(22,1,22,22,'真不错，已经迫不及待的穿上了，朋友们都说好，挺有气质的，就是上衣稍微有点肥，不过肉肉被藏起来了！有看上的赶紧下单吧！','2018-03-12 18:12:56');

/*Data for the table `order` */

/*Data for the table `order_product` */

/*Data for the table `permission` */

/*Data for the table `product` */

insert  into `product`(`id`,`name`,`price`,`discount`,`status`,`category_id`,`root_category_id`,`store_num`,`sale_num`,`image_urls`,`description_image_urls`,`params`,`comment_num`) values (1,'加绒卫衣帽衫加厚宽松','336.00','168.00','ON_SHELF',18,1,1086,688,'http://img02.static.yohobuy.com/product/2014/03/19/13/02b54572ff6c6e9bf3e72f2f412a31a47c.jpg;http://img02.static.yohobuy.com/product/2014/03/19/13/02b54572ff6c6e9bf3e72f2f412a31a47c.jpg;http://img02.static.yohobuy.com/product/2014/03/19/13/02b54572ff6c6e9bf3e72f2f412a31a47c.jpg','http://img02.static.yohobuy.com/product/2014/03/19/13/02b54572ff6c6e9bf3e72f2f412a31a47c.jpg;http://img02.static.yohobuy.com/product/2014/03/19/13/02b54572ff6c6e9bf3e72f2f412a31a47c.jpg','{\r\n  \"款式\":\"套头\",\r\n  \"货号\":\"1524\",\r\n  \"厚薄\":\"加厚\",\r\n  \"材质\":\"涤纶\",\r\n}',1001),(2,'圆领印花卫衣女','188.00','78.00','ON_SHELF',18,1,1999,919,'http://img01.static.yohobuy.com/product/2014/04/25/11/019e0ebf4e91868afc68b6530bb07c3eb0.jpg；http://img01.static.yohobuy.com/product/2014/04/25/11/019e0ebf4e91868afc68b6530bb07c3eb0.jpg；http://img01.static.yohobuy.com/product/2014/04/25/11/019e0ebf4e91868afc68b6530bb07c3eb0.jpg','http://img01.static.yohobuy.com/product/2014/04/25/11/019e0ebf4e91868afc68b6530bb07c3eb0.jpg；http://img01.static.yohobuy.com/product/2014/04/25/11/019e0ebf4e91868afc68b6530bb07c3eb0.jpg；http://img01.static.yohobuy.com/product/2014/04/25/11/019e0ebf4e91868afc68b6530bb07c3eb0.jpg','{\r\n  \"款式\":\"套头\",\r\n  \"货号\":\"1625\",\r\n  \"厚薄\":\"常规\",\r\n  \"材质\":\"棉\",\r\n}',999),(3,'休闲牛仔衬衫','288.00','208.00','ON_SHELF',17,1,46830,5930,'http://www.sc115.com/wenku/uploads/allimg/130313/21012322V-2.jpg;http://www.sc115.com/wenku/uploads/allimg/130313/21012322V-2.jpg;http://www.sc115.com/wenku/uploads/allimg/130313/21012322V-2.jpg','http://www.sc115.com/wenku/uploads/allimg/130313/21012322V-2.jpg;http://www.sc115.com/wenku/uploads/allimg/130313/21012322V-2.jpg','{\r\n  \"款式\":\"单排多扣\",\r\n  \"货号\":\"1785\",\r\n  \"厚薄\":\"常规\",\r\n  \"材质\":\"棉\",\r\n}',4892),(4,'白色雪纺衬衫','179.00','169.00','ON_SHELF',17,1,530,123,'http://vogue.moonbasa.com/files/2011/8/24/32-059379855427.jpg;http://vogue.moonbasa.com/files/2011/8/24/32-059379855427.jpg;http://vogue.moonbasa.com/files/2011/8/24/32-059379855427.jpg','http://vogue.moonbasa.com/files/2011/8/24/32-059379855427.jpg;http://vogue.moonbasa.com/files/2011/8/24/32-059379855427.jpg','{\r\n  \"款式\":\"立领套头\",\r\n  \"货号\":\"1622\",\r\n  \"厚薄\":\"薄款\",\r\n  \"材质\":\"雪纺\",\r\n}',5088),(5,'字母短袖T恤','99.00','69.00','ON_SHELF',19,1,0,0,'http://img.zcool.cn/community/018615577cd4070000012e7e89ab4d.jpg@900w_1l_2o_100sh.jpg;http://img.zcool.cn/community/018615577cd4070000012e7e89ab4d.jpg@900w_1l_2o_100sh.jpg;http://img.zcool.cn/community/018615577cd4070000012e7e89ab4d.jpg@900w_1l_2o_100sh.jpg','http://img.zcool.cn/community/018615577cd4070000012e7e89ab4d.jpg@900w_1l_2o_100sh.jpg;http://img.zcool.cn/community/018615577cd4070000012e7e89ab4d.jpg@900w_1l_2o_100sh.jpg','{\r\n  \"款式\":\"圆领套头\",\r\n  \"货号\":\"522\",\r\n  \"厚薄\":\"薄款\",\r\n  \"材质\":\"棉\",\r\n}',6250);

/*Data for the table `role` */

/*Data for the table `role_permission` */

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`email`,`nickname`,`gender`,`telephone`,`birthday`,`avatar`,`create_at`,`last_login_at`,`last_login_ip`,`status`) values (1,'111111','111111','11@qq.com','111','MALE',NULL,NULL,NULL,'2018-03-01 21:23:37',NULL,NULL,'ACTIVATED');

/*Data for the table `user_role` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
