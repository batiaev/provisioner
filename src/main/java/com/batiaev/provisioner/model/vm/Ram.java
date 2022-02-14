package com.batiaev.provisioner.model.vm;

import com.batiaev.provisioner.model.Microtype;

public class Ram extends Microtype<Long> {
    protected Ram(Long value) {
        super(value);
    }

    public static Ram gb(long gb) {//FIXME add same for other measures and add parsing from string
        return new Ram(gb * 1024 * 1024 * 1024);
    }

    @Override
    public String toString() {
        return "Ram(" + convert(getValue()) + ")";
    }

    private String convert(long value) {
        if (value < 1024)
            return value + "B";
        if (value < 1024 * 1024)
            return value / 1024 + "KB";
        if (value < 1024 * 1024 * 1024)
            return value / 1024 / 1024 + "MB";
        if (value < 1024 * 1024 * 1024 * 1024)
            return value / 1024 / 1024 / 1024 + "GB";
        else
            return value / 1024 / 1024 / 1024 / 1024 + "TB";
    }
}
