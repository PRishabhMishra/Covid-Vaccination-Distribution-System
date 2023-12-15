package com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.dto.request;

import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.enums.VaccinationPreference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PatientSignupDTO {
    String name;
    String password;
    String aadharNumber;
    long phoneNumber;
    String gender;
    VaccinationPreference vaccinationPreference;
    String address;
    String email;

}
