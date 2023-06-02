package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import valueObject.VLecture;
import valueObject.VPersonalInfo;

public class MSincheong {

	public void write(VPersonalInfo vPersonalInfo, VLecture vLecture) {
		try {
			File file = new File("sincheong/" + vPersonalInfo.getId());
			FileWriter fileWriter = new FileWriter(file, true);
			fileWriter.write("" + vLecture.getId());
			fileWriter.write(" ");
			fileWriter.write("" + vLecture.getName());
			fileWriter.write(" ");
			fileWriter.write("" + vLecture.getProfessor());
			fileWriter.write(" ");
			fileWriter.write("" + vLecture.getScore());
			fileWriter.write(" ");
			fileWriter.write("" + vLecture.getTime());
			fileWriter.write("\n");
			
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Vector<VLecture> read(VPersonalInfo vPersonalInfo) {
		Vector<VLecture>vLectures = new Vector<>();
		Scanner scanner = null;
		try {
			File file = new File("sincheong/" + vPersonalInfo.getId());
			scanner = new Scanner(file);
			while(scanner.hasNext()) {
				VLecture vLecture = new VLecture();
				vLecture.setId(scanner.nextInt());
				vLecture.setName(scanner.next());
				vLecture.setProfessor(scanner.next());
				vLecture.setScore(scanner.nextInt());
				vLecture.setTime(scanner.next());
				vLectures.add(vLecture);
			}
			return vLectures;
		} catch (FileNotFoundException e) {
			//√π Ω≈√ª
		} finally {
			if(scanner != null) scanner.close();
		} return vLectures;
	}

	public void delete(VPersonalInfo vPersonalInfo, VLecture vLecture) {
		Vector<VLecture> vLectures = read(vPersonalInfo);
		
		File file = new File("sincheong/" + vPersonalInfo.getId());
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(file);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int i = 0; i<vLectures.size(); i++) {
			if(vLectures.get(i).getId() != vLecture.getId()) {
				write(vPersonalInfo, vLectures.get(i));
			}
		}
	}

}
