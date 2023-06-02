package presentation;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import service.SMiri;
import service.SSincheong;
import valueObject.VLecture;
import valueObject.VPersonalInfo;

public class PList extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel miri;
	private JLabel sincheong;
	private JTable miriTable;
	private JTable sincheongTable;
	private JButton sincheongButton;
	private JButton deleteMiriButton;
	private JButton deleteSincheongButton;
	private VPersonalInfo vPersonalInfo;
	private Vector<VLecture> vMiriLectures;
	private Vector<VLecture> vSincheongLectures;

	public PList(VPersonalInfo vPersonalInfo) {
		this.vPersonalInfo = vPersonalInfo;
		this.setTitle("미리담기/신청 목록");
		PMain pMain = new PMain();
		Image iconImage = pMain.getIconImage();
		this.setIconImage(iconImage);
		this.setSize(700, 700);
		this.setLocationRelativeTo(null);
		this.setLayout(null);

		this.miri = new JLabel(vPersonalInfo.getName() + "님의 미리담기 목록");
		this.miri.setBounds(30, 10, 650, 30);
		this.add(this.miri);

		this.sincheong = new JLabel(vPersonalInfo.getName() + "님의 신청 목록");
		this.sincheong.setBounds(30, 330, 650, 30);
		this.add(this.sincheong);

		this.sincheongButton = new JButton("신청");
		this.sincheongButton.setBounds(500, 10, 80, 20);
		this.add(this.sincheongButton);
		sincheongButton();

		this.deleteMiriButton = new JButton("삭제");
		this.deleteMiriButton.setBounds(590, 10, 80, 20);
		this.add(this.deleteMiriButton);
		deleteMiriButton();

		this.deleteSincheongButton = new JButton("삭제");
		this.deleteSincheongButton.setBounds(590, 330, 80, 20);
		this.add(this.deleteSincheongButton);
		deleteSincheongButton();

		showMiri();
		showSincheong();
	}

	public void showMiri() {
		SMiri sMiri = new SMiri();
		vMiriLectures = sMiri.read(vPersonalInfo);
		String column[] = { "강좌명", "담당교수", "학점", "시간" };
		String data[][] = new String[vMiriLectures.size()][4];
		if (vMiriLectures != null) {
			for (int i = 0; i < vMiriLectures.size(); i++) {
				VLecture vLecture = vMiriLectures.get(i);
				data[i][0] = vLecture.getName();
				data[i][1] = vLecture.getProfessor();
				data[i][2] = Integer.toString(vLecture.getScore());
				data[i][3] = vLecture.getTime();
			}
		}

		DefaultTableModel model = new DefaultTableModel(data, column) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};

		this.miriTable = new JTable(model);

		JScrollPane scrollPane = new JScrollPane(this.miriTable);
		scrollPane.setBounds(30, 40, 650, 280);
		this.add(scrollPane);
	}

	public void showSincheong() {
		SSincheong sSincheong = new SSincheong();
		vSincheongLectures = sSincheong.read(vPersonalInfo);
		String column[] = { "강좌명", "담당교수", "학점", "시간" };
		String data[][] = new String[vSincheongLectures.size()][4];
		if (vSincheongLectures != null) {
			for (int i = 0; i < vSincheongLectures.size(); i++) {
				VLecture vLecture = vSincheongLectures.get(i);
				data[i][0] = vLecture.getName();
				data[i][1] = vLecture.getProfessor();
				data[i][2] = Integer.toString(vLecture.getScore());
				data[i][3] = vLecture.getTime();
			}
		}
		DefaultTableModel model = new DefaultTableModel(data, column) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		this.sincheongTable = new JTable(model);

		JScrollPane scrollPane = new JScrollPane(this.sincheongTable);
		scrollPane.setBounds(30, 360, 650, 280);
		this.add(scrollPane);
	}

	public void sincheongButton() {
		this.sincheongButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (miriTable.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(PList.this, "강좌를 선택한 후 신청 버튼을 눌러주세요.");
					return;
				}
				SSincheong sSincheong = new SSincheong();
				boolean overLap = sSincheong.validate(vPersonalInfo,
						vMiriLectures.get(miriTable.getSelectedRow()).getId());
				if (overLap == true) {
					JOptionPane.showMessageDialog(PList.this, "이미 신청한 강좌입니다.");
				} else {
					VLecture vLecture = vMiriLectures.get(miriTable.getSelectedRow());
					sSincheong.write(vPersonalInfo, vLecture);
					JOptionPane.showMessageDialog(PList.this, vLecture.getName() + " 신청 완료");
					vSincheongLectures.add(vLecture);
					DefaultTableModel model = (DefaultTableModel) sincheongTable.getModel();
					model.insertRow(model.getRowCount(), new Object[] { vLecture.getName(), vLecture.getProfessor(),
							vLecture.getScore(), vLecture.getTime() });
					sincheongTable.updateUI();
				}
			}
		});
	}

	public void deleteMiriButton() {
		this.deleteMiriButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (miriTable.getSelectedRow() < 0)
					return;
				SMiri sMiri = new SMiri();
				// 삭제
				sMiri.delete(vPersonalInfo, vMiriLectures.get(miriTable.getSelectedRow()));
				vMiriLectures.remove(miriTable.getSelectedRow());
				DefaultTableModel model = (DefaultTableModel) miriTable.getModel();
				model.removeRow(miriTable.getSelectedRow());
				miriTable.updateUI();
			}
		});
	}

	public void deleteSincheongButton() {
		this.deleteSincheongButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (sincheongTable.getSelectedRow() < 0)
					return;
				SSincheong sSincheong = new SSincheong();
				//삭제
				sSincheong.delete(vPersonalInfo, vSincheongLectures.get(sincheongTable.getSelectedRow()));
				vSincheongLectures.remove(sincheongTable.getSelectedRow());
				DefaultTableModel model = (DefaultTableModel) sincheongTable.getModel();
				model.removeRow(sincheongTable.getSelectedRow());
				sincheongTable.updateUI();
			}
		});
	}

}
