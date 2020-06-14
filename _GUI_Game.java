package blackjack;
/* _GUI_Game.java
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;


public class _GUI_Game extends JFrame implements ActionListener {
		/********************************	Compose container and component		********************************/
   public int wins, loses, draws, cash, bets;                                    // record of game
   private String user = new String();                                           // User name
   private JTextArea jlblStatus = new JTextArea("Please Click Button \"Deal\""); // initial screen status
   private String status = new String();                                         // the status of player 
   private static final long serialVersionUID = 1L;                              // check class version 
                       
   private Deck deck;                                                            // initialize card Deck;
   public Player player = new Player("player");                                  // initialize Player
   public Player dealer = new Player("dealer");                                  // initialize Dealer

   private JButton jbtnHit = new JButton("Hit");                                 // Hit button 
   private JButton jbtnStay = new JButton("Stay");                               // Stay button 
   private JButton jbtnDeal = new JButton("Deal");                               // Deal button 
   private JButton jbtnSurrender = new JButton("Surrender");                     // Surrender button 
   private JButton jbtnDouble = new JButton("Double");                           // Double button 
   private JButton jbtnExit = new JButton("Exit");                               // Exit button 

   JPanel playerPanel = new JPanel();                                            // Initialize Player_panel
   JPanel dealerPanel = new JPanel();                                            // Initialize Delaer_panel
   JPanel buttonsPanel = new JPanel();                                           // Initialize Button_panel
   JPanel statusPanel = new JPanel();                                            // Initialize Status_panel
   MediaTest bgm = new MediaTest();                                              // Initialize Sound class;
   
   public _GUI_Game(String userName) {
	    bgm.b();                                                                   // Play Background music
      this.user = userName;                                                      // assign user name;
      User_File uf = new User_File(userName);                                    // call User_file class
      uf.userfile(userName);                                                     // read user information
      wins = uf.win();                                                           // assign score of win
      draws= uf.draw();                                                          // assign score of draw
      loses = uf.lose();                                                         // assign score of lose
      cash = uf.cash();                                                          // assign the score of win
      jlblStatus.setBackground(Color.green);                                     // color of status_panel
      player.setName(userName);                                                  
      
      JFrame gameFrame = new JFrame("BlackJack");                                // program name
      gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                  // X button
      
//Making Panel
      buttonsPanel.add(jbtnHit);                                           // add hit to button_panel
      buttonsPanel.add(jbtnStay);                                          // add stay to button_panel
      buttonsPanel.add(jbtnSurrender);                                     // add surrender to button_panel
      buttonsPanel.add(jbtnDouble);                                        // add Double to button_panel
      buttonsPanel.add(jbtnDeal);                                          // add Deal to button_panel
      statusPanel.add(jlblStatus);                                         // add status to Status_panel
      buttonsPanel.add(jbtnExit);                                          // add Exit to button_panel
      
//Activation of Button    
      jbtnHit.addActionListener(this);                                    // Activation of Hit button
      jbtnStay.addActionListener(this);                                   // Activation of Stay button
      jbtnDeal.addActionListener(this);                                   // Activation of Deal button
      jbtnExit.addActionListener(this);                                   // Activation of Exit button
      jbtnSurrender.addActionListener(this);                              // Activation of Surrender button
      jbtnDouble.addActionListener(this);                                 // Activation of Double button
      
//Setting the status of button     
      jbtnHit.setEnabled(false);                                          // Status in Hit button
      jbtnStay.setEnabled(false);                                         // Status in Stay button
      jbtnSurrender.setEnabled(false);                                    // Status in Surrender button
      jbtnDouble.setEnabled(false);                                       // Status in Double button     
      
//Setting the Background of Panel   
      dealerPanel.setBackground(Color.green);                             // set the color of Dealer Panel
      playerPanel.setBackground(Color.green);                             // set the color of Player Panel
      buttonsPanel.setBackground(Color.green);                            // set the color of Button Panel
      statusPanel.setBackground(Color.green);                             // set the color of Status Panel

//Setting the Layout of Frame
      gameFrame.setLayout(new BorderLayout());                            // call the BorderLayout
      gameFrame.add(dealerPanel, BorderLayout.NORTH);         
      gameFrame.add(playerPanel, BorderLayout.CENTER);
      gameFrame.add(buttonsPanel, BorderLayout.SOUTH);
      gameFrame.add(statusPanel, BorderLayout.WEST);
      gameFrame.repaint();                                                // Component relocation
      gameFrame.setSize(450, 350);                                        // Set the size of Frame
      gameFrame.setVisible(true);                                         // show the frame
      gameFrame.setLocationRelativeTo(null);                              // set the Screen to center
   }
   
	/*******************************************************************************************/
   private void hitPlayer() {                               // Player pull the card from deck
      Card newCard = player.dealTo(deck.dealFrom());
      playerPanel.add(new JLabel(new ImageIcon("cards/" + newCard.toString())));
      playerPanel.updateUI();
   }

   private void hitDealerDown() {                            // Dealer pull the card from deck
      dealer.dealTo(deck.dealFrom());
      dealerPanel.add(new JLabel(new ImageIcon("cards/b2fv.png"))); // the status of card is upside down
      dealerPanel.updateUI();
   }

   private void hitDealer() {                                // Dealer pull the card from deck   
      Card newCard = dealer.dealTo(deck.dealFrom());
      dealerPanel.add(new JLabel(new ImageIcon("cards/" + newCard.toString())));
      dealerPanel.updateUI();
   }

   private void deal() {                                     // Player bets the money and plays game
      playerPanel.removeAll();                               // 1. initialize the screen.
      dealerPanel.removeAll();
      playerPanel.updateUI();
      dealerPanel.updateUI();
      player.reset();
      dealer.reset();
      if (deck == null || deck.size() < 15) {                 // initialize card Deck
         deck = new Deck();
         deck.shuffle();
      }
      
//Game start
      hitPlayer();        
      hitDealerDown();
      hitPlayer();
      hitDealer();
   }
   
