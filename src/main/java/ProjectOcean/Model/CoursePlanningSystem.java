package ProjectOcean.Model;

import java.util.*;

public class CoursePlanningSystem {

    private final Map<UUID, Course> courses;

    public CoursePlanningSystem() {
        this.courses = generateCourses();
    }

    public Map<UUID, Course> getAllCourses() {
        return new HashMap<>(courses);
    }

    public Map<UUID, Course> generateCourses() {
        Map courses = new HashMap<UUID, Course>();

        Course course = new Course("DAT017","Maskinorienterad programmering", 7.5f);
        courses.put(course.getId(), course);

        course = new Course("EDA433","Grundläggande Datorteknik", 7.5f);
        courses.put(course.getId(), course);

        course = new Course("MVE045","Matematisk Analys", 7.5f);
        courses.put(course.getId(), course);

        course = new Course("TMV206","Linjär Algebra", 7.5f);
        courses.put(course.getId(), course);

        course = new Course("TDA552","Objektorienterad Programmering och Design", 7.5f);
        courses.put(course.getId(),course);

        return courses;
    }

    //Three methods that searches for course information based on UUID
    public String getCourseCode(UUID id) {
        return courses.get(id).getCourseCode();
    }

    public String getCourseName(UUID id) {
        return courses.get(id).getCourseName();
    }

    public String getCourseStudyPoints(UUID id) {
        return courses.get(id).getStudyPoints();
    }

    public List<UUID> getAllCoursesIDs() {
        List<UUID> idList = new ArrayList<>();

        Iterator it = courses.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            idList.add((UUID) pair.getKey());
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
