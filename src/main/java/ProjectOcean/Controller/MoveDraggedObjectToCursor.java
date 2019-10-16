package ProjectOcean.Controller;

import javafx.scene.input.DragEvent;

@FunctionalInterface
public interface MoveDraggedObjectToCursor {
    void moveDraggedObjectToCursor(Movable icon, DragEvent event);
}
