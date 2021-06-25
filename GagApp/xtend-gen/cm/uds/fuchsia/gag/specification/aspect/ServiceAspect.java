package cm.uds.fuchsia.gag.specification.aspect;

import cm.uds.fuchsia.gag.model.specification.DecompositionRule;
import cm.uds.fuchsia.gag.model.specification.Parameter;
import cm.uds.fuchsia.gag.model.specification.Service;
import java.util.ArrayList;

@SuppressWarnings("all")
public class ServiceAspect extends Service {
  public ServiceAspect() {
  }
  
  public ServiceAspect(final Service s) {
    String _name = s.getName();
    this.setName(_name);
    Boolean _isAxiom = s.isAxiom();
    this.setAxiom(_isAxiom);
    ArrayList<Parameter> _inputParameters = s.getInputParameters();
    this.setInputParameters(_inputParameters);
    ArrayList<Parameter> _outputParameters = s.getOutputParameters();
    this.setOutputParameters(_outputParameters);
    ArrayList<DecompositionRule> _rules = s.getRules();
    this.setRules(_rules);
  }
}
