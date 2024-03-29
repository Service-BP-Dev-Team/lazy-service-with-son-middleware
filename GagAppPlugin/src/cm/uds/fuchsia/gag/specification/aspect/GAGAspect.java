package cm.uds.fuchsia.gag.specification.aspect;

import cm.uds.fuchsia.gag.configuration.aspect.ConfigurationAspect;
import cm.uds.fuchsia.gag.configuration.aspect.PendingLocalFunctionComputationAspect;
import cm.uds.fuchsia.gag.model.configuration.Configuration;
import cm.uds.fuchsia.gag.model.configuration.Data;
import cm.uds.fuchsia.gag.model.configuration.PendingLocalFunctionComputation;
import cm.uds.fuchsia.gag.model.configuration.Task;
import cm.uds.fuchsia.gag.model.specification.DecompositionRule;
import cm.uds.fuchsia.gag.model.specification.Equation;
import cm.uds.fuchsia.gag.model.specification.Expression;
import cm.uds.fuchsia.gag.model.specification.FunctionDeclaration;
import cm.uds.fuchsia.gag.model.specification.FunctionExpression;
import cm.uds.fuchsia.gag.model.specification.GAG;
import cm.uds.fuchsia.gag.model.specification.Guard;
import cm.uds.fuchsia.gag.model.specification.IdExpression;
import cm.uds.fuchsia.gag.model.specification.Parameter;
import cm.uds.fuchsia.gag.model.specification.RuntimeData;
import cm.uds.fuchsia.gag.model.specification.SemanticRule;
import cm.uds.fuchsia.gag.model.specification.Service;
import cm.uds.fuchsia.gag.network.Component;
import cm.uds.fuchsia.gag.network.Subscription;
import cm.uds.fuchsia.gag.specification.aspect.GuardAspect;
import cm.uds.fuchsia.gag.specification.aspect.OutputInterface;
import cm.uds.fuchsia.gag.util.Console;
import cm.uds.fuchsia.gag.util.EncapsulatedValue;
import com.google.common.base.Objects;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtext.xbase.lib.ExclusiveRange;

@SuppressWarnings("all")
public class GAGAspect extends GAG {
  private Component component;
  
  public Component getComponent() {
    return this.component;
  }
  
  public void setComponent(final Component comp) {
    this.component = comp;
  }
  
  public GAGAspect() {
  }
  
  public GAGAspect(final GAG g) {
    String _name = g.getName();
    this.setName(_name);
    RuntimeData _configuration = g.getConfiguration();
    this.setConfiguration(_configuration);
    ArrayList<Service> _services = g.getServices();
    this.setServices(_services);
  }
  
  public void initExecution() {
    RuntimeData _configuration = this.getConfiguration();
    boolean _tripleEquals = (_configuration == null);
    if (_tripleEquals) {
      Configuration _configuration_1 = new Configuration();
      this.setConfiguration(_configuration_1);
    }
  }
  
  public void run() {
    this.initExecution();
    RuntimeData _configuration = this.getConfiguration();
    final Configuration conf = ((Configuration) _configuration);
    this.chooseTheAxiom();
    Task _root = conf.getRoot();
    ArrayList<Task> openTask = this.getOpenTask(_root);
    int _size = openTask.size();
    boolean _notEquals = (_size != 0);
    boolean _while = _notEquals;
    while (_while) {
      {
        Task task = this.chooseTask(openTask);
        DecompositionRule rule = this.chooseRule(task);
        boolean _notEquals_1 = (!Objects.equal(rule, null));
        if (_notEquals_1) {
          this.applyRule(task, rule);
        }
        String _print = this.print(conf);
        String _plus = ("La configuration resultante est " + _print);
        Console.debug(_plus);
        Task _root_1 = conf.getRoot();
        ArrayList<Task> _openTask = this.getOpenTask(_root_1);
        openTask = _openTask;
      }
      int _size_1 = openTask.size();
      boolean _notEquals_1 = (_size_1 != 0);
      _while = _notEquals_1;
    }
    Console.debug("Ex�cution termin�e !");
  }
  
