package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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

public class GraphVisualizationFrame extends JFrame implements Serializable, ActionListener{

	private static final long serialVersionUID = -55705117335683992L;

	private JMenuItem saveOption;
	private JMenuItem loadOption;

	private String title;
	private CustomLayout<Vertex,Edge> roadNetworkLayout;
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

		/*SaveLoadMenu fileOp = new SaveLoadMenu(roadNetworkLayout, graph, vv);
		fileOp.setIcon(null);
		fileOp.setPreferredSize(new Dimension(90,20));
		menuBar.add(fileOp);*/

		JMenu fileOp = new JMenu("File Operations");
		saveOption = new JMenuItem("Save");
		loadOption = new JMenuItem("Load");	
		saveOption.setPreferredSize(new Dimension(90,20));
		loadOption.setPreferredSize(new Dimension(90,20));
		saveOption.addActionListener(this);
		loadOption.addActionListener(this);
		fileOp.add(saveOption);
		fileOp.add(loadOption);
		fileOp.setPreferredSize(new Dimension(90,20));
		menuBar.add(fileOp);

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



	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem source = (JMenuItem)(e.getSource());

		if(source.equals(saveOption)){
			save();
		}else{
			if(source.equals(loadOption)){
				load();
			}	
		}	

	}



	@SuppressWarnings("unchecked")
	private void load() {
		 try
	      {
			 
			 FileInputStream fileIn = new FileInputStream("Graph.ser");
			 ObjectInputStream in = new ObjectInputStream(fileIn);
	         graph = (CityGraphNetwork)in.readObject();
	         in.close();
	         fileIn.close();
	         
	         fileIn = new FileInputStream("RoadNetworkLayout.ser");
	         in = new ObjectInputStream(fileIn);
	         roadNetworkLayout = new CustomLayout<Vertex, Edge>(graph.getRoadNetwork());
	 		 roadNetworkLayout.setSize(new Dimension(Values.window_initial_x_resolution, Values.window_initial_y_resolution));
	 		 roadNetworkLayout.setLocations((Map<Vertex, Point2D>) in.readObject());
	         in.close();
	         fileIn.close();

	         vv = new CustomVisualizationViewer<Vertex,Edge>(roadNetworkLayout);
	         vv.repaint();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	         return;
	      }

	}



	private void save() {
		try {
			FileOutputStream fileOut = new FileOutputStream("RoadNetworkLayout.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(roadNetworkLayout.getLocations());
			out.close();
			fileOut.close();

			fileOut = new FileOutputStream("Graph.ser");
			out = new ObjectOutputStream(fileOut);
			out.writeObject(graph);
			out.close();
			fileOut.close();


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
