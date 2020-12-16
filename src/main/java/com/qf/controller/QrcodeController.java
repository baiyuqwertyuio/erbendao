package com.qf.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.google.zxing.Result;
import com.qf.utils.QRCodeUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

/**
 * 二维码调用前端控制器
 */
@RequestMapping("/Qrcode")
@RestController
@CrossOrigin
public class QrcodeController {

    /**
     * 生成二维码
     */
    @GetMapping("/")
    public void productcode() {
        String s = UUID.randomUUID().toString();
        int i = new Random().nextInt(10);
        QRCodeUtil.zxingCodeCreate("www.baidu.com", "D:/voice/picture/2018/",500,"D:/voice/picture/2018/"+i+".jpg");
    }

    @RequestMapping("/image")
    public void productcodeimage(HttpServletResponse response) throws IOException {
        int i = new Random().nextInt(1000);
        String s = UUID.randomUUID().toString();
        BufferedImage bufferedImage = QRCodeUtil.getBufferedImage(s, 500, "D:/voice/picture/2018/"+i+".jpg");
        ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
    }

    /**
     * 解析二维码
     */
    @GetMapping("/test")
    public void analysiscode() {
        Result result = QRCodeUtil.zxingCodeAnalyze("D:/voice/picture/2018/759.jpg");
        System.err.println("二维码解析内容："+result.toString());
    }
    @RequestMapping("/image1")
    public String productcodeimage1(HttpServletResponse response) throws IOException {
        String s = UUID.randomUUID().toString();
        QrConfig config = new QrConfig(100, 100);
        // 设置边距，既二维码和背景之间的边距
        config.setMargin(1);
        // 设置前景色，既二维码颜色（青色）
        config.setForeColor(Color.BLACK.getRGB());
        // 设置背景色（灰色）
        config.setBackColor(Color.WHITE.getRGB());
        // 生成二维码到文件，也可以到流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        QrCodeUtil.generate("http://app.iqiyi.com/pc/player/index.html", config, ImgUtil.IMAGE_TYPE_PNG, outputStream);
        byte[] pngData = outputStream.toByteArray();
        // 这个前缀，可前端加，可后端加，不加的话，不能识别为图片
        return "data:image/png;base64," + Base64.encode(pngData);
    }
}
