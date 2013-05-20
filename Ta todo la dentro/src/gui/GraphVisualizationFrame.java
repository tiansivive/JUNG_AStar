package gui;

import gui.mouse.InteractiveModalGraphMouse;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import magicNumbers.Values;

import org.apache.commons.collections15.BidiMap;
import org.apache.commons.collections15.Transformer;

import utilities.GUI_EdgeColoringTransformer;
import utilities.GUI_EdgeFactory;
import utilities.GUI_VertexColoringTransformer;
import utilities.GUI_VertexFactory;
import utilities.GUI_VertexShapeTransformer;
import dataStructure.graph.CityGraphNetwork;
import dataStructure.graph.Edge;
import dataStructure.graph.RoadNetworkGraph;
import dataStructure.graph.Vertex;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.io.GraphMLMetadata;
import edu.uci.ics.jung.io.GraphMLReader;
import edu.uci.ics.jung.io.GraphMLWriter;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

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
		edgeFactory = new GUI_EdgeFactory(true); //False para não aparecer aquela box sempre que se cria um edge
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
	/*	try
		{

			GraphMLReader<RoadNetworkGraph<Vertex,Edge>,Vertex, Edge> gmlr = new GraphMLReader<RoadNetworkGraph<Vertex,Edge>,Vertex, Edge>(new VertexFactory(), new EdgeFactory());
			 
			//Next we need a Graph to store the data that we are reading in from GraphML. This is also an Undirected Graph because it needs to match to the type of graph we are reading in.
			final RoadNetworkGraph<Vertex,Edge> graph = new RoadNetworkGraph<Vertex,Edge>();
			 
			//Here we read in our graph. filename is our .graphml file, and graph is where we will store our graph.
			gmlr.load("roadNetworkGraph", graph);
			
			BidiMap<Vertex, String> vertex_ids = gmlr.getVertexIDs();  //The vertexIDs are stored in a BidiMap.
			Map<String, GraphMLMetadata<Vertex>> vertex_color = gmlr.getVertexMetadata(); //Our vertex Metadata is stored in a map.
			Map<String, GraphMLMetadata<Edge>> edge_meta = gmlr.getEdgeMetadata(); // Our edge Metadata is stored in a map.
			 
			// Here we iterate through our vertices, n, and we set the value and the color of our nodes from the data we have
			// in the vertex_ids map and vertex_color map.
			for (Vertex n : graph.getVertices())
			{
				n.setValue(vertex_ids.get(n)); //Set the value of the node to the vertex_id which was read in from the GraphML Reader.
				n.setColor(vertex_color.get("d0").transformer.transform(n)); // Set the color, which we get from the Map vertex_color.            //Let's print out the data so we can get a good understanding of what we've got going on.
				System.out.println("ID: "+n.getID()+", Value: "+n.getValue()+", Color: "+n.getColor());
			}
			 // Just as we added the vertices to the graph, we add the edges as well.
			for (Edge e : graph.getEdges())
			{
				e.setValue(edge_meta.get("d1").transformer.transform(e)); //Set the edge's value.
				System.out.println("Edge ID: "+e.getID());
			}
			
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
*/
	}



	private void save() {
		try {
			GraphMLWriter<Vertex, Edge> graphWriter = new GraphMLWriter<Vertex, Edge> ();

			PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter("roadNetworkGraph")));



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
			
			graphWriter.addEdgeData("name", "The Edge name", "E", new Transformer<Edge, String>(){
				public String transform(Edge v){
					return v.toString();
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
			graphWriter.addEdgeData("weight", "The Edge weight", "0", new Transformer<Edge, String>(){
				public String transform(Edge e){
					return Double.toString(e.getWeight());
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

}
