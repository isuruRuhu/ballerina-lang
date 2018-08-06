/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.ballerinalang.stdlib.math.nativeimpl;

import org.ballerinalang.bre.Context;
import org.ballerinalang.bre.bvm.BlockingNativeCallableUnit;
import org.ballerinalang.model.types.TypeKind;
import org.ballerinalang.model.values.BFloat;
import org.ballerinalang.natives.annotations.Argument;
import org.ballerinalang.natives.annotations.BallerinaFunction;
import org.ballerinalang.natives.annotations.ReturnType;

/**
 * Extern function ballerina.math:hypot.
 *
 * @since 0.94
 */
@BallerinaFunction(
        orgName = "ballerina", packageName = "math",
        functionName = "hypot",
        args = {@Argument(name = "a", type = TypeKind.FLOAT),
                @Argument(name = "b", type = TypeKind.FLOAT)},
        returnType = {@ReturnType(type = TypeKind.FLOAT)},
        isPublic = true
)
public class Hypot extends BlockingNativeCallableUnit {

    public void execute(Context ctx) {
        double a = ctx.getFloatArgument(0);
        double b = ctx.getFloatArgument(1);
        ctx.setReturnValues(new BFloat(Math.hypot(a, b)));
    }
}
