import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
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
                      /* studentFrameItem4, */ professorFrameItem1, professorFrameItem2;
    private JInternalFrame loginFrame, findStudentFrame, findProfessorFrame,
                           addDropSectionFrame, teachingAssignmentsFrame;
                           //studentRosterFrame;
    private ActionListener listener;
    private ArrayList<JInternalFrame> frameList = new ArrayList<JInternalFrame>();
    private Student loggedIn = null;
    private SRSContainer srsCon = new SRSContainer(this);

    public MainFrame()
    {
        bar =                 new JMenuBar();
        fileMenu =            new JMenu("File");
        studentMenu =         new JMenu("Student");
        professorMenu =       new JMenu("Professor");
        fileFrameItem1 =      new JMenuItem("Login");
        fileFrameItem2 =      new JMenuItem("Logoff");
        fileFrameItem3 =      new JMenuItem("Close"); 
        studentFrameItem1 =   new JMenuItem("Find Student");
        studentFrameItem2 =   new JMenuItem("Display Course Schedule");
        studentFrameItem3 =   new JMenuItem("Add/Drop Section");
        //studentFrameItem4 =   new JMenuItem("Display Student Roster");
        professorFrameItem1 = new JMenuItem("Find Professor");
        professorFrameItem2 = new JMenuItem("Display Teaching Assignments");                                   
        fileMenu.add(fileFrameItem1);
        fileMenu.add(fileFrameItem2);
        fileMenu.add(fileFrameItem3);
        studentMenu.add(studentFrameItem1);
        studentMenu.add(studentFrameItem2);
        studentMenu.add(studentFrameItem3);
        //studentMenu.add(studentFrameItem4);
        professorMenu.add(professorFrameItem1);
        professorMenu.add(professorFrameItem2);
        bar.add(fileMenu);
        setJMenuBar(bar);
        bar.add(studentMenu);
        setJMenuBar(bar);
        bar.add(professorMenu);
        setJMenuBar(bar);
        // LOGIN CHEAT
        //try{ loggedIn = srsCon.getSRSDataAccess().initializeStudent("995-64-7152"); }
        //catch (FileNotFoundException e){}
        mainPane = new JDesktopPane(); // create desktop pane
        add(mainPane); // add desktop pane to frame
        loginFrame = new LoginFrame(srsCon);
        frameList.add(loginFrame);
        findStudentFrame = new FindStudentFrame(srsCon);
        frameList.add(findStudentFrame);
        findProfessorFrame = new FindProfessorFrame(srsCon);
        frameList.add(findProfessorFrame);
        addDropSectionFrame = new AddDropSectionFrame(srsCon);
        frameList.add(addDropSectionFrame);
        teachingAssignmentsFrame = new TeachingAssignmentsFrame(srsCon);
        frameList.add(teachingAssignmentsFrame);
        //studentRosterFrame = new StudentRosterFrame(srsCon);
        //frameList.add(studentRosterFrame);
        for (JInternalFrame jIF : frameList)
        {
            jIF.setSize(325,300);
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
                else if (sourceItem.equals(studentFrameItem2))
                {
                    StringBuilder displayString = new StringBuilder("Here is your current schedule:");
                    for (Section s : loggedIn.getEnrolledSections())
                    {
                      displayString.append("\n");
                      displayString.append(s.getRepresentedCourse().getCourseNo()+" ");
                      displayString.append(s.getRepresentedCourse().getCourseName()+", ");
                      displayString.append("Section "+s.getSectionNo()+": ");
                      switch (s.getDayOfWeek().charAt(0))
                      {
                        case 'M': displayString.append("Monday "+s.getTimeOfDay());
                                  break;
                        case 'T': displayString.append("Tuesday "+s.getTimeOfDay());
                                  break;
                        case 'W': displayString.append("Wednesday "+s.getTimeOfDay());
                                  break;
                        case 'R': displayString.append("Thursday "+s.getTimeOfDay());
                                  break;
                        case 'F': displayString.append("Friday "+s.getTimeOfDay());
                                  break;
                        default:  displayString.append(s.getTimeOfDay());
                                  break;
                      }
                    }
                    JOptionPane.showMessageDialog(null, ""+displayString, "Display Schedule", JOptionPane.INFORMATION_MESSAGE);
                }
                else if (sourceItem.equals(studentFrameItem3))
                    addDropSectionFrame.setVisible(true);
                //else if (sourceItem.equals(studentFrameItem4))
                //    studentRosterFrame.setVisible(true);
                else if (sourceItem.equals(professorFrameItem1))
                    findProfessorFrame.setVisible(true);
                else if (sourceItem.equals(professorFrameItem2))
                    teachingAssignmentsFrame.setVisible(true);
                else if (sourceItem.equals(fileFrameItem2))
                {
                    int option = JOptionPane.showOptionDialog(null, "Are you sure you want to log off?","Logoff", 
                                                              JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
                    if (option == JOptionPane.YES_OPTION)
                        logOff();
                }
            }
            else if(sourceItem.equals(fileFrameItem1))
                loginFrame.setVisible(true);
            else if (sourceItem.equals(fileFrameItem3))
                {
                    if (loggedIn != null)
                    {
                    int option = JOptionPane.showOptionDialog(null, "Would you like to log off before exiting?","Logoff", 
                                                              JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
                    if (option == JOptionPane.YES_OPTION)
                        logOff();
                    }
                    dispose();
                }
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

    public Student getLoggedIn()
    {
        return this.loggedIn;
    }

    public void logOff()
    {
            try
            {
                srsCon.getSRSDataAccess().persistStudent(loggedIn);
                loggedIn = null;
                for (JInternalFrame jIF : frameList)
                {
                    jIF.setVisible(false);
                }
                JOptionPane.showMessageDialog(null, "Logoff Successful","Logoff", JOptionPane.INFORMATION_MESSAGE);
            }
            catch(FileNotFoundException e)
            {
                JOptionPane.showMessageDialog(null, "There was an error logging off.","Failed Logoff", JOptionPane.ERROR_MESSAGE);
            }
    }

    public void refreshPersonalFrames()
    {
        ((AddDropSectionFrame)addDropSectionFrame).refresh();
    }

    public static void main( String[] args )
    {
        MainFrame mainPane = new MainFrame();  
        mainPane.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPane.setSize( 600, 480 ); // set frame size
        mainPane.setVisible( true ); // display frame
    }
}