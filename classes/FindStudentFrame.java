import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JButton; 
import javax.swing.JTextField; 
import javax.swing.JPasswordField; 
import javax.swing.JOptionPane; 
import javax.swing.JInternalFrame; 
import java.util.Scanner; 
import java.awt.GridLayout; 
import java.io.File; 
import java.util.Arrays; 

public class FindStudentFrame extends JInternalFrame  
{ 
  private JLabel SSNLabel; //user label 
  private JTextField SSNTextField; // user text filed 
  private JButton enterButton; //login button 
  private JButton cancelButton; //cancel button 
  
  String userName; 
  char[] password;
  
  public FindStudentFrame() 
  { 
    super("Find Student", true, true, true, true ); 
    
    setLayout( new GridLayout(2,2) );  
    
    SSNLabel = new JLabel("SSN: "); 
    
    SSNTextField = new JTextField( 10 );  
    
    enterButton = new JButton("Enter"); 
    cancelButton = new JButton("Cancel"); 
    
    getContentPane().add(SSNLabel); 
    getContentPane().add(SSNTextField); 
    
    
    getContentPane().add(enterButton); 
    getContentPane().add(cancelButton); 
    
    
    LoginHandler handler = new LoginHandler(); 
    enterButton.addActionListener( handler ); 
    cancelButton.addActionListener( handler ); 
  }  
  
  private class LoginHandler implements ActionListener  
  { 
    // process textfield events 
    public void actionPerformed( ActionEvent event ) 
    { 
      if ( event.getSource() == enterButton ) 
      { 
        userName = SSNTextField.getText(); 

        boolean foundMatch = false; 
        try 
        { 
          Scanner sc = new Scanner(new File("logins.txt")); 
          while(sc.hasNext() && !foundMatch) 
          { 
            String[] entry = sc.next().split(","); 
            System.out.println("user: "+userName+ "  entry[0]: "+entry[0]); 
            System.out.println("password: "+password+ "  entry[1]: "+entry[1]); 
            
            if( Arrays.equals (entry[0].toCharArray(), userName.toCharArray()) && Arrays.equals (entry[1].toCharArray(), password)) 
            { 
              foundMatch = true; 
              break; 
            }              
          } 
          if(!foundMatch) 
          { 
            JOptionPane.showMessageDialog( null, "Unsuccessful Login","Failed Login", JOptionPane.ERROR_MESSAGE ); 
            SSNTextField.setText(""); 

          } 
          else 
          { 
            JOptionPane.showMessageDialog( null, "Successful Login","Successful Login", JOptionPane.INFORMATION_MESSAGE ); 
            setClosed( true );  
          } 
        } 
        catch(Exception e) 
        { 
          e.printStackTrace(); 
        } 
      } 
      else if( event.getSource() == cancelButton ) 
      { 
        setVisible(false); 
      } 
    }  
  }
}  

