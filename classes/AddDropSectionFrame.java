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


public class AddDropSectionFrame extends JInternalFrame  
{ 
  private JButton addButton, dropButton, viewCourseButton, cancelButton;
  private SRSContainer srsCon;
  private JPanel panel1, panel2, panel3, panel4;
  private JList<Section> inArea, outArea;
  ArrayList<Section> studentList = new ArrayList<Section>(), classList;
  
  public AddDropSectionFrame(SRSContainer srsCon) 
  { 
    super("Add/Drop a Course", true, true, true, true);
    this.srsCon = srsCon;
    setLayout(new BorderLayout());
    panel1 = new JPanel(new GridLayout(1,2));
    panel2 = new JPanel(new GridLayout(1,1));
    panel3 = new JPanel(new GridLayout(1,1));
    panel4 = new JPanel(new GridLayout(1,4));
    if (srsCon.getMainFrame().getLoggedIn() != null)
    {
      studentList = srsCon.getMainFrame().getLoggedIn().getEnrolledSections();
      Section[] studentArray = studentList.toArray(new Section[studentList.size()]);
      inArea = new JList<Section>(studentArray);
    }
    else
      inArea = new JList<Section>();
    inArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    panel2.add(new JScrollPane(inArea));
    classList = srsCon.getScheduleOfClasses().getSectionsOffered();
    classList.removeAll(studentList);
    Section[] classArray = classList.toArray(new Section[studentList.size()]);
    outArea = new JList<Section>(classArray);
    outArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    panel3.add(new JScrollPane(outArea));
    panel1.add(panel2);
    panel1.add(panel3);
    addButton = new JButton("Add");
    dropButton = new JButton("Drop");
    viewCourseButton = new JButton("View");
    cancelButton = new JButton("Cancel");
    panel4.add(addButton);
    panel4.add(dropButton);
    panel4.add(viewCourseButton);
    panel4.add(cancelButton);
    getContentPane().add(panel4,BorderLayout.SOUTH);
    getContentPane().add(panel1,BorderLayout.CENTER);
    AddDropSectionHandler handler = new AddDropSectionHandler();
    addButton.addActionListener(handler);
    dropButton.addActionListener(handler);
    viewCourseButton.addActionListener(handler);
    cancelButton.addActionListener(handler);
    ListFocusHandler listHandler = new ListFocusHandler(inArea,outArea);
    inArea.addFocusListener(listHandler);
    outArea.addFocusListener(listHandler);
  } 

  private void updateLists()
  {
    Section[] studentArray = studentList.toArray(new Section[studentList.size()]);
    inArea.setListData(studentArray);
    Section[] classArray = classList.toArray(new Section[studentList.size()]);
    outArea.setListData(classArray);
  }

  private void updateLists(int index)
  {
    Section[] studentArray = studentList.toArray(new Section[studentList.size()]);
    inArea.setListData(studentArray);
    Section[] classArray = classList.toArray(new Section[studentList.size()]);
    outArea.setListData(classArray);
    if (index > -1)
      outArea.setSelectedIndex(index);
    else
      inArea.setSelectedIndex(inArea.getModel().getSize()-1);
  }

  public void refresh()
  {
    studentList = srsCon.getMainFrame().getLoggedIn().getEnrolledSections();
    classList = srsCon.getScheduleOfClasses().getSectionsOffered();
    classList.removeAll(studentList);
    updateLists();
  }
  
  private class AddDropSectionHandler implements ActionListener 
  {
    public void actionPerformed( ActionEvent event ) 
    {
      if ( event.getSource() == viewCourseButton ) 
      {
        Section viewSection = null;
        if (!inArea.isSelectionEmpty())
          viewSection = inArea.getSelectedValue();
        else if (!outArea.isSelectionEmpty())
          viewSection = outArea.getSelectedValue();
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
        if (!inArea.isSelectionEmpty())
          JOptionPane.showMessageDialog(null, "Please select a Section from the right-side list to add it to your schedule.",
                                        "Wrong List", JOptionPane.ERROR_MESSAGE);
        else if (!outArea.isSelectionEmpty())
          addSection = outArea.getSelectedValue();
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
        if (!inArea.isSelectionEmpty())
          dropSection = inArea.getSelectedValue();
        else if (!outArea.isSelectionEmpty())
          JOptionPane.showMessageDialog(null, "Please select a Section from the left-side list to drop it from your schedule.",
                                        "Wrong List", JOptionPane.ERROR_MESSAGE);
        if (dropSection != null)
        {   
        srsCon.getMainFrame().getLoggedIn().getEnrolledSections().remove(dropSection);
        dropSection.dropStudent(srsCon.getMainFrame().getLoggedIn());
        int i = 0;
        while(dropSection.toString().compareTo(classList.get(i).toString()) > 0)
          i++;
        classList.add(i, dropSection);
        updateLists(i);
        }
      }
      else if(event.getSource() == cancelButton) 
      {
        inArea.clearSelection();
        outArea.clearSelection();
        setVisible(false);
      } 
    }
  }
}  

