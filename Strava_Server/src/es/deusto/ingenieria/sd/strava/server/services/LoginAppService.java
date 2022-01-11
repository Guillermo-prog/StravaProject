package es.deusto.ingenieria.sd.strava.server.services;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import es.deusto.ingenieria.sd.strava.server.data.dao.UserDAO;
import es.deusto.ingenieria.sd.strava.server.data.domain.User;
import es.deusto.ingenieria.sd.strava.server.factory.LoginFactory;


public class LoginAppService {

	// Instance for the Singleton Pattern
	private static LoginAppService instance;
	
	private List<User> users = new ArrayList<>();

	private LoginAppService() {
		this.initializeData();
	}
	
	//Initialize data and set it into the list
	private void initializeData() {
		// TODO Auto-generated method stub
		//Create Users
		User user0 = new User();
		user0.setEmail("thomas.e2001@gmail.com");
		user0.setNickname("Thomas");
		user0.setPassword("$!9PhNz,");
		
		User user1 = new User();
		user1.setEmail("sample@gmail.com");
		user1.setNickname("buyer33");		
		user1.setPassword("hqc`}3Hb");
		
		User user2 = new User();
		user2.setEmail("guillermo@gmail.com");
		user2.setNickname("guille");		
		user2.setPassword("guillermo");
		
		this.users.add(user0);
		this.users.add(user1);
		this.users.add(user2);
		}

	public static LoginAppService getInstance() {
		if (instance == null) {
			instance = new LoginAppService();
		}

		return instance;
	}
	
	
	public User login(String email, String hashedPassword, String type) throws RemoteException {
		User existingUser = new User();
		if (type.equals("normal")) {
				User user = UserDAO.getInstance().find(email);
				System.out.println(user);
				System.out.println("Password from db: " + user.getPassword());
				System.out.println("Password from login: " + hashedPassword);
				if (user != null && user.checkPassword(hashedPassword)) {
					existingUser = user;
				} 
				else {
					existingUser = null;
				}
		} 

			
			/*
			 * for (User user : users) { String sha1 =
			 * org.apache.commons.codec.digest.DigestUtils.sha1Hex(user.getPassword()); if
			 * (user.getEmail().equals(email) && sha1.equals(hashedPassword)) { existingUser
			 * = user; } }
			 */
		if (type.equals("Google") || type.equals("Facebook")) {
			LoginFactory factory = new LoginFactory();
			try {
				boolean userExist = factory.createServiceGateways(type).login(email, hashedPassword);
				if (userExist) {
					existingUser = UserDAO.getInstance().find(email);
				}
				else {
					existingUser = null;
				}
			}
			catch (RemoteException e) {
				e.printStackTrace();
			}
		}	
		return existingUser;

	};


	// registration
	public User registration(String type, String email, String password, String nickname, String birthdate, int weigth, int heigth,
			int maxrate, int minRate) {
		
		User user = new User();
		if (type.equals("normal")) {
			user.setEmail(email);
			user.setNickname(nickname);		
			user.setPassword(password);
			// nuevos campos
			user.setNickname(nickname);
			user.setWeight(weigth);
			user.setHeight(heigth);
			user.setMaxRate(maxrate);
			user.setMinRate(minRate);
			user.setBirthdate(birthdate);
			
			UserDAO.getInstance().save(user);
			
			System.out.println("User has been created");
			this.users.add(user);
		}
		
		if (type.equals("Facebook") || type.equals("Google")) {
			LoginFactory factory = new LoginFactory();
			try {
				boolean existsInFbOrGoogle = factory.createServiceGateways(type).login(email, password);
				if (existsInFbOrGoogle) {
					user.setEmail(email);
					user.setNickname(nickname);		
					//user.setPassword(password);
					// nuevos campos
					user.setNickname(nickname);
					user.setWeight(weigth);
					user.setHeight(heigth);
					user.setMaxRate(maxrate);
					user.setMinRate(minRate);
					user.setBirthdate(birthdate);
					
					UserDAO.getInstance().save(user);
					
					System.out.println("User has been created");
					this.users.add(user);
				}
			}
			catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		
		return user;

	}
}