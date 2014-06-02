/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */
package mage.sets.ajanivsnicolbolas;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.GainLifeControllerTriggeredAbility;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.effects.common.counter.AddCountersSourceEffect;
import mage.cards.CardImpl;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.Rarity;
import mage.counters.CounterType;
import mage.game.Game;

/**
 *
 * @author LevelX2
 */
public class AgelessEntity extends CardImpl<AgelessEntity> {

    public AgelessEntity(UUID ownerId) {
        super(ownerId, 18, "Ageless Entity", Rarity.RARE, new CardType[]{CardType.CREATURE}, "{3}{G}{G}");
        this.expansionSetCode = "DDH";
        this.subtype.add("Elemental");

        this.color.setGreen(true);
        this.power = new MageInt(4);
        this.toughness = new MageInt(4);

        // Whenever you gain life, put that many +1/+1 counters on Ageless Entity.
        this.addAbility(new GainLifeControllerTriggeredAbility(new AgelessEntityEffect(), false, true));
    }

    public AgelessEntity(final AgelessEntity card) {
        super(card);
    }

    @Override
    public AgelessEntity copy() {
        return new AgelessEntity(this);
    }
}

class AgelessEntityEffect extends OneShotEffect {

    public AgelessEntityEffect() {
        super(Outcome.Benefit);
        this.staticText = "put that many +1/+1 counters on this creature";
    }

    public AgelessEntityEffect(final AgelessEntityEffect effect) {
        super(effect);
    }

    @Override
    public AgelessEntityEffect copy() {
        return new AgelessEntityEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        int lifeGained = (Integer) this.getValue("gainedLife");
        if (lifeGained > 0) {
            return new AddCountersSourceEffect(CounterType.P1P1.createInstance(lifeGained)).apply(game, source);
        }
        return false;
    }
}
