package JavaFiles;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImgToBArray {
	@SuppressWarnings("null")
	public byte[] convertImageToBArray(String ImageName) throws IOException {

		
		ByteArrayOutputStream baos = null;
		try {
		    BufferedImage originalImage = ImageIO.read(new File(ImageName));
		    baos = new ByteArrayOutputStream();
		    
		    ImageIO.write(originalImage, "jpg", baos);
		    baos.flush();
		    
		    byte[] imageInByte = baos.toByteArray();
		    baos.close();
		    return imageInByte;
		   

		} catch (IOException e) {
		    e.printStackTrace();
		}
		return null;
	}
	

	@SuppressWarnings("resource")
	public String convertBArrayToImage(byte[] imageBArray) throws IOException {
		
		File out = new File("profile_pic.jpg");
		FileOutputStream fileOuputStream = null;
		 // convert array of bytes into modified file again
		fileOuputStream = new FileOutputStream(out);
		fileOuputStream.write(imageBArray);

		return out.getAbsolutePath();
		
    }
}
