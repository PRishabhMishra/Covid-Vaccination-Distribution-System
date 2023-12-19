package com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.controller;

import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.dto.request.PatientLoginDTO;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.dto.request.PatientSignupDTO;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.dto.response.GeneralMessageDTO;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.exceptions.PatientDoesNotExistException;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.exceptions.WrongCredentials;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.models.Patient;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody PatientSignupDTO patientSignupDTO){
        Patient patient = patientService.signUp(patientSignupDTO);
        return new ResponseEntity(patient, HttpStatus.CREATED);
    }

    @PostMapping("/login")

    public ResponseEntity login(@RequestBody PatientLoginDTO patientLoginDTO) {
        try {
            Patient patient = patientService.login(patientLoginDTO);
            return new ResponseEntity(patient,HttpStatus.OK);
        }
        catch (WrongCredentials wrongCredentials) {
            return new ResponseEntity(new GeneralMessageDTO(wrongCredentials.getMessage()),HttpStatus.UNAUTHORIZED);
        }
        catch (PatientDoesNotExistException patientDoesNotExistException){
            return new ResponseEntity(new GeneralMessageDTO(patientDoesNotExistException.getMessage()),HttpStatus.NOT_FOUND);
        }
    }
}
