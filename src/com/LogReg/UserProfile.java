package com.LogReg;

public class UserProfile {
    private static String nama;
    private static String jenis;
    private static String email;
    private static String telp;

    public static void setProfile(String n, String j, String e, String t) {
        nama = n;
        jenis = j;
        email = e;
        telp = t;
    }

    public static String getNama() {
        return nama;
    }

    public static String getJenis() {
        return jenis;
    }
    
    public static String getEmail() {
        return email;
    }

    public static String getTelp() {
        return telp;
    }
}
