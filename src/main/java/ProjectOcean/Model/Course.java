package ProjectOcean.Model;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Course {

    private final UUID id;
    private final String courseCode;
    private final String courseName;
    private final float studyPoints;
    private final int studyPeriod;
    private final String examinator;
    private final String examinationMeans;
    private final String language;
    private final List<Course> requiredCourses;
    private final String coursePMLink;
    private final String courseDescription;

    public Course(String courseCode, String courseName, float studyPoints, int studyPeriod, String examinator, String examinationMeans, String language, List<Course> requiredCourses, String coursePMLink, String courseDescription) {
        this.id = UUID.randomUUID();
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.studyPoints = studyPoints;
        this.studyPeriod = studyPeriod;
        this.examinator = examinator;
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

    public String getExaminator() {
        return examinator;
    }

    public String getExaminationMeans() {
        return examinationMeans;
    }

    public String getLanguage() {
        return language;
    }

    public List<Course> getRequiredCourses() {
        return Collections.unmodifiableList(requiredCourses);
    }

    public String getCoursePMLink() {
        return coursePMLink;
    }

    public String getCourseDescription() {
        return courseDescription;
    }
}
