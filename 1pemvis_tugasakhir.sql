-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 09, 2025 at 10:22 AM
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
-- Database: `1pemvis_tugasakhir`
--

-- --------------------------------------------------------

--
-- Table structure for table `data_keluarga`
--

CREATE TABLE `data_keluarga` (
  `no_kk` varchar(100) NOT NULL,
  `kepala_keluarga` varchar(100) NOT NULL,
  `jumlah_anggota` int(11) NOT NULL,
  `pekerjaan` enum('Petani','Pedagang','Pegawai Negeri Sipil','Wiraswasta','Lainnya') NOT NULL,
  `status_ekonomi` enum('Pratama','Madya','Purnama') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `data_keluarga`
--

INSERT INTO `data_keluarga` (`no_kk`, `kepala_keluarga`, `jumlah_anggota`, `pekerjaan`, `status_ekonomi`) VALUES
('186374629384573', 'Syahrul', 2, 'Wiraswasta', 'Pratama'),
('2222222233333333', 'Iradnaluw', 3, 'Pegawai Negeri Sipil', 'Purnama'),
('3175456785678909', 'Bambang', 3, 'Pegawai Negeri Sipil', 'Purnama'),
('3333333344444444', 'Inaliem', 3, 'Wiraswasta', 'Pratama'),
('371532456789098', 'Syahrul', 4, 'Wiraswasta', 'Pratama');

-- --------------------------------------------------------

--
-- Table structure for table `data_layanan`
--

CREATE TABLE `data_layanan` (
  `kode_layanan` varchar(100) NOT NULL,
  `jenis_layanan` varchar(100) NOT NULL,
  `tipe` enum('Gratis','Berbayar') NOT NULL,
  `harga_default` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `data_layanan`
--

INSERT INTO `data_layanan` (`kode_layanan`, `jenis_layanan`, `tipe`, `harga_default`) VALUES
('LYN002', 'IUD', 'Berbayar', 250000),
('LYN003', 'Implan', 'Berbayar', 274000),
('LYN004', 'Konsultasi', 'Gratis', 0),
('LYN005', 'SUNTIK KB', 'Berbayar', 200000);

-- --------------------------------------------------------

--
-- Table structure for table `data_peserta_kb`
--

CREATE TABLE `data_peserta_kb` (
  `nik` varchar(100) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `alamat` text NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `telepon` varchar(100) NOT NULL,
  `nama_suami` varchar(100) NOT NULL,
  `jumlah_anak` int(11) NOT NULL,
  `status_kb` enum('Aktif','Non-Aktif','Komplikasi') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `data_peserta_kb`
--

INSERT INTO `data_peserta_kb` (`nik`, `nama`, `alamat`, `tanggal_lahir`, `telepon`, `nama_suami`, `jumlah_anak`, `status_kb`) VALUES
('0000111122223333', 'Rosidah', 'Jakarta Selatan', '2025-07-06', '085884042545', 'Hadisor', 2, 'Aktif'),
('2222333344441111', 'Wulandari', 'Jakarta TImur', '2025-07-06', '085884042545', 'Iradnaluw', 3, 'Non-Aktif'),
('2345678923456789', 'Jennie', 'Korea', '1999-07-08', '07968857473376', 'Steven', 1, 'Aktif'),
('3214567546789034', 'Mutiara Dinda', 'Jakarta', '2003-07-03', '0879685747373', 'Syahrul', 2, 'Aktif'),
('3333444411112222', 'Meilani', 'Bogor', '2025-07-06', '085884042545', 'Inaliem', 1, 'Komplikasi'),
('4567934567345678', 'Desy Bella', 'Bogor', '2003-09-07', '097867573632', 'Zidane', 1, 'Non-Aktif'),
('5678907654325678', 'Rissa Hartanti', 'Bekasi', '2004-07-07', '09864325788', 'Syaiful', 3, 'Komplikasi'),
('6543217890876543', 'Hera Syakira', 'Depok', '2000-01-08', '0897647326272', 'Bambang', 2, 'Non-Aktif');

-- --------------------------------------------------------

--
-- Table structure for table `data_petugas`
--

CREATE TABLE `data_petugas` (
  `id_petugas` varchar(30) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `jenis_kelamin` varchar(30) NOT NULL,
  `jabatan` varchar(30) NOT NULL,
  `no_hp` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `data_petugas`
--

INSERT INTO `data_petugas` (`id_petugas`, `nama`, `jenis_kelamin`, `jabatan`, `no_hp`) VALUES
('PTG001', 'Syaiful Fahtu Rohmi', 'Laki-laki', 'Dokter', '085884042545'),
('PTG002', 'Susanti', 'Perempuan', 'Bidan', '085884042545'),
('PTG003', 'Mei-mei', 'Perempuan', 'Perawat', '085884042545');

-- --------------------------------------------------------

--
-- Table structure for table `layanan_kb`
--

CREATE TABLE `layanan_kb` (
  `idtransaksi` varchar(20) NOT NULL,
  `tanggal_layanan` date NOT NULL,
  `nik` varchar(100) NOT NULL,
  `jenis_layanan` varchar(100) NOT NULL,
  `biaya` int(11) NOT NULL,
  `id_petugas` varchar(100) NOT NULL,
  `status` varchar(20) DEFAULT 'Belum Dibayar'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `layanan_kb`
--

INSERT INTO `layanan_kb` (`idtransaksi`, `tanggal_layanan`, `nik`, `jenis_layanan`, `biaya`, `id_petugas`, `status`) VALUES
('TRX001', '2025-07-06', '0000111122223333', 'Implan', 145000, 'PTG002', 'Lunas'),
('TRX002', '2025-07-06', '2222333344441111', 'IUD', 233000, 'PTG001', 'Lunas'),
('TRX003', '2025-07-06', '3333444411112222', 'Konsultasi', 0, 'PTG003', 'Lunas'),
('TRX004', '2025-07-06', '2222333344441111', 'IUD', 233000, 'PTG003', 'Lunas'),
('TRX005', '2024-07-06', '2222333344441111', 'Suntik KB', 0, 'PTG002', 'Lunas'),
('TRX008', '2025-07-07', '3333444411112222', 'Implan', 275000, 'PTG003', 'Belum Dibayar'),
('TRX009', '2024-07-07', '0000111122223333', 'IUD', 250000, 'PTG002', 'Belum Dibayar'),
('TRX010', '2025-07-05', '6543217890876543', 'IUD', 250000, 'PTG001', 'Lunas'),
('TRX011', '2025-07-05', '6543217890876543', 'Implan', 274000, 'PTG001', 'Lunas');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `id_user` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `jenis` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `telp` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id_user`, `nama`, `jenis`, `email`, `telp`, `password`) VALUES
(1, 'Syaiful Fahtu Rohmi', 'Laki-laki', 'syaifulfr04@gmail.com', '085884042545', '12345'),
(2, 'MUHAMAD HAFIDZ', 'Laki-laki', 'Hafidz@gmail.com', '08123456778', '12345'),
(3, 'Hera Syakira', 'Perempuan', 'hera@gmail.com', '089614266104', 'her'),
(4, 'syaiful fahtu rohmi', 'Laki-laki', 'syaiful@gmail.com', '085884042545', '12345');

-- --------------------------------------------------------

--
-- Table structure for table `pemeriksaan_kb`
--

CREATE TABLE `pemeriksaan_kb` (
  `id_pemeriksaan` varchar(100) NOT NULL,
  `tanggal` date NOT NULL,
  `nik` varchar(100) NOT NULL,
  `jenis_pemeriksaan` varchar(100) NOT NULL,
  `berat_badan` double NOT NULL,
  `tekanan_darah` varchar(100) NOT NULL,
  `catatan` text NOT NULL,
  `id_petugas` varchar(100) NOT NULL,
  `status_pemeriksaan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pemeriksaan_kb`
--

INSERT INTO `pemeriksaan_kb` (`id_pemeriksaan`, `tanggal`, `nik`, `jenis_pemeriksaan`, `berat_badan`, `tekanan_darah`, `catatan`, `id_petugas`, `status_pemeriksaan`) VALUES
('PMR001', '2025-07-06', '0000111122223333', 'Konsultasi', 75, '115/70', '-', 'PTG001', 'Sehat'),
('PMR002', '2025-07-06', '3333444411112222', 'Lainnya', 90, '145/90', 'Pusing', 'PTG002', 'Perlu Rujukan');

-- --------------------------------------------------------

--
-- Table structure for table `pendaftaran_kb`
--

CREATE TABLE `pendaftaran_kb` (
  `id_pendaftaran` varchar(20) NOT NULL,
  `tanggal_daftar` date NOT NULL,
  `nik` varchar(100) NOT NULL,
  `telepon` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pendaftaran_kb`
--

INSERT INTO `pendaftaran_kb` (`id_pendaftaran`, `tanggal_daftar`, `nik`, `telepon`) VALUES
('DFT001', '2025-07-06', '0000111122223333', '085884042545'),
('DFT002', '2025-07-06', '2222333344441111', '085884042545'),
('DFT003', '2025-07-06', '3333444411112222', '085884042545'),
('DFT004', '2025-07-07', '0000111122223333', '085884042545');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data_keluarga`
--
ALTER TABLE `data_keluarga`
  ADD PRIMARY KEY (`no_kk`);

--
-- Indexes for table `data_layanan`
--
ALTER TABLE `data_layanan`
  ADD PRIMARY KEY (`kode_layanan`);

--
-- Indexes for table `data_peserta_kb`
--
ALTER TABLE `data_peserta_kb`
  ADD PRIMARY KEY (`nik`);

--
-- Indexes for table `data_petugas`
--
ALTER TABLE `data_petugas`
  ADD PRIMARY KEY (`id_petugas`);

--
-- Indexes for table `layanan_kb`
--
ALTER TABLE `layanan_kb`
  ADD PRIMARY KEY (`idtransaksi`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id_user`);

--
-- Indexes for table `pemeriksaan_kb`
--
ALTER TABLE `pemeriksaan_kb`
  ADD PRIMARY KEY (`id_pemeriksaan`);

--
-- Indexes for table `pendaftaran_kb`
--
ALTER TABLE `pendaftaran_kb`
  ADD PRIMARY KEY (`id_pendaftaran`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
