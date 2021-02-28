package com.invillia.CursoSpringEssentialsDevDojo.controller;

import com.invillia.CursoSpringEssentialsDevDojo.domain.Student;
import com.invillia.CursoSpringEssentialsDevDojo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "protected/students")
    public ResponseEntity<?> listAll(){
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

//    @GetMapping(path = "protected/students")
//    public ResponseEntity<?> listAll(Pageable pageable){
//        return new ResponseEntity<>(studentService.findAll(pageable), HttpStatus.OK);
//    }

    @GetMapping(path = "protected/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable long id, @AuthenticationPrincipal UserDetails userDetails){

        System.out.println(userDetails);
        return new ResponseEntity<>(studentService.findById(id), HttpStatus.OK);
    }

    @GetMapping("protected/findbyname/{name}")
    public ResponseEntity<?> findStudentsByName(@PathVariable String name){
        return new ResponseEntity<>(studentService.findByName(name), HttpStatus.OK);
    }

    @PostMapping(path = "admin/students")
    public ResponseEntity<?> save(@Valid @RequestBody Student student){
        return new ResponseEntity<>(studentService.save(student), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "admin/students/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable long id){
        studentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "admin/students")
    public ResponseEntity<?> update(@RequestBody Student student){
        studentService.update(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
