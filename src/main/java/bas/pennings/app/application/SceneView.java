package bas.pennings.app.application;

public enum SceneView {
    LoginScene("login-scene", 600, 500),
    CreateAccountScene("create-account-scene", 600, 500);

    public final String FxmlFileName;
    public final int width;
    public final int height;

    SceneView(String fileName, int width, int height) {
        this.FxmlFileName = fileName + ".fxml";
        this.width = width;
        this.height = height;
    }
}
