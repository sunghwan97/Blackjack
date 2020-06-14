package blackjack;
/* _GUI_Login.java
 */

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ImageIcon;

public class _GUI_Login {
	private JFrame frame;
	
	public _GUI_Login() {
		final MediaTest bgm = new MediaTest();		// Declare MediaTest class
		bgm.a();									// Casino Night music play
		
		/********************************	Compose container and component		********************************/
		frame = new JFrame();												// 1. Frame
		frame.setTitle("Blackjack Login");									// Theme
		frame.setBounds(100, 100, 498, 520);								// Location and size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				// Close function activate
		frame.setLocationRelativeTo(null);									// Center of the screen
		frame.setLayout(null);												// Layout

		final JTextField id = new JTextField();								// 2. TextField
		id.setBounds(181, 396, 108, 37);									// Location and size
		frame.getContentPane().add(id);										// Add in Frame

		JButton LoginButton = new JButton();								// 3. JButton
		LoginButton.setIcon(new ImageIcon("./image/LoginButton.jpg"));		// Image
		LoginButton.setBounds(301, 398, 91, 30);							// Location and size
		frame.getContentPane().add(LoginButton);							// Add in Frame

		JLabel IDLabel = new JLabel("ID");									// 4. Label
		IDLabel.setForeground(Color.WHITE);									// Text Color
		IDLabel.setHorizontalAlignment(SwingConstants.CENTER);				// Center Alignment
		IDLabel.setFont(new Font("Arial", Font.PLAIN, 20));					// Font
		IDLabel.setBounds(137, 400, 45, 30);								// Location and size
		frame.getContentPane().add(IDLabel);								// Add in Frame

		JLabel Background = new JLabel("Background");						// 5. Label
		Background.setIcon(new ImageIcon("./image/Blackjack.jpg"));			// Background Image
		Background.setBounds(0, 10, 490, 490);								// Location and size
		frame.getContentPane().add(Background);								// Add in Frame
		
		frame.setVisible(true);
		/*******************************************************************************************/		
		
		// Event occurs when LoginButtion is pressed
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					bgm.stop();							//BGM stop											
				    bgm.button();						//Button click sound effect
					String loginText = id.getText();
					if(loginText.equals("")) {			//When nothing is entered in the login window
						JOptionPane.showMessageDialog(null, "Please enter ID");
						return;
					}
					else {
						checkLogin(loginText);		
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	public void checkLogin(String userName) throws IOException {
		File userfile = new File(userName);
		boolean userExists = userfile.exists();
																// 1.
		if (userExists) {	 									// If existing user
			User_File uf = new User_File(userName);				// Declare User_FIle class
			double sum =  uf.win()+uf.draw()+uf.lose();
			double win_rate = uf.win()/sum;
			win_rate = Math.round(win_rate*1000)/1000.0;
			if(uf.cash()==0) {									//If the user has no money
				JOptionPane.showMessageDialog(null, "You don't have money!!\nYou can't enter game.");
				System.exit(0);
			}
			
			String Message1 = "You are existing user.\n"+"< "+userName+" >\n";
			String Message2 = "Wins: "+uf.win()+ " / Draws: "+uf.draw()+" / Loses: "+uf.lose();
			String Message3 = "\n( Win rate : "+win_rate+" )"+"\nCash: "+uf.cash();
			JOptionPane.showMessageDialog(null, Message1+Message2+Message3);		// Notification Message
			frame.setVisible(false);												// Hide Login Window
			new _GUI_Game(userName);												// Execute Blackjack
		}
																// 2.
		else { 													// If new user
			String Message = "You are new user.\n"+"< "+userName+" account has been created. >\n";
			JOptionPane.showMessageDialog(null, Message);		// Notification Message
			FileWriter fw = new FileWriter(userfile);
			fw.write("0\n0\n0\n10000");							//Status initialize and Cache 10000
			fw.close();
			frame.setVisible(false);							// Hide Login Window
			new _GUI_Game(userName);							// Execute Blackjack
		}
	}

	public static void main(String[] args) {
		new _GUI_Login();
	}
}
