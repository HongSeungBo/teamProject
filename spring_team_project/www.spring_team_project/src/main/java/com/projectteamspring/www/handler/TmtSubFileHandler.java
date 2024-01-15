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

import com.projectteamspring.www.domain.TmtFileSaveVO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@Component
public class TmtSubFileHandler {
	private final String UP_DIR ="D:\\_myweb\\_java\\fileupload";
	
	public List<TmtFileSaveVO> subFiles(MultipartFile[] files){
		List<TmtFileSaveVO> flist = new ArrayList<>();
		String myFile = "tmtSubFile";
		LocalDate date = LocalDate.now();
		
		String today = date.toString();
		today = today.replace("-", File.separator);
		today = myFile+File.separator+today;
		
		File folders = new File(UP_DIR, today);
		if(!folders.exists()) {
			folders.mkdirs();
		}
		
		for(MultipartFile file : files) {
			TmtFileSaveVO tvo = new TmtFileSaveVO();
			tvo.setSaveDir(today);
			String originalFilename = file.getOriginalFilename();
			String fileName = originalFilename.substring(originalFilename.lastIndexOf(File.separator)+1);
			tvo.setFileName(fileName);
			
			UUID uuid = UUID.randomUUID();
			tvo.setUuid(uuid.toString());
			String fullFileName = uuid.toString()+"_"+fileName;
			File storeFile = new File(folders, fullFileName);
			try {
				file.transferTo(storeFile);
				if(isImageFile(storeFile)) {
					tvo.setFileType(1);
					File thumNail = new File(folders, uuid.toString()+"_th_"+fileName);
					Thumbnails.of(storeFile).size(75, 75).toFile(thumNail);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			flist.add(tvo);
		}
		return flist;
	}
	private boolean isImageFile(File StoreFile) throws IOException {
		String mimeType = new Tika().detect(StoreFile);
		return mimeType.startsWith("image")? true : false;
	}
	
	public List<TmtFileSaveVO> markerFiles(MultipartFile[] files){
		List<TmtFileSaveVO> flist = new ArrayList<>();
		String myFile = "markerFiles";
		
		File folders = new File(UP_DIR, myFile);
		if(!folders.exists()) {
			folders.mkdirs();
		}
		
		for(MultipartFile file : files) {
			TmtFileSaveVO tvo = new TmtFileSaveVO();
			tvo.setSaveDir(myFile);
			String originalFilename = file.getOriginalFilename();
			String fileName = originalFilename.substring(originalFilename.lastIndexOf(File.separator)+1);
			tvo.setFileName(fileName);
			
			UUID uuid = UUID.randomUUID();
			tvo.setUuid(uuid.toString());
			String fullFileName = uuid.toString()+"_"+fileName;
			File storeFile = new File(folders, fullFileName);
			try {
				file.transferTo(storeFile);
				if(isImageFile(storeFile)) {
					tvo.setFileType(1);
					File thumNail = new File(folders, uuid.toString()+"_th_"+fileName);
					Thumbnails.of(storeFile).size(100, 100).toFile(thumNail);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			flist.add(tvo);
		}
		return flist;
	}
}
