import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

public class ChatCommand {
    public void help() {
        System.out.println("Liste des commandes disponibles :");
        System.out.println("1. Écrit 'YAQUI' sans les guillemets pour voir la liste des utilisateurs connectés ");
        System.out.println("2. Écrit 'QUIT' pour te deconnecter. ");
        System.out.println("3. Écrit 'DEPRESSION' pour voir mon état après 2 jours passer sur ce programme.");
        System.out.println("3. Écrit 'HELP' pour voir la liste des commandes.");
    }

    public void depression() {

        String[] links = {
                "https://giphy.com/gifs/the-simpsons-depressed-milhouse-GIvajz0TlE316",
                "https://giphy.com/gifs/rain-depressed-joey-E2d2tsgz7iHo4",
                "https://giphy.com/gifs/family-guy-suicide-vkwAeqMEUSaoU",
                "https://giphy.com/gifs/monday-the-it-crowd-workplace-c6DIpCp1922KQ"
        };

        Random rand = new Random();
        int randomIndex = rand.nextInt(links.length);
        String randomLink = links[randomIndex];

        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(randomLink));
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
