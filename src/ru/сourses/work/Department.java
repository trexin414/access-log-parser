package ru.сourses.work;

public class Department {
    private String name;
    private Employee boss;

    public Department(String name, Employee employee){
        this.name = name;
        this.boss = employee;

        employee.setDepartment(this);
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setBoss(Employee boss) {
        if (boss.getDepartment().getName().equals(this.name))
            this.boss = boss;
        else throw new IllegalArgumentException("Данный сотрудник не может быть начальником отдела, в котором не работает");
    }

    public String getName() {
        return name;
    }

    public Employee getBoss() {
        return boss;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", boss=" + boss +
                '}';
    }
}
