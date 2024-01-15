package com.projectteamspring.www.repository;

import java.util.List;

import com.projectteamspring.www.domain.PayMentVO;
import com.projectteamspring.www.domain.RefundVO;

public interface PaymentDAO {

	int insertPayment(PayMentVO pmvo);

	List<PayMentVO> selectMyPayment(String email);

	PayMentVO setPayment(String impUid);
	
	void updateStatus(RefundVO rvo);

	int deletePayments(PayMentVO pmvo);

	int buyUp(PayMentVO pmvo);

	int refundApplication(RefundVO rvo);

	List<RefundVO> getList();

	RefundVO getRefund(String uid);

	int refundSes(String uid);

	void updateRefundData(String uid);

}
