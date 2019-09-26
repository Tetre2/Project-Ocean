package ProjectOcean.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CoursePlanningSystem implements IModelCourseListIcon, IModelSearchBrowse{

    private List<Course> courses;

    public CoursePlanningSystem() {
        this.courses = new CourseInfo().getAllCourses();
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }

    //Three methods that searches for course information based on UUID
    public String getCourseCode(UUID id) {
        for(Course c:courses){
            if(c.getId() == id) {
                return c.getCourseCode();
            }
        }
        return "000-000";
    }

    public String getCourseName(UUID id) {
        for(Course c:courses){
            if(c.getId() == id) {
                return c.getName();
            }
        }
        return "No matching course id";
    }

    public String getCourseStudyPoints(UUID id) {
        for(Course c:courses){
            if(c.getId() == id) {
                return c.getStudyPoints() + "";
            }
        }
        return "0";
    }

    //IModelSearchBrowse implementation
    public List<UUID> getAllCoursesIDs() {
        List<UUID> idList = new ArrayList<UUID>();
        for (Course c : courses) {
            idList.add(c.getId());
        }
        return idList;
    }
}
