package com.batiaev.provisioner.model.user;

import com.batiaev.provisioner.model.RegexpValidatedString;

public class Email extends RegexpValidatedString {
    private final static String REGEXP = "^[A-Za-z][0-9A-Za-z-_]*@[A-Za-z][0-9A-Za-z-_]*\\.[A-Za-z][0-9A-Za-z-_]{0,10}$";

    public Email(String email) {
        super(email);
    }

    @Override
    protected String getRegExp() {
        return REGEXP;
    }
}
