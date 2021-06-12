-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1:3306
-- Thời gian đã tạo: Th1 06, 2021 lúc 03:20 AM
-- Phiên bản máy phục vụ: 10.4.10-MariaDB
-- Phiên bản PHP: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlyhoadon`
--
CREATE DATABASE IF NOT EXISTS `quanlyhoadon` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `quanlyhoadon`;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
CREATE TABLE IF NOT EXISTS `hoadon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `maHD` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ngayBan` date DEFAULT NULL,
  `maNV` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tenNV` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tenKH` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `diaChi` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `dienThoai` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `maHD` (`maHD`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`id`, `maHD`, `ngayBan`, `maNV`, `tenNV`, `tenKH`, `diaChi`, `dienThoai`) VALUES
(1, 'hd1', '2021-01-06', 'nv1', 'lê anh vũ', 'khách hàng Lê Anh Vũ', 'Thủ Đức HCM', '0333124198'),
(2, 'hd2', '2021-01-06', 'nv1', 'lê anh vũ', 'le anh vu', 'HCM', '0000111123'),
(3, 'hd3', '2021-01-06', 'nv1', 'lê anh vũ', 'Lê Lê', 'Nam Tà', '000000000'),
(4, 'hd5', '2021-01-06', 'nv1', 'lê anh vũ', 'lê anh vũ', 'Phú Yêu', '02221'),
(5, 'hd6', '2021-01-06', 'nv1', 'lê anh vũ', 'lê anh vũ', 'Phú Yêu', '02221'),
(6, 'hd7', '2021-01-06', 'nv1', 'lê anh vũ', 'Nguyên Hà Phi', 'Phú Thọ', '0223');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
CREATE TABLE IF NOT EXISTS `nhanvien` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `maNV` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tenNV` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `maNV` (`maNV`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`id`, `maNV`, `tenNV`, `username`, `password`) VALUES
(1, 'nv1', 'lê anh vũ', 'lav', 'lav');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

DROP TABLE IF EXISTS `sanpham`;
CREATE TABLE IF NOT EXISTS `sanpham` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `maHD` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `maSP` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tenSP` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `soLuong` int(11) NOT NULL,
  `donGia` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`id`, `maHD`, `maSP`, `tenSP`, `soLuong`, `donGia`) VALUES
(1, 'hd1', 'F', 'Quả cam', 2, 10000),
(2, 'hd1', 'V', 'Rau Diếp Cá', 100, 1000),
(3, 'hd1', 'R', 'Gạo nếp', 100, 2000),
(4, 'hd1', 'M', 'Thị Bò', 2, 3),
(5, 'hd2', 'F', 'Quýt', 30, 1090000),
(6, 'hd2', 'F', 'Táo', 12, 112900),
(7, 'hd3', 'R', 'Gạo lức', 12, 20000),
(8, 'hd5', 'F', 'Nước mía', 7, 10000),
(9, 'hd5', 'F', 'Nước cam', 7, 10000),
(10, 'hd5', 'F', '7 úp', 1, 10000),
(11, 'hd6', 'M', 'Thị heo ba chỉ', 4, 25000),
(12, 'hd6', 'M', 'Thị bò', 2, 55000),
(13, 'hd7', 'M', 'Thị heo ba chỉ', 4, 25000),
(14, 'hd7', 'M', 'Thị bò', 2, 55000);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
