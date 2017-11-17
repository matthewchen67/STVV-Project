package org.pitest.mutationtest.engine.gregor.mutators;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.AbstractJumpMutator;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public class RORMutator {
    public enum ConditionalsBoundaryMutator implements MethodMutatorFactory {

      ROR_MUTATOR;

      @Override
      public MethodVisitor create(final MutationContext context,
          final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        return new ConditionalsBoundaryMethodVisitorC(this, context, methodVisitor);
      }

      @Override
      public String getGloballyUniqueId() {
        return this.getClass().getName();
      }

      @Override
      public String getName() {
        return name();
      }

    }
    
    public enum ConditionalsBoundaryMutator2 implements MethodMutatorFactory {

      ROR_MUTATOR;

      @Override
      public MethodVisitor create(final MutationContext context,
          final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        return new ConditionalsBoundaryMethodVisitorC2(this, context, methodVisitor);
      }

      @Override
      public String getGloballyUniqueId() {
        return this.getClass().getName();
      }

      @Override
      public String getName() {
        return name();
      }

    }
    
    public enum ConditionalsBoundaryMutator3 implements MethodMutatorFactory {

      ROR_MUTATOR;

      @Override
      public MethodVisitor create(final MutationContext context,
          final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        return new ConditionalsBoundaryMethodVisitorC3(this, context, methodVisitor);
      }

      @Override
      public String getGloballyUniqueId() {
        return this.getClass().getName();
      }

      @Override
      public String getName() {
        return name();
      }

    }
}

class ConditionalsBoundaryMethodVisitorC extends AbstractJumpMutator {

  private static final Map<Integer, Substitution> MUTATIONS   = new HashMap<Integer, Substitution>();

  static {
    MUTATIONS.put(Opcodes.IFLE, new Substitution(Opcodes.IFLT, "Changed > to >="));
    MUTATIONS.put(Opcodes.IFGE, new Substitution(Opcodes.IFGT, "Changed < to <="));
    MUTATIONS.put(Opcodes.IFGT, new Substitution(Opcodes.IFGE, "Changed <= to <"));
    MUTATIONS.put(Opcodes.IFLT, new Substitution(Opcodes.IFLE, "Changed >= to >"));
    MUTATIONS.put(Opcodes.IF_ICMPLE, new Substitution(Opcodes.IF_ICMPLT,
            "Changed > to >="));
    MUTATIONS.put(Opcodes.IF_ICMPGE, new Substitution(Opcodes.IF_ICMPGT,
            "Changed < to <="));
    MUTATIONS.put(Opcodes.IF_ICMPGT, new Substitution(Opcodes.IF_ICMPGE,
            "Changed <= to <"));
    MUTATIONS.put(Opcodes.IF_ICMPLT, new Substitution(Opcodes.IF_ICMPLE,
            "Changed >= to >"));
  }

  ConditionalsBoundaryMethodVisitorC(final MethodMutatorFactory factory,
      final MutationContext context, final MethodVisitor delegateMethodVisitor) {
    super(factory, context, delegateMethodVisitor);
  }

  @Override
  protected Map<Integer, Substitution> getMutations() {
    return MUTATIONS;
  }

}

class ConditionalsBoundaryMethodVisitorC2 extends AbstractJumpMutator {

  private static final Map<Integer, Substitution> MUTATIONS   = new HashMap<Integer, Substitution>();

  static {
    MUTATIONS.put(Opcodes.IFLE, new Substitution(Opcodes.IFGT, "Changed > to <="));
    MUTATIONS.put(Opcodes.IFGE, new Substitution(Opcodes.IFLT, "Changed < to >="));
    MUTATIONS.put(Opcodes.IFGT, new Substitution(Opcodes.IFLE, "Changed <= to >"));
    MUTATIONS.put(Opcodes.IFLT, new Substitution(Opcodes.IFGE, "Changed >= to <"));
    MUTATIONS.put(Opcodes.IF_ICMPLE, new Substitution(Opcodes.IF_ICMPGT,
            "Changed > to <="));
    MUTATIONS.put(Opcodes.IF_ICMPGE, new Substitution(Opcodes.IF_ICMPLT,
            "Changed < to >="));
    MUTATIONS.put(Opcodes.IF_ICMPGT, new Substitution(Opcodes.IF_ICMPLE,
            "Changed <= to >"));
    MUTATIONS.put(Opcodes.IF_ICMPLT, new Substitution(Opcodes.IF_ICMPGE,
            "Changed >= to <"));
  }

  ConditionalsBoundaryMethodVisitorC2(final MethodMutatorFactory factory,
      final MutationContext context, final MethodVisitor delegateMethodVisitor) {
    super(factory, context, delegateMethodVisitor);
  }

  @Override
  protected Map<Integer, Substitution> getMutations() {
    return MUTATIONS;
  }

}

class ConditionalsBoundaryMethodVisitorC3 extends AbstractJumpMutator {

  private static final Map<Integer, Substitution> MUTATIONS   = new HashMap<Integer, Substitution>();

  static {
    MUTATIONS.put(Opcodes.IFLE, new Substitution(Opcodes.IFGE, "Changed > to <"));
    MUTATIONS.put(Opcodes.IFGE, new Substitution(Opcodes.IFLE, "Changed < to >"));
    MUTATIONS.put(Opcodes.IFGT, new Substitution(Opcodes.IFLT, "Changed <= to >="));
    MUTATIONS.put(Opcodes.IFLT, new Substitution(Opcodes.IFGT, "Changed >= to <="));
    MUTATIONS.put(Opcodes.IF_ICMPLE, new Substitution(Opcodes.IF_ICMPGE,
            "Changed > to <"));
    MUTATIONS.put(Opcodes.IF_ICMPGE, new Substitution(Opcodes.IF_ICMPLE,
            "Changed < to >"));
    MUTATIONS.put(Opcodes.IF_ICMPGT, new Substitution(Opcodes.IF_ICMPLT,
            "Changed <= to >="));
    MUTATIONS.put(Opcodes.IF_ICMPLT, new Substitution(Opcodes.IF_ICMPGT,
            "Changed >= to <="));
  }

  ConditionalsBoundaryMethodVisitorC3(final MethodMutatorFactory factory,
      final MutationContext context, final MethodVisitor delegateMethodVisitor) {
    super(factory, context, delegateMethodVisitor);
  }

  @Override
  protected Map<Integer, Substitution> getMutations() {
    return MUTATIONS;
  }

}