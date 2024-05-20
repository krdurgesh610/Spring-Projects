package org.jsp.merchantbootapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantbootapp.dao.MerchantDao;
import org.jsp.merchantbootapp.dto.Merchant;
import org.jsp.merchantbootapp.dto.ResponseStructure;
import org.jsp.merchantbootapp.exception.MerchantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {
	@Autowired
	private MerchantDao merchantDao;

	public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(Merchant merchant) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		structure.setMessage("Merchant Saved");
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setData(merchantDao.saveMerchant(merchant));
		return ResponseEntity.status(HttpStatus.CREATED).body(structure);
	}

	public ResponseEntity<ResponseStructure<Merchant>> findById(int id) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();

		Optional<Merchant> recMerchant = merchantDao.findById(id);

		if (recMerchant.isPresent()) {
			structure.setMessage("Merchant Found");
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setData(recMerchant.get());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new MerchantNotFoundException("Invalid Merchant Id");
	}

	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(Merchant merchant) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantDao.findById(merchant.getId());
		if (recMerchant.isPresent()) {
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			Merchant dbMerchant = recMerchant.get();
			dbMerchant.setEmail(merchant.getEmail());
			dbMerchant.setGst_number(merchant.getGst_number());
			dbMerchant.setName(merchant.getName());
			dbMerchant.setPassword(merchant.getPassword());
			dbMerchant.setPhone(merchant.getPhone());
			structure.setData(dbMerchant);
			structure.setMessage("Merchant Updated with Given Id");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
		throw new MerchantNotFoundException("Merchant Not Update Id is Invalid");
	}

	public List<Merchant> findAll() {
		return merchantDao.findAll();
	}

	public String deleteMerchnat(int id) {
		Optional<Merchant> recMerchant = merchantDao.findById(id);
		if (recMerchant.isPresent()) {
			merchantDao.delete(id);
			return "Merchant Deleted";
		}

		return "Cannot Delete Merchant as Id is Invalid";
	}

	public ResponseEntity<ResponseStructure<List<Merchant>>> findByName(String name) {
		ResponseStructure<List<Merchant>> structure = new ResponseStructure<>();
		List<Merchant> merchants = merchantDao.findByName(name);
		structure.setData(merchants);
		if (merchants.isEmpty()) {
			structure.setMessage("No Merchant Found With entered Name");
			structure.setStatusCode(HttpStatus.NO_CONTENT.value());
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(structure);
		}
		structure.setMessage("List of Merchant with entered Name");
		structure.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}

	public ResponseEntity<ResponseStructure<Merchant>> findByPhone(long phone) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantDao.findByPhone(phone);
		if (recMerchant.isPresent()) {
			structure.setMessage("Merchant Found");
			structure.setData(recMerchant.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new MerchantNotFoundException("Invalid Phone number");
	}

	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(long phone, String password) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantDao.verifyMerchant(phone, password);
		if (recMerchant.isPresent()) {
			structure.setMessage("Merchant Verifyed");
			structure.setData(recMerchant.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new MerchantNotFoundException("can not verify Invalid Phone number and Password");
	}

	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(String email, String password) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantDao.verifyMerchant(email, password);
		if (recMerchant.isPresent()) {
			structure.setMessage("Merchnat Verify by given Email Id");
			structure.setData(recMerchant.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new MerchantNotFoundException("can not verify Invalid Email Id and Password");
	}

	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(int id, String password) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantDao.verifyMerchant(id, password);
		if (recMerchant.isPresent()) {
			structure.setMessage("Merchant Verify by this Id");
			structure.setData(recMerchant.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new MerchantNotFoundException("can not verify Invalid Id and Password");
	}

	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchantWithGst(String gst_number, String password) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		Optional<Merchant> recMerchant = merchantDao.verifyMerchant1(gst_number, password);
		if (recMerchant.isPresent()) {
			structure.setMessage("Merchant Verify with Given Gst Number");
			structure.setData(recMerchant.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new MerchantNotFoundException("can not verify Invalid Gst number and Password");
	}

}
