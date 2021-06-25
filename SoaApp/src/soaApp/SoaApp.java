package soaApp;

import cm.uds.fuchsia.gag.network.NetworkInformation;


public abstract class SoaApp extends inria.communicationprotocol.CommunicationProtocolFacade {
	protected NetworkInformation network = null;
	public SoaApp () {
		//view = getView ();
		//SwingUtilities.invokeLater(new Runnable() {
       //     public void run() {
       //         view = getView ();
        //    }
        //});
		network= new NetworkInformation();
	}

	public void inInvokeTo() {
	}
	
	public void inReturnTo() {
		
	}

	public abstract void outInvokeTo();
	public abstract void outReturnTo();
public NetworkInformation getNetwork() {
	if (network == null) {
		network = new NetworkInformation();
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
