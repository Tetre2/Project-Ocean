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

    @Override
    public String getStudyPeriod() {
        return String.valueOf(studyPeriod);
    }

    @Override
    public String getCoursePMLink() {
        return cDetails.getCoursePMLink();
    }

    @Override
    public String getCourseDescription() {
        return cDetails.getCourseDescription();
    }

    @Override
    public List<String> getRequiredCourses() {
        return cDetails.getRequiredCourses();
    }

    @Override
    public String getCourseName() {
        return cInfo.getCourseName();
    }

    @Override
    public String getCourseCode() {
        return cInfo.getCourseCode();
    }

    @Override
    public String getStudyPoints() {
        return cInfo.getStudyPoints();
    }

    @Override
    public String getExaminator() {
        return cAccessibility.getExaminator();
    }

    @Override
    public String getExaminationMeans() {
        return cAccessibility.getExaminationMeans();
    }

    @Override
    public String getLanguage() {
        return cAccessibility.getLanguage();
    }

}
