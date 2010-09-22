/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hackervaillant.util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import tp2hackervaillantgetbankid.bank.Bank;

/**
 *
 * @author judu
 */
public class TCPHandler extends Thread {

   private Socket socket;

   public TCPHandler(Socket sock) {
      super();
      this.socket = sock;
   }

   @Override
   public void run() {
      ObjectInputStream ois = null;
      try {
         Person wantedPerson = null;
         String bankID;
         ois = new ObjectInputStream(socket.getInputStream());
         try {
            wantedPerson = (Person) ois.readObject();
         } catch (ClassNotFoundException ex) {
            Logger.getLogger(TCPHandler.class.getName()).log(Level.SEVERE, null, ex);
         }
         bankID = BankDB.getInstance().getIDFromPerson(wantedPerson);
         DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
         outToServer.writeBytes(bankID);
         socket.close();
      } catch (IOException ex) {
         Logger.getLogger(TCPHandler.class.getName()).log(Level.SEVERE, null, ex);
      } finally {
         try {
            ois.close();
         } catch (IOException ex) {
            Logger.getLogger(TCPHandler.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }
}
