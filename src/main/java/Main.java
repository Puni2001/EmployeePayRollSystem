package Oops.EmployeePayRollSystem.src.main.java;

import java.util.ArrayList;
import java.util.Scanner;

abstract class Employee {
    private String name;
    private int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public abstract double calculateSalary();

    @Override
    public String toString() {
        return "Employee [name=" + name + ", id=" + id + ", salary=" + calculateSalary() + "]";
    }
}

class FullTimeEmployee extends Employee {
    private double monthlySalary;

    public FullTimeEmployee(String name, int id, double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }
}

class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String name, int id, int hoursWorked, double hourlyRate) {
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }
}

class PayRollSystem {
    private ArrayList<Employee> employeeList;

    public PayRollSystem() {
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void removeEmployee(int id) {
        Employee employeeToRemove = null;
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                employeeToRemove = employee;
                break;
            }
        }
        if (employeeToRemove != null) {
            employeeList.remove(employeeToRemove);
            System.out.println("Employee with ID " + id + " removed.");
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }

    public void displayEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees to display.");
        } else {
            for (Employee employee : employeeList) {
                System.out.println(employee);
            }
        }
    }

    public boolean validateZeroEmployee() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees currently.");
            return true;
        }
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PayRollSystem payrollSystem = new PayRollSystem();
        boolean running = true;

        System.out.println("Welcome to the Employee Payroll System");

        while (running) {
            System.out.println("\n--- Features ---");
            System.out.println("1. Add Full-Time Employee");
            System.out.println("2. Add Part-Time Employee");
            System.out.println("3. Remove Employee");
            System.out.println("4. Display All Employees");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Name: ");
                    scanner.nextLine(); // consume newline
                    String fullName = scanner.nextLine();
                    System.out.print("Enter ID: ");
                    int fullId = scanner.nextInt();
                    System.out.print("Enter Monthly Salary: ");
                    double salary = scanner.nextDouble();

                    FullTimeEmployee fullTimeEmployee = new FullTimeEmployee(fullName, fullId, salary);
                    payrollSystem.addEmployee(fullTimeEmployee);
                }

                case 2 -> {
                    System.out.print("Enter Name: ");
                    scanner.nextLine(); // consume newline
                    String partName = scanner.nextLine();
                    System.out.print("Enter ID: ");
                    int partId = scanner.nextInt();
                    System.out.print("Enter Hours Worked: ");
                    int hours = scanner.nextInt();
                    System.out.print("Enter Hourly Rate: ");
                    double rate = scanner.nextDouble();

                    PartTimeEmployee partTimeEmployee = new PartTimeEmployee(partName, partId, hours, rate);
                    payrollSystem.addEmployee(partTimeEmployee);
                }

                case 3 -> {
                    if (!payrollSystem.validateZeroEmployee()) {
                        System.out.print("Enter ID of employee to remove: ");
                        int removeId = scanner.nextInt();
                        payrollSystem.removeEmployee(removeId);
                    }
                }

                case 4 -> payrollSystem.displayEmployees();

                case 5 -> {
                    running = false;
                    System.out.println("Exiting the system. Goodbye!");
                }

                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
