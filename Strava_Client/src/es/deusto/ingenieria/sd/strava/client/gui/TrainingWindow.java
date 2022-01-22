package es.deusto.ingenieria.sd.strava.client.gui;

import java.util.ArrayList;
import java.util.List;
import java.lang.Float;
import java.lang.Integer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import es.deusto.ingenieria.sd.strava.server.data.dto.ActivityDTO;
import es.deusto.ingenieria.sd.strava.server.data.dto.ChallengeDTO;
import es.deusto.ingenieria.sd.strava.client.controller.TrainingController;

public class TrainingWindow {
	private TrainingController controller;
	private long loginToken;
	
	
	private String Ctitle;
	private String Csport;
	private String Cstart;
	private String Cend;
	private float CtargetDistance;
	private int CtargetTime;
	
	private String Atitle;
	private String Asport;
	private float Akm;
	private String Adate;
	private String AstartTime;
	private int Aduration;
	
	private JFrame loginWindow;

	public TrainingWindow(TrainingController training, long token, JFrame j) {
		this.controller = training;
		this.loginToken = token;
		this.loginWindow = j;
	}

	public boolean createActivity(long token, String title, String sport, Float km, String date, String startTime, int duration) {
		boolean activityStarted = this.controller.createActivity(token, title, sport, km, date, startTime, duration);
		System.out.println("Activity Created!");
		return activityStarted;
	}

	public List<ActivityDTO> getActivities(long token) {
		List<ActivityDTO> activities = this.controller.getActivities(token);

		for (ActivityDTO activity : activities) {
			System.out.println("Activities: " + activity);
		}
		return activities;
	}

	public List<ChallengeDTO> getChallenges() {
		List<ChallengeDTO> challenges = this.controller.getChallenges();

		for (ChallengeDTO challenge : challenges) {
			System.out.println("Challenges: " + challenge);
		}
		return challenges;
	}

	public boolean createChallenge(String title, String sport, String start, String end, Float targetDistance, int targetTime ) {
		boolean challengeCreated = this.controller.createChallenge(title, sport, start, end, targetDistance, targetTime);
		return challengeCreated;
	}

	public void acceptChallenge(long token, String challenge) {
		this.controller.acceptChallenge(token, challenge);
	}
	
	public void logout(long token) {
		System.out.println(" - Logout from the server...");
		this.controller.logout(token);
		this.loginWindow.setVisible(true);
		System.out.println("\t* Logout success!");
	}
	

