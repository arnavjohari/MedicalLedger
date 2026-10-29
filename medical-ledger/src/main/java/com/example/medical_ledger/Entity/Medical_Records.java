package com.example.medical_ledger.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medical_records")
public class Medical_Records {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String diagnosis;
    private String prescription;

    @CreationTimestamp
    private LocalDateTime created_at;
    private Boolean isEmergency;

    @ManyToOne
    @JoinColumn(name = "Patient_id" , nullable = false)
    private User user;
}
