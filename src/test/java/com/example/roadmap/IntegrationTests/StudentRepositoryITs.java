package com.example.roadmap.IntegrationTests;

import com.example.roadmap.day3.Entity.Address;
import com.example.roadmap.day3.Entity.Student;
import com.example.roadmap.day3.repository.AddressRepository;
import com.example.roadmap.day3.repository.StudentRepository;
import com.example.roadmap.unitTests.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.Assert;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase
@TestPropertySource("classpath:application-test.properties")
public class StudentRepositoryITs {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    void givenStudentEntity_whenSaveStudent_thenStudentPersisted(){
        Student student = TestUtils.getStudent();
        student.setId(null);
        Address address = student.getAddress();
        address.setId(null);
        Address saveAddress = addressRepository.save(address);
        student.setAddress(saveAddress);
        Student saveStudent = studentRepository.save(student);
        System.out.println(saveStudent);
        Optional<Student> retrievedStudent = studentRepository.findById(saveStudent.getId());
        Assert.isTrue(retrievedStudent.isPresent(),"Object not persisted");
        Assert.isTrue(retrievedStudent.get().getName().equals("Pawan"),"Getting wrong student name");
    }

    @Test
    void givenStudentEntity_whenUpdateStudent_thenStudentUpdated(){
        Student student = TestUtils.getStudent();
        student.setId(null);
        Address address = student.getAddress();
        address.setId(null);
        Address saveAddress = addressRepository.save(address);
        student.setAddress(saveAddress);
        Student saveStudent = studentRepository.save(student);
        saveStudent.setName("Rahul");
        Student save = studentRepository.save(saveStudent);
        System.out.println(saveStudent);
        Optional<Student> retrievedStudent = studentRepository.findById(save.getId());
        Assert.isTrue(retrievedStudent.isPresent(),"Object not persisted");
        Assert.isTrue(retrievedStudent.get().getName().equals("Rahul"),"Getting wrong student name");
    }

    @Test
    void givenStudentEntity_whenDeleteStudent_thenStudentDeleted(){
        Student student = TestUtils.getStudent();
        student.setId(null);
        Address address = student.getAddress();
        address.setId(null);
        Address saveAddress = addressRepository.save(address);
        student.setAddress(saveAddress);
        Student saveStudent = studentRepository.save(student);
        System.out.println(saveStudent);
        studentRepository.deleteById(saveStudent.getId());
        Optional<Student> retrievedStudent = studentRepository.findById(saveStudent.getId());
        Assert.isTrue(retrievedStudent.isEmpty(),"Object not deleted");
    }

}
