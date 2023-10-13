package com.hiberus.model;

import com.hiberus.exception.CreatorNotValidException;
import lombok.*;

import java.time.LocalDate;
import java.util.regex.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Creator {

    private static final Pattern PATTERN_EMAIL = Pattern
            .compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_PHONE = Pattern
            .compile("^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$");

    private Long identifier;
    private String name;
    private String surname;
    private LocalDate birth;
    private String email;
    private String phone;

    public void validCreator() throws CreatorNotValidException {
        if (incompleteFields() || negativeIdentifier() || invalidEmail() || invalidPhone())
            throw new CreatorNotValidException();
    }

    private boolean incompleteFields() {
        return identifier == null || name.isBlank() || surname.isBlank() || birth == null ||
                email.isBlank() || phone.isBlank();
    }

    private boolean negativeIdentifier() {
        return identifier < 0;
    }

    private boolean invalidEmail() {
        return !PATTERN_EMAIL.matcher(email).find();
    }

    private boolean invalidPhone() {
        return !PATTERN_PHONE.matcher(phone).find();
    }

}
