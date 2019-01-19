package cn.honosayaka.jt;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;


public class PicPicker {
	public static File pick(JFrame f) {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("请选择png/jpg图片");
		chooser.setFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean accept(File f) {
				String fileName = f.getName();
				if(fileName.endsWith(".jpg") || fileName.endsWith(".png") || f.isDirectory()) {
					return true;
				}
				return false;
			}
		});
		int re = chooser.showOpenDialog(f);
		if(re==0) {
			return chooser.getSelectedFile();
		}
		return null;
	}
}
