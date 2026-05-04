package com.Usage_Based.API.Billing.Platform.MeterFlow.security;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class RazorpayUtils {


		public static boolean verifySignature(String payload, String actualSignature, String secret) {
	        try {
	            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
	            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
	            sha256_HMAC.init(secret_key);

	            byte[] hash = sha256_HMAC.doFinal(payload.getBytes());

	            String generatedSignature = bytesToHex(hash);

	            return generatedSignature.equals(actualSignature);

	        } catch (Exception e) {
	            return false;
	        }
	    }

	    private static String bytesToHex(byte[] bytes) {
	        StringBuilder hex = new StringBuilder(bytes.length * 2);
	        for (byte b : bytes) {
	            String s = Integer.toHexString(0xff & b);
	            if (s.length() == 1) hex.append('0');
	            hex.append(s);
	        }
	        return hex.toString();
	    }
	}