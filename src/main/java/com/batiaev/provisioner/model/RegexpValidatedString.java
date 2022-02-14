package com.batiaev.provisioner.model;

import static java.lang.String.format;

public abstract class RegexpValidatedString extends Microtype<String> {
    protected RegexpValidatedString(String value) {
        super(value);
    }

    @Override
    protected String validate(String value) {
        boolean matches = value.matches(getRegExp());
        if (!matches) throw new IllegalArgumentException(format("Invalid %s format", getClass().getSimpleName()));
        return value;
    }

    protected abstract String getRegExp();
}
