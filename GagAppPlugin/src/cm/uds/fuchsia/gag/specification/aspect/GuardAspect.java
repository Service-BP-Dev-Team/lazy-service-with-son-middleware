package cm.uds.fuchsia.gag.specification.aspect;

import cm.uds.fuchsia.gag.configuration.aspect.PendingLocalFunctionComputationAspect;
import cm.uds.fuchsia.gag.model.configuration.Data;
import cm.uds.fuchsia.gag.model.configuration.PendingLocalFunctionComputation;
import cm.uds.fuchsia.gag.model.configuration.Task;
import cm.uds.fuchsia.gag.model.specification.FunctionDeclaration;
import cm.uds.fuchsia.gag.model.specification.Guard;
import java.util.ArrayList;
import org.eclipse.xtext.xbase.lib.ExclusiveRange;

@SuppressWarnings("all")
public class GuardAspect extends Guard {
  public GuardAspect() {
  }
  
  public GuardAspect(final Guard g) {
    String _location = g.getLocation();
    this.setLocation(_location);
    String _method = g.getMethod();
    this.setMethod(_method);
  }
  
  public boolean isApplicable(final Task t) {
    boolean result = false;
    FunctionDeclaration funcDec = new FunctionDeclaration();
    String _location = this.getLocation();
    funcDec.setLocation(_location);
    String _method = this.getMethod();
    funcDec.setMethod(_method);
    PendingLocalFunctionComputation funcCall = new PendingLocalFunctionComputation();
    funcCall.setFunctionDeclaration(funcDec);
    ArrayList<Data> _inputs = t.getInputs();
    int _size = _inputs.size();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size, true);
    for (final Integer i : _doubleDotLessThan) {
      ArrayList<Data> _actualParameters = funcCall.getActualParameters();
      ArrayList<Data> _inputs_1 = t.getInputs();
      Data _get = _inputs_1.get((i).intValue());
      _actualParameters.add(_get);
    }
    Object _execute = this.execute(funcCall);
    result = (((Boolean) _execute)).booleanValue();
    return result;
  }
  
  public Boolean isExecutable(final PendingLocalFunctionComputation computation) {
    PendingLocalFunctionComputationAspect _pendingLocalFunctionComputationAspect = new PendingLocalFunctionComputationAspect(computation);
    return Boolean.valueOf(_pendingLocalFunctionComputationAspect.isExecutable());
  }
  
  public Object execute(final PendingLocalFunctionComputation computation) {
    PendingLocalFunctionComputationAspect _pendingLocalFunctionComputationAspect = new PendingLocalFunctionComputationAspect(computation);
    return _pendingLocalFunctionComputationAspect.execute();
  }
}
