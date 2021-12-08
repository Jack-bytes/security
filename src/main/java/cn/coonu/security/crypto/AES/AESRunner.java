package cn.coonu.security.crypto.AES;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AESRunner {

    private static final String ALGORITHM = "AES/CBC/PKCS7Padding";

    private final byte[] aesKey;

    private final byte[] iv;

//    private String mode;
//
//    private String padding;

    public AESRunner(byte[] aesKey, byte[] iv) {
        this.aesKey = aesKey;
        this.iv = iv;
    }

    public byte[] encrypt(byte[] plaintext) {
        byte[] ciphertext;
        try {
            Cipher cipher = Cipher.getInstance(AESRunner.ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(this.aesKey, "AES"), new IvParameterSpec(this.iv));
            ciphertext = cipher.doFinal(plaintext);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException("错误算法", e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException("非法秘钥", e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException("非法参数", e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException("非法size", e);
        } catch (BadPaddingException e) {
            throw new RuntimeException("非法", e);
        }
        return ciphertext;
    }

    public byte[] decrypt(byte[] ciphertext) {
        byte[] plaintext;
        try {
            Cipher cipher = Cipher.getInstance(AESRunner.ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(this.aesKey, "AES"), new IvParameterSpec(this.iv));
            plaintext = cipher.doFinal(ciphertext);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException("错误算法", e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException("非法秘钥", e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException("非法参数", e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException("非法size", e);
        } catch (BadPaddingException e) {
            throw new RuntimeException("非法", e);
        }
        return plaintext;
    }

}
