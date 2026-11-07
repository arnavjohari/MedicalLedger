package com.example.medical_ledger.Service;

import com.example.medical_ledger.Entity.User;
import com.example.medical_ledger.Repository.MedicalRecordsRepository;
import com.example.medical_ledger.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private MedicalRecordsRepository medicalRecordsRepository;

    public UserService(UserRepository userRepository , MedicalRecordsRepository medicalRecordsRepository) {

        this.medicalRecordsRepository = medicalRecordsRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void createUser(User user){
        userRepository.save(user);
    }

    @Transactional
    public User getUser(Long id){

        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("No User Found"));
    }

    @Transactional
    public void updateUser(User user , Long id){

        User UserToChange = userRepository.findById(id).orElseThrow(() -> new RuntimeException("No User Found"));


        //Todo - add edge case and add checks so onlt the changed value is set not all

        if(user.getAge() != null) {
            UserToChange.setAge(user.getAge());
        }

        if(user.getName() != null) {
            UserToChange.setName(user.getName());
        }

        if(user.getEmail() != null) {
            UserToChange.setEmail(user.getEmail());
        }

        if(user.getGender() != null) {
            UserToChange.setGender(user.getGender());
        }

        if(user.getPassword() != null) {
            UserToChange.setPassword(user.getPassword());
        }

        if(user.getPhoneNo() != null) {
            UserToChange.setPhoneNo(user.getPhoneNo());
        }

        if(user.getRole() != null) {
            UserToChange.setRole(user.getRole());
        }

        if(user.getCreatedAt() != null) {
            UserToChange.setCreatedAt(user.getCreatedAt());
        }
        userRepository.save(UserToChange);
    }

    @Transactional
    public String deleteUser(Long id){
        Optional<User> existingUser = userRepository.findById(id);

        if(!existingUser.isEmpty()){
            userRepository.deleteById(id);
            return "User Deleted";
        }

        return "User Not Found";
    }
}
