package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

/**
 * Tests.
 */
public final class GradebookTest {
    /** Gradebook instance. */
    private Gradebook gradebook;
    /** Test grade. */
    private final double gradeFive = 5.0;

    /**
     * Setup.
     */
    @BeforeEach
    public void setUp() {
        gradebook = new Gradebook();
    }

    /**
     * Test add.
     */
    @Test
    public void testAddSubject() {
        gradebook.addSubject("Math");
        Assertions.assertTrue(gradebook.getSubjects().contains("Math"));
    }

    /**
     * Test list.
     */
    @Test
    public void testGetSubjects() {
        gradebook.addSubject("Math");
        gradebook.addSubject("Physics");
        List<String> subjects = gradebook.getSubjects();
        Assertions.assertEquals(2, subjects.size());
    }

    /**
     * Test average.
     */
    @Test
    public void testAddGrade() {
        gradebook.addSubject("Math");
        gradebook.addGrade("Math", gradeFive);
        Assertions.assertEquals(gradeFive, gradebook.calcAvgForSubject("Math"));
    }
}

