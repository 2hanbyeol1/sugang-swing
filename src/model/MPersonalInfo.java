package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import valueObject.VPersonalInfo;

public class MPersonalInfo {

	public void write(VPersonalInfo vPersonalInfo) {
		try {
			File file = new File("users/" + vPersonalInfo.getId());
			FileWriter fileWriter = new FileWriter(file, true);	
			
			fileWriter.write(vPersonalInfo.getName());
			fileWriter.write(" ");
			fileWriter.write(vPersonalInfo.getStudentId());
			fileWriter.write(" ");
			fileWriter.write(vPersonalInfo.getId());
			fileWriter.write(" ");
			fileWriter.write(vPersonalInfo.getPassword());
			
			fileWriter.close();
			
			File file2 = new File("users/[Don't Delete] 아이디학번");
			FileWriter fileWriter2 = new FileWriter(file2, true);
			
			fileWriter2.write(vPersonalInfo.getId());
			fileWriter2.write(" ");
			fileWriter2.write(vPersonalInfo.getStudentId());
			fileWriter2.write("\n");
			
			fileWriter2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public VPersonalInfo read(String id) {
		VPersonalInfo vPersonalInfo = null;
		Scanner scanner = null;
		try {
			File file = new File("users/" + id); // 회원가입 정보 파일
			scanner = new Scanner(file);
				vPersonalInfo = new VPersonalInfo();
				vPersonalInfo.setName(scanner.next());
				vPersonalInfo.setStudentId(scanner.next());
				vPersonalInfo.setId(scanner.next());
				vPersonalInfo.setPassword(scanner.next());
				return vPersonalInfo; // 리턴
		} catch (FileNotFoundException e) {
			return null;
			//존재하지 않는 아이디
		} finally {
			if(scanner != null) scanner.close();
		}
	}

	public void modify(VPersonalInfo modifyInfo) {
		try {
			File file = new File("users/" + modifyInfo.getId());
			FileWriter fileWriter = new FileWriter(file);	
			
			fileWriter.write(modifyInfo.getName());
			fileWriter.write(" ");
			fileWriter.write(modifyInfo.getStudentId());
			fileWriter.write(" ");
			fileWriter.write(modifyInfo.getId());
			fileWriter.write(" ");
			fileWriter.write(modifyInfo.getPassword());
			fileWriter.write("\n");
			fileWriter.close();
	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
