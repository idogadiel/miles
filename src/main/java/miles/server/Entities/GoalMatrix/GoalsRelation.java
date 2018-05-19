package miles.server.Entities.GoalMatrix;

public class GoalsRelation {

    public Double economy;
    public Double business;
    public Double firstClass;

    public String data;

    public enum GoalRelationType {
        ECONOMY("Economy"),
        BUSINESS("Buisness"),
        FIRST_CLASS("First_class");

        private String text;

        GoalRelationType(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }

        public static GoalRelationType fromString(String text) {
            for (GoalRelationType b : GoalRelationType.values()) {
                if (b.text.equalsIgnoreCase(text)) {
                    return b;
                }
            }
            return null;
        }
    }
}
