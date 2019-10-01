package ProjectOcean.Model;

import java.util.*;

public class CoursePlanningSystem {

    private final Map<UUID, Course> courses;

    public CoursePlanningSystem() {
        this.courses = generateCourses();
    }

    /**
     * @return returns all courses stored
     */
    public Map<UUID, Course> getAllCourses() {
        return Collections.unmodifiableMap(courses);
    }

    /**
     * Creates a list of hard coded courses
     * @return returns a list full of courses
     */
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

    /**
     * @param id is a UUID for a specific course
     * @return returns the CourseCode for the specified UUID
     */
    public String getCourseCode(UUID id) {
        return courses.get(id).getCourseCode();
    }

    /**
     * @param id is a UUID for a specific course
     * @return returns the CourseName for the specified UUID
     */
    public String getCourseName(UUID id) {
        return courses.get(id).getCourseName();
    }

    /**
     * @return returns a List with all courses stored in CoursePlaningSystem
     */
    public List<UUID> getAllCoursesIDs() {
        List<UUID> idList = new ArrayList<>();

        Iterator it = courses.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            idList.add((UUID) pair.getKey());
        }

        return Collections.unmodifiableList(idList);
    }

    /**
     * @param id is a UUID for a specific course
     * @return returns the StudyPoints for the specified UUID
     */
    public String getStudyPoints(UUID id){
        return courses.get(id).getStudyPoints();
    }

    /**
     * @param id is a UUID for a specific course
     * @return returns the StudyPeriod for the specified UUID
     */
    public String getStudyPeriod(UUID id){
        return courses.get(id).getStudyPeriod();
    }

    /**
     * @param id is a UUID for a specific course
     * @return returns the Examinaot for the specified UUID
     */
    public String getExaminator(UUID id){
        return courses.get(id).getExaminator();
    }

    /**
     * @param id is a UUID for a specific course
     * @return returns the ExaminationMeans for the specified UUID
     */
    public String getExaminationMeans(UUID id){
        return courses.get(id).getExaminationMeans();
    }

    /**
     * @param id is a UUID for a specific course
     * @return returns the Language for the specified UUID
     */
    public String getLanguage(UUID id){
        return courses.get(id).getLanguage();
    }

    /**
     * @param id is a UUID for a specific course
     * @return returns a list of required courses for a specific course defined by a UUID
     */
    public List<UUID> getRequiredCourses(UUID id){
        Iterator<Course> iterator = courses.get(id).getRequiredCourses().iterator();
        List<UUID> uuids = new ArrayList<>();

        while(iterator.hasNext()) {
            uuids.add(iterator.next().getId());
        }
        return uuids;
    }

    /**
     * @param id is a UUID for a specific course
     * @return returns the CoursePMLink for the specified UUID
     */
    public String getCoursePMLink(UUID id){
        return courses.get(id).getCoursePMLink();
    }

    /**
     * @param id is a UUID for a specific course
     * @return returns the CourseDescription for the specified UUID
     */
    public String getCourseDescription(UUID id){
        return courses.get(id).getCourseDescription();
    }


}
