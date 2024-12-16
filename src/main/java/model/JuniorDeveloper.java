package model;

public class JuniorDeveloper extends Developer {
    public JuniorDeveloper(long id, String name, double salary, Experience experience) {
        super(id, name, salary, experience);
    }

    public double calculateSalary() {
        return getSalary() - getSalary() * 0.15;
    }
}
