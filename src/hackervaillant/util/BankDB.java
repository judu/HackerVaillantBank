/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hackervaillant.util;

import java.util.Map;

/**
 *
 * @author judu
 */
class BankDB {

   private static BankDB instance;

   private Map<String,Person> db;

   private BankDB() {
      //TODO:init DB.
   }

   public static BankDB getInstance() {
      if(instance == null) {
         instance = new BankDB();
      }
      return instance;
   }

   public Person getPersonByID(String id) {
      return db.get(id);
   }

   public String getIDFromPerson(Person person) {
      for(String key:db.keySet()) {
         if(db.get(key).equals(person)) {
            return key;
         }
      }
      return "";
   }

   public void add(Person person, String cbn) {
      db.put(cbn, person);
   }

}
