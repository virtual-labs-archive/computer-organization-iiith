/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package learningtool;

import java.awt.Color;
import java.util.Random;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author kuldeep
 */
public abstract class Policy {
    public static int VPN, VPO, VM_pages[] = new int [16], PM_pages[] = new int [16];
    public static int Read[]=new int[16];
    public static int outer,inner,timer=0;
    public void VM_ColorPageAt(int Pno,Color c)
    {
       // LearningToolView.VM_renderer = new TableColor(Pno,c);
        //LearningToolView.VM_Table.setDefaultRenderer(Object.class,LearningToolView.VM_renderer);
        TableCellRenderer renderer = new TableColor(Pno,c);
        System.out.println("pno C->"+Pno);
        LearningToolView.VM_Table.setDefaultRenderer(Object.class,renderer);

    }

    public void MM_colorPageat(int Pno,Color c,int bold)
    {

    }
    public void SplitAddr(int button, int address)
    {
        LearningToolView.va = (InputParameter.VM*1024)-1;
        Random r = new Random();
        LearningToolView.va = r.nextInt(LearningToolView.va)%LearningToolView.va;
        String VA_addr_HEX = Integer.toHexString(LearningToolView.va);

        if(button==0)
            LearningToolView.VA_Generate.setText("    "+VA_addr_HEX);
        if(button==1)
        {
            LearningToolView.va = address;
            VA_addr_HEX = Integer.toHexString(LearningToolView.va);
        }
        if(button==0 || button==1)
            LearningToolView.HistoryBox.append(VA_addr_HEX);
        LearningToolView.VA_Generate.setForeground(new Color(102,102,102));

        VPN = LearningToolView.va / (InputParameter.PSz*1024);
        VPO = LearningToolView.va % (InputParameter.PSz*1024);
        if(button==2)
        {
            VPN =  r.nextInt(InputParameter.NumOfVMpages);
            LearningToolView.VPN_Box.setForeground(new Color(102,102,102));
            LearningToolView.VPN_Box.setText("    "+Integer.toString(VPN));
        }
        if(button==3)
        {
            VPN = Integer.parseInt(LearningToolView.VPN_Box.getText().trim());
        }

        if(InputParameter.PageLevel.equals("TWO LEVEL"))
        {
            outer = VPN/4;
            inner = VPN%4;
            LearningToolView.AddressDetail2.setValueAt(outer, 0, 0);
            LearningToolView.AddressDetail2.setValueAt(inner, 0, 1);
            LearningToolView.AddressDetail2.setValueAt(VPO, 0, 2);

        }

        LearningToolView.AddressDetail1.setValueAt(VPN, 0, 0);
        LearningToolView.AddressDetail1.setValueAt(VPO, 0, 1);

    }
    public void setDirty()
    {
        for(int i=0;i<InputParameter.NumOfMMpages;i++)
        {
            if(Read[i]!=-1)
               LearningToolView.MM_Table.setValueAt("  "+Read[i],i,1);
        }
    }
    public void updateHistoryBox(String data) {
          LearningToolView.HistoryBox.append(data);
    }

    public void setPTE(int vpn,int ppn,Color valid)
    {
        if(InputParameter.PageLevel.equals("TWO LEVEL"))
        {
           // TwoLevelColor();
            LearningToolView.Level2.setRowSelectionInterval(vpn/4,vpn/4);
            LearningToolView.Level2.setSelectionBackground(valid);
            
        }
        //else
        {
             LearningToolView.Level1.setValueAt("        "+ppn,vpn,1);
             for(int i=0;i<InputParameter.NumOfVMpages;i++)
             {
               if(VM_pages[i]>=0)
                   LearningToolView.Level1.setValueAt("        "+1,i,2);
               else
                   LearningToolView.Level1.setValueAt("        "+0,i,2);
             }
              LearningToolView.Level1.setRowSelectionInterval(vpn,vpn);
              LearningToolView.Level1.setSelectionBackground(valid);
         }
    }
    public void TwoLevelColor()
    {
        Color P[] = new Color[4];
        P[0] = Color.BLACK;
        P[1] = Color.BLUE;
        P[2] = Color.orange;
        P[3] = Color.MAGENTA;
        int c=0;
        /*LearningToolView.Level1.setRowSelectionInterval(0,4);
        LearningToolView.Level1.setSelectionBackground(Color.MAGENTA);

        LearningToolView.Level1.setRowSelectionInterval(0,4);
        LearningToolView.Level1.setSelectionBackground(Color.blue);*/
       for(int i=0;i<InputParameter.NumOfVMpages;i++)
       {

            LearningToolView.Level1.setForeground(P[c]);
            LearningToolView.Level1.setValueAt("  PTE "+i/4+" - "+i%4, i, 0);
//            LearningToolView.Level1.setValueAt(, i, c);



        }
         
    }
    public abstract int pagehit();
    public abstract void setUnderTheHood();
    public abstract int getVictimPage();
    public boolean IsMainMemoryEmpty()
    {
        for(int i=0; i<InputParameter.NumOfMMpages; i++)
        {
            if(PM_pages[i]==-1)
                return true;
        }
        return false;
    }

    public static void GiveExplaination(String EXP)
    {
        LearningToolView.Explaination.append(EXP+"\n\n");
    }

