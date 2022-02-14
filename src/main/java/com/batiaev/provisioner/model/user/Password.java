package com.batiaev.provisioner.model.user;

import com.batiaev.provisioner.model.Microtype;

public class Password extends Microtype<String> {

    public Password(String value) {
        super(value);
    }

    @Override
    protected String validate(String value) {
        if (value.length() < 8)
            throw new IllegalArgumentException("Password should be more than 8 symbols");
        //FIXME add validation of numbers, upper/lower cases and special sumbols
        return value;
    }
}
