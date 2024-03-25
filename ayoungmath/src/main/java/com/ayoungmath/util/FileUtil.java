package com.ayoungmath.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	private String resourcesLocation;
	
	public FileUtil(String path) {
		resourcesLocation = path;
		mkdirUploadFolder();
	}

	/**
	 * BASE 64 파일 업로드
	 * @param uuid base64 subDirectory
	 * @throws Exception
	 */
	public void base64fileUpload(String uuid, String base64, String ext, String subDirectory) throws Exception{
		uploadBase64(uuid, base64, ext, subDirectory);
	}
	
	/**
	 * 파일 업로드
	 * @param uuid
	 * @param uploadFile
	 * @param subDirectory
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,Object> fileUpload(String fileName, MultipartFile uploadFile, String subDirectory) throws Exception{
		return uploadFile(fileName, uploadFile, subDirectory);
	}
	
	/**
	 * 파일 삭제
	 * @param fileName
	 * @param subDirectory
	 * @throws Exception
	 */
	public void fileDelete(String fileName, String subDirectory) throws Exception{
		deleteFile(fileName, subDirectory);
	}
	
	/**
	 * 파일 다운로드
	 * @param originFileName
	 * @param fileName
	 * @param subDirectory
	 * @return
	 * @throws Exception
	 */
	public ResponseEntity<Resource> fileDownload(String originFileName, String fileName, String subDirectory) throws Exception{
		return downloadFile(originFileName, fileName, subDirectory);
	}
	
	/**
	 * 이미지 미리보기
	 * @param fileName
	 * @param subDirectory
	 * @return
	 * @throws Exception
	 */
	public ResponseEntity<Resource> fileImageView(String fileName) throws Exception{
		return imageViewFile(fileName);
	}
	
	public ResponseEntity<Resource> fileVideoView(String fileName) throws Exception{
		return videoViewFile(fileName);
	}

	/**
	 * 업로드 파일 제한 용량 체크
	 * @param files
	 * @param limitSize
	 * @return
	 */
	public boolean isLimitUploadFile(List<MultipartFile> files, long limitSize) {
		return isMultiPartFileLimit(files, limitSize);
	}
	
	private void mkdirUploadFolder() {
		String filePath = resourcesLocation.trim();
    	
		File path = new File(filePath);
		
    	if(!path.exists()) {
    		path.mkdir();
    	}
	}
	
	private void uploadBase64(String uuid, String base64, String ext, String subDirectory) throws Exception
	{
		byte decode[] = Base64.decodeBase64(base64);
		FileOutputStream fos;

		//String fileExtension = StringUtils.getFilenameExtension(uploadFile.getOriginalFilename());
		String filePath = resourcesLocation.trim()+subDirectory;
		
		String newFileName = uuid+ "." + ext;
		
		String newFilePathAndName = Paths.get(resourcesLocation.trim()+subDirectory, newFileName).toString();

		try {
	    	File path = new File(filePath);
			if(!path.exists())
				path.mkdir();
			
			File target = new File(newFilePathAndName);
			target.createNewFile();
			fos = new FileOutputStream(target);
			fos.write(decode);
			fos.close();
		}catch (Exception e) {
			throw new Exception(e.getMessage());	
		}
	}
	
	private HashMap<String,Object> uploadFile(String fileName, MultipartFile uploadFile, String subDirectory) throws Exception
	{
		HashMap<String,Object> fileMap = new HashMap<String,Object>();
		String fileExtension = StringUtils.getFilenameExtension(uploadFile.getOriginalFilename());
		String filePath = resourcesLocation.trim() + subDirectory;
		
		fileMap.put("fileExt", fileExtension);
		fileMap.put("fileName", fileName);
		fileMap.put("originFileName", uploadFile.getOriginalFilename());
		
		if(fileExtension.equals("jfif")) {
			fileExtension = "jpg";
		}
		
		String newFileName = fileName+"."+fileExtension;
		
		String newFilePathAndName = Paths.get(resourcesLocation.trim()+subDirectory, newFileName).toString();
		fileMap.put("filePath", filePath);
		try {
	    	File path = new File(filePath);
			if(!path.exists()) {
				path.mkdirs();
			}
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(newFilePathAndName)));
			stream.write(uploadFile.getBytes());
			stream.close();
		}catch (Exception e) {
			throw new Exception(e.getMessage());	
		}
		
		return fileMap;
	}
	
	private void deleteFile(String fileName, String subDirectory) throws Exception{
		try {
			String filePath = resourcesLocation.trim() + subDirectory + "/" + fileName;
			File deleteFile = new File(filePath);
			if(deleteFile.exists()) {//파일이 존재 true
				deleteFile.delete();
			} else {
			}
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}
	
	private ResponseEntity<Resource> downloadFile(String originFileName, String fileName, String subDirectory) throws Exception{
		try {
			Path path = Paths.get(resourcesLocation.trim()+ subDirectory + "/" + fileName);
			
			String mimeType = Files.probeContentType(path);
			String originName = URLEncoder.encode(originFileName,"UTF-8").replaceAll("\\+", "%20");
			String contentType = "attachment;filename=" + originName + ";";
			Resource resource = new UrlResource(path.toUri());
			HttpHeaders headers = new HttpHeaders();
			
			headers.add(HttpHeaders.CONTENT_TYPE, contentType);
			headers.add(HttpHeaders.CONTENT_DISPOSITION, contentType);//다운 받아지는 파일 명 설정
			headers.setContentType(MediaType.parseMediaType(mimeType));
			
			headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(resource.getFile().length()));//파일 사이즈 설정
			headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM.toString());//바이너리 데이터로 받아오기 설정
			return new ResponseEntity<>(resource, headers, HttpStatus.OK);
		} catch(FileNotFoundException e) { 
			throw new FileNotFoundException();
		} catch(Exception e ) {
			e.printStackTrace();
			throw new Exception();
		} 
	}
	
	private ResponseEntity<Resource> imageViewFile(String fileName) {
		try {
			Path path = Paths.get(resourcesLocation.trim()+ "/" +fileName);

			String contentType = Files.probeContentType(path);
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_TYPE, contentType);

			Resource resource = new InputStreamResource(Files.newInputStream(path));
			
			return new ResponseEntity<>(resource, headers, HttpStatus.OK);
		} catch(FileNotFoundException e) { 
			return null;
		} catch(Exception e ) {
			return null;
		}
	}
	
	private ResponseEntity<Resource> videoViewFile(String fileName) {
	        
			String path = resourcesLocation + fileName;
			Resource resource = new FileSystemResource(path);
			
			HttpHeaders headers = new HttpHeaders();
			headers.set(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s", fileName));
			headers.setContentType(MediaType.parseMediaType("video/mp4"));
			

	        // ResponseEntity로 동영상 파일 반환
	        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}

	
	private boolean isMultiPartFileLimit(List<MultipartFile> files, long limitSize) {
		for(MultipartFile item : files) {			
			if(! item.isEmpty()) {
				if(item.getSize() > limitSize || item.getSize() == 0) {
					return true;
				}
			}	
		}
		return false;
	}

}
