package com.biz.rbooks;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class MakeSecure {

	private final static String saltPass = "";
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		StandardPBEStringEncryptor pbEnc = new StandardPBEStringEncryptor();
		
		// 운영체제 환경변수 가져오기
		Map<String, String> envList = System.getenv();
		
		System.out.print("UserID >> ");
		String pUserId = scan.nextLine();
		
		System.out.print("Password >> ");
		String pPassword= scan.nextLine();
		
		// 운영체제 환경변수 중 oracle_pass로 등록된값 가져오기
		String envString = envList.get("ORACLE_PASS");
		if(envString == null) envString = saltPass;
			
		pbEnc.setAlgorithm("PBEWithMD5AndDES");
		pbEnc.setPassword(envString);
		
		String encUserId = pbEnc.encrypt(pUserId);
		String encPassword = pbEnc.encrypt(pPassword);
		
		System.out.printf("userid : %s, %s \n",  pUserId, encUserId);
		System.out.printf("userid : %s, %s \n",  pPassword, encPassword);
		
		String strUserId = String.format("oracle.username=ENC(%s)", encUserId);
		String strPassword= String.format("oracle.password=ENC(%s)", encPassword);
		
		String proFileName = "./src/main/webapp/WEB-INF/spring/oracle.sec.properties";
		try {
			
			PrintWriter out = new PrintWriter(proFileName);
			out.println(strUserId);
			out.println(strPassword);
			out.flush();
			out.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		scan.close();
	}
}
