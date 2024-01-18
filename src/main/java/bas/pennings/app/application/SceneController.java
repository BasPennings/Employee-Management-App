package bas.pennings.app.application;

import bas.pennings.app.Job;
import bas.pennings.app.MainApplication;
import bas.pennings.app.Manager;
import bas.pennings.app.UserAccount;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.Objects;

public class SceneController {
    MainApplication mainApplication = Manager.getMainApplication();
    UserInterfaceManager userInterfaceManager = Manager.getUserInterfaceManager();

    // Variables for the login and create account scenes
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField repeatPasswordField; // Used to confirm the entered password in the create account scene
    @FXML private TextField ageField;
    @FXML private TextField emailField;

    @FXML private Text invalidAge; // [Create Account]  "Age must be 12 years or older"
    @FXML private Text invalidEmail; // [Create Account]  "Invalid email address"
    @FXML private Text noPasswordMatch; // [Create Account]  "Passwords do not match"
    @FXML private Text invalidPassword; // [Create Account] "Password must contain at least 5 characters"
    @FXML private Text incorrectUsernameOrPassword1; // [Login] "Incorrect username or password"
    @FXML private Text incorrectUsernameOrPassword2; // [Login] "Incorrect username or password"
    @FXML private Text usernameAlreadyInUse; // [Create Account] "Username already in use"
    @FXML protected void onLoginSubmit() {
        resetFeedbackFields();

        if (usernameField.getText().replaceAll("\\s", "").equals("")) return;

        UserAccount userAccount = mainApplication.getUserAccount(usernameField.getText(), passwordField.getText());
        if (userAccount != null && Objects.equals(userAccount.password, passwordField.getText())) {
            System.out.println("Access granted!");
        }
        else {
            incorrectUsernameOrPassword1.setVisible(true);
            incorrectUsernameOrPassword2.setVisible(true);
        }
    }

    @FXML protected void onAccountCreateSubmit() {
        resetFeedbackFields();

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
                : passwordField.getText().length() < 5 ? invalidPassword // Too short password
                : mainApplication.doesUsernameExist(usernameField.getText()) ? usernameAlreadyInUse // Name already in use
                : null;

         // Invoke the setVisible method to make the feedback field visible, if there is one.
        if (feedbackField != null) try {
            feedbackField.getClass().getMethod("setVisible", boolean.class).invoke(feedbackField, true);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create the user account when all account requirements are met
        mainApplication.createUserAccount(new UserAccount(
                usernameField.getText(),
                Integer.parseInt(ageField.getText()),
                Job.Empty,
                emailField.getText(),
                passwordField.getText()));

        System.out.println("Account created!");

        switchToLoginScene();
    }

    @FXML protected void switchToCreateAccountScene() {
        userInterfaceManager.displayScene(SceneView.CREATE_ACCOUNT_SCENE);
    }

    @FXML protected void switchToLoginScene() {
        userInterfaceManager.displayScene(SceneView.LOGIN_SCENE);
    }

    private void resetFeedbackFields() {
        Object[] feedbackFields = { invalidAge,
            invalidEmail,
            noPasswordMatch,
            invalidPassword,
            incorrectUsernameOrPassword1,
            incorrectUsernameOrPassword2,
            usernameAlreadyInUse };

        // Invoke the setVisible method to make the feedback fields invisible
        for (Object feedbackField : feedbackFields) {
            if (feedbackField != null) try {
                feedbackField.getClass().getMethod("setVisible", boolean.class).invoke(feedbackField, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML private void closeApplication() {
        userInterfaceManager.closeApplication();
    }
}