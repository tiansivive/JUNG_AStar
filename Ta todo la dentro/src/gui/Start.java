package gui;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;





public class Start extends JFrame{
	

	private static final long serialVersionUID = -2707712944901661771L;

	private GraphVisualizationFrame gui;
	
	
	public Start()
	{
		
	}

	public static void main(String[] args)
	{
		 try { // code retrieved from http://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
	            // Set System L&F
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	       // handle exception
	    }
	    catch (ClassNotFoundException e) {
	       // handle exception
	    }
	    catch (InstantiationException e) {
	       // handle exception
	    }
	    catch (IllegalAccessException e) {
	       // handle exception
	    }
		
		 GraphVisualizationFrame gui = new GraphVisualizationFrame();
	}
}