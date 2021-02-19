public class Main {

    public static void main(String[] args) {
        String choice = "";
        World myWorld = new World();
        System.out.println("A quel mode de jeu désirez vous jouer:");
        System.out.println("Scénario\nBac à sable");

        if(choice == "Scénario")
        {
            System.out.println("A quel scénario voulez vous jouer:");
            myWorld.getScenarios();
            myWorld.executeActions();
        }
        else if(choice == "Bac à sable")
        {
            //créer les données à la main
            myWorld.executeActions();
        }
        else
        {

        }
    }
}
