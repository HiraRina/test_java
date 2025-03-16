package jp.co.sss.test.dto;

import jp.co.sss.test.entity.User;

public class UserDTO {

	private Integer userId;
	private String userName;
	private String userAddress;
	
	public UserDTO(User user) {
		this.userId = user.getUserId();
		this.userName = user.getUserName();
		this.userAddress = user.getUserAddress();
	}
	
	public Integer getUserId() { return userId;}
	public String getUserName() { return userName; }
	public String getUserAddress() { return userAddress; }

}
