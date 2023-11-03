package com.hospitalmanagement.backend.Hospital.Management.System.BackEnd.Services;

import com.hospitalmanagement.backend.Hospital.Management.System.BackEnd.Models.Doctor;
import com.hospitalmanagement.backend.Hospital.Management.System.BackEnd.Models.Patient;
import com.hospitalmanagement.backend.Hospital.Management.System.BackEnd.Repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository docRepo;

    public void addDoctorToDatabase(Doctor obj){
        String docID = "Doctor" + (docRepo.getOverAllDoctors()+1);
        obj.setDocID(docID);
        docRepo.addDoctorToDatabase(obj);
    }

    public Doctor getMinimumPatientDoctor(){
        int min = Integer.MAX_VALUE;
        HashMap<String, ArrayList<Patient>> docVsPatient = docRepo.getDocVsPatientDB();
        //Key -> DoctorID;
        Doctor obj = null;
        for(String key : docVsPatient.keySet()){
            ArrayList<Patient> doctorsPatient = docVsPatient.get(key);
            int totalPatients = doctorsPatient.size();
            if(totalPatients<min) {
                min = totalPatients;
                obj = docRepo.getDoctorByDocID(key);
            }
        }
        return obj;
    }
    public ArrayList<Patient> getDoctorPatients(String docID){
        return docRepo.getDoctorsPatients(docID);
    }

    public Doctor getDoctorByDocID(String docID){
        return docRepo.getDoctorByDocID(docID);
    }


    public void removeParticularPatientFromParticularDoctor(String docId, String pId){
        docRepo.removeParticularPatientFromParticularDoctor(pId,docId);
    }

    public void updateDocDetailsByID(String docID, Doctor obj){
        docRepo.updateDocDetailsByDocID(docID,obj);
    }

}
