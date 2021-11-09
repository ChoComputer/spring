package org.ict.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.ict.domain.AttachFileDTO;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
public class UploadController {
	
	// 이미지 여부를 판단해 썸네일 제작 여부를 판단해주는 메서드
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			
			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	// 날짜에 맞춰서 폴더형식을 만들어주는 메서드
	// 파일 업로드시 업로드 날짜별로 폴더를 만들어 관리할것이기 때문
	private String getFolder() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		// java.util.Date
		Date date = new Date();
		
		String str = sdf.format(date);
		
		return str.replace("-", File.separator);
		
	}
	
	@GetMapping("/uploadForm")
	public void uploadForm() {
		
		log.info("upload form");
	}
	
	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
	
		// 저장 경로
		String uploadFolder = "C:\\upload_data\\temp";
		
		for(MultipartFile multipartFile : uploadFile) {
			
			log.info("-------------------------");
			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
			log.info("Upload File Size : " + multipartFile.getSize());
			
			// 어디에 어떤 파일을 저장할지
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			try {
				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			
		}
	}
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		
		log.info("upload ajax");
	}
	
	// 일반 컨트롤러에서 rest요청을 처리시키는 경우에 @ResponseBody를 붙여줍니다.
	@ResponseBody
	@PostMapping("/uploadAjaxAction")
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(
											MultipartFile[] uploadFile) {
		
		log.info("ajax post update!");
		
		// 그림 여러개를 받아야 하기 때문에 먼저 ArrayList를 생성
		List<AttachFileDTO> list = new ArrayList<>();
		
		String uploadFolder = "C:\\upload_data\\temp";
		
		// 폴더 생성
		File uploadPath = new File(uploadFolder, getFolder());
		log.info("upload path: " + uploadPath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		
		for(MultipartFile multipartFile : uploadFile) {
			// 파일 이름, 폴더경로(업로드날짜), UUID, 그림파일 여부를 모두
			// 이 반복문에서 처리하므로 제일 상단에 먼저 그림정보를 받는
			// AttachFileDTO를 생성합니다.
			AttachFileDTO attachDTO = new AttachFileDTO();
			
			log.info("--------------------");
			log.info("Upload file name: " + multipartFile.getOriginalFilename());
			log.info("Upload file size: " + multipartFile.getSize());
			
			String uploadFileName = multipartFile.getOriginalFilename();
			
			log.info("파일명 \\짜르기전 : " + uploadFileName);
			
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			
			log.info("last file name : " + uploadFileName);

			// uploadFileName에 uuid가 포함되기 전에 세터에 넣어줘야
			// 파일 이미지를 불러오는데 문제가 없음
			attachDTO.setFileName(uploadFileName);
			
			// UUID 발급하기
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			try {
				//File saveFile = new File(uploadFolder, uploadFileName);
				//경로를 고정폴더인 uploadFolder에서 날짜별 가변폴더인 uploadPath로 변경
				File saveFile= new File(uploadPath, uploadFileName); 
				multipartFile.transferTo(saveFile);
				
				// AttachFileDTO에 세터로 데이터 입력
				attachDTO.setUuid(uuid.toString());
				attachDTO.setUploadPath(getFolder());
				
				// 이 아래부터 썸네일 생성로직
				if(checkImageType(saveFile)) {
					// 클래스 생성 후 boolean타입은 자료입력을 하지 않으면
					// 자동으로 false로 간주됨
					// 이미지인경우는 true를 넣고, 이미지가 아니면 그냥 건들지 않습니다.
					attachDTO.setImage(true);
					
					FileOutputStream thumbnail= 
						new FileOutputStream(
							new File(uploadPath, "s_" + uploadFileName));
					
					Thumbnailator.createThumbnail(
						multipartFile.getInputStream(), thumbnail, 100, 100);
					thumbnail.close();
				}
				// 정상적으로 그림에 대한 정보가 입력되었다면 list에 쌓기
				list.add(attachDTO);
			} catch(Exception e) {
				log.error(e.getMessage());
			}
			
		}// end for
		return new ResponseEntity<List<AttachFileDTO>>(list, HttpStatus.OK);
	}// uploadAjaxPost
	
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName){
		
		log.info("fileName : " + fileName);
		
		File file = new File("c:\\upload_data\\temp\\" + fileName);
		
		log.info("file : " + file);
		
		ResponseEntity<byte[]> result = null;
		
		try {
			HttpHeaders header = new HttpHeaders();
			
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),
										header,
										HttpStatus.OK);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@GetMapping(value="/download", 
				produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(String fileName){
		
		log.info("download file : " + fileName);
		
		Resource resource = new FileSystemResource(
								"C:\\upload_data\\temp\\" + fileName);
		
		log.info("resource: " + resource);
		
		String resourceName = resource.getFilename();
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			headers.add("Content-Disposition", "attachment; filename=" +
						new String(resourceName.getBytes("UTF-8"), "ISO-8859-1"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource,
											headers,
											HttpStatus.OK);
	}
	
	
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String type){
		log.info("deleteFile : " + fileName);
		// 빈 파일, 추후에 삭제할 파일을 대입해 삭제 진행
		File file = null;
		
		try {
			// url의 파일 이름을 원래대로 복원시킴
			file = new File("c:\\upload_data\\temp\\" 
						+ URLDecoder.decode(fileName, "UTF-8"));
			
			file.delete();
			
			// 웹화면에서 썸네일을 먼저 삭제하기 때문에 원본파일도 마저 잡아서 삭제해야함
			if(type.equals("image")) {
				// 썸네일 파일명에서 s_를 소거해 원본으로 교체
				String largeFileName = file.getAbsolutePath().replace("s_", "");
				
				log.info("원본파일명 : " + largeFileName);
				
				file = new File(largeFileName);
				
				file.delete();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("delete", HttpStatus.OK);
	}
	
	
	
}






