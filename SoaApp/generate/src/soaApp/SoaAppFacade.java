/**
 **/
package soaApp;

import java.util.*;
import inria.smarttools.core.component.*;

/**
 **/
public class SoaAppFacade extends SoaApp implements SoaAppFacadeInterface {
   //
   // Fields 
   //

   /**
    * exit
    * 
    **/
   protected Vector<ExitListener> exitListeners = new Vector<ExitListener>();
   /**
    * disconnect
    * 
    **/
   protected Vector<DisconnectListener> disconnectListeners = new Vector<DisconnectListener>();
   /**
    * initData
    * 
    **/
   protected Vector<InitDataListener> initDataListeners = new Vector<InitDataListener>();
   /**
    * undo
    * 
    **/
   protected Vector<UndoListener> undoListeners = new Vector<UndoListener>();
   /**
    * serviceCall
    * null
    **/
   protected Vector<ServiceCallListener> serviceCallListeners = new Vector<ServiceCallListener>();
   /**
    * log
    * 
    **/
   protected Vector<LogListener> logListeners = new Vector<LogListener>();
   /**
    * logUndo
    * 
    **/
   protected Vector<LogUndoListener> logUndoListeners = new Vector<LogUndoListener>();
   /**
    * connectTo
    * 
    **/
   protected Vector<ConnectToListener> connectToListeners = new Vector<ConnectToListener>();
   /**
    * send
    * 
    **/
   protected Vector<SendListener> sendListeners = new Vector<SendListener>();
   /**
    * notify
    * null
    **/
   protected Vector<NotifyListener> notifyListeners = new Vector<NotifyListener>();
   /**
    **/
   private String idName;

   /**
    **/
   public void setIdName(String v){
      this.idName = v;
   }

   public String getIdName(){
      return idName;
   }

   //
   // Constructors 
   //

   /**
    * Constructor
    **/
   public   SoaAppFacade(String idName){
      setIdName(idName);
   }

   /**
    * Constructor
    **/
   public   SoaAppFacade(){
   }


   //
   // Methods 
   //

   /**
    *  request init data 
    **/
   public  Object requestInitData(String expeditor){
      return "";
   }

   /**
    * disconnect
    * disconnect
    * @param expeditor is the component name who sent this message
    **/
   public  void disconnectInput(String expeditor){
      super.disconnectInput(expeditor);
   }

   /**
    * quit
    * quit
    * @param expeditor is the component name who sent this message
    **/
   public  void quit(String expeditor){
      super.quit(expeditor);
   }

   /**
    * serviceCall
    * null
    * @param expeditor is the component name who sent this message
    **/
   public  void inServiceCall(String expeditor, cm.uds.fuchsia.gag.model.configuration.Task task){
      super.inServiceCall(expeditor, task);
   }

   /**
    * shutdown
    * shutdown
    * @param expeditor is the component name who sent this message
    **/
   public  void shutdown(String expeditor){
      super.shutdown(expeditor);
   }

   /**
    * notify
    * null
    * @param expeditor is the component name who sent this message
    **/
   public  void inNotify(String expeditor, cm.uds.fuchsia.gag.network.Subscription subscription){
      super.inNotify(expeditor, subscription);
   }

   /**
    * requestInitData
    * 
    * @param expeditor is the component name who sent this message
    **/
   public  Object requestTree(String expeditor){
      return super.requestTree(expeditor);
   }

   /**
    * exit
    * 
    * @param ev a <code>Object</code> value : data
    **/
   public  void exit(){
      exit(null);
   }

   /**
    * exit
    * 
    * @param adressee component name, which will receive this message
    * @param ev a <code>Object</code> value : data
    **/
   public  void exit(String adressee){
      PropertyMap args = new PropertyMap();
      ExitEvent ev =  new ExitEvent(adressee);
      ev.setAttributes(args);
      for(int i = 0 ; i < exitListeners.size() ; i++)
      (( ExitListener ) exitListeners.elementAt(i)).exit(ev);
   }

   /**
    * disconnect
    * 
    * @param ev a <code>Object</code> value : data
    **/
   public  void disconnectOut(){
      disconnectOut(null);
   }

   /**
    * disconnect
    * 
    * @param adressee component name, which will receive this message
    * @param ev a <code>Object</code> value : data
    **/
   public  void disconnectOut(String adressee){
      PropertyMap args = new PropertyMap();
      DisconnectEvent ev =  new DisconnectEvent(adressee);
      ev.setAttributes(args);
      for(int i = 0 ; i < disconnectListeners.size() ; i++)
      (( DisconnectListener ) disconnectListeners.elementAt(i)).disconnectOut(ev);
   }

   /**
    * initData
    * 
    * @param ev a <code>Object</code> value : data
    **/
   public  void initData(inria.smarttools.core.component.PropertyMap inits){
      initData(null, inits);
   }

