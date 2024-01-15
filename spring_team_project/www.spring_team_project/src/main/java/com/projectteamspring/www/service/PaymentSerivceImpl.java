package com.projectteamspring.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projectteamspring.www.domain.PayMentVO;
import com.projectteamspring.www.domain.RefundVO;
import com.projectteamspring.www.repository.MyCartDAO;
import com.projectteamspring.www.repository.PaymentDAO;
import com.projectteamspring.www.repository.StorageCartDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentSerivceImpl implements PaymentService {
	
	@Inject
	private PaymentDAO pdao;
	
	@Inject
	private MyCartDAO mcdao;
	
	@Inject
	private StorageCartDAO scdao;
	
	
	@Override
	public int insertPayment(PayMentVO pmvo) {
		log.info("insertPayment in PaymentServiceImpl");
		return pdao.insertPayment(pmvo);
	}

	@Override
	public List<PayMentVO> selectMyPayment(String email) {
		log.info("selectMyPayment in PaymentServiceImpl");
		return pdao.selectMyPayment(email);
	}
	@Transactional
	@Override
	public PayMentVO setPayment(String impUid) {
		log.info("setPayment in PaymentServiceImpl");
		return pdao.setPayment(impUid);
	}

	@Override
	public int deletePayments(PayMentVO pmvo) {
		log.info("deletePayments in PaymentServiceImpl");
		return pdao.deletePayments(pmvo);
	}

	@Transactional
	@Override
	public int successPayment(PayMentVO pmvo) {
		int isUp = mcdao.buyUp(pmvo);
		isUp = scdao.buyUp(pmvo);
		if(isUp>0) {
			int isOk = pdao.buyUp(pmvo);
			return isOk;
		}else {
			return 0;
		}
	}

	@Override
	public int refundApplication(RefundVO rvo) {
		log.info("refundApplication in PaymentServiceImpl");
		pdao.updateStatus(rvo);
		return pdao.refundApplication(rvo);
	}

	@Override
	public List<RefundVO> getList() {
		log.info("getList in PaymentServiceImpl");
		return pdao.getList();
	}

	@Override
	public RefundVO getRefund(String merchantUid) {
		log.info("getRefund in PaymentServiceImpl");
		return pdao.getRefund(merchantUid);
	}
	
	@Transactional
	@Override
	public int refundSes(String uid) {
		log.info("refundSes in PaymentServiceImpl");
		pdao.updateRefundData(uid);
		return pdao.refundSes(uid);
	}
}