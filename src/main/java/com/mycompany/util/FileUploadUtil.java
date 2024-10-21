package com.mycompany.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	
	public static String saveFile(String fileName, MultipartFile multipartFile) throws IOException {
		Path desktopDir = Paths.get(System.getProperty("user.home"), "Desktop");
        
        // Define the path to the "File-Upload" folder on the desktop
        Path uploadDir = desktopDir.resolve("FS-Uploads");
        
        String filecode = RandomStringUtils.randomAlphanumeric(8);
        
        try (InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = uploadDir.resolve(filecode + "-" + fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            
        }catch(IOException ioe){
            throw new IOException("Error saving uploaded file: "+ fileName,ioe);
        }
        
     

        
        return filecode;
    }

}
