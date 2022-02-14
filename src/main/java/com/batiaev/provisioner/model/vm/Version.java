package com.batiaev.provisioner.model.vm;

import com.batiaev.provisioner.model.Microtype;

public class Version extends Microtype<String> {
    protected Version(String value) {
        super(value);
    }

    public static Version version(String version) {
        return new Version(version);
    }
}
