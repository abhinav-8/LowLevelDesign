package SOLID.Example1.Problem;


/*
Why is this problematic?
- The class is trying to do too many things
- There can be various reasons to change this class
1. updateEmployee() -> If the data storage config/req changes, this function will be updated
2. calculateSalary() -> If the tax slabs change or the way of calculating the salary changes, some new
parameter like age is introduced, this function will be updated
3. printPerformanceReport() -> If the type of report or the format of report changes,
let's say word to pdf or some new kind of graphs etc are required , this function will be updated too.

This violates SRP(single responsibility principle)
"There should be only one reason to change a class."

Reason is often referred to a class/role.

 */
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

    public void updateEmployee() {
        System.out.println("Employee updated successfully");
    }

    public double calculateSalary() {
        //Calculations based on tax slabs , other factors etc
        return 1200222.00;
    }

    public void printPerformanceReport() {
        System.out.println("Performance report for " + id + " " + name + " " + age);
    }
}
