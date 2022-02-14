package com.batiaev.provisioner.model.user;

import com.batiaev.provisioner.model.Microtype;

public class JwtToken extends Microtype<String> {
    public JwtToken(String value) {
        super(value);
    }
}
