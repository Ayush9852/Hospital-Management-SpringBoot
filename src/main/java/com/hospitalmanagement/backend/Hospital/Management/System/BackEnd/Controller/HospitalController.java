package com.hospitalmanagement.backend.Hospital.Management.System.BackEnd.Controller;

import com.hospitalmanagement.backend.Hospital.Management.System.BackEnd.Models.Patient;
import com.hospitalmanagement.backend.Hospital.Management.System.BackEnd.Services.HospitalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HospitalController {

    HospitalService hospitalService;
    @GetMapping("/api/hospital/getpatient")
    public Patient getPatientByBedNumber(@RequestParam int bedNumber){
        return hospitalService.getPatientsFromBedNumber(bedNumber);
    }

}
