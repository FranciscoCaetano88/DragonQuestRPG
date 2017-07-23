package org.academiadecodigo.www.test;

import org.academiadecodigo.www.FileManager;
import org.academiadecodigo.www.game.story.StoryLineParser;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by oem on 22-07-2017.
 */
public class StoryLineParserTest {

    public static void main(String[] args) {

        int storyIndex = 0;

        StoryLineParser storyParser = new StoryLineParser();

        FileManager fileManager = new FileManager();

        String home = "";

        try {
            home = fileManager.read("home");
        } catch (IOException e) {
            System.err.println("Failed to read file " + e.getMessage());
        }

        storyParser.split(home);

        Scanner sc = new Scanner(System.in);
        String s;

        System.out.println("Press [ENTER] to continue");
        while (storyIndex < storyParser.getLine().length) {
            System.out.print(storyParser.getLineFrom(storyIndex++));
            s = sc.nextLine();

            if (storyIndex == 10) {
                while (!s.equals("/commands")) {
                    s = sc.nextLine();

                }

            }

            if (storyIndex == 14) {
                while (!s.equals("ls")) {
                    s = sc.nextLine();
                }
            }

            if (storyIndex == 21) {
                while ((!s.equals("interact") && (!s.equals("pick") && (!s.equals("attack"))))) {
                    s = sc.nextLine();
                }

            }

        }

    }

}
