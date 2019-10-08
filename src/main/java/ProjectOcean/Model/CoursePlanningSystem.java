package ProjectOcean.Model;

import java.util.*;

/**
 * The model's main aggregate class acting like an interface for the views and controllers
 */
public class CoursePlanningSystem extends Observable {

    private Student student;
    private Workspace workspace;
    private final Map<UUID, Course> courses;

    public CoursePlanningSystem() {
        this.courses = generateCourses();
        this.workspace = new Workspace();
        this.student = new Student();
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
     * @return the current student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Attempts to add the given course to the given year, study period and slot for the current student
     * @param course the course to be added
     * @param year the year to add the course to
     * @param studyPeriod the study period to add the course to
     * @param slot the slot in which the course will be added
     */
    public void addCourse(Course course, int year, int studyPeriod, int slot) {
        student.addCourse(course, year, studyPeriod,slot);
        setChanged();
        notifyObservers();
    }
    /**
     * Attempts to add the given course to the given year, study period and slot for the current student
     * @param id the UUID of the course to be added
     * @param year the year to add the course to
     * @param studyPeriod the study period to add the course to
     * @param slot the slot in which the course will be added
     */
    public void addCourse(UUID id, int year, int studyPeriod, int slot){
        addCourse(getCourse(id), year, studyPeriod, slot);
    }
    /**
     * Removes the given course in the given year and study period, for the current student
     * @param course the course to be removed
     * @param year the year to remove the course from
     * @param studyPeriod the study period to remove the course from
     */
    public void removeCourse(Course course, int year, int studyPeriod, int slot) {
        student.removeCourse(course, year, studyPeriod, slot);
        setChanged();
        notifyObservers();
    }
    /**
     * Removes the given course in the given year and study period, for the current student
     * @param id the UUID of the course to be added
     * @param year the year to remove the course from
     * @param studyPeriod the study period to remove the course from
     */
    public void removeCourse(UUID id, int year, int studyPeriod, int slot){
        removeCourse(getCourse(id), year, studyPeriod, slot);
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

    /**
     * @param id is a UUID for a specific course
     * @return returns the Course corresponding to the given UUID
     */
    public Course getCourse(UUID id) {
        return courses.get(id);
    }
    /**
     * Adds a course to the workspace
     * @param id is a UUID for a specific course
     */
    public void addCourseToWorkspace(UUID id){
        workspace.addCourse(courses.get(id));
        setChanged();
        notifyObservers();
    }
    /**
     * Gets a list of all courses in the workspace by their id.
     * @return a list of UUID:s för the courses in workspace.
     */
    public List<UUID> getCoursesInWorkspaceIDs(){
        List<UUID> idList = new ArrayList<UUID>();
        for (Course c : workspace.getAllCourses()) {
            idList.add(c.getId());
        }
        return idList;
    }

    /**
     * Removes a course from the workspace
     * @param id is a UUID for a specific course
     */
    public void removeCourseFromWorkspace(UUID id) {
        workspace.removeCourse(courses.get(id));
        setChanged();
        notifyObservers();
    }
}
