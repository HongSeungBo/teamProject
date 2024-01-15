package com.projectteamspring.www.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.projectteamspring.www.domain.MemberFileVO;
import com.projectteamspring.www.domain.MemberPagingVO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@Component
public class MemberFileHandler {
	private final String UP_DIR = "D:\\_myweb\\_java\\fileupload\\";

	

	public MemberFileVO MemberFile(MultipartFile file){
		MemberFileVO mfvo = new MemberFileVO();
		String myFile = "Profile";
		LocalDate date = LocalDate.now();
		
		String today = date.toString();
		today = today.replace("-", File.separator);
		today = myFile + File.separator + today;
		
		File folders = new File(UP_DIR, today);
		
		if(!folders.exists()) {
			folders.mkdirs();
		}
		
		
			
			mfvo.setSaveDir(today);
			mfvo.setFileSize(file.getSize());
			String originalname = file.getOriginalFilename();
			String fileName = originalname.substring(originalname.lastIndexOf(File.separator)+1);
			mfvo.setFileName(fileName);
			
			UUID uuid = UUID.randomUUID();
			mfvo.setUuid(uuid.toString());
			String fullFileName = uuid.toString()+"_"+fileName;
			File storeFile = new File(folders, fullFileName);
			
			try {
				file.transferTo(storeFile);
				if(isImageFile(storeFile)) {
					File thumbNail = new File(folders, uuid.toString()+"_th_"+fileName);
					Thumbnails.of(storeFile).size(100, 100).toFile(thumbNail);
				}
			} catch (Exception e) {
				log.info("진짜 파일 핸들러 에러");
				e.printStackTrace();
			}
			
		
		return mfvo;
	}
	
	private boolean isImageFile(File StoreFile) throws IOException {
		String mimeType = new Tika().detect(StoreFile);
		return mimeType.startsWith("image")? true : false;
	}

	
}
