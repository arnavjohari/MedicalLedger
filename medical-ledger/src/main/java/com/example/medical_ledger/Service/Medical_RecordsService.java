package com.example.medical_ledger.Service;

import com.example.medical_ledger.Entity.Medical_Records;
import com.example.medical_ledger.Entity.Shared_Access;
import com.example.medical_ledger.Entity.User;
import com.example.medical_ledger.Repository.Medical_RecordsRepository;
import com.example.medical_ledger.Repository.Shared_AccessRepository;
import com.example.medical_ledger.Repository.UserRepository;
import com.example.medical_ledger.eNums.Status;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class Medical_RecordsService {

    Medical_RecordsRepository medicalRecordsRepository;
    UserRepository userRepository;
    Shared_AccessRepository sharedAccessRepository;

    public Medical_RecordsService(Medical_RecordsRepository medicalRecordsRepository , UserRepository userRepository , Shared_AccessRepository sharedAccessRepository) {
        this.medicalRecordsRepository = medicalRecordsRepository;
        this.userRepository = userRepository;
        this.sharedAccessRepository = sharedAccessRepository;
    }

    @Transactional
    public void createRecord(Medical_Records medicalRecords , Long patient_id){
        User user = userRepository.findById(patient_id).orElseThrow(() -> new RuntimeException("No Patient Found"));
        medicalRecords.setUser(user);

        medicalRecordsRepository.save(medicalRecords);
    }

    @Transactional
    public List<Medical_Records> getRecords(Long patient_id , Long doctor_id){

        Shared_Access sharedAccess = sharedAccessRepository.findByUserPatientIdAndUserDoctorId(patient_id, doctor_id).orElseThrow(() -> new RuntimeException("No Access Found"));

        if(sharedAccess.getStatus() == Status.Active){
            return medicalRecordsRepository.findByUser_idOrderByCreatedAtDesc(patient_id);
        }
     return null;  // Todo - Later add exception handling
    }

    @Transactional
    public void deleteRecord(Long id){

        medicalRecordsRepository.deleteById(id);
    }
}
