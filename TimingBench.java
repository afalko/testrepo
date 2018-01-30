import java.util.regex.Pattern;

public class TimingBench {
	static String toSnakeAndLowerCase(String attrName) {
        if (attrName == null || attrName.isEmpty()) {
            return attrName;
        }
        StringBuilder resultBuilder = new StringBuilder(attrName.substring(0,1).toLowerCase());
        for (char attrChar : attrName.substring(1).toCharArray()) {
            if (Character.isUpperCase(attrChar)) {
                resultBuilder.append("_").append(Character.toLowerCase(attrChar));
            } else {
                resultBuilder.append(attrChar);
            }
        }
        return resultBuilder.toString();
    }

    public static void main(String[] args) {
        final Pattern snakeCasePattern = Pattern.compile("([a-z0-9])([A-Z])");
        String attrName = "StartTime_$1_$2";

        String result = "";

        long startTime = System.currentTimeMillis();
        for (int i = 0; i<1000000; i++) {
            result = snakeCasePattern.matcher(attrName).replaceAll("$1_$2").toLowerCase();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(result);
        System.out.println(endTime - startTime);

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
			result = toSnakeAndLowerCase(attrName);
        }
        endTime = System.currentTimeMillis();
        System.out.println(result);
        System.out.println(endTime - startTime);
    }
}