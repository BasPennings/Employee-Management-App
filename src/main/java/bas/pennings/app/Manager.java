package bas.pennings.app;

import bas.pennings.app.application.UserInterfaceManager;
import javafx.application.Application;
import javafx.stage.Stage;
import lombok.Getter;

public class Manager {
    @Getter private static MainApplication mainApplication;
    @Getter private static StorageManager storageManager;
    @Getter private static UserInterfaceManager userInterfaceManager;

    public static void InitializeManagers(MainApplication mainApp, Stage startStage) {
        mainApplication = mainApp;
        storageManager = new StorageManager();
        userInterfaceManager = new UserInterfaceManager();

        userInterfaceManager.setCurrentStage(startStage);
    }
}