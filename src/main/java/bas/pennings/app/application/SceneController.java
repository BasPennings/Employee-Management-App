package bas.pennings.app.application;

import bas.pennings.app.Job;
import bas.pennings.app.MainApplication;
import bas.pennings.app.UserAccount;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Objects;

public class SceneController {
    @FXML private Label welcomeText;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField ageField;
    @FXML private TextField emailField;
    @FXML private PasswordField repeatPasswordField;

    @FXML protected void onLoginSubmit() {
        UserAccount userAccount = MainApplication.getUserAccount(usernameField.getText(), passwordField.getText());
        if (userAccount != null && Objects.equals(userAccount.password, passwordField.getText())) {
            welcomeText.setText("Access granted!");
        }
        else {
            welcomeText.setText("Access denied!");
        }
    }

    @FXML protected void onAccountCreateSubmit() {
        if (!Objects.equals(passwordField.getText(), repeatPasswordField.getText())) return; // Incorrect password
        if (!ageField.getText().matches("\\d+")) return; // Age does contain string
        if (!emailField.getText().contains("@")) return; // Incorrect email

        UserAccount userAccount = new UserAccount(
                usernameField.getText(),
                Integer.parseInt(ageField.getText()),
                Job.Empty,
                emailField.getText(),
                passwordField.getText());

        MainApplication.createUserAccount(userAccount);

        welcomeText.setText("Account info: " +
                "username: " + userAccount.name +
                "age " + userAccount.age +
                "job: " + userAccount.job.jobName +
                "email: " + userAccount.email +
                "password: " + userAccount.password);
    }
}