  public void runWithExternalOuputInterface(final OutputInterface OI) {
    this.initExecution();
    RuntimeData _configuration = this.getConfiguration();
    final Configuration conf = ((Configuration) _configuration);
    this.chooseTheAxiom();
    OI.update(this);
    Task _root = conf.getRoot();
    ArrayList<Task> openTask = this.getOpenTask(_root);
    int _size = openTask.size();
    boolean _notEquals = (_size != 0);
    boolean _while = _notEquals;
    while (_while) {
      {
        Task task = this.chooseTask(openTask);
        DecompositionRule rule = this.chooseRule(task);
        boolean _notEquals_1 = (!Objects.equal(rule, null));
        if (_notEquals_1) {
          this.applyRule(task, rule);
        }
        String _print = this.print(conf);
        String _plus = ("La configuration resultante est " + _print);
        Console.debug(_plus);
        OI.update(this);
        Task _root_1 = conf.getRoot();
        ArrayList<Task> _openTask = this.getOpenTask(_root_1);
        openTask = _openTask;
      }
      int _size_1 = openTask.size();
      boolean _notEquals_1 = (_size_1 != 0);
      _while = _notEquals_1;
    }
    Console.debug("Ex�cution termin�e !");
  }
  
  public ArrayList<Service> getAxioms() {
    final ArrayList<Service> services = this.getServices();
    final ArrayList<Service> axioms = new ArrayList<Service>();
    int _size = services.size();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size, true);
    for (final Integer i : _doubleDotLessThan) {
      {
        final Service element = services.get((i).intValue());
        Boolean _isAxiom = element.isAxiom();
        if ((_isAxiom).booleanValue()) {
          Service _get = services.get((i).intValue());
          axioms.add(_get);
        }
      }
    }
    return axioms;
  }
  
  public void chooseTheAxiom() {
    final ArrayList<Service> services = this.getServices();
    final ArrayList<Service> axioms = this.getAxioms();
    Console.debug("Veuillez choisir le service axiome de d�marrage parmi les services suivants : ");
    String txtAf = "";
    int _size = axioms.size();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size, true);
    for (final Integer i : _doubleDotLessThan) {
      {
        Service _get = axioms.get((i).intValue());
        final Service element = ((Service) _get);
        String _plus = (Integer.valueOf(((i).intValue() + 1)) + "- ");
        String _name = element.getName();
        String _plus_1 = (_plus + _name);
        Console.debug(_plus_1);
      }
    }
    final String choice = Console.readConsoleLine(txtAf);
    final int id = Integer.parseInt(choice);
    Service _get = axioms.get((id - 1));
    final Service serviceChoice = ((Service) _get);
    RuntimeData _configuration = this.getConfiguration();
    final Configuration conf = ((Configuration) _configuration);
    Task _createRootTask = this.createRootTask(serviceChoice);
    conf.setRoot(_createRootTask);
  }
  
  public Task createRootTask(final Service serviceChoice, final ArrayList<Object> inputParams) {
    Task root = new Task();
    root.setService(serviceChoice);
    Service _service = root.getService();
    ArrayList<Parameter> _inputParameters = _service.getInputParameters();
    int _size = _inputParameters.size();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size, true);
    for (final Integer i : _doubleDotLessThan) {
      {
        Data data = new Data();
        Service _service_1 = root.getService();
        ArrayList<Parameter> _inputParameters_1 = _service_1.getInputParameters();
        Parameter _get = _inputParameters_1.get((i).intValue());
        data.setParameter(_get);
        Object _get_1 = inputParams.get((i).intValue());
        final EncapsulatedValue ecD = new EncapsulatedValue(_get_1);
        ecD.setContainerRef(data);
        data.setValue(ecD);
        ArrayList<Data> _inputs = root.getInputs();
        _inputs.add(data);
      }
    }
    Service _service_1 = root.getService();
    ArrayList<Parameter> _outputParameters = _service_1.getOutputParameters();
    int _size_1 = _outputParameters.size();
    ExclusiveRange _doubleDotLessThan_1 = new ExclusiveRange(0, _size_1, true);
    for (final Integer i_1 : _doubleDotLessThan_1) {
      {
        Data data = new Data();
        Service _service_2 = root.getService();
        ArrayList<Parameter> _outputParameters_1 = _service_2.getOutputParameters();
        Parameter _get = _outputParameters_1.get((i_1).intValue());
        data.setParameter(_get);
        final EncapsulatedValue ecD = new EncapsulatedValue();
        ecD.setContainerRef(data);
        data.setValue(ecD);
        ArrayList<Data> _outputs = root.getOutputs();
        _outputs.add(data);
      }
    }
    return root;
  }
  
  public Task createRootTask(final Service serviceChoice) {
    Task root = new Task();
    root.setService(serviceChoice);
    Console.debug("Veuillez fournir les valeurs des entr�es de l\'axiome ");
    Service _service = root.getService();
    ArrayList<Parameter> _inputParameters = _service.getInputParameters();
    int _size = _inputParameters.size();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size, true);
    for (final Integer i : _doubleDotLessThan) {
      {
        Data data = new Data();
        Service _service_1 = root.getService();
        ArrayList<Parameter> _inputParameters_1 = _service_1.getInputParameters();
        Parameter _get = _inputParameters_1.get((i).intValue());
        data.setParameter(_get);
        Parameter _parameter = data.getParameter();
        String _name = _parameter.getName();
        String _plus = ("Veuillez entrer la valeur du param�tre " + _name);
        Console.debug(_plus);
        String _readConsoleLine = Console.readConsoleLine("");
        final EncapsulatedValue ecD = new EncapsulatedValue(_readConsoleLine);
        data.setValue(ecD);
        ecD.setContainerRef(data);
        ArrayList<Data> _inputs = root.getInputs();
        _inputs.add(data);
      }
    }
    Service _service_1 = root.getService();
    ArrayList<Parameter> _outputParameters = _service_1.getOutputParameters();
    int _size_1 = _outputParameters.size();
    ExclusiveRange _doubleDotLessThan_1 = new ExclusiveRange(0, _size_1, true);
    for (final Integer i_1 : _doubleDotLessThan_1) {
      {
        Data data = new Data();
        Service _service_2 = root.getService();
        ArrayList<Parameter> _outputParameters_1 = _service_2.getOutputParameters();
        Parameter _get = _outputParameters_1.get((i_1).intValue());
        data.setParameter(_get);
        final EncapsulatedValue ecD = new EncapsulatedValue();
        data.setValue(ecD);
        ecD.setContainerRef(data);
        ArrayList<Data> _outputs = root.getOutputs();
        _outputs.add(data);
      }
    }
    return root;
  }
  
  public Task chooseTask(final ArrayList<Task> openTasks) {
    Console.debug("Veuillez choisir la t�che � traiter parmi les t�ches suivantes : ");
    String txtAf = "";
    int _size = openTasks.size();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size, true);
    for (final Integer i : _doubleDotLessThan) {
      {
        final Task element = openTasks.get((i).intValue());
        String _plus = (Integer.valueOf(((i).intValue() + 1)) + "- ");
        Service _service = element.getService();
        String _name = _service.getName();
        String _plus_1 = (_plus + _name);
        Console.debug(_plus_1);
      }
    }
    final String choice = Console.readConsoleLine(txtAf);
    final int id = Integer.parseInt(choice);
    return openTasks.get((id - 1));
  }
  
  public DecompositionRule chooseRule(final Task t) {
    ArrayList<DecompositionRule> applicableRules = new ArrayList<DecompositionRule>();
    Service _service = t.getService();
    ArrayList<DecompositionRule> _rules = _service.getRules();
    int _size = _rules.size();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size, true);
    for (final Integer i : _doubleDotLessThan) {
      {
        Service _service_1 = t.getService();
        ArrayList<DecompositionRule> _rules_1 = _service_1.getRules();
        final DecompositionRule element = _rules_1.get((i).intValue());
        Guard guard = element.getGuard();
        boolean _or = false;
        boolean _equals = Objects.equal(guard, null);
        if (_equals) {
          _or = true;
        } else {
          boolean _isApplicable = this.isApplicable(guard, t);
          _or = _isApplicable;
        }
        if (_or) {
          applicableRules.add(element);
        }
      }
    }
    int _size_1 = applicableRules.size();
    boolean _notEquals = (_size_1 != 0);
    if (_notEquals) {
      Console.debug("Veuillez choisir la r�gle de d�composition � appliquer parmi les r�gles suivantes : ");
      String txtAf = "";
      int _size_2 = applicableRules.size();
      ExclusiveRange _doubleDotLessThan_1 = new ExclusiveRange(0, _size_2, true);
      for (final Integer i_1 : _doubleDotLessThan_1) {
        String _plus = (Integer.valueOf(((i_1).intValue() + 1)) + "- ");
        DecompositionRule _get = applicableRules.get((i_1).intValue());
        String _name = _get.getName();
        String _plus_1 = (_plus + _name);
        Console.debug(_plus_1);
      }
      final String choice = Console.readConsoleLine(txtAf);
      final int id = Integer.parseInt(choice);
      final DecompositionRule rule = applicableRules.get((id - 1));
      return rule;
    } else {
      Console.debug("Aucune r�gle de d�composition n\'est actuellement applicable pour cette t�che ");
      Console.readConsoleLine("");
      return null;
    }
  }
  
  public ArrayList<DecompositionRule> getApplicablesRules(final Task t) {
    ArrayList<DecompositionRule> applicableRules = new ArrayList<DecompositionRule>();
    Service _service = t.getService();
    ArrayList<DecompositionRule> _rules = _service.getRules();
    int _size = _rules.size();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size, true);
    for (final Integer i : _doubleDotLessThan) {
      {
        Service _service_1 = t.getService();
        ArrayList<DecompositionRule> _rules_1 = _service_1.getRules();
        final DecompositionRule element = _rules_1.get((i).intValue());
        Guard guard = element.getGuard();
        boolean _or = false;
        boolean _equals = Objects.equal(guard, null);
        if (_equals) {
          _or = true;
        } else {
          boolean _isApplicable = this.isApplicable(guard, t);
          _or = _isApplicable;
        }
        if (_or) {
          applicableRules.add(element);
        }
      }
    }
    return applicableRules;
  }
  
  public ArrayList<Task> getOpenTask(final Task root) {
    final ArrayList<Task> openTasks = new ArrayList<Task>();
    boolean _isOpen = root.isOpen();
    if (_isOpen) {
      openTasks.add(root);
    } else {
      ArrayList<Task> _subTasks = root.getSubTasks();
      int _size = _subTasks.size();
      ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size, true);
      for (final Integer i : _doubleDotLessThan) {
        {
          ArrayList<Task> _subTasks_1 = root.getSubTasks();
          final Task element = _subTasks_1.get((i).intValue());
          final ArrayList<Task> subOpenTasks = this.getOpenTask(element);
          openTasks.addAll(subOpenTasks);
        }
      }
    }
    return openTasks;
  }
  
  public ArrayList<Task> getAllTasks(final Task root) {
    Task myroot = root;
    boolean _tripleEquals = (root == null);
    if (_tripleEquals) {
      RuntimeData _configuration = this.getConfiguration();
      Task _root = ((Configuration) _configuration).getRoot();
      myroot = _root;
    }
    final ArrayList<Task> allTasks = new ArrayList<Task>();
    allTasks.add(myroot);
    ArrayList<Task> _subTasks = myroot.getSubTasks();
    int _size = _subTasks.size();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size, true);
    for (final Integer i : _doubleDotLessThan) {
      {
        ArrayList<Task> _subTasks_1 = myroot.getSubTasks();
        final Task element = _subTasks_1.get((i).intValue());
        final ArrayList<Task> subAllTasks = this.getAllTasks(element);
        allTasks.addAll(subAllTasks);
      }
    }
    return allTasks;
  }
  
  public void applyRule(final Task t, final DecompositionRule r) {
    String _name = r.getName();
    t.setAppliedRule(_name);
    t.setOpen(false);
    ArrayList<Service> _subServices = r.getSubServices();
    int _size = _subServices.size();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size, true);
    for (final Integer i : _doubleDotLessThan) {
      {
        ArrayList<Service> _subServices_1 = r.getSubServices();
        final Service element = _subServices_1.get((i).intValue());
        Task st = new Task();
        this.initTask(st, element);
        ArrayList<Task> _subTasks = t.getSubTasks();
        _subTasks.add(st);
      }
    }
    RuntimeData _configuration = this.getConfiguration();
    Configuration conf = ((Configuration) _configuration);
    ArrayList<Task> context = new ArrayList<Task>();
    context.add(t);
    ArrayList<Task> _subTasks = t.getSubTasks();
    context.addAll(_subTasks);
    SemanticRule _semantic = r.getSemantic();
    ArrayList<Equation> _equations = _semantic.getEquations();
    int _size_1 = _equations.size();
    ExclusiveRange _doubleDotLessThan_1 = new ExclusiveRange(0, _size_1, true);
    for (final Integer i_1 : _doubleDotLessThan_1) {
      {
        SemanticRule _semantic_1 = r.getSemantic();
        ArrayList<Equation> _equations_1 = _semantic_1.getEquations();
        Equation eq = _equations_1.get((i_1).intValue());
        IdExpression _leftpart = eq.getLeftpart();
        String _serviceName = _leftpart.getServiceName();
        IdExpression _leftpart_1 = eq.getLeftpart();
        String _parameterName = _leftpart_1.getParameterName();
        String[] ref1 = new String[] { _serviceName, _parameterName };
        Data data1 = this.findReference(ref1, context);
        Expression _rightpart = eq.getRightpart();
        if ((_rightpart instanceof IdExpression)) {
          Data data2 = ((Data) null);
          Expression _rightpart_1 = eq.getRightpart();
          final IdExpression rightPartIdExpression = ((IdExpression) _rightpart_1);
          String _serviceName_1 = rightPartIdExpression.getServiceName();
          String _parameterName_1 = rightPartIdExpression.getParameterName();
          final String[] ref2 = new String[] { _serviceName_1, _parameterName_1 };
          Data _findReference = this.findReference(ref2, context);
          data2 = _findReference;
          Object _value = data1.getValue();
          EncapsulatedValue ecData1 = ((EncapsulatedValue) _value);
          Object _value_1 = data2.getValue();
          ecData1.addReference(((EncapsulatedValue) _value_1));
        } else {
          Expression _rightpart_2 = eq.getRightpart();
          FunctionExpression func = ((FunctionExpression) _rightpart_2);
          Object _value_2 = data1.getValue();
          EncapsulatedValue ecData1_1 = ((EncapsulatedValue) _value_2);
          PendingLocalFunctionComputation runningFunction = new PendingLocalFunctionComputation();
          runningFunction.setDataToCompute(data1);
          FunctionDeclaration _function = func.getFunction();
          runningFunction.setFunctionDeclaration(_function);
          ArrayList<IdExpression> _idExpressions = func.getIdExpressions();
          int _size_2 = _idExpressions.size();
          ExclusiveRange _doubleDotLessThan_2 = new ExclusiveRange(0, _size_2, true);
          for (final Integer k : _doubleDotLessThan_2) {
            {
              ArrayList<IdExpression> _idExpressions_1 = func.getIdExpressions();
              IdExpression elId = _idExpressions_1.get((k).intValue());
              String _serviceName_2 = elId.getServiceName();
              String _parameterName_2 = elId.getParameterName();
              final String[] ref = new String[] { _serviceName_2, _parameterName_2 };
              Data data = this.findReference(ref, context);
              ArrayList<Data> _actualParameters = runningFunction.getActualParameters();
              _actualParameters.add(data);
            }
          }
          ArrayList<PendingLocalFunctionComputation> _pendingLocalComputations = conf.getPendingLocalComputations();
          _pendingLocalComputations.add(runningFunction);
        }
      }
    }
    ArrayList<PendingLocalFunctionComputation> _pendingLocalComputations = conf.getPendingLocalComputations();
    this.computeFunction(_pendingLocalComputations);
    boolean _notEquals = (!Objects.equal(this.component, null));
    if (_notEquals) {
      ArrayList<Task> _subTasks_1 = t.getSubTasks();
      int _size_2 = _subTasks_1.size();
      ExclusiveRange _doubleDotLessThan_2 = new ExclusiveRange(0, _size_2, true);
      for (final Integer i_2 : _doubleDotLessThan_2) {
        {
          ArrayList<Task> _subTasks_2 = t.getSubTasks();
          Task el = _subTasks_2.get((i_2).intValue());
          Service _service = el.getService();
          Boolean _isRemote = _service.isRemote();
          if ((_isRemote).booleanValue()) {
            this.component.sendTask(el);
          }
        }
      }
    }
    this.notifyComponents();
  }
  
  public Data findReference(final String[] ref, final ArrayList<Task> tasks) {
    Data objectRef = ((Data) null);
    String _get = ref[0];
    String _string = _get.toString();
    String serviceName = _string.trim();
    String _get_1 = ref[1];
    String _string_1 = _get_1.toString();
    String serviceParameter = _string_1.trim();
    Console.debug(((serviceName + ".") + serviceParameter));
    int _size = tasks.size();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size, true);
    for (final Integer i : _doubleDotLessThan) {
      {
        Task element = tasks.get((i).intValue());
        Service _service = element.getService();
        String _name = _service.getName();
        boolean _equals = _name.equals(serviceName);
        if (_equals) {
          ArrayList<Data> _inputs = element.getInputs();
          int _size_1 = _inputs.size();
          ExclusiveRange _doubleDotLessThan_1 = new ExclusiveRange(0, _size_1, true);
          for (final Integer j : _doubleDotLessThan_1) {
            ArrayList<Data> _inputs_1 = element.getInputs();
            Data _get_2 = _inputs_1.get((j).intValue());
            Parameter _parameter = _get_2.getParameter();
            String _name_1 = _parameter.getName();
            boolean _equals_1 = _name_1.equals(serviceParameter);
            if (_equals_1) {
              ArrayList<Data> _inputs_2 = element.getInputs();
              Data _get_3 = _inputs_2.get((j).intValue());
              objectRef = _get_3;
              Console.debug("i found");
            }
          }
          ArrayList<Data> _outputs = element.getOutputs();
          int _size_2 = _outputs.size();
          ExclusiveRange _doubleDotLessThan_2 = new ExclusiveRange(0, _size_2, true);
          for (final Integer j_1 : _doubleDotLessThan_2) {
            ArrayList<Data> _outputs_1 = element.getOutputs();
            Data _get_4 = _outputs_1.get((j_1).intValue());
            Parameter _parameter_1 = _get_4.getParameter();
            String _name_2 = _parameter_1.getName();
            boolean _equals_2 = _name_2.equals(serviceParameter);
            if (_equals_2) {
              ArrayList<Data> _outputs_2 = element.getOutputs();
              Data _get_5 = _outputs_2.get((j_1).intValue());
              objectRef = _get_5;
              Console.debug("i found");
            }
          }
        }
      }
    }
    return objectRef;
  }
  
  public void computeFunction(final List<PendingLocalFunctionComputation> runningFunctions) {
    ArrayList<PendingLocalFunctionComputation> executableFunctions = new ArrayList<PendingLocalFunctionComputation>();
    int _size = runningFunctions.size();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size, true);
    for (final Integer i : _doubleDotLessThan) {
      {
        PendingLocalFunctionComputation func = runningFunctions.get((i).intValue());
        boolean _and = false;
        Boolean _isExecutable = this.isExecutable(func);
        if (!(_isExecutable).booleanValue()) {
          _and = false;
        } else {
          boolean _isTerminated = func.isTerminated();
          boolean _not = (!_isTerminated);
          _and = _not;
        }
        if (_and) {
          executableFunctions.add(func);
        }
      }
    }
    int _size_1 = executableFunctions.size();
    boolean _notEquals = (_size_1 != 0);
    boolean _while = _notEquals;
    while (_while) {
      {
        int _size_2 = executableFunctions.size();
        ExclusiveRange _doubleDotLessThan_1 = new ExclusiveRange(0, _size_2, true);
        for (final Integer i_1 : _doubleDotLessThan_1) {
          {
            PendingLocalFunctionComputation elFunc = executableFunctions.get((i_1).intValue());
            Object result = this.execute(elFunc);
            elFunc.setTerminated(true);
            Data _dataToCompute = elFunc.getDataToCompute();
            Object _value = _dataToCompute.getValue();
            EncapsulatedValue ecObj = ((EncapsulatedValue) _value);
            ecObj.setValue(result);
          }
        }
        ArrayList<PendingLocalFunctionComputation> _arrayList = new ArrayList<PendingLocalFunctionComputation>();
        executableFunctions = _arrayList;
        int _size_3 = runningFunctions.size();
        ExclusiveRange _doubleDotLessThan_2 = new ExclusiveRange(0, _size_3, true);
        for (final Integer i_2 : _doubleDotLessThan_2) {
          {
            PendingLocalFunctionComputation func = runningFunctions.get((i_2).intValue());
            boolean _and = false;
            Boolean _isExecutable = this.isExecutable(func);
            if (!(_isExecutable).booleanValue()) {
              _and = false;
            } else {
              boolean _isTerminated = func.isTerminated();
              boolean _not = (!_isTerminated);
              _and = _not;
            }
            if (_and) {
              executableFunctions.add(func);
            }
          }
        }
      }
      int _size_2 = executableFunctions.size();
      boolean _notEquals_1 = (_size_2 != 0);
      _while = _notEquals_1;
    }
  }
  
  public void initTask(final Task t, final Service s) {
    t.setService(s);
    t.setOpen(true);
    ArrayList<Parameter> _inputParameters = s.getInputParameters();
    int _size = _inputParameters.size();
    ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size, true);
    for (final Integer i : _doubleDotLessThan) {
      {
        Data data = new Data();
        ArrayList<Parameter> _inputParameters_1 = s.getInputParameters();
        Parameter _get = _inputParameters_1.get((i).intValue());
        data.setParameter(_get);
        final EncapsulatedValue ecD = new EncapsulatedValue();
        data.setValue(ecD);
        ecD.setContainerRef(data);
        ArrayList<Data> _inputs = t.getInputs();
        _inputs.add(data);
      }
    }
    ArrayList<Parameter> _outputParameters = s.getOutputParameters();
    int _size_1 = _outputParameters.size();
    ExclusiveRange _doubleDotLessThan_1 = new ExclusiveRange(0, _size_1, true);
    for (final Integer i_1 : _doubleDotLessThan_1) {
      {
        Data data = new Data();
        ArrayList<Parameter> _outputParameters_1 = s.getOutputParameters();
        Parameter _get = _outputParameters_1.get((i_1).intValue());
        data.setParameter(_get);
        final EncapsulatedValue ecD = new EncapsulatedValue();
        data.setValue(ecD);
        ecD.setContainerRef(data);
        ArrayList<Data> _outputs = t.getOutputs();
        _outputs.add(data);
      }
    }
  }
  
  public void notifyComponents() {
    boolean _notEquals = (!Objects.equal(this.component, null));
    if (_notEquals) {
      ArrayList<Subscription> array = new ArrayList<Subscription>();
      ArrayList<Subscription> _subscriptionList = this.component.getSubscriptionList();
      array.addAll(_subscriptionList);
      int _size = array.size();
      ExclusiveRange _doubleDotLessThan = new ExclusiveRange(0, _size, true);
      for (final Integer i : _doubleDotLessThan) {
        {
          Subscription sub = array.get((i).intValue());
          Data data = sub.getData();
          Object _value = data.getValue();
          EncapsulatedValue ecData = ((EncapsulatedValue) _value);
          boolean _isNull = ecData.isNull();
          boolean _not = (!_isNull);
          if (_not) {
            this.component.sendNotification(sub);
          }
        }
      }
    }
  }
  
  public boolean isApplicable(final Guard guard, final Task task) {
    GuardAspect guardAspect = new GuardAspect(guard);
    return guardAspect.isApplicable(task);
  }
  
  public Boolean isExecutable(final PendingLocalFunctionComputation computation) {
    PendingLocalFunctionComputationAspect _pendingLocalFunctionComputationAspect = new PendingLocalFunctionComputationAspect(computation);
    return Boolean.valueOf(_pendingLocalFunctionComputationAspect.isExecutable());
  }
  
  public Object execute(final PendingLocalFunctionComputation computation) {
    PendingLocalFunctionComputationAspect _pendingLocalFunctionComputationAspect = new PendingLocalFunctionComputationAspect(computation);
    return _pendingLocalFunctionComputationAspect.execute();
  }
  
  public String print(final Configuration configuration) {
    ConfigurationAspect _configurationAspect = new ConfigurationAspect(configuration);
    return _configurationAspect.print();
  }
}
