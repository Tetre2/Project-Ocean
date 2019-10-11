package ProjectOcean.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a course in the model
 */
public class Course implements ICourse {

    private final int studyPeriod;
    private final CourseDetails cDetails;
    private final CourseInfo cInfo;
    private final CourseAccessibility cAccessibility;

    public Course(int studyPeriod, CourseDetails cDeatils, CourseInfo cInfo, CourseAccessibility cAccessibility) {
        this.studyPeriod = studyPeriod;
        this.cDetails = cDeatils;
        this.cInfo = cInfo;
        this.cAccessibility = cAccessibility;
    }

    @Override
    public String toString() {
        return cInfo.toString();
    }

    /**
     * @return this course's PM link
     */
    @Override
    public String getCoursePMLink() {
        return cDetails.getCoursePMLink();
    }

    /**
     * @return this course's description
     */
    @Override
    public String getCourseDescription() {
        return cDetails.getCourseDescription();
    }

    /**
     * @return this course's required courses
     */
    @Override
    public List<String> getRequiredCourses() {
        return cDetails.getRequiredCourses();
    }

    /**
     * @return this course's name
     */
    @Override
    public String getCourseName() {
        return cInfo.getCourseName();
    }

    /**
     * @return this course's code
     */
    @Override
    public String getCourseCode() {
        return cInfo.getCourseCode();
    }

    /**
     * @return this course's study period
     */
    public String getStudyPeriod() {
        return String.valueOf(studyPeriod);
    }

    /**
     * @return this course's study points
     */
    @Override
    public String getStudyPoints() {
        return cInfo.getStudyPoints();
    }

    /**
     * @return this course's examinator
     */
    @Override
    public String getExaminator() {
        return cAccessibility.getExaminator();
    }

    /**
     * @return this course's examination forms
     */
    @Override
    public String getExaminationMeans() {
        return cAccessibility.getExaminationMeans();
    }

    /**
     * @return this course's language
     */
    @Override
    public String getLanguage() {
        return cAccessibility.getLanguage();
    }
}
