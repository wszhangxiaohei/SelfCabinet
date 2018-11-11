package com.selfcabinet.service;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.selfcabinet.constant.ResponseMessage;
import com.selfcabinet.mapper.OrderMapper;
import com.selfcabinet.model.SelfCabinetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

public class QRCodeService {

    private final OrderMapper orderMapper;

    @Autowired
    public QRCodeService(OrderMapper orderMapper){
        this.orderMapper=orderMapper;
    }

    public void createQRCode(String order_id,String type){
        if(!(type.equals("user")||type.equals("courier"))){
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR, ResponseMessage.ERROR_QRCODE_TYPE);
        }
        if (orderMapper.getById(order_id)==null){
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR, ResponseMessage.ERROR_ORDER_ID);
        }
        int width=300;
        int height=300;
        String format="png";
        String content= order_id+"_"+type;
        HashMap map=new HashMap();
        map.put(EncodeHintType.CHARACTER_SET, "utf-8");
        map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        map.put(EncodeHintType.MARGIN, 0);

        try {
            BitMatrix bm= new MultiFormatWriter().encode(content,BarcodeFormat.QR_CODE,width,height);
            Path file =new File("D:/QRCode.png").toPath();
            MatrixToImageWriter.writeToPath(bm,format,file);
        }
        catch (WriterException e){
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR, e.getMessage());
        }
        catch (IOException e){
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR, e.getMessage());
        }

    }

    public String readQRCode(){
        try {
            MultiFormatReader reader =new MultiFormatReader();
            File file =new File("D:/QRCode.png");
            BufferedImage image = ImageIO.read(file);
            BinaryBitmap binaryBitmap=new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            HashMap map =new HashMap();
            map.put(EncodeHintType.CHARACTER_SET, "utf-8");
            Result result =reader.decode(binaryBitmap,map);
            return result.toString();
        } catch (NotFoundException e) {
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR, e.getMessage());
        } catch (IOException e) {
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR, e.getMessage());
        }
    }
}