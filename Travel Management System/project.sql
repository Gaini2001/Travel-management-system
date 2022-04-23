-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 01, 2021 at 09:27 AM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `anurodh`
--

-- --------------------------------------------------------

--
-- Table structure for table `project`
--

CREATE TABLE `project` (
  `sno` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `project`
--

INSERT INTO `project` (`sno`, `email`, `password`) VALUES
(1, 'hello@gmail.com', 'hello'),
(4, 'anurodhsinghmp@gmail.com', '$2y$10$zpW4rW/JZ2eMjprVuhLBPu/aRdJmFPkqZOFY3Igc/rhxE85J9WLqa'),
(5, 'ashijadhav77@gmail.com', '$2y$10$q5u0GpznRBI9Fb7s2ZWeIuwNES.BupZGCJUiP4ZQTWmraAlaIpJKu'),
(6, 'aviralsinghmp@gmail.com', '$2y$10$dFqs5U8GN4lj.ai4SM3uTe6sjSsxnwdlSQbrfe7EJjdznsk2yeZJi'),
(7, '201951028@iiitvadodara.ac.in', '$2y$10$0KH/gJHQnES.9grRD6VX.ORe2865JyNDNt/xu4MfphrsQlguWsN/2'),
(8, 'mohit@gmail.com', '$2y$10$wTg71bIaY4IJspzFCEFwGuIJIzwK2xs47C0DL6aFXvZwf4PikTcMW'),
(9, 'aniket@gmail.com', '$2y$10$by5INeRbmUvItwZYjuMA1uMQ1NfbtGISk1OndZD9xdk7OWWrQo5a.'),
(10, 'dummy@gmail.com', '$2y$10$oTH/sat7P7gVvIcs4PzC.OKcmmwFli1slPaupSSKMTGt/a0p/RjlS');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`sno`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `project`
--
ALTER TABLE `project`
  MODIFY `sno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
