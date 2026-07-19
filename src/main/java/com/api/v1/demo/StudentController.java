package com.api.v1.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private List<Student> studentList = new ArrayList<>();
    private Long idCounter = 1L;

    public StudentController() {
        studentList.add(new Student(idCounter++, "Thai Sulinh", 22, "CS"));
        studentList.add(new Student(idCounter++, "Nang Devit", 21, "IT"));
        studentList.add(new Student(idCounter++, "Ken Sokraingsey", 20, "SA"));
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        for (Student s : studentList) {
            if (s.getId().equals(id)) {
                return ResponseEntity.ok(s);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found!");
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        student.setId(idCounter++);
        studentList.add(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @PutMapping
    public ResponseEntity<?> updateStudent(@RequestBody Student updated) {
        for (Student s : studentList) {
            if (s.getId().equals(updated.getId())) {
                s.setName(updated.getName());
                s.setAge(updated.getAge());
                s.setMajor(updated.getMajor());
                return ResponseEntity.ok(s);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId().equals(id)) {
                studentList.remove(i);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found!");
    }
}
