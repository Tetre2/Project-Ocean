package ProjectOcean.IO;

public class SaveloaderFactory {

    public static ICourseLoader createICourseSaveLoader(){
        return new CourseLoader();
    }

    public static IStudyPlanSaverLoader createIStudyPlanSaverLoader(){
        return new StudyPlanSaverLoader();
    }



}
