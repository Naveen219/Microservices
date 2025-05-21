package com.nerdyprogrammer.service;

import com.nerdyprogrammer.dto.CardDto;

public interface ICardService {
    /**
     *
     * @param mobileNumber - Mobile number of the user
     */
    void createCard(String mobileNumber);

    /**
     *
     * @param cardDto - Card details to be updated
     *  @return - true if card is updated successfully, false otherwise
     */
    boolean updateCard(CardDto cardDto);

    /**
     *
      * @param mobileNumber - Mobile number of the user
     * @return - true if card is deleted successfully, false otherwise
     */
    boolean deleteCard(String mobileNumber);

    /**
     * @param mobileNumber = Mobile number of the user
     * @return - Card details of the user for the given mobile number
     */
    CardDto getCardDetails(String mobileNumber);

}