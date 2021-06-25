package cm.uds.fuchsia.gag.network;

import java.util.ArrayList;

import cm.uds.fuchsia.gag.model.specification.GAG;
import cm.uds.fuchsia.gag.ui.component.ComponentIHM;

public class Component {

	private  String COMPONENT_NAME;
	private  GAG ASSOCIATE_GAG;
	private  ComponentIHM IHM;
	private  ArrayList<Subscription> SUBSCRIPTION_LIST;

	public  String getCOMPONENT_NAME() {
		return COMPONENT_NAME;
	}

	public  void setCOMPONENT_NAME(String cOMPONENT_NAME) {
		COMPONENT_NAME = cOMPONENT_NAME;
	}

	public  GAG getASSOCIATE_GAG() {
		return ASSOCIATE_GAG;
	}

	public  void setASSOCIATE_GAG(GAG aSSOCIATE_GAG) {
		ASSOCIATE_GAG = aSSOCIATE_GAG;
	}

	public  ComponentIHM getIHM() {
		return IHM;
	}

	public  void setIHM(ComponentIHM iHM) {
		IHM = iHM;
	}

	public  ArrayList<Subscription> getSUBSCRIPTION_LIST() {
		return SUBSCRIPTION_LIST;
	}

	public  void setSUBSCRIPTION_LIST(ArrayList<Subscription> sUBSCRIPTION_LIST) {
		SUBSCRIPTION_LIST = sUBSCRIPTION_LIST;
	}
	
	
	
	

	
	
	
}