	public void TrainingFrame() {
		JFrame f = new JFrame("STRAVA TRAINING ");

		ArrayList<JTextField> listChal = new ArrayList<>();
		ArrayList<JTextField> listAct = new ArrayList<>();
		
		// Create Activity Fields:
		final JLabel activityHeadline = new JLabel("CREATE ACTIVITY");
		activityHeadline.setBounds(120, 5, 150, 20);
		final JLabel activityTitle = new JLabel("Title of Activity: ");
		activityTitle.setBounds(120, 20, 150, 20);
		final JTextField activityTitleBox = new JTextField();
		activityTitleBox.setBounds(120, 40, 150, 20);
		
		final JLabel activitySport = new JLabel("Type of Sport:");
		activitySport.setBounds(120, 220, 150, 20);
		final JTextField activitySportBox = new JTextField();
		activitySportBox.setBounds(120, 240, 150, 20);

		final JLabel activityDate = new JLabel("Date of Activity:");
		activityDate.setBounds(120, 60, 150, 20);
		final JTextField activityDateBox = new JTextField();
		activityDateBox.setBounds(120, 80, 150, 20);

		final JLabel activityKm = new JLabel("Activity Length (Km):");
		activityKm.setBounds(120, 100, 150, 20);
		final JTextField activityKmBox = new JTextField();
		activityKmBox.setBounds(120, 120, 150, 20);

		final JLabel activityStartTime = new JLabel("Starttime:");
		activityStartTime.setBounds(120, 140, 150, 20);
		final JTextField activityStartTimeBox = new JTextField();
		activityStartTimeBox.setBounds(120, 160, 150, 20);

		final JLabel activityDurationTime = new JLabel("Duration time:");
		activityDurationTime.setBounds(120, 180, 150, 20);
		final JTextField activityDurationTimeBox = new JTextField();
		activityDurationTimeBox.setBounds(120, 200, 150, 20);
	
		// Create Challenge Fields:
		final JLabel challengeHeadline = new JLabel("CREATE CHALLENGE");
		challengeHeadline.setBounds(120, 5, 150, 20);
		
		final JLabel title = new JLabel("Title of Challenge:");
		title.setBounds(120, 20, 150, 20);
		final JTextField titleBox = new JTextField();
		titleBox.setBounds(120, 40, 150, 20);
		
		final JLabel sport = new JLabel("Type of Sport:");
		sport.setBounds(120, 220, 150, 20);
		final JTextField sportBox = new JTextField();
		sportBox.setBounds(120, 240, 150, 20);

		final JLabel start = new JLabel("Start Date:");
		start.setBounds(120, 60, 150, 20);
		final JTextField startBox = new JTextField();
		startBox.setBounds(120, 80, 150, 20);

		final JLabel end = new JLabel("End Date:");
		end.setBounds(120, 100, 150, 20);
		final JTextField endBox = new JTextField();
		endBox.setBounds(120, 120, 150, 20);

		final JLabel targetDist = new JLabel("Target Distance:");
		targetDist.setBounds(120, 140, 150, 20);
		final JTextField targetDistBox = new JTextField();
		targetDistBox.setBounds(120, 160, 150, 20);

		final JLabel targetTime = new JLabel("Target Time:");
		targetTime.setBounds(120, 180, 150, 20);
		final JTextField targetTimeBox = new JTextField();
		targetTimeBox.setBounds(120, 200, 150, 20);

		// Button show activities:
				JButton activities = new JButton("Get Activities");
				activities.setBounds(120, 50, 160, 30);
				activities.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						List<ActivityDTO> activityList = getActivities(loginToken);

						int i = 0;
						
						for (ActivityDTO act : getActivities(loginToken)) {
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
				
				JButton createChallengeButton = new JButton("Create Challenge");
				createChallengeButton.setBounds(120, 110, 160, 30);

				JButton acceptChallenge = new JButton("Accept Challenge");
				acceptChallenge.setBounds(120, 280, 120, 30);
				acceptChallenge.setVisible(false);
				
				challenges.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						List<ChallengeDTO> challengeList = getChallenges();

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
				
		// Create Activity:
		JButton b = new JButton("Create Activity");
		
		JButton backActivityButton = new JButton("Go back");
		backActivityButton.setBounds(120, 300, 160, 30);
		backActivityButton.setVisible(false);
		
		JButton sendActivityButton = new JButton("Send");
		sendActivityButton.setBounds(120, 270, 160, 30);
		sendActivityButton.setVisible(false);
				
		b.setBounds(120, 20, 160, 30);
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				b.setVisible(false);
				challenges.setVisible(false);
				activities.setVisible(false);
				createChallengeButton.setVisible(false);
				acceptChallenge.setVisible(false);
				
				activityHeadline.setVisible(true);
				activityTitle.setVisible(true);
				activityTitleBox.setVisible(true);
				activitySport.setVisible(true);
				activitySportBox.setVisible(true);
				activityDate.setVisible(true);
				activityDateBox.setVisible(true);			
				activityStartTime.setVisible(true);
				activityStartTimeBox.setVisible(true);
				activityKm.setVisible(true);
				activityKmBox.setVisible(true);
				activityDurationTime.setVisible(true);
				activityDurationTimeBox.setVisible(true);
				
				backActivityButton.setVisible(true);
				sendActivityButton.setVisible(true);

				sendActivityButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Atitle = activityTitleBox.getText();
						Asport = activitySportBox.getText();
//						Akm = activityKmBox.getText();
						Adate = activityDateBox.getText();
						AstartTime = activityStartTimeBox.getText();
//						Aduration = activityDurationTimeBox.getText();
						
						boolean activityStatus = createActivity(loginToken, Atitle, Asport, Akm, Adate, AstartTime, Aduration);
						System.out.println("Challenge created: " + activityStatus);
						
						b.setVisible(true);
						challenges.setVisible(true);
						activities.setVisible(true);
						createChallengeButton.setVisible(true);					
						backActivityButton.setVisible(false);
						sendActivityButton.setVisible(false);	
						acceptChallenge.setVisible(false);
						
						activityHeadline.setVisible(false);
						activityTitle.setVisible(false);
						activityTitleBox.setVisible(false);			
						activitySport.setVisible(false);
						activitySportBox.setVisible(false);						
						activityDate.setVisible(false);
						activityDateBox.setVisible(false);				
						activityKm.setVisible(false);
						activityKmBox.setVisible(false);							
						activityStartTime.setVisible(false);
						activityStartTimeBox.setVisible(false);								
						activityDurationTime.setVisible(false);
						activityDurationTimeBox.setVisible(false);	
						
						activityTitleBox.setText("");
						activitySportBox.setText("");
						activityDateBox.setText("");
						activityKmBox.setText("");
						activityStartTimeBox.setText("");
						activityDurationTimeBox.setText("");	
						
						((JButton) e.getSource()).removeActionListener(this);
					}
				});
				b.setVisible(false);
				
				for (JTextField act : listAct) {
					act.setVisible(false);
				}
				for (JTextField chal : listChal) {
					chal.setVisible(false);
				}
			}		
		});
	
		backActivityButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				b.setVisible(true);
				challenges.setVisible(true);
				activities.setVisible(true);
				createChallengeButton.setVisible(true);					
				backActivityButton.setVisible(false);
				sendActivityButton.setVisible(false);	
				acceptChallenge.setVisible(false);
				
				activityHeadline.setVisible(false);
				activityTitle.setVisible(false);
				activityTitleBox.setVisible(false);			
				activitySport.setVisible(false);
				activitySportBox.setVisible(false);						
				activityDate.setVisible(false);
				activityDateBox.setVisible(false);				
				activityKm.setVisible(false);
				activityKmBox.setVisible(false);							
				activityStartTime.setVisible(false);
				activityStartTimeBox.setVisible(false);								
				activityDurationTime.setVisible(false);
				activityDurationTimeBox.setVisible(false);	
				
				activityTitleBox.setText("");
				activitySportBox.setText("");
				activityDateBox.setText("");
				activityKmBox.setText("");
				activityStartTimeBox.setText("");
				activityDurationTimeBox.setText("");	
			}
		});
		
		JButton backChallengeButton = new JButton("Go back");
		backChallengeButton.setBounds(120, 300, 160, 30);
		backChallengeButton.setVisible(false);
		
		JButton sendChallengeButton = new JButton("Send");
		sendChallengeButton.setBounds(120, 270, 160, 30);
		sendChallengeButton.setVisible(false);
		
		createChallengeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				b.setVisible(false);
				challenges.setVisible(false);
				activities.setVisible(false);
				createChallengeButton.setVisible(false);
				challengeHeadline.setVisible(true);
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
						Ctitle = titleBox.getText();
						Csport = sportBox.getText();
						Cstart = startBox.getText();
						Cend = endBox.getText();
						// GET THESE TO WORK: (getText doesnt work because of float and int)
