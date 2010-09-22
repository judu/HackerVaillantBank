/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hackervaillant.util;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author judu
 */
public class TCPServer extends Thread {

   public TCPServer() {
      super();
   }

   @Override
   public void run() {
      try {
         ServerSocket serverSocket = new ServerSocket(4242);

         ExecutorService pool = Executors.newFixedThreadPool(50);

         boolean estActif = true;
         while (estActif) {
            TCPHandler handler = new TCPHandler(serverSocket.accept());
            pool.execute(handler);
         }
      } catch (IOException ex) {
         Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
}
