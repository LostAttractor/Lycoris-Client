// ASM: a very small and fast Java bytecode manipulation framework
// Copyright (c) 2000-2011 INRIA, France Telecom
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions
// are met:
// 1. Redistributions of source code must retain the above copyright
//    notice, this list of conditions and the following disclaimer.
// 2. Redistributions in binary form must reproduce the above copyright
//    notice, this list of conditions and the following disclaimer in the
//    documentation and/or other materials provided with the distribution.
// 3. Neither the name of the copyright holders nor the names of its
//    contributors may be used to endorse or promote products derived from
//    this software without specific prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
// AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
// ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
// LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
// CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
// SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
// INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
// CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
// ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
// THE POSSIBILITY OF SUCH DAMAGE.
package rbq.lycoris.agent.asm.analysis;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import rbq.lycoris.agent.asm.Opcodes;
import rbq.lycoris.agent.asm.Type;
import rbq.lycoris.agent.asm.tree.AbstractInsnNode;
import rbq.lycoris.agent.asm.tree.FieldInsnNode;
import rbq.lycoris.agent.asm.tree.InvokeDynamicInsnNode;
import rbq.lycoris.agent.asm.tree.LdcInsnNode;
import rbq.lycoris.agent.asm.tree.MethodInsnNode;

/**
 * An {@link Interpreter} for {@link rbq.lycoris.agent.asm.analysis.SourceValue} values.
 *
 * @author Eric Bruneton
 */
public class SourceInterpreter extends Interpreter<rbq.lycoris.agent.asm.analysis.SourceValue> implements Opcodes {

  /**
   * Constructs a new {@link SourceInterpreter} for the latest ASM API version. <i>Subclasses must
   * not use this constructor</i>. Instead, they must use the {@link #SourceInterpreter(int)}
   * version.
   */
  public SourceInterpreter() {
    super(/* latest api = */ ASM9);
    if (getClass() != SourceInterpreter.class) {
      throw new IllegalStateException();
    }
  }

  /**
   * Constructs a new {@link SourceInterpreter}.
   *
   * @param api the ASM API version supported by this interpreter. Must be one of the {@code
   *     ASM}<i>x</i> values in {@link Opcodes}.
   */
  protected SourceInterpreter(final int api) {
    super(api);
  }

  @Override
  public rbq.lycoris.agent.asm.analysis.SourceValue newValue(final Type type) {
    if (type == Type.VOID_TYPE) {
      return null;
    }
    return new rbq.lycoris.agent.asm.analysis.SourceValue(type == null ? 1 : type.getSize());
  }

  @Override
  public rbq.lycoris.agent.asm.analysis.SourceValue newOperation(final AbstractInsnNode insn) {
    int size;
    switch (insn.getOpcode()) {
      case LCONST_0:
      case LCONST_1:
      case DCONST_0:
      case DCONST_1:
        size = 2;
        break;
      case LDC:
        Object value = ((LdcInsnNode) insn).cst;
        size = value instanceof Long || value instanceof Double ? 2 : 1;
        break;
      case GETSTATIC:
        size = Type.getType(((FieldInsnNode) insn).desc).getSize();
        break;
      default:
        size = 1;
        break;
    }
    return new rbq.lycoris.agent.asm.analysis.SourceValue(size, insn);
  }

  @Override
  public rbq.lycoris.agent.asm.analysis.SourceValue copyOperation(final AbstractInsnNode insn, final rbq.lycoris.agent.asm.analysis.SourceValue value) {
    return new rbq.lycoris.agent.asm.analysis.SourceValue(value.getSize(), insn);
  }

