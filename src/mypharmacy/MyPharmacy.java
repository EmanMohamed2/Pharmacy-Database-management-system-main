
package mypharmacy;

import javax.swing.SwingUtilities;


public class MyPharmacy{

    public static void main(String[] args) {

        //running my application 
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                new MainClass().setVisible(true);
            }
        });
        
    }
    
}
