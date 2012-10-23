/**
 *  Copyright 2012 Douglas Campos, and individual contributors
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.dynjs.parser.ast;

import me.qmx.jitescript.CodeBlock;

import org.dynjs.compiler.CodeBlockUtils;
import org.dynjs.parser.CodeVisitor;
import org.dynjs.runtime.BlockManager;
import org.dynjs.runtime.ExecutionContext;

public class FunctionExpression extends AbstractExpression {

    private BlockManager blockManager;
    private FunctionDescriptor descriptor;

    public FunctionExpression(BlockManager blockManager, FunctionDescriptor descriptor) {
        super(descriptor.getTree());
        this.blockManager = blockManager;
        this.descriptor = descriptor;
    }

    public FunctionDescriptor getDescriptor() {
        return this.descriptor;
    }

    public CodeBlock getCodeBlock() {
        return CodeBlockUtils.compiledFunction(blockManager, descriptor.getFormalParameters(), descriptor.getBlock(), descriptor.isStrict());
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("function").append(this.descriptor.getIdentifier() == null ? "" : " " + this.descriptor.getIdentifier()).append("(");
        String[] params = this.descriptor.getFormalParameters();
        for (int i = 0; i < params.length; ++i) {
            if (i > 0) {
                buf.append(", ");
            }
            buf.append(params[i]);
        }
        buf.append(") {");
        buf.append(this.descriptor.getBlock().toIndentedString("  "));
        buf.append("}");
        return buf.toString();

    }

    @Override
    public void accept(ExecutionContext context, CodeVisitor visitor, boolean strict) {
        visitor.visit( context, this, strict );
    }

}
