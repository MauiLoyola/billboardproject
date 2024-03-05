package billboardanalyzer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.JScrollBar;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JScrollPane;

public class BillboardGUI extends JFrame {

	private static BillboardTopMain main = new BillboardTopMain(); 
	//allows main Methods to be called
	
	private JPanel contentPane;
	private static int year; //static ints to be used in method
	private static int month;

	private String week = null; 
	private String song = null;
	private JTable table;
	private DefaultTableModel tm;
	
	public static void main(String[] args) {
		System.out.println("DONT RUN ON THIS CLASS");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BillboardGUI frame = new BillboardGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void BillboardGUICall() {//calls basic GUI
		new BillboardGUI(); 
	}

	public BillboardGUI() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		int ScreenHeight = (int) size.getHeight();
		int ScreenWidth = (int) size.getWidth();
		setBounds(100, 100, (int) (ScreenWidth * .50), (int) (ScreenHeight*.60)); //edit to scan resolution
		contentPane = new JPanel();
		contentPane.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		contentPane.setBackground(new Color(252, 250, 228));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Maui's Billboard Top 100 ");
		lblNewLabel_4.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 24));
		lblNewLabel_4.setBounds(157, 10, 385, 63);
		//lblNewLabel_4.setBounds(157, 10, 385, 63);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_4_1 = new JLabel("Search Engine");
		lblNewLabel_4_1.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 24));
		lblNewLabel_4_1.setBounds(235, 37, 217, 63);
		contentPane.add(lblNewLabel_4_1);

		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(228, 202, 224, 21);
		contentPane.add(comboBox_3);
		//comboBox for picking search method

		comboBox_3.addItem("<Select>");
		comboBox_3.addItem("Search Based on Chart Date");
		comboBox_3.addItem("Search Based on Song Name");
		

		comboBox_3.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				if (comboBox_3.getSelectedIndex() == 0) {	
					return;
				} else if(comboBox_3.getSelectedIndex() == 1)	{
					searchDate();
					//starts method components to show Date Search GUI
				} else if(comboBox_3.getSelectedIndex() == 2) {
					searchName();
					System.out.println("not yet implemented");
					//would implement separate method for searching based on song names
					return; //remove when fully implemented
				
				}
				//hides menu GUI
				comboBox_3.setVisible(false);
				lblNewLabel_4_1.setVisible(false);
				lblNewLabel_4.setVisible(false);
			}
		});
	}


	public void searchDate () {	//Date Search GUI

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 33, 81, 21);
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox.setMaximumRowCount(70);
		contentPane.add(comboBox);
		comboBox.addItem("<Select>");
		for(int i = 1958; i < 2024; i++) {
			comboBox.addItem(i);	
		}
		//creates comboBox for Years 1958-2023

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setMaximumRowCount(70);
		comboBox_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox_1.setBounds(120, 33, 81, 21);
		contentPane.add(comboBox_1);
		comboBox_1.setEnabled(false);
		comboBox_1.addItem("<Select>"); 
		//creates empty comboBox to be filled with months

		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		comboBox_2.setBounds(10, 126, 81, 21);
		contentPane.add(comboBox_2);
		comboBox_2.setEnabled(false);
		comboBox_2.addItem("<Select>");
		//creates empty comboBox to be filled with weeks 

		JLabel lblNewLabel = new JLabel("Select Year");
		lblNewLabel.setBounds(10, 10, 100, 29);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Select Month");
		lblNewLabel_1.setBounds(120, 10, 100, 29);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Select Week");
		lblNewLabel_2.setBounds(10, 111, 66, 13);
		contentPane.add(lblNewLabel_2);

		JButton btnNewButton_1 = new JButton("Confirm");
		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 10));
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setBounds(20, 157, 90, 36);
		contentPane.add(btnNewButton_1);
		//confirm button after selecting all options

		tm = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column)
			{
				return false;//This causes all cells within table to be not editable
			}
		};
	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVisible(false); //hides scrollpane
		scrollPane.setBounds(230, 33, 500, 425);
		contentPane.add(scrollPane);
		table = new JTable(tm); //creates new table based on DefaultTableModel (tm)
		scrollPane.setViewportView(table); //sets scrollpane view as table
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		tm.addColumn("#"); 
		tm.addColumn("Song");
		tm.addColumn("Performer");
		table.getColumnModel().getColumn(0).setPreferredWidth(25);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setPreferredWidth(225);
		//creates columns and sets their widths for table

		JLabel lblNewLabel_3 = new JLabel();
		lblNewLabel_3.setBounds(230, 10, 496, 29);
		contentPane.add(lblNewLabel_3);
		lblNewLabel_3.setVisible(false);
		//label for displaying table name


		String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};
		//string of months to be referenced for comboBox_1.additem()
		comboBox.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				comboBox_2.setSelectedIndex(0);
				comboBox_2.setEnabled(false);
				btnNewButton_1.setEnabled(false);
				scrollPane.setVisible(false);
				lblNewLabel_3.setVisible(false);
				//disables visibility when comboBox_year is interacted with. 
				//otherwise you would see the table/combobox being actively changed

				if(comboBox_1.getItemCount() < 13 ){ //checks if comboBox_1 doesnt have all 12 months 
					while(comboBox_1.getItemCount() > 1) { //if so it resets the comboBox
						comboBox_1.removeItemAt(comboBox_1.getItemCount()-1); //removes all except first index
					} 
					for(int i = 0; i < months.length; i++) {
						comboBox_1.addItem(months[i]); //adds all 12 months 
					}
				}
				//this is done so that the months can be reset everytime a new year is picked
				//the first year(1958) and last(2023) don't contain all 12 months
				//so the comboBox would need to reset should you want to switch from
				//a 12 month year to a 5 month year, and this also allows switching
				//from every year with 12 months in it(pretty much all of them) to be
				//seamless and not reset the comboBox_1 at all.



				if(comboBox.getSelectedIndex() == 0) {//<select> option: this is the null
					comboBox_1.setEnabled(false);
					return;
				} else if(comboBox.getSelectedIndex() == 1) {//year 1958
					for(int i = 1; i < 8; i++) {
						comboBox_1.removeItemAt(1); //removes months 1-7, leaving august to december
					}
				} else if(comboBox.getSelectedIndex() == 66) {//year 2023
					for(int i = 5; i < months.length; i++) { 
						comboBox_1.removeItemAt(6); //removes months 6-12, leaving january to may

					}
				} 

				comboBox_1.setEnabled(true);//sets the month comboBox to be selectable
				year = Integer.valueOf(String.valueOf(comboBox.getSelectedItem()));
				//turns object into string which is parsed into integer 
				if(!(comboBox_1.getSelectedIndex() == 0)) {
					comboWeekFind(comboBox, comboBox_1, comboBox_2); 
					//this calls a method that triggers the WeekFinder in main
					//more details in method
				}
			}
		});

		comboBox_1.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				comboWeekFind(comboBox, comboBox_1, comboBox_2); 
				//calls same method should this comboBox be changed without
				//changing the comboBox_year
			}
		});

		comboBox_2.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				scrollPane.setVisible(false);
				lblNewLabel_3.setVisible(false);
				//table is set invisible when weeks is changed
				//only set visible again when confirm button is pressed

				if(comboBox_2.getSelectedIndex() == 0) {//option <select>
					btnNewButton_1.setEnabled(false);
					return;
				}

				btnNewButton_1.setEnabled(true);
				week = String.valueOf(comboBox_2.getSelectedItem());
				lblNewLabel_3.setText("For the week of: " + week); 	
				//sets text above table to correspond to the week displayed
			}
		});

		btnNewButton_1.addActionListener (new ActionListener () {//confirm button could be merged with combobox2 but this just feels more interactive.
			public void actionPerformed(ActionEvent e) {
				main.ChartFinder(week); //ChartFinder call
				
				tm.setRowCount(0); //resets table
				
				while(table.getColumnModel().getColumnCount() > 3) {
				TableColumn tcol = table.getColumnModel().getColumn(3);
				table.removeColumn(tcol);
				table.getColumnModel().getColumn(0).setPreferredWidth(25);
				table.getColumnModel().getColumn(1).setPreferredWidth(250);
				table.getColumnModel().getColumn(2).setPreferredWidth(225);
				}
				
				for(int i = 0; i < main.getChartData().size(); i++) { 
					String[] chartRow = main.getChartData().get(i).split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); 
					//chartRow splits chartData into 3 separate parts
					//(chartPos,songName,PerformerName) = chartRow ex.
					
					//System.out.println(chartRow[0]);
					tm.addRow(new Object[] {chartRow[0],chartRow[1], chartRow[2]}); //adds each of the rows to table 
				} //loops through all of chartData(100 rows) 
				scrollPane.setVisible(true);
				lblNewLabel_3.setVisible(true);
				//sets table and label visible


			}
		});

		table.addMouseListener(new MouseAdapter() { //checks for mouse clicks on table
			public void	 mousePressed(MouseEvent e) {
				if(e.getClickCount() == 2 && table.getSelectedColumn() == 1) {//checks double click in song column
					song = table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString() + "," + table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()+1).toString();
					//gets value at (row,column) which is song then appends ",valueAt(row,column+1)"
					//which is performerName and assigns it to variable song which will be passed to songFinder
					main.SongFinder(song);
					//System.out.println(main.getSongStats());
					if(main.getSongStats() != null) {
						tm.setRowCount(0);
						if(table.getColumnCount() < 4) {
						tm.addColumn("Date");
						tm.addColumn("Consecutive Weeks on Chart");
						tm.addColumn("Instance");
						} 
						
							while(table.getColumnCount() > 6) {
							TableColumn tcol = table.getColumnModel().getColumn(6);
							table.removeColumn(tcol);
							}
						
						for(int i = 0; i < main.getSongStats().size(); i++) {
							String[] songRow = main.getSongStats().get(i).split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
							tm.addRow(new Object[] {songRow[0], songRow[1], songRow[2], songRow[3], songRow[4], songRow[5]});
							}
						}
					}
				}
				
		});
	}//end searchDate

	public void searchName() {
		//implement search based on songName
	}
	
	public static void comboWeekFind(JComboBox cb, JComboBox cb1, JComboBox cb2) {
		//comboWeekFind used in SearchDate
		if(cb.getSelectedIndex() == 1) {	//edge case for year 1958
			month = cb1.getSelectedIndex() + 7;//add 7 to index to equal august
		} else {
			month = cb1.getSelectedIndex();//pulls index as every month corresponds to its index
		}
		main.WeekFinder(month, year); 
		if(main.getWeeks().isEmpty() == true) { //if weeks is empty the weeks combobox is disabled, this should be the case always for the first call as there is no selected month
			cb2.setEnabled(false);
			return;//ensures that the code goes no further
		} else {
			cb2.setEnabled(true); //if weeks contains anything this gets called and code continues
		}
		while(cb2.getItemCount() > 1) {
			cb2.removeItemAt(cb2.getItemCount()-1); //removes all except first index
		} 
		//resets weeks comboBox should this method be called again
		
		for(int i = 0; i < main.getWeeks().size(); i++) {
			cb2.addItem(main.getWeeks().get(i));
		}//adds everything from main.getWeeks() to ComboBox2
	}

}//end class
