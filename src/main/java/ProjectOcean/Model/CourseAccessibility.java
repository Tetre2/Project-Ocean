package ProjectOcean.Model;

/**
 * Class has responsible for accessibility of a course.
 */
public class CourseAccessibility {

    private final String examinator;
    private final String examinationMeans;
    private final String language;

    public CourseAccessibility(String examinator, String examinationMeans, String language) {
        this.examinator = examinator;
        this.examinationMeans = examinationMeans;
        this.language = language;
    }

    /**
     * @return this course's examinator
     */
    public String getExaminator() {
        return examinator;
    }

    /**
     * @return this course's examination forms
     */
    public String getExaminationMeans() {
        return examinationMeans;
    }

    /**
     * @return this course's language
     */
    public String getLanguage() {
        return language;
    }
}
