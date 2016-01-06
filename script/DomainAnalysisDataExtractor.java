package focusedCrawler.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.util.HashMap;
import java.util.zip.InflaterInputStream;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.codec.digest.DigestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import focusedCrawler.target.TargetModelJson;
import focusedCrawler.util.Page;
import focusedCrawler.util.parser.PaginaURL;

public class DomainAnalysisDataExtractor {
	
	private static final String rootPath = "/Users/brunomacedo/Desktop/NYU-Poly/3rd-Semester/Memex/code/topic-dynamics/data-compressed/";
	private static final String outputFilePath = "/Users/brunomacedo/Desktop/NYU-Poly/3rd-Semester/Memex/code/topic-dynamics/pageData.txt";
	private static final String outlinksFilePath = "/Users/brunomacedo/Desktop/NYU-Poly/3rd-Semester/Memex/code/topic-dynamics/outlinks.txt";
	private static final String outputFileHeader = "crawl_domain crawl_date fetch_time url_domain original_url redirected_url classified_relevant content_hash\n";
	private static final String outlinksFileHeader = "from_url to_url\n";
	
	public static void main(String[] args) {
		try {
//			generateDataFiles();
			
			CommandLineParser parser = new DefaultParser();
	        Options options = new Options();
	        
	        options.addOption("dn", "domain-name", true, "Domain name: {ebola,movies}");
	        options.addOption("dt", "date", true, "Date to perform analysis.");
	        options.addOption("rp", "root-path", true, "Path where the data is.");
	        options.addOption("opfp", "output-file-path", true, "Path of output file.");
	        options.addOption("olfp", "outlinks-file-path", true, "Path of outlinks file.");
	        
	        CommandLine cmd = parser.parse(options, args);
	        
	        String rootPath = getMandatoryOption(options, cmd, "root-path");
	        String domainName = getMandatoryOption(options, cmd, "domain-name");
	        String date = cmd.getOptionValue("date");
	        String outputFilePath = getMandatoryOption(options, cmd, "output-file-path");
	        String outlinksFilePath = getMandatoryOption(options, cmd, "outlinks-file-path");
	           
			generateDataFiles(rootPath, domainName, date, outputFilePath, outlinksFilePath);
			
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}
	
	private static String getMandatoryOption(Options options, CommandLine cmd, String optionName) {
        String value = cmd.getOptionValue(optionName);
        if(value == null) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(ElasticSearchIndexer.class.getName(), options, true);
            System.exit(0);
        }
        return value;
    }
	
	public static void printOutlinksToStream(PaginaURL pageParser, PrintStream outlinksStream) throws IOException {
		URL pageUrl = pageParser.getURL(); // get originalURL or redirectedUrl ?
		URL[] outlinks = pageParser.links();
		for (URL outlink : outlinks) {
			outlinksStream.append(pageUrl.toString());
			outlinksStream.append(" ");
			outlinksStream.append(outlink.toString());
			outlinksStream.append("\n");
		}
	}
	
	public static String getPageFingerprint(String textParsed) {
		String pageFingerprint = DigestUtils.sha256Hex(textParsed);
		return pageFingerprint;
	}
		
	public static PaginaURL getPageParser(TargetModelJson urlJson) throws IOException {
		URL originURL = new URL(urlJson.getUrl());
//		URL redirectedURL = new URL(urlJson.getRedirectedUrl());
		Page page = new Page(originURL, urlJson.getResponseBody(), urlJson.getResponseHeaders());
		return new PaginaURL(page.getURL(), page.getContent());
	}
	
	public static void generateDomainDataFiles(String rootPath, String domainName, PrintStream pageDataStream, PrintStream outlinksStream) throws IOException {
		generateDomainDataFiles(rootPath, domainName, null, pageDataStream, outlinksStream);
	}
	
