package main;

import javax.swing.JOptionPane;

import presentation.PMain;
import presentation.PSugangsincheong;
import service.SRemember;
import valueObject.VPersonalInfo;

public class Main {

	public static void main(String[] args) {
		SRemember sRemember = new SRemember();
		VPersonalInfo vPersonalInfo = sRemember.read();
		if (vPersonalInfo != null) {
			PSugangsincheong pSugangsincheong = new PSugangsincheong(vPersonalInfo);
			pSugangsincheong.setVisible(true);
			JOptionPane.showMessageDialog(pSugangsincheong, "자동로그인 되었습니다.");
		} else {
			PMain pMain = new PMain();
			pMain.setVisible(true);
		}
	}

}
