/*
 * Copyright 2013 Torben Werner
 *
 * Torben Werner licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.github.torbinsky.billing.recurly;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import com.github.torbinsky.billing.recurly.exception.RecurlyAPIException;
import com.github.torbinsky.billing.recurly.exception.RecurlyException;
import com.github.torbinsky.billing.recurly.exception.RecurlySerializationException;
import com.github.torbinsky.billing.recurly.model.RecurlyObject;
import com.github.torbinsky.billing.recurly.serialize.XmlPayloadMap;

/**
 * Basic/common client features such as managing the AsyncHttpClient etc...
 *
 * @author twerner
 *
 */
public abstract class RecurlyClientBase {

	private static final String RECURLY_PAGINATION_HEADER = "Link";

	private static final Logger log = LoggerFactory.getLogger(RecurlyClientBase.class);

	public static final String RECURLY_DEBUG_KEY = "recurly.debug";
	public static final String RECURLY_PAGE_SIZE_KEY = "recurly.page.size";

	protected static final Integer DEFAULT_PAGE_SIZE = new Integer(200);
	protected static final String PER_PAGE = "per_page=";

	public static final String FETCH_RESOURCE = "/recurly_js/result";

	/**
	 * Checks a system property to see if debugging output is required. Used
	 * internally by the client to decide whether to generate debug output
	 */
	protected static boolean debug() {
		return Boolean.getBoolean(RECURLY_DEBUG_KEY);
	}

	/**
	 * Returns the page Size to use when querying. The page size is set as
	 * System.property: recurly.page.size
	 */
	public static Integer getPageSize() {
		Integer pageSize;
		try {
			pageSize = new Integer(System.getProperty(RECURLY_PAGE_SIZE_KEY));
		} catch (NumberFormatException nfex) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
		return pageSize;
	}

	public static String getPageSizeGetParam() {
		return PER_PAGE + getPageSize().toString();
	}

	protected final XmlMapper xmlMapper = new XmlMapper();

	private String apiKey;
	private ThreadLocal<String> threadApiKey = new ThreadLocal<>();
	private final String baseUrl;

	public RecurlyClientBase(final String apiKey) {
		this(apiKey, "api.recurly.com", 443, "v2");
	}

	public RecurlyClientBase(final String apiKey, final String host, final int port, final String version) {
		if (apiKey != null) {
			this.apiKey = DatatypeConverter.printBase64Binary(apiKey.getBytes());
		}
		this.baseUrl = String.format("https://%s:%d/%s", host, port, version);

		final AnnotationIntrospector primary = new JacksonAnnotationIntrospector();
		final AnnotationIntrospector secondary = new JaxbAnnotationIntrospector(TypeFactory.defaultInstance());
		final AnnotationIntrospector pair = new AnnotationIntrospectorPair(primary, secondary);
		xmlMapper.setAnnotationIntrospector(pair);
		xmlMapper.registerModule(new JodaModule());
		xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}

	protected void setThreadLocalApiKey(String apiKey) {
		this.threadApiKey.set(DatatypeConverter.printBase64Binary(apiKey.getBytes()));
	}

	protected void unsetThreadLocalApiKey() {
		this.threadApiKey.remove();
	}

	private String getApiKey() {
		String threadKey = threadApiKey.get();
		if (threadKey != null) {
			return threadKey;
		}

		// Fall back to the non-thread local api key
		return apiKey;
	}

	/**
	 * Open the underlying http client
	 */
	public synchronized void open() {
	}

	/**
	 * Close the underlying http client
	 * @throws IOException
	 */
	public synchronized void close() throws IOException {
	}

	// /////////////////////////////////////////////////////////////////////////

	protected <T> List<T> fetches(final String recurlyToken, final Class<T> clazz, final boolean singleResult) {
		return doGETs(FETCH_RESOURCE + "/" + recurlyToken, clazz, singleResult);
	}

	protected <T> List<T> doGETs(final String resource, final Class<T> clazz, final boolean singleResult) {
		return doGETs(resource, null, clazz, singleResult);
	}

	protected <T> List<T> doGETsByUrl(final String url, final Class<T> clazz, final boolean singleResult) {
		if(debug()){
			log.info("Msg to Recurly API [GET] :: URL : {}", url);
		}
		return callRecurlySafe(new RecurlySimpleRequestBuilder("GET", url.toString()), clazz, true, singleResult);
	}

	protected <T> List<T> doGETs(final String resource, String paramString, final Class<T> clazz,
			final boolean singleResult) {
		String url = buildRecurlyUrl(resource, paramString);
		return doGETsByUrl(url, clazz, singleResult);
	}

