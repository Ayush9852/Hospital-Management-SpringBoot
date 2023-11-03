package com.hospitalmanagement.backend.Hospital.Management.System.BackEnd.Services;

import com.hospitalmanagement.backend.Hospital.Management.System.BackEnd.Models.Bill;
import com.hospitalmanagement.backend.Hospital.Management.System.BackEnd.Models.Doctor;
import com.hospitalmanagement.backend.Hospital.Management.System.BackEnd.Models.Patient;
import com.hospitalmanagement.backend.Hospital.Management.System.BackEnd.Repositories.DoctorRepository;
import com.hospitalmanagement.backend.Hospital.Management.System.BackEnd.Repositories.HospitalRepository;
import com.hospitalmanagement.backend.Hospital.Management.System.BackEnd.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    @Autowired
    HospitalService hospitalService;
    @Autowired
    DoctorRepository docRepo;
    @Autowired
    PatientRepository patientRepo;
    @Autowired
    DoctorService docService;
    public void addPatientToDatabase(Patient obj){
        String pId = "Patient" + (patientRepo.getOverAllPatients() + 1);
        obj.setpId(pId);
        int bedNumber = hospitalService.getFirstEmptyBedNumber();
        hospitalService.assignPatientToBedNumber(bedNumber,obj);
        Doctor doc = docService.getMinimumPatientDoctor();
        patientRepo.assignPatientToDoctor(pId,doc);
        docRepo.assignPatientToDoctor(doc.getDocID(), obj);
        patientRepo.addPatientToDatabase(obj);
    }

    public Doctor getPatientsDoctor(String pId){
        return patientRepo.getPatientsDoctor(pId);
    }

    public Bill dischargePatient(String pId, String dischargeDate){

        // dd-mm-yy
        Patient obj = patientRepo.getPatientById(pId);

        String admitDate = obj.getAdmitDate();

        String[] admitDateArray = admitDate.split("-");
        String[] dischargeDateArray = dischargeDate.split("-");

        int diff = Integer.parseInt(dischargeDateArray[0])-Integer.parseInt(admitDateArray[0]);

        Doctor docObj = patientRepo.getPatientsDoctor(pId);

        int docFee = docObj.getDocFee();

        int bedFee = hospitalService.getBedFee();

        int totalBill = diff * (docFee + bedFee);

        Bill billObj = new Bill(docFee,bedFee,totalBill);

        patientRepo.dischargePatientByPatientId(pId);

        return billObj;
    }
    public void dischargePatient(String pId){
        patientRepo.dischargePatientByPatientId(pId);
    }

    public Patient getPatientById(String pId){
        return patientRepo.getPatientById(pId);
    }
}
