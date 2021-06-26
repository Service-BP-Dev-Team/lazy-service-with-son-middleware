package cm.uds.fuchsia.gag.configuration.aspect;

import cm.uds.fuchsia.gag.configuration.aspect.DataAspect;
import cm.uds.fuchsia.gag.model.configuration.Data;
import cm.uds.fuchsia.gag.model.configuration.Task;
import cm.uds.fuchsia.gag.model.specification.Service;
import java.util.ArrayList;
import org.eclipse.xtext.xbase.lib.ExclusiveRange;

@SuppressWarnings("all")
public class TaskAspect extends Task {
  public TaskAspect() {
  }
  
  public TaskAspect(final Task t) {
    String _appliedRule = t.getAppliedRule();
    this.setAppliedRule(_appliedRule);
    ArrayList<Data> _inputs = t.getInputs();
    this.setInputs(_inputs);
    ArrayList<Data> _outputs = t.getOutputs();
    this.setOutputs(_outputs);
    ArrayList<Task> _subTasks = t.getSubTasks();
    this.setSubTasks(_subTasks);
    Service _service = t.getService();
    this.setService(_service);
    boolean _isOpen = t.isOpen();
    this.setOpen(_isOpen);
  }
  
  public String print() {
    String conf = "";
    String _conf = conf;
    Service _service = this.getService();
    String _name = _service.getName();
    String _plus = (_name + "(");
    conf = (_conf + _plus);
    boolean _isOpen = this.isOpen();
    boolean _not = (!_isOpen);
    if (_not) {
      String _conf_1 = conf;
      String _appliedRule = this.getAppliedRule();
      String _plus_1 = (" appliedRule=" + _appliedRule);
      conf = (_conf_1 + _plus_1);
      String _conf_2 = conf;
      conf = (_conf_2 + ")[");
      int i = 1;
      ArrayList<Task> _subTasks = this.getSubTasks();
      for (final Task t : _subTasks) {
        {
          String _conf_3 = conf;
          String _print = this.print(t);
          conf = (_conf_3 + _print);
          ArrayList<Task> _subTasks_1 = this.getSubTasks();
          int _size = _subTasks_1.size();
          boolean _lessThan = (i < _size);
          if (_lessThan) {
            String _conf_4 = conf;
            conf = (_conf_4 + ", ");
          }
        }
      }
      String _conf_3 = conf;
      conf = (_conf_3 + "]");
    } else {
      ArrayList<Data> _inputs = this.getInputs();
      int _size = _inputs.size();
      ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size, true);
      for (final Integer i_1 : _doubleDotLessThan) {
        {
          ArrayList<Data> _inputs_1 = this.getInputs();
          final Data el = _inputs_1.get((i_1).intValue());
          String _conf_4 = conf;
          String _print = this.print(el);
          conf = (_conf_4 + _print);
          ArrayList<Data> _inputs_2 = this.getInputs();
          int _size_1 = _inputs_2.size();
          int _minus = (_size_1 - 1);
          boolean _lessThan = ((i_1).intValue() < _minus);
          if (_lessThan) {
            String _conf_5 = conf;
            conf = (_conf_5 + ", ");
          }
        }
      }
      String _conf_4 = conf;
      conf = (_conf_4 + ")[");
      ArrayList<Data> _outputs = this.getOutputs();
      int _size_1 = _outputs.size();
      ExclusiveRange _doubleDotLessThan_1 = new ExclusiveRange(0, _size_1, true);
      for (final Integer i_2 : _doubleDotLessThan_1) {
        {
          ArrayList<Data> _outputs_1 = this.getOutputs();
          final Data el = _outputs_1.get((i_2).intValue());
          String _conf_5 = conf;
          String _print = this.print(el);
          conf = (_conf_5 + _print);
          ArrayList<Data> _outputs_2 = this.getOutputs();
          int _size_2 = _outputs_2.size();
          int _minus = (_size_2 - 1);
          boolean _lessThan = ((i_2).intValue() < _minus);
          if (_lessThan) {
            String _conf_6 = conf;
            conf = (_conf_6 + ", ");
          }
        }
      }
      String _conf_5 = conf;
      conf = (_conf_5 + "]");
    }
    return conf;
  }
  
  public String prettyPrint() {
    String result = "(";
    ArrayList<Data> _outputs = this.getOutputs();
    int _size = _outputs.size();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size, true);
    for (final Integer i : _doubleDotLessThan) {
      {
        String _result = result;
        ArrayList<Data> _outputs_1 = this.getOutputs();
        Data _get = _outputs_1.get((i).intValue());
        String _name = _get.getName();
        result = (_result + _name);
        ArrayList<Data> _outputs_2 = this.getOutputs();
        int _size_1 = _outputs_2.size();
        int _minus = (_size_1 - 1);
        boolean _notEquals = ((i).intValue() != _minus);
        if (_notEquals) {
          String _result_1 = result;
          result = (_result_1 + ",");
        }
      }
    }
    String _result = result;
    Service _service = this.getService();
    String _name = _service.getName();
    String _plus = (") = " + _name);
    String _plus_1 = (_plus + "(");
    result = (_result + _plus_1);
    ArrayList<Data> _inputs = this.getInputs();
    int _size_1 = _inputs.size();
    ExclusiveRange _doubleDotLessThan_1 = new ExclusiveRange(0, _size_1, true);
    for (final Integer i_1 : _doubleDotLessThan_1) {
      {
        String _result_1 = result;
        ArrayList<Data> _inputs_1 = this.getInputs();
        Data _get = _inputs_1.get((i_1).intValue());
        String _name_1 = _get.getName();
        result = (_result_1 + _name_1);
        ArrayList<Data> _inputs_2 = this.getInputs();
        int _size_2 = _inputs_2.size();
        int _minus = (_size_2 - 1);
        boolean _notEquals = ((i_1).intValue() != _minus);
        if (_notEquals) {
          String _result_2 = result;
          result = (_result_2 + ",");
        }
      }
    }
    String _result_1 = result;
    result = (_result_1 + ")");
    return result;
  }
  
  public String print(final Task task) {
    TaskAspect _taskAspect = new TaskAspect(task);
    return _taskAspect.print();
  }
  
  public String print(final Data data) {
    DataAspect _dataAspect = new DataAspect(data);
    return _dataAspect.print();
  }
}