	public static void generateDomainDataFiles(String rootPath, String domainName, String date, PrintStream pageDataStream, PrintStream outlinksStream) throws IOException {
		
		String domainPath = rootPath + domainName;
		File domainFile = new File(domainPath);
		String[] dateDirNames;
		
		if(date == null)
			dateDirNames = domainFile.list();
		else {
			dateDirNames = new String[1];
			dateDirNames[0] = date;
		}
		
		StringBuilder currentPath;
		HashMap<String, String> relevantMap = new HashMap<>();
		relevantMap.put("0", "data_negative/");
		relevantMap.put("1", "data_target/");
		
		for(String dateDirName : dateDirNames) {
			
			if (dateDirName.contains(".log"))
				continue;
			
			currentPath = new StringBuilder(domainPath);
			currentPath.append("/");
			currentPath.append(dateDirName);
			currentPath.append("/");
			String dateDirPath = currentPath.toString();
			
			for(String relevant : relevantMap.keySet()) {
				
				currentPath = new StringBuilder(dateDirPath);
				currentPath.append(relevantMap.get(relevant));
				File targetDomains = new File(currentPath.toString());
				String[] targetDomainNames = targetDomains.list();
				String relevantPath = currentPath.toString();
				
//				System.out.println(currentPath);
				
				for(String targetDomainName : targetDomainNames) {
					
					currentPath = new StringBuilder(relevantPath);
					currentPath.append(targetDomainName);
					currentPath.append("/");
					
					File domainUrls = new File(currentPath.toString());
					String[] domainUrlNames = domainUrls.list();
					String targetDomainPath = currentPath.toString();
										
					for(String domainUrlName : domainUrlNames) {
						
						currentPath = new StringBuilder(targetDomainPath);
						currentPath.append(domainUrlName);
						
						System.out.println(currentPath);
						
						File urlFile = new File(currentPath.toString());
						InputStream urlGzipStream = new FileInputStream(urlFile);
						InputStream urlFileStream = new InflaterInputStream(urlGzipStream);
						String urlFileString = getStringFromInputStream(urlFileStream);
						
						ObjectMapper urlMapper = new ObjectMapper();
						TargetModelJson urlJson;
						Long fetchTime;
						String originalUrl;
						String redirectedUrl;
						String textParsed;
						String pageFingerprint;
						StringBuilder pageData = new StringBuilder();
						
						urlJson = urlMapper.readValue(urlFileString, TargetModelJson.class);
						fetchTime = urlJson.getFetchTime(); 
						originalUrl = urlJson.getUrl();
						redirectedUrl = urlJson.getRedirectedUrl();
						if (redirectedUrl == null)
							redirectedUrl = originalUrl;
						PaginaURL pageParser = getPageParser(urlJson);
						printOutlinksToStream(pageParser, outlinksStream);
						textParsed = pageParser.palavras_to_string();
						pageFingerprint = getPageFingerprint(textParsed);
						
						pageData.append(domainName);
						pageData.append(" ");
						pageData.append(dateDirName);
						pageData.append(" ");
						pageData.append(fetchTime);
						pageData.append(" ");
						pageData.append(targetDomainName);
						pageData.append(" ");
						pageData.append(originalUrl);
						pageData.append(" ");
						pageData.append(redirectedUrl);
						pageData.append(" ");
						pageData.append(relevant);
						pageData.append(" ");
						pageData.append(pageFingerprint);
						pageData.append("\n");
						pageDataStream.print(pageData.toString());
					}
				}
			}
		}
	}
	
	// this method is for testing only
	public static void generateDataFiles() throws IOException {
		
		File rootFile = new File(rootPath);
		String[] domainPaths = rootFile.list();
		
		PrintStream pageDataStream = new PrintStream(outputFilePath);
		PrintStream outlinksStream = new PrintStream(outlinksFilePath);
		pageDataStream.append(outputFileHeader);
		outlinksStream.append(outlinksFileHeader);
		
		for(String domainName : domainPaths)
			generateDomainDataFiles(rootPath, domainName, pageDataStream, outlinksStream);
		
		pageDataStream.close();
		outlinksStream.close();
	}
	
	public static void generateDataFiles(String rootPath, String domainName, String date, String outputFilePath, String outlinksFilePath) throws IOException {
		
		PrintStream pageDataStream = new PrintStream(outputFilePath);
		PrintStream outlinksStream = new PrintStream(outlinksFilePath);
		pageDataStream.append(outputFileHeader);
		outlinksStream.append(outlinksFileHeader);
		
		generateDomainDataFiles(rootPath, domainName, date, pageDataStream, outlinksStream);
		
		pageDataStream.close();
		outlinksStream.close();
	}
	
	private static String getStringFromInputStream(InputStream is) {
		
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
	
}
