package blackjack;
/* User_File.java
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class User_File {
	public String win;
    public String draw;
    public String lose;
    public String cash;
    
	public void userfile(String username){
        BufferedReader bReader = null;
        
        try {								//Read line by line in file
            File file = new File(username);
            bReader = new BufferedReader(new FileReader(file));
            win = bReader.readLine();		//Save win
            draw = bReader.readLine();		//Save draw
            lose = bReader.readLine();		//Save lose
            cash = bReader.readLine();		//Save cash
          }
          catch(IOException e) {
            e.printStackTrace();
        }
    }
	
	public int win() {					
		return Integer.parseInt(win);		//Converting string into integer
	}
	
	public int draw() {
		return Integer.parseInt(draw);		//Converting string into integer
	}
	
	public int lose() {
		return Integer.parseInt(lose);		//Converting string into integer
	}
	
	public int cash() {
		return Integer.parseInt(cash);		//Converting string into integer
	}
	
	public User_File(String username){		//Constructor
		userfile(username);
	}
}

