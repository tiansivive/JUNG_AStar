package gui;

import gui.mouse.InteractiveModalGraphMouse;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.xml.parsers.ParserConfigurationException;

import magicNumbers.Values;

import org.apache.commons.collections15.Transformer;
import org.xml.sax.SAXException;

import utilities.EdgeLabel;
import utilities.GraphML_FileExtensionFilter;
import utilities.enums.EdgeShapeType;
import utilities.enums.VertexType;
import utilities.factories.GUI_EdgeFactory;
import utilities.factories.GUI_VertexFactory;
import utilities.factories.Load_EdgeFactory;
import utilities.factories.Load_VertexFactory;
import utilities.transformers.EdgeLabellerTransformer;
import utilities.transformers.GUI_EdgeColoringTransformer;
import utilities.transformers.GUI_EdgeShapeTransformer;
import utilities.transformers.GUI_EdgeStrokeTransformer;
import utilities.transformers.GUI_VertexColoringTransformer;
import utilities.transformers.GUI_VertexShapeTransformer;
import algorithms.AStar;
import algorithms.State;
import dataStructure.city.infraStructure.Vehicle;
import dataStructure.graph.CityGraphNetwork;
import dataStructure.graph.Edge;
import dataStructure.graph.RoadNetworkGraph;
import dataStructure.graph.Vertex;
import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;
import edu.uci.ics.jung.io.GraphMLMetadata;
import edu.uci.ics.jung.io.GraphMLReader;
import edu.uci.ics.jung.io.GraphMLWriter;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

public class StartWindow implements ActionListener {
	
	private JFrame frame;
	private JPanel leftPanel;
	private JPanel buttonsPanel;
	private JPanel radioButtonPanel;

	private CustomLayout<Vertex,Edge> roadNetworkLayout;
	private CityGraphNetwork graph;
	private CustomVisualizationViewer<Vertex, Edge> vv;
	private InteractiveModalGraphMouse<Vertex, Edge> gm;

	private GUI_VertexFactory vertexFactory;
	private GUI_EdgeFactory edgeFactory;

	private GUI_VertexColoringTransformer vertexColoringTransformer;
	private GUI_VertexShapeTransformer vertexShapeTransformer;
	private GUI_EdgeColoringTransformer edgeColoringTransformer;
	private GUI_EdgeShapeTransformer edgeShapeTransformer;
	private GUI_EdgeStrokeTransformer edgeStrokeTransformer;
	private EdgeLabellerTransformer edgeLabelTransformer;

	private JButton new_button;
	private JButton save_button;
	private JButton load_button;
	private JButton AStar_button;
	private JRadioButton vertexType_intersection_radioButton;
	private JRadioButton vertexType_gasStation_radioButton;	
	private JRadioButton vertexType_building_radioButton;
	
	private ButtonGroup radioButtonsGroup;
	private ButtonGroup edgeLabelMenuButtonGroup;
	private ButtonGroup edgeShapeMenuButtonGroup;
	private ButtonGroup algTypeMenuButtonGroup;
	private ButtonGroup otherMenuButtonGroup;
	
	private GroupLayout gl_buttonsPanel;
	private GroupLayout gl_leftPanel;
	private GroupLayout gl_contentPane;
	private GroupLayout gl_radioButtonPanel;
	
	private JMenuBar menuBar;
	private JMenu modeMenu;
	private JMenu edgeMenu;
	private JMenu edgeShapeMenu;
	private JMenu algTypeMenu;
	private JMenu otherMenu;
	
	private JRadioButtonMenuItem nameLabel_radioButton;
	private JRadioButtonMenuItem speedLimitLabel_radioButton;
	private JRadioButtonMenuItem distanceLabel_radioButton;
	private JRadioButtonMenuItem capacityLabel_radioButton;
	private JRadioButtonMenuItem edgeLineShape_radioButton;
	private JRadioButtonMenuItem edgeBentLineShape_radioButton;
	private JRadioButtonMenuItem edgeCubicCurve_radioButton;
	private JRadioButtonMenuItem edgeQuadCurve_radioButton;
	private JRadioButtonMenuItem distance_radioButton;
	private JRadioButtonMenuItem time_radioButton;
	private JMenuItem vehicle_menuButton;
	private JMenuItem resetTempl_menuButton;
	private JMenuItem updateDist_menuButton;
	
