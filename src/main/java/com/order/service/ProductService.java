package com.order.service;

import java.util.List;

import com.order.dao.ProductDAO;
import com.order.dao.UserDAO;
import com.order.entity.Product;

public class ProductService {
	private ProductDAO productDAO;

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		product.setProstate("0");
		productDAO.save(product);
	}

	public Product findProductById(int id) {
		// TODO Auto-generated method stub
		return productDAO.findById(id);
	}

	public void delProduct(Product product) {
		// TODO Auto-generated method stub
		product.setProstate("1");
		productDAO.attachDirty(product);
	}

	public List<Product> findAllPro() {
		// TODO Auto-generated method stub
		return productDAO.findByProstate("0");
//		return productDAO.findAll();
	}

	public void modProduct(Product product) {
		// TODO Auto-generated method stub
		productDAO.attachDirty(product);
	}

	public List<Product> findProByPlace(String proplace) {
		// TODO Auto-generated method stub
		return productDAO.findByProplace(proplace);
	}
}
