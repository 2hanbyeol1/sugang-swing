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
		this.setTitle("명지대학교 수강신청");  //제목
		Image iconImage = getIconImage(); //아이콘
		this.setIconImage(iconImage); 
		this.setSize(700, 700); //프레임 크기
		this.setLocationRelativeTo(null); //창이 가운데 뜨도록 함
		this.setLayout(null); //사용자가 원하는 위치에 넣고자 할 때
		this.setDefaultCloseOperation(PMain.EXIT_ON_CLOSE); //창을 끄면 프로그램 종료
		
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
		
		this.rememberCheck = new JCheckBox("자동로그인");
		this.rememberCheck.setBounds(100, 490, 300, 20);
		this.add(this.rememberCheck);
		
		this.loginButton = new JButton("Login");
		this.loginButton.setBounds(490, 390, 100, 90);
		this.add(this.loginButton);
		loginButton(this, rememberCheck);
		
		this.signUpButton = new JButton("계정 만들기");
		this.signUpButton.setBounds(100, 520, 240, 40);
		this.add(this.signUpButton);
		signUpButton();
		
		this.findInfoButton = new JButton("ID나 PW를 잊으셨나요?");
		this.findInfoButton.setBounds(350,520,240,40);
		this.add(this.findInfoButton);
		findInfoButton();
	}
	
	public Image getIconImage() {
		try {
			File iconFile = new File("명지대로고.gif");
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
			File mjuImageFile = new File("수강신청메인.png");
			Image mainImage = ImageIO.read(mjuImageFile);
			this.imageLabel = new JLabel(new ImageIcon(mainImage));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// 로그인 버튼
	public void loginButton(PMain PMain, JCheckBox rememberCheck) {
		this.loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VLogin vLogin = new VLogin();
				vLogin.setId(idField.getText());
				vLogin.setPassword(new String(passwordField.getPassword()));
				SLogin sLogin = new SLogin();
				VPersonalInfo vPersonalInfo = sLogin.validate(vLogin); // 로그인 정보 = 회원가입된 정보인지 확인
				// 로그인 성공
				if(vPersonalInfo != null) {
					PMain.setVisible(false);
					PSugangsincheong pSugangsincheong = new PSugangsincheong(vPersonalInfo);
					pSugangsincheong.setVisible(true);
					if(rememberCheck.isSelected()) { // 자동 로그인 체크 확인
						SRemember sRemember = new SRemember();
						sRemember.writeO(vPersonalInfo);
					}
				// 로그인 실패
				} else {
					JOptionPane.showMessageDialog(PMain, "id나 비밀번호가 잘못되었습니다.");
				}
			}
		});
	}
	// 계정만들기 버튼
	public void signUpButton() {
		this.signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 개인정보 입력 창 표출
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
