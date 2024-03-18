-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 01, 2023 at 10:10 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sakancom`
--

-- --------------------------------------------------------

--
-- Table structure for table `owner`
--

CREATE TABLE `owner` (
  `owner_username` varchar(20) NOT NULL,
  `owner_name` varchar(30) NOT NULL,
  `owner_phone` varchar(20) NOT NULL,
  `owner_email` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `owner`
--

INSERT INTO `owner` (`owner_username`, `owner_name`, `owner_phone`, `owner_email`) VALUES
('Diaa', 'Diaa Sharqawi', '0599201020', 'diaaSh@yahoo.com'),
('Sara', 'Sara Khaled', '059947896', 'SaraKh2023@gmail.com'),
('Omar', 'Omar qamhieh', '2563002', 'Omar_Qamhieh1999@hotmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `requesthouse`
--

CREATE TABLE `requesthouse` (
  `RequestID` int(11) NOT NULL,
  `OwnerUserName` varchar(20) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `City` varchar(30) NOT NULL,
  `Street` varchar(30) NOT NULL,
  `Description` text NOT NULL,
  `Contact Number` varchar(20) NOT NULL,
  `House For` varchar(10) NOT NULL,
  `R/month` decimal(10,0) NOT NULL,
  `Number of Room` tinyint(4) NOT NULL,
  `Number of Bathroom` tinyint(4) NOT NULL,
  `Area` decimal(10,0) NOT NULL,
  `Home Features` text NOT NULL,
  `Image` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `requesthouse`
--

INSERT INTO `requesthouse` (`RequestID`, `OwnerUserName`, `Name`, `City`, `Street`, `Description`, `Contact Number`, `House For`, `R/month`, `Number of Room`, `Number of Bathroom`, `Area`, `Home Features`, `Image`) VALUES
(3, 'Omar', 'Modern Apartment', 'Gaza', 'Al-Whda', 'Contemporary and stylish apartment', '555-2468', 'Rent', '1800', 2, 1, '900', 'Balcony', 'https://images.app.goo.gl/4HcMHWzsVYR33Tei8');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `Type` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `Type`) VALUES
('Ahmad', '1234', 'Admin'),
('Diaa', '1212', 'Owner'),
('Khaled', '200200', 'Tenant'),
('Omar', '1414', 'Owner'),
('Sara', '1212', 'Owner');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `owner`
--
ALTER TABLE `owner`
  ADD KEY `fk_owner_userName_Constraint` (`owner_username`);

--
-- Indexes for table `requesthouse`
--
ALTER TABLE `requesthouse`
  ADD PRIMARY KEY (`RequestID`),
  ADD KEY `fk_home_userName_Constraint` (`OwnerUserName`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `requesthouse`
--
ALTER TABLE `requesthouse`
  MODIFY `RequestID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `owner`
--
ALTER TABLE `owner`
  ADD CONSTRAINT `fk_owner_userName_Constraint` FOREIGN KEY (`owner_username`) REFERENCES `user` (`username`);

--
-- Constraints for table `requesthouse`
--
ALTER TABLE `requesthouse`
  ADD CONSTRAINT `fk_home_userName_Constraint` FOREIGN KEY (`OwnerUserName`) REFERENCES `owner` (`owner_username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
