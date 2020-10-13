package by.belhard.j24.exampleproject.controller;

import org.apache.commons.codec.digest.DigestUtils;

public class Test {

    public static void main(String[] args) {

        String s1 = "abc";
        String s1hex = DigestUtils.sha256Hex(s1);
        String s2 = "abcdef";
        String s2hex = DigestUtils.sha256Hex(s2);
        System.out.println(s1);
        System.out.println(s1hex);
        System.out.println(s1hex.length());
        System.out.println(s2);
        System.out.println(s2hex);
        System.out.println(s2hex.length());
    }
}
