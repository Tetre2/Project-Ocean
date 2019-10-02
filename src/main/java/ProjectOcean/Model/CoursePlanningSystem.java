package ProjectOcean.Model;

import java.util.*;

/**
 * Represents the aggregate of the model
 */
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

        Course course = new Course("DAT017","Maskinorienterad programmering", 7.5f, 1, "Roger Johansson", "Tenta/Laborationer", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        courses.put(course.getId(), course);

        course = new Course("EDA433","Grundläggande Datorteknik", 7.5f, 2, "Rolf Söderström", "Tenta/Laborationer", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        courses.put(course.getId(), course);

        course = new Course("MVE045","Matematisk Analys", 7.5f, 1, "Zoran Konkoli", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        courses.put(course.getId(), course);

        course = new Course("TMV206","Linjär Algebra", 7.5f, 3, "Lukás Malý", "Tenta", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        courses.put(course.getId(), course);

        course = new Course("TDA552","Objektorienterad Programmering och Design", 7.5f, 2, "Alex Gerdes", "Munta/Inlämningsuppgift", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        courses.put(course.getId(),course);

        course = new Course("DAT096", "Konstruktionsprojekt i inbyggda elektroniksystem", 15f, 3, "Lena Peterson", "Projekt", "Svenska", new ArrayList<>(), "www.google.com", "Lorem Ipsum");
        courses.put(course.getId(),course);
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
     *
     * @param searchText: A string of search terms seperated by blankspaces
     * @return searchResult: A List<UUID> with the id of each course that matches, in the order that they are matched
     */
    public List<UUID> executeSearch(String searchText) {
        String[] searchTerms = trimString(searchText);
        List<UUID> searchResult = new ArrayList<>();
        searchCourseNames(searchTerms, searchResult);
        searchCourseCodes(searchTerms, searchResult);
        searchExaminors(searchTerms, searchResult);
        return searchResult;
    }

    private String[] trimString(String searchText) {
        searchText = searchText.trim();
        searchText = searchText.toLowerCase();
        searchText = searchText.trim().replaceAll(" +", " ");
        String[] searchTerms = searchText.split(" ");
        return searchTerms;
    }

    private void searchCourseNames(String[] searchTerms, List<UUID> searchResult){
        for(String s : searchTerms) {
            for(Course c : courses.values()) {
                if(!(s.length()< 3) && c.getCourseName().toLowerCase().contains(s) && !searchResult.contains(c.getId())) {
                    searchResult.add(c.getId());
                }
            }
        }
    }

    private void searchCourseCodes(String[] searchTerms, List<UUID> searchResult) {
        for(String s : searchTerms) {
            for(Course c : courses.values()) {
                if(c.getCourseCode().toLowerCase().contains(s) && !searchResult.contains(c.getId())) {
                    searchResult.add(c.getId());
                }
            }
        }
    }

    private void searchExaminors(String[] searchTerms, List<UUID> searchResult) {
        for(String s : searchTerms) {
            for(Course c : courses.values()) {
                if(c.getExaminator().toLowerCase().contains(s) && !searchResult.contains(c.getId())) {
                    searchResult.add(c.getId());
                }
            }
        }
    }

    /**
     * @param id is a UUID for a specific course
     * @return returns the CourseDescription for the specified UUID
     */
    public String getCourseDescription(UUID id){
        return courses.get(id).getCourseDescription();
    }

}