	protected List<String> doGET(final String resource, String paramString, final boolean singleResult) {
		String url = buildRecurlyUrl(resource, paramString);
		return callRecurlySafe(new RecurlySimpleRequestBuilder("GET", url), singleResult);
	}

	protected String buildRecurlyUrl(String resource, String paramString) {
		StringBuffer url = new StringBuffer(baseUrl);
		url.append(resource);
		if (resource != null && !resource.contains("?")) {
			url.append("?");
		} else {
			url.append("&");
		}
		url.append(getPageSizeGetParam());

		if (paramString != null) {
			url.append(paramString);
		}

		return url.toString();
	}

	protected <T> List<T> doPOSTs(final String resource, final RecurlyObject payload,
			final Class<T> clazz, final boolean singleResult) {
		final String xmlPayload;
		try {
			xmlPayload = xmlMapper.writeValueAsString(payload);
			if (debug()) {
				log.info("Msg to Recurly API [POST]:: URL : {}", baseUrl + resource);
				log.info("Payload for [POST]:: {}", xmlPayload);
			}
		} catch (IOException e) {
			log.warn("Unable to serialize {} object as XML: {}", clazz.getName(), payload.toString(), e);
			throw new RecurlySerializationException("Unable to serialize {} object as XML: {}", e);
		}

		return callRecurlySafe(new RecurlySimpleRequestBuilder("POST", baseUrl + resource).withPayload(xmlPayload),
				clazz, true, singleResult);
	}

	protected <T> List<T> doPUTs(final String resource, final RecurlyObject payload,
			final Class<T> clazz, final boolean singleResult) {
		final String xmlPayload;
		try {
			xmlPayload = xmlMapper.writeValueAsString(payload);
			if (debug()) {
				log.info("Msg to Recurly API [PUT]:: URL : {}", baseUrl + resource);
				log.info("Payload for [PUT]:: {}", xmlPayload);
			}
		} catch (IOException e) {
			log.warn("Unable to serialize {} object as XML: {}", clazz.getName(), payload.toString(), e);
			throw new RecurlySerializationException("Unable to serialize {} object as XML: {}", e);
		}

		return callRecurlySafe(new RecurlySimpleRequestBuilder("PUT", baseUrl + resource).withPayload(xmlPayload),
				clazz, true, singleResult);
	}

	protected <T> List<T> doPOSTs(final String resource, final XmlPayloadMap<?, ?> payload,
			final Class<T> clazz, final boolean singleResult) {
		final String xmlPayload;
		try {
			xmlPayload = convertPayloadMapToXmlString(payload);
			if (debug()) {
				log.info("Msg to Recurly API [POST]:: URL : {}", baseUrl + resource);
				log.info("Payload for [POST]:: {}", xmlPayload);
			}
		} catch (IOException e) {
			log.warn("Unable to serialize {} object as XML: {}", clazz.getName(), payload.toString(), e);
			throw new RecurlySerializationException("Unable to serialize {} object as XML: {}", e);
		}

		return callRecurlySafe(new RecurlySimpleRequestBuilder("POST", baseUrl + resource).withPayload(xmlPayload),
				clazz, true, singleResult);
	}

	protected <T> List<T> doPUTs(final String resource, final XmlPayloadMap<?, ?> payload,
			final Class<T> clazz, final boolean singleResult) {
		final String xmlPayload;
		try {
			xmlPayload = convertPayloadMapToXmlString(payload);
			if (debug()) {
				log.info("Msg to Recurly API [PUT]:: URL : {}", baseUrl + resource);
				log.info("Payload for [PUT]:: {}", xmlPayload);
			}
		} catch (IOException e) {
			log.warn("Unable to serialize {} object as XML: {}", clazz.getName(), payload.toString(), e);
			throw new RecurlySerializationException("Unable to serialize {} object as XML: {}", e);
		}

		return callRecurlySafe(new RecurlySimpleRequestBuilder("PUT", baseUrl + resource).withPayload(xmlPayload),
				clazz, true, singleResult);
	}

	protected <T> T fetch(final String recurlyToken, final Class<T> clazz) {
		return doGET(FETCH_RESOURCE + "/" + recurlyToken, clazz);
	}

	protected <T> T doGET(final String resource, final Class<T> clazz) {
		return doGET(resource, null, clazz);
	}

	protected <T> T doGETByUrl(final String url, final Class<T> clazz){
		return returnSingleResult(doGETsByUrl(url, clazz, true));
	}

	protected <T> T doGET(final String resource, String paramString, final Class<T> clazz) {
		return returnSingleResult(doGETs(resource, paramString, clazz, true));
	}

	protected <T> T doPOST(final String resource, final RecurlyObject payload, final Class<T> clazz) {
		return returnSingleResult(doPOSTs(resource, payload, clazz, true));
	}

