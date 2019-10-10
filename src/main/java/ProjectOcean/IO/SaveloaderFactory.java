package ProjectOcean.IO;

public class SaveloaderFactory {

    public static ICourseSaveLoader createICourseSaveLoader(){
        return new CoursesSaverLoader();
    }

    public static IStudyPlanSaverLoader createIStudyPlanSaverLoader(){
        return new StudyPlanSaverLoader();
    }



}
