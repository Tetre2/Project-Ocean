package ProjectOcean.Model;

import java.util.List;

public interface ICourse {

    String getCourseName();

    String getCourseCode();

    String getStudyPoints();

    String getStudyPeriod();

    String getExaminer();

    String getExaminationMeans();

    String getLanguage();

    List<String> getRequiredCourses();

    String getCoursePMLink();

    String getCourseDescription();

    String getCourseTypes();


}
