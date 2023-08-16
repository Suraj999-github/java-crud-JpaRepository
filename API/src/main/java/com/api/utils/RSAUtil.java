package com.api.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;

public class RSAUtil {
	ObjectMapper objectMapper = new ObjectMapper();
	  public static PublicKey getPublicKey(String base64PublicKey){
	        PublicKey publicKey = null;
	        try{
	            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
	            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	            publicKey = keyFactory.generatePublic(keySpec);
	            return publicKey;
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        } catch (InvalidKeySpecException e) {
	            e.printStackTrace();
	        }
	        return publicKey;
	    }

	    public static PrivateKey getPrivateKey(String base64PrivateKey){
	        PrivateKey privateKey = null;
	        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
	        KeyFactory keyFactory = null;
	        try {
	            keyFactory = KeyFactory.getInstance("RSA");
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        try {
	            privateKey = keyFactory.generatePrivate(keySpec);
	        } catch (InvalidKeySpecException e) {
	            e.printStackTrace();
	        }
	        return privateKey;
	    }

	    public static String encrypt(String data) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, IOException {
	        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(GetPublickKey()));
	        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
	    }

	    public static String decrypt(byte[] data, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
	        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	        cipher.init(Cipher.DECRYPT_MODE, privateKey);
	        return new String(cipher.doFinal(data));
	    }

	    public static String decrypt(String data) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, IOException {	       
	    	return decrypt(Base64.getDecoder().decode(data.getBytes()), getPrivateKey(GetPrivateKey()));
	    }
	    
		public String getRequestBody(HttpServletRequest request) throws IOException {

			 StringBuilder sb = new StringBuilder();
			 BufferedReader reader = null;
	         reader = request.getReader();
	         String line;
	         int c=0;
	         while ((line = reader.readLine()) != null) {
	         	if(c==0) {
	         		c=1;
	         		sb.append(line);
	         	}else {
	         		sb.append("\n");
	         		sb.append(line);
	         	}          
	         }
	         String requestBody = sb.toString();       
		        return requestBody;		        
		    }
		 
		 public String generateSignature(HttpServletRequest request) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, IOException {					 
			  String payload= objectMapper.writeValueAsString(request);		       
			  String encryptedtext=encrypt(payload);
			  return encryptedtext;
		 }
		 
		 public String compareSignature(String requestBody,String requestedSignature) {
			 return requestBody;
		 }
		 
		public static String GetPublickKey() throws IOException{    	

		    	 String file ="src/main/resources/publickey.txt";	         
		         BufferedReader reader = new BufferedReader(new FileReader(file));
		         String currentLine = reader.readLine();         
		          return currentLine;
		    }
		 public static String GetPrivateKey() throws IOException{    	

		    	 String file ="src/main/resources/privatekey.txt";	         
		         BufferedReader reader = new BufferedReader(new FileReader(file));
		         String currentLine = reader.readLine();         
		          return currentLine;
		    }
		 
		 public  String getBody(HttpServletRequest request) throws IOException {

			    String body = null;
			    StringBuilder stringBuilder = new StringBuilder();
			    BufferedReader bufferedReader = null;

			    try {
			        InputStream inputStream = request.getInputStream();
			        if (inputStream != null) {
			            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			            char[] charBuffer = new char[128];
			            int bytesRead = -1;
			            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
			                stringBuilder.append(charBuffer, 0, bytesRead);
			            }
			        } else {
			            stringBuilder.append("");
			        }
			    } catch (IOException ex) {
			        throw ex;
			    } finally {
			        if (bufferedReader != null) {
			            try {
			                bufferedReader.close();
			            } catch (IOException ex) {
			                throw ex;
			            }
			        }
			    }

			    body = stringBuilder.toString();
			    return body;
			}
		    
}
