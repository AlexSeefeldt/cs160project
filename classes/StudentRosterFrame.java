import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
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
  private JList<Section> sectionArea;
  private JList<Student> studentArea;
  ArrayList<Student> studentList;
  Student[] studentArray;
  
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
    studentList = sectionArea.getSelectedValue().getEnrolledStudents();
    Student[] studentArray = studentList.toArray(new Student[studentList.size()]);
    studentArea = new JList<Student>(studentArray);
    studentArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    panel1.add(new JScrollPane(studentArea));
    getContentPane().add(panel1);
    SectionListHandler handler = new SectionListHandler();
    sectionArea.addListSelectionListener(handler);
    ListFocusHandler listHandler = new ListFocusHandler(sectionArea,studentArea);
    sectionArea.addFocusListener(listHandler);
    studentArea.addFocusListener(listHandler);
  }
  
  public class SectionListHandler implements ListSelectionListener
  {
    public void valueChanged(ListSelectionEvent event)
    {
      studentList = sectionArea.getSelectedValue().getEnrolledStudents();
      for (Student s : studentList)
      {
        System.out.println(s);
      }
      studentArray = studentList.toArray(new Student[studentList.size()]);
      studentArea = new JList<Student>(studentArray);
    }
  }
}  

