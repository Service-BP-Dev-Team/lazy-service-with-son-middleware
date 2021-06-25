/**
 * null
 **/
package soaApp;

import inria.smarttools.core.util.*;

/**
 **/
public class InvokeToEvent extends StEventImpl {
   //
   // Constructors 
   //

   /**
    * Constructor
    **/
   public   InvokeToEvent(){
   }

   /**
    * Constructor
    **/
   public   InvokeToEvent(String adressee){
      super(adressee);
   }


   //
   // Methods 
   //

   /**
    * Return a short description of the InvokeToEvent object.
    * @return a value of the type 'String' : a string representation of this InvokeToEvent
    **/
   public  String toString(){
      String res = "InvokeToEvent";
      return res;
   }


}
