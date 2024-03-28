package mage.game.permanent.token;

import mage.MageInt;
import mage.MageObjectReference;
import mage.abilities.Ability;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.condition.common.PermanentsOnTheBattlefieldCondition;
import mage.abilities.decorator.ConditionalContinuousEffect;
import mage.abilities.effects.common.continuous.BoostControlledEffect;
import mage.constants.*;
import mage.filter.FilterPermanent;
import mage.filter.predicate.permanent.EnteredThisTurnPredicate;
import mage.game.Game;
import mage.game.events.EntersTheBattlefieldEvent;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;
import mage.watchers.Watcher;

import java.util.*;

/**
 * @author justinjohnson14
 */
public final class ThePrydwenSteelFlagshipToken extends TokenImpl {

    private static final FilterPermanent filter = new FilterPermanent();

    static{
        filter.add(EnteredThisTurnPredicate.instance);
        filter.add(TargetController.YOU.getPlayerPredicate());
        filter.add(CardType.ARTIFACT.getPredicate());
    }

    public ThePrydwenSteelFlagshipToken() {
        super("Human Knight Token", "create a 2/2 white Human Knight creature token with “This creature gets +2/+2 as long as an artifact entered the battlefield under your control this turn");
        cardType.add(CardType.CREATURE);
        color.setWhite(true);
        subtype.add(SubType.HUMAN);
        subtype.add(SubType.KNIGHT);
        power = new MageInt(2);
        toughness = new MageInt(2);
        Ability ability = new SimpleStaticAbility(new ConditionalContinuousEffect(
                new BoostControlledEffect(2,2, Duration.WhileOnBattlefield),
                new PermanentsOnTheBattlefieldCondition(filter), "{this} has +2/+2 as long as " +
                "an artifact entered the battlefield under you control this turn"
        ));
        this.addAbility(ability);
    }

    private ThePrydwenSteelFlagshipToken(final ThePrydwenSteelFlagshipToken token) {
        super(token);
    }

    @Override
    public ThePrydwenSteelFlagshipToken copy() {
        return new ThePrydwenSteelFlagshipToken(this);
    }
}

class ThePrydwenSteelFlagshipWatcher extends Watcher {

    private final Map<UUID, Set<MageObjectReference>> map = new HashMap<>();

    ThePrydwenSteelFlagshipWatcher() {
        super(WatcherScope.GAME);
    }

    @Override
    public void watch(GameEvent event, Game game) {
        if (event.getType() != GameEvent.EventType.ENTERS_THE_BATTLEFIELD) {
            return;
        }
        Permanent permanent = ((EntersTheBattlefieldEvent) event).getTarget();
        if (permanent.hasCardTypeForDeckbuilding(CardType.ARTIFACT)) {
            map.computeIfAbsent(permanent.getControllerId(), x -> new HashSet<>())
                    .add(new MageObjectReference(permanent, game));
        }
    }

    @Override
    public void reset() {
        super.reset();
        map.clear();
    }

    static boolean checkPermanent(Game game, Ability source) {
        return game
                .getState()
                .getWatcher(ThePrydwenSteelFlagshipWatcher.class)
                .map
                .getOrDefault(source.getControllerId(), Collections.emptySet())
                .stream()
                .anyMatch(mor -> !mor.refersTo(source, game));
    }
}
