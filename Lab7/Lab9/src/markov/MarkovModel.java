package markov;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Random;

/**
 * This class represents an order k Markov model. After the model is created
 * with
 * 
 * <pre>
 * MarkovModel model = new MarkovModel(k);
 * </pre>
 * 
 * the model needs to be trained by adding prefix strings and their associated
 * suffixes.
 * 
 * For example, if the training string were "CS is great!" and k were 2, the
 * model could be trained by calling
 * 
 * <pre>
 * model.add("CS", ' ');
 * model.add("S ", 'i');
 * model.add(" i", 's');
 * model.add("is", ' ');
 * model.add("s ", 'g');
 * model.add(" g", 'r');
 * model.add("gr", 'e');
 * model.add("re", 'a');
 * model.add("ea", 't');
 * model.add("at", '!');
 * </pre>
 * 
 * After the model has been trained, it can generate strings. To generate a
 * string of length 20, use
 * 
 * <pre>
 * String output = model.generate("CS", 20, new SecureRandom());
 * </pre>
 * 
 * @author Stephen Checkoway, 2021
 *
 */
public class MarkovModel {
    /**
     * The State class represents a state in our Markov model.
     * 
     * For an order k MarkovModel, each state represents a prefix string of length k
     * and all the possible ways it could be extended to a string of length k+1 by
     * adding a single character suffix.
     * 
     * For example, if k is 3 and the prefix string is "the" then we might have the
     * following mappings
     * 
     * <pre>
     *     ' ' ↦ 9
     *     'a' ↦ 3
     *     'e' ↦ 1
     *     'r' ↦ 7
     * </pre>
     * 
     * which means that the model has been trained such that the string "the" was
     * followed by a space 9 times, followed by an 'a' 3 times, followed by an 'e' 1
     * time, and followed by an 'r' 7 times.
     * 
     * After the model has been trained, the <code>randomSuffix()</code> method will
     * return one of these 4 characters with probability proportional to the number
     * of times "the" was followed by that specific character.
     * 
     * Continuing our example, since "the" was seen in the training phase 20 times,
     * then <code>randomSuffix()</code> will return space with probability 9/20, 'a'
     * with probability 3/20, 'e' with probability 1/20, and 'r' with probability
     * 7/20.
     */
    private class State {
        /** The number of total transitions the model has seen from this state. */
        private int count;
        /**
         * Represents the transitions as a map from characters to the number of
         * transitions from this state with the given character.
         */
        private MyHashMap<Character, Integer> transitions;

        /**
         * Create a new State with no transitions.
         */
        State() {
            this.count = 0;
            this.transitions = new MyHashMap<Character, Integer>();
        }

        /**
         * Add a new transition from this state with the given suffix character.
         * 
         * @param suffix The suffix character.
         */
        void add(char suffix) {
            // Implement this.
        }

        /**
         * Return one of the characters the suffix characters that training the model
         * encountered in this state.
         * 
         * In other words, this returns one of the characters for which
         * <code>add(suffix)</code> was called with probability proportional to the
         * number of times <code>add(suffix)</code> was called with that particular
         * suffix.
         * 
         * @param rand The random number generator to use to generate the character.
         * @return
         */
        char randomSuffix(Random rand) {
            throw new IllegalStateException();
        }
    }

    /** The map from strings to <Code>State</code>s. */
    private MyHashMap<String, State> states;
    /** The model order. */
    private int order;

    /**
     * Create a new Markov model with the given order.
     * 
     * @param order The order of the Markov model.
     */
    public MarkovModel(int order) {
        this.order = order;
        states = new MyHashMap<String, State>();
    }

    /**
     * Add a new (prefix, suffix) pair to the model.
     * 
     * @param prefix The prefix string. This must be a string whose length is the
     *               order of the model.
     * @param suffix The suffix character.
     */
    public void add(String prefix, char suffix) {
        // Implement this
    }

    /**
     * Train the model on a given string.
     * 
     * @param input The input string to use to train. The string must be at least
     *              <code>this.order</code> characters long.
     */
    public void train(String input) {
        assert (input.length() >= this.order);

        // Implement this
    }

    /**
     * Generate a string of length <code>length</code> starting with
     * <code>prefix</code>.
     * 
     * The <code>prefix</code> must have been given to the model as part of its
     * training data via <code>add(prefix, suffix)</code>.
     * 
     * @param prefix A prefix string of length <code>this.k</code>.
     * @param length The length of the generated string.
     * @param rand   A random number generator to use to generate the string.
     * @return The generated string.
     */
    public String generate(String prefix, int length, Random rand) {
        // Implement this
        return null;
    }

    /**
     * Run the text generation program.
     * 
     * The three command line arguments must be the order (a positive integer), the
     * output length (a positive integer), and a path to a training file.
     * 
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("MarkovModel requires two parameters: order file");
            System.exit(1);
        }

        int order = Integer.parseInt(args[0]);
        MarkovModel model = new MarkovModel(order);

        String input = null;
        try {
            File file = new File(args[1]);
            input = Files.readString(file.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
            e.printStackTrace();
            System.exit(1);
        }
        model.train(input);

        List<SimpleEntry<String, State>> states = model.states.entryList();

        System.out.println(states.size() + " states");
        for (SimpleEntry<String, State> entry : states) {
            System.out.print('"' + entry.getKey() + "\" ↦ ");
            State state = entry.getValue();
            boolean first = true;
            for (SimpleEntry<Character, Integer> transition : state.transitions.entryList()) {
                if (!first) {
                    System.out.print(", ");
                }
                first = false;
                char c = transition.getKey();
                System.out.print(transition.getValue() + " '" + c + "'");
            }
            System.out.println();
        }
    }
}
