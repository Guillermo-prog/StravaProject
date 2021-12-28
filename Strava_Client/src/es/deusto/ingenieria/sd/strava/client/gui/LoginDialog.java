package es.deusto.ingenieria.sd.strava.client.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import es.deusto.ingenieria.sd.strava.client.controller.LoginController;
import es.deusto.ingenieria.sd.strava.client.controller.TrainingController;
import es.deusto.ingenieria.sd.strava.client.remote.ServiceLocator;

public class LoginDialog {

	private LoginController controller;
	private ServiceLocator serviceLocator;
	private String email = "vegard.pettersson@gmail.com";
	private String password = "vegard";
	private String nickname;
	private String dbirthdate;
	private int dweight;
	private int dheight;
	private int dmaxRate;
	private int dminRate;

	public LoginDialog(LoginController controller, ServiceLocator serviceLocator) {
		this.controller = controller;
		this.serviceLocator = serviceLocator;
	}

	public boolean LoginD() {
		JFrame f = new JFrame("STRAVA LOGIN");

		final JLabel lemail = new JLabel("Email");
		lemail.setBounds(120, 20, 150, 20);

		final JTextField emailBox = new JTextField();
		emailBox.setBounds(120, 50, 150, 20);

		final JLabel lpass = new JLabel("Password");
		lpass.setBounds(120, 70, 150, 20);

		final JTextField pass = new JTextField();
		pass.setBounds(120, 100, 150, 20);

		final JLabel lname = new JLabel("Name");
		lname.setBounds(120, 120, 150, 20);

		final JTextField name = new JTextField();
		name.setBounds(120, 140, 150, 20);
		name.setText("Thomas");

		final JLabel lweigth = new JLabel("Weigth");
		lweigth.setBounds(120, 160, 150, 20);

		final JTextField weigth = new JTextField();
		weigth.setBounds(120, 180, 50, 20);
		weigth.setText("80");

		final JLabel lheight = new JLabel("Height");
		lheight.setBounds(120, 200, 150, 20);

		final JTextField height = new JTextField();
		height.setBounds(120, 220, 50, 20);
		height.setText("175");

		final JLabel lmaxrate = new JLabel("Max Heart Rate");
		lmaxrate.setBounds(180, 160, 150, 20);

		final JTextField maxrate = new JTextField();
		maxrate.setBounds(180, 180, 50, 20);
		maxrate.setText("220");

		final JLabel lminrate = new JLabel("Min Heart Rate");
		lminrate.setBounds(180, 200, 150, 20);

		final JTextField minrate = new JTextField();
		minrate.setBounds(180, 220, 50, 20);
		minrate.setText("65");

		final JLabel lbirth = new JLabel("Birthdate");
		lbirth.setBounds(120, 240, 150, 20);

		final JTextField birthBox = new JTextField();
		birthBox.setBounds(120, 260, 70, 20);
		birthBox.setText("1998-10-22");

		JButton b = new JButton("Login with email");
		b.setBounds(100, 210, 195, 30);

		JButton bgoogle = new JButton("Login with Google");
		bgoogle.setBounds(100, 250, 195, 30);


		JButton bfacebook = new JButton("Login with Facebook");
		bfacebook.setBounds(100, 290, 195, 30);

		emailBox.setText("test@gmail.google.com");

		pass.setText("$!9PhNz,");
		
		bfacebook.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
				email = emailBox.getText();
				password = pass.getText();

				JOptionPane.showMessageDialog(null, "Facebook Login request sent");
				String sha1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex(password);
				JOptionPane.showMessageDialog(null, "\t* Password hash: " + sha1);
				//login facebook
				boolean result = controller.login(email, sha1, "Facebook");
				System.out.println("User signed in.");
				JOptionPane.showMessageDialog(null, "Facebook login result");
				long loginToken = controller.getToken();
				JOptionPane.showMessageDialog(null, "Facebook Token: " + loginToken);
				// reset text fields
				emailBox.setText("");
				pass.setText("");

				 if (loginToken != -1) {
				 	TrainingController trainingController = new TrainingController(serviceLocator);
				 	TrainingWindow trainingWindow = new TrainingWindow(trainingController, loginToken);
				 	trainingWindow.TrainingFrame();
				 	f.setVisible(false);
				 }
			}
		});

		bgoogle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				email = emailBox.getText();
				password = pass.getText();

				JOptionPane.showMessageDialog(null, "Google Login request sent");
				boolean result=controller.login(email, password, "Google");
				System.out.println("User signed in.");
				JOptionPane.showMessageDialog(null, "Google login result");
				long loginToken = controller.getToken();
				JOptionPane.showMessageDialog(null, "Google Token: " + loginToken);
				// reset text fields
				emailBox.setText("");
				pass.setText("");

				 if (loginToken != -1) {
				 	TrainingController trainingController = new TrainingController(serviceLocator);
				 	TrainingWindow trainingWindow = new TrainingWindow(trainingController, loginToken);
				 	trainingWindow.TrainingFrame();
				 	f.setVisible(false);
				 }
			}
		});

		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * JOptionPane.showMessageDialog(null, "Login request sent"); email.setText("");
				 * pass.setText("");
				 */
				email = emailBox.getText();
				password = pass.getText();

				JOptionPane.showMessageDialog(null, "Login request sent");
				String sha1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex(password);
				JOptionPane.showMessageDialog(null, "\t* Password hash: " + sha1);
				boolean result = controller.login(email, sha1, "normal");
				System.out.println("User signed in.");
				JOptionPane.showMessageDialog(null, "Login result");
				long loginToken = controller.getToken();
				JOptionPane.showMessageDialog(null, "Token: " + loginToken);
				// reset text fields
				emailBox.setText("");
				pass.setText("");

				if (loginToken != -1) {
					TrainingController trainingController = new TrainingController(serviceLocator);
					TrainingWindow trainingWindow = new TrainingWindow(trainingController, loginToken);
					trainingWindow.TrainingFrame();
					f.setVisible(false);
				}
			}
		});

		JButton c = new JButton("Register");
		c.setBounds(100, 330, 95, 30);
		c.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				c.setBounds(200, 300, 95, 30);
				b.setVisible(false);
				c.setVisible(false);
				bfacebook.setVisible(false);
				bgoogle.setVisible(false);
				
				lname.setVisible(true);
				name.setVisible(true);

				lweigth.setVisible(true);
				weigth.setVisible(true);

				lheight.setVisible(true);
				height.setVisible(true);

				lmaxrate.setVisible(true);
				maxrate.setVisible(true);

				lminrate.setVisible(true);
				minrate.setVisible(true);

				lbirth.setVisible(true);
				birthBox.setVisible(true);

			}
		});

		JButton d = new JButton("SEND");
		d.setBounds(100, 330, 95, 30);
		d.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				c.setBounds(200, 250, 95, 30);
				b.setVisible(true);
				c.setVisible(true);
				d.setVisible(true);

				lname.setVisible(false);
				name.setVisible(false);

				lweigth.setVisible(false);
				weigth.setVisible(false);

				lheight.setVisible(false);
				height.setVisible(false);

				lmaxrate.setVisible(false);
				maxrate.setVisible(false);

				lminrate.setVisible(false);
				minrate.setVisible(false);

				lbirth.setVisible(false);
				birthBox.setVisible(false);

				email = emailBox.getText();
				nickname = name.getText();
				dbirthdate = birthBox.getText();
				dweight = Integer.valueOf(weigth.getText());
				dheight = Integer.valueOf(height.getText());
				dmaxRate = Integer.valueOf(maxrate.getText());
				dminRate = Integer.valueOf(minrate.getText());

				JOptionPane.showMessageDialog(null, "Register request sent");
				String sha1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex(password);
				JOptionPane.showMessageDialog(null, "\t* Password hash: " + sha1);
				System.out.println("lanza register");
				boolean result = controller.registration(email, sha1, nickname, dbirthdate, dweight, dheight, dmaxRate,
						dminRate);

				System.out.println("devuelve register");
				JOptionPane.showMessageDialog(null, "Register result");

				long loginToken = controller.getToken();
				JOptionPane.showMessageDialog(null, "Token: " + loginToken);

				System.out.println("token i reg: " + loginToken);
				if (loginToken != -1) {
					TrainingController trainingController = new TrainingController(serviceLocator);
					TrainingWindow trainingWindow = new TrainingWindow(trainingController, loginToken);
					trainingWindow.TrainingFrame();
					f.setVisible(false);
				}

			}
		});

		f.add(b);
		f.add(c);
		f.add(d);
		f.add(emailBox);
		f.add(lemail);

		f.add(pass);
		f.add(lpass);
		//google facebook

		f.add(bgoogle);
		f.add(bfacebook);
		// register fields
		f.add(lname);
		f.add(name);
		lname.setVisible(false);
		name.setVisible(false);

		f.add(lmaxrate);
		f.add(maxrate);
		lmaxrate.setVisible(false);
		maxrate.setVisible(false);

		f.add(lminrate);
		f.add(minrate);
		lminrate.setVisible(false);
		minrate.setVisible(false);

		f.add(lweigth);
		f.add(weigth);
		lweigth.setVisible(false);
		weigth.setVisible(false);

		f.add(lheight);
		f.add(height);
		lheight.setVisible(false);
		height.setVisible(false);

		f.add(lbirth);
		f.add(birthBox);
		lbirth.setVisible(false);
		birthBox.setVisible(false);

		f.setSize(400, 400);
		f.setLayout(null);
		f.setVisible(true);
		
		boolean result = true;
		return result;
	}

}