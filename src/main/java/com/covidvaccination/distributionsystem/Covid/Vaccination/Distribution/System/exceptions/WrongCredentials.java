package com.covidvaccination.distributionsystem.Covid.Vaccination.Distribution.System.exceptions;

public class WrongCredentials extends RuntimeException{
    public WrongCredentials(String mssg){
        super(mssg);
    }
}
