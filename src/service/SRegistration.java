package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.MPersonalInfo;
import valueObject.VPersonalInfo;

public class SRegistration {
	public void write(VPersonalInfo vPersonalInfo) {
		MPersonalInfo mPersonalInfo = new MPersonalInfo();
		mPersonalInfo.write(vPersonalInfo); // 개인정보 저장
	}
	
	public String validate(VPersonalInfo vRegisterInfo) {
		Scanner scanner = null;
		File file = new File("users/" + vRegisterInfo.getId());
		if(file.exists() == true) {
			return "이미 존재하는 아이디입니다.";
		}else {
			try {
				File file2 = new File("users/[Don't Delete] 아이디학번");
				scanner = new Scanner(file2);
				VPersonalInfo vPersonalInfo = new VPersonalInfo();
				while(scanner.hasNext()) {
					scanner.next();
					vPersonalInfo.setStudentId(scanner.next());
					if(vRegisterInfo.getStudentId().equals(vPersonalInfo.getStudentId())) {
						return "이미 존재하는 학번입니다.";
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				if(scanner != null) scanner.close();
			} 
		}
		return null;
	}

	public void modify(VPersonalInfo modifyInfo) {
		MPersonalInfo mPersonalInfo = new MPersonalInfo();
		mPersonalInfo.modify(modifyInfo);
	}
}
