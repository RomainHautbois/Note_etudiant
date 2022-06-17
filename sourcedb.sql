-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le : Dim 27 Janvier 2019 à 14:38
-- Version du serveur: 5.5.16
-- Version de PHP: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `sourcedb`
--

-- --------------------------------------------------------

--
-- Structure de la table `aide`
--

CREATE TABLE IF NOT EXISTS `aide` (
  `id` int(11) NOT NULL,
  `comment` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `aide`
--

INSERT INTO `aide` (`id`, `comment`) VALUES
(1, '\nApplication de gestion réalisée avec le langage de\n programmation JAVA, réliée à une base de données \n MySQL par TARGOTO CHRISTIAN pour le lycée et collège\n SOURCE DE PROGRES, le 27 janvier 2019.\nPour vos bésoins d''aide à la manipulation, contactez\n moi au 60316682 ou à ctargoto@gmail.com');

-- --------------------------------------------------------

--
-- Structure de la table `eleve`
--

CREATE TABLE IF NOT EXISTS `eleve` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `matricule` varchar(30) DEFAULT NULL,
  `nom` varchar(100) DEFAULT NULL,
  `daten` varchar(100) DEFAULT NULL,
  `sexe` varchar(30) DEFAULT NULL,
  `classe` varchar(30) DEFAULT NULL,
  `moy1` decimal(4,2) DEFAULT NULL,
  `moy2` decimal(4,2) DEFAULT NULL,
  `moy3` decimal(4,2) DEFAULT NULL,
  `photo` longblob,
  `tuteur` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=12 ;

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `vueleve`
--
CREATE TABLE IF NOT EXISTS `vueleve` (
`id` int(11)
,`matricule` varchar(30)
,`nom` varchar(100)
,`daten` varchar(100)
,`sexe` varchar(30)
,`classe` varchar(30)
,`tuteur` varchar(30)
,`moy1` decimal(4,2)
,`moy2` decimal(4,2)
,`moy3` decimal(4,2)
,`photo` longblob
,`moyan` decimal(10,6)
);
-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `vueleve2`
--
CREATE TABLE IF NOT EXISTS `vueleve2` (
`id` int(11)
,`matricule` varchar(30)
,`nom` varchar(100)
,`daten` varchar(100)
,`sexe` varchar(30)
,`classe` varchar(30)
,`tuteur` varchar(30)
,`moy1` decimal(4,2)
,`moy2` decimal(4,2)
,`moy3` decimal(4,2)
,`photo` longblob
,`moyan` decimal(10,6)
,`constat` varchar(7)
);
-- --------------------------------------------------------

--
-- Structure de la vue `vueleve`
--
DROP TABLE IF EXISTS `vueleve`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vueleve` AS select `eleve`.`id` AS `id`,`eleve`.`matricule` AS `matricule`,`eleve`.`nom` AS `nom`,`eleve`.`daten` AS `daten`,`eleve`.`sexe` AS `sexe`,`eleve`.`classe` AS `classe`,`eleve`.`tuteur` AS `tuteur`,`eleve`.`moy1` AS `moy1`,`eleve`.`moy2` AS `moy2`,`eleve`.`moy3` AS `moy3`,`eleve`.`photo` AS `photo`,(((`eleve`.`moy1` + `eleve`.`moy2`) + `eleve`.`moy3`) / 3) AS `moyan` from `eleve`;

-- --------------------------------------------------------

--
-- Structure de la vue `vueleve2`
--
DROP TABLE IF EXISTS `vueleve2`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vueleve2` AS select `vueleve`.`id` AS `id`,`vueleve`.`matricule` AS `matricule`,`vueleve`.`nom` AS `nom`,`vueleve`.`daten` AS `daten`,`vueleve`.`sexe` AS `sexe`,`vueleve`.`classe` AS `classe`,`vueleve`.`tuteur` AS `tuteur`,`vueleve`.`moy1` AS `moy1`,`vueleve`.`moy2` AS `moy2`,`vueleve`.`moy3` AS `moy3`,`vueleve`.`photo` AS `photo`,`vueleve`.`moyan` AS `moyan`,(case when (`vueleve`.`moyan` >= 10) then 'PASSE' else 'REPREND' end) AS `constat` from `vueleve`;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
