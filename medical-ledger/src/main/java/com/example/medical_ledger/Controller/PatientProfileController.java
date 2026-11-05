package com.example.medical_ledger.Controller;

import com.example.medical_ledger.Entity.PatientProfile;
import com.example.medical_ledger.Service.PatientProfileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class PatientProfileController {

    PatientProfileService patientProfileService;

    public PatientProfileController(PatientProfileService patientProfileService) {
        this.patientProfileService = patientProfileService;
    }

    @PostMapping("/create/{user_id}")
    public void createProfile(@RequestBody PatientProfile patientProfile, @PathVariable Long user_id){
        patientProfileService.createProfile(patientProfile ,user_id);
    }

    @GetMapping("/get/{user_id}")
    public PatientProfile getProfile(@PathVariable Long user_id){
       return patientProfileService.getProfile(user_id);
    }

    @DeleteMapping("/delete/{user_id}")
    public void deleteProfile(@PathVariable Long user_id){
        patientProfileService.deleteProfile(user_id);
    }

    // Todo - update after i learn how to update only specific fields not entire
}
