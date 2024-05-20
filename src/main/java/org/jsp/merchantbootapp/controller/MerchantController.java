package org.jsp.merchantbootapp.controller;

import java.util.List;

import org.jsp.merchantbootapp.dto.Merchant;
import org.jsp.merchantbootapp.dto.ResponseStructure;
import org.jsp.merchantbootapp.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {
	@Autowired
	private MerchantService merchantService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(@RequestBody Merchant merchant) {
		return merchantService.saveMerchant(merchant);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Merchant>> findById(@PathVariable(name = "id") int id) {
		return merchantService.findById(id);
	}

	@GetMapping
	public List<Merchant> findAll() {
		return merchantService.findAll();
	}

	@DeleteMapping("/{id}")
	public String deleteMerchnat(@PathVariable(name = "id") int id) {
		return merchantService.deleteMerchnat(id);
	}

	@GetMapping("/find-by-name/{name}")
	public ResponseEntity<ResponseStructure<List<Merchant>>> findByName(@PathVariable(name = "name") String name) {
		return merchantService.findByName(name);
	}

	@GetMapping("/find-by-phone/{phone}")
	public ResponseEntity<ResponseStructure<Merchant>> findByPhone(@PathVariable(name = "phone") long phone) {
		return merchantService.findByPhone(phone);
	}

	@PostMapping("/verify-by-phone")
	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(@RequestParam(name = "phone") long phone,
			@RequestParam(name = "password") String password) {
		return merchantService.verifyMerchant(phone, password);
	}

	@PostMapping("/verify-by-email")
	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(@RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password) {
		return merchantService.verifyMerchant(email, password);
	}

	@PostMapping("/verify-by-id")
	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(@RequestParam(name = "id") int id,
			@RequestParam(name = "password") String password) {
		return merchantService.verifyMerchant(id, password);
	}

	@PostMapping("/find-by-gst")
	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchantWithGst(
			@RequestParam(name = "gst_number") String gst_number, @RequestParam(name = "password") String password) {
		return merchantService.verifyMerchantWithGst(gst_number, password);
	}

}
