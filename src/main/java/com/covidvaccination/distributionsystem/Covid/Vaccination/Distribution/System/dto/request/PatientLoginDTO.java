package com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PatientLoginDTO {
    String email;
    String password;
}
