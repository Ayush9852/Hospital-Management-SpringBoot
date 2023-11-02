package com.hospitalmanagement.backend.Hospital.Management.System.BackEnd.Controller;

import com.hospitalmanagement.backend.Hospital.Management.System.BackEnd.Models.Patient;
import com.hospitalmanagement.backend.Hospital.Management.System.BackEnd.Services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping("/api/patient")
    public Patient getPatientByID(@RequestParam String patientId){
        return patientService.getPatientById(patientId);
    }
    @PostMapping("/api/patient/addpatient")
    public String addPatientToDatabase(@RequestBody Patient obj){
        patientService.addPatientToDatabase(obj);
        return "Patient got added successfully into database";
    }

    @DeleteMapping("/api/patient")
    public String deletePatientByPatientID(@RequestParam String patientID){
        patientService.dischargePatient(patientID);
        return "Patient got successfully deleted from database";
    }
}
