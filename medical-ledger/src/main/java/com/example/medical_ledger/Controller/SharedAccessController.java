package com.example.medical_ledger.Controller;

import com.example.medical_ledger.Entity.SharedAccess;
import com.example.medical_ledger.Service.SharedAccessService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shared_access")
public class SharedAccessController {

    SharedAccessService sharedAccessService;

    public SharedAccessController(SharedAccessService sharedAccessService) {
        this.sharedAccessService = sharedAccessService;
    }
    @PostMapping("/create/{patient_id}/{doctor_id}")
    public void createAccess(@RequestBody SharedAccess sharedAccess , @PathVariable Long patient_id , @PathVariable Long doctor_id){
        sharedAccessService.createAccess(sharedAccess , patient_id , doctor_id);
    }

    // Todo - we dont need get here now we will use this table for a check before get in other tables
    @DeleteMapping("/revoke/{id}")
    public  void revokeAccess(@PathVariable Long id){
        sharedAccessService.revokeAccess(id);
    }
    @DeleteMapping("/delete")
    public void deleteAccess(){
        sharedAccessService.deleteAccess();
    }
}
