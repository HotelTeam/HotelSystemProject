-- phpMyAdmin SQL Dump
-- version 4.0.6deb1
-- http://www.phpmyadmin.net
--
-- Φιλοξενητής: localhost
-- Χρόνος δημιουργίας: 09 Φεβ 2014 στις 13:45:15
-- Έκδοση διακομιστή: 5.5.35-0ubuntu0.13.10.2
-- Έκδοση PHP: 5.5.3-1ubuntu2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Βάση: `hotel_db`
--

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `analysis`
--

CREATE TABLE IF NOT EXISTS `analysis` (
  `id_an` int(11) NOT NULL AUTO_INCREMENT,
  `id_month` int(11) DEFAULT NULL,
  `id_year` int(11) DEFAULT NULL,
  `profit` double DEFAULT NULL,
  `cost` double DEFAULT NULL,
  PRIMARY KEY (`id_an`),
  KEY `id_month` (`id_month`),
  KEY `id_year` (`id_year`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Άδειασμα δεδομένων του πίνακα `analysis`
--

INSERT INTO `analysis` (`id_an`, `id_month`, `id_year`, `profit`, `cost`) VALUES
(1, 5, 2, 200, 150),
(2, 4, 2, 250, 100);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `bed_type`
--

CREATE TABLE IF NOT EXISTS `bed_type` (
  `id_bed` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id_bed`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Άδειασμα δεδομένων του πίνακα `bed_type`
--

INSERT INTO `bed_type` (`id_bed`, `number`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `id_client` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `age` int(10) unsigned DEFAULT NULL,
  `creditcard` varchar(40) DEFAULT NULL,
  `firstname` varchar(20) DEFAULT NULL,
  `lastname` varchar(20) DEFAULT NULL,
  `mail` varchar(140) NOT NULL,
  PRIMARY KEY (`id_client`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Άδειασμα δεδομένων του πίνακα `client`
--

INSERT INTO `client` (`id_client`, `username`, `password`, `age`, `creditcard`, `firstname`, `lastname`, `mail`) VALUES
(1, 'foo1', 'ssss', 20, '1234', 'geo', 'dddd', ''),
(2, 'foo2', '1111', 22, '13456', 'dddd', 'ssss', ''),
(3, 'foo', '123', 22, '2456-7896-7980-7857', 'adas', 'asdas', 'fo@gmail.com');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `month`
--

CREATE TABLE IF NOT EXISTS `month` (
  `id_month` int(11) NOT NULL AUTO_INCREMENT,
  `month` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_month`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Άδειασμα δεδομένων του πίνακα `month`
--

INSERT INTO `month` (`id_month`, `month`) VALUES
(1, 'January'),
(2, 'February'),
(3, 'March'),
(4, 'April'),
(5, 'May'),
(6, 'June'),
(7, 'July'),
(8, 'August'),
(9, 'September'),
(10, 'October'),
(11, 'November'),
(12, 'December');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `passwords`
--

CREATE TABLE IF NOT EXISTS `passwords` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(40) NOT NULL,
  `password` varchar(160) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Άδειασμα δεδομένων του πίνακα `passwords`
--

INSERT INTO `passwords` (`id`, `username`, `password`) VALUES
(1, 'admin', '18a16d4530763ef43321d306c9f6c59ffed33072'),
(2, 'manager', 'aaadd94977b8fbf3f6fb09fc3bbbc9edbdfa8427');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `reservation`
--

CREATE TABLE IF NOT EXISTS `reservation` (
  `id_res` int(11) NOT NULL AUTO_INCREMENT,
  `arrival_date` varchar(20) DEFAULT NULL,
  `departure_date` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_res`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Άδειασμα δεδομένων του πίνακα `reservation`
--

INSERT INTO `reservation` (`id_res`, `arrival_date`, `departure_date`) VALUES
(1, '12/2/10', '14/2/10'),
(2, '12/2/13', '12/4/13');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `res_cl`
--

CREATE TABLE IF NOT EXISTS `res_cl` (
  `id_res` int(11) NOT NULL DEFAULT '0',
  `id_client` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_res`,`id_client`),
  KEY `id_client` (`id_client`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Άδειασμα δεδομένων του πίνακα `res_cl`
--

INSERT INTO `res_cl` (`id_res`, `id_client`) VALUES
(1, 1),
(2, 2);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `rooms`
--

CREATE TABLE IF NOT EXISTS `rooms` (
  `id_room` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(10) unsigned NOT NULL,
  `price` double unsigned DEFAULT NULL,
  `offer` double DEFAULT NULL,
  PRIMARY KEY (`id_room`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Άδειασμα δεδομένων του πίνακα `rooms`
--

INSERT INTO `rooms` (`id_room`, `number`, `price`, `offer`) VALUES
(9, 1, 3000, 0.12),
(10, 2, 200, 0.3),
(11, 36, 1200, 0.14),
(12, 32, 112, 0.13),
(14, 33, 140, 0.12),
(15, 15, 120, 0.12);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `simpleRoom`
--

CREATE TABLE IF NOT EXISTS `simpleRoom` (
  `id_room` int(11) NOT NULL DEFAULT '0',
  `id_bed` int(11) DEFAULT NULL,
  `air_con` tinyint(1) DEFAULT NULL,
  `multimedia` tinyint(1) DEFAULT NULL,
  `wi_fi` tinyint(1) DEFAULT NULL,
  `tv` tinyint(1) DEFAULT NULL,
  `refrigerator` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_room`),
  KEY `id_bed` (`id_bed`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Άδειασμα δεδομένων του πίνακα `simpleRoom`
--

INSERT INTO `simpleRoom` (`id_room`, `id_bed`, `air_con`, `multimedia`, `wi_fi`, `tv`, `refrigerator`) VALUES
(9, 2, 1, 1, 1, 1, 1),
(10, 3, 1, 1, 1, 1, 0),
(11, 4, 1, 1, 1, 1, 1),
(12, 2, 0, 1, 1, 1, 0),
(14, 3, 0, 1, 1, 1, 0),
(15, 3, 1, 0, 1, 0, 1);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `specific_res`
--

CREATE TABLE IF NOT EXISTS `specific_res` (
  `id_room` int(11) NOT NULL DEFAULT '0',
  `id_res` int(11) NOT NULL DEFAULT '0',
  `id_month` int(11) DEFAULT NULL,
  `id_year` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id_room`,`id_res`),
  KEY `id_res` (`id_res`),
  KEY `id_month` (`id_month`),
  KEY `id_year` (`id_year`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Άδειασμα δεδομένων του πίνακα `specific_res`
--

INSERT INTO `specific_res` (`id_room`, `id_res`, `id_month`, `id_year`, `price`) VALUES
(9, 1, 4, 1, 250),
(10, 2, 4, 4, 270);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `suiteRoom`
--

CREATE TABLE IF NOT EXISTS `suiteRoom` (
  `id_room` int(11) NOT NULL DEFAULT '0',
  `jacuzzi` tinyint(1) DEFAULT NULL,
  `breakfast` tinyint(1) DEFAULT NULL,
  `meal` tinyint(1) DEFAULT NULL,
  `dinner` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_room`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Άδειασμα δεδομένων του πίνακα `suiteRoom`
--

INSERT INTO `suiteRoom` (`id_room`, `jacuzzi`, `breakfast`, `meal`, `dinner`) VALUES
(9, 1, 1, 1, 0);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `year`
--

CREATE TABLE IF NOT EXISTS `year` (
  `id_year` int(11) NOT NULL AUTO_INCREMENT,
  `year` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_year`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Άδειασμα δεδομένων του πίνακα `year`
--

INSERT INTO `year` (`id_year`, `year`) VALUES
(1, '2010'),
(2, '2011'),
(3, '2012'),
(4, '2013'),
(5, '2014'),
(6, '2015'),
(7, '2016'),
(8, '2017'),
(9, '2018'),
(10, '2019'),
(11, '2020'),
(12, '2021');

--
-- Περιορισμοί για άχρηστους πίνακες
--

--
-- Περιορισμοί για πίνακα `analysis`
--
ALTER TABLE `analysis`
  ADD CONSTRAINT `analysis_ibfk_1` FOREIGN KEY (`id_month`) REFERENCES `month` (`id_month`),
  ADD CONSTRAINT `analysis_ibfk_2` FOREIGN KEY (`id_year`) REFERENCES `year` (`id_year`);

--
-- Περιορισμοί για πίνακα `res_cl`
--
ALTER TABLE `res_cl`
  ADD CONSTRAINT `res_cl_ibfk_1` FOREIGN KEY (`id_res`) REFERENCES `reservation` (`id_res`),
  ADD CONSTRAINT `res_cl_ibfk_2` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`);

--
-- Περιορισμοί για πίνακα `simpleRoom`
--
ALTER TABLE `simpleRoom`
  ADD CONSTRAINT `simpleRoom_ibfk_1` FOREIGN KEY (`id_room`) REFERENCES `rooms` (`id_room`) ON DELETE CASCADE,
  ADD CONSTRAINT `simpleRoom_ibfk_2` FOREIGN KEY (`id_bed`) REFERENCES `bed_type` (`id_bed`);

--
-- Περιορισμοί για πίνακα `specific_res`
--
ALTER TABLE `specific_res`
  ADD CONSTRAINT `specific_res_ibfk_1` FOREIGN KEY (`id_room`) REFERENCES `rooms` (`id_room`),
  ADD CONSTRAINT `specific_res_ibfk_2` FOREIGN KEY (`id_res`) REFERENCES `reservation` (`id_res`),
  ADD CONSTRAINT `specific_res_ibfk_3` FOREIGN KEY (`id_month`) REFERENCES `month` (`id_month`),
  ADD CONSTRAINT `specific_res_ibfk_4` FOREIGN KEY (`id_year`) REFERENCES `year` (`id_year`);

--
-- Περιορισμοί για πίνακα `suiteRoom`
--
ALTER TABLE `suiteRoom`
  ADD CONSTRAINT `suiteRoom_ibfk_1` FOREIGN KEY (`id_room`) REFERENCES `simpleRoom` (`id_room`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
