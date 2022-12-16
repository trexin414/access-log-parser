package ru.сourses.work;

public class Employee {
    private String name;
    private Department department;

    public Employee(String name){
        this.name = name;
    }

    public Employee(String name, Department department){
        this.name = name;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        if(this.department.getBoss() == null)
            return this.name + " работает в отделе " + department.getName() + ", в котором пока нет начальника, а " + this.name + " еще не дослужился";

        if (this.name.equals(department.getBoss().getName()))
            return this.name + " начальник отдела " + department.getName();

        return this.name + " работает в отделе " + department.getName() + ", начальник которого " + department.getBoss().getName();
    }
}
class main {
    public static void main(String[] args) {
        Employee natasha = new Employee("Natasha");
        Employee pet9 = new Employee("Петя");
        Department it = new Department("IT", natasha);
        Department car = new Department("Car", pet9);
        Employee arsenii = new Employee("Арсений", car);

        System.out.println(arsenii);
        System.out.println(natasha);
        System.out.println(pet9);
    }
}