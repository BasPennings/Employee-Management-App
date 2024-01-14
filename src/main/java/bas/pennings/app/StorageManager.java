package bas.pennings.app;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class StorageManager {
    private final String appDirectoryName = "EmployeeManagementApp";
    private final String userAccountsFilepath = getFilePath("userAccounts.json");
    private final ObjectMapper objectMapper;
    private Map<String, UserAccount> userAccounts;

    public StorageManager() {
        this.objectMapper = new ObjectMapper();
        this.userAccounts = new HashMap<>();
        loadUserAccounts();
    }

    /**
     * Adds a new user account to the storage.
     * @param userAccount The user account to be added.
     */
    public void addUserAccount(UserAccount userAccount) {
        userAccounts.put(userAccount.name, userAccount);
        saveUserAccounts();
    }

    /**
     * Retrieves a user account based on the provided username and password.
     * @param username The username of the user account.
     * @param password The password of the user account.
     * @return The UserAccount object if the username and password match, otherwise null.
     */
    public UserAccount getUserAccount(String username, String password) {
        UserAccount userAccount = userAccounts.get(username);
        if (userAccount != null && userAccount.password.equals(password)) return userAccount;

        return null;
    }

    /**
     * Generates a file path for the specified file within the app directory.
     * @param fileName The name of the file.
     * @return The full file path.
    */
    private String getFilePath(String fileName) {
        String appDirectoryPath = System.getProperty("user.home") + File.separator + appDirectoryName;

        // Create the app directory if it doesn't exist already
        Path appDirectory = Paths.get(appDirectoryPath);
        if (Files.notExists(appDirectory)) {
            try {
                Files.createDirectories(appDirectory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return appDirectoryPath + File.separator + fileName;
    }

    /** Loads user accounts from the storage file using JSON deserialization.*/
    private void loadUserAccounts() {
        try {
            File file = new File(userAccountsFilepath);
            if (file.exists()) {
                userAccounts = objectMapper.readValue(file, new TypeReference<>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Saves the user accounts to the storage file using JSON serialization.*/
    private void saveUserAccounts() {
        try {
            objectMapper.writeValue(new File(userAccountsFilepath), userAccounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean doesUsernameExist(String name) {
        return userAccounts.containsKey(name);
    }
}
