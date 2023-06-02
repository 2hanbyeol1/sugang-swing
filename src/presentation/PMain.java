package presentation;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import service.SLogin;
import service.SRemember;
import valueObject.VLogin;
import valueObject.VPersonalInfo;

public class PMain extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel imageLabel;
	private JLabel idLabel;
	private JLabel passwordLabel;
	private JTextField idField;
	private JPasswordField passwordField;
	private JCheckBox rememberCheck;
	private JButton loginButton;
	private JButton signUpButton;
	private JButton findInfoButton;
	
	public PMain() {
		this.setTitle("�������б� ������û");  //����
		Image iconImage = getIconImage(); //������
		this.setIconImage(iconImage); 
		this.setSize(700, 700); //������ ũ��
		this.setLocationRelativeTo(null); //â�� ��� �ߵ��� ��
		this.setLayout(null); //����ڰ� ���ϴ� ��ġ�� �ְ��� �� ��
		this.setDefaultCloseOperation(PMain.EXIT_ON_CLOSE); //â�� ���� ���α׷� ����
		
		imageLabel();
		this.imageLabel.setBounds(200,60,300,300);
		this.add(this.imageLabel);
		
		this.idLabel = new JLabel("ID");
		this.idLabel.setBounds(100, 390, 80, 40);
		this.add(this.idLabel);
		
		this.passwordLabel = new JLabel("Password");
		this.passwordLabel.setBounds(100, 440, 80, 40);
		this.add(this.passwordLabel);
		
		this.idField = new JTextField();
		this.idField.setBounds(190, 390, 280, 40);
		this.add(this.idField);
		
		this.passwordField = new JPasswordField();
		this.passwordField.setBounds(190, 440, 280, 40);
		this.add(this.passwordField);
		
		this.rememberCheck = new JCheckBox("�ڵ��α���");
		this.rememberCheck.setBounds(100, 490, 300, 20);
		this.add(this.rememberCheck);
		
		this.loginButton = new JButton("Login");
		this.loginButton.setBounds(490, 390, 100, 90);
		this.add(this.loginButton);
		loginButton(this, rememberCheck);
		
		this.signUpButton = new JButton("���� �����");
		this.signUpButton.setBounds(100, 520, 240, 40);
		this.add(this.signUpButton);
		signUpButton();
		
		this.findInfoButton = new JButton("ID�� PW�� �����̳���?");
		this.findInfoButton.setBounds(350,520,240,40);
		this.add(this.findInfoButton);
		findInfoButton();
	}
	
	public Image getIconImage() {
		try {
			File iconFile = new File("������ΰ�.gif");
			Image iconImage;
			iconImage = ImageIO.read(iconFile);
			return iconImage;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void imageLabel() {
		try {
			File mjuImageFile = new File("������û����.png");
			Image mainImage = ImageIO.read(mjuImageFile);
			this.imageLabel = new JLabel(new ImageIcon(mainImage));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// �α��� ��ư
	public void loginButton(PMain PMain, JCheckBox rememberCheck) {
		this.loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VLogin vLogin = new VLogin();
				vLogin.setId(idField.getText());
				vLogin.setPassword(new String(passwordField.getPassword()));
				SLogin sLogin = new SLogin();
				VPersonalInfo vPersonalInfo = sLogin.validate(vLogin); // �α��� ���� = ȸ�����Ե� �������� Ȯ��
				// �α��� ����
				if(vPersonalInfo != null) {
					PMain.setVisible(false);
					PSugangsincheong pSugangsincheong = new PSugangsincheong(vPersonalInfo);
					pSugangsincheong.setVisible(true);
					if(rememberCheck.isSelected()) { // �ڵ� �α��� üũ Ȯ��
						SRemember sRemember = new SRemember();
						sRemember.writeO(vPersonalInfo);
					}
				// �α��� ����
				} else {
					JOptionPane.showMessageDialog(PMain, "id�� ��й�ȣ�� �߸��Ǿ����ϴ�.");
				}
			}
		});
	}
	// ��������� ��ư
	public void signUpButton() {
		this.signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �������� �Է� â ǥ��
				PRegistration pRegistration = new PRegistration();
				pRegistration.setVisible(true);
			}
		});
	}
	
	public void findInfoButton() {
		this.findInfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PFindInfo pFindInfo = new PFindInfo();
				pFindInfo.setVisible(true);
			}
		});
	}
	
}
