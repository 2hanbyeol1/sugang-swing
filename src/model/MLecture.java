package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import valueObject.VLecture;

public class MLecture {
	public Vector<VLecture> getData(String fileName) {
			
		Vector<VLecture> vLecturees = new Vector<VLecture>();
		File file = new File("data/"+ fileName);
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(file);
			while(scanner.hasNext()) {
				VLecture vLecture = new VLecture();
				vLecture.setId(scanner.nextInt());
				vLecture.setName(scanner.next());
				vLecture.setProfessor(scanner.next());
				vLecture.setScore(scanner.nextInt());
				vLecture.setTime(scanner.next());
				vLecturees.add(vLecture);
			}
			return vLecturees;
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(scanner != null) scanner.close();
		}
		return null;
	}
}
