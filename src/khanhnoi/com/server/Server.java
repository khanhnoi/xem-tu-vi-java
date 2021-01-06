package khanhnoi.com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import tuoi.Tuoi;

public class Server {
	static int resetServerOld = 0;
	static int resetServerNew = 0;
	static int nhan = 0;
	private final String className = "com.mysql.jdbc.Driver";
	Connection connection = null;
	private final String url = "jdbc:mysql://localhost:3306/tuvitest";
	String user = "root";
	String pass = "123456";
	String table = "tuoi";

	// cac luong vao ra
	ObjectInputStream ois;
	ObjectOutputStream oos;
	ServerSocket serversocket;
	OutputStream os;
	InputStream is;
	Socket socket; //

	// 1 Ket noi toi co so du lieu
	public void connect() {
		try {
			Class.forName(className);
			try {
				connection = DriverManager.getConnection(url, user, pass);
				System.out.println("Connect success - ket noi co su lieu");
			} catch (SQLException e) {
				System.out.println("-Loi ket noi - Fail connect" + e);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("Class not found" + e);
		}
	}

	// 2 Get data Tuoi - Hien thi dw lieu len bang
	public ResultSet getData01() {
		ResultSet rs = null;
		Statement stt;
		try {
			stt = connection.createStatement();
			rs = stt.executeQuery("select * from tuoi ");

		} catch (SQLException e) {
			System.out.println("Select error" + e.toString());
		}
		return rs;
	}

	// thuc hien truy van du lieu - man
	public List<Tuoi> getData02() {
		List<Tuoi> arr = new ArrayList<Tuoi>();
		ResultSet rss = this.getData01(); // lay data Tuoi
		System.out.println("+ Bat dau truy van du lieu - mang du lieu");
		try {
			while (rss.next()) {
				Tuoi tuoi = new Tuoi();
				tuoi.setId(rss.getString("ID"));
				tuoi.setTuoi(rss.getString("Tuoi"));
				tuoi.setNoiDung(rss.getString("NoiDung"));
				tuoi.setTT(rss.getInt("TT"));
				arr.add(tuoi);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}

	// server mo cong va chap nhan ket noi tu client
	public void connectPort() {
		ArrayList<Socket> clientSockets = new ArrayList<>();
		try {

			ServerSocket serverSocket = new ServerSocket(2222); // port same as client
			InetAddress inetAddress = InetAddress.getLocalHost(); //
			System.out.println("Server opened at: " + inetAddress.getHostAddress()); //
			System.out.println("Server ready...");

			while (true) // this keeps the server listening
			{
				final Socket socket = serverSocket.accept(); // this accepts incomming connections
				clientSockets.add(socket); // adds current connection to an arraylist
				System.out.println("Connection from " + socket.getInetAddress());
				System.out.println("Client " + clientSockets.size() + " Connected to server....");

				Thread t = new Thread(new Runnable() // Thread handles messages sent by client that just connected
				{
					@Override
					public void run() {
						try {
							while (socket.isConnected()) {
//								System.out.println("socket.isConnected()");
//								BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//								String fromClient = br.readLine();

								List<Tuoi> arr = new ArrayList<Tuoi>();
								oos = new ObjectOutputStream(socket.getOutputStream());
								ois = new ObjectInputStream(socket.getInputStream());
								os = socket.getOutputStream();
								is = socket.getInputStream();
//								System.out.println("1");
								oos.writeObject(arr);
//								System.out.println("2");
								
								
								if (arr != null) {
									// use message from client
									while (true) {
//										System.out.println("3");
										// lien tuc check xem client gui yeu cau j ko va thuc hien no
										ktra();
										System.out.println("+ Check xong");
										arr = getData02();
										oos.writeObject(arr);

									}
								} else // connection might have been reset by client
								{
									socket.close();
									clientSockets.remove(socket);
								}
							}
						} catch (SocketException e) {
							System.out.println("Disconnection from " + socket.getInetAddress());
						} catch (IOException e) {
						}
					}
				});
				t.start();

			}

//			Socket socket = serverSocket.accept();
//
//			System.out.println("Client Connected to server....");
//			oos = new ObjectOutputStream(socket.getOutputStream());
//			ois = new ObjectInputStream(socket.getInputStream());
//			os = socket.getOutputStream();
//			is = socket.getInputStream();
		} catch (IOException e) {
			System.out.println("Loi o server: connectPort - IOException " + e);
			System.out.println("Reset Server ");
//			resetServerNew += 1;
		}

	}

	// server truyen du lieu cho client
	public void connectClient() {
		List<Tuoi> arr = new ArrayList<Tuoi>();
		System.out.println("+ Bat dau ket noi vs Client");

		// truy van database = lay dc mang data
		arr = this.getData02();
		System.out.println(arr != null ? "- Du Lieu (Arr) co" : "-Du Lieu trong");

		// server mo cong va chap nhan ket noi tu client
		this.connectPort();
		System.out.println("Het - Cho nay ko chay dau");
	}

	// 0. Ktra
	public void ktra() {
		try {
//			System.out.println("4");
			// ma nhan dc
			int nhan = 0;
//			System.out.println("+ Dang doi Check Yeu cau ben client:");	
			nhan = is.read();
			// Danhba db = (Danhba) ois.readObject();
			if (nhan == 1) {
				System.out.println("-> Client yeu cau Them");
				this.them();
			} else if (nhan == 2) {
				System.out.println("-> Client yeu cau Xoa");
				this.xoa();
			} else if (nhan == 3) {
				System.out.println("-> Client yeu cau Update");
				this.capNhat();
			} else if (nhan == 4) {
				System.out.println("-> Client yeu cau Xem All");
				this.xemAll();
			}
			else if (nhan == 5) {
				System.out.println("-> Client yeu cau Xem");
				this.xemAll();
			}
			if (nhan != 0) {
//				System.out.println("+ Dang doi Check Yeu cau ben client:");
				System.out.println("+ Nhan duoc la : " + nhan);
//				System.out.println("+ Check xong");
			}

			// db.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Loi o server: KtraNhan " + e);
			e.printStackTrace();
		}

	}

	// 1. Them doi tuong
	// server chen du lieu server toi database
	public void them() {
		try {
			// thong tin client nhap gui cho sever dung de xu ly
			Tuoi db = (Tuoi) ois.readObject();
			db.show();

			// lenh sql them
			String sql = "insert into tuoi value('" + db.getId() + "','" + db.getTuoi() + "','" + db.getNoiDung()
					+ "','" + db.getTT() + "')";
			System.out.println(sql);
			Statement stt;
			try {

				stt = connection.createStatement();
				System.out.println(sql);
				stt.executeUpdate(sql);
			} catch (SQLException e) {
				System.out.println("fail" + e);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Loi them01: " + e);
		} catch (IOException e) {
			System.out.println("Loi them01: " + e);
		}
	}

	// 2. Xoa doi tuong
	// server xoa 1 ban ghi tren database
	public void xoa() {
		try {
			Tuoi db = (Tuoi) ois.readObject();
			db.show();
			String sql = "delete from tuoi where id = '" + db.getId() + "'";
			Statement stt;
			try {

				stt = connection.createStatement();
				System.out.println(sql);
				stt.executeUpdate(sql);
			} catch (SQLException e) {
				System.out.println("fail" + e);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Loi xoa01: " + e);
		} catch (IOException e) {
			System.out.println("Loi xoa02: " + e);
		}
	}

	// 3. Update - server cap nhat dua lieu toi database.
	public void capNhat() {
		try {
			Tuoi db = (Tuoi) ois.readObject();
			db.show();
			String sql = "update tuoi set Tuoi = '" + db.getTuoi() + "', NoiDung = '" + db.getNoiDung() + "', TT='"
					+ db.getTT() + "' where id ='" + db.getId() + "'";
			Statement stt;
			try {

				stt = connection.createStatement();
				System.out.println(sql);
				stt.executeUpdate(sql);
			} catch (SQLException e) {
				System.out.println("fail" + e);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Loi update01: " + e);
		} catch (IOException e) {
			System.out.println("Loi update02: " + e);
		}
	}

	public void xemAll() {
//		try {
//			Tuoi db = (Tuoi) ois.readObject();
//			db.show();

		// lenh sql them
//			String sql = "select * from tuoi ";
//			System.out.println(sql);
		Statement stt;
		try {

			stt = connection.createStatement();
//				System.out.println(sql);
//				stt.executeUpdate(sql);
//				rs = stt.executeQuery("select * from tuoi ");
		} catch (SQLException e) {
			System.out.println("fail" + e);
		}
//		} catch (ClassNotFoundException e) {
//			System.out.println("Loi them01: " + e);
//		} catch (IOException e) {
//			System.out.println("Loi them01: " + e);
//		}
	}

//	public void timKiem() {
//		try {
//			Tuoi db = (Tuoi) ois.readObject();
//			db.show();
//			
//			//lenh sql them
//			String sql = "insert into tuoi value('" + db.getId() + "','" + db.getTuoi() + "','" + db.getNoiDung()
//					+ "','" + db.getTT() + "')";
//			System.out.println(sql);
//			Statement stt;
//			try {
//
//				stt = connection.createStatement();
//				System.out.println(sql);
//				stt.executeUpdate(sql);
//			} catch (SQLException e) {
//				System.out.println("fail" + e);
//			}
//		} catch (ClassNotFoundException e) {
//			System.out.println("Loi them01: " + e);
//		} catch (IOException e) {
//			System.out.println("Loi them01: " + e);
//		}
//	}

	public static void main(String[] args) {
		while (true) {
			Server sv = new Server();
			// ket noi co so du lieu
			sv.connect();
			// Vector<Danhba> arr01 = sv.getData02();
			System.out.println("----------Server da chay----------");
			// ket noi vs Client
			sv.connectClient();
			// sv.them();
			if (resetServerNew > resetServerOld) {
				resetServerNew = 0;
				continue;
			}
			break;
		}

	}
}
