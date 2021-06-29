/**
 **/
package soaApp;

import inria.communicationprotocol.CommunicationProtocolContainer;
import java.util.List;
import java.util.ArrayList;
import inria.smarttools.core.component.*;
import inria.smarttools.core.component.PropertyMap;
import java.lang.String;
import cm.uds.fuchsia.gag.model.configuration.Task;
import cm.uds.fuchsia.gag.network.Subscription;

/**
 **/
public class SoaAppContainer extends CommunicationProtocolContainer implements inria.smarttools.core.component.Container, soaApp.ExitListener, soaApp.DisconnectListener, soaApp.InitDataListener, soaApp.UndoListener, soaApp.ServiceCallListener, soaApp.LogListener, soaApp.LogUndoListener, soaApp.ConnectToListener, soaApp.SendListener, soaApp.NotifyListener {
   {
      List<MethodCall> methodCalls;
      methodCalls = calls.get("disconnect");
      if (methodCalls == null) {
         methodCalls = new ArrayList<MethodCall>();
         calls.put("disconnect", methodCalls);
      }
      methodCalls.add(new MethodCall() {
         public Object call(ContainerProxy expeditor, String expeditorId, String expeditorType, PropertyMap parameters) {
            ((SoaAppFacade) facade).disconnectInput(expeditorId);
            return null;
         	}});
      methodCalls = calls.get("quit");
      if (methodCalls == null) {
         methodCalls = new ArrayList<MethodCall>();
         calls.put("quit", methodCalls);
      }
      methodCalls.add(new MethodCall() {
         public Object call(ContainerProxy expeditor, String expeditorId, String expeditorType, PropertyMap parameters) {
            ((SoaAppFacade) facade).quit(expeditorId);
            return null;
         	}});
      methodCalls = calls.get("serviceCall");
      if (methodCalls == null) {
         methodCalls = new ArrayList<MethodCall>();
         calls.put("serviceCall", methodCalls);
      }
      methodCalls.add(new MethodCall() {
         public Object call(ContainerProxy expeditor, String expeditorId, String expeditorType, PropertyMap parameters) {
            ((SoaAppFacade) facade).inServiceCall(expeditorId, (cm.uds.fuchsia.gag.model.configuration.Task) parameters.get("task"));
            return null;
         	}});
      methodCalls = calls.get("shutdown");
      if (methodCalls == null) {
         methodCalls = new ArrayList<MethodCall>();
         calls.put("shutdown", methodCalls);
      }
      methodCalls.add(new MethodCall() {
         public Object call(ContainerProxy expeditor, String expeditorId, String expeditorType, PropertyMap parameters) {
            ((SoaAppFacade) facade).shutdown(expeditorId);
            return null;
         	}});
      methodCalls = calls.get("notify");
      if (methodCalls == null) {
         methodCalls = new ArrayList<MethodCall>();
         calls.put("notify", methodCalls);
      }
      methodCalls.add(new MethodCall() {
         public Object call(ContainerProxy expeditor, String expeditorId, String expeditorType, PropertyMap parameters) {
            ((SoaAppFacade) facade).inNotify(expeditorId, (cm.uds.fuchsia.gag.network.Subscription) parameters.get("subscription"));
            return null;
         	}});
      methodCalls = calls.get("requestInitData");
      if (methodCalls == null) {
         methodCalls = new ArrayList<MethodCall>();
         calls.put("requestInitData", methodCalls);
      }
      methodCalls.add(new MethodCall() {
         public Object call(ContainerProxy expeditor, String expeditorId, String expeditorType, PropertyMap parameters) {
            Object res = ((SoaAppFacade) facade).requestInitData(expeditorId);
            if (res != null) {
               buildResponseForInOut(expeditor, expeditorId, expeditorType, getContainerDescription().getInOuts().get("requestInitData"), res);
            }
            return null;
         	}});
   }

   //
   // Constructors 
   //

