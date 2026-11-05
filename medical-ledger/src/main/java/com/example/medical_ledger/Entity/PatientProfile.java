package com.example.medical_ledger.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient_profile")
public class PatientProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(length = 1000)  // so it creates a VARCHAR(1000) in mysql db
    private String chronicDiseases;

    @Column(columnDefinition = "Text") // so it creates a TEXT type var in mysql db so it can save large statements
    private String surgicalHistory;

    @Column(length = 1000)  // so it creates a VARCHAR(1000) in mysql db
    private String allergy;

    private String emergencyContactName;
    private String emergencyContactRelation;
    private Integer emergencyContactAge;
    private String emergencyContactPhone;

    @OneToOne
    @JoinColumn(name = "user_id" , unique = true , nullable = false)
    @JsonIgnore // Todo - Later we will use DTO here
    private User user;
}
