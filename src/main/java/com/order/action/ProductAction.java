package com.order.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.order.entity.Product;
import com.order.entity.User;
import com.order.service.ProductService;
import com.order.util.UploadFile;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	private Product product=new Product();
	private ProductService productService;
	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return this.product;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	/**
	 * 添加商品
	 */
	public String addProduct() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = new JSONObject();
		String path = product.getImageurl();
		ActionContext ac = ActionContext.getContext();
		this.product.setImageurl(UploadFile.upload(path));
		this.product.setProstate("0");
		this.product.setDiscount(1.0);
		productService.addProduct(product);
		PrintWriter out = response.getWriter();
		json.accumulate("code", 200);
		json.accumulate("data", product);
		out.println(json.toString());
		out.flush();
		out.close();
		return NONE;
	}
	/**
	 * 删除商品
	 * @throws IOException 
	 */
	public String delProduct() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		Product product = productService.findProductById(Integer.parseInt(request
				.getParameter("productid")));
		JSONObject json = new JSONObject();
		productService.delProduct(product);
		json.accumulate("code", 200);
		json.accumulate("data", null);
		response.getWriter().println(json.toString());
		return NONE;
	}
	/**
	 * 修改商品
	 */
	public String modProduct() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = new JSONObject();
		Product product2 = productService.findProductById(Integer
				.parseInt(request.getParameter("productid")));
		//product2.setDiscount(Double.parseDouble(request.getParameter("discount")));
		product2.setGoplace(request.getParameter("goplace"));
		product2.setHoteldetail(request.getParameter("hoteldetail"));
		product2.setPrice(Double.parseDouble(request.getParameter("price")));
		product2.setProdescribe(request.getParameter("prodescribe"));
		product2.setProdetail(request.getParameter("prodetail"));
		product2.setProductname(request.getParameter("productname"));
		product2.setProplace(request.getParameter("proplace"));
		productService.modProduct(product2);
		json.accumulate("code", 200);
		json.accumulate("data", product2);
		response.getWriter().print(json.toString());
		return NONE;
	}
	/**
	 * 获取所有正常商品
	 */
	public String findAllPro() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = new JSONObject();
		List<Product> products=productService.findAllPro();
		if(products!=null){
			json.accumulate("code", 200);
			json.accumulate("data", products);
		}else{
			json.accumulate("code", 300);
		}
		response.getWriter().println(json.toString());
		return NONE;
	}
	public String findProById() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = new JSONObject();
		Product product=productService.findProductById(Integer.parseInt(request.getParameter("productid")));
		json.accumulate("code", 200);
		json.accumulate("data", product);
		response.getWriter().println(json.toString());
		return NONE;
	}
	/**
	 * 根据地名获取景点
	 */
	public String findSomePro() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = new JSONObject();
		String proplace=request.getParameter("proplace");	
		List<Product> list=productService.findProByPlace(proplace);
		json.accumulate("code", 200);
		json.accumulate("data", list);
		response.getWriter().println(json.toString());
		return NONE;
	}
}
