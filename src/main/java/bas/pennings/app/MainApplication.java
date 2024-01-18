package bas.pennings.app;

import bas.pennings.app.application.SceneView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    private StorageManager storageManager;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Manager.InitializeManagers(this, stage);
        storageManager = Manager.getStorageManager();

        Manager.getUserInterfaceManager().createStage(SceneView.LOGIN_SCENE);
    }

    public void createUserAccount(UserAccount userAccount) {
        storageManager.addUserAccount(userAccount);
    }

    public UserAccount getUserAccount(String userAccountName, String userAccountPassword) {
        return storageManager.getUserAccount(userAccountName, userAccountPassword);
    }

    public boolean doesUsernameExist(String name) {
        return storageManager.doesUsernameExist(name);
    }
}