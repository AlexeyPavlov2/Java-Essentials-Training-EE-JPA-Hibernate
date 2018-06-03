-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Май 28 2018 г., 20:18
-- Версия сервера: 5.7.16
-- Версия PHP: 5.6.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `test_1`
--
CREATE DATABASE IF NOT EXISTS `test_1` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `test_1`;

-- --------------------------------------------------------

--
-- Структура таблицы `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `address` varchar(150) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `mail` varchar(50) DEFAULT NULL,
  `site` varchar(50) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Очистить таблицу перед добавлением данных `customer`
--

TRUNCATE TABLE `customer`;
--
-- Дамп данных таблицы `customer`
--

INSERT INTO `customer` (`id`, `name`, `address`, `city`, `phone`, `mail`, `site`, `type`) VALUES
(1, 'Gateway Technical College', 'Kenosha Campus 3520 - 30th Avenue Kenosha, WI 53144', 'Kenosha', '+1-800-247-7122', 'info@gtc.edu', 'https://www.gtc.edu', 'Academic'),
(2, 'California Energy Commission', 'California Energy Commission Media and Public Communications Office 1516 Ninth Street, MS-29 Sacramento, CA 95814-5512  USA', 'Sacramento', '+1-800-772-3300', 'appliances@energy.ca.gov', 'http://www.energy.ca.gov/', 'Energy'),
(3, 'Bose Corporation', '100 The Mountain Road Framingham, MA 01701 USA', 'Framingham', '+1-800 444-2673', 'support@bose.com', 'https://www.bose.com', 'Consumer products/Retail'),
(4, 'Cetco Energy Services', '1001 Ochsner Blvd. Suite 425 Covington, Louisiana 70433 USA', 'Louisiana ', '+1 985 871 4700', 'info@cetcoenergyservices.com', 'www.cetcoenergyservices.com', 'Energy'),
(5, 'Baumer Optronic GmbH', '122 Spring Street, Unit C-6 CT 06489 Southington, USA', 'Southington', '+1 800 937 9336', 'sales.us@baumer.com', 'https://www.baumer.com', 'Healthcare/Pharmaceutical'),
(6, 'Fluke Networks', 'Fluke Networks 6920 Seaway Boulevard Everett WA 98203, USA', 'Everett ', '1-800-283-5853', 'info@flukenetworks.com', 'http://www.flukenetworks.com', 'Telecommunications'),
(7, 'Atlas Technologies', '3100 Copper Ave Fenton, MI 48430 USA', 'Fenton', '+1-810-629-6663', 'sales@atlastechnologies.com', 'http://www.atlastechnologies.com', 'Internet /Hi-Tech'),
(8, 'Macewan Univeristy', 'City Centre Campus 10700 - 104 Avenue Edmonton, AB T5J 4S2 Canada', 'Edmonton', '780-497-5040', 'info@macewan.ca', 'https://www.macewan.ca', 'Academic'),
(9, 'Parallax Inc.', '599 Menlo Drive, Suite 100 Rocklin, CA 95765  USA', 'Rocklin', '+1-888-512-1024', 'support@parallax.com', 'https://www.parallax.com', 'Manufacturing/Mining'),
(10, 'R.J. Reynolds Tobacco Co', '1201 F St NW #1000, Washington, DC 20004, USA', 'Washington', '+1 202-626-7200', 'suppliers@rjrt.com', 'http://www.rjrt.com', 'Consumer products/Retail');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
