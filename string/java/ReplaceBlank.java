import org.junit.Assert;

/**
 * @title 请实现一个函数，将一个字符串中的空格替换成“%20”。
 * @example 例如，当字符串为"We Are Happy".则经过替换之后的字符串为"We%20Are%20Happy"。
 * @idea
 */
public class ReplaceBlank {

    public static String replaceSpace(StringBuffer str) {
        if (str == null) {
            return null;
        }
        String string = str.toString();
        int blankCount = 0;
        char[] array = string.toCharArray();
        for (char c : array) {
            if (c == ' ') {
                blankCount++;
            }
        }
        if (blankCount == 0) {
            return str.toString();
        }
        String replaceFrom = " ";
        String replaceTo = "%20";
        int newArrayLength = array.length + blankCount * (replaceTo.length() - replaceFrom.length());
        char[] newArray = new char[newArrayLength];
        for (int i = newArray.length - 1, j = array.length - 1; i >= 0 && j >= 0; j--) {
            if (array[j] == ' ') {
                newArray[i--] = replaceTo.charAt(2);
                newArray[i--] = replaceTo.charAt(1);
                newArray[i--] = replaceTo.charAt(0);
            } else {
                newArray[i--] = array[j];
            }
        }
        return new String(newArray);
    }

    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer("hello world");
        String result = ReplaceBlank.replaceSpace(stringBuffer);
        Assert.assertEquals("hello%20world", result);
    }
}
