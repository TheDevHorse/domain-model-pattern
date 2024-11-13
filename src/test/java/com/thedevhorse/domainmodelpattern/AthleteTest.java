package com.thedevhorse.domainmodelpattern;

import com.thedevhorse.domainmodelpattern.domain.Athlete;
import com.thedevhorse.domainmodelpattern.domain.Contact;
import com.thedevhorse.domainmodelpattern.domain.PersonalData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AthleteTest {

    @Test
    void givenValidInputs_whenCreateAthleteIsCalled_thenAthleteIsReturned() {
        // Given
        String expected = "POR001";
        PersonalData personalData = PersonalData.of("123", "John", 25,null);

        // When
        Athlete actual = Athlete.create(expected, personalData);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual.id());
    }

    @Test
    void giveNewEmail_whenUpdateEmailIsCalled_thenEmailIsUpdated() {
        // Given
        String expected = "john@gmail.com";
        Contact contact = Contact.of("johndoe@gmail.com", null, null);
        PersonalData personalData = PersonalData.of("123456789", "John Doe", 28, contact);
        Athlete actual = Athlete.create("1", personalData);

        // When
        actual.updateEmail(expected);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual.personalData().contact().email());
    }

    @Test
    void giveInvalidEmail_whenUpdateEmailIsCalled_thenIllegalArgumentExceptionThrown() {
        // Given
        String expectedMessage = "Invalid email address.";
        String invalidEmail = "johngmail.com";
        Contact contact = Contact.of("johndoe@gmail.com", null, null);
        PersonalData personalData = PersonalData.of("123456789", "John Doe", 28, contact);
        Athlete actual = Athlete.create("1", personalData);

        // When
        String actualMessage = assertThrows(IllegalArgumentException.class, () -> actual.updateEmail(invalidEmail)).getMessage();

        // Then
        assertEquals(expectedMessage, actualMessage);
    }
}
