package rbq.lycoris.agent.instrument;

import java.security.ProtectionDomain;


public interface ClassTransformer {
    byte[] transform(final ClassLoader loader, final String className, final Class<?> classBeingRedefined, final ProtectionDomain protectionDomain, final byte[] classfileBuffer);
}