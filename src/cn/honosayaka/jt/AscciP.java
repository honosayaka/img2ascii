
package cn.honosayaka.jt;

 

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;

public class AscciP {


	static BufferedWriter out;
	public static void createAsciiPic(String path,int precisionY,int precisionX) {
		String base = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. ";// 字符串由复杂到简单
		Map<String,String> m = System.getenv();
		String descktop = "c:/Users/"+m.get("USERNAME")+"/Desktop/AsciiPic.txt";
		try {
			File f = new File(descktop);
			if(!f.exists()) {
				f.createNewFile();
			}
			out = new BufferedWriter(new FileWriter(f));

			BufferedImage image = ImageIO.read(new File(path));

			for (int y = 0; y < image.getHeight(); y+=precisionY) {

				for (int x = 0; x < image.getWidth(); x+=precisionX) {

					int pixel = image.getRGB(x, y);

					int r = (pixel & 0xff0000) >> 16, g = (pixel & 0xff00) >> 8, b = pixel & 0xff;

					float gray = 0.299f * r + 0.578f * g + 0.114f * b;

					int index = Math.round(gray * (base.length() + 1) / 255);

					String curString = index >= base.length() ? " " : String.valueOf(base.charAt(index));
					out.write(curString);
				}
				out.newLine();

			}

		} catch (final IOException e) {

			e.printStackTrace();

		}finally{
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}


}
