/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tp2hackervaillantgetbankid.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author judu
 */
public class UDPServer extends Thread {

   @Override
   public void run() {
      try {
         DatagramSocket serverSocket = new DatagramSocket(4243);

         ExecutorService pool = Executors.newFixedThreadPool(50);
         
         while(true) {
            UDPHandler handler = new UDPHandler(serverSocket);

         }



         


      } catch (SocketException ex) {
         Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
      }

   }

}
