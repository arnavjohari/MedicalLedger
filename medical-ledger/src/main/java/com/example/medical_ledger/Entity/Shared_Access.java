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
public class Shared_Access {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_active")
    private Boolean isActive;

    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime validTill;

    @ManyToOne
    @JoinColumn(name = "patient_id" , nullable = false)
    private User userPatient;

    @ManyToOne
    @JoinColumn(name = "doctor_id" , nullable = false)
    private  User userDoctor;
}
