package noroff.project.hvz.utils;

import java.util.Random;

public class RandomIdGenerator {
    private static final char[] _baseChars =
            "0123456789abcdefghijklmnopqrstuvwxyz"
                    .toCharArray();

    private static final Random _random = new Random();

    /**
     * Generate random string including numbers 0-9 and alphabet a-z
     * @param length Length of the generated String
     * @return Returns the generated String
     */
    public static String GetBase36(int length)
    {
        var sb = new StringBuilder(length);

        for (int i=0; i<length; i++)
            sb.append(_baseChars[_random.nextInt(36)]);

        return sb.toString();
    }
}
