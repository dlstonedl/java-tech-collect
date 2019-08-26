文档
ObjectId
复合主键：主键是一个文档

直接匹配
比较操作符
逻辑操作符
字段操作符：$exist, $type
数组操作符：$all, $eleMatch
运算操作符：正则表达式

文档游标：
1. skip在limit之前执行；
2. sort在skip和limit之前执行；

文档投影: 除文档主键外，不可以在投影中混合使用包含与不包含；
数组投影: $slice, $eleMatch, $

更新文档
文档主键_id不可更改；
update整篇文档，只有第一篇符合query筛选条件的文档会被更新；

更新操作符：$set, $unset, $rename, $inc, $mul, $min, $max

数组更新操作符：$addToSet, $pop, $pull, $push

mongo只能保证单个文档操作的原子性，不能保证多个文档操作的原子性

### 聚合操作,aggregate
聚合管道，聚合表达式，聚合阶段，聚合操作符

聚合表达式：
字段路径表达式：$, .
系统变量表达式：$$
常量表达式：$literal

聚合管道阶段：
$project, $match, $limit, $skip, $unwind, $sort, $lookup, $group, $out







