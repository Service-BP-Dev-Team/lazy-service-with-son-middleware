package cm.uds.fuchsia.gag.configuration.aspect;

import cm.uds.fuchsia.gag.model.configuration.Data;
import cm.uds.fuchsia.gag.model.specification.Parameter;
import cm.uds.fuchsia.gag.util.EncapsulatedValue;

@SuppressWarnings("all")
public class DataAspect extends Data {
  public DataAspect() {
  }
  
  public DataAspect(final Data d) {
    Parameter _parameter = d.getParameter();
    this.setParameter(_parameter);
    Object _value = d.getValue();
    this.setValue(_value);
  }
  
  public String print() {
    String stToPrint = "?";
    Object _value = this.getValue();
    EncapsulatedValue encVal = ((EncapsulatedValue) _value);
    boolean _isNull = encVal.isNull();
    boolean _not = (!_isNull);
    if (_not) {
      Object _value_1 = encVal.getValue();
      String _string = _value_1.toString();
      stToPrint = _string;
    }
    Parameter _parameter = this.getParameter();
    String _name = _parameter.getName();
    String _plus = (_name + "=");
    return (_plus + stToPrint);
  }
}
