package cm.uds.fuchsia.gag.configuration.aspect;

import cm.uds.fuchsia.gag.model.configuration.Data;
import cm.uds.fuchsia.gag.model.configuration.PendingLocalFunctionComputation;
import cm.uds.fuchsia.gag.model.specification.FunctionDeclaration;
import cm.uds.fuchsia.gag.util.EncapsulatedValue;
import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyShell;
import java.util.ArrayList;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.ExclusiveRange;
import org.eclipse.xtext.xbase.lib.InputOutput;

@SuppressWarnings("all")
public class PendingLocalFunctionComputationAspect extends PendingLocalFunctionComputation {
  private static String classPath = "E:\\PhD Recherche\\Implementation\\workspace-java\\GagApp\\bin";
  
  public PendingLocalFunctionComputationAspect() {
  }
  
  public PendingLocalFunctionComputationAspect(final PendingLocalFunctionComputation pend) {
    FunctionDeclaration _functionDeclaration = pend.getFunctionDeclaration();
    this.setFunctionDeclaration(_functionDeclaration);
    ArrayList<Data> _actualParameters = pend.getActualParameters();
    this.setActualParameters(_actualParameters);
    Data _dataToCompute = pend.getDataToCompute();
    this.setDataToCompute(_dataToCompute);
  }
  
  public boolean isExecutable() {
    boolean isexc = true;
    ArrayList<Data> _actualParameters = this.getActualParameters();
    int _size = _actualParameters.size();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size, true);
    for (final Integer i : _doubleDotLessThan) {
      {
        ArrayList<Data> _actualParameters_1 = this.getActualParameters();
        Data data = _actualParameters_1.get((i).intValue());
        Object _value = data.getValue();
        EncapsulatedValue ecData = ((EncapsulatedValue) _value);
        boolean _isNull = ecData.isNull();
        if (_isNull) {
          isexc = false;
        }
      }
    }
    return isexc;
  }
  
  public Object execute() {
    Binding binding = new Binding();
    Object res = ((Object) null);
    try {
      InputOutput.<String>println("run code");
      final GroovyShell shell = new GroovyShell(binding);
      GroovyClassLoader cl = shell.getClassLoader();
      FunctionDeclaration _functionDeclaration = this.getFunctionDeclaration();
      String _location = _functionDeclaration.getLocation();
      cl.addClasspath(_location);
      String params = "(";
      ArrayList<Data> _actualParameters = this.getActualParameters();
      int _size = _actualParameters.size();
      ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size, true);
      for (final Integer i : _doubleDotLessThan) {
        {
          ArrayList<Data> _actualParameters_1 = this.getActualParameters();
          Data d = _actualParameters_1.get((i).intValue());
          Object _value = d.getValue();
          EncapsulatedValue ecD = ((EncapsulatedValue) _value);
          Object _value_1 = ecD.getValue();
          binding.setVariable(("data" + i), _value_1);
          if (((i).intValue() != 0)) {
            String _params = params;
            params = (_params + ",");
          }
          String _params_1 = params;
          params = (_params_1 + ("data" + i));
        }
      }
      String _params = params;
      params = (_params + ")");
      FunctionDeclaration _functionDeclaration_1 = this.getFunctionDeclaration();
      String _method = _functionDeclaration_1.getMethod();
      String stringToExecute = (_method + params);
      Object _evaluate = shell.evaluate(stringToExecute);
      res = _evaluate;
    } catch (final Throwable _t) {
      if (_t instanceof Exception) {
        final Exception cnfe = (Exception)_t;
        String _message = cnfe.getMessage();
        String _plus = ("Failed to call Groovy script " + _message);
        InputOutput.<String>println(_plus);
        cnfe.printStackTrace();
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    return res;
  }
  
  public String prettyPrint() {
    FunctionDeclaration _functionDeclaration = this.getFunctionDeclaration();
    String _name = _functionDeclaration.getName();
    String _plus = ("_ " + _name);
    String result = (_plus + "(");
    ArrayList<Data> _actualParameters = this.getActualParameters();
    int _size = _actualParameters.size();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size, true);
    for (final Integer i : _doubleDotLessThan) {
      {
        String _result = result;
        ArrayList<Data> _actualParameters_1 = this.getActualParameters();
        Data _get = _actualParameters_1.get((i).intValue());
        String _fullDisplayName = _get.getFullDisplayName();
        result = (_result + _fullDisplayName);
        ArrayList<Data> _actualParameters_2 = this.getActualParameters();
        int _size_1 = _actualParameters_2.size();
        int _minus = (_size_1 - 1);
        boolean _notEquals = ((i).intValue() != _minus);
        if (_notEquals) {
          String _result_1 = result;
          result = (_result_1 + ",");
        }
      }
    }
    String _result = result;
    result = (_result + ")");
    return result;
  }
}
