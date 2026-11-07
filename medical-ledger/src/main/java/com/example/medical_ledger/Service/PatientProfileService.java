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
    public void updateProfile(PatientProfile patientProfile , Long user_id){
        PatientProfile currProfile = patientProfileRepository.findByUser_id(user_id);

        if(patientProfile.getAllergy() != null){
            currProfile.setAllergy(patientProfile.getAllergy());
        }

        if(patientProfile.getChronicDiseases() != null){
            currProfile.setChronicDiseases(patientProfile.getChronicDiseases());
        }

        if(patientProfile.getEmergencyContactAge() != null){
            currProfile.setEmergencyContactAge(patientProfile.getEmergencyContactAge());
        }

        if(patientProfile.getEmergencyContactName() != null){
            currProfile.setEmergencyContactName(patientProfile.getEmergencyContactName());
        }

        if(patientProfile.getEmergencyContactRelation() != null){
            currProfile.setEmergencyContactRelation(patientProfile.getEmergencyContactRelation());
        }

        if(patientProfile.getEmergencyContactPhone() != null){
            currProfile.setEmergencyContactPhone(patientProfile.getEmergencyContactPhone());
        }

        patientProfileRepository.save(currProfile);
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
