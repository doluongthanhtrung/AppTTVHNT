-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 08, 2018 at 08:27 AM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ttvhnt`
--

-- --------------------------------------------------------

--
-- Table structure for table `caulacbo`
--

CREATE TABLE `caulacbo` (
  `MaCLB` varchar(25) NOT NULL,
  `TenCLB` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `caulacbo`
--

INSERT INTO `caulacbo` (`MaCLB`, `TenCLB`) VALUES
('DX', 'Diễn xuất'),
('EV-PR1', 'Sự kiện - Truyền thông 1'),
('EV-PR2', 'Sự kiện - Truyền thông 2'),
('GT1', 'Guitar 1'),
('GT2', 'Guitar 2'),
('GT3', 'Guitar 3'),
('PI1', 'Piano 1'),
('PI2', 'Piano 2'),
('TN1', 'Thanh nhạc 1'),
('TN2', 'Thanh nhạc 2');

-- --------------------------------------------------------

--
-- Table structure for table `giangvien`
--

CREATE TABLE `giangvien` (
  `MaNV` varchar(20) NOT NULL,
  `MACLB` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `giangvien`
--

INSERT INTO `giangvien` (`MaNV`, `MACLB`) VALUES
('NTMP', 'DX'),
('PTB', 'EV-PR1'),
('PTB', 'EV-PR2'),
('CH', 'GT1'),
('CH', 'GT2'),
('CH', 'GT3'),
('HDY', 'PI1'),
('HDY', 'PI2'),
('TTT', 'TN1'),
('TTT', 'TN2');

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `MaNV` varchar(20) NOT NULL,
  `HoTen` varchar(255) NOT NULL,
  `ChucVu` varchar(100) DEFAULT NULL,
  `SDT` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`MaNV`, `HoTen`, `ChucVu`, `SDT`) VALUES
('CH', 'Chình Hân', 'GV', 0),
('HDY', 'Huỳnh Đông Uyên', 'GV', 0),
('HST', 'Hoàng Sinh Trung', 'CTV', 947165733),
('LDT', 'Lữ Đình Trương', 'CTV', NULL),
('LKTT', 'Lê Khổng Thanh Toàn', 'CTV', 0),
('LTH', 'Lê Thanh Hiệp', 'CTV', 823916502),
('NTMP', 'Nguyễn Thị Mai Phương', 'GV', 0),
('PTB', 'Phạm Thái Bình', 'GV', 0),
('TAN', 'Trần Anh Nga', 'CTV', NULL),
('TTT', 'Trần Thiện Thảo', 'GV', 0);

-- --------------------------------------------------------

--
-- Table structure for table `sinhvien`
--

CREATE TABLE `sinhvien` (
  `MSSV` int(11) NOT NULL,
  `HoTen` varchar(150) NOT NULL,
  `SDT` int(11) DEFAULT NULL,
  `MaCLB` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sinhvien`
--

