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
	
	private int size_labirinth = 11;
	private int number_dragons = 2;
	private int typeDragons = 1;

	
	public OptionsWindow() {

		setTitle("Options");
		getContentPane().setLayout(new GridLayout(11, 1));
		
		initializeMazePreferences();
		initializeDragonsPreferences();
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
		dimensionSlider.setMaximum(55);
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
		num_Dragons.setMaximum(40);
		num_Dragons.setMinimum(0);
		num_Dragons.setValue(5);
		

		JPanel dragons_type = new JPanel();
		getContentPane().add(dragons_type);
		JLabel lblDragons = new JLabel("Type of dragons");
		lblDragons.setHorizontalAlignment(SwingConstants.LEFT);
		dragons_type.add(lblDragons);
		String[] type = {"Static", "Room",
				"Room and Sleep"};
		typeSelector = new JComboBox<Object>(type);
		typeSelector.setSelectedIndex(0);
		dragons_type.add(typeSelector);

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

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(e -> {
			String message = "Do you want to cancel the operation?";
			int option = JOptionPane.showConfirmDialog(rootPane, message);

			if (option == JOptionPane.YES_OPTION) {
				setVisible(false);
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

	

}
//TODO old values for options sliders