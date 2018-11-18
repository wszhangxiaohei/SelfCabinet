package com.selfcabinet.utils;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.selfcabinet.constant.ResponseMessage;
import com.selfcabinet.model.SelfCabinetException;

import java.util.Date;

public class CommonUtil {
    public static <T> String createJWT(String content, Date createTime, Date expireTime) throws Exception {
        //HMAC
        Algorithm algorithm = Algorithm.HMAC256("SelfCabinet");
        String token = JWT.create()
                .withIssuer("auth0")
                .withIssuedAt(createTime)
                .withExpiresAt(expireTime)
                .withSubject(content)
                .sign(algorithm);
        return token;
    }

    public static DecodedJWT phraseJWT(String token) throws Exception {
        Algorithm algorithm = Algorithm.HMAC256("SelfCabinet");
        DecodedJWT jwt;
        try{
            JWTVerifier verifier =JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();
            jwt=verifier.verify(token);
        }
        catch (JWTVerificationException e){
            throw new SelfCabinetException(403, ResponseMessage.ERROR,ResponseMessage.ERROR);
        }
        return jwt;
    }

}


