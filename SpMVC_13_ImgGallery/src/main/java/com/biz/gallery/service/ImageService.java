package com.biz.gallery.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.biz.gallery.domain.ImageFilesVO;
import com.biz.gallery.domain.ImageVO;
import com.biz.gallery.repository.ImageDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ImageService {

	private final ImageDao imDao;
	
	private final FileService fService ;
	private final ImageFileService ifService;
	
	/*
	 * 기존 방법 : setter 주입방식
	 * 		@Autowired
	 * 		클래스 객체
	 */
	/*
	 * 생성자한테 한번에 주입시켜 하나하나 주입을 해준다(생성자에 @Autowired)
	 * 생성자가 존재할 경우 클래스를 final로 선언할 수 있다.(안전하다)
	 * 
	 * 생성자에서 객체 주입
	 * 사용하는 객체를 fianl로 선언해서 보호할 수 있고
	 * 혹시 모를 해킹에 의한 데이터 변조를 막을 수 있다.
	 * 클래스의 교차찾모를 컴파일러 차원에서 방지할 수 있다.
	 * 코드가 다소 번잡스러울 수 있지만
	 * setter 주입보다는 constructor(생성자) 주입방식을 사용하자
	 * 인텔리제이(inteliJ) setter 주입방식을 사용하면
	 * 	ide가 심각한 경고를 보인다
	 */
	@Autowired
	public ImageService(ImageDao imDao, ImageFileService ifService, FileService fService) {
		super();
		this.imDao = imDao;
		this.fService = fService;
		this.ifService = ifService;
	}

	public List<ImageVO> selectAll(){
		return imDao.selectAll();
	}
	
	/*
	 * 일반적으로 dao.insert(VO)를 호출했을 때
	 * 담아서 전달한 값은 insert()가 수행된 후에 볼 수 있으나
	 * seq처럼 SQL에서 생성된 값은 확인 불가능하다.
	 * 그런데 이 값을 insert()후에 사용해야할 경우가 있다.
	 * 
	 *  이때는 dao, mapper SelectKey를 사용해서 값을
	 *  생성하면 insert() 후에 그 값을 사용할 수 있다.
	 */
	public int insert(ImageVO imageVO) {
		
		List<String> fileList = imageVO.getImg_files();
		
		
		// 여러개의 파일중 0번 파일을 대표파일로 업로드
		if(fileList != null && fileList.size() > 0) {
			imageVO.setImg_file(imageVO.getImg_files().get(0));
		}
		
		// 1 tbl_gallery에 데이터에 insert
		int ret = imDao.insert(imageVO);
		
		// 2. 파일이름을 ImageFilesVO의 리스트에 생성
		// ImageFilesO에 img_file_p_code 칼럼에
		// tbl_gallery의 seq값을 추가해서 리스트 생성
		List<ImageFilesVO> files = new ArrayList<ImageFilesVO>();
		
		if(fileList != null ) {
			for(String fileName : fileList) {
				
				files.add(ImageFilesVO.builder()
					.img_file_upload_name(fileName)
					.img_file_p_code(imageVO.getImg_seq())
					.build());
				
			}
			
			// 3. 파일정보를 tbl_images에 insert
			ifService.imageFileInsert(files);
		}
		
		log.debug(imageVO.toString());
		
		return ret;
	}

	public ImageVO findBySeq(String img_seq) {

		ImageVO imgVO = imDao.findBySeq(img_seq);
		
		log.debug(imgVO.toString());
		
		return imgVO;
	}

	/*
	 * 혹시 파일이 변경이 되면 기존파일을 제거하고
	 * 새로운 파일 등록
	 */
	public int update(ImageVO imageVO) {

		ImageVO oldImageVO = imDao.findBySeq(imageVO.getImg_seq() + "");
		
		
		
		// 새로 업데이트된 파일이름이
		// 기존 테이블에 저장된 파일이름과 다르면
		// 먼저 파일을 삭제한다.
		if (oldImageVO.getImg_file() != null && imageVO.getImg_file() != null) {
			
			if (!oldImageVO.getImg_file().equals(imageVO.getImg_file())) {
				fService.file_delete(oldImageVO.getImg_file());
			}
		}

		int ret = imDao.update(imageVO);
		return ret;
	}

	public int delete(String img_seq) {

		ImageVO imageVO = imDao.findBySeq(img_seq);
		
		if(imageVO.getImg_file() != null) {
			fService.file_delete(imageVO.getImg_file());
		}
		int ret = imDao.delete(img_seq);
		
		return ret;
	}
	
	public List<String> files_up(MultipartHttpServletRequest mFiles) {
		
		List<String> fileNames = new ArrayList<String>();
		
		for (MultipartFile file : mFiles.getFiles("files")) {
			
			// 1개의 파일을 업로드하고
			// 저장한 파일이름을 return
			
			// return된 저장파일이름을 list에 추가
			fileNames.add(fService.file_up(file));
			
		}
		
		//list를 return
		return fileNames;
	}
}














