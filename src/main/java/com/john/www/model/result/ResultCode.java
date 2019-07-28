package com.john.www.model.result;

/**
 * 响应码枚举
 */
public enum ResultCode {

    /**
     * 成功
     */
    SUCCESS(0, "成功"),

    /**********************************************************************************
     ********系统级异常响应码，包括web服务器错误、数据库异常，与具体业务无关(10开头)***************
     ***********************************************************************************/
    SYS_DATABASE_ERROR(10_0001, "数据库异常"),
    SYS_WEB_SERVER_ERROR(10_0002, "WEB服务器异常"),
    SYS_FILE_IO_ERROR(10_0003, "文件流读取异常"),
    SYS_CONFIG_ERROR(10_0004, "系统配置异常"),
    SYS_INTERFACE_NO_FOUND_ERROR(10_0005, "系统不存在此接口"),
    SYS_FILE_NAME_EMPTY(10_0006, "文件名为空,创建失败"),
    SYS_FILE_EMPTY(10_0007, "文件为空,上传失败"),
    SYS_FILE_URL_EMPTY(10_0008, "文件路径为空，无法处理"),
    SYS_DB_MYBATIS_ERROR(10_0009, "myBatis操作数据库出错，具体错误原因请查看本地日志"),
    SYS_DB_TK_MAPPER_ERROR(10_0010, "tk操作数据库出错，具体错误原因请查看本地日志"),
    SYS_UNAUTHORIZED(10_0011, "授权码失效或不存在，请联系管理员"),
    AUTHORIZATION_CODE_ERROR(10_0010, "授权码错误或已失效"),
    SYS_UNKNOWN_ERROR(10_9999, "未知系统错误"),

    /**********************************************************************************
     ********业务级公共异常响应码，包括非法登录、参数格式错误、json解析错误等(11开头)************
     *********************************************************************************/

    /**
     * 参数格式异常
     **/
    SERVICE_COMM_PARAMETER_ERROR(11_00_01, "参数格式错误"),
    SERVICE_COMM_PARAMETER_VALUE_EMPTY(11_00_02, "参数值不能为空"),

    /**
     * 数据操作异常
     **/
    SERVICE_COMM_DATA_EXISTED(11_01_01, "数据已存在"),
    SERVICE_COMM_OPERATION_DATA_NOT_EXIST(11_01_02, "操作的数据已不存在(包含删除、修改、查询等)"),
    SERVICE_COMM_ENABLE_DATA_UNABLE_TO_DELETE(11_01_03, "启用数据无法删除"),
    SERVICE_COMM_DATA_CORRELATION(11_01_04, "数据存在关联无法删除"),
    SERVICE_COMM_DATA_REPEAT(11_01_05, "存在重复数据"),

    /**
     * 安全相关异常
     **/
    SERVICE_COMM_SECURITY_NOT_LOGIN(11_02_01, "用户未登录系统"),
    SERVICE_COMM_SECURITY_USER_PASSWORD_ERROR(11_02_02, "用户名密码错误"),
    SERVICE_COMM_SECURITY_USER_TOO_MANY(11_02_03, "账号存在异常，请联系管理员处理"),
    SERVICE_COMM_SECURITY_USER_FORBIDEN(11_02_04, "账号已被禁用"),
    SERVICE_COMM_SECURITY_NO_AUTH(11_02_05, "你没有权限访问此接口"),
    SERVICE_COMM_SECURITY_ERROR(11_02_06, "认证鉴权出现错误"),
    SERVICE_COMM_SECURITY_TIMEOUT(11_02_07, "登录超时"),
    SERVICE_COMM_HIS_USER_PASSWORD_ERROR(11_02_08, "密码校验失败"),

    /**
     * 其他异常
     **/
    SERVICE_COMM_OTHER_JSON_ERROR(11_03_01, "JSON解析异常"),
    SERVICE_COMM_OTHER_JAVA_REFLECT_ERROR(11_03_02, "JAVA反射异常"),
    SERVICE_COMM_OTHER_XML_ERROR(11_03_03, "XML解析异常"),
    SERVICE_COMM_OTHER_HTML_ERROR(11_03_04, "HTML解析异常"),
    SERVICE_COMM_OTHER_DATA_ERROR(11_03_05, "数据异常"),
    SERVICE_COMM_OTHER_HTTP_REQUEST_ERROR(11_03_06, "HTTP请求异常"),


