package com.vod.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vod.model.Category;
import com.vod.model.Country;
import com.vod.model.Director;
import com.vod.model.Starring;
import com.vod.service.CategoryService;
import com.vod.service.CountryService;
import com.vod.service.DirectorService;
import com.vod.service.StarringService;

@Controller
public class InitDBController {
	@Autowired
	private CategoryService cateService;
	@Autowired
	private CountryService countryService;
	@Autowired
	private StarringService starService;
	@Autowired
	private DirectorService dService;

	@RequestMapping(value = "/init/data/create", method = RequestMethod.GET)
	public String doCreateData(Map<String, Object> map) {
		if (addCate() && addCountry() && addStarring() && addDirector()) {
			map.put("message", "Thêm dữ liệu thành công");
		} else {
			map.put("message", "Thêm dữ liệu không thành công");
		}
		return "init";
	}

	private boolean addCate() {
		Category cate = new Category();
		cate.setName("Tình Cảm");
		Category cate2 = new Category();
		cate2.setName("Hành Động");
		Category cate3 = new Category();
		cate3.setName("Tâm Lý");
		Category cate4 = new Category();
		cate4.setName("Hoạt Hình");
		Category cate5 = new Category();
		cate5.setName("Phiêu Lưu");
		Category cate6 = new Category();
		cate6.setName("Chiến Tranh");
		Category cate7 = new Category();
		cate7.setName("Khoa Học Viễn Tưởng");
		Category cate8 = new Category();
		cate8.setName("Hài Hước");
		Category cate9 = new Category();
		cate9.setName("Thần Thoại");
		Category cate10 = new Category();
		cate10.setName("Võ Thuật");
		Category cate11 = new Category();
		cate11.setName("Tài Liệu");
		Category cate12 = new Category();
		cate12.setName("Hình Sự");
		Category cate13 = new Category();
		cate13.setName("Kình Dị");
		Category cate14 = new Category();
		cate14.setName("Cổ Trang");
		Category cate15 = new Category();
		cate15.setName("Gia Đình");

		if (cateService.deleleAll()) {
			try {
				cateService.add(cate);
				cateService.add(cate2);
				cateService.add(cate3);
				cateService.add(cate4);
				cateService.add(cate5);
				cateService.add(cate6);
				cateService.add(cate7);
				cateService.add(cate8);
				cateService.add(cate9);
				cateService.add(cate10);
				cateService.add(cate11);
				cateService.add(cate12);
				cateService.add(cate13);
				cateService.add(cate14);
				cateService.add(cate15);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}

	private boolean addCountry() {
		Country country = new Country();
		country.setName("Việt Nam");
		Country country2 = new Country();
		country2.setName("Nhật Bản");
		Country country3 = new Country();
		country3.setName("Hàn Quốc");
		Country country4 = new Country();
		country4.setName("Âu Mỹ");
		Country country5 = new Country();
		country5.setName("Trung Quốc");
		Country country6 = new Country();
		country6.setName("Thái Lan");
		Country country7 = new Country();
		country7.setName("Thái Lan");
		Country country8 = new Country();
		country8.setName("Anh");
		Country country9 = new Country();
		country9.setName("Đài Loan");
		Country country10 = new Country();
		country10.setName("Hồng Kông");
		Country country11 = new Country();
		country11.setName("Canada");
		Country country12 = new Country();
		country12.setName("Quốc Gia Khác");
		Country country13 = new Country();
		country13.setName("Pháp");
		Country country14 = new Country();
		country14.setName("Ấn Độ");
		Country country15 = new Country();
		country15.setName("Úc");

		if (countryService.deleleAll()) {
			try {
				countryService.add(country);
				countryService.add(country2);
				countryService.add(country3);
				countryService.add(country4);
				countryService.add(country5);
				countryService.add(country6);
				countryService.add(country7);
				countryService.add(country8);
				countryService.add(country9);
				countryService.add(country10);
				countryService.add(country11);
				countryService.add(country12);
				countryService.add(country13);
				countryService.add(country14);
				countryService.add(country15);
				return true;
			} catch (Exception e) {
				return false;
			}
		}

		return false;
	}

	private boolean addStarring() {
		Starring d = new Starring();
		d.setName("Chung Tử Đơn");
		d.setAvatar("chungtudon.JPG");
		d.setCountry("Trung Quốc");

		Starring d1 = new Starring();
		d1.setName("Lý Liên Kiệt");
		d1.setAvatar("lylienkiet.JPG");
		d1.setCountry("Trung Quốc");

		Starring d2 = new Starring();
		d2.setName("Jason Statham");
		d2.setAvatar("statham.JPG");
		d2.setCountry("Mỹ");

		Starring d3 = new Starring();
		d3.setName("Paul Walker");
		d3.setAvatar("paul.JPG");
		d3.setCountry("Mỹ");

		Starring d4 = new Starring();
		d4.setName("Vin Diesel");
		d4.setAvatar("diesel.JPG");
		d4.setCountry("Mỹ");

		if (starService.deleteAll()) {
			try {
				starService.add(d);
				starService.add(d1);
				starService.add(d2);
				starService.add(d3);
				starService.add(d4);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	private boolean addDirector() {
		Director d = new Director();
		d.setName("John Ford");
		d.setAvatar("joihnford.JPG");
		d.setCountry("United States");

		Director d2 = new Director();
		d2.setName("William Wyler");
		d2.setAvatar("wyler.JPG");
		d2.setCountry("United States");

		Director d3 = new Director();
		d3.setName("Lý An");
		d3.setAvatar("lyan.JPG");
		d3.setCountry("Trung Quốc");

		Director d4 = new Director();
		d4.setName("Ngô Thừa Ân");
		d4.setAvatar("ngothuaan.JPG");
		d4.setCountry("Trung Quốc");

		Director d5 = new Director();
		d5.setName("Lê Hoàng");
		d5.setAvatar("lehoang.JPG");
		d5.setCountry("Việt Nam");

		if (dService.deleteAll()) {
			try {
				dService.add(d);
				dService.add(d2);
				dService.add(d3);
				dService.add(d4);
				dService.add(d5);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
}
