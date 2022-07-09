package by.ashurmatov.anime.controller.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.util.regex.Pattern;

public class XSSRequestWrapper extends HttpServletRequestWrapper {
    private final String AVOID_NULL_CHARACTERS = "";
    private final String AVOID_ANYTHING_BETWEEN_SCRIPT_TAGS = "<script>(.*?)</script>";
    private final String AVOID_ANYTHING_IN_A_SRC_1 = "src[\n" + "]*=[\n" + "]*\\'(.*?)\\'";
    private final String AVOID_ANYTHING_IN_A_SRC_2 = "src[\n" + "]*=[\n" + "]*\\\"(.*?)\\\"";
    private final String REMOVE_ANY_LONESOME_SCRIPT_TAG_1 = "</script>";
    private final String REMOVE_ANY_LONESOME_SCRIPT_TAG_2 = "<script(.*?)>";
    private final String AVOID_EVAL_EXPRESSIONS = "eval\\((.*?)\\)";
    private final String AVOID_EXPRESSIONS = "expression\\((.*?)\\)";
    private final String AVOID_JAVASCRIPT_EXPRESSIONS = "javascript:";
    private final String AVOID_VBSCRIPT_EXPRESSIONS = "vbscript:";
    private final String AVOID_ONLOAD_EXPRESSIONS = "onload(.*?)=";

    public XSSRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);

        if (values == null) {
            return null;
        }

        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = stripXSS(values[i]);
        }

        return encodedValues;
    }

    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);

        return stripXSS(value);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return stripXSS(value);
    }

    private String stripXSS(String value) {
        if (value != null) {
            
            // Avoid null characters
            value = value.replaceAll(AVOID_NULL_CHARACTERS, "");

            // Avoid anything between script tags
            Pattern scriptPattern = Pattern.compile(AVOID_ANYTHING_BETWEEN_SCRIPT_TAGS, Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid anything in a src='...' type of expression
            scriptPattern = Pattern.compile(AVOID_ANYTHING_IN_A_SRC_1, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile(AVOID_ANYTHING_IN_A_SRC_2, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome </script> tag
            scriptPattern = Pattern.compile(REMOVE_ANY_LONESOME_SCRIPT_TAG_1, Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile(REMOVE_ANY_LONESOME_SCRIPT_TAG_2, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid eval(...) expressions
            scriptPattern = Pattern.compile(AVOID_EVAL_EXPRESSIONS, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid expression(...) expressions
            scriptPattern = Pattern.compile(AVOID_EXPRESSIONS, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid javascript:... expressions
            scriptPattern = Pattern.compile(AVOID_JAVASCRIPT_EXPRESSIONS, Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid vbscript:... expressions
            scriptPattern = Pattern.compile(AVOID_VBSCRIPT_EXPRESSIONS, Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid onload= expressions
            scriptPattern = Pattern.compile(AVOID_ONLOAD_EXPRESSIONS, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
        }
        return value;
    }
}
