package ProjectOcean.Controller;

import ProjectOcean.Model.IModelCourseListIcon;
import ProjectOcean.Model.IModelSearchBrowse;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class SearchBrowseController extends VBox{

    @FXML
    private VBox searchResultVBox;

    private IModelSearchBrowse model;

    public SearchBrowseController(IModelSearchBrowse model) {
        this.model = model;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/SearchBrowseWindow.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        //Displays all courses in CPS
        List<UUID> idList = model.getAllCoursesIDs();
        displayAllCourses(idList);
    }

    private void displayAllCourses(List<UUID> IDList) {
        //searchResultVBox.getChildren().remove(0, searchResultVBox.getChildren().size()-1);
        for(UUID id : IDList) {
            searchResultVBox.getChildren().add((new CourseListIconController(id, (IModelCourseListIcon) model)));
        }
    }
}
