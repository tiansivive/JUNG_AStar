package gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;

import dataStructure.graph.Edge;
import dataStructure.graph.GraphNetwork;
import dataStructure.graph.Vertex;

import magicNumbers.Values;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.EditingModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import factories.GUI_EdgeFactory;
import factories.GUI_VertexFactory;
import gui.mouse.InteractiveModalGraphMouse;
import gui.mouse.plugins.ClickMenusGraphMousePlugin;
import gui.mouse.plugins.ModifiedEditGraphModalMousePlugin;
import gui.mouse.plugins.menus.EdgePopupMenu;
import gui.mouse.plugins.menus.VertexPopupMenu;

public class GraphVisualizationFrame extends JFrame{

	private static final long serialVersionUID = -55705117335683992L;

	private String title;
	private Layout<Vertex,Edge> roadNetworkLayout;
	private GraphNetwork graph;
	private VisualizationViewer<Vertex, Edge> vv;
	private InteractiveModalGraphMouse<Vertex, Edge> gm;
	
	private GUI_VertexFactory vertexFactory;
	private GUI_EdgeFactory edgeFactory;
	
	
	public GraphVisualizationFrame(){
		
		this.setTitle(title);
		graph = new GraphNetwork();
		roadNetworkLayout = new CustomLayout<Vertex, Edge>(graph.getRoadNetwork());
		roadNetworkLayout.setSize(new Dimension(Values.window_initial_x_resolution, Values.window_initial_y_resolution));
		
		vv = new VisualizationViewer<Vertex,Edge>(roadNetworkLayout);
		vv.setPreferredSize(new Dimension(Values.window_initial_x_resolution +72, Values.window_initial_y_resolution +45)); //Valores para manter a proporcao da resolucao da janela
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Vertex>());
		vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<Edge>());
		
		vertexFactory = new GUI_VertexFactory();
		edgeFactory = new GUI_EdgeFactory();
		
		// Create a graph mouse and add it to the visualization viewer
		gm = new InteractiveModalGraphMouse<Vertex, Edge>(vv.getRenderContext(), vertexFactory, edgeFactory);

	
		
		vv.setGraphMouse(gm);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(vv);
		
		 // add a menu for changing mouse modes
        JMenuBar menuBar = new JMenuBar();
        JMenu modeMenu = gm.getModeMenu();
        modeMenu.setText("Mouse Mode");
        modeMenu.setIcon(null); // I'm using this in a main menu
        modeMenu.setPreferredSize(new Dimension(80,20)); // Change the size so I can see the text
        
        menuBar.add(modeMenu);
        this.setJMenuBar(menuBar);
        gm.setMode(ModalGraphMouse.Mode.EDITING); // Start off in editing mode
        this.pack();
        this.setVisible(true);  
		
	}
	
	
	
}
