package com.example.medical_ledger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MedicalLedgerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalLedgerApplication.class, args);
	}
	// Todo - Later we will add a seperate entity/table for hospital which will be able to store doctor_id and details
}
