package ProjectOcean.Model;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Represents a course in the model
 */
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

    /**
     * @return this course UUID
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * @return the course UUID as a String
     */
    public String getIdToString() {
        return this.id.toString();
    }

    /**
     * @return this course's name
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @return this course's code
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * @return this course's study points
     */
    public String getStudyPoints() {
        return String.valueOf(studyPoints);
    }

    /**
     * @return this course's study period
     */
    public String getStudyPeriod() {
        return String.valueOf(studyPeriod);
    }

    /**
     * @return this course's examinator
     */
    public String getExaminator() {
        return examinator;
    }

    /**
     * @return this course's examination forms
     */
    public String getExaminationMeans() {
        return examinationMeans;
    }

    /**
     * @return this course's language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @return this course's required courses
     */
    public List<Course> getRequiredCourses() {
        return Collections.unmodifiableList(requiredCourses);
    }

    /**
     * @return this course's PM link
     */
    public String getCoursePMLink() {
        return coursePMLink;
    }

    /**
     * @return this course's description
     */
    public String getCourseDescription() {
        return courseDescription;
    }
}
