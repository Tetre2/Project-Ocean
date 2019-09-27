package ProjectOcean.Model;

import java.util.*;

public class CoursePlanningSystem {

    private final Map<UUID, Course> courses;

    public CoursePlanningSystem() {
        this.courses = generateCourses();
    }

    public Map<UUID, Course> getAllCourses() {
        return Collections.unmodifiableMap(courses);
    }

    public Map<UUID, Course> generateCourses() {
        Map courses = new HashMap<UUID, Course>();

        Course course = new Course("DAT017","Maskinorienterad programmering", 7.5f, 1, "Joakim hacht", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        courses.put(course.getId(), course);

        course = new Course("EDA433","Grundläggande Datorteknik", 7.5f, 2, "Rolf Söderström", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        courses.put(course.getId(), course);

        /*course = new Course("MVE045","Matematisk Analys", 7.5f);
        courses.put(course.getId(), course);

        course = new Course("TMV206","Linjär Algebra", 7.5f);
        courses.put(course.getId(), course);

        course = new Course("TDA552","Objektorienterad Programmering och Design", 7.5f);
        courses.put(course.getId(),course);*/

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

        return Collections.unmodifiableList(idList);
    }

    public String getStudyPoints(UUID id){
        return courses.get(id).getStudyPoints();
    }

    public String getStudyPeriod(UUID id){
        return courses.get(id).getStudyPoints();
    }

    public String getExaminator(UUID id){
        return courses.get(id).getExaminator();
    }

    public String getExaminationMeans(UUID id){
        return courses.get(id).getExaminationMeans();
    }

    public String getLanguage(UUID id){
        return courses.get(id).getLanguage();
    }

    public List<UUID> getRequiredCourses(UUID id){
        Iterator<Course> iterator = courses.get(id).getRequiredCourses().iterator();
        List<UUID> uuids = new ArrayList<>();

        while(iterator.hasNext()) {
            uuids.add(iterator.next().getId());
        }
        return uuids;
    }

    public String getCoursePMLink(UUID id){
        return courses.get(id).getCoursePMLink();
    }

    public String getCourseDescription(UUID id){
        return courses.get(id).getCourseDescription();
    }


}
