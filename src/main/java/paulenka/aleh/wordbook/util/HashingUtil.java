package paulenka.aleh.wordbook.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashingUtil {

	private static String DIGEST_NAME_SHA_2 = "SHA-256";

	public static byte[] sha2(String data) throws NoSuchAlgorithmException {
		return data == null ? null : sha2(data.getBytes());
	}

	public static byte[] sha2(byte[] data) throws NoSuchAlgorithmException {
		return data == null ? null : MessageDigest.getInstance(DIGEST_NAME_SHA_2).digest(data);
	}
}