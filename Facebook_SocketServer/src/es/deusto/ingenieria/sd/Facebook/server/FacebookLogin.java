package es.deusto.ingenieria.sd.Facebook.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import org.json.JSONArray;

public class FacebookLogin {
	
	private boolean doesUserExist = false;
	
	private List<String> user1 = new ArrayList<>(Arrays.asList("vegard.pettersson@gmail.com", "vegard"));
	private List<String> user2 = new ArrayList<>(Arrays.asList("federika@gmail.com", "federika"));
	private List<String> user3 = new ArrayList<>(Arrays.asList("gorka@gmail.com", "gorka"));
	
	private List<List<String>> userList = new ArrayList<>(Arrays.asList(user1, user2, user3));
	
	public boolean userExists(String email, String password) {
		for (List<String> user : userList) {
			if(email.equals(user.get(0)) && password.equals(user.get(1))) {
				doesUserExist = true;
				break;
			}
			else {
				doesUserExist = false;
			}
		}
		return doesUserExist;	
	}

//	public String translate(String langFrom, String langTo, String text) throws Exception {
//		String url = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=" + langFrom + 
//				     "&tl=" + langTo + "&dt=t&q=" + URLEncoder.encode(text, "UTF-8");
//		URL obj = new URL(url);
//		
//		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//		con.setRequestProperty("User-Agent", "Mozilla/5.0");
//		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//		String inputLine;
//		StringBuffer response = new StringBuffer();
//
//		while ((inputLine = in.readLine()) != null) {
//			response.append(inputLine);
//		}
//		
//		in.close();
//
//		return parseResult(response.toString());
//	}

	//private String parseResult(String inputJson) throws Exception {
		/*
		 * inputJson for "sentence" translated from langFrom to langTo
		 * [[["result","sentence",,,1]],,"langFrom"] We have to get 'translated
		 * sentence' from this json.
		 */
//		JSONArray jsonArray = new JSONArray(inputJson);
//		JSONArray jsonArray2 = (JSONArray) jsonArray.get(0);
//		JSONArray jsonArray3 = (JSONArray) jsonArray2.get(0);
//
//		return jsonArray3.get(0).toString();
//	}
}