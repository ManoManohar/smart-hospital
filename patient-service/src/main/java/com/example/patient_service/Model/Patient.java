package com.example.patient_service.Model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "patientDB")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String conditionStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getConditionStatus() {
        return conditionStatus;
    }

    public void setConditionStatus(String conditionStatus) {
        this.conditionStatus = conditionStatus;
    }

    public Patient(Long id, String name, int age, String conditionStatus) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.conditionStatus = conditionStatus;
    }

    public Patient() {
    }
}