   /**
    * initData
    * 
    * @param adressee component name, which will receive this message
    * @param ev a <code>Object</code> value : data
    **/
   public  void initData(String adressee, inria.smarttools.core.component.PropertyMap inits){
      PropertyMap args = new PropertyMap();
      args.put("inits",inits);
      InitDataEvent ev =  new InitDataEvent(adressee, inits);
      ev.setAttributes(args);
      for(int i = 0 ; i < initDataListeners.size() ; i++)
      (( InitDataListener ) initDataListeners.elementAt(i)).initData(ev);
   }

   /**
    * undo
    * 
    * @param ev a <code>Object</code> value : data
    **/
   public  void undo(java.lang.String message, java.lang.String receiver){
      undo(null, message, receiver);
   }

   /**
    * undo
    * 
    * @param adressee component name, which will receive this message
    * @param ev a <code>Object</code> value : data
    **/
   public  void undo(String adressee, java.lang.String message, java.lang.String receiver){
      PropertyMap args = new PropertyMap();
      args.put("message",message);
      args.put("receiver",receiver);
      UndoEvent ev =  new UndoEvent(adressee, message, receiver);
      ev.setAttributes(args);
      for(int i = 0 ; i < undoListeners.size() ; i++)
      (( UndoListener ) undoListeners.elementAt(i)).undo(ev);
   }

   /**
    * serviceCall
    * null
    * @param ev a <code>Object</code> value : data
    **/
   public  void outServiceCall(cm.uds.fuchsia.gag.model.configuration.Task task){
      outServiceCall(null, task);
   }

   /**
    * serviceCall
    * null
    * @param adressee component name, which will receive this message
    * @param ev a <code>Object</code> value : data
    **/
   public  void outServiceCall(String adressee, cm.uds.fuchsia.gag.model.configuration.Task task){
      PropertyMap args = new PropertyMap();
      args.put("task",task);
      ServiceCallEvent ev =  new ServiceCallEvent(adressee, task);
      ev.setAttributes(args);
      for(int i = 0 ; i < serviceCallListeners.size() ; i++)
      (( ServiceCallListener ) serviceCallListeners.elementAt(i)).outServiceCall(ev);
   }

   /**
    * log
    * 
    * @param ev a <code>Object</code> value : data
    **/
   public  void log(java.lang.String info){
      log(null, info);
   }

   /**
    * log
    * 
    * @param adressee component name, which will receive this message
    * @param ev a <code>Object</code> value : data
    **/
   public  void log(String adressee, java.lang.String info){
      PropertyMap args = new PropertyMap();
      args.put("info",info);
      LogEvent ev =  new LogEvent(adressee, info);
      ev.setAttributes(args);
      for(int i = 0 ; i < logListeners.size() ; i++)
      (( LogListener ) logListeners.elementAt(i)).log(ev);
   }

   /**
    * logUndo
    * 
    * @param ev a <code>Object</code> value : data
    **/
   public  void logUndo(java.lang.String message, java.lang.String receiver){
      logUndo(null, message, receiver);
   }

   /**
    * logUndo
    * 
    * @param adressee component name, which will receive this message
    * @param ev a <code>Object</code> value : data
    **/
   public  void logUndo(String adressee, java.lang.String message, java.lang.String receiver){
      PropertyMap args = new PropertyMap();
      args.put("message",message);
      args.put("receiver",receiver);
      LogUndoEvent ev =  new LogUndoEvent(adressee, message, receiver);
      ev.setAttributes(args);
      for(int i = 0 ; i < logUndoListeners.size() ; i++)
      (( LogUndoListener ) logUndoListeners.elementAt(i)).logUndo(ev);
   }

   /**
    * connectTo
    * 
    * @param ev a <code>Object</code> value : data
    **/
   public  void connectTo(java.lang.String id_src, java.lang.String type_dest, java.lang.String id_dest, java.lang.String dc, java.lang.String tc, java.lang.String sc, inria.smarttools.core.component.PropertyMap actions){
      connectTo(null, id_src, type_dest, id_dest, dc, tc, sc, actions);
   }

   /**
    * connectTo
    * 
    * @param adressee component name, which will receive this message
    * @param ev a <code>Object</code> value : data
    **/
   public  void connectTo(String adressee, java.lang.String id_src, java.lang.String type_dest, java.lang.String id_dest, java.lang.String dc, java.lang.String tc, java.lang.String sc, inria.smarttools.core.component.PropertyMap actions){
      PropertyMap args = new PropertyMap();
      args.put("id_src",id_src);
      args.put("type_dest",type_dest);
      args.put("id_dest",id_dest);
      args.put("dc",dc);
      args.put("tc",tc);
      args.put("sc",sc);
      args.put("actions",actions);
      ConnectToEvent ev =  new ConnectToEvent(adressee, id_src, type_dest, id_dest, dc, tc, sc, actions);
      ev.setAttributes(args);
      for(int i = 0 ; i < connectToListeners.size() ; i++)
      (( ConnectToListener ) connectToListeners.elementAt(i)).connectTo(ev);
   }

