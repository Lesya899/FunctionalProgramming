package stream;


import java.util.List;
import java.util.Objects;

public class Students {
    private String firstName;
    private String lastName;
    private int courseNumber;
    private List<Integer> academicGrades;

    public Students(String firstName, String lastName, int courseNumber, List<Integer> academicGrades) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.courseNumber = courseNumber;
        this.academicGrades = academicGrades;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public List<Integer> getAcademicGrades() {
        return academicGrades;
    }

    @Override
    public String toString() {
        return "Students{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", courseNumber=" + courseNumber +
                ", academicGrades=" + academicGrades +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Students students = (Students) o;
        return courseNumber == students.courseNumber && Objects.equals(firstName, students.firstName) && Objects.equals(lastName, students.lastName) && Objects.equals(academicGrades, students.academicGrades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, courseNumber, academicGrades);
    }
}

