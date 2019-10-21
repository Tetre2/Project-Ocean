package ProjectOcean.IO;

import ProjectOcean.Model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StudyPlanSaverLoaderTests {

    private StudyPlanSaverLoader saverLoader = new StudyPlanSaverLoader();
    private CourseLoader courseSaverLoader = new CourseLoader();
    private List<StudyPlan> studyPlans;
    private Student student;
    private List<ICourse> courses;

    @Before
    public void setup(){
        studyPlans = new ArrayList<>();
        courses = courseSaverLoader.generatePreDefinedCourses();
        saverLoader.createNewStudentFile();

        //---- studyPlan 1 ----
        StudyPlan studyPlan = new StudyPlan(1);
        Workspace workspace = new Workspace();
        studyPlan.addYear();
        studyPlan.addCourse(courses.get(0), studyPlan.getYearByOrder(1).getID(), 1, 1);
        studyPlan.addYear();
        studyPlan.addCourse(courses.get(1), studyPlan.getYearByOrder(2).getID(), 1, 1);
        workspace.addCourse(courses.get(1));

        //---- studyPlan 2 ----
        StudyPlan studyPlan2 = new StudyPlan(2);
        Workspace workspace2 = new Workspace();
        studyPlan2.addYear();
        studyPlan2.addCourse(courses.get(0), studyPlan2.getYearByOrder(1).getID(), 1, 1);
        studyPlan2.addYear();
        studyPlan2.addCourse(courses.get(1), studyPlan2.getYearByOrder(2).getID(), 1, 1);
        workspace2.addCourse(courses.get(1));

        studyPlans.add(studyPlan);
        studyPlans.add(studyPlan2);

        student = new Student();
        student.setStudyPlans(studyPlans);
        student.setWorkspace(workspace);
        student.setCurrentStudyPlan(studyPlans.get(0));

    }

    @Test
    public void saveStudyplansTest(){
        saverLoader.saveModel(student);

    }

    @Test
    public void loadWorkspace(){
        try {

            List<Course> expected = student.getAllCoursesInWorkspace();
            List<Course> actual = saverLoader.loadWorkspace().getAllCourses();

            if(expected.size()== actual.size()){
                for (Course course : expected) {
                    if (!actual.contains(course)) {
                        Assert.assertTrue(false);
                    }
                }
            }

        } catch (StudyPlanNotFoundException e) {
            e.printStackTrace();
        } catch (OldStudyplanExeption oldStudyplanExeption) {
            oldStudyplanExeption.printStackTrace();
        }
    }

    @Test
    public void loadStudyplans(){
        try {

            List<Course> expected = student.getAllCoursesInWorkspace();
            List<Course> actual = saverLoader.loadWorkspace().getAllCourses();

            if(expected.size()== actual.size()){
                for (Course course : expected) {
                    if (!actual.contains(course)) {
                        Assert.assertTrue(false);
                    }
                }
            }

        } catch (StudyPlanNotFoundException e) {
            e.printStackTrace();
        } catch (OldStudyplanExeption oldStudyplanExeption) {
            oldStudyplanExeption.printStackTrace();
        }
    }




}
