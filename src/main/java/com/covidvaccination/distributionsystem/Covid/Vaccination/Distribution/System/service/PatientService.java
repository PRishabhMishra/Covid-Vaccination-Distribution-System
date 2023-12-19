package com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.service;

import ch.qos.logback.core.util.PropertySetterException;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.dto.request.PatientLoginDTO;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.dto.request.PatientSignupDTO;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.exceptions.PatientDoesNotExistException;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.exceptions.WrongCredentials;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.models.Patient;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.repository.PatientRepository;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@ToString
@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;

    public Patient signUp(PatientSignupDTO patientSignupDTO){
        Patient patient = new Patient();
        patient.setName(patientSignupDTO.getName());
        patient.setGender(patientSignupDTO.getGender());
        patient.setEmail(patientSignupDTO.getEmail());
        patient.setAddress(patientSignupDTO.getAddress());
        patient.setAadharNumber(patientSignupDTO.getAadharNumber());
        patient.setPassword(patientSignupDTO.getPassword());
        patient.setPhoneNumber(patientSignupDTO.getPhoneNumber());
        patient.setVaccinationPreference(patientSignupDTO.getVaccinationPreference().toString());
        patientRepository.save(patient);
        return patient;
    }

    public Patient login(PatientLoginDTO patientLoginDTO){
        Patient patient = patientRepository.getPatientBYEmail(patientLoginDTO.getEmail());
        if(patient == null){
            throw new PatientDoesNotExistException("Patient email Id is not registered in our portal.");
        }
        if(!patient.getPassword().equals(patientLoginDTO.getPassword())){
            throw new WrongCredentials("Patient Entered Wrong Password.");
        }
        return patient;
    }
}
