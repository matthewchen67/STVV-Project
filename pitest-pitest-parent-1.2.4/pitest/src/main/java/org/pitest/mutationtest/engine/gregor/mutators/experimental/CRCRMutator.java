package org.pitest.mutationtest.engine.gregor.mutators.experimental;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public class CRCRMutator {
    public enum CRCRMutator1 implements MethodMutatorFactory {

        CRCR_MUTATOR;

        @Override
        public MethodVisitor create(final MutationContext context,
            final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
          return new CRCRMethodVisitorC(this, context, methodVisitor);
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
    
    public enum CRCRMutator2 implements MethodMutatorFactory {

        CRCR_MUTATOR;

        @Override
        public MethodVisitor create(final MutationContext context,
            final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
          return new CRCRMethodVisitorC2(this, context, methodVisitor);
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
    
    public enum CRCRMutator3 implements MethodMutatorFactory {

        CRCR_MUTATOR;

        @Override
        public MethodVisitor create(final MutationContext context,
            final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
          return new CRCRMethodVisitorC3(this, context, methodVisitor);
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
    
    public enum CRCRMutator4 implements MethodMutatorFactory {

        CRCR_MUTATOR;

        @Override
        public MethodVisitor create(final MutationContext context,
            final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
          return new CRCRMethodVisitorC4(this, context, methodVisitor);
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
    
    public enum CRCRMutator5 implements MethodMutatorFactory {

        CRCR_MUTATOR;

        @Override
        public MethodVisitor create(final MutationContext context,
            final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
          return new CRCRMethodVisitorC5(this, context, methodVisitor);
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

class CRCRMethodVisitorC extends MethodVisitor {

  private final MethodMutatorFactory factory;
  private final MutationContext      context;

  CRCRMethodVisitorC(final MethodMutatorFactory factory,
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

class CRCRMethodVisitorC2 extends MethodVisitor {

  private final MethodMutatorFactory factory;
  private final MutationContext      context;

  CRCRMethodVisitorC2(final MethodMutatorFactory factory,
      final MutationContext context, final MethodVisitor delegateMethodVisitor) {
    super(Opcodes.ASM6, delegateMethodVisitor);
    this.factory = factory;
    this.context = context;
  }
  
  @Override
  public void visitIntInsn(final int opcode, final int operand) {
      if (opcode == Opcodes.BIPUSH) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "integer operand replaced with " + 0);
        if (this.context.shouldMutate(newId)) {
            super.visitIntInsn(Opcodes.BIPUSH, 0);
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
            this.factory, "double operand replaced with " + 0);
        if (this.context.shouldMutate(newId)) {
            super.visitLdcInsn(new Double("0.0"));
        } else {
            super.visitLdcInsn(cst);
        }
      } else if (cst instanceof Float) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "float operand replaced with " + 0);
        if (this.context.shouldMutate(newId)) {
            super.visitLdcInsn(new Float("0.0"));
        } else {
            super.visitLdcInsn(cst);
        }
      } else if (cst instanceof Long) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "long operand replaced with " + 0);
        if (this.context.shouldMutate(newId)) {
            super.visitLdcInsn(new Long(0L));
        } else {
            super.visitLdcInsn(cst);
        }
      } else {
        super.visitLdcInsn(cst);
      }
  }

}

class CRCRMethodVisitorC3 extends MethodVisitor {

  private final MethodMutatorFactory factory;
  private final MutationContext      context;

  CRCRMethodVisitorC3(final MethodMutatorFactory factory,
      final MutationContext context, final MethodVisitor delegateMethodVisitor) {
    super(Opcodes.ASM6, delegateMethodVisitor);
    this.factory = factory;
    this.context = context;
  }
  
  @Override
  public void visitIntInsn(final int opcode, final int operand) {
      if (opcode == Opcodes.BIPUSH) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "integer operand replaced with it's negation");
        if (this.context.shouldMutate(newId)) {
            this.mv.visitIntInsn(Opcodes.BIPUSH, -operand);
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
            this.factory, "double operand replaced with it's negation");
        if (this.context.shouldMutate(newId)) {
            this.mv.visitLdcInsn(new Double(-((Double) cst).doubleValue()));
        } else {
            super.visitLdcInsn(cst);
        }
      } else if (cst instanceof Float) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "float operand replaced with it's negation");
        if (this.context.shouldMutate(newId)) {
            this.mv.visitLdcInsn(new Float(-((Float) cst).floatValue()));
        } else {
            super.visitLdcInsn(cst);
        }
      } else if (cst instanceof Long) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "long operand replaced with it's negation");
        if (this.context.shouldMutate(newId)) {
            this.mv.visitLdcInsn(new Long(-((Long) cst).longValue()));
        } else {
            super.visitLdcInsn(cst);
        }
      } else {
        super.visitLdcInsn(cst);
      }
  }

}

class CRCRMethodVisitorC4 extends MethodVisitor {

  private final MethodMutatorFactory factory;
  private final MutationContext      context;

  CRCRMethodVisitorC4(final MethodMutatorFactory factory,
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

class CRCRMethodVisitorC5 extends MethodVisitor {

  private final MethodMutatorFactory factory;
  private final MutationContext      context;

  CRCRMethodVisitorC5(final MethodMutatorFactory factory,
      final MutationContext context, final MethodVisitor delegateMethodVisitor) {
    super(Opcodes.ASM6, delegateMethodVisitor);
    this.factory = factory;
    this.context = context;
  }
  
  @Override
  public void visitIntInsn(final int opcode, final int operand) {
      if (opcode == Opcodes.BIPUSH) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "integer operand decremented by 1");
        if (this.context.shouldMutate(newId)) {
            this.mv.visitIntInsn(Opcodes.BIPUSH, operand - 1);
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
            this.factory, "double operand decremented by 1");
        if (this.context.shouldMutate(newId)) {
            this.mv.visitLdcInsn(new Double(((Double) cst).doubleValue() - 1));
        } else {
            super.visitLdcInsn(cst);
        }
      } else if (cst instanceof Float) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "float operand decremented by 1");
        if (this.context.shouldMutate(newId)) {
            this.mv.visitLdcInsn(new Float(((Float) cst).floatValue() - 1));
        } else {
            super.visitLdcInsn(cst);
        }
      } else if (cst instanceof Long) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "long operand decremented by 1");
        if (this.context.shouldMutate(newId)) {
            this.mv.visitLdcInsn(new Long(((Long) cst).longValue() - 1));
        } else {
            super.visitLdcInsn(cst);
        }
      } else {
        super.visitLdcInsn(cst);
      }
  }

}