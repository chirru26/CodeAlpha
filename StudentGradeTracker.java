import java.util.*;

/**
 * Student Grade Tracker - Java Console Application
 * Manages student grades with full CRUD operations and statistical analysis
 */
public class StudentGradeTracker {
    
    // Student class to represent individual student records
    static class Student {
        private String name;
        private String email;
        private String subject;
        private double grade;
        
        public Student(String name, String email, String subject, double grade) {
            this.name = name;
            this.email = email;
            this.subject = subject;
            this.grade = grade;
        }
        
        // Getters and setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        
        public String getSubject() { return subject; }
        public void setSubject(String subject) { this.subject = subject; }
        
        public double getGrade() { return grade; }
        public void setGrade(double grade) { this.grade = grade; }
        
        @Override
        public String toString() {
            return String.format("%-20s %-25s %-15s %6.1f", name, email, subject, grade);
        }
    }
    
    // ArrayList to store student data
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        // Initialize with sample data
        initializeSampleData();
        
        System.out.println("========================================");
        System.out.println("    STUDENT GRADE TRACKER SYSTEM");
        System.out.println("========================================");
        
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getChoice();
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayAllStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    searchStudent();
                    break;
                case 6:
                    displayStatistics();
                    break;
                case 7:
                    displaySummaryReport();
                    break;
                case 8:
                    displaySubjectReport();
                    break;
                case 0:
                    System.out.println("\nThank you for using Student Grade Tracker!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
            
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    private static void initializeSampleData() {
        students.add(new Student("Sarah Johnson", "sarah.johnson@email.com", "Mathematics", 95.0));
        students.add(new Student("Michael Davis", "michael.davis@email.com", "Science", 88.0));
        students.add(new Student("Emma Wilson", "emma.wilson@email.com", "English", 92.0));
        students.add(new Student("James Brown", "james.brown@email.com", "History", 85.0));
        students.add(new Student("Lisa Anderson", "lisa.anderson@email.com", "Mathematics", 91.0));
        students.add(new Student("David Miller", "david.miller@email.com", "Science", 87.0));
        students.add(new Student("Ashley Garcia", "ashley.garcia@email.com", "Art", 94.0));
        students.add(new Student("Ryan Martinez", "ryan.martinez@email.com", "English", 89.0));
    }
    
    private static void displayMenu() {
        System.out.println("\n========================================");
        System.out.println("              MAIN MENU");
        System.out.println("========================================");
        System.out.println("1. Add New Student");
        System.out.println("2. Display All Students");
        System.out.println("3. Update Student Grade");
        System.out.println("4. Delete Student");
        System.out.println("5. Search Student");
        System.out.println("6. Display Statistics");
        System.out.println("7. Summary Report");
        System.out.println("8. Subject Performance Report");
        System.out.println("0. Exit");
        System.out.println("========================================");
        System.out.print("Enter your choice: ");
    }
    
    private static int getChoice() {
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            return choice;
        } catch (InputMismatchException e) {
            scanner.nextLine(); // consume invalid input
            return -1;
        }
    }
    
