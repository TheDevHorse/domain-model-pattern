package com.thedevhorse.domainmodelpattern.domain;

import com.thedevhorse.domainmodelpattern.domain.base.AbstractAggregateRoot;
import com.thedevhorse.domainmodelpattern.domain.enumerate.Category;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;

public class Athlete extends AbstractAggregateRoot<String> {

    private PersonalData personalData;

    private Category category;

    private Athlete(String athleteId,
                    PersonalData personalData) {
        super(athleteId);
        setPersonalData(personalData);
        setCategory(personalData.age());
    }

    public static Athlete create(final String athleteId,
                                 final PersonalData personalData) {
        return new Athlete(
                athleteId,
                personalData
        );
    }

    public void updateEmail(final String newMobileNumber) {
        this.personalData.updateEmail(newMobileNumber);
    }

    private void setPersonalData(PersonalData personalData) {
        this.personalData = requireNonNull(
                personalData,
                "The personalData cannot be null."
        );
    }

    private void setCategory(int age){
        this.category = Arrays.stream(Category.values())
                .filter(c -> age >= c.minAge() && age <= c.maxAge())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Category not found for the age: " + age));
    }

    public PersonalData personalData() {
        return personalData;
    }

    public Category category() {
        return category;
    }
}
