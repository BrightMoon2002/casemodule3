package com.group4.service.financial.Revenue;

import com.group4.model.financial.Revenue;
import com.group4.service.IService;

import java.util.List;

public interface IRevenueService extends IService<Revenue> {
    public List<Revenue> findAllByAccountId(int account_id);
    public List<Revenue> findAllNotByAccountId(int account_id);
}
