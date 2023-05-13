package com.xunmeng.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * ClassName: YxShopInfo
 * Package: com.xunmeng.domain
 * Description:
 *
 * @Author LTM
 * @Create 2023/5/13 16:38
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="YxShopInfo对象", description="")
public class YxShopInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自动增长ID")
    @TableId(value = "shop_id", type = IdType.AUTO)
    private Long shopId;

    @ApiModelProperty(value = "门店名称")
    private String shopName;

    @ApiModelProperty(value = "微信openId")
    private String wxOpenId;

    @ApiModelProperty(value = "登录密码")
    private String password;

    @ApiModelProperty(value = "加密因子")
    private String encrypt;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "门头照")
    private String doorPhoto;

    @ApiModelProperty(value = "身份证号")
    private String cardId;

    @ApiModelProperty(value = "身份证照片正面")
    private String cardPhoto;

    @ApiModelProperty(value = "身份证照片反面")
    private String cardPhoto2;

    @ApiModelProperty(value = "联系电话")
    private String shopPhone;

    @ApiModelProperty(value = "真实名称")
    private String realName;

    @ApiModelProperty(value = "公告")
    private String notice;

    @ApiModelProperty(value = "收款账号类型：1：支付宝，2：银行卡号")
    private Integer accountType;

    @ApiModelProperty(value = "收款账号")
    private String accountNo;

    @ApiModelProperty(value = "收款账号对应的姓名")
    private String accountName;

    @ApiModelProperty(value = "收款账号银行，如中国工商银行")
    private String accountBankName;

    @ApiModelProperty(value = "收款账号分行")
    private String accountBankAdress;

    @ApiModelProperty(value = "商家描述")
    private String shopDescribe;

    @ApiModelProperty(value = "保证金")
    private BigDecimal bondMoney;

    @ApiModelProperty(value = "应收金额，商家订单汇总后的金额")
    private BigDecimal totalMoney;

    @ApiModelProperty(value = "实收金额，寻梦扣点后的金额")
    private BigDecimal payMoney;

    @ApiModelProperty(value = "待审核提现金额")
    private BigDecimal waitMoney;

    @ApiModelProperty(value = "处罚金额")
    private BigDecimal punishMoney;

    @ApiModelProperty(value = "状态；0：申请，1,审核通过 10：营业中，-1：休息，-10：处罚中，-100：关店")
    private Integer dataStatus;

    @ApiModelProperty(value = "来源；1：网页，2：微信申请，3：管理员")
    private Integer dataSource;

    @ApiModelProperty(value = "门店所属类别ID，形式：[1],[2]隔开，方便搜索")
    private String shopCategoryId;

    @ApiModelProperty(value = "门店所属类别，用,隔开，方便展示")
    private String shopCategoryName;

    @ApiModelProperty(value = "添加时间")
    private LocalDateTime addTime;

    @ApiModelProperty(value = "最后修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "最后操作的寻梦用户id")
    private Long lastOperatorIdXm;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "店铺地址")
    private String address;

    @ApiModelProperty(value = "微信code,用于获取微信的openId，不能重复获取")
    private String wxCode;

    @ApiModelProperty(value = "月销，近30天的单子数量")
    private Integer monthSells;

    @ApiModelProperty(value = "费率，超市和小茶仙不扣点，其他是0.03")
    private BigDecimal rate;

    @ApiModelProperty(value = "极光推送设备标识")
    private String registrationId;

    @ApiModelProperty(value = "保证金类型： 0：需要保证金 1：房租模式不需要保证金")
    private Integer bondType;
}