    public static void RestartFunction()
    {
       // VM table reset
        DefaultTableModel v = (DefaultTableModel)LearningToolView.VM_Table.getModel();
        v.setRowCount(0);

        // MM Table set
         DefaultTableModel m = (DefaultTableModel)LearningToolView.MM_Table.getModel();
        m.setRowCount(0);

        // PTE Tables Level 1 & 2
        DefaultTableModel l1 = (DefaultTableModel)LearningToolView.Level1.getModel();
        l1.setRowCount(0);

        DefaultTableModel l2 = (DefaultTableModel)LearningToolView.Level2.getModel();
        l2.setRowCount(0);

        // Explanation Box
         LearningToolView.Explaination.setText("");
         
         //Page miss & hit status
         LearningToolView.NumMiss.setText("0");
         LearningToolView.NumHit.setText("0");

         //Recent History
         LearningToolView.HistoryBox.setText("");

         // Set underthe hood Table set
         DefaultTableModel hood = (DefaultTableModel)LearningToolView.HoodTable.getModel();
         hood.setRowCount(0);

         // Page miss & hit Counter
         LearningToolView.PageHit = 0;
         LearningToolView.PageMiss = 0;
         
         // timer
            timer =0;

    }




 public static void initialize()
    {
        if(InputParameter.PageLevel.equals("TWO LEVEL"))
                       LearningToolView.jSplitPane4.setDividerLocation(84);
        else
                       LearningToolView.jSplitPane4.setDividerLocation(0);


        for(int i=0; i<16; i++)
        {
            PM_pages[i] = -1;
            VM_pages[i] = -1;
            Read[i]=-1;
        }


    }

    
}

class FIFO extends Policy
{
    public static int fifo_array[] = new int[16];
    public static int fif=0;
    public int next_victim;
    @Override
    public int getVictimPage()
    {
        System.out.println(timer);
        for(int i=0; i<InputParameter.NumOfMMpages; i++)
        {
            if(PM_pages[i] == -1)
            {
                PM_pages[i]=VPN;
                VM_pages[VPN] = i;
                fifo_array[VM_pages[VPN]] = timer++;
                LearningToolView.MM_Table.setValueAt("   PPG - "+i+" (VPG - "+VPN+")", i, 0);
                
                return i;

            }
        }
        int min=timer, i,flag=0, temp=-1;
         
        for(i=0; i<InputParameter.NumOfMMpages; i++)
        {
            if(fifo_array[i]<min)
            {
                temp=i;
                min = fifo_array[i];
            }
        }
        int min2=timer;
        next_victim=-1;
        for(i=0; i<InputParameter.NumOfMMpages; i++)
        {
            if(fifo_array[i]>min && fifo_array[i]<min2)
            {
                next_victim=i;
                min2 = fifo_array[i];
            }
        }
         i= temp;

         VM_pages[PM_pages[i]] = -1;
         PM_pages[i]=VPN;
         VM_pages[VPN] = i;
         LearningToolView.MM_Table.setValueAt("       PPG-"+i+"(VPG-"+VPN+")", i, 0);
         fifo_array[VM_pages[VPN]] = timer++;
         

         System.out.println("nv-> "+next_victim);
          return i;
    /*    if(fif>=InputParameter.NumOfMMpages)
            fif=0;
   
        VM_pages[PM_pages[fif]] = -1;
        PM_pages[fif]=VPN;
        VM_pages[VPN] = fif;
        LearningToolView.MM_Table.setValueAt("       PPG-"+fif+"(VPG-"+VPN+")", fif, 0);
        return fif++;*/
    }
    public int pagehit()
    {
       
         

           if(VM_pages[VPN]>=0)
           {
               fifo_array[VM_pages[VPN]] = timer++;
//               PM_pages[VM_pages[VPN]]=VPN;
               LearningToolView.MM_Table.setValueAt("       PPG-"+VM_pages[VPN]+"(VPG-"+VPN+")",VM_pages[VPN], 0);
         
               int min=timer, i ;
               next_victim=-1;

               for(i=0; i<InputParameter.NumOfMMpages; i++)
               {
                  if(fifo_array[i]<min)
                  {
                    next_victim =i;
                    min = fifo_array[i];
                  }
               }

            return VM_pages[VPN];
           }
         return -1;
    }

    @Override
    public void setUnderTheHood() {
        for(int i=0;i<InputParameter.NumOfVMpages;i++)
        {
            if(VM_pages[i]>=0)
            {
                LearningToolView.HoodTable.setValueAt("  VPG-"+i+"  AT PPG-"+VM_pages[i], i, 0);
                LearningToolView.HoodTable.setValueAt(fifo_array[VM_pages[i]]+"     ",i,1);
            }
            else
            {
                    LearningToolView.HoodTable.setValueAt("  VPG-"+i, i, 0);
                    LearningToolView.HoodTable.setValueAt("",i,1);
            }
            
        }
        if(next_victim!=-1)
         LearningToolView.Next_victim.setText("PPG-"+next_victim);
    }

   
    
}

class LIFO extends Policy
{

    @Override
    public int getVictimPage() {
     System.out.println("2");
     return -1;
    }
    @Override
    public int pagehit() {
     System.out.println("2");
     return -1;
    }

    @Override
    public void setUnderTheHood() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    

}
