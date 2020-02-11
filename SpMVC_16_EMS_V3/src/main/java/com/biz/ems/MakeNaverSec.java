package com.biz.ems;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class MakeNaverSec {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		StandardPBEStringEncryptor pbEnc = new StandardPBEStringEncryptor();

									// 환경변수 가져오기
		Map<String, String> envList = System.getenv();
		String saltPass = envList.get("NAVER");
		System.out.println("SaltPass : " + saltPass);

		if (saltPass == null || saltPass.isEmpty()) {
			System.out.println("salt Password Not Found");
			scanner.close();
			return;
		}

		// 암호화 설정
		pbEnc.setAlgorithm("PBEWithMD5AndDES");
		pbEnc.setPassword(saltPass);

		Map<String, String[]> secFileList = new TreeMap<String, String[]>();

		secFileList.put("hiber.user.properties", new String[] { 
				"mysql.user", "mysql.password"
		});
		secFileList.put("naver.email.properties",
				new String[] { "naver.username", "naver.password", "naver.client.id", "naver.client.secret" });

		// properties를 저장하는 곳
		String proFileDir = "./src/main/webapp/WEB-INF/spring/appServlet/props";

		// keySet : 모든 key값을 set으로 리턴한다.
		// secFileList의 key값을 files라는 Set형 Map에 주입한다.
		Set<String> files = secFileList.keySet();
		
		try {
			
			for (String file : files) {

				// File형의 proFile객체에 properties저장경로와 String형 file을 주입한다.
				File proFile = new File(proFileDir, file);
				System.out.println("============================================");
				// proFile에 담겨져 있는 경로(입력된 절대경로)를 가져온다.
				System.out.println(proFile.getAbsolutePath() + "파일 생성");
				System.out.println("--------------------------------------------");
				
				// PrintWriter : 파일을 생성, 수정, 삭제할 경우 쓰인다.
				PrintWriter out = new PrintWriter(proFile);
				
				// secFileList :  properties가 담겨져있는 List
				// secFileList에 있는 File을 가져와서 key객체에 반복문을 돌린다.
				for(String key : secFileList.get(file)) {
				
					System.out.print(key + " : ");
					String plainString = scanner.nextLine();
					if(plainString.isEmpty()) {
						System.out.println("Exit !!");
						out.close();
						return;
					}
					
					String encString= pbEnc.encrypt(plainString);
					
					// 변수 = ENC(값)
					encString = String.format("%s=ENC(%s)", key, encString);
					System.out.println(encString);
					out.println(encString);
					out.flush();
				}
				out.close();
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		
		}
		scanner.close();
	}

}
