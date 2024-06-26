package mage.cards.a;

import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.effects.common.AttachEffect;
import mage.abilities.effects.common.continuous.BoostEnchantedEffect;
import mage.abilities.effects.common.continuous.GainAbilityAttachedEffect;
import mage.abilities.keyword.EnchantAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.*;
import mage.target.TargetPermanent;
import mage.target.common.TargetCreaturePermanent;

import java.util.UUID;

/**
 * @author JayDi85
 */

public final class AngelicReward extends CardImpl {

    public AngelicReward(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ENCHANTMENT}, "{3}{W}{W}");
        this.subtype.add(SubType.AURA);

        // Enchant creature
        TargetPermanent auraTarget = new TargetCreaturePermanent();
        this.getSpellAbility().addTarget(auraTarget);
        this.getSpellAbility().addEffect(new AttachEffect(Outcome.BoostCreature));
        this.addAbility(new EnchantAbility(auraTarget));

        // Enchanted creature gets +3/+3 and has flying.
        SimpleStaticAbility ability = new SimpleStaticAbility(new BoostEnchantedEffect(3, 3));
        ability.addEffect(new GainAbilityAttachedEffect(FlyingAbility.getInstance(), AttachmentType.AURA).setText("and has flying"));
        this.addAbility(ability);
    }

    private AngelicReward(final AngelicReward card) {
        super(card);
    }

    @Override
    public AngelicReward copy() {
        return new AngelicReward(this);
    }
}
