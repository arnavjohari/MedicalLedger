package com.example.medical_ledger.Repository;

import com.example.medical_ledger.Entity.PatientProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientProfileRepository extends JpaRepository<PatientProfile , Long> {
    public void deleteByUser_id(Long user_id);

    public PatientProfile findByUser_id(Long user_id);
}
