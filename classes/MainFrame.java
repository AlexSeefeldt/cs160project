import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.MenuElement;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import java.io.FileNotFoundException;

public class MainFrame extends JFrame
{
    private JDesktopPane mainPane;
    private JMenuBar bar;
    private JMenu fileMenu, studentMenu, professorMenu;
    private JMenuItem fileFrameItem1, fileFrameItem2, fileFrameItem3,
                      studentFrameItem1, studentFrameItem2, studentFrameItem3,
                      studentFrameItem4, studentFrameItem5,
                      professorFrameItem1, professorFrameItem2, professorFrameItem3, professorFrameItem4;
    private JInternalFrame loginFrame, findStudentFrame, findProfessorFrame;
    private ActionListener listener;
    private ArrayList<JInternalFrame> frameList = new ArrayList<JInternalFrame>();
    private Student loggedIn = null;
    private SRSContainer srsCon = new SRSContainer(this);

    public MainFrame()
    {
        bar =               new JMenuBar();
        fileMenu =          new JMenu("File");
        studentMenu =       new JMenu("Student");
        professorMenu =     new JMenu("Professor");
        fileFrameItem1 =    new JMenuItem("Login");
        fileFrameItem2 =    new JMenuItem("Logoff");
        fileFrameItem3 =    new JMenuItem("Close"); 
        studentFrameItem1 = new JMenuItem("Find Student");
        studentFrameItem2 = new JMenuItem("Display Course Schedule");
        studentFrameItem3 = new JMenuItem("Add Section");
        studentFrameItem4 = new JMenuItem("Drop Section");
        studentFrameItem5 = new JMenuItem("View Transcript");
        professorFrameItem1 =   new JMenuItem("Find Professor");
        professorFrameItem2 =   new JMenuItem("Displaying Teaching Assignments");
        professorFrameItem3 =   new JMenuItem("Display Student Roster");
        professorFrameItem4 =   new JMenuItem("Agree to teach a course");                                        
        fileMenu.add(fileFrameItem1);
        fileMenu.add(fileFrameItem2);
        fileMenu.add(fileFrameItem3);
        studentMenu.add(studentFrameItem1);
        studentMenu.add(studentFrameItem2);
        studentMenu.add(studentFrameItem3);
        studentMenu.add(studentFrameItem4);
        studentMenu.add(studentFrameItem5);
        professorMenu.add(professorFrameItem1);
        professorMenu.add(professorFrameItem2);
        professorMenu.add(professorFrameItem3);
        professorMenu.add(professorFrameItem4);
        bar.add(fileMenu); // add Add menu to menu bar
        setJMenuBar(bar); // set menu bar for this application
        bar.add(studentMenu);
        setJMenuBar(bar);
        bar.add(professorMenu);
        setJMenuBar(bar);
        mainPane = new JDesktopPane(); // create desktop pane
        add(mainPane); // add desktop pane to frame
        loginFrame = new LoginFrame(srsCon);
        frameList.add(loginFrame);
        findStudentFrame = new FindStudentFrame(srsCon);
        frameList.add(findStudentFrame);
        findProfessorFrame = new FindProfessorFrame(srsCon);
        frameList.add(findProfessorFrame);
        for (JInternalFrame jIF : frameList)
        {
            jIF.setSize(300,300);
            mainPane.add(jIF);
            jIF.setVisible(false);
            jIF.setDefaultCloseOperation(HIDE_ON_CLOSE);
        }
        listener = new FrameListener();
        for (MenuElement menu : bar.getSubElements())
        {
            for (MenuElement popUp : menu.getSubElements())
            {
                for (MenuElement menuElement : popUp.getSubElements())
                {
                    JMenuItem menuItem = (JMenuItem)menuElement;
                    menuItem.addActionListener(listener);
                }
            }
        }
    }

    class FrameListener implements ActionListener
    {  
        public void actionPerformed(ActionEvent event)
        {
            Object sourceItem = event.getSource();
            if (loggedIn != null)
            {
                if (sourceItem.equals(studentFrameItem1))
                    findStudentFrame.setVisible(true);
                else if (sourceItem.equals(professorFrameItem1))
                    findProfessorFrame.setVisible(true);

                else if (sourceItem.equals(fileFrameItem2))
                {
                    logOff();
                }
            }
            else if(sourceItem.equals(fileFrameItem1))
                loginFrame.setVisible(true);
            else
            {
                JOptionPane.showMessageDialog(null, "Log in before attempting a function", "Not Logged In", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void setLoggedIn(Student loggedIn)
    {
        this.loggedIn = loggedIn;
    }

    public void logOff()
    {
        int option = JOptionPane.showOptionDialog(null, "Are you sure you want to log off?","Logoff", 
                                                  JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
        if (option == JOptionPane.YES_OPTION)
        {
            try
            {
                srsCon.getSRSDataAccess().persistStudent(loggedIn);
                loggedIn = null;
                JOptionPane.showMessageDialog(null, "Logoff Successful","Logoff", JOptionPane.INFORMATION_MESSAGE);
            }
            catch(FileNotFoundException e)
            {
                JOptionPane.showMessageDialog(null, "There was an error logging off.","Failed Logoff", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main( String[] args )
    {
        MainFrame mainPane = new MainFrame();  
        mainPane.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPane.setSize( 600, 480 ); // set frame size
        mainPane.setVisible( true ); // display frame
    }
}