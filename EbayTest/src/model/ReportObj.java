package model;
import java.util.ArrayList;

@SuppressWarnings("static-access")
public class ReportObj {

	private String startTime ;
	private String endTime ;
	private ArrayList<Assertion> assertionList = new ArrayList<Assertion>();
	private static ReportObj reportObj;
	
	public static ReportObj getReportInstance(){
		if (reportObj == null) {
			reportObj = new ReportObj();
		}
		return reportObj;	
	}	
	

	public void updateTestExceutionStartTime(String startTime) {
		this.reportObj.startTime = startTime ;
	}
	
	public void updateTestExceutionEndTime(String endTime) {
		this.reportObj.endTime = endTime ;
	}
	
	public void addObjToProductList(Assertion assertion) {
		this.reportObj.assertionList.add(assertion);
	}


	public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public ArrayList<Assertion> getAssertionList() {
		return assertionList;
	}


	public void setAssertionList(ArrayList<Assertion> assertionList) {
		this.assertionList = assertionList;
	}
}
