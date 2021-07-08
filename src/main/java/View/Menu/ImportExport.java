package View.Menu;

import Controller.JsonController;
import Controller.Regex;
import Model.Monster;
import Model.Spell;
import Model.Trap;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;

public class ImportExport extends Menu {
    public static Stage mainStage;

    public ImportExport(Menu parentMenu) {
        super("Import/Export Menu", parentMenu);
    }

    @Override
    public void execute() {
        String input;
        Matcher matcher;
        input = scanner.nextLine().trim();
        input = editSpaces(input);

        if (Regex.getMatcher(input, Regex.menuExit).find()) {
            this.menuExit();
        } else if ((matcher = Regex.getMatcher(input, Regex.menuEnter)).find()) {
            this.menuEnter(matcher.group(1));
            this.execute();
        } else if (Regex.getMatcher(input, Regex.showCurrentMenu).find()) {
            this.showName();
            this.execute();
        } else if (Regex.getMatcher(input, Regex.userLogout).find()) {
            this.logoutUser();
        } else if ((matcher = Regex.getMatcher(input, Regex.importCard)).find()) {
            try {
                importCard(matcher.group(1));
            } catch (Exception ignored) {
            }
            this.execute();
        } else if ((matcher = Regex.getMatcher(input, Regex.exportCard)).find()) {
            try {
                exportCard(matcher.group(1));
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.execute();
        } else {
            System.out.println("invalid command!");
            this.execute();
        }
    }

    private void exportCard(String group) throws IOException {
        System.out.println("please Enter Card Type :");
        while (true) {
            String type = scanner.nextLine().trim();
            if (!type.equalsIgnoreCase("monster") && !type.equalsIgnoreCase("spell")
                    && !type.equalsIgnoreCase("trap")) {
                System.out.println("Wrong Type ! Please Try again ...");
                continue;
            }
            if (JsonController.writeCard(group, type)) {
                System.out.println("Exported Successfully !");
                break;
            } else {
                this.execute();
            }
        }
    }

    private void importCard(String group) throws Exception {
        System.out.println("please Enter Card Type :");
        while (true) {
            String type = scanner.nextLine().trim();
            if (!type.equalsIgnoreCase("monster") && !type.equalsIgnoreCase("spell")
                    && !type.equalsIgnoreCase("trap")) {
                System.out.println("Wrong Type ! Please Try again ...");
                continue;
            }
            if (type.equalsIgnoreCase("monster")) {
                Monster monster = (Monster) JsonController.readCard(group, type);
                if (monster == null) {
                    System.out.println("This Card Doesn't Exist In DataBase !");
                    this.execute();
                    return;
                }
                loggedUser.getAllCards().add(monster);
            } else if (type.equalsIgnoreCase("spell")) {
                Spell spell = (Spell) JsonController.readCard(group, type);
                if (spell == null) {
                    System.out.println("This Card Doesn't Exist In DataBase !");
                    this.execute();
                    return;
                }
                loggedUser.getAllCards().add(spell);
            } else {
                Trap trap = (Trap) JsonController.readCard(group, type);
                if (trap == null) {
                    System.out.println("This Card Doesn't Exist In DataBase !");
                    this.execute();
                    return;
                }
                loggedUser.getAllCards().add(trap);
            }
            System.out.println("Imported Successfully !");
            break;
        }
    }

    private String editSpaces(String string) {
        return string.replaceAll("(\\s)+", " ");
    }

}
