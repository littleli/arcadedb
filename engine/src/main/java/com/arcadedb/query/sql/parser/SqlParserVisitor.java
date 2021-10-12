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
/* Generated By:JavaCC: Do not edit this line. SqlParserVisitor.java Version 5.0 */
package com.arcadedb.query.sql.parser;

public interface SqlParserVisitor
{
  Object visit(SimpleNode node, Object data);
  Object visit(Rid node, Object data);
  Object visit(parse node, Object data);
  Object visit(ParseScript node, Object data);
  Object visit(PString node, Object data);
  Object visit(Identifier node, Object data);
  Object visit(PInteger node, Object data);
  Object visit(FloatingPoint node, Object data);
  Object visit(PNumber node, Object data);
  Object visit(Statement node, Object data);
  Object visit(StatementSemicolon node, Object data);
  Object visit(StatementInternal node, Object data);
  Object visit(QueryStatement node, Object data);
  Object visit(SelectWithoutTargetStatement node, Object data);
  Object visit(SelectStatement node, Object data);
  Object visit(TraverseStatement node, Object data);
  Object visit(MatchStatement node, Object data);
  Object visit(DeleteStatement node, Object data);
  Object visit(DeleteVertexStatement node, Object data);
  Object visit(DeleteEdgeStatement node, Object data);
  Object visit(DeleteEdgeByRidStatement node, Object data);
  Object visit(DeleteEdgeFromToStatement node, Object data);
  Object visit(DeleteEdgeToStatement node, Object data);
  Object visit(DeleteEdgeVToStatement node, Object data);
  Object visit(DeleteEdgeWhereStatement node, Object data);
  Object visit(UpdateEdgeStatement node, Object data);
  Object visit(UpdateStatement node, Object data);
  Object visit(UpdateOperations node, Object data);
  Object visit(UpdateItem node, Object data);
  Object visit(UpdateIncrementItem node, Object data);
  Object visit(UpdateRemoveItem node, Object data);
  Object visit(UpdatePutItem node, Object data);
  Object visit(UpdateAddItem node, Object data);
  Object visit(InsertStatement node, Object data);
  Object visit(InsertBody node, Object data);
  Object visit(CreateVertexStatementEmptyNoTarget node, Object data);
  Object visit(CreateVertexStatementEmpty node, Object data);
  Object visit(CreateVertexStatement node, Object data);
  Object visit(CreateVertexStatementNoTarget node, Object data);
  Object visit(CreateEdgeStatement node, Object data);
  Object visit(InputParameter node, Object data);
  Object visit(PositionalParameter node, Object data);
  Object visit(NamedParameter node, Object data);
  Object visit(Projection node, Object data);
  Object visit(ProjectionItem node, Object data);
  Object visit(NestedProjection node, Object data);
  Object visit(NestedProjectionItem node, Object data);
  Object visit(ArraySelector node, Object data);
  Object visit(ArrayNumberSelector node, Object data);
  Object visit(ArraySingleValuesSelector node, Object data);
  Object visit(ArrayRangeSelector node, Object data);
  Object visit(Alias node, Object data);
  Object visit(RecordAttribute node, Object data);
  Object visit(FunctionCall node, Object data);
  Object visit(MethodCall node, Object data);
  Object visit(LevelZeroIdentifier node, Object data);
  Object visit(SuffixIdentifier node, Object data);
  Object visit(BaseIdentifier node, Object data);
  Object visit(Modifier node, Object data);
  Object visit(Expression node, Object data);
  Object visit(ArrayConcatExpression node, Object data);
  Object visit(ArrayConcatExpressionElement node, Object data);
  Object visit(MathExpression node, Object data);
  Object visit(FirstLevelExpression node, Object data);
  Object visit(ParenthesisExpression node, Object data);
  Object visit(BaseExpression node, Object data);
  Object visit(FromClause node, Object data);
  Object visit(LetClause node, Object data);
  Object visit(LetItem node, Object data);
  Object visit(FromItem node, Object data);
  Object visit(Bucket node, Object data);
  Object visit(BucketList node, Object data);
  Object visit(SchemaIdentifier node, Object data);
  Object visit(IndexName node, Object data);
  Object visit(IndexIdentifier node, Object data);
  Object visit(WhereClause node, Object data);
  Object visit(OrBlock node, Object data);
  Object visit(AndBlock node, Object data);
  Object visit(NotBlock node, Object data);
  Object visit(ParenthesisBlock node, Object data);
  Object visit(ConditionBlock node, Object data);
  Object visit(CompareOperator node, Object data);
  Object visit(LtOperator node, Object data);
  Object visit(GtOperator node, Object data);
  Object visit(NeOperator node, Object data);
  Object visit(NeqOperator node, Object data);
  Object visit(GeOperator node, Object data);
  Object visit(LeOperator node, Object data);
  Object visit(LikeOperator node, Object data);
  Object visit(NearOperator node, Object data);
  Object visit(WithinOperator node, Object data);
  Object visit(ScAndOperator node, Object data);
  Object visit(ContainsKeyOperator node, Object data);
  Object visit(ContainsValueOperator node, Object data);
  Object visit(EqualsCompareOperator node, Object data);
  Object visit(RightBinaryCondition node, Object data);
  Object visit(BinaryCondition node, Object data);
  Object visit(ContainsValueCondition node, Object data);
  Object visit(InstanceofCondition node, Object data);
  Object visit(IndexMatchCondition node, Object data);
  Object visit(BetweenCondition node, Object data);
  Object visit(IsNullCondition node, Object data);
  Object visit(IsNotNullCondition node, Object data);
  Object visit(IsDefinedCondition node, Object data);
  Object visit(IsNotDefinedCondition node, Object data);
  Object visit(ContainsCondition node, Object data);
  Object visit(InOperator node, Object data);
  Object visit(InCondition node, Object data);
  Object visit(NotInCondition node, Object data);
  Object visit(ContainsAllCondition node, Object data);
  Object visit(ContainsAnyCondition node, Object data);
  Object visit(ContainsTextCondition node, Object data);
  Object visit(MatchesCondition node, Object data);
  Object visit(OrderBy node, Object data);
  Object visit(GroupBy node, Object data);
  Object visit(Unwind node, Object data);
  Object visit(Limit node, Object data);
  Object visit(Skip node, Object data);
  Object visit(Batch node, Object data);
  Object visit(Timeout node, Object data);
  Object visit(Wait node, Object data);
  Object visit(Retry node, Object data);
  Object visit(PCollection node, Object data);
  Object visit(TraverseProjectionItem node, Object data);
  Object visit(Json node, Object data);
  Object visit(MatchExpression node, Object data);
  Object visit(MatchPathItem node, Object data);
  Object visit(MatchPathItemFirst node, Object data);
  Object visit(MultiMatchPathItem node, Object data);
  Object visit(MultiMatchPathItemArrows node, Object data);
  Object visit(MatchFilter node, Object data);
  Object visit(MatchFilterItem node, Object data);
  Object visit(OutPathItem node, Object data);
  Object visit(InPathItem node, Object data);
  Object visit(BothPathItem node, Object data);
  Object visit(OutPathItemOpt node, Object data);
  Object visit(InPathItemOpt node, Object data);
  Object visit(BothPathItemOpt node, Object data);
  Object visit(TruncateTypeStatement node, Object data);
  Object visit(TruncateBucketStatement node, Object data);
  Object visit(TruncateRecordStatement node, Object data);
  Object visit(CreateDocumentTypeStatement node, Object data);
  Object visit(AlterTypeStatement node, Object data);
  Object visit(DropTypeStatement node, Object data);
  Object visit(IfNotExists node, Object data);
  Object visit(CreatePropertyStatement node, Object data);
  Object visit(CreatePropertyAttributeStatement node, Object data);
  Object visit(AlterPropertyStatement node, Object data);
  Object visit(DropPropertyStatement node, Object data);
  Object visit(CreateIndexStatement node, Object data);
  Object visit(RebuildIndexStatement node, Object data);
  Object visit(DropIndexStatement node, Object data);
  Object visit(CreateBucketStatement node, Object data);
  Object visit(AlterBucketStatement node, Object data);
  Object visit(DropBucketStatement node, Object data);
  Object visit(AlterDatabaseStatement node, Object data);
  Object visit(CommandLineOption node, Object data);
  Object visit(ExplainStatement node, Object data);
  Object visit(ProfileStatement node, Object data);
  Object visit(LetStatement node, Object data);
  Object visit(BeginStatement node, Object data);
  Object visit(CommitStatement node, Object data);
  Object visit(RollbackStatement node, Object data);
  Object visit(ReturnStatement node, Object data);
  Object visit(IfStatement node, Object data);
  Object visit(SleepStatement node, Object data);
}
/* JavaCC - OriginalChecksum=a3d1f02279c43f6a885410002709c1e4 (do not edit this line) */
