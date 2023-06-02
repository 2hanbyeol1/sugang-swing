package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import valueObject.VData;

public class MData {
	public Vector<VData> readFile(String fileName) {
		// 파일 읽어서 vCampuses(Vector)에 저장
		Vector<VData> vDatas = new Vector<VData>();
		File file = new File("data/"+ fileName);
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(file);
			while(scanner.hasNext()) {
				VData vData = new VData();
				vData.setId(scanner.nextInt());
				vData.setName(scanner.next());
				vData.setFilename(scanner.next());
				vDatas.add(vData);
			}
			return vDatas;
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(scanner != null) scanner.close();
		} return null;
	}
}
