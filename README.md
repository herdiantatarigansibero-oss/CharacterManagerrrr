# Character Manager — TP3 (Java Visual SIH-6406)

Proyek ini membangun aplikasi Java sederhana untuk mengelola karakter game, termasuk tipe karakter dan statistik seperti level, HP, attack, serta mana. Implementasinya menerapkan encapsulation dengan atribut `private`, getter dan setter tervalidasi, dua constructor, enum `CharacterType`, serta method `isAlive()` untuk memeriksa status hidup karakter.

## Analisis Bagian D

- **Google Docs Bagian D:** https://docs.google.com/document/d/1YKmga7Uj2yRrgGlOR-6NpUlERtDJRzGhGCnn6Wq2eZU/edit
- **Salinan Markdown:** [docs/Analisis-Bagian-D.md](./docs/Analisis-Bagian-D.md)

> Jika Google Docs meminta izin akses, buka dokumen dengan akun yang diberi akses atau minta pemilik dokumen mengubah pengaturan berbagi.

## Screenshot Output

Berikut bukti output program di terminal, termasuk hasil `isAlive()`:

![Output terminal Character Manager, termasuk hasil isAlive()](./Screenshot%202026-11-10%20034432.png)

## Fitur

- Membuat karakter dengan tipe dari enum `CharacterType`.
- Menghitung HP dan attack berdasarkan level.
- Memvalidasi perubahan level dan mana.
- Menampilkan statistik karakter melalui `toString()`.
- Memeriksa apakah karakter masih hidup dengan `isAlive()`.

## Menjalankan Program

Pastikan JDK terpasang, lalu jalankan perintah berikut dari root proyek:

```shell
javac -d out src/com/unpri/charactermanager/model/CharacterType.java src/com/unpri/charactermanager/model/Character.java src/Main.java
java -cp out Main
```

## Struktur Proyek

```text
CharacterManagerrrr/
├── README.md
├── Screenshot 2026-11-10 034432.png
├── docs/
│   ├── Analisis-Bagian-D.md
│   └── terminal-output.svg
└── src/
    ├── Main.java
    └── com/
        └── unpri/
            └── charactermanager/
                └── model/
                    ├── Character.java
                    └── CharacterType.java
```

## Identitas

- **Nama:** HERDIANTA YUVENTUS TARIGAN
- **NIM:** 253303621206
