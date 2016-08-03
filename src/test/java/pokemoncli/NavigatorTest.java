package pokemoncli;

import org.junit.Test;
import pkmncore.Pokemon;
import pkmncore.search.PokemonFinder;
import pkmncore.storage.PokemonManager;
import pkmncore.testfakes.SearchFake;
import pkmncore.testfakes.StorageFake;
import pokemoncli.consoleUI.Script;
import pokemoncli.navigation.Action;
import pokemoncli.navigation.Navigator;
import pokemoncli.pages.*;

import static org.junit.Assert.assertEquals;

public class NavigatorTest {

    @Test
    public void navigatesToMenuPage() {
        Navigator navigator = new Navigator(new DisplayFake(new Script()), new InputFake(), new PokemonFinder(new SearchFake()), new PokemonManager(new StorageFake()));
        Page page = navigator.redirect(Action.MENU, Pokemon.NULL);
        assertEquals(page.getClass(), MenuPage.class);
    }

    @Test
    public void navigatesToSearchPage() {
        Navigator navigator = new Navigator(new DisplayFake(new Script()), new InputFake(), new PokemonFinder(new SearchFake()), new PokemonManager(new StorageFake()));
        Page page = navigator.redirect(Action.SEARCH, Pokemon.NULL);
        assertEquals(page.getClass(), SearchPage.class);
    }

    @Test
    public void navigatesToManagePage() {
        Navigator navigator = new Navigator(new DisplayFake(new Script()), new InputFake(), new PokemonFinder(new SearchFake()), new PokemonManager(new StorageFake()));
        Page page = navigator.redirect(Action.MANAGE, Pokemon.NULL);
        assertEquals(page.getClass(), ManagementPage.class);
    }

    @Test
    public void navigatesToPokemonDetailPage() {
        Navigator navigator = new Navigator(new DisplayFake(new Script()), new InputFake(), new PokemonFinder(new SearchFake()), new PokemonManager(new StorageFake()));
        Page page = navigator.redirect(Action.SHOWPOKEMON, Pokemon.NULL);
        assertEquals(page.getClass(), PokemonDetailPage.class);
    }

    @Test
    public void navigatesToCatchPage() {
        Navigator navigator = new Navigator(new DisplayFake(new Script()), new InputFake(), new PokemonFinder(new SearchFake()), new PokemonManager(new StorageFake()));
        Page page = navigator.redirect(Action.CATCH, Pokemon.NULL);
        assertEquals(page.getClass(), CatchPage.class);
    }

    @Test
    public void navigatesToFreePokemonPage() {
        Navigator navigator = new Navigator(new DisplayFake(new Script()), new InputFake(), new PokemonFinder(new SearchFake()), new PokemonManager(new StorageFake()));
        Page page = navigator.redirect(Action.FREE, Pokemon.NULL);
        assertEquals(page.getClass(), FreePage.class);
    }
}