// Checking the winner method
   private void checkWinner() {                                
      dealerPanel.removeAll();                                 // 1.resetting the Dealer panel.
      for (int i = 0; i < dealer.inHand(); i++) {
         dealerPanel.add(new JLabel(new ImageIcon("cards/" + dealer.cards[i].toString())));
      }
                                                               // 2. Check Winner
      if (player.value() > 21) {                               // 2.a. Player burst
         jlblStatus.setText("Player Busts");
         loses++;                                              // Lose
         status = "WIN : "+wins+"\nLOSE : "+loses+"\nDRAW : "+draws+"\nMONEY : "+cash;
         jlblStatus.append("\n"+status);                            // update status
      } else if (dealer.value() > 21) {                        // 2.b. Dealer burst                     
         jlblStatus.setText("Dealer Busts");
         wins++;                                               // Win
         cash+=bets*2;                                         // cash update
         status = "WIN : "+wins+"\nLOSE : "+loses+"\nDRAW : "+draws+"\nMONEY : "+cash;
         jlblStatus.append("\n"+status);                            // update status
         
      } else if (dealer.value() == player.value()) {           // 2.c. Push
         jlblStatus.setText("Push");
         draws++;                                              // Draw
         cash+=bets;                                           // return the betting money
         status = "WIN : "+wins+"\nLOSE : "+loses+"\nDRAW : "+draws+"\nMONEY : "+cash;
         jlblStatus.append("\n"+status);                            // update status
         
      } else if (dealer.value() < player.value()) {            // 2.d. Win
         jlblStatus.setText("Player Wins");
         wins++;                                               // Win
         cash+=bets*2;                                         // Cash Update
         status = "WIN : "+wins+"\nLOSE : "+loses+"\nDRAW : "+draws+"\nMONEY : "+cash;
         jlblStatus.append("\n"+status);                            // Update Status
         
      }
       else {                                                   // 2.e. Lose
            jlblStatus.setText("Dealer Wins");
            loses++;                                            // Lose
            status = "WIN : "+wins+"\nLOSE : "+loses+"\nDRAW : "+draws+"\nMONEY : "+cash;
            jlblStatus.append("\n"+status);                          // Update Status
         }
   }

   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == jbtnHit) {                           // 1.Action of Hit button
    	 bgm.button();                                            // active Button bgm 
         hitPlayer();                                           // Hit
         if (player.value() > 21) {                             // Player burst
            checkWinner();
            jbtnHit.setEnabled(false);
            jbtnStay.setEnabled(false);
            jbtnDeal.setEnabled(true);
            jbtnSurrender.setEnabled(false);
            jbtnDouble.setEnabled(false);
         }
      }

      if (e.getSource() == jbtnStay) {                      // 2.Action of Stay button
    	 bgm.button();                                        // active Button bgm 
         while (dealer.value() < 17 || player.value() > dealer.value()) {
            hitDealer();                                    // Dealer pull card until sum is lower than 17
         }
         checkWinner();                                     // check winner
         jbtnHit.setEnabled(false);
         jbtnStay.setEnabled(false);
         jbtnDeal.setEnabled(true);
         jbtnSurrender.setEnabled(false);
         jbtnDouble.setEnabled(false);
      }

      if (e.getSource() == jbtnDeal) {                      // 3.Action of Deal button
    	  bgm.button();                                       // active Button bgm
          if(cash <= 0) {                                   // If player broke,
             JOptionPane.showMessageDialog(null, "You don't have enough money"); // show the message 
            try {
               FileWriter fw = new FileWriter(user);        // write the records
               fw.write(wins+"\n"+draws+"\n"+loses+"\n"+cash);
               fw.close();                                  
            } catch (IOException e1) {
               e1.printStackTrace();
            }
             System.exit(0);                                // Exit program
          }
          
          try {
             String input = JOptionPane.showInputDialog(null, "How much would you like to bet?\nYour cash : "+cash);                                     // store the input value
              if(input.equals("")) {
                 JOptionPane.showMessageDialog(null, "Please enter the money.");
                 return;
              }
              if(Integer.parseInt(input) > cash) {
                 JOptionPane.showMessageDialog(null, "You don't have enough money");
                 return;
              }
              
              this.bets= Integer.parseInt(input);           // setting the betting money.
          } catch (Exception e2) {
             return;
          }
           cash-=bets;                                      // betting
           
           deal();                                          // call Deal method;
           if(player.value()==21&&dealer.value()==21) {     // 3.a.Push
               jlblStatus.setText("Push");
               draws++;
               cash+=bets;
               status = "WIN : "+wins+"\nLOSE : "+loses+"\nDRAW : "+draws+"\nMONEY : "+cash;
               jlblStatus.append("\n"+status);
               return;
           }
           else if(player.value()==21) {                     // 3.b. Win
               jlblStatus.setText("Player BLACKJACK !");
               wins++;
               cash+=(bets*2.5);
               status = "WIN : "+wins+"\nLOSE : "+loses+"\nDRAW : "+draws+"\nMONEY : "+cash;
               jlblStatus.append("\n"+status);
               return;
           }
           else if(dealer.value()==21) {                     // 3.c. Lose
        	   hitDealer();
               jlblStatus.setText("Dealer BLACKJACK !");
               loses++;
               status = "WIN : "+wins+"\nLOSE : "+loses+"\nDRAW : "+draws+"\nMONEY : "+cash;
               jlblStatus.append("\n"+status);
               return;
           }
           jlblStatus.setText(" ");
           jbtnHit.setEnabled(true);
           jbtnStay.setEnabled(true);
           jbtnDeal.setEnabled(false);
           jbtnSurrender.setEnabled(true);
           jbtnDouble.setEnabled(true);
           status = "WIN : "+wins+"\nLOSE : "+loses+"\nDRAW : "+draws+"\nMONEY : "+cash;
           jlblStatus.append("\n"+status); //
        }
      
      if (e.getSource() == jbtnSurrender) {                    // 4.Action of Surrender Button
    	   bgm.button();                                           // active bgm button
    	   cash+=bets*0.5;                                         // cash update
         jlblStatus.setText("you Surrender ");          
         status = "WIN : "+wins+"\nLOSE : "+loses+"\nDRAW : "+draws+"\nMONEY : "+cash;
         jlblStatus.append("\n"+status);
         jbtnHit.setEnabled(false);
         jbtnStay.setEnabled(false);
         jbtnSurrender.setEnabled(false);
         jbtnDouble.setEnabled(false);
         jbtnDeal.setEnabled(true);
      }
      
      if (e.getSource() == jbtnDouble) {                       // 5.Action of Double Button
    	 bgm.button();
         if(bets > cash)                                       // 5.a betting money > cash 
         {
           JOptionPane.showMessageDialog(null, "You don't have enough money");
            return;
         }                                                     // 5.b betting money < cash
            cash-=bets;
            bets=bets*2;                                       // betting money doulbe
            hitPlayer();                                       // hit
            jbtnHit.setEnabled(false);
            jbtnStay.setEnabled(false);
            jbtnSurrender.setEnabled(false);
            jbtnDouble.setEnabled(false);
            while (dealer.value() < 17 || player.value() > dealer.value()) {
                hitDealer();
             }
            checkWinner();
            jbtnDeal.setEnabled(true);
            if (player.value() > 21) {
               checkWinner();
               jbtnHit.setEnabled(false);
               jbtnStay.setEnabled(false);
               jbtnSurrender.setEnabled(false);
               jbtnDouble.setEnabled(false);
               jbtnDeal.setEnabled(true);
            }
         }
      
      if (e.getSource() == jbtnExit) {                          // 6. activation of Exit
    	 bgm.button();
         try {
            FileWriter fw = new FileWriter(user);               // Call the Filewriter class
            fw.write(wins+"\n"+draws+"\n"+loses+"\n"+cash);     // writhe the records
            fw.close();                                         // close the file
            JOptionPane.showMessageDialog(null, "win: " + wins+"\n"+ "draw: " + draws+"\n"+ "loses: "+ loses+"\n"+ "cash: " + cash);                       // show the records to the screen
         } catch (IOException e1) {
            e1.printStackTrace();
         }
         
         System.exit(0);                                        // Exit
      }
   }

   public static void main(String[] args) {
      new _GUI_Game("player");
   }
}
