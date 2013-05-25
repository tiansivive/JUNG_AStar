package magicNumbers;

import java.awt.Color;




public class Values{
	
	
	public static int VerticesCurrentID = 0;
	public static int EdgesCurrentID = 0;
	public static int CityZonesCurrentID = 0;
	public static final int roadWeight = 5;
	
	public static final int window_initial_x_resolution = 720;
	public static final int window_initial_y_resolution = 550;
	
	public static final int min_edge_speedLimit = 0;
	public static final double min_edge_capacity = 0;
	public static final double min_edge_distance = 0;
	
	public static int default_edge_speedLimit = 100;
	public static double default_edge_capacity = 1;
	public static double default_edge_distance = 1;
	
	
	public static double default_vehicle_fuel = 100;
	public static double default_vehicle_fuel_tank_capacity = 100;
	public static double default_vehicle_consumption = 0.1;
	public static int default_vehicle_speed = 100;
	
	public static final String vertex_popupMenu_delete_option = "Delete Vertex";
	public static final String vertex_popupMenu_name = "Vertex Menu";
	public static final String vertex_set_as_begin = "Set as begin";
	public static final String vertex_set_as_end = "Set as end";
	public static final String vertex_set_as_point_to_traverse = "Set as point to traverse";
	public static final String vertex_remove_as_point_to_traverse = "Remove as point to traverse";
	
	public static final String edge_popupMenu_delete_option = "Delete Edge";
	public static final String edge_popupMenu_name = "Edge Menu";
	public static final String edge_properties_editor_menu_entry = "Edit Edge Properties";
	public static final String edge_properties_editor_frame_name = "Edge Properties";

	
	
	public static final Color default_unassigned_zone_color = Color.BLACK;
	public static final Color default_selected_vertex_color = Color.YELLOW;	
	
	
	public static final String file_extension = ".graphml";
}