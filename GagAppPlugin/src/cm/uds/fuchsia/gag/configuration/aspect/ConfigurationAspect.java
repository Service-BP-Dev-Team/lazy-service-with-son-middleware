package cm.uds.fuchsia.gag.configuration.aspect;

import cm.uds.fuchsia.gag.configuration.aspect.TaskAspect;
import cm.uds.fuchsia.gag.model.configuration.Configuration;
import cm.uds.fuchsia.gag.model.configuration.PendingLocalFunctionComputation;
import cm.uds.fuchsia.gag.model.configuration.Task;
import java.util.ArrayList;

@SuppressWarnings("all")
public class ConfigurationAspect extends Configuration {
  public ConfigurationAspect() {
  }
  
  public ConfigurationAspect(final Configuration conf) {
    ArrayList<PendingLocalFunctionComputation> _pendingLocalComputations = conf.getPendingLocalComputations();
    this.setPendingLocalComputations(_pendingLocalComputations);
    Task _root = conf.getRoot();
    this.setRoot(_root);
  }
  
  public String print() {
    Task _root = this.getRoot();
    return this.print(_root);
  }
  
  public String print(final Task task) {
    TaskAspect _taskAspect = new TaskAspect(task);
    return _taskAspect.print();
  }
}
