package com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.service;

import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.models.Doctor;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.models.VaccinationCenter;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    VaccinationCenterService vaccinationCenterService;

    //Doctor will get assigned in that vaccination center who is having min number of doctor
    public Doctor registerDoctor(Doctor obj){
        List<VaccinationCenter>vaccinationCenterList = vaccinationCenterService.getMinimumDoctorCountVC();
        VaccinationCenter vaccinationCenter = vaccinationCenterList.get(0);
        obj.setVaccinationCenter(vaccinationCenter);
        doctorRepository.save(obj);
        return obj;


    }
}
