package com.api.v1.demo;

/**
 * Student Entity – a plain Java class (POJO).
 * Managed in-memory by StudentController using an ArrayList and for-loops.
 */
public class Student {

    private Long id;
    private String name;
    private int age;
    private String major;

    // ── Constructors ─────────────────────────────────────────────────────────
    public Student() {
    }

    public Student(Long id, String name, int age, String major) {
        this.id    = id;
        this.name  = name;
        this.age   = age;
        this.major = major;
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', age=" + age + ", major='" + major + "'}";
    }
}