    /**********************************************************************************
     ********biss-app 业务异常响应码定义(21开头)******************************************
     *********************************************************************************/

    /**
     * 公共
     **/
    BISS_APP_COMM_INFO_ERROR(21_00_0001, "暂未获取到信息，请联系管理员"),
    BISS_APP_COMM_PUSH_MSG_FAILED(21_00_0002, "消息推送失败"),
    BISS_APP_COMM_PAGE_INFO_ERROR(21_00_0003, "页面资源获取错误"),
    BISS_APP_COMM_EQUIPMENT_IS_NOT_TIED_UP(21_00_0004, "设备未绑定科室"),
    BISS_APP_COMM_DEPARTMENT_NOT_FOUND(21_00_0005, "科室信息不存在，请联系管理员"),
    BISS_APP_COMM_NOT_FOUND(21_00_0006, "暂无数据"),
    BISS_APP_COMM_NOT_BINDING(21_00_0007, "设备未绑定"),

    /**
     * 请求biss-check
     **/
    BISS_APP_REQUEST_BISS_CHECK_EXCEPTION(21_01_0001, "无法请求biss-check，请检查：1. biss-check是否启动 2.biss-app到biss-check网络是否畅通"),
    BISS_APP_REQUEST_BISS_CHECK_RETURN_ERROR(21_01_0002, "请求biss-check返回错误"),
    BISS_APP_REQUEST_BISS_CHECK_DATA_ERROR(21_01_0003, "解析biss-check返回数据时发生异常"),

    /**
     * 床头卡
     **/
    BISS_APP_BEDSIDECARD_NO_PATIENT_INFO(21_02_0001, "没有病人信息"),
    BISS_APP_BEDSIDECARD_ORAL_INFORMATION_IS_NULL(21_02_0002, "获取病人口服单信息为空"),
    BISS_APP_BEDSIDECARD_INFUSION_INFORMATION_IS_NULL(21_02_0003, "获取病人输液卡信息为空"),
    BISS_APP_BEDSIDECARD_TREATMENT_INFORMATION_IS_NULL(21_02_0004, "获取病人治疗单信息为空"),
    BISS_APP_BEDSIDECARD_NO_BEDSIDE_INFO(21_02_0006, "没有床头卡信息"),
    BISS_APP_BEDSIDECARD_NO_PROCESSEDID(21_02_0007, "病人就诊号不存在"),
    BISS_APP_BEDSIDECARD_NO_COST(21_02_0008, "病人消费记录不存在"),

    /**
     * 护士站
     **/
    BISS_APP_NURSE_ALREADY_BINDING_DEPARTMENT(21_03_0001, "设备已绑定科室"),
    BISS_APP_NURSE_BINDING_ERROR(21_03_0002, "设备绑定错误"),
    BISS_APP_NURSE_ADD_ATTENTION_FAILED(21_03_0003, "注意事项批量添加失败"),
    BISS_APP_NURSE_DEL_ATTENTION_FAILED(21_03_0004, "注意事项批量删除失败"),
    BISS_APP_NURSE_PATIENT_INFO_ERROR(21_03_0005, "病人信息有误"),
    BISS_APP_NURSE_THIS_BED_NO_PATIENT(21_03_0006, "该病床无病人"),
    BISS_APP_NURSE_NO_PATIENT_INFO(21_03_0007, "没有病人信息"),
    BISS_APP_NURSE_LOGIN_FAIL(21_03_0008, "护士登录失败,请检查 1. 用户名密码是否正确 2.NFC卡是否匹配"),
    BISS_APP_NURSE_FAR_CALL_NO_EXIST(21_03_0009, "无法查询到远程呼叫信息"),
    BISS_APP_HIS_OUTERDEPARTMENTID_IS_NULL(21_03_0010, "HIS科室ID是NULL"),

    /**
     * 病人
     **/
    BISS_APP_PATIENT_NO_PATIENT_INFO(21_04_0001, "没有病人信息"),
    BISS_APP_DEPARTMENT_NO_EQUIPMENT(21_04_0002, "该科室无可用设备"),
    BISS_APP_PATIENT_FAR_CALL_NO_EXIST(21_04_0003, "病人远程呼叫不存在"),
    BISS_APP_PATIENT_ID_CARD_NULL(21_04_0004, "患者省份证账号为空，验证失败"),
    BISS_APP_PATIENT_CARD_NULL(21_05_0005, "患者卡号为空验证失败"),

