package mm.com.merchant.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mm.com.merchant.constant.TransactConstant;
import mm.com.merchant.dto.FileMasterDTO;
import mm.com.merchant.service.ExtractionService;

@Controller
public class ExtractionController {
	@Autowired
	ExtractionService extractionService;
	@PostMapping("/fileExtraction")
	public String fileExtraction(@ModelAttribute("FileMasterDTO") FileMasterDTO fileMasterDTO,
			HttpSession session, ModelMap model,HttpServletRequest request) throws Exception {
		String fileDestPath="C:\\File\\Extract\\";
		session.setAttribute("userId", session.getAttribute("userId"));
		String availFiles = getFileList(fileDestPath);
		model.addAttribute("availFiles", availFiles);
		model.addAttribute("successStatus", "");
		model.addAttribute("filePath",fileDestPath);
		model.addAttribute("errorStatus", "");
		model.addAttribute("requestType", "H");
		model.addAttribute("fileMasterDTO", fileMasterDTO);
		return "extraction";
	}

	private String getFileList(String filePath) {

		List<String> fileNames = new ArrayList<String>();
		File[] files = new File(filePath).listFiles();
		for (File file : files) {
			if (file.isFile()) {
				fileNames.add(file.getName());
		}
		}
		Iterator<String> itr = fileNames.iterator();
		StringBuilder fileList = new StringBuilder();
		while (itr.hasNext()) {
			fileList.append(itr.next()).append((itr.hasNext() ? "," : ""));
		}
		String fileName = fileList.toString();
		return fileName;

	}
	
	@PostMapping("/fileProcess")
	public String fileProcess(@ModelAttribute("FileMasterDTO") FileMasterDTO fileMasterDTO,@RequestParam String selectFileList,
			HttpSession session, ModelMap model,HttpServletRequest request) throws Exception {

		session.setAttribute("userId", session.getAttribute("userId"));
		model.addAttribute("fileProcess", "Y");
		List<FileMasterDTO> fileMasterDTOList=new ArrayList<FileMasterDTO>();

		String fileList[] = (selectFileList != null) ? selectFileList.trim().split("#") : "".split("#");
		String files = "";
		for (int i = 0; i < fileList.length; i++) {
			files = files + "," + fileList[i];
		}
		String fileExtract = files.replaceFirst(",", "");
		String[] fileArray = fileExtract.split(",");
		for(String fileArr:fileArray) {
		String filename=fileArr;
		String nameWithoutExtension = filename.contains(".") ? filename.substring(0, filename.indexOf(".")) : filename;
		String path=fileMasterDTO.getFile_dest_path()+fileArr;
		fileMasterDTO.setFilename(nameWithoutExtension);
		fileMasterDTO.setFile_dest_path(path);
		String status=extractionService.readFile(path);
		fileMasterDTO.setStatus(status);
		if(status.equalsIgnoreCase("C"))
		{
		fileMasterDTOList.add(fileMasterDTO);
		}
		}
		model.addAttribute("process", fileMasterDTOList);
		model.addAttribute("successStatus", "");
		model.addAttribute("errorStatus","");
		return "FileProcessStatus";
	}
	

	@PostMapping("/reportDwld")
	public String reportDwld(@ModelAttribute("FileMasterDTO") FileMasterDTO fileMasterDTO,
			HttpSession session, ModelMap model,HttpServletRequest request) throws Exception {

		session.setAttribute("userId", session.getAttribute("userId"));
		model.addAttribute("successStatus", "");
		model.addAttribute("errorStatus","");
		return "ReportDwld";
	}
	
