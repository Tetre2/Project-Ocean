package ProjectOcean.Model;

import java.util.*;

/**
 * Represents the aggregate of the model
 */
public class CoursePlanningSystem extends Observable {


    private Student student;
    private final List<ICourse> courses;

    public CoursePlanningSystem() {
        courses = generateCourses();
        this.student = new Student();
    }

    /**
     * @return returns all courses stored
     */
    public List<ICourse> getAllCourses() {
        return Collections.unmodifiableList(courses);
    }

    /**
     * Creates a list of hard coded courses
     * @return returns a list full of courses
     */
    public List<ICourse> generateCourses() {

        List<ICourse> courses = new ArrayList<>();

        courses.add(new Course("DAT017","Maskinorienterad programmering", 7.5f, 1, "Roger Johansson", "Tenta/Laborationer", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum"));

        courses.add(new Course("EDA433","Grundläggande Datorteknik", 7.5f, 2, "Rolf Söderström", "Tenta/Laborationer", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum"));

        courses.add(new Course("MVE045","Matematisk Analys", 7.5f, 1, "Zoran Konkoli", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum"));

        courses.add(new Course("TMV206","Linjär Algebra", 7.5f, 3, "Lukás Malý", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum"));

        courses.add(new Course("TDA552","Objektorienterad Programmering och Design", 7.5f, 2, "Alex Gerdes", "Munta/Inlämningsuppgift", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum"));

        courses.add(new Course("DAT096", "Konstruktionsprojekt i inbyggda elektroniksystem", 15f, 3, "Lena Peterson", "Projekt", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum"));

        return courses;
    }

    /**
     * @param course is a UUID for a specific course
     * @return returns the CourseCode for the specified UUID
     */
    public String getCourseCode(ICourse course) {
        return course.getCourseCode();
    }

    /**
     * @param course is a UUID for a specific course
     * @return returns the CourseName for the specified UUID
     */
    public String getCourseName(ICourse course) {
        return course.getCourseName();
    }

    /**
     * @param course is a UUID for a specific course
     * @return returns the StudyPoints for the specified UUID
     */
    public String getStudyPoints(ICourse course){
        return course.getStudyPoints();
    }

    /**
     * @param course is a UUID for a specific course
     * @return returns the StudyPeriod for the specified UUID
     */
    public String getStudyPeriod(ICourse course){
        return course.getStudyPeriod();
    }

    /**
     * @param course is a UUID for a specific course
     * @return returns the Examinaot for the specified UUID
     */
    public String getExaminator(ICourse course){
        return course.getExaminator();
    }

    /**
     * @param course is a UUID for a specific course
     * @return returns the ExaminationMeans for the specified UUID
     */
    public String getExaminationMeans(ICourse course){
        return course.getExaminationMeans();
    }

    /**
     * @param course is a UUID for a specific course
     * @return returns the Language for the specified UUID
     */
    public String getLanguage(ICourse course){
        return course.getLanguage();
    }

    /**
     * @param course is a UUID for a specific course
     * @return returns a list of required courses for a specific course defined by a UUID
     */
    public List<String> getRequiredCourses(ICourse course){
        return Collections.unmodifiableList(course.getRequiredCourses());
    }

    /**
     * @param course is a UUID for a specific course
     * @return returns the CoursePMLink for the specified UUID
     */
    public String getCoursePMLink(ICourse course){
        return course.getCoursePMLink();
    }

    /**
     *
     * @param searchText: A string of search terms seperated by blankspaces
     * @return searchResult: A List<UUID> with the id of each course that matches, in the order that they are matched
     */
    public List<ICourse> executeSearch(String searchText) {
        String[] searchTerms = trimString(searchText);
        List<ICourse> searchResult = new ArrayList<>();
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

    private void matchCourseNameAndAdd(String[] searchTerms, List<ICourse> searchResult){
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

    private void matchCourseCodeAndAdd(String[] searchTerms, List<ICourse> searchResult) {
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

    private void matchExaminorAndAdd(String[] searchTerms, List<ICourse> searchResult) {
        //For each search term, searches through each courses examinor for matches and
        // if found adds the course to search result.
        for(String s : searchTerms) {
            for(ICourse c : courses) {
                if(c.getExaminator().toLowerCase().contains(s) && !searchResult.contains(c)) {
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
        List<ICourse> idList = new ArrayList<ICourse>();
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

    /**
     * @param course is a UUID for a specific course
     * @return returns the CourseDescription for the specified UUID
     */
    public String getCourseDescription(ICourse course){
        return course.getCourseDescription();
    }
}
