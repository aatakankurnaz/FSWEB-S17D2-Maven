package model;

public class MidDeveloper extends Developer{
    public MidDeveloper(long id, String name, double salary, Experience experience) {
        super(id, name, salary, experience);
    }

    public double calculateSalary() {
        return getSalary() - getSalary() * 0.25;
    }
}
