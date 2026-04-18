package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Gradebook logic.
 */
public final class Gradebook {
    private final Map<String, List<Double>> subjects = new HashMap<>();
    private final double multiplier = 100.0;

    /**
     * Adds subject.
     * @param subject name.
     */
    public void addSubject(final String subject) {
        if (subject == null || subject.isEmpty()) {
            throw new IllegalArgumentException("Empty subject");
        }
        subjects.putIfAbsent(subject, new ArrayList<>());
    }

    /**
     * Adds grade.
     * @param subject name.
     * @param grade value.
     */
    public void addGrade(final String subject, final double grade) {
        if (!subjects.containsKey(subject)) {
            throw new IllegalArgumentException("No subject");
        }
        subjects.get(subject).add(grade);
    }

    /**
     * Calculates avg.
     * @param subject name.
     * @return average.
     */
    public double calcAvgForSubject(final String subject) {
        List<Double> grades = subjects.get(subject);
        if (grades == null || grades.isEmpty()) {
            throw new IllegalArgumentException("No grades");
        }
        double sum = 0;
        for (double g : grades) {
            sum += g;
        }
        double avg = sum / grades.size();
        return Math.round(avg * multiplier) / multiplier;
    }

    /**
     * Gets subjects.
     * @return list.
     */
    public List<String> getSubjects() {
        return new ArrayList<>(subjects.keySet());
    }
}

