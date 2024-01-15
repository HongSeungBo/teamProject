package com.projectteamspring.www.handler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.projectteamspring.www.domain.MapFileVO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@Component
public class MapFileHandler {
	private final String UP_DIR = "D:\\_myweb\\_java\\fileupload";
	
	public List<MapFileVO> MapFiles(MultipartFile[] files,long mno){
		List<MapFileVO> flist = new ArrayList<MapFileVO>();
		String myFile = "MapFile";
		
		File folders = new File(UP_DIR,"MapFile"+mno);
		if(!folders.exists()) {
			folders.mkdirs();
		}
		
		for(MultipartFile file : files) {
			MapFileVO mfvo = new MapFileVO();
			mfvo.setMno(mno);
			mfvo.setSaveDir("MapFile"+mno);
			
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
					Thumbnails.of(storeFile).size(300, 300).toFile(thumbNail);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			flist.add(mfvo);
			
		}
		
		return flist;
	}
	
	private boolean isImageFile(File StoreFile) throws IOException {
		String mimeType = new Tika().detect(StoreFile);
		return mimeType.startsWith("image")? true : false;
	}
}
