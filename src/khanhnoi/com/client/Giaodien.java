package khanhnoi.com.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

//import khanhnoi.com.server.Danhba;
import tuoi.Tuoi;

public class Giaodien extends JFrame {

	DefaultTableModel dtm;
	JLabel lblTuVi;
	JTable tblTuoi;
	JButton btnTimKiem,btnEdit, btnThem, btnAll;
	JButton btnXoa, btnUpdate, btnThoat, btnTim;
	public JTextField txtTimKiem, txtID, txtTuoi, txtNoiDung, txtTT;
	public JPanel pnTB;
	public boolean edit = false;
	
	
	// Phan client-server
	ObjectInputStream ois;
	ObjectOutputStream oos;
	OutputStream os;
	InputStream is;

	// Socket socket;
	public Giaodien(String title) {
		this.setTitle(title);
		addControl();
		addEvent();
	}

	//tao ui - awt
	public void addControl() {
		//Container - vung chua cac thanh phan awt khac nhu: button, textfield, label
		//Cac lop duoc ke thua lop Container duoc biet den nhu cac container nhu Frame, Dialog, Panel.
		Container con = getContentPane();
		//phuong thuc getContentPane tra ve mot doi tuong Container ma khong phai la doi tuong JComponent
		//quan ly bo cuc cho JPanel la FlowLayout;.
		
		// Tao mot bang (va them thanh phan)
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BorderLayout());

		// Chia layout thanh hai phan
		
		//Phan1
		JPanel pnTB = new JPanel();
		pnTB.setBackground(Color.blue);
		
//		PhanTuViUser
		JPanel pnUser = new JPanel();
		pnUser.setBackground(Color.gray);
		pnUser.setPreferredSize(new Dimension(0, 100)); //kich thuoc
		JLabel lblTuVi = new JLabel("KetQua");
		lblTuVi.setForeground(Color.WHITE);
		
		JTextArea txtTuVi = new JTextArea(5,40);
		txtTuVi.setText("");
		
//		lblTuVi.setText("TestTuVi2");
		pnUser.add(lblTuVi);
		pnUser.add(txtTuVi);
		
		//Phan2
		JPanel pnBTN = new JPanel();
		pnBTN.setPreferredSize(new Dimension(0, 200)); //kich thuoc
		
		//them 2 phan vao bang
		// pnBTN.setBackground(Color.DARK_GRAY);
//		pnMain.add(pnTB, BorderLayout.CENTER);
		
//		pnMain.add(pnUser);
		pnMain.add(pnTB);
		
		pnTB.setVisible(edit);
		
		pnMain.add(pnUser, BorderLayout.NORTH);
		pnMain.add(pnBTN, BorderLayout.SOUTH);
		con.add(pnMain);
		
		// set Phan 1
		pnTB.setLayout(new BorderLayout());
		//Tao bang
		dtm = new DefaultTableModel();

