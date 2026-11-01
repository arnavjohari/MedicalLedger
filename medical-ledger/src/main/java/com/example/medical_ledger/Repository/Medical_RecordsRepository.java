package com.example.medical_ledger.Repository;

import com.example.medical_ledger.Entity.Medical_Records;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Medical_RecordsRepository extends JpaRepository<Medical_Records , Long> {

    List<Medical_Records> findByUser_idOrderByCreatedAtDesc(Long patient_id);  // OrderByCreatedAtDesc just tells Jpa to sort the result acc to created_at in desc order
}
