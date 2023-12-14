package com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;
@Entity
public class VaccinationCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    String name;

    String type;

    int covaxinCount;

    int covishieldCount;

    int sputnikCount;

    int patientCount;

    int doctorCount;

    String address;

    @OneToMany(mappedBy = "vaccinationCenter")
    List<Doctor> doctors;

    public VaccinationCenter() {
    }

    public VaccinationCenter(UUID id, String name, String type, int covaxinCount, int covishieldCount, int sputnikCount, int patientCount, int doctorCount, String address, List<Doctor> doctors) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.covaxinCount = covaxinCount;
        this.covishieldCount = covishieldCount;
        this.sputnikCount = sputnikCount;
        this.patientCount = patientCount;
        this.doctorCount = doctorCount;
        this.address = address;

        this.doctors = doctors;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getCovaxinCount() {
        return covaxinCount;
    }

    public int getCovishieldCount() {
        return covishieldCount;
    }

    public int getSputnikCount() {
        return sputnikCount;
    }

    public int getPatientCount() {
        return patientCount;
    }

    public int getDoctorCount() {
        return doctorCount;
    }

    public String getAddress() {
        return address;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCovaxinCount(int covaxinCount) {
        this.covaxinCount = covaxinCount;
    }

    public void setCovishieldCount(int covishieldCount) {
        this.covishieldCount = covishieldCount;
    }

    public void setSputnikCount(int sputnikCount) {
        this.sputnikCount = sputnikCount;
    }

    public void setPatientCount(int patientCount) {
        this.patientCount = patientCount;
    }

    public void setDoctorCount(int doctorCount) {
        this.doctorCount = doctorCount;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }
}
