package com.github.maven_nar.cpptasks.intel;

import org.apache.tools.ant.types.Environment;

import com.github.maven_nar.cpptasks.compiler.LinkType;
import com.github.maven_nar.cpptasks.compiler.Linker;
import com.github.maven_nar.cpptasks.compiler.Processor;
import com.github.maven_nar.cpptasks.gcc.GccCompatibleCCompiler;
/**
 * Adapter for the Intel (r) C/C++ compiler for IA-64 Linux (r)
 * 
 * The Intel C/C++ compiler for IA-64 Linux mimics the command options for gcc
 * compiler.
 * 
 * @author Curt Arnold
 */
public final class IntelLinux64CCompiler extends GccCompatibleCCompiler {
    private static final IntelLinux64CCompiler instance = new IntelLinux64CCompiler(
            false, new IntelLinux64CCompiler(true, null, false, null), false,
            null);
    public static IntelLinux64CCompiler getInstance() {
        return instance;
    }
    private IntelLinux64CCompiler(boolean isLibtool,
            IntelLinux64CCompiler libtoolCompiler, boolean newEnvironment,
            Environment env) {
        super("ecc", "-V", isLibtool, libtoolCompiler, newEnvironment, env);
    }
    public Processor changeEnvironment(boolean newEnvironment, Environment env) {
        if (newEnvironment || env != null) {
            return new IntelLinux64CCompiler(getLibtool(),
                    (IntelLinux64CCompiler) this.getLibtoolCompiler(),
                    newEnvironment, env);
        }
        return this;
    }
    public Linker getLinker(LinkType type) {
// FREEHEP
        return IntelLinux64CLinker.getInstance().getLinker(type);
    }
    public int getMaximumCommandLength() {
        return Integer.MAX_VALUE;
    }
}
