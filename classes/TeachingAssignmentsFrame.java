import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import javax.swing.JFrame; 
import javax.swing.JList; 
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Scanner; 
import java.awt.BorderLayout; 
import java.awt.GridLayout;
import java.io.File; 
import java.io.FileNotFoundException;
import java.util.ArrayList; 

public class TeachingAssignmentsFrame extends JInternalFrame  
{ 
  private JList<Professor> profArea;
  private JButton viewButton, cancelButton;
  private SRSContainer srsCon;
  private JPanel panel1, panel2;
  
  public TeachingAssignmentsFrame(SRSContainer srsCon) 
  { 
    super("Display Teaching Assignments", true, true, true, true);
    this.srsCon = srsCon;
    setLayout(new BorderLayout());
    panel1 = new JPanel(new GridLayout(1,1));
    panel2 = new JPanel(new GridLayout(1,2));
    ArrayList<Professor> profList = srsCon.getFaculty().getProfessors();
    Professor[] profArray = profList.toArray(new Professor[profList.size()]);
    profArea = new JList<Professor>(profArray);
    panel1.add(new JScrollPane(profArea));
    viewButton = new JButton("View");
    cancelButton = new JButton("Cancel");
    panel2.add(viewButton);
    panel2.add(cancelButton);
    getContentPane().add(panel1,BorderLayout.CENTER);
    getContentPane().add(panel2,BorderLayout.SOUTH);
    TeachingAssignmentsHandler handler = new TeachingAssignmentsHandler();
    viewButton.addActionListener(handler);
    cancelButton.addActionListener(handler);
  }
  
  private class TeachingAssignmentsHandler implements ActionListener  
  {
    public void actionPerformed( ActionEvent event ) 
    {
      if ( event.getSource() == viewButton ) 
      {
        StringBuilder displayString = new StringBuilder("Here are the Sections this professor teaches:");
        for (Section s : profArea.getSelectedValue().getTeachingAssignments())
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
        JOptionPane.showMessageDialog(null, ""+displayString, "Display Teaching Assignments", JOptionPane.INFORMATION_MESSAGE);
      } 
      else if(event.getSource() == cancelButton) 
      {
        setVisible(false);
        profArea.clearSelection();
      } 
    }  
  }
}  

