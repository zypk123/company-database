package cy.its.vehTrack.domain.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cy.its.vehTrack.domain.repository.ICollAnalyzeRepository;
import cy.its.vehTrack.domain.service.ICollAnalyzeService;

@Service
public class CollAnalyzeService implements ICollAnalyzeService {
	@Autowired
	ICollAnalyzeRepository repository;

	@Override
	public JSONObject findCollAnalyResList(String query) throws FileNotFoundException, IOException, Exception {
		// TODO Auto-generated method stub
		return this.repository.findCollAnalyResList(query);
	}

}
