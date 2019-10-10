package ProjectOcean.IO;

import ProjectOcean.Model.Course;

import java.util.Map;
import java.util.UUID;

public interface ICourseSaveLoader {

    Map<UUID, Course> tryToLoadCoursesFileIfNotCreateNewFile();

}
