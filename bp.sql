-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 29, 2017 at 12:32 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `bp`
--

-- --------------------------------------------------------

--
-- Table structure for table `agence`
--

CREATE TABLE IF NOT EXISTS `agence` (
  `idAg` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL,
  `adresse` varchar(50) NOT NULL,
  `tel` varchar(50) NOT NULL,
  PRIMARY KEY (`idAg`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `agence`
--

INSERT INTO `agence` (`idAg`, `code`, `adresse`, `tel`) VALUES
(1, '0001', 'pikine', '338220144'),
(2, '0002', 'dakar', '338012544'),
(3, '0004', 'guediawaye', '338524232'),
(4, '0006', 'yeumbeul', '334859231');

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `idCli` int(11) NOT NULL AUTO_INCREMENT,
  `nomCli` varchar(255) NOT NULL,
  `prenomCli` varchar(255) NOT NULL,
  `adresseCli` varchar(255) NOT NULL,
  `emailCli` varchar(255) NOT NULL,
  `telCli` varchar(255) NOT NULL,
  `salactCli` double NOT NULL,
  `professionCli` varchar(255) NOT NULL,
  `infoEmployeurCli` varchar(255) NOT NULL,
  `numCNi` varchar(255) NOT NULL,
  PRIMARY KEY (`idCli`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`idCli`, `nomCli`, `prenomCli`, `adresseCli`, `emailCli`, `telCli`, `salactCli`, `professionCli`, `infoEmployeurCli`, `numCNi`) VALUES
(1, 'nael', 'amadou', 'ouest foire', 'naelamadou@gmail.com', '775025838', 1200500, 'etudiant', 'chef entreprise', '11993409011'),
(2, 'ngom', 'tapha', 'ougounay', 'tapha@gmail.com', '774012502', 50000000, 'dg', 'chef ese', '125789631'),
(3, 'ngom', 'tapha', 'ouounay', 'tapha@gmail.com', '774012502', 50000000, 'dg', 'chef ese', '125789631'),
(4, 'Traore', 'Mahamoud', 'Hann', 'tra.mahamoud77@gmail.com', '777228833', 2500000, 'Chef De Projet', 'PDG TraCom', '1234456'),
(5, 'Sangare', 'Mariam Larissa', 'Bobo', 'tr', 'sd', 300000, 'Gerante', 'zx', '123'),
(6, 'ndiaye', 'amy', 'parcelle', 'amy@hot.com', '77802548', 50000, 'etudiant', 'PDG', '1245789635'),
(7, 'mbengue', 'anta', 'camberene', 'anta@gmail.com', '778023656', 8000, 'etuciante', 'directeur general', '196714');

-- --------------------------------------------------------

--
-- Table structure for table `compte`
--

CREATE TABLE IF NOT EXISTS `compte` (
  `idcpte` int(11) NOT NULL AUTO_INCREMENT,
  `numCompte` varchar(50) NOT NULL,
  `agence` varchar(50) NOT NULL,
  `cleRib` varchar(5) NOT NULL,
  `type` varchar(50) NOT NULL,
  `fraisDouverture` double DEFAULT NULL,
  `agio` double DEFAULT NULL,
  `raisonSocialEmploy` varchar(50) DEFAULT NULL,
  `adresseEmploy` varchar(50) DEFAULT NULL,
  `numIdentifieEmploy` varchar(50) DEFAULT NULL,
  `idClient` int(11) NOT NULL,
  PRIMARY KEY (`idcpte`),
  KEY `idClient` (`idClient`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `compte`
--

INSERT INTO `compte` (`idcpte`, `numCompte`, `agence`, `cleRib`, `type`, `fraisDouverture`, `agio`, `raisonSocialEmploy`, `adresseEmploy`, `numIdentifieEmploy`, `idClient`) VALUES
(3, 'COMPTE 3250002', '2', '25', 'compte bloquer', 25000, NULL, NULL, NULL, NULL, 5),
(4, 'COMPTE 4010002', '2', '01', 'compte bloquer', 10000, NULL, NULL, NULL, NULL, 6),
(5, 'COMPTE 3001240001', '1', '00124', 'compte bloquer', 10000, NULL, NULL, NULL, NULL, 1),
(6, 'COMPTE 400450001', '1', '0045', 'compte courant', NULL, 12547, 'employe', 'parcel', '01247', 6),
(7, 'COMPTE 500360006', '4', '0036', 'compte courant', NULL, 5000, 'pas mal', 'dakar', '1245', 7);

-- --------------------------------------------------------

--
-- Table structure for table `employe`
--

CREATE TABLE IF NOT EXISTS `employe` (
  `idEmp` int(11) NOT NULL AUTO_INCREMENT,
  `nomComplet` varchar(50) NOT NULL,
  `adresse` varchar(50) NOT NULL,
  `tel` varchar(50) NOT NULL,
  `idAgence` int(11) NOT NULL,
  PRIMARY KEY (`idEmp`),
  KEY `idAgence` (`idAgence`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `employe`
--

INSERT INTO `employe` (`idEmp`, `nomComplet`, `adresse`, `tel`, `idAgence`) VALUES
(1, 'astou ndiaye', 'parcel', '704000144', 4);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `idU` int(11) NOT NULL AUTO_INCREMENT,
  `loginU` varchar(255) NOT NULL,
  `passwordU` varchar(255) NOT NULL,
  `profil` varchar(50) NOT NULL,
  PRIMARY KEY (`idU`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`idU`, `loginU`, `passwordU`, `profil`) VALUES
(1, 'nael', 'nael', 'Responsable des comptes'),
(2, 'admin', 'admin', 'administrateur');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `compte`
--
ALTER TABLE `compte`
  ADD CONSTRAINT `compte_ibfk_1` FOREIGN KEY (`idClient`) REFERENCES `client` (`idCli`);

--
-- Constraints for table `employe`
--
ALTER TABLE `employe`
  ADD CONSTRAINT `employe_ibfk_1` FOREIGN KEY (`idEmp`) REFERENCES `agence` (`idAg`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
