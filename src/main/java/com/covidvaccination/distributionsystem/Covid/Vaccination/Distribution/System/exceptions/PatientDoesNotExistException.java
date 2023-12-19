package com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.exceptions;

public class PatientDoesNotExistException extends RuntimeException{
    public PatientDoesNotExistException(String mssg){
        super(mssg);
    }
}
