package cn.coonu.security.crypto.AES;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

public class AESRunner {

    private static final String ALGORITHM = "AES/CBC/PKCS7Padding";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private final byte[] aesKey;

    /**
     * 初始向量, CBC模式中引入的, 主要作用是为了防止数据段加密后呈现一致, 引入初始向量之后即使每段数据块一致,
     * 加密后的数据块也会不一样, 提高安全性;
     */
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
            Cipher cipher = Cipher.getInstance(AESRunner.ALGORITHM, "BC");
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
        } catch (NoSuchProviderException e) {
            throw new RuntimeException("非法1", e);
        }
        return ciphertext;
    }

    public byte[] decrypt(byte[] ciphertext) {
        byte[] plaintext;
        try {
            Cipher cipher = Cipher.getInstance(AESRunner.ALGORITHM, "BC");
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
        } catch (NoSuchProviderException e) {
            throw new RuntimeException("非法1", e);
        }
        return plaintext;
    }

}
