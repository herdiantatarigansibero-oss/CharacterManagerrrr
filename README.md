# Character Manager — TP3 / TP5 (Java Visual )

Proyek ini membangun aplikasi Java sederhana untuk mengelola karakter game, termasuk tipe karakter dan statistik seperti level, HP, attack, serta mana. Implementasinya menerapkan encapsulation dengan atribut `private`, getter dan setter tervalidasi, dua constructor, enum `CharacterType`, serta method `isAlive()` untuk memeriksa status hidup karakter.

## Analisis TP3 — Bagian D

- **Google Docs Bagian D:** https://docs.google.com/document/d/1YKmga7Uj2yRrgGlOR-6NpUlERtDJRzGhGCnn6Wq2eZU/edit
- **Salinan Markdown:** [docs/Analisis-Bagian-D.md](./docs/Analisis-Bagian-D.md)

## Fitur

- Mengelola koleksi karakter dengan `CharacterManager` (tambah, cari, hapus, dan hitung statistik).
- Menghitung rata-rata level karakter, mencari karakter berdasarkan tipe, dan menghitung jumlah karakter per tipe.
- Membuat karakter dengan tipe dari enum `CharacterType`.
- Menghitung HP dan attack berdasarkan level.
- Memvalidasi perubahan level dan mana.
- Menampilkan statistik karakter melalui `toString()`.
- Memeriksa apakah karakter masih hidup dengan `isAlive()`.

## TP5

- **CRUD dan validasi:** menambah, mencari, dan menghapus karakter; nama duplikat ditolak.
- **Analitik:** rata-rata level (termasuk koleksi kosong), pencarian karakter berdasarkan tipe, dan jumlah karakter per tipe.
- **Eksperimen reference:** menunjukkan bahwa dua variabel dapat menunjuk objek yang sama, sementara penambahan elemen ke salinan list tidak mengubah koleksi manager.
- **Fitur analitik Bagian C:** `CharacterManager.countByType(CharacterType)` menghitung langsung tanpa mengekspos ArrayList internal. Rancangannya menjaga encapsulation sekaligus menyediakan statistik per tipe.
- **Google Docs Bagian D TP5:** [JavaVisual_TP05_253303621206_HERDIANTA YUVENTUS TARIGAN](https://docs.google.com/document/d/1FqPyn-bHtHbp-G_ItmMZ2uDQhRkKlO8_5oDj8rdXAto/edit?tab=t.0).
- **Salinan analisis lokal (lebih dari 500 kata):** [docs/Analisis-TP5-Bagian-D.md](./docs/Analisis-TP5-Bagian-D.md).

Analisis Bagian D membahas reference objek, perbedaan dengan primitive, rancangan counter static, `equals()` untuk nama duplikat, serta risiko membuka reference ke list internal. `totalCharacterCreated` belum ada dalam `Character.java`, sehingga counter dijelaskan sebagai rancangan sesuai pertanyaan, bukan fitur yang telah diimplementasikan.

Screenshot berikut memperlihatkan output CRUD dan eksperimen reference:

![Screenshot output CRUD dan eksperimen object reference](./Screenshot%202026-11-10%20043128.png)

## Screenshot Output TP3

Berikut screenshot terminal versi sebelumnya, termasuk hasil `isAlive()`:

![Output terminal Character Manager versi TP3](./Screenshot%202026-11-10%20034432.png)

## Rasional Fitur Analitik

`CharacterManager.countByType(CharacterType)` menghitung jumlah karakter berdasarkan tipe tanpa mengembalikan atau mengekspos `ArrayList` internal. Dengan begitu, pemanggil dapat memperoleh statistik tipe sementara pengelolaan koleksi tetap menjadi tanggung jawab `CharacterManager`. Method menolak nilai tipe `null` dengan `NullPointerException` yang menyertakan pesan yang jelas.

## Menjalankan Program

Pastikan JDK terpasang, lalu jalankan perintah berikut dari root proyek:

```shell
javac -d out src/com/unpri/charactermanager/model/CharacterType.java src/com/unpri/charactermanager/model/Character.java src/com/unpri/charactermanager/model/CharacterManager.java src/Main.java
java -cp out Main
```

## Struktur Proyek

```text
CharacterManagerrrr/
├── README.md
├── Screenshot 2026-11-10 034432.png
├── Screenshot 2026-11-10 043128.png
├── docs/
│   ├── Analisis-Bagian-D.md
│   ├── Analisis-TP5-Bagian-D.md
│   └── terminal-output.svg
└── src/
    ├── Main.java
    └── com/
        └── unpri/
            └── charactermanager/
                └── model/
                    ├── Character.java
                    ├── CharacterManager.java
                    └── CharacterType.java
```

## Identitas

- **Nama:** HERDIANTA YUVENTUS TARIGAN
- **NIM:** 253303621206
