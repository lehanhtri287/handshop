-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.2.14-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for shophandmade
CREATE DATABASE IF NOT EXISTS `shophandmade` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `shophandmade`;

-- Dumping structure for table shophandmade.chitietdonhang
CREATE TABLE IF NOT EXISTS `chitietdonhang` (
  `ID_DONHANG` int(11) DEFAULT NULL,
  `ID_SANPHAM` int(11) DEFAULT NULL,
  `SO_LUONG` int(11) DEFAULT NULL,
  KEY `FK_CTDH` (`ID_DONHANG`),
  KEY `FK_CTDH1` (`ID_SANPHAM`),
  CONSTRAINT `FK_CTDH` FOREIGN KEY (`ID_DONHANG`) REFERENCES `donhang` (`ID_DONHANG`),
  CONSTRAINT `FK_CTDH1` FOREIGN KEY (`ID_SANPHAM`) REFERENCES `sanpham` (`ID_SANPHAM`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table shophandmade.chitietdonhang: ~14 rows (approximately)
/*!40000 ALTER TABLE `chitietdonhang` DISABLE KEYS */;
INSERT INTO `chitietdonhang` (`ID_DONHANG`, `ID_SANPHAM`, `SO_LUONG`) VALUES
	(13, 5, 1),
	(18, 13, 1),
	(18, 12, 1),
	(19, 14, 1),
	(19, 12, 1),
	(20, 1000, 1),
	(20, 13, 1),
	(25, 6, 2),
	(26, 3, 1),
	(27, 3, 1),
	(28, 3, 1),
	(29, 3, 1),
	(30, 3, 1),
	(31, 3, 1);
/*!40000 ALTER TABLE `chitietdonhang` ENABLE KEYS */;

-- Dumping structure for table shophandmade.cmt
CREATE TABLE IF NOT EXISTS `cmt` (
  `ID_CMT` int(11) NOT NULL AUTO_INCREMENT,
  `ID_SANPHAM` int(11) NOT NULL,
  `ID_TK` int(11) NOT NULL,
  `THOI_GIAN` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `NOI_DUNG` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`ID_CMT`),
  KEY `FK_CMT` (`ID_SANPHAM`),
  KEY `FK_TKCM` (`ID_TK`),
  CONSTRAINT `FK_CMT` FOREIGN KEY (`ID_SANPHAM`) REFERENCES `sanpham` (`ID_SANPHAM`),
  CONSTRAINT `FK_TKCM` FOREIGN KEY (`ID_TK`) REFERENCES `taikhoan` (`ID_TK`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table shophandmade.cmt: ~3 rows (approximately)
/*!40000 ALTER TABLE `cmt` DISABLE KEYS */;
INSERT INTO `cmt` (`ID_CMT`, `ID_SANPHAM`, `ID_TK`, `THOI_GIAN`, `NOI_DUNG`) VALUES
	(2, 2, 12, '2017-10-30 19:46:13', 'Sản phẩm chất liệu rất tốt'),
	(3, 2, 12, '2017-10-30 20:07:08', 'hahahaha'),
	(4, 1000, 16, '2018-01-18 16:52:47', '');
/*!40000 ALTER TABLE `cmt` ENABLE KEYS */;

-- Dumping structure for table shophandmade.confirmation
CREATE TABLE IF NOT EXISTS `confirmation` (
  `ID_TK` int(11) NOT NULL,
  `ID_CONFIRM` varchar(100) NOT NULL,
  `STATUS` int(11) NOT NULL,
  `DATE_CREATED` timestamp NOT NULL DEFAULT current_timestamp(),
  `TYPE_CONFIRMED` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_CONFIRM`),
  KEY `FK_TKCF` (`ID_TK`),
  CONSTRAINT `FK_TKCF` FOREIGN KEY (`ID_TK`) REFERENCES `taikhoan` (`ID_TK`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table shophandmade.confirmation: ~1 rows (approximately)
/*!40000 ALTER TABLE `confirmation` DISABLE KEYS */;
INSERT INTO `confirmation` (`ID_TK`, `ID_CONFIRM`, `STATUS`, `DATE_CREATED`, `TYPE_CONFIRMED`) VALUES
	(19, '-998797888', 1, '2018-01-19 11:04:59', 2);
/*!40000 ALTER TABLE `confirmation` ENABLE KEYS */;

-- Dumping structure for procedure shophandmade.delete_loaihang
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_loaihang`(
	IN `idLoaihang` INT
)
BEGIN
	update loaihang set is_delete = 1 where id_loaihang = idloaihang;
END//
DELIMITER ;

-- Dumping structure for procedure shophandmade.delete_sanpham
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_sanpham`(
	IN `idSanpham` INT

)
BEGIN
	update sanpham set tinh_trang = 0 where id_sanpham = idSanpham;
END//
DELIMITER ;

-- Dumping structure for table shophandmade.donhang
CREATE TABLE IF NOT EXISTS `donhang` (
  `ID_DONHANG` int(11) NOT NULL AUTO_INCREMENT,
  `NGAY_DH` timestamp NOT NULL DEFAULT current_timestamp(),
  `TONG_TIEN` int(11) DEFAULT NULL,
  `TEN_KHACHHANG` varchar(200) CHARACTER SET utf8 NOT NULL,
  `SDT` varchar(20) DEFAULT NULL,
  `DIACHI` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `STATUS` int(1) DEFAULT NULL COMMENT '0: dang xac nhan, 1: xac nhan, 2: da giao hang, 3: cancel',
  PRIMARY KEY (`ID_DONHANG`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;

-- Dumping data for table shophandmade.donhang: ~28 rows (approximately)
/*!40000 ALTER TABLE `donhang` DISABLE KEYS */;
INSERT INTO `donhang` (`ID_DONHANG`, `NGAY_DH`, `TONG_TIEN`, `TEN_KHACHHANG`, `SDT`, `DIACHI`, `EMAIL`, `STATUS`) VALUES
	(1, '2017-12-06 13:12:57', 200000, 'Tri', '0123123123', 'ĐH Nong Lam', 'trile1234@gmail.com', 3),
	(2, '2017-12-13 13:48:53', 50000, 'Le tri', '0163875778', 'ĐH Nong Lam', 'asdasd@fdas.com', 3),
	(3, '2017-12-13 13:56:08', 130000, 'Tri', '0123123123', 'ĐH Nong Lam', 'trile1234@gmail.com', 3),
	(4, '2017-12-13 13:58:27', 160000, 'Tri', '0123123123', 'ĐH Nong Lam', 'trile1234@gmail.com', 3),
	(5, '2017-12-20 12:50:58', 200000, 'Tri', '0123123123', 'ĐH Nong Lam', 'trile1234@gmail.com', 3),
	(6, '2017-12-20 13:01:55', 230000, 'Tri', '0123123123', 'ĐH Nong Lam', 'trile1234@gmail.com', 3),
	(7, '2017-12-20 13:19:25', 300000, 'Tri', '0123123123', 'ĐH Nong Lam', 'trile1234@gmail.com', 3),
	(8, '2017-12-20 13:21:17', 600000, 'Tri', '0123123123', 'ĐH Nong Lam', 'trile1234@gmail.com', 3),
	(9, '2017-12-28 21:58:58', 190000, 'Tri', '0123123123', 'ĐH Nong Lam', 'trile1234@gmail.com', 1),
	(10, '2017-12-28 22:21:40', 200000, 'Tri', '0123123123', 'ĐH Nong Lam', 'trile1234@gmail.com', 1),
	(11, '2017-12-28 22:24:48', 150000, 'Tri', '0123123123', 'ĐH Nong Lam', 'trile1234@gmail.com', 1),
	(12, '2017-12-28 23:12:39', 200000, 'Tri', '0123123123', 'ĐH Nong Lam', 'trile1234@gmail.com', 2),
	(13, '2017-12-28 23:55:36', 120000, 'Tri', '0123123123', 'ĐH Nong Lam', 'trile1234@gmail.com', 2),
	(14, '2018-01-15 15:10:16', 50000, 'Tri', '0123123123', 'ĐH Nong Lam', 'trile1234@gmail.com', 0),
	(15, '2018-01-15 15:16:38', 250000, 'Trí Lê', '01222977046', 'Linh Trung, Thủ Đức, TP HCM', '14130355@st.hcmuaf.edu.vn', 2),
	(16, '2018-01-15 15:18:46', 150000, 'Trí Lê', '01222977046', 'Linh Trung, Thủ Đức, TP HCM', '14130355@st.hcmuaf.edu.vn', 0),
	(17, '2018-01-15 15:37:14', 260000, 'Trí Lê', '01222977046', 'Linh Trung, Thủ Đức, TP HCM', '14130355@st.hcmuaf.edu.vn', 0),
	(18, '2018-01-15 16:54:19', 130000, 'Trí Lê', '01222977046', 'Linh Trung, Thủ Đức, TP HCM', '14130355@st.hcmuaf.edu.vn', 0),
	(19, '2018-01-18 21:54:59', 230000, 'Trí Lê', '01222977046', 'Linh Trung, Thủ Đức, TP HCM', '14130355@st.hcmuaf.edu.vn', 0),
	(20, '2018-01-19 11:07:16', 200000, 'Trí Lê', '01222977046', 'Thủ Đức, TP HCM, Việt Nam', 'lehanhtri287@gmail.com', 0),
	(24, '2018-06-03 10:29:08', 245000, 'Hello Kitty', '2131231231', 'TPHCM', 'lehanhtri287@gmail.com', 0),
	(25, '2018-06-03 11:03:06', 245000, 'Hello Kitty', '2131231231', 'TPHCM', 'lehanhtri287@gmail.com', 0),
	(26, '2018-06-06 14:55:27', 250000, 'Tri', '12312312311', 'TPHMC', 'lele@gmail.com', 3),
	(27, '2018-06-07 10:56:13', 250000, 'Trilr', '12312312311', 'TPHCM', 'lala@gmail.com', NULL),
	(28, '2018-06-07 10:57:33', 250000, 'Trilr', '12312312311', 'TPHCM', 'lala@gmail.com', NULL),
	(29, '2018-06-07 10:58:38', 250000, 'Trilr', '12312312311', 'TPHCM', 'lala@gmail.com', NULL),
	(30, '2018-06-07 10:58:58', 250000, 'Trilr', '12312312311', 'TPHCM', 'lala@gmail.com', NULL),
	(31, '2018-06-07 10:59:11', 250000, 'Trilr', '12312312311', 'TPHCM', 'lala@gmail.com', NULL);
/*!40000 ALTER TABLE `donhang` ENABLE KEYS */;

-- Dumping structure for table shophandmade.loaihang
CREATE TABLE IF NOT EXISTS `loaihang` (
  `ID_LOAIHANG` int(11) NOT NULL AUTO_INCREMENT,
  `TEN_LOAIHANG` varchar(100) CHARACTER SET utf8 NOT NULL,
  `IS_DELETE` int(1) DEFAULT NULL COMMENT '0 is not deleted yet, 1 is deleted',
  PRIMARY KEY (`ID_LOAIHANG`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

-- Dumping data for table shophandmade.loaihang: ~6 rows (approximately)
/*!40000 ALTER TABLE `loaihang` DISABLE KEYS */;
INSERT INTO `loaihang` (`ID_LOAIHANG`, `TEN_LOAIHANG`, `IS_DELETE`) VALUES
	(1, 'Đồ Handmade', 0),
	(2, 'Thời trang', 0),
	(3, 'Phụ kiện', 0),
	(11, 'Sách vải', 0),
	(12, 'Mặt hàng khác', 0),
	(14, 'Áo thun trẻ em', 0);
/*!40000 ALTER TABLE `loaihang` ENABLE KEYS */;

-- Dumping structure for table shophandmade.sanpham
CREATE TABLE IF NOT EXISTS `sanpham` (
  `ID_SANPHAM` int(15) NOT NULL AUTO_INCREMENT,
  `TEN_SANPHAM` varchar(200) CHARACTER SET utf8 NOT NULL,
  `GIA` int(11) NOT NULL,
  `MO_TA` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `TINH_TRANG` int(11) NOT NULL COMMENT '0: not deleted yet, 1: deleted',
  `ID_LOAIHANG` int(11) DEFAULT NULL,
  `GIAM_GIA` int(11) DEFAULT NULL,
  `IMAGES` varchar(500) DEFAULT NULL,
  `SO_LUONG` int(11) DEFAULT 1,
  PRIMARY KEY (`ID_SANPHAM`),
  KEY `FK_SP` (`ID_LOAIHANG`),
  CONSTRAINT `FK_SP` FOREIGN KEY (`ID_LOAIHANG`) REFERENCES `loaihang` (`ID_LOAIHANG`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=latin1;

-- Dumping data for table shophandmade.sanpham: ~12 rows (approximately)
/*!40000 ALTER TABLE `sanpham` DISABLE KEYS */;
INSERT INTO `sanpham` (`ID_SANPHAM`, `TEN_SANPHAM`, `GIA`, `MO_TA`, `TINH_TRANG`, `ID_LOAIHANG`, `GIAM_GIA`, `IMAGES`, `SO_LUONG`) VALUES
	(2, 'Gối nhỏ có hình', 150000, 'Hàng mới', 0, 1, 0, 'images/pic_1.jpg', 10),
	(3, 'Gối lớn hình cá heo', 250000, 'Hàng mới', 0, 1, 0, 'images/pic_2.jpg', 10),
	(4, 'Thú bông vịt con', 200000, 'Hàng mới', 0, 1, 0, 'images/pic_3.jpg', 10),
	(5, 'Gối hình cá heo', 100000, 'Hàng mới', 0, 1, 0, 'images/pic_5.jpg', 10),
	(6, 'Túi dây rút hình cà rốt', 40000, 'Hàng mới', 0, 1, 0, 'images/pic_6.jpg', 10),
	(7, 'Gối hình trái tim', 80000, 'Áo gối', 0, 3, 0, 'images/pic_7.jpg', 10),
	(12, 'Gối kê cổ', 80000, 'Đồ handmade', 0, 3, 0, 'images/pic_8.jpg', 10),
	(13, 'Gối kê cổ angry bird', 50000, 'Hàng xách tay từ Mỹ', 0, 1, 0, 'images/pic_8.jpg', 10),
	(14, 'Áo sơ mi trắng', 150000, 'Áo sơ mi trắng size S, M, L, XL ', 0, 2, 10, 'images/pr2.jpg', 10),
	(1000, 'Áo sơ mi xanh', 150000, 'THÔNG TIN SẢN PHẨM\r\nÁo sơ mi nam – Chất liệu poplin, bề mặt vải ít nhăn, kết cấu chặt chẽ, xang bông mềm mại, tạo cảm giác mịn màng, thấm mồ hôi, thoáng mát.\r\n- Sản phẩm có màu sắc đen, trắng tao nhã kết hợp với hình rồng thêu màu xanh cá tính, tạo nên vẻ đẹp thanh lịch và sang trọng cho người mặc.\r\n- Kiểu dáng Slimfit – form ôm.\r\n- Cổ áo kiểu cổ bẻ Đức khỏe khoắn.\r\n- Chỉ thêu sản xuất tại Việt Nam, bền màu không độc hại.\r\n- Thông số mẫu: Cao 1.72m - Nặng 73kg - Size áo L', 0, 2, NULL, 'images/pi3.png', 10),
	(1002, 'Voi con', 99000, 'Voi bông màu xanh \r\nĐồ chơi cho trẻ em trên 3 tuổi', 0, 1, NULL, '/images/pic_4.jpg', 10),
	(1003, 'Lục lạc', 230000, 'Lục lạc vàng đáng yêu quá đi', 0, 12, 0, 'images/1.jpg', 5);
/*!40000 ALTER TABLE `sanpham` ENABLE KEYS */;

-- Dumping structure for table shophandmade.taikhoan
CREATE TABLE IF NOT EXISTS `taikhoan` (
  `ID_TK` int(11) NOT NULL AUTO_INCREMENT,
  `TEN_DANGNHAP` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `MAT_KHAU` varchar(200) DEFAULT NULL,
  `CHUCVU` varchar(50) DEFAULT 'KH',
  `TEN_KH` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `DIACHI` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `SDT` varchar(15) DEFAULT NULL,
  `IS_CONFIRM` int(1) DEFAULT NULL,
  PRIMARY KEY (`ID_TK`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

-- Dumping data for table shophandmade.taikhoan: ~9 rows (approximately)
/*!40000 ALTER TABLE `taikhoan` DISABLE KEYS */;
INSERT INTO `taikhoan` (`ID_TK`, `TEN_DANGNHAP`, `MAT_KHAU`, `CHUCVU`, `TEN_KH`, `DIACHI`, `SDT`, `IS_CONFIRM`) VALUES
	(2, 'trile@gmail.com', 'jgXSEb8rRL9MfZ62Do2StA==', 'ADM', 'Trí Lê', 'Quảng Ngãi', '0123456789', 1),
	(3, 'kaitoukid287@gmail.com', 'Mkq9I7T+bvAkZLbAskE+5g==', 'KH', 'Lê Huỳnh Anh Trí', 'Mộ Đức, Quảng Ngãi', '0123456789', 1),
	(11, 'tranlevihandshop@gmail.com', '5aOX+VJPKGuPh8haPkIxUUH7ZB7U2MyLheBHdsK7bBo=', 'KH', 'Tran Le Vi', 'Quảng Ngãi', '01231231312', 1),
	(12, 'trile287@gmail.com', 'q/BzX55ILGJ1KbVzCzVR2PRRYGjkU77b/7tz3twFxRc=', 'KH', 'Lê Trí', 'KTX Khu B DDHQG, TPHCM', '0123456789', 1),
	(14, 'abcde@gmail.com', 'w3yVQyUYr1eLxfSexYK7kg==', 'KH', 'Trí Lê', 'TPHCM', '01231313123', 1),
	(15, 'trile111@gmail.com', 'm4ogkrvo5P4ES9wB3YgB1g==', 'KH', 'Tri', 'TPHCM', '01234213123', 1),
	(16, 'trile1234@gmail.com', 'tXFqyKae5bg6DwE0k1nDTA==', 'KH', 'Tri', 'ĐH Nong Lam', '0123123123', 1),
	(19, 'lehanhtri287@gmail.com', '4rETeruPmfPLHDMGWM7U/A==', 'KH', 'Trí Lê', 'Thủ Đức, TP HCM, Việt Nam', '01222977046', 1),
	(20, 'lehanhtri27@gmail.com', '$2a$10$xn.C.7/YI/olt4sW2KpNcOYOyk.XBoqS59/5r4nQDAgRCDVPvSG7O', 'KH', 'Tri Le', 'TPHCM', '12312312311', 0);
/*!40000 ALTER TABLE `taikhoan` ENABLE KEYS */;

-- Dumping structure for table shophandmade.tintuc
CREATE TABLE IF NOT EXISTS `tintuc` (
  `ID_TT` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `CONTEXT` text CHARACTER SET utf8 DEFAULT NULL,
  `ID_TK` int(11) NOT NULL,
  `NGUOI_DANG` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `NGAY_DANG` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`ID_TT`),
  KEY `FK_TT` (`ID_TK`),
  CONSTRAINT `FK_TT` FOREIGN KEY (`ID_TK`) REFERENCES `taikhoan` (`ID_TK`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table shophandmade.tintuc: ~1 rows (approximately)
/*!40000 ALTER TABLE `tintuc` DISABLE KEYS */;
INSERT INTO `tintuc` (`ID_TT`, `TITLE`, `CONTEXT`, `ID_TK`, `NGUOI_DANG`, `NGAY_DANG`) VALUES
	(1, 'TEST', 'TESTING MOTHERFUCKER', 3, 'TRÍ LÊ', '2017-08-14 10:01:37');
/*!40000 ALTER TABLE `tintuc` ENABLE KEYS */;

-- Dumping structure for view shophandmade.v_comment
-- Creating temporary table to overcome VIEW dependency errors
CREATE TABLE `v_comment` (
	`ID_CMT` INT(11) NOT NULL,
	`ID_SANPHAM` INT(11) NOT NULL,
	`TEN_KH` VARCHAR(200) NULL COLLATE 'utf8_general_ci',
	`THOI_GIAN` TIMESTAMP NOT NULL,
	`NOI_DUNG` VARCHAR(500) NULL COLLATE 'utf8_general_ci',
	`TEN_DANGNHAP` VARCHAR(200) NULL COLLATE 'utf8_general_ci'
) ENGINE=MyISAM;

-- Dumping structure for view shophandmade.v_dhofkh
-- Creating temporary table to overcome VIEW dependency errors
CREATE TABLE `v_dhofkh` (
	`ID_DONHANG` INT(11) NOT NULL,
	`NGAY_DH` TIMESTAMP NOT NULL,
	`TONG_TIEN` INT(11) NULL,
	`STATUS` INT(1) NULL COMMENT '0: dang xac nhan, 1: xac nhan, 2: da giao hang, 3: cancel',
	`ID_TK` INT(11) NOT NULL
) ENGINE=MyISAM;

-- Dumping structure for view shophandmade.v_donhang
-- Creating temporary table to overcome VIEW dependency errors
CREATE TABLE `v_donhang` (
	`ID_DONHANG` INT(11) NULL,
	`ID_SANPHAM` INT(11) NULL,
	`SO_LUONG` INT(11) NULL,
	`TEN_SANPHAM` VARCHAR(200) NOT NULL COLLATE 'utf8_general_ci'
) ENGINE=MyISAM;

-- Dumping structure for view shophandmade.v_likemost
-- Creating temporary table to overcome VIEW dependency errors
CREATE TABLE `v_likemost` (
	`ID_SANPHAM` INT(15) NOT NULL,
	`TEN_SANPHAM` VARCHAR(200) NOT NULL COLLATE 'utf8_general_ci',
	`GIA` INT(11) NOT NULL,
	`MO_TA` VARCHAR(500) NULL COLLATE 'utf8_general_ci',
	`TINH_TRANG` INT(11) NOT NULL COMMENT '0: not deleted yet, 1: deleted',
	`ID_LOAIHANG` INT(11) NULL,
	`GIAM_GIA` INT(11) NULL,
	`IMAGES` VARCHAR(500) NULL COLLATE 'latin1_swedish_ci',
	`SO_LUONG` INT(11) NULL
) ENGINE=MyISAM;

-- Dumping structure for view shophandmade.v_sanpham
-- Creating temporary table to overcome VIEW dependency errors
CREATE TABLE `v_sanpham` (
	`ID_SANPHAM` INT(15) NOT NULL,
	`TEN_SANPHAM` VARCHAR(200) NOT NULL COLLATE 'utf8_general_ci',
	`GIA` INT(11) NOT NULL,
	`MO_TA` VARCHAR(500) NULL COLLATE 'utf8_general_ci',
	`TINH_TRANG` INT(11) NOT NULL COMMENT '0: not deleted yet, 1: deleted',
	`ID_LOAIHANG` INT(11) NULL,
	`GIAM_GIA` INT(11) NULL,
	`IMAGES` VARCHAR(500) NULL COLLATE 'latin1_swedish_ci',
	`TEN_LOAIHANG` VARCHAR(100) NOT NULL COLLATE 'utf8_general_ci'
) ENGINE=MyISAM;

-- Dumping structure for view shophandmade.v_comment
-- Removing temporary table and create final VIEW structure
DROP TABLE IF EXISTS `v_comment`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_comment` AS select `cmt`.`ID_CMT` AS `ID_CMT`,`cmt`.`ID_SANPHAM` AS `ID_SANPHAM`,`tk`.`TEN_KH` AS `TEN_KH`,`cmt`.`THOI_GIAN` AS `THOI_GIAN`,`cmt`.`NOI_DUNG` AS `NOI_DUNG`,`tk`.`TEN_DANGNHAP` AS `TEN_DANGNHAP` from (`cmt` join `taikhoan` `tk` on((`cmt`.`ID_TK` = `tk`.`ID_TK`))) ;

-- Dumping structure for view shophandmade.v_dhofkh
-- Removing temporary table and create final VIEW structure
DROP TABLE IF EXISTS `v_dhofkh`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_dhofkh` AS select `dh`.`ID_DONHANG` AS `ID_DONHANG`,`dh`.`NGAY_DH` AS `NGAY_DH`,`dh`.`TONG_TIEN` AS `TONG_TIEN`,`dh`.`STATUS` AS `STATUS`,`tk`.`ID_TK` AS `ID_TK` from (`donhang` `dh` join `taikhoan` `tk` on((convert(`dh`.`EMAIL` using utf8) = `tk`.`TEN_DANGNHAP`))) ;

-- Dumping structure for view shophandmade.v_donhang
-- Removing temporary table and create final VIEW structure
DROP TABLE IF EXISTS `v_donhang`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_donhang` AS select `ct`.`ID_DONHANG` AS `ID_DONHANG`,`ct`.`ID_SANPHAM` AS `ID_SANPHAM`,`ct`.`SO_LUONG` AS `SO_LUONG`,`sp`.`TEN_SANPHAM` AS `TEN_SANPHAM` from (`chitietdonhang` `ct` join `sanpham` `sp` on((`ct`.`ID_SANPHAM` = `sp`.`ID_SANPHAM`))) ;

-- Dumping structure for view shophandmade.v_likemost
-- Removing temporary table and create final VIEW structure
DROP TABLE IF EXISTS `v_likemost`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_likemost` AS select `sp`.`ID_SANPHAM` AS `ID_SANPHAM`,`sp`.`TEN_SANPHAM` AS `TEN_SANPHAM`,`sp`.`GIA` AS `GIA`,`sp`.`MO_TA` AS `MO_TA`,`sp`.`TINH_TRANG` AS `TINH_TRANG`,`sp`.`ID_LOAIHANG` AS `ID_LOAIHANG`,`sp`.`GIAM_GIA` AS `GIAM_GIA`,`sp`.`IMAGES` AS `IMAGES`,`sp`.`SO_LUONG` AS `SO_LUONG` from (`sanpham` `sp` join `chitietdonhang` `ctdh` on((`sp`.`ID_SANPHAM` = `ctdh`.`ID_SANPHAM`))) where (`sp`.`TINH_TRANG` = 1) group by `sp`.`ID_SANPHAM` limit 5 ;

-- Dumping structure for view shophandmade.v_sanpham
-- Removing temporary table and create final VIEW structure
DROP TABLE IF EXISTS `v_sanpham`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_sanpham` AS select `sp`.`ID_SANPHAM` AS `ID_SANPHAM`,`sp`.`TEN_SANPHAM` AS `TEN_SANPHAM`,`sp`.`GIA` AS `GIA`,`sp`.`MO_TA` AS `MO_TA`,`sp`.`TINH_TRANG` AS `TINH_TRANG`,`sp`.`ID_LOAIHANG` AS `ID_LOAIHANG`,`sp`.`GIAM_GIA` AS `GIAM_GIA`,`sp`.`IMAGES` AS `IMAGES`,`lh`.`TEN_LOAIHANG` AS `TEN_LOAIHANG` from (`sanpham` `sp` join `loaihang` `lh` on((`sp`.`ID_LOAIHANG` = `lh`.`ID_LOAIHANG`))) where (`sp`.`TINH_TRANG` = 1) ;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
