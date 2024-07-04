package com.hdnguyen.dto.deck;

import com.hdnguyen.dto.auth.UserResponse;
import com.hdnguyen.dto.card.CardOfDeck;
import com.hdnguyen.dto.group.GroupResponse;
import com.hdnguyen.entity.CommonDeck;
import com.hdnguyen.entity.Deck;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DetailCommonDeckResponse {
    private Long id;
    private String description;
    private String name;
    private Date createAt;
    private List<CardOfDeck> cards;
    private Boolean isPublic;
    private Integer quantityClone;
    private UserResponse user;
    private Integer quantityCards;
    private GroupResponse group;

    public DetailCommonDeckResponse(CommonDeck commonDeck) {
        cards = new ArrayList<>();
        this.id = commonDeck.getId();
        this.description = commonDeck.getDescription();
        this.name = commonDeck.getName();
        this.createAt = commonDeck.getCreateAt();
        commonDeck.getCards().forEach(card -> {
            this.cards.add(new CardOfDeck(card));
        });
        this.quantityClone = commonDeck.getQuantityClone();
        this.quantityCards = commonDeck.getCards() == null ? 0 : commonDeck.getCards().size();
        this.group = GroupResponse.mapToGroupDto(commonDeck.getGroup());
    }
}
