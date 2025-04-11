package com.clava1096.musicstreaming.models.enumpack;

public enum UserRole {
    ROLE_USER,   // 0
    ROLE_ADMIN,  // 1
    ROLE_MOD,    // 2
    ROLE_ARTIST; // 3

    // Можно оставить ordinal() для обратной совместимости
    public String getAuthority() {
        return name();
    }
}
