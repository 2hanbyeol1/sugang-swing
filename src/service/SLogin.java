package service;

import model.MPersonalInfo;
import valueObject.VLogin;
import valueObject.VPersonalInfo;

public class SLogin {

	public VPersonalInfo validate(VLogin vLogin) {
		MPersonalInfo mPersonalInfo = new MPersonalInfo();
		VPersonalInfo vPersonalInfo = mPersonalInfo.read(vLogin.getId()); // ���� �б�
		if(vPersonalInfo == null) {
			// File not found (�������� �ʴ� id)
			return null;
		} else if(vLogin.getPassword().equals(vPersonalInfo.getPassword())) { // �α��� ���, ȸ������ ��� ��ġ
			return vPersonalInfo;
		} else { // ���̵�� ���������� ��� ��ġ���� ����
			return null;
		}
	}
	
}
