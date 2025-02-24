/**
 * Copyright 2020 Alibaba Group Holding Limited.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.graphscope.groot.wal.kafka;

import com.alibaba.graphscope.groot.wal.LogEntry;
import com.alibaba.maxgraph.compiler.api.exception.MaxGraphException;
import com.alibaba.maxgraph.proto.groot.LogEntryPb;
import com.google.protobuf.InvalidProtocolBufferException;

import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class LogEntryDeserializer implements Deserializer<LogEntry> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {}

    @Override
    public LogEntry deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        try {
            return LogEntry.parseProto(LogEntryPb.parseFrom(data));
        } catch (InvalidProtocolBufferException e) {
            throw new MaxGraphException(e);
        }
    }

    @Override
    public void close() {}
}
