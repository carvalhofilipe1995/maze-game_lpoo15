package maze.gui;

import maze.logic.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	private Game game;

	private JSlider dimensionSlider;
	private JSlider num_Dragons;
	private JComboBox<?> typeSelector;
	private JButton confirmButton;
	private JButton cancelButton;
	
	private JTextField upControl;
	private JTextField downControl;
	private JTextField rightControl;
	private JTextField leftControl;
	private JTextField sendDartsC;
	
	private String upC = "";
	private String downC = " ";
	private String rightC = "";
	private String leftC = "";
	private String dartsC = "";
	
	private int size_labirinth = 11;
	private int number_dragons = 2;
	private int typeDragons = 1;


	public OptionsWindow(JFrame parent, String name, boolean modal) {

		super(parent, name, modal);

		getContentPane().setLayout(new GridLayout(11, 1));
		
		initializeMazePreferences();
		initializeDragonsPreferences();
		initializeKeysPreferences();
		initializeButtons();

		
		pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height
				/ 2 - this.getSize().height / 2);
	}


	public void initializeMazePreferences() {
		JLabel sizeMaze = new JLabel("Maze Dimension");
		sizeMaze.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(sizeMaze);

		JPanel mazeDimensions = new JPanel();
		getContentPane().add(mazeDimensions);
		JLabel dimension = new JLabel("Dimension Labirinth");
		mazeDimensions.add(dimension);
		dimensionSlider = new JSlider();
		dimensionSlider.setMinorTickSpacing(2);
		dimensionSlider.setPaintLabels(true);
		dimensionSlider.setPaintTicks(true);
		dimensionSlider.setSnapToTicks(true);
		dimensionSlider.setMajorTickSpacing(10);
		dimensionSlider.setMaximum(25);
		dimensionSlider.setMinimum(5);
		dimensionSlider.setValue(11);
		mazeDimensions.add(dimensionSlider);

	}

	
	public void initializeDragonsPreferences() {
		JLabel DragonsPreferences = new JLabel("Dragon Settings");
		DragonsPreferences.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(DragonsPreferences);
		
		JPanel numDragons = new JPanel();
		getContentPane().add(numDragons);
		JLabel lblNumDragonsSlideLabel = new JLabel("Number of dragons");
		numDragons.add(lblNumDragonsSlideLabel);
		num_Dragons = new JSlider();
		numDragons.add(num_Dragons);
		num_Dragons.setPaintTicks(true);
		num_Dragons.setPaintLabels(true);
		num_Dragons.setMinorTickSpacing(5);
		num_Dragons.setMajorTickSpacing(10);
		num_Dragons.setMaximum(20);
		num_Dragons.setMinimum(0);
		num_Dragons.setValue(5);
		

		JPanel dragons_type = new JPanel();
		getContentPane().add(dragons_type);
		JLabel lblDragons = new JLabel("Type of dragons");
		lblDragons.setHorizontalAlignment(SwingConstants.LEFT);
		dragons_type.add(lblDragons);
		String[] type = { "Static", "Roam",
				"Roam and Sleep" };
		typeSelector = new JComboBox<Object>(type);
		typeSelector.setSelectedIndex(0);
		dragons_type.add(typeSelector);

	}
	
	
	public void initializeKeysPreferences(){
		
		JLabel GameControls = new JLabel("Controls");
		GameControls.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(GameControls);

		JPanel upAndDown= new JPanel();
		getContentPane().add(upAndDown);
		upAndDown.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel Up = new JLabel("Up");
		upAndDown.add(Up);
		upControl = new JTextField();
		upControl.setText(upC);
		upAndDown.add(upControl);
		upControl.setColumns(10);
		JLabel Down = new JLabel("Down");
		upAndDown.add(Down);
		downControl = new JTextField();
		upAndDown.add(downControl);
		downControl.setText(downC);
		downControl.setColumns(10);

		JPanel leftAndRight = new JPanel();
		getContentPane().add(leftAndRight);
		JLabel Left = new JLabel("Left");
		leftAndRight.add(Left);
		leftControl = new JTextField();
		leftControl.setText(leftC);
		leftControl.setColumns(10);
		leftAndRight.add(leftControl);
		JLabel lblRight = new JLabel("Right");
		leftAndRight.add(lblRight);
		rightControl = new JTextField();
		leftAndRight.add(rightControl);
		rightControl.setText(rightC);
		rightControl.setColumns(10);

		JPanel sendDartsControl = new JPanel();
		getContentPane().add(sendDartsControl);
		JLabel SendDarts = new JLabel("Send Darts");
		sendDartsControl.add(SendDarts);
		sendDartsC = new JTextField();
		sendDartsC.setText(dartsC);
		sendDartsC.setColumns(10);
		sendDartsControl.add(sendDartsC);
		
	}
	
	
	
	public void initializeButtons(){
		
		JPanel buttons = new JPanel();
		
		confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = "Do you want to save this settings?";
				int option = JOptionPane.showConfirmDialog(rootPane, message);
				
				if(option == JOptionPane.YES_OPTION){
					
				 size_labirinth = dimensionSlider.getValue();
				 number_dragons = num_Dragons.getValue();
				 int type_dragons = typeSelector.getSelectedIndex();
					
					if(type_dragons == 0){
						typeDragons = 1;
					} else if(type_dragons == 1){
						typeDragons = 2;
					} else if(type_dragons == 2){
						typeDragons = 3;
					}
					
					setVisible(false);
					
				} else{
					setVisible(false);
				}
			}
		});
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = "Do you want to cancel the operation?";
				int option = JOptionPane.showConfirmDialog(rootPane, message);
				
				if(option == JOptionPane.YES_OPTION){
					setVisible(false);
				}
			}
		});
		
		buttons.add(confirmButton);
		buttons.add(cancelButton);
		getContentPane().add(buttons, BorderLayout.PAGE_END);
		
	}
	
	public int getSizeLabirinth(){
		return size_labirinth;
	}
	
	public int getNumberDragons(){
		return number_dragons;
	}
	
	public int getTypeDragons(){
		return typeDragons;
	}

	
	public void startOption(){
		setVisible(true);
	}
	
	public String getUpKey(){
		return upC;
	}
	
	public String getDownKey(){
		return downC;
	}
	
	public String getRightKey(){
		return rightC;
	}
	
	public String getLeftKey(){
		return leftC;
	}
	
	public String getDartsKey(){
		return dartsC;
	}

	

}
