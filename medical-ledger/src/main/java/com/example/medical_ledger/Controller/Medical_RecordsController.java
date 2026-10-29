package com.example.medical_ledger.Controller;

import com.example.medical_ledger.Entity.Medical_Records;
import com.example.medical_ledger.Service.Medical_RecordsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical_records")
public class Medical_RecordsController {

    private Medical_RecordsService medicalRecordsService;

    public Medical_RecordsController(Medical_RecordsService medicalRecordsService) {
        this.medicalRecordsService = medicalRecordsService;
    }

    @PostMapping("/create/{patient_id}")
    public void createRecord(@RequestBody Medical_Records medicalRecords ,@PathVariable Long patient_id) {
        medicalRecordsService.createRecord(medicalRecords, patient_id);

    }
    @DeleteMapping("/delete/{id}")
    public void  deleteRecord(@PathVariable Long id) {  // Todo - add edge cases
       medicalRecordsService.deleteRecord(id);
    }
    }

