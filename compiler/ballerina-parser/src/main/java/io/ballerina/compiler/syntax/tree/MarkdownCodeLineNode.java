/*
 *  Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package io.ballerina.compiler.syntax.tree;

import io.ballerina.compiler.internal.parser.tree.STNode;

import java.util.Objects;

/**
 * This is a generated syntax tree node.
 *
 * @since 2.0.0
 */
public class MarkdownCodeLineNode extends DocumentationNode {

    public MarkdownCodeLineNode(STNode internalNode, int position, NonTerminalNode parent) {
        super(internalNode, position, parent);
    }

    public Token hashToken() {
        return childInBucket(0);
    }

    public Token CodeDescription() {
        return childInBucket(1);
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <T> T apply(NodeTransformer<T> visitor) {
        return visitor.transform(this);
    }

    @Override
    protected String[] childNames() {
        return new String[]{
                "hashToken",
                "CodeDescription"};
    }

    public MarkdownCodeLineNode modify(
            Token hashToken,
            Token CodeDescription) {
        if (checkForReferenceEquality(
                hashToken,
                CodeDescription)) {
            return this;
        }

        return NodeFactory.createMarkdownCodeLineNode(
                hashToken,
                CodeDescription);
    }

    public MarkdownCodeLineNodeModifier modify() {
        return new MarkdownCodeLineNodeModifier(this);
    }

    /**
     * This is a generated tree node modifier utility.
     *
     * @since 2.0.0
     */
    public static class MarkdownCodeLineNodeModifier {
        private final MarkdownCodeLineNode oldNode;
        private Token hashToken;
        private Token CodeDescription;

        public MarkdownCodeLineNodeModifier(MarkdownCodeLineNode oldNode) {
            this.oldNode = oldNode;
            this.hashToken = oldNode.hashToken();
            this.CodeDescription = oldNode.CodeDescription();
        }

        public MarkdownCodeLineNodeModifier withHashToken(
                Token hashToken) {
            Objects.requireNonNull(hashToken, "hashToken must not be null");
            this.hashToken = hashToken;
            return this;
        }

        public MarkdownCodeLineNodeModifier withCodeDescription(
                Token CodeDescription) {
            Objects.requireNonNull(CodeDescription, "CodeDescription must not be null");
            this.CodeDescription = CodeDescription;
            return this;
        }

        public MarkdownCodeLineNode apply() {
            return oldNode.modify(
                    hashToken,
                    CodeDescription);
        }
    }
}
