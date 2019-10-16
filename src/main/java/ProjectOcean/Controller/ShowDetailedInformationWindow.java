package ProjectOcean.Controller;

import ProjectOcean.Model.ICourse;

@FunctionalInterface
public interface ShowDetailedInformationWindow {
    /**
     * Clears and adds a detailedController to the contentWindow
     * @param course the ICourse representing the course from which the details will be taken from
     */
    void showDetailedInformationWindow(ICourse course);
}
