# Bagian D — Analisis Desain & Ekosistem Java

## 1. Ekosistem Java

JDK, JRE, dan JVM bisa dibayangkan sebagai perlengkapan untuk menonton film. JVM adalah pemutar yang membaca format film tertentu dan menjalankannya; dalam Java, JVM menjalankan bytecode. JRE adalah satu paket untuk penonton: pemutar JVM beserta pustaka standar dan berkas runtime yang diperlukan untuk menjalankan aplikasi Java. JDK adalah studio produksinya: JRE ditambah alat untuk membuat dan memeriksa program, terutama compiler `javac`, serta alat pengembangan lain. Jadi, pengguna yang hanya menjalankan aplikasi biasanya memerlukan runtime yang cocok, sedangkan programmer memerlukan JDK untuk mengompilasi dan menguji kode.

Saat kode seperti `Main.java` dikompilasi, compiler Java menghasilkan bytecode dalam berkas `.class`. Bytecode bukan instruksi khusus Windows atau macOS; JVM pada masing-masing sistem operasi yang menerjemahkan dan menjalankannya. Inilah inti semboyan *Write Once, Run Anywhere*: program dapat dibangun sekali dan dijalankan pada berbagai sistem yang menyediakan JVM kompatibel. Namun, semboyan itu bukan jaminan tanpa syarat. Program tetap membutuhkan versi Java yang cocok, pustaka dependensi yang tersedia, dan perilaku yang kompatibel. Penggunaan pustaka native atau asumsi khusus sistem operasi juga dapat mengurangi portabilitas.

JDK 17 layak dipilih untuk tugas ini karena merupakan rilis LTS (*Long-Term Support*) yang lebih modern dan masih mendapat dukungan lebih panjang dibanding JDK 8 yang jauh lebih lama. Lingkungan Java 17 juga memberi akses ke peningkatan runtime, keamanan, dan fitur bahasa yang dirilis setelah Java 8. Kode dalam proyek ini sederhana dan tidak memakai fitur khusus Java 17; alasan pemilihannya lebih kepada memakai lingkungan pembelajaran yang lebih baru dan didukung, bukan karena program tidak bisa berjalan di Java 8. Dalam penggunaan nyata, versi JDK sebaiknya tetap mengikuti persyaratan mata kuliah dan lingkungan tempat aplikasi akan dijalankan.

## 2. Encapsulation

Di `Character.java`, atribut seperti `id`, `name`, `type`, `level`, `hitPoints`, `attackPower`, dan `mana` dibuat `private`. Dengan begitu, kode dari luar kelas tidak dapat mengganti isi karakter secara langsung tanpa melewati aturan yang disediakan kelas. Jika atribut dibuat `public`, pemanggil bisa, misalnya, menetapkan `mana` menjadi angka negatif atau mengubah `level` tanpa memperbarui HP dan attack. Akibatnya, data objek dapat berada pada kondisi yang tidak masuk akal dan setiap bagian program harus mengingat sendiri aturan validasinya.

Kelas ini menyediakan getter dan setter sebagai pintu akses yang terkontrol. `setMana(int mana)` menolak nilai negatif dengan melempar `IllegalArgumentException`, sedangkan `getMana()` memberikan nilai mana tanpa membuka atributnya. `setLevel(int level)` juga menolak level di bawah satu. Setelah level valid diubah, method itu memanggil `calculateBaseHP()` dan `calculateBaseAttack()` dari dalam kelas untuk memperbarui statistik yang bergantung pada level. Menempatkan perhitungan di dalam `Character` menjaga aturan tersebut pada satu tempat: pemanggil cukup mengatakan bahwa level berubah, sementara kelas menjaga konsistensi HP dan attack. Jika pemanggil harus menghitung sendiri, setiap pemanggil bisa memakai rumus berbeda atau lupa memperbarui salah satunya.

## 3. Dua constructor dan enum

Constructor pertama, `Character(String name, CharacterType type, int level)`, cocok untuk membuat karakter baru. Constructor ini mengisi nama, jenis, dan level, lalu menghitung HP dasar dan attack dari level. Contohnya terlihat di `Main.java` saat objek `hero`, `wizard`, `scout`, dan `guardian` dibuat. Mana belum diberikan pada constructor ini, sehingga nilai awalnya mengikuti nilai default `int`, yaitu nol; `guardian` kemudian diberi mana melalui `setMana(30)`.

