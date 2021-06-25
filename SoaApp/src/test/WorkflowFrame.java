package test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;


import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;

import casemanagement.Case;
import gag.behaviour.GAGProcessList;
import gag.Artefact;
import gag.Rule;
import gag.ServiceNode;
import gag.Subscription;
import gag.Term;
import gag.behaviour.GAGProcess;
import gag.behaviour.GAGProcessListener;
import gag.behaviour.GAGSpecification;
import gag.behaviour.SubscriptionTable;
import gag.son.SONAdaptator;
import gag.son.SONAdaptatorListener;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import soaApp.SoaApp;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkflowFrame extends JFrame implements GAGProcessListener,SONAdaptatorListener {

	private JPanel contentPane;
	private JPanel panProcess;
	private JPanel panCase;

	private GAGProcessList caseList= new GAGProcessList();
	private SoaApp soaApp;
	private JPanel panData;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WorkflowFrame frame = new WorkflowFrame(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WorkflowFrame(SoaApp soaApp) {
		this.soaApp=soaApp;
		
		soaApp.getOrchestrator().getListeners().add(this);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 547);
		/*  try {
	            UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaWhiteVisionLookAndFeel");
	        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
	            Logger.getLogger(WorkflowFrame.class.getName()).log(Level.SEVERE, null, ex);
	        } catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		  setTitle(soaApp.getIdName());
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFichier = new JMenu("File");
		menuBar.add(mnFichier);
		
		JMenuItem mntmOuvrirUnComposant = new JMenuItem("Open a gag component");
		mnFichier.add(mntmOuvrirUnComposant);
		
		JMenuItem mntmOuvrirUnProcessus = new JMenuItem("Open a process");
		mnFichier.add(mntmOuvrirUnProcessus);
		
		JMenuItem mntmEnregistrer = new JMenuItem("Save");
		mnFichier.add(mntmEnregistrer);
		
		JMenuItem mntmEnregistrerSous = new JMenuItem("Save as");
		mnFichier.add(mntmEnregistrerSous);
		
		JMenuItem mntmQui = new JMenuItem("Exit");
		mnFichier.add(mntmQui);
		
		JMenu mnOutils = new JMenu("Tools");
		menuBar.add(mnOutils);
		
		JMenu mnAide = new JMenu("Help ?");
		menuBar.add(mnAide);
		
		JMenu mnAPropos = new JMenu("About");
		menuBar.add(mnAPropos);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel toolsBar = new JPanel();
		contentPane.add(toolsBar, BorderLayout.NORTH);
		GridBagLayout gbl_toolsBar = new GridBagLayout();
		gbl_toolsBar.columnWidths = new int[]{34, 34, 0, 0, 0, 0};
		gbl_toolsBar.rowHeights = new int[]{23, 0};
		gbl_toolsBar.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_toolsBar.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		toolsBar.setLayout(gbl_toolsBar);
		
		JButton btnSave = new JButton("Save");
		btnSave.setPreferredSize(new Dimension(30, 30));
		btnSave.setLabel("");
		
		btnSave.setIcon(new ImageIcon(WorkflowFrame.class.getResource("/icons/png/save_edit.png")));
		btnSave.setFocusable(false);
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnSave.gridx = 0;
		gbc_btnSave.gridy = 0;
		toolsBar.add(btnSave, gbc_btnSave);
		
		JButton btnNouveau = new JButton("New");
		btnNouveau.setIcon(new ImageIcon(WorkflowFrame.class.getResource("/icons/png/newprj_wiz.png")));
		btnNouveau.setLabel("");
		btnNouveau.setPreferredSize(new Dimension(30, 30));
		btnNouveau.setFocusable(false);
		GridBagConstraints gbc_btnNouveau = new GridBagConstraints();
		gbc_btnNouveau.insets = new Insets(0, 0, 0, 5);
		gbc_btnNouveau.gridx = 1;
		gbc_btnNouveau.gridy = 0;
		toolsBar.add(btnNouveau, gbc_btnNouveau);
		
		JButton btnChargegag = new JButton("chargeGag");
		btnChargegag.setIcon(new ImageIcon(WorkflowFrame.class.getResource("/icons/png/prj_obj.png")));
		
		btnChargegag.setLabel("");
		btnChargegag.setPreferredSize(new Dimension(30, 30));
		btnChargegag.setFocusable(false);
		GridBagConstraints gbc_btnChargegag = new GridBagConstraints();
		gbc_btnChargegag.insets = new Insets(0, 0, 0, 5);
		gbc_btnChargegag.gridx = 2;
		gbc_btnChargegag.gridy = 0;
		toolsBar.add(btnChargegag, gbc_btnChargegag);
		
		JButton btnNotification = new JButton("(0)");
		btnNotification.setIcon(new ImageIcon(WorkflowFrame.class.getResource("/icons/png/bell.png")));
		
		btnNotification.setPreferredSize(new Dimension(80, 30));
		btnNotification.setFocusable(false);
		GridBagConstraints gbc_btnNotification = new GridBagConstraints();
		gbc_btnNotification.insets = new Insets(0, 0, 0, 5);
		gbc_btnNotification.gridx = 3;
		gbc_btnNotification.gridy = 0;
		toolsBar.add(btnNotification, gbc_btnNotification);
		
		JButton btnEnvoyerUnDossier = new JButton("Submit a thesis");
		GridBagConstraints gbc_btnEnvoyerUnDossier = new GridBagConstraints();
		gbc_btnEnvoyerUnDossier.gridx = 4;
		gbc_btnEnvoyerUnDossier.gridy = 0;
		btnEnvoyerUnDossier.setPreferredSize(new Dimension(170, 30));
		btnEnvoyerUnDossier.setFocusable(false);
		if(soaApp.getIdName().equals("student")){
		toolsBar.add(btnEnvoyerUnDossier, gbc_btnEnvoyerUnDossier);
		}
		JPanel menu = new JPanel();
		menu.setBorder(new TitledBorder(null, "MENU", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		menu.setPreferredSize(new Dimension(200, 400));
		contentPane.add(menu, BorderLayout.WEST);
		menu.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		menu.add(scrollPane, BorderLayout.CENTER);
		//GAGSpecification gag= GAGSpecification.readGAGSpecificationFromResources("/resources/gagdepartement.json");
		GAGSpecification gag= soaApp.getOrchestrator().getGAGSpecication();
		//Gson gson = new GsonBuilder().create();
		//GAGSpecification gag= new GAGSpecification();
		
		//gag.getRules().add(Test.departmentGAGSpecification());
		//String json=gson.toJson(gag);
		//System.out.println(json);
		//GAGSpecification gspec= (GAGSpecification)gson.fromJson(json, GAGSpecification.class);
		//gspec.print();
		JTree tree = GraphicsTools.getJTree(gag, soaApp.getIdName());
		scrollPane.setViewportView(tree);
		
		JPanel body = new JPanel();
		contentPane.add(body, BorderLayout.CENTER);
		body.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		body.add(tabbedPane, BorderLayout.CENTER);
		panCase= new JPanel();
		panProcess= new JPanel();
		panData= new JPanel();
		panProcess.setBackground(Color.WHITE);
		tabbedPane.add("Applications",panCase);
		tabbedPane.add("Process",panProcess);
		tabbedPane.add("Subscriptions",panData);
		//testGraphics();
		insertCaseManagementListInPanel();
		btnEnvoyerUnDossier.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				DialogSendCase dialog = new DialogSendCase();
				dialog.setOrchestrator(WorkflowFrame.this.soaApp.getOrchestrator());
				dialog.setVisible(true);
			}
		});
		setVisible(true);
		
	}
	
	
	public static Artefact startArtefact(){
		Artefact a = new Artefact();
		ServiceNode departementService= new ServiceNode();
		departementService.setName("Service du département");
		departementService.setLocation("département");
		Term dossier= new Term();
		dossier.setName("dossier");
		dossier.setValue(Case.createTestCase());
		Term traitement= new Term();
		traitement.setName("traitement");
		ArrayList<Term> departementServiceIn= new ArrayList<Term>();
		departementServiceIn.add(dossier);
		ArrayList<Term> departementServiceOut= new ArrayList<Term>();
		departementServiceOut.add(traitement);
		departementService.setIn(departementServiceIn);
		departementService.setOut(departementServiceOut);
		a.setNode(departementService);
		return a;
	}
	
	public  void testGraphics() {
		GAGProcess p=new GAGProcess();
		GAGSpecification gag= GAGSpecification.readGAGSpecificationFromFile("resources/gagdepartement.json");
		p.setArtefact(startArtefact());
		p.setGagSpecification(gag);
		SubscriptionTable LS = new SubscriptionTable();
		p.setSubscriptionTable(LS);
		Subscription sub= new Subscription();
		sub.setLocation("étudiant");
		sub.setVariableName("traitement");
		LS.addSubscription(sub);
		Rule rd=gag.getRules().get(0).clone();
		Term param= new Term();
		param.setName("traitement département");
		//param.setName("raison rejet");
		param.setValue("Hum");
		rd.setAParameter(param);
		rd.print();
		p.getListeners().add(this);
		p.setOrchestrator(new SONAdaptator());
		//p.getArtefact().match(rd, new ArrayList<Term>(), p.getSubscriptionTable(), new SONAdaptator());
		StructuredLayout layout = new StructuredLayout();
		layout.dispose(panProcess, p);
		caseList.getList().add(p);
		
	}
	
	public void insertCaseManagementListInPanel() {
		panCase.removeAll();
		JTable tCase=null;
		if(soaApp.getIdName().equals("étudiant")){
			tCase=GraphicsTools.renderForStudent(caseList);
		}else{
		    tCase=GraphicsTools.render(caseList);	
		}
		panCase.setLayout(new BorderLayout());
		panCase.add(new JScrollPane(tCase));
		panCase.validate();
	}

	@Override
	public void notify(GAGProcess process) {
		// TODO Auto-generated method stub
		StructuredLayout layout = new StructuredLayout();
		StructuredDataLayout layoutData = new StructuredDataLayout();
		layout.dispose(panProcess, process);
		layoutData.dispose(panData, process, process.getArtefact().getDistantData(soaApp.getIdName()));
	}

	@Override
	public void update(GAGProcessList processList) {
		// TODO Auto-generated method stub
		caseList=processList;
		if(processList.getList().size()>0) {
			notify(processList.getList().get(0));
		}
		insertCaseManagementListInPanel();
	}

	@Override
	public void updateATerm(String sender, Term t) {
		// TODO Auto-generated method stub
		
	}

	public SoaApp getSoaApp() {
		return soaApp;
	}

	public void setSoaApp(SoaApp soaApp) {
		this.soaApp = soaApp;
	}

}
