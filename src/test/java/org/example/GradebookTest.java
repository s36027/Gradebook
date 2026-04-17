package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import java.util.List;
import java.util.stream.Stream;

public class GradebookTest {
    private Gradebook gradebook;

    @BeforeEach
    public void setUp() {
        gradebook = new Gradebook();
    }

    @Test
    public void testAddSubject() {
        gradebook.addSubject("Physics");
        assertEquals(List.of("Physics"), gradebook.getSubjects());
    }

    @Test
    public void testAddGradeToSubject() {
        gradebook.addSubject("Math");
        gradebook.addGrade("Math", 5.0);
        assertEquals(List.of(5.0), gradebook.getGrades().get("Math"));
    }

    @TestFactory
    Stream<DynamicTest> dynamicTestsForSubjects() {
        List<String> testSubjects = List.of("Math", "Chemistry", "Biology");
        testSubjects.forEach(gradebook::addSubject);

        return gradebook.getSubjects().stream()
                .map(subject -> DynamicTest.dynamicTest("Test for subject: " + subject, () -> {
                    gradebook.addGrade(subject, 4.0);
                    gradebook.addGrade(subject, 3.0);
                    assertEquals(2, gradebook.getGrades().get(subject).size());
                    assertEquals(3.5, gradebook.calcAvgForSubject(subject));
                }));
    }

    @Test
    public void testCalcAvgForAllSubjects() {
        gradebook.addSubject("Math");
        gradebook.addSubject("Physics");
        gradebook.addGrade("Math", 4.0);
        gradebook.addGrade("Physics", 2.0);
        assertEquals(3.0, gradebook.calcAvgForAllSubjects());
    }

    @Test
    public void testAddGradeToNonExistentSubject() {
        assertThrows(IllegalArgumentException.class, () -> gradebook.addGrade("Geography", 4.0));
    }

    @Test
    public void testHistoryExclusion() {
        gradebook.addSubject("History");
        gradebook.addGrade("History", 5.0);
        assertTrue(gradebook.getGrades().get("History").isEmpty());
        assertThrows(IllegalArgumentException.class, () -> gradebook.calcAvgForSubject("History"));
    }

    @Test
    public void testAverageFromEmptyGradebook() {
        assertThrows(IllegalArgumentException.class, () -> gradebook.calcAvgForAllSubjects());
    }

    @Test
    public void testDuplicateSubjectPrevention() {
        gradebook.addSubject("Math");
        gradebook.addGrade("Math", 5.0);
        gradebook.addSubject("Math");
        assertEquals(1, gradebook.getSubjects().size());
        assertEquals(1, gradebook.getGrades().get("Math").size());
    }

    @Test
    public void testEncapsulationOfLists() {
        gradebook.addSubject("Math");
        List<String> subjects = gradebook.getSubjects();
        subjects.add("IT");
        assertFalse(gradebook.getSubjects().contains("IT"));
    }
}