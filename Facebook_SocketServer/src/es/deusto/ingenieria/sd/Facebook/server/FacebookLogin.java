package es.deusto.ingenieria.sd.Facebook.server;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import org.json.JSONArray;

public class FacebookLogin {
	
	private boolean doesUserExist = false;
	
	private List<String> user1 = new ArrayList<>(Arrays.asList("vegard.pettersson@gmail.com", "vegard"));
	private List<String> user2 = new ArrayList<>(Arrays.asList("federika@gmail.com", "federika"));
	private List<String> user3 = new ArrayList<>(Arrays.asList("gorka@gmail.com", "gorka"));
	private List<String> user4 = new ArrayList<>(Arrays.asList("brinkfredrika@gmail.com", "federika"));

	
	private List<List<String>> userList = new ArrayList<>(Arrays.asList(user1, user2, user3, user4));
	
	public boolean userExists(String email, String password) {
		for (List<String> user : userList) {
			String sha1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex(user.get(1));
			if(email.equals(user.get(0)) && password.equals(sha1)) {
				doesUserExist = true;
				break;
			}
			else {
				doesUserExist = false;
			}
		}
		return doesUserExist;	
	}

}