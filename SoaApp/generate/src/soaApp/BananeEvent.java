/**
 * null
 * null
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * null
 * null
 * 
 * 
 * null
 **/
package soaApp;

import inria.smarttools.core.util.*;

/**
 **/
public class BananeEvent extends StEventImpl {
   //
   // Fields 
   //

   /**
    **/
   protected java.lang.String repliquePartielle;

   /**
    **/
   public void setRepliquePartielle(java.lang.String v){
      this.repliquePartielle = v;
   }

   public java.lang.String getRepliquePartielle(){
      return repliquePartielle;
   }

   //
   // Constructors 
   //

   /**
    * Constructor
    **/
   public   BananeEvent(java.lang.String repliquePartielle){
      setRepliquePartielle(repliquePartielle);
   }

   /**
    * Constructor
    **/
   public   BananeEvent(String adressee, java.lang.String repliquePartielle){
      super(adressee);
      setRepliquePartielle(repliquePartielle);
   }


   //
   // Methods 
   //

   /**
    * Return a short description of the BananeEvent object.
    * @return a value of the type 'String' : a string representation of this BananeEvent
    **/
   public  String toString(){
      String res = "BananeEvent";
      return res;
   }


}
