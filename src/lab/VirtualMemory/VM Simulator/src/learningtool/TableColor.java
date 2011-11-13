/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package learningtool;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
/**
 *
 * @author kuldeep
 */
public class TableColor implements TableCellRenderer {
    public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();
    public static int r;
    public static Color C ;
    TableColor(int i,Color c) {
        r=i;//throw new UnsupportedOperationException("Not yet implemented");
        C=c;
        System.out.println("r="+r);
    }
         
  public Component getTableCellRendererComponent(JTable table, Object value,
      boolean isSelected, boolean hasFocus, int row, int column) {
    Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(
        table, value, isSelected, hasFocus, row, column);
    ((JLabel) renderer).setOpaque(true);
    Color foreground;
    Color background;
    //foreground=Color.yellow;
    //background=Color.white;
    /*if (isSelected) {
      foreground = Color.yellow;
      background = Color.green;
    }*/
   
    if (row  == 4) {
        
            foreground = Color.black;
            background = Color.GREEN;
      }
    else
    {
            foreground = Color.black;
            background = Color.GRAY;

    }
        /*else if(row==1)
        {
            foreground = Color.YELLOW;
            background = Color.RED;
        }

            else
        {
        foreground = Color.white;
        background = Color.blue;
      }*/
    
    if(r==0)
    {
        foreground = Color.BLACK;
        background = C;
    }
    renderer.setForeground(foreground);
    renderer.setBackground(background);
    return renderer;
  }

}
