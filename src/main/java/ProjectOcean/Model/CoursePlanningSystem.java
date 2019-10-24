package ProjectOcean.Model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

/**
 * The model's main aggregate class acting like an interface for the views and controllers
 */
public class CoursePlanningSystem extends Observable {

    private final List<Course> courses;
    private final Student student;
    private static CoursePlanningSystem model;

    public static CoursePlanningSystem getInstance(){
        if(model == null){

            return model = new CoursePlanningSystem();
        }
        return model;
    }

    private CoursePlanningSystem() {
        this.courses = new ArrayList<>();
        this.student = new Student();
        setChanged();
        notifyObservers();
    }

    /**
     * @return all years in the student's current study plan as IYears.
     * Instead returns a list of null if current study plan doesn't exist.
     */
    public List<IYear> getYears(){
        if(student.getCurrentStudyPlan() == null)
            return Collections.unmodifiableList(new ArrayList<Year>());
        List<Year> years = student.getCurrentStudyPlan().getYears();

        return Collections.unmodifiableList(new ArrayList<>(years));
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
     * @return A list of id:s of all study plans
     */
    public List<Integer> getStudyPlanIds() {
        return student.getStudyPlanIds();
    }

    /**
     * Adds a study plan
     */
    public void addStudyPlan() {
        StudyPlan studyPlan = new StudyPlan();
        student.addStudyPlan(studyPlan);
        setCurrentStudyPlan(studyPlan.getId());
    }

    /**
     * Set a given study plan as current, active.
     * @param studyPlanID A study plan to assign as current.
     */
    public void setCurrentStudyPlan(Integer studyPlanID) {
        student.setFirstStudyPlanAsCurrent(studyPlanID);
        setChanged();
        notifyObservers();
    }

    /**
     * Attempts to add the given course to the given year, study period and slot for the current student
     * @param course the course to be added
     * @param year the year to add the course to
     * @param studyPeriod the study period to add the course to
     * @param slot the slot in which the course will be added
     */
    public void addCourse(ICourse course, int year, int studyPeriod, int slot) {
        student.addCourse(courses.get(courses.indexOf(course)), year, studyPeriod,slot);
        setChanged();
        notifyObservers();
    }

    /**
     * Creates a new year instance and adds it to the list of years, in the study plan
     */
    public void addYear() {
        student.addYear();
        setChanged();
        notifyObservers();
    }

    /**
     * Removes the year specified by the index, in the study plan
     * @param id the year to be removed
     */
    public void removeYear(int id) {
        student.removeYear(id);
        setChanged();
        notifyObservers();
    }

    /**
     * Removes the given course in the given year and study period, for the current student
     * @param yearID the year to remove the course from
     * @param studyPeriod the study period to remove the course from
     */
    public void removeCourse(int yearID, int studyPeriod, int slot) {
        student.removeCourse(yearID, studyPeriod, slot);
        setChanged();
        notifyObservers();
    }

    /**
     *
     * @param searchText: A string of search terms separated by blank spaces
     * @return searchResult: A List<UUID> with the id of each course that matches, in the order that they are matched
     */
    public List<ICourse> executeSearch(String searchText) {
        String[] searchTerms = trimString(searchText);
        List<ICourse> searchResult = new ArrayList<>();
        matchCourseNameAndAddCourse(searchTerms, searchResult);
        matchCourseCodeAndAddCourse(searchTerms, searchResult);
        matchExaminerAndAddCourse(searchTerms, searchResult);
        matchCourseTypeAndAddCourse(searchTerms, searchResult);
        return searchResult;
    }

    private String[] trimString(String searchText) {
        //Trims away unnecessary blank spaces, makes them lowercase and splits the terms into a array.
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
                if(c.getCourseName().toLowerCase().contains(s) && !searchResult.contains(c)) {
                    searchResult.add(c);
                }
            }
        }
    }

    private void matchCourseTypeAndAddCourse(String[] searchTerms, List<ICourse> searchResult) {
        for (ICourse c : courses) {
            //Makes a list of course types that is lower case for this course c.
            List<String> courseTypesLowerString = new ArrayList<>();
            for(String courseType : c.getCourseTypes()) {
                courseTypesLowerString.add(courseType.toLowerCase());
            }
            //Goes through the search terms and see if they match the lower-case course type list
            //for this course
            for(String s : searchTerms) {
                if(courseTypesLowerString.contains(s)&& !searchResult.contains(c)) {
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
        //For each search term, searches through each courses examiner for matches and
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
     * This method sends a message to all the views that they should update.
     */
    public void update(){
        setChanged();
        notifyObservers();
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
     * Gets a list of all courses in the workspace.
     * @return a list of ICourses in workspace.
     */
    public List<ICourse> getCoursesInWorkspace(){
        List<ICourse> idList = new ArrayList<>();
        idList.addAll(student.getAllCoursesInWorkspace());
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

    /**
     * Removes all courses
     */
    public void removeAllCoursesInWorkspace(){
        student.removeAllCoursesInWorkspace();
    }

    /**
     * @param studyPlans is the list of studyPlans to be set in the model
     */
    public void setStudyPlans(List<StudyPlan> studyPlans) {
        student.setStudyPlans(studyPlans);
        setChanged();
        notifyObservers();
    }

    /**
     * @return all studyPlans
     */
    public List<StudyPlan> getAllStudyPlans() {
        List<StudyPlan> studyPlans = new ArrayList<>();
        studyPlans.addAll(student.getAllStudyPlans());
        return studyPlans;
    }

    /**
     * @return the student's current active study plan
     */
    public StudyPlan getCurrentStudyPlan() {
        return student.getCurrentStudyPlan();
    }

    /**
     * Method removes a given study plan.
     * @param studyPlanID Study plan of users decision to delete.
     */
    public void removeStudyPlan(Integer studyPlanID) {
        student.removeStudyPlan(studyPlanID);
        setChanged();
        notifyObservers();
    }

    /**
     * @param currentStudyPlan is the studyPlan to be set as the current studyPlan in the model
     */
    public void setCurrentStudyPlan(StudyPlan currentStudyPlan) {
        student.setFirstStudyPlanAsCurrent(currentStudyPlan);
    }

    /**
     * @param workspace is the workspace to be set as the workspace in the model
     */
    public void setWorkspace(Workspace workspace) {
        student.setWorkspace(workspace);
    }

    /**
     * Fills the model with a list of courses
     * @param courses the courses to be added
     */
    public void fillModelWithCourses(List<Course> courses){
        for (Course course: courses) {
            this.courses.add(course);
        }
    }

}
