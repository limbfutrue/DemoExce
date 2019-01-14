package com.baselibrary.network;

/**
 * 创建：Libaoming
 * 日期： 2018/1/23
 * 注释：接口地址
 * 版本： 1.0.0
 */
public class Urls {
    public static final String WEBVIEW_URL = "https://q.eqxiu.com/s/rHyraMmI?eqrcode=1&share_level=3&from_user=aefb8660-276d-4224-a9e3-0d05fe133fd8&from_id=4d09cee8-8a91-46cc-a7c2-72649a133a07&share_time=1529999930774&from=singlemessage";
    public static final String IP = "192.168.1.120";//ip地址
    public static final String BASE_URL = "http://"+IP+":8080";//基地址
    public static final String LOGIN_URL = BASE_URL+"/app/login/login";//登录接口
    public static final String ALL_ORDER_LIST_URL = BASE_URL+"/app/homepage/getAllOrderInfo";//全部订单接口
    public static final String ALL_ORDER_LIST_SEARCH_URL = BASE_URL+"/app/homepage/selectOrderByCode";//查询订单接口
    public static final String INSERT_PICK_PRODUCT_INFO_URL = BASE_URL+"/app/homepage/insertPickProductInfo";//提交上门服务接口
    public static final String ADDRESS_INFO_URL = BASE_URL+"/app/homepage/addressInfo";//地址接口flag: 标识。默认传0。 （0：查询 1：编辑提交 2：删除提交 3:新增收件人地址
    public static final String INSERT_FINANCE_INFO_URL = BASE_URL+"/app/homepage/insertFinanceInfo";//金融服务
    public static final String SELECT_AUDIT_INFO_URL = BASE_URL+"/app/storage/selectAuditInfo";//库房接口sign 菜单标识，默认传1.（1：入库 2：出库 3：库存 4：退货 5：报损 ）
    public static final String ALL_REPORT_URL = BASE_URL+"/allReport.html";
    public static final String STORAGE_WARN_URL = BASE_URL+"/storageWarn.html";
    public static final String SELECT_UNAUDIT_INFO_URL = BASE_URL+"/app/purchase/selectUnauditInfo";//采购flag 标识。默认传1（1：采购审核 2：订单管理）
    public static final String AUDIT_PURCHASE_INFO_AND_CANCEL_URL = BASE_URL+"/app/purchase/auditPurchaseInfoAndCancel";//status：状态，非空（1：通过 2：不通过  3：作废）



}

