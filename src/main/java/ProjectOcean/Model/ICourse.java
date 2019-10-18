package ProjectOcean.Model;

import java.util.List;

public interface ICourse {

    /**
     * @return this course's name
     */
    String getCourseName();

    /**
     * @return this course's code
     */
    String getCourseCode();

    /**
     * @return this course's study points
     */
    String getStudyPoints();
    /**
     * @return this course's study period
     */
    String getStudyPeriod();

    /**
     * @return this course's examinator
     */
    String getExaminer();

    /**
     * @return this course's examination forms
     */
    String getExaminationMeans();

    /**
     * @return this course's language
     */
    String getLanguage();

    /**
     * @return this course's required courses
     */
    List<String> getRequiredCourses();

    /**
     * @return this course's PM link
     */
    String getCoursePMLink();

    /**
     * @return this course's description
     */
    String getCourseDescription();

    /**
     * @return this course's course types
     */
    List<String> getCourseTypes();


}