	private int type = AStar.DISTANCE;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try { // code retrieved from http://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
			// Set System L&F
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			 UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
		} 
		catch (UnsupportedLookAndFeelException e) {
			// handle exception
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartWindow window = new StartWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame("IART");
		frame.getContentPane().setForeground(new Color(211, 211, 211));
		frame.getContentPane().setFont(new Font("Segoe Print", Font.PLAIN, 11));
		frame.getContentPane().setBackground(Color.DARK_GRAY);

		leftPanel = new JPanel();	
		buttonsPanel = new JPanel();
		radioButtonPanel = new JPanel();
		
		initButtons();
		initLayouts();

		graph = new CityGraphNetwork();
		roadNetworkLayout = new CustomLayout<Vertex, Edge>(graph.getRoadNetwork());
		roadNetworkLayout.setSize(new Dimension(Values.window_initial_x_resolution, Values.window_initial_y_resolution));

		initVisualizationViewer();
		initMouse();
		arrangeLayouts();

		frame.getContentPane().setLayout(gl_contentPane);
		frame.setBounds(100, 100, 1163, 449);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initMenuBar();
		frame.pack();
		frame.setVisible(true);
	}

	private void initButtons() {
		new_button = new JButton("New");
		new_button.setAlignmentY(Component.TOP_ALIGNMENT);
		new_button.setAlignmentX(Component.CENTER_ALIGNMENT);
		new_button.addActionListener(this);

		save_button = new JButton("Save");
		save_button.setAlignmentY(Component.TOP_ALIGNMENT);
		save_button.setAlignmentX(Component.CENTER_ALIGNMENT);
		save_button.addActionListener(this);

		load_button = new JButton("Load");
		load_button.setAlignmentY(Component.TOP_ALIGNMENT);
		load_button.setAlignmentX(Component.CENTER_ALIGNMENT);
		load_button.addActionListener(this);

		AStar_button = new JButton("Apply A*");
		AStar_button.setAlignmentY(Component.TOP_ALIGNMENT);
		AStar_button.setAlignmentX(Component.CENTER_ALIGNMENT);
		AStar_button.addActionListener(this);
		AStar_button.setFont(new Font(AStar_button.getFont().getName(),Font.BOLD,AStar_button.getFont().getSize()));
		AStar_button.setEnabled(false);
		
		vertexType_intersection_radioButton = new JRadioButton("Intersection");
		vertexType_intersection_radioButton.setHorizontalAlignment(SwingConstants.LEFT);
		vertexType_intersection_radioButton.setSelected(true);
		vertexType_intersection_radioButton.addActionListener(this);
		
		vertexType_gasStation_radioButton = new JRadioButton("Gas Station");
		vertexType_gasStation_radioButton.setHorizontalAlignment(SwingConstants.LEFT);
		vertexType_gasStation_radioButton.addActionListener(this);
		
		vertexType_building_radioButton = new JRadioButton("Building");
		vertexType_building_radioButton.setHorizontalAlignment(SwingConstants.LEFT);
		vertexType_building_radioButton.addActionListener(this);
		
		radioButtonsGroup = new ButtonGroup();
		radioButtonsGroup.add(vertexType_intersection_radioButton);
		radioButtonsGroup.add(vertexType_gasStation_radioButton);
		radioButtonsGroup.add(vertexType_building_radioButton);
		
	}

