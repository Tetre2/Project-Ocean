package ProjectOcean.Model;

public class CourseAccessibility {

    private final String examinator;
    private final String examinationMeans;
    private final String language;

    public CourseAccessibility(String examinator, String examinationMeans, String language) {
        this.examinator = examinator;
        this.examinationMeans = examinationMeans;
        this.language = language;
    }

    public String getExaminator() {
        return examinator;
    }

    public String getExaminationMeans() {
        return examinationMeans;
    }

    public String getLanguage() {
        return language;
    }
}
