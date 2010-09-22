/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tp2hackervaillantgetbankid.bank;

import hackervaillant.util.Person;
import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author judu
 */
public class BankDBTest {

    public BankDBTest() {
    }

   @BeforeClass
   public static void setUpClass() throws Exception {
   }

   @AfterClass
   public static void tearDownClass() throws Exception {
   }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

   /**
    * Test of getInstance method, of class BankDB.
    */
   @Test
   public void testGetInstance() {
      System.out.println("getInstance");
      BankDB expResult = null;
      BankDB result = BankDB.getInstance();
   }

   /**
    * Test of getPersonByID method, of class BankDB.
    */
   @Test
   public void testGetPersonByID() {
      System.out.println("getPersonByID");
      String id = "1234-5678-6789-1234"; // julien durillon
      BankDB instance = BankDB.getInstance();
      Calendar cal = Calendar.getInstance();
      cal.set(1989, 0, 11);

      Person expResult = new Person("Durillon", "Julien", cal.getTime());
      Person result = instance.getPersonByID(id);
      assertEquals(expResult, result);
   }

   /**
    * Test of getIDFromPerson method, of class BankDB.
    */
   @Test
   public void testGetIDFromPerson() {
      System.out.println("getIDFromPerson");
      Calendar cal = Calendar.getInstance();
      cal.set(1989, 0, 11);

      Person person = new Person("Durillon", "Julien", cal.getTime());
      BankDB instance = BankDB.getInstance();
      String expResult = "1234-5678-6789-1234";
      String result = instance.getIDFromPerson(person);
      assertEquals(expResult, result);
   }

   /**
    * Test of add method, of class BankDB.
    */
   @Test
   public void testAdd() {
      System.out.println("add");
      Calendar cal = Calendar.getInstance();
      cal.set(1990, 11, 10);

      Person person = new Person("Durillon", "Benoit", cal.getTime());
      String cbn = "9876-7654-1234-9876";
      BankDB instance = BankDB.getInstance();
      instance.add(cbn, person);

      assertEquals(cbn, instance.getIDFromPerson(person));
   }

   @Test
   public void testPersist() {
      BankDB instance = BankDB.getInstance();
      instance.persist();
   }

}