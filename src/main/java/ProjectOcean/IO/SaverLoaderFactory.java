package ProjectOcean.IO;

public class SaverLoaderFactory {

    public static ICourseLoader createICourseSaveLoader(){
        return new CourseLoader();
    }

    public static IStudyPlanSaverLoader createIStudyPlanSaverLoader(){
        return new StudyPlanSaverLoader();
    }

}
