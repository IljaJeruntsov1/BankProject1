-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 05, 2026 at 05:31 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bank_database_v4`
--

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

CREATE TABLE `accounts` (
  `id` bigint(20) NOT NULL,
  `account_name` varchar(255) NOT NULL,
  `balance` decimal(19,2) NOT NULL,
  `currency` varchar(3) NOT NULL,
  `iban` varchar(255) NOT NULL,
  `reserved` decimal(19,2) NOT NULL,
  `customer_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`id`, `account_name`, `balance`, `currency`, `iban`, `reserved`, `customer_id`) VALUES
(1, 'gfdgdfgdf hhgfhgfhgf', 1000.00, 'EUR', 'EE38 1010 0116 7723 5229', 100.00, 13);

-- --------------------------------------------------------

--
-- Table structure for table `application`
--

CREATE TABLE `application` (
  `id` bigint(20) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `date_of_birth` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `status` varchar(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `phone` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `application`
--

INSERT INTO `application` (`id`, `first_name`, `last_name`, `date_of_birth`, `email`, `status`, `created_at`, `phone`) VALUES
(1, 'Ivan', 'Ivanov', '2000-05-15', 'iiuu00225@gmail.com', 'NOT APPROVED', '2026-10-01 21:29:14', '+37255555555'),
(2, 'Ivan', 'Ivanov', '2000-05-15', 'iiuu00225@gmail.com', 'APPROVED', '2026-10-02 17:26:22', '+3725325555'),
(3, 'dsadsa', 'dsada', '2000-03-17', 'kopchik22848@gmail.com', 'APPROVED', '2026-10-02 20:16:26', '4444444444'),
(4, 'dsadsad', 'dsadsada', '2000-01-12', 'aunkezzz@gmail.com', 'APPROVED', '2026-10-02 20:20:11', '312321938'),
(5, 'gfdgdfgdf', 'hhgfhgfhgf', '2000-03-12', 'vcxd5608@gmail.com', 'APPROVED', '2026-10-03 11:29:08', '423423432423');

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `id` bigint(20) NOT NULL,
  `customer_number` varchar(30) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `date_of_birth` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(30) NOT NULL,
  `status` varchar(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`id`, `customer_number`, `first_name`, `last_name`, `date_of_birth`, `email`, `phone`, `status`, `created_at`, `updated_at`) VALUES
(1, 'CUST-3EF0E5B23688', 'dsa', 'dsad', '0000-00-00', 'dsa@dsa.com', '321321321321', 'ACTIVE', '2026-09-30 19:08:12', '2026-09-30 19:08:12'),
(2, '222222', 'admin', 'admin', '0000-00-00', 'admin', '666', 'admin', '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(4, 'CUS-81882', 'Ivan', 'Ivanov', '2000-05-15', 'ivan@example.com', '+37255555555', 'ACTIVE', '2026-10-01 22:29:38', '2026-10-01 22:29:38'),
(10, 'CUS-80134', 'Ivan', 'Ivanov', '2000-05-15', 'iiuu00225@gmail.com', '+3725325555', 'ACTIVE', '2026-10-02 17:41:45', '2026-10-02 17:41:45'),
(11, 'CUS-63957', 'dsadsa', 'dsada', '2000-03-17', 'kopchik22848@gmail.com', '4444444444', 'ACTIVE', '2026-10-02 20:16:41', '2026-10-02 20:16:41'),
(12, 'CUS-46317', 'dsadsad', 'dsadsada', '2000-01-12', 'aunkezzz@gmail.com', '312321938', 'ACTIVE', '2026-10-02 20:20:34', '2026-10-02 20:20:34'),
(13, 'CUS-26835', 'gfdgdfgdf', 'hhgfhgfhgf', '2000-03-12', 'vcxd5608@gmail.com', '423423432423', 'ACTIVE', '2026-10-03 11:29:53', '2026-10-03 11:29:53');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` text DEFAULT NULL,
  `created_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`, `description`, `created_at`) VALUES
(1, 'CLIENT', 'Bank client', '2026-09-30 19:07:39'),
(2, 'Admin', '+', '2026-09-30 19:52:39');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `username` varchar(100) NOT NULL,
  `status` varchar(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `last_login_at` datetime DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `customer_id`, `username`, `status`, `role_id`, `last_login_at`, `created_at`, `updated_at`) VALUES
(1, 1, 'dsadsa', 'ACTIVE', 2, NULL, '2026-09-30 19:08:12', '2026-09-30 19:08:12'),
(4, 10, 'Ivan33501', 'ACTIVE', 2, NULL, '2026-10-02 17:41:45', '2026-10-02 17:41:45'),
(5, 11, 'dsadsa82234', 'ACTIVE', 2, NULL, '2026-10-02 20:16:41', '2026-10-02 20:16:41'),
(6, 12, 'dsadsad14877', 'ACTIVE', 2, NULL, '2026-10-02 20:20:34', '2026-10-02 20:20:34'),
(7, 13, 'gfdgdfgdf75322', 'ACTIVE', 2, NULL, '2026-10-03 11:29:53', '2026-10-03 11:29:53');

-- --------------------------------------------------------

--
-- Table structure for table `user_credentials`
--

CREATE TABLE `user_credentials` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `password_changed_at` datetime DEFAULT NULL,
  `failed_login_attempts` int(11) NOT NULL,
  `locked_until` datetime DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_credentials`
--

INSERT INTO `user_credentials` (`id`, `user_id`, `password_hash`, `password_changed_at`, `failed_login_attempts`, `locked_until`, `created_at`, `updated_at`) VALUES
(1, 1, '$2a$10$N4ppDwORyjHOr/ZUIz3ZAe/r67xat6nZST8euJSp/76zsGETxj3zO', '2026-09-30 19:08:12', 0, NULL, '2026-09-30 19:08:12', '2026-09-30 19:08:12'),
(3, 4, '$2a$10$p2BK4oBDBkX/..DakwiaXuPD73woyIa4L5w1MSY9/jAiaPE/OcHFu', NULL, 0, NULL, '2026-10-02 17:41:45', '2026-10-02 17:41:45'),
(4, 5, '$2a$10$EmU8sRRTcI50ivRUpMx4y.BnbC87T1P5wOnxscnck.hjZ0hxCFJ2.', NULL, 0, NULL, '2026-10-02 20:16:41', '2026-10-02 20:16:41'),
(5, 6, '$2a$10$oQdS2eN5yeI78czO4V647uZz4D2SpN4eavxYnBIADDkT9W66xideG', NULL, 0, NULL, '2026-10-02 20:20:34', '2026-10-02 20:20:34'),
(6, 7, '$2a$10$4g0/lmDh6pQLXgkwlT10murqL1iteT15APBkIza0KhwX4GtdyTcMG', NULL, 0, NULL, '2026-10-03 11:29:53', '2026-10-03 11:29:53');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKnubpiuxhnr0f0tl3tx3y9i80u` (`iban`),
  ADD KEY `FKn6x8pdp50os8bq5rbb792upse` (`customer_id`);

--
-- Indexes for table `application`
--
ALTER TABLE `application`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `customer_number` (`customer_number`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `phone` (`phone`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `fk_user_customer` (`customer_id`),
  ADD KEY `fk_user_role` (`role_id`);

--
-- Indexes for table `user_credentials`
--
ALTER TABLE `user_credentials`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `user_id` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accounts`
--
ALTER TABLE `accounts`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `application`
--
ALTER TABLE `application`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `user_credentials`
--
ALTER TABLE `user_credentials`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `accounts`
--
ALTER TABLE `accounts`
  ADD CONSTRAINT `FKn6x8pdp50os8bq5rbb792upse` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `fk_user_customer` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
  ADD CONSTRAINT `fk_user_role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);

--
-- Constraints for table `user_credentials`
--
ALTER TABLE `user_credentials`
  ADD CONSTRAINT `fk_credentials_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
