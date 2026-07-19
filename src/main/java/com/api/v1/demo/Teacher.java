package com.api.v1.demo;

/**
 * Teacher Entity – a plain Java class (POJO), NOT a JPA entity.
 * Used by TeacherController which manages data manually via an in-memory list
 * and basic for-loops (no database, no JPA).
 */
public class Teacher {

    private Long id;
    private String name;
    private int age;
    private String subject;

    // ── Constructors ─────────────────────────────────────────────────────────
    public Teacher() {
    }

    public Teacher(Long id, String name, int age, String subject) {
        this.id      = id;
        this.name    = name;
        this.age     = age;
        this.subject = subject;
    }

    // ── Getters & Setters ─────────────────────────────────────────────────────
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Teacher{id=" + id + ", name='" + name + "', age=" + age + ", subject='" + subject + "'}";
    }
}
