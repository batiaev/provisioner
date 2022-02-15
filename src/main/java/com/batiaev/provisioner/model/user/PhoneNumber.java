package com.batiaev.provisioner.model.user;

import com.batiaev.provisioner.model.RegexpValidatedString;

public class PhoneNumber extends RegexpValidatedString {
    private static final String REGEXP = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";

    public PhoneNumber(String value) {
        super(value);
    }

    @Override
    protected String getRegExp() {
        return REGEXP;
    }

    @Override
    public String toString() {
        return super.getValue();
    }
}
