package bas.pennings.app;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class UserAccount {

    public final String name;
    public final int age;
    public final Job job;
    public final String email;
    public final String password;
    public final UUID uuid;
    public UserAccount(
            @JsonProperty("name") String name,
            @JsonProperty("age") int age,
            @JsonProperty("job") Job job,
            @JsonProperty("email") String email,
            @JsonProperty("password") String password) {
        this.name = name;
        this.age = age;
        this.job = job;
        this.email = email;
        this.password = password;
        this.uuid = UUID.randomUUID();
    }
}
