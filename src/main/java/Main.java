package Oops.EmployeePayRollSystem;

import java.util.ArrayList;

abstract  class Employee{
    private String name;
    private int id;


    public Employee(String name, int id){
        this.name = name;
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    public abstract double calculateSalary();

    @Override
    public  String toString(){
        return "Employee [name="+name+", id="+id+", salary="+calculateSalary()+"]";
    }
}

class FullTimeEmployee extends  Employee{
    private double monthlySalary;

    public FullTimeEmployee(String name, int id, double monthlySalary){
        super(name,id) ;
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary(){
        return  monthlySalary;
    }
}

class PartTimeEmployee extends Employee{
    private int hourWorked;
    private double hourlyRate;

    public PartTimeEmployee(String name, int id, int hourWorked, double hourlyRate){
        super(name,id);
        this.hourWorked = hourWorked;
        this.hourlyRate = hourlyRate;
    }
    @Override
    public double calculateSalary(){
        return hourlyRate * hourWorked;
    }
}

class PayRollSystem{
    private ArrayList<Employee> employeeList;

    public PayRollSystem(){
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee){
            employeeList.add(employee);
    }

    public void removeEmployee(int id){
        Employee employeeToRemove = null;
        for(Employee employee:employeeList){
            if (employee.getId()==id){
                employeeToRemove = employee;
                break;
            }
        }
        if (employeeToRemove != null){
            employeeList.remove(employeeToRemove);
        }

    }

    public void displayEmployee(){
        for(Employee employee:employeeList){
            System.out.println(employee);
        }
    }
}

public class Main {

    public  static  void main(String[] args) {
        System.out.println("Employee pay roll system");


    }
}
