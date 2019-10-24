package ProjectOcean.IO;

public class SaverLoaderFactory {

    public static CourseLoader createICourseSaveLoader(){
        return new CourseLoader();
    }

    public static StudyPlanSaverLoader createIStudyPlanSaverLoader(){
        return new StudyPlanSaverLoader();
    }

}
