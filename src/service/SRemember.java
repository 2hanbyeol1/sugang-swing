package service;

import model.MRemember;
import valueObject.VPersonalInfo;

public class SRemember {

	public void writeO(VPersonalInfo vPersonalInfo) {
		MRemember mRemember = new MRemember();
		mRemember.writeO(vPersonalInfo);
	}
	
	public VPersonalInfo read() {
		MRemember mRemember = new MRemember();
		return mRemember.read();
	}

	public void writeX() {
		MRemember mRemember = new MRemember();
		mRemember.writeX();
	}

}
