package com.unpri.charactermanager.model;

import java.util.Objects;

/**
 * Merepresentasikan satu karakter dalam aplikasi Character Manager.
 * Menerapkan prinsip Encapsulation: semua atribut private,
 * akses hanya melalui getter/setter.
 */
public   class Character {

    // ===== ATRIBUT (private — tidak bisa diakses langsung dari luar class) =====
    private int id;
    private final String name;
    private final CharacterType type;
    private int level;
    private int hitPoints;
    private int attackPower;
    private int mana;

    // ===== CONSTRUCTOR 1: untuk membuat karakter BARU =====
    // Dipakai saat user membuat karakter dari form (belum punya id dari database)
    public Character(String name, CharacterType type, int level) {
        this.name = name;              // "this" merujuk ke atribut milik object ini
        this.type = type;
        this.level = level;
        this.hitPoints = calculateBaseHP();      // HP dihitung otomatis dari level
        this.attackPower = calculateBaseAttack(); // Attack dihitung otomatis dari level
    }

    // ===== CONSTRUCTOR 2: untuk MEMUAT karakter dari database =====
    // Dipakai saat data sudah ada (id dan statistik sudah final dari DB)
    public Character(int id, String name, CharacterType type, int level, int hitPoints, int attackPower) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.level = level;
        this.hitPoints = hitPoints;
        this.attackPower = attackPower;
    }

    // ===== HELPER PRIVATE: hanya dipakai internal class ini =====
    private int calculateBaseHP() {
        return 100 + (level * 10);   // contoh: level 5 → HP = 150
    }

    private int calculateBaseAttack() {
        return 10 + (level * 5);     // contoh: level 5 → Attack = 35
    }

    // ===== GETTER: cara AMAN membaca nilai atribut private dari luar =====
    public int getId() { return id; }
    public String getName() { return name; }
    public CharacterType getType() { return type; }
    public int getLevel() { return level; }
    public int getHitPoints() { return hitPoints; }
    public int getAttackPower() { return attackPower; }
    public int getMana() { return mana; }

    // ===== SETTER: cara AMAN mengubah atribut, dengan VALIDASI =====
    public void setId(int id) {
        this.id = id;
    }

    public void setMana(int mana) {
        if (mana < 0) {
            throw new IllegalArgumentException("Mana tidak boleh negatif");
        }
        this.mana = mana;
    }

    public void setLevel(int level) {
        if (level < 1) {                       // validasi: level tidak boleh kurang dari 1
            throw new IllegalArgumentException("Level minimal adalah 1");
        }
        this.level = level;
        this.hitPoints = calculateBaseHP();      // statistik dihitung ULANG setiap level berubah
        this.attackPower = calculateBaseAttack();
    }

    public boolean isAlive() {
        return hitPoints > 0;
    }

    // ===== toString(): representasi teks object, berguna untuk debugging =====
    @Override
    public String toString() {
        return "Character{id=" + id +
               ", name='" + name + '\'' +
               ", type=" + type +
               ", level=" + level +
               ", hp=" + hitPoints +
               ", atk=" + attackPower +
               ", mana=" + mana + '}';
    }

    // ===== equals(): membandingkan isi data, bukan alamat memori =====
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Character)) return false;
        Character other = (Character) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
