package gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import magicNumbers.Values;
import utilities.GUI_EdgeColoringTransformer;
import utilities.GUI_EdgeFactory;
import utilities.GUI_VertexColoringTransformer;
import utilities.GUI_VertexFactory;
import utilities.GUI_VertexShapeTransformer;
import dataStructure.graph.CityGraphNetwork;
import dataStructure.graph.Edge;
import dataStructure.graph.Vertex;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import gui.mouse.InteractiveModalGraphMouse;

public class GraphVisualizationFrame extends JFrame{

	private static final long serialVersionUID = -55705117335683992L;

	private String title;
	private Layout<Vertex,Edge> roadNetworkLayout;
	private CityGraphNetwork graph;
	private CustomVisualizationViewer<Vertex, Edge> vv;
	private InteractiveModalGraphMouse<Vertex, Edge> gm;

	private GUI_VertexFactory vertexFactory;
	private GUI_EdgeFactory edgeFactory;

	private GUI_VertexColoringTransformer vertexColoringTransformer;
	private GUI_VertexShapeTransformer vertexShapeTransformer;
	private GUI_EdgeColoringTransformer edgeColoringTransformer;




	public GraphVisualizationFrame(){

		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initVisualizationViewer();
		initMouse();
		initMenuBar();
		
		
		gm.setMode(ModalGraphMouse.Mode.EDITING); // Start off in editing mode
		this.pack();
		this.setVisible(true);  


	}



	private void initMenuBar() {
		// add a menu for changing mouse modes
		JMenuBar menuBar = new JMenuBar();
		JMenu modeMenu = gm.getModeMenu();
		modeMenu.setText("Mouse Mode");
		modeMenu.setIcon(null); // I'm using this in a main menu
		modeMenu.setPreferredSize(new Dimension(80,20)); // Change the size so I can see the text
		menuBar.add(modeMenu);
		
		
		
		this.setJMenuBar(menuBar);

	}



	private void initVisualizationViewer(){
		graph = new CityGraphNetwork();
		roadNetworkLayout = new CustomLayout<Vertex, Edge>(graph.getRoadNetwork());
		roadNetworkLayout.setSize(new Dimension(Values.window_initial_x_resolution, Values.window_initial_y_resolution));


		vv = new CustomVisualizationViewer<Vertex,Edge>(roadNetworkLayout);
		vv.setPreferredSize(new Dimension(Values.window_initial_x_resolution +72, Values.window_initial_y_resolution +45)); //Valores para manter a proporcao da resolucao da janela


		vertexFactory = new GUI_VertexFactory();
		edgeFactory = new GUI_EdgeFactory();
		vertexColoringTransformer = new GUI_VertexColoringTransformer(vv);
		vertexShapeTransformer = new GUI_VertexShapeTransformer();
		edgeColoringTransformer = new GUI_EdgeColoringTransformer(vv);

		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Vertex>());
		vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<Edge>());
		vv.getRenderContext().setVertexFillPaintTransformer(vertexColoringTransformer);
		vv.getRenderContext().setEdgeDrawPaintTransformer(edgeColoringTransformer);
		//vv.getRenderContext().setVertexShapeTransformer(vertexSize);
	}

	private void initMouse(){	

		// Create a graph mouse and add it to the visualization viewer
		gm = new InteractiveModalGraphMouse<Vertex, Edge>(vv.getRenderContext(), vertexFactory, edgeFactory);
		vv.setGraphMouse(gm);
		this.getContentPane().add(vv);
		
	}

}
