import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int choice;
        boolean keepGoing = true;
        World myWorld = new World();
        do{
                System.out.println("A quel mode de jeu désirez vous jouer:");
                System.out.println("1) Scénario\n2) Bac à sable\n3) Quitter le jeu");
                Scanner input = new Scanner(System.in);
                try
                {
                    choice = input.nextInt();
                }catch (InputMismatchException exception)
                {
                    System.out.println("Je n'ai pas compris, veuillez réessayer.");
                    continue;
                }


                if(choice == 1)
                {
                    System.out.println("Vous avez choisi de jouer au mode scénario");
                    ScenarioSelector scenarioSelector = new ScenarioSelector();
                    String filePath = scenarioSelector.selectScenario();
                    if(filePath == null)
                    {
                        continue;
                    }
                    else
                    {
                        myWorld.createDataWithJSON(filePath);
                        myWorld.executeActions();
                    }

                }
                else if(choice == 2)
                {
                    //créer les données à la main
                    myWorld.executeActions();
                }
                else if(choice == 3)
                {
                    System.out.println("Vous avez choisi de quitter le jeu.\nAu revoir, et à bientôt!");
                    break;
                }
                else
                {
                    System.out.println("Je n'ai pas compris votre choix");
                }
        }while(keepGoing);

    }
}
