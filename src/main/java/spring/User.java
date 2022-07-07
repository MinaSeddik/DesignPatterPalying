package spring;

import javax.validation.constraints.*;

public class User {

    @NotNull(message = "Name cannot be null")
    private String name;

    @AssertTrue
    private boolean working;

    @Size(min = 10, max = 200, message = "About Me must be between 10 and 200 characters")
    private String aboutMe;

    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 150, message = "Age should not be greater than 150")
    private int age;

    @Email(message = "Email should be valid")
    private String email;

//    @Pattern(message="max 5 words please" , regexp="^[a-zA-Z+#\-.0-9]{1,5}(\s[a-zA-Z+#\-.0-9]{1,5}){0,4}$")
    private String keyword;

    // standard setters and getters
}