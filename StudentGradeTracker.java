import java.util.ArrayList;
import java.util.Scanner;

class Student {
        private String name;
        private double grade;
        
    public Student(String name, double grade) {
            this.name = name;
            this.grade = grade;
        }
        
        public String getName() { return name; }
        public double getGrade() { return grade; }
}

public class StudentGradeTracker {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Student Grade Tracker ---");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Show Grade Statistics");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1) {
        System.out.print("Enter student name: ");
                String name = scanner.nextLine();
                System.out.print("Enter student grade: ");
                double grade = scanner.nextDouble();
                students.add(new Student(name, grade));
                System.out.println("Student added.");
            } else if (choice == 2) {
                System.out.println("\n--- Student List ---");
                for (Student s : students) {
                    System.out.println(s.getName() + ": " + s.getGrade());
                }
            } else if (choice == 3) {
        if (students.isEmpty()) {
                    System.out.println("No students to show statistics for.");
                } else {
                    double sum = 0, max = Double.MIN_VALUE, min = Double.MAX_VALUE;
                    for (Student s : students) {
                        double g = s.getGrade();
                        sum += g;
                        if (g > max) max = g;
                        if (g < min) min = g;
                    }
                    double avg = sum / students.size();
                    System.out.println("Average Grade: " + avg);
                    System.out.println("Highest Grade: " + max);
                    System.out.println("Lowest Grade: " + min);
                }
            } else if (choice == 4) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }
}