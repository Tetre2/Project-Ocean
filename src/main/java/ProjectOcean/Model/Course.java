package ProjectOcean.Model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a course in the model
 */
public class Course {

    private final UUID id;
    private final String courseCode;
    private final String courseName;
    private final String studyPoints;
    private final String studyPeriod;
    private final String examiner;
    private final String examinationMeans;
    private final String language;
    private final List<String> requiredCourses;
    private final String coursePMLink;
    private final String courseDescription;

    public Course(UUID uuid, String courseCode, String courseName, String studyPoints, String studyPeriod, String examiner, String examinationMeans, String language, List<String> requiredCourses, String coursePMLink, String courseDescription) {
        this.id = uuid;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.studyPoints = studyPoints;
        this.studyPeriod = studyPeriod;
        this.examiner = examiner;
        this.examinationMeans = examinationMeans;
        this.language = language;
        this.requiredCourses = requiredCourses;
        this.coursePMLink = coursePMLink;
        this.courseDescription = courseDescription;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + courseName + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", studyPoints=" + studyPoints +
                '}';
    }

    public UUID getId() {
        return this.id;
    }

    public String getIdToString() {
        return this.id.toString();
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getStudyPoints() {
        return String.valueOf(studyPoints);
    }

    public String getStudyPeriod() {
        return String.valueOf(studyPeriod);
    }

    public String getExaminer() {
        return examiner;
    }

    public String getExaminationMeans() {
        return examinationMeans;
    }

    public String getLanguage() {
        return language;
    }

    public List<String> getRequiredCourses() {
        return Collections.unmodifiableList(requiredCourses);
    }

    public String getCoursePMLink() {
        return coursePMLink;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id.toString().equals(course.id.toString()) &&
                courseCode.equals(course.courseCode) &&
                courseName.equals(course.courseName) &&
                studyPoints.equals(course.studyPoints) &&
                studyPeriod.equals(course.studyPeriod) &&
                examiner.equals(course.examiner) &&
                examinationMeans.equals(course.examinationMeans) &&
                language.equals(course.language) &&
                requiredCourses.equals(course.requiredCourses) &&
                coursePMLink.equals(course.coursePMLink) &&
                courseDescription.equals(course.courseDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseCode, courseName, studyPoints, studyPeriod, examiner, examinationMeans, language, requiredCourses, coursePMLink, courseDescription);
    }
}
