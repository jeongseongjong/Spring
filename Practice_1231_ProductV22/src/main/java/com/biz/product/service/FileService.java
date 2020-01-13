package com.biz.product.service;

import java.io.File;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileService {

	@Autowired
	ServletContext context;

	// final을 사용하는 이유 : 한번 선언시 수정 x 
	private final String filesPath = "/bizwork/files/";

	public String fileUp(MultipartFile u_file) {

		// 업로드된 파일정보의 파일이름만 추출
		String originName = u_file.getOriginalFilename();

		// tomcat server가 작동되고 있는곳에 대한 정보(path)
		// getRealPath("/")
		// tomcat server가 우리 프로젝트를 서비스 할 때
		// root로 설정하여 여러가지 정보를 저장하고 있는 폴더정보
		String upLoadPath = context.getRealPath("/");

		// upLoadPath : /product/files/
		upLoadPath += "files/";

		upLoadPath = filesPath;
		if (u_file != null) {

			// /files/폴더에 대한 io정보 추출
			File dir = new File(upLoadPath);
			
			// 현재 서버에 /files/라는 폴더가 없으면
			if(!dir.exists()) {
				
				// 폴더를 생성해라
				dir.mkdirs();
			}
			
			// 해킹위험으로 인한 유일한 ID생성
			String strUUID = UUID.randomUUID().toString();
			strUUID += originName;
			
			// upLoadPath + originName = upLoadFile
			// /product/files/2019.jpg라는 이름으로 파일명을 만들고
			// 해당하는 파일에 대한 정보를 객체로 생성하라
			File upLoadFile = new File(upLoadPath, strUUID);
			
			// web에서 upLoad한 파일(u_file)을
			// 방금 설정한 파일이름(upLoadFile)
			// web에서 서버로 파일이 복사된다.
			try {
				
				// u_file을 upLoadFile로 복사하라
				// upLoadFile로 저장
				u_file.transferTo(upLoadFile);
				
				// 파일이 정상적으로 업로드 되면
				// originName을 Controller로 return할 것이다.
				return strUUID;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		// 파일이 정상적으로 upLoad 되지 않으면
		// null을 Controller로 return하라
		return null;
	}
	
	public void fileDelete(String p_file) {
		
		File dFile = new File(filesPath, p_file);
		
		// dFile이 존재하면
		if(dFile.exists()) {
			
			// dFile이 삭제되면 참 안되면 거짓
			boolean ok = dFile.delete();
			
			if(ok) {
				log.debug("파일 삭제 성공");
			}else {
				log.debug("파일 삭제 실패");
			}
			
		}else {
			log.debug("삭제할 파일이 없다");
		}
	}
}