	@PostMapping("/generateReport")
	public String generateReport(@ModelAttribute("FileMasterDTO") FileMasterDTO fileMasterDTO,
	HttpSession session, ModelMap model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		String methodName="generateReport";
		String processName=fileMasterDTO.getProcessName();
		String path="";
		String fileName="";
		String csvFile="";
		String onDate=fileMasterDTO.getOnDate();
	    System.out.println("Received onDate from menu: " + fileMasterDTO.getOnDate());	
		int userId = (int)session.getAttribute("userId");
		model.addAttribute("userId", userId);
		model.addAttribute("requestType", "A");
		model.addAttribute("processName", fileMasterDTO.getProcessName());
		model.addAttribute("onDate", fileMasterDTO.getOnDate());
		if(processName.equalsIgnoreCase("D")) {
			path="C:\\File\\Reports\\"+"detailReport"+"\\"+onDate+"\\";
			fileName="detailReport"+onDate+".csv";
			System.out.println(">>>>>>>>>>>fileName>>>>>>>>"+fileName);
			fileMasterDTO.setFilename(fileName);
			csvFile=path+fileName;
			System.out.println(">>>>>>>>>>>csvFile>>>>>>>>"+csvFile);

			if(isFolderExists(path)) {
				extractionService.detailReportGen(csvFile);
			}
		}
		else {
			path="C:\\File\\Reports\\"+"SummaryReport"+"\\"+onDate+"\\";
			fileName="summaryReport"+onDate+".txt";
			System.out.println(">>>>>>>>>>>fileName>>>>>>>>"+fileName);
			fileMasterDTO.setFilename(fileName);
			csvFile=path+fileName;
			System.out.println(">>>>>>>>>>>csvFile>>>>>>>>"+csvFile);

			if(isFolderExists(path)) {
				extractionService.summaryReportGen(csvFile);
			}
		}
		model.addAttribute("successStatus", "");
		model.addAttribute("errorStatus","");
		return "ReportDwld";
	}
	public Boolean isFolderExists(String path) throws IOException {
		Boolean isExists=true;
	 Path directoryPath = Paths.get(path);
     if (!Files.exists(directoryPath)) {
         Files.createDirectories(directoryPath);
         System.out.println("Directory created: " + directoryPath); 
     } else {
         System.out.println("Directory already exists: " + directoryPath);
     }
     return isExists;
	}
	
	@PostMapping("/downloadFile")
	public void downloadFile(@RequestParam String onDate,@RequestParam String processName,@ModelAttribute("FileMasterDTO") FileMasterDTO fileMasterDTO,
			HttpSession session, ModelMap model, HttpServletRequest request,HttpServletResponse response) {
		
		session.setAttribute("userId", session.getAttribute("userId"));
		String filePath="";
		//System.out.println("_______value or report Id_________"+report_id);
		String destDir="C:\\FileDownload\\";
		 String reportName = null;
		 String path="C:\\File\\Reports\\";
		 if(processName.equals("D")) {
			 reportName="detailReport"+onDate+".csv";
			 filePath=path+"DetailReport"+"/"+onDate+"/"+reportName;
		 }else {
			 reportName="summaryReport"+onDate+".txt";
			 filePath=path+"SummaryReport"+"/"+onDate+"/"+reportName;
		 }
		 //YYYY-MM-DD
		try {
			model.addAttribute("processName", processName);
			model.addAttribute("onDate", onDate);
			if(request != null && request.getServletContext() != null && null != filePath) {
				ServletContext context = request.getServletContext();
				File downloadFile = new File(filePath);
				FileInputStream inputStream = new FileInputStream(downloadFile);
				String mimeType = context.getMimeType(filePath);
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}
				if(!mimeType.contains(TransactConstant.CRLF_CHAR)) {
				response.setContentType(mimeType);
				response.setHeader("Content-Length", String.valueOf(downloadFile.length()));
				String headerKey = "Content-Disposition";
				//String fileName=FileNameUtil.sanitizeFileName(downloadFile.getName());
				String headerValue = String.format("attachment; filename=\"%s\"", reportName);
				response.setHeader(headerKey, headerValue);
				if(response.getOutputStream() != null) {
					OutputStream outStream = response.getOutputStream();
					byte[] buffer = new byte[100];
					int bytesRead = -1;
					while ((bytesRead = inputStream.read(buffer)) != -1) {
						outStream.write(buffer, 0, bytesRead);
					}
				
					inputStream.close();
		
					outStream.flush();
					outStream.close();
				}
				}
				 try {
				 FileUtils.copyFileToDirectory(new File(filePath),new File(destDir));  
				 } catch (MalformedURLException e) {
				 e.printStackTrace();
				 } catch (IOException e) {
				 e.printStackTrace();
				 }
				 
			}
			return;
		} catch (Exception e) {
			System.out.println("exception>>>>>>"+e);
		}
		}
}
