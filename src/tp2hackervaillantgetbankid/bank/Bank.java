/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tp2hackervaillantgetbankid.bank;

import hackervaillant.util.Person;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author judu
 */
public class Bank {

   private final static Map<String, Person> accounts = new HashMap<String, Person>();


   static {
      Calendar cal = Calendar.getInstance();
      cal.set(1987, 5, 28);
      accounts.put("9872-1897-9872-1028", new Person("Fortun", "Manoel", cal.getTime()));
      cal.set(1989,0,11);
      accounts.put("1111-2222-3333-4444", new Person("Durillon", "Julien", cal.getTime()));
      cal.set(1987, 8,20);
      accounts.put("9876-5432-1234-5678", new Person("Garnier", "Alexandre", cal.getTime()));
   }


   

}
