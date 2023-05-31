/*
 * Copyright © 2021-present Arcade Data Ltd (info@arcadedata.com)
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
 *
 * SPDX-FileCopyrightText: 2021-present Arcade Data Ltd (info@arcadedata.com)
 * SPDX-License-Identifier: Apache-2.0
 */
/* Generated By:JJTree: Do not edit this line. ORebuildIndexStatement.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_USERTYPE_VISIBILITY_PUBLIC=true */
package com.arcadedb.query.sql.parser;

import com.arcadedb.database.Database;
import com.arcadedb.exception.CommandExecutionException;
import com.arcadedb.index.Index;
import com.arcadedb.index.TypeIndex;
import com.arcadedb.index.lsm.LSMTreeIndexAbstract;
import com.arcadedb.log.LogManager;
import com.arcadedb.query.sql.executor.CommandContext;
import com.arcadedb.query.sql.executor.InternalResultSet;
import com.arcadedb.query.sql.executor.ResultInternal;
import com.arcadedb.query.sql.executor.ResultSet;
import com.arcadedb.schema.EmbeddedSchema;

import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.logging.*;

public class RebuildIndexStatement extends DDLStatement {
  protected            boolean    all      = false;
  protected            Identifier name;
  private static final int        pageSize = LSMTreeIndexAbstract.DEF_PAGE_SIZE;

  public RebuildIndexStatement(final int id) {
    super(id);
  }

  @Override
  public ResultSet executeDDL(final CommandContext context) {
    final ResultInternal result = new ResultInternal();
    result.setProperty("operation", "rebuild index");

    final AtomicLong total = new AtomicLong();

    final Database database = context.getDatabase();
    database.transaction(() -> {
      final Index.BuildIndexCallback callback = (document, totalIndexed) -> {
        total.incrementAndGet();

        if (totalIndexed % 100000 == 0) {
          System.out.print(".");
          System.out.flush();
        }
      };

      final List<String> indexList = new ArrayList<>();

      if (all) {
        final Index[] indexes = database.getSchema().getIndexes();

        for (final Index idx : indexes) {
          try {
            if (idx instanceof TypeIndex) {
              final EmbeddedSchema.INDEX_TYPE indexType = idx.getType();
              final boolean unique = idx.isUnique();
              final List<String> propNames = idx.getPropertyNames();
              final String typeName = idx.getTypeName();
              final int pageSize = idx.getPageSize();
              final LSMTreeIndexAbstract.NULL_STRATEGY nullStrategy = idx.getNullStrategy();

              database.getSchema().dropIndex(idx.getName());

              database.getSchema().buildTypeIndex(typeName, propNames.toArray(new String[propNames.size()])).withType(indexType).withUnique(unique)
                  .withPageSize(LSMTreeIndexAbstract.DEF_PAGE_SIZE).withNullStrategy(nullStrategy).withCallback(callback).create();

              indexList.add(idx.getName());
            }
          } catch (final Exception e) {
            LogManager.instance().log(this, Level.SEVERE, "Error on rebuilding index '%s'", e, idx.getName());
          }
        }

      } else {
        final Index idx = database.getSchema().getIndexByName(name.getValue());
        if (idx == null)
          throw new CommandExecutionException("Index '" + name + "' not found");

        if (!idx.isAutomatic())
          throw new CommandExecutionException("Cannot rebuild index '" + name + "' because it's manual and there aren't indications of what to index");

        final EmbeddedSchema.INDEX_TYPE type = idx.getType();
        final String typeName = idx.getTypeName();
        final boolean unique = idx.isUnique();
        final List<String> propertyNames = idx.getPropertyNames();
        final LSMTreeIndexAbstract.NULL_STRATEGY nullStrategy = idx.getNullStrategy();

        database.getSchema().dropIndex(idx.getName());

        if (typeName != null && idx instanceof TypeIndex) {
          database.getSchema().getType(typeName)
              .createTypeIndex(type, unique, propertyNames.toArray(new String[propertyNames.size()]), LSMTreeIndexAbstract.DEF_PAGE_SIZE, nullStrategy,
                  callback);
        } else {
          database.getSchema().buildBucketIndex(typeName, database.getSchema().getBucketById(idx.getAssociatedBucketId()).getName(),
                  propertyNames.toArray(new String[propertyNames.size()])).withType(type).withUnique(unique).withPageSize(pageSize).withCallback(callback)
              .withNullStrategy(nullStrategy).create();
        }

        indexList.add(idx.getName());
      }
      result.setProperty("indexes", indexList);
      result.setProperty("totalIndexed", total.get());
    });

    final InternalResultSet rs = new InternalResultSet();
    rs.add(result);
    return rs;
  }

  @Override
  public void toString(final Map<String, Object> params, final StringBuilder builder) {
    builder.append("REBUILD INDEX ");
    if (all) {
      builder.append("*");
    } else {
      name.toString(params, builder);
    }
  }

  @Override
  public RebuildIndexStatement copy() {
    final RebuildIndexStatement result = new RebuildIndexStatement(-1);
    result.all = all;
    result.name = name == null ? null : name.copy();
    return result;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    final RebuildIndexStatement that = (RebuildIndexStatement) o;

    if (all != that.all)
      return false;
    return Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    int result = (all ? 1 : 0);
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }
}
/* JavaCC - OriginalChecksum=baca3c54112f1c08700ebdb691fa85bd (do not edit this line) */
