package com.selfcabinet.service;

import com.selfcabinet.constant.ResponseMessage;
import com.selfcabinet.mapper.AdminMapper;
import com.selfcabinet.mapper.CabinetMapper;
import com.selfcabinet.model.Cabinet;
import com.selfcabinet.model.SelfCabinetException;
import com.selfcabinet.model.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final AdminMapper adminMapper;
    private final CabinetMapper cabinetMapper;

    @Autowired
    public AdminService(AdminMapper adminMapper,CabinetMapper cabinetMapper){
        this.adminMapper=adminMapper;
        this.cabinetMapper=cabinetMapper;
    }

    private static final Logger log = LoggerFactory.getLogger(AdminService.class);


    //登录
    public String login(Admin admin)throws Exception{
        log.info("[admin][login]");
        if (admin.getAdmin_id()==null){
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR,ResponseMessage.MISSED_ADMIN_ID);
        }
        Admin adminFromDb=adminMapper.getById(admin.getAdmin_id());
        if (adminFromDb==null){
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(),ResponseMessage.ERROR,ResponseMessage.ERROR_ADMIN_ID);
        }
        if (adminFromDb.getPassword().equals(admin.getPassword())){
            if (adminFromDb.getBar_id().equals(admin.getBar_id())){
                adminMapper.updateStateById(Admin.LOGIN,admin.getAdmin_id());
                log.info("[admin][login]登录成功"+admin.getAdmin_id());
                return ResponseMessage.SUCCESS_REGISTER;
            }
            else {
                throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR,ResponseMessage.ERROR_BAR);
            }
        }
        else {
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR,ResponseMessage.ERROR_PASSWORD);
        }

    }

    //更新密码
    public String updatePassword(Admin admin,String newPassword){
        if (admin.getAdmin_id()==null){
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR,ResponseMessage.MISSED_ADMIN_ID);
        }
        if (newPassword==null){
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(),ResponseMessage.ERROR,ResponseMessage.MISSED_NEW_PASSWORD);
        }
        Admin adminFromDb=adminMapper.getById(admin.getAdmin_id());
        if (adminFromDb==null){
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(),ResponseMessage.ERROR,ResponseMessage.ERROR_ADMIN_ID);
        }
        if (adminFromDb.getState().equals(Admin.LOGOUT)){
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR,ResponseMessage.NO_LOGIN);
        }
        if (adminFromDb.getPassword().equals(admin.getPassword())){
            adminMapper.updatePasswordById(newPassword,admin.getAdmin_id());
            admin.setPassword(newPassword);
            return  ResponseMessage.SUCCESS_UPDATE_PASSWORD;
        }
        else {
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR,ResponseMessage.ERROR_OLD_PASSWORD);
            //return  ResponseMessage.ERROR_OLD_PASSWORD;
        }
    }

    public void logout(String id){
        Admin adminFromDb=adminMapper.getById(id);
        if (adminFromDb==null){
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(),ResponseMessage.ERROR,ResponseMessage.ERROR_ADMIN_ID);
        }
        adminMapper.updateStateById(Admin.LOGOUT,id);
    }

    public List<Cabinet>showStatus(String admin_id,String cupboard_id){
        log.info("[admin][showStatus]");

        Admin adminFromDb=adminMapper.getById(admin_id);
        if (adminFromDb==null){
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(),ResponseMessage.ERROR,ResponseMessage.ERROR_ADMIN_ID);
        }
        if (adminMapper.getStateById(admin_id).equals(Admin.LOGIN)){
           List<Cabinet> cabinetList=cabinetMapper.getByCupboardId(cupboard_id);
           return cabinetList;
        }
        else {
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR,ResponseMessage.NO_LOGIN);
        }
    }

    public Cabinet openCabinet (String admin_id,String cupboard_id,String no){
        Admin adminFromDb=adminMapper.getById(admin_id);
        if (adminFromDb==null){
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(),ResponseMessage.ERROR,ResponseMessage.ERROR_ADMIN_ID);
        }
        if (adminMapper.getStateById(admin_id).equals(Admin.LOGIN)){
            Cabinet cabinet=cabinetMapper.getByCupAndNo(cupboard_id,no);
            cabinet.setOpen(Cabinet.OPEN);
            return cabinet;
        }
        else {
            throw new SelfCabinetException(HttpStatus.FORBIDDEN.value(), ResponseMessage.ERROR,ResponseMessage.NO_LOGIN);
        }
    }

    public List<Admin> queryAdmin(){
        return adminMapper.getAll();
    }
}

