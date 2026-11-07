package com.example.medical_ledger.Controller;

import com.example.medical_ledger.Entity.MedicalRecords;
import com.example.medical_ledger.Service.MedicalRecordsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical_records")
public class MedicalRecordsController {

    private MedicalRecordsService medicalRecordsService;

    public MedicalRecordsController(MedicalRecordsService medicalRecordsService) {
        this.medicalRecordsService = medicalRecordsService;
    }

    @PostMapping("/create/{patient_id}")
    public void createRecord(@RequestBody MedicalRecords medicalRecords , @PathVariable Long patient_id) {
        medicalRecordsService.createRecord(medicalRecords, patient_id);

    }

    @GetMapping("/get/{patient_id}/{doctor_id}")
    public List<MedicalRecords> getRecords(@PathVariable Long patient_id , @PathVariable Long doctor_id){
        return medicalRecordsService.getRecords(patient_id , doctor_id);
    }
    @DeleteMapping("/delete/{id}")
    public void  deleteRecord(@PathVariable Long id) {  // Todo - add edge cases
       medicalRecordsService.deleteRecord(id);
    }
    }

