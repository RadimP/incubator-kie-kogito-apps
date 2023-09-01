/*
 * Copyright 2023 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.kogito.index.mongodb.query;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.kie.kogito.index.model.ProcessDefinition;
import org.kie.kogito.index.mongodb.model.ProcessDefinitionEntity;
import org.kie.kogito.index.mongodb.model.ProcessDefinitionEntityMapper;
import org.kie.kogito.index.test.query.AbstractProcessDefinitionQueryIT;
import org.kie.kogito.persistence.api.Storage;
import org.kie.kogito.persistence.mongodb.client.MongoClientManager;
import org.kie.kogito.persistence.mongodb.storage.MongoStorage;
import org.kie.kogito.testcontainers.quarkus.MongoDBQuarkusTestResource;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

import static org.kie.kogito.index.storage.Constants.PROCESS_DEFINITIONS_STORAGE;

@QuarkusTest
@QuarkusTestResource(MongoDBQuarkusTestResource.class)
class ProcessDefinitionQueryIT extends AbstractProcessDefinitionQueryIT {

    @Inject
    MongoClientManager mongoClientManager;

    Storage<String, ProcessDefinition> storage;

    @BeforeEach
    void setUp() {
        this.storage = new MongoStorage<>(mongoClientManager.getCollection(PROCESS_DEFINITIONS_STORAGE, ProcessDefinitionEntity.class),
                ProcessDefinition.class.getName(), new ProcessDefinitionEntityMapper());
    }

    @Override
    public Storage<String, ProcessDefinition> getStorage() {
        return storage;
    }
}
