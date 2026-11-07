package com.example.medical_ledger.Service;

import com.example.medical_ledger.Entity.MedicalRecords;
import com.example.medical_ledger.Entity.SharedAccess;
import com.example.medical_ledger.Entity.User;
import com.example.medical_ledger.Repository.MedicalRecordsRepository;
import com.example.medical_ledger.Repository.SharedAccessRepository;
import com.example.medical_ledger.Repository.UserRepository;
import com.example.medical_ledger.eNums.Status;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordsService {

    MedicalRecordsRepository medicalRecordsRepository;
    UserRepository userRepository;
    SharedAccessRepository sharedAccessRepository;

    public MedicalRecordsService(MedicalRecordsRepository medicalRecordsRepository , UserRepository userRepository , SharedAccessRepository sharedAccessRepository) {
        this.medicalRecordsRepository = medicalRecordsRepository;
        this.userRepository = userRepository;
        this.sharedAccessRepository = sharedAccessRepository;
    }

    @Transactional
    public void createRecord(MedicalRecords medicalRecords , Long patient_id){
        User user = userRepository.findById(patient_id).orElseThrow(() -> new RuntimeException("No Patient Found"));
        medicalRecords.setUser(user);

        medicalRecordsRepository.save(medicalRecords);
    }

    @Transactional
    public List<MedicalRecords> getRecords(Long patient_id , Long doctor_id){

        SharedAccess sharedAccess = sharedAccessRepository.findByUserPatientIdAndUserDoctorId(patient_id, doctor_id).orElseThrow(() -> new RuntimeException("No Access Found"));

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
