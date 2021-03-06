/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tp2hackervaillantgetbankid;

import hackervaillant.util.Person;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tp2hackervaillantgetbankid.net.TCPServer;
import tp2hackervaillantgetbankid.net.UDPServer;
import java.util.Calendar;

/**
 *
 * @author judu
 */
public class Main {


   public static String person = "{'nom' : 'Durillon', 'prenom' : 'Julien', 'dateNaissance' : '11/01/1989'}";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        TCPServer tcp = new TCPServer();
        UDPServer udp = new UDPServer();
        tcp.start();
        udp.start();
        udp.join();
    }

    public static void testGson() {
      Gson gsonObject = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
      Person myPerson = gsonObject.fromJson(person, Person.class);

       System.out.println(myPerson.nom + " " + myPerson.prenom + " " + myPerson.dateNaissance.toString());

       Calendar cal = Calendar.getInstance();
       cal.set(1986, 8, 20);
       Person alex = new Person("Garnier", "Alexandre", cal.getTime());
       System.out.println(gsonObject.toJson(alex));

    }

}