		dtm.addColumn("ID");
		dtm.addColumn("Tuoi");
		dtm.addColumn("NoiDung");
		dtm.addColumn("TT");
		tblTuoi = new JTable(dtm);
		JScrollPane scroll = new JScrollPane(tblTuoi, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		pnTB.add(scroll, BorderLayout.CENTER);
		
		
		//user
			JPanel pnTimKiem = new JPanel();
			pnTimKiem.setLayout(new FlowLayout());
//				pnTimKiem.setBackground(Color.BLACK);
			txtTimKiem = new JTextField(20);
			JLabel lblTimKiem = new JLabel("Nhap Nam Sinh");
			btnTimKiem = new JButton("Xem Tu Vi");
			btnEdit = new JButton("Edit");


			pnTimKiem.add(lblTimKiem);
			pnTimKiem.add(txtTimKiem);
			pnTimKiem.add(btnTimKiem);
			pnTimKiem.add(btnEdit);
			
//			pnUser.add(pnTimKiem);
			
		
		
		// Phan 2
		pnBTN.setLayout(new BoxLayout(pnBTN, BoxLayout.Y_AXIS));
		
		
		
		
		
		//end user
		
		//input
		// ma
		JPanel pnMa = new JPanel();
		pnMa.setLayout(new FlowLayout(FlowLayout.LEFT));
		// pnMa.setBackground(Color.BLACK);
		
//		nhap id
		JLabel lblID = new JLabel("ID");
		txtID = new JTextField(20);
		pnMa.add(lblID);
		pnMa.add(txtID);
		//end ma
		
		// nhap tuoi
		JPanel pnTuoi = new JPanel();
		pnTuoi.setLayout(new FlowLayout(FlowLayout.LEFT));
		// pnTen.setBackground(Color.BLUE);
		JLabel lblTuoi = new JLabel("Tuoi");
		txtTuoi = new JTextField(20);
		pnTuoi.add(lblTuoi);
		pnTuoi.add(txtTuoi);
		//end Tuoi
		
		// NoiDung 
		JPanel pnNoiDung = new JPanel();
		pnNoiDung.setLayout(new FlowLayout(FlowLayout.LEFT));
		// pnSdt.setBackground(Color.CYAN);
		JLabel lblNoiDung = new JLabel("NoiDung");
		txtNoiDung = new JTextField(20);
		pnNoiDung.add(lblNoiDung);
		pnNoiDung.add(txtNoiDung);
		//end NoiDung
		
		//  TT
		JPanel pnTT = new JPanel();
		pnTT.setLayout(new FlowLayout(FlowLayout.LEFT));
		// pnTT.setBackground(Color.BLACK);
		JLabel lblTT = new JLabel("TT");
		txtTT = new JTextField(20);
		pnTT.add(lblTT);
		pnTT.add(txtTT);
		//endTT

		//cho nhap bang nhau cho dep
		lblID.setPreferredSize(lblNoiDung.getPreferredSize());	
		lblTuoi.setPreferredSize(lblNoiDung.getPreferredSize());
		lblTT.setPreferredSize(lblNoiDung.getPreferredSize());
		
		//end input
		
		// Button
		JPanel pnButton = new JPanel();
		pnButton.setLayout(new FlowLayout());
		
		
		btnAll = new JButton("TatCa");	
		btnThem = new JButton("Them");
		btnXoa = new JButton("Xoa");
		btnUpdate = new JButton("Update");
		btnThoat = new JButton("Thoat");
		btnTim = new JButton("Tim kiem");
		
		
		pnButton.add(btnAll);
		
		pnButton.add(btnThem);
		pnButton.add(btnXoa);
		pnButton.add(btnUpdate);
//		pnButton.add(btnTim);  
//		pnButton.add(btnThoat);
		
		
		pnBTN.add(pnTimKiem);
//		pnBTN.add(btnEdit);
//		if(edit) {
			pnBTN.add(pnMa);
			pnBTN.add(pnTuoi);
			pnBTN.add(pnNoiDung);
			pnBTN.add(pnTT);			
			pnBTN.add(pnButton);
			
            pnTB.setVisible(edit);
            pnMa.setVisible(edit);
            pnTuoi.setVisible(edit);
            pnNoiDung.setVisible(edit);
            pnTT.setVisible(edit);
            pnButton.setVisible(edit);
//		}
		
		//end Button
            
         
         //event   
			
		//edit display	
			btnEdit.addActionListener(new ActionListener(){
		        public void actionPerformed(ActionEvent e){
		        	   System.out.println("+displayEdit: " + edit);
		        	   edit = !edit;
		               pnTB.setVisible(edit);
		               pnMa.setVisible(edit);
		               pnTuoi.setVisible(edit);
		               pnNoiDung.setVisible(edit);
		               pnTT.setVisible(edit);
		               pnButton.setVisible(edit);
		               
//		               pnTimKiem.setVisible(!edit);
		               btnTimKiem.setVisible(!edit);
		               lblTimKiem.setVisible(!edit);
		               txtTimKiem.setVisible(!edit);
		               btnEdit.setText(!edit ? "Edit" : "Xem");
		        }
		    });
			// end edit display
			
			//Xem Tu Vu
			btnTimKiem.addActionListener(new ActionListener() {
				
//				@Override
				public void actionPerformed(ActionEvent e) {
					//lay data lai 1 lan
					try {
						// 1 la them, 2 la xoa, 3 la update, 4 l� all, 5 l� xem
						os.write(5);
//						oos.writeObject(t);
						showOnTable02();
					} catch (IOException e1) {
						System.out.println("Loi khi them ben client: " + e1);
					}
					//end data
					
					
					List<Tuoi> result = new ArrayList<Tuoi>();				
					String tuoiInput = txtTimKiem.getText();
//					Number number = NumberFormat.getInstance().parse(tuoiInput);
					Integer num = Integer.parseInt(tuoiInput);
					
					
					System.out.println("Tuoi: " + tuoiInput);
//					System.out.println(" => Du:" + num % 12);
//					num = (Integer)num;
					num = num % 12;
					
					int rowCount = dtm.getRowCount();
//					System.out.println("rowCount:" + rowCount);
					for (int i = 0; i < rowCount; i++) {
						if(dtm.getValueAt(i,3).toString().contains(num.toString())) {
							Tuoi t = new Tuoi();
//							System.out.println("dtm[" +i +"]=");
							System.out.println(dtm.getValueAt(i, 2));
							t.setId(dtm.getValueAt(i, 0).toString());
							t.setTuoi(dtm.getValueAt(i, 1).toString());
							t.setNoiDung(dtm.getValueAt(i, 2).toString());						
							t.setTT((Integer) dtm.getValueAt(i, 3));
							result.add(t);
							
//							lblTuVi.setText(dtm.getValueAt(i, 1).toString());
//							txtTuVi.setText(dtm.getValueAt(i, 1).toString());
							txtTuVi.setText(dtm.getValueAt(i, 2).toString());
						} 
						
							//System.out.println("Khong thấy");
					}
//					System.out.println(dtm.getRowCount());
//					System.out.println("rowCount:" + rowCount);
					for (int i = rowCount - 1; i >= 0; i--) {
						dtm.removeRow(i);
					}
					for (Tuoi str : result) {
						// str.show();
						Vector<Object> vec = new Vector<Object>();
						vec.add(str.getId());
						vec.add(str.getTuoi());
						vec.add(str.getNoiDung());
						vec.add(str.getTT());
						dtm.addRow(vec);
					}
				}
			});
			//End Xem Tu Vu
			
	}

