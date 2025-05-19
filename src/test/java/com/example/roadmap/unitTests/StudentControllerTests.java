package com.example.roadmap.unitTests;

import com.example.roadmap.day3.Entity.Student;
import com.example.roadmap.day3.controller.StudentController;
import com.example.roadmap.day3.request.RequestWrapper;
import com.example.roadmap.day3.response.ResponseWrapper;
import com.example.roadmap.day3.service.StudentService;
import com.example.roadmap.day3.validation.StudentValidation;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.util.Assert;

@WebMvcTest(StudentController.class)
public class StudentControllerTests {

    @Autowired
    private StudentController controller;

    @MockitoBean
    private StudentService studentService;

    @MockitoBean
    private StudentValidation studentValidation;

    @Test
    void testGetStudent() throws Exception {
        Student student = TestUtils.getStudent();
        Mockito.doNothing().when(studentValidation).notEmpty(1);
        Mockito.when(studentService.getStudent(1)).thenReturn(student);
        Mockito.when(studentService.convertStudentResponse(student)).thenReturn(TestUtils.getResponseWrapper());
        ResponseWrapper response = controller.getStudent(1);
        Assert.notNull(student,"Getting student as null");
    }

    @Test
    void testCreateStudent(){
        Mockito.doNothing().when(studentValidation).validateStudentObject(new RequestWrapper());
        Mockito.when(studentService.createStudent(new RequestWrapper())).thenReturn(1);
        ResponseWrapper responseWrapper = controller.createStudent(new RequestWrapper());
        Assert.isTrue(responseWrapper.getStatusCode().equals(201),"Getting wrong status code");
        Assert.isTrue(responseWrapper.getStudentId().equals(1),"Getting wrong student id");
    }

    @Test
    void testUpdateStudent(){
        Mockito.doNothing().when(studentValidation).validateStudentObject(new RequestWrapper());
        Mockito.when(studentService.updateStudent(new RequestWrapper())).thenReturn(1);
        ResponseWrapper responseWrapper = controller.updateStudent(new RequestWrapper());
        Assert.isTrue(responseWrapper.getStatusCode().equals(201),"Getting wrong status code :"+responseWrapper.getStatusCode());
        Assert.isTrue(responseWrapper.getStudentId().equals(1),"Getting wrong student id : "+responseWrapper.getStudentId());
    }

    @Test
    void testDeleteStudent(){
        Mockito.doNothing().when(studentValidation).notEmpty(1);
        Mockito.doNothing().when(studentService).deleteStudent(1);
        ResponseWrapper responseWrapper = controller.deleteStudent(1);
        Assert.isTrue(responseWrapper.getStatusCode().equals(204),"Getting wrong status code :"+responseWrapper.getStatusCode());
        Assert.isTrue(responseWrapper.getStudentId().equals(1),"Getting wrong student id : "+responseWrapper.getStudentId());
    }

}
