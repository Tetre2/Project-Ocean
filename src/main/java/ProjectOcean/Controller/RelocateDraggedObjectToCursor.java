package ProjectOcean.Controller;

import javafx.scene.input.DragEvent;

@FunctionalInterface
public interface RelocateDraggedObjectToCursor {

    /**
     * Moves the icon to the cursor position
     * @param icon the icon to be moved
     * @param event the event representing the mouse drag
     */
    void relocateDraggedObjectToCursor(Movable icon, DragEvent event);
}
