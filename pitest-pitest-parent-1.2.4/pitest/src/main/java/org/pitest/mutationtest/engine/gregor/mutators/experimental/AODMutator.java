package org.pitest.mutationtest.engine.gregor.mutators.experimental;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public class AODMutator {
    public enum AODMutator1 implements MethodMutatorFactory {

      AOD_MUTATOR;

      @Override
      public MethodVisitor create(final MutationContext context,
          final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        return new AODMethodVisitor1(this, context, methodVisitor);
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
    
    public enum AODMutator2 implements MethodMutatorFactory {

        AOD_MUTATOR;

        @Override
        public MethodVisitor create(final MutationContext context,
            final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
          return new AODMethodVisitor2(this, context, methodVisitor);
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
class AODMethodVisitor1 extends MethodVisitor {

  private final MethodMutatorFactory factory;
  private final MutationContext      context;

  AODMethodVisitor1(final MethodMutatorFactory factory,
      final MutationContext context, final MethodVisitor delegateMethodVisitor) {
    super(Opcodes.ASM6, delegateMethodVisitor);
    this.factory = factory;
    this.context = context;
  }
  
  @Override
  public void visitInsn(final int opcode) {
      if (opcode == Opcodes.IMUL || opcode == Opcodes.IDIV || opcode == Opcodes.IADD || opcode == Opcodes.ISUB || opcode == Opcodes.IREM) {
          final MutationIdentifier newId = this.context.registerMutation(
                    this.factory, "Replaced A [operator] B with A");
          if (this.context.shouldMutate(newId)) {
              this.mv.visitInsn(Opcodes.POP);
          } else {
              super.visitInsn(opcode);
          }
      } else if (opcode == Opcodes.FMUL || opcode == Opcodes.FDIV || opcode == Opcodes.FADD || opcode == Opcodes.FSUB || opcode == Opcodes.FREM) {
          final MutationIdentifier newId = this.context.registerMutation(
                  this.factory, "Replaced Af [operator] Bf with Af");
        if (this.context.shouldMutate(newId)) {
            this.mv.visitInsn(Opcodes.POP);
        } else {
            super.visitInsn(opcode);
        }
      } else if (opcode == Opcodes.LMUL || opcode == Opcodes.LDIV || opcode == Opcodes.LADD || opcode == Opcodes.LSUB || opcode == Opcodes.LREM) {
          final MutationIdentifier newId = this.context.registerMutation(
                  this.factory, "Replaced Al [operator] Bl with Al");
        if (this.context.shouldMutate(newId)) {
            this.mv.visitInsn(Opcodes.POP2);
        } else {
            super.visitInsn(opcode);
        }
      } else if (opcode == Opcodes.DMUL || opcode == Opcodes.DDIV || opcode == Opcodes.DADD || opcode == Opcodes.DSUB || opcode == Opcodes.DREM) {
          final MutationIdentifier newId = this.context.registerMutation(
                  this.factory, "Replaced Ad [operator] Bd with Ad");
        if (this.context.shouldMutate(newId)) {
            this.mv.visitInsn(Opcodes.POP2);
        } else {
            super.visitInsn(opcode);
        }
      } else {
          super.visitInsn(opcode);
      }
  }

}

class AODMethodVisitor2 extends MethodVisitor {

  private final MethodMutatorFactory factory;
  private final MutationContext      context;

  AODMethodVisitor2(final MethodMutatorFactory factory,
      final MutationContext context, final MethodVisitor delegateMethodVisitor) {
    super(Opcodes.ASM6, delegateMethodVisitor);
    this.factory = factory;
    this.context = context;
  }
  
  @Override
  public void visitInsn(final int opcode) {
      if (opcode == Opcodes.IMUL || opcode == Opcodes.IDIV || opcode == Opcodes.IADD || opcode == Opcodes.ISUB || opcode == Opcodes.IREM) {
          final MutationIdentifier newId = this.context.registerMutation(
                    this.factory, "Replaced A [operator] B with B");
          if (this.context.shouldMutate(newId)) {
              this.mv.visitInsn(Opcodes.DUP_X1);
              this.mv.visitInsn(Opcodes.POP2);
          } else {
              super.visitInsn(opcode);
          }
      } else if (opcode == Opcodes.FMUL || opcode == Opcodes.FDIV || opcode == Opcodes.FADD || opcode == Opcodes.FSUB || opcode == Opcodes.FREM) {
          final MutationIdentifier newId = this.context.registerMutation(
                  this.factory, "Replaced Af [operator] Bf with Bf");
        if (this.context.shouldMutate(newId)) {
            this.mv.visitInsn(Opcodes.DUP_X1);
            this.mv.visitInsn(Opcodes.POP2);
        } else {
            super.visitInsn(opcode);
        }
      } else if (opcode == Opcodes.LMUL || opcode == Opcodes.LDIV || opcode == Opcodes.LADD || opcode == Opcodes.LSUB || opcode == Opcodes.LREM) {
          final MutationIdentifier newId = this.context.registerMutation(
                  this.factory, "Replaced Al [operator] Bl with Bl");
        if (this.context.shouldMutate(newId)) {
            this.mv.visitInsn(Opcodes.DUP2_X1);
            this.mv.visitInsn(Opcodes.POP2);
            this.mv.visitInsn(Opcodes.POP2);
        } else {
            super.visitInsn(opcode);
        }
      } else if (opcode == Opcodes.DMUL || opcode == Opcodes.DDIV || opcode == Opcodes.DADD || opcode == Opcodes.DSUB || opcode == Opcodes.DREM) {
          final MutationIdentifier newId = this.context.registerMutation(
                  this.factory, "Replaced Ad [operator] Bd with Bd");
        if (this.context.shouldMutate(newId)) {
            this.mv.visitInsn(Opcodes.DUP2_X1);
            this.mv.visitInsn(Opcodes.POP2);
            this.mv.visitInsn(Opcodes.POP2);
        } else {
            super.visitInsn(opcode);
        }
      } else {
          super.visitInsn(opcode);
      }
  }

}