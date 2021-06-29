/**
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
public class ServiceCallEvent extends StEventImpl {
   //
   // Fields 
   //

   /**
    **/
   protected cm.uds.fuchsia.gag.model.configuration.Task task;

   /**
    **/
   public void setTask(cm.uds.fuchsia.gag.model.configuration.Task v){
      this.task = v;
   }

   public cm.uds.fuchsia.gag.model.configuration.Task getTask(){
      return task;
   }

   //
   // Constructors 
   //

   /**
    * Constructor
    **/
   public   ServiceCallEvent(cm.uds.fuchsia.gag.model.configuration.Task task){
      setTask(task);
   }

   /**
    * Constructor
    **/
   public   ServiceCallEvent(String adressee, cm.uds.fuchsia.gag.model.configuration.Task task){
      super(adressee);
      setTask(task);
   }


   //
   // Methods 
   //

   /**
    * Return a short description of the ServiceCallEvent object.
    * @return a value of the type 'String' : a string representation of this ServiceCallEvent
    **/
   public  String toString(){
      String res = "ServiceCallEvent";
      return res;
   }


}
