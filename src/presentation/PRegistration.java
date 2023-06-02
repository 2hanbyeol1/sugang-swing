package presentation;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import service.SRegistration;
import valueObject.VPersonalInfo;

public class PRegistration extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel frameName;
	private JLabel nameLabel;
	private JLabel studentIdLabel;
	private JLabel idLabel;
	private JLabel passwordLabel;
	private JLabel passwordCheckLabel;
	private JTextField nameField;
	private JTextField studentIdField;
	private JTextField idField;
	private JPasswordField passwordField;
	private JPasswordField passwordCheckField;
	private JButton signUpButton;
	private JButton cancelButton;
	
	
	public PRegistration () {
		PMain pMain = new PMain();
		Image iconImage = pMain.getIconImage();
		this.setIconImage(iconImage);
		
		this.setTitle("���� �����");
		this.setSize(700,550);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		this.frameName = new JLabel("Sign Up");
		this.frameName.setBounds(50, 30, 80, 40);
		this.add(this.frameName);
		
		this.nameLabel = new JLabel("Name");
		this.nameLabel.setBounds(100, 100, 80, 40);
		this.add(this.nameLabel);
		
		this.studentIdLabel = new JLabel("Student ID");
		this.studentIdLabel.setBounds(100, 160, 80, 40);
		this.add(studentIdLabel);
		
		this.idLabel = new JLabel("ID");
		this.idLabel.setBounds(100, 220, 80, 40);
		this.add(this.idLabel);
		
		this.passwordLabel = new JLabel("Password");
		this.passwordLabel.setBounds(100, 280, 80, 40);
		this.add(this.passwordLabel);
		
		this.passwordCheckLabel = new JLabel("Check");
		this.passwordCheckLabel.setBounds(100, 340, 80, 40);
		this.add(this.passwordCheckLabel);
		
		this.nameField = new JTextField();
		this.nameField.setBounds(180, 100, 400, 40);
		this.add(this.nameField);
		
		this.studentIdField = new JTextField();
		this.studentIdField.setBounds(180, 160, 400, 40);
		this.add(this.studentIdField);
		
		this.idField = new JTextField();
		this.idField.setBounds(180, 220, 400, 40);
		this.add(this.idField);
		
		this.passwordField = new JPasswordField();
		this.passwordField.setBounds(180, 280, 400, 40);
		this.add(this.passwordField);
		
		this.passwordCheckField = new JPasswordField();
		this.passwordCheckField.setBounds(180, 340, 400, 40);
		this.add(this.passwordCheckField);
		
		this.signUpButton = new JButton("OK");
		this.signUpButton.setBounds(180, 400, 190, 40);
		this.add(this.signUpButton);
		signUpButton();
		
		this.cancelButton = new JButton("Cancel");
		this.cancelButton.setBounds(390, 400, 190, 40);
		this.add(this.cancelButton);
		cancelButton();
		
	}
	
	public void signUpButton() {
		this.signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String registerName = nameField.getText();
				String registerStudentId = studentIdField.getText();
				String registerId = idField.getText();
				String registerPassword = new String (passwordField.getPassword());
				String registerPasswordCheck = new String (passwordCheckField.getPassword());
				
				// �ʼ�Ȯ��
				if(registerName.equals("")) {
					JOptionPane.showMessageDialog(PRegistration.this, "�̸��� �Է����ּ���.");
				} else if(registerStudentId.equals("")){
					JOptionPane.showMessageDialog(PRegistration.this, "�й��� �Է����ּ���.");
				} else if(registerId.equals("")) {
					JOptionPane.showMessageDialog(PRegistration.this, "���̵� �Է����ּ���.");
				} else if(registerPassword.equals("")) {
					JOptionPane.showMessageDialog(PRegistration.this, "��й�ȣ�� �Է����ּ���.");
				} else if(!(registerPassword.equals(registerPasswordCheck))){
					JOptionPane.showMessageDialog(PRegistration.this, "��й�ȣ Ȯ���� ��ġ���� �ʽ��ϴ�.");
				} else {
					VPersonalInfo vPersonalInfo = new VPersonalInfo();
					vPersonalInfo.setName(registerName);
					vPersonalInfo.setStudentId(registerStudentId);
					vPersonalInfo.setId(registerId);
					vPersonalInfo.setPassword(registerPassword);
					// �ߺ�Ȯ��
					SRegistration sRegistration = new SRegistration();
					String Overlap = sRegistration.validate(vPersonalInfo);
					if(Overlap == null) { // �ߺ�Ȯ�� ����
						sRegistration.write(vPersonalInfo); // �������� ���� (���ϸ�: users/vPersonalInfo�� Id)
						JOptionPane.showMessageDialog(PRegistration.this, vPersonalInfo.getName() + "�� ȸ������ �Ϸ�Ǿ����ϴ�.");
						PMain pMain = new PMain();
						pMain.setVisible(true);
						PRegistration.this.setVisible(false);
					} else if(Overlap.equals("�̹� �����ϴ� ���̵��Դϴ�.")) { // ���̵� �ߺ�
						JOptionPane.showMessageDialog(PRegistration.this, Overlap);
					} else { // �й� �ߺ�
						JOptionPane.showMessageDialog(PRegistration.this, Overlap);
					}
				}
			}
		});
	}
	
	public void cancelButton() {
		this.cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PRegistration.this.setVisible(false);
			}
		});
	}
	
}
