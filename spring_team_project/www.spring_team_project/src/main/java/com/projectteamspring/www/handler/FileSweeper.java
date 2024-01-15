package com.projectteamspring.www.handler;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.projectteamspring.www.domain.MemberFileVO;
import com.projectteamspring.www.domain.TmtFileSaveVO;
import com.projectteamspring.www.repository.MemberFileDAO;
import com.projectteamspring.www.repository.MemberRealFileDAO;
import com.projectteamspring.www.repository.TmtFileSaveDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileSweeper {

	private final String tmtMain_PATH = "D:\\_myweb\\_java\\fileupload\\tmtFile\\";
	private final String tmtSub_PATH = "D:\\_myweb\\_java\\fileupload\\tmtSubFile\\";
	private static final String UPLOAD_DIR = "D:\\_myweb\\_java\\fileupload\\profile\\";
	
	@Inject
	private TmtFileSaveDAO tfdao;
	
	@Inject
	private MemberFileDAO mfdao;
	
	@Inject
	private MemberRealFileDAO mrfdao;
	
	@Scheduled(cron = "0 00 00 * * * ")
	public void FileSweeper(){
		int isOk = tfdao.deleteAllFiles();
		LocalDate now = LocalDate.now();
		String today = now.toString();
		today = today.replace("-", File.separator);
		if(isOk>0){
			
			File mainDir = Paths.get(tmtMain_PATH+today).toFile();
			File subDir = Paths.get(tmtSub_PATH+today).toFile();
			
			File[] mainFiles = mainDir.listFiles();
			File[] subFiles = subDir.listFiles();
			
			for(File file : mainFiles) {
				file.delete();
			}
			for(File file : subFiles) {
				file.delete();
			}
		}
		File profile = Paths.get(UPLOAD_DIR+today).toFile();
		File[] profiles = profile.listFiles();
		
		List<MemberFileVO> list = mfdao.selectAll();
		for (int i = 0; i < list.size(); i++) {
			String uuid = list.get(i).getUuid();
			for(File file : profiles) {
				if(file.getName().contains(uuid)) {
					file.delete();
				}
			}
		}
		mfdao.allDeleteFile();
		List<MemberFileVO> realList = mrfdao.selectAll();
		List<String> cuurrFiles = new ArrayList<String>();
		for(MemberFileVO mfvo : realList) {
			String filePath = mfvo.getSaveDir()+"\\"+mfvo.getUuid();
			String fileName = mfvo.getFileName();
			cuurrFiles.add(UPLOAD_DIR+filePath+"_"+fileName);
			cuurrFiles.add(UPLOAD_DIR+filePath+"_th_"+fileName);
		}
		for(File file : profiles) {
			String storedFileName = file.toPath().toString();
			if(!cuurrFiles.contains(storedFileName)) {
				file.delete();
			}
		}
	}
			
}
