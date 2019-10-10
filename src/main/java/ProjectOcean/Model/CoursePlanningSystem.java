package ProjectOcean.Model;

import ProjectOcean.IO.ICourseSaveLoader;
import ProjectOcean.IO.IStudyPlanSaverLoader;
import ProjectOcean.IO.SaveloaderFactory;

import java.util.*;

/**
 * Represents the aggregate of the model
 */
public class CoursePlanningSystem extends Observable {

    private Student student;
    private final Map<UUID, Course> courses;
    private static CoursePlanningSystem model;
    private static ICourseSaveLoader courseSaveLoader = SaveloaderFactory.createICourseSaveLoader();
    private static IStudyPlanSaverLoader studyPlanSaverLoader = SaveloaderFactory.createIStudyPlanSaverLoader();

    public static CoursePlanningSystem getInstance(){
        if(model == null){
            return model = new CoursePlanningSystem(studyPlanSaverLoader.tryToLoadFileIfNotCreateNewFile(), courseSaveLoader.loadCourses());
        }
        return model;
    }

    private CoursePlanningSystem(Student student, Map<UUID, Course> courses) {
        this.courses = courses;
        this.student = student;
        setChanged();
        notifyObservers();
    }

    /**
     * @return returns all courses stored
     */
    public Map<UUID, Course> getAllCourses() {
        return Collections.unmodifiableMap(courses);
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
    public void removeCourse(Course course, int year, int studyPeriod) {
        student.removeCourse(course, year, studyPeriod);
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
        return courses.get(id).getExaminer();
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
    public List<String> getRequiredCourses(UUID id){
        return courses.get(id).getRequiredCourses();
    }

    /**
     * @param id is a UUID for a specific course
     * @return returns the CoursePMLink for the specified UUID
     */
    public String getCoursePMLink(UUID id){
        return courses.get(id).getCoursePMLink();
    }

    /**
     *
     * @param searchText: A string of search terms seperated by blankspaces
     * @return searchResult: A List<UUID> with the id of each course that matches, in the order that they are matched
     */
    public List<UUID> executeSearch(String searchText) {
        String[] searchTerms = trimString(searchText);
        List<UUID> searchResult = new ArrayList<>();
        matchCourseNameAndAdd(searchTerms, searchResult);
        matchCourseCodeAndAdd(searchTerms, searchResult);
        matchExaminorAndAdd(searchTerms, searchResult);
        return searchResult;
    }

    private String[] trimString(String searchText) {
        //Trims away unnecessary blankspaces, makes them lowercase and splits the terms into a array.
        searchText = searchText.trim();
        searchText = searchText.toLowerCase();
        searchText = searchText.trim().replaceAll(" +", " ");
        return searchText.split(" ");
    }

    private void matchCourseNameAndAdd(String[] searchTerms, List<UUID> searchResult){
        //For each search term, searches through each courses course name for matches and if found adds the course
        // to search result.
        for(String s : searchTerms) {
            for(Course c : courses.values()) {
                if(!(s.length()< 3) && c.getCourseName().toLowerCase().contains(s) && !searchResult.contains(c.getId())) {
                    searchResult.add(c.getId());
                }
            }
        }
    }

    private void matchCourseCodeAndAdd(String[] searchTerms, List<UUID> searchResult) {
        //For each search term, searches through each courses course code for matches and if found
        // adds the course to search result.
        for(String s : searchTerms) {
            for(Course c : courses.values()) {
                if(c.getCourseCode().toLowerCase().contains(s) && !searchResult.contains(c.getId())) {
                    searchResult.add(c.getId());
                }
            }
        }
    }

    private void matchExaminorAndAdd(String[] searchTerms, List<UUID> searchResult) {
        //For each search term, searches through each courses examinor for matches and
        // if found adds the course to search result.
        for(String s : searchTerms) {
            for(Course c : courses.values()) {
                if(c.getExaminer().toLowerCase().contains(s) && !searchResult.contains(c.getId())) {
                    searchResult.add(c.getId());
                }
            }
        }
    }
      
     /**
     * Adds a course to the workspace
     * @param id is a UUID for a specific course
     */
    public void addCourseToWorkspace(UUID id){
        student.addCourseToWorkspace(courses.get(id));
        setChanged();
        notifyObservers();
    }

    /**
     * Gets a list of all courses in the workspace by their id.
     * @return a list of UUID:s för the courses in workspace.
     */
    public List<UUID> getCoursesInWorkspaceIDs(){
        List<UUID> idList = new ArrayList<UUID>();
        for (Course c : student.getAllCoursesInWorkspace()) {
            idList.add(c.getId());
        }
        return idList;
    }

    /**
     * Removes a course from the workspace
     * @param id is a UUID for a specific course
     */
    public void removeCourseFromWorkspace(UUID id) {
        student.removeCourseFromWorkspace(courses.get(id));
        setChanged();
        notifyObservers();
    }

    /**
     * @param id is a UUID for a specific course
     * @return returns the CourseDescription for the specified UUID
     */
    public String getCourseDescription(UUID id){
        return courses.get(id).getCourseDescription();
    }

    public Course getCourse(UUID id) {
        return courses.get(id);
    }

    /**
     * Saves the student and its contents
     */
    public void saveStudentToJSON(){
        studyPlanSaverLoader.saveStudyplans(student);
    }

}
