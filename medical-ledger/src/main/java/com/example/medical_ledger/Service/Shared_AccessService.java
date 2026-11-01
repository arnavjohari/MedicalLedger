package com.example.medical_ledger.Service;

import com.example.medical_ledger.Entity.Shared_Access;
import com.example.medical_ledger.Entity.User;
import com.example.medical_ledger.Repository.Shared_AccessRepository;
import com.example.medical_ledger.Repository.UserRepository;
import com.example.medical_ledger.eNums.Role;
import com.example.medical_ledger.eNums.Status;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class Shared_AccessService {

    Shared_AccessRepository sharedAccessRepository;
    UserRepository userRepository;

    public Shared_AccessService(Shared_AccessRepository sharedAccessRepository ,  UserRepository userRepository) {
        this.sharedAccessRepository = sharedAccessRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void createAccess(Shared_Access sharedAccess , Long patient_id , Long doctor_id){
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

        sharedAccessRepository.save(sharedAccess);
    }

    @Transactional
    public void revokeAccess(Long id){
        Shared_Access sharedAccess = sharedAccessRepository.findById(id).orElseThrow(() -> new RuntimeException("No access found"));

        sharedAccess.setStatus(Status.Revoked);
        sharedAccess.setIsActive(false);

        sharedAccessRepository.save(sharedAccess);
    }

    @Transactional
    public void deleteAccess(){
        LocalDateTime now = LocalDateTime.now();
        sharedAccessRepository.expireOutOfDateAccess(now);
        sharedAccessRepository.deleteByStatus();
    }

}
