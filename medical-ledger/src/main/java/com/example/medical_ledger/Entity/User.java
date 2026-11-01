package com.example.medical_ledger.Entity;

import com.example.medical_ledger.eNums.Gender;
import com.example.medical_ledger.eNums.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;
    private String password;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private  int age;
    @Column(name = "phone_no")
    private String phoneNo;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @JsonManagedReference  // When Serialize is done as this is parent class jackson includes the medical records in this user
    @OneToMany(mappedBy = "user")
    private List<Medical_Records> medicalRecords = new ArrayList<>();


}
