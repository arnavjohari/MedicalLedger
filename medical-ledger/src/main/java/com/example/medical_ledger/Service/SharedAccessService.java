package com.example.medical_ledger.Service;

import com.example.medical_ledger.Entity.SharedAccess;
import com.example.medical_ledger.Entity.User;
import com.example.medical_ledger.Repository.SharedAccessRepository;
import com.example.medical_ledger.Repository.UserRepository;
import com.example.medical_ledger.eNums.Role;
import com.example.medical_ledger.eNums.Status;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SharedAccessService {

    SharedAccessRepository sharedAccessRepository;
    UserRepository userRepository;

    public SharedAccessService(SharedAccessRepository sharedAccessRepository , UserRepository userRepository) {
        this.sharedAccessRepository = sharedAccessRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void createAccess(SharedAccess sharedAccess , Long patient_id , Long doctor_id){
        User patient = userRepository.findById(patient_id).orElseThrow(() -> new RuntimeException("No Patient Found"));

        if(patient.getRole() != Role.Patient){
            //Todo - add a exception handling
        }

        User doctor = userRepository.findById(doctor_id).orElseThrow(() -> new RuntimeException("No Doctor Found"));

        if(doctor.getRole() != Role.Doctor){
            //Todo - add a exception handling
        }

        sharedAccess.setUserPatient(patient);
        sharedAccess.setUserDoctor(doctor);

        sharedAccess.setStatus(Status.Pending);
        sharedAccessRepository.save(sharedAccess);
    }

    @Transactional
    public void revokeAccess(Long id){
        SharedAccess sharedAccess = sharedAccessRepository.findById(id).orElseThrow(() -> new RuntimeException("No access found"));

        sharedAccess.setStatus(Status.Revoked);

        sharedAccessRepository.save(sharedAccess);
    }

    @Transactional
    public void deleteAccess(){
        LocalDateTime now = LocalDateTime.now();
        sharedAccessRepository.expireOutOfDateAccess(now);
        sharedAccessRepository.deleteByStatus();
    }

}
