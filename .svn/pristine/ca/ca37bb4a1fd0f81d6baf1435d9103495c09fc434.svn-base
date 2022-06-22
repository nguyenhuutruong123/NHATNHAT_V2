package geso.dms.center.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
public class Encryption_T 
{
	private static final String ALGORITHM = "AES";
	
	public static SecretKeySpec getSecretKeySpec() throws NoSuchAlgorithmException
	{
		String myKey = Utility.SECRET_KEY;
    	byte[] key = myKey.getBytes(StandardCharsets.UTF_8);
    	MessageDigest sha = MessageDigest.getInstance("SHA-1");
    	key = sha.digest(key);
        key = Arrays.copyOf(key, 16);
        SecretKeySpec  secretKey = new SecretKeySpec(key, ALGORITHM);
        return secretKey;
	}
	
	public static String encrypt(String strToEncrypt) {
        try 
        {
        	SecretKeySpec  secretKey = getSecretKeySpec() ;
            
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.encodeBase64String((cipher.doFinal(strToEncrypt.getBytes("UTF-8"))));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
	public static String decrypt(String strToDecrypt) {
	    try {
	    	SecretKeySpec  secretKey = getSecretKeySpec() ;
	        Cipher cipher = Cipher.getInstance(ALGORITHM);
	        cipher.init(Cipher.DECRYPT_MODE, secretKey);
	        return new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt)));
	    } catch (Exception e) {
	        System.out.println("Error while decrypting: " + e.toString());
	    }
	    return null;
	}
}