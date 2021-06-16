package View.Menu;

import Controller.Regex;

import java.util.regex.Matcher;

public class ImportExport extends Menu {
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
            importCard(matcher.group(1));
        } else if (Regex.getMatcher(input, Regex.exportCard).find()) {
            exportCard(matcher.group(1));
        } else {
            System.out.println("invalid command!");
            this.execute();
        }
    }

    private void exportCard(String group) {
    }

    private void importCard(String group) {
    }

    private String editSpaces(String string) {
        return string.replaceAll("(\\s)+", " ");
    }

}
