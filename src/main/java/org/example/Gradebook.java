package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Gradebook {
    private final List<String> subjects;
    private final Map<String, List<Double>> subjectsGrades;

    public Gradebook() {
        this.subjects = new ArrayList<>();
        this.subjectsGrades = new HashMap<>();
    }

    public void addSubject(String subject) {
        if (!subjects.contains(subject)) {
            subjects.add(subject);
            subjectsGrades.put(subject, new ArrayList<>());
        }
    }

    public void addGrade(String subject, double grade) {
        if (subjectsGrades.containsKey(subject)) {
            if (!subject.equals("History")) {
                subjectsGrades.get(subject).add(grade);
            }
        } else {
            throw new IllegalArgumentException(subject + " not found in list of subjects");
        }
    }

    public double calcAvgForSubject(String subject) {
        if (subjectsGrades.containsKey(subject)) {
            List<Double> grades = subjectsGrades.get(subject);
            if (grades.isEmpty()) {
                throw new IllegalArgumentException("No grades found for subject");
            }
            double subjectGradeSum = grades.stream().mapToDouble(Double::doubleValue).sum();
            return Math.round((subjectGradeSum / grades.size()) * 100.0) / 100.0;
        } else {
            throw new IllegalArgumentException("Subject not in subjects");
        }
    }

    public double calcAvgForAllSubjects() {
        List<Double> allGrades = new ArrayList<>();
        for (List<Double> grades : subjectsGrades.values()) {
            allGrades.addAll(grades);
        }
        if (allGrades.isEmpty()) {
            throw new IllegalArgumentException("No grades in gradebook");
        }
        double sum = allGrades.stream().mapToDouble(Double::doubleValue).sum();
        return Math.round((sum / allGrades.size()) * 100.0) / 100.0;
    }

    public List<String> getSubjects() {
        return new ArrayList<>(subjects);
    }

    public Map<String, List<Double>> getGrades() {
        return new HashMap<>(subjectsGrades);
    }
}
