


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrew
 */
public class UI2 extends javax.swing.JFrame implements ActionListener, MouseWheelListener {
	Map[] LoadedMaps;
	TruthData[] LoadedTruth;
	private Display Map;

	public UI2() {
		initComponents();
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {
		LoadedMaps = FileUtils.getMapList();
		LoadedTruth = null;
		Timer myTimer;
		myTimer = new Timer(500, this);
		myTimer.start();
		Map = new Display(800, 630);
		addMouseWheelListener(this);
		jPanel1 = new javax.swing.JPanel();
		label1 = new javax.swing.JLabel();
		changeMapButton = new JButton();
		changeGoalButton = new JButton();
		scaleFitCheckbox = new JCheckBox();
		showDataCheckbox = new JCheckBox();
		displayStatsCheckbox = new JCheckBox();
		showAICheckbox = new JCheckBox();
		generateMapButton = new JButton();
		label2 = new javax.swing.JLabel();
		button6 = new JButton();
		label3 = new javax.swing.JLabel();
		label4 = new javax.swing.JLabel();
		dimensionSlider = new javax.swing.JSlider();
		label5 = new javax.swing.JLabel();
		moveMakerSlider = new javax.swing.JSlider();
		startAIButton = new JButton();
		label6 = new javax.swing.JLabel();
		jScrollPane2 = new javax.swing.JScrollPane();
		mapList = new javax.swing.JList<>(mapListElements);
		jScrollPane3 = new javax.swing.JScrollPane();
		goalList = new javax.swing.JList<>(moveListElements);
		label7 = new javax.swing.JLabel();
		label8 = new javax.swing.JLabel();
		helpButton = new JButton();
		moveSlider = new javax.swing.JSlider();
		label9 = new javax.swing.JLabel();
		jlabel1 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(Map, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(Map, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE));

		label1.setName("MapsLabel"); // NOI18N
		label1.setText("Maps");

		changeMapButton.setText("Change Map");
		changeMapButton.setName("ChangeMapButton"); // NOI18N
		changeMapButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				changeMapButtonActionPerformed(evt);
			}
		});

		changeGoalButton.setText("Change Moves");
		changeGoalButton.setName("ChangeGoalsButton"); // NOI18N
		changeGoalButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				changeGoalButtonActionPerformed(evt);
			}
		});
		scaleFitCheckbox.setText("Scale Fit");
		scaleFitCheckbox.setName("Scale"); // NOI18N

		showDataCheckbox.setText("Show Field Data");
		showDataCheckbox.setSelected(true);
		;
		showDataCheckbox.setName("GoalCheck"); // NOI18N

		displayStatsCheckbox.setText("Show Stats");
		displayStatsCheckbox.setName("Stats"); // NOI18N

		showAICheckbox.setText("Show HeatMap");
		showAICheckbox.setSelected(true);
		showAICheckbox.setName("AICheck"); // NOI18N

		generateMapButton.setText("Generate Map");
		generateMapButton.setName("MapGenerationButton"); // NOI18N
		generateMapButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				generateMapButtonActionPerformed(evt);
			}
		});

		label2.setName("ToolsLabel"); // NOI18N
		label2.setText("Additional Tools");

		button6.setText("Generate Moves");
		button6.setName("testButton"); // NOI18N
		button6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				button6ActionPerformed(evt);
			}
		});

		label3.setName("GoalsLabel"); // NOI18N
		label3.setText("Heat Maps");

		label4.setName("AIWeightLabel"); // NOI18N
		label4.setText("Size: 50");

		dimensionSlider.setToolTipText("Slider For map dimensions");
		dimensionSlider.setName("dimensionSlider"); // NOI18N
		dimensionSlider.setMaximum(100);
		dimensionSlider.setMinimum(1);
		dimensionSlider.setValue(50);
		dimensionSlider.setPaintTicks(true);
		dimensionSlider.setMinorTickSpacing(10);
		dimensionSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				JSlider source = (JSlider) e.getSource();
				label4.setText("Size: " + (int) source.getValue());
			}
		});



		moveSlider.setName("moveSlider");
		moveSlider.setMinimum(1);
		moveSlider.setMaximum(100);
		moveSlider.setValue(1);
		moveSlider.setPaintTicks(true);
		moveSlider.setMinorTickSpacing(5);
		moveSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				if(!Map.isMoveStates())
					return;
				JSlider source = (JSlider) e.getSource();
				//set map step
				label9.setText("Step: " + (int) source.getValue());
				Map.setStep(source.getValue());
			}
		});
		
		label5.setName("moveLabel"); // NOI18N
		label5.setText("Moves: 50");
		
		moveMakerSlider.setName("moveMakerSlider"); // NOI18N
		moveMakerSlider.setMinimum(1);
		moveMakerSlider.setMaximum(100);
		moveMakerSlider.setPaintTicks(true);
		moveMakerSlider.setMinorTickSpacing(10);
		moveMakerSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				JSlider source = (JSlider) e.getSource();
				label5.setText("Moves: " + (int) source.getValue());
			}
		});
		startAIButton.setText("Start");
		startAIButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				startAIButtonActionPerformed(evt);
			}
		});

		label6.setName("label1"); // NOI18N
		label6.setText("Map");

		Map[] temp = FileUtils.getMapList();
		for (int x = 0; x < temp.length; x++) {
			mapListElements.addElement(temp[x].getName());
		}
		mapList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		mapList.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		mapList.setName("Maps"); // NOI18N
		jScrollPane2.setViewportView(mapList);

		goalList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		goalList.setName("Goals"); // NOI18N
		jScrollPane3.setViewportView(goalList);
		

		helpButton.setText("Help");
		helpButton.setName("HelpButton"); // NOI18N
		helpButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				helpButtonActionPerformed(evt);
			}
		});

		label9.setName("ZoomLabel"); // NOI18N
		label9.setText("Step:  ");

		jlabel1.setText("By: Andrew Zhu & Richard Li");
		jlabel1.setName("nameLabel"); // NOI18N

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap().addGroup(layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(label9, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addGroup(layout.createSequentialGroup().addGroup(layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
										.addComponent(label7, javax.swing.GroupLayout.Alignment.LEADING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										
										.addComponent(label1, javax.swing.GroupLayout.Alignment.LEADING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(changeMapButton, javax.swing.GroupLayout.Alignment.LEADING,
												javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
										.addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
												.addComponent(changeGoalButton,
														javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
												.addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(label8, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												))
								.addGroup(layout.createSequentialGroup()
										.addComponent(scaleFitCheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(displayStatsCheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup()
										.addComponent(showDataCheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(showAICheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createSequentialGroup()
										.addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup()
										.addComponent(dimensionSlider, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(moveMakerSlider, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup()
										.addComponent(startAIButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(helpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup()
										.addComponent(generateMapButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(12, 12, 12).addComponent(button6,
												javax.swing.GroupLayout.PREFERRED_SIZE, 200,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addComponent(moveSlider, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jlabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(label6, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE))
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(label6, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGap(10, 10, 10)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 138,
												Short.MAX_VALUE)
										.addComponent(jScrollPane3))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(changeMapButton, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(changeGoalButton, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(scaleFitCheckbox, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(displayStatsCheckbox, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(showDataCheckbox, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(showAICheckbox, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(generateMapButton, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(button6, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(dimensionSlider, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(moveMakerSlider, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(startAIButton, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(helpButton, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(moveSlider, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jlabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addContainerGap()));
		pack();
	}// </editor-fold>

	private void changeMapButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		// change map
		String mapWanted = mapList.getSelectedValue();
		moveListElements.clear();
		for (int x = 0; x < LoadedMaps.length; x++) {
			if (LoadedMaps[x].getName().equals(mapWanted)) {
				Map.setNewMap(LoadedMaps[x]);
				LoadedTruth = FileUtils.getTruthDataList(LoadedMaps[x].getName().substring(0,LoadedMaps[x].getName().length()-4));
				for (int w = 0; w < LoadedTruth.length; w++) {
					moveListElements.addElement(LoadedTruth[w].getName()+" moves: "+LoadedTruth[w].getMoveData().length);
				}
				return;
			}
		}
	}

	private void changeGoalButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		// change moves
		String moveWanted = goalList.getSelectedValue();
		TruthData temp = null;
		for(int x=0;x<LoadedTruth.length;x++){
			if(moveWanted.contains(LoadedTruth[x].getName())){
				temp =  LoadedTruth[x];
			}
		}
		Map[] steps = Algorithms.filter(temp, Map.getCurrentMap());
		
		System.out.println(steps.length);
		Map.setMoveStates(steps.length-1, steps,temp);
		moveSlider.setMaximum(steps.length-1);
		moveSlider.setMinimum(0);
		moveSlider.setValue(steps.length-1);
		System.out.println("Set moves!");
		
	}

	private void generateMapButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		// generate map
		boolean contains = false;
		String name = "";
		do {
			contains = false;
			name = JOptionPane.showInputDialog(
					"Enter a name for your map! (note cannot already exist and must be atleast 3 characters long!)");
			if (name == null)
				return;
			for (int x = 0; x < LoadedMaps.length; x++) {
				if (LoadedMaps[x].getName().equals(name + ".txt")) {
					contains = true;
				}
			}
		} while (contains);
		if (name.trim().length() > 3) {
			try {
				Map[] tempMaps = LoadedMaps;
				LoadedMaps = new Map[tempMaps.length + 1];
				for (int x = 0; x < tempMaps.length; x++) {
					LoadedMaps[x] = tempMaps[x];
				}
				LoadedMaps[LoadedMaps.length - 1] = MapGenerator.createMap(name + ".txt",dimensionSlider.getValue());
				mapListElements.addElement(name + ".txt");
				System.out.println("Created new map!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void button6ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		// TEST BUTTON
		if(Map.getMap()==null){
			JOptionPane.showMessageDialog(null, "No map set!");
			return;
		}
		boolean contains = false;
		String name = "";
		do {
			contains = false;
			name = JOptionPane.showInputDialog(
					"Enter a name for your moves! (note cannot already exist and must be atleast 3 characters long!)");
			if (name == null)
				return;
			for (int x = 0; x < LoadedTruth.length; x++) {
				if (LoadedTruth[x].getName().equals(name + ".txt")) {
					contains = true;
				}
			}
		} while (contains);
		if (name.trim().length() > 3) {
			try {
				TruthData[] tempMaps = LoadedTruth;
				LoadedTruth = new TruthData[tempMaps.length + 1];
				for (int x = 0; x < tempMaps.length; x++) {
					LoadedTruth[x] = tempMaps[x];
				}
				LoadedTruth[LoadedTruth.length - 1] = MapGenerator.createMoves(name + ".txt",moveMakerSlider.getValue(),Map.getMap());
				moveListElements.addElement(LoadedTruth[LoadedTruth.length - 1].getName()+" moves: "+LoadedTruth[LoadedTruth.length - 1].getMoveData().length);
				System.out.println("Created new Move list!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void startAIButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		// AI start
	}

	private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		// Help Button
		JOptionPane.showMessageDialog(this,
				"1. Select a map and click Change Map.\n" + "2. Highlight a goal.\n"
						+ "3. Select the Algorithm and Heuristic you want.\n" + "4. Click Start.\n"
						+ "Notes: Scroll wheel for zoom, click to drag map\n");
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting
		// code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.
		 * html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(UI2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(UI2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(UI2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(UI2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				UI2 test = new UI2();
				test.setVisible(true);
				test.setResizable(false);
				test.setTitle("Assignment 1 - By Andrew Zhu, Richard Li");
			}
		});
	}

	// Variables declaration - do not modify
	private DefaultListModel mapListElements = new DefaultListModel();
	private DefaultListModel moveListElements = new DefaultListModel();
	private javax.swing.JButton changeMapButton;
	private javax.swing.JButton changeGoalButton;
	private javax.swing.JButton generateMapButton;
	private javax.swing.JButton startAIButton;
	private javax.swing.JButton button6;
	private javax.swing.JButton helpButton;
	private JCheckBox scaleFitCheckbox;
	private JCheckBox showDataCheckbox;
	private JCheckBox displayStatsCheckbox;
	private JCheckBox showAICheckbox;
	private javax.swing.JLabel jlabel1;
	private javax.swing.JList<String> mapList;
	private javax.swing.JList<String> goalList;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JSlider dimensionSlider;
	private javax.swing.JSlider moveMakerSlider;
	private javax.swing.JSlider moveSlider;
	private javax.swing.JLabel label1;
	private javax.swing.JLabel label2;
	private javax.swing.JLabel label3;
	private javax.swing.JLabel label4;
	private javax.swing.JLabel label5;
	private javax.swing.JLabel label6;
	private javax.swing.JLabel label7;
	private javax.swing.JLabel label8;
	private javax.swing.JLabel label9;
	private double zoom=100;
	// End of variables declaration
	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub
		zoom = zoom - arg0.getWheelRotation() * 2;
		if(zoom<.5){
			zoom=1;
		}else if(zoom>200){
			zoom=200;
		}
		// System.out.println(moveMakerSlider.getValue()/10.0);
		Map.setZoom(zoom/ 10.0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Map.setShowStats(displayStatsCheckbox.isSelected());
		Map.setShowFieldData(showDataCheckbox.isSelected());
	}
}