    /**
     * 腕表
     **/
    BISS_APP_WATCH_BINDING_ERROR(21_05_0001, "设备未绑定科室"),
    BISS_APP_WATCH_MODIFY_CALL_INFO_FAILED(21_05_0002, "修改呼叫状态失败"),
    BISS_APP_WATCH_NO_FAR_CAL(21_05_0003, "病人远程呼叫信息不存在"),

    /**
     * 保险
     **/
    BISS_APP_INSURANCE_WITHDRAW(21_06_0001, "该产品已下架，无法投保！"),
    BISS_APP_INSURANCE_NOT_EXISTED(21_06_0002, "商品不存在！"),
    BISS_APP_INSURANCE_INFOERROR(21_06_0003, "产品信息错误，请联系管理员！"),
    BISS_APP_INSURANCE_LINKERROR(21_06_0004, "获取支付链接失败！"),
    BISS_APP_INSURANCE_PARAMERROR(21_06_0005, "参数异常！"),
    BISS_APP_INSURANCE_RESULTERROR(21_06_0006, "数据异常！"),
    BISS_APP_INSURANCE_NO_GOODS(21_06_0007, "您的设备无保险信息！"),
    BISS_APP_INSURANCE_PERSON_INFO_ERROR(21_06_0008, "保单人员的信息不完整！"),
    BISS_APP_INSURANCE_REQUEST_ERROR(21_06_0009, "请求失败！"),
    BISS_APP_INSURANCE_NO_ORDER(21_06_0010, "订单信息不存在！"),
    BISS_APP_INSURANCE_SELL_OUT(21_06_0011, "商品售罄！"),
    BISS_APP_INSURANCE_CHECK_ERROR(21_06_0012, "保险核保失败！"),

    /**
     * 床旁平板
     **/
    BISS_APP_BEDPAD_REPEAT_BED(21_07_0001, "系统中存在重复的病床信息，请删除重复病床"),
    BISS_APP_BEDPAD_BED_BEEN_BIND(21_07_0002, "病床已经被其他平板绑定"),
    BISS_APP_BEDPAD_UUID_NO_EXIST(21_07_0003, "床旁平板uuid不能为空"),
    BISS_APP_BEDPAD_NO_FOUND_BY_UUID(21_07_0004, "通过uuid无法查询到平板,请检查数据库中是否录入对应的平板"),
    BISS_APP_BEDPAD_NO_DEPARTMENT(21_07_0005, "平板未绑定到科室"),
    BISS_APP_BEDPAD_IS_BINDING(21_07_0006, "该平板已存在绑定数据"),

    /**
     * 医院
     **/
    BISS_APP_HOSPITAL_NO_PROFILE(21_08_0001, "没有配置医院简介"),
    BISS_APP_HOSPITAL_NO_EQUIPMENT_USE_TIME(21_08_0002, "没有配置设备使用时间"),

    /**
     * 远程探视
     **/
    BISS_APP_VISIT_EXPIRE(21_09_0001, "您的探视已过期,请重新申请"),
    BISS_APP_VISIT_DEVICE_NO_BINDING(21_09_0002, "设备未绑定"),
    BISS_APP_VISIT_VIDEO_NOT_OPEN(21_09_0003, "当前视频探视未开启，请稍后再试"),
    BISS_APP_VISIT_IS_APPLYING_FOR(21_09_0004, "您的申请正在处理中,请勿重复提交"),
    BISS_APP_VISIT_APPLY_NO_EXIST(21_09_0005, "不存在探视申请记录"),

    /**
     * 入院风险评估
     **/
    BISS_APP_RISK_JOB_NUMBER_AND_PASSWORD_ERROR(21_10_0001, "工号或密码错误"),

    /**
     * 广告
     **/
    BISS_APP_ADV_POSITION_NO_EXIST(21_11_0001, "广告位置不存在"),
    BISS_APP_ADV_TYPE_NO_EXIST(21_11_0002, "广告类型不存在"),

    /**
     * 出入院指引
     **/
    BISS_APP_INPATIENT_GUIDE_NO_EXIST(21_12_0001, "出入院指引不存在"),