	protected <T> T doPUT(final String resource, final RecurlyObject payload, final Class<T> clazz) {
		return returnSingleResult(doPUTs(resource, payload, clazz, true));
	}

	protected <T> T doPOST(final String resource, final XmlPayloadMap<?, ?> payload, final Class<T> clazz) {
		return returnSingleResult(doPOSTs(resource, payload, clazz, true));
	}

	protected <T> T doPUT(final String resource, final XmlPayloadMap<?, ?> payload, final Class<T> clazz) {
		return returnSingleResult(doPUTs(resource, payload, clazz, true));
	}

	protected String convertPayloadMapToXmlString(final XmlPayloadMap<?, ?> xmlPayloadMap) throws JsonProcessingException {
		String xmlPayload = xmlMapper.writeValueAsString(xmlPayloadMap);
		return xmlPayload.replaceAll("XmlPayloadMap", xmlPayloadMap.getRootElementName());
	}

	protected void doDELETE(final String resource) {
		callRecurlySafe(new RecurlySimpleRequestBuilder("DELETE", baseUrl + resource), null, false, true);
	}

	protected void doDELETE(final String resource, Map<String,String> queryParameters){
		StringBuilder sb = new StringBuilder(baseUrl + resource);
		if (sb.indexOf("?") < 0) sb.append("?");
		queryParameters.forEach((k, v) -> {
			sb.append("&").append(urlEncode(k)).append("=").append(urlEncode(v));
		});
		callRecurlySafe(new RecurlySimpleRequestBuilder("DELETE", sb.toString()), null, false, true);
	}

	protected <T> T returnSingleResult(List<T> results) {
		if (!results.isEmpty()) {
			// Warn if we received more than one result
			if (results.size() > 1) {
				log.warn("Received multiple results from Recurly when only one was expected.");
			}
			return results.get(0);
		}

		return null;
	}

	protected <T> List<T> callRecurlySafe(final RecurlySimpleRequestBuilder builder, @Nullable final Class<T> clazz,
			final boolean parseResult, final boolean singleResult) {
		List<String> results = callRecurlySafe(builder, singleResult);
		if(parseResult){
			try {
				return deserialize(results, clazz);
			} catch (IOException e) {
				log.warn("Error while calling Recurly", e);
				throw new RecurlySerializationException("Error while calling Recurly", e);
			}
		}

		return null;
	}

	protected List<String> callRecurlySafe(final RecurlySimpleRequestBuilder builder,
			final boolean singleResult) {
		final String requestKey = getApiKey();
		final RecurlyAPICallResults<String> results = doSinglePageRecurlySafeCall(
				builder, new RecurlyAPICallResults<String>(), requestKey);
		if (singleResult) {
			if (results.hasNextPage()) {
				log.warn("Received multiple results from Recurly when only one was expected. "
						+ "nextPageUrl[{}] size[{}] originalUrl[{}]",
						results.getNextPageUrl(), results.getResults().size(),
						builder.url);
			}
		} else {
			while (results.hasNextPage()) {
				doSinglePageRecurlySafeCall(new RecurlySimpleRequestBuilder("GET", results.getNextPageUrl()),
						results, requestKey);
			}
		}

		return results.getResults();
	}

	protected RecurlyAPICallResults<String> doSinglePageRecurlySafeCall(final RecurlySimpleRequestBuilder builder,
		final RecurlyAPICallResults<String> pageResults, final String requestKey) {
		builder.headers.put("Authorization", "Basic " + requestKey);
		builder.headers.put("Accept", "application/xml");
		builder.headers.put("Content-Type", "application/xml; charset=utf-8");
		builder.headers.put("X-Api-Version", "2.21");
		try {
			final HttpURLConnection conn = builder.exec();
			final int statusCode = conn.getResponseCode();
			String payload;
			try (InputStream in = statusCode > 399 ? conn.getErrorStream() : conn.getInputStream()) {
				payload = in == null ? "" : convertStreamToString(in);
			} catch (FileNotFoundException e) {
				payload = "";
			}
			if (statusCode >= 300) {
				log.debug("Recurly error whilst calling: status[{}] body{}", statusCode, conn.getURL());
				log.debug("Recurly error: {}", payload);
				throw new RecurlyAPIException("Recurly error status:[" + statusCode + "] error body: "
						+ payload, statusCode);
			}
			if (debug()) {
				log.info("Msg from Recurly API :: {}", payload);
			}
			pageResults.getResults().add(payload);
			pageResults.setNextPageUrl(getPageUrlFromResponseHeader(conn));
			return pageResults;
		} catch (IOException e) {
			// Unwrap any of the API exceptions
			RecurlyAPIException apiE = unwrapRecurlyAPIException(e);
			if(apiE != null){
				throw apiE;
			}
			throw new RecurlyException("Execution error", e);
		}
		// TODO: Investigate why sometimes when we encounter exceptions we don't bubble it up but rather Ning somehow smothers it and returns null
		// ---> Begin hacky workaround <---
		// ---> End hacky workaround <---
	}

