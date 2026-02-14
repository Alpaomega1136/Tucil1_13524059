# Tugas Kecil 1 IF2211 Strategi Algoritma (2025/2026)
## Permainan Queens Puzzle

### Deskripsi Singkat
Program ini adalah aplikasi desktop berbasis Java (GUI menggunakan JavaFX) yang bertujuan untuk menyelesaikan permainan logika **"Queens"** (seperti yang ada di [LinkedIn](https://www.linkedin.com/help/linkedin/answer/a6269510)).

Permainan "Queens" merupakan permainan dimana pemain meletakkan ratu pada area yang diberikan. Solusi valid harus memenuhi syarat:
1.  Hanya ada **1 Ratu per baris**.
2.  Hanya ada **1 Ratu per kolom**.
3.  Hanya ada **1 Ratu per wilayah warna**.
4.  Tidak ada Ratu yang bersentuhan (termasuk secara diagonal).

Program ini menggunakan pencarian semua kemungkinan solusi yang ada dengan paradigma **Brute-Force**, atau menampilkan bahwa tidak ada solusi untuk area yang diberikan.

---

### Requirement & Instalasi
Untuk menjalankan program ini, pastikan perangkat Anda memiliki:

1.  **Java Development Kit (JDK)**
    * Versi: **JDK 21** atau terbaru.
    * Cek versi: `java --version`
2.  **Apache Maven**
    * Digunakan untuk build otomatis dan mengelola library JavaFX.
    * Cek versi: `mvn -version`

#### 📥 Panduan Instalasi Maven

**Untuk Windows:**
1.  Unduh **Binary zip archive** dari situs resmi: [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi).
2.  Ekstrak file zip tersebut ke folder yang aman
3.  Buka **Start Menu**, cari *"Edit the system environment variables"*, lalu tekan Enter.
4.  Klik tombol **Environment Variables**.
5.  Di bagian **System variables**, cari variabel bernama **Path**, lalu klik **Edit**.
6.  Klik **New**, lalu tempelkan alamat folder `bin` dari Maven yang sudah diekstrak tadi (contoh: `C:\Program Files\Maven\apache-maven-3.9.6\bin`).
7.  Klik OK di semua jendela.
8.  Buka CMD baru, ketik `mvn -version` untuk memverifikasi.

**Untuk Linux:**
1.  Buka Terminal.
2.  Jalankan perintah berikut:
    ```bash
    sudo apt update
    sudo apt install maven
    ```
3.  Verifikasi instalasi dengan mengetik: `mvn -version`.

---

### Cara Mengkompilasi Program
Program ini dapat dikompilasi menggunakan dua cara: **Maven (Otomatis)** atau **Manual**.

#### Opsi 1: Menggunakan Maven (Disarankan)
> Cara ini otomatis mengunduh library JavaFX yang dibutuhkan.

1.  Buka terminal/CMD di folder root proyek `Tucil1_13524059`.
2.  Jalankan perintah:
    ```bash
    mvn clean compile
    ```

#### Opsi 2: Kompilasi Manual (Tanpa Maven)
*Prasyarat: Anda harus memiliki JavaFX SDK yang sudah diunduh.*

**Windows:**
```cmd
mkdir bin
javac --module-path "C:\Path\To\JavaFX\lib" --add-modules javafx.controls,javafx.fxml,javafx.swing -d bin src\main\java\com\tucil1\*.java
```
**Linux:**
```cmd
mkdir -p bin
javac --module-path "/path/to/javafx/lib" --add-modules javafx.controls,javafx.fxml,javafx.swing -d bin src/main/java/com/tucil1/*.java
```

### Cara Menjalankan Program (Run)
#### Opsi 1: Menggunakan Maven (Windows & Linux)
Pastikan berada di folder root proyek, lalu jalankan:
```cmd
mvn javafx:run
```

#### Opsi 2: Menjalankan Manual (Dari folder bin)
Gunakan perintah ini jika Anda telah melakukan kompilasi manual.
**Windows:**
```cmd
java --module-path "C:\Path\To\JavaFX\lib" --add-modules javafx.controls,javafx.fxml,javafx.swing -cp bin com.tucil1.App
```

**Linux**
```cmd
java --module-path "/path/to/javafx/lib" --add-modules javafx.controls,javafx.fxml,javafx.swing -cp bin com.tucil1.App
```

### Cara Menggunakan Program
1. Input Papan:

    - Klik tombol 📂 Load File untuk memilih file .txt dari folder test/.

    - ATAU ketik langsung konfigurasi papan pada area input teks.

    Format: Huruf rapat (contoh: AABB) atau dipisah spasi.

2. Mencari Solusi:

    - Klik tombol ▶ SOLVE QUEENS.

    - Program akan memvisualisasikan proses pencarian.

    - Status akan berubah menjadi "Solusi Ditemukan!" atau "Solusi Tidak Ditemukan".

3. Menyimpan Solusi:

    - Jika solusi ditemukan, panel penyimpanan akan muncul di bawah papan.

    - Masukkan nama file (tanpa ekstensi).

    - Klik Save .TXT untuk menyimpan representasi teks.

    - Klik Save .PNG untuk menyimpan gambar papan solusi.

    - File output akan otomatis tersimpan di dalam folder test/.

### Identitas Pembuat
- Nama: Raymond Jonathan Dwi Putra Julianto

- NIM: 13524059

- Kelas: K-01