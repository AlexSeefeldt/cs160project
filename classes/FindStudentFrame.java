import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JButton; 
import javax.swing.JTextField; 
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.util.Scanner; 
import java.awt.GridLayout; 
import javax.swing.BoxLayout;
import java.io.File; 
import java.io.FileNotFoundException;
import java.util.Arrays; 

public class FindStudentFrame extends JInternalFrame  
{ 
  private JLabel studentName, studentMajor, studentDegree; 
  private JTextField ssnTextField;
  private JButton enterButton, cancelButton;
  private SRSContainer srsCon;
  private JPanel panel1, panel2, panel3;
  private JTextArea scheduleArea;
  
  public FindStudentFrame(SRSContainer srsCon) 
  { 
    super("Find Student", true, true, true, true );
    this.srsCon = srsCon;
    setLayout( new GridLayout(3,1) );
    panel1 = new JPanel(new GridLayout(2,2));
    panel2 = new JPanel(new GridLayout(3,2));
    panel3 = new JPanel(new GridLayout(1,1));
    ssnTextField = new JTextField( 10 );
    enterButton = new JButton("Enter");
    cancelButton = new JButton("Cancel");
    panel1.add(new JLabel("SSN: "));
    panel1.add(ssnTextField);
    panel1.add(enterButton);
    panel1.add(cancelButton);
    studentName = new JLabel();
    studentMajor = new JLabel();
    studentDegree = new JLabel();
    panel2.add(new JLabel("Name: "));
    panel2.add(studentName);
    panel2.add(new JLabel("Major: "));
    panel2.add(studentMajor);
    panel2.add(new JLabel("Degree: "));
    panel2.add(studentDegree);
    scheduleArea = new JTextArea(10, 30);
    scheduleArea.setEditable(false);
    scheduleArea.setText("This Student's Classes:"+"\n");
    panel3.add(scheduleArea);
    getContentPane().add(panel1);
    getContentPane().add(panel2);
    getContentPane().add(panel3);
    FindStudentHandler handler = new FindStudentHandler();
    enterButton.addActionListener(handler);
    cancelButton.addActionListener(handler);
  }  
  
  private class FindStudentHandler implements ActionListener  
  { 
    // process textfield events 
    public void actionPerformed( ActionEvent event ) 
    {
      if ( event.getSource() == enterButton ) 
      {
        studentName.setText("");
        studentMajor.setText("");
        studentDegree.setText("");
        scheduleArea.setText("This Student's Classes:"+"\n");
        Student studentGotten = null;
        try
        {
          studentGotten = srsCon.getSRSDataAccess().initializeStudent(ssnTextField.getText());
        }
        catch(FileNotFoundException e)
        {
          JOptionPane.showMessageDialog( null, "The SSN you provided does not correspond to a student in our system.", "No Such Student", 
                                         JOptionPane.ERROR_MESSAGE );
          ssnTextField.setText("");
        }
        catch(UninitializedScheduleOfClassesException e)
        { System.out.println("UninitializedScheduleOfClassesException"); }
        if (studentGotten != null)
        {
          studentName.setText(studentGotten.getName());
          studentMajor.setText(studentGotten.getMajor());
          studentDegree.setText(studentGotten.getDegree());
          for (Section s : studentGotten.getEnrolledSections())
          {
            scheduleArea.append(s.getRepresentedCourse().getCourseNo()+" "+s.getRepresentedCourse().getCourseName()+": "+s.getDayOfWeek()+" "+s.getTimeOfDay()+"\n");
          }
        }

      } 
      else if( event.getSource() == cancelButton ) 
      {
        studentName.setText("");
        studentMajor.setText("");
        studentDegree.setText("");
        scheduleArea.setText("This Student's Classes:"+"\n");
        ssnTextField.setText("");
        setVisible(false);
      } 
    }  
  }
}  

