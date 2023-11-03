package com.hospitalmanagement.backend.Hospital.Management.System.BackEnd.Services;

import com.hospitalmanagement.backend.Hospital.Management.System.BackEnd.Models.Patient;
import com.hospitalmanagement.backend.Hospital.Management.System.BackEnd.Repositories.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalService {

    @Autowired
    HospitalRepository hospitalRepo;

    public int getFirstEmptyBedNumber(){
        Patient[] beds =hospitalRepo.getAllBeds();

        for(int i = 0; i < beds.length; i++){
            if(beds[i] == null){
                return i; // Got first empty bed
            }
        }
        return -1; // No bed is empty
    }

    public void assignPatientToBedNumber(int bedNumber, Patient obj){
        hospitalRepo.assignPatientToBedNumber(bedNumber,obj);
    }
    public Patient getPatientsFromBedNumber(int bedNumber){
        return hospitalRepo.getPatientAtParticularBedNumber(bedNumber);
    }

    public void deAllocatePatientsFromBed(String pId){
        hospitalRepo.deAllocatePatientFromBed(pId);
    }

    public int getBedFee(){
        return hospitalRepo.getBedFee();
    }
}
