package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import model.MMiri;
import valueObject.VLecture;
import valueObject.VPersonalInfo;

public class SMiri {

	public void write(VPersonalInfo vPersonalInfo, VLecture vLecture) {
		MMiri mMiri = new MMiri();
		mMiri.write(vPersonalInfo, vLecture);
	}
	
	public boolean validate(VPersonalInfo vPersonalInfo, int intMiriLectureId) {
		Scanner scanner = null;
		try {
			String miriLectureId = Integer.toString(intMiriLectureId);
			File file = new File("miri/" + vPersonalInfo.getId());
			scanner = new Scanner(file);
			while(scanner.hasNext()) {
				String writtenId = scanner.next();
				scanner.next();
				scanner.next();
				scanner.next();
				scanner.next();
				if(miriLectureId.equals(writtenId)) {
					return true;
				}
			}
		} catch (FileNotFoundException e) {
			//첫 미리담기
		} finally {
			if(scanner != null) scanner.close();
		} return false;
	}
	
	public Vector<VLecture> read(VPersonalInfo vPersonalInfo) {
		MMiri mMiri = new MMiri();
		return mMiri.read(vPersonalInfo);
	}
	
	public void delete(VPersonalInfo vPersonalInfo, VLecture vLecture) {
		MMiri mMiri = new MMiri();
		mMiri.delete(vPersonalInfo, vLecture);
	}
}
