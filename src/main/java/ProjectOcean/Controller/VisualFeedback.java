package ProjectOcean.Controller;

import ProjectOcean.Model.ICourse;

/**
 * An interface that handles callbacks regarding visual feedback.
 */
public interface VisualFeedback {

    /**
     * Shows which slots is available for placing courses and color codes accordingly
     * @param course is the current course being drag and dropped.
     */
    void showAvailablePlacementInSchedule(ICourse course);
}
