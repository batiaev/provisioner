package com.batiaev.provisioner.model.user;

import com.batiaev.provisioner.model.RegexpValidatedString;

public class PhoneNumber extends RegexpValidatedString {
    private static final String REGEXP = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";

    protected PhoneNumber(String value) {
        super(value);
    }

    @Override
    protected String getRegExp() {
        return REGEXP;
    }
}
