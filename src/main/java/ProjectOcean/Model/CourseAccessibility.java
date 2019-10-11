package ProjectOcean.Model;

import java.util.Objects;

/**
 * Class has responsible for accessibility of a course.
 */
public class CourseAccessibility {

    private final String examiner;
    private final String examinationMeans;
    private final String language;

    public CourseAccessibility(String examinator, String examinationMeans, String language) {
        this.examiner = examinator;
        this.examinationMeans = examinationMeans;
        this.language = language;
    }

    /**
     * @return this course's examinator
     */
    public String getExaminator() {
        return examiner;
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

    @Override
    public String toString() {
        return "examiner='" + examiner + '\'' +
                ", examinationMeans='" + examinationMeans + '\'' +
                ", language='" + language + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseAccessibility that = (CourseAccessibility) o;
        return examiner.equals(that.examiner) &&
                examinationMeans.equals(that.examinationMeans) &&
                language.equals(that.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(examiner, examinationMeans, language);
    }
}
