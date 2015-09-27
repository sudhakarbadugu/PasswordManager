package com.blmsr.manager.util;

import android.util.Log;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Created by LakshmiMadhav on 8/26/2015.
 * This class has some utility methods to encrypt and decrypt.
 * This class has the Message digestion methods to digest the text.
 */
public class EncryptionUtil {
    private static final String CLASS_NAME = "EncryptionUtil";
    private static final CryptoUtil CRYPTO_UTIL_INSTANCE = new CryptoUtil();
    /**
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static String generateStrongPasswordHash(String password) {
        String aPassword = "1000";
        try {
            int iterations = 1000;
            char[] chars = password.toCharArray();
            byte[] salt = getSalt().getBytes();

            PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = skf.generateSecret(spec).getEncoded();
            aPassword = iterations + ":" + toHex(salt) + ":" + toHex(hash);
        } catch (Exception anException) {
            Log.e(CLASS_NAME, " " + anException);

        }

        return aPassword;
    }

    private static String getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt.toString();
    }

    private static String toHex(byte[] array) throws NoSuchAlgorithmException {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }

    public static boolean validatePassword(String originalPassword, String storedPassword) {
        try {

            String[] parts = storedPassword.split(":");
            int iterations = Integer.parseInt(parts[0]);
            byte[] salt = fromHex(parts[1]);
            byte[] hash = fromHex(parts[2]);

            PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] testHash = skf.generateSecret(spec).getEncoded();

            int diff = hash.length ^ testHash.length;
            for (int i = 0; i < hash.length && i < testHash.length; i++) {
                diff |= hash[i] ^ testHash[i];
            }
            return diff == 0;

        } catch (Exception anException) {
            Log.e(CLASS_NAME, " " + anException);
        }
        return false;
    }

    private static byte[] fromHex(String hex) throws NoSuchAlgorithmException {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

    private static byte[] iv =
            {0x0a, 0x01, 0x02, 0x03, 0x04, 0x0b, 0x0c, 0x0d};

    // Key size must be 16 characters.
    private static final String DEFAULT_KEY = "PasswordBestKey";

    private static final String ENCODINGTYPE = "UTF-8";

    /*
     * Provides basic encryption on a String.
     * @param theValue the String which need to be encrypted.
     * @param theKeyValue the String which need to be used as key for encryption mechanism. It should be 16 characters only.
     * @return Returns The string in encrypted form in byte[] array.
     */
    public static String encrypt(String theValue) {
        try {
            return CRYPTO_UTIL_INSTANCE.encrypt(DEFAULT_KEY, theValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * Provides decryption of a String encrypted using encrypt()
     *
     * @param theEncryptedString The string in encrypted form in byte[] array.
     * @return String the decrypted string.
     */
    public static String decrypt(String theEncryptedString) {
        try {
            return CRYPTO_UTIL_INSTANCE.decrypt(DEFAULT_KEY, theEncryptedString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static void main(String[] args) throws Exception {
        String plain = "test";
        String enc = encrypt(plain);
        System.out.println("Original text: " + plain);
        System.out.println("Encrypted text: " + enc);
        String plainAfter = decrypt(enc);
        System.out.println("Original text after decryption: " + plainAfter);
    }
}
