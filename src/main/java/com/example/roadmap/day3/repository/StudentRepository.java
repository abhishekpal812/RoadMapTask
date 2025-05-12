package com.example.roadmap.day3.repository;

import com.example.roadmap.day3.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
