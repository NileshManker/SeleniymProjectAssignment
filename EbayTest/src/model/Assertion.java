package model;

public class Assertion {

	private String assertionStatement;
	private boolean assertionResult ;
	private String failedAssertionScreenShotPath ;
	
	public Assertion(String assertionStatement, boolean assertionResult,
			String failedAssertionScreenShotPath) {
		super();
		this.assertionStatement = assertionStatement;
		this.assertionResult = assertionResult;
		this.failedAssertionScreenShotPath = failedAssertionScreenShotPath;
	}
	
	public String getAssertionStatement() {
		return assertionStatement;
	}
	public void setAssertionStatement(String assertionStatement) {
		this.assertionStatement = assertionStatement;
	}
	public boolean isAssertionResult() {
		return assertionResult;
	}
	public void setAssertionResult(boolean assertionResult) {
		this.assertionResult = assertionResult;
	}

	public String getFailedAssertionScreenShotPath() {
		return failedAssertionScreenShotPath;
	}

	public void setFailedAssertionScreenShotPath(
			String failedAssertionScreenShotPath) {
		this.failedAssertionScreenShotPath = failedAssertionScreenShotPath;
	}
	
	
	
	
}
