package jp.co.sss.test.dto;

public class AddressInfoDTO {

	private String dbAddress;
	private String dbApartment;
	private String userAddress;
	private String userApartment;
	private String selectedRadio;
	private boolean hasDBAddress;
	
	
	public String getDbAddress() {
		return dbAddress;
	}
	public void setDbAddress(String dbAddress) {
		this.dbAddress = dbAddress;
	}
	public String getDbApartment() {
		return dbApartment;
	}
	public void setDbApartment(String dbApartment) {
		this.dbApartment = dbApartment;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserApartment() {
		return userApartment;
	}
	public void setUserApartment(String userApartment) {
		this.userApartment = userApartment;
	}
	public boolean isHasDBAddress() {
		return hasDBAddress;
	}
	public void setHasDBAddress(boolean hasDBAddress) {
		this.hasDBAddress = hasDBAddress;
	}
	public String getSelectedRadio() {
		return selectedRadio;
	}
	public void setSelectedRadio(String selectedRadio) {
		this.selectedRadio = selectedRadio;
	}
	
	
}