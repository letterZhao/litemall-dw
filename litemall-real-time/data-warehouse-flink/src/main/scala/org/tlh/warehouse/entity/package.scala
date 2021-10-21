package org.tlh.warehouse

import java.text.SimpleDateFormat
import java.util.Date

import org.json4s._
import org.json4s.jackson.Serialization.read

/**
  * @author 离歌笑
  * @desc
  * @date 2021-10-19
  */
package object entity {

  implicit val formats: Formats = new DefaultFormats {
    override def dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
  }

  /** *
    * 订单数据
    */
  case class Order(
                    id: Int,
                    user_id: Int,
                    order_sn: String, //订单编号
                    order_status: Byte, //订单状态
                    goods_price: Double, //商品总费用
                    freight_price: Double, //配送费用
                    coupon_price: Double, //优惠券减免
                    integral_price: Double, //用户积分减免
                    groupon_price: Double, //团购优惠价减免
                    order_price: Double, //订单费用， = goods_price + freight_price - coupon_price
                    actual_price: Double, //实付费用， = order_price - integral_price
                    pay_id: String, //支付
                    pay_time: Date,
                    ship_sn: String, //发货
                    ship_channel: String,
                    ship_time: Date,
                    refund_amount: Option[Double], //退款
                    refund_type: String,
                    refund_time: Date,
                    confirm_time: Date, //用户确认收货时间
                    add_time: Date, //下单时间
                    update_time: Date, //订单状态更新时间
                    province: Int, //省份ID
                    city: Int, //城市ID
                    country: Int //乡镇ID
                  )

  object Order {

    def apply(message: String): Order = {
      read[Order](message)
    }

  }

  /**
    * 订单宽表
    */
  case class OrderWide(
                        id: Int,
                        user_id: Int,
                        order_sn: String, //订单编号
                        order_status: Byte, //订单状态
                        goods_price: Double, //商品总费用
                        freight_price: Double, //配送费用
                        coupon_price: Double, //优惠券减免
                        integral_price: Double, //用户积分减免
                        groupon_price: Double, //团购优惠价减免
                        order_price: Double, //订单费用， = goods_price + freight_price - coupon_price
                        actual_price: Double, //实付费用， = order_price - integral_price
                        add_time: Date, //下单时间
                        province: Int, //省份ID
                        city: Int, //城市ID
                        country: Int, //乡镇ID
                        var province_name: String = "",
                        var city_name: String = "",
                        var country_name: String = ""
                      )


  /**
    * 订单详情
    */
  case class OrderDetail(
                          id: Int,
                          order_id: Int,
                          goods_id: Int,
                          goods_name: String,
                          goods_sn: String,
                          product_id: String,
                          number: Int,
                          price: Double,
                          specifications: String,
                          pic_url: String,
                          add_time: Date
                        )

  object OrderDetail {
    def apply(message: String): OrderDetail = {
      read[OrderDetail](message)
    }
  }

  case class OrderDetailWide(
                              id: Int,
                              order_id: Int,
                              goods_id: Int,
                              goods_name: String,
                              goods_sn: String,
                              product_id: String,
                              number: Int,
                              price: Double,
                              specifications: String,
                              pic_url: String,
                              add_time: Date,
                              var brand_id: Int = 0, //商品品牌
                              var brand_name: String = "",
                              var first_category_id: Int = 0, // 商品分类
                              var first_category_name: String = "",
                              var second_category_id: Int = 0,
                              var second_category_name: String = ""
                            )

  object OrderDetailWide {

    def apply(orderDetail: OrderDetail): OrderDetailWide = {
      OrderDetailWide(
        orderDetail.id,
        orderDetail.order_id,
        orderDetail.goods_id,
        orderDetail.goods_name,
        orderDetail.goods_sn,
        orderDetail.product_id,
        orderDetail.number,
        orderDetail.price,
        orderDetail.specifications,
        orderDetail.pic_url,
        orderDetail.add_time
      )
    }

  }


  case class Region(
                     id: Int,
                     name: String,
                     code: Int
                   )

  case class GoodsItem(
                        id: Int,
                        goods_sn: String,
                        name: String,
                        category_id: Int, //二级分类ID
                        brand_id: Int // 品牌ID
                      )

  case class GoodsBrand(
                         id: Int,
                         name: String
                       )

  case class GoodsCategory(
                            id: Int,
                            name: String,
                            pid: Int, //父级分类ID
                            level: String
                          )

}
