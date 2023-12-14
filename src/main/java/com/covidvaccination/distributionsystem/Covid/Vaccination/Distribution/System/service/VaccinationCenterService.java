package com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.service;

import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.models.VaccinationCenter;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccinationCenterService {
    @Autowired
    VaccinationCenterRepository vaccinationCenterRepository;

    public VaccinationCenter registerVaccinationCenter(VaccinationCenter vaccinationCenter){
        vaccinationCenterRepository.save(vaccinationCenter);
        return vaccinationCenter;
    }


    public List<VaccinationCenter> getMinimumDoctorCountVC(){
        List<VaccinationCenter>vaccinationCenters = vaccinationCenterRepository.getMinimumDoctorVaccinationCenter();
        return vaccinationCenters;

    }
}