INSERT INTO `sinhvien` (`MSSV`, `HoTen`, `SDT`, `MaCLB`) VALUES
(1511030066, 'Nguyễn Vinh Huy', 915668830, 'TN1'),
(1511180952, 'Phạm Chí Nguyện\r\n', 1279613678, 'TN2'),
(1511190385, 'Dương Thu Hiền\r\n', 961537897, 'PI2'),
(1511201198, 'Nguyễn Thị Tiểu Ly', 1655042211, 'GT2'),
(1511250094, 'Lương quốc duy anh ', 966795896, 'GT3'),
(1511270149, 'Trần Uyên Trâm\r\n', 983278945, 'PI1'),
(1611200302, 'Lê vũ hồng phúc\r\n\r\n', 898892612, 'TN1'),
(1611201685, 'Nguyễn Duy Anh', 1642053555, 'TN1'),
(1611370831, 'Nguyễn lâm thanh trúc', 164989778, 'TN1'),
(1611700825, 'Quách Ngọc Bảo Châu ', 1204683239, 'DX'),
(1611701164, 'La Ngọc Liên', 1296286957, 'TN1'),
(1611701342, 'Nguyễn Duy Bình\r\n', 1636864848, 'PI2'),
(1711040087, 'Phạm Quốc Khánh', 969939873, 'GT2'),
(1711040495, 'Trang Minh Vũ\r\n', 947539798, 'PI1'),
(1711050030, 'Phan Nhựt Trường', NULL, 'GT3'),
(1711050077, 'Đỗ Trọng Tín', 943160454, 'GT1'),
(1711060425, 'Nguyễn Thái Đức\r\n', 1214357984, 'PI1'),
(1711060503, 'Phạm Hồng Phúc\r\n', 1627233610, 'PI2'),
(1711060790, 'Phạm Anh Hào', 869367707, 'DX'),
(1711060855, 'Trần Đức Thanh', 1282941742, 'GT1'),
(1711060974, 'Phạm Quang Thạch', 0, 'GT2'),
(1711061139, 'Võ Đặng Tuấn Phong\r\n', 1644493689, 'PI2'),
(1711061253, 'Trần Nguyễn Phi Hùng', 937479630, 'DX'),
(1711061706, 'Trần Thanh Tài', 1629887962, 'GT2'),
(1711061831, 'Hà Quang Linh', 1657980195, 'GT2'),
(1711062058, 'Nguyễn Ngọc Thông', 1669625514, 'GT1'),
(1711100176, 'Nguyễn Thị Thanh\r\n', 1647798596, 'EV-PR1'),
(1711110083, 'Ngô Thị Thanh Thảo', 1229712048, 'DX'),
(1711120020, 'phan nguyễn công đức', 1269872433, 'GT3'),
(1711120044, 'Nguyễn Duy Thông\r\n', 996767400, 'PI2'),
(1711140015, 'Lâm Tuấn Anh', 1246684263, 'GT1'),
(1711140092, 'Hứa Hướng Dương', 946482747, 'GT1'),
(1711140854, 'Lê Thanh Phong', 1218714898, 'GT3'),
(1711141299, 'Lê Tường Vi\r\n', 1662447099, 'PI1'),
(1711141893, 'Trần thị thanh thiên\r\n', 1284541695, 'EV-PR2'),
(1711143368, 'Trần Xuân Nhi \r\n \r\n', 905940364, 'EV-PR1'),
(1711143440, 'Trần Hoàng Duy\r\n', 1637800036, 'GT3'),
(1711143536, 'Nguyễn Trần Ngọc Trâm', 1664989589, 'DX'),
(1711150048, 'Trương Hoài Hiếu', 986254040, 'TN2'),
(1711150088, 'TRẦN TÙNG LINH', 1215870239, 'GT1'),
(1711150182, 'Lê Nguyễn Minh Thương\r\n', 868067151, 'GT3'),
(1711150198, 'Trần Kim Trúc\r\n', 1224880529, 'PI2'),
(1711150233, 'Nguyễn Huy Bảo ', 937511503, 'TN2'),
(1711150394, 'Phan Thị Thu Hà\r\n', 1638311329, 'PI1'),
(1711150399, 'Nguyễn Thanh Ngọc Linh\r\n', 937953203, 'EV-PR1'),
(1711150454, 'Thai thuy yen nhi\r\n', 1648097571, 'PI1'),
(1711150469, 'Đoàn Ngọc Phương Uyên', 931889590, 'DX'),
(1711150740, 'Nguyễn Trầm Gia Hân \r\n', 1658000335, 'EV-PR1'),
(1711160593, 'Đặng lê phương anh\r\n', 948844702, 'GT2'),
(1711161044, 'Phạm Thị Hồng Ái\r\n', 1695666939, 'EV-PR1'),
(1711170101, 'Hồ Thị Ái Ly\r\n', 965986500, 'TN1'),
(1711170125, 'Trần Hữu Minh Nhật', 1283219915, 'TN2'),
(1711170138, 'Võ Hoàng Yến Nhi\r\n', 1217046499, 'EV-PR1'),
(1711170154, 'Nguyễn Hoàng Oanh\r\n', 981771714, 'PI1'),
(1711170480, 'Trần Thị Kim Ngân\r\n', 1627518536, 'EV-PR2'),
(1711170509, 'Lưu Thành Đương', 981577830, 'GT1'),
(1711180081, 'Phạm Thúy Nhi\r\n', 966941401, 'TN2'),
(1711190093, 'Lâm thị kim ngân', 898866785, 'DX'),
(1711190241, 'Nguyễn Thị Tuyết Nhung\r\n', 1668105211, 'PI1'),
(1711190313, 'Nguyễn Hữu Thông\r\n', 989667025, 'GT3'),
(1711200043, 'nguyễn kim chi', 1633462417, 'DX'),
(1711200152, 'Nguyễn hoàng yến linh', 1627143301, 'DX'),
(1711200318, 'Đoàn Phương Trinh', 968611835, 'TN1'),
(1711200353, 'Nguyễn Ngọc Tường Vy\r\n', 1678534732, 'TN2'),
(1711200475, 'lê thị mỹ hiền\r\n', 1687042766, 'PI2'),
(1711200813, 'Hồ Nguyên Thiên Kim\r\n', 903811420, 'TN2'),
(1711200834, 'Nguyễn Thị Tuyết Nhi', 927960769, 'GT2'),
(1711201089, 'Hoàng Thị Thúy Anh \r\n\r\n', 1883438526, 'EV-PR2'),
(1711201102, 'Huỳnh Công Ẩn\r\n', 964751241, 'GT3'),
(1711230006, 'Kiều lê vân anh\r\n', 888749005, 'EV-PR1'),
(1711230122, 'Nguyễn Mạnh Tâm Như\r\n', 909290182, 'EV-PR2'),
(1711230214, 'Nguyễn Thanh Uyên\r\n', 902496128, 'EV-PR1'),
(1711230231, 'Nguyễn Thị Tường Vy\r\n', 1866454853, 'EV-PR2'),
(1711230557, 'Đoàn Tấn Phong', 927765360, 'GT1'),
(1711230746, 'NGUYỄN HOÀNG VIỆT\r\n', 1245668938, 'EV-PR2'),
(1711240026, 'Đoàn Tấn Đạt\r\n', 1688301601, 'GT3'),
(1711240217, 'Nguyễn Võ Thu Trang', 901408727, 'TN2'),
(1711250159, 'Nguyễn Phương Nam', 1636784155, 'GT2'),
(1711251588, 'Trần Nguyễn Phố Văn\r\n', 904081039, 'PI2'),
(1711270039, 'HỒ NGỌC ĐIỆP HÂN', 907208082, 'PI1'),
(1711270550, 'Nguyễn Thị Phương Thanh', 1676639518, 'GT1'),
(1711290114, 'Trần Nguyễn Quang Huy', 1629543958, 'TN2'),
(1711290169, 'Mai Hoàng Phúc', 908707928, 'GT1'),
(1711290223, 'Dương Lê Ngọc Nguyệt\r\n', 963313610, 'PI1'),
(1711300034, 'Võ Tuấn Linh\r\n', 945694465, 'EV-PR2'),
(1711300313, 'Đào Vĩnh Thông\r\n', 918877350, 'EV-PR2'),
(1711401003, 'LÊ THỊ THU HIỀN\r\n', 989509650, 'EV-PR1'),
(1711700026, 'Vũ Thị Ngọc Anh', 961414856, 'TN1'),
(1711700471, 'Hà Trang Vân', 1863844697, 'TN1'),
(1711700720, 'Mai Khả Ngọc', 1678299608, 'EV-PR1'),
(1711700844, 'Nguyễn Thuỳ Dương\r\n', 1233170599, 'TN2'),
(1711700901, 'Phan Ngọc Quỳnh Như', 1222738878, 'TN1'),
(1711701068, 'Nguyễn Hoàng Tuấn Huy', NULL, 'GT3'),
(1711701084, 'Trương Nữ Quỳnh My', 1292556272, 'GT2'),
(1711701141, 'Phạm Trần Anh Thư \r\n', 931415675, 'EV-PR2'),
(1711701357, 'Nguyễn Ca Kỳ Hân\r\n', 1636808504, 'PI2'),
(1711701754, 'Phạm Huỳnh Anh Thư', 908758281, 'DX'),
(1711701993, 'Trần Ngọc Hải Huỳnh\r\n', 1242890789, 'EV-PR2'),
(1711760158, 'Phạm Vũ Nhật Yến', 906988459, 'GT2'),
(1811701208, 'Võ Phương Trà\r\n\r\n', 886167866, 'PI2');

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

CREATE TABLE `taikhoan` (
  `TenDangNhap` varchar(20) NOT NULL,
  `MatKhau` varchar(20) DEFAULT NULL,
  `LoaiTaiKhoan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `taikhoan`
--

INSERT INTO `taikhoan` (`TenDangNhap`, `MatKhau`, `LoaiTaiKhoan`) VALUES
('Admin', 'admin', 0),
('thanhhiep', 'matkhau', 1),
('thanhtrung', 'matkhau', 0),
('User', 'user', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `caulacbo`
--
ALTER TABLE `caulacbo`
  ADD PRIMARY KEY (`MaCLB`);

--
-- Indexes for table `giangvien`
--
ALTER TABLE `giangvien`
  ADD PRIMARY KEY (`MACLB`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MaNV`);

--
-- Indexes for table `sinhvien`
--
ALTER TABLE `sinhvien`
  ADD PRIMARY KEY (`MSSV`);

--
-- Indexes for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`TenDangNhap`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
