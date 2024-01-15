package com.projectteamspring.www.service;

import java.util.List;

import com.projectteamspring.www.domain.PayMentVO;
import com.projectteamspring.www.domain.RefundVO;

public interface PaymentService {

	int successPayment(PayMentVO pmvo);

	int deletePayments(PayMentVO pmvo);

	int insertPayment(PayMentVO pmvo);

	List<PayMentVO> selectMyPayment(String email);

	PayMentVO setPayment(String impUid);

	int refundApplication(RefundVO rvo);

	List<RefundVO> getList();

	RefundVO getRefund(String merchantUid);

	int refundSes(String uid);

}
