package cm.uds.fuchsia.gag.network;

import java.util.ArrayList;

import cm.uds.fuchsia.gag.model.configuration.Configuration;
import cm.uds.fuchsia.gag.model.configuration.Data;
import cm.uds.fuchsia.gag.model.configuration.Task;
import cm.uds.fuchsia.gag.model.specification.GAG;
import cm.uds.fuchsia.gag.model.specification.Service;
import cm.uds.fuchsia.gag.specification.aspect.GAGAspect;
import cm.uds.fuchsia.gag.ui.component.ComponentIHM;

public class Component {

	private  String componentName;
	private  GAGAspect associateGAG;
	private  ComponentIHM ihm;
	private  ArrayList<Subscription> subscriptionList;
	private ComponentMiddleware middleware;

	public  String getComponentName() {
		return componentName;
	}

	public  void setComponentName(String cOMPONENT_NAME) {
		componentName = cOMPONENT_NAME;
	}

	public  GAGAspect getAssociateGAG() {
		return associateGAG;
	}

	public  void setAssociateGAG(GAGAspect aSSOCIATE_GAG) {
		associateGAG = aSSOCIATE_GAG;
	}

	public  ComponentIHM getIhm() {
		return ihm;
	}

	public  void setIhm(ComponentIHM iHM) {
		ihm = iHM;
	}

	public  ArrayList<Subscription> getSubscriptionList() {
		return subscriptionList;
	}

	public  void setSubscriptionList(ArrayList<Subscription> sUBSCRIPTION_LIST) {
		subscriptionList = sUBSCRIPTION_LIST;
	}

	public ComponentMiddleware getMiddleware() {
		return middleware;
	}

	public void setMiddleware(ComponentMiddleware middleware) {
		this.middleware = middleware;
	}
	
	// behavior when receiving a task, this method will be call by the middleware (inServiceCall method)
	public void receiveTask(Task t) {
		//GAGAspect gag = new GAGAspect(g);
		Task myTask= new Task();
		myTask.setInputs(t.getInputs());
		myTask.setOutputs(t.getOutputs());
		//find associate service
		Service serv = t.getService();
		Service myService=null;
		ArrayList<Service> services = this.associateGAG.getServices();
		for(int i=0;i<services.size();i++) {
			if(serv.getName().equals(services.get(i).getName())) {
				myService=services.get(i);
				break;
			}
		}
		// we set the configuration
		myTask.setService(myService);
		Configuration conf= new Configuration();
		conf.setRoot(myTask);
		associateGAG.setConfiguration(conf);
		
		//create output subscriptions
		for(int i=0;i<myTask.getOutputs().size();i++) {
			Subscription sub=new Subscription();
			sub.setRemote(true);
			sub.setData(myTask.getOutputs().get(i));
			sub.setComponentName(this.componentName);
			this.subscriptionList.add(sub);
		}
		
		//put the right subscription list to ihm
		
		this.ihm.setSubscriptionsList(this.subscriptionList);
		
		//draw the graph
		this.ihm.disposeTheGraph(this.associateGAG);
		
		//update the UI
		this.ihm.updateUI();
		
	}
	
	//behavior when sending Task
	public void sendTask(Task t) {
		
		Task myTask = t;
		// Store subscriptions to recall the remote call and to handle notifications receipt
		for(int i=0;i<myTask.getOutputs().size();i++) {
			Subscription sub=new Subscription();
			sub.setRemote(true);
			sub.setData(myTask.getOutputs().get(i));
			sub.setComponentName(componentName);
			subscriptionList.add(sub);
		}
		//update the ui
		ihm.updateUI();
		//network aspect, we use the middleware
		middleware.outServiceCall(t.getService().getLocation(), t);
		
	}
	
	public  void sendNotification(Subscription sub) {
		
		//remove the subscription in the list
		this.subscriptionList.remove(sub);
		
		//network aspect, we use the middleware
		middleware.outNotify(sub.getComponentName(), sub);
	}
	
	// behavior when receiving a notification, this method will be call by the middleware (inNotification method)
	public void receiveNotification(Subscription subscription ) {
		
			Data data = subscription.getData();
			//look for the subscriptions
			Subscription mySub=null;
			for(int i=0;i<subscriptionList.size();i++) {
				Subscription sub = subscriptionList.get(i);
				if(sub.getData().getName().equals(data.getName()) && sub.getComponentName().equals(componentName))
				{
					mySub=sub;
					break;
				}
			}
			subscriptionList.remove(mySub);
			
			//update the gag
			
			//get the data 
			Data dat = mySub.getData();
			dat.setValue(data.getValue());
		
				//draw the graph
				ihm.disposeTheGraph(associateGAG);
				
				//update the UI
				ihm.updateUI();
		
	}

	
	
	
	

	
	
	
}