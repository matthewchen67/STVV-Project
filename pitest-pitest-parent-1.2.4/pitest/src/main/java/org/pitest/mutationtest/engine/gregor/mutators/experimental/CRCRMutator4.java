package org.pitest.mutationtest.engine.gregor.mutators.experimental;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public enum CRCRMutator4 implements MethodMutatorFactory {

    CRCR_MUTATOR;

    @Override
    public MethodVisitor create(final MutationContext context,
        final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
      return new CRCRMethodVisitor4(this, context, methodVisitor);
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

class CRCRMethodVisitor4 extends MethodVisitor {

  private final MethodMutatorFactory factory;
  private final MutationContext      context;

  CRCRMethodVisitor4(final MethodMutatorFactory factory,
      final MutationContext context, final MethodVisitor delegateMethodVisitor) {
    super(Opcodes.ASM6, delegateMethodVisitor);
    this.factory = factory;
    this.context = context;
  }
  
  @Override
  public void visitIntInsn(final int opcode, final int operand) {
      if (opcode == Opcodes.BIPUSH) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "integer operand incremented by 1");
        if (this.context.shouldMutate(newId)) {
            this.mv.visitIntInsn(Opcodes.BIPUSH, operand + 1);
        } else {
            super.visitIntInsn(opcode, operand);
        }
      } else {
          super.visitIntInsn(opcode, operand);
      }
  }
  
  @Override
  public void visitLdcInsn(final Object cst) {
      if (cst instanceof Double) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "double operand incremented by 1");
        if (this.context.shouldMutate(newId)) {
            this.mv.visitLdcInsn(new Double(((Double) cst).doubleValue() + 1));
        } else {
            super.visitLdcInsn(cst);
        }
      } else if (cst instanceof Float) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "float operand incremented by 1");
        if (this.context.shouldMutate(newId)) {
            this.mv.visitLdcInsn(new Float(((Float) cst).floatValue() + 1));
        } else {
            super.visitLdcInsn(cst);
        }
      } else if (cst instanceof Long) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "long operand incremented by 1");
        if (this.context.shouldMutate(newId)) {
            this.mv.visitLdcInsn(new Long(((Long) cst).longValue() + 1));
        } else {
            super.visitLdcInsn(cst);
        }
      } else {
        super.visitLdcInsn(cst);
      }
  }

}