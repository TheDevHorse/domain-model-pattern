package com.thedevhorse.domainmodelpattern.domain;

import com.thedevhorse.domainmodelpattern.domain.base.ValueObject;

import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;

public class Contact implements ValueObject {

    private String email;

    private String mobileNumber;

    private String address;

    private Contact(String email,
                    String mobileNumber,
                    String address) {
        setEmail(email);
        this.mobileNumber = mobileNumber;
        this.address = address;
    }

    public static Contact of(final String email,
                             final String mobileNumber,
                             final String address) {
        return new Contact(
                email,
                mobileNumber,
                address
        );
    }

    private void setEmail(String email) {
        requireNonNull(
                email,
                "the email cannot be null."
        );

        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        if (!isMatches(email, regexPattern)) {
            throw new IllegalArgumentException("Invalid email address.");
        }
        this.email = email;
    }

    private boolean isMatches(String value, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(value)
                .matches();
    }

    public String email() {
        return email;
    }

    public String mobileNumber() {
        return mobileNumber;
    }

    public String address() {
        return address;
    }
}
