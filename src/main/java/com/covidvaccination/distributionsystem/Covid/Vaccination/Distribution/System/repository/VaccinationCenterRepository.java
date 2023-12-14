package com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.repository;

import ch.qos.logback.classic.pattern.LineSeparatorConverter;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.models.VaccinationCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface VaccinationCenterRepository extends JpaRepository<VaccinationCenter, UUID> {
    @Query(value = "select * from vaccination_center where doctor_count = (select min(doctor_count) from vaccination_center)",nativeQuery = true)
    public List<VaccinationCenter>getMinimumDoctorVaccinationCenter();


}