package org.pitest.mutationtest.engine.gregor.mutators.experimental;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public enum CRCRMutator1 implements MethodMutatorFactory {

    CRCR_MUTATOR;

    @Override
    public MethodVisitor create(final MutationContext context,
        final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
      return new CRCRMethodVisitor(this, context, methodVisitor);
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

class CRCRMethodVisitor extends MethodVisitor {

  private final MethodMutatorFactory factory;
  private final MutationContext      context;

  CRCRMethodVisitor(final MethodMutatorFactory factory,
      final MutationContext context, final MethodVisitor delegateMethodVisitor) {
    super(Opcodes.ASM6, delegateMethodVisitor);
    this.factory = factory;
    this.context = context;
  }
  
  @Override
  public void visitIntInsn(final int opcode, final int operand) {
      if (opcode == Opcodes.BIPUSH) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "integer operand replaced with " + 1);
        if (this.context.shouldMutate(newId)) {
            this.mv.visitIntInsn(Opcodes.BIPUSH, 1);
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
            this.factory, "double operand replaced with " + 1);
        if (this.context.shouldMutate(newId)) {
            this.mv.visitLdcInsn(new Double("1.0"));
        } else { 
            super.visitLdcInsn(cst);
        }
      } else if (cst instanceof Float) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "float operand replaced with " + 1);
        if (this.context.shouldMutate(newId)) {
            this.mv.visitLdcInsn(new Float("1.0"));
        } else { 
            super.visitLdcInsn(cst);
        }
      } else if (cst instanceof Long) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "long operand replaced with " + 1);
        if (this.context.shouldMutate(newId)) {
            this.mv.visitLdcInsn(new Long(1L));
        } else { 
            super.visitLdcInsn(cst);
        }
      } else {
        super.visitLdcInsn(cst);
      }
  }

}