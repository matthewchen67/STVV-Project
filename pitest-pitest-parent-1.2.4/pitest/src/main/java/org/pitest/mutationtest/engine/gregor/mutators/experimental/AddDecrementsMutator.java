package org.pitest.mutationtest.engine.gregor.mutators.experimental;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public enum AddDecrementsMutator implements MethodMutatorFactory {

  ADD_DECREMENTS_MUTATOR;

  @Override
  public MethodVisitor create(final MutationContext context,
      final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
    return new AddDecrementsMethodVisitor(this, context, methodVisitor);
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

class AddDecrementsMethodVisitor extends MethodVisitor {

  private final MethodMutatorFactory factory;
  private final MutationContext      context;

  AddDecrementsMethodVisitor(final MethodMutatorFactory factory,
      final MutationContext context, final MethodVisitor delegateMethodVisitor) {
    super(Opcodes.ASM6, delegateMethodVisitor);
    this.factory = factory;
    this.context = context;
  }
  
  @Override
  public void visitVarInsn(final int opcode, final int var) {
    if (opcode == Opcodes.ILOAD) {
      final MutationIdentifier newId = this.context.registerMutation(
          this.factory, "Added integer decrement");
      mv.visitVarInsn(opcode, var);
      mv.visitIincInsn(var, -1);
      super.visitVarInsn(opcode, var);
    } else if (opcode == Opcodes.DLOAD) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "Added double increment");
        mv.visitVarInsn(opcode, var);
        mv.visitInsn(Opcodes.DUP2);
        mv.visitInsn(Opcodes.DCONST_1);
        mv.visitInsn(Opcodes.DSUB);
        mv.visitVarInsn(Opcodes.DSTORE, var);
        super.visitVarInsn(opcode, var);
      } else if (opcode == Opcodes.FLOAD) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "Added float increment");
        mv.visitVarInsn(opcode, var);
        mv.visitInsn(Opcodes.DUP);
        mv.visitInsn(Opcodes.FCONST_1);
        mv.visitInsn(Opcodes.FSUB);
        mv.visitVarInsn(Opcodes.FSTORE, var);
        super.visitVarInsn(opcode, var);
      } else if (opcode == Opcodes.LLOAD) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "Added long increment");
        mv.visitVarInsn(opcode, var);
        mv.visitInsn(Opcodes.DUP2);
        mv.visitInsn(Opcodes.LCONST_1);
        mv.visitInsn(Opcodes.LSUB);
        mv.visitVarInsn(Opcodes.LSTORE, var);
        super.visitVarInsn(opcode, var);
      } else {
        super.visitVarInsn(opcode, var);
      }
  }

}