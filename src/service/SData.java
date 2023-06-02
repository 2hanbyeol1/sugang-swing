package service;

import java.util.Vector;

import model.MData;
import valueObject.VData;

public class SData {
	public Vector<VData> getData(String fileName) {
		MData mData = new MData();
		Vector<VData> vDatas = mData.readFile(fileName);
		return vDatas;
	}
}
