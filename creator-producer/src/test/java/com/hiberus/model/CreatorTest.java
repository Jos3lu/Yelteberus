package com.hiberus.model;

import com.hiberus.exception.CreatorNotValidException;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreatorTest {

    private static final String CREATOR_IDENTIFIER = "CREATOR-1";
    private static final String NAME = "John";
    private static final String SURNAME = "Smith";
    private static final LocalDate BIRTH = LocalDate.parse("1997-03-20");
    private static final String EMAIL = "email.gmail.com";
    private static final String PHONE = "+34 385 37 58 93";

    @Test
    public void creatorIdentifierShouldNotBeNull() {
        // Given & When
        Creator creator = new Creator(null, NAME, SURNAME, BIRTH, EMAIL, PHONE);

        // Then
        assertThrows(CreatorNotValidException.class, creator::validCreator);
    }

    @Test
    public void creatorIdentifierShouldNotBeEmpty() {
        // Given & When
        Creator creator = new Creator("", NAME, SURNAME, BIRTH, EMAIL, PHONE);

        // Then
        assertThrows(CreatorNotValidException.class, creator::validCreator);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            // Given
            "293848", "ddhh3838DDH3", "CREATOR", "CREATOR-"
    })
    public void videoIdentifierShouldFollowPattern(String creatorIdentifier) {
        // When
        Creator creator = new Creator(creatorIdentifier, NAME, SURNAME, BIRTH, EMAIL, PHONE);

        // Then
        assertThrows(CreatorNotValidException.class, creator::validCreator);
    }

    @Test
    public void nameShouldNotBeNull() {
        // Given & When
        Creator creator = new Creator(CREATOR_IDENTIFIER, null, SURNAME, BIRTH, EMAIL, PHONE);

        // Then
        assertThrows(CreatorNotValidException.class, creator::validCreator);
    }

    @Test
    public void nameShouldNotBeEmpty() {
        // Given & When
        Creator creator = new Creator(CREATOR_IDENTIFIER, "", SURNAME, BIRTH, EMAIL, PHONE);

        // Then
        assertThrows(CreatorNotValidException.class, creator::validCreator);
    }

    @Test
    public void surnameShouldNotBeNull() {
        // Given & When
        Creator creator = new Creator(CREATOR_IDENTIFIER, NAME, null, BIRTH, EMAIL, PHONE);

        // Then
        assertThrows(CreatorNotValidException.class, creator::validCreator);
    }

    @Test
    public void surnameShouldNotBeEmpty() {
        // Given & When
        Creator creator = new Creator(CREATOR_IDENTIFIER, NAME, "", BIRTH, EMAIL, PHONE);

        // Then
        assertThrows(CreatorNotValidException.class, creator::validCreator);
    }

    @Test
    public void birthShouldNotBeNul() {
        // Given & When
        Creator creator = new Creator(CREATOR_IDENTIFIER, NAME, SURNAME, null, EMAIL, PHONE);

        // Then
        assertThrows(CreatorNotValidException.class, creator::validCreator);
    }

    @Test
    public void emailShouldNotBeNull() {
        // Given & When
        Creator creator = new Creator(CREATOR_IDENTIFIER, NAME, SURNAME, BIRTH, null, PHONE);

        // Then
        assertThrows(CreatorNotValidException.class, creator::validCreator);
    }

    @Test
    public void emailShouldNotBeEmpty() {
        // Given & When
        Creator creator = new Creator(CREATOR_IDENTIFIER, NAME, SURNAME, BIRTH, "", PHONE);

        // Then
        assertThrows(CreatorNotValidException.class, creator::validCreator);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            // Given
            "correo", "correo.com", "correo@gmail", "correo@gmail.", "@gmail.com"
    })
    public void emailShouldFollowPattern(String email) {
        // When
        Creator creator = new Creator(CREATOR_IDENTIFIER, NAME, SURNAME, BIRTH, email, PHONE);

        // Then
        assertThrows(CreatorNotValidException.class, creator::validCreator);
    }

    @Test
    public void phoneShouldNotBeNull() {
        // Given & When
        Creator creator = new Creator(CREATOR_IDENTIFIER, NAME, SURNAME, BIRTH, EMAIL, null);

        // Then
        assertThrows(CreatorNotValidException.class, creator::validCreator);
    }

    @Test
    public void phoneShouldNotBeEmpty() {
        // Given & When
        Creator creator = new Creator(CREATOR_IDENTIFIER, NAME, SURNAME, BIRTH, EMAIL, "");

        // Then
        assertThrows(CreatorNotValidException.class, creator::validCreator);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            // Given
            "3838 3838 3939", "22 383 383 394", "2837", "1238492724889893", "233 489 389"
    })
    public void phoneShouldFollowPattern(String phone) {
        // When
        Creator creator = new Creator(CREATOR_IDENTIFIER, NAME, SURNAME, BIRTH, PHONE, phone);

        // Then
        assertThrows(CreatorNotValidException.class, creator::validCreator);
    }

}
