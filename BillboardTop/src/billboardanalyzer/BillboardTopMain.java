package billboardanalyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


class BillboardTopMain {
	private static BillboardGUI GUI = new BillboardGUI(); //allows GUI methods to be called
	private static ArrayList<BillboardTopRecord> records = new ArrayList<>(); //creates arrayList to store all records(csv)
	private static List<String> weeks; //for relaying weeks to GUI
	private static List<String> chartData; //for relaying chartData to GUI
	private static List<String> songStats; //for relaying songs to GUI

	public static void main(String[] args) {

		try {
			File file = new File("Hot 100.csv");
			Scanner scanner = new Scanner(file);

			//skips the header line
			scanner.nextLine();

			while (scanner.hasNextLine()) {
				String csvString = scanner.nextLine();
				BillboardTopRecord record = new BillboardTopRecord(csvString); 
				//calls billboardTopRecord to split string and assigns it to record
				records.add(record);
			
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
		}

		//for testing 
		/*
		for (BillboardTopRecord record : records) {
			System.out.println(record);
		}	
		 */

		// TEST CASES
		// May 27th 1978: two 18's
		// June 19th 1971: two 30's 
		// May 20th 1967: Quoted String Placement: 11 
		/*
		for(BillboardTopRecord record : records) {
			if(record.getChartDate().equals("5/20/1967")) {
				if(record.getChartPosition() == 11) {
					System.out.println(record.getSongName());
				}
			}
		}
		*/

		GUI(); //calls GUI method

	}//end main

	/*
	 * TO DO LIST
	 * Figure out OPENCSV to load songs more efficiently
	 *
	 * GUI:
	 * Scale GUI to Resolution, the GUI is currently only scaled to laptop(1080p)
	 * Change Boldness for Readability
	 * Search By Artist or Song Name
	 * Recolor GUI
	 * 
	 * Other:
	 * Find most popular song of each year based on factors?? (consecutive weeks)
	 * Quiz using performers most popular songs.. !contains maybe
	 *	Quiz based on songs - 2 weeks
			Search based on queries - a few days
			Recommendations based on song - a few weeks
			Optimizations of search algorithms. - multiple days
			Find how many times an artist appears in the billboard charts. 
			Find how long or how many times a song has been number 1
			Take two user inputted songs and compare their stats.


			O(nlog(n)) time because of Collections.sort() uses Merge Sort
	 */


	public void WeekFinder(int month, int year) {
		weeks = new ArrayList<String>();
		for (BillboardTopRecord record : records) {//loops through records
			if(record.getChartMonth() == month) {//checks records for given month
				if(record.getChartYear() == year) {//checks records for given year
					if(!weeks.contains(record.getChartDate())) { //only adds weeks once to List<String>
						weeks.add(record.getChartDate());
						//System.out.println(weeks);
					}
				}
			}
			if(weeks.size() == 5) {//months can't be more than 5 weeks so when 5 it hit we know its the max
				break;
			}
		}
		//sorts based on the substring day in date and uses a comparator with Collections.sort
		//this is because collections.sort doesn't sort numbers in a string correctly so
		//a comparator is needed to correctly compare the two substrings in the merge sort method
		Collections.sort(weeks, new Comparator<String>(){ 
			public int compare(String str1, String str2) {
				String substr1 = str1.substring(str1.indexOf("/")+ 1, str1.lastIndexOf("/")); //sorts weeks based on substring day
				String substr2 = str2.substring(str2.indexOf("/") + 1, str2.lastIndexOf("/"));
				//System.out.println(substr1);
				//System.out.println(substr2);

				return Integer.valueOf(substr1).compareTo(Integer.valueOf(substr2));
			}
		});
		//System.out.println(weeks);
	}//end weekfinder	


	public void ChartFinder (String week) {
		chartData = new ArrayList<String>();

		while(chartData.size() < 100) {//data always is 100 records long
			for (BillboardTopRecord record : records) {//loops through records
				if(record.getChartDate().equals(week)) {//checks for given week
					chartData.add(String.valueOf(record.getChartPosition())+ "," +record.getSongName() + "," +record.getPerformerName());
					//adds all wanted fields together into one long string and adds it to arrayList<String>
				}
			}
		} 
		//similar to last comparator/collections.sort as we need to sort based on chartPos
		Collections.sort(chartData, new Comparator<String>(){
			public int compare(String str1, String str2) {
				String substr1 = str1.substring(0, str1.indexOf(",")); 
				String substr2 = str2.substring(0, str2.indexOf(","));
				//System.out.println(substr1);
				//System.out.println(substr2);

				return Integer.valueOf(substr1).compareTo(Integer.valueOf(substr2));
			}
		});
		//System.out.println(chartData);
	}//End ChartFinder


	public void SongFinder(String song) {
		//IMPORTANT TIPS FOR IMPLEMENTATION...
		//SINCE THE CSV IS ORDERED BY SONGS ALPHABETICALLY, YOU CAN SEARCH FOR INSTANCE
		//USING BINARY SEARCH AND HAVE A SIMILIAR IMPLEMENTATION TO queueWithTwitterData WHERE
		//YOU SEARCH MORE EFFICIENTLY RATHER THAN for(BillboardTopRecord)
		//ONLY WOULD WORK WITH THIS ONE.
		songStats = new ArrayList<>();
		
		for(BillboardTopRecord record: records) {
			if((record.getSongName()+","+record.getPerformerName()).equals(song)) {
				songStats.add(record.getChartPosition()+ "," + song + "," + record.getChartDate() + "," + record.getConsecutiveWeeksOnChart() + "," + record.getChartInstance());
			}
			/*
			for(int i = 0; i < songStats.size() - 1; i++) {
				System.out.println(songStats.get(i));
				System.out.println(i);
			}
			*/
		}
		/*
		int lowIndex = 0;
		int highIndex = records.size()-1;
		songStats = new ArrayList<>();
		//songs with quotations may appear within alphabetical order despite the fact that they need to be at the top
		//might circumvent with searching for first non quotation character?

		while(highIndex >= lowIndex) {
			int mid = (highIndex + lowIndex)/2;
			BillboardTopRecord node = records.get(mid);
			System.out.println(node.getSongName()+","+node.getPerformerName());
			System.out.println("Quoted String: "+(node.getSongName().substring(1,(node.getSongName().length()-1))+","+node.getPerformerName()));
			System.out.println(song);
			System.out.println("high: "+highIndex);
			System.out.println("low: "+lowIndex);
			System.out.println("Mid: "+mid);
			if((node.getSongName()+","+node.getPerformerName()).equals(song)) {
				int index = mid;
				while((node.getSongName()+","+node.getPerformerName()).equals(song) || (node.getSongName()+","+node.getPerformerName()).substring(1,(node.getSongName()+","+node.getPerformerName()).length()).equals(song)) {
					node = records.get(--index);
					System.out.println(node);

				}
				index++;
				node = records.get(index);
				System.out.println("RECORD BEGIN" + records.get(index));
				while((node.getSongName()+","+node.getPerformerName()).equals(song) || (node.getSongName()+","+node.getPerformerName()).substring(1,(node.getSongName()+","+node.getPerformerName()).length()).equals(song)) {
					node = records.get(++index);
					System.out.println(node);
					//songStats.add(node);
				}
				return;
			} else if ((node.getSongName().substring(1,(node.getSongName().length()-1))+","+node.getPerformerName()).equals(song)) {


			}else if((node.getSongName()+","+node.getPerformerName()).compareTo(song) < 0 || (node.getSongName()+","+node.getPerformerName()).substring(1,(node.getSongName()+","+node.getPerformerName()).length()).compareTo(song) < 0) {
				lowIndex = mid + 1;
			} else {
				highIndex = mid - 1;
			}
		}//end binary search 	
		return;
	}
		*/
	}

	public static void GUI() {

		GUI.BillboardGUICall();
		GUI.setVisible(true);
	}
	
	public List<String> getChartData() {
		return chartData;
	}

	public List<String> getWeeks() {
		return weeks;
	}
	
	public List<String> getSongStats() {
		return songStats;
	}
}//end class