   /**
    * send
    * 
    * @param ev a <code>Object</code> value : data
    **/
   public  void send(java.lang.String messageName, java.lang.String messageExpeditor){
      send(null, messageName, messageExpeditor);
   }

   /**
    * send
    * 
    * @param adressee component name, which will receive this message
    * @param ev a <code>Object</code> value : data
    **/
   public  void send(String adressee, java.lang.String messageName, java.lang.String messageExpeditor){
      PropertyMap args = new PropertyMap();
      args.put("messageName",messageName);
      args.put("messageExpeditor",messageExpeditor);
      SendEvent ev =  new SendEvent(adressee, messageName, messageExpeditor);
      ev.setAttributes(args);
      for(int i = 0 ; i < sendListeners.size() ; i++)
      (( SendListener ) sendListeners.elementAt(i)).send(ev);
   }

   /**
    * notify
    * null
    * @param ev a <code>Object</code> value : data
    **/
   public  void outNotify(cm.uds.fuchsia.gag.network.Subscription subscription){
      outNotify(null, subscription);
   }

   /**
    * notify
    * null
    * @param adressee component name, which will receive this message
    * @param ev a <code>Object</code> value : data
    **/
   public  void outNotify(String adressee, cm.uds.fuchsia.gag.network.Subscription subscription){
      PropertyMap args = new PropertyMap();
      args.put("subscription",subscription);
      NotifyEvent ev =  new NotifyEvent(adressee, subscription);
      ev.setAttributes(args);
      for(int i = 0 ; i < notifyListeners.size() ; i++)
      (( NotifyListener ) notifyListeners.elementAt(i)).outNotify(ev);
   }

   /**
    * exit
    * 
    **/
   public  void addExitListener(ExitListener data){
      exitListeners.add(data);
   }

   /**
    * exit
    * 
    **/
   public  void removeExitListener(ExitListener data){
      exitListeners.remove(data);
   }

   /**
    * disconnect
    * 
    **/
   public  void addDisconnectListener(DisconnectListener data){
      disconnectListeners.add(data);
   }

   /**
    * disconnect
    * 
    **/
   public  void removeDisconnectListener(DisconnectListener data){
      disconnectListeners.remove(data);
   }

   /**
    * initData
    * 
    **/
   public  void addInitDataListener(InitDataListener data){
      initDataListeners.add(data);
   }

   /**
    * initData
    * 
    **/
   public  void removeInitDataListener(InitDataListener data){
      initDataListeners.remove(data);
   }

   /**
    * undo
    * 
    **/
   public  void addUndoListener(UndoListener data){
      undoListeners.add(data);
   }

   /**
    * undo
    * 
    **/
   public  void removeUndoListener(UndoListener data){
      undoListeners.remove(data);
   }

   /**
    * serviceCall
    * null
    **/
   public  void addServiceCallListener(ServiceCallListener data){
      serviceCallListeners.add(data);
   }

   /**
    * serviceCall
    * null
    **/
   public  void removeServiceCallListener(ServiceCallListener data){
      serviceCallListeners.remove(data);
   }

   /**
    * log
    * 
    **/
   public  void addLogListener(LogListener data){
      logListeners.add(data);
   }

   /**
    * log
    * 
    **/
   public  void removeLogListener(LogListener data){
      logListeners.remove(data);
   }

   /**
    * logUndo
    * 
    **/
   public  void addLogUndoListener(LogUndoListener data){
      logUndoListeners.add(data);
   }

   /**
    * logUndo
    * 
    **/
   public  void removeLogUndoListener(LogUndoListener data){
      logUndoListeners.remove(data);
   }

   /**
    * connectTo
    * 
    **/
   public  void addConnectToListener(ConnectToListener data){
      connectToListeners.add(data);
   }

   /**
    * connectTo
    * 
    **/
   public  void removeConnectToListener(ConnectToListener data){
      connectToListeners.remove(data);
   }

   /**
    * send
    * 
    **/
   public  void addSendListener(SendListener data){
      sendListeners.add(data);
   }

   /**
    * send
    * 
    **/
   public  void removeSendListener(SendListener data){
      sendListeners.remove(data);
   }

   /**
    * notify
    * null
    **/
   public  void addNotifyListener(NotifyListener data){
      notifyListeners.add(data);
   }

   /**
    * notify
    * null
    **/
   public  void removeNotifyListener(NotifyListener data){
      notifyListeners.remove(data);
   }


}
