package com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.repository;

import ch.qos.logback.classic.pattern.LineSeparatorConverter;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.models.VaccinationCenter;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface VaccinationCenterRepository extends JpaRepository<VaccinationCenter, UUID> {
    @Query(value = "select * from vaccination_center where doctor_count = (select min(doctor_count) from vaccination_center)",nativeQuery = true)
    public List<VaccinationCenter>getMinimumDoctorVaccinationCenter();

    @Query(value = "select * from vaccination_center where type =:type and covishield_count !=0 and patient_count = (select min(patient_count) from vaccination_center)",nativeQuery = true)
    public List<VaccinationCenter>getAllVCOntheBasisOfTypeAndCovishieldCount(String type);

    @Query(value = "select * from vaccination_center where type = :type and covaxin_count !=0 and patient_count = (select min(patient_count) from vaccination_center)",nativeQuery = true)
    public List<VaccinationCenter>getAllVCOntheBasisOfTypeAndCovaxinCount(String type);

    @Query(value ="select * from vaccination_center where type = :type and sputnik_count !=0 and patient_count = (select min(patient_count) from vaccination_center)",nativeQuery = true )
    public List<VaccinationCenter>getAllVCOntheBasisOfTypeAndSputnikCount(String type);


    @Modifying
    @Transactional
    @Query(value = "update vaccination_center set patient_count =:patientCount where id =:vcid",nativeQuery = true)
    public void updatePatientCountByOne(int patientCount,UUID vcid);

    @Modifying
    @Transactional
    @Query(value = "update vaccination_center set doctor_count =:docCount where id =:id",nativeQuery = true)
    public  void updateDocCountByOne(UUID id,int docCount);



}
