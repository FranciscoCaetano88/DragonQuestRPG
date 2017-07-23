package org.academiadecodigo.www.game.story;

public class StoryLineParser {

    private String[] line;

    /**
     * Splits a String
     * @param text    String type
     */
    public void split(String text) {
        this.line = text.split("\n");

    }

    /**
     * Returns a line
     * @return          String type
     */
    public String getLineFrom(int number) {

        if (line == null) {
            return null;
        }

        return line[number];
    }

    public String[] getLine() {
        return line;
    }
}