//						CtargetDistance = targetDistBox.getText();
						CtargetDistance = Float.valueOf(targetDistBox.getText());
						CtargetTime = Integer.parseInt(targetTimeBox.getText());

						boolean createdChallengeStatus = createChallenge(Ctitle, Csport, Cstart, Cend, CtargetDistance, CtargetTime);
						System.out.println("Challenge created: " + createdChallengeStatus);
						
						((JButton) e.getSource()).removeActionListener(this);
					}
				});

				backChallengeButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						b.setVisible(true);
						challenges.setVisible(true);
						activities.setVisible(true);
						createChallengeButton.setVisible(true);

						challengeHeadline.setVisible(false);
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

				for (JTextField act : listAct) {
					act.setVisible(false);

				}
				for (JTextField chal : listChal) {
					chal.setVisible(false);
				}
			}
		});

		JButton logoutButton = new JButton("Logout");
		logoutButton.setBounds(290,320,80,30);	
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logout(loginToken);
				f.setVisible(false);
				JOptionPane.showMessageDialog(null, "Goodbye! Logout successful.");
			}
		});
		
		f.add(b);
		f.add(logoutButton);
		f.setSize(400, 400);
		f.setLayout(null);
		f.setVisible(true);
		f.add(activities);
		f.add(challenges);
		f.add(createChallengeButton);		
		
		// Send Challenge
		f.add(sendChallengeButton);
		f.add(backChallengeButton);
		
		// Add Challenge
		f.add(acceptChallenge);
		f.add(backActivityButton);
		f.add(sendActivityButton);
				
		// Create Challenge Fields:
		f.add(challengeHeadline);
		f.add(title);
		f.add(titleBox);
		challengeHeadline.setVisible(false);
		title.setVisible(false);
		titleBox.setVisible(false);
		
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
		
		// Create Activity Fields:
		f.add(activityHeadline);
		f.add(activityTitle);
		f.add(activityTitleBox);
		activityHeadline.setVisible(false);
		activityTitle.setVisible(false);
		activityTitleBox.setVisible(false);
		
		f.add(activitySport);
		f.add(activitySportBox);
		activitySport.setVisible(false);
		activitySportBox.setVisible(false);
		
		f.add(activityDate);
		f.add(activityDateBox);
		activityDate.setVisible(false);
		activityDateBox.setVisible(false);
		
		f.add(activityKm);
		f.add(activityKmBox);
		activityKm.setVisible(false);
		activityKmBox.setVisible(false);
		
		f.add(activityStartTime);
		f.add(activityStartTimeBox);
		activityStartTime.setVisible(false);
		activityStartTimeBox.setVisible(false);
		
		f.add(activityDurationTime);
		f.add(activityDurationTimeBox);
		activityDurationTime.setVisible(false);
		activityDurationTimeBox.setVisible(false);	

	}
}
