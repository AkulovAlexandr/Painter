package by.painter.view.userinterface;

public enum Theme {

    LIGHT_THEME("Светлая тема"), DARK_THEME("Тёмная тема");
    private final String title;

    Theme(String title) {
        this.title = title;
    }

    public static Theme fromString(String value) {
        if (value != null) {
            for (Theme theme : Theme.values()) {
                if (theme.title.equalsIgnoreCase(value)) {
                    return theme;
                }
            }
        }
        throw new IllegalArgumentException("No such value");
    }


    public String getTitle() {
        return title;
    }
}
