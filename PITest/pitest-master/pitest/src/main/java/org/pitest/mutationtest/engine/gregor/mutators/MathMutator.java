/*
 * Copyright 2010 Henry Coles
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package org.pitest.mutationtest.engine.gregor.mutators;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.AbstractInsnMutator;
import org.pitest.mutationtest.engine.gregor.InsnSubstitution;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.pitest.mutationtest.engine.gregor.ZeroOperandMutation;

public enum MathMutator implements MethodMutatorFactory {

  MATH_MUTATOR;

  @Override
  public MethodVisitor create(final MutationContext context,
      final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
    return new MathMethodVisitor(this, methodInfo, context, methodVisitor);
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

class MathMethodVisitor extends AbstractInsnMutator {

  MathMethodVisitor(final MethodMutatorFactory factory,
      final MethodInfo methodInfo, final MutationContext context,
      final MethodVisitor writer) {
    super(factory, methodInfo, context, writer);
  }

  private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<Integer, ZeroOperandMutation>();

  static {
    MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.ISUB,
        "Replaced integer addition with subtraction"));
    MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.IMUL,
            "Replaced integer addition with multiplication"));
    MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.IDIV,
            "Replaced integer addition with division"));
    MUTATIONS.put(Opcodes.IADD, new InsnSubstitution(Opcodes.IREM,
            "Replaced integer modulus with multiplication"));
    //end of addition mutators

    MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.IADD,
        "Replaced integer subtraction with addition"));
    MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.IMUL,
            "Replaced integer subtraction with multiplication"));
    MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.IDIV,
            "Replaced integer subtraction with division"));
    MUTATIONS.put(Opcodes.ISUB, new InsnSubstitution(Opcodes.IREM,
            "Replaced integer modulus with multiplication"));
    //end of subtraction mutators

    MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.IDIV,
        "Replaced integer multiplication with division"));
    MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.ISUB,
            "Replaced integer multiplication with subtraction"));
    MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.IADD,
            "Replaced integer multiplication with addition"));
    MUTATIONS.put(Opcodes.IMUL, new InsnSubstitution(Opcodes.IREM,
            "Replaced integer modulus with multiplication"));
    //end of multiplication mutators

    MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.IMUL,
        "Replaced integer division with multiplication"));
    MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.IADD,
            "Replaced integer division with addition"));
    MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.ISUB,
            "Replaced integer division with subtraction"));
    MUTATIONS.put(Opcodes.IDIV, new InsnSubstitution(Opcodes.IREM,
            "Replaced integer modulus with multiplication"));
    //end of division mutators

    MUTATIONS.put(Opcodes.IOR, new InsnSubstitution(Opcodes.IAND,
        "Replaced bitwise OR with AND"));
    MUTATIONS.put(Opcodes.IAND, new InsnSubstitution(Opcodes.IOR,
        "Replaced bitwise AND with OR"));

    MUTATIONS.put(Opcodes.IREM, new InsnSubstitution(Opcodes.IMUL,
        "Replaced integer modulus with multiplication"));
    MUTATIONS.put(Opcodes.IREM, new InsnSubstitution(Opcodes.IADD,
        "Replaced integer modulus with multiplication"));
    MUTATIONS.put(Opcodes.IREM, new InsnSubstitution(Opcodes.ISUB,
        "Replaced integer modulus with multiplication"));
    MUTATIONS.put(Opcodes.IREM, new InsnSubstitution(Opcodes.IDIV,
        "Replaced integer modulus with multiplication"));
    //end of modulo mutators
    
    MUTATIONS.put(Opcodes.IXOR, new InsnSubstitution(Opcodes.IAND,
        "Replaced XOR with AND"));
    MUTATIONS.put(Opcodes.ISHL, new InsnSubstitution(Opcodes.ISHR,
        "Replaced Shift Left with Shift Right"));
    MUTATIONS.put(Opcodes.ISHR, new InsnSubstitution(Opcodes.ISHL,
        "Replaced Shift Right with Shift Left"));
    MUTATIONS.put(Opcodes.IUSHR, new InsnSubstitution(Opcodes.ISHL,
        "Replaced Unsigned Shift Right with Shift Left"));

    
    // longs
    MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.LSUB,
            "Replaced integer addition with subtraction"));
    MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.LMUL,
            "Replaced integer addition with multiplication"));
    MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.LDIV,
            "Replaced integer addition with division"));
    MUTATIONS.put(Opcodes.LADD, new InsnSubstitution(Opcodes.LREM,
            "Replaced integer modulus with multiplication"));
        //end of addition mutators
        
    MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.LADD,
            "Replaced integer subtraction with addition"));
    MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.LMUL,
            "Replaced integer subtraction with multiplication"));
    MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.LDIV,
            "Replaced integer subtraction with division"));
    MUTATIONS.put(Opcodes.LSUB, new InsnSubstitution(Opcodes.LREM,
            "Replaced integer modulus with multiplication"));
    //end of subtraction mutators
        
    MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.LDIV,
            "Replaced integer multiplication with division"));
    MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.LSUB,
            "Replaced integer multiplication with subtraction"));
    MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.LADD,
            "Replaced integer multiplication with addition"));
    MUTATIONS.put(Opcodes.LMUL, new InsnSubstitution(Opcodes.LREM,
            "Replaced integer modulus with multiplication"));
    //end of multiplication mutators

    MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.LMUL,
        "Replaced integer division with multiplication"));
    MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.LADD,
            "Replaced integer division with addition"));
    MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.LSUB,
            "Replaced integer division with subtraction"));
    MUTATIONS.put(Opcodes.LDIV, new InsnSubstitution(Opcodes.LREM,
            "Replaced integer modulus with multiplication"));
    //end of division mutators
    
    MUTATIONS.put(Opcodes.LOR, new InsnSubstitution(Opcodes.LAND,
        "Replaced bitwise OR with AND"));
    MUTATIONS.put(Opcodes.LAND, new InsnSubstitution(Opcodes.LOR,
        "Replaced bitwise AND with OR"));
    
    MUTATIONS.put(Opcodes.LREM, new InsnSubstitution(Opcodes.LMUL,
            "Replaced integer modulus with multiplication"));
    MUTATIONS.put(Opcodes.LREM, new InsnSubstitution(Opcodes.LADD,
        "Replaced integer modulus with multiplication"));
    MUTATIONS.put(Opcodes.LREM, new InsnSubstitution(Opcodes.LSUB,
        "Replaced integer modulus with multiplication"));
    MUTATIONS.put(Opcodes.LREM, new InsnSubstitution(Opcodes.LDIV,
        "Replaced integer modulus with multiplication"));
    //end of modulo mutators
    
    MUTATIONS.put(Opcodes.LXOR, new InsnSubstitution(Opcodes.LAND,
        "Replaced XOR with AND"));
    MUTATIONS.put(Opcodes.LSHL, new InsnSubstitution(Opcodes.LSHR,
        "Replaced Shift Left with Shift Right"));
    MUTATIONS.put(Opcodes.LSHR, new InsnSubstitution(Opcodes.LSHL,
        "Replaced Shift Right with Shift Left"));
    MUTATIONS.put(Opcodes.LUSHR, new InsnSubstitution(Opcodes.LSHL,
        "Replaced Unsigned Shift Right with Shift Left"));

    
    // floats
    MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.FSUB,
            "Replaced integer addition with subtraction"));
    MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.FMUL,
            "Replaced integer addition with multiplication"));
    MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.FDIV,
            "Replaced integer addition with division"));
    MUTATIONS.put(Opcodes.FADD, new InsnSubstitution(Opcodes.FREM,
            "Replaced integer modulus with multiplication"));
        //end of addition mutators
        
    MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.FADD,
            "Replaced integer subtraction with addition"));
    MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.FMUL,
            "Replaced integer subtraction with multiplication"));
    MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.FDIV,
            "Replaced integer subtraction with division"));
    MUTATIONS.put(Opcodes.FSUB, new InsnSubstitution(Opcodes.FREM,
            "Replaced integer modulus with multiplication"));
    //end of subtraction mutators
        
    MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.FDIV,
            "Replaced integer multiplication with division"));
    MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.FSUB,
            "Replaced integer multiplication with subtraction"));
    MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.FADD,
            "Replaced integer multiplication with addition"));
    MUTATIONS.put(Opcodes.FMUL, new InsnSubstitution(Opcodes.FREM,
            "Replaced integer modulus with multiplication"));
    //end of multiplication mutators

    MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.FMUL,
        "Replaced integer division with multiplication"));
    MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.FADD,
            "Replaced integer division with addition"));
    MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.FSUB,
            "Replaced integer division with subtraction"));
    MUTATIONS.put(Opcodes.FDIV, new InsnSubstitution(Opcodes.FREM,
            "Replaced integer modulus with multiplication"));
    //end of division mutators
    
    MUTATIONS.put(Opcodes.FREM, new InsnSubstitution(Opcodes.FMUL,
            "Replaced integer modulus with multiplication"));
    MUTATIONS.put(Opcodes.FREM, new InsnSubstitution(Opcodes.FADD,
        "Replaced integer modulus with multiplication"));
    MUTATIONS.put(Opcodes.FREM, new InsnSubstitution(Opcodes.FSUB,
        "Replaced integer modulus with multiplication"));
    MUTATIONS.put(Opcodes.FREM, new InsnSubstitution(Opcodes.FDIV,
        "Replaced integer modulus with multiplication"));
    //end of modulo mutators

    // doubles
    MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.DSUB,
            "Replaced integer addition with subtraction"));
    MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.DMUL,
            "Replaced integer addition with multiplication"));
    MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.DDIV,
            "Replaced integer addition with division"));
    MUTATIONS.put(Opcodes.DADD, new InsnSubstitution(Opcodes.DREM,
            "Replaced integer modulus with multiplication"));
        //end of addition mutators
        
    MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.DADD,
            "Replaced integer subtraction with addition"));
    MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.DMUL,
            "Replaced integer subtraction with multiplication"));
    MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.DDIV,
            "Replaced integer subtraction with division"));
    MUTATIONS.put(Opcodes.DSUB, new InsnSubstitution(Opcodes.DREM,
            "Replaced integer modulus with multiplication"));
    //end of subtraction mutators
        
    MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.DDIV,
            "Replaced integer multiplication with division"));
    MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.DSUB,
            "Replaced integer multiplication with subtraction"));
    MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.DADD,
            "Replaced integer multiplication with addition"));
    MUTATIONS.put(Opcodes.DMUL, new InsnSubstitution(Opcodes.DREM,
            "Replaced integer modulus with multiplication"));
    //end of multiplication mutators

    MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DMUL,
        "Replaced integer division with multiplication"));
    MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DADD,
            "Replaced integer division with addition"));
    MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DSUB,
            "Replaced integer division with subtraction"));
    MUTATIONS.put(Opcodes.DDIV, new InsnSubstitution(Opcodes.DREM,
            "Replaced integer modulus with multiplication"));
    //end of division mutators
    
    MUTATIONS.put(Opcodes.DREM, new InsnSubstitution(Opcodes.DMUL,
            "Replaced integer modulus with multiplication"));
    MUTATIONS.put(Opcodes.DREM, new InsnSubstitution(Opcodes.DADD,
        "Replaced integer modulus with multiplication"));
    MUTATIONS.put(Opcodes.DREM, new InsnSubstitution(Opcodes.DSUB,
        "Replaced integer modulus with multiplication"));
    MUTATIONS.put(Opcodes.DREM, new InsnSubstitution(Opcodes.DDIV,
        "Replaced integer modulus with multiplication"));
    //end of modulo mutators

  }

  @Override
  protected Map<Integer, ZeroOperandMutation> getMutations() {
    return MUTATIONS;
  }

}
