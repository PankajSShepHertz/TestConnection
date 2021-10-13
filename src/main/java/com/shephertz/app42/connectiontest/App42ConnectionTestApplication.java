package com.shephertz.app42.connectiontest;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class App42ConnectionTestApplication {

	public static void main(String[] args) {

		SpringApplication.run(App42ConnectionTestApplication.class, args);

		System.out.println("Step1::::::::");
		OutputStream out = null;
		try {
			System.setProperty("java.net.useSystemProxies", "true");
			String body = "{ \r\n"
					+ "\"to\":\"e3TM20ZYTi2PaVYaSudE9m:APA91bF7M-lMbLzfcAwp08I9F-U0fkDD3D7M8neOvsA-qwlUNvcG531s0KXL0zB6wyuONciapAFhOQ8CjdzoFZquVagOWaK8gRN05kUsBOw_QO6BpQaCBYkCyUTyOkxUoIVhgnXPHLZ5\", \r\n"
					+ " \"notification\" : {\r\n" + " \"body\" : \"Pankaj App42ConnectionTestApplication test\",\r\n"
					+ " \"OrganizationId\":\"2\",\r\n" + " \"content_available\" : true,\r\n"
					+ " \"priority\" : \"high\",\r\n" + " \"subtitle\":\"Elementary School\",\r\n"
					+ " \"Title\":\"hello\"\r\n" + " },\r\n" + " \"data\" : {\r\n" + " \"priority\" : \"high\",\r\n"
					+ " \"sound\":\"app_sound.wav\",\r\n" + " \"content_available\" : true,\r\n"
					+ " \"bodyText\" : \"New Announcement assigned\",\r\n"
					+ " \"organization\" :\"Elementary school\"\r\n" + "}\r\n" + "}";
			byte[] bytes = body.getBytes("UTF-8");
			HttpURLConnection conn = getConnection("https://fcm.googleapis.com/fcm/send");
			System.out.println("Step2::::URL::::" + conn.getURL());
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setFixedLengthStreamingMode(bytes.length);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", "key="
					+ "AAAAIepmzcE:APA91bF_oAVAgGzlvrcJHUyW2r_JnXOuDhG8Xh_ozL1YXyKT9tBuDYFrHcac9F0xRB1s1bfgPosYkmBv1R0hGVCD13tEMdWquEmeDIOw17KAM97BiAPl8RTw9PgD1uX8HLn19XgN_fAm");
			out = conn.getOutputStream();
			System.out.println("Step:::3:::::");
			out.write(bytes);
		} catch (Exception e) {
			System.out.println("Exception::::::::");
			e.printStackTrace();
		} finally {
			System.out.println("finally::::::::");
			close(out);
		}

	}

	private static void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				// ignore error
				e.printStackTrace();
			}
		}
	}

	static HttpURLConnection getConnection(String url) throws IOException {
		return (HttpURLConnection) new URL(url).openConnection();
	}

}
