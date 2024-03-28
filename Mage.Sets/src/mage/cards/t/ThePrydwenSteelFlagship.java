package mage.cards.t;

import java.util.*;

import mage.MageInt;
import mage.abilities.common.EntersBattlefieldControlledTriggeredAbility;
import mage.abilities.effects.common.CreateTokenEffect;
import mage.constants.*;
import mage.abilities.keyword.FlyingAbility;
import mage.abilities.keyword.CrewAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.filter.FilterPermanent;
import mage.filter.predicate.permanent.TokenPredicate;
import mage.game.permanent.token.ThePrydwenSteelFlagshipToken;

/**
 *
 * @author justinjohnson14
 */
public final class ThePrydwenSteelFlagship extends CardImpl {

    private static FilterPermanent filter = new FilterPermanent();

    static{
        filter.add(TokenPredicate.FALSE);
        filter.add(CardType.ARTIFACT.getPredicate());
    }

    public ThePrydwenSteelFlagship(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{4}{W}{W}");
        
        this.supertype.add(SuperType.LEGENDARY);
        this.subtype.add(SubType.VEHICLE);
        this.power = new MageInt(6);
        this.toughness = new MageInt(6);

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        // Whenever another nontoken artifact enters the battlefield under your control, create a 2/2 white Human Knight creature token with "This creature gets +2/+2 as long as an artifact entered the battlefield under your control this turn."
        this.addAbility(new EntersBattlefieldControlledTriggeredAbility(Zone.BATTLEFIELD, new CreateTokenEffect(new ThePrydwenSteelFlagshipToken()), filter, false));

        // Crew 2
        this.addAbility(new CrewAbility(2));

    }

    private ThePrydwenSteelFlagship(final ThePrydwenSteelFlagship card) {
        super(card);
    }

    @Override
    public ThePrydwenSteelFlagship copy() {
        return new ThePrydwenSteelFlagship(this);
    }
}


