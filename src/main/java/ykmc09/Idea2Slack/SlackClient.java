package ykmc09.Idea2Slack;

import org.apache.commons.lang3.StringEscapeUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class SlackClient {

	public static void sendText(String webHookUrl, String text) {

		try {
			String json = "payload={\"username\": \"Idea2Slack\", \"text\": \"```"
					+ StringEscapeUtils.escapeJson(text)
					+ "```\", \"icon_emoji\": \":ghost:\"}";
			System.out.println("PAYLOAD: " + json);

			URLConnection connection = new URL(webHookUrl).openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

			try (OutputStream output = connection.getOutputStream()) {
				output.write(json.getBytes(Charset.defaultCharset()));
			}

			try (InputStream response = connection.getInputStream()) {
				System.out.println("RESPONCE: " + getStringFromInputStream(response));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String getStringFromInputStream(InputStream is) {
		StringBuilder sb = new StringBuilder();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			br.lines().forEach(sb::append);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return sb.toString();
	}
}
