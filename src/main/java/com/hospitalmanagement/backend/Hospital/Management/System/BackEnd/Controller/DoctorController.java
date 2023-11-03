package com.hospitalmanagement.backend.Hospital.Management.System.BackEnd.Controller;

import com.hospitalmanagement.backend.Hospital.Management.System.BackEnd.Models.Doctor;
import com.hospitalmanagement.backend.Hospital.Management.System.BackEnd.Models.Patient;
import com.hospitalmanagement.backend.Hospital.Management.System.BackEnd.Services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class DoctorController {

    @Autowired
    DoctorService docService;

    @GetMapping("/api/doctor/adddoctor")
    public String addDoctorToDatabase(Doctor obj){
        docService.addDoctorToDatabase((obj));
        return "Doctor got added successfully to database";
    }

    @GetMapping("/api/doctor")
    public Doctor getDoctorByID(@RequestParam String docID){
        return docService.getDoctorByDocID(docID);
    }

    @PutMapping("/api/doctor")
    public String updateDoctorByID(@RequestParam String docID, @RequestBody Doctor obj){
        docService.updateDocDetailsByID(docID,obj);
        return "Doctor details with docID this " + docID + "get updated";
    }

    @GetMapping("/api/doctor/getallpatients/{docID}")
    public ArrayList<Patient> getDoctorPatients(@PathVariable String docID){
        return docService.getDoctorPatients(docID);
    }
}
