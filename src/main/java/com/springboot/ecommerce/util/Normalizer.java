package com.springboot.ecommerce.util;

public class Normalizer {

    public static String normalizeEmail(String email) {
        if (email == null) return null;
        return email.trim().toLowerCase();
    }

    public static String normalizeMobile(String mobile) {
        if (mobile == null) return null;
        return mobile.trim();
    }
}
