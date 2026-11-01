package com.example.medical_ledger.Repository;

import com.example.medical_ledger.Entity.Shared_Access;
import com.example.medical_ledger.eNums.Status;
import jakarta.transaction.Transactional;
import org.hibernate.sql.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;


@Repository
public interface Shared_AccessRepository extends JpaRepository<Shared_Access , Long> {

    @Modifying
    @Transactional
    @Query(value = """ 
                        Update shared_access
                        s SET s.status = 'Expired' , s.is_active = false 
                        WHERE s.valid_till < :now
                                                """ , nativeQuery = true)
    void expireOutOfDateAccess(@Param("now")LocalDateTime now);

    @Modifying
    @Transactional
    @Query(value = """
                      DELETE FROM shared_access s
                      WHERE s.status IN ('Revoked' , 'Expired')
                                                """ , nativeQuery = true)
    void deleteByStatus();

    //Boolean existsByUserPatientIdAndUserDoctorId(Long patient_id , Long doctor_id);

    Optional<Shared_Access> findByUserPatientIdAndUserDoctorId(Long patient_id , Long doctor_id);

}
