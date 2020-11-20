/*
 * Copyright (c) 2020, WSO2 Inc. (http://wso2.com) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ballerinalang.langserver.completions.providers.context;

import io.ballerina.compiler.syntax.tree.NonTerminalNode;
import io.ballerina.compiler.syntax.tree.QualifiedNameReferenceNode;
import io.ballerina.compiler.syntax.tree.StreamTypeParamsNode;
import org.ballerinalang.annotation.JavaSPIService;
import org.ballerinalang.langserver.common.utils.completion.QNameReferenceUtil;
import org.ballerinalang.langserver.commons.CompletionContext;
import org.ballerinalang.langserver.commons.completion.LSCompletionException;
import org.ballerinalang.langserver.commons.completion.LSCompletionItem;
import org.ballerinalang.langserver.completions.providers.AbstractCompletionProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Completion Provider for {@link StreamTypeParamsNode} context.
 *
 * @since 2.0.0
 */
@JavaSPIService("org.ballerinalang.langserver.commons.completion.spi.CompletionProvider")
public class StreamTypeParamsNodeContext extends AbstractCompletionProvider<StreamTypeParamsNode> {

    public StreamTypeParamsNodeContext() {
        super(StreamTypeParamsNode.class);
    }

    @Override
    public List<LSCompletionItem> getCompletions(CompletionContext context, StreamTypeParamsNode node)
            throws LSCompletionException {
        NonTerminalNode nodeAtCursor = context.getNodeAtCursor();

        if (this.onQualifiedNameIdentifier(context, nodeAtCursor)) {
            QualifiedNameReferenceNode refNode = ((QualifiedNameReferenceNode) nodeAtCursor);
            return this.getCompletionItemList(QNameReferenceUtil.getTypesInModule(context, refNode), context);
        }

        List<LSCompletionItem> completionItems = new ArrayList<>(this.getModuleCompletionItems(context));
        completionItems.addAll(this.getTypeItems(context));

        return completionItems;
    }
}
