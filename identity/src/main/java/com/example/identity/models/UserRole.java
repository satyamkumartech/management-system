package com.example.identity.models;

public enum UserRole {
    ROLE_STUDENT, ROLE_TEACHER, ROLE_ADMINISTRATOR;

    public static UserRole getInstance(String role) {
        if (role == null) {
            return null;
        }
        for (UserRole r : UserRole.values()) {
            if (role.equals(r.name())) {
                return r;
            }
        }
        return null;
    }

    public static String[] TEACHER_ACCESS = {ROLE_TEACHER.name(), ROLE_ADMINISTRATOR.name()};
    public static String[] ADMINISTRATOR_ACCESS = {ROLE_STUDENT.name(), ROLE_ADMINISTRATOR.name()};
}