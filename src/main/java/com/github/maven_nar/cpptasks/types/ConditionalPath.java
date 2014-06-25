package com.github.maven_nar.cpptasks.types;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Path;

import com.github.maven_nar.cpptasks.CUtil;
/**
 * An Ant Path object augmented with if and unless conditionals
 * 
 * @author Curt Arnold
 */
public class ConditionalPath extends Path {
    private String ifCond;
    private String unlessCond;
    public ConditionalPath(Project project) {
        super(project);
    }
    public ConditionalPath(Project p, String path) {
        super(p, path);
    }
    /**
     * Returns true if the Path's if and unless conditions (if any) are
     * satisfied.
     */
    public boolean isActive(org.apache.tools.ant.Project p)
            throws BuildException {
        return CUtil.isActive(p, ifCond, unlessCond);
    }
    /**
     * Sets the property name for the 'if' condition.
     * 
     * The path will be ignored unless the property is defined.
     * 
     * The value of the property is insignificant, but values that would imply
     * misinterpretation ("false", "no") will throw an exception when
     * evaluated.
     * 
     * @param propName
     *            property name
     */
    public void setIf(String propName) {
        ifCond = propName;
    }
    /**
     * Set the property name for the 'unless' condition.
     * 
     * If named property is set, the path will be ignored.
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
}
