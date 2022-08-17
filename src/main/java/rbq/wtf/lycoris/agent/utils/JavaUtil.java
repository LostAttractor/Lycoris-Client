package rbq.wtf.lycoris.agent.utils;

import java.util.Arrays;
import java.util.Stack;

public final class JavaUtil {
    private JavaUtil() {
    }

    public static boolean equals(final byte[] firstByteArray, final byte[] secondByteArray) {
        if (firstByteArray.length != secondByteArray.length)
            return false;

        for (int i = 0; i < firstByteArray.length; i++) {
            if (firstByteArray[i] != secondByteArray[i])
                return false;
        }

        return true;
    }

    static void printStacktrace(final Throwable throwable) {
        System.err.println("Exception: " + throwable.toString());
        Arrays.stream(throwable.getStackTrace()).forEach(stackTraceElement -> System.err.println(" at " + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + " (" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ")"));

        final Stack<Throwable> throwableStack = new Stack<>();
        throwableStack.push(throwable.getCause());

        while (!throwableStack.empty()) {
            final Throwable newThrowable = throwableStack.pop();
            if (newThrowable.getCause() == null) {
                System.err.println("Exception: " + newThrowable.toString());
                Arrays.stream(throwable.getStackTrace()).forEach(stackTraceElement -> System.err.println(" at " + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + " (" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ")"));
                break;
            }

            System.err.println("Exception: " + newThrowable.toString());
            Arrays.stream(throwable.getStackTrace()).forEach(stackTraceElement -> System.err.println("at " + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + " (" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ")"));
        }
    }
}