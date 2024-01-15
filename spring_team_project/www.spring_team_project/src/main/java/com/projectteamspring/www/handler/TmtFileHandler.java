package com.projectteamspring.www.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.projectteamspring.www.domain.TmtFileSaveVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TmtFileHandler {
	private final String UP_DIR="D:\\_myweb\\_java\\fileupload";
	
	public TmtFileSaveVO tmtFile(MultipartFile file) {
		TmtFileSaveVO tvo = new TmtFileSaveVO();
		String myFile = "tmtFile";
		LocalDate date = LocalDate.now();
		
		String today = date.toString();
		today = today.replace("-", File.separator);
		today = myFile+File.separator+today;
		File folders = new File(UP_DIR, today);
		
		if(!folders.exists()) {
			folders.mkdirs();
		}
		
		tvo.setSaveDir(today);
		String originalFileName = file.getOriginalFilename();
		String fileName = originalFileName.substring(originalFileName.lastIndexOf(File.separator)+1);
		tvo.setFileName(fileName);
		
		UUID uuid = UUID.randomUUID();
		tvo.setUuid(uuid.toString());
		
		String fullFileName = uuid.toString()+"_"+fileName;
		File storeFile = new File(folders, fullFileName);
		
		try {
			file.transferTo(storeFile);
			if(isImageFile(storeFile)) {
				tvo.setFileType(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return tvo;
	}
	
	private boolean isImageFile(File StoreFile) throws IOException {
		String mimeType = new Tika().detect(StoreFile);
		return mimeType.startsWith("image")? true : false;
	}
	
}
