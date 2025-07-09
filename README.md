# JAVA NETBEANS PROJECTS 🎒

**Aplikasi Sistem Pendataan Keluarga Berencana (KB)**  
Mata Kuliah: Pemrograman Visual  
Program Studi: Teknik Informatika

---

## 📌 Deskripsi

Project ini merupakan aplikasi desktop berbasis Java (Swing) yang digunakan untuk mendata layanan Keluarga Berencana (KB).  
Dibangun menggunakan NetBeans dan MySQL, aplikasi ini menyimpan dan mengelola data peserta KB, keluarga, jenis layanan, serta transaksi pelayanan.

---

## 🧩 Fitur Utama

1.**🔐 Login Admin**
 Sistem  autentifikasi pengguna untuk  meningkatkan keamanan dan mengoptimalkan pengelolaan akses.
 
2.**🧾 Form Master**
  -Form Data Peserta KB : Manajemen Data Peserta KB.
  -Form Data Keluarga : Manajemen Data keluarga.
  -Form Data Jenis Layanan : Manajemen Data Jenis Layanan KB yang tersedia.
  -Form Data Petugas Kesehatan : Manajemen Petugas Data Petugas Kesehatan.
  -Form Data Pemeriksaan Peserta : Pencatatan Kesehatan Peserta KB.
  
3.**📊 Transaksi**
  - Form Pendaftaraan Peserta KB : Pencatatan Peserta yang mendaftar Program KB (keluarga berencana).
  - Form Pembayaran Jenis Layanan KB : Pencatatan Pembayaran Jenis Layanan  KB yang digunakan.
  - 
4.**🖨️ Laporan**
  - Laporan Peserta KB
  - Laporan Pemakaian Alat
  - Laporan Pemeriksaan Rutin
  - Laporan Rekapitulasi
  
---

## Cara Menjalankan Aplikasi di Java NetBeans

1. *Persiapan*
   - Pastikan Java Development Kit (JDK) 23 atau 24 sudah terpasang di komputer Anda.
   - Install NetBeans IDE (disarankan versi terbaru 25 atau 26).

2. *Clone atau Download Repository*
   - Clone repository ini atau download source code-nya.

3. *Buka Project di NetBeans*
   - Buka NetBeans IDE.
   - Pilih menu File > Open Project.
   - Arahkan ke folder hasil clone/download repository ini, lalu pilih project.

4. *Setup Database*
   - Buat database dengan nama 1pemvis_tugasakhir menggunakan MySQL atau database server localhost/phpmyadmin.
   - Import file SQL yang berada pada folder ini kemudian cari file bernama (1pemvis_tugasakhir.sql) untuk membuat struktur dan data awal database.
   - Pastikan konfigurasi koneksi database pada aplikasi sudah sesuai dengan host, user, password, dan nama database.

5. *Install Library Pendukung*
   - Install library JasperReports dan iReport untuk kebutuhan cetak laporan.
   - JasperReports: [Download di sini](https://community.jaspersoft.com/project/jasperreports-library)
   - iReport: [Download di sini](https://community.jaspersoft.com/project/ireport-designer)
   - Tambahkan library JasperReports ke dalam libraries project di NetBeans (klik kanan pada Libraries → Add JAR/Folder).

6. *Jalankan Project*
   - Klik kanan pada nama project di NetBeans, lalu pilih Run Project.
   - Login dengan akun yang sudah terdaftar atau buat akun baru jika diperlukan.

---

## 🛠️ Teknologi yang Digunakan

- ☕ Java (Swing GUI)
- 🧰 NetBeans IDE
- 🗃️ MySQL (XAMPP)
- 📊 JasperReports

---
