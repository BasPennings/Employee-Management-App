package bas.pennings.app.application;

import bas.pennings.app.Job;
import bas.pennings.app.MainApplication;
import bas.pennings.app.StorageManager;
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
    @FXML private Text invalidPassword;
    @FXML private Text incorrectUsernameOrPassword1;
    @FXML private Text incorrectUsernameOrPassword2;
    @FXML private Text usernameAlreadyInUse;
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
        int age;
        try {
            age = Integer.parseInt(ageField.getText());
        } catch (Exception e) {
            return;
        }

        // Get the feedback field of the representative input field if it's condition is met.
         Object feedbackField = !Objects.equals(passwordField.getText(), repeatPasswordField.getText()) ? noPasswordMatch // No repeat password match
                : age < 12 ? invalidAge // Underage
                : !emailField.getText().contains("@") ? invalidEmail // Invalid email address
                : emailField.getText().length() < 5 ? invalidPassword // Too short password
                : MainApplication.doesUsernameExist(usernameField.getText()) ? usernameAlreadyInUse // Name already in use
                : null;

         // Invoke the setVisible method to make the feedback field visible, if there is one.
        if (feedbackField != null) try {
            feedbackField.getClass().getMethod("setVisible", boolean.class).invoke(feedbackField, true);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create the user account when all account requirements are met
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