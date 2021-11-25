package es.deusto.ingenieria.sd.strava.client.gui;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import es.deusto.ingenieria.sd.strava.server.data.dto.ActivityDTO;
import es.deusto.ingenieria.sd.strava.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.strava.client.controller.TrainingController;
import es.deusto.ingenieria.sd.strava.client.controller.LoginController;

public class TrainingWindow {
	private TrainingController controller;
	private LoginController loginController;
	private long loginToken;
//	private String email = "thomas.e2001@gmail.com";
//	private String password = "$!9PhNz,";
	
	private String Ttitle = "Bike 100 KM";
	private String Tsport = "biking";
	private String Tstart = "20210113";
	private String Tend = "20220113";
	private float TtargetDistance = 100;
	private int TtargetTime = 11;

	public TrainingWindow(TrainingController training, long token) {
		this.controller = training;
		this.loginToken = token;
	}

	public boolean startActivity() {
		boolean activityStarted = this.controller.startActivity();
		System.out.println("Activity started!");
		return activityStarted;
	}

	public List<ActivityDTO> getActivities() {
		List<ActivityDTO> activities = this.controller.getActivities();

		for (ActivityDTO activity : activities) {
			System.out.println("Activities: " + activity);
		}
		return activities;
	}

	public List<ChallengeDTO> getChallenges() {
		List<ChallengeDTO> challenges = this.controller.getChallenges();

		for (ChallengeDTO challenge : challenges) {
			//System.out.println("Challenges: " + challenge);
		}
		return challenges;
	}

	public boolean createChallenge(String title, String sport, String start, String end, Float targetDistance, int targetTime ) {
		boolean challengeCreated = this.controller.createChallenge(title, sport, start, end, targetDistance, targetTime);
		return challengeCreated;
	}

	public void acceptChallenge(long token, String challenge) {

		boolean acceptChallenge = this.controller.acceptChallenge(token, challenge);
		System.out.println("CHALLENGE HAS BEEN ACCEPTED ---  " + acceptChallenge);

	}

	public void TrainingFrame() {
		JFrame f = new JFrame("STRAVA TRAINING ");

		ArrayList<JTextField> listChal = new ArrayList<>();
		ArrayList<JTextField> listAct = new ArrayList<>();
		final JLabel title = new JLabel("Title");
		title.setBounds(120, 20, 150, 20);
		final JTextField titleBox = new JTextField();
		titleBox.setBounds(120, 40, 150, 20);
		//titleBox.setText("Marathon");
		
		final JLabel sport = new JLabel("Type of Sport");
		sport.setBounds(120, 220, 150, 20);
		final JTextField sportBox = new JTextField();
		sportBox.setBounds(120, 240, 150, 20);

		final JLabel start = new JLabel("Start Date");
		start.setBounds(120, 60, 150, 20);
		final JTextField startBox = new JTextField();
		startBox.setBounds(120, 80, 150, 20);
//		startBox.setText("2021-11-15");

		final JLabel end = new JLabel("End Date");
		end.setBounds(120, 100, 150, 20);
		final JTextField endBox = new JTextField();
		endBox.setBounds(120, 120, 150, 20);
//		endBox.setText("2022-05-15");

		final JLabel targetDist = new JLabel("Target Distance");
		targetDist.setBounds(120, 140, 150, 20);
		final JTextField targetDistBox = new JTextField();
		targetDistBox.setBounds(120, 160, 150, 20);
//		targetDistBox.setText("42 km");

		final JLabel targetTime = new JLabel("Target Time");
		targetTime.setBounds(120, 180, 150, 20);
		final JTextField targetTimeBox = new JTextField();
		targetTimeBox.setBounds(120, 200, 150, 20);
//		targetTimeBox.setText("4h 10min");

		//Button for going into create challenge mode
		JButton createChallengeSend = new JButton("Create Challenge!");
		createChallengeSend.setBounds(120, 20, 160, 30);
		createChallengeSend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {				

			}
		});

		JButton b = new JButton("Start Activity");
		JLabel activityStarted = new JLabel("Activity Started!");
		activityStarted.setVisible(false);
		activityStarted.setBounds(120, 20, 150, 20);
		b.setBounds(120, 20, 160, 30);
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean activityStatus = startActivity();
				// THIS IS THE ACCEPTCHALLENGE which needs interface, you can move the call to
				// acceptChallenge()
				// this challenge has to be changed after you've added it once

				b.setVisible(false);
				activityStarted.setVisible(true);
			}
		});
		// boton show activities
		JButton activities = new JButton("Get Activities");
		activities.setBounds(120, 50, 160, 30);
		activities.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<ActivityDTO> activityList = getActivities();
				System.out.println("Activity List: " + activityList);

				int i = 0;
				
				for (ActivityDTO act : getActivities()) {
					JTextField activitiesBox = new JTextField();
					activitiesBox.setBounds(90, 170 + i, 290, 40);
					activitiesBox.setEditable(false);
					activitiesBox.setText(act.getTitle().toString());
					f.add(activitiesBox);
					i += 40;
					listAct.add(activitiesBox);
				}

				for (JTextField chal : listChal) {
					chal.setVisible(false);

				}

			}
		});

		JButton challenges = new JButton("Get Challenges");
		challenges.setBounds(120, 80, 160, 30);

		JButton acceptChallenge = new JButton("Accept Challenge");
		acceptChallenge.setBounds(200, 280, 120, 30);
		acceptChallenge.setVisible(false);

		challenges.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<ChallengeDTO> challengeList = getChallenges();
				// System.out.println("i trainingframe " + challengeList);
				int i = 0;
				for (ChallengeDTO chal : getChallenges()) {
					JTextField challengeBox = new JTextField();
					challengeBox.setBounds(90, 190 + i, 290, 40);
					challengeBox.setEditable(false);
					challengeBox.setText(chal.getTitle().toString());
					f.add(challengeBox);
					listChal.add(challengeBox);
					i += 40;
				}
				// accept
				acceptChallenge.setVisible(true);

				acceptChallenge.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						acceptChallenge.setVisible(false);

						// logic part for ACCEPT
						// JOptionPane.showMessageDialog(null,"Try accept");
						// acceptChallenge(loginToken, "Marathon");
						// JOptionPane.showMessageDialog(null,"Challenge accepted");

						JOptionPane.showMessageDialog(null, "Accept Challenge");
						acceptChallenge(loginToken, challengeList.get(0).getTitle());
						JOptionPane.showMessageDialog(null, "Challenge Accepted!");
					}
				});

				for (JTextField act : listAct) {
					act.setVisible(false);
				}
			}
		});

		JButton backChallengeButton = new JButton("Go back");
		backChallengeButton.setBounds(120, 250, 160, 30);
		backChallengeButton.setVisible(false);
		
		JButton sendChallengeButton = new JButton("Send");
		sendChallengeButton.setBounds(120, 280, 160, 30);
		sendChallengeButton.setVisible(false);
		
		JButton createChallengeButton = new JButton("Create Challenge");
		createChallengeButton.setBounds(120, 110, 160, 30);

		createChallengeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				b.setVisible(false);
				activityStarted.setVisible(false);
				challenges.setVisible(false);
				activities.setVisible(false);
				createChallengeButton.setVisible(false);

				title.setVisible(true);
				titleBox.setVisible(true);
				
				sport.setVisible(true);
				sportBox.setVisible(true);
				
				start.setVisible(true);
				startBox.setVisible(true);
				
				end.setVisible(true);
				endBox.setVisible(true);

				targetDist.setVisible(true);
				targetDistBox.setVisible(true);

				targetTime.setVisible(true);
				targetTimeBox.setVisible(true);
				
				backChallengeButton.setVisible(true);
				sendChallengeButton.setVisible(true);

				sendChallengeButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Ttitle = titleBox.getText();
						Tsport = sportBox.getText();
						Tstart = startBox.getText();
						Tend = endBox.getText();
						// GET THESE TO WORK: (getText doesnt work because of float and int)
