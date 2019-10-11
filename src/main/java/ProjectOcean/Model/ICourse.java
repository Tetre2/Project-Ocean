package ProjectOcean.Model;

import java.util.Collections;
import java.util.List;

public interface ICourse {

    public String getCourseName();

    public String getCourseCode();

    public String getStudyPoints();

    public String getStudyPeriod();

    public String getExaminator();

    public String getExaminationMeans();

    public String getLanguage();

    public List<String> getRequiredCourses();

    public String getCoursePMLink();

    public String getCourseDescription();


}
