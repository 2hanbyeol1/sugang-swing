package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import valueObject.VPersonalInfo;

public class MRemember {

	public void writeO(VPersonalInfo vPersonalInfo) {
		try {
			File file = new File("users/[Don't Delete] üũ");
			FileWriter fileWriter;
			fileWriter = new FileWriter(file);
			fileWriter.write("O");
			fileWriter.write(" ");
			fileWriter.write(vPersonalInfo.getName());
			fileWriter.write(" ");
			fileWriter.write(vPersonalInfo.getStudentId());
			fileWriter.write(" ");
			fileWriter.write(vPersonalInfo.getId());
			fileWriter.write(" ");
			fileWriter.write(vPersonalInfo.getPassword());
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public VPersonalInfo read() {

		Scanner scanner = null;
		try {
			File file = new File("users/[Don't Delete] üũ");
			scanner = new Scanner(file);
			String check = scanner.next();
			if(check.equals("O")) { //üũ�ߴ� ��
				VPersonalInfo vPersonalInfo = new VPersonalInfo();
				vPersonalInfo.setName(scanner.next());
				vPersonalInfo.setStudentId(scanner.next());
				vPersonalInfo.setId(scanner.next());
				vPersonalInfo.setPassword(scanner.next());

				return vPersonalInfo;
			}
		} catch (Exception e) {
			//���� �α����� �� ���� ���
			System.out.println("Never Used");
		} finally{
			if(scanner != null) scanner.close();
		} return null; //üũ���ߴ� ��
	}

	public void writeX() {
		try {
			File file = new File("users/[Don't Delete] üũ");
			FileWriter fileWriter;
			fileWriter = new FileWriter(file);
			fileWriter.write("X");
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
