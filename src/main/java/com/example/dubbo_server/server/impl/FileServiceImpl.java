package com.example.dubbo_server.server.impl;

import com.example.dubbo_server.api.FileService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * <p>
 * 無
 * </p>
 *
 * @author NWT)hxl
 * @version 1.0
 * <p>
 * 変更履歴:
 * 2020/08/17 ： NWT)hxl ： 新規作成
 * @date 2020/08/17 19:21
 */
@Service
@DubboService(version = "1.0.0")
public class FileServiceImpl implements FileService {
    @Override
    public byte[] encodeText(File file) throws IOException {
        BufferedReader bf = null;
        ByteArrayOutputStream baos = null;
        try {

            //读取txt中的文字到String
            bf = new BufferedReader(new FileReader(file));
            String content = "";
            StringBuilder sb = new StringBuilder();
            while ((content = bf.readLine()) != null) {
                sb.append(content);
                sb.append('\n');
            }
            content = sb.toString();
            long textLen = content.length();
            int width = (int)Math.sqrt(textLen)+1;
            BufferedImage image = new BufferedImage(width, width, BufferedImage.TYPE_INT_RGB);

            int x = 0, y = 0;
            for(int i = 0; i < textLen; i++) {
                char s = content.charAt(i);
                Color color = new Color(0, ((int)s & 0xFF00) >> 8, (int)s & 0xFF);
                image.setRGB(x, y, color.getRGB());
                if (x==(width-1)) {
                    x = 0;
                    ++y;
                } else {
                    ++x;
                }
            }
            baos = new ByteArrayOutputStream();
            ImageIO.write(image, "BMP", baos);
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bf != null) {
                bf.close();
            }
        }
        return null;
    }

    @Override
    public String decodeText(File file) throws IOException {

        BufferedImage img = ImageIO.read(file);
        int width = img.getWidth(), height = img.getHeight();
        StringBuilder sb = new StringBuilder();

        for(int y = 0; y<height; y++) {
            for(int x = 0; x<width; x++) {
                Color color = new Color(img.getRGB(x, y));
                if(color.getGreen()==0&&color.getBlue()==0&&color.getRed()==0) {
                    break;
                }
                int index = (color.getGreen() << 8) + color.getBlue();
                sb.append((char) index);
            }
        }

        return sb.toString();
    }

}
