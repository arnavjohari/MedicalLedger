package com.example.medical_ledger.Service;

import com.example.medical_ledger.Entity.Medical_Records;
import com.example.medical_ledger.Entity.User;
import com.example.medical_ledger.Repository.Medical_RecordsRepository;
import com.example.medical_ledger.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private Medical_RecordsRepository medicalRecordsRepository;

    public UserService(UserRepository userRepository , Medical_RecordsRepository medicalRecordsRepository) {

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

        UserToChange.setAge(user.getAge());
        UserToChange.setName(user.getName());
        UserToChange.setEmail(user.getEmail());
        UserToChange.setGender(user.getGender());
        UserToChange.setPassword(user.getPassword());
        UserToChange.setPhoneNo(user.getPhoneNo());
        UserToChange.setRole(user.getRole());
        UserToChange.setCreatedAt(user.getCreatedAt());

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
