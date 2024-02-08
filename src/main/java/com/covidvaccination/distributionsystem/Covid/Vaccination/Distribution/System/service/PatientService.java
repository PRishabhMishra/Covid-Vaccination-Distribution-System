
package com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.service;

import ch.qos.logback.core.util.PropertySetterException;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.dto.request.PatientLoginDTO;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.dto.request.PatientSignupDTO;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.dto.response.AppointmentDTO;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.enums.VaccinationCenterPreference;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.exceptions.PatientDoesNotExistException;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.exceptions.WrongCredentials;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.models.Doctor;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.models.Patient;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.models.VaccinationCenter;
import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.repository.PatientRepository;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@ToString
@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    MailService mailService;

    @Autowired
    VaccinationCenterService vaccinationCenterService;
    @Autowired
    DoctorService doctorService;

        public Patient signUp(PatientSignupDTO patientSignupDTO) {
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

    public Patient login(PatientLoginDTO patientLoginDTO) {
        Patient patient = patientRepository.getPatientBYEmail(patientLoginDTO.getEmail());
        if (patient == null) {
            throw new PatientDoesNotExistException("Patient email Id is not registered in our portal.");
        }
        if (!patient.getPassword().equals(patientLoginDTO.getPassword())) {
            throw new WrongCredentials("Patient Entered Wrong Password.");
        }
        return patient;
    }



    public AppointmentDTO createAppointment(String email, String vaccinationCenterPreference) {
        //get patient by email
        Patient p = patientRepository.getPatientBYEmail(email);
        //Identify patient vaccination preference
        String vPreference = p.getVaccinationPreference();
        List<VaccinationCenter>vcList = vaccinationCenterService.getMinimumVCOnTheBasisOfTypeAndPreference(vaccinationCenterPreference,vPreference);
        //Assigning 0th index vaccination center to patient
        VaccinationCenter patientVC = vcList.get(0);
        //Assign doctor who is handling minimum number of patients to the current patient

        List<Doctor>docList = doctorService.getMinimumDoctorOnTheBasisOfVC(patientVC.getId());
        // Take out minimum doctor
        System.out.println(docList.size());



        Doctor patientDoctor = docList.get(0);

        updateDoseCountByOne(p);
        vaccinationCenterService.updatePatientCountByOne(patientVC);
        doctorService.updatePatientCountBYOne(patientDoctor);
        patientDoctor.getPatients().add(p);
        doctorService.addPatientVsDoctor(p.getId(),patientDoctor.getId());
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setDoseNumber(p.getDoseCount()+1);
        appointmentDTO.setPatient(p);
        appointmentDTO.setDocID(patientDoctor.getId());
        appointmentDTO.setDocName(patientDoctor.getName());
        appointmentDTO.setVcID(patientVC.getId());
        appointmentDTO.setVaccinationCenterName(patientVC.getName());

        String to = p.getEmail();
        String sub = String.format("Congratulations !! %s your appointment got created",p.getName());
        String text = String.format("Hii %s" +
                        " \n Your appointment got created. Below are your appointment details :"+
                "\n1. Dose Count : %d" +
                "\n2. Doctor Name : %s" +
                "\n3. Vaccination Center Name : %s" +
                "\n4. Vaccination Center Address : %s",
                p.getName(),
                p.getDoseCount(),
                patientDoctor.getName(),
                patientVC.getAddress()

                );
        mailService.generateMail(to,sub,text);

        return appointmentDTO;

        }
        public void updateDoseCountByOne(Patient patient){
            UUID id  = patient.getId();
            int doseCount = patient.getDoseCount();
            patientRepository.updateDoseCountByOne(id,doseCount);

        }

    }
