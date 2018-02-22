import java.util.regex.Pattern;

public class TimingBench2 {
    private static final Pattern unsafeChars = Pattern.compile("[^a-zA-Z0-9:_]");
    private static final Pattern multipleUnderscores = Pattern.compile("__+");

    static String safeName(String name) {
      if (name == null) {
        return null;
      }
      boolean prevCharIsUnderscore = false;
      StringBuilder safeNameBuilder = new StringBuilder();
      for (char nameChar : name.toCharArray()) {
        //boolean isUnsafeChar = unsafeChars.matcher(String.valueOf(nameChar)).matches();
        boolean isUnsafeChar = !(Character.isLetterOrDigit(nameChar) || nameChar == ':' || nameChar == '_');
        if ((isUnsafeChar || nameChar == '_') && !prevCharIsUnderscore) {
          safeNameBuilder.append("_");
          prevCharIsUnderscore = true;
        } else if (nameChar == '_' || isUnsafeChar) {
          continue;
        } else {
          safeNameBuilder.append(nameChar);
          prevCharIsUnderscore = false;
        }
      }

      return safeNameBuilder.toString();
      //return multipleUnderscores.matcher(unsafeChars.matcher(name).replaceAll("_")).replaceAll("_");
    }
 
    static String safeNameRegex(String name) {
      return multipleUnderscores.matcher(unsafeChars.matcher(name).replaceAll("_")).replaceAll("_");
    }

    static String safeNameMatcher(String name) {
      if (name == null) {
        return null;
      }
      boolean prevCharIsUnderscore = false;
      StringBuilder safeNameBuilder = new StringBuilder();
      for (char nameChar : name.toCharArray()) {
        boolean isUnsafeChar = unsafeChars.matcher(String.valueOf(nameChar)).matches();
        //boolean isUnsafeChar = !(Character.isLetterOrDigit(nameChar) || nameChar == ':' || nameChar == '_');
        if ((isUnsafeChar || nameChar == '_') && !prevCharIsUnderscore) {
          safeNameBuilder.append("_");
          prevCharIsUnderscore = true;
        } else if (nameChar == '_' || isUnsafeChar) {
          continue;
        } else {
          safeNameBuilder.append(nameChar);
          prevCharIsUnderscore = false;
        }
      }

      return safeNameBuilder.toString();
    }

    public static void main(String[] args) {
        String attrName = "StartTime_$1_$2________";

        String result = "";

        long startTime = System.currentTimeMillis();
        for (int i = 0; i<1000000; i++) {
            result = safeName(attrName);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("No regex: " + result);
        System.out.println(endTime - startTime);
        
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
	    result = safeNameRegex(attrName);
        }
        endTime = System.currentTimeMillis();
        System.out.println("Original: " + result);
        System.out.println(endTime - startTime);

	startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            result = safeNameMatcher(attrName);
        }
        endTime = System.currentTimeMillis();
        System.out.println("Partial regex: " + result);
        System.out.println(endTime - startTime);
    }
}
