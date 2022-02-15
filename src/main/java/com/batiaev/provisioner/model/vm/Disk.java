package com.batiaev.provisioner.model.vm;

import com.batiaev.provisioner.model.Microtype;

import java.util.Objects;

public class Disk extends Microtype<Long> {
    private final DiskType type;

    public Disk(DiskType type, Long size) {
        super(size);
        this.type = type;
    }

    public DiskType getType() {
        return type;
    }

    public long getSize() {
        return getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Disk disk = (Disk) o;
        return type == disk.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }

    @Override
    public String toString() {
        return type + " " + getValue();
    }
}