	public void addEvent() {
		//xem tu Vi
	
		
		// 1.btnAll
				btnAll.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// connectServer();
//						Tuoi t = new Tuoi(txtID.getText().toUpperCase(), txtTuoi.getText(), txtNoiDung.getText(), Integer.parseInt(txtTT.getText()));
//						t.setTuoi(txtID.getText().toUpperCase(), txtTuoi.getText(), txtNoiDung.getText(), txtTT.getText());
						// db.show();
						
			
						try {
							// 1 la them, 2 la xoa, 3 la update, 4 l� all
							os.write(4);
//							oos.writeObject(t);
							showOnTable02();
						} catch (IOException e1) {
							System.out.println("Loi khi them ben client: " + e1);
						}
					}
				});
				
		// 1.btnThem
		btnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// connectServer();
				Tuoi t = new Tuoi(txtID.getText().toUpperCase(), txtTuoi.getText(), txtNoiDung.getText(), Integer.parseInt(txtTT.getText()));
//				t.setTuoi(txtID.getText().toUpperCase(), txtTuoi.getText(), txtNoiDung.getText(), txtTT.getText());
				// db.show();
				try {
					// 1 la them, 2 la xoa, 3 la update
					os.write(1);
					oos.writeObject(t);
					showOnTable02();
				} catch (IOException e1) {
					System.out.println("Loi khi them ben client: " + e1);
				}
			}
		});
		// 2.btnXoa
		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
//				Tuoi t = new Tuoi("", "", "", 0);	
//				t.setTuoi(txtID.getText().toUpperCase(), txtTuoi.getText(), txtNoiDung.getText(), Integer.parseInt(txtTT.getText()));
//				
				//test
				Tuoi t = new Tuoi(txtID.getText().toUpperCase(), "test", "test", 0);
				try {
					os.write(2);
					oos.writeObject(t);
					showOnTable02();
				} catch (IOException e1) {
					System.out.println("Loi khi them ben client: " + e1);
				}
			}
		});
		// 3.btnUpdate
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Tuoi t = new Tuoi(txtID.getText().toUpperCase(), txtTuoi.getText(), txtNoiDung.getText(), Integer.parseInt(txtTT.getText()));
//				db.setDanhba(txtID.getText(), txtTuoi.getText(), txtNoiDung.getText(), txtTT.getText());
				try {
					os.write(3);
					oos.writeObject(t);
					showOnTable02();
				} catch (IOException e1) {
					System.out.println("Loi khi update ben client: " + e1);
				}
			}
		});
		
		//4.btnTim
		btnTim.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Tuoi> result = new ArrayList<Tuoi>();				
				String id = txtID.getText().toUpperCase();
				
