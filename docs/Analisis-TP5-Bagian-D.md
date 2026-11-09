# Bagian D — Analisis Reference dan Rancangan

**Nama:** HERDIANTA YUVENTUS TARIGAN

**NIM:** 253303621206

**Kelas:** SIH-6406

**Tanggal:** 10 November 2026

## 1. Reference dan salinan objek

Misalnya dibuat `Character a = new Character("Arjuna", CharacterType.WARRIOR, 5);`, lalu ditulis `Character b = a;`. Variabel `a` dan `b` bukan dua objek karakter. Keduanya menyimpan reference yang menunjuk ke satu objek `Character` yang sama. Karena itu, ketika `b.setLevel(8)` dipanggil, setter mengubah objek yang ditunjuk keduanya. `a.getLevel()` kemudian mencetak `8`, bukan level awal `5`. Ekspresi `a == b` mencetak `true`, sebab operator `==` untuk dua reference memeriksa apakah keduanya menunjuk ke objek yang sama.

Hal ini dapat dibuktikan dengan setter di `Character.java`: `setLevel` mengubah `level` pada objek dan menghitung ulang HP serta attack. Eksperimen di `Main.java` juga menerapkan pola yang sama: `duplikatReferensi` diberi nilai dari `asli`, lalu level diubah melalui variabel pertama itu. Nilai yang dibaca lewat `asli` ikut berubah, dan perbandingan `asli == duplikatReferensi` bernilai benar.

Agar `b` independen, program harus membuat objek baru, bukan hanya menyalin reference. Untuk data karakter pada contoh ini dapat ditulis `Character b = new Character(a.getName(), a.getType(), a.getLevel());`. Sesudah itu `a == b` bernilai `false`; perubahan level di `b` tidak mengubah level di `a`. Class saat ini belum memiliki copy constructor, jadi membuat objek baru lewat constructor yang tersedia adalah cara langsung. Jika kedua objek perlu menyalin seluruh status, termasuk mana dan id, copy constructor khusus dapat ditambahkan dengan aturan penyalinan yang jelas.

## 2. Analogi reference dan primitive

Bayangkan satu catatan inventaris tersimpan di sebuah lemari arsip, sedangkan reference adalah nomor lokasi laci yang diberikan kepada beberapa petugas. Jika dua petugas memegang nomor laci yang sama, perubahan pada catatan yang dibuat petugas pertama dapat dibaca petugas kedua: mereka mengakses benda yang sama melalui reference berbeda. Menyalin nomor lokasi tidak menggandakan catatan.

Primitive seperti `int` bekerja berbeda. Menulis `int x = 5; int y = x;` menyalin nilainya, sehingga `y` mendapat angka `5` sendiri. Jika kemudian `y = 8`, nilai `x` tetap `5`. Untuk objek, penugasan `b = a` menyalin nilai reference, bukan menggandakan objek yang dituju. Pembedaan inilah yang menjelaskan mengapa perubahan melalui `b` terlihat melalui `a`.

## 3. Alasan counter dibuat static

`totalCharacterCreated` sebaiknya `static` jika maksudnya menghitung jumlah objek `Character` yang dibuat oleh seluruh aplikasi. Field static dimiliki oleh class, bukan oleh satu objek, sehingga semua instance membaca dan memperbarui satu nilai bersama. Constructor dapat menaikkan counter setiap kali objek baru dibuat, dan accessor static dapat melaporkan jumlah total tersebut.

Penting dicatat bahwa `Character.java` dalam proyek saat ini belum mendefinisikan `totalCharacterCreated`. Jadi, penjelasan ini adalah rancangan untuk counter yang disebut dalam soal, bukan klaim bahwa fitur itu sudah ada. Sebagai pembanding di kode aktual, `id`, `name`, `type`, dan `level` adalah field instance. Nilai `level` Arjuna tidak menjadi nilai level Merlin. Jika counter total dibuat non-static, setiap `Character` akan memiliki penghitungnya sendiri. Hasilnya bukan satu jumlah global: objek baru biasanya mulai dengan nilai default sendiri, dan membaca counter memerlukan instance tertentu. Informasi agregat itu menjadi terpecah dan tidak menjawab berapa objek yang telah dibuat secara keseluruhan.

## 4. Kesetaraan objek dan duplikat nama

Implementasi bawaan `Object.equals()` menganggap dua objek sama hanya jika keduanya adalah instance yang sama. Dua pemanggilan constructor dengan nama `"Arjuna"` membuat dua objek berbeda, sehingga tanpa override, `equals()` akan menganggap keduanya berbeda. Pengecekan duplikat di `CharacterManager.addCharacter()` menggunakan `characterList.contains(character)`, dan `contains` bergantung pada `equals()`. Akibatnya, pemeriksaan duplikat nama tidak akan berhasil jika identitas objek saja yang dibandingkan.

Solusinya di proyek ini adalah override `equals()` pada `Character` agar membandingkan `name` dengan `Objects.equals`, dan override `hashCode()` berdasarkan nama yang sama. Dengan definisi tersebut, karakter dengan nama sama dianggap setara meskipun reference-nya berbeda. `addCharacter()` kemudian dapat menolak duplikat melalui `contains`. Kebijakan ini case-sensitive: `"Arjuna"` dan `"arjuna"` tetap dianggap nama berbeda. Konsistensi `equals()` dan `hashCode()` juga penting agar koleksi berbasis hash dapat berperilaku benar.

## 5. Risiko mengembalikan ArrayList asli

Jika `getAllCharacters()` mengembalikan `characterList` asli, pemanggil akan menerima reference ke koleksi internal milik manager. Ia dapat menambahkan karakter tanpa melewati `addCharacter()`, menghapus item tanpa aturan manager, atau mengosongkan seluruh daftar. Validasi nama duplikat dan kontrol perubahan koleksi menjadi mudah dilewati. Manager tidak lagi menjadi satu-satunya pihak yang menjaga keadaan koleksi.

`CharacterManager.getCharacters()` saat ini mengembalikan `new ArrayList<>(characterList)`, dan `getAllCharacters()` mendelegasikan ke method tersebut. Jadi, struktur list yang diterima pemanggil adalah salinan. Pada eksperimen `Main.java`, menambahkan `"Hantu"` ke `salinanList` menaikkan ukuran list salinan, tetapi tidak mengubah jumlah karakter manager. Ini adalah perlindungan terhadap reference list asli.

Namun salinan itu dangkal (*shallow copy*): elemen `Character` di dalamnya masih reference ke objek karakter yang sama. Karena itu, memanggil `salinanList.get(0).setLevel(...)` tetap bisa mengubah objek yang dikelola manager. Eksperimen reference sebelumnya memperlihatkan prinsip tersebut. Jika manager juga harus melindungi keadaan objek karakter, ia perlu mengembalikan salinan objek, objek immutable, atau menyediakan operasi perubahan melalui method manager. Kedalaman salinan yang dibutuhkan bergantung pada kontrak API.
