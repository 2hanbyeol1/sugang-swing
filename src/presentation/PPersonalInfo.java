package presentation;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import service.SRegistration;
import valueObject.VPersonalInfo;

public class PPersonalInfo extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel name;
	private JLabel studentId;
	private JLabel id;
	private JLabel password;
	private JLabel passwordCheck;
	private JLabel nameLabel;
	private JLabel studentIdLabel;
	private JLabel idLabel;
	private JPasswordField passwordField;
	private JPasswordField passwordCheckField;
	private JButton modifyInfoButton;
	
	public PPersonalInfo(VPersonalInfo vPersonalInfo) {
		PMain pMain = new PMain();
		Image iconImage = pMain.getIconImage();
		this.setIconImage(iconImage);
		this.setTitle("��������");
		this.setSize(410, 250);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		this.name = new JLabel("�̸�");
		this.name.setBounds(35, 20, 100, 15);
		this.add(this.name);
		
		this.nameLabel = new JLabel(vPersonalInfo.getName());
		this.nameLabel.setBounds(120, 20, 240, 15);
		this.add(this.nameLabel);
		
		this.studentId = new JLabel("�й�");
		this.studentId.setBounds(35, 50, 100, 15);
		this.add(this.studentId);
		
		this.studentIdLabel = new JLabel(vPersonalInfo.getStudentId());
		this.studentIdLabel.setBounds(120, 50, 240, 15);
		this.add(this.studentIdLabel);
		
		this.id = new JLabel("���̵�");
		this.id.setBounds(35, 80, 100, 15);
		this.add(this.id);
		
		this.idLabel = new JLabel(vPersonalInfo.getId());
		this.idLabel.setBounds(120, 80, 240, 15);
		this.add(this.idLabel);
		
		this.password = new JLabel("��й�ȣ");
		this.password.setBounds(35, 110, 100, 15);
		this.add(this.password);
		
		this.passwordField = new JPasswordField();
		this.passwordField.setBounds(120, 110, 240, 20);
		this.add(this.passwordField);
		
		this.passwordCheck = new JLabel("��й�ȣ Ȯ��");
		this.passwordCheck.setBounds(35, 140, 100, 20);
		this.add(this.passwordCheck);
		
		this.passwordCheckField = new JPasswordField();
		this.passwordCheckField.setBounds(120, 140, 240, 20);
		this.add(this.passwordCheckField);
		
		this.modifyInfoButton = new JButton("��й�ȣ ����");
		this.modifyInfoButton.setBounds(35, 180, 130, 20);
		this.add(this.modifyInfoButton);
		
		modifyInfoButton(vPersonalInfo);
	}
	
	public void modifyInfoButton(VPersonalInfo vPersonalInfo) {
		this.modifyInfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VPersonalInfo modifyInfo = new VPersonalInfo();
				modifyInfo.setName(vPersonalInfo.getName());
				modifyInfo.setStudentId(vPersonalInfo.getStudentId());
				modifyInfo.setId(vPersonalInfo.getId());
				modifyInfo.setPassword(new String(passwordField.getPassword()));
				if(modifyInfo.getPassword().equals(new String(passwordCheckField.getPassword()))) {
					SRegistration sRegistration = new SRegistration();
					//����
					sRegistration.modify(modifyInfo);
					JOptionPane.showMessageDialog(PPersonalInfo.this, "��й�ȣ ���� �Ϸ�");
				} else if(modifyInfo.getPassword().equals("")){
					JOptionPane.showMessageDialog(PPersonalInfo.this, "��й�ȣ�� �Է����ּ���.");
				}
				else {
					JOptionPane.showMessageDialog(PPersonalInfo.this, "��й�ȣ Ȯ���� ��ġ���� �ʽ��ϴ�.");
				}
			}
		});
	}
}
