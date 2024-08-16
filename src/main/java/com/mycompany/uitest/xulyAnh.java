/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.uitest;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 *
 * @author ADMIN
 */

//Lam mo
public class xulyAnh {
    public static BufferedImage applyFilter(BufferedImage inputImage) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            // Ma trận kernel làm mờ
        float[] kernel = {
            1.0f / 9, 1.0f / 9, 1.0f / 9,
            1.0f / 9, 1.0f / 9, 1.0f / 9,
            1.0f / 9, 1.0f / 9, 1.0f / 9
        };
        
        // Duyệt qua từng điểm ảnh và áp dụng kernel để làm mờ
        for (int x = 1; x < width - 1; x++) {
            for (int y = 1; y < height - 1; y++) {
                float[] rgb = {0, 0, 0};
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        int pixel = inputImage.getRGB(x + dx, y + dy);
                        float weight = kernel[(dx + 1) + (dy + 1) * 3];
                        rgb[0] += ((pixel >> 16) & 0xFF) * weight;
                        rgb[1] += ((pixel >> 8) & 0xFF) * weight;
                        rgb[2] += (pixel & 0xFF) * weight;
                    }
                }
                int blurredPixel = ((int) rgb[0] << 16) | ((int) rgb[1] << 8) | (int) rgb[2];
                outputImage.setRGB(x, y, blurredPixel);
            }
        }
        return outputImage;
    }
    
    //Chuyen sang trang den
    public static BufferedImage convertToGrayscale(BufferedImage inputImage) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = inputImage.getRGB(x, y);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                // Chuyển màu sang màu trắng đen (lấy giá trị trung bình của ba kênh màu)
                int gray = (int) (0.299 * red + 0.587 * green + 0.114 * blue);

                int grayscalePixel = (gray << 16) | (gray << 8) | gray;
                outputImage.setRGB(x, y, grayscalePixel);
            }
        }

        return outputImage;
    }
    
    //Chinh do sang va tuong phan
    public static BufferedImage enhanceBrightnessAndContrast(BufferedImage inputImage, double brightness, double contrast) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        BufferedImage enhancedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = inputImage.getRGB(x, y);
                int alpha = (rgb >> 24) & 0xFF;
                int red = (int) (((rgb >> 16) & 0xFF) * contrast + brightness);
                int green = (int) (((rgb >> 8) & 0xFF) * contrast + brightness);
                int blue = (int) ((rgb & 0xFF) * contrast + brightness);

                // Đảm bảo rằng giá trị RGB nằm trong phạm vi từ 0 đến 255
                red = Math.min(Math.max(red, 0), 255);
                green = Math.min(Math.max(green, 0), 255);
                blue = Math.min(Math.max(blue, 0), 255);

                int newRGB = (alpha << 24) | (red << 16) | (green << 8) | blue;
                enhancedImage.setRGB(x, y, newRGB);
            }
        }

        return enhancedImage;
    }
    
    //Giam nhieu
    public static BufferedImage applyCustomGaussianFilter(BufferedImage inputImage) {
        float[] matrix = new float[] {
            1.0f, 2.0f, 1.0f,
            2.0f, 4.0f, 2.0f,
            1.0f, 2.0f, 1.0f
        };
        Kernel kernel = new Kernel(2, 2, matrix);
        ConvolveOp convolve = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);

        return convolve.filter(inputImage, null);
    }
}
