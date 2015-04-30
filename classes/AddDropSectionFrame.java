import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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
  ArrayList<Section> studentList, classList;
  
  public AddDropSectionFrame(SRSContainer srsCon) 
  { 
    super("Add/Drop a Course", true, true, true, true);
    this.srsCon = srsCon;
    setLayout(new BorderLayout());
    panel1 = new JPanel(new GridLayout(1,2));
    panel2 = new JPanel(new GridLayout(1,1));
    panel3 = new JPanel(new GridLayout(1,1));
    panel4 = new JPanel(new GridLayout(1,4));
    studentList = srsCon.getMainFrame().getLoggedIn().getEnrolledSections();
    Section[] studentArray = studentList.toArray(new Section[studentList.size()]);
    inArea = new JList<Section>(studentArray);
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
  } 

  public void updateLists()
  {
    Section[] studentArray = studentList.toArray(new Section[studentList.size()]);
    inArea.setListData(studentArray);
    Section[] classArray = classList.toArray(new Section[studentList.size()]);
    outArea.setListData(classArray);
  }
  
  private class AddDropSectionHandler implements ActionListener  
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

