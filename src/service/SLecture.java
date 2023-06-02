package service;

import java.util.Vector;

import model.MLecture;
import valueObject.VLecture;

public class SLecture {
	public Vector<VLecture> getLecture(String fileName) {
		MLecture mLecture = new MLecture();
		Vector<VLecture> vLectures = mLecture.getData(fileName);
		return vLectures;
	}
}
