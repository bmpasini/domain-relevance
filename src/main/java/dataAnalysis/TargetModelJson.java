package dataAnalysis;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TargetModelJson {
	
	@JsonProperty("url")
	private String url;
	
	@JsonProperty("redirected_url")
	private String redirectedUrl;
	
	@JsonProperty("response_body")
	private String responseBody;
	
	@JsonProperty("response_headers")
	private Map<String, List<String>> responseHeaders;
	
	public TargetModelJson() {
		// required for JSON serialization
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRedirectedUrl() {
		return redirectedUrl;
	}

	public void setRedirectedUrl(String redirectedUrl) {
		this.redirectedUrl = redirectedUrl;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	public Map<String, List<String>> getResponseHeaders() {
		return responseHeaders;
	}

	public void setResponseHeaders(Map<String, List<String>> responseHeaders) {
		this.responseHeaders = responseHeaders;
	}
	
}