package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) throws IOException {
		URL u = new URL("http://www.baidu.com");
		URLConnection connection = u.openConnection();
		HttpURLConnection htCon = (HttpURLConnection) connection;
		int code = htCon.getResponseCode();
		if (code == HttpURLConnection.HTTP_OK) {
			System.out.println("返回状态 "+HttpURLConnection.HTTP_OK);
			BufferedReader in = new BufferedReader(new InputStreamReader(htCon.getInputStream(), "utf-8"));
			StringBuffer sb = new StringBuffer();
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				sb.append(inputLine + "\n");
			in.close();
			String regStr = RegString.MatchUrl;
			Pattern p = Pattern.compile(regStr);
			Matcher m = p.matcher(sb);
			while(m.find()) {
					System.out.println();
				System.out.print(m.group());
			}
			//System.out.println(m);
		} else {
			System.out.println("无法连接到 "+u.toString());
		}
	}

}
