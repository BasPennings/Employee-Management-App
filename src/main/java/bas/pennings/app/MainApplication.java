package bas.pennings.app;

import bas.pennings.app.application.SceneView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    private static final String appTitle = "Employee Management App";
    private static StorageManager storageManager;
    private static Stage currentStage;
//    private static UserAccount currentUserAccount;


    @Override
    public void start(Stage stage) throws IOException {
        storageManager = new StorageManager();
        currentStage = stage;

        createStage(SceneView.LoginScene);
    }

    public static void main(String[] args) {
        launch();
    }

    /**
     * Displays the given scene on the currently opened stage.
     * @param sceneView The scene to be displayed.
     * */
    public static void displayScene(SceneView sceneView) {
        try {
            StackPane stackPane = new StackPane();
            stackPane.getChildren().clear();
            stackPane.getChildren().add(new FXMLLoader(MainApplication.class.getResource(sceneView.FxmlFileName)).load());
            currentStage.setScene(new Scene(stackPane, sceneView.width, sceneView.height));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * creates and opens a new stage.
     * @param sceneView The scene to be displayed on the new stage.
     * */
    public static void createStage(SceneView sceneView) throws IOException {
        Scene scene = new Scene(new FXMLLoader(MainApplication.class.getResource(sceneView.FxmlFileName)).load(), sceneView.width, sceneView.height);
        currentStage.setTitle(appTitle);
        currentStage.setScene(scene);
        currentStage.show();
    }

    public static void createUserAccount(UserAccount userAccount) {
        storageManager.addUserAccount(userAccount);
    }

    public static UserAccount getUserAccount(String userAccountName, String userAccountPassword) {
        return storageManager.getUserAccount(userAccountName, userAccountPassword);
    }

    public static boolean doesUsernameExist(String name) {
        return storageManager.doesUsernameExist(name);
    }
}