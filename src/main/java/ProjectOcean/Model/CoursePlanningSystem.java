package ProjectOcean.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CoursePlanningSystem {

    private final List<Course> courses;

    public CoursePlanningSystem() {
        this.courses = generateCourses();
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }

    public List<Course> generateCourses() {
        List<Course> courses = new ArrayList<Course>();
        courses.add(new Course("DAT017","Maskinorienterad programmering", 7.5f));
        courses.add(new Course("EDA433","Grundläggande Datorteknik", 7.5f));
        courses.add(new Course("MVE045","Matematisk Analys", 7.5f));
        courses.add(new Course("TMV206","Linjär Algebra", 7.5f));
        courses.add(new Course("TDA552","Objektorienterad Programmering och Design", 7.5f));
        return courses;
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

    public List<UUID> getAllCoursesIDs() {
        List<UUID> idList = new ArrayList<UUID>();
        for (Course c : courses) {
            idList.add(c.getId());
        }
        return idList;
    }

    public String getStudyPoints(UUID uuid){
        return null;
    }

    public String getStudyPeriod(UUID uuid){
        return null;
    }

    public String getExaminator(UUID uuid){
        return null;
    }

    public String getExaminationMeans(UUID uuid){
        return null;
    }

    public String getLanguage(UUID uuid){
        return null;
    }

    public List<UUID> getRequiredCourses(UUID uuid){
        return null;
    }

    public String getCoursePMLink(UUID uuid){
        return null;
    }

    public String getCourseDescription(UUID uuid){
        return null;
    }


}
