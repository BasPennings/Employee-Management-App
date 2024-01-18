package bas.pennings.app.application;

import bas.pennings.app.MainApplication;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class UserInterfaceManager {
    private static final AtomicReference<String> APP_TITLE = new AtomicReference<>("Employee Management App");
    @Getter private final double screenMinWidth = Screen.getPrimary().getVisualBounds().getMinX();
    @Getter private final double screenMinHeight = Screen.getPrimary().getVisualBounds().getMinY();
    @Getter private final double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
    @Getter private final double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();
    @Setter @Getter private Stage currentStage;

    /**
     * creates and opens a new stage.
     * @param sceneView The sceneView to be displayed on the new stage.
     * */
    public void createStage(SceneView sceneView) throws IOException {
        Scene scene = new Scene(new FXMLLoader(MainApplication.class.getResource(sceneView.getFxmlFileName())).load(), sceneView.getWidth(), sceneView.getHeight());
        currentStage.initStyle(sceneView.getStageStyle());
        currentStage.setTitle(APP_TITLE.get());
        currentStage.setScene(scene);

        currentStage.show();
    }

    public void displayScene(SceneView sceneView) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(MainApplication.class.getResource(sceneView.getFxmlFileName())));
            StackPane stackPane = new StackPane(root);

            currentStage.setScene(new Scene(stackPane, sceneView.getWidth(), sceneView.getHeight()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeApplication() {
        Platform.runLater(() -> {
            currentStage.close();
            Platform.exit();
        });
    }

    private void adjustStageToScene(SceneView sceneView) {
        currentStage.setX(sceneView.getX());
        currentStage.setY(sceneView.getY());
        currentStage.setWidth(sceneView.getWidth());
        currentStage.setHeight(sceneView.getHeight());
    }
}
