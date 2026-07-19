package com.api.v1.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * StudentController – REST controller for Student CRUD operations.
 *
 * Approach: Manages data manually using an in-memory {@link ArrayList}.
 * Uses Java 8's {@link Optional} for safe, null-free lookup by ID.
 * A simple counter is used to simulate auto-increment IDs.
 *
 * Endpoints:
 * GET /api/v1/students – get all
 * PUT /api/v1/students – update (id supplied in request body)
 * POST /api/v1/students – create
 * GET /api/v1/students/{id} – get by id
 * DELETE /api/v1/students/{id} – delete by id
 */
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    // In-memory storage for students (no database)
    private List<Student> studentList = new ArrayList<>();

    // Simple ID counter to simulate auto-increment
    private Long idCounter = 1L;

    // ── Constructor: pre-populate with sample data ────────────────────────────
    public StudentController() {
        studentList.add(new Student(idCounter++, "Thai Sulinh", 22, "CS"));
        studentList.add(new Student(idCounter++, "Nang Devit", 21, "IT"));
        studentList.add(new Student(idCounter++, "Ken Sokraingsey", 20, "SA"));
    }

    // ── 1. Get All Students ───────────────────────────────────────────────────
    /**
     * GET /api/v1/students
     * Returns the entire in-memory student list.
     */
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentList);
    }

    // ── 2. Update Student ─────────────────────────────────────────────────────
    /**
     * PUT /api/v1/students
     * Uses Optional to safely find the student by the id in the request body,
     * then updates its fields. Returns 404 if not found.
     *
     * Example body: { "id": 1, "name": "Nang Devit", "age": 21, "major": "IT" }
     */
    @PutMapping
    public ResponseEntity<?> updateStudent(@RequestBody Student updatedData) {
        // Use Optional to find the student by id
        Optional<Student> optionalStudent = studentList.stream()
                .filter(s -> s.getId().equals(updatedData.getId()))
                .findFirst();

        if (optionalStudent.isPresent()) {
            Student existingStudent = optionalStudent.get();
            existingStudent.setName(updatedData.getName());
            existingStudent.setAge(updatedData.getAge());
            existingStudent.setMajor(updatedData.getMajor());
            return ResponseEntity.ok(existingStudent);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found!");
        }
    }

    // ── 3. Create Student ─────────────────────────────────────────────────────
    /**
     * POST /api/v1/students
     * Assigns a new ID and adds the student to the in-memory list.
     * Returns 201 Created with the newly added student.
     */
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        // Assign a unique ID manually
        student.setId(idCounter++);
        studentList.add(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    // ── 4. Get Student by ID ──────────────────────────────────────────────────
    /**
     * GET /api/v1/students/{id}
     * Uses Optional to safely retrieve a student by ID.
     * Returns 404 if no student is found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        // Use Optional for safe lookup
        Optional<Student> optionalStudent = studentList.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();

        if (optionalStudent.isPresent()) {
            return ResponseEntity.ok(optionalStudent.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found!");
        }
    }

    // ── 5. Delete Student ─────────────────────────────────────────────────────
    /**
     * DELETE /api/v1/students/{id}
     * Removes the student from the list using a for-loop and index tracking.
     * Returns 204 No Content on success, 404 if not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId().equals(id)) {
                studentList.remove(i);
                return ResponseEntity.noContent().build();
            }
        }
        // Not found
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found!");
    }
}
