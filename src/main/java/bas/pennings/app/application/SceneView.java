package bas.pennings.app.application;

import bas.pennings.app.Manager;
import javafx.stage.StageStyle;
import lombok.Getter;

public enum SceneView {
    LOGIN_SCENE("login-scene",
            (Manager.getUserInterfaceManager().getScreenWidth() - 600) / 2,
            (Manager.getUserInterfaceManager().getScreenHeight() - 600) / 2,
            600,
            500,
            StageStyle.UNDECORATED),
    CREATE_ACCOUNT_SCENE("create-account-scene",
            (Manager.getUserInterfaceManager().getScreenWidth() - 600) / 2,
            (Manager.getUserInterfaceManager().getScreenHeight() - 600) / 2,
            600,
            500,
            StageStyle.UNDECORATED),
    PROFILE_SCENE("profile-scene",
            Manager.getUserInterfaceManager().getScreenMinWidth(), // top left corner x
            Manager.getUserInterfaceManager().getScreenMinHeight(), // top left corner y
            Manager.getUserInterfaceManager().getScreenWidth(), // screen width
            Manager.getUserInterfaceManager().getScreenHeight(), // screen height
            StageStyle.DECORATED);

    @Getter private final String FxmlFileName;
    @Getter private final double x;
    @Getter private final double y;
    @Getter private final double width;
    @Getter private final double height;
    @Getter private final StageStyle stageStyle;

    SceneView(String fileName, double x, double y, double width, double height, StageStyle stageStyle) {
        this.FxmlFileName = fileName + ".fxml";
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.stageStyle = stageStyle;
    }
}
