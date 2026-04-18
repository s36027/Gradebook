package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Test class for Gradebook logic.
 */
public final class GradebookTest {

    private Gradebook gradebook;
    private final double gradeFive = 5.0;
    private final double gradeFour = 4.0;
    private final double gradeThree = 3.0;
    private final double gradeThreeHalf = 3.5;

    /**
     * Sets up the test environment.
     */
    @BeforeEach
    public void setUp() {
        gradebook = new Gradebook();
    }

    /**
     * Tests adding a subject.
     */
    @Test
    public void testAddSubject() {
        gradebook.addSubject("Math");
        Assertions.assertTrue(gradebook.getSubjects().contains("Math"));
    }

    /**
     * Tests getting all subjects.
     */
    @Test
    public void testGetSubjects() {
        gradebook.addSubject("Math");
        gradebook.addSubject("Physics");
        List<String> subjects = gradebook.getSubjects();
        Assertions.assertEquals(2, subjects.size());
    }

    /**
     * Tests adding a grade to a subject.
     */
    @Test
    public void testAddGrade() {
        gradebook.addSubject("Math");
        gradebook.addGrade("Math", gradeFive);
        Assertions.assertEquals(gradeFive, gradebook.calcAvgForSubject("Math"));
    }

    /**
     * Factory for dynamic tests.
     * @return collection of dynamic tests.
     */
    @TestFactory
    public Collection<DynamicTest> dynamicTestsForSubjects() {
        return Arrays.asList(
                DynamicTest.dynamicTest("Test Math Avg", () -> {
                    gradebook.addSubject("Math");
                    gradebook.addGrade("Math", gradeFour);
                    gradebook.addGrade("Math", gradeThree);
                    Assertions.assertEquals(gradeThreeHalf,
                            gradebook.calcAvgForSubject("Math"));
                }),
                DynamicTest.dynamicTest("Test Physics Avg", () -> {
                    gradebook.addSubject("Physics");
                    gradebook.addGrade("Physics", gradeFour);
                    Assertions.assertEquals(gradeFour,
                            gradebook.calcAvgForSubject("Physics"));
                })
        );
    }

    /**
     * Tests exception for non-existent subject.
     */
    @Test
    public void testAddGradeToNonExistentSubject() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            gradebook.addGrade("Bio", gradeFive);
        });
    }

    /**
     * Tests average for subject with no grades.
     */
    @Test
    public void testAvgForSubjectWithNoGrades() {
        gradebook.addSubject("Chemistry");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            gradebook.calcAvgForSubject("Chemistry");
        });
    }
}
