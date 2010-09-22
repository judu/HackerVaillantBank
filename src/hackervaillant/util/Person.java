package hackervaillant.util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author judu
 */
public class Person implements Serializable {

   public static final long serialVersionUID = 2L;

   public String nom;

   public String prenom;

   public Date dateNaissance;

   public Person(String nom, String prenom, Date dateNaissance) {
      this.nom = nom;
      this.prenom = prenom;
      this.dateNaissance = dateNaissance;
   }

   public Person() {
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      }
      if (getClass() != obj.getClass()) {
         return false;
      }
      final Person other = (Person) obj;
      if ((this.nom == null) ? (other.nom != null) : !this.nom.equals(other.nom)) {
         return false;
      }
      if ((this.prenom == null) ? (other.prenom != null) : !this.prenom.equals(other.prenom)) {
         return false;
      }



      if (this.dateNaissance != other.dateNaissance && this.dateNaissance == null) {
         Calendar c1 = Calendar.getInstance();
         c1.setTime(this.dateNaissance);
         Calendar c2 = Calendar.getInstance();
         c2.setTime(other.dateNaissance);
         if(c1.get(Calendar.YEAR) != c2.get(Calendar.YEAR) || c1.get(Calendar.MONTH) != c2.get(Calendar.MONTH) || c1.get(Calendar.DATE) != c2.get(Calendar.DATE))
            return false;
      }
      return true;
   }

   @Override
   public int hashCode() {
      int hash = 7;
      hash = 11 * hash + (this.nom != null ? this.nom.hashCode() : 0);
      hash = 11 * hash + (this.prenom != null ? this.prenom.hashCode() : 0);
      hash = 11 * hash + (this.dateNaissance != null ? this.dateNaissance.hashCode() : 0);
      return hash;
   }


   
}
