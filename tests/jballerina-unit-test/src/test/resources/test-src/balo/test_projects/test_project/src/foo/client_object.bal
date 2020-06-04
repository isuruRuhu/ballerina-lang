// Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
//
// WSO2 Inc. licenses this file to you under the Apache License,
// Version 2.0 (the "License"); you may not use this file except
// in compliance with the License.
// You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.
public type DummyEndpoint client object {
    public remote function invoke1 (string a) returns error? {
        error e = error("i1");
        return e;
    }

    public remote function invoke2 (string a) returns string {
        return "done" + a;
    }
};

DummyEndpoint dyEP = new;

public function getDummyEndpoint() returns DummyEndpoint {
    return dyEP;
}

public type ObjectWithOnlyRemoteMethod client object {
    public string name;
    public string id = "";

    public function __init(string name) {
        self.name = name;
    }
    public remote function send(string message) returns error? {
    }
    public remote function receive(string message) {
    }
};

public type ClientObjectWithoutRemoteMethod client object {
    public string name;
    public string id = "";

    public function __init(string name) {
        self.name = name;
    }
    public function send(string message) returns error? {
    }
    public function receive(string message) {
    }
};

public type ObjectWithRemoteMethod client object {
    public string name;
    public string id = "";

    public function __init(string name) {
        self.name = name;
    }
    public remote function send(string message) returns error? {
    }
    public function receive(string message) {
    }
};
