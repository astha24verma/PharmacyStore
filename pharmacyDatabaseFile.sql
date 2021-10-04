/*
SQLyog Ultimate v8.82 
MySQL - 5.1.45-community : Database - pharmacy
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`pharmacy` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `pharmacy`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `user` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `admin` */

insert  into `admin`(`user`,`password`) values ('astha','12345');

/*Table structure for table `bill_detail` */

DROP TABLE IF EXISTS `bill_detail`;

CREATE TABLE `bill_detail` (
  `invc_no` int(4) NOT NULL,
  `date_time` varchar(40) DEFAULT NULL,
  `cust_name` varchar(30) DEFAULT NULL,
  `cust_cont` decimal(10,0) DEFAULT NULL,
  `bill_amt` decimal(7,0) DEFAULT NULL,
  PRIMARY KEY (`invc_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `bill_detail` */

insert  into `bill_detail`(`invc_no`,`date_time`,`cust_name`,`cust_cont`,`bill_amt`) values (1,NULL,NULL,NULL,NULL),(2,NULL,NULL,NULL,NULL),(3,'Tue May 25 21:41:42 IST 2021','Astha','4566546612','224200'),(4,'Tue May 25 22:19:46 IST 2021','Rivanah','9120734521','63200'),(5,'Tue May 25 22:42:15 IST 2021','Vinay','9214565643','275800'),(6,'Tue May 25 23:02:07 IST 2021','Roshni','3454542114','96600'),(7,'Tue Jun 01 11:08:03 IST 2021','Anurag Verma','9045456721','32880'),(8,'Tue Jun 01 11:19:14 IST 2021','Mia','3425543216','1120');

/*Table structure for table `emp_mstr` */

DROP TABLE IF EXISTS `emp_mstr`;

CREATE TABLE `emp_mstr` (
  `ecode` int(4) NOT NULL,
  `ename` varchar(30) DEFAULT NULL,
  `epost` varchar(20) DEFAULT NULL,
  `edob` date DEFAULT NULL,
  `equa` varchar(30) DEFAULT NULL,
  `egender` varchar(20) DEFAULT NULL,
  `eaddr` varchar(100) DEFAULT NULL,
  `econtact` decimal(10,0) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `password` varchar(15) DEFAULT NULL,
  `ac_status` varchar(20) DEFAULT 'Active',
  PRIMARY KEY (`ecode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `emp_mstr` */

insert  into `emp_mstr`(`ecode`,`ename`,`epost`,`edob`,`equa`,`egender`,`eaddr`,`econtact`,`email`,`password`,`ac_status`) values (1001,'Dimpy','Employee','2000-12-23','MCA','Female','Gore gao,Delhi ','4545456666','dimpy123@gmail.com','232000','Active'),(1002,'Ria','spdm','2000-03-17','BCA','Female','cv road, Delhi','3434787890','riv404@gmail.com','riv404','Active'),(1003,'Vishal','Employee','2000-05-18','BPharma','Male','abc','5656568434','vishal182000@gmail.com','v562000','Inactive');

/*Table structure for table `invoice` */

DROP TABLE IF EXISTS `invoice`;

CREATE TABLE `invoice` (
  `invoice_id` int(4) NOT NULL,
  `med_name` varchar(20) DEFAULT NULL,
  `med_price` decimal(7,0) DEFAULT NULL,
  `med_qty` int(4) DEFAULT NULL,
  `invc_date_time` varchar(40) DEFAULT NULL,
  `cust_name` varchar(30) DEFAULT NULL,
  `cust_contact` decimal(10,0) DEFAULT NULL,
  `emp_id` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `invoice` */

insert  into `invoice`(`invoice_id`,`med_name`,`med_price`,`med_qty`,`invc_date_time`,`cust_name`,`cust_contact`,`emp_id`) values (3,'Detronjel','590',380,'Tue May 25 21:39:20 IST 2021','Astha','4566546612','Empxyz'),(4,'CPO','350',112,'Tue May 25 22:19:26 IST 2021','Rivanah','9120734521','Empxyz'),(4,'XYZ','60',400,'Tue May 25 22:19:40 IST 2021','Rivanah','9120734521','Empxyz'),(5,'XYZ','60',375,'Tue May 25 22:41:45 IST 2021','Vinay','9214565643','Empxyz'),(5,'CPO','350',100,'Tue May 25 22:41:57 IST 2021','Vinay','9214565643','Empxyz'),(5,'Detronjel','590',370,'Tue May 25 22:42:12 IST 2021','Vinay','9214565643','Empxyz'),(6,'Covisheild','250',300,'Tue May 25 23:01:52 IST 2021','Roshni','3454542114','Empxyz'),(6,'XYZ','60',360,'Tue May 25 23:02:04 IST 2021','Roshni','3454542114','Empxyz'),(7,'Azithral','20',600,'Tue Jun 01 11:06:28 IST 2021','Anurag Verma','9045456721','Empxyz'),(7,'XYZ','60',348,'Tue Jun 01 11:07:58 IST 2021','Anurag Verma','9045456721','Empxyz'),(8,'Covisheild','250',4,'Tue Jun 01 11:18:50 IST 2021','Mia','3425543216','Empxyz'),(8,'Azithral','20',6,'Tue Jun 01 11:19:10 IST 2021','Mia','3425543216','Empxyz');

/*Table structure for table `product_mstr` */

DROP TABLE IF EXISTS `product_mstr`;

CREATE TABLE `product_mstr` (
  `med_name` varchar(30) NOT NULL,
  `med_company` varchar(30) DEFAULT NULL,
  `med_formula` varchar(30) DEFAULT NULL,
  `med_sup` varchar(30) DEFAULT NULL,
  `comp_contact` decimal(10,0) DEFAULT NULL,
  `sup_cont` decimal(10,0) DEFAULT NULL,
  `med_purpose` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`med_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `product_mstr` */

insert  into `product_mstr`(`med_name`,`med_company`,`med_formula`,`med_sup`,`comp_contact`,`sup_cont`,`med_purpose`) values ('Azithral','Alembic Ltd','Unknown','Vishnu Pratap','9922336611','9120567854','Antibiotic'),('Covisheild','OPL','Xyz','2000','1234567891','4321543211','Vaccine'),('CPO','Ciplex','Gtx455','Rishabh','9934345643','8877665544','Fever'),('Detronjel','CBCO','PhtNano','Jason','7899556677','2390908776','Fever'),('XYZ','Cipla','DtX','800','4545665511','9090887766','Headache');

/*Table structure for table `stock_detail` */

DROP TABLE IF EXISTS `stock_detail`;

CREATE TABLE `stock_detail` (
  `med_name` varchar(30) NOT NULL,
  `med_cp` decimal(5,0) DEFAULT NULL,
  `med_sp` decimal(5,0) DEFAULT NULL,
  `med_mfd` varchar(15) DEFAULT NULL,
  `med_exd` varchar(15) DEFAULT NULL,
  `reorder_level` int(4) DEFAULT NULL,
  `med_qty` int(4) DEFAULT NULL,
  PRIMARY KEY (`med_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `stock_detail` */

insert  into `stock_detail`(`med_name`,`med_cp`,`med_sp`,`med_mfd`,`med_exd`,`reorder_level`,`med_qty`) values ('Azithral','15','20','20-03-2021','20-04-2023',20,592),('Covisheild','200','250','23 April 2021','25 Dec 2025',20,294),('CPO','300','350','2020-12-12','2025-12-01',20,8),('Detronjel','550','590','2018-05-08','2026-02-06',20,20),('XYZ','45','60','March 2021','May 2022',20,347);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
