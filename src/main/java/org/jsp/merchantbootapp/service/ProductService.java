package org.jsp.merchantbootapp.service;

import java.util.Optional;

import org.jsp.merchantbootapp.dao.MerchantDao;
import org.jsp.merchantbootapp.dao.ProductDao;
import org.jsp.merchantbootapp.dto.Merchant;
import org.jsp.merchantbootapp.dto.Product;
import org.jsp.merchantbootapp.dto.ResponseStructure;
import org.jsp.merchantbootapp.exception.MerchantNotFoundException;
import org.jsp.merchantbootapp.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;

	@Autowired
	private MerchantDao merchantDao;

	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product, int merchant_id) {
		Optional<Merchant> recMerchant = merchantDao.findById(merchant_id);
		if (recMerchant.isPresent()) {
			Merchant merchant = recMerchant.get();
			product.setMerchant(merchant);// Assigning Merchant to product
			merchant.getProducts().add(product);// Assigning Product to Merchant
			ResponseStructure<Product> structure = new ResponseStructure<>();
			structure.setData(productDao.saveProduct(product));// adding Product
			merchantDao.saveMerchant(merchant);// Updateing merchant
			structure.setMessage("Product Added");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return ResponseEntity.status(HttpStatus.CREATED).body(structure);
		}
		throw new MerchantNotFoundException("Can not add Product as Merchant Id is Invalid");
	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product) {
		Optional<Product> recProduct = productDao.findById(product.getId());
		ResponseStructure<Product> structure = new ResponseStructure<>();
		if (recProduct.isPresent()) {
			Product dbProduct = recProduct.get();
			dbProduct.setName(product.getName());
			dbProduct.setBrand(product.getBrand());
			dbProduct.setCategory(product.getCategory());
			dbProduct.setCost(product.getCost());
			dbProduct.setDescription(product.getDescription());
			dbProduct.setImg_url(product.getImg_url());
			structure.setData(productDao.saveProduct(dbProduct));
			structure.setMessage("Product Updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
		throw new ProductNotFoundException("Cannot Update Product as Id is Invalid");
	}

}
