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
package io.ballerina.projects;

import java.util.Objects;

/**
 * The class {@code CompilationOptions} holds various Ballerina compilation options.
 *
 * @since 2.0.0
 */
class CompilationOptions {
    private Boolean skipTests;
    private Boolean offlineBuild;
    private Boolean experimental;
    private Boolean observabilityIncluded;
    private Boolean dumpBir;
    private String dumpBirFile;
    private String cloud;
    private Boolean taintCheck;
    private Boolean listConflictedClasses;

    public CompilationOptions(Boolean skipTests, Boolean offlineBuild, Boolean experimental,
                              Boolean observabilityIncluded, Boolean dumpBir, String dumpBirFile,
                              String cloud, Boolean taintCheck, Boolean listConflictedClasses) {
        this.skipTests = skipTests;
        this.offlineBuild = offlineBuild;
        this.experimental = experimental;
        this.observabilityIncluded = observabilityIncluded;
        this.dumpBir = dumpBir;
        this.dumpBirFile = dumpBirFile;
        this.cloud = cloud;
        this.taintCheck = taintCheck;
        this.listConflictedClasses = listConflictedClasses;
    }

    boolean skipTests() {
        return toBooleanDefaultIfNull(skipTests);
    }

    boolean offlineBuild() {
        return toBooleanDefaultIfNull(offlineBuild);
    }

    boolean experimental() {
        return toBooleanDefaultIfNull(experimental);
    }

    boolean observabilityIncluded() {
        return toBooleanDefaultIfNull(observabilityIncluded);
    }

    public Boolean dumpBir() {
        return toBooleanDefaultIfNull(dumpBir);
    }

    public String getBirDumpFile() {
        return dumpBirFile;
    }

    public String getCloud() {
        return cloud;
    }

    public boolean getTaintCheck() {
        return toBooleanDefaultIfNull(taintCheck);
    }

    public boolean listConflictedClasses() {
        return toBooleanDefaultIfNull(listConflictedClasses);
    }

    /**
     * Merge the given compilation options by favoring theirs if there are conflicts.
     *
     * @param theirOptions Compilation options to be merged
     * @return a new {@code CompilationOptions} instance that contains our options and their options
     */
    CompilationOptions acceptTheirs(CompilationOptions theirOptions) {

        this.skipTests = Objects.requireNonNullElseGet(
                theirOptions.skipTests, () -> toBooleanDefaultIfNull(this.skipTests));
        this.offlineBuild = Objects.requireNonNullElseGet(
                theirOptions.offlineBuild, () -> toBooleanDefaultIfNull(this.offlineBuild));
        this.experimental = Objects.requireNonNullElseGet(
                theirOptions.experimental, () -> toBooleanDefaultIfNull(this.experimental));
        this.observabilityIncluded = Objects.requireNonNullElseGet(
                theirOptions.observabilityIncluded, () -> toBooleanDefaultIfNull(this.observabilityIncluded));
        this.dumpBir = Objects.requireNonNullElseGet(theirOptions.dumpBir, () -> toBooleanDefaultIfNull(this.dumpBir));
        this.cloud = Objects.requireNonNullElse(theirOptions.cloud, toStringDefaultIfNull(this.cloud));
        this.dumpBirFile = theirOptions.dumpBirFile;
        this.taintCheck = Objects.requireNonNullElseGet(theirOptions.taintCheck,
                () -> toBooleanDefaultIfNull(this.taintCheck));
        this.listConflictedClasses = Objects.requireNonNullElseGet(
                theirOptions.listConflictedClasses, () -> toBooleanDefaultIfNull(this.listConflictedClasses));
        return this;
    }

    private boolean toBooleanDefaultIfNull(Boolean bool) {
        if (bool == null) {
            return false;
        }
        return bool;
    }

    private String toStringDefaultIfNull(String value) {
        if (value == null) {
            return "";
        }
        return value;
    }
}
