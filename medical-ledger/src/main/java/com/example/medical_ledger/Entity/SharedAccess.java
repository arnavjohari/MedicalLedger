package com.example.medical_ledger.Entity;

import com.example.medical_ledger.eNums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shared_access")
public class SharedAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;  // Puts the default as Pending as it should be pending first then active
    private LocalDateTime validTill;

    @ManyToOne
    @JoinColumn(name = "patient_id" , nullable = false)
    private User userPatient;

    @ManyToOne
    @JoinColumn(name = "doctor_id" , nullable = false)
    private  User userDoctor;
}
