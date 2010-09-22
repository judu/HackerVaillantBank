/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hackervaillant.util;

import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author judu
 */
class UDPHandler extends Thread {

   byte[] receiveData = new byte[1024];

   DatagramPacket receivePacket;
   GsonBuilder gbuilder = new GsonBuilder();

   UDPHandler(DatagramSocket serverSocket) {
      receivePacket = new DatagramPacket(receiveData, receiveData.length);
      try {
         serverSocket.receive(receivePacket);
      } catch (IOException ex) {
         Logger.getLogger(UDPHandler.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   @Override
   public void run() {
      gbuilder.setDateFormat("dd/MM/yyyy");
      try {
         String idPlusPerson = new String(receivePacket.getData());
         BufferedReader messageReader = new BufferedReader(new StringReader(idPlusPerson));
         String str;
         String cbn = null;
         String personJson = null;
         while ((str = messageReader.readLine()) != null) {
            String line = str.trim();
            if (line.startsWith("CBN")) {
               cbn = line.substring(line.indexOf(":") + 1);
            } else if (line.startsWith("P")) {
               personJson = line.substring(line.indexOf(":") + 1);
            }
         }

         if(personJson != null && cbn != null) {
            //On peut ajouter à la base.
            Person person = gbuilder.create().fromJson(personJson, Person.class);
            if(person != null) {
               BankDB.getInstance().add(person, cbn);
            }
         }

      } catch (IOException ex) {
         Logger.getLogger(UDPHandler.class.getName()).log(Level.SEVERE, null, ex);
      }


   }
}
