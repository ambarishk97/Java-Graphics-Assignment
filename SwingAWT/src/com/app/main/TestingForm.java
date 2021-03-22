package com.app.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class TestingForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtphone;
	private JTextField txtcourse;
	private JTable table1;

	/**
	 * Launch the application.
	 * 
	 */
	
	public static void main(String[] args) {
		TestingForm ts = new TestingForm();
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					TestingForm frame = new TestingForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

	/**
	 * Create the frame.
	 */
	//@SuppressWarnings("serial")
	
	
	public TestingForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {

			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 869, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("                                STUDENT REGISTRATION");
		lblNewLabel.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 24));
		
		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "REGISTRATION", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JLabel lblNewLabel_1 = new JLabel("Student Name");
		
		txtname = new JTextField();
		txtname.setHorizontalAlignment(SwingConstants.LEFT);
		txtname.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Phone No");
		
		txtphone = new JTextField();
		txtphone.setHorizontalAlignment(SwingConstants.LEFT);
		txtphone.setColumns(10);
		
		txtcourse = new JTextField();
		txtcourse.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Course");
		
		JButton btnNewButton_1 = new JButton("Edit");
		
		JButton btnNewButton_2 = new JButton("Add");
		btnNewButton_2.addActionListener(new ActionListener() {
			Connection con;
			PreparedStatement insert;
			
			public void updateTable(){
				int c;
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					try {
						String url = "jdbc:mysql://localhost:3306/swing";
			            String username = "root";
			            String password = "root";
						con = DriverManager.getConnection(url,username,password);
				        // System.out.println("Connection is successful !!!!!");

						insert=con.prepareStatement("select * from students");
						
						ResultSet rs = insert.executeQuery();
						ResultSetMetaData rss= rs.getMetaData();
						
						c=rss.getColumnCount();
						DefaultTableModel df = (DefaultTableModel)table1.getModel();
						
						
						df.setRowCount(0);
						
						while(rs.next())
						{
							Vector v2 = new Vector();
							
							for(int i=1;i<=c;i++)
							{
								v2.add(rs.getString("student_id"));
								v2.add(rs.getString("student_name"));
								v2.add(rs.getString("student_phone"));
								v2.add(rs.getString("student_course"));
							}
							df.addRow(v2);
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
			public void actionPerformed(ActionEvent arg0) {
				
				System.out.println("done");

				String name=txtname.getText();
				String phone=txtphone.getText();
				String course=txtcourse.getText();
				
				
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					try {
						String url = "jdbc:mysql://localhost:3306/swing";
			            String username = "root";
			            String password = "root";
						con = DriverManager.getConnection(url,username,password);
				         System.out.println("Connection is successful !!!!!");

						insert=con.prepareStatement("insert into students (student_name,student_phone,student_course) values (?,?,?) ");
						insert.setString(1,name);
						insert.setString(2,phone);
						insert.setString(3,course);
						insert.executeUpdate();
						
						System.out.println("done");
						JOptionPane.showMessageDialog(null, "Student Registration", "Record Added",JOptionPane.INFORMATION_MESSAGE);
						updateTable();

						txtname.setText("");
						txtphone.setText("");
						txtcourse.setText("");
						
						txtname.requestFocus();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//updateTable();
			}
		});
		
		JButton btnNewButton_1_1 = new JButton("Delete");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_2, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(110)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtname)
						.addComponent(txtphone)
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtcourse, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)))
					.addGap(66))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(54)
					.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(100, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(txtname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(txtphone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(txtcourse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(72)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(79, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		table1 = new JTable();
		table1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Phone", "Course"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE)
							.addGap(46)
							.addComponent(table1, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(62)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 679, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
						.addComponent(table1, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
