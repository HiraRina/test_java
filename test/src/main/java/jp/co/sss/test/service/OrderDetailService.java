package jp.co.sss.test.service;

import org.springframework.stereotype.Service;

import jp.co.sss.test.dto.AddressInfoDTO;
import jp.co.sss.test.entity.User;

@Service
public class OrderDetailService {
	
	public AddressInfoDTO createAddressInfo(User loginUser, String selectedRadio,String selectedAddress, String selectedApartment) {
		AddressInfoDTO dto = new AddressInfoDTO();
		
		dto.setSelectedRadio(selectedRadio);
		
		boolean hasDBAddress = hasDBAddress(loginUser);
		dto.setHasDBAddress(hasDBAddress);
		
		String userAddress = "";  // 住所部分
	    String userApartment = "";  // アパート名部分
	    String dbAddress = ""; //DBに登録されている住所
	    String dbApartment = ""; //DBに登録されているアパート
		
    	if(hasDBAddress) {
    		//DB に住所がある場合は DB の住所を２つに分ける
    		String userFullAddress =loginUser.getUserAddress();
    		String[] fullAddress = userFullAddress.split(" ",2);
    		dbAddress = fullAddress.length > 0 ? fullAddress[0] : "";
    		dbApartment = fullAddress.length > 1 ? fullAddress[1] : "";
    		dto.setDbAddress(dbAddress);
    		dto.setDbApartment(dbApartment);
    	}
    	
	    if("radio1".equals(selectedRadio)) {
	    	if(!hasDBAddress) {
	    		dto.setUserAddress(defaultIfNull(userAddress));
	    		dto.setUserApartment(defaultIfNull(userApartment));
	    	}
	    } else {
	    	//radio2, radio3は編集で戻った場合、入力された情報を表示する
    		dto.setUserAddress(defaultIfNull(selectedAddress));
    		dto.setUserApartment(defaultIfNull(selectedApartment));
	    }
	    return dto;
	}
	
	private String defaultIfNull(String value) {
	    return value != null ? value : "";
	}

	//住所がDBに登録があるかの確認
	public boolean hasDBAddress(User loginUser) {
		return loginUser.getUserAddress() != null && !loginUser.getUserAddress().isEmpty();
	}


}
