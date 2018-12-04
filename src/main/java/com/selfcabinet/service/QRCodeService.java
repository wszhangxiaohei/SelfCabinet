package com.selfcabinet.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.selfcabinet.constant.ResponseMessage;
import com.selfcabinet.mapper.OrderMapper;
import com.selfcabinet.model.Order;
import com.selfcabinet.model.SelfCabinetException;
import com.selfcabinet.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;
import java.util.HashMap;

@Service
public class QRCodeService {

    private final OrderMapper orderMapper;
    private static final Integer QRExpireTime = 1000 * 60 * 60*24*2;

    @Autowired
    public QRCodeService(OrderMapper orderMapper){
        this.orderMapper=orderMapper;
    }

    public void createQRCode(String order_id,String cupboard_id, String type,String carrier_code) throws Exception {
        if(!(type.equals("user")||type.equals("courier"))){
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR_QRCODE_TYPE, ResponseMessage.ERROR_QRCODE_TYPE);
        }

        if (orderMapper.getIdNumByCarrierCode(carrier_code)!=0){
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.MULT_CARRIER_CODE, ResponseMessage.MULT_CARRIER_CODE);
        }
        if (orderMapper.getIdNumById(order_id)!=0){
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.MULT_ORDER_ID, ResponseMessage.MULT_ORDER_ID);
        }

        int width=300;
        int height=300;
        String format="png";
        String contentInfo= order_id+"_"+cupboard_id+"_"+type+"_"+carrier_code;
        Date createTime = new Date();
        Date expireTime = new Date(createTime.getTime() + QRExpireTime);
        String content= CommonUtil.createJWT(contentInfo,createTime, expireTime);
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
            String resultString=result.toString();

            DecodedJWT jwt = CommonUtil.phraseJWT(resultString);
            return jwt.getSubject()+'+'+jwt.getToken();


//            DecodedJWT jwt;
//            jwt.decode(result,);
//
//            } catch (JWTVerificationException exception) {
//                //无效签名或者无效token
//                throw new SelfCabinetException(403, ResponseMessage.OUTDATE_QRCODE, ResponseMessage.OUTDATE_QRCODE);
//            }
//
//            return jwt.getSubject().toString();
        } catch (NotFoundException e) {
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR, e.getMessage());
        } catch (IOException e) {
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR, e.getMessage());
        } catch (Exception e) {
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR, e.getMessage());
        }
    }
}