	private void initLayouts() {
		
		gl_buttonsPanel = new GroupLayout(buttonsPanel);
		gl_buttonsPanel.setHorizontalGroup(
			gl_buttonsPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(new_button, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
				.addComponent(save_button, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
				.addComponent(load_button, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
				.addComponent(AStar_button, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
		);
		gl_buttonsPanel.setVerticalGroup(
			gl_buttonsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttonsPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(new_button, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(save_button, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(load_button, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(AStar_button, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		buttonsPanel.setLayout(gl_buttonsPanel);
		
		gl_leftPanel = new GroupLayout(leftPanel);
		gl_leftPanel.setHorizontalGroup(
			gl_leftPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_leftPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(buttonsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_leftPanel.createSequentialGroup()
					.addComponent(radioButtonPanel, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_leftPanel.setVerticalGroup(
			gl_leftPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_leftPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(buttonsPanel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(radioButtonPanel, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
					.addContainerGap())
		);
		leftPanel.setLayout(gl_leftPanel);
		
		gl_radioButtonPanel = new GroupLayout(radioButtonPanel);
		gl_radioButtonPanel.setHorizontalGroup(
			gl_radioButtonPanel.createParallelGroup(Alignment.TRAILING)
				.addComponent(vertexType_intersection_radioButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
				.addComponent(vertexType_gasStation_radioButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
				.addComponent(vertexType_building_radioButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
		);
		gl_radioButtonPanel.setVerticalGroup(
			gl_radioButtonPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_radioButtonPanel.createSequentialGroup()
					.addComponent(vertexType_intersection_radioButton)
					.addGap(8)
					.addComponent(vertexType_gasStation_radioButton)
					.addGap(8)
					.addComponent(vertexType_building_radioButton)
					.addContainerGap())
		);
		radioButtonPanel.setLayout(gl_radioButtonPanel);

	}

	private void arrangeLayouts() {

		frame.getContentPane().removeAll();
		gl_contentPane = new GroupLayout(frame.getContentPane());
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(leftPanel, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(vv, GroupLayout.DEFAULT_SIZE, 1019, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(leftPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(16)
					.addComponent(vv, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
					.addContainerGap())
		);
	}

	private void initMouse(){	

		// Create a graph mouse and add it to the visualization viewer
		gm = new InteractiveModalGraphMouse<Vertex, Edge>(vv.getRenderContext(), vertexFactory, edgeFactory);
		vv.setGraphMouse(gm);
	}

	private void initVisualizationViewer(){

		vv = new CustomVisualizationViewer<Vertex,Edge>(roadNetworkLayout) {
			@Override
			public void repaint() {
				RoadNetworkGraph<Vertex, Edge> roadNetwork = graph.getRoadNetwork();
				Vertex arg0 = roadNetwork.getInitialVertex();
				Vertex arg1 = roadNetwork.getEndVertex();
				if(arg0 != null && arg1 != null) {
					if(AStar_button != null) {
						AStar_button.setEnabled(true);
					}
				} else {
					if(AStar_button != null) {
						AStar_button.setEnabled(false);
					}
				}
				if(resetTempl_menuButton!= null && edgeFactory != null) {
					resetTempl_menuButton.setEnabled(edgeFactory.getTemplate());
				}
				super.repaint();
			}
		};
		vv.setBackground(UIManager.getColor("Panel.background"));
		vv.setPreferredSize(new Dimension(Values.window_initial_x_resolution +72, Values.window_initial_y_resolution +45)); //Valores para manter a proporcao da resolucao da janela

		vertexFactory = new GUI_VertexFactory();
		edgeFactory = new GUI_EdgeFactory(true); //False para não aparecer aquela box sempre que se cria um edge
		vertexColoringTransformer = new GUI_VertexColoringTransformer(vv);
		vertexShapeTransformer = new GUI_VertexShapeTransformer();
		edgeColoringTransformer = new GUI_EdgeColoringTransformer(vv);
		edgeLabelTransformer = new EdgeLabellerTransformer();
		edgeShapeTransformer = new GUI_EdgeShapeTransformer();
		edgeStrokeTransformer = new GUI_EdgeStrokeTransformer(vv);
		
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Vertex>());
		vv.getRenderContext().setEdgeLabelTransformer(edgeLabelTransformer);
		vv.getRenderContext().setVertexFillPaintTransformer(vertexColoringTransformer);
		vv.getRenderContext().setEdgeDrawPaintTransformer(edgeColoringTransformer);
		vv.getRenderContext().setEdgeShapeTransformer(edgeShapeTransformer);
		vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
		
		//vv.getRenderContext().getedge
		//vv.getRenderContext().setedge
		//vv.getRenderContext().setVertexShapeTransformer(vertexSize);

	}

	private void initMenuBar() {

		menuBar = new JMenuBar();
		modeMenu = gm.getModeMenu();
		modeMenu.setText("Mouse Mode");
		modeMenu.setIcon(null); // I'm using this in a main menu
		modeMenu.setPreferredSize(new Dimension(80,20)); // Change the size so I can see the text
		menuBar.add(modeMenu);
		frame.setJMenuBar(menuBar);
		/**************************************************************************/
		
		edgeShapeMenu = new JMenu("Shape");
		edgeShapeMenuButtonGroup = new ButtonGroup();
		
		edgeQuadCurve_radioButton = new JRadioButtonMenuItem("Quad Curve");
		edgeQuadCurve_radioButton.addActionListener(this);
		edgeQuadCurve_radioButton.setSelected(true);
		
		edgeLineShape_radioButton = new JRadioButtonMenuItem("Straight Line");
		edgeLineShape_radioButton.addActionListener(this);
		
		edgeBentLineShape_radioButton = new JRadioButtonMenuItem("Bent Line");
		edgeBentLineShape_radioButton.addActionListener(this);
		
		edgeCubicCurve_radioButton = new JRadioButtonMenuItem("Cubic Curve");
		edgeCubicCurve_radioButton.addActionListener(this);
		
		
		edgeShapeMenuButtonGroup.add(edgeLineShape_radioButton);
		edgeShapeMenuButtonGroup.add(edgeBentLineShape_radioButton);
		edgeShapeMenuButtonGroup.add(edgeCubicCurve_radioButton);
		edgeShapeMenuButtonGroup.add(edgeQuadCurve_radioButton);
		
		edgeShapeMenu.add(edgeLineShape_radioButton);
		edgeShapeMenu.add(edgeBentLineShape_radioButton);
		edgeShapeMenu.add(edgeCubicCurve_radioButton);
		edgeShapeMenu.add(edgeQuadCurve_radioButton);
		
		/**************************************************************************/
		
		edgeMenu = new JMenu("Edge");
		edgeLabelMenuButtonGroup = new ButtonGroup();
		
		nameLabel_radioButton = new JRadioButtonMenuItem("Name");
		nameLabel_radioButton.addActionListener(this);
		nameLabel_radioButton.setSelected(true);
		
		speedLimitLabel_radioButton = new JRadioButtonMenuItem("Speed Limit");
		speedLimitLabel_radioButton.addActionListener(this);
		
		distanceLabel_radioButton = new JRadioButtonMenuItem("Distance");
		distanceLabel_radioButton.addActionListener(this);
		
		capacityLabel_radioButton = new JRadioButtonMenuItem("Capacity");
		capacityLabel_radioButton.addActionListener(this);
		
		edgeLabelMenuButtonGroup.add(nameLabel_radioButton);
		edgeLabelMenuButtonGroup.add(speedLimitLabel_radioButton);
		edgeLabelMenuButtonGroup.add(distanceLabel_radioButton);
		edgeLabelMenuButtonGroup.add(capacityLabel_radioButton);
		
		edgeMenu.add(nameLabel_radioButton);
		edgeMenu.add(speedLimitLabel_radioButton);
		edgeMenu.add(distanceLabel_radioButton);
		edgeMenu.add(capacityLabel_radioButton);
		edgeMenu.addSeparator();
		edgeMenu.add(edgeShapeMenu);
		menuBar.add(edgeMenu);
		
		/**************************************************************************/
		
		algTypeMenu = new JMenu("Alg Type"); //TODO: change the text of menu
		algTypeMenuButtonGroup = new ButtonGroup();
		
		distance_radioButton = new JRadioButtonMenuItem("Distance");
		distance_radioButton.addActionListener(this);
		if(type == AStar.DISTANCE) {
			distance_radioButton.setSelected(true);
		}
		
		time_radioButton = new JRadioButtonMenuItem("Time");
		time_radioButton.addActionListener(this);
		if(type == AStar.TIME) {
			time_radioButton.setSelected(true);
		}
		
		algTypeMenuButtonGroup.add(distance_radioButton);
		algTypeMenuButtonGroup.add(time_radioButton);
		
		algTypeMenu.add(distance_radioButton);
		algTypeMenu.add(time_radioButton);
		menuBar.add(algTypeMenu);
		
		/**************************************************************************/
		
		otherMenu = new JMenu("Other Options");
		otherMenuButtonGroup = new ButtonGroup();
		
		vehicle_menuButton = new JMenuItem("Edit Vehicle");
		vehicle_menuButton.addActionListener(this);
		
		resetTempl_menuButton = new JMenuItem("Reset edges template");
		resetTempl_menuButton.addActionListener(this);
		
		updateDist_menuButton = new JMenuItem("Update distance");
		updateDist_menuButton.addActionListener(this);		
		
		otherMenuButtonGroup.add(vehicle_menuButton);
		otherMenuButtonGroup.add(resetTempl_menuButton);
		otherMenuButtonGroup.add(updateDist_menuButton);
		
		otherMenu.add(vehicle_menuButton);
		otherMenu.add(resetTempl_menuButton);
		otherMenu.add(updateDist_menuButton);
		menuBar.add(otherMenu);
		
		/**************************************************************************/

		gm.setMode(ModalGraphMouse.Mode.EDITING); // Start off in editing mode
	}

	private void save() {
		try {
			
			JFileChooser chooser = new JFileChooser();
			GraphML_FileExtensionFilter filter = new GraphML_FileExtensionFilter();
		    chooser.setFileFilter(filter);
		  
		    String filename = null;
		    int returnVal = chooser.showSaveDialog(null);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    	if(!chooser.getSelectedFile().getAbsolutePath().endsWith(Values.file_extension)){
					filename = chooser.getSelectedFile().getAbsolutePath() + Values.file_extension;
				}else{
					filename = chooser.getSelectedFile().getAbsolutePath();
				}
		    }else{
		    	return;
		    }

			graph.getRoadNetwork().updateVertexPositions(roadNetworkLayout);

			GraphMLWriter<Vertex, Edge> graphWriter = new GraphMLWriter<Vertex, Edge> ();
			PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter(filename)));

			graphWriter.addVertexData("x", "The X coordinate", "0", new Transformer<Vertex, String>(){
				public String transform(Vertex v) {
					return Double.toString(roadNetworkLayout.getX(v));
				}
			});
			graphWriter.addVertexData("y", "The Y coordinate", "0", new Transformer<Vertex, String>() {
				public String transform(Vertex v) {
					return Double.toString(roadNetworkLayout.getY(v));
				}
			});	
			graphWriter.addVertexData("name", "The Vertex name", "V", new Transformer<Vertex, String>(){
				public String transform(Vertex v){
					return v.toString();
				}
			});		
			graphWriter.addVertexData("id", "The Vertex id", "0", new Transformer<Vertex, String>(){
				public String transform(Vertex v){
					return Integer.toString(v.getId());
				}
			});
			graphWriter.addVertexData("type", "The Vertex type", "null", new Transformer<Vertex, String>(){
				public String transform(Vertex v){
					return v.getType().toString();
				}
			});


			graphWriter.addEdgeData("name", "The Edge name", "E", new Transformer<Edge, String>(){
				public String transform(Edge e){
					return e.toString();
				}
			});		
			graphWriter.addEdgeData("id", "The Edge id", "0", new Transformer<Edge, String>(){
				public String transform(Edge e){
					return Integer.toString(e.getId());
				}
			});
			graphWriter.addEdgeData("speedLimit", "The Edge speedLimit", "0", new Transformer<Edge, String>(){
				public String transform(Edge e){
					return Integer.toString(e.getSpeedLimit());
				}
			});
			graphWriter.addEdgeData("distance", "The Edge distance", "0", new Transformer<Edge, String>(){
				public String transform(Edge e){
					return Double.toString(e.getDistance());
				}
			});
			graphWriter.addEdgeData("capacity", "The Edge capacity", "0", new Transformer<Edge, String>(){
				public String transform(Edge e){
					return Double.toString(e.getCapacity());
				}
			});


			graphWriter.save(graph.getRoadNetwork(), out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void load(){
		try{		
			JFileChooser chooser = new JFileChooser();
			GraphML_FileExtensionFilter filter = new GraphML_FileExtensionFilter();
		    chooser.setFileFilter(filter);
		  
		    String filename = null;
		    int returnVal = chooser.showOpenDialog(null);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    	filename = chooser.getSelectedFile().getAbsolutePath();
		    }else{
		    	return;
		    }

			GraphMLReader<RoadNetworkGraph<Vertex,Edge>,Vertex, Edge> graphReader = new GraphMLReader<RoadNetworkGraph<Vertex,Edge>,Vertex, Edge>(new Load_VertexFactory(), new Load_EdgeFactory());			 
			RoadNetworkGraph<Vertex,Edge> tmpGraph = new RoadNetworkGraph<Vertex,Edge>();
			graphReader.load(filename, tmpGraph);

			Map<String, GraphMLMetadata<Vertex>> vertex_meta = graphReader.getVertexMetadata(); //Our vertex Metadata is stored in a map.
			Map<String, GraphMLMetadata<Edge>> edge_meta = graphReader.getEdgeMetadata(); // Our edge Metadata is stored in a map.

			for (Vertex v : tmpGraph.getVertices())
			{	
				v.setId(Integer.parseInt(vertex_meta.get("id").transformer.transform(v)));
				v.setName(vertex_meta.get("name").transformer.transform(v));
				
				String type = vertex_meta.get("type").transformer.transform(v);
				switch(type){
					case "INTERSECTION":{
						v.setType(VertexType.INTERSECTION);
						break;
					}
					case "GAS_STATION":{
						v.setType(VertexType.GAS_STATION);
						break;
					}
					case "BUILDING":{
						v.setType(VertexType.BUILDING);
						break;
					}
					default:{
						v.setType(null);
						break;
					}
				}
				
				double x = Double.parseDouble(vertex_meta.get("x").transformer.transform(v));
				double y = Double.parseDouble(vertex_meta.get("y").transformer.transform(v));

				Point pos = new Point();
				pos.setLocation(x, y);
				v.setPosition(pos);
			}	
			for (Edge e : tmpGraph.getEdges())
			{		
				e.setId(Integer.parseInt(edge_meta.get("id").transformer.transform(e)));
				e.setName(edge_meta.get("name").transformer.transform(e));
				e.setSpeedLimit(Integer.parseInt(edge_meta.get("speedLimit").transformer.transform(e)));
				e.setDistance(Double.parseDouble(edge_meta.get("distance").transformer.transform(e)));
				e.setCapacity(Double.parseDouble(edge_meta.get("capacity").transformer.transform(e)));
			}

			graph = new CityGraphNetwork();
			graph.setRoadNetwork(tmpGraph);

			roadNetworkLayout = new CustomLayout<Vertex, Edge>(graph.getRoadNetwork());
			roadNetworkLayout.setSize(new Dimension(Values.window_initial_x_resolution, Values.window_initial_y_resolution));

			int biggestID = 0;
			for(Vertex v : graph.getRoadNetwork().getVertices()){			
				roadNetworkLayout.setLocation(v, v.getPosition());
				if(v.getId() > biggestID){
					biggestID = v.getId();
				}
			}
			Values.VerticesCurrentID = biggestID +1;

			initVisualizationViewer();	
			initMouse();
			arrangeLayouts();

			frame.getContentPane().setLayout(gl_contentPane);
			frame.setBounds(100, 100, 775, 400);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			initMenuBar();
			frame.pack();
			frame.setVisible(true);
			


		}catch(IOException i){
			i.printStackTrace();
			return;
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	private void reset() {
		graph = new CityGraphNetwork();

		roadNetworkLayout = new CustomLayout<Vertex, Edge>(graph.getRoadNetwork());
		roadNetworkLayout.setSize(new Dimension(Values.window_initial_x_resolution, Values.window_initial_y_resolution));

		Values.VerticesCurrentID = 0;

		initVisualizationViewer();	
		initMouse();
		arrangeLayouts();

		frame.getContentPane().setLayout(gl_contentPane);
		frame.setBounds(100, 100, 775, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initMenuBar();
		frame.pack();
		frame.setVisible(true);
	}
	
	private void star() {
		System.out.println("A_STAR!"); //TODO: remove this
		RoadNetworkGraph<Vertex, Edge> roadNetwork = graph.getRoadNetwork();
		roadNetwork.updateVertexPositions(roadNetworkLayout);
		roadNetwork.getSelectedEdges().clear();
		
		/*System.out.println("\nVERTEXES IN GRAPH:"); //TODO: remove this
		int ind = 1;
		for(Vertex v : graph.getRoadNetwork().getVertices()){
			
			System.out.println(ind + ": " + v.toString());
		}
		System.out.println("END VERTEXES IN GRAPH\n"); */
		
		
		
		Vertex begin = roadNetwork.getInitialVertex();
		Vertex end = roadNetwork.getEndVertex();
		Set<Vertex> toTravelSet = roadNetwork.getPointsToTraverse();
		Vector<Vertex> toTravelVec = new Vector<>();
		for(Vertex vertex: toTravelSet) {
			if(vertex != null) {
				toTravelVec.add(vertex);
			} else {
				System.out.println("null!"); //TODO: remove this
			}
		}
		//System.out.println("POINTS TO TRAVERSE"); //TODO: remove this
		/*for(Vertex vertex: toTravelVec) {
			System.out.println("To visit " + vertex.getName() + "| order " + vertex.getOrder());
		}
		//System.out.println("END POINTS TO TRAVERSE\n");*/
		//System.out.println(toTravelVec.size());
		
		Vehicle car = new Vehicle(100,100,0.1, 100); //TODO: change this
		
		if(begin != null && end != null) { //TODO: test if lastState not null
			State lastState = AStar.getPath(begin, end, toTravelVec, car, roadNetwork, type);
			//System.out.println("OPEN: " + AStar.openSize());  
			//System.out.println("CLOS: " + AStar.closedSize()); //TODO: remove this
			if(lastState == null) {
				System.out.println("PATH NOT FOUND!");
				vv.repaint();
				return;
			}
			System.out.println("Distance: " + lastState.getDistance());
			System.out.println("Time: " + lastState.getTime());
			Vector<Vertex> pathVertex = AStar.getPathVertex(lastState);
			for(int i = 1; i < pathVertex.size(); ++i) {
				Vertex arg0 = pathVertex.elementAt(i);
				Vertex arg1 = pathVertex.elementAt(i-1);
				Edge edge = roadNetwork.findEdge(arg0, arg1);
				roadNetwork.getSelectedEdges().add(edge);
			}
		}
		vv.repaint();		
	}
	
	private void update() {
		RoadNetworkGraph<Vertex, Edge> roadNetwork = graph.getRoadNetwork();
		roadNetwork.updateVertexPositions(roadNetworkLayout);
		
		for(Edge edge: roadNetwork.getEdges()) {
			Vertex vertex0 = roadNetwork.getSource(edge);
			Vertex vertex1 = roadNetwork.getDest(edge);
			edge.setDistance(vertex0.distance(vertex1));
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() instanceof JButton){
			JButton source = (JButton)(e.getSource());
			
			if(source.equals(save_button)){
				save();
			}else{
				if(source.equals(load_button)){
					load();
				}else{
					if(source.equals(new_button)){
						reset();
					} else{
						if(source.equals(AStar_button)){
							star();
						}
					}
				}
			}
		}else{
			if(e.getSource() instanceof JRadioButton){
				JRadioButton source = (JRadioButton) e.getSource();
				
				if(source.equals(vertexType_intersection_radioButton)){
					vertexFactory.setType(VertexType.INTERSECTION);
				}
				if(source.equals(vertexType_gasStation_radioButton)){
					vertexFactory.setType(VertexType.GAS_STATION);
				}
				if(source.equals(vertexType_building_radioButton)){
					vertexFactory.setType(VertexType.BUILDING);
				}
			}else{	
				if(e.getSource() instanceof JRadioButtonMenuItem){
					JRadioButtonMenuItem source = (JRadioButtonMenuItem) e.getSource();
					
					if(source.equals(nameLabel_radioButton)){
						edgeLabelTransformer.setLabelType(EdgeLabel.NAME);
					}
					if(source.equals(speedLimitLabel_radioButton)){
						edgeLabelTransformer.setLabelType(EdgeLabel.SPEED_LIMIT);
					}
					if(source.equals(distanceLabel_radioButton)){
						edgeLabelTransformer.setLabelType(EdgeLabel.DISTANCE);
					}
					if(source.equals(capacityLabel_radioButton)){
						edgeLabelTransformer.setLabelType(EdgeLabel.CAPACITY);
					}
					if(source.equals(edgeBentLineShape_radioButton)){
						edgeShapeTransformer.setType(EdgeShapeType.BENT_LINE);
					}
					if(source.equals(edgeLineShape_radioButton)){
						edgeShapeTransformer.setType(EdgeShapeType.LINE);
					}
					if(source.equals(edgeQuadCurve_radioButton)){
						edgeShapeTransformer.setType(EdgeShapeType.QUAD_CURVE);
					}
					if(source.equals(edgeCubicCurve_radioButton)){
						edgeShapeTransformer.setType(EdgeShapeType.CUBIC_CURVE);
					}
					if(source.equals(distance_radioButton)){
						type = AStar.DISTANCE;
					}
					if(source.equals(time_radioButton)){
						type = AStar.TIME;
					}
					vv.repaint();
				} else {
					if(e.getSource() instanceof JMenuItem){
						JMenuItem source = (JMenuItem) e.getSource();
						
						if(source.equals(vehicle_menuButton)){
							System.out.println("VEHICLE"); //TODO: add vehicle box!
						}
						if(source.equals(resetTempl_menuButton)){
							System.out.println("TEMPLATE");
							edgeFactory.resetTemplate();
						}
						if(source.equals(updateDist_menuButton)){
							update();
						}
						vv.repaint();
					}
				}
				
			}
			
		}
			
	}
	
}

