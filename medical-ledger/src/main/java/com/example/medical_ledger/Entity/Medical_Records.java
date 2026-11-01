package com.example.medical_ledger.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    private Boolean isEmergency;

    @JsonBackReference // When serialize is done as this is a child class jackson ignores the user part in it and only gives records
    @ManyToOne
    @JoinColumn(name = "Patient_id" , nullable = false)
    private User user;
}
