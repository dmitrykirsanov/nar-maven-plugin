package com.github.maven_nar.cpptasks.os400;
import java.io.File;
import java.util.Vector;



import org.apache.tools.ant.types.Environment;

import com.github.maven_nar.cpptasks.CUtil;
import com.github.maven_nar.cpptasks.OptimizationEnum;
import com.github.maven_nar.cpptasks.compiler.AbstractCompiler;
import com.github.maven_nar.cpptasks.compiler.CommandLineCCompiler;
import com.github.maven_nar.cpptasks.compiler.LinkType;
import com.github.maven_nar.cpptasks.compiler.Linker;
import com.github.maven_nar.cpptasks.compiler.Processor;
/**
 * Adapter for the IBM (R) OS/390 (tm) C++ Compiler
 * 
 * @author Hiram Chirino (cojonudo14@hotmail.com)
 */
public class IccCompiler extends CommandLineCCompiler {
    private static final AbstractCompiler instance = new IccCompiler(false,
            null);
    public static AbstractCompiler getInstance() {
        return instance;
    }
    private IccCompiler(boolean newEnvironment, Environment env) {
        super("icc", null, new String[]{".c", ".cc", ".cpp", ".cxx", ".c++",
                ".s"}, new String[]{".h", ".hpp"}, ".o", false, null,
                newEnvironment, env);
    }
    protected void addImpliedArgs(final Vector args, 
    		final boolean debug,
            final boolean multithreaded, 
			final boolean exceptions, 
			final LinkType linkType,
			final Boolean rtti,
			final OptimizationEnum optimization) {
        // Specifies that only compilations and assemblies be done.
        //  Link-edit is not done
        args.addElement("-c");
        /*
         * if (exceptions) { args.addElement("/GX"); }
         */
        if (debug) {
            args.addElement("-g");
            /*
             * args.addElement("-D"); args.addElement("_DEBUG"); if
             * (multithreaded) { args.addElement("/D_MT"); if (staticLink) {
             * args.addElement("/MTd"); } else { args.addElement("/MDd");
             * args.addElement("/D_DLL"); } } else { args.addElement("/MLd"); }
             */
        } else {
            /*
             * args.addElement("-D"); args.addElement("NEBUG"); if
             * (multithreaded) { args.addElement("/D_MT"); if (staticLink) {
             * args.addElement("/MT"); } else { args.addElement("/MD");
             * args.addElement("/D_DLL"); } } else { args.addElement("/ML"); }
             */
        }
    }
    protected void addWarningSwitch(Vector args, int level) {
        IccProcessor.addWarningSwitch(args, level);
    }
    public Processor changeEnvironment(boolean newEnvironment, Environment env) {
        if (newEnvironment || env != null) {
            return new IccCompiler(newEnvironment, env);
        }
        return this;
    }
    protected void getDefineSwitch(StringBuffer buffer, String define,
            String value) {
        buffer.append("-q");
        buffer.append(define);
        if (value != null && value.length() > 0) {
            buffer.append('=');
            buffer.append(value);
        }
    }
    protected File[] getEnvironmentIncludePath() {
        return CUtil.getPathFromEnvironment("INCLUDE", ":");
    }
    protected String getIncludeDirSwitch(String includeDir) {
        return IccProcessor.getIncludeDirSwitch(includeDir);
    }
    public Linker getLinker(LinkType type) {
        return IccLinker.getInstance().getLinker(type);
    }
    public int getMaximumCommandLength() {
        return Integer.MAX_VALUE;
    }
    /* Only compile one file at time for now */
    protected int getMaximumInputFilesPerCommand() {
        return 1;
        //return Integer.MAX_VALUE;
    }
    protected void getUndefineSwitch(StringBuffer buffer, String define) {
        /*
         * buffer.addElement("-q"); buf.append(define);
         */
    }
}
