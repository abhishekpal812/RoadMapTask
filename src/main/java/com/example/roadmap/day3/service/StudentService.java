package com.example.roadmap.day3.service;

import com.example.roadmap.day3.Entity.Address;
import com.example.roadmap.day3.Entity.Student;
import com.example.roadmap.day3.repository.StudentRepository;
import com.example.roadmap.day3.request.RequestWrapper;
import com.example.roadmap.day3.response.ResponseWrapper;
import com.example.roadmap.exception.customClass.StudentNotFoundException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student getStudent(Integer studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student not available for given id : " + studentId));
    }

    public Integer createStudent(RequestWrapper requestWrapper) {
        return studentRepository.save(prepareStudentObject(requestWrapper)).getId();
    }

    private Student prepareStudentObject(RequestWrapper requestWrapper) {

        Address address = new Address();
        address.setHouse(requestWrapper.getHouse());
        address.setCity(requestWrapper.getCity());
        address.setState(requestWrapper.getState());
        address.setPin(requestWrapper.getPin());

        Student student = new Student();
        student.setName(requestWrapper.getName());
        student.setContactNumber(requestWrapper.getContactNumber());
        student.setEmail(requestWrapper.getEmail());
        student.setAddress(address);

        return student;
    }


    public Integer updateStudent(RequestWrapper requestWrapper) {
        Student student = studentRepository.findById(requestWrapper.getId()).orElseThrow(() -> new StudentNotFoundException("Student not available for given id : " + requestWrapper.getId()));
        student.setName(requestWrapper.getName());
        student.setContactNumber(requestWrapper.getContactNumber());
        student.setEmail(requestWrapper.getEmail());

        Address address = student.getAddress();
        address.setCity(requestWrapper.getCity());
        address.setPin(requestWrapper.getPin());
        address.setState(requestWrapper.getState());
        address.setHouse(requestWrapper.getHouse());

        student.setAddress(address);
        return studentRepository.save(student).getId();
    }

    public void deleteStudent(Integer studentId) {
        studentRepository.deleteById(studentId);
    }

    public ResponseWrapper convertStudentResponse(Student student) {
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setStudentId(student.getId());
        responseWrapper.setStudentName(student.getName());
        responseWrapper.setStudentContactNumber(student.getContactNumber());
        responseWrapper.setStudentEmail(student.getEmail());
        return responseWrapper;
    }

    public String uploadFile(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream();
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFSheet createSheet = workbook.getSheet("CreateStudent");
            Iterator<Row> rowIterator = createSheet.iterator();
            List<RequestWrapper> requestWrappersList = new ArrayList<>();
            while(rowIterator.hasNext()){
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                RequestWrapper requestWrapper = new RequestWrapper();
                while(cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch(cell.getCellType()){
                        case NUMERIC:
                            double numericCellValue = cell.getNumericCellValue();
                            switch(cell.getColumnIndex()){
                                case 1:
                                    requestWrapper.setContactNumber(String.valueOf(numericCellValue));
                                    break;
                                case 6:
                                    requestWrapper.setPin((int) numericCellValue);
                                    break;
                            }
                            break;
                        case STRING:
                            String stringCellValue = cell.getStringCellValue();
                            switch(cell.getColumnIndex()){
                                case 0:
                                    requestWrapper.setName(stringCellValue);
                                    break;
                                case 2:
                                    requestWrapper.setEmail(stringCellValue);
                                    break;
                                case 3:
                                    requestWrapper.setHouse(stringCellValue);
                                    break;
                                case 4:
                                    requestWrapper.setCity(stringCellValue);
                                    break;
                                case 5:
                                    requestWrapper.setState(stringCellValue);
                                    break;
                            }
                            break;
                    }
                }
                requestWrappersList.add(requestWrapper);
                List<Student> students = convertRequestWrapperToStudentObject(requestWrappersList);
                studentRepository.saveAll(students);
            }
            return "Student created successfully";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Student> convertRequestWrapperToStudentObject(List<RequestWrapper> requestWrappersList) {
        List<Student> studentList = new ArrayList<>();
        for (RequestWrapper requestWrapper : requestWrappersList){
            Address address = new Address();
            address.setHouse(requestWrapper.getHouse());
            address.setCity(requestWrapper.getCity());
            address.setState(requestWrapper.getState());
            address.setPin(requestWrapper.getPin());

            Student student = new Student();
            student.setName(requestWrapper.getName());
            student.setEmail(requestWrapper.getEmail());
            student.setContactNumber(requestWrapper.getContactNumber());
            student.setAddress(address);

            studentList.add(student);
        }
        return studentList;
    }
}
