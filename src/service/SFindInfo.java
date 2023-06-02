package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import valueObject.VPersonalInfo;

public class SFindInfo {
	public VPersonalInfo find(String findName, String findStudentId) {
		File file = new File("users/[Don't Delete] 아이디학번");
		VPersonalInfo vPersonalInfo = new VPersonalInfo();
		Scanner scanner = null;
		Scanner scanner2 = null;
		try {
			scanner = new Scanner(file);
			while(scanner.hasNext()) {
				String id = scanner.next();
				String StudentId = scanner.next();
				if (StudentId.equals(findStudentId)) {
					File file2 = new File("users/" + id);
					scanner2 = new Scanner(file2);
					vPersonalInfo.setName(scanner2.next());
					scanner2.next();
					vPersonalInfo.setId(scanner2.next());
					vPersonalInfo.setPassword(scanner2.next());
					if(findName.equals(vPersonalInfo.getName())) {
						return vPersonalInfo;
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(scanner != null) scanner.close();
			if(scanner2 != null) scanner2.close();
		}  return null;
	}
}
