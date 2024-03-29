/**
 * 
 */
package com.sree.common.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author srinivasr
 *
 */
public class CommonUtil {
	public static String encript(String toEnc){
		MessageDigest mdEnc;
		String md5 = null ;
		try {
			mdEnc = MessageDigest.getInstance("MD5");
			mdEnc.update(toEnc.getBytes(), 0, toEnc.length());
			md5 = new BigInteger(1, mdEnc.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		return md5;
	}
	
	public static void main(String args[]){
		System.out.println(encript("admin"));
	}
}
