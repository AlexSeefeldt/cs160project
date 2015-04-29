import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JInternalFrame;

public class MainFrame extends JFrame
{
    private JDesktopPane theMainFrame;
  
    public MainFrame()
    {
    super( "Using a JDesktopPane" );
    JMenuBar bar = new JMenuBar(); // create menu bar
    JMenu fileMenu = new JMenu( "File" ); // create Add menu
    JMenu studentMenu = new JMenu("Student");
    JMenu professorMenu = new JMenu("Professor");
    JMenuItem fileFrameItem = new JMenuItem( "Login" );
    JMenuItem fileFrameItem2 = new JMenuItem("LogOff");
    JMenuItem fileFrameItem3 = new JMenuItem("Close"); 
    JMenuItem studentFrameItem = new JMenuItem("Display/Find Student");
    JMenuItem studentFrameItem2 = new JMenuItem("Display Course Schedule");
    JMenuItem studentFrameItem3 = new JMenuItem("Add Section");
    JMenuItem studentFrameItem4 = new JMenuItem("Drop Section");
    JMenuItem studentFrameItem5 = new JMenuItem("View Transcript");
    JMenuItem professorFrame = new JMenuItem("Display/Find Professor");
    JMenuItem professorFrame2 = new JMenuItem("Displaying Teaching Assignments");
    JMenuItem professorFrame3 = new JMenuItem("Display Student Roster");
    JMenuItem professorFrame4 = new JMenuItem("Agree to teach a course");                                        
    fileMenu.add(fileFrameItem); // add new frame item to Add menu
    fileMenu.add(fileFrameItem2);
    fileMenu.add(fileFrameItem3); 
    studentMenu.add(studentFrameItem);
    studentMenu.add(studentFrameItem2);
    studentMenu.add(studentFrameItem3);
    studentMenu.add(studentFrameItem4);
    studentMenu.add(studentFrameItem5);
    professorMenu.add(professorFrame);
    professorMenu.add(professorFrame2); 
    professorMenu.add(professorFrame3);
    professorMenu.add(professorFrame4);
    bar.add(fileMenu ); // add Add menu to menu bar
    setJMenuBar( bar ); // set menu bar for this application
    bar.add(studentMenu);
    setJMenuBar(bar);
    bar.add(professorMenu);
    setJMenuBar(bar);
    theMainFrame = new JDesktopPane(); // create desktop pane
    add( theMainFrame ); // add desktop pane to frame
    
    // set up listener for newFrame menu item
    ActionListener listener = new FrameListener();
    studentFrameItem.addActionListener(listener);
    }

    class FrameListener implements ActionListener
    {  
        public void actionPerformed(ActionEvent event)
        {  
            JInternalFrame frame = new FindStudent( "Find/Display Student", true, true, true, true ); 
            frame.pack();
            theMainFrame.add( frame ); // attach internal frame
            frame.setVisible( true ); // show internal frame
            frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);}
    }
    public static void main( String[] args )
    {
        MainFrame theMainFrame = new MainFrame();  
        theMainFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        theMainFrame.setSize( 600, 480 ); // set frame size
        theMainFrame.setVisible( true ); // display frame
    }
}