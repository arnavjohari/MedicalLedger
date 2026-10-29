package com.example.medical_ledger.Service;

import com.example.medical_ledger.Entity.Medical_Records;
import com.example.medical_ledger.Entity.User;
import com.example.medical_ledger.Repository.Medical_RecordsRepository;
import com.example.medical_ledger.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Medical_RecordsService {

    Medical_RecordsRepository medicalRecordsRepository;
    UserRepository userRepository;

    public Medical_RecordsService(Medical_RecordsRepository medicalRecordsRepository , UserRepository userRepository) {
        this.medicalRecordsRepository = medicalRecordsRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void createRecord(Medical_Records medicalRecords , Long patient_id){
        User user = userRepository.findById(patient_id).orElseThrow(() -> new RuntimeException("No Patient Found"));
        medicalRecords.setUser(user);

        medicalRecordsRepository.save(medicalRecords);
    }

    @Transactional
    public void deleteRecord(Long id){

        medicalRecordsRepository.deleteById(id);
    }
}
