package com.igeek.shop.entity;

import java.util.Date;

/**
 * 商品类
 */
public class Product {

	private String pid;
	private String pname;         //商品名称
	private Double market_price;  //参考价格
	private Double shop_price;    //实际销售价格
	private String pimage;        //商品图片
	private Date pdate;           //上架日期
	private Integer is_hot;       //0-非热门商品  1-热门商品
	private String pdesc;         //商品描述
	private Integer pflag;        //是否上下架 0-上架 1-下架
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Double getMarket_price() {
		return market_price;
	}
	public void setMarket_price(Double market_price) {
		this.market_price = market_price;
	}
	public Double getShop_price() {
		return shop_price;
	}
	public void setShop_price(Double shop_price) {
		this.shop_price = shop_price;
	}
	public String getPimage() {
		return pimage;
	}
	public void setPimage(String pimage) {
		this.pimage = pimage;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	public Integer getIs_hot() {
		return is_hot;
	}
	public void setIs_hot(Integer is_hot) {
		this.is_hot = is_hot;
	}
	public String getPdesc() {
		return pdesc;
	}
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
	public Integer getPflag() {
		return pflag;
	}
	public void setPflag(Integer pflag) {
		this.pflag = pflag;
	}
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(String pid, String pname, Double market_price, Double shop_price, String pimage, Date pdate,
			Integer is_hot, String pdesc, Integer pflag) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.market_price = market_price;
		this.shop_price = shop_price;
		this.pimage = pimage;
		this.pdate = pdate;
		this.is_hot = is_hot;
		this.pdesc = pdesc;
		this.pflag = pflag;
	}
	
	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", market_price=" + market_price + ", shop_price="
				+ shop_price + ", pimage=" + pimage + ", pdate=" + pdate + ", is_hot=" + is_hot + ", pdesc=" + pdesc
				+ ", pflag=" + pflag + "]";
	}
	
}
