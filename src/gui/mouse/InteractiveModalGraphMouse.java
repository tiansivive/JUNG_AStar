package gui.mouse;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.ItemSelectable;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.plaf.basic.BasicIconFactory;


import org.apache.commons.collections15.Factory;

import dataStructure.graph.Edge;
import dataStructure.graph.Vertex;

import edu.uci.ics.jung.visualization.MultiLayerTransformer;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.annotations.AnnotatingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.AbstractGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.AbstractModalGraphMouse;
import edu.uci.ics.jung.visualization.control.AbstractPopupGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.AnimatedPickingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.LabelEditingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.PickingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.RotatingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.ScalingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.ShearingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.TranslatingGraphMousePlugin;
import gui.mouse.plugins.ClickMenusGraphMousePlugin;
import gui.mouse.plugins.ModifiedEditGraphModalMousePlugin;
import gui.mouse.plugins.menus.EdgePopupMenu;
import gui.mouse.plugins.menus.VertexPopupMenu;

public class InteractiveModalGraphMouse<V, E> extends AbstractModalGraphMouse implements ModalGraphMouse, ItemSelectable{


	protected Factory<V> vertexFactory;
	protected Factory<E> edgeFactory;
	protected AbstractPopupGraphMousePlugin popupEditingPlugin;
	protected AbstractGraphMousePlugin editingPlugin;
	protected LabelEditingGraphMousePlugin<V,E> labelEditingPlugin;
	protected AnnotatingGraphMousePlugin<V,E> annotatingPlugin;
	protected MultiLayerTransformer basicTransformer;
	protected RenderContext<V,E> rc;


	public InteractiveModalGraphMouse(RenderContext<V,E> rc,Factory<V> vertexFactory, Factory<E> edgeFactory) {
		this(rc, vertexFactory, edgeFactory, 1.1f, 1/1.1f);

	}


	public InteractiveModalGraphMouse(RenderContext<V,E> rc, Factory<V> vertexFactory, Factory<E> edgeFactory, float in, float out) {
		super(in,out);
		this.vertexFactory = vertexFactory;
		this.edgeFactory = edgeFactory;
		this.rc = rc;
		this.basicTransformer = rc.getMultiLayerTransformer();
		loadPlugins();
		setModeKeyListener(new ModeKeyAdapter(this));
	}




	protected void loadPlugins(){

		ClickMenusGraphMousePlugin<Vertex, Edge> clickMenusPlugin = new ClickMenusGraphMousePlugin<Vertex, Edge>();

		// Add some popup menus for the edges and vertices to the mouse plugin
		JPopupMenu edgeMenu = new EdgePopupMenu(new JFrame());
		JPopupMenu vertexMenu = new VertexPopupMenu();

		clickMenusPlugin.setEdgeClickPopup(edgeMenu);
		clickMenusPlugin.setVertexClickPopup(vertexMenu);

		popupEditingPlugin = clickMenusPlugin;

		pickingPlugin = new PickingGraphMousePlugin<V,E>();
		animatedPickingPlugin = new AnimatedPickingGraphMousePlugin<V,E>();
		translatingPlugin = new TranslatingGraphMousePlugin(InputEvent.BUTTON1_MASK);
		scalingPlugin = new ScalingGraphMousePlugin(new CrossoverScalingControl(), 0, in, out);
		rotatingPlugin = new RotatingGraphMousePlugin();
		shearingPlugin = new ShearingGraphMousePlugin();
		editingPlugin = new ModifiedEditGraphModalMousePlugin<V,E>(vertexFactory, edgeFactory);
		labelEditingPlugin = new LabelEditingGraphMousePlugin<V,E>();
		annotatingPlugin = new AnnotatingGraphMousePlugin<V,E>(rc);

		add(scalingPlugin);
		setMode(Mode.EDITING);

	}

	@Override
	public void setMode(Mode mode) {
		if(this.mode != mode) {
			fireItemStateChanged(new ItemEvent(this, ItemEvent.ITEM_STATE_CHANGED, this.mode, ItemEvent.DESELECTED));

			this.mode = mode;
			if(mode == Mode.TRANSFORMING) {
				setTransformingMode();
			} else if(mode == Mode.PICKING) {
				setPickingMode();
			} else if(mode == Mode.EDITING) {
				setEditingMode();
			} else if(mode == Mode.ANNOTATING) {
				setAnnotatingMode();
			}
			if(modeBox != null) {
				modeBox.setSelectedItem(mode);
			}
			fireItemStateChanged(new ItemEvent(this, ItemEvent.ITEM_STATE_CHANGED, mode, ItemEvent.SELECTED));
		}
	}