//						TtargetDistance = targetDistBox.getText();
//						TtargetTime = targetTimeBox.getText();

						boolean createdChallengeStatus = createChallenge(Ttitle, Tsport, Tstart, Tend, TtargetDistance, TtargetTime);
						System.out.println("Challenge created: " + createdChallengeStatus);

					}
				});

				backChallengeButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						//System.out.println("Challenge result: " + createChallenge());
						b.setVisible(true);
						activityStarted.setVisible(true);
						challenges.setVisible(true);
						activities.setVisible(true);
						createChallengeButton.setVisible(true);

						title.setVisible(false);
						titleBox.setVisible(false);
						
						sport.setVisible(false);
						sportBox.setVisible(false);
						
						start.setVisible(false);
						startBox.setVisible(false);

						end.setVisible(false);
						endBox.setVisible(false);

						targetDist.setVisible(false);
						targetDistBox.setVisible(false);

						targetTime.setVisible(false);
						targetTimeBox.setVisible(false);
						//
						backChallengeButton.setVisible(false);
						sendChallengeButton.setVisible(false);
						
						titleBox.setText("");
						sportBox.setText("");
						startBox.setText("");
						endBox.setText("");
						targetDistBox.setText("");
						targetTimeBox.setText("");	
					}
				});

				//
				for (JTextField act : listAct) {
					act.setVisible(false);

				}
				for (JTextField chal : listChal) {
					chal.setVisible(false);

				}

			}
		});

		f.add(b);
		f.add(activityStarted);
		f.setSize(400, 400);
		f.setLayout(null);
		f.setVisible(true);
		f.add(activities);
		f.add(challenges);
		f.add(createChallengeButton);

		// send challenge
		f.add(sendChallengeButton);
		f.add(backChallengeButton);
		// create challenges
		f.add(title);
		f.add(titleBox);
		title.setVisible(false);
		titleBox.setVisible(false);
		// add challenge
		f.add(acceptChallenge);
		
		f.add(sport);
		f.add(sportBox);
		sport.setVisible(false);
		sportBox.setVisible(false);
		
		f.add(start);
		f.add(startBox);
		start.setVisible(false);
		startBox.setVisible(false);

		f.add(end);
		f.add(endBox);
		end.setVisible(false);
		endBox.setVisible(false);

		f.add(targetTime);
		f.add(targetTimeBox);
		targetTime.setVisible(false);
		targetTimeBox.setVisible(false);

		f.add(targetDist);
		f.add(targetDistBox);
		targetDist.setVisible(false);
		targetDistBox.setVisible(false);

	}
}
