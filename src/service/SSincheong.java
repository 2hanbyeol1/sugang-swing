package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import model.MSincheong;
import valueObject.VLecture;
import valueObject.VPersonalInfo;

public class SSincheong {
	
	public void write(VPersonalInfo vPersonalInfo, VLecture vLecture) {
		MSincheong mSincheong = new MSincheong();
		mSincheong.write(vPersonalInfo, vLecture);
	}

	public boolean validate(VPersonalInfo vPersonalInfo, int intSincheongLectureId) {
		Scanner scanner = null;
		try {
			String sincheongLectureId = Integer.toString(intSincheongLectureId);
			File file = new File("sincheong/" + vPersonalInfo.getId());
			scanner = new Scanner(file);
			while(scanner.hasNext()) {
				String writtenId = scanner.next();
				scanner.next();
				scanner.next();
				scanner.next();
				scanner.next();
				if(sincheongLectureId.equals(writtenId)) {
					return true;
				}
			}
		} catch (FileNotFoundException e) {
			//√π Ω≈√ª
		} finally {
			if(scanner != null) scanner.close();
		} 
		return false;
	}
	
	public Vector<VLecture> read(VPersonalInfo vPersonalInfo) {
		MSincheong mSincheong = new MSincheong();
		return mSincheong.read(vPersonalInfo);
	}

	public void delete(VPersonalInfo vPersonalInfo, VLecture vLecture) {
		MSincheong mSincheong = new MSincheong();
		mSincheong.delete(vPersonalInfo, vLecture);
	}
}
