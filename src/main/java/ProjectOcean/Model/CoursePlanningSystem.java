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
        courses.add(new Course("DAT017","Maskinorienterad programmering", 7.5f, "Roger Johansson"));
        courses.add(new Course("EDA433","Grundläggande Datorteknik", 7.5f, "Jan Jonsson"));
        courses.add(new Course("MVE045","Matematisk Analys", 7.5f, "Zoran Konkoli"));
        courses.add(new Course("TMV206","Linjär Algebra", 7.5f, "Lukás Malý"));
        courses.add(new Course("TDA552","Objektorienterad Programmering och Design", 7.5f, "Alex Gerdes"));
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


    public List<UUID> executeSearch(String[] searchTerms) {
        List<UUID> searchResult = new ArrayList<>();
        searchCourseNames(searchTerms, searchResult);
        searchCourseCodes(searchTerms, searchResult);
        searchExaminors(searchTerms, searchResult);
        return searchResult;
    }

    private void searchCourseNames(String[] searchTerms, List<UUID> searchResult){
        for(String s : searchTerms) {
            for(Course c : courses) {
                if(!(s.length()< 3) && c.getName().toLowerCase().contains(s) && !searchResult.contains(c.getId())) {
                    searchResult.add(c.getId());
                }
            }
        }
    }

    private void searchCourseCodes(String[] searchTerms, List<UUID> searchResult) {
        for(String s : searchTerms) {
            for(Course c : courses) {
                if(c.getCourseCode().toLowerCase().contains(s) && !searchResult.contains(c.getId())) {
                    searchResult.add(c.getId());
                }
            }
        }
    }

    private void searchExaminors(String[] searchTerms, List<UUID> searchResult) {
        for(String s : searchTerms) {
            for(Course c : courses) {
                if(c.getExaminor().toLowerCase().contains(s) && !searchResult.contains(c.getId())) {
                    searchResult.add(c.getId());
                }
            }
        }
    }

}
