package com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.repository;

import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface DoctorRepository extends JpaRepository<Doctor,UUID> {
}
