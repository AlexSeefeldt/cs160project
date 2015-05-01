import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import javax.swing.JList;

public class ListFocusHandler implements FocusListener
{
  private JList leftArea, rightArea;

  public ListFocusHandler(JList leftArea, JList rightArea)
  {
    this.leftArea = leftArea;
    this.rightArea = rightArea;
  }

  public void focusLost(FocusEvent event) {}

  public void focusGained(FocusEvent event)
  {
    if (event.getSource() == leftArea)
      rightArea.clearSelection();
    else if (event.getSource() == rightArea)
      leftArea.clearSelection();
  }
}