    private static void addStudent() {
        System.out.println("\n--- ADD NEW STUDENT ---");
        
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();
        
        System.out.print("Enter email address: ");
        String email = scanner.nextLine().trim();
        
        System.out.print("Enter subject: ");
        String subject = scanner.nextLine().trim();
        
        double grade = -1;
        while (grade < 0 || grade > 100) {
            System.out.print("Enter grade (0-100): ");
            try {
                grade = scanner.nextDouble();
                scanner.nextLine(); // consume newline
                if (grade < 0 || grade > 100) {
                    System.out.println("Grade must be between 0 and 100!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number!");
                scanner.nextLine(); // consume invalid input
            }
        }
        
        students.add(new Student(name, email, subject, grade));
        System.out.println("\nStudent added successfully!");
    }
    
    private static void displayAllStudents() {
        System.out.println("\n--- ALL STUDENTS ---");
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        
        System.out.println(String.format("%-20s %-25s %-15s %-6s", "Name", "Email", "Subject", "Grade"));
        System.out.println("------------------------------------------------------------------------");
        
        for (int i = 0; i < students.size(); i++) {
            System.out.printf("%2d. %s\n", (i + 1), students.get(i));
        }
    }
    
    private static void updateStudent() {
        if (students.isEmpty()) {
            System.out.println("\nNo students available to update.");
            return;
        }
        
        displayAllStudents();
        System.out.print("\nEnter student number to update (1-" + students.size() + "): ");
        
        try {
            int index = scanner.nextInt() - 1;
            scanner.nextLine(); // consume newline
            
            if (index >= 0 && index < students.size()) {
                Student student = students.get(index);
                
                System.out.println("\nUpdating: " + student.getName());
                System.out.print("Enter new grade (current: " + student.getGrade() + "): ");
                
                double newGrade = scanner.nextDouble();
                scanner.nextLine(); // consume newline
                
                if (newGrade >= 0 && newGrade <= 100) {
                    student.setGrade(newGrade);
                    System.out.println("Grade updated successfully!");
                } else {
                    System.out.println("Invalid grade! Must be between 0 and 100.");
                }
            } else {
                System.out.println("Invalid student number!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number!");
            scanner.nextLine();
        }
    }
    
    private static void deleteStudent() {
        if (students.isEmpty()) {
            System.out.println("\nNo students available to delete.");
            return;
        }
        
        displayAllStudents();
        System.out.print("\nEnter student number to delete (1-" + students.size() + "): ");
        
        try {
            int index = scanner.nextInt() - 1;
            scanner.nextLine(); // consume newline
            
            if (index >= 0 && index < students.size()) {
                Student student = students.get(index);
                System.out.print("Are you sure you want to delete " + student.getName() + "? (y/n): ");
                String confirm = scanner.nextLine().toLowerCase();
                
                if (confirm.equals("y") || confirm.equals("yes")) {
                    students.remove(index);
                    System.out.println("Student deleted successfully!");
                } else {
                    System.out.println("Deletion cancelled.");
                }
            } else {
                System.out.println("Invalid student number!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number!");
            scanner.nextLine();
        }
    }
    
    private static void searchStudent() {
        if (students.isEmpty()) {
            System.out.println("\nNo students available to search.");
            return;
        }
        
        System.out.print("\nEnter student name to search: ");
        String searchName = scanner.nextLine().toLowerCase().trim();
        
        boolean found = false;
        System.out.println("\n--- SEARCH RESULTS ---");
        System.out.println(String.format("%-20s %-25s %-15s %-6s", "Name", "Email", "Subject", "Grade"));
        System.out.println("------------------------------------------------------------------------");
        
        for (Student student : students) {
            if (student.getName().toLowerCase().contains(searchName)) {
                System.out.println(student);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No students found with name containing: " + searchName);
        }
    }
    
    private static void displayStatistics() {
        if (students.isEmpty()) {
            System.out.println("\nNo students available for statistics.");
            return;
        }
        
        // Calculate statistics using arrays
        double[] grades = new double[students.size()];
        for (int i = 0; i < students.size(); i++) {
            grades[i] = students.get(i).getGrade();
        }
        
        double average = calculateAverage(grades);
        double highest = findHighest(grades);
        double lowest = findLowest(grades);
        int passingCount = countPassing(grades, 70.0);
        double passingRate = (double) passingCount / grades.length * 100;
        
        System.out.println("\n========================================");
        System.out.println("           GRADE STATISTICS");
        System.out.println("========================================");
        System.out.printf("Total Students:     %d\n", students.size());
        System.out.printf("Average Grade:      %.2f\n", average);
        System.out.printf("Highest Grade:      %.2f\n", highest);
        System.out.printf("Lowest Grade:       %.2f\n", lowest);
        System.out.printf("Passing Students:   %d\n", passingCount);
        System.out.printf("Passing Rate:       %.1f%%\n", passingRate);
        
        // Find top student
        String topStudent = "";
        for (Student student : students) {
            if (student.getGrade() == highest) {
                topStudent = student.getName();
                break;
            }
        }
        System.out.printf("Top Student:        %s\n", topStudent);
    }
    
    private static void displaySummaryReport() {
        System.out.println("\n========================================");
        System.out.println("           SUMMARY REPORT");
        System.out.println("========================================");
        
        if (students.isEmpty()) {
            System.out.println("No data available for report generation.");
            return;
        }
        
        displayStatistics();
        
        System.out.println("\n--- GRADE DISTRIBUTION ---");
        int[] distribution = new int[5]; // [90-100, 80-89, 70-79, 60-69, <60]
        String[] ranges = {"90-100 (Excellent)", "80-89 (Good)", "70-79 (Satisfactory)", "60-69 (Below Average)", "Below 60 (Failing)"};
        
        for (Student student : students) {
            double grade = student.getGrade();
            if (grade >= 90) distribution[0]++;
            else if (grade >= 80) distribution[1]++;
            else if (grade >= 70) distribution[2]++;
            else if (grade >= 60) distribution[3]++;
            else distribution[4]++;
        }
        
        for (int i = 0; i < distribution.length; i++) {
            System.out.printf("%-25s: %2d students\n", ranges[i], distribution[i]);
        }
        
        System.out.println("\n--- RECENT STUDENTS ---");
        displayAllStudents();
    }
    
    private static void displaySubjectReport() {
        if (students.isEmpty()) {
            System.out.println("\nNo students available for subject report.");
            return;
        }
        
        // Group students by subject using HashMap
        HashMap<String, ArrayList<Student>> subjectGroups = new HashMap<>();
        
        for (Student student : students) {
            String subject = student.getSubject();
            if (!subjectGroups.containsKey(subject)) {
                subjectGroups.put(subject, new ArrayList<>());
            }
            subjectGroups.get(subject).add(student);
        }
        
        System.out.println("\n========================================");
        System.out.println("        SUBJECT PERFORMANCE REPORT");
        System.out.println("========================================");
        
        System.out.printf("%-15s %-8s %-8s %-8s %-8s\n", "Subject", "Students", "Average", "Highest", "Lowest");
        System.out.println("--------------------------------------------------------");
        
        for (Map.Entry<String, ArrayList<Student>> entry : subjectGroups.entrySet()) {
            String subject = entry.getKey();
            ArrayList<Student> subjectStudents = entry.getValue();
            
            double[] subjectGrades = new double[subjectStudents.size()];
            for (int i = 0; i < subjectStudents.size(); i++) {
                subjectGrades[i] = subjectStudents.get(i).getGrade();
            }
            
            double avg = calculateAverage(subjectGrades);
            double high = findHighest(subjectGrades);
            double low = findLowest(subjectGrades);
            
            System.out.printf("%-15s %-8d %-8.1f %-8.1f %-8.1f\n", 
                            subject, subjectStudents.size(), avg, high, low);
        }
    }
    
    // Utility methods for statistical calculations
    private static double calculateAverage(double[] grades) {
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.length;
    }
    
    private static double findHighest(double[] grades) {
        double highest = grades[0];
        for (double grade : grades) {
            if (grade > highest) {
                highest = grade;
            }
        }
        return highest;
    }
    
    private static double findLowest(double[] grades) {
        double lowest = grades[0];
        for (double grade : grades) {
            if (grade < lowest) {
                lowest = grade;
            }
        }
        return lowest;
    }
    
    private static int countPassing(double[] grades, double passingGrade) {
        int count = 0;
        for (double grade : grades) {
            if (grade >= passingGrade) {
                count++;
            }
        }
        return count;
    }
}