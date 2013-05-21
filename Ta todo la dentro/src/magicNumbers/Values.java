package magicNumbers;

import java.awt.Color;




public class Values{
	
	
	public static int VerticesCurrentID = 0;
	public static int EdgesCurrentID = 0;
	public static int CityZonesCurrentID = 0;
	public static final int roadWeight = 5;
	
	public static final int window_initial_x_resolution = 720;
	public static final int window_initial_y_resolution = 550;
	
	
	public static final int default_edge_speedLimit = -1;
	public static final double default_edge_weight = -1;
	public static final double default_edge_capacity = -1;
	public static final double default_edge_distance = -1;
	public static final boolean default_edge_bidirectionality = true;
	
	
	public static final String vertex_popupMenu_delete_option = "Delete Vertex";
	public static final String vertex_popupMenu_name = "Vertex Menu";
	
	public static final String edge_popupMenu_delete_option = "Delete Edge";
	public static final String edge_popupMenu_name = "Edge Menu";
	public static final String edge_properties_editor_menu_entry = "Edit Edge Properties";
	public static final String edge_properties_editor_frame_name = "Edge Properties";
	
	public static final Color default_unassigned_zone_color = Color.BLACK;
	public static final Color default_selected_vertex_color = Color.YELLOW;	
	
	
	public static final String file_extension = ".graphml";
}