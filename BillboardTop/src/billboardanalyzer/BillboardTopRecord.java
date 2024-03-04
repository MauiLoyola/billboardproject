package billboardanalyzer;

import java.util.Arrays;

public class BillboardTopRecord{
	private int chartPos; //position in the top 100
	private String chartDate; //the week that this instance is at
	private int chartMonth;//
	private int chartDay;//
	private int chartYear; //
	private String songName; //song name
	private String performerName; //performer
	private int chartInstance; //increments based on if it reappears in the charts
	private int weekOnChart; //overall weeks on top 100
	private int consWeekOnChart; //consecutive weeks in each instance
	private	int previousPos; //shows previous position in chart
	private int maxPos; //shows highest position on chart so far
	private int minPos; //shows lowest position on chart so far
	private String chartDebut; //first week it appeared in chart
	private String chartURL; //shows website of specific chart instance: WILL MOST LIKELY NOT USE	
	private int[] dates;


	public BillboardTopRecord(String csvString) {
		String[] tokens = csvString.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
		//splits String(row) into tokens based on "," and lookforwards 
		this.chartPos = Integer.parseInt(tokens[0]);
		this.chartDate = tokens[1];
		this.songName = tokens[2];
		this.performerName = tokens[3];
		this.chartInstance = Integer.parseInt(tokens[4]);
		this.weekOnChart = Integer.parseInt(tokens[5]);
		this.consWeekOnChart = Integer.parseInt(tokens[6]);
		this.previousPos = Integer.parseInt(tokens[7]);
		this.maxPos = Integer.parseInt(tokens[8]);
		this.minPos = Integer.parseInt(tokens[9]);
		this.chartDebut = tokens[10];
		this.chartURL = tokens[11];
		this.dates = Arrays.stream(tokens[1].split("/")).mapToInt(Integer::parseInt).toArray(); //splits chartDate and parses it into an int array
		//splits chartDate and parses into int using array.stream. 
		//Then assigns it to dates[]
		
		this.chartMonth = dates[0];
		this.chartDay = dates[1];
		this.chartYear = dates[2];
	}

	//setters and getters
	public int getChartPosition() {
		return chartPos;
	}
	public void setChartPosition(int chartPos) {
		this.chartPos = chartPos;
	}

	public String getChartDate() {
		return chartDate;
	}
	public void setChartDate(String chartDate) {
		this.chartDate = chartDate;
	}

	public int getChartMonth() {
		return chartMonth;
	}
	public void setChartMonth(int chartMonth ) {
		this.chartMonth = chartMonth;
	}

	public int getChartDay() {
		return chartDay;
	}
	public void setChartDay(int chartDay ) {
		this.chartDay = chartDay;
	}

	public int getChartYear() {
		return chartYear;
	}
	public void setChartYear(int chartYear ) {
		this.chartYear = chartYear;
	}

	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}

	public String getPerformerName() {
		return performerName;
	}
	public void setPerformerName(String performerName) {
		this.performerName = performerName;
	}

	public int getChartInstance() {
		return chartInstance;
	}
	public void setChartInstance(int chartInstance) {
		this.chartInstance = chartInstance;
	}

	public int getWeeksOnChart() {
		return weekOnChart;
	}
	public void setWeeksOnChart(int weekOnChart ) {
		this.weekOnChart = weekOnChart;
	}

	public int getConsecutiveWeeksOnChart() {
		return consWeekOnChart;
	}
	public void setConsecutiveWeeksOnChart(int consWeekOnChart ) {
		this.consWeekOnChart = consWeekOnChart;
	}

	public int getPreviousPosition() {
		return previousPos;
	}
	public void setPreviousPostion(int previousPos ) {
		this.previousPos = previousPos;
	}

	public int getMaxPos() {
		return maxPos;
	}
	public void setMaxPos(int maxPos ) {
		this.maxPos = maxPos;
	}

	public int getMinPos() {
		return maxPos;
	}
	public void setMinPos(int minPos ) {
		this.minPos = minPos;
	}

	public String getChartDebut() {
		return chartDebut;
	}
	public void setChartDebut(String chartDebut) {
		this.chartDebut = chartDebut;
	}

	public String getChartURL() {
		return chartURL;
	}
	public void setChartURL(String chartURL) {
		this.chartURL = chartURL;
	}


	//toString for outputting full record
	public String toString() {
		return 	"Record {"+ 
				"chartPos='" + chartPos + '\'' +
				", chartDate='" + chartDate + '\'' +
				", song='" + songName + '\'' +
				", performer='" + performerName + '\'' +
				", chartInstance='" + chartInstance + '\'' +
				", weekOnChart='" + weekOnChart + '\'' +
				", consWeekOnChart='" + consWeekOnChart + '\'' +
				", maxPos'" + maxPos + '\'' +
				", minPos='" + minPos + '\'' + 
				", chartDebut='" + chartDebut + '\'' +
				'}';

	}
}
