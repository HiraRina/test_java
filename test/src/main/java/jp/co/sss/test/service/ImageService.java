package jp.co.sss.test.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {
	
	@Value("${file.upload-dir}")
	private	String uploadDir;
	
	@Value("${server.servlet.context-path:/}")
	private String contextPath;
		
	public String createImageFile(MultipartFile imageFile) {
		try {
			//画像の保存
			String fileName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
			Path uploadPath = Paths.get(uploadDir);
			if(!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(imageFile.getInputStream(),filePath);
			return contextPath + "/img/" + fileName;
		} catch (IOException e){
			throw new RuntimeException("画像が保存に失敗しました。");
		}
	}
}
