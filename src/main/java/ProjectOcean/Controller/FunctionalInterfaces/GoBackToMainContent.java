package ProjectOcean.Controller.FunctionalInterfaces;

@FunctionalInterface
public interface GoBackToMainContent {

    /**
     * Clears contentWindow's current window and implicitly shows StudyPlan and Workspace(effectively "goes back" to the main content window)
     */
    void goBack();
}
