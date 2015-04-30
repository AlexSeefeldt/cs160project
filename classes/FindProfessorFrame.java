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

public class FindProfessorFrame extends JInternalFrame  
{ 
  private JLabel professorName, professorTitle, professorDepartment; 
  private JTextField ssnTextField;
  private JButton enterButton;
  private JButton cancelButton;
  private SRSContainer srsCon;
  private JPanel panel1, panel2, panel3;
  private JTextArea scheduleArea;
  
  public FindProfessorFrame(SRSContainer srsCon) 
  { 
    super("Find Professor", true, true, true, true );
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
    professorName = new JLabel();
    professorTitle = new JLabel();
    professorDepartment = new JLabel();
    panel2.add(new JLabel("Name: "));
    panel2.add(professorName);
    panel2.add(new JLabel("Title: "));
    panel2.add(professorTitle);
    panel2.add(new JLabel("Department: "));
    panel2.add(professorDepartment);
    scheduleArea = new JTextArea(10, 30);
    scheduleArea.setEditable(false);
    scheduleArea.setText("This Professor's Classes:"+"\n");
    panel3.add(scheduleArea);
    getContentPane().add(panel1);
    getContentPane().add(panel2);
    getContentPane().add(panel3);
    FindProfessorHandler handler = new FindProfessorHandler();
    enterButton.addActionListener(handler);
    cancelButton.addActionListener(handler);
  }  
  
  private class FindProfessorHandler implements ActionListener  
  { 
    // process textfield events 
    public void actionPerformed( ActionEvent event ) 
    {
      if ( event.getSource() == enterButton ) 
      {
        professorName.setText("");
        professorTitle.setText("");
        professorDepartment.setText("");
        scheduleArea.setText("This Professor's Classes:"+"\n");
        Professor professorGotten = srsCon.getFaculty().findProfessor(ssnTextField.getText());
        if (professorGotten != null)
        {
          professorName.setText(professorGotten.getName());
          professorTitle.setText(professorGotten.getTitle());
          professorDepartment.setText(professorGotten.getDepartment());
          for (Section s : professorGotten.getTeachingAssignments())
          {
            scheduleArea.append(s.getRepresentedCourse().getCourseNo()+" "+s.getRepresentedCourse().getCourseName()+": "+s.getDayOfWeek()+" "+s.getTimeOfDay()+"\n");
          }
        }
        else
        {
          JOptionPane.showMessageDialog( null, "The SSN you provided does not correspond to a professor in our system.", "No Such Professor", 
                                         JOptionPane.ERROR_MESSAGE );
          ssnTextField.setText("");
        }

      } 
      else if( event.getSource() == cancelButton ) 
      {
        professorName.setText("");
        professorTitle.setText("");
        professorDepartment.setText("");
        scheduleArea.setText("This Professor's Classes:"+"\n");
        ssnTextField.setText("");
        setVisible(false);
      } 
    }  
  }
}  

