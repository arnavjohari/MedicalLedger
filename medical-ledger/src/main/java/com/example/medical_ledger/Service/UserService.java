package com.example.medical_ledger.Service;

import com.example.medical_ledger.Entity.User;
import com.example.medical_ledger.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user){
        userRepository.save(user);
    }
    public Optional<User> getUser(Long id){
        return userRepository.findById(id);
    }

    public void updateUser(User user , Long id){
        Optional<User> existingUser = userRepository.findById(id);

        User UserToChange = existingUser.get();   // First we find by findById then get it
        //Todo - add edge case

        UserToChange.setAge(user.getAge());
        UserToChange.setName(user.getName());
        UserToChange.setEmail(user.getEmail());
        UserToChange.setGender(user.getGender());
        UserToChange.setPassword(user.getPassword());
        UserToChange.setPhone_no(user.getPhone_no());
        UserToChange.setRole(user.getRole());
        UserToChange.setCreated_at(user.getCreated_at());

        userRepository.save(UserToChange);
    }

    public String deleteUser(Long id){
        Optional<User> existingUser = userRepository.findById(id);

        if(!existingUser.isEmpty()){
            userRepository.deleteById(id);
            return "User Deleted";
        }

        return "User Not Found";
    }
}
