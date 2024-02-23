package student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Student {
    private String name;
    private int rollNumber;
    private Map<String, Integer> subjectMarks;

    public Student(String name, int rollNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.subjectMarks = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public Map<String, Integer> getSubjectMarks() {
        return subjectMarks;
    }

    public void addSubjectMark(String subject, int mark) {
        subjectMarks.put(subject, mark);
    }

    public void updateSubjectMark(String subject, int mark) {
        if (subjectMarks.containsKey(subject)) {
            subjectMarks.put(subject, mark);
        } else {
            System.out.println("Subject not found for the student.");
        }
    }

    public void removeSubject(String subject) {
        subjectMarks.remove(subject);
    }

    public double calculatePercentage() {
        if (subjectMarks.isEmpty()) {
            return 0.0;
        }

        int totalMarks = subjectMarks.values().stream().mapToInt(Integer::intValue).sum();
        return (double) totalMarks / subjectMarks.size();
    }

    public String calculateGrade() {
        double percentage = calculatePercentage();

        if (percentage >= 90) {
            return "A";
        } else if (percentage >= 80) {
            return "B";
        } else if (percentage >= 70) {
            return "C";
        } else if (percentage >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
}

public class ClassA {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            System.out.println("Student Grade Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Display Student Information");
            System.out.println("5. Exit");

            System.out.print("Enter your choice (1-5): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    displayStudentInformation();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }

        scanner.close();
    }

    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student roll number: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Student student = new Student(name, rollNumber);

        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter subject name: ");
            String subject = scanner.nextLine();

            System.out.print("Enter marks for " + subject + ": ");
            int marks = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            student.addSubjectMark(subject, marks);
        }

        students.add(student);
        System.out.println("Student added successfully.");
    }

    private static void updateStudent() {
        System.out.print("Enter student roll number to update: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Student student = findStudentByRollNumber(rollNumber);

        if (student != null) {
            System.out.println("Student found. Enter the operation to perform:");
            System.out.println("1. Update subject marks");
            System.out.println("2. Remove subject");
            System.out.print("Enter your choice (1/2): ");
            int operation = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (operation) {
                case 1:
                    updateSubjectMarks(student);
                    break;
                case 2:
                    removeSubject(student);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } else {
            System.out.println("Student with roll number " + rollNumber + " not found.");
        }
    }

    private static void updateSubjectMarks(Student student) {
        System.out.print("Enter subject to update: ");
        String subject = scanner.nextLine();

        if (student.getSubjectMarks().containsKey(subject)) {
            System.out.print("Enter new marks for " + subject + ": ");
            int newMarks = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            student.updateSubjectMark(subject, newMarks);
            System.out.println("Subject marks updated successfully.");
        } else {
            System.out.println("Subject not found for the student.");
        }
    }

    private static void removeSubject(Student student) {
        System.out.print("Enter subject to remove: ");
        String subject = scanner.nextLine();

        if (student.getSubjectMarks().containsKey(subject)) {
            student.removeSubject(subject);
            System.out.println("Subject removed successfully.");
        } else {
            System.out.println("Subject not found for the student.");
        }
    }

    private static void deleteStudent() {
        System.out.print("Enter student roll number to delete: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Student student = findStudentByRollNumber(rollNumber);

        if (student != null) {
            students.remove(student);
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student with roll number " + rollNumber + " not found.");
        }
    }

    private static void displayStudentInformation() {
        System.out.print("Enter student roll number to display information: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Student student = findStudentByRollNumber(rollNumber);

        if (student != null) {
            System.out.println("Student Information:");
            System.out.println("Name: " + student.getName());
            System.out.println("Roll Number: " + student.getRollNumber());
            System.out.println("Subject Marks: " + student.getSubjectMarks());
            System.out.println("Overall Percentage: " + student.calculatePercentage());
            System.out.println("Grade: " + student.calculateGrade());
        } else {
            System.out.println("Student with roll number " + rollNumber + " not found.");
        }
    }

    private static Student findStudentByRollNumber(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }
}

