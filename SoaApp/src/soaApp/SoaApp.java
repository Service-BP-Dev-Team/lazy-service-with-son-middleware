package soaApp;

import main.Launcher;
import cm.uds.fuchsia.gag.network.Component;
import cm.uds.fuchsia.gag.network.ComponentMiddleware;
import cm.uds.fuchsia.gag.model.configuration.Task;
import cm.uds.fuchsia.gag.network.Subscription;

public abstract class SoaApp extends inria.communicationprotocol.CommunicationProtocolFacade implements ComponentMiddleware {
	protected Component component = null;
	public static String SPECIFICATION_FOLDER="gag-specification";
	public SoaApp () {
		//view = getView ();
		//SwingUtilities.invokeLater(new Runnable() {
       //     public void run() {
       //         view = getView ();
        //    }
        //});
		
		//network= Launcher.launchComponent(this.getIdName(), gagSpecificationPath);
	}

	public void inServiceCall(String expeditor, Task task) {
		component.receiveTask(task);
	}
	
	public void inNotify(String expeditor, Subscription subscription) {
		component.receiveNotification(subscription);
	}

	public abstract void outServiceCall(String expeditor, Task task);
	public abstract void outNotify(String expeditor, Subscription subscription);
public Component getComponent() {
	if (component == null) {
		String gagSpecificationPath =SPECIFICATION_FOLDER+"\\"+this.getIdName()+".xml"; //this is a relative path
		component= Launcher.launchComponent(this.getIdName(), gagSpecificationPath,this); // use the current object as middleware
	}
	return component;
}

@Override
protected void neighbourJustConnected(String name, String service) {
		if (name.equals("ComponentsManager")) {
			component = getComponent();
		}
	}

	public void disconnectInput(String expeditor) {
		// TODO Auto-generated method stub
		
	}

	
	
	public void shutdown(String expeditor) {
		// TODO Auto-generated method stub
		
	}

	public Object requestTree(String expeditor) {
		// TODO Auto-generated method stub
		return null;
	}

	public void quit(String expeditor) {
		// TODO Auto-generated method stub
		
	}

	
	
	

}
