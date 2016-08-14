package pokemoncli.navigation;

import org.junit.Test;
import pokemoncli.Page;
import pokemoncli.consoleUI.Script;
import pokemoncli.pages.*;
import pokemoncli.ui.DisplayFake;
import pokemoncli.ui.InputFake;
import pokemonmanager.Pokemon;
import pokemonmanager.search.PokemonFinder;
import pokemonmanager.storage.PokemonManager;
import pokemonmanager.testfakes.SearchFake;
import pokemonmanager.testfakes.StorageFake;

import static org.junit.Assert.assertEquals;

public class NavigatorTest {

    private Navigator navigator = new Navigator(new DisplayFake(new Script()), new InputFake(), new PokemonFinder(new SearchFake()), new PokemonManager(new StorageFake()));

    @Test
    public void navigatesToMenuPage() {
        Page page = navigator.redirect(Action.MENU, Pokemon.NULL);
        assertEquals(page.getClass(), MenuPage.class);
    }

    @Test
    public void navigatesToSearchPage() {
        Page page = navigator.redirect(Action.SEARCH, Pokemon.NULL);
        assertEquals(page.getClass(), SearchPage.class);
    }

    @Test
    public void navigatesToManagePage() {
        Page page = navigator.redirect(Action.MANAGE, Pokemon.NULL);
        assertEquals(page.getClass(), ManagementPage.class);
    }

    @Test
    public void navigatesToPokemonDetailPage() {
        Page page = navigator.redirect(Action.SHOWPOKEMON, Pokemon.NULL);
        assertEquals(page.getClass(), PokemonDetailPage.class);
    }

    @Test
    public void navigatesToCatchPage() {
        Page page = navigator.redirect(Action.CATCH, Pokemon.NULL);
        assertEquals(page.getClass(), CatchPage.class);
    }

    @Test
    public void navigatesToFreePokemonPage() {
        Page page = navigator.redirect(Action.FREE, Pokemon.NULL);
        assertEquals(page.getClass(), FreePage.class);
    }
}
