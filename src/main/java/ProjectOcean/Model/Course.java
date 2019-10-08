package ProjectOcean.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a course in the model
 */
public class Course implements ICourse{

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

    @Override
    public String getCourseName() {
        return courseName;
    }

    @Override
    public String getCourseCode() {
        return courseCode;
    }

    @Override
    public String getStudyPoints() {
        return String.valueOf(studyPoints);
    }

    @Override
    public String getStudyPeriod() {
        return String.valueOf(studyPeriod);
    }

    @Override
    public String getExaminator() {
        return examinator;
    }

    @Override
    public String getExaminationMeans() {
        return examinationMeans;
    }

    @Override
    public String getLanguage() {
        return language;
    }

    @Override
    public List<String> getRequiredCourses() {
        List<String> requiredCourses = new ArrayList<>();

        for (Course course : this.requiredCourses) {
            requiredCourses.add(course.getCourseName());
        }

        return Collections.unmodifiableList(requiredCourses);
    }

    @Override
    public String getCoursePMLink() {
        return coursePMLink;
    }

    @Override
    public String getCourseDescription() {
        return courseDescription;
    }
}
