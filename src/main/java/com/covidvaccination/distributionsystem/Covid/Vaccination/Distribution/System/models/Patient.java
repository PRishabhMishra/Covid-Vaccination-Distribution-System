package com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    String name;

    String gender;

    @Column(unique = true)
    int aadharNumber;

    int doseCount;

    String vaccinationPreference;

    String address;

    @Column(unique = true)
    long phoneNumber;

    @Column(unique = true)
    String email;

    public Patient(UUID id, String name, String gender, int aadharNumber, int doseCount, String vaccinationPreference, String address, long phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.aadharNumber = aadharNumber;
        this.doseCount = doseCount;
        this.vaccinationPreference = vaccinationPreference;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Patient() {
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAadharNumber() {
        return aadharNumber;
    }

    public int getDoseCount() {
        return doseCount;
    }

    public String getVaccinationPreference() {
        return vaccinationPreference;
    }

    public String getAddress() {
        return address;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAadharNumber(int aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public void setDoseCount(int doseCount) {
        this.doseCount = doseCount;
    }

    public void setVaccinationPreference(String vaccinationPreference) {
        this.vaccinationPreference = vaccinationPreference;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
