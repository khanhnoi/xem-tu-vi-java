-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 21, 2018 at 08:02 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tuvi`
--

-- --------------------------------------------------------

--
-- Table structure for table `tuoi`
--

CREATE TABLE `tuoi` (
  `ID` varchar(50) NOT NULL,
  `Tuoi` varchar(50) NOT NULL,
  `NoiDung` text NOT NULL,
  `TT` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tuoi`
--

INSERT INTO `tuoi` (`ID`, `Tuoi`, `NoiDung`, `TT`) VALUES
('ID01', 'Tuổi Tí', 'Trong số 12 con giáp, chuột được chọn và con vật tiên phong, dẫn đầu,\n bởi vậy những người thuộc tuổi này thường có tài năng lãnh đạo, \n quản lí rất tốt. Hơn nữa, những người tuổi Tý còn được mọi người ngưỡng mộ bởi sự thông minh,\n sáng tạo, nhanh nhẹn tháo vát của mình.', 4 ),
('ID02', 'Tuổi Sửu', 'Trong cuộc đua của 12 con giáp, trâu là con vật đạt vị trí số hai, \n đại diện cho sức mạnh về cả thể chất lẫn tinh thần. Bởi vậy những \n người tuổi Sửu đa số sống tự lập, trưởng thành sớm, có tính cách mạnh mẽ và kiên định. \n Người tuổi này có nhiều ước mơ, hoài bão, ý chí lớn, không ngại khó khăn, nguy hiểm.', 5 ),
('ID03', 'Tuổi Dần', 'Tuổi Dần đại diện cho những người quyền lực, mạnh mẽ, năng động trong\n cuộc sống. Họ thích những công việc mạo hiểm, đem đến vinh quanh, \n thành công rực rỡ cho mình. Người tuổi này có tài lãnh đạo, luôn nhiệt tình giúp đỡ \nnhững người xung quanh  và ra sức bảo vệ gia đình của mình.', 6 ),
('ID04', 'Tuổi Mão', 'Tuổi Mão đại diện cho những con người thông minh, mưu trí, nhanh nhẹn \n và có con mắt tinh tế, nhìn xa trông rộng. Người tuổi này rất tốt bụng, \n nhiệt tình và luôn sẵn sàng giúp đỡ những người gặp khó khăn. Họ có khả năng \n thích nghi với hoàn cảnh, chịu đựng những áp lực của công việc, cuộc sống.', 7 ),
('ID05', 'Tuổi Thìn', ' Rồng là con vật đại diện cho sức mạnh phi thường, thiêng liêng, được \n mọi người tôn kính. Những người tuổi Rồng vì thế mà thông minh sắc sảo, \n luôn đạt được nhiều thành công trong cuộc sống, có uy quyền, địa vị.', 8 ),
('ID06', 'Tuổi Tỵ', 'Tuổi Tỵ là những người khôn ngoan, khéo léo và mạo hiểm. Họ giàu tình cảm,\n có đời sống nội tâm khá phong phú, lãng mạn và luôn khiến người khác \n bị thu hút bởi vẻ đẹp, sự uyển chuyển của mình.', 9 ),
('ID07', 'Tuổi Ngọ', 'Ngựa là con vật yêu thích cuộc sống tự do, hoang dã để được hòa mình vào \n thiên nhiên. Những người tuổi Ngọ vì thế mà rất năng động, nhiệt tình, \n thích những nơi đông người và đam mê khám phá những điều mới mẻ trong \n cuộc sống', 10 ),
('ID08', 'Tuổi Mùi', 'Tuổi Mùi đại diện cho những người sống giàu tình cảm, nhân hậu, tốt bụng,\n luôn sẵn sàng giúp đỡ mọi người xung quanh. Đặc biệt, người tuổi này \n thông minh sắc sảo, tài trí hơn người, có con mắt nhìn xa trông rộng và \n khả năng phân tích nhạy bén.', 11 ),
('ID09', 'Tuổi Thân', 'Có thể nói rằng, Khỉ là con vật thông minh, khôn khéo nhất trong 12 con giáp. \n Bởi vậy mà những người tuổi Thân không những tài giỏi, thông minh, \n nhanh nhẹn mà còn giàu lòng vị tha, luôn sẵn sàng giúp đỡ những người \n gặp khó khăn.', 0 ),
('ID10', 'Tuổi Dậu', 'Gà là con vật đại diện cho sự năng động, nhiệt tình và kiêu hãnh. Vì thế mà \n những người tuổi Dậu luôn luôn sôi nổi, yêu đời, tràn đầy năng lượng sống. \n Họ được mọi người yêu quý bởi lòng tốt bụng, tinh thần trách nhiệm \n và tính tình ngay thẳng.', 1 ),
('ID011', 'Tuổi Tuất', 'Tuổi Tuất là những người nhân hậu, tốt bụng, sẵn sàng giúp đỡ mọi người xung \n quanh. Họ có thể vì người khác mà quên đi lợi ích của bản thân mình. \n Những người này coi trọng danh dự và chữ tín, một khi đã hứa, tuổi Tuất \n nhất định sẽ thực hiện.', 2 ),
('ID012', 'Tuổi Hợi', 'Tuổi Hợi là những người giàu lòng trắc ẩn, nhân ái và yêu thương mọi người. \n Họ sống tự do, thoải mái và phóng khoáng, bởi vậy tuổi Hợi được mọi người \n xung quanh quý mến, tôn trọng. Nhờ vào tài năng và sự chăm chỉ của mình \n, những người này sớm xây dựng được một sự nghiệp vững chắc, ổn định.', 3 );

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tuoi`
--
ALTER TABLE `tuoi`
  ADD PRIMARY KEY (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
