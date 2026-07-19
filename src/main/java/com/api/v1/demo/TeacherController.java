package com.api.v1.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
public class TeacherController {

    private List<Teacher> teacherList = new ArrayList<>();
    private Long idCounter = 1L;

    public TeacherController() {
        teacherList.add(new Teacher(idCounter++, "Vannthy", 25, "JAVA - III"));
        teacherList.add(new Teacher(idCounter++, "Lay", 23, "MA - II"));
        teacherList.add(new Teacher(idCounter++, "Sydeth", 30, "CND - II"));
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return ResponseEntity.ok(teacherList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable Long id) {
        for (Teacher teacher : teacherList) {
            if (teacher.getId().equals(id)) {
                return ResponseEntity.ok(teacher);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher not found!");
    }

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        teacher.setId(idCounter++);
        teacherList.add(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(teacher);
    }

    @PutMapping
    public ResponseEntity<?> updateTeacher(@RequestBody Teacher updatedData) {
        for (Teacher teacher : teacherList) {
            if (teacher.getId().equals(updatedData.getId())) {
                teacher.setName(updatedData.getName());
                teacher.setAge(updatedData.getAge());
                teacher.setSubject(updatedData.getSubject());
                return ResponseEntity.ok(teacher);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher not found!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long id) {
        for (int i = 0; i < teacherList.size(); i++) {
            if (teacherList.get(i).getId().equals(id)) {
                teacherList.remove(i);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher not found!");
    }
}