    /**
     * 视频
     **/
    BISS_APP_VIDEO_NUM_NO_EXIST(21_13_0001, "要查询的视频不存在"),

    /**
     * 模块
     **/
    BISS_APP_MODULAR_NO_EXIST(21_14_0001, "模块不存在"),
    BISS_APP_HOSPITAL_PROFILE_IS_NULL(21_15_0001, "缺少医院简介"),

    /**
     * 点餐
     **/
    BISS_APP_COMPANY_INTRODUCTION_NO_EXIST(21_16_0001, "公司简介不存在"),

    /**********************************************************************************
     ********biss-pay 业务异常响应码定义(22开头)********************************************
     *********************************************************************************/
    BISS_PAY_REQUEST_BISS_CHECK_ERROR(22_01_0001, "请求biss-check发生错误"),

    /**********************************************************************************
     ********biss-check 业务异常响应码定义(23开头)******************************************
     *********************************************************************************/
    BISS_CHECK_REQUEST_HIS_ERROR(23_00_0001, "请求his出错"),

    BISS_CHECK_DATA_IS_NULL(23_01_0001, "没有该业务数据"),
    BISS_CHECK_NO_BINDING_EQUIPMENT(23_01_0002, "设备未绑定用户"),
    BISS_CHECK_NO_BEDSIDE_INFO(23_01_0003, "没有床头卡信息"),
    BISS_CHECK_NO_PATIENT_INFO(23_01_0004, "没有患者信息"),
    BISS_CHECK_NO_PATIENT_INFO_COMPLETE(23_01_0005, "患者信息不完整"),
    BISS_CHECK_NO_PATIENT_IS_NOT_IN_HOSPITAL(23_01_0006, "此床暂无病人"),
    BISS_CHECK_EXCISE_IS_NOT_EXIST(23_01_0007, "没有消费明细记录"),
    BISS_CHECK_TRUNCATE_TABLE_EXCEPITON(23_01_0008, "清空表数据异常"),
    BISS_CHECK_CALL_NO_TEMP_INSERT_EXCEPITON(23_01_0009, "临时表添加数据异常"),
    BISS_CHECK_CALL_NO_UPDATE_EXCEPITON(23_01_0010, "更新叫号信息异常"),

    /**********************************************************************************
     ********biss-job 业务异常响应码定义(24开头)********************************************
     *********************************************************************************/
    BISS_JOB_GET_TIME_CONSUMING_ERROR(24_01_0001, "获取耗时异常"),
    BISS_JOB_MESSAGEDIGEST_GETINSTANCE_ERROR(24_01_0002, "获取拼接后的唯一业务主键异常"),
    BISS_JOB_TRUNCATE_TABLE_EXCEPITON(24_01_0004, "清空表数据异常"),
    BISS_JOB_CALL_NO_TEMP_INSERT_EXCEPITON(24_01_0004, "叫号表添加叫号数据异常"),
    BISS_JOB_CALL_NO_UPDATE_EXCEPITON(24_01_0005, "更新叫号信息异常"),
    BISS_JOB_SHOT_PATIENT_SEQUENCE_DATA_ERROR(24_01_0006, "给当日未检查的用户分配序号"),
    BISS_JOB_MOVE_DATE_FORHISTORYTABLE_ERROR(24_01_0007, "将前一天数据移动至历史表异常"),
    BISS_JOB_UUID_IS_NULL(24_01_0008, "设备ID是NULL"),
    BISS_JOB_GET_PATIENTINFO_BY_PROCESSEDID(24_01_0009, "获取根据就诊号获取患者预缴金和余额异常"),
    BISS_JOB_UPDATE_PATIENT_BALANCE(24_01_0010, "更新患者余额异常"),
    BISS_JOB_REQUEST_BISS_CHECK_ERROR(24_01_0011, "请求biss-check获取his数据出错"),

    /**********************************************************************************
     ********biss-manage 业务异常响应码定义(25开头)*****************************************
     *********************************************************************************/

