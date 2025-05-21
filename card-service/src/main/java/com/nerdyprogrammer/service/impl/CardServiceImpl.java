package com.nerdyprogrammer.service.impl;


import com.nerdyprogrammer.constants.CardConstants;
import com.nerdyprogrammer.dto.CardDto;
import com.nerdyprogrammer.entity.Card;
import com.nerdyprogrammer.exception.ResourceNotFoundException;
import com.nerdyprogrammer.mapper.CardMapper;
import com.nerdyprogrammer.repo.CardRepository;
import com.nerdyprogrammer.service.ICardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardServiceImpl implements ICardService {
    private CardRepository cardRepository;

    @Override
    public void createCard(String mobileNumber) {
        Optional<Card> optionalCards = cardRepository.findByMobileNumber(mobileNumber);
        if (optionalCards.isPresent()) {
            throw new RuntimeException("Card already exists for this mobile number" + " " + mobileNumber);
        }
        cardRepository.save(createNewCard(mobileNumber));
    }

    private Card createNewCard(String mobileNumber) {
        Card card = new Card();
        long randomCardNumber = 1000_0000_0000_0000L + new Random().nextInt(900_000_000);
        card.setMobileNumber(mobileNumber);
        card.setCardNumber(String.valueOf(randomCardNumber));
        card.setCardType(CardConstants.CREDIT_CARD);
        card.setTotalLimit(CardConstants.NEW_CARD_LIMIT);
        card.setAmountUsed(0);
        card.setAvailableAmount(CardConstants.NEW_CARD_LIMIT);
        return card;
    }

    public CardDto getCardDetails(String mobileNumber) {
        Card card = cardRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
        return CardMapper.mapToCardDto(card, new CardDto());

    }

    @Override
    public boolean updateCard(CardDto cardDto) {
        Card card = cardRepository.findByCardNumber(cardDto.getCardNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Card", "cardNumber", cardDto.getCardNumber()));
        CardMapper.mapToCard(cardDto, card);
        cardRepository.save(card);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Card card = cardRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
        cardRepository.deleteById(card.getCardId());
        return true;
    }

}
