package org.springside.examples.quickstart.utils;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
     
/**   
 * 图片格式  
 */     
public class ImageUtils {     
    /**   
     * 缩放图片   
     *    
    * @param width   
     *            输出宽度   
     * @param height   
     *            输出高度   
     * @param input   
     *            输入流   
     * @param output   
     *            输出流   
     * @param format   
     *            输出格式   
     * @return   
     * @throws Exception   
     */     
    public static boolean convert(int width, int height, InputStream input, OutputStream output, String format) throws Exception {     
        // 输入     
        BufferedImage inputImage = ImageIO.read(input);     
        // 转换     
        RenderedImage im = (RenderedImage) convert(height, height, inputImage);     
        // 输出     
        return ImageIO.write(im, format, output);     
    }     
     
    /**   
     * 转换压缩算法   
     *    
     * @param input   
     *            输入文件   
     * @param output   
     *            输出文件   
     * @return   
     * @throws Exception   
     */     
    public static boolean convert(File input, File output) throws Exception {     
        // 输入     
        BufferedImage inputImage = ImageIO.read(input);     
     
        // 转换     
        int width = inputImage.getWidth();     
        int height = inputImage.getHeight();     
     
        RenderedImage im = (RenderedImage) convert(width, height, inputImage);     
        String outputFilename = output.getName();     
        String format = outputFilename.substring(outputFilename.lastIndexOf('.') + 1);     
        // 输出     
        return ImageIO.write(im, format, output);     
    }     
     
    /**   
     * 缩放图片   
     *    
     * @param width   
     *            输出宽度   
     * @param height   
     *            输出高度   
     * @param input   
     *            输入文件   
     * @param output   
     *            输出文件   
     * @return   
     * @throws Exception   
     */     
    public static boolean convert(int width, int height, File input, File output)  throws Exception {     
        // 输入     
        BufferedImage inputImage = ImageIO.read(input);     
        // 转换     
        RenderedImage im = (RenderedImage) convert(width, height, inputImage);     
        String outputFilename = output.getName();     
        String format = outputFilename.substring(outputFilename.lastIndexOf('.') + 1);     
        // 输出     
        return ImageIO.write(im, format, output);     
    }     
     
    /**   
     * 缩放图片   
     *    
     * @param width     输出宽度   
     * @param height     输出高度   
     * @param input      输入路径   
     * @param output     输出路径   
     * @return   
     * @throws Exception   
     */     
    public static boolean convert(int width, int height, String inputPath, String outputPath) throws Exception {     
        return convert(width, height, new File(inputPath), new File(outputPath));     
    }     
     
    /**   
     * 转换   
     * @param width    输出宽度   
     * @param height     输出高度   
     * @param input    BufferedImage   
     * @return BufferedImage   
     * @throws Exception   
     */     
    private static BufferedImage convert(int width, int height, BufferedImage input) throws Exception {     
        // 初始化输出图片     
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);     
     
        // 重新绘图     
        Image image = input.getScaledInstance(output.getWidth(), output.getHeight(), output.getType());     
     
        output.createGraphics().drawImage(image, null, null);     
     
        return output;     
    }     
     
    /**   
     * 等比缩放图片   
     * @param width      输出宽度   
     * @param height     输出高度   
     * @param input    输入流   
     * @param output    输出流   
     * @return   
     * @throws Exception   
     */     
    public static boolean equimultipleConvert(int width, int height, String input, String output) throws Exception {     
        return equimultipleConvert(width, height, new File(input), new File(output));     
    }     
     
    /**   
     * 等比缩放图片   
     * @param width    输出宽度   
     * @param height    输出高度   
     * @param input   输入流   
     * @param output   输出流   
     * @return   
     * @throws Exception   
     *      */     
    public static boolean equimultipleConvert(int width, int height, File input, File output) throws Exception {     
        // 输入     
        BufferedImage image = ImageIO.read(input);     
     
        // 重新核算尺寸     
        if (image.getWidth() > 0 && image.getHeight() > 0) {     
            if ((image.getWidth() / image.getHeight()) >= (width / height)) {     
                if (image.getWidth() > width) {     
                    height = (image.getHeight() * width) / image.getWidth();     
                } else {     
                    width = image.getWidth();     
                    height = image.getHeight();     
                }     
            } else {     
                if (image.getHeight() > height) {     
                    width = (image.getWidth() * height) / image.getHeight();     
                } else {     
                    width = image.getWidth();     
                    height = image.getHeight();     
                }     
            }     
        }     
     
        // 转换 输出     
        return convert(width, height, input, output);     
    }     
    
    public  static String saveFile(String newFileName, MultipartFile filedata,HttpServletRequest httpRequest) {
		// 根据配置文件获取服务器图片存放路径
		String saveFilePath =httpRequest.getRealPath("")+"/images";
		/* 构建文件目录 */
		File fileDir = new File(saveFilePath);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		try {
			File file = new File(saveFilePath, newFileName);
//			ImageUtils.equimultipleConvert(500, 500, input, file);
			FileOutputStream out = new FileOutputStream(file);
			// 写入文件
			out.write(filedata.getBytes());
			out.flush();
			out.close();
			return "http://www.hyb158.com"+httpRequest.getContextPath()+"/images/"+newFileName;
		} catch (Exception e) {
			e.printStackTrace();
			return "";		
		}
	}
    
    public static void main(String[] args){
    	try {
			System.out.println(ImageUtils.convert(260, 283, "e:\\QQ图片\\3x446230456.bmp",  "e:\\QQ图片\\3x446230456.jpg"));
		} catch (Exception e) {
			e.printStackTrace();
		}     
    			 
    }
}    