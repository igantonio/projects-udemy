package com.invillia.CursoSpringEssentialsDevDojo.service;

import com.invillia.CursoSpringEssentialsDevDojo.domain.Student;
import com.invillia.CursoSpringEssentialsDevDojo.exception.ResourceNotFoundException;
import com.invillia.CursoSpringEssentialsDevDojo.exception.StudentsAlreadyRegistredException;
import com.invillia.CursoSpringEssentialsDevDojo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public long save(Student student){
        if(!studentRepository.existsByEmail(student.getEmail())){
            return studentRepository.save(student).getId();
        }
        throw new StudentsAlreadyRegistredException("Student already registred with this email !!!");

    }

    @Transactional
    public Student update(Student student){
        return studentRepository.save(student);
    }

    @Transactional
    public void deleteById(long id){
        studentRepository.delete(studentIdExists(id));
    }

    @Transactional(readOnly = true)
    public List<Student> findAll(){
        return studentRepository.findAll();
    }

//    @Transactional(readOnly = true)
//    public Page<Student> findAll(Pageable pageable){
//        return studentRepository.findAll(pageable);
//    }

    @Transactional(readOnly = true)
    public List<Student> findByName(String name){
        return studentRepository.findByNameIgnoreCaseContaining(name);
    }

    @Transactional(readOnly = true)
    public Student findById(long id){
        return studentIdExists(id);
    }

    private Student studentIdExists(long id){
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for ID: " + id));
    }


}