	@Override
	protected void setPickingMode() {
		remove(translatingPlugin);
		remove(rotatingPlugin);
		remove(shearingPlugin);
		remove(editingPlugin);
		remove(annotatingPlugin);
		add(pickingPlugin);
		add(animatedPickingPlugin);
		add(labelEditingPlugin);
		add(popupEditingPlugin);
	}

	@Override
	protected void setTransformingMode() {
		remove(pickingPlugin);
		remove(animatedPickingPlugin);
		remove(editingPlugin);
		remove(annotatingPlugin);
		add(translatingPlugin);
		add(rotatingPlugin);
		add(shearingPlugin);
		add(labelEditingPlugin);
		add(popupEditingPlugin);
	}

	protected void setEditingMode() {
		remove(pickingPlugin);
		remove(animatedPickingPlugin);
		remove(translatingPlugin);
		remove(rotatingPlugin);
		remove(shearingPlugin);
		remove(labelEditingPlugin);
		remove(annotatingPlugin);
		add(editingPlugin);
		add(popupEditingPlugin);
	}

	protected void setAnnotatingMode() {
		remove(pickingPlugin);
		remove(animatedPickingPlugin);
		remove(translatingPlugin);
		remove(rotatingPlugin);
		remove(shearingPlugin);
		remove(labelEditingPlugin);
		remove(editingPlugin);
		remove(popupEditingPlugin);
		add(annotatingPlugin);
	}




	@Override
	public JComboBox<?> getModeComboBox() {
		if(modeBox == null) {
			modeBox = new JComboBox<Object>(new Mode[]{Mode.TRANSFORMING, Mode.PICKING, Mode.EDITING, Mode.ANNOTATING});
			modeBox.addItemListener(getModeListener());
		}
		modeBox.setSelectedItem(mode);
		return modeBox;
	}


	@Override
	public JMenu getModeMenu() {
		if(modeMenu == null) {
			modeMenu = new JMenu();// {
			Icon icon = BasicIconFactory.getMenuArrowIcon();
			modeMenu.setIcon(BasicIconFactory.getMenuArrowIcon());
			modeMenu.setPreferredSize(new Dimension(icon.getIconWidth()+10, 
					icon.getIconHeight()+10));

			final JRadioButtonMenuItem transformingButton = 
					new JRadioButtonMenuItem(Mode.TRANSFORMING.toString());
			transformingButton.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED) {
						setMode(Mode.TRANSFORMING);
					}
				}});

			final JRadioButtonMenuItem pickingButton =
					new JRadioButtonMenuItem(Mode.PICKING.toString());
			pickingButton.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED) {
						setMode(Mode.PICKING);
					}
				}});

			final JRadioButtonMenuItem editingButton =
					new JRadioButtonMenuItem(Mode.EDITING.toString());
			editingButton.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED) {
						setMode(Mode.EDITING);
					}
				}});

			ButtonGroup radio = new ButtonGroup();
			radio.add(transformingButton);
			radio.add(pickingButton);
			radio.add(editingButton);
			transformingButton.setSelected(true);
			modeMenu.add(transformingButton);
			modeMenu.add(pickingButton);
			modeMenu.add(editingButton);
			modeMenu.setToolTipText("Menu for setting Mouse Mode");
			addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange() == ItemEvent.SELECTED) {
						if(e.getItem() == Mode.TRANSFORMING) {
							transformingButton.setSelected(true);
						} else if(e.getItem() == Mode.PICKING) {
							pickingButton.setSelected(true);
						} else if(e.getItem() == Mode.EDITING) {
							editingButton.setSelected(true);
						}
					}
				}});
		}
		return modeMenu;
	}

	public static class ModeKeyAdapter extends KeyAdapter {
		private char t = 't';
		private char p = 'p';
		private char e = 'e';
		private char a = 'a';
		protected ModalGraphMouse graphMouse;

		public ModeKeyAdapter(ModalGraphMouse graphMouse) {
			this.graphMouse = graphMouse;
		}

		public ModeKeyAdapter(char t, char p, char e, char a, ModalGraphMouse graphMouse) {
			this.t = t;
			this.p = p;
			this.e = e;
			this.a = a;
			this.graphMouse = graphMouse;
		}

		@Override
		public void keyTyped(KeyEvent event) {
			char keyChar = event.getKeyChar();
			if(keyChar == t) {
				((Component)event.getSource()).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				graphMouse.setMode(Mode.TRANSFORMING);
			} else if(keyChar == p) {
				((Component)event.getSource()).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				graphMouse.setMode(Mode.PICKING);
			} else if(keyChar == e) {
				((Component)event.getSource()).setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
				graphMouse.setMode(Mode.EDITING);
			} else if(keyChar == a) {
				((Component)event.getSource()).setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
				graphMouse.setMode(Mode.ANNOTATING);
			}
		}
	}


}
