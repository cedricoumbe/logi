-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Mer 14 Avril 2021 à 10:33
-- Version du serveur: 5.5.8
-- Version de PHP: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `sales`
--

-- --------------------------------------------------------

--
-- Structure de la table `competence`
--

CREATE TABLE IF NOT EXISTS `competence` (
  `competence_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `competence_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`competence_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `competence`
--


-- --------------------------------------------------------

--
-- Structure de la table `competence_employee`
--

CREATE TABLE IF NOT EXISTS `competence_employee` (
  `id` bigint(20) NOT NULL,
  `competence_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`,`competence_id`),
  KEY `FKl4gwrh4hwhx38wceyd74btgc3` (`competence_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `competence_employee`
--


-- --------------------------------------------------------

--
-- Structure de la table `departement`
--

CREATE TABLE IF NOT EXISTS `departement` (
  `departement_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `departement_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`departement_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `departement`
--


-- --------------------------------------------------------

--
-- Structure de la table `employees`
--

CREATE TABLE IF NOT EXISTS `employees` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(30) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `departement_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKj9xgmd0ya5jmus09o0b8pqrpb` (`email`),
  KEY `departement_id` (`departement_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `employees`
--


-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `operation`
--

CREATE TABLE IF NOT EXISTS `operation` (
  `operation_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `operation_code_unique` varchar(255) DEFAULT NULL,
  `operation_date_cre` datetime DEFAULT NULL,
  `operation_date_mod` date DEFAULT NULL,
  `operation_montant_decaisser` int(11) NOT NULL,
  `operation_montant_payer` int(11) NOT NULL,
  `operation_nom_beneficiaire` varchar(255) NOT NULL,
  `operation_numero_transfert` int(11) DEFAULT NULL,
  `operation_numero_transfert_operateur` varchar(255) NOT NULL,
  `reseautransfertid` bigint(20) DEFAULT NULL,
  `sous_agent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`operation_id`),
  KEY `FK2nuq7cppduate3m2c7g8ne7i5` (`reseautransfertid`),
  KEY `FKgfdty474cbkuu356wuwojbw2m` (`sous_agent_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `operation`
--

INSERT INTO `operation` (`operation_id`, `operation_code_unique`, `operation_date_cre`, `operation_date_mod`, `operation_montant_decaisser`, `operation_montant_payer`, `operation_nom_beneficiaire`, `operation_numero_transfert`, `operation_numero_transfert_operateur`, `reseautransfertid`, `sous_agent_id`) VALUES
(1, NULL, '2021-04-14 11:27:00', NULL, 10000, 10000, 'KANA FERRY', NULL, '1', 2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `reseautransfert`
--

CREATE TABLE IF NOT EXISTS `reseautransfert` (
  `reseautransfertid` bigint(20) NOT NULL AUTO_INCREMENT,
  `reseautransfertcodeunique` varchar(255) DEFAULT NULL,
  `reseautransfertdatecre` varchar(255) DEFAULT NULL,
  `reseautransfertdatemod` varchar(255) DEFAULT NULL,
  `reseautransfertnom` varchar(30) NOT NULL,
  `reseautransfertnomimage` varchar(255) NOT NULL,
  PRIMARY KEY (`reseautransfertid`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `reseautransfert`
--

INSERT INTO `reseautransfert` (`reseautransfertid`, `reseautransfertcodeunique`, `reseautransfertdatecre`, `reseautransfertdatemod`, `reseautransfertnom`, `reseautransfertnomimage`) VALUES
(1, NULL, '2021/04/14', NULL, 'RIA', 'logo.png'),
(2, NULL, '2021/04/14', NULL, 'Gadjo Money', 'logo.png');

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL,
  `role_name` varchar(65) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `role`
--


-- --------------------------------------------------------

--
-- Structure de la table `sousagent_reseautransfert`
--

CREATE TABLE IF NOT EXISTS `sousagent_reseautransfert` (
  `sous_agent_id` bigint(20) NOT NULL,
  `reseautransfertid` bigint(20) NOT NULL,
  PRIMARY KEY (`sous_agent_id`,`reseautransfertid`),
  KEY `FKis5s9p3gmk9o1wh6covio5wr2` (`reseautransfertid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `sousagent_reseautransfert`
--

INSERT INTO `sousagent_reseautransfert` (`sous_agent_id`, `reseautransfertid`) VALUES
(1, 1),
(1, 2);

-- --------------------------------------------------------

--
-- Structure de la table `sous_agent`
--

CREATE TABLE IF NOT EXISTS `sous_agent` (
  `sous_agent_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sous_agent_adresse` varchar(30) NOT NULL,
  `sous_agent_code_unique` varchar(255) DEFAULT NULL,
  `sous_agent_conctact_operateur` varchar(30) NOT NULL,
  `sous_agent_date_cre` date DEFAULT NULL,
  `sous_agent_date_mod` date DEFAULT NULL,
  `sous_agent_email` varchar(30) NOT NULL,
  `sous_agent_mot_de_passe` varchar(30) NOT NULL,
  `sous_agent_nom` varchar(30) NOT NULL,
  `sous_agent_nom_commercial` varchar(30) NOT NULL,
  `sous_agent_nom_operateur` varchar(30) NOT NULL,
  PRIMARY KEY (`sous_agent_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `sous_agent`
--

INSERT INTO `sous_agent` (`sous_agent_id`, `sous_agent_adresse`, `sous_agent_code_unique`, `sous_agent_conctact_operateur`, `sous_agent_date_cre`, `sous_agent_date_mod`, `sous_agent_email`, `sous_agent_mot_de_passe`, `sous_agent_nom`, `sous_agent_nom_commercial`, `sous_agent_nom_operateur`) VALUES
(1, 'Fin barrière essec', NULL, '77777777', NULL, NULL, 'coumbe@yahoo.fr', 'exmpi', 'Nyalla', 'Bustra Money', 'kana ferry');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(115) DEFAULT NULL,
  `first_name` varchar(75) DEFAULT NULL,
  `last_name` varchar(80) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `username` varchar(65) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `user`
--

