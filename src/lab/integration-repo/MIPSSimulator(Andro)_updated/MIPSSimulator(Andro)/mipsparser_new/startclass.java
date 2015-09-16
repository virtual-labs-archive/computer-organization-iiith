package mipsparser_new;

import javax.swing.JFrame;
import mipsparser_new.Interface;

public class startclass {
   public static void main(String[] args) {
      Interface theApplet = new Interface();
      theApplet.init();
      theApplet.start();
      JFrame window = new JFrame("MIPS Simulator");
      window.setContentPane(theApplet);
      window.setDefaultCloseOperation(3);
      window.pack();
      window.setVisible(true);
   }
}
