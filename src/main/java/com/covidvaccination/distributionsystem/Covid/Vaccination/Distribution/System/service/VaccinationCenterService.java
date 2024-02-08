package com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.service;

import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.models.VaccinationCenter;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public void updateDocCountByOne(VaccinationCenter vaccinationCenter) {
        UUID id = vaccinationCenter.getId();
        int docCount = vaccinationCenter.getDoctorCount() + 1;
        vaccinationCenterRepository.updateDocCountByOne(id, docCount);

    }

    public void updatePatientCountByOne(VaccinationCenter vaccinationCenter){
        UUID id  = vaccinationCenter.getId();
        int patientCount = vaccinationCenter.getPatientCount()+1;
        vaccinationCenterRepository.updatePatientCountByOne(patientCount,id);
    }
    public List<VaccinationCenter>getMinimumVCOnTheBasisOfTypeAndPreference(String type,String  preference){
        if(preference.equals("Sputnik")){
            return vaccinationCenterRepository.getAllVCOntheBasisOfTypeAndSputnikCount(type);
        }else if(preference.equals("Covishield")){
            return vaccinationCenterRepository.getAllVCOntheBasisOfTypeAndCovishieldCount(type);
        }else{
            return vaccinationCenterRepository.getAllVCOntheBasisOfTypeAndCovaxinCount(type);
        }

        }


    }

