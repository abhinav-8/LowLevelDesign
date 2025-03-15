package SOLID.Example1.Improved;

public class Employee {
    private int id;
    private String name;
    private int age;

    public Employee(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public void fetchEmployee() {
        System.out.println("Fetching Employee " + id + " " + name + " " + age);
    }

    public String getName() {
        return name;
    }
    public void updateEmployee() {
        System.out.println("Employee updated successfully");
    }

    //We can make individual getters setters
}
