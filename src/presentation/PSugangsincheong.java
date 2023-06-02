package presentation;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import service.SLecture;
import service.SMiri;
import service.SRemember;
import service.SSincheong;
import valueObject.VLecture;
import service.SData;
import valueObject.VData;
import presentation.PMain;
import valueObject.VPersonalInfo;

public class PSugangsincheong extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel hi;
	private JList<String> campusList;
	private JList<String> collegeList;
	private JList<String> departmentList;
	private JTable lectureTable;
	private JButton listButton;
	private JButton miriButton;
	private JButton sincheongButton;
	private JButton personalInfoButton;
	private JButton logoutButton;
	private VPersonalInfo vPersonalInfo;
			
	public PSugangsincheong(VPersonalInfo vPersonalInfo) {
		
		this.vPersonalInfo = vPersonalInfo;
		PMain pMain = new PMain();
		Image iconImage = pMain.getIconImage();
		this.setIconImage(iconImage);
		this.setTitle("수강신청");
		this.setSize(700,700);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setDefaultCloseOperation(PSugangsincheong.EXIT_ON_CLOSE);
		
		this.hi = new JLabel(vPersonalInfo.getName() + "님 안녕하세요.");
		this.hi.setBounds(30, 20, 530, 20);
		this.add(this.hi);
		
		this.campusList = new JList<>();
		this.campusList.setBounds(30, 70, 130, 200);
		this.add(this.campusList);
		showCampus();
		setCampusSelectionListener();

		this.collegeList = new JList<>();
		this.collegeList.setBounds(190, 70, 130, 200);
		this.add(this.collegeList);
		setCollegeSelectionListener();
		
		this.departmentList = new JList<>();
		this.departmentList.setBounds(350, 70, 200, 200);
		this.add(this.departmentList);
		setDepartmentSelectionListener();
		
		String column[] = {"강좌명","담당교수","학점","시간"};
		this.lectureTable = new JTable(new String[0][0], column);
		JScrollPane jScrollPane = new JScrollPane(this.lectureTable);
		jScrollPane.setBounds(30, 290, 640, 290);
		this.add(jScrollPane);
	
		this.listButton = new JButton("미리담기/신청 목록");
		this.listButton.setBounds(300, 600, 150, 30);
		this.add(this.listButton);
		listButton();
		
		this.miriButton = new JButton("미리 담기");
		this.miriButton.setBounds(460, 600, 100, 30);
		this.add(this.miriButton);
		miriButton();
		
		this.sincheongButton = new JButton("신청");
		this.sincheongButton.setBounds(570, 600, 100, 30);
		this.add(this.sincheongButton);
		sincheongButton();
		
		this.personalInfoButton = new JButton("개인정보");
		this.personalInfoButton.setBounds(460, 20, 100, 20);
		this.add(personalInfoButton);
		personalInfoButton(vPersonalInfo);
		
		this.logoutButton = new JButton("Logout");
		this.logoutButton.setBounds(570, 20, 100, 20);
		this.add(this.logoutButton);
		logoutButton();
	}

	Vector<VData> vCampuses;
	public void showCampus() {
		SData sCampus = new SData();
		vCampuses = sCampus.getData("root");
		DefaultListModel<String> defaultCampusList = new DefaultListModel<>();
		for(int i = 0; i < vCampuses.size(); i++) {
			VData vCampus = vCampuses.get(i);
			defaultCampusList.addElement(vCampus.getName());
		}
		campusList.setModel(defaultCampusList);
	}
	
	public void setCampusSelectionListener() {
		campusList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting())
			          return;
				campusList.getSelectionModel();
				int index = campusList.getSelectedIndex();
				if(index >= 0 && index < vCampuses.size()) {
					VData vCampus = vCampuses.get(index);
					showCollege(vCampus.getFilename());
				}
			}
		});
	}
	
	Vector<VData> vColleges;
	public void showCollege(String collegeFile) {
		SData sCollege = new SData();
		vColleges = sCollege.getData(collegeFile);
		DefaultListModel<String> defaultCollegeList = new DefaultListModel<>();
		for(int i = 0; i < vColleges.size(); i++) {
			VData vCollege = vColleges.get(i);
			defaultCollegeList.addElement(vCollege.getName());	
		}	
		collegeList.setModel(defaultCollegeList);
	}
	
	public void setCollegeSelectionListener() {
		collegeList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting())
			          return;
				collegeList.getSelectionModel();
				int index = collegeList.getSelectedIndex();
				if(index >= 0 && index < vColleges.size()) {
					VData vCollege = vColleges.get(index);
					showDepartment(vCollege.getFilename());
				}
			}
		});
	}
	
	Vector<VData> vDepartments;
	public void showDepartment(String departmentFile) {
		SData sDepartment = new SData();
		vDepartments = sDepartment.getData(departmentFile);
		DefaultListModel<String> defaultDepartmentList = new DefaultListModel<>();
		for(int i = 0; i < vDepartments.size(); i++) {
			VData vDepartment = vDepartments.get(i);
			defaultDepartmentList.addElement(vDepartment.getName());
		}	
		departmentList.setModel(defaultDepartmentList);
	}
	
	public void setDepartmentSelectionListener() {
		departmentList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting())
			          return;
				departmentList.getSelectionModel();
				int index = departmentList.getSelectedIndex();
				if(index >= 0 && index < vDepartments.size()) {
					VData vDepartment = vDepartments.get(index);
					showLecture(vDepartment.getFilename());
				}
			}
		});
	}
	
	Vector<VLecture> vLectures;
	public void showLecture(String lectureFile) {
		SLecture sLecture = new SLecture();
		vLectures = sLecture.getLecture(lectureFile);
		String data[][] = new String[vLectures.size()][4];
			
			for(int i = 0; i < vLectures.size(); i++) {
				VLecture vLecture = vLectures.get(i);
				data[i][0] = vLecture.getName();
				data[i][1] = vLecture.getProfessor();
				data[i][2] = Integer.toString(vLecture.getScore());
				data[i][3] = vLecture.getTime();			
			}
		String column[] = {"강좌명","담당교수","학점","시간"};
		
		DefaultTableModel model = new DefaultTableModel(data, column) {
			public boolean isCellEditable(int i, int c){ return false; }
		};
		lectureTable.setModel(model);
	}
	
	public void miriButton () {
		miriButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SMiri sMiri = new SMiri();
					// 미리담기 중복 확인
					boolean overLap = sMiri.validate(vPersonalInfo, vLectures.get(lectureTable.getSelectedRow()).getId());
					if(overLap == true) { // 미리담기 중복
						JOptionPane.showMessageDialog(PSugangsincheong.this, "이미 미리담기한 강좌입니다.");
					}
					else { // 미리담기 중복 안됨
						sMiri.write(vPersonalInfo, vLectures.get(lectureTable.getSelectedRow())); // 저장
						JOptionPane.showMessageDialog(PSugangsincheong.this, vLectures.get(lectureTable.getSelectedRow()).getName() + " 미리담기 완료");
					}
				} catch(Exception e2) { // 강좌 선택 안됨
					JOptionPane.showMessageDialog(PSugangsincheong.this, "강좌를 선택한 후 미리담기 버튼을 눌러주세요.");
				}
			}
		});
	}
	
	public void sincheongButton () {
		sincheongButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SSincheong sSincheong = new SSincheong();
					boolean overLap = sSincheong.validate(vPersonalInfo, vLectures.get(lectureTable.getSelectedRow()).getId());
					if(overLap == true) {
						JOptionPane.showMessageDialog(PSugangsincheong.this, "이미 신청한 강좌입니다.");
					}
					else {
						sSincheong.write(vPersonalInfo, vLectures.get(lectureTable.getSelectedRow()));
						JOptionPane.showMessageDialog(PSugangsincheong.this, vLectures.get(lectureTable.getSelectedRow()).getName() + " 신청 완료");
					}
				} catch(Exception e2) {
					JOptionPane.showMessageDialog(PSugangsincheong.this,"강좌를 선택한 후 신청 버튼을 눌러주세요.");
				}
			}
		});
	}
	
	public void personalInfoButton (VPersonalInfo vPersonalInfo) {
		personalInfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PPersonalInfo pPersonalInfo = new PPersonalInfo(vPersonalInfo);
				pPersonalInfo.setVisible(true);
			}
		});
	}
	
	public void logoutButton () {
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PMain pMain = new PMain();
				pMain.setVisible(true);
				PSugangsincheong.this.setVisible(false);
				SRemember sRemember = new SRemember();
				sRemember.writeX();
			}
		});
	}
	
	public void listButton () {
		listButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PList pList = new PList(vPersonalInfo);
				pList.setVisible(true);
			}
		});
	}
	
}
