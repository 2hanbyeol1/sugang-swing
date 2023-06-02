package presentation;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import service.SFindInfo;
import valueObject.VPersonalInfo;

public class PFindInfo extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel name;
	private JLabel studentId;
	private JTextField nameField;
	private JTextField studentIdField;
	private JButton findButton;

	public PFindInfo() {
		PMain pMain = new PMain();
		Image iconImage = pMain.getIconImage();
		this.setIconImage(iconImage);
		
		this.setTitle("계정 찾기");
		this.setSize(260,170);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		this.name = new JLabel("이름");
		this.name.setBounds(20, 20, 40, 20);
		this.add(this.name);
		
		this.studentId = new JLabel("학번");
		this.studentId.setBounds(20, 45, 40, 20);
		this.add(this.studentId);
		
		this.nameField = new JTextField();
		this.nameField.setBounds(70, 20, 150, 20);
		this.add(this.nameField);
		
		this.studentIdField = new JTextField();
		this.studentIdField.setBounds(70, 45, 150, 20);
		this.add(studentIdField);
		
		this.findButton = new JButton("찾기");
		this.findButton.setBounds(95, 80, 60, 25);
		this.add(this.findButton);
		findButton();
	}
	
	public void findButton() {
		this.findButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String findName = nameField.getText();
				String findStudentId = studentIdField.getText();
				SFindInfo sFindInfo = new SFindInfo();
				VPersonalInfo vPersonalInfo = sFindInfo.find(findName, findStudentId);
				if (vPersonalInfo == null) {
					JOptionPane.showMessageDialog(PFindInfo.this, "입력하신 정보는 없는 정보입니다.");
				}else {
					JOptionPane.showMessageDialog(PFindInfo.this, "아이디: " + vPersonalInfo.getId() + " 비밀번호: " + vPersonalInfo.getPassword());
				}
			}
		});
	}
}
