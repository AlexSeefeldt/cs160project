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

public class ViewTranscriptFrame extends JInternalFrame 
{
  private JLabel userLabel; //user label
  private JTextField userTextField; // user text filed
  private JLabel passwordLabel; //user label
  private JButton closeButton; //cancel button
  

  public ViewTranscriptFrame()
  {
    super("View Transcript"true, true, true, true );
    setLayout( new GridLayout(3,2) ); 
    
   
    loginButton = new JButton("Select Term");
    cancelButton = new JButton("Close");
    
    getContentPane().add(closeButton);
    
    
    LoginHandler handler = new LoginHandler();
    loginButton.addActionListener( handler );
    cancelButton.addActionListener( handler );
  } 
  private class LoginHandler implements ActionListener 
  {
     public void actionPerformed( ActionEvent event ){
      if ( event.getSource() == loginButton ){
        userName = userTextField.getText();
        password = passwordField.getPassword();
        boolean foundMatch = false;
        try {
          Scanner sc = new Scanner(new File("logins.txt"));
          while(sc.hasNext() && !foundMatch){
            String[] entry = sc.next().split(",");
            System.out.println("user: "+userName+ "  entry[0]: "+entry[0]);
            System.out.println("password: "+password+ "  entry[1]: "+entry[1]);           
             if( Arrays.equals (entry[0].toCharArray(), userName.toCharArray()) && Arrays.equals (entry[1].toCharArray(), password)){
              foundMatch = true;
              break;
            }             
          }
          if(!foundMatch){
            JOptionPane.showMessageDialog( null, "Unsuccessful Login","Failed Login", JOptionPane.ERROR_MESSAGE );
            userTextField.setText("");
            passwordField.setText("");
          }
          else{
            JOptionPane.showMessageDialog( null, "Successful Login","Successful Login", JOptionPane.INFORMATION_MESSAGE );
            setClosed( true ); 
          }
        }
        catch(Exception e){
          e.printStackTrace();
        }
      }
      else if( event.getSource() == cancelButton ){
        setVisible(false);
      }     
    } 
  }   
} 


