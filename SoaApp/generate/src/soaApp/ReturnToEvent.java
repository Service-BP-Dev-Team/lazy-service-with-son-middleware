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
 **/
package soaApp;

import inria.smarttools.core.util.*;

/**
 **/
public class ReturnToEvent extends StEventImpl {
   //
   // Constructors 
   //

   /**
    * Constructor
    **/
   public   ReturnToEvent(){
   }

   /**
    * Constructor
    **/
   public   ReturnToEvent(String adressee){
      super(adressee);
   }


   //
   // Methods 
   //

   /**
    * Return a short description of the ReturnToEvent object.
    * @return a value of the type 'String' : a string representation of this ReturnToEvent
    **/
   public  String toString(){
      String res = "ReturnToEvent";
      return res;
   }


}
