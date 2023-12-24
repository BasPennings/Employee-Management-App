package bas.pennings.app.application;

public enum SceneView {
    LoginScene("loginScene", 600, 400),
    CreateAccountScene("createAccountScene", 1000, 750);

    public final String FxmlFileName;
    public final int width;
    public final int height;
    SceneView(String fileName, int width, int height) {
        this.FxmlFileName = fileName + ".fxml";
        this.width = width;
        this.height = height;
    }
}
