package com.hospitalmanagement.backend.Hospital.Management.System.BackEnd.Repositories;

import com.hospitalmanagement.backend.Hospital.Management.System.BackEnd.Models.Doctor;
import com.hospitalmanagement.backend.Hospital.Management.System.BackEnd.Models.Patient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Repository
public class DoctorRepository {
    private HashMap<String, Doctor> docDatabase;
    private HashMap<String, ArrayList<Patient>> docVsPatient;
    private int overAllDoctors;

    public DoctorRepository(){
        this.docVsPatient = new HashMap<>();
        this.docDatabase = new HashMap<>();
        this.overAllDoctors = 0;
    }

    public void removeParticularPatientFromParticularDoctor(String pId, String docId){

        ArrayList<Patient> allPatientsHandledDoctor = docVsPatient.get(docId);

        for(int i = 0; i < allPatientsHandledDoctor.size(); i++){
            Patient obj = allPatientsHandledDoctor.get(i);
            if(obj.getpId().equals(pId)){
                allPatientsHandledDoctor.remove(i);
                break;
            }
        }

    }

    public void addDoctorToDatabase(Doctor obj){
        this.overAllDoctors += 1;
        docVsPatient.put(obj.getDocID(),new ArrayList<>());
        docDatabase.put(obj.getDocID(),obj);
    }

    public void assignPatientToDoctor(String docId, Patient obj){
        ArrayList<Patient> patients = this.docVsPatient.get(docId);
        patients.add(obj);
    }

    public ArrayList<Patient> getDoctorsPatients(String docID){
        return docVsPatient.get(docID);
    }

    public HashMap<String, ArrayList<Patient>> getDocVsPatientDB(){
        return docVsPatient;
    }

    public Doctor getDoctorByDocID(String docID){
        return docDatabase.get(docID);
    }
    public int getTotalDoctors(){
        return docDatabase.size();
    }
    public int getOverAllDoctors(){
        return this.overAllDoctors;
    }

    public void updateDocDetailsByDocID(String docID, Doctor obj){
        this.docDatabase.put(docID, obj);
    }
}
