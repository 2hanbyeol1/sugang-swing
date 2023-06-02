package service;

import model.MPersonalInfo;
import valueObject.VLogin;
import valueObject.VPersonalInfo;

public class SLogin {

	public VPersonalInfo validate(VLogin vLogin) {
		MPersonalInfo mPersonalInfo = new MPersonalInfo();
		VPersonalInfo vPersonalInfo = mPersonalInfo.read(vLogin.getId()); // 파일 읽기
		if(vPersonalInfo == null) {
			// File not found (존재하지 않는 id)
			return null;
		} else if(vLogin.getPassword().equals(vPersonalInfo.getPassword())) { // 로그인 비번, 회원가입 비번 일치
			return vPersonalInfo;
		} else { // 아이디는 존재하지만 비번 일치하지 않음
			return null;
		}
	}
	
}
