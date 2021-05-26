-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3308
-- Généré le :  mer. 26 mai 2021 à 19:48
-- Version du serveur :  8.0.18
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `forumjavaws`
--

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'Mathématiques'),
(2, 'Informatique'),
(3, 'Astronomie'),
(4, 'Ingénierie');

-- --------------------------------------------------------

--
-- Structure de la table `category_topics`
--

DROP TABLE IF EXISTS `category_topics`;
CREATE TABLE IF NOT EXISTS `category_topics` (
  `category_id` bigint(20) NOT NULL,
  `topics_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_dhb63sss71wl8gvkv0s79hi81` (`topics_id`),
  KEY `FKlru5spmodlr8r77654vs2emwh` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `category_topics`
--

INSERT INTO `category_topics` (`category_id`, `topics_id`) VALUES
(1, 1),
(1, 2),
(2, 3),
(3, 4),
(4, 5);

-- --------------------------------------------------------

--
-- Structure de la table `post`
--

DROP TABLE IF EXISTS `post`;
CREATE TABLE IF NOT EXISTS `post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `topic_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg8ln3nj8tjclai0nuvpw5s5uw` (`topic_id`),
  KEY `FK72mt33dhhs48hf9gcqrq4fxte` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `post`
--

INSERT INTO `post` (`id`, `content`, `created_at`, `updated_at`, `topic_id`, `user_id`) VALUES
(1, 'Post modifié', '2021-05-26 19:35:05.858000', '2021-05-26 19:35:20.096000', 1, 1),
(2, 'ipsum lorem', '2021-05-26 19:35:08.333000', NULL, 2, 1),
(3, 'contenu d\'un post', '2021-05-26 19:35:10.912000', NULL, 1, 1),
(4, 'ipsum lorem', '2021-05-26 19:37:17.056000', NULL, 3, 1),
(5, 'ipsum lorem', '2021-05-26 19:37:19.978000', NULL, 4, 1),
(6, 'ipsum lorem', '2021-05-26 19:37:23.090000', NULL, 5, 1);

-- --------------------------------------------------------

--
-- Structure de la table `post_reports`
--

DROP TABLE IF EXISTS `post_reports`;
CREATE TABLE IF NOT EXISTS `post_reports` (
  `post_id` bigint(20) NOT NULL,
  `reports_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_7tfsogxfngxfaq896aiyu6b7u` (`reports_id`),
  KEY `FKtu5yk7b6ygixcau3fchm28yy` (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `post_reports`
--

INSERT INTO `post_reports` (`post_id`, `reports_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `report`
--

DROP TABLE IF EXISTS `report`;
CREATE TABLE IF NOT EXISTS `report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reason` varchar(20) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnuqod1y014fp5bmqjeoffcgqy` (`post_id`),
  KEY `FKj62onw73yx1qnmd57tcaa9q3a` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `report`
--

INSERT INTO `report` (`id`, `reason`, `post_id`, `user_id`) VALUES
(1, 'RACISM', 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER'),
(3, 'ROLE_MODERATOR');

-- --------------------------------------------------------

--
-- Structure de la table `topic`
--

DROP TABLE IF EXISTS `topic`;
CREATE TABLE IF NOT EXISTS `topic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `locked` bit(1) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8n7r9utm8sjpdfstb4wcqd7qj` (`category_id`),
  KEY `FK38wu074adxipuj6a9ifd7jv5f` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `topic`
--

INSERT INTO `topic` (`id`, `locked`, `title`, `category_id`, `user_id`) VALUES
(1, b'1', 'lorem ipsum', 1, 1),
(2, b'0', 'lorem ipsum', 1, 1),
(3, b'0', 'lorem ipsum', 2, 1),
(4, b'0', 'lorem ipsum', 3, 1),
(5, b'0', 'lorem ipsum', 4, 1);

-- --------------------------------------------------------

--
-- Structure de la table `topic_posts`
--

DROP TABLE IF EXISTS `topic_posts`;
CREATE TABLE IF NOT EXISTS `topic_posts` (
  `topic_id` bigint(20) NOT NULL,
  `posts_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_s5bd5c2jgcw49x5w4fiqmiagd` (`posts_id`),
  KEY `FKp3croykyaohnl662esakg61yj` (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `topic_posts`
--

INSERT INTO `topic_posts` (`topic_id`, `posts_id`) VALUES
(1, 1),
(1, 3),
(2, 2),
(3, 4),
(4, 5),
(5, 6);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `locked` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `email`, `locked`, `password`) VALUES
(1, 'ludovic.angenard@campus.academy', b'0', '$2a$10$Gneas4jP8.QZkbTxUhtlZOX420C.vKyXZkYwUI1oDlGCA8FZSYABO'),
(2, 'lu.angenard@campus.academy', b'1', '$2a$10$96NiL6g.uunhlh/VvvBVy.8FsIUODb/UqzWXU/O35z0.YpMheWsZC'),
(3, 'modo@campus.academy', b'0', '$2a$10$UELO7jNxyi84H3BOS3QaVewtQ/RG5D8GwCj1eEELI5YVCtrxe52ve');

-- --------------------------------------------------------

--
-- Structure de la table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 2),
(3, 3);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `category_topics`
--
ALTER TABLE `category_topics`
  ADD CONSTRAINT `FKlru5spmodlr8r77654vs2emwh` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `FKnpma3dg1w2k7bjtsw9okxgxn5` FOREIGN KEY (`topics_id`) REFERENCES `topic` (`id`);

--
-- Contraintes pour la table `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `FK72mt33dhhs48hf9gcqrq4fxte` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKg8ln3nj8tjclai0nuvpw5s5uw` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`);

--
-- Contraintes pour la table `post_reports`
--
ALTER TABLE `post_reports`
  ADD CONSTRAINT `FKjpfi1ccu92trho7l4q980xt1d` FOREIGN KEY (`reports_id`) REFERENCES `report` (`id`),
  ADD CONSTRAINT `FKtu5yk7b6ygixcau3fchm28yy` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`);

--
-- Contraintes pour la table `report`
--
ALTER TABLE `report`
  ADD CONSTRAINT `FKj62onw73yx1qnmd57tcaa9q3a` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKnuqod1y014fp5bmqjeoffcgqy` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`);

--
-- Contraintes pour la table `topic`
--
ALTER TABLE `topic`
  ADD CONSTRAINT `FK38wu074adxipuj6a9ifd7jv5f` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK8n7r9utm8sjpdfstb4wcqd7qj` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

--
-- Contraintes pour la table `topic_posts`
--
ALTER TABLE `topic_posts`
  ADD CONSTRAINT `FKitr4seeq9uagrsdo5e13tb8iw` FOREIGN KEY (`posts_id`) REFERENCES `post` (`id`),
  ADD CONSTRAINT `FKp3croykyaohnl662esakg61yj` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`);

--
-- Contraintes pour la table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
