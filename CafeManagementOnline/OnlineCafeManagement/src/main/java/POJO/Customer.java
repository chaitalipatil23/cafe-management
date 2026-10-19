package POJO;

public class Customer {
	private String custName;
	private String emailId;
	private String custPassword;
	private String custAddress;
	private String custNo;
	private String custLocation;
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(String custName, String emailId, String custPassword, String custAddress, String custNo,
			String custLocation) {
		super();
		this.custName = custName;
		this.emailId = emailId;
		this.custPassword = custPassword;
		this.custAddress = custAddress;
		this.custNo = custNo;
		this.custLocation = custLocation;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getCustPassword() {
		return custPassword;
	}
	public void setCustPassword(String custPassword) {
		this.custPassword = custPassword;
	}
	public String getCustAddress() {
		return custAddress;
	}
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getCustLocation() {
		return custLocation;
	}
	public void setCustLocation(String custLocation) {
		this.custLocation = custLocation;
	}
	@Override
	public String toString() {
		return "Customer [custName=" + custName + ", emailId=" + emailId + ", custPassword=" + custPassword
				+ ", custAddress=" + custAddress + ", custNo=" + custNo + ", custLocation=" + custLocation + "]";
	}
	

}
