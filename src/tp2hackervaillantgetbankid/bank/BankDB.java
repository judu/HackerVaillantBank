/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2hackervaillantgetbankid.bank;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hackervaillant.util.Person;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simulates a database, written in json format into a file.
 *
 * This class implements the Singleton pattern.
 * @author judu
 */
public class BankDB {

   private static BankDB instance;

   private static final GsonBuilder builder = new GsonBuilder().setPrettyPrinting();

   private static final String DBDIRPATH = "./db";

   private static final String DBFILEPATH = DBDIRPATH + "/accounts.db";

   private Map<String, Person> db;

   static {
      builder.setDateFormat("dd/MM/yyyy");
   }

   /**
    * Initialize the database.
    */
   private BankDB() {
      File dbdir = new File(DBDIRPATH);
      if (!dbdir.exists() && !dbdir.isDirectory()) {
         System.out.println(dbdir.getAbsolutePath());
         System.out.println("mkdir");
         dbdir.mkdir();
      }

      File dbfile = new File(DBFILEPATH);
      if (!dbfile.exists()) {
         try {
            dbfile.createNewFile();
         } catch (IOException ex) {
            Logger.getLogger(BankDB.class.getName()).log(Level.SEVERE, null, ex);
         }
      }

      loadDB(dbfile);
   }

   /**
    * Initializes db from a file.
    * @param dbfile
    */
   private void loadDB(File dbfile) {
      db = new HashMap<String, Person>();
      FileReader reader = null;
      try {
         reader = new FileReader(dbfile);
      } catch (FileNotFoundException ex) {
         Logger.getLogger(BankDB.class.getName()).log(Level.SEVERE, null, ex);
      }

      BufferedReader breader = new BufferedReader(reader);

      Gson gsondb = builder.create();

      Jsondb obj = gsondb.fromJson(reader, Jsondb.class);

      for (JsondbEntity ent : obj.accounts) {
         add(ent.number, ent.person);
      }
   }

   public static BankDB getInstance() {
      if (instance == null) {
         instance = new BankDB();
      }
      return instance;
   }

   public Person getPersonByID(String id) {
      return db.get(id);
   }

   public String getIDFromPerson(Person person) {
      for (String key : db.keySet()) {
         if (db.get(key).equals(person)) {
            return key;
         }
      }
      return "";
   }

   public void add(String cbn, Person person) {
      db.put(cbn, person);
   }

   /**
    * Write the database
    */
   public void persist() {
      Gson gson = builder.create();
      Jsondb accounts = new Jsondb();
      for (String key : db.keySet()) {
         JsondbEntity entity = new JsondbEntity();
         entity.number = key;
         entity.person = db.get(key);
         accounts.accounts.add(entity);
      }



      String jsonStr = gson.toJson(accounts);

      File dbfile = new File(DBFILEPATH);
      dbfile.delete();
      FileWriter writer = null;
      try {
         dbfile.createNewFile();

         writer = new FileWriter(dbfile);
         writer.write(jsonStr);

      } catch (IOException ex) {
         Logger.getLogger(BankDB.class.getName()).log(Level.SEVERE, null, ex);
      } finally {
         try {
            writer.close();
         } catch (IOException ex) {
            Logger.getLogger(BankDB.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }
}
