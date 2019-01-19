package cn.honosayaka.jt;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MyFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static MyFrame myframe;
	
	static {
		myframe = new MyFrame();
	}
	
	private MyFrame() {
		super();
	}
	private static String pickedImg = null;
	public static void initMyFrame() {
		myframe.setTitle("ImgToAscciiPic");
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		myframe.setBounds(((int) dimension.getWidth() - 500) / 2, ((int) dimension.getHeight() - 200) / 2, 500, 210);
		myframe.setResizable(false);
		myframe.setLayout(null);
		myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTextArea text = new JTextArea();
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setEditable(false);
		text.setBounds(200, 120, 200, 40);
		text.setText("请先选择图片");
		myframe.add(text);
		text.setVisible(true);
		JButton btn1 = new JButton("选择图片");
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					pickedImg = PicPicker.pick(myframe).getAbsolutePath();
					
				}catch(Exception ex) {
					text.setText("还没有选择图片");
					return;
				}

				text.setText("已选："+pickedImg);
			}
		});
		btn1.setBounds(350, 50, 100, 50);
		btn1.setVisible(true);
		myframe.add(btn1);
		
		JButton btn2 = new JButton("开始生成");
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(pickedImg == null) {
					text.setText("所选图片不能为空！");
					return;
				}
				AscciP.createAsciiPic(pickedImg, 2, 1);
				
				text.setText("已经输出到桌面AscciiPic.txt\n用浏览器打开用CTRL+滚轮调整大小");
			}
		});
		btn2.setBounds(200, 50, 100, 50);
		btn2.setVisible(true);
		myframe.add(btn2);
		myframe.setVisible(true);
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Image img = ImgUtill.getImg("img/photo.jpg");
		g.drawImage(img, 10, 50, 150, 150, null);
	}
	
	public static MyFrame getMyFrame() {
		return myframe;
		
	}
	
}
