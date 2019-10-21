package ProjectOcean.Controller.FunctionalInterfaces;

import ProjectOcean.Controller.Movable;
import javafx.scene.input.DragEvent;

@FunctionalInterface
public interface MoveDraggedObjectToCursor {

    /**
     * Moves the icon to the cursor position
     * @param icon the icon to be moved
     * @param event the event representing the mouse drag
     */
    void moveDraggedObjectToCursor(Movable icon, DragEvent event);
}
