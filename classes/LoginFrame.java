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

public class LoginFrame extends JInternalFrame 
{
  private JLabel userLabel; //user label
  private JTextField userTextField; // user text field
  private JLabel passwordLabel; //user label
  private JPasswordField passwordField; // password field with text
  private JButton loginButton; //login button
  private JButton cancelButton; //cancel button
  
  String userName;
  char[] password;

  public LoginFrame()
  {
    super("Login", true, true, true, true);
    setLayout( new GridLayout(3,2) );
    userLabel = new JLabel("Username: ");
    passwordLabel = new JLabel("Password: ");
    userTextField = new JTextField( 10 ); 
    passwordField = new JPasswordField( 10 ); 
    loginButton = new JButton("Login");
    cancelButton = new JButton("Cancel");
    getContentPane().add(userLabel);
    getContentPane().add(userTextField);
    getContentPane().add(passwordLabel);
    getContentPane().add(passwordField);
    getContentPane().add(loginButton);
    getContentPane().add(cancelButton);
    LoginHandler handler = new LoginHandler();
    loginButton.addActionListener( handler );
    cancelButton.addActionListener( handler );
  }

  private class LoginHandler implements ActionListener 
  {
     public void actionPerformed( ActionEvent event )
     {
      if ( event.getSource() == loginButton )
      {
      	userName = userTextField.getText();
		password = passwordField.getPassword();
		boolean confirmed = false;
		if (userName.equals(password)) {confirmed = true;}
		if(!confirmed)
		{
		  JOptionPane.showMessageDialog( null, "Unsuccessful Login","Failed Login", JOptionPane.ERROR_MESSAGE );
  		  userTextField.setText("");
  		  passwordField.setText("");
		}
		else
		{
		  JOptionPane.showMessageDialog( null, "Successful Login","Successful Login", JOptionPane.INFORMATION_MESSAGE );
		  userTextField.setText("");
  		  passwordField.setText("");
		  setVisible(false);
		}

//        userName = userTextField.getText();
//        password = passwordField.getPassword();
//        boolean confirmed = false;
//        try 
//        {
//          Scanner sc = new Scanner(new File("logins.txt"));
//          while(sc.hasNext() && !confirmed)
//          {
//            String[] entry = sc.next().split(",");
//            System.out.println("user: "+userName+ "  entry[0]: "+entry[0]);
//            System.out.println("password: "+password+ "  entry[1]: "+entry[1]);           
//             if( Arrays.equals (entry[0].toCharArray(), userName.toCharArray()) && Arrays.equals (entry[1].toCharArray(), password))
//             {
//              confirmed = true;
//              break;
//            }             
//          }
//          if(!confirmed)
//          {
//            JOptionPane.showMessageDialog( null, "Unsuccessful Login","Failed Login", JOptionPane.ERROR_MESSAGE );
//            userTextField.setText("");
//            passwordField.setText("");
//          }
//          else
//          {
//            JOptionPane.showMessageDialog( null, "Successful Login","Successful Login", JOptionPane.INFORMATION_MESSAGE );
//            setClosed( true ); 
//          }
//        }
//        catch(Exception e)
//        {
//          e.printStackTrace();
//        }
      }
      else if( event.getSource() == cancelButton )
      {
        setVisible(false);
      }     
    } 
  }   
}