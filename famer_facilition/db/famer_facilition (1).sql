-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 24, 2021 at 12:39 AM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `famer_facilition`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`username`, `password`) VALUES
('admin', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `announcement`
--

CREATE TABLE `announcement` (
  `id` int(11) NOT NULL,
  `famerId` varchar(40) NOT NULL,
  `date` varchar(200) NOT NULL,
  `subject` varchar(400) NOT NULL,
  `message` varchar(4000) NOT NULL,
  `status` varchar(20) DEFAULT 'sent'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `announcement`
--

INSERT INTO `announcement` (`id`, `famerId`, `date`, `subject`, `message`, `status`) VALUES
(1, '1', '17/02/2021', 'Kwemererwa inkunga', 'Mwiriwe neza ubuyobozi bwa company yacu bwabemereye inkunga ya miriyoni 20 murakoze imana ibarinde', 'sent');

-- --------------------------------------------------------

--
-- Table structure for table `apply`
--

CREATE TABLE `apply` (
  `id` int(11) NOT NULL,
  `date` varchar(20) NOT NULL,
  `farmerId` varchar(40) NOT NULL,
  `seed_id` varchar(100) NOT NULL,
  `quantity` varchar(100) NOT NULL,
  `fertilizer_id` varchar(100) NOT NULL,
  `fertilizer_quantity` varchar(100) NOT NULL,
  `pesticide_id` varchar(100) NOT NULL,
  `pesticide_quantity` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `apply`
--

INSERT INTO `apply` (`id`, `date`, `farmerId`, `seed_id`, `quantity`, `fertilizer_id`, `fertilizer_quantity`, `pesticide_id`, `pesticide_quantity`) VALUES
(1, '', '', '', '', '', '', '', ''),
(2, '', '', '', '', '', '', '', ''),
(3, '23/01/2021', 'userId', '1', '50', '1', '25', '', '25'),
(4, '23/01/2021', '1', '1', '2', '2', '42', '1', '12'),
(5, '23/01/2021', '1', '1', '2', '2', '42', '1', '12'),
(6, '25/01/2021', '1', '', '2', '', '4', '', '5'),
(7, '26/01/2021', '1', '1', '28', '2', '39', '1', '5'),
(8, '27/01/2021', '1', '1', '3', '2', '40', '1', '5'),
(9, '17/02/2021', '1', '2', '7', '1', '2', '1', '20');

-- --------------------------------------------------------

--
-- Table structure for table `crop`
--

CREATE TABLE `crop` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `type` varchar(100) NOT NULL,
  `quantity` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `farmer`
--

CREATE TABLE `farmer` (
  `id` int(11) NOT NULL,
  `fname` varchar(100) NOT NULL,
  `lname` varchar(100) NOT NULL,
  `sex` varchar(10) NOT NULL,
  `age` varchar(10) NOT NULL,
  `national_ID` int(16) NOT NULL,
  `district` varchar(100) NOT NULL,
  `sector` varchar(100) NOT NULL,
  `village` varchar(100) NOT NULL,
  `cell` varchar(100) NOT NULL,
  `zone` varchar(100) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `farmer`
--

INSERT INTO `farmer` (`id`, `fname`, `lname`, `sex`, `age`, `national_ID`, `district`, `sector`, `village`, `cell`, `zone`, `phone`, `username`, `password`) VALUES
(1, 'mugisha', 'eric', 'm', '28', 5, 'Huye', 'ngoma', 'ngoma', 'ngoma 1', 'matyazo', '0780591269', 'admin', '12345');

-- --------------------------------------------------------

--
-- Table structure for table `fertilizer`
--

CREATE TABLE `fertilizer` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `type` varchar(100) NOT NULL,
  `category` varchar(100) NOT NULL,
  `quantity` varchar(100) NOT NULL,
  `amount` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fertilizer`
--

INSERT INTO `fertilizer` (`id`, `name`, `type`, `category`, `quantity`, `amount`) VALUES
(1, 'Imborera', 'gakondo', 'ghj', '50', '59'),
(2, 'imvaruganda', 'kizungu', 'fgj', '49', '39');

-- --------------------------------------------------------

--
-- Table structure for table `harvest`
--

CREATE TABLE `harvest` (
  `id` int(11) NOT NULL,
  `farmerId` varchar(40) NOT NULL,
  `harvest_name` varchar(100) NOT NULL,
  `date` varchar(40) NOT NULL,
  `quantity` varchar(10) NOT NULL,
  `price` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `harvest`
--

INSERT INTO `harvest` (`id`, `farmerId`, `harvest_name`, `date`, `quantity`, `price`) VALUES
(3, '1', 'ibishimbo', '22/01/2021', '500', '450'),
(4, '1', 'amashu', '22/01/2021', '15', '200'),
(5, '1', 'ikitoki', '23/01/2021', '500', '200'),
(12, '1', 'Karote', '17/02/2021', '4', '500');

-- --------------------------------------------------------

--
-- Table structure for table `pesticide`
--

CREATE TABLE `pesticide` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `type` varchar(100) NOT NULL,
  `category` varchar(100) NOT NULL,
  `quantity` varchar(100) NOT NULL,
  `amount` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pesticide`
--

INSERT INTO `pesticide` (`id`, `name`, `type`, `category`, `quantity`, `amount`) VALUES
(1, 'NPK', 'type of ', 'jruh', '50', '1000'),
(2, 'NTP', 'fjhh', 'hi', '49', '400');

-- --------------------------------------------------------

--
-- Table structure for table `seeds`
--

CREATE TABLE `seeds` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `type` varchar(200) NOT NULL,
  `category` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `seeds`
--

INSERT INTO `seeds` (`id`, `name`, `type`, `category`) VALUES
(1, 'ibigori', 'ibinyampeke', 'hhhj'),
(2, 'amashu', 'imbiga', 'hghh');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `announcement`
--
ALTER TABLE `announcement`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `apply`
--
ALTER TABLE `apply`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `crop`
--
ALTER TABLE `crop`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `farmer`
--
ALTER TABLE `farmer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `fertilizer`
--
ALTER TABLE `fertilizer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `harvest`
--
ALTER TABLE `harvest`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pesticide`
--
ALTER TABLE `pesticide`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `seeds`
--
ALTER TABLE `seeds`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `announcement`
--
ALTER TABLE `announcement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `apply`
--
ALTER TABLE `apply`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `crop`
--
ALTER TABLE `crop`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `farmer`
--
ALTER TABLE `farmer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `fertilizer`
--
ALTER TABLE `fertilizer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `harvest`
--
ALTER TABLE `harvest`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `pesticide`
--
ALTER TABLE `pesticide`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `seeds`
--
ALTER TABLE `seeds`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
