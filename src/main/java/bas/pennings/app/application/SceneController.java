package bas.pennings.app.application;

import bas.pennings.app.Job;
import bas.pennings.app.MainApplication;
import bas.pennings.app.UserAccount;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.Objects;

public class SceneController {
    // Variables for the login and create account scenes
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField repeatPasswordField;
    @FXML private TextField ageField;
    @FXML private TextField emailField;
    @FXML private Text invalidAge;
    @FXML private Text invalidEmail;
    @FXML private Text noPasswordMatch;
    @FXML private Text incorrectUsernameOrPassword1;
    @FXML private Text incorrectUsernameOrPassword2;

    @FXML protected void onLoginSubmit() {
        UserAccount userAccount = MainApplication.getUserAccount(usernameField.getText(), passwordField.getText());
        if (userAccount != null && Objects.equals(userAccount.password, passwordField.getText())) {
            System.out.println("Access granted!");
        }
        else {
            incorrectUsernameOrPassword1.setVisible(true);
            incorrectUsernameOrPassword2.setVisible(true);
        }
    }

    @FXML protected void onAccountCreateSubmit() {
        if (!Objects.equals(passwordField.getText(), repeatPasswordField.getText()))
            noPasswordMatch.setVisible(true); // Incorrect password

        if (!(Integer.getInteger(ageField.getText()) >= 12))
            invalidAge.setVisible(true); // Age does contain string or is smaller than 12

        if (!emailField.getText().contains("@"))
            invalidEmail.setVisible(true); // Incorrect email

        if (passwordField != repeatPasswordField)
            noPasswordMatch.setVisible(true); // Incorrect email

        MainApplication.createUserAccount(new UserAccount(
                usernameField.getText(),
                Integer.parseInt(ageField.getText()),
                Job.Empty,
                emailField.getText(),
                passwordField.getText()));
    }

    @FXML protected void switchToCreateAccountScene(MouseEvent mouseEvent) {
        MainApplication.displayScene(SceneView.CreateAccountScene);
    }

    @FXML protected void switchToLoginScene(MouseEvent mouseEvent) {
        MainApplication.displayScene(SceneView.LoginScene);
    }
}