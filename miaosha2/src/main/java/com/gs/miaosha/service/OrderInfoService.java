package com.gs.miaosha.service;

import com.gs.miaosha.entity.OrderInfo;
import com.gs.miaosha.entity.User;

public interface OrderInfoService {

	OrderInfo createOrder(User user ,long id);

}
