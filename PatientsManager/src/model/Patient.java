package model;

public class Patient implements IAppElement{
	private String patientId;
	private String name;
	private String SSN;
	private String address;
	private int consNum;
	
	/** Constructors */
	
	public Patient() {}
	
	public Patient(String name, String SSN, String address)
	{
			patientId = SSN;
			this.name = name;
			this.SSN = SSN;
			this.address = address;	
			consNum = 0;
	}
	
	/** Getters and setters */
	public String getPatientID() {
		return patientId;
	}
	public void setPatientID(String patientId) {
		this.patientId = patientId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSSN() {
		return SSN;
	}
	public void setSSN(String sSN) {
		SSN = sSN;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setConsNum(int num)
	{
		consNum = num;
	}
	public int getConsNum()
	{
		return consNum;
	}
	
	/** Others */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append(", ");
		sb.append(SSN);
		sb.append(", ");
		sb.append(address);
		return sb.toString();
	}
}
