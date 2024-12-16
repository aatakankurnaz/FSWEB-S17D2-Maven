package model;

public class SeniorDeveloper extends Developer{
    public SeniorDeveloper(long id, String name, double salary, Experience experience) {
        super(id, name, salary, experience);
    }

    public double calculateSalary() {
        return getSalary() - getSalary() * 0.35;
    }
}
