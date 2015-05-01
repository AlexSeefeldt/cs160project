import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import java.util.Scanner;
import java.util.ArrayList;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.io.File;
import java.io.FileNotFoundException;


public class StudentRosterFrame extends JInternalFrame  
{
  private SRSContainer srsCon;
  private JPanel panel1;
  private JList<Section> sectionArea, studentArea;
  
  public StudentRosterFrame(SRSContainer srsCon) 
  { 
    super("Add/Drop a Course", true, true, true, true);
    this.srsCon = srsCon;
    panel1 = new JPanel(new GridLayout(1,2));
    ArrayList<Section> sectionList = srsCon.getScheduleOfClasses().getSectionsOffered();
    Section[] sectionArray = sectionList.toArray(new Section[sectionList.size()]);
    sectionArea = new JList<Section>(sectionArray);
    sectionArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    panel1.add(new JScrollPane(sectionArea));
    sectionArea.setSelectedIndex(0);
    studentList = sectionArea.getSelectedValue().;
    Section[] studentArray = studentList.toArray(new Section[studentList.size()]);
    studentArea = new JList<Section>(studentArray);
    studentArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    panel1.add(new JScrollPane(studentArea));
    getContentPane().add(panel1);
    StudentRosterHandler handler = new StudentRosterHandler();
    addButton.addActionListener(handler);
    dropButton.addActionListener(handler);
    viewCourseButton.addActionListener(handler);
    cancelButton.addActionListener(handler);
    ListFocusHandler listHandler = new ListFocusHandler();
    sectionArea.addFocusListener(listHandler);
    studentArea.addFocusListener(listHandler);
  }
  
  private class StudentRosterHandler implements ActionListener  
  {
    public void actionPerformed( ActionEvent event ) 
    {
      if ( event.getSource() == viewCourseButton ) 
      {
        Section viewSection = null;
        if (!sectionArea.isSelectionEmpty())
          viewSection = sectionArea.getSelectedValue();
        else if (!studentArea.isSelectionEmpty())
          viewSection = studentArea.getSelectedValue();
        StringBuilder displayString = new StringBuilder(viewSection+"\n");
        displayString.append(viewSection.getRepresentedCourse().getCourseName()+"\n");
        displayString.append("Taught by "+viewSection.getInstructor().getName()+"\n");
        switch (viewSection.getDayOfWeek().charAt(0))
        {
          case 'M': displayString.append("Monday "+viewSection.getTimeOfDay()+"\n");
                    break;
          case 'T': displayString.append("Tuesday "+viewSection.getTimeOfDay()+"\n");
                    break;
          case 'W': displayString.append("Wednesday "+viewSection.getTimeOfDay()+"\n");
                    break;
          case 'R': displayString.append("Thursday "+viewSection.getTimeOfDay()+"\n");
                    break;
          case 'F': displayString.append("Friday "+viewSection.getTimeOfDay()+"\n");
                    break;
          default:  displayString.append(viewSection.getTimeOfDay()+"\n");
                    break;
        }
        displayString.append(viewSection.getRoom()+": "+(viewSection.getSeatingCapacity()-viewSection.getTotalEnrollment())+
                             "/"+viewSection.getSeatingCapacity()+" seats available");
        JOptionPane.showMessageDialog(null, ""+displayString, ""+viewSection, JOptionPane.INFORMATION_MESSAGE);
      }
      else if(event.getSource() == addButton)
      {
        Section addSection = null;
        if (!sectionArea.isSelectionEmpty())
          JOptionPane.showMessageDialog(null, "Please select a Section from the right-side list to add it to your schedule.",
                                        "Wrong List", JOptionPane.ERROR_MESSAGE);
        else if (!studentArea.isSelectionEmpty())
          addSection = studentArea.getSelectedValue();
        if (addSection != null)
        {
          EnrollmentStatus status = addSection.enroll(srsCon.getMainFrame().getLoggedIn());
          if (status == EnrollmentStatus.SUCCESS)
          {
            JOptionPane.showMessageDialog(null, status.getValue(), "Enrollment Successful", JOptionPane.INFORMATION_MESSAGE);
            classList.remove(addSection);
            updateLists(-1);
          }
          else
            JOptionPane.showMessageDialog(null, status.getValue(), "Enrollment Failed", JOptionPane.ERROR_MESSAGE);
        }
      }
      else if (event.getSource() == dropButton)
      {
        Section dropSection = null;
        if (!sectionArea.isSelectionEmpty())
          dropSection = sectionArea.getSelectedValue();
        else if (!studentArea.isSelectionEmpty())
          JOptionPane.showMessageDialog(null, "Please select a Section from the left-side list to drop it from your schedule.",
                                        "Wrong List", JOptionPane.ERROR_MESSAGE);
        srsCon.getMainFrame().getLoggedIn().getEnrolledSections().remove(dropSection);
        dropSection.dropStudent(srsCon.getMainFrame().getLoggedIn());
        int i = 0;
        while(dropSection.toString().compareTo(classList.get(i).toString()) > 0)
          i++;
        classList.add(i, dropSection);
        updateLists(i);
      }
      else if(event.getSource() == cancelButton) 
      {
        sectionArea.clearSelection();
        studentArea.clearSelection();
        setVisible(false);
      } 
    }

  }

  private class ListFocusHandler implements FocusListener
  {
    public void focusLost(FocusEvent event) {}

    public void focusGained(FocusEvent event)
    {
      if (event.getSource() == inArea)
        outArea.clearSelection();
      else if (event.getSource() == outArea)
        inArea.clearSelection();
    }
  }
}  

