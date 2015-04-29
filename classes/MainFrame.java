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
import javax.swing.JInternalFrame;

public class MainFrame extends JFrame
{
    private JDesktopPane mainPane;
    private JMenuBar bar;
    private JMenu fileMenu, studentMenu, professorMenu;
    private JMenuItem fileFrameItem1, fileFrameItem2, fileFrameItem3,
                      studentFrameItem1, studentFrameItem2, studentFrameItem3,
                      studentFrameItem4, studentFrameItem5,
                      professorFrame1, professorFrame2, professorFrame3, professorFrame4;
    private JInternalFrame loginFrame, findStudentFrame;
    private ActionListener listener;
    private ArrayList<JInternalFrame> frameList = new ArrayList<JInternalFrame>();
    private Faculty faculty;
    private CourseCatalog courseCatalog ;
    private ScheduleOfClasses scheduleOfClasses;
    private Person loggedIn = null;
    private SRSDataAccess srsDataAccess;

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
        professorFrame1 =   new JMenuItem("Find Professor");
        professorFrame2 =   new JMenuItem("Displaying Teaching Assignments");
        professorFrame3 =   new JMenuItem("Display Student Roster");
        professorFrame4 =   new JMenuItem("Agree to teach a course");                                        
        fileMenu.add(fileFrameItem1);
        fileMenu.add(fileFrameItem2);
        fileMenu.add(fileFrameItem3);
        studentMenu.add(studentFrameItem1);
        studentMenu.add(studentFrameItem2);
        studentMenu.add(studentFrameItem3);
        studentMenu.add(studentFrameItem4);
        studentMenu.add(studentFrameItem5);
        professorMenu.add(professorFrame1);
        professorMenu.add(professorFrame2);
        professorMenu.add(professorFrame3);
        professorMenu.add(professorFrame4);
        bar.add(fileMenu ); // add Add menu to menu bar
        setJMenuBar( bar ); // set menu bar for this application
        bar.add(studentMenu);
        setJMenuBar(bar);
        bar.add(professorMenu);
        setJMenuBar(bar);
        mainPane = new JDesktopPane(); // create desktop pane
        add(mainPane); // add desktop pane to frame
        loginFrame = new LoginFrame();
        frameList.add(loginFrame);
        findStudentFrame = new FindStudentFrame();
        frameList.add(findStudentFrame);
        for (JInternalFrame jIF : frameList)
        {
            jIF.setSize(200,200);
            mainPane.add(jIF);
            jIF.setVisible(false);
            jIF.setDefaultCloseOperation(HIDE_ON_CLOSE);
        }
        // set up listener for newFrame menu item
        listener = new FrameListener();
        fileFrameItem1.addActionListener(listener);
        studentFrameItem1.addActionListener(listener);
    }

    class FrameListener implements ActionListener
    {  
        public void actionPerformed(ActionEvent event)
        {
            Object sourceItem = event.getSource();
            if(sourceItem.equals(fileFrameItem1))
            {
                if (loggedIn == null)
                    loginFrame.setVisible(true);
            }
            else if(sourceItem.equals(studentFrameItem1))
                findStudentFrame.setVisible(true);
            //JInternalFrame frame = new FindStudent( "Find/Display Student", true, true, true, true ); 
            //frame.pack();
            //mainPane.add( frame ); // attach internal frame
            //frame.setVisible( true ); // show internal frame
            //frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
    }

    public void setLoggedIn(Person loggedIn)
    {
        this.loggedIn = loggedIn;
    }

    public static void main( String[] args )
    {
        MainFrame mainPane = new MainFrame();  
        mainPane.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPane.setSize( 600, 480 ); // set frame size
        mainPane.setVisible( true ); // display frame
    }
}