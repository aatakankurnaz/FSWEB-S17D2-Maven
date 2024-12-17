package com.workintech.s17d2.model;

public class MidDeveloper extends Developer{
    public MidDeveloper(int id, String name, double salary) {
        super(id, name, salary);
    }

    public double calculateSalary() {
        return getSalary() - getSalary() * 0.25;
    }
}