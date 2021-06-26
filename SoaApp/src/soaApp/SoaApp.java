package soaApp;

import main.Launcher;
import cm.uds.fuchsia.gag.network.Component;


public abstract class SoaApp extends inria.communicationprotocol.CommunicationProtocolFacade {
	protected Component network = null;
	public SoaApp () {
		//view = getView ();
		//SwingUtilities.invokeLater(new Runnable() {
       //     public void run() {
       //         view = getView ();
        //    }
        //});
		String gagSpecificationPath ="E:\\PhD Recherche\\Implementation\\Workspace Eclipse SON\\GagApp\\gag-specification\\gag.xml";
		network= Launcher.launchComponent(this.getIdName(), gagSpecificationPath);
	}

	public void inInvokeTo(String expeditor) {
	}
	
	public void inReturnTo(String expeditor) {
		
	}

	public abstract void outInvokeTo(String expeditor);
	public abstract void outReturnTo(String expeditor);
public Component getNetwork() {
	if (network == null) {
		String gagSpecificationPath ="E:\\PhD Recherche\\Implementation\\Workspace Eclipse SON\\GagApp\\gag-specification\\gag.xml";
		network= Launcher.launchComponent(this.getIdName(), gagSpecificationPath);
	}
	return network;
}

@Override
protected void neighbourJustConnected(String name, String service) {
		if (name.equals("ComponentsManager")) {
			network = getNetwork();
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