//				String tuoi = txtTuoi.getText(); //.toUpperCase();
				System.out.println("NhapID:" + id);
				
				int rowCount = dtm.getRowCount();
				System.out.println("rowCount:" + rowCount);
				for (int i = 0; i < rowCount; i++) {
					if(dtm.getValueAt(i,0).toString().toUpperCase().contains(id)) {
						Tuoi t = new Tuoi();
						System.out.println("dtm[" +i +"]=");
						System.out.println(dtm.getValueAt(i, 0));
						t.setId(dtm.getValueAt(i, 0).toString());
						t.setTuoi(dtm.getValueAt(i, 1).toString());
						t.setNoiDung(dtm.getValueAt(i, 2).toString());
						t.setTT((Integer) dtm.getValueAt(i, 3));
						result.add(t);
					} 
					
						//System.out.println("Khong thay");
				}
				System.out.println(dtm.getRowCount());
				for (int i = rowCount - 1; i >= 0; i--) {
					dtm.removeRow(i);
				}
				for (Tuoi str : result) {
					// str.show();
					Vector<Object> vec = new Vector<Object>();
					vec.add(str.getId());
					vec.add(str.getTuoi());
					vec.add(str.getNoiDung());
					vec.add(str.getTT());
					dtm.addRow(vec);
				}
			}
		});
		
		// btnThoat
		btnThoat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
	}

	public void showWindow() {
		this.setSize(600, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	// Phan ket noi toi server
    // server
	public void connectServer() {

		Socket socket = null;

		try {
			InetAddress address = InetAddress.getByName("192.168.61.1"); // host IPaddress
            socket = new Socket(address, 2222); // port same as server
			//socket = new Socket("localhost", 2222);
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
			os = socket.getOutputStream();
			is = socket.getInputStream();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//hien thi
	public void showOnTable() {
		int rowCount = dtm.getRowCount();
		// Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			dtm.removeRow(i);
		}
		List<Tuoi> vecDB = new ArrayList<Tuoi>();
		try {
			this.connectServer();
			try {
				vecDB = (List<Tuoi>) ois.readObject();
				for (Tuoi str : vecDB) {
					//test
					 str.show();
					 
					Vector<Object> vec = new Vector<Object>();
					vec.add(str.getId());
					vec.add(str.getTuoi());
					vec.add(str.getNoiDung());
					vec.add(str.getTT());
					dtm.addRow(vec);
				}
				// socket.close();
			} catch (ClassNotFoundException e) {
				System.out.println("Loi Client: ClassNotFoundException!!!!! " + e);
			}
		} catch (UnknownHostException e) {
			System.out.println("Loi client: UnknownHostException " + e);
		} catch (IOException e) {
			System.out.println("Loi client: IOException: " + e);
		}
	}

	public void showOnTable02() {
		int rowCount = dtm.getRowCount();
		// xoa toan bo bang
		for (int i = rowCount - 1; i >= 0; i--) {
			dtm.removeRow(i);
		}
		List<Tuoi> vecDB = new ArrayList<Tuoi>();
		try {
			// this.connectServer();
			try {
				vecDB = (List<Tuoi>) ois.readObject();
				for (Tuoi str : vecDB) {
					// str.show();
					Vector<Object> vec = new Vector<Object>();
					vec.add(str.getId());
					vec.add(str.getTuoi());
					vec.add(str.getNoiDung());
					vec.add(str.getTT());
					dtm.addRow(vec);
				}
				// socket.close();
			} catch (ClassNotFoundException e) {
				System.out.println("Loi!!!!! " + e);
			}
		} catch (UnknownHostException e) {
			System.out.println("Loi client1: " + e);
		} catch (IOException e) {
			System.out.println("Loi client2: " + e);
		}
	}
	
	public void displayTable() {
		pnTB.setVisible(false);
	}

	public static void main(String[] args) {
		Giaodien ui = new Giaodien("Xem Tu Vi");
		ui.showWindow();
		ui.showOnTable();
	}
}
