package com.unpri.charactermanager.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Mengelola koleksi Character: tambah, cari, hapus, dan statistik dasar.
 * Menerapkan Composition: CharacterManager "has-a" ArrayList<Character>.
 */
public class CharacterManager {

    private final ArrayList<Character> characterList;

    public CharacterManager() {
        this.characterList = new ArrayList<>();
    }

    /**
     * Menambahkan karakter baru ke dalam list.
     * Menolak jika sudah ada karakter dengan nama yang sama.
     *
     * @return true jika berhasil ditambahkan, false jika ditolak karena duplikat
     */
    public boolean addCharacter(Character character) {
        Objects.requireNonNull(character, "Karakter tidak boleh null");

        if (characterList.contains(character)) {
            System.out.println("Gagal: karakter dengan nama '" + character.getName() + "' sudah ada!");
            return false;
        }

        characterList.add(character);
        System.out.println("Karakter '" + character.getName() + "' berhasil ditambahkan.");
        return true;
    }

    /**
     * Mencari karakter berdasarkan nama secara case-sensitive.
     *
     * @return karakter yang ditemukan, atau null jika tidak ada
     */
    public Character findByName(String name) {
        for (Character character : characterList) {
            if (Objects.equals(character.getName(), name)) {
                return character;
            }
        }
        return null;
    }

    /**
     * Menghapus karakter berdasarkan nama secara case-sensitive.
     *
     * @return true jika karakter ditemukan dan dihapus
     */
    public boolean removeByName(String name) {
        Character character = findByName(name);
        if (character == null) {
            return false;
        }
        return characterList.remove(character);
    }

    public boolean removeCharacter(String name) {
        return removeByName(name);
    }

    public int getCharacterCount() {
        return characterList.size();
    }

    public int getTotalCharacters() {
        return getCharacterCount();
    }

    public double getAverageLevel() {
        if (characterList.isEmpty()) {
            return 0;
        }

        int totalLevel = 0;
        for (Character character : characterList) {
            totalLevel += character.getLevel();
        }
        return (double) totalLevel / characterList.size();
    }

    public ArrayList<Character> findByType(CharacterType type) {
        ArrayList<Character> matchingCharacters = new ArrayList<>();
        for (Character character : characterList) {
            if (character.getType() == type) {
                matchingCharacters.add(character);
            }
        }
        return matchingCharacters;
    }

    /**
     * Menghitung jumlah karakter dengan tipe tertentu tanpa mengekspos koleksi internal.
     */
    public int countByType(CharacterType type) {
        Objects.requireNonNull(type, "Tipe karakter tidak boleh null");

        int count = 0;
        for (Character character : characterList) {
            if (character.getType() == type) {
                count++;
            }
        }
        return count;
    }

    public int getAliveCharacterCount() {
        int aliveCount = 0;
        for (Character character : characterList) {
            if (character.isAlive()) {
                aliveCount++;
            }
        }
        return aliveCount;
    }

    /**
     * Mengembalikan salinan list agar pemanggil tidak dapat mengubah koleksi internal.
     */
    public ArrayList<Character> getCharacters() {
        return new ArrayList<>(characterList);
    }

    public ArrayList<Character> getAllCharacters() {
        return getCharacters();
    }
}
