-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 10, 2021 at 04:41 PM
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

-- --------------------------------------------------------

--
-- Table structure for table `vanzare`
--

CREATE TABLE `vanzare` (
  `id` int(11) NOT NULL,
  `idSpectacol` int(11) NOT NULL,
  `data_vanzare` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `vanzare_loc`
--

CREATE TABLE `vanzare_loc` (
  `idVanzare` int(11) NOT NULL,
  `NrLoc` int(11) NOT NULL
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
  ADD KEY `FK_spectacol_vanzare` (`idSpectacol`);

--
-- Indexes for table `vanzare_loc`
--
ALTER TABLE `vanzare_loc`
  ADD KEY `FK_vanzare_loc` (`idVanzare`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `spectacol`
--
ALTER TABLE `spectacol`
  MODIFY `ID_spectacol` int(11) NOT NULL AUTO_INCREMENT;

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
  ADD CONSTRAINT `FK_spectacol_vanzare` FOREIGN KEY (`idSpectacol`) REFERENCES `spectacol` (`ID_spectacol`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `vanzare_loc`
--
ALTER TABLE `vanzare_loc`
  ADD CONSTRAINT `FK_vanzare_loc` FOREIGN KEY (`idVanzare`) REFERENCES `vanzare` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
