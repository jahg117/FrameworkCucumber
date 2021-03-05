package utils;

import org.apache.commons.codec.binary.Base64;

public class Credentials {
    public static void main(String[] args) {
        String originalInput = "anVhbmFsZWphbmRyb2hlcjE=";
        String decodedString = new String(Base64.decodeBase64(originalInput.getBytes()));
        System.out.println(decodedString);
    }
}