Constructor kedua, `Character(int id, String name, CharacterType type, int level, int hitPoints, int attackPower)`, ditujukan untuk membuat objek dari data yang sudah tersimpan, misalnya hasil pemuatan dari database. Karena data statistik sudah disediakan, constructor ini menetapkan `id`, HP, dan attack yang diterimanya alih-alih menghitung statistik baru. Kedua constructor melayani konteks pembuatan yang berbeda.

`CharacterType` berupa enum dengan nilai `WARRIOR`, `MAGE`, `ARCHER`, dan `HEALER`, bukan `String` bebas. Enum membatasi jenis pada pilihan yang dikenal compiler dan memudahkan perbandingan nilai secara konsisten. Jika memakai string, salah ketik seperti `"WARRIORR"` tetap bisa tersimpan sebagai data dan mungkin membuat logika yang mencari `"WARRIOR"` tidak berjalan. Dengan enum, salah ketik nama konstanta biasanya terdeteksi saat kompilasi.

## 4. Diagram class UML sederhana

Berikut ringkasan `Character` versi proyek ini. Tanda `-` berarti `private`, sedangkan `+` berarti `public`.

| Anggota | Tipe / return type | Akses |
|---|---|---|
| `id` | `int` | `private` |
| `name` | `String` | `private final` |
| `type` | `CharacterType` | `private final` |
| `level` | `int` | `private` |
| `hitPoints` | `int` | `private` |
| `attackPower` | `int` | `private` |
| `mana` | `int` | `private` |
| `Character(String, CharacterType, int)` | constructor | `public` |
| `Character(int, String, CharacterType, int, int, int)` | constructor | `public` |
| `calculateBaseHP()` | `int` | `private` |
| `calculateBaseAttack()` | `int` | `private` |
| `getId() : int` | — | `public` |
| `getName() : String` | — | `public` |
| `getType() : CharacterType` | — | `public` |
| `getLevel() : int` | — | `public` |
| `getHitPoints() : int` | — | `public` |
| `getAttackPower() : int` | — | `public` |
| `getMana() : int` | — | `public` |
| `setId(int) : void` | — | `public` |
| `setMana(int) : void` | — | `public` |
| `setLevel(int) : void` | — | `public` |
| `isAlive() : boolean` | — | `public` |
| `toString() : String` | — | `public` |

`isAlive()` mengembalikan `true` apabila `hitPoints > 0`. Pada contoh `guardian` di `Main.java`, HP hasil perhitungan level adalah 120, maka output `guardian.isAlive()` adalah `true`. `toString()` juga menyertakan `mana`, sehingga representasi karakter yang dicetak memuat statistik tersebut.

## 5. Refleksi: menambah NECROMANCER

Untuk menambah jenis `NECROMANCER` pada desain saat ini, langkah pertama adalah menambahkan konstanta `NECROMANCER` pada `CharacterType.java`. Setelah itu, karakter baru dapat dibuat menggunakan `CharacterType.NECROMANCER`. Jika daftar jenis karakter ditampilkan pada antarmuka, pilihan itu juga perlu dimasukkan di sana; jika aturan permainan memberi necromancer statistik atau kemampuan khusus, aturan tersebut harus ditambahkan dan diuji di bagian kode yang bertanggung jawab. Saat ini, rumus HP dan attack dalam `Character` hanya bergantung pada level, jadi penambahan konstanta enum saja belum memberi statistik khusus untuk jenis baru.

Desain ini mendukung penambahan jenis dengan cukup sederhana, tetapi belum sepenuhnya memenuhi prinsip *open for extension, closed for modification* (OCP). Untuk menambah pilihan enum, `CharacterType.java` tetap harus diubah. Jika aturan khusus setiap jenis kelak ditulis sebagai banyak percabangan `switch` di dalam `Character`, setiap jenis baru juga akan memaksa perubahan pada kelas tersebut. Untuk aplikasi kecil tugas ini, enum sederhana mudah dipahami dan memadai. Jika variasi perilaku makin sering bertambah, perilaku khusus dapat dipisahkan ke strategi atau kelas-kelas turunan sehingga fitur baru lebih banyak ditambahkan sebagai ekstensi daripada dengan mengubah logika yang sudah ada.
