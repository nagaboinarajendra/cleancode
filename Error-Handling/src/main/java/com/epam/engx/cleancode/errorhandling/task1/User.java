package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.persistence.AddressException;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Address;

import java.util.List;

public class User {

    private AddressDao addressDao;
    private OrderDao orderDao;
    private String userId;
    private Address preferredAddress;

    public Address getPreferredAddress() {
        try {
            setPreferredAddress(getAvailableAddress());
        } catch (AddressException e) {
            setPreferredAddress(addressDao.getHomeAddress(userId));
        }
        return preferredAddress;
    }

    private Address getAvailableAddress() {
        List<Address> deliveryAddresses = addressDao.getDeliveryAddresses(userId);
        List<Address> orderAddresses = orderDao.getOrderAddresses(userId);
        if (isNotEmptyList(deliveryAddresses))
            return deliveryAddresses.get(0);
        else if (isNotEmptyList(orderAddresses))
            return orderAddresses.get(orderAddresses.size() - 1);
        else
            return addressDao.getHomeAddress(userId);
    }

    private boolean isNotEmptyList(List<Address> addressList) {
        return !addressList.isEmpty();
    }

    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPreferredAddress(Address defaultAddress) {
        this.preferredAddress = defaultAddress;
    }
}
