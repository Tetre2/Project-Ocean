package ProjectOcean.Model;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;

public class Workspace {

    private final List courses = new LinkedList<Course>();


    public void addCourse(Course course){
        if(existsInList(course))
            return;
        else{
            courses.add(course);
        }
    }

    public void removeCourse(Course course){
        if(existsInList(course)){
            courses.remove(course);
        }
        else{
            return;
        }
    }

    private Boolean existsInList(Course course){
        for (Object c : courses) {
            if (c.equals(course)){
                return true;
            }
        }
        return false;

    }

    public void removeAllCourses(){
        while (courses.isEmpty() == false){
            courses.remove(0);
        }
    }

    public List<Course> getAllCourses(){
        return Collections.unmodifiableList(courses);
    }


    //Eventuell metod att implementera i ett senare skede för att tydliggöra visuella markeringar.

   /* public void findCourse(Course course){

    }*/
}
