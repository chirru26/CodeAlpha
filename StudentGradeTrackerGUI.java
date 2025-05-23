import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

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

public class StudentGradeTrackerGUI extends JFrame {
    private ArrayList<Student> students = new ArrayList<>();
    private JTextField nameField, gradeField;
    private JTextArea displayArea;

    public StudentGradeTrackerGUI() {
        setTitle("Student Grade Tracker");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Top panel for input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 5, 5));
        inputPanel.add(new JLabel("Student Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Student Grade:"));
        gradeField = new JTextField();
        inputPanel.add(gradeField);
        JButton addButton = new JButton("Add Student");
        inputPanel.add(addButton);
        JButton statsButton = new JButton("Show Statistics");
        inputPanel.add(statsButton);
        add(inputPanel, BorderLayout.NORTH);

        // Center area for display
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom panel for view all
        JPanel bottomPanel = new JPanel();
        JButton viewButton = new JButton("View All Students");
        bottomPanel.add(viewButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Button actions
        addButton.addActionListener(e -> addStudent());
        viewButton.addActionListener(e -> viewStudents());
        statsButton.addActionListener(e -> showStatistics());
    }

    private void addStudent() {
        String name = nameField.getText().trim();
        String gradeText = gradeField.getText().trim();
        if (name.isEmpty() || gradeText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both name and grade.");
            return;
        }
        try {
            double grade = Double.parseDouble(gradeText);
            students.add(new Student(name, grade));
            displayArea.setText("Student added: " + name + " (" + grade + ")\n");
            nameField.setText("");
            gradeField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid grade. Please enter a number.");
        }
    }

    private void viewStudents() {
        if (students.isEmpty()) {
            displayArea.setText("No students to display.\n");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("--- Student List ---\n");
        for (Student s : students) {
            sb.append(s.getName()).append(": ").append(s.getGrade()).append("\n");
        }
        displayArea.setText(sb.toString());
    }

    private void showStatistics() {
        if (students.isEmpty()) {
            displayArea.setText("No students to show statistics for.\n");
            return;
        }
        double sum = 0, max = Double.MIN_VALUE, min = Double.MAX_VALUE;
        for (Student s : students) {
            double g = s.getGrade();
            sum += g;
            if (g > max) max = g;
            if (g < min) min = g;
        }
        double avg = sum / students.size();
        String stats = String.format("Average Grade: %.2f\nHighest Grade: %.2f\nLowest Grade: %.2f\n", avg, max, min);
        displayArea.setText(stats);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentGradeTrackerGUI().setVisible(true);
        });
    }
} 