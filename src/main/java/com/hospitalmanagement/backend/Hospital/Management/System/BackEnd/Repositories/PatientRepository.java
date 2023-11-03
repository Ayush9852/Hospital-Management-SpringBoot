package com.hospitalmanagement.backend.Hospital.Management.System.BackEnd.Repositories;

import com.hospitalmanagement.backend.Hospital.Management.System.BackEnd.Models.Doctor;
import com.hospitalmanagement.backend.Hospital.Management.System.BackEnd.Models.Patient;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class PatientRepository {

    private HashMap<String, Patient> patientDatabase;
    private HashMap<String, Doctor> patientVsDoctor;
    private int overAllPatients;
    public PatientRepository(){
        this.patientVsDoctor = new HashMap<String, Doctor>();
        this.overAllPatients = 0;
        this.patientDatabase = new HashMap<>();
    }
    public void assignPatientToDoctor(String pId, Doctor obj){
        this.patientVsDoctor.put(pId, obj);
    }

    public Patient getPatientById(String patientId){
        return this.patientDatabase.get(patientId);
    }

    public void addPatientToDatabase(Patient obj){
        this.overAllPatients += 1;
        this.patientDatabase.put(obj.getpId(),obj);
    }
    public Doctor getPatientsDoctor(String pId){
        return this.patientVsDoctor.get(pId);
    }

    public void dischargePatientByPatientId(String pId){
        this.patientDatabase.remove(pId);
    }

    public int getOverAllPatients(){
        return this.overAllPatients;
    }
    public int totalPatients(){return patientDatabase.size();}
}
