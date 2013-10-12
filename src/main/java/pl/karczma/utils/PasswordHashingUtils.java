package pl.karczma.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashingUtils {

	public String hashAndReturn(String password) {

		try {
			MessageDigest hasher = MessageDigest.getInstance("SHA-256");
			hasher.update(password.getBytes("UTF-8"));
			byte[] result = hasher.digest();
			return getHexString(result);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Error occured";
	}

	public static String getHexString(byte[] b) throws Exception {
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}

}
