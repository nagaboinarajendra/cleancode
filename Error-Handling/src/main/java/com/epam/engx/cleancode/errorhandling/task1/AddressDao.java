package com.epam.engx.cleancode.errorhandling.task1;

import com.epam.engx.cleancode.errorhandling.task1.persistence.AddressException;
import com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar.Address;

import java.util.List;

public interface AddressDao {

    Address getHomeAddress(String userId) throws AddressException;

    List<Address> getDeliveryAddresses(String userId);

}
