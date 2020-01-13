package com.biz.product.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.biz.product.domain.ProFileDTO;
import com.biz.product.persistence.FileDao;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class FileService {
	
	@Autowired
	SqlSession sqlSession;
	
	FileDao fileDao;
	
	@Autowired
	public void fileDao() {
		this.fileDao = sqlSession.getMapper(FileDao.class);
	}
	
	@Autowired
	String winFilePath;
	
	@Autowired
	String macFilePath;
	
	String fileUpLoadPath;
	
	/*
	 * macFilePath를 검사하여
	 * path가 존재하면 파일 업로드 path를 macFilePath로 변경하고
	 * 그렇지 않으면 winFilePath를 업로드 폴더로 쓰겠다. 
	 */
	@Autowired
	public void fileUpPath() {
		this.fileUpLoadPath = this.macFilePath;
		File path = new File(this.winFilePath);
		
		if(path.exists()) {
			this.fileUpLoadPath = this.winFilePath;
		}
	}
	
	/*
	 * 여러개의 파일들을 개별파일로 분리하여
	 * fileUp() method에게 보내서 파일을 업로드하도록 하고
	 * fileUp()이 새성한 업로드 파일이름을 return받아서
	 * List에 추가한 후 파일이름들 List를 Controller로 return
	 */
	public List<ProFileDTO> filesUp(MultipartHttpServletRequest u_files) {
		
		if(u_files.getFile("u_files").getSize() < 1 )return null;
		
		List<ProFileDTO> upFileList = new ArrayList<ProFileDTO>();
		
		/*
		 * 만약 개별파일을 업로드 하는중에 문제가 발생을 하면
		 * controller에게 null 값을 return하여 파일 업로드에
		 * 문제가 발생했음을 알리자
		 */
		try {
			for(MultipartFile file : u_files.getFiles("u_files")) {
				
				// 파일을 업로드하고 파일병 받기
				// upFileName = UUID + originFileName
				String upFileName = this.fileUp(file);
				ProFileDTO pf = ProFileDTO.builder()
						.file_origin_name(file.getOriginalFilename())
						.file_upload_name(upFileName)
						.build();
				upFileList.add(pf);
			}
		} catch (Exception e) {
			log.debug("Exception : " + e.getMessage());
			return null;
		}
		
		
		return upFileList;
	}
	
	public String fileUp(MultipartFile u_file) throws Exception {
		
		// 업로드된 파일정보에서 파일이름만 추출
		String originName = u_file.getOriginalFilename();
		
		// tomcat server가 작동되고 있는곳에 대한 정보(path)
		// getRealPath("/")
		// tomcat server가 우리 프로젝트를 서비스 할 때
		// root로 설정하여 여러가지 정보를 저장하고 있는 폴더정보
	// 	String upLoadPath = winFile
		
		// upLoadPath : /product/files/
	//	upLoadPath += "files/";
		
		
		if(u_file != null) {
			
			// /files/폴더에 대한 io정보를 추출
			File dir = new File(fileUpLoadPath);
			
			// 현재 서버에 /files/라는 폴더가 없으면
			if(!dir.exists()) {
				
				if(dir.mkdirs()) {
					log.debug("폴더 생성 OK");
				}else {
					fileUpLoadPath = this.macFilePath;
				}
				
				// 폴더를 생성해라
				dir.mkdirs();
			}
			
			// UUID란? 하나밖에 없는 랜덤값ID
			// original 파일 이름을 사용을 하면
			// 해킹 사고를 일으킬 수 있기 때문에
			// 파일이름을 UUID로 설정하여 DB테이블에 저장하자
			String strUUID = UUID.randomUUID().toString();
			strUUID += originName;
			
			// upLoadPath + originName = upLoadFile과 같다
			// /product/files/2019.jpg라는 이름으로 파일명을 만들고
			// 해당하는 파일에 대한 정보를 객체로 생성하라
			File upLoadFile = new File(fileUpLoadPath, strUUID);
			
			// web에서 upLoa된 파일(u_file)을
			// 방금 설정한 파일이름(upLoadFile)
			// web에서 server로 파일이 복사가 된다.
			try {
				
				// u_file을 upLoadFile로 복사하라
				// upLoadFile로 저장하라
				u_file.transferTo(upLoadFile);
				
				// 파일이 정상적으로 업로드가 되면
				// originName을 Controller로 return할 것이고
				return strUUID;
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
		
		// 파일이 정상적으로 upload가 안되면
		// null값을 Controller로 return하라
		return null;
	}

	public void fileDelete(String p_file) {

		File dFile = new File(fileUpLoadPath, p_file);
		if(dFile.exists()) {
			boolean ok = dFile.delete();
			if(ok) {
				log.debug("파일 삭제 성공");
			}else {
				log.debug("파일 삭제 실패");
			}
		}else {
			log.debug("삭제할 파일이 없음");
		}
		
	}

	public ProFileDTO findByFileSeq(String file_seq) {
		// TODO Auto-generated method stub
		
		return fileDao.findByFileSeq(file_seq);
	}

	public List<ProFileDTO> getFiles(String p_code) {

		return fileDao.findByPCode(p_code);
		
	}
}
