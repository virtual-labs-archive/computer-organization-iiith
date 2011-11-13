/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package learningtool;

import java.awt.Color;
import javax.swing.ButtonModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author kuldeep
 */
public class InputParameter {
    public static int VM ;
    public static int MM;
    public static int PSz;
    public static int TLB;

    public static String RPolicy;
    public static String PageLevel;
    public static int NumOfVMpages;
    public static int NumOfMMpages;
    public static int NumofOuterPages;
    public static float VPN_bits;
    public static int NumOfOuterpages;
    public static int NumOfInnerpages;
    public void getPsz()
    {
        String a = (String)LearningToolView.PS_Combo.getSelectedItem();
        int index = a.indexOf(" ");
        String temp = a.substring(0, index);
        PSz = Integer.parseInt(temp);

    }
    public void getVM()
    {
        String a = (String)LearningToolView.VM_Combo.getSelectedItem();
        int index = a.indexOf(" ");
        String temp = a.substring(0, index);
        VM = Integer.parseInt(temp);
        //System.out.println("It works!!!"+VM);
        System.out.println(VM);
    }
    public void getMM()
    {
        String a = (String)LearningToolView.MM_Combo.getSelectedItem();
        int index = a.indexOf(" ");
        String temp = a.substring(0, index);
        MM = Integer.parseInt(temp);

    }
    public void setVM_MM()
    {
        String set_VM[] = new String[16];
        int cnt=0,tempSize=0;
        for(int j=PSz;j<=PSz*16;j+=PSz) {
            String v = new String();
            v = Integer.toString(j);
            v+=" ";
            v+="KB";
            if(cnt==0)
            {
                tempSize=j;
            }
            set_VM[cnt++]=v;
        }

        LearningToolView.MM_Combo.setModel(new javax.swing.DefaultComboBoxModel(set_VM));
        LearningToolView.VM_Combo.setModel(new javax.swing.DefaultComboBoxModel(set_VM));

    }
    public void getPolicy()
    {
        RPolicy = (String)LearningToolView.Extra_Policy.getSelectedItem();
    }
    public void setPages(int p,Color c)
    {
        NumOfVMpages=VM/PSz;
        //Num of inner and outer page
        VPN_bits = (float) (Math.log(NumOfVMpages) / Math.log(2));
        VPN_bits = (float) Math.ceil(VPN_bits);
        if(NumOfVMpages==1)
            VPN_bits=1;
        int bits = (int)VPN_bits;
        switch(bits)
        {
            case 1:
                NumOfOuterpages=0;
                break;
            case 2:
                NumOfOuterpages=1;
                break;
            case 3:
                NumOfOuterpages=2;
                break;
            case 4:
                if(NumOfVMpages>8 && NumOfVMpages<=12)
                    NumOfOuterpages=3;
                else
                NumOfOuterpages=4;
                break;
        }
        if(PageLevel.equals("TWO LEVEL"))
        {
            DefaultTableModel L2 = (DefaultTableModel)LearningToolView.Level2.getModel();
            L2.setRowCount(NumOfOuterpages);
            for (int i=0;i<NumOfOuterpages;i++)
                LearningToolView.Level2.setValueAt("          "+i, i, 0);

        }
        //System.out.print("bits in vpn< "+VPN_bits+" > ");
         
        DefaultTableModel v = (DefaultTableModel)LearningToolView.VM_Table.getModel();
        v.setRowCount(NumOfVMpages);
        DefaultTableModel hood = (DefaultTableModel)LearningToolView.HoodTable.getModel();
        hood.setRowCount(NumOfVMpages);


        DefaultTableModel l1 = (DefaultTableModel)LearningToolView.Level1.getModel();
        l1.setRowCount(NumOfVMpages);
        for(int i=0;i<NumOfVMpages; i++)
        {
            LearningToolView.VM_Table.setValueAt("        VPG  -  "+i, i, 0);
            if(PageLevel.equals("ONE LEVEL"))
                    LearningToolView.Level1.setValueAt("         PTE "+i, i, 0);

        }
        NumOfMMpages=MM/PSz;
        DefaultTableModel m = (DefaultTableModel)LearningToolView.MM_Table.getModel();
        m.setRowCount(NumOfMMpages);


       // TableCellRenderer renderer = new TableColor(p,c);
        //LearningToolView.VM_Table.setDefaultRenderer(Object.class, renderer);
        //LearningToolView.VM_renderer = new TableColor(0,Color.YELLOW);
        //LearningToolView.VM_Table.setDefaultRenderer(Object.class,LearningToolView.VM_renderer);

    }



}
