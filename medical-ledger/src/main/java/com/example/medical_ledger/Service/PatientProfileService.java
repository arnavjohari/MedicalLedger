package com.example.medical_ledger.Service;

import com.example.medical_ledger.Entity.PatientProfile;
import com.example.medical_ledger.Entity.User;
import com.example.medical_ledger.Repository.PatientProfileRepository;
import com.example.medical_ledger.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PatientProfileService {

    PatientProfileRepository patientProfileRepository;
    UserRepository userRepository;

    public PatientProfileService(PatientProfileRepository patientProfileRepository , UserRepository userRepository) {
        this.patientProfileRepository = patientProfileRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void createProfile(PatientProfile patientProfile ,Long user_id){
        User user = userRepository.findById(user_id).orElseThrow(()->new RuntimeException("No User Found"));

        patientProfile.setUser(user);
        patientProfileRepository.save(patientProfile);
    }

    @Transactional
    public PatientProfile getProfile(Long user_id){
       return patientProfileRepository.findByUser_id(user_id);
    }

    @Transactional
    public void deleteProfile(Long user_id){
        User user = userRepository.findById(user_id).orElseThrow(()->new RuntimeException("No User Found"));

        patientProfileRepository.deleteByUser_id(user_id);
    }
}
