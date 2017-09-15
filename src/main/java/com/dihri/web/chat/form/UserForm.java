package com.dihri.web.chat.form;

import com.dihri.web.chat.validation.IsExistsLogin;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Model for registration new user
 */
@Getter
@Setter
public class UserForm {
    @NotBlank
    @IsExistsLogin
    @Pattern(regexp = "^[a-zA-Z0-9]{3,15}$")
    private String login;
    @NotBlank
    @Size(min = 3)
    private String password;
}
