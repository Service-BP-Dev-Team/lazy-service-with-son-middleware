package cm.uds.fuchsia.gag.model.specification;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

public class Parameter {

	private String name;
	private String shortName;
	private Service Service; // may be null if the parsing from xml fail

	
	@XmlTransient
	public Service getService() {
		return Service;
	}
	
	 public void afterUnmarshal(Unmarshaller u, Object parent) {
		    this.setService((Service)parent);
		  }

	public void setService(Service service) {
		Service = service;
	}

	@XmlAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Parameter() {
		
	}
	public Parameter(String name) {
		super();
		this.name = name;
	}
	@XmlAttribute
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	
}
