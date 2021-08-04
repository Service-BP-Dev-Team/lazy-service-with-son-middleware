package cm.uds.fuchsia.gag.model.specification;

import javax.xml.bind.annotation.XmlAttribute;

public class Parameter {

	private String name;
	private String shortName;

	

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
