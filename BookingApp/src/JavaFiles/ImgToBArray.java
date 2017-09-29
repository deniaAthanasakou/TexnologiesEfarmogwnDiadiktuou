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

		// open image
		/*File imgPath = new File(ImageName);
		
		BufferedImage bufferedImage = ImageIO.read(imgPath);

		// get DataBufferBytes from Raster
		WritableRaster raster = bufferedImage .getRaster();
		DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

		return ( data.getData() );*/
		ByteArrayOutputStream baos = null;
		//System.out.println("FILENAME: " + ImageName);
		try {
		    BufferedImage originalImage = ImageIO.read(new File(ImageName));
		    //System.out.println("originalImage: " + originalImage.toString());
		    baos = new ByteArrayOutputStream();
		    //System.out.println("baos: " + baos.toString());
		    
		    ImageIO.write(originalImage, "jpg", baos);
		    baos.flush();
		    
		    byte[] imageInByte = baos.toByteArray();
		    //System.out.println("imageInByte" + imageInByte);
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

		System.out.println("Conversion completed");
		return out.getAbsolutePath();
		
    }
}
