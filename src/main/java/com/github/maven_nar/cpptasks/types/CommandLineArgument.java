package com.github.maven_nar.cpptasks.types;
import org.apache.tools.ant.types.EnumeratedAttribute;
/**
 * An compiler/linker command line flag.
 */
public class CommandLineArgument {
    /**
     * Enumerated attribute with the values "start", "mid" and "end",
     */
    public static class LocationEnum extends EnumeratedAttribute {
        public String[] getValues() {
            return new String[]{"start", "mid", "end"};
        }
    }
    private String ifCond;
    private int location;
    private String unlessCond;
    private String value;
    public CommandLineArgument() {
    }
    public int getLocation() {
        return location;
    }
    public String getValue() {
        return value;
    }
    /**
     * Returns true if the define's if and unless conditions (if any) are
     * satisfied.
     */
    public boolean isActive(org.apache.tools.ant.Project p) {
        if (value == null) {
            return false;
        }
        if (ifCond != null && p.getProperty(ifCond) == null) {
            return false;
        } else if (unlessCond != null && p.getProperty(unlessCond) != null) {
            return false;
        }
        return true;
    }
    /**
     * Sets the property name for the 'if' condition.
     * 
     * The argument will be ignored unless the property is defined.
     * 
     * The value of the property is insignificant, but values that would imply
     * misinterpretation ("false", "no") will throw an exception when
     * evaluated.
     */
    public void setIf(String propName) {
        ifCond = propName;
    }
    /**
     * Specifies relative location of argument on command line. "start" will
     * place argument at start of command line, "mid" will place argument after
     * all "start" arguments but before filenames, "end" will place argument
     * after filenames.
     *  
     */
    public void setLocation(LocationEnum location) {
        this.location = location.getIndex();
    }
    /**
     * Set the property name for the 'unless' condition.
     * 
     * If named property is set, the argument will be ignored.
     * 
     * The value of the property is insignificant, but values that would imply
     * misinterpretation ("false", "no") of the behavior will throw an
     * exception when evaluated.
     * 
     * @param propName
     *            name of property
     */
    public void setUnless(String propName) {
        unlessCond = propName;
    }
    /**
     * Specifies the string that should appear on the command line. The
     * argument will be quoted if it contains embedded blanks. Use multiple
     * arguments to avoid quoting.
     *  
     */
    public void setValue(String value) {
        this.value = value;
    }
}
