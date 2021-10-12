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
 */
/* Generated By:JJTree: Do not edit this line. OAndBlock.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=O,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_USERTYPE_VISIBILITY_PUBLIC=true */
package com.arcadedb.query.sql.parser;

import com.arcadedb.database.Database;
import com.arcadedb.database.Identifiable;
import com.arcadedb.query.sql.executor.CommandContext;
import com.arcadedb.query.sql.executor.Result;
import com.arcadedb.schema.DocumentType;

import java.util.*;

public class AndBlock extends BooleanExpression {
  List<BooleanExpression> subBlocks = new ArrayList<>();

  public AndBlock(int id) {
    super(id);
  }

  public AndBlock(SqlParser p, int id) {
    super(p, id);
  }

  @Override
  public boolean evaluate(Identifiable currentRecord, CommandContext ctx) {
    if (getSubBlocks() == null) {
      return true;
    }

    for (BooleanExpression block : subBlocks) {
      if (!block.evaluate(currentRecord, ctx)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean evaluate(Result currentRecord, CommandContext ctx) {
    if (getSubBlocks() == null) {
      return true;
    }

    for (BooleanExpression block : subBlocks) {
      if (!block.evaluate(currentRecord, ctx)) {
        return false;
      }
    }
    return true;
  }

  public List<BooleanExpression> getSubBlocks() {
    return subBlocks;
  }

  public void setSubBlocks(List<BooleanExpression> subBlocks) {
    this.subBlocks = subBlocks;
  }

  public void toString(Map<String, Object> params, StringBuilder builder) {
    if (subBlocks == null || subBlocks.size() == 0) {
      return;
    }
    // if (subBlocks.size() == 1) {
    // subBlocks.get(0).toString(params, builder);
    // }

    boolean first = true;
    for (BooleanExpression expr : subBlocks) {
      if (!first) {
        builder.append(" AND ");
      }
      expr.toString(params, builder);
      first = false;
    }
  }

  @Override
  protected boolean supportsBasicCalculation() {
    for (BooleanExpression expr : subBlocks) {
      if (!expr.supportsBasicCalculation()) {
        return false;
      }
    }
    return true;
  }

  @Override
  protected int getNumberOfExternalCalculations() {
    int result = 0;
    for (BooleanExpression expr : subBlocks) {
      result += expr.getNumberOfExternalCalculations();
    }
    return result;
  }

  @Override
  protected List<Object> getExternalCalculationConditions() {
    List<Object> result = new ArrayList<>();
    for (BooleanExpression expr : subBlocks) {
      result.addAll(expr.getExternalCalculationConditions());
    }
    return result;
  }

  public List<BinaryCondition> getIndexedFunctionConditions(DocumentType iSchemaClass, Database database) {
    if (subBlocks == null) {
      return null;
    }
    List<BinaryCondition> result = new ArrayList<>();
    for (BooleanExpression exp : subBlocks) {
      List<BinaryCondition> sub = exp.getIndexedFunctionConditions(iSchemaClass, database);
      if (sub != null && sub.size() > 0) {
        result.addAll(sub);
      }
    }
    return result.size() == 0 ? null : result;
  }

  public List<AndBlock> flatten() {
    List<AndBlock> result = new ArrayList<>();
    boolean first = true;
    for (BooleanExpression sub : subBlocks) {
      List<AndBlock> subFlattened = sub.flatten();
      List<AndBlock> oldResult = result;
      result = new ArrayList<>();
      for (AndBlock subAndItem : subFlattened) {
        if (first) {
          result.add(subAndItem);
        } else {
          for (AndBlock oldResultItem : oldResult) {
            AndBlock block = new AndBlock(-1);
            block.subBlocks.addAll(oldResultItem.subBlocks);
            for (BooleanExpression resultItem : subAndItem.subBlocks) {
              block.subBlocks.add(resultItem);
            }
            result.add(block);
          }
        }
      }
      first = false;
    }
    return result;
  }

  protected AndBlock encapsulateInAndBlock(BooleanExpression item) {
    if (item instanceof AndBlock) {
      return (AndBlock) item;
    }
    AndBlock result = new AndBlock(-1);
    result.subBlocks.add(item);
    return result;
  }

  @Override
  public boolean needsAliases(Set<String> aliases) {
    for (BooleanExpression block : subBlocks) {
      if (block.needsAliases(aliases)) {
        return true;
      }
    }
    return false;
  }

  public AndBlock copy() {
    AndBlock result = new AndBlock(-1);
    for (BooleanExpression exp : subBlocks) {
      result.subBlocks.add(exp.copy());
    }
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    AndBlock andBlock = (AndBlock) o;

    return subBlocks != null ? subBlocks.equals(andBlock.subBlocks) : andBlock.subBlocks == null;
  }

  @Override
  public int hashCode() {
    return subBlocks != null ? subBlocks.hashCode() : 0;
  }

  @Override
  public boolean isEmpty() {
    if (subBlocks.isEmpty()) {
      return true;
    }
    for (BooleanExpression block : subBlocks) {
      if (!block.isEmpty()) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void extractSubQueries(SubQueryCollector collector) {
    for (BooleanExpression exp : subBlocks) {
      exp.extractSubQueries(collector);
    }
  }

  @Override
  public boolean refersToParent() {
    for (BooleanExpression exp : subBlocks) {
      if (exp.refersToParent()) {
        return true;
      }
    }
    return false;
  }

  @Override
  public List<String> getMatchPatternInvolvedAliases() {
    List<String> result = new ArrayList<>();
    for (BooleanExpression exp : subBlocks) {
      List<String> x = exp.getMatchPatternInvolvedAliases();
      if (x != null) {
        result.addAll(x);
      }
    }
    return result.isEmpty() ? null : result;
  }

  @Override
  public boolean isCacheable() {
    for (BooleanExpression exp : subBlocks) {
      if (!exp.isCacheable()) {
        return false;
      }
    }
    return true;
  }

}
/* JavaCC - OriginalChecksum=cf1f66cc86cfc93d357f9fcdfa4a4604 (do not edit this line) */
