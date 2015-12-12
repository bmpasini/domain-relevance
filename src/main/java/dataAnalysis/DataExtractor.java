package dataAnalysis;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DataExtractor {
	
	public static String getPageFingerprint(String textParsed) {
		String pageFingerprint = new String();
		return pageFingerprint;
	}
	
	public static String getTextFromFileUrl(File urlPage) {
		String text = new String();
		return text;
	}
	
	public static void generateDomainDataFile(String rootPath, String domainName, String fileName) throws IOException {
		
		String domainPath = rootPath + domainName;
		File domainFile = new File(domainPath);
		String[] dateDirNames = domainFile.list();
		StringBuilder currentPath;
		PrintStream pageDataStream = new PrintStream(fileName);
		
		HashMap<String, String> relevantMap = new HashMap<>();
		relevantMap.put("0", "data_negative/");
		relevantMap.put("1", "data_target/");
		
		for(String dateDirName : dateDirNames) {
			
			currentPath = new StringBuilder();
			currentPath.append(domainPath);
			currentPath.append(dateDirName);
			
			for(String relevant : relevantMap.keySet()) {
				
				currentPath.append(relevantMap.get(relevant));
				File targetDomains = new File(currentPath.toString());
				String[] targetDomainNames = targetDomains.list();
				
				for(String targetDomainName : targetDomainNames) {
					
					currentPath.append(targetDomainName);
					File domainUrls = new File(currentPath.toString());
					String[] domainUrlNames = domainUrls.list();
					
					for(String domainUrlName : domainUrlNames) {
						
						currentPath.append(domainUrlName);
						File urlFile = new File(currentPath.toString());
						ObjectMapper urlMapper = new ObjectMapper();
						TargetModelJson urlJson;
						String originalUrl;
						String redirectedUrl;
						String textParsed;
						String pageFingerprint;
						StringBuilder pageData = new StringBuilder();
						
						urlJson = urlMapper.readValue(urlFile, TargetModelJson.class);
						originalUrl = urlJson.getUrl();
						redirectedUrl = urlJson.getRedirectedUrl();
						textParsed = getTextFromFileUrl(urlFile);
						pageFingerprint = getPageFingerprint(textParsed);
						
						pageData.append(domainName);
						pageData.append(" ");
						pageData.append(dateDirName);
						pageData.append(" ");
						pageData.append(domainUrlName);
						pageData.append(" ");
						pageData.append(originalUrl);
						pageData.append(" ");
						pageData.append(redirectedUrl);
						pageData.append(" ");
						pageData.append(relevant);
						pageData.append(" ");
						pageData.append(pageFingerprint);
						pageData.append(" ");
						pageDataStream.print(pageData);
					}
				}
			}
		}
		pageDataStream.close();
	}
	
	public static void generateDataFile(String rootPath, String fileName) throws IOException {
		
		File rootFile = new File(rootPath);
		String[] domainPaths = rootFile.list();
		
		for(String domainName : domainPaths) {
			generateDomainDataFile(rootPath, domainName, fileName);
		}	
	}
	
	public static void main(String[] args) {
		
		String rootPath = args[1];
		String outputFilePath = "pageData.txt"; 
		String outputFileHeader = "crawl_domain crawl_date url_domain original_url redirected_url classified_relevant content_hash";
		PrintStream pageDataStream;
		
		try {
			pageDataStream = new PrintStream(outputFilePath);
			pageDataStream.append(outputFileHeader);
			generateDataFile(rootPath, outputFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