  @Override
  public rbq.lycoris.agent.asm.analysis.SourceValue unaryOperation(final AbstractInsnNode insn, final rbq.lycoris.agent.asm.analysis.SourceValue value) {
    int size;
    switch (insn.getOpcode()) {
      case LNEG:
      case DNEG:
      case I2L:
      case I2D:
      case L2D:
      case F2L:
      case F2D:
      case D2L:
        size = 2;
        break;
      case GETFIELD:
        size = Type.getType(((FieldInsnNode) insn).desc).getSize();
        break;
      default:
        size = 1;
        break;
    }
    return new rbq.lycoris.agent.asm.analysis.SourceValue(size, insn);
  }

  @Override
  public rbq.lycoris.agent.asm.analysis.SourceValue binaryOperation(
          final AbstractInsnNode insn, final rbq.lycoris.agent.asm.analysis.SourceValue value1, final rbq.lycoris.agent.asm.analysis.SourceValue value2) {
    int size;
    switch (insn.getOpcode()) {
      case LALOAD:
      case DALOAD:
      case LADD:
      case DADD:
      case LSUB:
      case DSUB:
      case LMUL:
      case DMUL:
      case LDIV:
      case DDIV:
      case LREM:
      case DREM:
      case LSHL:
      case LSHR:
      case LUSHR:
      case LAND:
      case LOR:
      case LXOR:
        size = 2;
        break;
      default:
        size = 1;
        break;
    }
    return new rbq.lycoris.agent.asm.analysis.SourceValue(size, insn);
  }

  @Override
  public rbq.lycoris.agent.asm.analysis.SourceValue ternaryOperation(
      final AbstractInsnNode insn,
      final rbq.lycoris.agent.asm.analysis.SourceValue value1,
      final rbq.lycoris.agent.asm.analysis.SourceValue value2,
      final rbq.lycoris.agent.asm.analysis.SourceValue value3) {
    return new rbq.lycoris.agent.asm.analysis.SourceValue(1, insn);
  }

  @Override
  public rbq.lycoris.agent.asm.analysis.SourceValue naryOperation(
      final AbstractInsnNode insn, final List<? extends rbq.lycoris.agent.asm.analysis.SourceValue> values) {
    int size;
    int opcode = insn.getOpcode();
    if (opcode == MULTIANEWARRAY) {
      size = 1;
    } else if (opcode == INVOKEDYNAMIC) {
      size = Type.getReturnType(((InvokeDynamicInsnNode) insn).desc).getSize();
    } else {
      size = Type.getReturnType(((MethodInsnNode) insn).desc).getSize();
    }
    return new rbq.lycoris.agent.asm.analysis.SourceValue(size, insn);
  }

  @Override
  public void returnOperation(
          final AbstractInsnNode insn, final rbq.lycoris.agent.asm.analysis.SourceValue value, final rbq.lycoris.agent.asm.analysis.SourceValue expected) {
    // Nothing to do.
  }

  @Override
  public rbq.lycoris.agent.asm.analysis.SourceValue merge(final rbq.lycoris.agent.asm.analysis.SourceValue value1, final rbq.lycoris.agent.asm.analysis.SourceValue value2) {
    if (value1.insns instanceof SmallSet && value2.insns instanceof SmallSet) {
      Set<AbstractInsnNode> setUnion =
          ((SmallSet<AbstractInsnNode>) value1.insns)
              .union((SmallSet<AbstractInsnNode>) value2.insns);
      if (setUnion == value1.insns && value1.size == value2.size) {
        return value1;
      } else {
        return new rbq.lycoris.agent.asm.analysis.SourceValue(Math.min(value1.size, value2.size), setUnion);
      }
    }
    if (value1.size != value2.size || !containsAll(value1.insns, value2.insns)) {
      HashSet<AbstractInsnNode> setUnion = new HashSet<>();
      setUnion.addAll(value1.insns);
      setUnion.addAll(value2.insns);
      return new SourceValue(Math.min(value1.size, value2.size), setUnion);
    }
    return value1;
  }

  private static <E> boolean containsAll(final Set<E> self, final Set<E> other) {
    if (self.size() < other.size()) {
      return false;
    }
    return self.containsAll(other);
  }
}
