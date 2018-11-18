package com.selfcabinet.constant;

public class ResponseMessage {
    public static final String ERROR = "error!";
    public static final String SUCCESS = "success!";
    public static final String MISSED_ADMIN_ID="缺失管理员账号";
    public static final String MISSED_NEW_PASSWORD="新密码不能为空";
    public static final String ERROR_PASSWORD="密码错误，登录失败";
    public static final String ERROR_BAR="管理员权限错误";
    public static final String SUCCESS_REGISTER="管理员登录成功";
    public static final String SUCCESS_UPDATE_PASSWORD="更新密码成功";
    public static final String ERROR_OLD_PASSWORD="密码验证错误";
    public static final String ERROR_CABINET_DISTRIBUTION="自提柜分配错误";
    public static final String NO_SPARE_CABINET="没有空闲柜子";
    public static final String NO_GOODS="商品未送达";
    public static final String ERROR_HARD_STATE_OF_CABINET="柜子故障";
    public static final String ERROR_QRCODE_TYPE="二维码类型错误";
    public static final String OUTDATE_QRCODE="二维码过期";
    public static final String NO_LOGIN="您尚未登录";
    public static final String FAILED_CANCEl_ORDER="货物已经送到，取消订单失败";
    public static final String SUCCESS_CANCEL_ORDER="取消订单成功";
    public static final String ERROR_CABINET_USED="自提柜状态错误";
    public static final String ERROR_ORDER_ID="没有相应订单";
    public static final String ERROR_CABINET_OPEN="输入自提柜门的开关错误";

}
