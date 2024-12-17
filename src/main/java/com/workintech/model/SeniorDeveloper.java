package com.workintech.model;

public class SeniorDeveloper extends Developer{
    public SeniorDeveloper(int id, String name, double salary) {
        super(id, name, salary);
    }

    public double calculateSalary() {
        return getSalary() - getSalary() * 0.35;
    }
}
