package com.batiaev.provisioner.service;

import lombok.SneakyThrows;

import java.security.MessageDigest;

import static io.netty.util.CharsetUtil.UTF_8;

public class Sha256Hasher implements Hasher {
    private final MessageDigest md;

    @SneakyThrows
    public Sha256Hasher() {
        this.md = MessageDigest.getInstance("SHA3-256");
    }

    @Override
    public String hash(String data) {
        var hash = md.digest(data.getBytes(UTF_8));
        var hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            var hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString().toUpperCase();
    }
}