    /**
     * 用户模块
     ***/
    BISS_MANAGE_USER_SUPER_ADMIN_CANNOT_DELETE(25_01_0001, "超级管理员不能删除"),
    BISS_MANAGE_USER_ROLE_USED_CANNOT_DELETE(25_01_0002, "角色存在引用无法删除"),
    BISS_MANAGE_USER_NO_EXISTED(25_01_0003, "用户不存在"),
    BISS_MANAGE_USER_NAME_EXISTED(25_01_0004, "用户名已存在"),
    BISS_MANAGE_USER_UNABLE_TO_OPERATE(25_01_0005, "无法进行操作，请完善您的个人信息！"),
    BISS_MANAGE_USER_SUPER_ADMIN_INFO_CANNOT_UPDATE(25_01_0006, "超级管理员的信息不能修改"),

    /**
     * 病人模块
     ***/
    BISS_MANAGE_PATIENT_NO_EXISTED(25_02_0001, "病人已出院，无需xxx"),
    BISS_MANAGE_PATIENT_STATUS(25_02_0002, "病人状态错误"),
    BISS_MANAGE_HIS_DATA_UNABLE_TO_DELETE(25_02_0003, "HIS数据无法删除"),

    /**
     * 科室模块
     ***/
    BISS_MANAGE_DEPARTMENT_NO_EXISTED(25_03_0001, "根据实际情况填写......."),
    BISS_MANAGE_DEPARTMENT_STATUS(25_03_0002, "根据实际情况填写......"),
    SERVICE_DEPARTMENT_NO_EQUIPMENT(25_03_0003, "科室无可用设备"),

    BISS_MANAGE_EQUIPMENT_BINDED(25_04_0001, "设备已被绑定"),
    BISS_MANAGE_CHANNEL_MODIFY_ERROR(25_04_0002, "修改频道出错"),


    /**
     * app模块
     ***/
    BISS_MANAGE_APP_MODULAR_NAME_EXISTED(25_05_0001, "上传的APK模块名称与输入的不一致"),
    BISS_MANAGE_HOSPITAL_ONLY_ONE(25_05_0002, "医院只能添加一个"),
    BISS_MANAGE_APP_VERSION_NUMBER_ERROR(25_05_0003, "上传的APK版本错误"),

    /**
     * 广告模块
     ***/
    BISS_MANAGE_ADVERTISING_NAME_EXISTED(25_06_0001, "广告名称已存在"),

    /**
     * 医护人员介绍模块
     ***/
    BISS_MANAGE_Personnel_VALUE_EXISTED(25_07_0001, "医护人员级别已存在"),
    BISS_MANAGE_Personnel_VALUE_DELETE(25_07_0002, "本人不能删除本人"),


    /**视频管理模块**/
    BISS_MANAGE_VIDEO_NUM_EXISTED(25_08_0001, "该集数已存在"),
    BISS_MANAGE_VIDEO_NUM_SET_EXCEEDED_THE_MAXIMUM(25_08_0002, "超出视频最大集数"),


    /**远程探视模块**/
    BISS_MANAGE_VISIT_APPLY_ID_ERROR(25_09_0001, "远程探视申请编号为空或非法"),
    BISS_MANAGE_VISIT_APPLY_DURATION_ERROR(25_09_0002, "远程探视申请探视时间为空或非法"),
    BISS_MANAGE_VISIT_APPLY_ID_DEVICEID_ERROR(25_09_0003, "远程探视申请编号或探视设备参数为空或非法"),
    BISS_MANAGE_VISIT_APPLY_UUID_ERROR(25_09_0004, "终止探视的设备编号为空或非法"),
    BISS_MANAGE_VISIT_APPLY_CAMERASRC_ERROR(25_09_0005, "未匹配到探视设备信息"),
    BISS_MANAGE_VISIT_PUSH_ERROR(25_09_0006, "远程探视推送任务消息失败"),
    BISS_MANAGE_VISIT_STATUS_ERROR(25_09_0007, "远程探视服务状态未设置或未开启"),
    BISS_MANAGE_VISIT_TIME_OUT(25_09_0008, "探视申请已过期,状态更新失败"),


