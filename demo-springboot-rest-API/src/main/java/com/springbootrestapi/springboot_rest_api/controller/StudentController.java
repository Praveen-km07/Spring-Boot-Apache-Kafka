package com.springbootrestapi.springboot_rest_api.controller;

import com.springbootrestapi.springboot_rest_api.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @GetMapping("/student")
    public Student getStudent(){
        Student student=new Student(
        1,
        "Praveen",
        "km"
                );
        return student;
    }

    @GetMapping("/studentlist")
    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"praveen","km"));
        students.add(new Student(2,"anjana","pm"));
        students.add(new Student(3,"mark","joseph"));
        students.add(new Student(4,"john","salt"));
        return students;
    }

    @GetMapping("{id}")
    public Student studentPathVariable(@PathVariable("id") int studentId){
        return new Student(studentId,"praveen","km");
    }

    //passing multiplepath variable
    @GetMapping("{id}/{first-name}/{last-name}")
    public Student studentMultiplePathVariable(@PathVariable("id") int studentId,@PathVariable("first-name") String firstName,@PathVariable("last-name") String lastName){
        return new Student(studentId,firstName,lastName);
    }

    //Request params
    @GetMapping("query")
    public Student studentRequestVariable(@RequestParam int id){
        return new Student(id,"John","salt");
    }

    //Multiple Request Query params
    @GetMapping("mutiplequeryparams")
    public Student studentsMultipleRequestParams(@RequestParam int id,@RequestParam String firstName,@RequestParam String lastName){
        return new Student(id,firstName,lastName);
    }

    //post
    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    //put
    @PutMapping("{id}/update")
    public Student updateStudent(@RequestBody Student student,@PathVariable int id){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }
    //delete
    @DeleteMapping("{id}/delete")
    public String deleteStudent(@PathVariable int id){
        System.out.println(id);
        return "Student record deleted successfully";
    }

    //ResponseEntity
    @GetMapping("/studentsentity")
    public ResponseEntity<Student> getStudentReponseEntity(){
        Student student = new Student(1,"praveen","km");
        //return new ResponseEntity<>(student,HttpStatus.OK);
        //return ResponseEntity.ok(student);
        //passing header to response entity
        return ResponseEntity.ok().header("custom-header","praveen").body(student);
    }

    //ResponseEntityClass
    @GetMapping("/studentResponseEntityClass")
    public ResponseEntity<List<Student>> getStudentsResponseEntityClass(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"praveen","km"));
        return ResponseEntity.ok(students);
    }
}
