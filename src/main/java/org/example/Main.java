package org.example;

public class Main {
    public static void main(String[] args) {
        Gradebook gb = new Gradebook();
        gb.addSubject("Math");
        gb.addGrade("Math", 5.0);

        System.out.println("Subjects: " + gb.getSubjects());
        System.out.println("Average: " + gb.calcAvgForSubject("Math"));
    }
}