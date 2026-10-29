package com.example.medical_ledger.Repository;

import com.example.medical_ledger.Entity.Medical_Records;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Medical_RecordsRepository extends JpaRepository<Medical_Records , Long> {
}
