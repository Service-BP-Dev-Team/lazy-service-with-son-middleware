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
 * null
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
public class NotifyEvent extends StEventImpl {
   //
   // Fields 
   //

   /**
    **/
   protected cm.uds.fuchsia.gag.network.Subscription subscription;

   /**
    **/
   public void setSubscription(cm.uds.fuchsia.gag.network.Subscription v){
      this.subscription = v;
   }

   public cm.uds.fuchsia.gag.network.Subscription getSubscription(){
      return subscription;
   }

   //
   // Constructors 
   //

   /**
    * Constructor
    **/
   public   NotifyEvent(cm.uds.fuchsia.gag.network.Subscription subscription){
      setSubscription(subscription);
   }

   /**
    * Constructor
    **/
   public   NotifyEvent(String adressee, cm.uds.fuchsia.gag.network.Subscription subscription){
      super(adressee);
      setSubscription(subscription);
   }


   //
   // Methods 
   //

   /**
    * Return a short description of the NotifyEvent object.
    * @return a value of the type 'String' : a string representation of this NotifyEvent
    **/
   public  String toString(){
      String res = "NotifyEvent";
      return res;
   }


}
