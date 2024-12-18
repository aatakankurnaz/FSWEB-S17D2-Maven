package com.workintech.model;

public class JuniorDeveloper extends Developer {
    public JuniorDeveloper(int id, String name, double salary) {
        super(id, name, salary);
    }

    public double calculateSalary() {
        return getSalary() - getSalary() * 0.15;
    }
}
