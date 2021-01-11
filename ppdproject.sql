-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 11, 2021 at 01:12 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ppdproject`
--

-- --------------------------------------------------------

--
-- Table structure for table `sala`
--

CREATE TABLE `sala` (
  `nr_locuri` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `spectacol`
--

CREATE TABLE `spectacol` (
  `ID_spectacol` int(11) NOT NULL,
  `data_spectacol` datetime NOT NULL,
  `titlu` varchar(30) NOT NULL,
  `pret_bilet` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `spectacol`
--

INSERT INTO `spectacol` (`ID_spectacol`, `data_spectacol`, `titlu`, `pret_bilet`) VALUES
(1, '2021-01-11 13:54:14', 'sdfsdfs', '13123');

-- --------------------------------------------------------

--
-- Table structure for table `vanzare`
--

CREATE TABLE `vanzare` (
  `id` int(11) NOT NULL,
  `data_vanzare` datetime NOT NULL,
  `id_spectacol` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `spectacol`
--
ALTER TABLE `spectacol`
  ADD PRIMARY KEY (`ID_spectacol`);

--
-- Indexes for table `vanzare`
--
ALTER TABLE `vanzare`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3wibqo1jo8iswyv5mfb9c0s5q` (`id_spectacol`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `spectacol`
--
ALTER TABLE `spectacol`
  MODIFY `ID_spectacol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `vanzare`
--
ALTER TABLE `vanzare`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `vanzare`
--
ALTER TABLE `vanzare`
  ADD CONSTRAINT `FK3wibqo1jo8iswyv5mfb9c0s5q` FOREIGN KEY (`id_spectacol`) REFERENCES `spectacol` (`ID_spectacol`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
