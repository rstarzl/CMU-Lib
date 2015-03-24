package edu.cmu.cmulib.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class UI {

	private final static String[] ALGOS_NAME = { "SVD", "Decision Tree" };
	private static final String TITLE = "CMULib";
	private static final int GRID_GAP = 10;
	private static final int BORDER_LEN = 10;
	private static final int FIELD_LEN = 30;
	private static final int INPUT_ITEM_NUM = 3;
	private static final int DUMP_ITEM_NUM = 2;
	private static final int TEXT_AREA_SIZE = 50;

	private final JTextField inputPathField = new JTextField(FIELD_LEN);
	private final JTextField outputFolderField = new JTextField(FIELD_LEN);
	private final JButton browse1Btn = new JButton("Browse");
	private final JButton browse2Btn = new JButton("Browse");
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private final JComboBox algoListBox = new JComboBox(ALGOS_NAME);

	private final JButton runBtn = new JButton("Run");
	private final JTextArea progressArea = new JTextArea(TEXT_AREA_SIZE,
			TEXT_AREA_SIZE);

	@SuppressWarnings("rawtypes")
	private final JComboBox fileListBox = new JComboBox();
	private final JTextField dumpPathField = new JTextField(FIELD_LEN);
	private final JButton browse3Btn = new JButton("Browse");
	private final JButton startDumpBtn = new JButton("Start Downloading");

	private final JFrame frame = new JFrame(TITLE);

	// private final Model model

	public UI() {
		// model = inputModel;

		JPanel labelsPanel = new JPanel();
		labelsPanel.setLayout(new GridLayout(INPUT_ITEM_NUM, 1, GRID_GAP,
				GRID_GAP));
		labelsPanel.add(new JLabel("Input Path: "));
		labelsPanel.add(new JLabel("Output Folder: "));
		labelsPanel.add(new JLabel("Algorithm: "));
		labelsPanel.setBorder(BorderFactory.createEmptyBorder(BORDER_LEN,
				BORDER_LEN, BORDER_LEN, BORDER_LEN));

		JPanel fieldsPanel = new JPanel();
		fieldsPanel.setLayout(new GridLayout(INPUT_ITEM_NUM, 1, GRID_GAP,
				GRID_GAP));
		fieldsPanel.setBorder(BorderFactory.createEmptyBorder(BORDER_LEN,
				BORDER_LEN, BORDER_LEN, BORDER_LEN));

		JPanel grid1 = new JPanel();
		grid1.add(inputPathField);
		grid1.add(browse1Btn);
		fieldsPanel.add(grid1);

		JPanel grid2 = new JPanel();
		grid2.add(outputFolderField);
		grid2.add(browse2Btn);
		fieldsPanel.add(grid2);

		JPanel grid3 = new JPanel();
		grid3.add(algoListBox);
		fieldsPanel.add(grid3);

		/** input panel */
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BorderLayout());
		inputPanel.add(labelsPanel, BorderLayout.WEST);
		inputPanel.add(fieldsPanel, BorderLayout.EAST);
		inputPanel.setBorder(BorderFactory.createEmptyBorder(BORDER_LEN,
				BORDER_LEN, BORDER_LEN, BORDER_LEN));

		/** run panel */
		JPanel runPanel = new JPanel();
		runPanel.add(runBtn);
		runBtn.setPreferredSize(new Dimension(500, 50));
		runPanel.setBorder(BorderFactory.createEmptyBorder(BORDER_LEN,
				5 * BORDER_LEN, 5 * BORDER_LEN, 5 * BORDER_LEN));

		/** progress panel */
		JPanel progressPanel = new JPanel();
		JScrollPane scrollPane = new JScrollPane(progressArea);
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		progressPanel.add(scrollPane);
		progressPanel.setBorder(new TitledBorder(new EtchedBorder(), "Progress & Result",
				TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));


		/** dump Panel */
		JPanel dumpPanel = new JPanel();
		
		JPanel pathNamePanel = new JPanel();
		pathNamePanel.setLayout(new GridLayout(DUMP_ITEM_NUM, 1, GRID_GAP,
				GRID_GAP));

		pathNamePanel.add(new JLabel("File to Download: "));
		pathNamePanel.add(new JLabel("Download to: "));
		
		JPanel pathFieldPanel = new JPanel();
		pathFieldPanel.setLayout(new GridLayout(DUMP_ITEM_NUM, 1, GRID_GAP,
				GRID_GAP));
		pathFieldPanel.add(fileListBox);
		JPanel downtoBrowse = new JPanel();
		downtoBrowse.add(dumpPathField);
		downtoBrowse.add(browse3Btn);
		pathFieldPanel.add(downtoBrowse);
		
		dumpPanel.setLayout(new BorderLayout());
		dumpPanel.add(pathNamePanel, BorderLayout.WEST);
		dumpPanel.add(pathFieldPanel, BorderLayout.EAST);
		dumpPanel.setBorder(BorderFactory.createEmptyBorder(BORDER_LEN,
				BORDER_LEN, BORDER_LEN, BORDER_LEN));
		
		
		/** start downloading button panel */
		JPanel startDownPanel = new JPanel();
		startDownPanel.add(startDumpBtn);
		startDumpBtn.setPreferredSize(new Dimension(500, 50));
		startDownPanel.setBorder(BorderFactory.createEmptyBorder(BORDER_LEN,
				BORDER_LEN, 5 * BORDER_LEN, BORDER_LEN));		

		/** put together */
		frame.getContentPane().setLayout(
				new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.getContentPane().add(inputPanel);
		frame.getContentPane().add(runPanel);
		frame.getContentPane().add(progressPanel);
		frame.getContentPane().add(dumpPanel);
		frame.getContentPane().add(startDownPanel);
		
		frame.pack();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		/** SET ALL ACTION LISTENER HERE */
		setAllActionListener();
	}
	
	private void setAllActionListener(){
		// TODO SET ACTION LISTERNER FOR BUTTONS AND TEXT FIELD
	}

	/** show the frame */
	public void show() {
		frame.setVisible(true);
	}

	/** create GUI and show it */
	public static void createAndShowGUI() {
		UI gui = new UI();
		gui.show();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createAndShowGUI();
			}
		});
	}

}
