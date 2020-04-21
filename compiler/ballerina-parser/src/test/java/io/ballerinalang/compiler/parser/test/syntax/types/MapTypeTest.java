/*
 * Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package io.ballerinalang.compiler.parser.test.syntax.types;

import org.testng.annotations.Test;

/**
 * Test parsing map type.
 */

public class MapTypeTest extends AbstractTypesTest {

    //Valid source test

    @Test
    public void testValidLocalLevelMapType() {
        testFile("map-type/map_type_assert_01.bal", "map-type/map_type_assert_01.json");
    }

    @Test
    public void testValidModuleLevelMapType() {
        test("map<string> a;", "map-type/map_type_assert_02.json");
    }

    @Test
    public void testValidModuleLevelMapTypeWithUserDefinedType() {
        test("map<T> a;", "map-type/map_type_assert_03.json");
    }

    @Test
    public void testValidModuleLevelMapTypeWithOptionalType() {
        test("map<string?> a;", "map-type/map_type_assert_04.json");
    }

    @Test
    public void testValidModuleLevelMapTypeWithArrayType() {
        test("map<int[][]> a;", "map-type/map_type_assert_05.json");
    }

    @Test
    public void testValidMapTypeDescAsReturnType() {
        testFile("map-type/map_type_assert_02.bal", "map-type/map_type_assert_11.json");
    }

    //Recovery tests

    @Test
    public void testInValidModuleLevelMapTypeWithMissingGT() {
        test("map<int a;", "map-type/map_type_assert_06.json");
    }

    @Test
    public void testInValidModuleLevelMapTypeWithUserDefinedTypeMissingGT() {
        test("map<T a;", "map-type/map_type_assert_07.json");
    }

    @Test
    public void testInValidModuleLevelMapTypeWithMissingLT() {
        test("map int> a;", "map-type/map_type_assert_08.json");
    }

    @Test
    public void testInValidModuleLevelMapTypeWithMissingLTAndGT() {
        test("map int a;", "map-type/map_type_assert_09.json");
    }

    @Test
    public void testInValidModuleLevelMapTypeWithMissingtype() {
        test("map<> a;", "map-type/map_type_assert_10.json");
    }

    @Test
    public void testInValidModuleLevelMapTypeWithInvalidTokenInside() {
        test("map<%> a;", "map-type/map_type_assert_10.json");
    }
}