	public static RecurlyAPIException unwrapRecurlyAPIException(Throwable t){
		do {
			if (t instanceof RecurlyAPIException) {
				return (RecurlyAPIException) t;
			}
			t = t.getCause();
		}while(t != null);

		return null;
	}

	private String getPageUrlFromResponseHeader(HttpURLConnection response) {
		// TODO: There is probably a less hacky way to parse the pagination
		// header...

		String header = response.getHeaderField(RECURLY_PAGINATION_HEADER);
		if (header != null) {
			/*
			 * EXAMPLE:
			 *
			 * Status: 200 OK X-Records: 204 Link:
			 * <https://api.recurly.com/v2/transactions>; rel="start",
			 * <https://api.recurly.com/v2/transactions?cursor=-1241412>;
			 * rel="prev"
			 * <https://api.recurly.com/v2/transactions?cursor=124142>;
			 * rel="next" ETag: "c7431fcfc386fd59ee6c3c2e9ac2a30c"
			 */
			for (String paginationInfo : header.split(",")) {
				String[] p = paginationInfo.split(";");
				if (p.length >= 2) {
					String pageType = p[1].trim();
					if (pageType.equals("rel=\"next\"")) {
						try {
							return new URL(p[0].trim().replaceAll("<|>", "")).toString();
						} catch (IOException e) {
							log.warn("Unable to understand pagination url[" + p[0] + "]", e);
						}
					}
				}
			}
		}

		return null;
	}

	protected <T> List<T> deserialize(List<String> data, @Nullable final Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
		List<T> results = new ArrayList<>();
		for (String dataItem : data) {
			// EM - the jackson xml parser messes up and reads quantity/unit_amount as null with "type" is included in these fields so parse it out (it's ugly/hacky but nothing else seems to work)
			dataItem = dataItem.replaceAll("<(subscription_add_on)>([\\s\\S]+?)<quantity(.*?)>([\\s\\S]+?)</(\\1)>", "<$1>$2<quantity>$4</$1>");
			dataItem = dataItem.replaceAll("<(subscription_add_on)>([\\s\\S]+?)<unit_amount_in_cents(.*?)>([\\s\\S]+?)</(\\1)>", "<$1>$2<unit_amount_in_cents>$4</$1>");
			T obj = xmlMapper.readValue(dataItem, clazz);
			results.add(obj);
		}

		return results;
	}

	protected String convertStreamToString(java.io.InputStream is) {
		try (java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A")) {
			return s.next();
		} catch (java.util.NoSuchElementException e) {
			return "";
		}
	}

	protected void closeStream(final InputStream in) {
		if (in != null) {
			try {
				in.close();
			} catch (IOException e) {
				log.warn("Failed to close http-client - provided InputStream: {}", e.getLocalizedMessage(), e);
			}
		}
	}

	protected class RecurlyAPICallResults<T> {
		private String nextPageUrl = null;
		private List<T> results = new ArrayList<>();

		private RecurlyAPICallResults() {
			super();
		}

		public String getNextPageUrl() {
			return nextPageUrl;
		}

		public void setNextPageUrl(String nextPageUrl) {
			this.nextPageUrl = nextPageUrl;
		}

		public List<T> getResults() {
			return results;
		}

		public boolean hasNextPage(){
			return nextPageUrl != null;
		}
	}

	static String urlEncode(String s) {
		try {
			return URLEncoder.encode(s, UTF_8.name());
		} catch (UnsupportedEncodingException e) {
			throw new RecurlyException(e);
		}
	}

	public static class RecurlySimpleRequestBuilder {

		public final String method;
		public final String url;
		private String payload;
		public final Map<String, String> headers = new HashMap<>();

		public RecurlySimpleRequestBuilder(String method, String url) {
			this.method = method;
			this.url = url;
		}

		public RecurlySimpleRequestBuilder withPayload(String payload) {
			this.payload = payload;
			return this;
		}

		public HttpURLConnection exec() throws IOException {
			final HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestMethod(method);
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(15000);
			headers.forEach(conn::setRequestProperty);
			if (payload != null) {
				conn.setDoOutput(true);
				try (OutputStream out = conn.getOutputStream()) {
					out.write(payload.getBytes(UTF_8));
				}
			}
			return conn;
		}

	}

}
