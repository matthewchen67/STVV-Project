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
            this.factory, "integer constant replaced with " + 1);
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
            this.factory, "double constant replaced with " + 1);
        if (this.context.shouldMutate(newId)) {
            this.mv.visitLdcInsn(new Double("1.0"));
        } else { 
            super.visitLdcInsn(cst);
        }
      } else if (cst instanceof Float) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "float constant replaced with " + 1);
        if (this.context.shouldMutate(newId)) {
            this.mv.visitLdcInsn(new Float("1.0"));
        } else { 
            super.visitLdcInsn(cst);
        }
      } else if (cst instanceof Long) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "long constant replaced with " + 1);
        if (this.context.shouldMutate(newId)) {
            this.mv.visitLdcInsn(new Long(1L));
        } else { 
            super.visitLdcInsn(cst);
        }
      } else {
        super.visitLdcInsn(cst);
      }
  }
  
  @Override
  public void visitInsn(final int opcode) {
      if (opcode == Opcodes.ICONST_M1 || opcode == Opcodes.ICONST_0 || opcode == Opcodes.ICONST_1 || opcode == Opcodes.ICONST_2 || opcode == Opcodes.ICONST_3 || opcode == Opcodes.ICONST_4 || opcode == Opcodes.ICONST_5) {
          final MutationIdentifier newId = this.context.registerMutation(
                    this.factory, "Integer constant replaced with " + 1);
          if (this.context.shouldMutate(newId)) {
              this.mv.visitInsn(Opcodes.ICONST_1);
          } else {
              super.visitInsn(opcode);
          }
      } else if (opcode == Opcodes.DCONST_1 || opcode == Opcodes.DCONST_0) {
          final MutationIdentifier newId = this.context.registerMutation(
                  this.factory, "Double constant replaced with " + 1);
          if (this.context.shouldMutate(newId)) {
              this.mv.visitInsn(Opcodes.DCONST_1);
          } else {
              super.visitInsn(opcode);
          }
      } else if (opcode == Opcodes.FCONST_1 || opcode == Opcodes.FCONST_0 || opcode == Opcodes.FCONST_2) {
          final MutationIdentifier newId = this.context.registerMutation(
                  this.factory, "Float constant replaced with " + 1);
          if (this.context.shouldMutate(newId)) {
              this.mv.visitInsn(Opcodes.FCONST_1);
          } else {
              super.visitInsn(opcode);
          }
      } else if (opcode == Opcodes.LCONST_1 || opcode == Opcodes.LCONST_0) {
          final MutationIdentifier newId = this.context.registerMutation(
                  this.factory, "Long constant replaced with " + 1);
          if (this.context.shouldMutate(newId)) {
              this.mv.visitInsn(Opcodes.LCONST_1);
          } else {
              super.visitInsn(opcode);
          }
      } else {
          super.visitInsn(opcode);
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
            this.factory, "integer constant replaced with " + 0);
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
            this.factory, "double constant replaced with " + 0);
        if (this.context.shouldMutate(newId)) {
            super.visitLdcInsn(new Double("0.0"));
        } else {
            super.visitLdcInsn(cst);
        }
      } else if (cst instanceof Float) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "float constant replaced with " + 0);
        if (this.context.shouldMutate(newId)) {
            super.visitLdcInsn(new Float("0.0"));
        } else {
            super.visitLdcInsn(cst);
        }
      } else if (cst instanceof Long) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "long constant replaced with " + 0);
        if (this.context.shouldMutate(newId)) {
            super.visitLdcInsn(new Long(0L));
        } else {
            super.visitLdcInsn(cst);
        }
      } else {
        super.visitLdcInsn(cst);
      }
  }
  
  @Override
  public void visitInsn(final int opcode) {
      if (opcode == Opcodes.ICONST_M1 || opcode == Opcodes.ICONST_0 || opcode == Opcodes.ICONST_1 || opcode == Opcodes.ICONST_2 || opcode == Opcodes.ICONST_3 || opcode == Opcodes.ICONST_4 || opcode == Opcodes.ICONST_5) {
          final MutationIdentifier newId = this.context.registerMutation(
                    this.factory, "Integer constant replaced with " + 0);
          if (this.context.shouldMutate(newId)) {
              this.mv.visitInsn(Opcodes.ICONST_0);
          } else {
              super.visitInsn(opcode);
          }
      } else if (opcode == Opcodes.DCONST_1 || opcode == Opcodes.DCONST_0) {
          final MutationIdentifier newId = this.context.registerMutation(
                  this.factory, "Double constant replaced with " + 0);
          if (this.context.shouldMutate(newId)) {
              this.mv.visitInsn(Opcodes.DCONST_0);
          } else {
              super.visitInsn(opcode);
          }
      } else if (opcode == Opcodes.FCONST_1 || opcode == Opcodes.FCONST_0 || opcode == Opcodes.FCONST_2) {
          final MutationIdentifier newId = this.context.registerMutation(
                  this.factory, "Float constant replaced with " + 0);
          if (this.context.shouldMutate(newId)) {
              this.mv.visitInsn(Opcodes.FCONST_0);
          } else {
              super.visitInsn(opcode);
          }
      } else if (opcode == Opcodes.LCONST_1 || opcode == Opcodes.LCONST_0) {
          final MutationIdentifier newId = this.context.registerMutation(
                  this.factory, "Long constant replaced with " + 0);
          if (this.context.shouldMutate(newId)) {
              this.mv.visitInsn(Opcodes.LCONST_0);
          } else {
              super.visitInsn(opcode);
          }
      } else {
          super.visitInsn(opcode);
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
            this.factory, "integer constant replaced with it's negation");
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
            this.factory, "double constant replaced with it's negation");
        if (this.context.shouldMutate(newId)) {
            this.mv.visitLdcInsn(new Double(-((Double) cst).doubleValue()));
        } else {
            super.visitLdcInsn(cst);
        }
      } else if (cst instanceof Float) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "float constant replaced with it's negation");
        if (this.context.shouldMutate(newId)) {
            this.mv.visitLdcInsn(new Float(-((Float) cst).floatValue()));
        } else {
            super.visitLdcInsn(cst);
        }
      } else if (cst instanceof Long) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "long constant replaced with it's negation");
        if (this.context.shouldMutate(newId)) {
            this.mv.visitLdcInsn(new Long(-((Long) cst).longValue()));
        } else {
            super.visitLdcInsn(cst);
        }
      } else {
        super.visitLdcInsn(cst);
      }
  }
  
  @Override
  public void visitInsn(final int opcode) {
      if (opcode == Opcodes.ICONST_M1 || opcode == Opcodes.ICONST_0 || opcode == Opcodes.ICONST_1 || opcode == Opcodes.ICONST_2 || opcode == Opcodes.ICONST_3 || opcode == Opcodes.ICONST_4 || opcode == Opcodes.ICONST_5) {
          final MutationIdentifier newId = this.context.registerMutation(
                    this.factory, "Integer constant replaced with it's negation");
          if (this.context.shouldMutate(newId)) {
              this.mv.visitInsn(opcode);
              this.mv.visitInsn(Opcodes.ICONST_M1);
              this.mv.visitInsn(Opcodes.IMUL);
          } else {
              super.visitInsn(opcode);
          }
      } else if (opcode == Opcodes.DCONST_1 || opcode == Opcodes.DCONST_0) {
          final MutationIdentifier newId = this.context.registerMutation(
                  this.factory, "Double constant replaced with it's negation");
          if (this.context.shouldMutate(newId)) {
              this.mv.visitInsn(opcode);
              this.mv.visitLdcInsn(new Double("-1.0"));
              this.visitInsn(Opcodes.DMUL);
          } else {
              super.visitInsn(opcode);
          }
      } else if (opcode == Opcodes.FCONST_1 || opcode == Opcodes.FCONST_0 || opcode == Opcodes.FCONST_2) {
          final MutationIdentifier newId = this.context.registerMutation(
                  this.factory, "Float constant replaced with it's negation");
          if (this.context.shouldMutate(newId)) {
              this.mv.visitInsn(opcode);
              this.mv.visitLdcInsn(new Float("-1.0"));
              this.visitInsn(Opcodes.FMUL);
          } else {
              super.visitInsn(opcode);
          }
      } else if (opcode == Opcodes.LCONST_1 || opcode == Opcodes.LCONST_0) {
          final MutationIdentifier newId = this.context.registerMutation(
                  this.factory, "Long constant replaced with it's negation");
          if (this.context.shouldMutate(newId)) {
              this.mv.visitInsn(opcode);
              this.mv.visitLdcInsn(new Long(-1L));
              this.visitInsn(Opcodes.FMUL);
          } else {
              super.visitInsn(opcode);
          }
      } else {
          super.visitInsn(opcode);
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
            this.factory, "integer constant incremented by 1");
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
            this.factory, "double constant incremented by 1");
        if (this.context.shouldMutate(newId)) {
            this.mv.visitLdcInsn(new Double(((Double) cst).doubleValue() + 1));
        } else {
            super.visitLdcInsn(cst);
        }
      } else if (cst instanceof Float) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "float constant incremented by 1");
        if (this.context.shouldMutate(newId)) {
            this.mv.visitLdcInsn(new Float(((Float) cst).floatValue() + 1));
        } else {
            super.visitLdcInsn(cst);
        }
      } else if (cst instanceof Long) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "long constant incremented by 1");
        if (this.context.shouldMutate(newId)) {
            this.mv.visitLdcInsn(new Long(((Long) cst).longValue() + 1));
        } else {
            super.visitLdcInsn(cst);
        }
      } else {
        super.visitLdcInsn(cst);
      }
  }
  
  public void visitInsn(final int opcode) {
      if (opcode == Opcodes.ICONST_M1 || opcode == Opcodes.ICONST_0 || opcode == Opcodes.ICONST_1 || opcode == Opcodes.ICONST_2 || opcode == Opcodes.ICONST_3 || opcode == Opcodes.ICONST_4 || opcode == Opcodes.ICONST_5) {
          final MutationIdentifier newId = this.context.registerMutation(
                    this.factory, "Integer constant incremented by 1");
          if (this.context.shouldMutate(newId)) {
              this.mv.visitInsn(opcode);
              this.mv.visitInsn(Opcodes.ICONST_1);
              this.mv.visitInsn(Opcodes.IADD);
          } else {
              super.visitInsn(opcode);
          }
      } else if (opcode == Opcodes.DCONST_1 || opcode == Opcodes.DCONST_0) {
          final MutationIdentifier newId = this.context.registerMutation(
                  this.factory, "Double constant incremented by 1");
          if (this.context.shouldMutate(newId)) {
              this.mv.visitInsn(opcode);
              this.mv.visitLdcInsn(new Double("1.0"));
              this.visitInsn(Opcodes.DADD);
          } else {
              super.visitInsn(opcode);
          }
      } else if (opcode == Opcodes.FCONST_1 || opcode == Opcodes.FCONST_0 || opcode == Opcodes.FCONST_2) {
          final MutationIdentifier newId = this.context.registerMutation(
                  this.factory, "Float constant incremented by 1");
          if (this.context.shouldMutate(newId)) {
              this.mv.visitInsn(opcode);
              this.mv.visitLdcInsn(new Float("1.0"));
              this.visitInsn(Opcodes.FADD);
          } else {
              super.visitInsn(opcode);
          }
      } else if (opcode == Opcodes.LCONST_1 || opcode == Opcodes.LCONST_0) {
          final MutationIdentifier newId = this.context.registerMutation(
                  this.factory, "Long constant incremented by 1");
          if (this.context.shouldMutate(newId)) {
              this.mv.visitInsn(opcode);
              this.mv.visitLdcInsn(new Long(1L));
              this.visitInsn(Opcodes.FADD);
          } else {
              super.visitInsn(opcode);
          }
      } else {
          super.visitInsn(opcode);
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
            this.factory, "integer constant decremented by 1");
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
            this.factory, "double constant decremented by 1");
        if (this.context.shouldMutate(newId)) {
            this.mv.visitLdcInsn(new Double(((Double) cst).doubleValue() - 1));
        } else {
            super.visitLdcInsn(cst);
        }
      } else if (cst instanceof Float) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "float constant decremented by 1");
        if (this.context.shouldMutate(newId)) {
            this.mv.visitLdcInsn(new Float(((Float) cst).floatValue() - 1));
        } else {
            super.visitLdcInsn(cst);
        }
      } else if (cst instanceof Long) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "long constant decremented by 1");
        if (this.context.shouldMutate(newId)) {
            this.mv.visitLdcInsn(new Long(((Long) cst).longValue() - 1));
        } else {
            super.visitLdcInsn(cst);
        }
      } else {
        super.visitLdcInsn(cst);
      }
  }
  
  public void visitInsn(final int opcode) {
      if (opcode == Opcodes.ICONST_M1 || opcode == Opcodes.ICONST_0 || opcode == Opcodes.ICONST_1 || opcode == Opcodes.ICONST_2 || opcode == Opcodes.ICONST_3 || opcode == Opcodes.ICONST_4 || opcode == Opcodes.ICONST_5) {
          final MutationIdentifier newId = this.context.registerMutation(
                    this.factory, "Integer constant decremented by 1");
          if (this.context.shouldMutate(newId)) {
              this.mv.visitInsn(opcode);
              this.mv.visitInsn(Opcodes.ICONST_M1);
              this.mv.visitInsn(Opcodes.IADD);
          } else {
              super.visitInsn(opcode);
          }
      } else if (opcode == Opcodes.DCONST_1 || opcode == Opcodes.DCONST_0) {
          final MutationIdentifier newId = this.context.registerMutation(
                  this.factory, "Double constant decremented by 1");
          if (this.context.shouldMutate(newId)) {
              this.mv.visitInsn(opcode);
              this.mv.visitLdcInsn(new Double("-1.0"));
              this.visitInsn(Opcodes.DADD);
          } else {
              super.visitInsn(opcode);
          }
      } else if (opcode == Opcodes.FCONST_1 || opcode == Opcodes.FCONST_0 || opcode == Opcodes.FCONST_2) {
          final MutationIdentifier newId = this.context.registerMutation(
                  this.factory, "Float constant decremented by 1");
          if (this.context.shouldMutate(newId)) {
              this.mv.visitInsn(opcode);
              this.mv.visitLdcInsn(new Float("-1.0"));
              this.visitInsn(Opcodes.FADD);
          } else {
              super.visitInsn(opcode);
          }
      } else if (opcode == Opcodes.LCONST_1 || opcode == Opcodes.LCONST_0) {
          final MutationIdentifier newId = this.context.registerMutation(
                  this.factory, "Long constant decremented by 1");
          if (this.context.shouldMutate(newId)) {
              this.mv.visitInsn(opcode);
              this.mv.visitLdcInsn(new Long(-1L));
              this.visitInsn(Opcodes.FADD);
          } else {
              super.visitInsn(opcode);
          }
      } else {
          super.visitInsn(opcode);
      }
  }

}