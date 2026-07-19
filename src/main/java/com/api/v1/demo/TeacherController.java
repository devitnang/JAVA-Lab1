package com.api.v1.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * TeacherController – REST controller for Teacher CRUD operations.
 *
 * Approach: Manages data manually using an in-memory {@link ArrayList}
 * and basic for-loops. No database or JPA is used.
 * A simple counter is used to simulate auto-increment IDs.
 */
@RestController
@RequestMapping("/api/v1/teachers")
public class TeacherController {

    // In-memory storage for teachers (no database)
    private List<Teacher> teacherList = new ArrayList<>();

    // Simple ID counter to simulate auto-increment
    private Long idCounter = 1L;

    // ── Constructor: pre-populate with sample data ────────────────────────────
    public TeacherController() {
        teacherList.add(new Teacher(idCounter++, "Vannthy",    25, "JAVA - III"));
        teacherList.add(new Teacher(idCounter++, "Lay",     23, "MA - II"));
        teacherList.add(new Teacher(idCounter++, "Sydeth", 30, "CND - II"));
    }

    // ── 1. Get All Teachers ───────────────────────────────────────────────────
    /**
     * GET /api/v1/teachers
     * Returns the entire in-memory teacher list.
     */
    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return ResponseEntity.ok(teacherList);
    }

    // ── 2. Get Teacher by ID ──────────────────────────────────────────────────
    /**
     * GET /api/v1/teachers/{id}
     * Searches the list using a basic for-loop.
     * Returns 404 if no teacher is found with the given ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable Long id) {
        // Manual search using a for-loop
        for (Teacher teacher : teacherList) {
            if (teacher.getId().equals(id)) {
                return ResponseEntity.ok(teacher);
            }
        }
        // Not found
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher not found!");
    }

    // ── 3. Create Teacher ─────────────────────────────────────────────────────
    /**
     * POST /api/v1/teachers
     * Assigns a new ID and adds the teacher to the in-memory list.
     * Returns 201 Created with the newly added teacher.
     */
    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        // Assign a unique ID manually
        teacher.setId(idCounter++);
        teacherList.add(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(teacher);
    }

    // ── 4. Update Teacher ─────────────────────────────────────────────────────
    /**
     * PUT /api/v1/teachers
     * Finds the teacher in the list using a for-loop and updates its fields.
     * The teacher's id must be included in the request body.
     * Returns 404 if no teacher is found.
     *
     * Example body: { "id": 1, "name": "Vannthy", "age": 25, "subject": "JAVA - III" }
     */
    @PutMapping
    public ResponseEntity<?> updateTeacher(@RequestBody Teacher updatedData) {
        // Manual search and update using a for-loop (id from request body)
        for (Teacher teacher : teacherList) {
            if (teacher.getId().equals(updatedData.getId())) {
                teacher.setName(updatedData.getName());
                teacher.setAge(updatedData.getAge());
                teacher.setSubject(updatedData.getSubject());
                return ResponseEntity.ok(teacher);
            }
        }
        // Not found
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher not found!");
    }

    // ── 5. Delete Teacher ─────────────────────────────────────────────────────
    /**
     * DELETE /api/v1/teachers/{id}
     * Removes the teacher from the list using a for-loop and index tracking.
     * Returns 204 No Content on success, 404 if not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long id) {
        // Manual search using a for-loop with index
        for (int i = 0; i < teacherList.size(); i++) {
            if (teacherList.get(i).getId().equals(id)) {
                teacherList.remove(i);
                return ResponseEntity.noContent().build();
            }
        }
        // Not found
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher not found!");
    }
}