    /**保险模块**/
    BISS_MANAGE_INSURANCE_HANDLE_SUCCESS(25_10_0001, "操作成功"),
    BISS_MANAGE_INSURANCE_HANDLE_FAIL(25_10_0002, "操作失败"),
    BISS_MANAGE_INSURANCE_PARAM_NULL(25_10_0003, "参数为空"),
    BISS_MANAGE_INSURANCE_NAME_REPEAT(25_10_0004, "数据重复"),
    /**
     * 叫号模块
     **/
    BISS_MANAGE_CALL_NUMBER_DATA_MISSING(25_11_0001, "部分叫号数据缺失"),
    /**护士站模块***/
    BISS_MANAGE_CONFIRM_DELETION(25_12_0001, "消息已绑定确认是否删除"),
    BISS_VERSINS_NOT_NEED_UPDATE(25_12_0002, "当前版本已经是最新版本，不需要更新"),
    BISS_VERSINS_ERRO(25_12_0002, "版本号错误，无法更新"),
    BISS_SCHEDULIN_IS_EXIST(25_12_0003, "该排班已存在"),
    BISS_SCHEDULIN_NOT_EXIST(25_12_0004, "该排班不存在"),
    TIME_BUCKE_NOT_EXIST(25_12_0005, "班别信息不存在"),
    MEDICAL_WORKERS_NOT_EXIST(25_12_0006, "医护人员信息不存在"),
    MEDICAL_WORKERS_RANK_EXIST(25_12_0007, "该分类下有医护人员，不可删除"),
    DEPARTMENT_PHONE_NOT_EXIST(25_12_0008, "姓名或电话号码为空"),
    DEPARTMENT_GROUP_NAME_NOT_EXIST(25_12_0009, "分组名称为空"),
    BISS_NURSE_GROUPE_NOT_DELETE(25_12_0010, "请先删除该分组信息中的床号信息或护士信息"),
    BISS_NURSE_NOTICE_NOT_EDIT(25_12_0011, "此消息不可编辑！"),
    BISS_NURSE_NOT_BIND_DEPARTMENT(25_12_0012, "人员信息未绑定科室！"),
    BISS_NURSE_DEPARTMENTID_IS_NULL(25_12_0013, "科室id不能为空！"),
    BISS_NURSE_BACKOUT_NOT_DELETE(25_12_0014, "未撤销的通知不能被删除！"),
    BISS_NURSE_COMMON_NOT_DELETE(25_12_0015, "启用的公告不能被删除！"),

    /**********************************************************************************
     ********biss-pay 业务异常响应码定义(26开头)*****************************************
     *********************************************************************************/
    /**
     * 大屏支付
     **/
    BISS_PAY_NO_BEDINFO(26_01_0001, "信息获取失败！"),
    /**
     * 床旁支付
     **/
    BISS_PAY_PATIENT_ERROR(26_01_0002, "患者信息与his不符，验证失败"),
    BISS_PAY_QR_CODE_ERROR(26_01_0003, "获取支付二维码异常，请联系管理员");

    /**
     * 系统错误码最小值
     */
    public static final  int SYS_CODE_VALE_MIN = 10_0001;
    /**
     * 系统错误码最大值
     */
    public static final  int SYS_CODE_VALE_MAX = 10_9999;


    /**
     * 业务错误公共码最小值
     */
    public static final  int SERVICE_COMM_CODE_VALE_MIN = 11_00_01;
    /**
     * 业务错误公共码最大值
     */
    public static final  int SERVICE_COMM_CODE_VALE_MAX = 11_99_99;

    /**
     * 业务错误非公共码最小值
     */
    public static final  int SERVICE_NO_COMM_CODE_VALE_MIN = 21_00_0000;
    /**
     * 业务错误非公共码最大值
     */
    public static final  int SERVICE_NO_COMM_VALE_MAX = 99_99_9999;


    private final int code; //状态码
    private final String message; //返回消息

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    /**
     * 判断是否为系统级错误码
     *
     * @return
     */
    public boolean isSysError() {
        if (this.code >= SYS_CODE_VALE_MIN && this.code <= SYS_CODE_VALE_MAX) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否为业务公共错误码
     *
     * @return
     */
    public boolean isServiceCommError() {
        if (this.code >= SERVICE_NO_COMM_CODE_VALE_MIN && this.code <= SERVICE_COMM_CODE_VALE_MAX) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否为业务公共非错误码
     *
     * @return
     */
    public boolean isServiceNoCommError() {
        if (this.code >= SERVICE_COMM_CODE_VALE_MIN && this.code <= SERVICE_NO_COMM_VALE_MAX) {
            return true;
        }
        return false;
    }
}