   /**
    * Constructor
    **/
   public   SoaAppContainer(String name, String componentDescriptionResource){
      super(name, componentDescriptionResource);
   }


   //
   // Methods 
   //

   /**
    * createFacade()
    * Do NOT invoke super.createFacade()
    **/
   protected  void createFacade(){
      facade = new soaApp.SoaAppFacade(getIdName());
      initFacadeListeners();
   }

   /**
    * getFacade()

    * Cast to the proper facade class
    **/
   public  soaApp.SoaAppFacade getFacade(){
      return (soaApp.SoaAppFacade) facade;
   }

   /**
    * initFacadeListeners()
    **/
   protected  void initFacadeListeners(){
      super.initFacadeListeners();
      ((SoaAppFacadeInterface) facade).addExitListener(this);
      ((SoaAppFacadeInterface) facade).addDisconnectListener(this);
      ((SoaAppFacadeInterface) facade).addInitDataListener(this);
      ((SoaAppFacadeInterface) facade).addUndoListener(this);
      ((SoaAppFacadeInterface) facade).addServiceCallListener(this);
      ((SoaAppFacadeInterface) facade).addLogListener(this);
      ((SoaAppFacadeInterface) facade).addLogUndoListener(this);
      ((SoaAppFacadeInterface) facade).addConnectToListener(this);
      ((SoaAppFacadeInterface) facade).addSendListener(this);
      ((SoaAppFacadeInterface) facade).addNotifyListener(this);
   }

   /**
    * ExitListener
    * @throws IllegalStateException to absolutely care in business methods
    **/
   public  void exit(ExitEvent e){
      send(new MessageImpl("exit", e.getAttributes() , null, e.getAdressee()));
   }

   /**
    * DisconnectListener
    * @throws IllegalStateException to absolutely care in business methods
    **/
   public  void disconnectOut(DisconnectEvent e){
      send(new MessageImpl("disconnect", e.getAttributes() , null, e.getAdressee()));
   }

   /**
    * InitDataListener
    * @throws IllegalStateException to absolutely care in business methods
    **/
   public  void initData(InitDataEvent e){
      send(new MessageImpl("initData", e.getAttributes() , null, e.getAdressee()));
   }

   /**
    * UndoListener
    * @throws IllegalStateException to absolutely care in business methods
    **/
   public  void undo(UndoEvent e){
      send(new MessageImpl("undo", e.getAttributes() , null, e.getAdressee()));
   }

   /**
    * ServiceCallListener
    * @throws IllegalStateException to absolutely care in business methods
    **/
   public  void outServiceCall(ServiceCallEvent e){
      send(new MessageImpl("serviceCall", e.getAttributes() , null, e.getAdressee()));
   }

   /**
    * LogListener
    * @throws IllegalStateException to absolutely care in business methods
    **/
   public  void log(LogEvent e){
      send(new MessageImpl("log", e.getAttributes() , null, e.getAdressee()));
   }

   /**
    * LogUndoListener
    * @throws IllegalStateException to absolutely care in business methods
    **/
   public  void logUndo(LogUndoEvent e){
      send(new MessageImpl("logUndo", e.getAttributes() , null, e.getAdressee()));
   }

   /**
    * ConnectToListener
    * @throws IllegalStateException to absolutely care in business methods
    **/
   public  void connectTo(ConnectToEvent e){
      send(new MessageImpl("connectTo", e.getAttributes() , null, e.getAdressee()));
   }

   /**
    * SendListener
    * @throws IllegalStateException to absolutely care in business methods
    **/
   public  void send(SendEvent e){
      send(new MessageImpl("send", e.getAttributes() , null, e.getAdressee()));
   }

   /**
    * NotifyListener
    * @throws IllegalStateException to absolutely care in business methods
    **/
   public  void outNotify(NotifyEvent e){
      send(new MessageImpl("notify", e.getAttributes() , null, e.getAdressee()));
   }

   /**
    *  Describe how to serialize this component
    **/
   public  void serialize(){
   }


}
