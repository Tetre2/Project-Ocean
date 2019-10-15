package ProjectOcean.Model;

import ProjectOcean.IO.*;

import java.util.Collections;
import java.util.Observable;
import java.util.ArrayList;
import java.util.List;

/**
 * The model's main aggregate class acting like an interface for the views and controllers
 */
public class CoursePlanningSystem extends Observable {

    private List<Course> courses;
    private Student student;
    private static CoursePlanningSystem model;
    private static ICourseSaveLoader courseSaveLoader = SaveloaderFactory.createICourseSaveLoader();
    private static IStudyPlanSaverLoader studyPlanSaverLoader = SaveloaderFactory.createIStudyPlanSaverLoader();

    public static CoursePlanningSystem getInstance(){
        if(model == null){

            return model = new CoursePlanningSystem(getStudentFromStudyPlanSaverLoader(), getCoursesFromCourseLoader());
        }
        return model;
    }

    private CoursePlanningSystem(Student student, List<Course> courses) {
        this.courses = courses;
        this.student = student;
        setChanged();
        notifyObservers();
    }

    /**
     * @return returns all courses stored
     */
    public List<ICourse> getAllCourses() {
        return Collections.unmodifiableList(courses);
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
    public void addCourse(ICourse course, int year, int studyPeriod, int slot) {
        student.addCourse(course, year, studyPeriod,slot);
        setChanged();
        notifyObservers();
    }

    /**
     * Attempts to add the given course to the given year, study period and slot for the current student
     * @param year the year to add the course to
     * @param studyPeriod the study period to add the course to
     * @param slot the slot in which the course will be added
     */
    public void addCourse(int year, int studyPeriod, int slot){
        addCourse(year, studyPeriod, slot);
    }

    /**
     * Removes the given course in the given year and study period, for the current student
     * @param year the year to remove the course from
     * @param studyPeriod the study period to remove the course from
     */
    public void removeCourse(int year, int studyPeriod, int slot) {
        student.removeCourse(year, studyPeriod, slot);
        setChanged();
        notifyObservers();
    }

    /**
     * @param course is a Icourse for a specific course
     * @return returns the Course corresponding to the given UUID
     */
    public Course getCourse(ICourse course) {
        return (Course) course;
    }

    /**
     *
     * @param searchText: A string of search terms seperated by blankspaces
     * @return searchResult: A List<UUID> with the id of each course that matches, in the order that they are matched
     */
    public List<ICourse> executeSearch(String searchText) {
        String[] searchTerms = trimString(searchText);
        List<ICourse> searchResult = new ArrayList<>();
        matchCourseNameAndAddCourse(searchTerms, searchResult);
        matchCourseCodeAndAddCourse(searchTerms, searchResult);
        matchExaminerAndAddCourse(searchTerms, searchResult);
        return searchResult;
    }

    private String[] trimString(String searchText) {
        //Trims away unnecessary blankspaces, makes them lowercase and splits the terms into a array.
        searchText = searchText.trim();
        searchText = searchText.toLowerCase();
        searchText = searchText.trim().replaceAll(" +", " ");
        return searchText.split(" ");
    }

    private void matchCourseNameAndAddCourse(String[] searchTerms, List<ICourse> searchResult){
        //For each search term, searches through each courses course name for matches and if found adds the course
        // to search result.
        for(String s : searchTerms) {
            for(ICourse c : courses) {
                if(!(s.length()< 3) && c.getCourseName().toLowerCase().contains(s) && !searchResult.contains(c)) {
                    searchResult.add(c);
                }
            }
        }
    }

    private void matchCourseCodeAndAddCourse(String[] searchTerms, List<ICourse> searchResult) {
        //For each search term, searches through each courses course code for matches and if found
        // adds the course to search result.
        for(String s : searchTerms) {
            for(ICourse c : courses) {
                if(c.getCourseCode().toLowerCase().contains(s) && !searchResult.contains(c)) {
                    searchResult.add(c);
                }
            }
        }
    }

    private void matchExaminerAndAddCourse(String[] searchTerms, List<ICourse> searchResult) {
        //For each search term, searches through each courses examinor for matches and
        // if found adds the course to search result.
        for(String s : searchTerms) {
            for(ICourse c : courses) {
                if(c.getExaminer().toLowerCase().contains(s) && !searchResult.contains(c)) {
                    searchResult.add(c);
                }
            }
        }
    }
      
     /**
     * Adds a course to the workspace
     * @param course is a UUID for a specific course
     */
    public void addCourseToWorkspace(ICourse course){
        student.addCourseToWorkspace((Course) course);
        setChanged();
        notifyObservers();
    }
    /**
     * Gets a list of all courses in the workspace by their id.
     * @return a list of UUID:s för the courses in workspace.
     */
    public List<ICourse> getCoursesInWorkspaceIDs(){
        List<ICourse> idList = new ArrayList<>();
        for (Course c : student.getAllCoursesInWorkspace()) {
            idList.add(c);
        }
        return idList;
    }

    /**
     * Removes a course from the workspace
     * @param course is a UUID for a specific course
     */
    public void removeCourseFromWorkspace(ICourse course) {
        student.removeCourseFromWorkspace((Course) course);
        setChanged();
        notifyObservers();
    }

    private static List<Course> getCoursesFromCourseLoader(){
        List<Course> courses = null;
        try {
            courses = courseSaveLoader.loadCoursesFile();
        } catch (CoursesNotFoundException e) {
            courseSaveLoader.createCoursesFile();
        }

        try {
            courses = courseSaveLoader.loadCoursesFile();
        } catch (CoursesNotFoundException e) {
            e.printStackTrace();
        }
        return courses;
    }

    private static Student getStudentFromStudyPlanSaverLoader(){
        Student student = null;

        try {
            student = studyPlanSaverLoader.loadStudent();
        } catch (StudyPlanNotFoundException e) {
            studyPlanSaverLoader.createNewStudentFile();
        }

        try {
            student = studyPlanSaverLoader.loadStudent();
        } catch (StudyPlanNotFoundException e) {
            e.printStackTrace();
        }

        return student;
    }

    /**
     * Saves the student and its contents
     */
    public void saveStudentToJSON(){
        studyPlanSaverLoader.saveStudyplans(student);
    }

}
