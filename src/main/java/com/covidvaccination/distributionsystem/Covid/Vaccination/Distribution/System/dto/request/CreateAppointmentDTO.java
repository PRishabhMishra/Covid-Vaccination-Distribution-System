package com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.dto.request;

import com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.enums.VaccinationCenterPreference;
import lombok.*;

import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class CreateAppointmentDTO {
    UUID id;
    VaccinationCenterPreference vaccinationCenterPreference;
}
